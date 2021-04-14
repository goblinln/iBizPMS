package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.TaskEstimate;
import cn.ibizlab.pms.core.zentao.service.ITaskEstimateService;
import cn.ibizlab.pms.core.zentao.filter.TaskEstimateSearchContext;
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
@Component("TaskEstimateRuntime")
public class TaskEstimateRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    ITaskEstimateService taskestimateService;

    @Override
    protected Object getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntity) {
            return o;
        } else {
            return taskestimateService.sysGet(String.valueOf(o));
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/TaskEstimate.json";
    }

    @Override
    public String getName() {
        return "ZT_TASKESTIMATE";
    }

    @Override
    public Object createEntity() {
        return new TaskEstimate();
    }

    @Override
    protected IService getService() {
        return this.taskestimateService;
    }

    @Override
    protected QueryWrapperContext createSearchContext() {
        return new TaskEstimateSearchContext();
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return taskestimateService.create((TaskEstimate) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return taskestimateService.update((TaskEstimate) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return taskestimateService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                return taskestimateService.get((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return taskestimateService.getDraft((TaskEstimate) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return taskestimateService.checkKey((TaskEstimate) args[0]);
            }
            else if (iPSDEAction.getName().equals("PMEvaluation")) {
                return taskestimateService.pMEvaluation((TaskEstimate) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return taskestimateService.save((TaskEstimate) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return taskestimateService.create((TaskEstimate) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return taskestimateService.update((TaskEstimate) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                return taskestimateService.get((Long) args[0]);
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return taskestimateService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return taskestimateService.sysGet((Long) args[0]);
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

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.TaskEstimateServiceImpl.*(..))")
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
        else if (action.equals("pMEvaluation")) {
            return aroundAction("PMEvaluation", point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("searchActionMonth")) {
            return aroundDataSet("ActionMonth", point);
        }
        else if (action.equals("searchActionYear")) {
            return aroundDataSet("ActionYear", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchDefaults")) {
            return aroundDataSet("DEFAULT1", point);
        }
        else if (action.equals("searchProjectActionMonth")) {
            return aroundDataSet("ProjectActionMonth", point);
        }
        else if (action.equals("searchProjectActionYear")) {
            return aroundDataSet("ProjectActionYear", point);
        }
        else if (action.equals("searchProjectTaskEstimate")) {
            return aroundDataSet("ProjectTaskEstimate", point);
        }
        return point.proceed();
    }

}
