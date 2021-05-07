package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.ibiz.domain.ProjectStats;
import cn.ibizlab.pms.core.ibiz.service.IProjectStatsService;
import cn.ibizlab.pms.core.ibiz.filter.ProjectStatsSearchContext;
import cn.ibizlab.pms.util.filter.QueryWrapperContext;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import com.baomidou.mybatisplus.extension.service.IService;
import lombok.extern.slf4j.Slf4j;
import net.ibizsys.model.dataentity.IPSDataEntity;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import net.ibizsys.model.dataentity.der.IPSDER1N;
import net.ibizsys.model.dataentity.der.IPSDERBase;
import net.ibizsys.model.dataentity.ds.IPSDEDataQuery;
import net.ibizsys.model.dataentity.ds.IPSDEDataSet;
import net.ibizsys.model.dataentity.logic.IPSDELogic;
import net.ibizsys.model.dataentity.wf.IPSDEWF;
import net.ibizsys.runtime.IDynaInstRuntime;
import cn.ibizlab.pms.util.domain.EntityBase;
import cn.ibizlab.pms.util.errors.BadRequestAlertException;
import cn.ibizlab.pms.util.helper.DELogicExecutor;
import net.ibizsys.runtime.dataentity.DataEntityRuntimeException;
import net.ibizsys.runtime.util.IEntityBase;
import net.ibizsys.runtime.util.ISearchContextBase;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.beans.factory.annotation.Autowired;
import net.ibizsys.runtime.dataentity.action.DEActions;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import java.io.Serializable;
import java.util.List;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.alibaba.fastjson.JSONObject;


