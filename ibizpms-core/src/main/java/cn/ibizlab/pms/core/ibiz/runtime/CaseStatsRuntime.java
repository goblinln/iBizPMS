package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.ibiz.domain.CaseStats;
import cn.ibizlab.pms.core.ibiz.service.ICaseStatsService;
import cn.ibizlab.pms.core.ibiz.filter.CaseStatsSearchContext;
import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("CaseStatsRuntime")
public class CaseStatsRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    ICaseStatsService casestatsService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new CaseStats();
    }

    @Override
    public String getId() {
        return "PSMODULES/ibiz/PSDATAENTITIES/CaseStats.json";
    }

    @Override
    public String getName() {
        return "IBZ_CASESTATS";
    }

    @Override
    public Object getFieldValue(Object o, IPSDEField ipsdeField) {
        CaseStats domain = (CaseStats) o;
        try {
            return domain.get(ipsdeField.getCodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public void setFieldValue(Object o, IPSDEField ipsdeField, Object o1) {
        CaseStats domain = (CaseStats) o;
        try {
            domain.set(ipsdeField.getCodeName(),o1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean containsFieldValue(Object o, IPSDEField ipsdeField) {
        CaseStats domain = (CaseStats) o;
        try {
            return domain.contains(ipsdeField.getCodeName().toLowerCase());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void resetFieldValue(Object o, IPSDEField ipsdeField) {
        CaseStats domain = (CaseStats) o;
        try {
            domain.reset(ipsdeField.getCodeName().toLowerCase());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object createEntity() {
        return new CaseStats();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    //@Around("execution(* cn.ibizlab.pms.core.ibiz.service.impl.CaseStatsServiceImpl.*(..))")
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
        if (action.equals("save")) {
            return aroundAction("Save", point);
        }

        if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        if (action.equals("searchTestCaseStats")) {
            return aroundDataSet("TestCaseStats", point);
        }
        return point.proceed();
    }

}
