package cn.ibizlab.pms.core.ou.runtime.aspect;

import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import cn.ibizlab.pms.core.ou.runtime.ISysEmployeeRuntime;
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
public class SysEmployeeAspect {

     @Autowired
    ISysEmployeeRuntime sysEmployeeRuntime;

    private SystemDataEntityRuntime getDataEntityRuntime() {
        return (SystemDataEntityRuntime) sysEmployeeRuntime;
    }

    @Around("execution(* cn.ibizlab.pms.core.ou.service.impl.SysEmployeeServiceImpl.*(..))")
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
        else if (action.equals("save")) {
            return getDataEntityRuntime().aroundAction("Save", point);
        }
        else if (action.equals("searchBugUser")) {
            return getDataEntityRuntime().aroundDataSet("BugUser", point);
        }
        else if (action.equals("searchContActList")) {
            return getDataEntityRuntime().aroundDataSet("ContActList", point);
        }
        else if (action.equals("searchDefault")) {
            return getDataEntityRuntime().aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchProductTeamM")) {
            return getDataEntityRuntime().aroundDataSet("ProductTeamM", point);
        }
        else if (action.equals("searchProjectTeamM")) {
            return getDataEntityRuntime().aroundDataSet("ProjectTeamM", point);
        }
        else if (action.equals("searchProjectTeamMProduct")) {
            return getDataEntityRuntime().aroundDataSet("ProjectTeamMProduct", point);
        }
        else if (action.equals("searchProjectTeamTaskUserTemp")) {
            return getDataEntityRuntime().aroundDataSet("ProjectTeamTaskUserTemp", point);
        }
        else if (action.equals("searchProjectTeamUser1")) {
            return getDataEntityRuntime().aroundDataSet("ProjectTeamUser", point);
        }
        else if (action.equals("searchProjectTeamUserTask")) {
            return getDataEntityRuntime().aroundDataSet("ProjectTeamUserTask", point);
        }
        else if (action.equals("searchProjectteamPk")) {
            return getDataEntityRuntime().aroundDataSet("ProjectteamPk", point);
        }
        else if (action.equals("searchStoryProductTeamPK")) {
            return getDataEntityRuntime().aroundDataSet("StoryProductTeamChoice", point);
        }
        else if (action.equals("searchTaskMTeam")) {
            return getDataEntityRuntime().aroundDataSet("TaskMTeam", point);
        }
        else if (action.equals("searchTaskTeam")) {
            return getDataEntityRuntime().aroundDataSet("TASKTEAM", point);
        }
        return point.proceed();
    }
}
