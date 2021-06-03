package cn.ibizlab.pms.core.ibiz.runtime.aspect;

import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import cn.ibizlab.pms.core.ibiz.runtime.IProjectTeamRuntime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Aspect
@org.springframework.core.annotation.Order(100)
@Slf4j
@Component
public class ProjectTeamAspect {

     @Autowired
    IProjectTeamRuntime projectTeamRuntime;

    private SystemDataEntityRuntime getDataEntityRuntime() {
        return (SystemDataEntityRuntime) projectTeamRuntime;
    }

    @Around("execution(* cn.ibizlab.pms.core.ibiz.service.impl.ProjectTeamServiceImpl.*(..))")
    @Transactional
    public Object aroundMethod(ProceedingJoinPoint point) throws Throwable {
        if (!getDataEntityRuntime().isRtmodel()) {
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
        else if (action.equals("getUserRole")) {
            return getDataEntityRuntime().aroundAction("GetUserRole", point);
        }
        else if (action.equals("save")) {
            return getDataEntityRuntime().aroundAction("Save", point);
        }
        else if (action.equals("searchDefault")) {
            return getDataEntityRuntime().aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchProjectTeamPm")) {
            return getDataEntityRuntime().aroundDataSet("ProjectTeamPm", point);
        }
        else if (action.equals("searchRowEditDefault")) {
            return getDataEntityRuntime().aroundDataSet("RowEditDefault", point);
        }
        else if (action.equals("searchSpecifyTeam")) {
            return getDataEntityRuntime().aroundDataSet("SpecifyTeam", point);
        }
        else if (action.equals("searchTaskCntEstimateConsumedLeft")) {
            return getDataEntityRuntime().aroundDataSet("TaskCntEstimateConsumedLeft", point);
        }
        return point.proceed();
    }
}
