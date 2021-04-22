package cn.ibizlab.pms.core.runtime;

import cn.ibizlab.pms.core.runtime.translator.BranchValueTranslator;
import cn.ibizlab.pms.core.runtime.translator.BuildValueTranslator;
import cn.ibizlab.pms.core.runtime.translator.ModulePathValueTranslator;
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
        if(iPSSysTranslator.getCodeName().equals("BuildValueTranslator")){
            return new BuildValueTranslator();
        }
        if(iPSSysTranslator.getCodeName().equals("BranchValueTranslator")){
            return new BranchValueTranslator();
        }
        if(iPSSysTranslator.getCodeName().equals("ModulePathValueTranslator")){
            return new ModulePathValueTranslator();
        }
        return super.createSysTranslatorRuntime(iPSSysTranslator);
    }

}
