package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import cn.ibizlab.pms.core.zentao.runtime.IModuleRuntime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Aspect
@org.springframework.core.annotation.Order(100)
@Slf4j
public class ModuleAspect {

     @Autowired
    IModuleRuntime moduleRuntime;

    private SystemDataEntityRuntime getDataEntityRuntime() {
        return (SystemDataEntityRuntime) moduleRuntime;
    }

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.ModuleServiceImpl.*(..))")
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
        else if (action.equals("fix")) {
            return getDataEntityRuntime().aroundAction("Fix", point);
        }
        else if (action.equals("save")) {
            return getDataEntityRuntime().aroundAction("Save", point);
        }
        else if (action.equals("searchBugModule")) {
            return getDataEntityRuntime().aroundDataSet("BugModule", point);
        }
        else if (action.equals("searchBugModuleCodeList")) {
            return getDataEntityRuntime().aroundDataSet("BugModuleCodeList", point);
        }
        else if (action.equals("searchDefault")) {
            return getDataEntityRuntime().aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchDocModule")) {
            return getDataEntityRuntime().aroundDataSet("DocModule", point);
        }
        else if (action.equals("searchLine")) {
            return getDataEntityRuntime().aroundDataSet("Line", point);
        }
        else if (action.equals("searchStoryModule")) {
            return getDataEntityRuntime().aroundDataSet("StoryModule", point);
        }
        else if (action.equals("searchTaskModule")) {
            return getDataEntityRuntime().aroundDataSet("TaskModule", point);
        }
        return point.proceed();
    }
}
