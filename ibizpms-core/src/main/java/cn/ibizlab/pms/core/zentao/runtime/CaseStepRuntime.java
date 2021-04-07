package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.CaseStep;
import cn.ibizlab.pms.core.zentao.service.ICaseStepService;
import cn.ibizlab.pms.core.zentao.filter.CaseStepSearchContext;
import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("CaseStepRuntime")
public class CaseStepRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    ICaseStepService casestepService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new CaseStep();
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/CaseStep.json";
    }

    @Override
    public String getName() {
        return "ZT_CASESTEP";
    }

    @Override
    public Object createEntity() {
        return new CaseStep();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    //@Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.CaseStepServiceImpl.*(..))")
    public Object aroundMethod(ProceedingJoinPoint point) throws Throwable {
        String action = point.getSignature().getName();
        if (action.equals("create")) {
            return aroundAction("Create", point);
        }
        if (action.equals("update")) {
            return aroundAction("Update", point);
        }
        if (action.equals("remove")) {
            return aroundAction("Remove", point);
        }
        if (action.equals("get")) {
            return aroundAction("Get", point);
        }
        if (action.equals("getDraft")) {
            return aroundAction("GetDraft", point);
        }
        if (action.equals("checkKey")) {
            return aroundAction("CheckKey", point);
        }
        if (action.equals("save")) {
            return aroundAction("Save", point);
        }

        if (action.equals("searchCurTest")) {
            return aroundDataSet("CurTest", point);
        }
        if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        if (action.equals("searchDefault1")) {
            return aroundDataSet("DEFAULT1", point);
        }
        if (action.equals("searchMob")) {
            return aroundDataSet("Mob", point);
        }
        if (action.equals("searchVersion")) {
            return aroundDataSet("Version", point);
        }
        if (action.equals("searchVersions")) {
            return aroundDataSet("Versions", point);
        }
        return point.proceed();
    }

}
