package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import cn.ibizlab.pms.core.zentao.runtime.IProductPlanRuntime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Aspect
@org.springframework.core.annotation.Order(100)
@Slf4j
public class ProductPlanAspect {

     @Autowired
    IProductPlanRuntime productPlanRuntime;

    private SystemDataEntityRuntime getDataEntityRuntime() {
        return (SystemDataEntityRuntime) productPlanRuntime;
    }

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.ProductPlanServiceImpl.*(..))")
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
        else if (action.equals("batchLinkBug")) {
            return getDataEntityRuntime().aroundAction("BatchLinkBug", point);
        }
        else if (action.equals("batchLinkStory")) {
            return getDataEntityRuntime().aroundAction("BatchLinkStory", point);
        }
        else if (action.equals("batchUnlinkBug")) {
            return getDataEntityRuntime().aroundAction("BatchUnlinkBug", point);
        }
        else if (action.equals("batchUnlinkStory")) {
            return getDataEntityRuntime().aroundAction("BatchUnlinkStory", point);
        }
        else if (action.equals("checkKey")) {
            return getDataEntityRuntime().aroundAction("CheckKey", point);
        }
        else if (action.equals("eeActivePlan")) {
            return getDataEntityRuntime().aroundAction("EeActivePlan", point);
        }
        else if (action.equals("eeCancelPlan")) {
            return getDataEntityRuntime().aroundAction("EeCancelPlan", point);
        }
        else if (action.equals("eeClosePlan")) {
            return getDataEntityRuntime().aroundAction("EeClosePlan", point);
        }
        else if (action.equals("eeFinishPlan")) {
            return getDataEntityRuntime().aroundAction("EeFinishPlan", point);
        }
        else if (action.equals("eePausePlan")) {
            return getDataEntityRuntime().aroundAction("EePausePlan", point);
        }
        else if (action.equals("eeRestartPlan")) {
            return getDataEntityRuntime().aroundAction("EeRestartPlan", point);
        }
        else if (action.equals("eeStartPlan")) {
            return getDataEntityRuntime().aroundAction("EeStartPlan", point);
        }
        else if (action.equals("getOldPlanName")) {
            return getDataEntityRuntime().aroundAction("GetOldPlanName", point);
        }
        else if (action.equals("importPlanTemplet")) {
            return getDataEntityRuntime().aroundAction("ImportPlanTemplet", point);
        }
        else if (action.equals("linkBug")) {
            return getDataEntityRuntime().aroundAction("LinkBug", point);
        }
        else if (action.equals("linkStory")) {
            return getDataEntityRuntime().aroundAction("LinkStory", point);
        }
        else if (action.equals("linkTask")) {
            return getDataEntityRuntime().aroundAction("LinkTask", point);
        }
        else if (action.equals("mobProductPlanCounter")) {
            return getDataEntityRuntime().aroundAction("MobProductPlanCounter", point);
        }
        else if (action.equals("save")) {
            return getDataEntityRuntime().aroundAction("Save", point);
        }
        else if (action.equals("unlinkBug")) {
            return getDataEntityRuntime().aroundAction("UnlinkBug", point);
        }
        else if (action.equals("unlinkStory")) {
            return getDataEntityRuntime().aroundAction("UnlinkStory", point);
        }
        else if (action.equals("searchChildPlan")) {
            return getDataEntityRuntime().aroundDataSet("ChildPlan", point);
        }
        else if (action.equals("searchCurProductPlan")) {
            return getDataEntityRuntime().aroundDataSet("CurProductPlan", point);
        }
        else if (action.equals("searchCurProductPlanStory")) {
            return getDataEntityRuntime().aroundDataSet("CurProductPlanStory", point);
        }
        else if (action.equals("searchDefault")) {
            return getDataEntityRuntime().aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchDefaultParent")) {
            return getDataEntityRuntime().aroundDataSet("DefaultParent", point);
        }
        else if (action.equals("searchPlanCodeList")) {
            return getDataEntityRuntime().aroundDataSet("PlanCodeList", point);
        }
        else if (action.equals("searchPlanTasks")) {
            return getDataEntityRuntime().aroundDataSet("PlanTasks", point);
        }
        else if (action.equals("searchProductQuery")) {
            return getDataEntityRuntime().aroundDataSet("ProductQuery", point);
        }
        else if (action.equals("searchProjectApp")) {
            return getDataEntityRuntime().aroundDataSet("ProjectApp", point);
        }
        else if (action.equals("searchProjectPlan")) {
            return getDataEntityRuntime().aroundDataSet("ProjectPlan", point);
        }
        else if (action.equals("searchRootPlan")) {
            return getDataEntityRuntime().aroundDataSet("RootPlan", point);
        }
        else if (action.equals("searchTaskPlan")) {
            return getDataEntityRuntime().aroundDataSet("TaskPlan", point);
        }
        return point.proceed();
    }
}
