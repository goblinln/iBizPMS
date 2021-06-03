package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import cn.ibizlab.pms.core.sample.runtime.IProjectStatsRuntime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Aspect
@org.springframework.core.annotation.Order(100)
@Component("ProjectStatsRuntime")
@Slf4j
public class ProjectStatsAspect extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

     @Autowired
    IProjectStatsRuntime projectStatsRuntime;

    private SystemDataEntityRuntime getDataEntityRuntime() {
        return (SystemDataEntityRuntime) projectStatsRuntime;
    }

    @Around("execution(* cn.ibizlab.pms.core.ibiz.service.impl.ProjectStatsServiceImpl.*(..))")
    @Transactional
    public Object aroundMethod(ProceedingJoinPoint point) throws Throwable {
        if (!this.isRtmodel()) {
            return point.proceed();
        }
        String action = point.getSignature().getName();
        if (action.equals("create")) {
            return getDataEntityRuntime().aroundAction("Create", point);
        }
        else if (action.equals("update")) {
            return getDataEntityRuntime().aroundAction("Update", point);
        }
        else if (action.equals("remove")) {
            return getDataEntityRuntime().aroundAction("Remove", point);
        }
        else if (action.equals("get")) {
            return getDataEntityRuntime().aroundAction("Get", point);
        }
        else if (action.equals("getDraft")) {
            return getDataEntityRuntime().aroundAction("GetDraft", point);
        }
        else if (action.equals("checkKey")) {
            return getDataEntityRuntime().aroundAction("CheckKey", point);
        }
        else if (action.equals("projectQualitySum")) {
            return getDataEntityRuntime().aroundAction("ProjectQualitySum", point);
        }
        else if (action.equals("save")) {
            return getDataEntityRuntime().aroundAction("Save", point);
        }
        else if (action.equals("searchDefault")) {
            return getDataEntityRuntime().aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchNoOpenProduct")) {
            return getDataEntityRuntime().aroundDataSet("NOOpenProduct", point);
        }
        else if (action.equals("searchProjectBugType")) {
            return getDataEntityRuntime().aroundDataSet("ProjectBugType", point);
        }
        else if (action.equals("searchProjectInputStats")) {
            return getDataEntityRuntime().aroundDataSet("ProjectInputStats", point);
        }
        else if (action.equals("searchProjectProgress")) {
            return getDataEntityRuntime().aroundDataSet("ProjectProgress", point);
        }
        else if (action.equals("searchProjectQuality")) {
            return getDataEntityRuntime().aroundDataSet("ProjectQuality", point);
        }
        else if (action.equals("searchProjectStoryStageStats")) {
            return getDataEntityRuntime().aroundDataSet("ProjectStoryStageStats", point);
        }
        else if (action.equals("searchProjectStoryStatusStats")) {
            return getDataEntityRuntime().aroundDataSet("ProjectStoryStatusStats", point);
        }
        else if (action.equals("searchProjectTaskCountByTaskStatus")) {
            return getDataEntityRuntime().aroundDataSet("ProjectTaskCountByTaskStatus", point);
        }
        else if (action.equals("searchProjectTaskCountByType")) {
            return getDataEntityRuntime().aroundDataSet("ProjectTaskCountByType", point);
        }
        else if (action.equals("searchTaskTime")) {
            return getDataEntityRuntime().aroundDataSet("TASKTIME", point);
        }
        return point.proceed();
    }
}
