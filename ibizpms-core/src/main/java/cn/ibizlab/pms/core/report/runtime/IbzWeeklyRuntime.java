package cn.ibizlab.pms.core.report.runtime;

import cn.ibizlab.pms.core.report.domain.IbzWeekly;
import cn.ibizlab.pms.core.report.service.IIbzWeeklyService;
import cn.ibizlab.pms.core.report.filter.IbzWeeklySearchContext;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import cn.ibizlab.pms.core.runtime.DataEntityRuntime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("IbzWeeklyRuntime")
public class IbzWeeklyRuntime extends DataEntityRuntime {

    @Autowired
    IIbzWeeklyService ibzweeklyService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new IbzWeekly();
    }

    @Override
    public String getId() {
        return "PSMODULES/report/PSDATAENTITIES/IbzWeekly.json";
    }

    @Override
    public String getName() {
        return IBZ_WEEKLY;
    }

    @Override
    public Object getFieldValue(Object o, IPSDEField ipsdeField) {
        IbzWeekly domain = (IbzWeekly)o;
        try {
            return domain.get(ipsdeField.getCodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public void setFieldValue(Object o, IPSDEField ipsdeField, Object o1) {
        IbzWeekly domain = (IbzWeekly)o;
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
        return new IbzWeekly();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    @Around("execution(* cn.ibizlab.pms.core.report.service.IbzWeeklyServiceImpl.*(..))")
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
        if (action.equals("createEveryWeekReport")) {
            return aroundAction("createEveryWeekReport", point);
        }
        if (action.equals("createGetLastWeekPlanAndWork")) {
            return aroundAction("CreateGetLastWeekPlanAndWork", point);
        }
        if (action.equals("editGetLastWeekTaskAndComTask")) {
            return aroundAction("EditGetLastWeekTaskAndComTask", point);
        }
        if (action.equals("haveRead")) {
            return aroundAction("haveRead", point);
        }
        if (action.equals("jugThisWeekCreateWeekly")) {
            return aroundAction("JugThisWeekCreateWeekly", point);
        }
        if (action.equals("pushUserWeekly")) {
            return aroundAction("pushUserWeekly", point);
        }
        if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        if (action.equals("submit")) {
            return aroundAction("submit", point);
        }

    //
        if (action.equals("searchDefault")) {
            return aroundAction("DEFAULT", point);
        }
        if (action.equals("searchMyNotSubmit")) {
            return aroundAction("MyNotSubmit", point);
        }
        if (action.equals("searchMyWeekly")) {
            return aroundAction("MyWeekly", point);
        }
        if (action.equals("searchProductTeamMemberWeekly")) {
            return aroundAction("ProductTeamMemberWeekly", point);
        }
        if (action.equals("searchProjectWeekly")) {
            return aroundAction("ProjectWeekly", point);
        }
        return point.proceed();
    }

}
