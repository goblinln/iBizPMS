package cn.ibizlab.pms.core.runtime;

import cn.ibizlab.pms.core.runtime.translator.ZeroTranslator;
import net.ibizsys.model.res.IPSSysTranslator;
import net.ibizsys.runtime.res.ISysTranslatorRuntime;
import org.springframework.stereotype.Component;

@Component
public class SystemRuntime extends SystemRuntimeBase {

    @Override
    public ISysTranslatorRuntime createSysTranslatorRuntime(IPSSysTranslator iPSSysTranslator) {
        if(iPSSysTranslator.getCodeName().equals("ZeroTranslator")){
            return new ZeroTranslator();
        }
        return super.createSysTranslatorRuntime(iPSSysTranslator);
    }

}
