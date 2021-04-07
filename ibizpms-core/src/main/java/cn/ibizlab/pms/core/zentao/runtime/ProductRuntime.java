package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Product;
import cn.ibizlab.pms.core.zentao.service.IProductService;
import cn.ibizlab.pms.core.zentao.filter.ProductSearchContext;
import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("ProductRuntime")
public class ProductRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IProductService productService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new Product();
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/Product.json";
    }

    @Override
    public String getName() {
        return "ZT_PRODUCT";
    }

    @Override
    public Object createEntity() {
        return new Product();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    //@Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.ProductServiceImpl.*(..))")
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
        if (action.equals("close")) {
            return aroundAction("Close", point);
        }
        if (action.equals("save")) {
            return aroundAction("Save", point);
        }

        if (action.equals("searchAllList")) {
            return aroundDataSet("AllList", point);
        }
        if (action.equals("searchAllProduct")) {
            return aroundDataSet("AllProduct", point);
        }
        if (action.equals("searchCheckNameOrCode")) {
            return aroundDataSet("CheckNameOrCode", point);
        }
        if (action.equals("searchCurProject")) {
            return aroundDataSet("CURPROJECT", point);
        }
        if (action.equals("searchCurUer")) {
            return aroundDataSet("CurUer", point);
        }
        if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        if (action.equals("searchESBulk")) {
            return aroundDataSet("ESBulk", point);
        }
        if (action.equals("searchProductPM")) {
            return aroundDataSet("ProductPM", point);
        }
        if (action.equals("searchProductTeam")) {
            return aroundDataSet("ProductTeam", point);
        }
        if (action.equals("searchStoryCurProject")) {
            return aroundDataSet("StoryCURPROJECT", point);
        }
        return point.proceed();
    }

}
