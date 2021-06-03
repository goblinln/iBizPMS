package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import cn.ibizlab.pms.core.sample.runtime.ITestReportRuntime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Aspect
@org.springframework.core.annotation.Order(100)
@Component("TestReportRuntime")
@Slf4j
public class TestReportAspect extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

     @Autowired
    ITestReportRuntime testReportRuntime;

    private SystemDataEntityRuntime getDataEntityRuntime() {
        return (SystemDataEntityRuntime) testReportRuntime;
    }

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.TestReportServiceImpl.*(..))")
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
        else if (action.equals("getInfoTaskOvByTime")) {
            return getDataEntityRuntime().aroundAction("GetInfoTaskOvByTime", point);
        }
        else if (action.equals("getInfoTestTask")) {
            return getDataEntityRuntime().aroundAction("GetInfoTestTask", point);
        }
        else if (action.equals("getInfoTestTaskOvProject")) {
            return getDataEntityRuntime().aroundAction("GetInfoTestTaskOvProject", point);
        }
        else if (action.equals("getInfoTestTaskProject")) {
            return getDataEntityRuntime().aroundAction("GetInfoTestTaskProject", point);
        }
        else if (action.equals("getInfoTestTaskR")) {
            return getDataEntityRuntime().aroundAction("GetInfoTestTaskR", point);
        }
        else if (action.equals("getInfoTestTaskS")) {
            return getDataEntityRuntime().aroundAction("GetInfoTestTaskS", point);
        }
        else if (action.equals("getTestReportBasicInfo")) {
            return getDataEntityRuntime().aroundAction("GetTestReportBasicInfo", point);
        }
        else if (action.equals("getTestReportProject")) {
            return getDataEntityRuntime().aroundAction("GetTestReportProject", point);
        }
        else if (action.equals("save")) {
            return getDataEntityRuntime().aroundAction("Save", point);
        }
        else if (action.equals("searchDefault")) {
            return getDataEntityRuntime().aroundDataSet("DEFAULT", point);
        }
        return point.proceed();
    }
}
