package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import cn.ibizlab.pms.core.zentao.runtime.IStoryRuntime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Aspect
@org.springframework.core.annotation.Order(100)
@Slf4j
public class StoryAspect {

     @Autowired
    IStoryRuntime storyRuntime;

    private SystemDataEntityRuntime getDataEntityRuntime() {
        return (SystemDataEntityRuntime) storyRuntime;
    }

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.StoryServiceImpl.*(..))")
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
            return getDataEntityRuntime().aroundAction("activate", point);
        }
        else if (action.equals("allPush")) {
            return getDataEntityRuntime().aroundAction("AllPush", point);
        }
        else if (action.equals("assignTo")) {
            return getDataEntityRuntime().aroundAction("AssignTo", point);
        }
        else if (action.equals("batchAssignTo")) {
            return getDataEntityRuntime().aroundAction("BatchAssignTo", point);
        }
        else if (action.equals("batchChangeBranch")) {
            return getDataEntityRuntime().aroundAction("BatchChangeBranch", point);
        }
        else if (action.equals("batchChangeModule")) {
            return getDataEntityRuntime().aroundAction("BatchChangeModule", point);
        }
        else if (action.equals("batchChangePlan")) {
            return getDataEntityRuntime().aroundAction("BatchChangePlan", point);
        }
        else if (action.equals("batchChangeStage")) {
            return getDataEntityRuntime().aroundAction("BatchChangeStage", point);
        }
        else if (action.equals("batchClose")) {
            return getDataEntityRuntime().aroundAction("BatchClose", point);
        }
        else if (action.equals("batchReview")) {
            return getDataEntityRuntime().aroundAction("BatchReview", point);
        }
        else if (action.equals("batchUnlinkStory")) {
            return getDataEntityRuntime().aroundAction("BatchUnlinkStory", point);
        }
        else if (action.equals("bugToStory")) {
            return getDataEntityRuntime().aroundAction("BugToStory", point);
        }
        else if (action.equals("buildBatchUnlinkStory")) {
            return getDataEntityRuntime().aroundAction("buildBatchUnlinkStory", point);
        }
        else if (action.equals("buildLinkStory")) {
            return getDataEntityRuntime().aroundAction("buildLinkStory", point);
        }
        else if (action.equals("buildUnlinkStory")) {
            return getDataEntityRuntime().aroundAction("buildUnlinkStory", point);
        }
        else if (action.equals("buildUnlinkStorys")) {
            return getDataEntityRuntime().aroundAction("buildUnlinkStorys", point);
        }
        else if (action.equals("change")) {
            return getDataEntityRuntime().aroundAction("Change", point);
        }
        else if (action.equals("checkKey")) {
            return getDataEntityRuntime().aroundAction("CheckKey", point);
        }
        else if (action.equals("close")) {
            return getDataEntityRuntime().aroundAction("Close", point);
        }
        else if (action.equals("createTasks")) {
            return getDataEntityRuntime().aroundAction("CreateTasks", point);
        }
        else if (action.equals("getStorySpec")) {
            return getDataEntityRuntime().aroundAction("GetStorySpec", point);
        }
        else if (action.equals("getStorySpecs")) {
            return getDataEntityRuntime().aroundAction("GetStorySpecs", point);
        }
        else if (action.equals("importPlanStories")) {
            return getDataEntityRuntime().aroundAction("importPlanStories", point);
        }
        else if (action.equals("linkStory")) {
            return getDataEntityRuntime().aroundAction("LinkStory", point);
        }
        else if (action.equals("projectBatchUnlinkStory")) {
            return getDataEntityRuntime().aroundAction("ProjectBatchUnlinkStory", point);
        }
        else if (action.equals("projectLinkStory")) {
            return getDataEntityRuntime().aroundAction("ProjectLinkStory", point);
        }
        else if (action.equals("projectUnlinkStory")) {
            return getDataEntityRuntime().aroundAction("ProjectUnlinkStory", point);
        }
        else if (action.equals("projectUnlinkStorys")) {
            return getDataEntityRuntime().aroundAction("projectUnlinkStorys", point);
        }
        else if (action.equals("push")) {
            return getDataEntityRuntime().aroundAction("Push", point);
        }
        else if (action.equals("releaseBatchUnlinkStory")) {
            return getDataEntityRuntime().aroundAction("ReleaseBatchUnlinkStory", point);
        }
        else if (action.equals("releaseLinkStory")) {
            return getDataEntityRuntime().aroundAction("ReleaseLinkStory", point);
        }
        else if (action.equals("releaseUnlinkStory")) {
            return getDataEntityRuntime().aroundAction("ReleaseUnlinkStory", point);
        }
        else if (action.equals("resetReviewedBy")) {
            return getDataEntityRuntime().aroundAction("ResetReviewedBy", point);
        }
        else if (action.equals("review")) {
            return getDataEntityRuntime().aroundAction("Review", point);
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
        else if (action.equals("setStage")) {
            return getDataEntityRuntime().aroundAction("setStage", point);
        }
        else if (action.equals("storyFavorites")) {
            return getDataEntityRuntime().aroundAction("StoryFavorites", point);
        }
        else if (action.equals("storyNFavorites")) {
            return getDataEntityRuntime().aroundAction("StoryNFavorites", point);
        }
        else if (action.equals("syncFromIbiz")) {
            return getDataEntityRuntime().aroundAction("SyncFromIbiz", point);
        }
        else if (action.equals("unlinkStory")) {
            return getDataEntityRuntime().aroundAction("UnlinkStory", point);
        }
        else if (action.equals("searchAccount")) {
            return getDataEntityRuntime().aroundDataSet("Account", point);
        }
        else if (action.equals("searchAssignedToMyStory")) {
            return getDataEntityRuntime().aroundDataSet("AssignedToMyStory", point);
        }
        else if (action.equals("searchAssignedToMyStoryCalendar")) {
            return getDataEntityRuntime().aroundDataSet("AssignedToMyStoryCalendar", point);
        }
        else if (action.equals("searchBugStory")) {
            return getDataEntityRuntime().aroundDataSet("BugStory", point);
        }
        else if (action.equals("searchBuildLinkCompletedStories")) {
            return getDataEntityRuntime().aroundDataSet("buildLinkCompletedStories", point);
        }
        else if (action.equals("searchBuildLinkableStories")) {
            return getDataEntityRuntime().aroundDataSet("BuildLinkableStories", point);
        }
        else if (action.equals("searchBuildStories")) {
            return getDataEntityRuntime().aroundDataSet("BuildStories", point);
        }
        else if (action.equals("searchByModule")) {
            return getDataEntityRuntime().aroundDataSet("ByModule", point);
        }
        else if (action.equals("searchCaseStory")) {
            return getDataEntityRuntime().aroundDataSet("CaseStory", point);
        }
        else if (action.equals("searchChildMore")) {
            return getDataEntityRuntime().aroundDataSet("ChildMore", point);
        }
        else if (action.equals("searchDefault")) {
            return getDataEntityRuntime().aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchESBulk")) {
            return getDataEntityRuntime().aroundDataSet("ESBulk", point);
        }
        else if (action.equals("searchGetProductStories")) {
            return getDataEntityRuntime().aroundDataSet("GetProductStories", point);
        }
        else if (action.equals("searchMy")) {
            return getDataEntityRuntime().aroundDataSet("My", point);
        }
        else if (action.equals("searchMyAgentStory")) {
            return getDataEntityRuntime().aroundDataSet("MyAgentStory", point);
        }
        else if (action.equals("searchMyCreateOrPartake")) {
            return getDataEntityRuntime().aroundDataSet("MyCreateOrPartake", point);
        }
        else if (action.equals("searchMyCurOpenedStory")) {
            return getDataEntityRuntime().aroundDataSet("MyCurOpenedStory", point);
        }
        else if (action.equals("searchMyFavorites")) {
            return getDataEntityRuntime().aroundDataSet("MyFavorites", point);
        }
        else if (action.equals("searchMyReProduct")) {
            return getDataEntityRuntime().aroundDataSet("MyReProduct", point);
        }
        else if (action.equals("searchNotCurPlanLinkStory")) {
            return getDataEntityRuntime().aroundDataSet("NotCurPlanLinkStory", point);
        }
        else if (action.equals("searchParentDefault")) {
            return getDataEntityRuntime().aroundDataSet("ParentDefault", point);
        }
        else if (action.equals("searchParentDefaultQ")) {
            return getDataEntityRuntime().aroundDataSet("ParentDefaultQ", point);
        }
        else if (action.equals("searchProjectLinkStory")) {
            return getDataEntityRuntime().aroundDataSet("projectLinkStory", point);
        }
        else if (action.equals("searchProjectStories")) {
            return getDataEntityRuntime().aroundDataSet("ProjectStories", point);
        }
        else if (action.equals("searchReleaseLinkableStories")) {
            return getDataEntityRuntime().aroundDataSet("ReleaseLinkableStories", point);
        }
        else if (action.equals("searchReleaseStories")) {
            return getDataEntityRuntime().aroundDataSet("ReleaseStories", point);
        }
        else if (action.equals("searchReportStories")) {
            return getDataEntityRuntime().aroundDataSet("ReportStories", point);
        }
        else if (action.equals("searchStoryChild")) {
            return getDataEntityRuntime().aroundDataSet("StoryChild", point);
        }
        else if (action.equals("searchStoryRelated")) {
            return getDataEntityRuntime().aroundDataSet("StoryRelated", point);
        }
        else if (action.equals("searchSubStory")) {
            return getDataEntityRuntime().aroundDataSet("SubStory", point);
        }
        else if (action.equals("searchTaskRelatedStory")) {
            return getDataEntityRuntime().aroundDataSet("TaskRelatedStory", point);
        }
        else if (action.equals("searchView")) {
            return getDataEntityRuntime().aroundDataSet("VIEW", point);
        }
        return point.proceed();
    }
}
