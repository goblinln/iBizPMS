package cn.ibizlab.pms.core.ibizplugin.runtime.aspect;

import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import cn.ibizlab.pms.core.ibizplugin.runtime.IIBIZProMessageRuntime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Aspect
@org.springframework.core.annotation.Order(100)
@Slf4j
@Component
public class IBIZProMessageAspect {

     @Autowired
    IIBIZProMessageRuntime iBIZProMessageRuntime;

    private SystemDataEntityRuntime getDataEntityRuntime() {
        return (SystemDataEntityRuntime) iBIZProMessageRuntime;
    }

    @Around("execution(* cn.ibizlab.pms.core.ibizplugin.service.impl.IBIZProMessageServiceImpl.*(..))")
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
        else if (action.equals("checkKey")) {
            return getDataEntityRuntime().aroundAction("CheckKey", point);
        }
        else if (action.equals("markDone")) {
            return getDataEntityRuntime().aroundAction("MarkDone", point);
        }
        else if (action.equals("markRead")) {
            return getDataEntityRuntime().aroundAction("MarkRead", point);
        }
        else if (action.equals("save")) {
            return getDataEntityRuntime().aroundAction("Save", point);
        }
        else if (action.equals("send")) {
            return getDataEntityRuntime().aroundAction("Send", point);
        }
        else if (action.equals("searchDefault")) {
            return getDataEntityRuntime().aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchUserAllMessages")) {
            return getDataEntityRuntime().aroundDataSet("UserAllMessages", point);
        }
        else if (action.equals("searchUserUnreadMessages")) {
            return getDataEntityRuntime().aroundDataSet("UserUnreadMessages", point);
        }
        return point.proceed();
    }
}
