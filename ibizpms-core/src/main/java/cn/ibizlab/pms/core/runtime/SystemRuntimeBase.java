package cn.ibizlab.pms.core.runtime;

import cn.ibizlab.pms.util.security.AuthenticationUser;
import net.ibizsys.model.IPSDynaInstService;

public abstract class SystemRuntimeBase extends net.ibizsys.runtime.SystemRuntimeImplBase {

	@Override
	public String getName() {
		return "iBiz软件生产管理";
	}

    @Override
    protected IPSDynaInstService createPSDynaInstService() throws Exception {
        SystemModelService systemModelService = new SystemModelService();
        systemModelService.setPSModelFolderPath("model");
        return systemModelService;
    }
    
    @Override
    public String getCurUserId() {
        AuthenticationUser curUser = AuthenticationUser.getAuthenticationUser();
        if (curUser == null)
            return null;
        return curUser.getUserid();
    }

    @Override
    public String getCurUserName() {
        AuthenticationUser curUser = AuthenticationUser.getAuthenticationUser();
        if (curUser == null)
            return null;
        return curUser.getUsername();
    }

    @Override
    public String getCurOrgId() {
        AuthenticationUser curUser = AuthenticationUser.getAuthenticationUser();
        if (curUser == null)
            return null;
        return curUser.getOrgid();
    }

    @Override
    public String getCurOrgName() {
        AuthenticationUser curUser = AuthenticationUser.getAuthenticationUser();
        if (curUser == null)
            return null;
        return curUser.getOrgname();
    }

    @Override
    public String getCurDeptId() {
        AuthenticationUser curUser = AuthenticationUser.getAuthenticationUser();
        if (curUser == null)
            return null;
        return curUser.getMdeptid();
    }

    @Override
    public String getCurDeptName() {
        AuthenticationUser curUser = AuthenticationUser.getAuthenticationUser();
        if (curUser == null)
            return null;
        return curUser.getMdeptname();
    }

    @Override
    public Object getSessionValue(String s) {
        AuthenticationUser curUser = AuthenticationUser.getAuthenticationUser();
        if (curUser == null)
            return null;
        if (!curUser.getSessionParams().containsKey(s))
            return null;
        return curUser.getSessionParams().get(s);
    }

    @Override
    public Object getGlobalValue(String s) {
        return null;
    }
}
