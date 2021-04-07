package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.TaskEstimate;
import cn.ibizlab.pms.core.zentao.service.ITaskEstimateService;
import cn.ibizlab.pms.core.zentao.filter.TaskEstimateSearchContext;
import cn.ibizlab.pms.core.runtime.DataEntityRuntime;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("TaskEstimateRuntime")
public class TaskEstimateRuntime extends DataEntityRuntime {

    @Autowired
    ITaskEstimateService taskestimateService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new TaskEstimate();
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/TaskEstimate.json";
    }

    @Override
    public String getName() {
        return "ZT_TASKESTIMATE";
    }

    @Override
    public Object getFieldValue(Object o, IPSDEField ipsdeField) {
        TaskEstimate domain = (TaskEstimate)o;
        try {
            return domain.get(ipsdeField.getCodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public void setFieldValue(Object o, IPSDEField ipsdeField, Object o1) {
        TaskEstimate domain = (TaskEstimate)o;
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
        return new TaskEstimate();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.TaskEstimateServiceImpl.*(..))")
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
        if (action.equals("pMEvaluation")) {
            return aroundAction("PMEvaluation", point);
        }
        if (action.equals("save")) {
            return aroundAction("Save", point);
        }

        if (action.equals("searchActionMonth")) {
            return aroundDataSet("ActionMonth", point);
        }
        if (action.equals("searchActionYear")) {
            return aroundDataSet("ActionYear", point);
        }
        if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        if (action.equals("searchDefaults")) {
            return aroundDataSet("DEFAULT1", point);
        }
        if (action.equals("searchProjectActionMonth")) {
            return aroundDataSet("ProjectActionMonth", point);
        }
        if (action.equals("searchProjectActionYear")) {
            return aroundDataSet("ProjectActionYear", point);
        }
        if (action.equals("searchProjectTaskEstimate")) {
            return aroundDataSet("ProjectTaskEstimate", point);
        }
        return point.proceed();
    }

}
