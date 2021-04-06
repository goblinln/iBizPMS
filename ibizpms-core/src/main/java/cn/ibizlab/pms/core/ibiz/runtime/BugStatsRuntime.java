package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.ibiz.domain.BugStats;
import cn.ibizlab.pms.core.ibiz.service.IBugStatsService;
import cn.ibizlab.pms.core.ibiz.filter.BugStatsSearchContext;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import cn.ibizlab.pms.core.runtime.DataEntityRuntime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("BugStatsRuntime")
public class BugStatsRuntime extends DataEntityRuntime {

    @Autowired
    IBugStatsService bugstatsService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new BugStats();
    }

    @Override
    public String getId() {
        return "PSMODULES/ibiz/PSDATAENTITIES/BugStats.json";
    }

    @Override
    public String getName() {
        return "IBZ_BUGSTATS";
    }

    @Override
    public Object getFieldValue(Object o, IPSDEField ipsdeField) {
        BugStats domain = (BugStats)o;
        try {
            return domain.get(ipsdeField.getCodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public void setFieldValue(Object o, IPSDEField ipsdeField, Object o1) {
        BugStats domain = (BugStats)o;
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
        return new BugStats();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    @Around("execution(* cn.ibizlab.pms.core.ibiz.service.impl.BugStatsServiceImpl.*(..))")
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

        if (action.equals("searchBugCountInResolution")) {
            return aroundDataSet("BugCountInResolution", point);
        }
        if (action.equals("searchBugResolvedBy")) {
            return aroundDataSet("BugResolvedBy", point);
        }
        if (action.equals("searchBugResolvedGird")) {
            return aroundDataSet("BugResolvedGird", point);
        }
        if (action.equals("searchBugassignedTo")) {
            return aroundDataSet("BugassignedTo", point);
        }
        if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        if (action.equals("searchProductBugResolutionStats")) {
            return aroundDataSet("ProductBugResolutionStats", point);
        }
        if (action.equals("searchProductBugStatusSum")) {
            return aroundDataSet("ProductBugStatusSum", point);
        }
        if (action.equals("searchProductCreateBug")) {
            return aroundDataSet("ProductCreateBug", point);
        }
        if (action.equals("searchProjectBugStatusCount")) {
            return aroundDataSet("ProjectBugStatusCount", point);
        }
        return point.proceed();
    }

}
