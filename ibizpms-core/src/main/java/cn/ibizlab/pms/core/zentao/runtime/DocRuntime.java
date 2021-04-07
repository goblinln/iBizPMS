package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Doc;
import cn.ibizlab.pms.core.zentao.service.IDocService;
import cn.ibizlab.pms.core.zentao.filter.DocSearchContext;
import cn.ibizlab.pms.core.runtime.DataEntityRuntime;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("DocRuntime")
public class DocRuntime extends DataEntityRuntime {

    @Autowired
    IDocService docService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new Doc();
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/Doc.json";
    }

    @Override
    public String getName() {
        return "ZT_DOC";
    }

    @Override
    public Object getFieldValue(Object o, IPSDEField ipsdeField) {
        Doc domain = (Doc)o;
        try {
            return domain.get(ipsdeField.getCodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public void setFieldValue(Object o, IPSDEField ipsdeField, Object o1) {
        Doc domain = (Doc)o;
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
        return new Doc();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.DocServiceImpl.*(..))")
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
        if (action.equals("byVersionUpdateContext")) {
            return aroundAction("ByVersionUpdateContext", point);
        }
        if (action.equals("checkKey")) {
            return aroundAction("CheckKey", point);
        }
        if (action.equals("collect")) {
            return aroundAction("Collect", point);
        }
        if (action.equals("getDocStatus")) {
            return aroundAction("GetDocStatus", point);
        }
        if (action.equals("onlyCollectDoc")) {
            return aroundAction("OnlyCollectDoc", point);
        }
        if (action.equals("onlyUnCollectDoc")) {
            return aroundAction("OnlyUnCollectDoc", point);
        }
        if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        if (action.equals("unCollect")) {
            return aroundAction("UnCollect", point);
        }

        if (action.equals("searchChildDocLibDoc")) {
            return aroundDataSet("ChildDocLibDoc", point);
        }
        if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        if (action.equals("searchDocLibAndDoc")) {
            return aroundDataSet("DocLibAndDoc", point);
        }
        if (action.equals("searchDocLibDoc")) {
            return aroundDataSet("DocLibDoc", point);
        }
        if (action.equals("searchDocModuleDoc")) {
            return aroundDataSet("DocModuleDoc", point);
        }
        if (action.equals("searchDocStatus")) {
            return aroundDataSet("DocStatus", point);
        }
        if (action.equals("searchModuleDocChild")) {
            return aroundDataSet("ModuleDocChild", point);
        }
        if (action.equals("searchMyFavourite")) {
            return aroundDataSet("MYFAVOURITE", point);
        }
        if (action.equals("searchMyFavouritesOnlyDoc")) {
            return aroundDataSet("MyFavouritesOnlyDoc", point);
        }
        if (action.equals("searchNotRootDoc")) {
            return aroundDataSet("NotRootDoc", point);
        }
        if (action.equals("searchRootDoc")) {
            return aroundDataSet("RootDoc", point);
        }
        return point.proceed();
    }

}
