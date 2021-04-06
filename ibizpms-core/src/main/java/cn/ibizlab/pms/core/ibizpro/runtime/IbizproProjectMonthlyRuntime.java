package cn.ibizlab.pms.core.ibizpro.runtime;

import cn.ibizlab.pms.core.ibizpro.domain.IbizproProjectMonthly;
import cn.ibizlab.pms.core.ibizpro.service.IIbizproProjectMonthlyService;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproProjectMonthlySearchContext;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import cn.ibizlab.pms.core.runtime.DataEntityRuntime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("IbizproProjectMonthlyRuntime")
public class IbizproProjectMonthlyRuntime extends DataEntityRuntime {

    @Autowired
    IIbizproProjectMonthlyService ibizproprojectmonthlyService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new IbizproProjectMonthly();
    }

    @Override
    public String getId() {
        return "PSMODULES/ibizpro/PSDATAENTITIES/IbizproProjectMonthly.json";
    }

    @Override
    public String getName() {
        return "IBIZPRO_PROJECTMONTHLY";
    }

    @Override
    public Object getFieldValue(Object o, IPSDEField ipsdeField) {
        IbizproProjectMonthly domain = (IbizproProjectMonthly)o;
        try {
            return domain.get(ipsdeField.getCodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public void setFieldValue(Object o, IPSDEField ipsdeField, Object o1) {
        IbizproProjectMonthly domain = (IbizproProjectMonthly)o;
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
        return new IbizproProjectMonthly();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    @Around("execution(* cn.ibizlab.pms.core.ibizpro.service.impl.IbizproProjectMonthlyServiceImpl.*(..))")
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
        if (action.equals("manualCreateMonthly")) {
            return aroundAction("ManualCreateMonthly", point);
        }
        if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        if (action.equals("sumProjectMonthly")) {
            return aroundAction("SumProjectMonthly", point);
        }

    //
        if (action.equals("searchDefault")) {
            return aroundAction("DEFAULT", point);
        }
        return point.proceed();
    }

}
