package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.ibiz.domain.ProductLife;
import cn.ibizlab.pms.core.ibiz.service.IProductLifeService;
import cn.ibizlab.pms.core.ibiz.filter.ProductLifeSearchContext;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import cn.ibizlab.pms.core.runtime.DataEntityRuntime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("ProductLifeRuntime")
public class ProductLifeRuntime extends DataEntityRuntime {

    @Autowired
    IProductLifeService productlifeService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new ProductLife();
    }

    @Override
    public String getId() {
        return "PSMODULES/ibiz/PSDATAENTITIES/ProductLife.json";
    }

    @Override
    public String getName() {
        return "IBZ_PRODUCTLIFE";
    }

    @Override
    public Object getFieldValue(Object o, IPSDEField ipsdeField) {
        ProductLife domain = (ProductLife)o;
        try {
            return domain.get(ipsdeField.getCodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public void setFieldValue(Object o, IPSDEField ipsdeField, Object o1) {
        ProductLife domain = (ProductLife)o;
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
        return new ProductLife();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    @Around("execution(* cn.ibizlab.pms.core.ibiz.service.impl.ProductLifeServiceImpl.*(..))")
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
        if (action.equals("searchGetRoadmap")) {
            return aroundDataSet("GetRoadmap", point);
        }
        if (action.equals("searchGetRoadmapS")) {
            return aroundDataSet("GetRoadmapS", point);
        }
        if (action.equals("searchRoadMapYear")) {
            return aroundDataSet("RoadMapYear", point);
        }
        return point.proceed();
    }

}
