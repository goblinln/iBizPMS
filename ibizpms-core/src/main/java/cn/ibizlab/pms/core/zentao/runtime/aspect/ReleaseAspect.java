package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import cn.ibizlab.pms.core.sample.runtime.IReleaseRuntime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Aspect
@org.springframework.core.annotation.Order(100)
@Component("ReleaseRuntime")
@Slf4j
public class ReleaseAspect extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

     @Autowired
    IReleaseRuntime releaseRuntime;

    private SystemDataEntityRuntime getDataEntityRuntime() {
        return (SystemDataEntityRuntime) releaseRuntime;
    }

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.ReleaseServiceImpl.*(..))")
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
        else if (action.equals("activate")) {
            return getDataEntityRuntime().aroundAction("Activate", point);
        }
        else if (action.equals("batchUnlinkBug")) {
            return getDataEntityRuntime().aroundAction("BatchUnlinkBug", point);
        }
        else if (action.equals("changeStatus")) {
            return getDataEntityRuntime().aroundAction("ChangeStatus", point);
        }
        else if (action.equals("checkKey")) {
            return getDataEntityRuntime().aroundAction("CheckKey", point);
        }
        else if (action.equals("linkBug")) {
            return getDataEntityRuntime().aroundAction("linkBug", point);
        }
        else if (action.equals("linkBugbyBug")) {
            return getDataEntityRuntime().aroundAction("LinkBugbyBug", point);
        }
        else if (action.equals("linkBugbyLeftBug")) {
            return getDataEntityRuntime().aroundAction("LinkBugbyLeftBug", point);
        }
        else if (action.equals("linkStory")) {
            return getDataEntityRuntime().aroundAction("linkStory", point);
        }
        else if (action.equals("mobReleaseCounter")) {
            return getDataEntityRuntime().aroundAction("MobReleaseCounter", point);
        }
        else if (action.equals("oneClickRelease")) {
            return getDataEntityRuntime().aroundAction("OneClickRelease", point);
        }
        else if (action.equals("save")) {
            return getDataEntityRuntime().aroundAction("Save", point);
        }
        else if (action.equals("terminate")) {
            return getDataEntityRuntime().aroundAction("Terminate", point);
        }
        else if (action.equals("unlinkBug")) {
            return getDataEntityRuntime().aroundAction("UnlinkBug", point);
        }
        else if (action.equals("unlinkStory")) {
            return getDataEntityRuntime().aroundAction("UnlinkStory", point);
        }
        else if (action.equals("searchDefault")) {
            return getDataEntityRuntime().aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchReportRelease")) {
            return getDataEntityRuntime().aroundDataSet("ReportRelease", point);
        }
        return point.proceed();
    }
}
