package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.TaskEstimate;
import cn.ibizlab.pms.core.zentao.service.ITaskEstimateService;
import cn.ibizlab.pms.core.zentao.filter.TaskEstimateSearchContext;
import  cn.ibizlab.pms.util.filter.QueryWrapperContext;
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



@Aspect
@Order(100)
@Component("TaskEstimateRuntime")
@Slf4j
public class TaskEstimateRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    ITaskEstimateService taskestimateService;

    @Autowired
    DELogicExecutor logicExecutor;

    @Override
    protected IEntityBase getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntityBase) {
            return (IEntityBase) o;
        } else {
            return taskestimateService.sysGet((Long) o);
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
    public IEntityBase createEntity() {
        return new TaskEstimate();
    }

    @Override
    protected IService getService() {
        return this.taskestimateService;
    }

    @Override
    public TaskEstimateSearchContext createSearchContext() {
        return new TaskEstimateSearchContext();
    }

    @Override
    public boolean existsData(ISearchContextBase iSearchContextBase) {
        Page<TaskEstimate> domains = taskestimateService.searchDefault((TaskEstimateSearchContext) iSearchContextBase);
        if (domains.getSize() == 0)
            return false;
        return true;
    }

    @Override
    public Page<TaskEstimate> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        TaskEstimateSearchContext searchContext = (TaskEstimateSearchContext) iSearchContextBase;
        if (iPSDEDataSet.getName().equals("ActionMonth"))
            return taskestimateService.searchActionMonth(searchContext);    
        if (iPSDEDataSet.getName().equals("ActionYear"))
            return taskestimateService.searchActionYear(searchContext);    
        if (iPSDEDataSet.getName().equals("DEFAULT"))
            return taskestimateService.searchDefault(searchContext);    
        if (iPSDEDataSet.getName().equals("DEFAULT1"))
            return taskestimateService.searchDefaults(searchContext);    
        if (iPSDEDataSet.getName().equals("ProjectActionMonth"))
            return taskestimateService.searchProjectActionMonth(searchContext);    
        if (iPSDEDataSet.getName().equals("ProjectActionYear"))
            return taskestimateService.searchProjectActionYear(searchContext);    
        if (iPSDEDataSet.getName().equals("ProjectTaskEstimate"))
            return taskestimateService.searchProjectTaskEstimate(searchContext);    
        return null;
    }

    @Override
    public List<TaskEstimate> selectDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        TaskEstimateSearchContext searchContext = (TaskEstimateSearchContext) iSearchContextBase;
        if (iPSDataQuery.getName().equals("ActionMonth"))
            return taskestimateService.selectQueryByActionMonth(searchContext);
        if (iPSDataQuery.getName().equals("ActionYear"))
            return taskestimateService.selectQueryByActionYear(searchContext);
        if (iPSDataQuery.getName().equals("DEFAULT"))
            return taskestimateService.selectQueryByDefault(searchContext);
        if (iPSDataQuery.getName().equals("DEFAULT1"))
            return taskestimateService.selectQueryByDefaults(searchContext);
        if (iPSDataQuery.getName().equals("ProjectActionMonth"))
            return taskestimateService.selectQueryByProjectActionMonth(searchContext);
        if (iPSDataQuery.getName().equals("ProjectActionYear"))
            return taskestimateService.selectQueryByProjectActionYear(searchContext);
        if (iPSDataQuery.getName().equals("ProjectTaskEstimate"))
            return taskestimateService.selectQueryByProjectTaskEstimate(searchContext);
        if (iPSDataQuery.getName().equals("VIEW"))
            return taskestimateService.selectQueryByView(searchContext);
        return null;
    }

    @Override
    public TaskEstimate selectOne(ISearchContextBase iSearchContextBase) {
        //单条数据查询，多条数数据时 返回第一条
        TaskEstimateSearchContext searchContext = (TaskEstimateSearchContext) iSearchContextBase;
        searchContext.setSize(1);
        List<TaskEstimate> domains = taskestimateService.select(searchContext);
        if (domains.size() == 0)
            return null;
        return domains.get(0);
    }

    @Override
    public List<TaskEstimate> select(ISearchContextBase iSearchContextBase) {
        TaskEstimateSearchContext searchContext = (TaskEstimateSearchContext) iSearchContextBase;
        return taskestimateService.select(searchContext);
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
                if(args[0] instanceof TaskEstimate){
                    TaskEstimate arg = (TaskEstimate) args[0] ;
                    arg = taskestimateService.get(arg.getId()) ;
                    return arg;
                }else{
                    return taskestimateService.get((Long) args[0]);
                }
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
                if(args[0] instanceof TaskEstimate){
                    TaskEstimate arg = (TaskEstimate) args[0] ;
                    arg = taskestimateService.get(arg.getId()) ;
                    return arg;
                }else{
                    return taskestimateService.get((Long) args[0]);
                }
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return taskestimateService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return taskestimateService.sysGet((Long) args[0]);
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
        TaskEstimate entity = null;
        String action = iPSDEAction.getName();
        if (arg0 instanceof TaskEstimate) {
            entity = (TaskEstimate) arg0;
        } else {
            if (arg0 != null && "get".equalsIgnoreCase(action)) {
                entity = taskestimateService.sysGet((Long) arg0);
            } else if ("remove".equalsIgnoreCase(action)) {
                entity = new TaskEstimate();
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

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.TaskEstimateServiceImpl.*(..))")
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
