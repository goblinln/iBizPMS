package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import cn.ibizlab.pms.core.sample.runtime.IIbzMyTerritoryRuntime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Aspect
@org.springframework.core.annotation.Order(100)
@Component("IbzMyTerritoryRuntime")
@Slf4j
public class IbzMyTerritoryAspect extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

     @Autowired
    IIbzMyTerritoryRuntime ibzMyTerritoryRuntime;

    private SystemDataEntityRuntime getDataEntityRuntime() {
        return (SystemDataEntityRuntime) ibzMyTerritoryRuntime;
    }

    @Around("execution(* cn.ibizlab.pms.core.ibiz.service.impl.IbzMyTerritoryServiceImpl.*(..))")
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
        else if (action.equals("mobMenuCount")) {
            return getDataEntityRuntime().aroundAction("MobMenuCount", point);
        }
        else if (action.equals("myFavoriteCount")) {
            return getDataEntityRuntime().aroundAction("MyFavoriteCount", point);
        }
        else if (action.equals("myTerritoryCount")) {
            return getDataEntityRuntime().aroundAction("MyTerritoryCount", point);
        }
        else if (action.equals("save")) {
            return getDataEntityRuntime().aroundAction("Save", point);
        }
        else if (action.equals("searchDefault")) {
            return getDataEntityRuntime().aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchMyWork")) {
            return getDataEntityRuntime().aroundDataSet("MyWork", point);
        }
        else if (action.equals("searchMyWorkMob")) {
            return getDataEntityRuntime().aroundDataSet("MyWorkMob", point);
        }
        else if (action.equals("searchMyWorkPm")) {
            return getDataEntityRuntime().aroundDataSet("MyWorkPm", point);
        }
        else if (action.equals("searchPersonInfo")) {
            return getDataEntityRuntime().aroundDataSet("PersonInfo", point);
        }
        else if (action.equals("searchWelcome")) {
            return getDataEntityRuntime().aroundDataSet("welcome", point);
        }
        return point.proceed();
    }
}
