package cn.ibizlab.pms.core.runtime;

import cn.ibizlab.pms.util.domain.EntityMP;
import cn.ibizlab.pms.util.filter.QueryWrapperContext;
import cn.ibizlab.pms.util.filter.ScopeUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import cn.ibizlab.pms.util.filter.SearchContextBase;
import cn.ibizlab.pms.util.helper.QueryContextHelper;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import cn.ibizlab.pms.util.security.UAADEAuthority;
import cn.ibizlab.pms.util.client.IBZWFFeignClient;
import cn.ibizlab.pms.util.domain.DTOBase;
import cn.ibizlab.pms.util.domain.DataAccessMode;
import cn.ibizlab.pms.util.errors.BadRequestAlertException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import lombok.extern.slf4j.Slf4j;
import net.ibizsys.model.dataentity.IPSDataEntity;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEFSearchMode;
import net.ibizsys.model.dataentity.defield.IPSLinkDEField;
import net.ibizsys.model.dataentity.der.IPSDER1N;
import net.ibizsys.model.dataentity.der.IPSDERBase;
import net.ibizsys.model.dataentity.ds.IPSDEDataQuery;
import net.ibizsys.model.dataentity.ds.IPSDEDataQueryCode;
import net.ibizsys.model.dataentity.ds.IPSDEDataQueryCodeCond;
import net.ibizsys.model.dataentity.ds.IPSDEDataSet;
import net.ibizsys.model.dataentity.priv.IPSDEOPPriv;
import net.ibizsys.model.dataentity.priv.IPSDEUserRole;
import net.ibizsys.model.dataentity.priv.IPSDEUserRoleOPPriv;
import net.ibizsys.model.dataentity.wf.IPSDEWF;
import net.ibizsys.runtime.IDynaInstRuntime;
import net.ibizsys.runtime.ISystemUtilRuntime;
import net.ibizsys.runtime.dataentity.DataEntityRuntimeException;
import net.ibizsys.runtime.dataentity.action.CheckKeyStates;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import net.ibizsys.runtime.ISystemRuntime;
import net.ibizsys.runtime.dataentity.IDataEntityRuntime;
import net.ibizsys.runtime.security.DataAccessActions;
import net.ibizsys.runtime.security.DataRanges;
import net.ibizsys.runtime.security.IUserContext;
import net.ibizsys.runtime.util.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.flowable.ui.common.service.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import javax.annotation.PostConstruct;

@Slf4j
public abstract class SystemDataEntityRuntimeBase extends net.ibizsys.runtime.dataentity.DataEntityRuntimeBase {

    /**
     * 审计日志
     */
    @Value("${ibiz.deauditlog:false}")
    protected boolean deauditlog;

    /**
     * 主键字段
     */
    protected String key;

    /**
     * 组织控制字段
     */
    protected String orgIdField;

    /**
     * 部门控制字段
     */
    protected String deptIdField;

    /**
     * 默认实体能力
     */
    protected List<UAADEAuthority> defaultAuthorities = new ArrayList<>();

    /**
     * 默认用户实体能力
     */
    protected List<UAADEAuthority> userAuthorities = null;

    /**
     * 默认管理员实体能力
     */
    protected List<UAADEAuthority> adminAuthorities = null;

    /**
     * 全部操作标识
     */
    protected Map<String, Integer> allOPPrivs = new HashMap<>();

    /**
    * 工作流（排除属性）
    */
    private final static String EXCLUDEFIELDS = "1";
    /**
    * 工作流（包含属性）
    */
    private final static String INCLUDEFIELDS = "2";

    @Autowired
    ISystemRuntime system;

    @Autowired
    IBZWFFeignClient wfClient;

    @Override
    public ISystemRuntime getSystemRuntime() {
        return system;
    }

    @Override
    protected IUserContext getUserContext() {
        return AuthenticationUser.getAuthenticationUser();
    }

    @PostConstruct
    public void init() {
        this.getSystemRuntime().registerDataEntityRuntime(this);
    }

    @Override
    protected void onInit() throws Exception {
        super.onInit();
        this.loadDefaultDEUserRoles();
        this.loadAllOPPrivs();
    }

