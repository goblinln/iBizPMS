package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Product;
import cn.ibizlab.pms.core.zentao.service.IProductService;
import cn.ibizlab.pms.core.zentao.filter.ProductSearchContext;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import cn.ibizlab.pms.core.runtime.DataEntityRuntime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("ProductRuntime")
public class ProductRuntime extends DataEntityRuntime {

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
        return ZT_PRODUCT;
    }

    @Override
    public Object getFieldValue(Object o, IPSDEField ipsdeField) {
        Product domain = (Product)o;
        try {
            return domain.get(ipsdeField.getCodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public void setFieldValue(Object o, IPSDEField ipsdeField, Object o1) {
        Product domain = (Product)o;
        try {
            domain.set(ipsdeField.getCodeName(),o1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean containsFieldValue(Object o, IPSDEField ipsdeField) {
        return false;
    }

    @Override
    public Object createEntity() {
        return new Product();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.ProductServiceImpl.*(..))")
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

    //
        if (action.equals("searchAllList")) {
            return aroundAction("AllList", point);
        }
        if (action.equals("searchAllProduct")) {
            return aroundAction("AllProduct", point);
        }
        if (action.equals("searchCheckNameOrCode")) {
            return aroundAction("CheckNameOrCode", point);
        }
        if (action.equals("searchCurProject")) {
            return aroundAction("CURPROJECT", point);
        }
        if (action.equals("searchCurUer")) {
            return aroundAction("CurUer", point);
        }
        if (action.equals("searchDefault")) {
            return aroundAction("DEFAULT", point);
        }
        if (action.equals("searchESBulk")) {
            return aroundAction("ESBulk", point);
        }
        if (action.equals("searchProductPM")) {
            return aroundAction("ProductPM", point);
        }
        if (action.equals("searchProductTeam")) {
            return aroundAction("ProductTeam", point);
        }
        if (action.equals("searchStoryCurProject")) {
            return aroundAction("StoryCURPROJECT", point);
        }
        return point.proceed();
    }

}
