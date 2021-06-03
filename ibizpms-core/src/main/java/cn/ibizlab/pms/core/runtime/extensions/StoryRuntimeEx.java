package cn.ibizlab.pms.core.runtime.extensions;

import cn.ibizlab.pms.core.zentao.runtime.StoryRuntime;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Primary
@DependsOn("storyRuntime")
public class StoryRuntimeEx extends StoryRuntime {

    @Override
    public boolean test(String PDEName, Serializable PKey, String Paction, String action) throws Exception {
        boolean bok = super.test(PDEName, PKey, Paction, action);

        return bok;
    }

    @Override
    public boolean test(String PDEName, Serializable PKey, String Paction, Serializable key, String action) throws Exception {
        boolean bok = super.test(PDEName, PKey, Paction, key, action);

        if ("ASSIGNTO".equals(action)) {
            return bok && this.test(key, action);
        }else if("DELETE".equals(action)) {
            return bok && this.test(key, action);
        }else if("UPDATE".equals(action)) {
            return bok && this.test(key, action);
        }else if("CLOSE".equals(action)) {
            return bok && this.test(key, action);
        }else if("ACTIVATE".equals(action)) {
            return bok && this.test(key, action);
        }else if("REVIEW".equals(action)) {
            return bok && this.test(key, action);
        }else if("CHANGE".equals(action)) {
            return bok && this.test(key, action);
        }

        return bok;
    }
}
