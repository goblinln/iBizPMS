package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.ibiz.domain.ProjectStats;
import cn.ibizlab.pms.core.ibiz.service.IProjectStatsService;
import cn.ibizlab.pms.core.ibiz.filter.ProjectStatsSearchContext;
import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("ProjectStatsRuntime")
public class ProjectStatsRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IProjectStatsService projectstatsService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new ProjectStats();
    }

    @Override
    public String getId() {
        return "PSMODULES/ibiz/PSDATAENTITIES/ProjectStats.json";
    }

    @Override
    public String getName() {
        return "IBZ_PROJECTSTATS";
    }

    @Override
    public Object createEntity() {
        return new ProjectStats();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    //@Around("execution(* cn.ibizlab.pms.core.ibiz.service.impl.ProjectStatsServiceImpl.*(..))")
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
        if (action.equals("searchNoOpenProduct")) {
            return aroundDataSet("NOOpenProduct", point);
        }
        if (action.equals("searchProjectBugType")) {
            return aroundDataSet("ProjectBugType", point);
        }
        if (action.equals("searchProjectInputStats")) {
            return aroundDataSet("ProjectInputStats", point);
        }
        if (action.equals("searchProjectProgress")) {
            return aroundDataSet("ProjectProgress", point);
        }
        if (action.equals("searchProjectQuality")) {
            return aroundDataSet("ProjectQuality", point);
        }
        if (action.equals("searchProjectStoryStageStats")) {
            return aroundDataSet("ProjectStoryStageStats", point);
        }
        if (action.equals("searchProjectStoryStatusStats")) {
            return aroundDataSet("ProjectStoryStatusStats", point);
        }
        if (action.equals("searchProjectTaskCountByTaskStatus")) {
            return aroundDataSet("ProjectTaskCountByTaskStatus", point);
        }
        if (action.equals("searchProjectTaskCountByType")) {
            return aroundDataSet("ProjectTaskCountByType", point);
        }
        if (action.equals("searchTaskTime")) {
            return aroundDataSet("TASKTIME", point);
        }
        return point.proceed();
    }

}
