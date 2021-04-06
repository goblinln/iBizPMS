package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Build;
import cn.ibizlab.pms.core.zentao.service.IBuildService;
import cn.ibizlab.pms.core.zentao.filter.BuildSearchContext;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import cn.ibizlab.pms.core.runtime.DataEntityRuntime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("BuildRuntime")
public class BuildRuntime extends DataEntityRuntime {

    @Autowired
    IBuildService buildService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new Build();
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/Build.json";
    }

    @Override
    public String getName() {
        return "ZT_BUILD";
    }

    @Override
    public Object getFieldValue(Object o, IPSDEField ipsdeField) {
        Build domain = (Build)o;
        try {
            return domain.get(ipsdeField.getCodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public void setFieldValue(Object o, IPSDEField ipsdeField, Object o1) {
        Build domain = (Build)o;
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
        return new Build();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.BuildServiceImpl.*(..))")
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
        if (action.equals("linkStory")) {
            return aroundAction("LinkStory", point);
        }
        if (action.equals("oneClickRelease")) {
            return aroundAction("OneClickRelease", point);
        }
        if (action.equals("save")) {
            return aroundAction("Save", point);
        }

        if (action.equals("searchBugProductBuild")) {
            return aroundDataSet("BugProductBuild", point);
        }
        if (action.equals("searchCurProduct")) {
            return aroundDataSet("CurProduct", point);
        }
        if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        if (action.equals("searchTestBuild")) {
            return aroundDataSet("TestBuild", point);
        }
        if (action.equals("searchTestRounds")) {
            return aroundDataSet("TestRounds", point);
        }
        if (action.equals("searchUpdateLog")) {
            return aroundDataSet("UpdateLog", point);
        }
        return point.proceed();
    }

}
