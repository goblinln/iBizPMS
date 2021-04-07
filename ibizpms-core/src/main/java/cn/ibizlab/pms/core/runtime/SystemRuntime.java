package cn.ibizlab.pms.core.runtime;

import net.ibizsys.model.IPSDynaInstService;
import net.ibizsys.runtime.SystemImplBase;
import org.springframework.stereotype.Component;

@Component
public class SystemRuntime extends SystemImplBase {

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
    
}