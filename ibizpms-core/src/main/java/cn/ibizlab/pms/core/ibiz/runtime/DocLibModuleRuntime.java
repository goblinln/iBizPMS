package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.ibiz.domain.DocLibModule;
import cn.ibizlab.pms.core.ibiz.service.IDocLibModuleService;
import cn.ibizlab.pms.core.ibiz.filter.DocLibModuleSearchContext;
import  cn.ibizlab.pms.util.filter.QueryWrapperContext;
import com.baomidou.mybatisplus.extension.service.IService;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import net.ibizsys.model.dataentity.der.IPSDERBase;
import net.ibizsys.model.dataentity.logic.IPSDELogic;
import net.ibizsys.model.dataentity.wf.IPSDEWF;
import net.ibizsys.runtime.IDynaInstRuntime;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.beans.factory.annotation.Autowired;
import net.ibizsys.runtime.dataentity.DEActions;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

@Aspect
@Order(100)
@Component("DocLibModuleRuntime")
public class DocLibModuleRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IDocLibModuleService doclibmoduleService;

    @Override
    protected Object getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntity) {
            return o;
        } else {
            return doclibmoduleService.sysGet(String.valueOf(o));
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/ibiz/PSDATAENTITIES/DocLibModule.json";
    }

    @Override
    public String getName() {
        return "IBZ_DOCLIBMODULE";
    }

    @Override
    public Object createEntity() {
        return new DocLibModule();
    }

    @Override
    protected IService getService() {
        return this.doclibmoduleService;
    }

    @Override
    protected QueryWrapperContext createSearchContext() {
        return new DocLibModuleSearchContext();
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return doclibmoduleService.create((DocLibModule) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return doclibmoduleService.update((DocLibModule) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return doclibmoduleService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                return doclibmoduleService.get((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return doclibmoduleService.getDraft((DocLibModule) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return doclibmoduleService.checkKey((DocLibModule) args[0]);
            }
            else if (iPSDEAction.getName().equals("Collect")) {
                return doclibmoduleService.collect((DocLibModule) args[0]);
            }
            else if (iPSDEAction.getName().equals("DocLibModuleNFavorite")) {
                return doclibmoduleService.docLibModuleNFavorite((DocLibModule) args[0]);
            }
            else if (iPSDEAction.getName().equals("DoclibModuleFavorite")) {
                return doclibmoduleService.doclibModuleFavorite((DocLibModule) args[0]);
            }
            else if (iPSDEAction.getName().equals("Fix")) {
                return doclibmoduleService.fix((DocLibModule) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return doclibmoduleService.save((DocLibModule) args[0]);
            }
            else if (iPSDEAction.getName().equals("UnCollect")) {
                return doclibmoduleService.unCollect((DocLibModule) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return doclibmoduleService.create((DocLibModule) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return doclibmoduleService.update((DocLibModule) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                return doclibmoduleService.get((Long) args[0]);
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return doclibmoduleService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return doclibmoduleService.sysGet((Long) args[0]);
            }  else if (strActionName.equals(DEActions.SYSUPDATE)) {
                
            }         }
        
        return null;
    }

    @Override
    protected void onExecuteDELogic(Object arg0, IPSDEAction iPSDEAction, IPSDELogic iPSDELogic, IDynaInstRuntime iDynaInstRuntime, ProceedingJoinPoint joinPoint) throws Throwable {
        //执行处理逻辑
    }

    @Override
    protected void onExecuteActionLogics(Object arg0, IPSDEAction iPSDEAction, String strAttachMode, IDynaInstRuntime iDynaInstRuntime, ProceedingJoinPoint joinPoint) throws Throwable {
        //行为附加操作
    }

    @Override
    public boolean containsForeignKey(IPSDEField iPSDEField, Object objKey, IPSDERBase iPSDERBase) {
        return false;
    }

    @Override
    public void resetByForeignKey(IPSDEField iPSDEField, Object objKey, IPSDERBase iPSDERBase) {

    }

    @Override
    public void removeByForeignKey(IPSDEField iPSDEField, Object objKey, IPSDERBase iPSDERBase) {

    }

    @Around("execution(* cn.ibizlab.pms.core.ibiz.service.impl.DocLibModuleServiceImpl.*(..))")
    public Object aroundMethod(ProceedingJoinPoint point) throws Throwable {
        if (!this.isRtmodel()) {
            return point.proceed();
        }
        String action = point.getSignature().getName();
        if (action.equals("create")) {
            return aroundAction("Create", point);
        }
        else if (action.equals("update")) {
            return aroundAction("Update", point);
        }
        else if (action.equals("remove")) {
            return aroundAction("Remove", point);
        }
        else if (action.equals("get")) {
            return aroundAction("Get", point);
        }
        else if (action.equals("getDraft")) {
            return aroundAction("GetDraft", point);
        }
        else if (action.equals("checkKey")) {
            return aroundAction("CheckKey", point);
        }
        else if (action.equals("collect")) {
            return aroundAction("Collect", point);
        }
        else if (action.equals("docLibModuleNFavorite")) {
            return aroundAction("DocLibModuleNFavorite", point);
        }
        else if (action.equals("doclibModuleFavorite")) {
            return aroundAction("DoclibModuleFavorite", point);
        }
        else if (action.equals("fix")) {
            return aroundAction("Fix", point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("unCollect")) {
            return aroundAction("UnCollect", point);
        }
        else if (action.equals("searchAllDocLibModule_Custom")) {
            return aroundDataSet("AllDocLibModule_Custom", point);
        }
        else if (action.equals("searchAllDoclibModule")) {
            return aroundDataSet("AllDoclibModule", point);
        }
        else if (action.equals("searchChildModuleByParent")) {
            return aroundDataSet("ChildModuleByParent", point);
        }
        else if (action.equals("searchChildModuleByRealParent")) {
            return aroundDataSet("ChildModuleByRealParent", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchMyFavourites")) {
            return aroundDataSet("MyFavourites", point);
        }
        else if (action.equals("searchParentModule")) {
            return aroundDataSet("ParentModule", point);
        }
        else if (action.equals("searchRootModuleMuLu")) {
            return aroundDataSet("RootModuleMuLu", point);
        }
        else if (action.equals("searchRootModuleMuLuByRoot")) {
            return aroundDataSet("RootModuleMuLuByRoot", point);
        }
        else if (action.equals("searchRootModuleMuLuBysrfparentkey")) {
            return aroundDataSet("RootModuleMuLuBysrfparentkey", point);
        }
        return point.proceed();
    }

}
