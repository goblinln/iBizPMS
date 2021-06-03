package cn.ibizlab.pms.core.report.runtime.aspect;

import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import cn.ibizlab.pms.core.report.runtime.IIbzMonthlyRuntime;
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
public class IbzMonthlyAspect {

     @Autowired
    IIbzMonthlyRuntime ibzMonthlyRuntime;

    private SystemDataEntityRuntime getDataEntityRuntime() {
        return (SystemDataEntityRuntime) ibzMonthlyRuntime;
    }

    @Around("execution(* cn.ibizlab.pms.core.report.service.impl.IbzMonthlyServiceImpl.*(..))")
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
        else if (action.equals("createGetInfo")) {
            return getDataEntityRuntime().aroundAction("CreateGetInfo", point);
        }
        else if (action.equals("createUserMonthly")) {
            return getDataEntityRuntime().aroundAction("CreateUserMonthly", point);
        }
        else if (action.equals("editGetCompleteTask")) {
            return getDataEntityRuntime().aroundAction("EditGetCompleteTask", point);
        }
        else if (action.equals("haveRead")) {
            return getDataEntityRuntime().aroundAction("HaveRead", point);
        }
        else if (action.equals("pushUserMonthly")) {
            return getDataEntityRuntime().aroundAction("PushUserMonthly", point);
        }
        else if (action.equals("save")) {
            return getDataEntityRuntime().aroundAction("Save", point);
        }
        else if (action.equals("submit")) {
            return getDataEntityRuntime().aroundAction("Submit", point);
        }
        else if (action.equals("searchDefault")) {
            return getDataEntityRuntime().aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchMyMonthly")) {
            return getDataEntityRuntime().aroundDataSet("MyMonthly", point);
        }
        else if (action.equals("searchMyMonthlyMob")) {
            return getDataEntityRuntime().aroundDataSet("MyMonthlyMob", point);
        }
        else if (action.equals("searchMyReceivedMonthly")) {
            return getDataEntityRuntime().aroundDataSet("MyReceivedMonthly", point);
        }
        else if (action.equals("searchMySubmitMonthly")) {
            return getDataEntityRuntime().aroundDataSet("MySubmitMonthly", point);
        }
        else if (action.equals("searchProductMonthly")) {
            return getDataEntityRuntime().aroundDataSet("ProductMonthly", point);
        }
        else if (action.equals("searchProjectMonthly")) {
            return getDataEntityRuntime().aroundDataSet("ProjectMonthly", point);
        }
        return point.proceed();
    }
}
