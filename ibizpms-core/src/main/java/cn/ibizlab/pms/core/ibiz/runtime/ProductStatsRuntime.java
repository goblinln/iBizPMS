package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.ibiz.domain.ProductStats;
import cn.ibizlab.pms.core.ibiz.service.IProductStatsService;
import cn.ibizlab.pms.core.ibiz.filter.ProductStatsSearchContext;
import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("ProductStatsRuntime")
public class ProductStatsRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IProductStatsService productstatsService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new ProductStats();
    }

    @Override
    public String getId() {
        return "PSMODULES/ibiz/PSDATAENTITIES/ProductStats.json";
    }

    @Override
    public String getName() {
        return "IBZ_PRODUCTSTATS";
    }

    @Override
    public Object createEntity() {
        return new ProductStats();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    //@Around("execution(* cn.ibizlab.pms.core.ibiz.service.impl.ProductStatsServiceImpl.*(..))")
    public Object aroundMethod(ProceedingJoinPoint point) throws Throwable {
        String action = point.getSignature().getName();
        if (action.equals("create")) {
            return aroundAction("Create", point);
        }
        if (action.equals("update")) {
            return aroundAction("Update", point);
        }
        if (action.equals("remove")) {
            return aroundAction("Remove", point);
        }
        if (action.equals("get")) {
            return aroundAction("Get", point);
        }
        if (action.equals("getDraft")) {
            return aroundAction("GetDraft", point);
        }
        if (action.equals("checkKey")) {
            return aroundAction("CheckKey", point);
        }
        if (action.equals("getTestStats")) {
            return aroundAction("GetTestStats", point);
        }
        if (action.equals("save")) {
            return aroundAction("Save", point);
        }

        if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        if (action.equals("searchNoOpenProduct")) {
            return aroundDataSet("NoOpenProduct", point);
        }
        if (action.equals("searchProdctQuantiGird")) {
            return aroundDataSet("ProdctQuantiGird", point);
        }
        if (action.equals("searchProductInputTable")) {
            return aroundDataSet("ProductInputTable", point);
        }
        if (action.equals("searchProductcompletionstatistics")) {
            return aroundDataSet("Productcompletionstatistics", point);
        }
        return point.proceed();
    }

}
