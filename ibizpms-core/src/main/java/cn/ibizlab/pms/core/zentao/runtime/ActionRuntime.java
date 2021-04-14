package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Action;
import cn.ibizlab.pms.core.zentao.service.IActionService;
import cn.ibizlab.pms.core.zentao.filter.ActionSearchContext;
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
@Component("ActionRuntime")
public class ActionRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IActionService actionService;

    @Override
    protected Object getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntity) {
            return o;
        } else {
            return actionService.sysGet(String.valueOf(o));
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/Action.json";
    }

    @Override
    public String getName() {
        return "ZT_ACTION";
    }

    @Override
    public Object createEntity() {
        return new Action();
    }

    @Override
    protected IService getService() {
        return this.actionService;
    }

    @Override
    protected QueryWrapperContext createSearchContext() {
        return new ActionSearchContext();
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return actionService.create((Action) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return actionService.update((Action) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return actionService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                return actionService.get((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return actionService.getDraft((Action) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return actionService.checkKey((Action) args[0]);
            }
            else if (iPSDEAction.getName().equals("Comment")) {
                return actionService.comment((Action) args[0]);
            }
            else if (iPSDEAction.getName().equals("editComment")) {
                return actionService.editComment((Action) args[0]);
            }
            else if (iPSDEAction.getName().equals("ManagePmsEe")) {
                return actionService.managePmsEe((Action) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return actionService.save((Action) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return actionService.create((Action) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return actionService.update((Action) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                return actionService.get((Long) args[0]);
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return actionService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return actionService.sysGet((Long) args[0]);
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

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.ActionServiceImpl.*(..))")
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
        else if (action.equals("comment")) {
            return aroundAction("Comment", point);
        }
        else if (action.equals("editComment")) {
            return aroundAction("editComment", point);
        }
        else if (action.equals("managePmsEe")) {
            return aroundAction("ManagePmsEe", point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchMobType")) {
            return aroundDataSet("MobType", point);
        }
        else if (action.equals("searchMyTrends")) {
            return aroundDataSet("MyTrends", point);
        }
        else if (action.equals("searchProductTrends")) {
            return aroundDataSet("ProductTrends", point);
        }
        else if (action.equals("searchProjectTrends")) {
            return aroundDataSet("ProjectTrends", point);
        }
        else if (action.equals("searchQueryUserYEAR")) {
            return aroundDataSet("QueryUserYEAR", point);
        }
        else if (action.equals("searchType")) {
            return aroundDataSet("Type", point);
        }
        return point.proceed();
    }

}
