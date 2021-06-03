package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import cn.ibizlab.pms.core.zentao.runtime.IBuildRuntime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Aspect
@org.springframework.core.annotation.Order(100)
@Slf4j
public class BuildAspect {

     @Autowired
    IBuildRuntime buildRuntime;

    private SystemDataEntityRuntime getDataEntityRuntime() {
        return (SystemDataEntityRuntime) buildRuntime;
    }

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.BuildServiceImpl.*(..))")
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
        else if (action.equals("linkBug")) {
            return getDataEntityRuntime().aroundAction("linkBug", point);
        }
        else if (action.equals("linkStory")) {
            return getDataEntityRuntime().aroundAction("LinkStory", point);
        }
        else if (action.equals("mobProjectBuildCounter")) {
            return getDataEntityRuntime().aroundAction("MobProjectBuildCounter", point);
        }
        else if (action.equals("oneClickRelease")) {
            return getDataEntityRuntime().aroundAction("OneClickRelease", point);
        }
        else if (action.equals("save")) {
            return getDataEntityRuntime().aroundAction("Save", point);
        }
        else if (action.equals("unlinkBug")) {
            return getDataEntityRuntime().aroundAction("unlinkBug", point);
        }
        else if (action.equals("unlinkStory")) {
            return getDataEntityRuntime().aroundAction("unlinkStory", point);
        }
        else if (action.equals("searchBugProductBuild")) {
            return getDataEntityRuntime().aroundDataSet("BugProductBuild", point);
        }
        else if (action.equals("searchBugProductOrProjectBuild")) {
            return getDataEntityRuntime().aroundDataSet("BugProductOrProjectBuild", point);
        }
        else if (action.equals("searchCurProduct")) {
            return getDataEntityRuntime().aroundDataSet("CurProduct", point);
        }
        else if (action.equals("searchDefault")) {
            return getDataEntityRuntime().aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchProductBuildDS")) {
            return getDataEntityRuntime().aroundDataSet("ProductBuildDS", point);
        }
        else if (action.equals("searchTestBuild")) {
            return getDataEntityRuntime().aroundDataSet("TestBuild", point);
        }
        else if (action.equals("searchTestRounds")) {
            return getDataEntityRuntime().aroundDataSet("TestRounds", point);
        }
        else if (action.equals("searchUpdateLog")) {
            return getDataEntityRuntime().aroundDataSet("UpdateLog", point);
        }
        return point.proceed();
    }
}
