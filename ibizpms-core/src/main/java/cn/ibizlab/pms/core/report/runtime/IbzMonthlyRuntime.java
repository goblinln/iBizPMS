package cn.ibizlab.pms.core.report.runtime;

import cn.ibizlab.pms.core.report.domain.IbzMonthly;
import cn.ibizlab.pms.core.report.service.IIbzMonthlyService;
import cn.ibizlab.pms.core.report.filter.IbzMonthlySearchContext;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import cn.ibizlab.pms.core.runtime.DataEntityRuntime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("IbzMonthlyRuntime")
public class IbzMonthlyRuntime extends DataEntityRuntime {

    @Autowired
    IIbzMonthlyService ibzmonthlyService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new IbzMonthly();
    }

    @Override
    public String getId() {
        return "PSMODULES/report/PSDATAENTITIES/IbzMonthly.json";
    }

    @Override
    public String getName() {
        return IBZ_MONTHLY;
    }

    @Override
    public Object getFieldValue(Object o, IPSDEField ipsdeField) {
        IbzMonthly domain = (IbzMonthly)o;
        try {
            return domain.get(ipsdeField.getCodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public void setFieldValue(Object o, IPSDEField ipsdeField, Object o1) {
        IbzMonthly domain = (IbzMonthly)o;
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
        return new IbzMonthly();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    @Around("execution(* cn.ibizlab.pms.core.report.service.IbzMonthlyServiceImpl.*(..))")
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
        if (action.equals("createGetInfo")) {
            return aroundAction("CreateGetInfo", point);
        }
        if (action.equals("createUserMonthly")) {
            return aroundAction("CreateUserMonthly", point);
        }
        if (action.equals("editGetCompleteTask")) {
            return aroundAction("EditGetCompleteTask", point);
        }
        if (action.equals("haveRead")) {
            return aroundAction("HaveRead", point);
        }
        if (action.equals("pushUserMonthly")) {
            return aroundAction("PushUserMonthly", point);
        }
        if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        if (action.equals("submit")) {
            return aroundAction("Submit", point);
        }

    //
        if (action.equals("searchDefault")) {
            return aroundAction("DEFAULT", point);
        }
        if (action.equals("searchMyMonthly")) {
            return aroundAction("MyMonthly", point);
        }
        if (action.equals("searchMyMonthlyMob")) {
            return aroundAction("MyMonthlyMob", point);
        }
        if (action.equals("searchMyReceivedMonthly")) {
            return aroundAction("MyReceivedMonthly", point);
        }
        if (action.equals("searchMySubmitMonthly")) {
            return aroundAction("MySubmitMonthly", point);
        }
        if (action.equals("searchProductMonthly")) {
            return aroundAction("ProductMonthly", point);
        }
        if (action.equals("searchProjectMonthly")) {
            return aroundAction("ProjectMonthly", point);
        }
        return point.proceed();
    }

}
