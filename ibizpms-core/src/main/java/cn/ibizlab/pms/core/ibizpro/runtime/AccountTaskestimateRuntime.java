package cn.ibizlab.pms.core.ibizpro.runtime;

import cn.ibizlab.pms.core.ibizpro.domain.AccountTaskestimate;
import cn.ibizlab.pms.core.ibizpro.service.IAccountTaskestimateService;
import cn.ibizlab.pms.core.ibizpro.filter.AccountTaskestimateSearchContext;
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
@Component("AccountTaskestimateRuntime")
public class AccountTaskestimateRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IAccountTaskestimateService accounttaskestimateService;

    @Override
    protected Object getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntity) {
            return o;
        } else {
            return accounttaskestimateService.sysGet(String.valueOf(o));
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/ibizpro/PSDATAENTITIES/AccountTaskestimate.json";
    }

    @Override
    public String getName() {
        return "ACCOUNTTASKESTIMATE";
    }

    @Override
    public Object createEntity() {
        return new AccountTaskestimate();
    }

    @Override
    protected IService getService() {
        return this.accounttaskestimateService;
    }

    @Override
    protected QueryWrapperContext createSearchContext() {
        return new AccountTaskestimateSearchContext();
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return accounttaskestimateService.create((AccountTaskestimate) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return accounttaskestimateService.update((AccountTaskestimate) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return accounttaskestimateService.remove((String) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                return accounttaskestimateService.get((String) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return accounttaskestimateService.getDraft((AccountTaskestimate) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return accounttaskestimateService.checkKey((AccountTaskestimate) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return accounttaskestimateService.save((AccountTaskestimate) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return accounttaskestimateService.create((AccountTaskestimate) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return accounttaskestimateService.update((AccountTaskestimate) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                return accounttaskestimateService.get((String) args[0]);
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return accounttaskestimateService.remove((String) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return accounttaskestimateService.sysGet((String) args[0]);
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

    @Around("execution(* cn.ibizlab.pms.core.ibizpro.service.impl.AccountTaskestimateServiceImpl.*(..))")
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
        else if (action.equals("searchAllAccountEstimate")) {
            return aroundDataSet("AllAccountEstimate", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        return point.proceed();
    }

}
