package cn.ibizlab.pms.core.zentao.runtime.aspect;

import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import cn.ibizlab.pms.core.zentao.runtime.IDocLibRuntime;
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
public class DocLibAspect {

     @Autowired
    IDocLibRuntime docLibRuntime;

    private SystemDataEntityRuntime getDataEntityRuntime() {
        return (SystemDataEntityRuntime) docLibRuntime;
    }

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.DocLibServiceImpl.*(..))")
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
        else if (action.equals("collect")) {
            return getDataEntityRuntime().aroundAction("Collect", point);
        }
        else if (action.equals("save")) {
            return getDataEntityRuntime().aroundAction("Save", point);
        }
        else if (action.equals("unCollect")) {
            return getDataEntityRuntime().aroundAction("UnCollect", point);
        }
        else if (action.equals("searchByCustom")) {
            return getDataEntityRuntime().aroundDataSet("ByCustom", point);
        }
        else if (action.equals("searchByProduct")) {
            return getDataEntityRuntime().aroundDataSet("ByProduct", point);
        }
        else if (action.equals("searchByProductNotFiles")) {
            return getDataEntityRuntime().aroundDataSet("ByProductNotFiles", point);
        }
        else if (action.equals("searchByProject")) {
            return getDataEntityRuntime().aroundDataSet("ByProject", point);
        }
        else if (action.equals("searchByProjectNotFiles")) {
            return getDataEntityRuntime().aroundDataSet("ByProjectNotFiles", point);
        }
        else if (action.equals("searchCurDocLib")) {
            return getDataEntityRuntime().aroundDataSet("CurDocLib", point);
        }
        else if (action.equals("searchDefault")) {
            return getDataEntityRuntime().aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchMyFavourites")) {
            return getDataEntityRuntime().aroundDataSet("MyFavourites", point);
        }
        else if (action.equals("searchRootModuleMuLu")) {
            return getDataEntityRuntime().aroundDataSet("RootModuleMuLu", point);
        }
        return point.proceed();
    }
}
