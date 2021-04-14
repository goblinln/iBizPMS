package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.ibiz.domain.ProjectModule;
import cn.ibizlab.pms.core.ibiz.service.IProjectModuleService;
import cn.ibizlab.pms.core.ibiz.filter.ProjectModuleSearchContext;
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
@Component("ProjectModuleRuntime")
public class ProjectModuleRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IProjectModuleService projectmoduleService;

    @Override
    protected Object getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntity) {
            return o;
        } else {
            return projectmoduleService.sysGet(String.valueOf(o));
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/ibiz/PSDATAENTITIES/ProjectModule.json";
    }

    @Override
    public String getName() {
        return "IBZ_PROJECTMODULE";
    }

    @Override
    public Object createEntity() {
        return new ProjectModule();
    }

    @Override
    protected IService getService() {
        return this.projectmoduleService;
    }

    @Override
    protected QueryWrapperContext createSearchContext() {
        return new ProjectModuleSearchContext();
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return projectmoduleService.create((ProjectModule) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return projectmoduleService.update((ProjectModule) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return projectmoduleService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                return projectmoduleService.get((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return projectmoduleService.getDraft((ProjectModule) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return projectmoduleService.checkKey((ProjectModule) args[0]);
            }
            else if (iPSDEAction.getName().equals("Fix")) {
                return projectmoduleService.fix((ProjectModule) args[0]);
            }
            else if (iPSDEAction.getName().equals("RemoveModule")) {
                return projectmoduleService.removeModule((ProjectModule) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return projectmoduleService.save((ProjectModule) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return projectmoduleService.create((ProjectModule) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return projectmoduleService.update((ProjectModule) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                return projectmoduleService.get((Long) args[0]);
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return projectmoduleService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return projectmoduleService.sysGet((Long) args[0]);
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

    @Around("execution(* cn.ibizlab.pms.core.ibiz.service.impl.ProjectModuleServiceImpl.*(..))")
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
        else if (action.equals("fix")) {
            return aroundAction("Fix", point);
        }
        else if (action.equals("removeModule")) {
            return aroundAction("RemoveModule", point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("searchByPath")) {
            return aroundDataSet("BYPATH", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchParentModule")) {
            return aroundDataSet("ParentModule", point);
        }
        else if (action.equals("searchRoot")) {
            return aroundDataSet("ROOT", point);
        }
        else if (action.equals("searchRoot_NoBranch")) {
            return aroundDataSet("Root_NoBranch", point);
        }
        else if (action.equals("searchRoot_Task")) {
            return aroundDataSet("ROOT_TASK", point);
        }
        else if (action.equals("searchTaskModules")) {
            return aroundDataSet("TaskModules", point);
        }
        return point.proceed();
    }

}
