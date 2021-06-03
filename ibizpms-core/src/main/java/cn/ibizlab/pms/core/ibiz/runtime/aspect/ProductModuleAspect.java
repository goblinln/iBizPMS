package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import cn.ibizlab.pms.core.ibiz.runtime.IProductModuleRuntime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Aspect
@org.springframework.core.annotation.Order(100)
@Slf4j
public class ProductModuleAspect {

     @Autowired
    IProductModuleRuntime productModuleRuntime;

    private SystemDataEntityRuntime getDataEntityRuntime() {
        return (SystemDataEntityRuntime) productModuleRuntime;
    }

    @Around("execution(* cn.ibizlab.pms.core.ibiz.service.impl.ProductModuleServiceImpl.*(..))")
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
        else if (action.equals("syncFromIBIZ")) {
            return getDataEntityRuntime().aroundAction("SyncFromIBIZ", point);
        }
        else if (action.equals("searchByPath")) {
            return getDataEntityRuntime().aroundDataSet("BYPATH", point);
        }
        else if (action.equals("searchDefault")) {
            return getDataEntityRuntime().aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchParentModule")) {
            return getDataEntityRuntime().aroundDataSet("ParentModule", point);
        }
        else if (action.equals("searchRoot")) {
            return getDataEntityRuntime().aroundDataSet("ROOT", point);
        }
        else if (action.equals("searchRoot_NoBranch")) {
            return getDataEntityRuntime().aroundDataSet("Root_NoBranch", point);
        }
        else if (action.equals("searchStoryModule")) {
            return getDataEntityRuntime().aroundDataSet("StoryModule", point);
        }
        return point.proceed();
    }
}
