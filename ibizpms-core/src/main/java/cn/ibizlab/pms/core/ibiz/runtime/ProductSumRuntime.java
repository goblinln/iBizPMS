package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.ibiz.domain.ProductSum;
import cn.ibizlab.pms.core.ibiz.service.IProductSumService;
import cn.ibizlab.pms.core.ibiz.filter.ProductSumSearchContext;
import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("ProductSumRuntime")
public class ProductSumRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IProductSumService productsumService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new ProductSum();
    }

    @Override
    public String getId() {
        return "PSMODULES/ibiz/PSDATAENTITIES/ProductSum.json";
    }

    @Override
    public String getName() {
        return "IBZ_PRODUCTSUM";
    }

    @Override
    public Object createEntity() {
        return new ProductSum();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    //@Around("execution(* cn.ibizlab.pms.core.ibiz.service.impl.ProductSumServiceImpl.*(..))")
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
        if (action.equals("save")) {
            return aroundAction("Save", point);
        }

        if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        if (action.equals("searchProductBugcnt_QA")) {
            return aroundDataSet("ProductBugcnt_QA", point);
        }
        if (action.equals("searchProductCreateStory")) {
            return aroundDataSet("ProductCreateStory", point);
        }
        if (action.equals("searchProductStoryHoursSum")) {
            return aroundDataSet("ProductStoryHoursSum", point);
        }
        if (action.equals("searchProductStorySum")) {
            return aroundDataSet("ProductStorySum", point);
        }
        if (action.equals("searchProductStorycntAndPlancnt")) {
            return aroundDataSet("ProductStorycntAndPlancnt", point);
        }
        if (action.equals("searchProductSumBugType")) {
            return aroundDataSet("ProductSumBugType", point);
        }
        return point.proceed();
    }

}
