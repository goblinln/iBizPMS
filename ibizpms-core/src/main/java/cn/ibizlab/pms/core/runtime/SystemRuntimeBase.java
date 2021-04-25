package cn.ibizlab.pms.core.runtime;

import cn.ibizlab.pms.util.client.IBZUAAFeignClient;
import cn.ibizlab.pms.util.helper.QueryContextHelper;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import cn.ibizlab.pms.util.security.UAADEAuthority;
import cn.ibizlab.pms.util.security.UAAMenuAuthority;
import cn.ibizlab.pms.util.security.UAAUniResAuthority;
import net.ibizsys.model.IPSDynaInstService;
import net.ibizsys.model.IPSSystem;
import net.ibizsys.model.dataentity.ds.IPSDEDataQuery;
import net.ibizsys.model.dataentity.ds.IPSDEDataQueryCode;
import net.ibizsys.model.dataentity.ds.IPSDEDataQueryCodeCond;
import net.ibizsys.model.dataentity.ds.IPSDEDataSet;
import net.ibizsys.model.dataentity.priv.IPSDEUserRole;
import net.ibizsys.model.dataentity.priv.IPSDEUserRoleOPPriv;
import net.ibizsys.model.security.IPSSysUniRes;
import net.ibizsys.model.security.IPSSysUserRole;
import net.ibizsys.model.security.IPSSysUserRoleData;
import net.ibizsys.model.security.IPSSysUserRoleRes;
import net.ibizsys.runtime.IDynaInstRuntime;
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

	@Override
	public String getName() {
		return "iBiz软件生产管理";
	}

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
                    continue;
                }
                //分配的实体角色能力
                List<IPSSysUserRoleData> userRoleDatas = userRole.getPSSysUserRoleDatas();
                if (userRoleDatas != null) {
                    for (IPSSysUserRoleData userRoleData : userRoleDatas) {
                        IPSDEUserRole psDEUserRole = userRoleData.getPSDEUserRole();
                        if (psDEUserRole == null)
                            continue;
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
                                Map<String, String> action = new HashMap<>();
                                action.put(psDEUserRoleOPPriv.getName(), org.apache.commons.lang3.StringUtils.isBlank(psDEUserRoleOPPriv.getCustomCond()) ? "" : psDEUserRoleOPPriv.getCustomCond());
                            }
                        }
                        authority.setDeAction(deActions);

                        if (SysUserRoleDefaultModes.USER.equals(userRole.getDefaultUser()))
                            userUAADEAuthority.add(authority);
                        else if (SysUserRoleDefaultModes.ADMIN.equals(userRole.getDefaultUser()))
                            adminUAADEAuthority.add(authority);
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
    }

    @Override
    protected IPSDynaInstService createPSSystemService() throws Exception {
        SystemModelService systemModelService = new SystemModelService();
        systemModelService.setFromJar(true);
        systemModelService.setPSModelFolderPath("model");
        return systemModelService;
    }

    @Value("${ibiz.dynamic.publishpath:/app/file/dynamicModel/publicpath}")
    private String publishPath;

    @Autowired
    IBZUAAFeignClient uaaClient;

    @Override
    protected IPSDynaInstService createPSDynaInstService(String strPSDynaInstId) throws Exception {
        String strDynaModelId = uaaClient.getDynaModelIdByInstId(strPSDynaInstId);
        SystemModelService systemModelService = new SystemModelService();
        systemModelService.setFromJar(false);
        String strPath = String.format("%s" + File.separator + "%s" + File.separator + "CFG", publishPath, strDynaModelId) ;
        if( StringUtils.hasLength(strPath)) {
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
            return true ;
        }

        return strDynaPath.indexOf(strDynaModelId) != -1;
    }

    @Override
    public Object getGlobalParam(String s) {
        return null;
    }
    
    public List<UAADEAuthority> getUserUAADEAuthority() {
        return userUAADEAuthority;
    }

    public List<UAADEAuthority> getAdminUAADEAuthority() {
        return adminUAADEAuthority;
    }

    public List<UAAUniResAuthority> getUAAUniResAuthority() {
        AuthenticationUser curUser = AuthenticationUser.getAuthenticationUser();
        List<UAAUniResAuthority> uaaUniResAuthorities = new ArrayList<>();
        uaaUniResAuthorities.addAll(userUAAUniResAuthority);
        if (curUser.isSuperuser())
            uaaUniResAuthorities.addAll(adminUAAUniResAuthority);
        String curSystemId = curUser.getSrfsystemid();
        if (StringUtils.isEmpty(curSystemId)) {
            uaaUniResAuthorities.addAll(curUser.getAuthorities().stream()
                    .filter(f -> f instanceof UAAUniResAuthority).map(f -> (UAAUniResAuthority) f).collect(Collectors.toList()));
        } else {
            uaaUniResAuthorities.addAll(curUser.getAuthorities().stream()
                    .filter(f -> f instanceof UAAUniResAuthority && curSystemId.equalsIgnoreCase(((UAAUniResAuthority) f).getSystemid())).map(f -> (UAAUniResAuthority) f).collect(Collectors.toList()));
        }

        return uaaUniResAuthorities;
    }

    public boolean testUniRes(String strUniResCode) {
        return this.getUAAUniResAuthority().stream().anyMatch(uaaUniResAuthority -> uaaUniResAuthority.equals(strUniResCode));
    }

}
