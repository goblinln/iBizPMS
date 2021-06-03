package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import cn.ibizlab.pms.core.zentao.runtime.IDocRuntime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Aspect
@org.springframework.core.annotation.Order(100)
@Slf4j
public class DocAspect {

     @Autowired
    IDocRuntime docRuntime;

    private SystemDataEntityRuntime getDataEntityRuntime() {
        return (SystemDataEntityRuntime) docRuntime;
    }

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.DocServiceImpl.*(..))")
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
        else if (action.equals("byVersionUpdateContext")) {
            return getDataEntityRuntime().aroundAction("ByVersionUpdateContext", point);
        }
        else if (action.equals("checkKey")) {
            return getDataEntityRuntime().aroundAction("CheckKey", point);
        }
        else if (action.equals("collect")) {
            return getDataEntityRuntime().aroundAction("Collect", point);
        }
        else if (action.equals("getDocStatus")) {
            return getDataEntityRuntime().aroundAction("GetDocStatus", point);
        }
        else if (action.equals("onlyCollectDoc")) {
            return getDataEntityRuntime().aroundAction("OnlyCollectDoc", point);
        }
        else if (action.equals("onlyUnCollectDoc")) {
            return getDataEntityRuntime().aroundAction("OnlyUnCollectDoc", point);
        }
        else if (action.equals("save")) {
            return getDataEntityRuntime().aroundAction("Save", point);
        }
        else if (action.equals("unCollect")) {
            return getDataEntityRuntime().aroundAction("UnCollect", point);
        }
        else if (action.equals("searchChildDocLibDoc")) {
            return getDataEntityRuntime().aroundDataSet("ChildDocLibDoc", point);
        }
        else if (action.equals("searchDefault")) {
            return getDataEntityRuntime().aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchDocLibAndDoc")) {
            return getDataEntityRuntime().aroundDataSet("DocLibAndDoc", point);
        }
        else if (action.equals("searchDocLibDoc")) {
            return getDataEntityRuntime().aroundDataSet("DocLibDoc", point);
        }
        else if (action.equals("searchDocModuleDoc")) {
            return getDataEntityRuntime().aroundDataSet("DocModuleDoc", point);
        }
        else if (action.equals("searchDocStatus")) {
            return getDataEntityRuntime().aroundDataSet("DocStatus", point);
        }
        else if (action.equals("searchModuleDocChild")) {
            return getDataEntityRuntime().aroundDataSet("ModuleDocChild", point);
        }
        else if (action.equals("searchMyCreateOrUpdateDoc")) {
            return getDataEntityRuntime().aroundDataSet("MyCreateOrUpdateDoc", point);
        }
        else if (action.equals("searchMyFavourite")) {
            return getDataEntityRuntime().aroundDataSet("MYFAVOURITE", point);
        }
        else if (action.equals("searchMyFavouritesOnlyDoc")) {
            return getDataEntityRuntime().aroundDataSet("MyFavouritesOnlyDoc", point);
        }
        else if (action.equals("searchNotRootDoc")) {
            return getDataEntityRuntime().aroundDataSet("NotRootDoc", point);
        }
        else if (action.equals("searchRootDoc")) {
            return getDataEntityRuntime().aroundDataSet("RootDoc", point);
        }
        return point.proceed();
    }
}
