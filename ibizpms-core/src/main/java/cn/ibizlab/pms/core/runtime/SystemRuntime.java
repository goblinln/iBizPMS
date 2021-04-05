package cn.ibizlab.pms.core.runtime;

import net.ibizsys.model.IPSDynaInstService;
import net.ibizsys.runtime.SystemImplBase;
import org.springframework.stereotype.Component;

@Component
public class SystemRuntime extends SystemImplBase {

    @Autowired
    IPSDynaInstService psDynaInstService;

    @Override
    protected IPSDynaInstService createPSDynaInstService() throws Exception {
        return psDynaInstService;
    }
    
}