package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Module;
import cn.ibizlab.pms.core.zentao.service.IModuleService;
import cn.ibizlab.pms.core.zentao.filter.ModuleSearchContext;
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
@Component("ModuleRuntime")
public class ModuleRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IModuleService moduleService;

    @Override
    protected Object getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntity) {
            return o;
        } else {
            return moduleService.sysGet(String.valueOf(o));
        }
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
    public Object createEntity() {
        return new Module();
    }

    @Override
    protected IService getService() {
        return this.moduleService;
    }

    @Override
    protected QueryWrapperContext createSearchContext() {
        return new ModuleSearchContext();
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return moduleService.create((Module) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return moduleService.update((Module) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return moduleService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                return moduleService.get((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return moduleService.getDraft((Module) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return moduleService.checkKey((Module) args[0]);
            }
            else if (iPSDEAction.getName().equals("Fix")) {
                return moduleService.fix((Module) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return moduleService.save((Module) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return moduleService.create((Module) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return moduleService.update((Module) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                return moduleService.get((Long) args[0]);
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return moduleService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return moduleService.sysGet((Long) args[0]);
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

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.ModuleServiceImpl.*(..))")
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
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("searchBugModule")) {
            return aroundDataSet("BugModule", point);
        }
        else if (action.equals("searchBugModuleCodeList")) {
            return aroundDataSet("BugModuleCodeList", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchDocModule")) {
            return aroundDataSet("DocModule", point);
        }
        else if (action.equals("searchLine")) {
            return aroundDataSet("Line", point);
        }
        else if (action.equals("searchStoryModule")) {
            return aroundDataSet("StoryModule", point);
        }
        else if (action.equals("searchTaskModule")) {
            return aroundDataSet("TaskModule", point);
        }
        return point.proceed();
    }

}
