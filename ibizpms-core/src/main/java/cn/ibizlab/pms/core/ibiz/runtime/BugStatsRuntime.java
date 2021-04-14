package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.ibiz.domain.BugStats;
import cn.ibizlab.pms.core.ibiz.service.IBugStatsService;
import cn.ibizlab.pms.core.ibiz.filter.BugStatsSearchContext;
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
@Component("BugStatsRuntime")
public class BugStatsRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IBugStatsService bugstatsService;

    @Override
    protected Object getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntity) {
            return o;
        } else {
            return bugstatsService.sysGet(String.valueOf(o));
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/ibiz/PSDATAENTITIES/BugStats.json";
    }

    @Override
    public String getName() {
        return "IBZ_BUGSTATS";
    }

    @Override
    public Object createEntity() {
        return new BugStats();
    }

    @Override
    protected IService getService() {
        return this.bugstatsService;
    }

    @Override
    protected QueryWrapperContext createSearchContext() {
        return new BugStatsSearchContext();
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return bugstatsService.create((BugStats) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return bugstatsService.update((BugStats) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return bugstatsService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                return bugstatsService.get((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return bugstatsService.getDraft((BugStats) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return bugstatsService.checkKey((BugStats) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return bugstatsService.save((BugStats) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return bugstatsService.create((BugStats) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return bugstatsService.update((BugStats) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                return bugstatsService.get((Long) args[0]);
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return bugstatsService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return bugstatsService.sysGet((Long) args[0]);
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

    @Around("execution(* cn.ibizlab.pms.core.ibiz.service.impl.BugStatsServiceImpl.*(..))")
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
        else if (action.equals("searchBugCountInResolution")) {
            return aroundDataSet("BugCountInResolution", point);
        }
        else if (action.equals("searchBugResolvedBy")) {
            return aroundDataSet("BugResolvedBy", point);
        }
        else if (action.equals("searchBugResolvedGird")) {
            return aroundDataSet("BugResolvedGird", point);
        }
        else if (action.equals("searchBugassignedTo")) {
            return aroundDataSet("BugassignedTo", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchProductBugResolutionStats")) {
            return aroundDataSet("ProductBugResolutionStats", point);
        }
        else if (action.equals("searchProductBugStatusSum")) {
            return aroundDataSet("ProductBugStatusSum", point);
        }
        else if (action.equals("searchProductCreateBug")) {
            return aroundDataSet("ProductCreateBug", point);
        }
        else if (action.equals("searchProjectBugStatusCount")) {
            return aroundDataSet("ProjectBugStatusCount", point);
        }
        return point.proceed();
    }

}
