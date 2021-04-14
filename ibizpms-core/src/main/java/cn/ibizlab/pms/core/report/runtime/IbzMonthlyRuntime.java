package cn.ibizlab.pms.core.report.runtime;

import cn.ibizlab.pms.core.report.domain.IbzMonthly;
import cn.ibizlab.pms.core.report.service.IIbzMonthlyService;
import cn.ibizlab.pms.core.report.filter.IbzMonthlySearchContext;
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
@Component("IbzMonthlyRuntime")
public class IbzMonthlyRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IIbzMonthlyService ibzmonthlyService;

    @Override
    protected Object getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntity) {
            return o;
        } else {
            return ibzmonthlyService.sysGet(String.valueOf(o));
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/report/PSDATAENTITIES/IbzMonthly.json";
    }

    @Override
    public String getName() {
        return "IBZ_MONTHLY";
    }

    @Override
    public Object createEntity() {
        return new IbzMonthly();
    }

    @Override
    protected IService getService() {
        return this.ibzmonthlyService;
    }

    @Override
    protected QueryWrapperContext createSearchContext() {
        return new IbzMonthlySearchContext();
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return ibzmonthlyService.create((IbzMonthly) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return ibzmonthlyService.update((IbzMonthly) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return ibzmonthlyService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                return ibzmonthlyService.get((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return ibzmonthlyService.getDraft((IbzMonthly) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return ibzmonthlyService.checkKey((IbzMonthly) args[0]);
            }
            else if (iPSDEAction.getName().equals("CreateGetInfo")) {
                return ibzmonthlyService.createGetInfo((IbzMonthly) args[0]);
            }
            else if (iPSDEAction.getName().equals("CreateUserMonthly")) {
                return ibzmonthlyService.createUserMonthly((IbzMonthly) args[0]);
            }
            else if (iPSDEAction.getName().equals("EditGetCompleteTask")) {
                return ibzmonthlyService.editGetCompleteTask((IbzMonthly) args[0]);
            }
            else if (iPSDEAction.getName().equals("HaveRead")) {
                return ibzmonthlyService.haveRead((IbzMonthly) args[0]);
            }
            else if (iPSDEAction.getName().equals("PushUserMonthly")) {
                return ibzmonthlyService.pushUserMonthly((IbzMonthly) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return ibzmonthlyService.save((IbzMonthly) args[0]);
            }
            else if (iPSDEAction.getName().equals("Submit")) {
                return ibzmonthlyService.submit((IbzMonthly) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return ibzmonthlyService.create((IbzMonthly) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return ibzmonthlyService.update((IbzMonthly) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                return ibzmonthlyService.get((Long) args[0]);
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return ibzmonthlyService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return ibzmonthlyService.sysGet((Long) args[0]);
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

    @Around("execution(* cn.ibizlab.pms.core.report.service.impl.IbzMonthlyServiceImpl.*(..))")
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
        else if (action.equals("createGetInfo")) {
            return aroundAction("CreateGetInfo", point);
        }
        else if (action.equals("createUserMonthly")) {
            return aroundAction("CreateUserMonthly", point);
        }
        else if (action.equals("editGetCompleteTask")) {
            return aroundAction("EditGetCompleteTask", point);
        }
        else if (action.equals("haveRead")) {
            return aroundAction("HaveRead", point);
        }
        else if (action.equals("pushUserMonthly")) {
            return aroundAction("PushUserMonthly", point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("submit")) {
            return aroundAction("Submit", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchMyMonthly")) {
            return aroundDataSet("MyMonthly", point);
        }
        else if (action.equals("searchMyMonthlyMob")) {
            return aroundDataSet("MyMonthlyMob", point);
        }
        else if (action.equals("searchMyReceivedMonthly")) {
            return aroundDataSet("MyReceivedMonthly", point);
        }
        else if (action.equals("searchMySubmitMonthly")) {
            return aroundDataSet("MySubmitMonthly", point);
        }
        else if (action.equals("searchProductMonthly")) {
            return aroundDataSet("ProductMonthly", point);
        }
        else if (action.equals("searchProjectMonthly")) {
            return aroundDataSet("ProjectMonthly", point);
        }
        return point.proceed();
    }

}
