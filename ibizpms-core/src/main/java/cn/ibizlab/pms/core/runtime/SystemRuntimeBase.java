package cn.ibizlab.pms.core.runtime;

import cn.ibizlab.pms.util.client.IBZUAAFeignClient;
import net.ibizsys.model.IPSDynaInstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;

public abstract class SystemRuntimeBase extends net.ibizsys.runtime.SystemRuntimeBase {

	@Override
	public String getName() {
		return "iBiz软件生产管理";
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
        systemModelService.setPSModelFolderPath(String.format("%s" + File.separator + "%s" + File.separator + "CFG", publishPath, strDynaModelId));
        return systemModelService;
    }

    @Override
    public Object getGlobalParam(String s) {
        return null;
    }
    

}
