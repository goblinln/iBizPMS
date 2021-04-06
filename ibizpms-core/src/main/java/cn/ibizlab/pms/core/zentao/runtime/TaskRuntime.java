package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Task;
import cn.ibizlab.pms.core.zentao.service.ITaskService;
import cn.ibizlab.pms.core.zentao.filter.TaskSearchContext;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import cn.ibizlab.pms.core.runtime.DataEntityRuntime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("TaskRuntime")
public class TaskRuntime extends DataEntityRuntime {

    @Autowired
    ITaskService taskService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new Task();
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
    public Object getFieldValue(Object o, IPSDEField ipsdeField) {
        Task domain = (Task)o;
        try {
            return domain.get(ipsdeField.getCodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public void setFieldValue(Object o, IPSDEField ipsdeField, Object o1) {
        Task domain = (Task)o;
        try {
            domain.set(ipsdeField.getCodeName(),o1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean containsFieldValue(Object o, IPSDEField ipsdeField) {
        return false;
    }

    @Override
    public Object createEntity() {
        return new Task();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.TaskServiceImpl.*(..))")
    public Object aroundMethod(ProceedingJoinPoint point) throws Throwable {
        String action = point.getSignature().getName();
        if (action.equals("create")) {
            return aroundAction("Create", point);
        }
        if (action.equals("update")) {
            return aroundAction("Update", point);
        }
        if (action.equals("remove")) {
            return aroundAction("Remove", point);
        }
        if (action.equals("get")) {
            return aroundAction("Get", point);
        }
        if (action.equals("getDraft")) {
            return aroundAction("GetDraft", point);
        }
        if (action.equals("activate")) {
            return aroundAction("Activate", point);
        }
        if (action.equals("assignTo")) {
            return aroundAction("AssignTo", point);
        }
        if (action.equals("cancel")) {
            return aroundAction("Cancel", point);
        }
        if (action.equals("checkKey")) {
            return aroundAction("CheckKey", point);
        }
        if (action.equals("close")) {
            return aroundAction("Close", point);
        }
        if (action.equals("confirmStoryChange")) {
            return aroundAction("ConfirmStoryChange", point);
        }
        if (action.equals("createCycleTasks")) {
            return aroundAction("CreateCycleTasks", point);
        }
        if (action.equals("deleteEstimate")) {
            return aroundAction("DeleteEstimate", point);
        }
        if (action.equals("editEstimate")) {
            return aroundAction("EditEstimate", point);
        }
        if (action.equals("finish")) {
            return aroundAction("Finish", point);
        }
        if (action.equals("getNextTeamUser")) {
            return aroundAction("GetNextTeamUserFinish", point);
        }
        if (action.equals("getTeamUserLeftActivity")) {
            return aroundAction("GetTeamUserLeftActivity", point);
        }
        if (action.equals("getTeamUserLeftStart")) {
            return aroundAction("GetTeamUserLeftStart", point);
        }
        if (action.equals("linkPlan")) {
            return aroundAction("LinkPlan", point);
        }
        if (action.equals("otherUpdate")) {
            return aroundAction("OtherUpdate", point);
        }
        if (action.equals("pause")) {
            return aroundAction("Pause", point);
        }
        if (action.equals("recordEstimate")) {
            return aroundAction("RecordEstimate", point);
        }
        if (action.equals("restart")) {
            return aroundAction("Restart", point);
        }
        if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        if (action.equals("sendMessage")) {
            return aroundAction("sendMessage", point);
        }
        if (action.equals("sendMsgPreProcess")) {
            return aroundAction("sendMsgPreProcess", point);
        }
        if (action.equals("start")) {
            return aroundAction("Start", point);
        }
        if (action.equals("taskForward")) {
            return aroundAction("taskForward", point);
        }

        if (action.equals("searchAssignedToMyTask")) {
            return aroundDataSet("AssignedToMyTask", point);
        }
        if (action.equals("searchAssignedToMyTaskPc")) {
            return aroundDataSet("AssignedToMyTaskPc", point);
        }
        if (action.equals("searchBugTask")) {
            return aroundDataSet("BugTask", point);
        }
        if (action.equals("searchByModule")) {
            return aroundDataSet("ByModule", point);
        }
        if (action.equals("searchChildTask")) {
            return aroundDataSet("ChildTask", point);
        }
        if (action.equals("searchChildTaskTree")) {
            return aroundDataSet("ChildTaskTree", point);
        }
        if (action.equals("searchCurFinishTask")) {
            return aroundDataSet("CurFinishTask", point);
        }
        if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        if (action.equals("searchDefaultRow")) {
            return aroundDataSet("DefaultRow", point);
        }
        if (action.equals("searchESBulk")) {
            return aroundDataSet("ESBulk", point);
        }
        if (action.equals("searchMyAgentTask")) {
            return aroundDataSet("MyAgentTask", point);
        }
        if (action.equals("searchMyAllTask")) {
            return aroundDataSet("MyAllTask", point);
        }
        if (action.equals("searchMyCompleteTask")) {
            return aroundDataSet("MyCompleteTask", point);
        }
        if (action.equals("searchMyCompleteTaskMobDaily")) {
            return aroundDataSet("MyCompleteTaskMobDaily", point);
        }
        if (action.equals("searchMyCompleteTaskMobMonthly")) {
            return aroundDataSet("MyCompleteTaskMobMonthly", point);
        }
        if (action.equals("searchMyCompleteTaskMonthlyZS")) {
            return aroundDataSet("MyCompleteTaskMonthlyZS", point);
        }
        if (action.equals("searchMyCompleteTaskZS")) {
            return aroundDataSet("MyCompleteTaskZS", point);
        }
        if (action.equals("searchMyFavorites")) {
            return aroundDataSet("MyFavorites", point);
        }
        if (action.equals("searchMyPlansTaskMobMonthly")) {
            return aroundDataSet("MyPlansTaskMobMonthly", point);
        }
        if (action.equals("searchMyTomorrowPlanTask")) {
            return aroundDataSet("MyTomorrowPlanTask", point);
        }
        if (action.equals("searchMyTomorrowPlanTaskMobDaily")) {
            return aroundDataSet("MyTomorrowPlanTaskMobDaily", point);
        }
        if (action.equals("searchNextWeekCompleteTaskMobZS")) {
            return aroundDataSet("NextWeekCompleteTaskMobZS", point);
        }
        if (action.equals("searchNextWeekCompleteTaskZS")) {
            return aroundDataSet("NextWeekCompleteTaskZS", point);
        }
        if (action.equals("searchNextWeekPlanCompleteTask")) {
            return aroundDataSet("NextWeekPlanCompleteTask", point);
        }
        if (action.equals("searchPlanTask")) {
            return aroundDataSet("PlanTask", point);
        }
        if (action.equals("searchProjectAppTask")) {
            return aroundDataSet("ProjectAppTask", point);
        }
        if (action.equals("searchProjectTask")) {
            return aroundDataSet("ProjectTask", point);
        }
        if (action.equals("searchRootTask")) {
            return aroundDataSet("RootTask", point);
        }
        if (action.equals("searchTaskLinkPlan")) {
            return aroundDataSet("TaskLinkPlan", point);
        }
        if (action.equals("searchThisMonthCompleteTaskChoice")) {
            return aroundDataSet("ThisMonthCompleteTaskChoice", point);
        }
        if (action.equals("searchThisWeekCompleteTask")) {
            return aroundDataSet("ThisWeekCompleteTask", point);
        }
        if (action.equals("searchThisWeekCompleteTaskChoice")) {
            return aroundDataSet("ThisWeekCompleteTaskChoice", point);
        }
        if (action.equals("searchThisWeekCompleteTaskMobZS")) {
            return aroundDataSet("ThisWeekCompleteTaskMobZS", point);
        }
        if (action.equals("searchThisWeekCompleteTaskZS")) {
            return aroundDataSet("ThisWeekCompleteTaskZS", point);
        }
        if (action.equals("searchTodoListTask")) {
            return aroundDataSet("TodoListTask", point);
        }
        if (action.equals("searchTypeGroup")) {
            return aroundDataSet("TypeGroup", point);
        }
        if (action.equals("searchTypeGroupPlan")) {
            return aroundDataSet("TypeGroupPlan", point);
        }
        return point.proceed();
    }

}
