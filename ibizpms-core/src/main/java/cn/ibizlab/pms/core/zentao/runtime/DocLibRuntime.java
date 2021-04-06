package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.DocLib;
import cn.ibizlab.pms.core.zentao.service.IDocLibService;
import cn.ibizlab.pms.core.zentao.filter.DocLibSearchContext;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import cn.ibizlab.pms.core.runtime.DataEntityRuntime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("DocLibRuntime")
public class DocLibRuntime extends DataEntityRuntime {

    @Autowired
    IDocLibService doclibService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new DocLib();
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/DocLib.json";
    }

    @Override
    public String getName() {
        return ZT_DOCLIB;
    }

    @Override
    public Object getFieldValue(Object o, IPSDEField ipsdeField) {
        DocLib domain = (DocLib)o;
        try {
            return domain.get(ipsdeField.getCodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public void setFieldValue(Object o, IPSDEField ipsdeField, Object o1) {
        DocLib domain = (DocLib)o;
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
        return new DocLib();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.DocLibServiceImpl.*(..))")
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
        if (action.equals("searchByCustom")) {
            return aroundAction("ByCustom", point);
        }
        if (action.equals("searchByProduct")) {
            return aroundAction("ByProduct", point);
        }
        if (action.equals("searchByProductNotFiles")) {
            return aroundAction("ByProductNotFiles", point);
        }
        if (action.equals("searchByProject")) {
            return aroundAction("ByProject", point);
        }
        if (action.equals("searchByProjectNotFiles")) {
            return aroundAction("ByProjectNotFiles", point);
        }
        if (action.equals("searchCurDocLib")) {
            return aroundAction("CurDocLib", point);
        }
        if (action.equals("searchDefault")) {
            return aroundAction("DEFAULT", point);
        }
        if (action.equals("searchMyFavourites")) {
            return aroundAction("MyFavourites", point);
        }
        if (action.equals("searchRootModuleMuLu")) {
            return aroundAction("RootModuleMuLu", point);
        }
        return point.proceed();
    }

}
