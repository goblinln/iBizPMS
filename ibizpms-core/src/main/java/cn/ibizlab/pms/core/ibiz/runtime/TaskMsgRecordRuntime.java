package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.ibiz.domain.TaskMsgRecord;
import cn.ibizlab.pms.core.ibiz.service.ITaskMsgRecordService;
import cn.ibizlab.pms.core.ibiz.filter.TaskMsgRecordSearchContext;
import cn.ibizlab.pms.core.runtime.DataEntityRuntime;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("TaskMsgRecordRuntime")
public class TaskMsgRecordRuntime extends DataEntityRuntime {

    @Autowired
    ITaskMsgRecordService taskmsgrecordService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new TaskMsgRecord();
    }

    @Override
    public String getId() {
        return "PSMODULES/ibiz/PSDATAENTITIES/TaskMsgRecord.json";
    }

    @Override
    public String getName() {
        return "TASKMSGRECORD";
    }

    @Override
    public Object getFieldValue(Object o, IPSDEField ipsdeField) {
        TaskMsgRecord domain = (TaskMsgRecord)o;
        try {
            return domain.get(ipsdeField.getCodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public void setFieldValue(Object o, IPSDEField ipsdeField, Object o1) {
        TaskMsgRecord domain = (TaskMsgRecord)o;
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
        return new TaskMsgRecord();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    @Around("execution(* cn.ibizlab.pms.core.ibiz.service.impl.TaskMsgRecordServiceImpl.*(..))")
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
        return point.proceed();
    }

}
