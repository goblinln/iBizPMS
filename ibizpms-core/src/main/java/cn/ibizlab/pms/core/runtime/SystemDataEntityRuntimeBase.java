package cn.ibizlab.pms.core.runtime;

import cn.ibizlab.pms.util.filter.QueryWrapperContext;
import cn.ibizlab.pms.util.filter.ScopeUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import cn.ibizlab.pms.util.helper.QueryContextHelper;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import cn.ibizlab.pms.util.security.UAADEAuthority;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import lombok.extern.slf4j.Slf4j;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.ds.IPSDEDataQuery;
import net.ibizsys.model.dataentity.ds.IPSDEDataQueryCode;
import net.ibizsys.model.dataentity.ds.IPSDEDataQueryCodeCond;
import net.ibizsys.model.dataentity.ds.IPSDEDataSet;
import net.ibizsys.model.dataentity.priv.IPSDEUserRole;
import net.ibizsys.model.dataentity.priv.IPSDEUserRoleOPPriv;
import net.ibizsys.model.dataentity.wf.IPSDEWF;
import net.ibizsys.runtime.IDynaInstRuntime;
import net.ibizsys.runtime.dataentity.action.CheckKeyStates;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import net.ibizsys.runtime.ISystemRuntime;
import net.ibizsys.runtime.dataentity.IDataEntityRuntime;
import net.ibizsys.runtime.security.DataAccessActions;
import net.ibizsys.runtime.security.DataRanges;
import net.ibizsys.runtime.security.IUserContext;
import net.ibizsys.runtime.util.Conditions;
import net.ibizsys.runtime.util.IEntity;
import net.ibizsys.runtime.util.IEntityBase;
import net.ibizsys.runtime.util.ISearchContextBase;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.security.core.GrantedAuthority;

import javax.annotation.PostConstruct;

@Slf4j
public abstract class SystemDataEntityRuntimeBase extends net.ibizsys.runtime.dataentity.DataEntityRuntimeBase {

    /**
	 * 模型驱动
	 */
    @Value("${ibiz.rtmodel:false}")
    protected boolean rtmodel ;

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

    private final ExpressionParser parser = new SpelExpressionParser();

    /**
     * 默认实体能力
     */
    protected List<UAADEAuthority> defaultAuthorities = new ArrayList<>();

