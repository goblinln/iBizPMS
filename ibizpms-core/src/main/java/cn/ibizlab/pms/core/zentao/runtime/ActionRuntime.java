package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Action;
import cn.ibizlab.pms.core.zentao.service.IActionService;
import cn.ibizlab.pms.core.zentao.filter.ActionSearchContext;
import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("ActionRuntime")
public class ActionRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IActionService actionService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new Action();
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/Action.json";
    }

    @Override
    public String getName() {
        return "ZT_ACTION";
    }

    @Override
    public Object createEntity() {
        return new Action();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    //@Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.ActionServiceImpl.*(..))")
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
        if (action.equals("editComment")) {
            return aroundAction("editComment", point);
        }
        if (action.equals("managePmsEe")) {
            return aroundAction("ManagePmsEe", point);
        }
        if (action.equals("save")) {
            return aroundAction("Save", point);
        }

        if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        if (action.equals("searchMobType")) {
            return aroundDataSet("MobType", point);
        }
        if (action.equals("searchMyTrends")) {
            return aroundDataSet("MyTrends", point);
        }
        if (action.equals("searchProductTrends")) {
            return aroundDataSet("ProductTrends", point);
        }
        if (action.equals("searchProjectTrends")) {
            return aroundDataSet("ProjectTrends", point);
        }
        if (action.equals("searchQueryUserYEAR")) {
            return aroundDataSet("QueryUserYEAR", point);
        }
        if (action.equals("searchType")) {
            return aroundDataSet("Type", point);
        }
        return point.proceed();
    }

}
