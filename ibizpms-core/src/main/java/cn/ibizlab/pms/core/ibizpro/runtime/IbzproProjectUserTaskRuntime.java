package cn.ibizlab.pms.core.ibizpro.runtime;

import cn.ibizlab.pms.core.ibizpro.domain.IbzproProjectUserTask;
import cn.ibizlab.pms.core.ibizpro.service.IIbzproProjectUserTaskService;
import cn.ibizlab.pms.core.ibizpro.filter.IbzproProjectUserTaskSearchContext;
import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("IbzproProjectUserTaskRuntime")
public class IbzproProjectUserTaskRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IIbzproProjectUserTaskService ibzproprojectusertaskService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new IbzproProjectUserTask();
    }

    @Override
    public String getId() {
        return "PSMODULES/ibizpro/PSDATAENTITIES/IbzproProjectUserTask.json";
    }

    @Override
    public String getName() {
        return "IBIZPRO_PROJECTUSERTASK";
    }

    @Override
    public Object createEntity() {
        return new IbzproProjectUserTask();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    //@Around("execution(* cn.ibizlab.pms.core.ibizpro.service.impl.IbzproProjectUserTaskServiceImpl.*(..))")
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
        if (action.equals("searchProjectDailyTask")) {
            return aroundDataSet("ProjectDailyTask", point);
        }
        if (action.equals("searchProjectMonthlyTask")) {
            return aroundDataSet("ProjectMonthlyTask", point);
        }
        if (action.equals("searchProjectWeeklyTask")) {
            return aroundDataSet("ProjectWeeklyTask", point);
        }
        return point.proceed();
    }

}
