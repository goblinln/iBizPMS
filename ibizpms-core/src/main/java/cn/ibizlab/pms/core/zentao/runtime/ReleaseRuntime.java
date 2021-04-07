package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Release;
import cn.ibizlab.pms.core.zentao.service.IReleaseService;
import cn.ibizlab.pms.core.zentao.filter.ReleaseSearchContext;
import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("ReleaseRuntime")
public class ReleaseRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IReleaseService releaseService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new Release();
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/Release.json";
    }

    @Override
    public String getName() {
        return "ZT_RELEASE";
    }

    @Override
    public Object getFieldValue(Object o, IPSDEField ipsdeField) {
        Release domain = (Release) o;
        try {
            return domain.get(ipsdeField.getCodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public void setFieldValue(Object o, IPSDEField ipsdeField, Object o1) {
        Release domain = (Release) o;
        try {
            domain.set(ipsdeField.getCodeName(),o1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean containsFieldValue(Object o, IPSDEField ipsdeField) {
        Release domain = (Release) o;
        try {
            return domain.contains(ipsdeField.getCodeName().toLowerCase());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void resetFieldValue(Object o, IPSDEField ipsdeField) {
        Release domain = (Release) o;
        try {
            domain.reset(ipsdeField.getCodeName().toLowerCase());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object createEntity() {
        return new Release();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    //@Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.ReleaseServiceImpl.*(..))")
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
        if (action.equals("activate")) {
            return aroundAction("Activate", point);
        }
        if (action.equals("batchUnlinkBug")) {
            return aroundAction("BatchUnlinkBug", point);
        }
        if (action.equals("changeStatus")) {
            return aroundAction("ChangeStatus", point);
        }
        if (action.equals("checkKey")) {
            return aroundAction("CheckKey", point);
        }
        if (action.equals("linkBug")) {
            return aroundAction("linkBug", point);
        }
        if (action.equals("linkBugbyBug")) {
            return aroundAction("LinkBugbyBug", point);
        }
        if (action.equals("linkBugbyLeftBug")) {
            return aroundAction("LinkBugbyLeftBug", point);
        }
        if (action.equals("linkStory")) {
            return aroundAction("linkStory", point);
        }
        if (action.equals("oneClickRelease")) {
            return aroundAction("OneClickRelease", point);
        }
        if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        if (action.equals("terminate")) {
            return aroundAction("Terminate", point);
        }
        if (action.equals("unlinkBug")) {
            return aroundAction("UnlinkBug", point);
        }

        if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        if (action.equals("searchReportRelease")) {
            return aroundDataSet("ReportRelease", point);
        }
        return point.proceed();
    }

}
