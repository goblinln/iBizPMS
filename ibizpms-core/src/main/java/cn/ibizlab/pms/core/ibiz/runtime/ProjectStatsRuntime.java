package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.ibiz.domain.ProjectStats;
import cn.ibizlab.pms.core.ibiz.service.IProjectStatsService;
import cn.ibizlab.pms.core.ibiz.filter.ProjectStatsSearchContext;
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
@Component("ProjectStatsRuntime")
public class ProjectStatsRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IProjectStatsService projectstatsService;

    @Override
    protected Object getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntity) {
            return o;
        } else {
            return projectstatsService.sysGet((Long)o);
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/ibiz/PSDATAENTITIES/ProjectStats.json";
    }

    @Override
    public String getName() {
        return "IBZ_PROJECTSTATS";
    }

    @Override
    public Object createEntity() {
        return new ProjectStats();
    }

    @Override
    protected IService getService() {
        return this.projectstatsService;
    }

    @Override
    protected QueryWrapperContext createSearchContext() {
        return new ProjectStatsSearchContext();
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return projectstatsService.create((ProjectStats) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return projectstatsService.update((ProjectStats) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return projectstatsService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                return projectstatsService.get((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return projectstatsService.getDraft((ProjectStats) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return projectstatsService.checkKey((ProjectStats) args[0]);
            }
            else if (iPSDEAction.getName().equals("ProjectQualitySum")) {
                return projectstatsService.projectQualitySum((ProjectStats) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return projectstatsService.save((ProjectStats) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return projectstatsService.create((ProjectStats) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return projectstatsService.update((ProjectStats) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                return projectstatsService.get((Long) args[0]);
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return projectstatsService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return projectstatsService.sysGet((Long) args[0]);
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

    @Around("execution(* cn.ibizlab.pms.core.ibiz.service.impl.ProjectStatsServiceImpl.*(..))")
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
        else if (action.equals("projectQualitySum")) {
            return aroundAction("ProjectQualitySum", point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchNoOpenProduct")) {
            return aroundDataSet("NOOpenProduct", point);
        }
        else if (action.equals("searchProjectBugType")) {
            return aroundDataSet("ProjectBugType", point);
        }
        else if (action.equals("searchProjectInputStats")) {
            return aroundDataSet("ProjectInputStats", point);
        }
        else if (action.equals("searchProjectProgress")) {
            return aroundDataSet("ProjectProgress", point);
        }
        else if (action.equals("searchProjectQuality")) {
            return aroundDataSet("ProjectQuality", point);
        }
        else if (action.equals("searchProjectStoryStageStats")) {
            return aroundDataSet("ProjectStoryStageStats", point);
        }
        else if (action.equals("searchProjectStoryStatusStats")) {
            return aroundDataSet("ProjectStoryStatusStats", point);
        }
        else if (action.equals("searchProjectTaskCountByTaskStatus")) {
            return aroundDataSet("ProjectTaskCountByTaskStatus", point);
        }
        else if (action.equals("searchProjectTaskCountByType")) {
            return aroundDataSet("ProjectTaskCountByType", point);
        }
        else if (action.equals("searchTaskTime")) {
            return aroundDataSet("TASKTIME", point);
        }
        return point.proceed();
    }

}
