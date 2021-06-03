package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import cn.ibizlab.pms.core.sample.runtime.ITaskEstimateRuntime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Aspect
@org.springframework.core.annotation.Order(100)
@Component("TaskEstimateRuntime")
@Slf4j
public class TaskEstimateAspect extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

     @Autowired
    ITaskEstimateRuntime taskEstimateRuntime;

    private SystemDataEntityRuntime getDataEntityRuntime() {
        return (SystemDataEntityRuntime) taskEstimateRuntime;
    }

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.TaskEstimateServiceImpl.*(..))")
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
        else if (action.equals("pMEvaluation")) {
            return getDataEntityRuntime().aroundAction("PMEvaluation", point);
        }
        else if (action.equals("save")) {
            return getDataEntityRuntime().aroundAction("Save", point);
        }
        else if (action.equals("searchActionMonth")) {
            return getDataEntityRuntime().aroundDataSet("ActionMonth", point);
        }
        else if (action.equals("searchActionYear")) {
            return getDataEntityRuntime().aroundDataSet("ActionYear", point);
        }
        else if (action.equals("searchDefault")) {
            return getDataEntityRuntime().aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchDefaults")) {
            return getDataEntityRuntime().aroundDataSet("DEFAULT1", point);
        }
        else if (action.equals("searchProjectActionMonth")) {
            return getDataEntityRuntime().aroundDataSet("ProjectActionMonth", point);
        }
        else if (action.equals("searchProjectActionYear")) {
            return getDataEntityRuntime().aroundDataSet("ProjectActionYear", point);
        }
        else if (action.equals("searchProjectTaskEstimate")) {
            return getDataEntityRuntime().aroundDataSet("ProjectTaskEstimate", point);
        }
        return point.proceed();
    }
}
