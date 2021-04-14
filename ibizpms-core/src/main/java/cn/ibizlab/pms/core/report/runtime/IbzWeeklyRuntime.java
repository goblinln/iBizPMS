package cn.ibizlab.pms.core.report.runtime;

import cn.ibizlab.pms.core.report.domain.IbzWeekly;
import cn.ibizlab.pms.core.report.service.IIbzWeeklyService;
import cn.ibizlab.pms.core.report.filter.IbzWeeklySearchContext;
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
@Component("IbzWeeklyRuntime")
public class IbzWeeklyRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IIbzWeeklyService ibzweeklyService;

    @Override
    protected Object getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntity) {
            return o;
        } else {
            return ibzweeklyService.sysGet(String.valueOf(o));
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/report/PSDATAENTITIES/IbzWeekly.json";
    }

    @Override
    public String getName() {
        return "IBZ_WEEKLY";
    }

    @Override
    public Object createEntity() {
        return new IbzWeekly();
    }

    @Override
    protected IService getService() {
        return this.ibzweeklyService;
    }

    @Override
    protected QueryWrapperContext createSearchContext() {
        return new IbzWeeklySearchContext();
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return ibzweeklyService.create((IbzWeekly) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return ibzweeklyService.update((IbzWeekly) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return ibzweeklyService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                return ibzweeklyService.get((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return ibzweeklyService.getDraft((IbzWeekly) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return ibzweeklyService.checkKey((IbzWeekly) args[0]);
            }
            else if (iPSDEAction.getName().equals("createEveryWeekReport")) {
                return ibzweeklyService.createEveryWeekReport((IbzWeekly) args[0]);
            }
            else if (iPSDEAction.getName().equals("CreateGetLastWeekPlanAndWork")) {
                return ibzweeklyService.createGetLastWeekPlanAndWork((IbzWeekly) args[0]);
            }
            else if (iPSDEAction.getName().equals("EditGetLastWeekTaskAndComTask")) {
                return ibzweeklyService.editGetLastWeekTaskAndComTask((IbzWeekly) args[0]);
            }
            else if (iPSDEAction.getName().equals("haveRead")) {
                return ibzweeklyService.haveRead((IbzWeekly) args[0]);
            }
            else if (iPSDEAction.getName().equals("JugThisWeekCreateWeekly")) {
                return ibzweeklyService.jugThisWeekCreateWeekly((IbzWeekly) args[0]);
            }
            else if (iPSDEAction.getName().equals("pushUserWeekly")) {
                return ibzweeklyService.pushUserWeekly((IbzWeekly) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return ibzweeklyService.save((IbzWeekly) args[0]);
            }
            else if (iPSDEAction.getName().equals("submit")) {
                return ibzweeklyService.submit((IbzWeekly) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return ibzweeklyService.create((IbzWeekly) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return ibzweeklyService.update((IbzWeekly) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                return ibzweeklyService.get((Long) args[0]);
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return ibzweeklyService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return ibzweeklyService.sysGet((Long) args[0]);
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

    @Around("execution(* cn.ibizlab.pms.core.report.service.impl.IbzWeeklyServiceImpl.*(..))")
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
        else if (action.equals("createEveryWeekReport")) {
            return aroundAction("createEveryWeekReport", point);
        }
        else if (action.equals("createGetLastWeekPlanAndWork")) {
            return aroundAction("CreateGetLastWeekPlanAndWork", point);
        }
        else if (action.equals("editGetLastWeekTaskAndComTask")) {
            return aroundAction("EditGetLastWeekTaskAndComTask", point);
        }
        else if (action.equals("haveRead")) {
            return aroundAction("haveRead", point);
        }
        else if (action.equals("jugThisWeekCreateWeekly")) {
            return aroundAction("JugThisWeekCreateWeekly", point);
        }
        else if (action.equals("pushUserWeekly")) {
            return aroundAction("pushUserWeekly", point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("submit")) {
            return aroundAction("submit", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchMyNotSubmit")) {
            return aroundDataSet("MyNotSubmit", point);
        }
        else if (action.equals("searchMyWeekly")) {
            return aroundDataSet("MyWeekly", point);
        }
        else if (action.equals("searchProductTeamMemberWeekly")) {
            return aroundDataSet("ProductTeamMemberWeekly", point);
        }
        else if (action.equals("searchProjectWeekly")) {
            return aroundDataSet("ProjectWeekly", point);
        }
        return point.proceed();
    }

}
