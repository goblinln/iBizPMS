package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.ProductPlan;
import cn.ibizlab.pms.core.zentao.service.IProductPlanService;
import cn.ibizlab.pms.core.zentao.filter.ProductPlanSearchContext;
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
@Component("ProductPlanRuntime")
public class ProductPlanRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IProductPlanService productplanService;

    @Override
    protected Object getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntity) {
            return o;
        } else {
            return productplanService.sysGet(String.valueOf(o));
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/ProductPlan.json";
    }

    @Override
    public String getName() {
        return "ZT_PRODUCTPLAN";
    }

    @Override
    public Object createEntity() {
        return new ProductPlan();
    }

    @Override
    protected IService getService() {
        return this.productplanService;
    }

    @Override
    protected QueryWrapperContext createSearchContext() {
        return new ProductPlanSearchContext();
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return productplanService.create((ProductPlan) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return productplanService.update((ProductPlan) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return productplanService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                return productplanService.get((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return productplanService.getDraft((ProductPlan) args[0]);
            }
            else if (iPSDEAction.getName().equals("BatchUnlinkBug")) {
                return productplanService.batchUnlinkBug((ProductPlan) args[0]);
            }
            else if (iPSDEAction.getName().equals("BatchUnlinkStory")) {
                return productplanService.batchUnlinkStory((ProductPlan) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return productplanService.checkKey((ProductPlan) args[0]);
            }
            else if (iPSDEAction.getName().equals("EeActivePlan")) {
                return productplanService.eeActivePlan((ProductPlan) args[0]);
            }
            else if (iPSDEAction.getName().equals("EeCancelPlan")) {
                return productplanService.eeCancelPlan((ProductPlan) args[0]);
            }
            else if (iPSDEAction.getName().equals("EeClosePlan")) {
                return productplanService.eeClosePlan((ProductPlan) args[0]);
            }
            else if (iPSDEAction.getName().equals("EeFinishPlan")) {
                return productplanService.eeFinishPlan((ProductPlan) args[0]);
            }
            else if (iPSDEAction.getName().equals("EePausePlan")) {
                return productplanService.eePausePlan((ProductPlan) args[0]);
            }
            else if (iPSDEAction.getName().equals("EeRestartPlan")) {
                return productplanService.eeRestartPlan((ProductPlan) args[0]);
            }
            else if (iPSDEAction.getName().equals("EeStartPlan")) {
                return productplanService.eeStartPlan((ProductPlan) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetOldPlanName")) {
                return productplanService.getOldPlanName((ProductPlan) args[0]);
            }
            else if (iPSDEAction.getName().equals("ImportPlanTemplet")) {
                return productplanService.importPlanTemplet((ProductPlan) args[0]);
            }
            else if (iPSDEAction.getName().equals("LinkBug")) {
                return productplanService.linkBug((ProductPlan) args[0]);
            }
            else if (iPSDEAction.getName().equals("LinkStory")) {
                return productplanService.linkStory((ProductPlan) args[0]);
            }
            else if (iPSDEAction.getName().equals("LinkTask")) {
                return productplanService.linkTask((ProductPlan) args[0]);
            }
            else if (iPSDEAction.getName().equals("MobProductPlanCounter")) {
                return productplanService.mobProductPlanCounter((ProductPlan) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return productplanService.save((ProductPlan) args[0]);
            }
            else if (iPSDEAction.getName().equals("UnlinkBug")) {
                return productplanService.unlinkBug((ProductPlan) args[0]);
            }
            else if (iPSDEAction.getName().equals("UnlinkStory")) {
                return productplanService.unlinkStory((ProductPlan) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return productplanService.create((ProductPlan) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return productplanService.update((ProductPlan) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                return productplanService.get((Long) args[0]);
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return productplanService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return productplanService.sysGet((Long) args[0]);
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

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.ProductPlanServiceImpl.*(..))")
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
        else if (action.equals("batchUnlinkBug")) {
            return aroundAction("BatchUnlinkBug", point);
        }
        else if (action.equals("batchUnlinkStory")) {
            return aroundAction("BatchUnlinkStory", point);
        }
        else if (action.equals("checkKey")) {
            return aroundAction("CheckKey", point);
        }
        else if (action.equals("eeActivePlan")) {
            return aroundAction("EeActivePlan", point);
        }
        else if (action.equals("eeCancelPlan")) {
            return aroundAction("EeCancelPlan", point);
        }
        else if (action.equals("eeClosePlan")) {
            return aroundAction("EeClosePlan", point);
        }
        else if (action.equals("eeFinishPlan")) {
            return aroundAction("EeFinishPlan", point);
        }
        else if (action.equals("eePausePlan")) {
            return aroundAction("EePausePlan", point);
        }
        else if (action.equals("eeRestartPlan")) {
            return aroundAction("EeRestartPlan", point);
        }
        else if (action.equals("eeStartPlan")) {
            return aroundAction("EeStartPlan", point);
        }
        else if (action.equals("getOldPlanName")) {
            return aroundAction("GetOldPlanName", point);
        }
        else if (action.equals("importPlanTemplet")) {
            return aroundAction("ImportPlanTemplet", point);
        }
        else if (action.equals("linkBug")) {
            return aroundAction("LinkBug", point);
        }
        else if (action.equals("linkStory")) {
            return aroundAction("LinkStory", point);
        }
        else if (action.equals("linkTask")) {
            return aroundAction("LinkTask", point);
        }
        else if (action.equals("mobProductPlanCounter")) {
            return aroundAction("MobProductPlanCounter", point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("unlinkBug")) {
            return aroundAction("UnlinkBug", point);
        }
        else if (action.equals("unlinkStory")) {
            return aroundAction("UnlinkStory", point);
        }
        else if (action.equals("searchChildPlan")) {
            return aroundDataSet("ChildPlan", point);
        }
        else if (action.equals("searchCurProductPlan")) {
            return aroundDataSet("CurProductPlan", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchDefaultParent")) {
            return aroundDataSet("DefaultParent", point);
        }
        else if (action.equals("searchPlanCodeList")) {
            return aroundDataSet("PlanCodeList", point);
        }
        else if (action.equals("searchPlanTasks")) {
            return aroundDataSet("PlanTasks", point);
        }
        else if (action.equals("searchProjectApp")) {
            return aroundDataSet("ProjectApp", point);
        }
        else if (action.equals("searchProjectPlan")) {
            return aroundDataSet("ProjectPlan", point);
        }
        else if (action.equals("searchRootPlan")) {
            return aroundDataSet("RootPlan", point);
        }
        else if (action.equals("searchTaskPlan")) {
            return aroundDataSet("TaskPlan", point);
        }
        return point.proceed();
    }

}
