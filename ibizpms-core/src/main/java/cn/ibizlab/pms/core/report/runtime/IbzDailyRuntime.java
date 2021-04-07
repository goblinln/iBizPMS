package cn.ibizlab.pms.core.report.runtime;

import cn.ibizlab.pms.core.report.domain.IbzDaily;
import cn.ibizlab.pms.core.report.service.IIbzDailyService;
import cn.ibizlab.pms.core.report.filter.IbzDailySearchContext;
import cn.ibizlab.pms.core.runtime.DataEntityRuntime;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("IbzDailyRuntime")
public class IbzDailyRuntime extends DataEntityRuntime {

    @Autowired
    IIbzDailyService ibzdailyService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new IbzDaily();
    }

    @Override
    public String getId() {
        return "PSMODULES/report/PSDATAENTITIES/IbzDaily.json";
    }

    @Override
    public String getName() {
        return "IBZ_DAILY";
    }

    @Override
    public Object getFieldValue(Object o, IPSDEField ipsdeField) {
        IbzDaily domain = (IbzDaily)o;
        try {
            return domain.get(ipsdeField.getCodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public void setFieldValue(Object o, IPSDEField ipsdeField, Object o1) {
        IbzDaily domain = (IbzDaily)o;
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
        return new IbzDaily();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    @Around("execution(* cn.ibizlab.pms.core.report.service.impl.IbzDailyServiceImpl.*(..))")
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
        if (action.equals("createUserDaily")) {
            return aroundAction("CreateUserDaily", point);
        }
        if (action.equals("getYeaterdayDailyPlansTaskEdit")) {
            return aroundAction("getYeaterdayDailyPlansTaskEdit", point);
        }
        if (action.equals("getYesterdayDailyPlansTask")) {
            return aroundAction("getYesterdayDailyPlansTask", point);
        }
        if (action.equals("haveRead")) {
            return aroundAction("HaveRead", point);
        }
        if (action.equals("linkCompleteTask")) {
            return aroundAction("LinkCompleteTask", point);
        }
        if (action.equals("pushUserDaily")) {
            return aroundAction("PushUserDaily", point);
        }
        if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        if (action.equals("submit")) {
            return aroundAction("submit", point);
        }

        if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        if (action.equals("searchMyAllDaily")) {
            return aroundDataSet("MyAllDaily", point);
        }
        if (action.equals("searchMyDaily")) {
            return aroundDataSet("MyDaily", point);
        }
        if (action.equals("searchMyNotSubmit")) {
            return aroundDataSet("MyNotSubmit", point);
        }
        if (action.equals("searchMySubmitDaily")) {
            return aroundDataSet("MySubmitDaily", point);
        }
        if (action.equals("searchProductDaily")) {
            return aroundDataSet("ProductDaily", point);
        }
        if (action.equals("searchProjectDaily")) {
            return aroundDataSet("ProjectDaily", point);
        }
        return point.proceed();
    }

}
