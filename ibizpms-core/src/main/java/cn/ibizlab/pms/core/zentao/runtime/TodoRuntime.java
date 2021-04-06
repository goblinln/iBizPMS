package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Todo;
import cn.ibizlab.pms.core.zentao.service.ITodoService;
import cn.ibizlab.pms.core.zentao.filter.TodoSearchContext;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import cn.ibizlab.pms.core.runtime.DataEntityRuntime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("TodoRuntime")
public class TodoRuntime extends DataEntityRuntime {

    @Autowired
    ITodoService todoService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new Todo();
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/Todo.json";
    }

    @Override
    public String getName() {
        return "ZT_TODO";
    }

    @Override
    public Object getFieldValue(Object o, IPSDEField ipsdeField) {
        Todo domain = (Todo)o;
        try {
            return domain.get(ipsdeField.getCodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public void setFieldValue(Object o, IPSDEField ipsdeField, Object o1) {
        Todo domain = (Todo)o;
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
        return new Todo();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.TodoServiceImpl.*(..))")
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
        if (action.equals("activate")) {
            return aroundAction("Activate", point);
        }
        if (action.equals("assignTo")) {
            return aroundAction("AssignTo", point);
        }
        if (action.equals("checkKey")) {
            return aroundAction("CheckKey", point);
        }
        if (action.equals("close")) {
            return aroundAction("Close", point);
        }
        if (action.equals("createCycle")) {
            return aroundAction("createCycle", point);
        }
        if (action.equals("finish")) {
            return aroundAction("Finish", point);
        }
        if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        if (action.equals("sendMessage")) {
            return aroundAction("sendMessage", point);
        }
        if (action.equals("sendMsgPreProcess")) {
            return aroundAction("sendMsgPreProcess", point);
        }

        if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        if (action.equals("searchMyTodo")) {
            return aroundDataSet("MyTodo", point);
        }
        if (action.equals("searchMyTodoPc")) {
            return aroundDataSet("MyTodoPc", point);
        }
        if (action.equals("searchMyUpcoming")) {
            return aroundDataSet("MyUpcoming", point);
        }
        return point.proceed();
    }

}
