package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.ibiz.domain.SysUpdateLog;
import cn.ibizlab.pms.core.ibiz.service.ISysUpdateLogService;
import cn.ibizlab.pms.core.ibiz.filter.SysUpdateLogSearchContext;
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
@Component("SysUpdateLogRuntime")
public class SysUpdateLogRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    ISysUpdateLogService sysupdatelogService;

    @Override
    protected Object getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntity) {
            return o;
        } else {
            return sysupdatelogService.sysGet((String)o);
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/ibiz/PSDATAENTITIES/SysUpdateLog.json";
    }

    @Override
    public String getName() {
        return "SYS_UPDATE_LOG";
    }

    @Override
    public Object createEntity() {
        return new SysUpdateLog();
    }

    @Override
    protected IService getService() {
        return this.sysupdatelogService;
    }

    @Override
    protected QueryWrapperContext createSearchContext() {
        return new SysUpdateLogSearchContext();
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return sysupdatelogService.create((SysUpdateLog) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return sysupdatelogService.update((SysUpdateLog) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return sysupdatelogService.remove((String) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                return sysupdatelogService.get((String) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return sysupdatelogService.getDraft((SysUpdateLog) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return sysupdatelogService.checkKey((SysUpdateLog) args[0]);
            }
            else if (iPSDEAction.getName().equals("getLastUpdateInfo")) {
                return sysupdatelogService.getLastUpdateInfo((SysUpdateLog) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return sysupdatelogService.save((SysUpdateLog) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return sysupdatelogService.create((SysUpdateLog) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return sysupdatelogService.update((SysUpdateLog) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                return sysupdatelogService.get((String) args[0]);
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return sysupdatelogService.remove((String) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return sysupdatelogService.sysGet((String) args[0]);
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

    @Around("execution(* cn.ibizlab.pms.core.ibiz.service.impl.SysUpdateLogServiceImpl.*(..))")
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
        else if (action.equals("getLastUpdateInfo")) {
            return aroundAction("getLastUpdateInfo", point);
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
