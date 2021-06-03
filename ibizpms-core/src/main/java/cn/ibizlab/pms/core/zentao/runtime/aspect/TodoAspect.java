package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import cn.ibizlab.pms.core.zentao.runtime.ITodoRuntime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Aspect
@org.springframework.core.annotation.Order(100)
@Slf4j
public class TodoAspect {

     @Autowired
    ITodoRuntime todoRuntime;

    private SystemDataEntityRuntime getDataEntityRuntime() {
        return (SystemDataEntityRuntime) todoRuntime;
    }

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.TodoServiceImpl.*(..))")
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
        else if (action.equals("activate")) {
            return getDataEntityRuntime().aroundAction("Activate", point);
        }
        else if (action.equals("assignTo")) {
            return getDataEntityRuntime().aroundAction("AssignTo", point);
        }
        else if (action.equals("checkKey")) {
            return getDataEntityRuntime().aroundAction("CheckKey", point);
        }
        else if (action.equals("close")) {
            return getDataEntityRuntime().aroundAction("Close", point);
        }
        else if (action.equals("createCycle")) {
            return getDataEntityRuntime().aroundAction("createCycle", point);
        }
        else if (action.equals("finish")) {
            return getDataEntityRuntime().aroundAction("Finish", point);
        }
        else if (action.equals("save")) {
            return getDataEntityRuntime().aroundAction("Save", point);
        }
        else if (action.equals("sendMessage")) {
            return getDataEntityRuntime().aroundAction("sendMessage", point);
        }
        else if (action.equals("sendMsgPreProcess")) {
            return getDataEntityRuntime().aroundAction("sendMsgPreProcess", point);
        }
        else if (action.equals("start")) {
            return getDataEntityRuntime().aroundAction("Start", point);
        }
        else if (action.equals("searchAccount")) {
            return getDataEntityRuntime().aroundDataSet("Account", point);
        }
        else if (action.equals("searchDefault")) {
            return getDataEntityRuntime().aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchMy")) {
            return getDataEntityRuntime().aroundDataSet("My", point);
        }
        else if (action.equals("searchMyCreateTodo")) {
            return getDataEntityRuntime().aroundDataSet("MyCreateTodo", point);
        }
        else if (action.equals("searchMyTodo")) {
            return getDataEntityRuntime().aroundDataSet("MyTodo", point);
        }
        else if (action.equals("searchMyTodoPc")) {
            return getDataEntityRuntime().aroundDataSet("MyTodoPc", point);
        }
        else if (action.equals("searchMyUpcoming")) {
            return getDataEntityRuntime().aroundDataSet("MyUpcoming", point);
        }
        return point.proceed();
    }
}
