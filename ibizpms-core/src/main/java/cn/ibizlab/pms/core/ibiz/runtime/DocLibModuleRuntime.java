package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.ibiz.domain.DocLibModule;
import cn.ibizlab.pms.core.ibiz.service.IDocLibModuleService;
import cn.ibizlab.pms.core.ibiz.filter.DocLibModuleSearchContext;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import cn.ibizlab.pms.core.runtime.DataEntityRuntime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("DocLibModuleRuntime")
public class DocLibModuleRuntime extends DataEntityRuntime {

    @Autowired
    IDocLibModuleService doclibmoduleService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new DocLibModule();
    }

    @Override
    public String getId() {
        return "PSMODULES/ibiz/PSDATAENTITIES/DocLibModule.json";
    }

    @Override
    public String getName() {
        return IBZ_DOCLIBMODULE;
    }

    @Override
    public Object getFieldValue(Object o, IPSDEField ipsdeField) {
        DocLibModule domain = (DocLibModule)o;
        try {
            return domain.get(ipsdeField.getCodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public void setFieldValue(Object o, IPSDEField ipsdeField, Object o1) {
        DocLibModule domain = (DocLibModule)o;
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
        return new DocLibModule();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    @Around("execution(* cn.ibizlab.pms.core.ibiz.service.DocLibModuleServiceImpl.*(..))")
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
        if (action.equals("collect")) {
            return aroundAction("Collect", point);
        }
        if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        if (action.equals("unCollect")) {
            return aroundAction("UnCollect", point);
        }

    //
        if (action.equals("searchAllDocLibModule_Custom")) {
            return aroundAction("AllDocLibModule_Custom", point);
        }
        if (action.equals("searchAllDoclibModule")) {
            return aroundAction("AllDoclibModule", point);
        }
        if (action.equals("searchChildModuleByParent")) {
            return aroundAction("ChildModuleByParent", point);
        }
        if (action.equals("searchChildModuleByRealParent")) {
            return aroundAction("ChildModuleByRealParent", point);
        }
        if (action.equals("searchDefault")) {
            return aroundAction("DEFAULT", point);
        }
        if (action.equals("searchMyFavourites")) {
            return aroundAction("MyFavourites", point);
        }
        if (action.equals("searchParentModule")) {
            return aroundAction("ParentModule", point);
        }
        if (action.equals("searchRootModuleMuLu")) {
            return aroundAction("RootModuleMuLu", point);
        }
        if (action.equals("searchRootModuleMuLuByRoot")) {
            return aroundAction("RootModuleMuLuByRoot", point);
        }
        if (action.equals("searchRootModuleMuLuBysrfparentkey")) {
            return aroundAction("RootModuleMuLuBysrfparentkey", point);
        }
        return point.proceed();
    }

}
