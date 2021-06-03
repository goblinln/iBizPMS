package cn.ibizlab.pms.core.report.runtime;

import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import cn.ibizlab.pms.core.report.runtime.IIbzDailyRuntime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Aspect
@org.springframework.core.annotation.Order(100)
@Slf4j
public class IbzDailyAspect {

     @Autowired
    IIbzDailyRuntime ibzDailyRuntime;

    private SystemDataEntityRuntime getDataEntityRuntime() {
        return (SystemDataEntityRuntime) ibzDailyRuntime;
    }

    @Around("execution(* cn.ibizlab.pms.core.report.service.impl.IbzDailyServiceImpl.*(..))")
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
        else if (action.equals("createUserDaily")) {
            return getDataEntityRuntime().aroundAction("CreateUserDaily", point);
        }
        else if (action.equals("getYeaterdayDailyPlansTaskEdit")) {
            return getDataEntityRuntime().aroundAction("getYeaterdayDailyPlansTaskEdit", point);
        }
        else if (action.equals("getYesterdayDailyPlansTask")) {
            return getDataEntityRuntime().aroundAction("getYesterdayDailyPlansTask", point);
        }
        else if (action.equals("haveRead")) {
            return getDataEntityRuntime().aroundAction("HaveRead", point);
        }
        else if (action.equals("linkCompleteTask")) {
            return getDataEntityRuntime().aroundAction("LinkCompleteTask", point);
        }
        else if (action.equals("pushUserDaily")) {
            return getDataEntityRuntime().aroundAction("PushUserDaily", point);
        }
        else if (action.equals("save")) {
            return getDataEntityRuntime().aroundAction("Save", point);
        }
        else if (action.equals("submit")) {
            return getDataEntityRuntime().aroundAction("submit", point);
        }
        else if (action.equals("searchDefault")) {
            return getDataEntityRuntime().aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchMyAllDaily")) {
            return getDataEntityRuntime().aroundDataSet("MyAllDaily", point);
        }
        else if (action.equals("searchMyDaily")) {
            return getDataEntityRuntime().aroundDataSet("MyDaily", point);
        }
        else if (action.equals("searchMyNotSubmit")) {
            return getDataEntityRuntime().aroundDataSet("MyNotSubmit", point);
        }
        else if (action.equals("searchMySubmitDaily")) {
            return getDataEntityRuntime().aroundDataSet("MySubmitDaily", point);
        }
        else if (action.equals("searchProductDaily")) {
            return getDataEntityRuntime().aroundDataSet("ProductDaily", point);
        }
        else if (action.equals("searchProjectDaily")) {
            return getDataEntityRuntime().aroundDataSet("ProjectDaily", point);
        }
        return point.proceed();
    }
}
