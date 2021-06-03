package cn.ibizlab.pms.core.ibizpro.runtime;

import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import cn.ibizlab.pms.core.sample.runtime.IIbizproProductDailyRuntime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Aspect
@org.springframework.core.annotation.Order(100)
@Component("IbizproProductDailyRuntime")
@Slf4j
public class IbizproProductDailyAspect extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

     @Autowired
    IIbizproProductDailyRuntime ibizproProductDailyRuntime;

    private SystemDataEntityRuntime getDataEntityRuntime() {
        return (SystemDataEntityRuntime) ibizproProductDailyRuntime;
    }

    @Around("execution(* cn.ibizlab.pms.core.ibizpro.service.impl.IbizproProductDailyServiceImpl.*(..))")
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
        else if (action.equals("manualCreateDaily")) {
            return getDataEntityRuntime().aroundAction("ManualCreateDaily", point);
        }
        else if (action.equals("save")) {
            return getDataEntityRuntime().aroundAction("Save", point);
        }
        else if (action.equals("statsProductDaily")) {
            return getDataEntityRuntime().aroundAction("StatsProductDaily", point);
        }
        else if (action.equals("searchDefault")) {
            return getDataEntityRuntime().aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchProductDaily")) {
            return getDataEntityRuntime().aroundDataSet("ProductDaily", point);
        }
        return point.proceed();
    }
}
