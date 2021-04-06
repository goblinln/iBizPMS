package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Module;
import cn.ibizlab.pms.core.zentao.service.IModuleService;
import cn.ibizlab.pms.core.zentao.filter.ModuleSearchContext;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import cn.ibizlab.pms.core.runtime.DataEntityRuntime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("ModuleRuntime")
public class ModuleRuntime extends DataEntityRuntime {

    @Autowired
    IModuleService moduleService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new Module();
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/Module.json";
    }

    @Override
    public String getName() {
        return "ZT_MODULE";
    }

    @Override
    public Object getFieldValue(Object o, IPSDEField ipsdeField) {
        Module domain = (Module)o;
        try {
            return domain.get(ipsdeField.getCodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public void setFieldValue(Object o, IPSDEField ipsdeField, Object o1) {
        Module domain = (Module)o;
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
        return new Module();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.ModuleServiceImpl.*(..))")
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
        if (action.equals("fix")) {
            return aroundAction("Fix", point);
        }
        if (action.equals("save")) {
            return aroundAction("Save", point);
        }

        if (action.equals("searchBugModule")) {
            return aroundDataSet("BugModule", point);
        }
        if (action.equals("searchBugModuleCodeList")) {
            return aroundDataSet("BugModuleCodeList", point);
        }
        if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        if (action.equals("searchDocModule")) {
            return aroundDataSet("DocModule", point);
        }
        if (action.equals("searchLine")) {
            return aroundDataSet("Line", point);
        }
        if (action.equals("searchStoryModule")) {
            return aroundDataSet("StoryModule", point);
        }
        if (action.equals("searchTaskModule")) {
            return aroundDataSet("TaskModule", point);
        }
        return point.proceed();
    }

}
