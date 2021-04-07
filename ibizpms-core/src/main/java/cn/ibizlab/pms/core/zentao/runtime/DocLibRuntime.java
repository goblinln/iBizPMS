package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.DocLib;
import cn.ibizlab.pms.core.zentao.service.IDocLibService;
import cn.ibizlab.pms.core.zentao.filter.DocLibSearchContext;
import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("DocLibRuntime")
public class DocLibRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

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
        return "ZT_DOCLIB";
    }

    @Override
    public Object createEntity() {
        return new DocLib();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    //@Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.DocLibServiceImpl.*(..))")
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

        if (action.equals("searchByCustom")) {
            return aroundDataSet("ByCustom", point);
        }
        if (action.equals("searchByProduct")) {
            return aroundDataSet("ByProduct", point);
        }
        if (action.equals("searchByProductNotFiles")) {
            return aroundDataSet("ByProductNotFiles", point);
        }
        if (action.equals("searchByProject")) {
            return aroundDataSet("ByProject", point);
        }
        if (action.equals("searchByProjectNotFiles")) {
            return aroundDataSet("ByProjectNotFiles", point);
        }
        if (action.equals("searchCurDocLib")) {
            return aroundDataSet("CurDocLib", point);
        }
        if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        if (action.equals("searchMyFavourites")) {
            return aroundDataSet("MyFavourites", point);
        }
        if (action.equals("searchRootModuleMuLu")) {
            return aroundDataSet("RootModuleMuLu", point);
        }
        return point.proceed();
    }

}
