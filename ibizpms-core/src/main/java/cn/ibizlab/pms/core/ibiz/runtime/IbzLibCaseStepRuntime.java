package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.ibiz.domain.IbzLibCaseStep;
import cn.ibizlab.pms.core.ibiz.service.IIbzLibCaseStepService;
import cn.ibizlab.pms.core.ibiz.filter.IbzLibCaseStepSearchContext;
import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("IbzLibCaseStepRuntime")
public class IbzLibCaseStepRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IIbzLibCaseStepService ibzlibcasestepService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new IbzLibCaseStep();
    }

    @Override
    public String getId() {
        return "PSMODULES/ibiz/PSDATAENTITIES/IbzLibCaseStep.json";
    }

    @Override
    public String getName() {
        return "IBZ_CASESTEP";
    }

    @Override
    public Object createEntity() {
        return new IbzLibCaseStep();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    //@Around("execution(* cn.ibizlab.pms.core.ibiz.service.impl.IbzLibCaseStepServiceImpl.*(..))")
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

        if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        return point.proceed();
    }

}
