package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.ibiz.domain.IbiLogin;
import cn.ibizlab.pms.core.ibiz.service.IIbiLoginService;
import cn.ibizlab.pms.core.ibiz.filter.IbiLoginSearchContext;
import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("IbiLoginRuntime")
public class IbiLoginRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IIbiLoginService ibiloginService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new IbiLogin();
    }

    @Override
    public String getId() {
        return "PSMODULES/ibiz/PSDATAENTITIES/IbiLogin.json";
    }

    @Override
    public String getName() {
        return "IBZ_LOGIN";
    }

    @Override
    public Object createEntity() {
        return new IbiLogin();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    //@Around("execution(* cn.ibizlab.pms.core.ibiz.service.impl.IbiLoginServiceImpl.*(..))")
    public Object aroundMethod(ProceedingJoinPoint point) throws Throwable {
        String action = point.getSignature().getName();
        if (action.equals("getUser")) {
            return aroundAction("getUser", point);
        }
        if (action.equals("ztlogin")) {
            return aroundAction("ztlogin", point);
        }

        if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        return point.proceed();
    }

}
