package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import cn.ibizlab.pms.core.zentao.runtime.IProjectRuntime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Aspect
@org.springframework.core.annotation.Order(100)
@Slf4j
public class ProjectAspect {

     @Autowired
    IProjectRuntime projectRuntime;

    private SystemDataEntityRuntime getDataEntityRuntime() {
        return (SystemDataEntityRuntime) projectRuntime;
    }

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.ProjectServiceImpl.*(..))")
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
        else if (action.equals("batchUnlinkStory")) {
            return getDataEntityRuntime().aroundAction("BatchUnlinkStory", point);
        }
        else if (action.equals("cancelProjectTop")) {
            return getDataEntityRuntime().aroundAction("CancelProjectTop", point);
        }
        else if (action.equals("checkKey")) {
            return getDataEntityRuntime().aroundAction("CheckKey", point);
        }
        else if (action.equals("close")) {
            return getDataEntityRuntime().aroundAction("Close", point);
        }
        else if (action.equals("importPlanStories")) {
            return getDataEntityRuntime().aroundAction("importPlanStories", point);
        }
        else if (action.equals("linkProduct")) {
            return getDataEntityRuntime().aroundAction("LinkProduct", point);
        }
        else if (action.equals("linkStory")) {
            return getDataEntityRuntime().aroundAction("LinkStory", point);
        }
        else if (action.equals("manageMembers")) {
            return getDataEntityRuntime().aroundAction("ManageMembers", point);
        }
        else if (action.equals("mobProjectCount")) {
            return getDataEntityRuntime().aroundAction("MobProjectCount", point);
        }
        else if (action.equals("pmsEeProjectAllTaskCount")) {
            return getDataEntityRuntime().aroundAction("PmsEeProjectAllTaskCount", point);
        }
        else if (action.equals("pmsEeProjectTodoTaskCount")) {
            return getDataEntityRuntime().aroundAction("PmsEeProjectTodoTaskCount", point);
        }
        else if (action.equals("projectTaskQCnt")) {
            return getDataEntityRuntime().aroundAction("ProjectTaskQCnt", point);
        }
        else if (action.equals("projectTop")) {
            return getDataEntityRuntime().aroundAction("projectTop", point);
        }
        else if (action.equals("putoff")) {
            return getDataEntityRuntime().aroundAction("Putoff", point);
        }
        else if (action.equals("save")) {
            return getDataEntityRuntime().aroundAction("Save", point);
        }
        else if (action.equals("start")) {
            return getDataEntityRuntime().aroundAction("Start", point);
        }
        else if (action.equals("suspend")) {
            return getDataEntityRuntime().aroundAction("Suspend", point);
        }
        else if (action.equals("unlinkMember")) {
            return getDataEntityRuntime().aroundAction("UnlinkMember", point);
        }
        else if (action.equals("unlinkProduct")) {
            return getDataEntityRuntime().aroundAction("UnlinkProduct", point);
        }
        else if (action.equals("unlinkStory")) {
            return getDataEntityRuntime().aroundAction("UnlinkStory", point);
        }
        else if (action.equals("updateOrder")) {
            return getDataEntityRuntime().aroundAction("UpdateOrder", point);
        }
        else if (action.equals("searchAccount")) {
            return getDataEntityRuntime().aroundDataSet("Account", point);
        }
        else if (action.equals("searchBugProject")) {
            return getDataEntityRuntime().aroundDataSet("BugProject", point);
        }
        else if (action.equals("searchCurDefaultQuery")) {
            return getDataEntityRuntime().aroundDataSet("CurDefaultQuery", point);
        }
        else if (action.equals("searchCurDefaultQueryExp")) {
            return getDataEntityRuntime().aroundDataSet("CurDefaultQueryExp", point);
        }
        else if (action.equals("searchCurPlanProject")) {
            return getDataEntityRuntime().aroundDataSet("CurPlanProject", point);
        }
        else if (action.equals("searchCurProduct")) {
            return getDataEntityRuntime().aroundDataSet("CurProduct", point);
        }
        else if (action.equals("searchCurUser")) {
            return getDataEntityRuntime().aroundDataSet("CurUser", point);
        }
        else if (action.equals("searchCurUserSa")) {
            return getDataEntityRuntime().aroundDataSet("CurUserSa", point);
        }
        else if (action.equals("searchDefault")) {
            return getDataEntityRuntime().aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchDeveloperQuery")) {
            return getDataEntityRuntime().aroundDataSet("DeveloperQuery", point);
        }
        else if (action.equals("searchESBulk")) {
            return getDataEntityRuntime().aroundDataSet("ESBulk", point);
        }
        else if (action.equals("searchInvolvedProject")) {
            return getDataEntityRuntime().aroundDataSet("InvolvedProject", point);
        }
        else if (action.equals("searchInvolvedProject_StoryTaskBug")) {
            return getDataEntityRuntime().aroundDataSet("InvolvedProject_StoryTaskBug", point);
        }
        else if (action.equals("searchMy")) {
            return getDataEntityRuntime().aroundDataSet("My", point);
        }
        else if (action.equals("searchMyProject")) {
            return getDataEntityRuntime().aroundDataSet("MyProject", point);
        }
        else if (action.equals("searchOpenByQuery")) {
            return getDataEntityRuntime().aroundDataSet("OpenByQuery", point);
        }
        else if (action.equals("searchOpenQuery")) {
            return getDataEntityRuntime().aroundDataSet("OpenQuery", point);
        }
        else if (action.equals("searchProjectTeam")) {
            return getDataEntityRuntime().aroundDataSet("ProjectTeam", point);
        }
        else if (action.equals("searchStoryProject")) {
            return getDataEntityRuntime().aroundDataSet("StoryProject", point);
        }
        else if (action.equals("searchUnDoneProject")) {
            return getDataEntityRuntime().aroundDataSet("UnDoneProject", point);
        }
        return point.proceed();
    }
}
