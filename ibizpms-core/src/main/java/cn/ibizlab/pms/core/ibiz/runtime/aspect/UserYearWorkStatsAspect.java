package cn.ibizlab.pms.core.ibiz.runtime.aspect;

import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import cn.ibizlab.pms.core.ibiz.runtime.IUserYearWorkStatsRuntime;
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
public class UserYearWorkStatsAspect {

     @Autowired
    IUserYearWorkStatsRuntime userYearWorkStatsRuntime;

    private SystemDataEntityRuntime getDataEntityRuntime() {
        return (SystemDataEntityRuntime) userYearWorkStatsRuntime;
    }

    @Around("execution(* cn.ibizlab.pms.core.ibiz.service.impl.UserYearWorkStatsServiceImpl.*(..))")
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
        else if (action.equals("getDevInfomation")) {
            return getDataEntityRuntime().aroundAction("GetDevInfomation", point);
        }
        else if (action.equals("getPoInfomation")) {
            return getDataEntityRuntime().aroundAction("GetPoInfomation", point);
        }
        else if (action.equals("getQaInfomation")) {
            return getDataEntityRuntime().aroundAction("GetQaInfomation", point);
        }
        else if (action.equals("getUserYearAction")) {
            return getDataEntityRuntime().aroundAction("GetUserYearAction", point);
        }
        else if (action.equals("save")) {
            return getDataEntityRuntime().aroundAction("Save", point);
        }
        else if (action.equals("updateTitleByYear")) {
            return getDataEntityRuntime().aroundAction("UpdateTitleByYear", point);
        }
        else if (action.equals("searchDefault")) {
            return getDataEntityRuntime().aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchMonthFinishTaskAndBug")) {
            return getDataEntityRuntime().aroundDataSet("MonthFinishTaskAndBug", point);
        }
        else if (action.equals("searchMonthOpenedBugAndCase")) {
            return getDataEntityRuntime().aroundDataSet("MonthOpenedBugAndCase", point);
        }
        else if (action.equals("searchMonthOpenedStory")) {
            return getDataEntityRuntime().aroundDataSet("MonthOpenedStory", point);
        }
        return point.proceed();
    }
}
