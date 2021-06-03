package cn.ibizlab.pms.core.zentao.runtime.aspect;

import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import cn.ibizlab.pms.core.zentao.runtime.ITaskRuntime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Aspect
@org.springframework.core.annotation.Order(100)
@Slf4j
@Component
public class TaskAspect {

     @Autowired
    ITaskRuntime taskRuntime;

    private SystemDataEntityRuntime getDataEntityRuntime() {
        return (SystemDataEntityRuntime) taskRuntime;
    }

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.TaskServiceImpl.*(..))")
    @Transactional
    public Object aroundMethod(ProceedingJoinPoint point) throws Throwable {
        if (!getDataEntityRuntime().isRtmodel()) {
            return point.proceed();
        }
        String action = point.getSignature().getName();
        if (action.equals("create")) {
            return getDataEntityRuntime().aroundAction("Create", point);
        }
        else if (action.equals("update")) {
            return getDataEntityRuntime().aroundAction("Update", point);
        }
        else if (action.equals("remove")) {
            return getDataEntityRuntime().aroundAction("Remove", point);
        }
        else if (action.equals("get")) {
            return getDataEntityRuntime().aroundAction("Get", point);
        }
        else if (action.equals("getDraft")) {
            return getDataEntityRuntime().aroundAction("GetDraft", point);
        }
        else if (action.equals("activate")) {
            return getDataEntityRuntime().aroundAction("Activate", point);
        }
        else if (action.equals("assignTo")) {
            return getDataEntityRuntime().aroundAction("AssignTo", point);
        }
        else if (action.equals("cancel")) {
            return getDataEntityRuntime().aroundAction("Cancel", point);
        }
        else if (action.equals("checkKey")) {
            return getDataEntityRuntime().aroundAction("CheckKey", point);
        }
        else if (action.equals("close")) {
            return getDataEntityRuntime().aroundAction("Close", point);
        }
        else if (action.equals("computeBeginAndEnd")) {
            return getDataEntityRuntime().aroundAction("computeBeginAndEnd", point);
        }
        else if (action.equals("computeHours4Multiple")) {
            return getDataEntityRuntime().aroundAction("computeHours4Multiple", point);
        }
        else if (action.equals("computeWorkingHours")) {
            return getDataEntityRuntime().aroundAction("computeWorkingHours", point);
        }
        else if (action.equals("confirmStoryChange")) {
            return getDataEntityRuntime().aroundAction("ConfirmStoryChange", point);
        }
        else if (action.equals("createByCycle")) {
            return getDataEntityRuntime().aroundAction("createByCycle", point);
        }
        else if (action.equals("createCycleTasks")) {
            return getDataEntityRuntime().aroundAction("CreateCycleTasks", point);
        }
        else if (action.equals("delete")) {
            return getDataEntityRuntime().aroundAction("delete", point);
        }
        else if (action.equals("deleteEstimate")) {
            return getDataEntityRuntime().aroundAction("DeleteEstimate", point);
        }
        else if (action.equals("editEstimate")) {
            return getDataEntityRuntime().aroundAction("EditEstimate", point);
        }
        else if (action.equals("finish")) {
            return getDataEntityRuntime().aroundAction("Finish", point);
        }
        else if (action.equals("getNextTeamUser")) {
            return getDataEntityRuntime().aroundAction("GetNextTeamUserFinish", point);
        }
        else if (action.equals("getTeamUserLeftActivity")) {
            return getDataEntityRuntime().aroundAction("GetTeamUserLeftActivity", point);
        }
        else if (action.equals("getTeamUserLeftStart")) {
            return getDataEntityRuntime().aroundAction("GetTeamUserLeftStart", point);
        }
        else if (action.equals("getUsernames")) {
            return getDataEntityRuntime().aroundAction("getUsernames", point);
        }
        else if (action.equals("linkPlan")) {
            return getDataEntityRuntime().aroundAction("LinkPlan", point);
        }
        else if (action.equals("otherUpdate")) {
            return getDataEntityRuntime().aroundAction("OtherUpdate", point);
        }
        else if (action.equals("pause")) {
            return getDataEntityRuntime().aroundAction("Pause", point);
        }
        else if (action.equals("recordEstimate")) {
            return getDataEntityRuntime().aroundAction("RecordEstimate", point);
        }
        else if (action.equals("recordTimZeroLeftAfterContinue")) {
            return getDataEntityRuntime().aroundAction("RecordTimZeroLeftAfterContinue", point);
        }
        else if (action.equals("recordTimateZeroLeft")) {
            return getDataEntityRuntime().aroundAction("RecordTimateZeroLeft", point);
        }
        else if (action.equals("recordTimateZeroLeftAfterStart")) {
            return getDataEntityRuntime().aroundAction("RecordTimateZeroLeftAfterStart", point);
        }
        else if (action.equals("restart")) {
            return getDataEntityRuntime().aroundAction("Restart", point);
        }
        else if (action.equals("save")) {
            return getDataEntityRuntime().aroundAction("Save", point);
        }
        else if (action.equals("sendMessage")) {
            return getDataEntityRuntime().aroundAction("sendMessage", point);
        }
        else if (action.equals("sendMsgPreProcess")) {
            return getDataEntityRuntime().aroundAction("sendMsgPreProcess", point);
        }
        else if (action.equals("start")) {
            return getDataEntityRuntime().aroundAction("Start", point);
        }
        else if (action.equals("taskFavorites")) {
            return getDataEntityRuntime().aroundAction("TaskFavorites", point);
        }
        else if (action.equals("taskForward")) {
            return getDataEntityRuntime().aroundAction("taskForward", point);
        }
        else if (action.equals("taskNFavorites")) {
            return getDataEntityRuntime().aroundAction("TaskNFavorites", point);
        }
        else if (action.equals("updateParentStatus")) {
            return getDataEntityRuntime().aroundAction("updateParentStatus", point);
        }
        else if (action.equals("updateRelatedPlanStatus")) {
            return getDataEntityRuntime().aroundAction("updateRelatedPlanStatus", point);
        }
        else if (action.equals("updateStoryVersion")) {
            return getDataEntityRuntime().aroundAction("updateStoryVersion", point);
        }
        else if (action.equals("searchAccount")) {
            return getDataEntityRuntime().aroundDataSet("Account", point);
        }
        else if (action.equals("searchAssignedToMyTask")) {
            return getDataEntityRuntime().aroundDataSet("AssignedToMyTask", point);
        }
        else if (action.equals("searchAssignedToMyTaskPc")) {
            return getDataEntityRuntime().aroundDataSet("AssignedToMyTaskPc", point);
        }
        else if (action.equals("searchBugTask")) {
            return getDataEntityRuntime().aroundDataSet("BugTask", point);
        }
        else if (action.equals("searchByModule")) {
            return getDataEntityRuntime().aroundDataSet("ByModule", point);
        }
        else if (action.equals("searchChildDefault")) {
            return getDataEntityRuntime().aroundDataSet("ChildDefault", point);
        }
        else if (action.equals("searchChildDefaultMore")) {
            return getDataEntityRuntime().aroundDataSet("ChildDefaultMore", point);
        }
        else if (action.equals("searchChildTask")) {
            return getDataEntityRuntime().aroundDataSet("ChildTask", point);
        }
        else if (action.equals("searchChildTaskTree")) {
            return getDataEntityRuntime().aroundDataSet("ChildTaskTree", point);
        }
        else if (action.equals("searchCurFinishTask")) {
            return getDataEntityRuntime().aroundDataSet("CurFinishTask", point);
        }
        else if (action.equals("searchCurProjectTaskQuery")) {
            return getDataEntityRuntime().aroundDataSet("CurProjectTaskQuery", point);
        }
        else if (action.equals("searchDefault")) {
            return getDataEntityRuntime().aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchDefaultRow")) {
            return getDataEntityRuntime().aroundDataSet("DefaultRow", point);
        }
        else if (action.equals("searchESBulk")) {
            return getDataEntityRuntime().aroundDataSet("ESBulk", point);
        }
        else if (action.equals("searchMy")) {
            return getDataEntityRuntime().aroundDataSet("My", point);
        }
        else if (action.equals("searchMyAgentTask")) {
            return getDataEntityRuntime().aroundDataSet("MyAgentTask", point);
        }
        else if (action.equals("searchMyAllTask")) {
            return getDataEntityRuntime().aroundDataSet("MyAllTask", point);
        }
        else if (action.equals("searchMyCompleteTask")) {
            return getDataEntityRuntime().aroundDataSet("MyCompleteTask", point);
        }
        else if (action.equals("searchMyCompleteTaskMobDaily")) {
            return getDataEntityRuntime().aroundDataSet("MyCompleteTaskMobDaily", point);
        }
        else if (action.equals("searchMyCompleteTaskMobMonthly")) {
            return getDataEntityRuntime().aroundDataSet("MyCompleteTaskMobMonthly", point);
        }
        else if (action.equals("searchMyCompleteTaskMonthlyZS")) {
            return getDataEntityRuntime().aroundDataSet("MyCompleteTaskMonthlyZS", point);
        }
        else if (action.equals("searchMyCompleteTaskZS")) {
            return getDataEntityRuntime().aroundDataSet("MyCompleteTaskZS", point);
        }
        else if (action.equals("searchMyCreateOrPartake")) {
            return getDataEntityRuntime().aroundDataSet("MyCreateOrPartake", point);
        }
        else if (action.equals("searchMyFavorites")) {
            return getDataEntityRuntime().aroundDataSet("MyFavorites", point);
        }
        else if (action.equals("searchMyPlansTaskMobMonthly")) {
            return getDataEntityRuntime().aroundDataSet("MyPlansTaskMobMonthly", point);
        }
        else if (action.equals("searchMyReProject")) {
            return getDataEntityRuntime().aroundDataSet("MyReProject", point);
        }
        else if (action.equals("searchMyTomorrowPlanTask")) {
            return getDataEntityRuntime().aroundDataSet("MyTomorrowPlanTask", point);
        }
        else if (action.equals("searchMyTomorrowPlanTaskMobDaily")) {
            return getDataEntityRuntime().aroundDataSet("MyTomorrowPlanTaskMobDaily", point);
        }
        else if (action.equals("searchNextWeekCompleteTaskMobZS")) {
            return getDataEntityRuntime().aroundDataSet("NextWeekCompleteTaskMobZS", point);
        }
        else if (action.equals("searchNextWeekCompleteTaskZS")) {
            return getDataEntityRuntime().aroundDataSet("NextWeekCompleteTaskZS", point);
        }
        else if (action.equals("searchNextWeekPlanCompleteTask")) {
            return getDataEntityRuntime().aroundDataSet("NextWeekPlanCompleteTask", point);
        }
        else if (action.equals("searchPlanTask")) {
            return getDataEntityRuntime().aroundDataSet("PlanTask", point);
        }
        else if (action.equals("searchProjectAppTask")) {
            return getDataEntityRuntime().aroundDataSet("ProjectAppTask", point);
        }
        else if (action.equals("searchProjectTask")) {
            return getDataEntityRuntime().aroundDataSet("ProjectTask", point);
        }
        else if (action.equals("searchReportDS")) {
            return getDataEntityRuntime().aroundDataSet("ReportDS", point);
        }
        else if (action.equals("searchRootTask")) {
            return getDataEntityRuntime().aroundDataSet("RootTask", point);
        }
        else if (action.equals("searchTaskLinkPlan")) {
            return getDataEntityRuntime().aroundDataSet("TaskLinkPlan", point);
        }
        else if (action.equals("searchThisMonthCompleteTaskChoice")) {
            return getDataEntityRuntime().aroundDataSet("ThisMonthCompleteTaskChoice", point);
        }
        else if (action.equals("searchThisWeekCompleteTask")) {
            return getDataEntityRuntime().aroundDataSet("ThisWeekCompleteTask", point);
        }
        else if (action.equals("searchThisWeekCompleteTaskChoice")) {
            return getDataEntityRuntime().aroundDataSet("ThisWeekCompleteTaskChoice", point);
        }
        else if (action.equals("searchThisWeekCompleteTaskMobZS")) {
            return getDataEntityRuntime().aroundDataSet("ThisWeekCompleteTaskMobZS", point);
        }
        else if (action.equals("searchThisWeekCompleteTaskZS")) {
            return getDataEntityRuntime().aroundDataSet("ThisWeekCompleteTaskZS", point);
        }
        else if (action.equals("searchTodoListTask")) {
            return getDataEntityRuntime().aroundDataSet("TodoListTask", point);
        }
        else if (action.equals("searchTypeGroup")) {
            return getDataEntityRuntime().aroundDataSet("TypeGroup", point);
        }
        else if (action.equals("searchTypeGroupPlan")) {
            return getDataEntityRuntime().aroundDataSet("TypeGroupPlan", point);
        }
        return point.proceed();
    }
}
