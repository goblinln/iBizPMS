package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import cn.ibizlab.pms.core.zentao.runtime.IFileRuntime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Aspect
@org.springframework.core.annotation.Order(100)
@Slf4j
public class FileAspect {

     @Autowired
    IFileRuntime fileRuntime;

    private SystemDataEntityRuntime getDataEntityRuntime() {
        return (SystemDataEntityRuntime) fileRuntime;
    }

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.FileServiceImpl.*(..))")
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
        else if (action.equals("save")) {
            return getDataEntityRuntime().aroundAction("Save", point);
        }
        else if (action.equals("updateObjectID")) {
            return getDataEntityRuntime().aroundAction("updateObjectID", point);
        }
        else if (action.equals("updateObjectIDForPmsEe")) {
            return getDataEntityRuntime().aroundAction("updateObjectIDForPmsEe", point);
        }
        else if (action.equals("searchDefault")) {
            return getDataEntityRuntime().aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchDocLibFile")) {
            return getDataEntityRuntime().aroundDataSet("DocLibFile", point);
        }
        else if (action.equals("searchMySubmitFile")) {
            return getDataEntityRuntime().aroundDataSet("MySubmitFile", point);
        }
        else if (action.equals("searchProductDocLibFile")) {
            return getDataEntityRuntime().aroundDataSet("ProductDocLibFile", point);
        }
        else if (action.equals("searchType")) {
            return getDataEntityRuntime().aroundDataSet("Type", point);
        }
        else if (action.equals("searchTypeNotBySrfparentkey")) {
            return getDataEntityRuntime().aroundDataSet("TypeNotBySrfparentkey", point);
        }
        return point.proceed();
    }
}
