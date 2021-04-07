package cn.ibizlab.pms.core.report.runtime;

import cn.ibizlab.pms.core.report.domain.IbzWeekly;
import cn.ibizlab.pms.core.report.service.IIbzWeeklyService;
import cn.ibizlab.pms.core.report.filter.IbzWeeklySearchContext;
import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("IbzWeeklyRuntime")
public class IbzWeeklyRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IIbzWeeklyService ibzweeklyService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new IbzWeekly();
    }

    @Override
    public String getId() {
        return "PSMODULES/report/PSDATAENTITIES/IbzWeekly.json";
    }

    @Override
    public String getName() {
        return "IBZ_WEEKLY";
    }

    @Override
    public Object createEntity() {
        return new IbzWeekly();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    //@Around("execution(* cn.ibizlab.pms.core.report.service.impl.IbzWeeklyServiceImpl.*(..))")
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
        if (action.equals("createEveryWeekReport")) {
            return aroundAction("createEveryWeekReport", point);
        }
        if (action.equals("createGetLastWeekPlanAndWork")) {
            return aroundAction("CreateGetLastWeekPlanAndWork", point);
        }
        if (action.equals("editGetLastWeekTaskAndComTask")) {
            return aroundAction("EditGetLastWeekTaskAndComTask", point);
        }
        if (action.equals("haveRead")) {
            return aroundAction("haveRead", point);
        }
        if (action.equals("jugThisWeekCreateWeekly")) {
            return aroundAction("JugThisWeekCreateWeekly", point);
        }
        if (action.equals("pushUserWeekly")) {
            return aroundAction("pushUserWeekly", point);
        }
        if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        if (action.equals("submit")) {
            return aroundAction("submit", point);
        }

        if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        if (action.equals("searchMyNotSubmit")) {
            return aroundDataSet("MyNotSubmit", point);
        }
        if (action.equals("searchMyWeekly")) {
            return aroundDataSet("MyWeekly", point);
        }
        if (action.equals("searchProductTeamMemberWeekly")) {
            return aroundDataSet("ProductTeamMemberWeekly", point);
        }
        if (action.equals("searchProjectWeekly")) {
            return aroundDataSet("ProjectWeekly", point);
        }
        return point.proceed();
    }

}
