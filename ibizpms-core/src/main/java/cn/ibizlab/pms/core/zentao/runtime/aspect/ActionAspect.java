package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import cn.ibizlab.pms.core.sample.runtime.IActionRuntime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Aspect
@org.springframework.core.annotation.Order(100)
@Component("ActionRuntime")
@Slf4j
public class ActionAspect extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

     @Autowired
    IActionRuntime actionRuntime;

    private SystemDataEntityRuntime getDataEntityRuntime() {
        return (SystemDataEntityRuntime) actionRuntime;
    }

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.ActionServiceImpl.*(..))")
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
        else if (action.equals("comment")) {
            return getDataEntityRuntime().aroundAction("Comment", point);
        }
        else if (action.equals("createHis")) {
            return getDataEntityRuntime().aroundAction("CreateHis", point);
        }
        else if (action.equals("editComment")) {
            return getDataEntityRuntime().aroundAction("editComment", point);
        }
        else if (action.equals("managePmsEe")) {
            return getDataEntityRuntime().aroundAction("ManagePmsEe", point);
        }
        else if (action.equals("save")) {
            return getDataEntityRuntime().aroundAction("Save", point);
        }
        else if (action.equals("sendMarkDone")) {
            return getDataEntityRuntime().aroundAction("sendMarkDone", point);
        }
        else if (action.equals("sendTodo")) {
            return getDataEntityRuntime().aroundAction("sendTodo", point);
        }
        else if (action.equals("sendToread")) {
            return getDataEntityRuntime().aroundAction("sendToread", point);
        }
        else if (action.equals("searchAccount")) {
            return getDataEntityRuntime().aroundDataSet("Account", point);
        }
        else if (action.equals("searchDefault")) {
            return getDataEntityRuntime().aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchMobType")) {
            return getDataEntityRuntime().aroundDataSet("MobType", point);
        }
        else if (action.equals("searchMy")) {
            return getDataEntityRuntime().aroundDataSet("My", point);
        }
        else if (action.equals("searchMyAction")) {
            return getDataEntityRuntime().aroundDataSet("MyAction", point);
        }
        else if (action.equals("searchMyTrends")) {
            return getDataEntityRuntime().aroundDataSet("MyTrends", point);
        }
        else if (action.equals("searchProductTrends")) {
            return getDataEntityRuntime().aroundDataSet("ProductTrends", point);
        }
        else if (action.equals("searchProjectTrends")) {
            return getDataEntityRuntime().aroundDataSet("ProjectTrends", point);
        }
        else if (action.equals("searchQueryUserYEAR")) {
            return getDataEntityRuntime().aroundDataSet("QueryUserYEAR", point);
        }
        else if (action.equals("searchType")) {
            return getDataEntityRuntime().aroundDataSet("Type", point);
        }
        return point.proceed();
    }
}
