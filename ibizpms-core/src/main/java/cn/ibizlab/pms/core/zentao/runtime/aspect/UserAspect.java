package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import cn.ibizlab.pms.core.sample.runtime.IUserRuntime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Aspect
@org.springframework.core.annotation.Order(100)
@Component("UserRuntime")
@Slf4j
public class UserAspect extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

     @Autowired
    IUserRuntime userRuntime;

    private SystemDataEntityRuntime getDataEntityRuntime() {
        return (SystemDataEntityRuntime) userRuntime;
    }

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.UserServiceImpl.*(..))")
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
        else if (action.equals("getByCommiter")) {
            return getDataEntityRuntime().aroundAction(action, point);
        }
        else if (action.equals("save")) {
            return getDataEntityRuntime().aroundAction("Save", point);
        }
        else if (action.equals("syncAccount")) {
            return getDataEntityRuntime().aroundAction("SyncAccount", point);
        }
        else if (action.equals("searchBugUser")) {
            return getDataEntityRuntime().aroundDataSet("BugUser", point);
        }
        else if (action.equals("searchDefault")) {
            return getDataEntityRuntime().aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchGetByCommiter")) {
            return getDataEntityRuntime().aroundDataSet("getByCommiter", point);
        }
        else if (action.equals("searchProjectTeamM")) {
            return getDataEntityRuntime().aroundDataSet("ProjectTeamM", point);
        }
        else if (action.equals("searchProjectTeamUser")) {
            return getDataEntityRuntime().aroundDataSet("ProjectTeamUser", point);
        }
        else if (action.equals("searchProjectTeamUserTask")) {
            return getDataEntityRuntime().aroundDataSet("ProjectTeamUserTask", point);
        }
        else if (action.equals("searchTaskTeam")) {
            return getDataEntityRuntime().aroundDataSet("TASKTEAM", point);
        }
        return point.proceed();
    }
}