    @Autowired
    ISystemRuntime system;

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
    }

    /**
     * 加载系统默认角色
     * @throws Exception
     */
    protected void loadDefaultDEUserRoles() throws Exception{
        List<IPSDEUserRole> psDEUserRoles = this.getDefaultPSDEUserRoles();
        if (psDEUserRoles != null) {
            for (IPSDEUserRole psDEUserRole : psDEUserRoles) {
                //非系统默认实体角色不处理
                if (!psDEUserRole.isDefaultMode())
                    continue;
                UAADEAuthority authority = new UAADEAuthority();
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
                                            authority.setBscope(strBScope);
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                java.util.List<IPSDEUserRoleOPPriv> psDEUserRoleOPPrivs = psDEUserRole.getPSDEUserRoleOPPrivs();
                if (psDEUserRoleOPPrivs != null) {
                    List<Map<String, String>> deActions = new ArrayList<>();
                    for (IPSDEUserRoleOPPriv psDEUserRoleOPPriv : psDEUserRoleOPPrivs) {
                        Map<String, String> deAction = new HashMap<>();
                        deAction.put(psDEUserRoleOPPriv.getDataAccessAction(), "");
                        deActions.add(deAction);
                    }
                    authority.setDeAction(deActions);
                }
                this.defaultAuthorities.add(authority);
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
    public void setSearchCondition(ISearchContextBase iSearchContextBase, IPSDEField iPSDEField, String strCondition, Object objValue) {
        try {
            String strSearchField = String.format("n_%s_%s", iPSDEField.getName().toLowerCase(), strCondition.toLowerCase());
            Field searchField = iSearchContextBase.getClass().getDeclaredField(strSearchField);
            searchField.setAccessible(true);
            if(strCondition.equals(Conditions.ISNULL) || strCondition.equals(Conditions.ISNULL)){
                searchField.set(iSearchContextBase, "true");
            }else{
                searchField.set(iSearchContextBase, objValue);
            }
        } catch (NoSuchFieldException e) {
            log.warn(String.format("不存在属性[%s]的查询条件[%s]。", iPSDEField.getLogicName(), strCondition));
        } catch (IllegalAccessException e) {
            log.warn(String.format("设置属性[%s]的查询条件[%s]值[%s]发生错误：%s", iPSDEField.getLogicName(), strCondition, objValue, e.getMessage()));
        }
    }

    @Override
    public int checkKeyState(Object objKey) {
        QueryWrapperContext context = this.createSearchContext();
        context.getSelectCond().eq(this.getKey(), objKey);
        List domains =this.query(this.getService(), context);
        if(domains.size()>0)
            return CheckKeyStates.EXIST ;
        return CheckKeyStates.NOTEXIST;
    }

    @Override
    protected void onWFStart(Object arg0, IPSDEAction iPSDEAction, IPSDEWF iPSDEWF, IDynaInstRuntime iDynaInstRuntime, Object joinPoint) throws Throwable {
        
    }

    abstract protected IService getService();

    abstract public QueryWrapperContext createSearchContext();

    public List query(IService service , QueryWrapperContext queryWrapperContext){
        EvaluationContext elContext = new StandardEvaluationContext();
        elContext.setVariable("service", service);
        elContext.setVariable("queryWrapperContext", queryWrapperContext);
        Expression expression = parser.parseExpression("#service.searchDefault(#queryWrapperContext)");
        Page data = expression.getValue(elContext, Page.class);
        return data.getContent() ;
    }

    /**
     * 判断是否含有统一资源标识
     * @param uniResTag
     * @return
     */
    public boolean testUnires(String uniResTag){
        //统一资源能力
        if(this.getUserContext().testSysUniRes(uniResTag))
            return true ;
        return false ;
    }

    /**
     * 判断是否含操作能力
     *
     * @param action 操作能力
     * @return
     */
    public boolean quickTest(String action) {
        AuthenticationUser curUser = AuthenticationUser.getAuthenticationUser();
        if (this.getUserContext().isSuperuser())
            return true;
        if(testUnires(action))
            return true ;
        String curSystemId = curUser.getSrfsystemid();
        List<GrantedAuthority> authorities;
        if(StringUtils.isEmpty(curSystemId)){
            authorities= curUser.getAuthorities().stream()
                .filter(uaadeAuthority -> uaadeAuthority instanceof UAADEAuthority
                        && ((UAADEAuthority) uaadeAuthority).getEntity().equals(this.getName())
                        && (DataAccessActions.READ.equals(action) || ((UAADEAuthority) uaadeAuthority).getDeAction().stream().anyMatch(deaction -> deaction.containsKey(action))))
                .collect(Collectors.toList());
        }else {
            authorities= curUser.getAuthorities().stream()
                    .filter(uaadeAuthority -> uaadeAuthority instanceof UAADEAuthority
                            && curSystemId.equalsIgnoreCase(((UAADEAuthority) uaadeAuthority).getSystemid())
                            && (DataAccessActions.READ.equals(action) || ((UAADEAuthority) uaadeAuthority).getEntity().equals(this.getName()) && ((UAADEAuthority) uaadeAuthority).getDeAction().stream().anyMatch(deaction -> deaction.containsKey(action))))
                    .collect(Collectors.toList());
        }

        List<UAADEAuthority> defaultAuthorities = getDefaultAuthorities().stream().filter(uaadeAuthority -> {
            if (StringUtils.isNotBlank(uaadeAuthority.getBscope()) 
                    && (DataAccessActions.READ.equals(action) || uaadeAuthority.getDeAction().stream().anyMatch(deaction -> deaction.containsKey(action)))) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());

        if (authorities.size() == 0 && defaultAuthorities.size() == 0)
            return false;

        return true;
    }

    /**
     * 判断数据对象是否含有操作能力
     *
     * @param key    数据
     * @param action 操作能力
     * @return
     */
    public boolean test(Serializable key, String action) {
        if (this.getUserContext().isSuperuser())
            return true;
        if(testUnires(action))
            return true ;
        //检查能力
        if(!quickTest(action))
            return false ;

        //检查数据范围
        QueryWrapperContext context = this.createSearchContext();
        context.getSelectCond().eq(this.getKey(), key);
        addAuthorityConditions(context, action);
        List domains =this.query(this.getService(), context);
        if (domains.size() == 0) {
            return false;
        }
        try {
            return testDataAccessAction(domains.get(0), action);
        } catch (Exception e) {
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
        if (this.getUserContext().isSuperuser())
            return true;
        if (testUnires(action))
            return true ;
        //检查能力
        if (!quickTest(action))
            return false ;

        //检查数据范围
        QueryWrapperContext context = this.createSearchContext();
        context.getSelectCond().in(this.getKey(), keys);
        addAuthorityConditions(context, action);

        List domains =this.query(this.getService(), context);
        if (domains.size() != keys.size()) {
            return false;
        }
        try {
            for (Object domain : domains){
                if(testDataAccessAction(domain,action)){
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 根据父判断时候含有action能力 未设置父时，使用自身能力判断
     *
     * @param PDEName
     * @param PKey
     * @param action
     * @return
     */
    public boolean test(String PDEName, Serializable PKey, String action) throws Exception {
        if (StringUtils.isBlank(PDEName))
            return quickTest(action);
        try {
            IDataEntityRuntime pDataEntityRuntime = getSystemRuntime().getDataEntityRuntime(PDEName);
            return ((SystemDataEntityRuntimeBase) pDataEntityRuntime).test(PKey, action);
        } catch (Exception e) {
            log.error(String.format("获取依赖实体[%s]运行对象错误：%s", PDEName, e.getLocalizedMessage()));
            throw e;
        }
    }

    /**
     * 根据父判断时候含有action能力 未设置父时，使用自身能力判断
     *
     * @param PDEName
     * @param PKey
     * @param key
     * @param action
     * @return
     */
    public boolean test(String PDEName, Serializable PKey, Serializable key, String action) throws Exception {
        if (StringUtils.isBlank(PDEName))
            return test(key, action);
        try {
            IDataEntityRuntime pDataEntityRuntime = getSystemRuntime().getDataEntityRuntime(PDEName);
            return ((SystemDataEntityRuntimeBase) pDataEntityRuntime).test(PKey, action);
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
        AuthenticationUser curUser = AuthenticationUser.getAuthenticationUser();
        if (this.getUserContext().isSuperuser())
            return;
        if(testUnires(action))
            return ;
        //系统默认能力
        Consumer<QueryWrapper> defaultAuthorityConditions = genDefaultConditions(context, action);
        String curSystemId = curUser.getSrfsystemid();
        //运行时分配能力
        List<GrantedAuthority> authorities;
        if(StringUtils.isEmpty(curSystemId)){
            authorities= curUser.getAuthorities().stream()
                .filter(f -> f instanceof UAADEAuthority 
                    && ((UAADEAuthority) f).getEntity().equals(this.getName()) 
                    && (DataAccessActions.READ.equals(action) || ((UAADEAuthority) f).getDeAction().stream().anyMatch(deaction -> deaction.containsKey(action))))
                    .collect(Collectors.toList());
        }else {
            authorities= curUser.getAuthorities().stream()
                    .filter(f -> f instanceof UAADEAuthority 
                    && curSystemId.equalsIgnoreCase(((UAADEAuthority) f).getSystemid()) &&((UAADEAuthority) f).getEntity().equals(this.getName()) 
                    && (DataAccessActions.READ.equals(action) || ((UAADEAuthority) f).getDeAction().stream().anyMatch(deaction -> deaction.containsKey(action))))
                    .collect(Collectors.toList());
        }
        Consumer<QueryWrapper> authorityConditions = authorityCondition -> {
            for (GrantedAuthority authority : authorities) {
                UAADEAuthority uaadeAuthority = (UAADEAuthority) authority;
                //未设置权限能力范围 拒绝操作
                if ((StringUtils.isBlank(this.getOrgIdField()) || uaadeAuthority.getEnableorgdr() == null || uaadeAuthority.getEnableorgdr() == 0 || (uaadeAuthority.getEnableorgdr() == 1 && (uaadeAuthority.getOrgdr() == null || uaadeAuthority.getOrgdr() == 0)))
                        && (StringUtils.isBlank(this.getDeptIdField()) || uaadeAuthority.getEnabledeptdr() != null || uaadeAuthority.getEnabledeptdr() == 0 || (uaadeAuthority.getEnabledeptdr() == 1 && (uaadeAuthority.getDeptdr() == null || uaadeAuthority.getDeptdr() == 0)))
                        && (StringUtils.isBlank(uaadeAuthority.getBscope()))
                ) {
                    Consumer<QueryWrapper> denyDataCondition = dataCondition -> {
                        dataCondition.apply("1 = 1");
                    };
                    authorityCondition.or(denyDataCondition);
                } else {
                    Consumer<QueryWrapper> dataCondition = genAuthorityConditions(uaadeAuthority, action);
                    authorityCondition.or(dataCondition);
                }
            }
        };
        
        if (defaultAuthorityConditions != null) {
            if (authorities.size() > 0) {
                Consumer<QueryWrapper> or = orCondition -> {
                    orCondition.or(defaultAuthorityConditions);
                    orCondition.or(authorityConditions);
                };
                context.getSelectCond().and(or);
            } else {
                context.getSelectCond().and(defaultAuthorityConditions);
            }
        } else {
            if (authorities.size() > 0) {
                context.getSelectCond().and(authorityConditions);
            }
        }
    }

    /**
     * 系统内置权限
     * @param context
     * @param action
     */
    protected Consumer<QueryWrapper> genDefaultConditions(QueryWrapperContext context, String action) {
        Consumer<QueryWrapper> authorityConditions = null;
        List<UAADEAuthority> actionUAADEAuthority = getDefaultAuthorities().stream().filter(uaadeAuthority -> {
            if (StringUtils.isNotBlank(uaadeAuthority.getBscope()) 
                    && (DataAccessActions.READ.equals(action) || uaadeAuthority.getDeAction().stream().anyMatch(deaction -> deaction.containsKey(action)))) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());

        if (actionUAADEAuthority.size() > 0) {
            authorityConditions = authorityCondition -> {
                for (UAADEAuthority uaadeAuthority : actionUAADEAuthority) {
                    if (DataAccessActions.READ.equals(action) || uaadeAuthority.getDeAction().stream().anyMatch(deaction -> deaction.containsKey(action))) {
                        Consumer<QueryWrapper> roleConditions = roleCondition -> {
                            roleCondition.apply(uaadeAuthority.getBscope());
                        };
                        authorityCondition.or(roleConditions);
                    }
                }
            };
        }
        return authorityConditions;
    }

    /**
     * 加载运行时能力
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
                        deptQw.eq(this.getDeptIdField(), curUser.getOrgid());
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
                authorityCondition.or(ScopeUtils.parse(uaadeAuthority.getBscope()));
            }


            //操作判断
            List<Map<String, String>> deActions = uaadeAuthority.getDeAction().stream().filter(deaction -> deaction.containsKey(action) && StringUtils.isNotBlank(deaction.get(action)))
                    .collect(Collectors.toList());
            if (deActions.size() > 0) {
                authorityCondition.and(ScopeUtils.parse(uaadeAuthority.getBscope()));
            }
        };
        return authorityConditions;
    }

    public boolean isRtmodel() {
        return rtmodel;
    }

    protected void setRtmodel(boolean rtmodel) {
        this.rtmodel = rtmodel;
    }

    /**
     * 获取默认能力
     * @return
     */
    public List<UAADEAuthority> getDefaultAuthorities() {
        return defaultAuthorities;
    }

    /**
     * 获取实体主键字段
     * @return
     */
    public String getKey() {
        if(StringUtils.isBlank(this.key)){
            this.key = this.getKeyFieldQueryExp();
        }
        return this.key;
    }

    /**
     * 获取实体组织控制字段
     * @return
     */
    public String getOrgIdField() {
        if(StringUtils.isBlank(this.orgIdField)){
            this.orgIdField = this.getOrgIdFieldQueryExp();
        }
        return this.orgIdField;
    }

    /**
     * 获取实体部门控制字段
     * @return
     */
    public String getDeptIdField() {
        if(StringUtils.isBlank(this.deptIdField)){
            this.deptIdField = this.getDeptIdFieldQueryExp();
        }
        return this.deptIdField;
    }
}
