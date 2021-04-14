package cn.ibizlab.pms.core.report.runtime;

import cn.ibizlab.pms.core.report.domain.IbzDaily;
import cn.ibizlab.pms.core.report.service.IIbzDailyService;
import cn.ibizlab.pms.core.report.filter.IbzDailySearchContext;
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
@Component("IbzDailyRuntime")
public class IbzDailyRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IIbzDailyService ibzdailyService;

    @Override
    protected Object getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntity) {
            return o;
        } else {
            return ibzdailyService.sysGet(String.valueOf(o));
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/report/PSDATAENTITIES/IbzDaily.json";
    }

    @Override
    public String getName() {
        return "IBZ_DAILY";
    }

    @Override
    public Object createEntity() {
        return new IbzDaily();
    }

    @Override
    protected IService getService() {
        return this.ibzdailyService;
    }

    @Override
    protected QueryWrapperContext createSearchContext() {
        return new IbzDailySearchContext();
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return ibzdailyService.create((IbzDaily) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return ibzdailyService.update((IbzDaily) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return ibzdailyService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                return ibzdailyService.get((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return ibzdailyService.getDraft((IbzDaily) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return ibzdailyService.checkKey((IbzDaily) args[0]);
            }
            else if (iPSDEAction.getName().equals("CreateUserDaily")) {
                return ibzdailyService.createUserDaily((IbzDaily) args[0]);
            }
            else if (iPSDEAction.getName().equals("getYeaterdayDailyPlansTaskEdit")) {
                return ibzdailyService.getYeaterdayDailyPlansTaskEdit((IbzDaily) args[0]);
            }
            else if (iPSDEAction.getName().equals("getYesterdayDailyPlansTask")) {
                return ibzdailyService.getYesterdayDailyPlansTask((IbzDaily) args[0]);
            }
            else if (iPSDEAction.getName().equals("HaveRead")) {
                return ibzdailyService.haveRead((IbzDaily) args[0]);
            }
            else if (iPSDEAction.getName().equals("LinkCompleteTask")) {
                return ibzdailyService.linkCompleteTask((IbzDaily) args[0]);
            }
            else if (iPSDEAction.getName().equals("PushUserDaily")) {
                return ibzdailyService.pushUserDaily((IbzDaily) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return ibzdailyService.save((IbzDaily) args[0]);
            }
            else if (iPSDEAction.getName().equals("submit")) {
                return ibzdailyService.submit((IbzDaily) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return ibzdailyService.create((IbzDaily) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return ibzdailyService.update((IbzDaily) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                return ibzdailyService.get((Long) args[0]);
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return ibzdailyService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return ibzdailyService.sysGet((Long) args[0]);
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

    @Around("execution(* cn.ibizlab.pms.core.report.service.impl.IbzDailyServiceImpl.*(..))")
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
        else if (action.equals("createUserDaily")) {
            return aroundAction("CreateUserDaily", point);
        }
        else if (action.equals("getYeaterdayDailyPlansTaskEdit")) {
            return aroundAction("getYeaterdayDailyPlansTaskEdit", point);
        }
        else if (action.equals("getYesterdayDailyPlansTask")) {
            return aroundAction("getYesterdayDailyPlansTask", point);
        }
        else if (action.equals("haveRead")) {
            return aroundAction("HaveRead", point);
        }
        else if (action.equals("linkCompleteTask")) {
            return aroundAction("LinkCompleteTask", point);
        }
        else if (action.equals("pushUserDaily")) {
            return aroundAction("PushUserDaily", point);
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
        else if (action.equals("searchMyAllDaily")) {
            return aroundDataSet("MyAllDaily", point);
        }
        else if (action.equals("searchMyDaily")) {
            return aroundDataSet("MyDaily", point);
        }
        else if (action.equals("searchMyNotSubmit")) {
            return aroundDataSet("MyNotSubmit", point);
        }
        else if (action.equals("searchMySubmitDaily")) {
            return aroundDataSet("MySubmitDaily", point);
        }
        else if (action.equals("searchProductDaily")) {
            return aroundDataSet("ProductDaily", point);
        }
        else if (action.equals("searchProjectDaily")) {
            return aroundDataSet("ProjectDaily", point);
        }
        return point.proceed();
    }

}
