package cn.ibizlab.pms.core.runtime;

import cn.ibizlab.pms.util.client.IBZUAAFeignClient;
import lombok.extern.slf4j.Slf4j;
import net.ibizsys.model.IPSDynaInstService;
import net.ibizsys.runtime.IDynaInstRuntime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import java.io.File;

@Slf4j
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
        String strPath = String.format("%s" + File.separator + "%s" + File.separator + "CFG", publishPath, strDynaModelId) ;
        if(StringUtils.hasLength(strPath)) {
            String strHeader = strPath.toLowerCase();
            if((strHeader.indexOf("http://") == 0) || (strHeader.indexOf("https://") == 0)) {
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
            log.error(String.format("刷新实例异常:%s",e.getMessage()));
            return true ;
        }

        return strDynaPath.indexOf(strDynaModelId) != -1;
    }

    @Override
    public Object getGlobalParam(String s) {
        return null;
    }
    

}
