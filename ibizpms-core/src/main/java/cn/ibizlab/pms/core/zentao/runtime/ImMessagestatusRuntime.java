package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.ImMessagestatus;
import cn.ibizlab.pms.core.zentao.service.IImMessagestatusService;
import cn.ibizlab.pms.core.zentao.filter.ImMessagestatusSearchContext;
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
@Component("ImMessagestatusRuntime")
public class ImMessagestatusRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IImMessagestatusService immessagestatusService;

    @Override
    protected Object getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntity) {
            return o;
        } else {
            return immessagestatusService.sysGet((String)o);
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/ImMessagestatus.json";
    }

    @Override
    public String getName() {
        return "ZT_IM_MESSAGESTATUS";
    }

    @Override
    public Object createEntity() {
        return new ImMessagestatus();
    }

    @Override
    protected IService getService() {
        return this.immessagestatusService;
    }

    @Override
    protected QueryWrapperContext createSearchContext() {
        return new ImMessagestatusSearchContext();
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return immessagestatusService.create((ImMessagestatus) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return immessagestatusService.update((ImMessagestatus) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return immessagestatusService.remove((String) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                return immessagestatusService.get((String) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return immessagestatusService.getDraft((ImMessagestatus) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return immessagestatusService.checkKey((ImMessagestatus) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return immessagestatusService.save((ImMessagestatus) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return immessagestatusService.create((ImMessagestatus) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return immessagestatusService.update((ImMessagestatus) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                return immessagestatusService.get((String) args[0]);
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return immessagestatusService.remove((String) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return immessagestatusService.sysGet((String) args[0]);
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

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.ImMessagestatusServiceImpl.*(..))")
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
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        return point.proceed();
    }

}