@Aspect
@Order(100)
@Component("ProjectStatsRuntime")
@Slf4j
public class ProjectStatsRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IProjectStatsService projectstatsService;

    @Autowired
    DELogicExecutor logicExecutor;

    @Override
    protected IEntityBase getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntityBase) {
            return (IEntityBase) o;
        } else {
            return projectstatsService.sysGet((Long) o);
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
    public IEntityBase createEntity() {
        return new ProjectStats();
    }

    @Override
    protected IService getService() {
        return this.projectstatsService;
    }

    @Override
    public ProjectStatsSearchContext createSearchContext() {
        return new ProjectStatsSearchContext();
    }

    @Override
    public Object serializeEntity(IEntityBase iEntityBase) {
        try {
            return MAPPER.writeValueAsString(iEntityBase);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public Object serializeEntities(IEntityBase[] list) {
        try {
            return MAPPER.writeValueAsString(list);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public IEntityBase deserializeEntity(Object objData) {
        try {
            return MAPPER.readValue(String.valueOf(objData),ProjectStats.class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public IEntityBase[] deserializeEntities(Object objData) {
        try {
            return MAPPER.readValue(String.valueOf(objData),ProjectStats[].class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public Page<ProjectStats> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        ProjectStatsSearchContext searchContext = (ProjectStatsSearchContext) iSearchContextBase;
        if (iPSDEDataSet.getName().equals("DEFAULT"))
            return projectstatsService.searchDefault(searchContext);    
        if (iPSDEDataSet.getName().equals("NOOpenProduct"))
            return projectstatsService.searchNoOpenProduct(searchContext);    
        if (iPSDEDataSet.getName().equals("ProjectBugType"))
            return projectstatsService.searchProjectBugType(searchContext);    
        if (iPSDEDataSet.getName().equals("ProjectInputStats"))
            return projectstatsService.searchProjectInputStats(searchContext);    
        if (iPSDEDataSet.getName().equals("ProjectProgress"))
            return projectstatsService.searchProjectProgress(searchContext);    
        if (iPSDEDataSet.getName().equals("ProjectQuality"))
            return projectstatsService.searchProjectQuality(searchContext);    
        if (iPSDEDataSet.getName().equals("ProjectStoryStageStats"))
            return projectstatsService.searchProjectStoryStageStats(searchContext);    
        if (iPSDEDataSet.getName().equals("ProjectStoryStatusStats"))
            return projectstatsService.searchProjectStoryStatusStats(searchContext);    
        if (iPSDEDataSet.getName().equals("ProjectTaskCountByTaskStatus"))
            return projectstatsService.searchProjectTaskCountByTaskStatus(searchContext);    
        if (iPSDEDataSet.getName().equals("ProjectTaskCountByType"))
            return projectstatsService.searchProjectTaskCountByType(searchContext);    
        if (iPSDEDataSet.getName().equals("TASKTIME"))
            return projectstatsService.searchTaskTime(searchContext);    
        return null;
    }

    @Override
    public List<ProjectStats> selectDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        ProjectStatsSearchContext searchContext = (ProjectStatsSearchContext) iSearchContextBase;
        if (iPSDataQuery.getName().equals("DEFAULT"))
            return projectstatsService.selectDefault(searchContext);
        if (iPSDataQuery.getName().equals("NOOpenProduct"))
            return projectstatsService.selectNoOpenProduct(searchContext);
        if (iPSDataQuery.getName().equals("ProjectBugType"))
            return projectstatsService.selectProjectBugType(searchContext);
        if (iPSDataQuery.getName().equals("ProjectInputStats"))
            return projectstatsService.selectProjectInputStats(searchContext);
        if (iPSDataQuery.getName().equals("ProjectProgress"))
            return projectstatsService.selectProjectProgress(searchContext);
        if (iPSDataQuery.getName().equals("ProjectQuality"))
            return projectstatsService.selectProjectQuality(searchContext);
        if (iPSDataQuery.getName().equals("ProjectStoryStageStats"))
            return projectstatsService.selectProjectStoryStageStats(searchContext);
        if (iPSDataQuery.getName().equals("ProjectStoryStatusStats"))
            return projectstatsService.selectProjectStoryStatusStats(searchContext);
        if (iPSDataQuery.getName().equals("ProjectTaskCountByTaskStatus"))
            return projectstatsService.selectProjectTaskCountByTaskStatus(searchContext);
        if (iPSDataQuery.getName().equals("ProjectTaskCountByType"))
            return projectstatsService.selectProjectTaskCountByType(searchContext);
        if (iPSDataQuery.getName().equals("TASKTIME"))
            return projectstatsService.selectTaskTime(searchContext);
        if (iPSDataQuery.getName().equals("VIEW"))
            return projectstatsService.selectView(searchContext);
        return null;
    }

    @Override
    public List<ProjectStats> select(ISearchContextBase iSearchContextBase) {
        ProjectStatsSearchContext searchContext = (ProjectStatsSearchContext) iSearchContextBase;
        return projectstatsService.select(searchContext);
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
                if(args[0] instanceof ProjectStats){
                    ProjectStats arg = (ProjectStats) args[0] ;
                    CachedBeanCopier.copy(projectstatsService.get(arg.getId()), arg);
                    return arg;
                }else{
                    return projectstatsService.get((Long) args[0]);
                }
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
                if(args[0] instanceof ProjectStats){
                    ProjectStats arg = (ProjectStats) args[0] ;
                    CachedBeanCopier.copy(projectstatsService.get(arg.getId()), arg);
                    return arg;
                }else{
                    return projectstatsService.get((Long) args[0]);
                }
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return projectstatsService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return projectstatsService.sysGet((Long) args[0]);
            }  else if (strActionName.equals(DEActions.SYSUPDATE)) {
                
            }             
        }
        
        return null;
    }


    /**
     * 执行实体处理逻辑
     * @param arg0 实体
     * @param iPSDEAction 行为
     * @param iPSDELogic  逻辑
     * @param iDynaInstRuntime 动态实例
     * @param joinPoint
     * @throws Throwable
     */
    @Override
    protected void onExecuteDELogic(Object arg0, IPSDEAction iPSDEAction, IPSDELogic iPSDELogic, IDynaInstRuntime iDynaInstRuntime, Object joinPoint) throws Throwable {
        if (arg0 instanceof EntityBase)
            logicExecutor.executeLogic((EntityBase) arg0, iPSDEAction, iPSDELogic, iDynaInstRuntime);
        else
            throw new BadRequestAlertException(String.format("执行实体逻辑异常，不支持参数[%s]", arg0.toString()), "", "");
    }

    /**
     * 执行实体附加逻辑
     * @param arg0 实体
     * @param iPSDEAction 行为
     * @param strAttachMode 附加模式
     * @param iDynaInstRuntime 动态实例
     * @param joinPoint
     * @throws Throwable
     */
    @Override
    protected void onExecuteActionLogics(Object arg0, IPSDEAction iPSDEAction, String strAttachMode, IDynaInstRuntime iDynaInstRuntime, Object joinPoint) throws Throwable {
        ProjectStats entity = null;
        String action = iPSDEAction.getName();
        if (arg0 instanceof ProjectStats) {
            entity = (ProjectStats) arg0;
        } else {
            if (arg0 != null && "get".equalsIgnoreCase(action)) {
                entity = projectstatsService.sysGet((Long) arg0);
            } else if ("remove".equalsIgnoreCase(action)) {
                entity = new ProjectStats();
                entity.setId((Long) arg0);
            }
        }
        if (entity != null) {
            logicExecutor.executeAttachLogic(entity, iPSDEAction, strAttachMode, iDynaInstRuntime);
        } else
            throw new BadRequestAlertException(String.format("执行实体行为附加逻辑[%s:%s]发生异常，无法获取传入参数", action, strAttachMode), "", "onExecuteActionLogics");
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
    @Transactional
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
