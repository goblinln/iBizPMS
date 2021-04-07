package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.ProductPlan;
import cn.ibizlab.pms.core.zentao.service.IProductPlanService;
import cn.ibizlab.pms.core.zentao.filter.ProductPlanSearchContext;
import cn.ibizlab.pms.core.runtime.DataEntityRuntime;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("ProductPlanRuntime")
public class ProductPlanRuntime extends DataEntityRuntime {

    @Autowired
    IProductPlanService productplanService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new ProductPlan();
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/ProductPlan.json";
    }

    @Override
    public String getName() {
        return "ZT_PRODUCTPLAN";
    }

    @Override
    public Object getFieldValue(Object o, IPSDEField ipsdeField) {
        ProductPlan domain = (ProductPlan)o;
        try {
            return domain.get(ipsdeField.getCodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public void setFieldValue(Object o, IPSDEField ipsdeField, Object o1) {
        ProductPlan domain = (ProductPlan)o;
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
        return new ProductPlan();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.ProductPlanServiceImpl.*(..))")
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
        if (action.equals("batchUnlinkBug")) {
            return aroundAction("BatchUnlinkBug", point);
        }
        if (action.equals("batchUnlinkStory")) {
            return aroundAction("BatchUnlinkStory", point);
        }
        if (action.equals("checkKey")) {
            return aroundAction("CheckKey", point);
        }
        if (action.equals("eeActivePlan")) {
            return aroundAction("EeActivePlan", point);
        }
        if (action.equals("eeCancelPlan")) {
            return aroundAction("EeCancelPlan", point);
        }
        if (action.equals("eeClosePlan")) {
            return aroundAction("EeClosePlan", point);
        }
        if (action.equals("eeFinishPlan")) {
            return aroundAction("EeFinishPlan", point);
        }
        if (action.equals("eePausePlan")) {
            return aroundAction("EePausePlan", point);
        }
        if (action.equals("eeRestartPlan")) {
            return aroundAction("EeRestartPlan", point);
        }
        if (action.equals("eeStartPlan")) {
            return aroundAction("EeStartPlan", point);
        }
        if (action.equals("importPlanTemplet")) {
            return aroundAction("ImportPlanTemplet", point);
        }
        if (action.equals("linkBug")) {
            return aroundAction("LinkBug", point);
        }
        if (action.equals("linkStory")) {
            return aroundAction("LinkStory", point);
        }
        if (action.equals("linkTask")) {
            return aroundAction("LinkTask", point);
        }
        if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        if (action.equals("unlinkBug")) {
            return aroundAction("UnlinkBug", point);
        }
        if (action.equals("unlinkStory")) {
            return aroundAction("UnlinkStory", point);
        }

        if (action.equals("searchChildPlan")) {
            return aroundDataSet("ChildPlan", point);
        }
        if (action.equals("searchCurProductPlan")) {
            return aroundDataSet("CurProductPlan", point);
        }
        if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        if (action.equals("searchDefaultParent")) {
            return aroundDataSet("DefaultParent", point);
        }
        if (action.equals("searchPlanCodeList")) {
            return aroundDataSet("PlanCodeList", point);
        }
        if (action.equals("searchPlanTasks")) {
            return aroundDataSet("PlanTasks", point);
        }
        if (action.equals("searchProjectApp")) {
            return aroundDataSet("ProjectApp", point);
        }
        if (action.equals("searchProjectPlan")) {
            return aroundDataSet("ProjectPlan", point);
        }
        if (action.equals("searchRootPlan")) {
            return aroundDataSet("RootPlan", point);
        }
        if (action.equals("searchTaskPlan")) {
            return aroundDataSet("TaskPlan", point);
        }
        return point.proceed();
    }

}
