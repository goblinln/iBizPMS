package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import cn.ibizlab.pms.core.sample.runtime.IProductStatsRuntime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Aspect
@org.springframework.core.annotation.Order(100)
@Component("ProductStatsRuntime")
@Slf4j
public class ProductStatsAspect extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

     @Autowired
    IProductStatsRuntime productStatsRuntime;

    private SystemDataEntityRuntime getDataEntityRuntime() {
        return (SystemDataEntityRuntime) productStatsRuntime;
    }

    @Around("execution(* cn.ibizlab.pms.core.ibiz.service.impl.ProductStatsServiceImpl.*(..))")
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
        else if (action.equals("getTestStats")) {
            return getDataEntityRuntime().aroundAction(action, point);
        }
        else if (action.equals("save")) {
            return getDataEntityRuntime().aroundAction("Save", point);
        }
        else if (action.equals("searchDefault")) {
            return getDataEntityRuntime().aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchNoOpenProduct")) {
            return getDataEntityRuntime().aroundDataSet("NoOpenProduct", point);
        }
        else if (action.equals("searchProdctQuantiGird")) {
            return getDataEntityRuntime().aroundDataSet("ProdctQuantiGird", point);
        }
        else if (action.equals("searchProductInputTable")) {
            return getDataEntityRuntime().aroundDataSet("ProductInputTable", point);
        }
        else if (action.equals("searchProductcompletionstatistics")) {
            return getDataEntityRuntime().aroundDataSet("Productcompletionstatistics", point);
        }
        return point.proceed();
    }
}