    /**
     * 加载系统默认角色
     * 
     * @throws Exception
     */
    protected void loadDefaultDEUserRoles() throws Exception {
        List<IPSDEUserRole> psDEUserRoles = this.getDefaultPSDEUserRoles();
        if (psDEUserRoles != null) {
            for (IPSDEUserRole psDEUserRole : psDEUserRoles) {
                UAADEAuthority authority = new UAADEAuthority();
                authority.setName(psDEUserRole.getName());
                authority.setEntity(this.getName());
                authority.setEnableorgdr(psDEUserRole.isEnableOrgDR() ? 1 : 0);
                authority.setOrgdr(psDEUserRole.getOrgDR());
                authority.setEnabledeptdr(psDEUserRole.isEnableSecDR() ? 1 : 0);
                authority.setDeptdr(psDEUserRole.getSecDR());
                authority.setEnabledeptbc(psDEUserRole.isEnableSecBC() ? 1 : 0);
                authority.setDeptbc(psDEUserRole.getSecBC());
                authority.setBscope(psDEUserRole.getCustomCond());
                //如果有自定义查询 以结果集有限 ，替换自定义条件
                IPSDEDataSet psdeDataSet = psDEUserRole.getPSDEDataSet();
                if (psdeDataSet != null) {
                    List<IPSDEDataQuery> psdeDataQueries = psdeDataSet.getPSDEDataQueries();
                    if (psdeDataQueries != null) {
                        for (IPSDEDataQuery ipsdeDataQuery : psdeDataQueries) {
                            List<IPSDEDataQueryCode> psdeDataQueryCodes = ipsdeDataQuery.getAllPSDEDataQueryCodes();
                            if (psdeDataQueryCodes != null) {
                                for (IPSDEDataQueryCode psdeDataQueryCode : psdeDataQueryCodes) {
                                    if (this.getDBType().equals(psdeDataQueryCode.getDBType())) {
                                        List<IPSDEDataQueryCodeCond> psdeDataQueryCodeConds = psdeDataQueryCode.getPSDEDataQueryCodeConds();
                                        if (psdeDataQueryCodeConds != null) {
                                            String strBScope = "";
                                            for (int i = 0; i < psdeDataQueryCodeConds.size(); i++) {
                                                IPSDEDataQueryCodeCond psdeDataQueryCodeCond = psdeDataQueryCodeConds.get(i);
                                                if (i > 0)
                                                    strBScope += " AND ";
                                                strBScope += QueryContextHelper.contextParamConvert(psdeDataQueryCodeCond.getCustomCond());
                                            }
                                            authority.setDataset(true);
                                            authority.setBscope(strBScope);
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                List<Map<String, String>> deActions = new ArrayList<>();
                java.util.List<IPSDEUserRoleOPPriv> psDEUserRoleOPPrivs = psDEUserRole.getPSDEUserRoleOPPrivs();
                if (psDEUserRoleOPPrivs != null) {
                    for (IPSDEUserRoleOPPriv psDEUserRoleOPPriv : psDEUserRoleOPPrivs) {
                        Map<String, String> deAction = new HashMap<>();
                        deAction.put(psDEUserRoleOPPriv.getDataAccessAction(), org.apache.commons.lang3.StringUtils.isBlank(psDEUserRoleOPPriv.getCustomCond()) ? "" : psDEUserRoleOPPriv.getCustomCond());
                        deActions.add(deAction);
                    }
                }
                authority.setDeAction(deActions);
                this.defaultAuthorities.add(authority);
            }
        }
    }

    /**
     * 加载操作标识
     * 
     * @throws Exception
     */
    protected void loadAllOPPrivs() throws Exception {
        List<IPSDEUserRole> psDEUserRoles = this.getPSDataEntity().getAllPSDEUserRoles();
        if (psDEUserRoles != null) {
            for (IPSDEUserRole psDEUserRole : psDEUserRoles) {
                java.util.List<IPSDEUserRoleOPPriv> psDEUserRoleOPPrivs = psDEUserRole.getPSDEUserRoleOPPrivs();
                if (psDEUserRoleOPPrivs != null) {
                    List<Map<String, String>> deActions = new ArrayList<>();
                    for (IPSDEUserRoleOPPriv psDEUserRoleOPPriv : psDEUserRoleOPPrivs) {
                        if (!allOPPrivs.containsKey(psDEUserRoleOPPriv.getDataAccessAction())) {
                            allOPPrivs.put(psDEUserRoleOPPriv.getDataAccessAction(), 0);
                        }
                    }
                }
            }
        }
    }

    @Override
    public Object getFieldValue(IEntityBase entityBase, IPSDEField ipsdeField) {
        IEntity entity = (IEntity) entityBase;
        if (entity == null)
            return null;
        return entity.get(ipsdeField.getCodeName());
    }

    @Override
    public void setFieldValue(IEntityBase entityBase, IPSDEField ipsdeField, Object o1) {
        IEntity entity = (IEntity) entityBase;
        if (entity == null)
            return;
        entity.set(ipsdeField.getCodeName(), o1);
    }

    @Override
    public boolean containsFieldValue(IEntityBase entityBase, IPSDEField ipsdeField) {
        IEntity entity = (IEntity) entityBase;
        if (entity == null)
            return false;
        return entity.contains(ipsdeField.getCodeName());
    }

    @Override
    public void resetFieldValue(IEntityBase entityBase, IPSDEField ipsdeField) {
        IEntity entity = (IEntity) entityBase;
        if (entity == null)
            return;
        entity.reset(ipsdeField.getCodeName());
    }

    @Override
    public IEntityBase[] getNestedDERValue(IEntityBase iEntityBase, IPSDERBase iPSDERBase) {
        IEntity entity = (IEntity) iEntityBase;
        if (iPSDERBase instanceof IPSDER1N) {
            IPSDER1N iPSDER1N = (IPSDER1N) iPSDERBase;
            String strNestField = iPSDER1N.getMinorCodeName();
            if (StringUtils.isBlank(strNestField)) {
                try {
                    strNestField = iPSDER1N.getMinorPSDataEntity().getCodeName();
                } catch (Exception e) {
                    throw new DataEntityRuntimeException(String.format("获取嵌套属性发生错误：%s", e.getMessage()), Errors.INTERNALERROR, this);
                }
            }
            Object obj = entity.get(strNestField.toLowerCase());
            if (obj != null) {
                List<IEntityBase> minorDatas = (List<IEntityBase>) obj;
                return minorDatas.toArray(new IEntityBase[minorDatas.size()]);
            }
        }
        return null;
    }

    @Override
    public void setNestedDERValue(IEntityBase iEntityBase, IPSDERBase iPSDERBase, IEntityBase[] value) {
        IEntity entity = (IEntity) iEntityBase;
        if (iPSDERBase instanceof IPSDER1N) {
            IPSDER1N iPSDER1N = (IPSDER1N) iPSDERBase;
            String strNestField = iPSDER1N.getMinorCodeName();
            if (StringUtils.isBlank(strNestField)) {
                try {
                    strNestField = iPSDER1N.getMinorPSDataEntity().getCodeName();
                } catch (Exception e) {
                    throw new DataEntityRuntimeException(String.format("获取嵌套属性发生错误：%s", e.getMessage()), Errors.INTERNALERROR, this);
                }
            }
            entity.set(strNestField.toLowerCase(), Arrays.asList(value));
        }
    }

    @Override
    public boolean containsNestedDERValue(IEntityBase iEntityBase, IPSDERBase iPSDERBase){
        IEntity entity = (IEntity) iEntityBase;
        if (iPSDERBase instanceof IPSDER1N) {
            IPSDER1N iPSDER1N = (IPSDER1N) iPSDERBase;
            String strNestField = iPSDER1N.getMinorCodeName();
            if (StringUtils.isBlank(strNestField)) {
                try {
                    strNestField = iPSDER1N.getMinorPSDataEntity().getCodeName();
                } catch (Exception e) {
                    throw new DataEntityRuntimeException(String.format("获取嵌套属性发生错误：%s", e.getMessage()), Errors.INTERNALERROR, this);
                }
            }
            return entity.contains(strNestField.toLowerCase());
        }
        return false;
    }

    @Override
    public void resetNestedDERValue(IEntityBase iEntityBase, IPSDERBase iPSDERBase) {
        IEntity entity = (IEntity) iEntityBase;
        if (iPSDERBase instanceof IPSDER1N) {
            IPSDER1N iPSDER1N = (IPSDER1N) iPSDERBase;
            String strNestField = iPSDER1N.getMinorCodeName();
            if (StringUtils.isBlank(strNestField)) {
                try {
                    strNestField = iPSDER1N.getMinorPSDataEntity().getCodeName();
                } catch (Exception e) {
                    throw new DataEntityRuntimeException(String.format("获取嵌套属性发生错误：%s", e.getMessage()), Errors.INTERNALERROR, this);
                }
            }
            entity.reset(strNestField.toLowerCase());
        }
    }

    @Override
    public List<? extends IEntityBase> select(String strCondition) {
        QueryWrapperContext context = (QueryWrapperContext) createSearchContext();
        context.getSelectCond().and(ScopeUtils.parse(this, strCondition));
        return this.select(context);
    }

    @Override
    public IEntityBase selectOne(String strCondition) {
        QueryWrapperContext context = (QueryWrapperContext) createSearchContext();
        context.getSelectCond().and(ScopeUtils.parse(this, strCondition));
        return selectOne(context);
    }

    @Override
    public IEntityBase selectOne(ISearchContextBase iSearchContextBase) {
        //单条数据查询，多条数数据时 返回第一条
        QueryWrapperContext context = (QueryWrapperContext) iSearchContextBase;
        context.setSize(1);
        List domains = this.select(context);
        if (domains.size() == 0)
            return null;
        return (IEntityBase) domains.get(0);
    }

    @Override
    public boolean existsData(ISearchContextBase iSearchContextBase) {
        QueryWrapperContext context = (QueryWrapperContext) iSearchContextBase;
        List domains = this.select(context);
        return domains.size() > 0;
    }

    @Override
    public void setSearchCondition(ISearchContextBase iSearchContextBase, IPSDEField iPSDEField, String strCondition, Object objValue) {
        try {
            String strSearchField = String.format("n_%s_%s", iPSDEField.getName().toLowerCase(), strCondition.toLowerCase());
            Field searchField = iSearchContextBase.getClass().getDeclaredField(strSearchField);
            searchField.setAccessible(true);
            if (strCondition.equals(Conditions.ISNULL) || strCondition.equals(Conditions.ISNULL)) {
                searchField.set(iSearchContextBase, "true");
            } else {
                searchField.set(iSearchContextBase, objValue);
            }
        } catch (NoSuchFieldException e) {
            log.warn(String.format("不存在属性[%s]的查询条件[%s]。", iPSDEField.getLogicName(), strCondition));
        } catch (IllegalAccessException e) {
            log.warn(String.format("设置属性[%s]的查询条件[%s]值[%s]发生错误：%s", iPSDEField.getLogicName(), strCondition, objValue, e.getMessage()));
        }
    }

    @Override
    public void setSearchCustomCondition(ISearchContextBase iSearchContextBase, String strCustomCondition) {
        QueryWrapperContext context = (QueryWrapperContext) iSearchContextBase;
        context.getSelectCond().and(ScopeUtils.parse(this, strCustomCondition));
    }

    @Override
    public void setSearchCondition(ISearchContextBase iSearchContextBase, IPSDEField iPSDEField, IPSDEFSearchMode iPSDEFSearchMode, Object objValue) {
        try {
            String strSearchField = iPSDEFSearchMode.getName().toLowerCase();
            Field searchField = iSearchContextBase.getClass().getDeclaredField(strSearchField);
            searchField.setAccessible(true);
            if (iPSDEFSearchMode.getValueOP().equals(Conditions.ISNULL) || iPSDEFSearchMode.getValueOP().equals(Conditions.ISNULL)) {
                searchField.set(iSearchContextBase, "true");
            } else {
                searchField.set(iSearchContextBase, objValue);
            }
        } catch (NoSuchFieldException e) {
            log.warn(String.format("不存在搜索项[%s]。", iPSDEFSearchMode.getName()));
        } catch (IllegalAccessException e) {
            log.warn(String.format("设置搜索项[%s]值[%s]发生错误：%s", iPSDEFSearchMode.getName(), objValue, e.getMessage()));
        }
    }

    @Override
    public Object getSearchCondition(ISearchContextBase iSearchContextBase, IPSDEField iPSDEField, IPSDEFSearchMode iPSDEFSearchMode) {
        try {
            String strSearchField = iPSDEFSearchMode.getName().toLowerCase();
            Field searchField = iSearchContextBase.getClass().getDeclaredField(strSearchField);
            searchField.setAccessible(true);
            if (iPSDEFSearchMode.getValueOP().equals(Conditions.ISNULL) || iPSDEFSearchMode.getValueOP().equals(Conditions.ISNULL)) {
                searchField.set(iSearchContextBase, "true");
            } else {
                return searchField.get(iSearchContextBase);
            }
        } catch (NoSuchFieldException e) {
            log.warn(String.format("不存在搜索项[%s]。", iPSDEFSearchMode.getName()));
        } catch (IllegalAccessException e) {
            log.warn(String.format("获取搜索项[%s]值发生错误：%s", iPSDEFSearchMode.getName(), e.getMessage()));
        }
        return null;
    }

    @Override
    public Object getSearchCondition(ISearchContextBase iSearchContextBase, IPSDEField iPSDEField, String strCondition) {
        return null;
    }

    @Override
    public void setSearchPaging(ISearchContextBase iSearchContextBase, int nPageIndex, int nPageSize, Sort sort) {
        QueryWrapperContext context = (QueryWrapperContext) iSearchContextBase;
        context.setPageable(null);
        context.setPage(nPageIndex > 0 ? nPageIndex - 1 : 0);
        context.setSize(nPageSize);
        context.setPageSort(sort);
    }

    @Override
    public void setSearchDataContext(ISearchContextBase iSearchContextBase, String strParam, Object objValue) {
        //throw new DataEntityRuntimeException("没有实现", Errors.NOTIMPL, this);
    }
    
    @Override
    public int checkKeyState(Object objKey) {
        QueryWrapperContext context = (QueryWrapperContext) this.createSearchContext();
        context.getSelectCond().eq(this.getKey(), objKey);
        List domains = this.select(context);
        if (domains.size() > 0)
            return CheckKeyStates.EXIST;
        return CheckKeyStates.NOTEXIST;
    }

    @Override
    protected void onWFStart(Object arg0, IPSDEAction iPSDEAction, IPSDEWF iPSDEWF, IDynaInstRuntime iDynaInstRuntime, Object joinPoint) throws Throwable {
        
    }

    abstract public SearchContextBase createSearchContext();

    /**
     * 判断是否含有统一资源标识
     *
     * @param uniResTag
     * @return
     */
    public boolean testUnires(String uniResTag) {
        boolean check = false;
        this.prepare();
        check = ((SystemRuntime) this.getSystemRuntime()).testUniRes(uniResTag);
        if (deauditlog) {
            if (!check) {
                //this.getSystemRuntime().logAudit(ISystemUtilRuntime.LOGLEVEL_WARN, "权限检查", String.format("检查统一资源[%s]权限失败。", uniResTag), this.getUserContext().getUserid(), this.getUserContext().getRemoteaddress(), null);
            } else {
                //this.getSystemRuntime().logAudit(ISystemUtilRuntime.LOGLEVEL_WARN, "权限检查", String.format("检查统一资源[%s]权限通过。", uniResTag), this.getUserContext().getUserid(), this.getUserContext().getRemoteaddress(), null);
            }
        }
        return check;
    }

    /**
     * 判断是否含操作能力
     *
     * @param action 操作能力
     * @return
     */
    public boolean quickTest(String action) {
        if (DataAccessActions.DENY.equals(action)) {
            return false;
        }
        if (DataAccessActions.NONE.equals(action)) {
            return true;
        }
        boolean check = true;
        this.prepare();
        if (this.getUserContext().isSuperuser())
            return true;
        if (testUnires(action))
            return true;
        if (this.getUAAAuthorities(action).size() == 0)
            check = false;
        if (deauditlog) {
            if (!check) {
                this.getSystemRuntime().logAudit(ISystemUtilRuntime.LOGLEVEL_WARN, "权限检查", String.format("检查实体[%s]操作[%s]权限失败。", this.getName(), action), this.getUserContext().getUserid(), this.getUserContext().getRemoteaddress(), null);
            } else {
                this.getSystemRuntime().logAudit(ISystemUtilRuntime.LOGLEVEL_WARN, "权限检查", String.format("检查实体[%s]操作[%s]权限通过。", this.getName(), action), this.getUserContext().getUserid(), this.getUserContext().getRemoteaddress(), null);
            }
        }
        return check;
    }

    /**
     * 判断数据对象是否含有操作能力
     *
     * @param key    数据
     * @param action 操作能力
     * @return
     */
    @SneakyThrows
    public boolean test(Serializable key, String action) {
        if (DataAccessActions.DENY.equals(action)) {
            return false;
        }
        if (DataAccessActions.NONE.equals(action)) {
            return true;
        }
        if(ObjectUtils.isEmpty(key) || "undefined".equals(key)){
            return false;
        }
        boolean check = true;
        int accessMode;
        this.prepare();
        if (this.getUserContext().isSuperuser())
            return true;
        //判断是否在流程中
        if (this.isEnableWF()) {
            if (DataAccessActions.READ.equals(action)) {
                accessMode = wfClient.getDataAccessMode(AuthenticationUser.getAuthenticationUser().getSrfsystemid(), this.getPSDataEntity().getCodeName().toLowerCase(), key);
                if ((accessMode & 1) > 0) {
                    return true;
                }
            } else if (DataAccessActions.UPDATE.equals(action) && testDataInWF(this.getSimpleEntity(key))) {
                accessMode = wfClient.getDataAccessMode(AuthenticationUser.getAuthenticationUser().getSrfsystemid(), this.getPSDataEntity().getCodeName().toLowerCase(), key);
                check = (accessMode & 2) > 0;
                if (deauditlog) {
                    if (!check) {
                        this.getSystemRuntime().logAudit(ISystemUtilRuntime.LOGLEVEL_WARN, "权限检查", String.format("流程检查实体[%s][%s]操作[%s]权限失败。", this.getName(), key, action), this.getUserContext().getUserid(), this.getUserContext().getRemoteaddress(), null);
                    } else {
                        this.getSystemRuntime().logAudit(ISystemUtilRuntime.LOGLEVEL_WARN, "权限检查", String.format("流程检查实体[%s][%s]操作[%s]权限通过。", this.getName(), key, action), this.getUserContext().getUserid(), this.getUserContext().getRemoteaddress(), null);
                    }
                }
                return check;
            }
        }
        if (testUnires(action))
            return true;
        //检查能力
        if (!quickTest(action)) {
            return false;
        }

        //检查数据范围
        QueryWrapperContext context = (QueryWrapperContext) this.createSearchContext();
        context.getSelectCond().eq(this.getKey(), key);
        addAuthorityConditions(context, action);
        List domains = this.select(context);
        if (domains.size() == 0) {
            check = false;
            if (deauditlog) {
                if (!check) {
                    this.getSystemRuntime().logAudit(ISystemUtilRuntime.LOGLEVEL_WARN, "权限检查", String.format("检查实体[%s]数据:[%s]操作[%s]权限失败。", this.getName(), key, action), this.getUserContext().getUserid(), this.getUserContext().getRemoteaddress(), null);
                } else {
                    this.getSystemRuntime().logAudit(ISystemUtilRuntime.LOGLEVEL_WARN, "权限检查", String.format("检查实体[%s]数据:[%s]操作[%s]权限通过。", this.getName(), key, action), this.getUserContext().getUserid(), this.getUserContext().getRemoteaddress(), null);
                }
            }
            return check;
        }
        try {
            check = testDataAccessAction(domains.get(0), action);
            if (deauditlog) {
                if (!check) {
                    this.getSystemRuntime().logAudit(ISystemUtilRuntime.LOGLEVEL_WARN, "权限检查", String.format("检查实体[%s]数据:[%s]操作[%s]主状态控制失败。", this.getName(), key, action), this.getUserContext().getUserid(), this.getUserContext().getRemoteaddress(), null);
                }
            }
            return check;
        } catch (Exception e) {
            this.getSystemRuntime().logAudit(ISystemUtilRuntime.LOGLEVEL_ERROR, "权限检查", String.format("检查实体[%s]数据:[%s]操作[%s]权限发生错误：%s", this.getName(), key, action, e.getMessage()), this.getUserContext().getUserid(), this.getUserContext().getRemoteaddress(), null);
            return false;
        }
    }

    /**
     * 判断数据集合是否含有操作能力
     *
     * @param keys   数据集合
     * @param action 操作能力
     * @return
     */
    public boolean test(List<Serializable> keys, String action) {
        this.prepare();
        if (this.getUserContext().isSuperuser())
            return true;
        if (testUnires(action))
            return true;
        //检查能力
        if (!quickTest(action))
            return false;

        //检查数据范围
        QueryWrapperContext context = (QueryWrapperContext) this.createSearchContext();
        context.getSelectCond().in(this.getKey(), keys);
        addAuthorityConditions(context, action);

        List domains = this.select(context);
        if (domains.size() != keys.size()) {
            return false;
        }
        try {
            for (Object domain : domains) {
                if (testDataAccessAction(domain, action)) {
                    return false;
                }
            }
        } catch (Exception e) {
            log.error(String.format("[%s]数据:[%s]权限检查错误：%s", this.getName(), keys, e.getMessage()));
            return false;
        }
        return true;
    }

    /**
     * 根据父判断时候含有Paction能力 
     *
     * @param PDEName
     * @param PKey
     * @param action
     * @return
     */
    public boolean test(String PDEName, Serializable PKey, String Paction, String action) throws Exception {
        this.prepare();
        try {
            IDataEntityRuntime pDataEntityRuntime = getSystemRuntime().getDataEntityRuntime(PDEName);
            return ((SystemDataEntityRuntimeBase) pDataEntityRuntime).test(PKey, Paction);
        } catch (Exception e) {
            log.error(String.format("获取依赖实体[%s]运行对象错误：%s", PDEName, e.getLocalizedMessage()));
            throw e;
        }
    }

    /**
     * 根据父判断时候含有action能力
     *
     * @param PDEName
     * @param PKey
     * @param key
     * @param action
     * @return
     */
    public boolean test(String PDEName, Serializable PKey, String Paction, Serializable key, String action) throws Exception {
        this.prepare();
        try {
            IDataEntityRuntime pDataEntityRuntime = getSystemRuntime().getDataEntityRuntime(PDEName);
            return ((SystemDataEntityRuntimeBase) pDataEntityRuntime).test(PKey, Paction);
        } catch (Exception e) {
            log.error(String.format("获取依赖实体[%s]运行对象错误：%s", PDEName, e.getLocalizedMessage()));
            throw e;
        }
    }

    /**
     * 附加操作权限条件
     *
     * @param context
     * @param action
     */
    public void addAuthorityConditions(QueryWrapperContext context, String action) {
        if (this.getUserContext().isSuperuser())
            return;
        if (testUnires(action))
            return;

        List<UAADEAuthority> authorities = this.getUAAAuthorities(action);
        Consumer<QueryWrapper> authorityConditions = authorityCondition -> {
            for (UAADEAuthority uaadeAuthority : authorities) {
                //未设置权限能力范围 拒绝操作
                if ((StringUtils.isBlank(this.getOrgIdField()) || (StringUtils.isNotBlank(this.getOrgIdField()) && ((uaadeAuthority.getEnableorgdr() == null || uaadeAuthority.getEnableorgdr() == 0) || (uaadeAuthority.getEnableorgdr() == 1 && (uaadeAuthority.getOrgdr() == null || uaadeAuthority.getOrgdr() == 0)))))
                        && (StringUtils.isBlank(this.getDeptIdField()) || (StringUtils.isNotBlank(this.getDeptIdField()) && ((uaadeAuthority.getEnabledeptdr() == null || uaadeAuthority.getEnabledeptdr() == 0) || (uaadeAuthority.getEnabledeptdr() == 1 && (uaadeAuthority.getDeptdr() == null || uaadeAuthority.getDeptdr() == 0)))))
                        && (StringUtils.isBlank(uaadeAuthority.getBscope()))
                ) {
                    Consumer<QueryWrapper> denyDataCondition = dataCondition -> {
                        dataCondition.apply("1 <> 1");
                    };
                    authorityCondition.or(denyDataCondition);
                } else {
                    Consumer<QueryWrapper> dataCondition = genAuthorityConditions(uaadeAuthority, action);
                    authorityCondition.or(dataCondition);
                }
            }
        };

        if (authorities.size() > 0) {
            context.getSelectCond().and(authorityConditions);
        }
    }

    /**
     * 加载运行时能力
     *
     * @param uaadeAuthority
     * @param action
     * @return
     */
    protected Consumer<QueryWrapper> genAuthorityConditions(UAADEAuthority uaadeAuthority, String action) {
        AuthenticationUser curUser = AuthenticationUser.getAuthenticationUser();
        Map<String, Set<String>> userInfo = curUser.getOrgInfo();
        Set<String> orgParent = userInfo.get("parentorg");
        Set<String> orgChild = userInfo.get("suborg");
        Set<String> orgDeptParent = userInfo.get("parentdept");
        Set<String> orgDeptChild = userInfo.get("subdept");
        Consumer<QueryWrapper> authorityConditions = authorityCondition -> {
            //组织范围
            if (uaadeAuthority.getEnableorgdr() != null && uaadeAuthority.getEnableorgdr() == 1
                    && uaadeAuthority.getOrgdr() != null && uaadeAuthority.getOrgdr() > 0) {
                //当前机构
                if (StringUtils.isNotBlank(this.getOrgIdField()) && (DataRanges.ORG_CURRENT & uaadeAuthority.getOrgdr()) > 0) {
                    Consumer<QueryWrapper> org = orgQw -> {
                        orgQw.eq(this.getOrgIdField(), curUser.getOrgid());
                    };
                    authorityCondition.or(org);
                }
                //上级机构
                if (StringUtils.isNotBlank(this.getOrgIdField()) && (DataRanges.ORG_PARENT & uaadeAuthority.getOrgdr()) > 0) {
                    if (orgParent.size() > 0) {
                        Consumer<QueryWrapper> org = orgQw -> {
                            orgQw.in(this.getOrgIdField(), orgParent);
                        };
                        authorityCondition.or(org);
                    }
                }
                //下级机构
                if (StringUtils.isNotBlank(this.getOrgIdField()) && (DataRanges.ORG_CHILD & uaadeAuthority.getOrgdr()) > 0) {
                    if (orgChild.size() > 0) {
                        Consumer<QueryWrapper> org = orgQw -> {
                            orgQw.in(this.getOrgIdField(), orgChild);
                        };
                        authorityCondition.or(org);
                    }
                }
                //无值
                if (StringUtils.isNotBlank(this.getOrgIdField()) && (DataRanges.ORG_NULL & uaadeAuthority.getOrgdr()) > 0) {
                    Consumer<QueryWrapper> org = orgQw -> {
                        orgQw.isNull(this.getOrgIdField());
                    };
                    authorityCondition.or(org);
                }
            }

            //部门范围
            if (uaadeAuthority.getEnabledeptdr() != null && uaadeAuthority.getEnabledeptdr() == 1
                    && uaadeAuthority.getDeptdr() != null && uaadeAuthority.getDeptdr() > 0) {
                //当前
                if (StringUtils.isNotBlank(this.getDeptIdField()) && (DataRanges.SECTOR_CURRENT & uaadeAuthority.getDeptdr()) > 0) {
                    Consumer<QueryWrapper> dept = deptQw -> {
                        deptQw.eq(this.getDeptIdField(), curUser.getDeptid());
                    };
                    authorityCondition.or(dept);
                }
                //上级
                if (StringUtils.isNotBlank(this.getDeptIdField()) && (DataRanges.SECTOR_PARENT & uaadeAuthority.getDeptdr()) > 0) {
                    if (orgDeptParent.size() > 0) {
                        Consumer<QueryWrapper> dept = deptQw -> {
                            deptQw.in(this.getDeptIdField(), orgDeptParent);
                        };
                        authorityCondition.or(dept);
                    }
                }
                //下级
                if (StringUtils.isNotBlank(this.getDeptIdField()) && (DataRanges.SECTOR_CHILD & uaadeAuthority.getDeptdr()) > 0) {
                    if (orgDeptChild.size() > 0) {
                        Consumer<QueryWrapper> dept = deptQw -> {
                            deptQw.in(this.getDeptIdField(), orgDeptChild);
                        };
                        authorityCondition.or(dept);
                    }
                }
                //无值
                if (StringUtils.isNotBlank(this.getDeptIdField()) && (DataRanges.SECTOR_NULL & uaadeAuthority.getDeptdr()) > 0) {
                    Consumer<QueryWrapper> dept = deptQw -> {
                        deptQw.isNull(this.getDeptIdField());
                    };
                    authorityCondition.or(dept);
                }
            }

            //自定义条件
            if (StringUtils.isNotBlank(uaadeAuthority.getBscope())) {
                if (!uaadeAuthority.isDataset()) {
                    authorityCondition.or(ScopeUtils.parse(this, uaadeAuthority.getBscope()));
                } else {
                    //直接由查询构造条件
                    Consumer<QueryWrapper> bScopeConditions = bScopeCondition -> {
                        bScopeCondition.apply(uaadeAuthority.getBscope());
                    };
                    authorityCondition.or(bScopeConditions);
                }
            }


            //操作判断
            List<Map<String, String>> deActions = uaadeAuthority.getDeAction().stream().filter(deaction -> deaction.containsKey(action) && StringUtils.isNotBlank(deaction.get(action)))
                    .collect(Collectors.toList());
            if (deActions.size() > 0) {
                authorityCondition.and(ScopeUtils.parse(this, uaadeAuthority.getBscope()));
            }
        };
        return authorityConditions;
    }

    /**
     * 获取数据全部能力
     *
     * @param key
     * @return
     */
    public Map<String, Integer> getOPPrivs(Serializable key) {
        Map<String, Integer> opprivsMap = new HashMap<>();
        if (this.getUserContext().isSuperuser()){
            return opprivsMap;
        }
        AuthenticationUser curUser = AuthenticationUser.getAuthenticationUser();
        //实体默认能力
        defaultAuthorities.stream().forEach(
                uaadeAuthority -> uaadeAuthority.getDeAction().stream().forEach(
                        deaction -> deaction.keySet().stream().forEach(
                                actionkey -> {
                                    if (!opprivsMap.containsKey(actionkey)) {
                                        opprivsMap.put(actionkey, 0);
                                    }
                                })));
        //系统用户能力
        this.getUserUAAAuthorities().stream().forEach(
                uaadeAuthority -> uaadeAuthority.getDeAction().stream().forEach(
                        deaction -> deaction.keySet().stream().forEach(
                                actionkey -> {
                                    if (!opprivsMap.containsKey(actionkey)) {
                                        opprivsMap.put(actionkey, 0);
                                    }
                                })));
        //系统管理员能力
        if (curUser.getAdminuser() == 1) {
            this.getAdminUAAAuthorities().stream().forEach(
                    uaadeAuthority -> uaadeAuthority.getDeAction().stream().forEach(
                            deaction -> deaction.keySet().stream().forEach(
                                    actionkey -> {
                                        if (!opprivsMap.containsKey(actionkey)) {
                                            opprivsMap.put(actionkey, 0);
                                        }
                                    })));
        }
        //系统角色分配固定能力
        this.getRoleUAAAuthorities().stream().forEach(
                uaadeAuthority -> uaadeAuthority.getDeAction().stream().forEach(
                        deaction -> deaction.keySet().stream().forEach(
                                actionkey -> {
                                    if (!opprivsMap.containsKey(actionkey)) {
                                        opprivsMap.put(actionkey, 0);
                                    }
                                })));
        //运行时分配能力
        if (curUser.getAuthorities() != null) {
            curUser.getAuthorities().stream()
                    .filter(f -> f instanceof UAADEAuthority)
                    .forEach(
                            uaadeAuthority -> ((UAADEAuthority) uaadeAuthority).getDeAction().stream().forEach(
                                    deaction -> deaction.keySet().stream().forEach(
                                            actionkey -> {
                                                if (!opprivsMap.containsKey(actionkey)) {
                                                    opprivsMap.put(actionkey, 0);
                                                }
                                            })));

        }
        opprivsMap.entrySet().stream().forEach(opprivsrEntry -> {
            if (test(key, opprivsrEntry.getKey())) {
                opprivsrEntry.setValue(1);
            }
        });
        //补充未分配能力
        allOPPrivs.entrySet().stream().forEach(opprivEntry -> {
            if (!opprivsMap.containsKey(opprivEntry.getKey())) {
                opprivsMap.put(opprivEntry.getKey(), 0);
            }
        });
        return opprivsMap;
    }

    /**
     * 根据父数据获取数据全部能力
     *
     * @param PDEName
     * @param pKey
     * @param key
     * @return
     */
    public Map<String, Integer> getOPPrivs(String PDEName, Serializable pKey, Serializable key) {
        Map<String, Integer> opprivsMap = new HashMap<>();
        //操作映射
        List<IPSDEOPPriv> mspdeopPrivs = null;
        try {
            mspdeopPrivs = this.getPSDataEntity().getAllPSDEOPPrivs().stream().filter(oppriv -> StringUtils.isNotBlank(oppriv.getMapPSDEName()) && oppriv.getMapPSDEName().equals(PDEName)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("根据[%s]数据获取数据全部能力发生错误：%s", PDEName, e.getMessage()), Errors.INTERNALERROR, this);
        }
        Map<String, String> opprivsPDEMaps = new HashMap<>();
        mspdeopPrivs.stream().forEach(oppriv -> {
            if (!opprivsPDEMaps.containsKey(oppriv.getName())) {
                opprivsPDEMaps.put(oppriv.getName(), oppriv.getMapPSDEOPPrivName());
            }
        });

        Map<String, Integer> opprivsPDEMapTest = new HashMap<>();
        mspdeopPrivs.stream().forEach(oppriv -> {
            if (!opprivsPDEMapTest.containsKey(oppriv.getMapPSDEOPPrivName())) {
                opprivsPDEMapTest.put(oppriv.getMapPSDEOPPrivName(), 0);
            }
        });
        try {
            SystemDataEntityRuntime pDataEntityRuntime = (SystemDataEntityRuntime) this.getSystemRuntime().getDataEntityRuntime(PDEName);
            opprivsPDEMapTest.entrySet().stream().forEach(opprivsrEntry -> {
                if (pDataEntityRuntime.test(pKey, opprivsrEntry.getKey())) {
                    opprivsrEntry.setValue(1);
                }
            });
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("测试父[%s]数据[%s]能力发生错误：%s", PDEName, pKey, e.getMessage()), Errors.INTERNALERROR, this);
        }
        allOPPrivs.entrySet().stream().forEach(opprivEntry -> {
            if (!opprivsPDEMaps.containsKey(opprivEntry.getKey())) {
                opprivsMap.put(opprivEntry.getKey(), 0);
            } else {
                opprivsMap.put(opprivEntry.getKey(), opprivsPDEMapTest.get(opprivsPDEMaps.get((opprivEntry.getKey()))));
            }
        });
        return opprivsMap;
    }

    public boolean isRtmodel() {
        return ((SystemRuntime) this.getSystemRuntime()).isRtmodel();
    }

    /**
     * 获取实体能力
     */
    public List<UAADEAuthority> getUAAAuthorities(String action) {
        List<UAADEAuthority> uaaDEAuthority = new ArrayList<>();
        AuthenticationUser curUser = AuthenticationUser.getAuthenticationUser();
        //实体默认能力
        uaaDEAuthority.addAll(defaultAuthorities.stream().filter(
                uaadeAuthority -> DataAccessActions.READ.equals(action) || uaadeAuthority.getDeAction().stream().anyMatch(deaction -> deaction.containsKey(action)))
                .collect(Collectors.toList()));
        //系统用户能力
        uaaDEAuthority.addAll(this.getUserUAAAuthorities().stream().filter(
                uaadeAuthority -> DataAccessActions.READ.equals(action) || uaadeAuthority.getDeAction().stream().anyMatch(deaction -> deaction.containsKey(action)))
                .collect(Collectors.toList()));
        //系统管理员能力
        if (curUser.getAdminuser() == 1) {
            uaaDEAuthority.addAll(this.getAdminUAAAuthorities().stream().filter(
                    uaadeAuthority -> DataAccessActions.READ.equals(action) || uaadeAuthority.getDeAction().stream().anyMatch(deaction -> deaction.containsKey(action)))
                    .collect(Collectors.toList()));
        }
        //系统角色分配固定能力
        uaaDEAuthority.addAll(this.getRoleUAAAuthorities().stream().filter(
                uaadeAuthority -> DataAccessActions.READ.equals(action) || uaadeAuthority.getDeAction().stream().anyMatch(deaction -> deaction.containsKey(action)))
                .collect(Collectors.toList()));
        //运行时分配能力
        if (curUser.getAuthorities() != null) {
            String curSystemId = curUser.getSrfsystemid();
            if (StringUtils.isEmpty(curSystemId)) {
                uaaDEAuthority.addAll(curUser.getAuthorities().stream()
                        .filter(f -> f instanceof UAADEAuthority
                                && ((UAADEAuthority) f).getEntity().equals(this.getName())
                                && (DataAccessActions.READ.equals(action) || ((UAADEAuthority) f).getDeAction().stream().anyMatch(deaction -> deaction.containsKey(action))))
                        .map(f -> (UAADEAuthority) f).collect(Collectors.toList()));
            } else {
                uaaDEAuthority.addAll(curUser.getAuthorities().stream()
                        .filter(f -> f instanceof UAADEAuthority
                                && curSystemId.equalsIgnoreCase(((UAADEAuthority) f).getSystemid()) && ((UAADEAuthority) f).getEntity().equals(this.getName())
                                && (DataAccessActions.READ.equals(action) || ((UAADEAuthority) f).getDeAction().stream().anyMatch(deaction -> deaction.containsKey(action))))
                        .map(f -> (UAADEAuthority) f).collect(Collectors.toList()));
            }
        }
        return uaaDEAuthority;
    }

    /**
     * 获取普通用户权限
     *
     * @return
     */
    public List<UAADEAuthority> getUserUAAAuthorities() {
        if (userAuthorities == null) {
            userAuthorities = ((SystemRuntime) this.getSystemRuntime()).getUserUAADEAuthority().stream().filter(
                    uaadeAuthority -> uaadeAuthority.getEntity().equals(this.getName()))
                    .collect(Collectors.toList());
        }
        return userAuthorities;
    }

    /**
     * 获取管理员权限
     *
     * @return
     */
    public List<UAADEAuthority> getAdminUAAAuthorities() {
        if (adminAuthorities == null) {
            adminAuthorities = ((SystemRuntime) this.getSystemRuntime()).getAdminUAADEAuthority().stream().filter(
                    uaadeAuthority -> uaadeAuthority.getEntity().equals(this.getName()))
                    .collect(Collectors.toList());
        }
        return adminAuthorities;
    }

    /**
     * 获取系统角色固定权限
     *
     * @return
     */
    public List<UAADEAuthority> getRoleUAAAuthorities() {
        return ((SystemRuntime) this.getSystemRuntime()).getRoleUAADEAuthority().stream().filter(
                uaadeAuthority -> uaadeAuthority.getEntity().equals(this.getName()))
                .collect(Collectors.toList());
    }

    /**
     * 获取实体主键字段
     *
     * @return
     */
    public String getKey() {
        if (StringUtils.isBlank(this.key)) {
            this.key = this.getKeyFieldQueryExp();
        }
        return this.key;
    }

    /**
     * 获取实体组织控制字段
     *
     * @return
     */
    public String getOrgIdField() {
        if (StringUtils.isBlank(this.orgIdField)) {
            this.orgIdField = this.getOrgIdFieldQueryExp();
        }
        return this.orgIdField;
    }

    /**
     * 获取实体部门控制字段
     *
     * @return
     */
    public String getDeptIdField() {
        if (StringUtils.isBlank(this.deptIdField)) {
            this.deptIdField = this.getDeptIdFieldQueryExp();
        }
        return this.deptIdField;
    }

    @Override
    protected String getFieldDataSetSortExp(IPSDEField iPSDEField) throws Exception {
        String fieldExp = super.getFieldDataSetSortExp(iPSDEField);
        if (StringUtils.isBlank(fieldExp))
            return iPSDEField.getName();
        if (fieldExp.indexOf(".") > 0) {
            fieldExp = fieldExp.substring(fieldExp.indexOf(".") + 1);
        }
        if (iPSDEField instanceof IPSLinkDEField && !iPSDEField.isPhisicalDEField()) {
            IPSLinkDEField iPSLinkDEField = (IPSLinkDEField) iPSDEField;
            fieldExp = fieldExp.replace(iPSLinkDEField.getRelatedPSDEField().getName(), iPSLinkDEField.getName());
            return fieldExp;
        }
        return fieldExp;
    }

    /**
    * 工作流数据权限检查
    *
    * @param dto
    * @param key
    * @return
    */
    public DTOBase testWFDataAccess(DTOBase dto, Serializable key){
        try {
            IPSDataEntity dataEntity = this.getPSDataEntity();
            if(testDataInWF(this.getSimpleEntity(key)) && dataEntity != null){
                DataAccessMode rs = wfClient.getEditFields(AuthenticationUser.getAuthenticationUser().getSrfsystemid(), dataEntity.getCodeName().toLowerCase(), key);
                if(rs != null){
                    String editMode = rs.getEditMode();
                    List<String> fieldMap = rs.getFieldMap();
                    if(fieldMap != null){
                        if(EXCLUDEFIELDS.equalsIgnoreCase(editMode)){
                            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
                            if(!ObjectUtils.isEmpty(fieldMap) && requestAttributes!=null){
                                HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
                                request.setAttribute("srffields",String.join(",",fieldMap));
                            }
                        }
                        else if(INCLUDEFIELDS.equalsIgnoreCase(editMode)){
                            List<IPSDEField> fields = getPSDataEntity().getAllPSDEFields();
                            if(fields != null){
                                for(IPSDEField field : fields){
                                    String fieldName = field.getCodeName().toLowerCase();
                                    if(!fieldMap.contains(fieldName))
                                        dto.reset(fieldName);
                                }
                             }
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new BadRequestException("工作流数据权限检查发生异常"+e);
        }
        return dto;
    }

}
