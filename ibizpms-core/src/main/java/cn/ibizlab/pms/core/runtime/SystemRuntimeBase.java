package cn.ibizlab.pms.core.runtime;

import cn.ibizlab.pms.util.client.IBZUAAFeignClient;
import cn.ibizlab.pms.util.client.IBZTPSFeignClient;
import cn.ibizlab.pms.util.domain.SysAudit;
import cn.ibizlab.pms.util.domain.SysEvent;
import cn.ibizlab.pms.util.domain.SysLog;
import cn.ibizlab.pms.util.domain.SysPO;
import cn.ibizlab.pms.util.helper.QueryContextHelper;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.util.security.*;
import net.ibizsys.model.IPSDynaInstService;
import net.ibizsys.model.IPSSystem;
import net.ibizsys.model.dataentity.ds.IPSDEDataQuery;
import net.ibizsys.model.dataentity.ds.IPSDEDataQueryCode;
import net.ibizsys.model.dataentity.ds.IPSDEDataQueryCodeCond;
import net.ibizsys.model.dataentity.ds.IPSDEDataSet;
import net.ibizsys.model.dataentity.priv.IPSDEUserRole;
import net.ibizsys.model.dataentity.priv.IPSDEUserRoleOPPriv;
import net.ibizsys.model.msg.IPSSysMsgQueue;
import net.ibizsys.model.msg.IPSSysMsgTarget;
import net.ibizsys.model.res.IPSSysDataSyncAgent;
import net.ibizsys.model.security.IPSSysUniRes;
import net.ibizsys.model.security.IPSSysUserRole;
import net.ibizsys.model.security.IPSSysUserRoleData;
import net.ibizsys.model.security.IPSSysUserRoleRes;
import net.ibizsys.runtime.IDynaInstRuntime;
import net.ibizsys.runtime.msg.ISysMsgQueueRuntime;
import net.ibizsys.runtime.msg.ISysMsgTargetRuntime;
import net.ibizsys.runtime.res.ISysDataSyncAgentRuntime;
import net.ibizsys.runtime.res.SysDataSyncAgentTypes;
import net.ibizsys.runtime.security.DataAccessActions;
import net.ibizsys.runtime.security.SysUserRoleDefaultModes;
import net.ibizsys.runtime.util.DBTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public abstract class SystemRuntimeBase extends net.ibizsys.runtime.SystemRuntimeBase {

    /**
	 * 模型驱动
	 */
    @Value("${ibiz.rtmodel:false}")
    protected boolean rtmodel ;

    @Value("${ibiz.deploysystemid:NONE}")
    private String strDeploySystemId;

    @Value("${ibiz.dynamic.publishpath:/app/file/dynamicModel/publicpath}")
    private String publishPath;

    @Autowired
    IBZUAAFeignClient uaaClient;
    
	@Override
	public String getName() {
		return "iBiz软件生产管理";
	}

    /**
     * 系统用户角色 实体角色能力权限
     */
    protected Map<String, List<UAADEAuthority>> userRoleUAADEAuthorityMap = new HashMap<>();

    /**
     * 系统用户角色 统一资源权限
     */
    protected Map<String, List<UAAUniResAuthority>> userRoleUAAUniResAuthorityMap = new HashMap<>();

    /**
     * 系统默认用户 实体角色能力权限
     */
    protected List<UAADEAuthority> userUAADEAuthority = new ArrayList<>();

    /**
     * 系统默认管理员 实体角色能力权限
     */
    protected List<UAADEAuthority> adminUAADEAuthority = new ArrayList<>();

    /**
     * 系统默认用户 统一资源权限
     */
    protected List<UAAUniResAuthority> userUAAUniResAuthority = new ArrayList<>();

    /**
     * 系统默认管理员 统一资源权限
     */
    protected List<UAAUniResAuthority> adminUAAUniResAuthority = new ArrayList<>();

    @PostConstruct
    public void sysInit() throws Exception {
        this.getPSSystem();
    }

    @Override
    public void onInit() throws Exception {
        IPSSystem system = this.getPSSystem();
        List<IPSSysUserRole> userRoles = system.getAllPSSysUserRoles();
        if (userRoles != null) {
            for (IPSSysUserRole userRole : userRoles) {
                if (DataAccessActions.NONE.equals(userRole.getDefaultUser())) {
                    //添加系统角色默认能力
                    List<IPSSysUserRoleData> userRoleDatas = userRole.getPSSysUserRoleDatas();
                    if (userRoleDatas != null) {
                        for (IPSSysUserRoleData userRoleData : userRoleDatas) {
                            IPSDEUserRole psDEUserRole = userRoleData.getPSDEUserRole();
                            if (psDEUserRole == null)
                                continue;
                            if ("CAT1".equals(userRoleData.getUserCat()) || "CAT1".equals(psDEUserRole.getUserCat())) {
                                UAADEAuthority authority = genUAADEAuthority(userRoleData);
                                if (authority != null) {
                                    if(!userRoleUAADEAuthorityMap.containsKey(userRole.getRoleTag())){
                                        userRoleUAADEAuthorityMap.put(userRole.getRoleTag(), new ArrayList<>());
                                    }
                                    userRoleUAADEAuthorityMap.get(userRole.getRoleTag()).add(authority);
                                }
                            }
                        }
                    }
                    //添加系统角色默认统一资源
                    List<IPSSysUserRoleRes> userRoleReses = userRole.getPSSysUserRoleReses();
                    if (userRoleReses != null) {
                        for (IPSSysUserRoleRes userRoleRes : userRoleReses) {
                            IPSSysUniRes uniRes = userRoleRes.getPSSysUniRes();
                            if (uniRes == null)
                                continue;
                            if ("CAT1".equals(userRoleRes.getUserCat()) || "CAT1".equals(uniRes.getUserCat())) {
                                UAAUniResAuthority authority = new UAAUniResAuthority();
                                authority.setName(uniRes.getName());
                                authority.setUnionResTag(uniRes.getResCode());
                                if(!userRoleUAAUniResAuthorityMap.containsKey(userRole.getRoleTag())){
                                    userRoleUAAUniResAuthorityMap.put(userRole.getRoleTag(), new ArrayList<>());
                                }
                                userRoleUAAUniResAuthorityMap.get(userRole.getRoleTag()).add(authority);
                            }
                        }
                    }
                    continue;
                }
                //分配的实体角色能力
                List<IPSSysUserRoleData> userRoleDatas = userRole.getPSSysUserRoleDatas();
                if (userRoleDatas != null) {
                    for (IPSSysUserRoleData userRoleData : userRoleDatas) {
                        UAADEAuthority authority = genUAADEAuthority(userRoleData);
                        if (authority != null) {
                            if (SysUserRoleDefaultModes.USER.equals(userRole.getDefaultUser()))
                                userUAADEAuthority.add(authority);
                            else if (SysUserRoleDefaultModes.ADMIN.equals(userRole.getDefaultUser()))
                                adminUAADEAuthority.add(authority);
                        }
                    }
                }
                //分配的统一资源
                List<IPSSysUserRoleRes> userRoleReses = userRole.getPSSysUserRoleReses();
                if (userRoleReses != null) {
                    for (IPSSysUserRoleRes userRoleRes : userRoleReses) {
                        IPSSysUniRes uniRes = userRoleRes.getPSSysUniRes();
                        if (uniRes == null)
                            continue;
                        UAAUniResAuthority authority = new UAAUniResAuthority();
                        authority.setName(uniRes.getName());
                        authority.setUnionResTag(uniRes.getResCode());
                        if (SysUserRoleDefaultModes.USER.equals(userRole.getDefaultUser()))
                            userUAAUniResAuthority.add(authority);
                        else if (SysUserRoleDefaultModes.ADMIN.equals(userRole.getDefaultUser()))
                            adminUAAUniResAuthority.add(authority);
                    }
                }
            }
        }
        super.onInit();
    }

    protected UAADEAuthority genUAADEAuthority(IPSSysUserRoleData userRoleData) throws Exception {
        IPSDEUserRole psDEUserRole = userRoleData.getPSDEUserRole();
        if (psDEUserRole == null)
            return null;
        UAADEAuthority authority = new UAADEAuthority();
        authority.setName(psDEUserRole.getName());
        authority.setEntity(userRoleData.getPSDataEntity().getName());
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
                            if (DBTypes.MYSQL5.equals(psdeDataQueryCode.getDBType())) {
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
        //实体操作能力
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
        return authority;
    }

    @Override
    protected IPSDynaInstService createPSSystemService() throws Exception {
        SystemModelService systemModelService = new SystemModelService();
        systemModelService.setFromJar(true);
        systemModelService.setPSModelFolderPath("model");
        return systemModelService;
    }

    @Override
    protected IPSDynaInstService createPSDynaInstService(String strPSDynaInstId) throws Exception {
        String strDynaModelId = uaaClient.getDynaModelIdByInstId(strPSDynaInstId);
        SystemModelService systemModelService = new SystemModelService();
        systemModelService.setFromJar(false);
        String strPath = String.format("%s" + File.separator + "%s" + File.separator + "CFG", publishPath, strDynaModelId);
        if (StringUtils.hasLength(strPath)) {
            String strHeader = strPath.toLowerCase();
            if ((strHeader.indexOf("http://") == 0) || (strHeader.indexOf("https://") == 0)) {
                strPath = strPath.replace("\\", "/");
            }
        }
        systemModelService.setPSModelFolderPath(strPath);
        return systemModelService;
    }

    @Override
    protected boolean checkDynaInstRuntime(IDynaInstRuntime iDynaInstRuntime) {
        String strDynaInstId = iDynaInstRuntime.getId();
        String strDynaPath = iDynaInstRuntime.getDynaInstFolderPath();
        String strDynaModelId = "";
        try {
            strDynaModelId = uaaClient.getDynaModelIdByInstId(strDynaInstId);
        } catch (Exception e) {
            log.error(String.format("刷新实例异常:%s", e.getMessage()));
            return true;
        }

        return strDynaPath.indexOf(strDynaModelId) != -1;
    }

    @Override
    public Object getGlobalParam(String s) {
        return null;
    }

    @Override
    public String getDeploySystemId() {
        return this.strDeploySystemId;
    }

    public boolean isRtmodel() {
        return rtmodel;
    }
    
    /**
     * 获取普通用户权限
     *
     * @return
     */
    public List<UAADEAuthority> getUserUAADEAuthority() {
        return userUAADEAuthority;
    }

    /**
     * 获取管理员权限
     *
     * @return
     */
    public List<UAADEAuthority> getAdminUAADEAuthority() {
        return adminUAADEAuthority;
    }

    /**
     * 获取系统角色固定权限
     *
     * @return
     */
    public List<UAADEAuthority> getRoleUAADEAuthority() {
        List<UAADEAuthority> roleUAADEAuthority = new ArrayList<>();
        AuthenticationUser curUser = AuthenticationUser.getAuthenticationUser();
        if (curUser.getAuthorities() != null) {
            curUser.getAuthorities().stream()
                    .filter(f -> f instanceof UAARoleAuthority).forEach(r -> {
                if (userRoleUAADEAuthorityMap.containsKey(((UAARoleAuthority) r).getRoleTag()))
                    roleUAADEAuthority.addAll(userRoleUAADEAuthorityMap.get(((UAARoleAuthority) r).getRoleTag()));
            });
        }
        return roleUAADEAuthority;
    }

    /**
     * 获取统一资源
     *
     * @return
     */
    public List<UAAUniResAuthority> getUAAUniResAuthority() {
        AuthenticationUser curUser = AuthenticationUser.getAuthenticationUser();
        List<UAAUniResAuthority> uaaUniResAuthorities = new ArrayList<>();
        uaaUniResAuthorities.addAll(userUAAUniResAuthority);
        if (curUser.getAdminuser() == 1)
            uaaUniResAuthorities.addAll(adminUAAUniResAuthority);
        if (curUser.getAuthorities() != null) {
            String curSystemId = curUser.getSrfsystemid();
            if (StringUtils.isEmpty(curSystemId)) {
                uaaUniResAuthorities.addAll(curUser.getAuthorities().stream()
                        .filter(f -> f instanceof UAAUniResAuthority).map(f -> (UAAUniResAuthority) f).collect(Collectors.toList()));
                //添加系统角色统一资源
                curUser.getAuthorities().stream()
                        .filter(f -> f instanceof UAARoleAuthority).forEach(r -> {
                    if (userRoleUAADEAuthorityMap.containsKey(((UAARoleAuthority) r).getRoleTag()))
                        uaaUniResAuthorities.addAll(userRoleUAAUniResAuthorityMap.get(((UAARoleAuthority) r).getRoleTag()));
                });
            } else {
                uaaUniResAuthorities.addAll(curUser.getAuthorities().stream()
                        .filter(f -> f instanceof UAAUniResAuthority && curSystemId.equalsIgnoreCase(((UAAUniResAuthority) f).getSystemid())).map(f -> (UAAUniResAuthority) f).collect(Collectors.toList()));
                //添加系统角色统一资源
                curUser.getAuthorities().stream()
                        .filter(f -> f instanceof UAARoleAuthority && curSystemId.equalsIgnoreCase(((UAARoleAuthority) f).getSystemid())).forEach(r -> {
                    if (userRoleUAADEAuthorityMap.containsKey(((UAARoleAuthority) r).getRoleTag()))
                        uaaUniResAuthorities.addAll(userRoleUAAUniResAuthorityMap.get(((UAARoleAuthority) r).getRoleTag()));
                });
            }
        }

        return uaaUniResAuthorities;
    }

    public boolean testUniRes(String strUniResCode) {
        return this.getUAAUniResAuthority().stream().anyMatch(uaaUniResAuthority -> uaaUniResAuthority.equals(strUniResCode));
    }

    @Override
    public void log(int nLogLevel, String strCat, String strInfo, Object objData) {
        try {
            IBZTPSFeignClient tps = SpringContextHolder.getBean(IBZTPSFeignClient.class);
            SysLog sysLog = new SysLog();
            sysLog.setLoglevel(nLogLevel);
            sysLog.setCat(strCat);
            sysLog.setInfo(strInfo);
            if (objData != null)
                sysLog.setObjdata(MAPPER.writeValueAsString(objData));
            tps.syslog(sysLog);
        } catch (Exception e) {
            log.error(String.format("登记系统日志发生错误：%s", e.getMessage()));
        }
    }

    @Override
    public void logAudit(int nLogLevel, String strCat, String strInfo, String strPersonId, String strAddress, Object objData) {
        try {
            IBZTPSFeignClient tps = SpringContextHolder.getBean(IBZTPSFeignClient.class);
            SysAudit sysAudit = new SysAudit();
            sysAudit.setLoglevel(nLogLevel);
            sysAudit.setCat(strCat);
            sysAudit.setInfo(strInfo);
            sysAudit.setAddress(strAddress);
            if (objData != null)
                sysAudit.setObjdata(MAPPER.writeValueAsString(objData));
            tps.audit(sysAudit);
        } catch (Exception e) {
            log.error(String.format("登记系统审计发生错误：%s", e.getMessage()));
        }
    }

    @Override
    public void logEvent(int nLogLevel, String strCat, String strInfo, Object objData) {
        try {
            IBZTPSFeignClient tps = SpringContextHolder.getBean(IBZTPSFeignClient.class);
            SysEvent sysEvent = new SysEvent();
            sysEvent.setLoglevel(nLogLevel);
            sysEvent.setCat(strCat);
            sysEvent.setInfo(strInfo);
            if (objData != null)
                sysEvent.setObjdata(MAPPER.writeValueAsString(objData));
            tps.event(sysEvent);
        } catch (Exception e) {
            log.error(String.format("登记系统事件发生错误：%s", e.getMessage()));
        }
    }

    @Override
    public void logPO(int nLogLevel, String strCat, String strInfo, String strDEName, String strAction, long nTime, Object objData) {
        try {
            IBZTPSFeignClient tps = SpringContextHolder.getBean(IBZTPSFeignClient.class);
            SysPO sysPO = new SysPO();
            sysPO.setLoglevel(nLogLevel);
            sysPO.setCat(strCat);
            sysPO.setInfo(strInfo);
            sysPO.setDe(strDEName);
            sysPO.setAction(strAction);
            sysPO.setTime(nTime);
            if (objData != null)
                sysPO.setObjdata(MAPPER.writeValueAsString(objData));
            tps.po(sysPO);
        } catch (Exception e) {
            log.error(String.format("登记系统优化日志发生错误：%s", e.getMessage()));
        }
    }

}
