package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import cn.ibizlab.pms.core.sample.runtime.IDocLibModuleRuntime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Aspect
@org.springframework.core.annotation.Order(100)
@Component("DocLibModuleRuntime")
@Slf4j
public class DocLibModuleAspect extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

     @Autowired
    IDocLibModuleRuntime docLibModuleRuntime;

    private SystemDataEntityRuntime getDataEntityRuntime() {
        return (SystemDataEntityRuntime) docLibModuleRuntime;
    }

    @Around("execution(* cn.ibizlab.pms.core.ibiz.service.impl.DocLibModuleServiceImpl.*(..))")
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
        else if (action.equals("collect")) {
            return getDataEntityRuntime().aroundAction("Collect", point);
        }
        else if (action.equals("docLibModuleNFavorite")) {
            return getDataEntityRuntime().aroundAction("DocLibModuleNFavorite", point);
        }
        else if (action.equals("doclibModuleFavorite")) {
            return getDataEntityRuntime().aroundAction("DoclibModuleFavorite", point);
        }
        else if (action.equals("fix")) {
            return getDataEntityRuntime().aroundAction("Fix", point);
        }
        else if (action.equals("save")) {
            return getDataEntityRuntime().aroundAction("Save", point);
        }
        else if (action.equals("unCollect")) {
            return getDataEntityRuntime().aroundAction("UnCollect", point);
        }
        else if (action.equals("searchAllDocLibModule_Custom")) {
            return getDataEntityRuntime().aroundDataSet("AllDocLibModule_Custom", point);
        }
        else if (action.equals("searchAllDoclibModule")) {
            return getDataEntityRuntime().aroundDataSet("AllDoclibModule", point);
        }
        else if (action.equals("searchChildModuleByParent")) {
            return getDataEntityRuntime().aroundDataSet("ChildModuleByParent", point);
        }
        else if (action.equals("searchChildModuleByRealParent")) {
            return getDataEntityRuntime().aroundDataSet("ChildModuleByRealParent", point);
        }
        else if (action.equals("searchDefault")) {
            return getDataEntityRuntime().aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchMyFavourites")) {
            return getDataEntityRuntime().aroundDataSet("MyFavourites", point);
        }
        else if (action.equals("searchParentModule")) {
            return getDataEntityRuntime().aroundDataSet("ParentModule", point);
        }
        else if (action.equals("searchRootModuleMuLu")) {
            return getDataEntityRuntime().aroundDataSet("RootModuleMuLu", point);
        }
        else if (action.equals("searchRootModuleMuLuByRoot")) {
            return getDataEntityRuntime().aroundDataSet("RootModuleMuLuByRoot", point);
        }
        else if (action.equals("searchRootModuleMuLuBysrfparentkey")) {
            return getDataEntityRuntime().aroundDataSet("RootModuleMuLuBysrfparentkey", point);
        }
        return point.proceed();
    }
}
