package cn.ibizlab.pms.core.runtime.extensions;

import cn.ibizlab.pms.core.zentao.runtime.CaseRuntime;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Primary
@DependsOn("caseRuntime")
public class CaseRuntimeEx extends CaseRuntime {

    @Override
    public boolean test(String PDEName, Serializable PKey, String Paction, String action) throws Exception {
        boolean bok = super.test(PDEName, PKey, Paction, action);

        return bok;
    }

    @Override
    public boolean test(String PDEName, Serializable PKey, String Paction, Serializable key, String action) throws Exception {
        boolean bok = super.test(PDEName, PKey, Paction, key, action);

        if ("RUNCASE".equals(action)) {
            return bok && this.test(key, action);
        }else if("DELETE".equals(action)) {
            return bok && this.test(key, action);
        }else if("UPDATE".equals(action)) {
            return bok && this.test(key, action);
        }else if("TESRUNCASE".equals(action)) {
            return bok && this.test(key, action);
        }else if("CONFIRMCHANGE".equals(action)) {
            return bok && this.test(key, action);
        }

        return bok;
    }
}
