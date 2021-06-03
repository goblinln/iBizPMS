package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import cn.ibizlab.pms.core.ibiz.runtime.IProductSumRuntime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Aspect
@org.springframework.core.annotation.Order(100)
@Slf4j
public class ProductSumAspect {

     @Autowired
    IProductSumRuntime productSumRuntime;

    private SystemDataEntityRuntime getDataEntityRuntime() {
        return (SystemDataEntityRuntime) productSumRuntime;
    }

    @Around("execution(* cn.ibizlab.pms.core.ibiz.service.impl.ProductSumServiceImpl.*(..))")
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
        else if (action.equals("searchDefault")) {
            return getDataEntityRuntime().aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchProductBugcnt_QA")) {
            return getDataEntityRuntime().aroundDataSet("ProductBugcnt_QA", point);
        }
        else if (action.equals("searchProductCreateStory")) {
            return getDataEntityRuntime().aroundDataSet("ProductCreateStory", point);
        }
        else if (action.equals("searchProductStoryHoursSum")) {
            return getDataEntityRuntime().aroundDataSet("ProductStoryHoursSum", point);
        }
        else if (action.equals("searchProductStorySum")) {
            return getDataEntityRuntime().aroundDataSet("ProductStorySum", point);
        }
        else if (action.equals("searchProductStorycntAndPlancnt")) {
            return getDataEntityRuntime().aroundDataSet("ProductStorycntAndPlancnt", point);
        }
        else if (action.equals("searchProductSumBugType")) {
            return getDataEntityRuntime().aroundDataSet("ProductSumBugType", point);
        }
        return point.proceed();
    }
}
