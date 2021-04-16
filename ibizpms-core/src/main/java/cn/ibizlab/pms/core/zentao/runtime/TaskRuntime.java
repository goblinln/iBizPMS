package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Task;
import cn.ibizlab.pms.core.zentao.service.ITaskService;
import cn.ibizlab.pms.core.zentao.filter.TaskSearchContext;
import  cn.ibizlab.pms.util.filter.QueryWrapperContext;
import com.baomidou.mybatisplus.extension.service.IService;
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
import net.ibizsys.runtime.dataentity.DEActions;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.ObjectUtils;
import java.io.Serializable;
import java.util.List;



@Aspect
@Order(100)
@Component("TaskRuntime")
public class TaskRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    ITaskService taskService;

    @Override
    protected IEntityBase getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntity) {
            return o;
        } else {
            return (IEntityBase) taskService.sysGet((Long) o);
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/Task.json";
    }

    @Override
    public String getName() {
        return "ZT_TASK";
    }

    @Override
    public IEntityBase createEntity() {
        return new Task();
    }

    @Override
    protected IService getService() {
        return this.taskService;
    }

    @Override
    public TaskSearchContext createSearchContext() {
        return new TaskSearchContext();
    }

    @Override
    public void setSearchCondition(ISearchContextBase iSearchContextBase, IPSDEField iPSDEField, String strCondition, Object objValue) {
        //设置查询条件 net.ibizsys.runtime.util.Conditions
    }

    @Override
    public boolean existsData(ISearchContextBase iSearchContextBase) {
        //判断数据是否存在
        return false;
    }

    @Override
    public Page<Task> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        //查询数据集合
        return null;
    }

    @Override
    public Page<Task> searchDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        //暂未实现
        return null;
    }

    @Override
    public Task selectOne(ISearchContextBase iSearchContextBase) {
        //单条数据查询，多条数数据时 返回第一条
        return null;
    }

    @Override
    public List<Task> select(ISearchContextBase iSearchContextBase) {
        //list
        return null;
    }

    @Override
    protected void fillEntityFullInfo(IEntityBase entityBase, String strActionName, IPSDEAction iPSDEAction, IPSDER1N iPSDER1N, IPSDataEntity iPSDataEntity, ProceedingJoinPoint joinPoint) throws Throwable {
        Object objPickupValue = this.getFieldValue(entityBase, iPSDER1N.getPSPickupDEField());
        if (ObjectUtils.isEmpty(objPickupValue) || NumberUtils.toLong(String.valueOf(objPickupValue), 0L) == 0L)
            return;
        super.fillEntityFullInfo(entityBase, strActionName, iPSDEAction, iPSDER1N, iPSDataEntity, joinPoint);
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return taskService.create((Task) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return taskService.update((Task) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return taskService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                if(args[0] instanceof Task){
                    Task arg = (Task) args[0] ;
                    arg = taskService.get(arg.getId()) ;
                    return arg;
                }else{
                    return taskService.get((Long) args[0]);
                }
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return taskService.getDraft((Task) args[0]);
            }
            else if (iPSDEAction.getName().equals("Activate")) {
                return taskService.activate((Task) args[0]);
            }
            else if (iPSDEAction.getName().equals("AssignTo")) {
                return taskService.assignTo((Task) args[0]);
            }
            else if (iPSDEAction.getName().equals("Cancel")) {
                return taskService.cancel((Task) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return taskService.checkKey((Task) args[0]);
            }
            else if (iPSDEAction.getName().equals("Close")) {
                return taskService.close((Task) args[0]);
            }
            else if (iPSDEAction.getName().equals("ConfirmStoryChange")) {
                return taskService.confirmStoryChange((Task) args[0]);
            }
            else if (iPSDEAction.getName().equals("CreateCycleTasks")) {
                return taskService.createCycleTasks((Task) args[0]);
            }
            else if (iPSDEAction.getName().equals("DeleteEstimate")) {
                return taskService.deleteEstimate((Task) args[0]);
            }
            else if (iPSDEAction.getName().equals("EditEstimate")) {
                return taskService.editEstimate((Task) args[0]);
            }
            else if (iPSDEAction.getName().equals("Finish")) {
                return taskService.finish((Task) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetNextTeamUserFinish")) {
                return taskService.getNextTeamUser((Task) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetTeamUserLeftActivity")) {
                return taskService.getTeamUserLeftActivity((Task) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetTeamUserLeftStart")) {
                return taskService.getTeamUserLeftStart((Task) args[0]);
            }
            else if (iPSDEAction.getName().equals("getUsernames")) {
                return taskService.getUsernames((Task) args[0]);
            }
            else if (iPSDEAction.getName().equals("LinkPlan")) {
                return taskService.linkPlan((Task) args[0]);
            }
            else if (iPSDEAction.getName().equals("OtherUpdate")) {
                return taskService.otherUpdate((Task) args[0]);
            }
            else if (iPSDEAction.getName().equals("Pause")) {
                return taskService.pause((Task) args[0]);
            }
            else if (iPSDEAction.getName().equals("RecordEstimate")) {
                return taskService.recordEstimate((Task) args[0]);
            }
            else if (iPSDEAction.getName().equals("Restart")) {
                return taskService.restart((Task) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return taskService.save((Task) args[0]);
            }
            else if (iPSDEAction.getName().equals("sendMessage")) {
                return taskService.sendMessage((Task) args[0]);
            }
            else if (iPSDEAction.getName().equals("sendMsgPreProcess")) {
                return taskService.sendMsgPreProcess((Task) args[0]);
            }
            else if (iPSDEAction.getName().equals("Start")) {
                return taskService.start((Task) args[0]);
            }
            else if (iPSDEAction.getName().equals("TaskFavorites")) {
                return taskService.taskFavorites((Task) args[0]);
            }
            else if (iPSDEAction.getName().equals("taskForward")) {
                return taskService.taskForward((Task) args[0]);
            }
            else if (iPSDEAction.getName().equals("TaskNFavorites")) {
                return taskService.taskNFavorites((Task) args[0]);
            }
            else if (iPSDEAction.getName().equals("updateStoryVersion")) {
                return taskService.updateStoryVersion((Task) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return taskService.create((Task) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return taskService.update((Task) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                if(args[0] instanceof Task){
                    Task arg = (Task) args[0] ;
                    arg = taskService.get(arg.getId()) ;
                    return arg;
                }else{
                    return taskService.get((Long) args[0]);
                }
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return taskService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return taskService.sysGet((Long) args[0]);
            }  else if (strActionName.equals(DEActions.SYSUPDATE)) {
                
            }         }
        
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
    protected void onExecuteDELogic(Object arg0, IPSDEAction iPSDEAction, IPSDELogic iPSDELogic, IDynaInstRuntime iDynaInstRuntime, ProceedingJoinPoint joinPoint) throws Throwable {
        if (arg0 instanceof EntityBase)
            DELogicExecutor.getInstance().executeLogic((EntityBase) arg0, iPSDEAction, iPSDELogic, iDynaInstRuntime);
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
    protected void onExecuteActionLogics(Object arg0, IPSDEAction iPSDEAction, String strAttachMode, IDynaInstRuntime iDynaInstRuntime, ProceedingJoinPoint joinPoint) throws Throwable {
        Task entity = null;
        String action = iPSDEAction.getName();
        if (arg0 instanceof Task) {
            entity = (Task) arg0;
        } else {
            if (arg0 != null && "get".equalsIgnoreCase(action)) {
                entity = taskService.sysGet((Long) arg0);
            } else if ("remove".equalsIgnoreCase(action)) {
                entity = new Task();
                entity.setId((Long) arg0);
            }
        }
        if (entity != null) {
            DELogicExecutor.getInstance().executeAttachLogic(entity, iPSDEAction, strAttachMode, iDynaInstRuntime);
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

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.TaskServiceImpl.*(..))")
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
        else if (action.equals("activate")) {
            return aroundAction("Activate", point);
        }
        else if (action.equals("assignTo")) {
            return aroundAction("AssignTo", point);
        }
        else if (action.equals("cancel")) {
            return aroundAction("Cancel", point);
        }
        else if (action.equals("checkKey")) {
            return aroundAction("CheckKey", point);
        }
        else if (action.equals("close")) {
            return aroundAction("Close", point);
        }
        else if (action.equals("confirmStoryChange")) {
            return aroundAction("ConfirmStoryChange", point);
        }
        else if (action.equals("createCycleTasks")) {
            return aroundAction("CreateCycleTasks", point);
        }
        else if (action.equals("deleteEstimate")) {
            return aroundAction("DeleteEstimate", point);
        }
        else if (action.equals("editEstimate")) {
            return aroundAction("EditEstimate", point);
        }
        else if (action.equals("finish")) {
            return aroundAction("Finish", point);
        }
        else if (action.equals("getNextTeamUser")) {
            return aroundAction("GetNextTeamUserFinish", point);
        }
        else if (action.equals("getTeamUserLeftActivity")) {
            return aroundAction("GetTeamUserLeftActivity", point);
        }
        else if (action.equals("getTeamUserLeftStart")) {
            return aroundAction("GetTeamUserLeftStart", point);
        }
        else if (action.equals("getUsernames")) {
            return aroundAction("getUsernames", point);
        }
        else if (action.equals("linkPlan")) {
            return aroundAction("LinkPlan", point);
        }
        else if (action.equals("otherUpdate")) {
            return aroundAction("OtherUpdate", point);
        }
        else if (action.equals("pause")) {
            return aroundAction("Pause", point);
        }
        else if (action.equals("recordEstimate")) {
            return aroundAction("RecordEstimate", point);
        }
        else if (action.equals("restart")) {
            return aroundAction("Restart", point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("sendMessage")) {
            return aroundAction("sendMessage", point);
        }
        else if (action.equals("sendMsgPreProcess")) {
            return aroundAction("sendMsgPreProcess", point);
        }
        else if (action.equals("start")) {
            return aroundAction("Start", point);
        }
        else if (action.equals("taskFavorites")) {
            return aroundAction("TaskFavorites", point);
        }
        else if (action.equals("taskForward")) {
            return aroundAction("taskForward", point);
        }
        else if (action.equals("taskNFavorites")) {
            return aroundAction("TaskNFavorites", point);
        }
        else if (action.equals("updateStoryVersion")) {
            return aroundAction("updateStoryVersion", point);
        }
        else if (action.equals("searchAssignedToMyTask")) {
            return aroundDataSet("AssignedToMyTask", point);
        }
        else if (action.equals("searchAssignedToMyTaskPc")) {
            return aroundDataSet("AssignedToMyTaskPc", point);
        }
        else if (action.equals("searchBugTask")) {
            return aroundDataSet("BugTask", point);
        }
        else if (action.equals("searchByModule")) {
            return aroundDataSet("ByModule", point);
        }
        else if (action.equals("searchChildDefault")) {
            return aroundDataSet("ChildDefault", point);
        }
        else if (action.equals("searchChildTask")) {
            return aroundDataSet("ChildTask", point);
        }
        else if (action.equals("searchChildTaskTree")) {
            return aroundDataSet("ChildTaskTree", point);
        }
        else if (action.equals("searchCurFinishTask")) {
            return aroundDataSet("CurFinishTask", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchDefaultRow")) {
            return aroundDataSet("DefaultRow", point);
        }
        else if (action.equals("searchESBulk")) {
            return aroundDataSet("ESBulk", point);
        }
        else if (action.equals("searchMyAgentTask")) {
            return aroundDataSet("MyAgentTask", point);
        }
        else if (action.equals("searchMyAllTask")) {
            return aroundDataSet("MyAllTask", point);
        }
        else if (action.equals("searchMyCompleteTask")) {
            return aroundDataSet("MyCompleteTask", point);
        }
        else if (action.equals("searchMyCompleteTaskMobDaily")) {
            return aroundDataSet("MyCompleteTaskMobDaily", point);
        }
        else if (action.equals("searchMyCompleteTaskMobMonthly")) {
            return aroundDataSet("MyCompleteTaskMobMonthly", point);
        }
        else if (action.equals("searchMyCompleteTaskMonthlyZS")) {
            return aroundDataSet("MyCompleteTaskMonthlyZS", point);
        }
        else if (action.equals("searchMyCompleteTaskZS")) {
            return aroundDataSet("MyCompleteTaskZS", point);
        }
        else if (action.equals("searchMyFavorites")) {
            return aroundDataSet("MyFavorites", point);
        }
        else if (action.equals("searchMyPlansTaskMobMonthly")) {
            return aroundDataSet("MyPlansTaskMobMonthly", point);
        }
        else if (action.equals("searchMyTomorrowPlanTask")) {
            return aroundDataSet("MyTomorrowPlanTask", point);
        }
        else if (action.equals("searchMyTomorrowPlanTaskMobDaily")) {
            return aroundDataSet("MyTomorrowPlanTaskMobDaily", point);
        }
        else if (action.equals("searchNextWeekCompleteTaskMobZS")) {
            return aroundDataSet("NextWeekCompleteTaskMobZS", point);
        }
        else if (action.equals("searchNextWeekCompleteTaskZS")) {
            return aroundDataSet("NextWeekCompleteTaskZS", point);
        }
        else if (action.equals("searchNextWeekPlanCompleteTask")) {
            return aroundDataSet("NextWeekPlanCompleteTask", point);
        }
        else if (action.equals("searchPlanTask")) {
            return aroundDataSet("PlanTask", point);
        }
        else if (action.equals("searchProjectAppTask")) {
            return aroundDataSet("ProjectAppTask", point);
        }
        else if (action.equals("searchProjectTask")) {
            return aroundDataSet("ProjectTask", point);
        }
        else if (action.equals("searchRootTask")) {
            return aroundDataSet("RootTask", point);
        }
        else if (action.equals("searchTaskLinkPlan")) {
            return aroundDataSet("TaskLinkPlan", point);
        }
        else if (action.equals("searchThisMonthCompleteTaskChoice")) {
            return aroundDataSet("ThisMonthCompleteTaskChoice", point);
        }
        else if (action.equals("searchThisWeekCompleteTask")) {
            return aroundDataSet("ThisWeekCompleteTask", point);
        }
        else if (action.equals("searchThisWeekCompleteTaskChoice")) {
            return aroundDataSet("ThisWeekCompleteTaskChoice", point);
        }
        else if (action.equals("searchThisWeekCompleteTaskMobZS")) {
            return aroundDataSet("ThisWeekCompleteTaskMobZS", point);
        }
        else if (action.equals("searchThisWeekCompleteTaskZS")) {
            return aroundDataSet("ThisWeekCompleteTaskZS", point);
        }
        else if (action.equals("searchTodoListTask")) {
            return aroundDataSet("TodoListTask", point);
        }
        else if (action.equals("searchTypeGroup")) {
            return aroundDataSet("TypeGroup", point);
        }
        else if (action.equals("searchTypeGroupPlan")) {
            return aroundDataSet("TypeGroupPlan", point);
        }
        return point.proceed();
    }

}
