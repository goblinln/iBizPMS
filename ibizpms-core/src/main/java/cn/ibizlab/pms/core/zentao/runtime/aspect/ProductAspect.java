package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import cn.ibizlab.pms.core.sample.runtime.IProductRuntime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Aspect
@org.springframework.core.annotation.Order(100)
@Component("ProductRuntime")
@Slf4j
public class ProductAspect extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

     @Autowired
    IProductRuntime productRuntime;

    private SystemDataEntityRuntime getDataEntityRuntime() {
        return (SystemDataEntityRuntime) productRuntime;
    }

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.ProductServiceImpl.*(..))")
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
        else if (action.equals("cancelProductTop")) {
            return getDataEntityRuntime().aroundAction("CancelProductTop", point);
        }
        else if (action.equals("checkKey")) {
            return getDataEntityRuntime().aroundAction("CheckKey", point);
        }
        else if (action.equals("close")) {
            return getDataEntityRuntime().aroundAction("Close", point);
        }
        else if (action.equals("mobProductCounter")) {
            return getDataEntityRuntime().aroundAction("MobProductCounter", point);
        }
        else if (action.equals("mobProductTestCounter")) {
            return getDataEntityRuntime().aroundAction("MobProductTestCounter", point);
        }
        else if (action.equals("productTop")) {
            return getDataEntityRuntime().aroundAction("ProductTop", point);
        }
        else if (action.equals("save")) {
            return getDataEntityRuntime().aroundAction("Save", point);
        }
        else if (action.equals("searchAccount")) {
            return getDataEntityRuntime().aroundDataSet("Account", point);
        }
        else if (action.equals("searchAllList")) {
            return getDataEntityRuntime().aroundDataSet("AllList", point);
        }
        else if (action.equals("searchAllProduct")) {
            return getDataEntityRuntime().aroundDataSet("AllProduct", point);
        }
        else if (action.equals("searchCheckNameOrCode")) {
            return getDataEntityRuntime().aroundDataSet("CheckNameOrCode", point);
        }
        else if (action.equals("searchCurDefault")) {
            return getDataEntityRuntime().aroundDataSet("CurDefault", point);
        }
        else if (action.equals("searchCurProject")) {
            return getDataEntityRuntime().aroundDataSet("CURPROJECT", point);
        }
        else if (action.equals("searchCurUer")) {
            return getDataEntityRuntime().aroundDataSet("CurUer", point);
        }
        else if (action.equals("searchDefault")) {
            return getDataEntityRuntime().aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchDeveloperQuery")) {
            return getDataEntityRuntime().aroundDataSet("DeveloperQuery", point);
        }
        else if (action.equals("searchESBulk")) {
            return getDataEntityRuntime().aroundDataSet("ESBulk", point);
        }
        else if (action.equals("searchMy")) {
            return getDataEntityRuntime().aroundDataSet("My", point);
        }
        else if (action.equals("searchOpenQuery")) {
            return getDataEntityRuntime().aroundDataSet("OpenQuery", point);
        }
        else if (action.equals("searchProductManagerQuery")) {
            return getDataEntityRuntime().aroundDataSet("ProductManagerQuery", point);
        }
        else if (action.equals("searchProductPM")) {
            return getDataEntityRuntime().aroundDataSet("ProductPM", point);
        }
        else if (action.equals("searchProductTeam")) {
            return getDataEntityRuntime().aroundDataSet("ProductTeam", point);
        }
        else if (action.equals("searchStoryCurProject")) {
            return getDataEntityRuntime().aroundDataSet("StoryCURPROJECT", point);
        }
        return point.proceed();
    }
}
