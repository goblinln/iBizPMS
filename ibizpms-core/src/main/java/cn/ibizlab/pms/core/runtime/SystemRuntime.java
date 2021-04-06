package cn.ibizlab.pms.core.runtime;

import cn.ibizlab.pms.util.security.AuthenticationUser;
import net.ibizsys.model.IPSDynaInstService;
import net.ibizsys.runtime.SystemImplBase;
import org.springframework.stereotype.Component;

@Component
public class SystemRuntime extends SystemImplBase {

    @Override
    protected IPSDynaInstService createPSDynaInstService() throws Exception {
        AuthenticationUser curUser = AuthenticationUser.getAuthenticationUser();
        SystemModelService systemModelService = new SystemModelService();
        systemModelService.setPSModelFolderPath("I:\\workspace\\idea\\iBizPMS\\model");
        return systemModelService;
    }
    
}