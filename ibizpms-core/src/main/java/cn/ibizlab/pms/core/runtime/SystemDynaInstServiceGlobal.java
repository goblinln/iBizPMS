package cn.ibizlab.pms.core.runtime;

import cn.ibizlab.pms.util.client.IBZUAAFeignClient;
import net.ibizsys.model.IPSDynaInstService;
import net.ibizsys.model.PSDynaInstServiceGlobal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;

@Component
public class SystemDynaInstServiceGlobal extends PSDynaInstServiceGlobal {

    @Autowired
    IBZUAAFeignClient uaaClient;

    @Value("${ibiz.dynamic.publishpath:/app/file/dynamicModel/publicpath}")
    private String publishPath;

    @PostConstruct
    public void init() {
        PSDynaInstServiceGlobal.setInstance(this);
    }

    @Override
    public IPSDynaInstService getPSDynaInstService(String strPSDynaInstId, boolean bReload) throws Exception {
        IPSDynaInstService dynaInstService = this.getCachePSDynaInstService(strPSDynaInstId);
        if (dynaInstService != null) {
            return super.getPSDynaInstService(strPSDynaInstId, bReload);
        }
        String strDynaModelId = uaaClient.getDynaModelIdByInstId(strPSDynaInstId);
        String strCfgPath = String.format("%s" + File.separator + "%s" + File.separator + "CFG", publishPath, strDynaModelId);
        strCfgPath = strCfgPath.replace("\\", "/");
        registerPSDynaInst(strPSDynaInstId, strCfgPath);
        return super.getPSDynaInstService(strPSDynaInstId, bReload);
    }

    @Override
    protected IPSDynaInstService createPSDynaInstService() {
        SystemModelService systemModelService = new SystemModelService();
        systemModelService.setFromJar(false);
        return systemModelService;
    }
}
