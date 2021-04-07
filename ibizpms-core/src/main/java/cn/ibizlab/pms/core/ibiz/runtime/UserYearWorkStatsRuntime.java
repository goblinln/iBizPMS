package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.ibiz.domain.UserYearWorkStats;
import cn.ibizlab.pms.core.ibiz.service.IUserYearWorkStatsService;
import cn.ibizlab.pms.core.ibiz.filter.UserYearWorkStatsSearchContext;
import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("UserYearWorkStatsRuntime")
public class UserYearWorkStatsRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IUserYearWorkStatsService useryearworkstatsService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new UserYearWorkStats();
    }

    @Override
    public String getId() {
        return "PSMODULES/ibiz/PSDATAENTITIES/UserYearWorkStats.json";
    }

    @Override
    public String getName() {
        return "IBZ_USERYEARWORKSTATS";
    }

    @Override
    public Object createEntity() {
        return new UserYearWorkStats();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    //@Around("execution(* cn.ibizlab.pms.core.ibiz.service.impl.UserYearWorkStatsServiceImpl.*(..))")
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
        if (action.equals("getUserYearAction")) {
            return aroundAction("GetUserYearAction", point);
        }
        if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        if (action.equals("updateTitleByYear")) {
            return aroundAction("UpdateTitleByYear", point);
        }

        if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        if (action.equals("searchMonthFinishTaskAndBug")) {
            return aroundDataSet("MonthFinishTaskAndBug", point);
        }
        if (action.equals("searchMonthOpenedBugAndCase")) {
            return aroundDataSet("MonthOpenedBugAndCase", point);
        }
        if (action.equals("searchMonthOpenedStory")) {
            return aroundDataSet("MonthOpenedStory", point);
        }
        return point.proceed();
    }

}
