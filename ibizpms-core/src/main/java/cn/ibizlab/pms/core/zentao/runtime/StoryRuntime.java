package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Story;
import cn.ibizlab.pms.core.zentao.service.IStoryService;
import cn.ibizlab.pms.core.zentao.filter.StorySearchContext;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import cn.ibizlab.pms.core.runtime.DataEntityRuntime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("StoryRuntime")
public class StoryRuntime extends DataEntityRuntime {

    @Autowired
    IStoryService storyService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new Story();
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/Story.json";
    }

    @Override
    public String getName() {
        return "ZT_STORY";
    }

    @Override
    public Object getFieldValue(Object o, IPSDEField ipsdeField) {
        Story domain = (Story)o;
        try {
            return domain.get(ipsdeField.getCodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public void setFieldValue(Object o, IPSDEField ipsdeField, Object o1) {
        Story domain = (Story)o;
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
        return new Story();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.StoryServiceImpl.*(..))")
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
            return aroundAction("activate", point);
        }
        if (action.equals("allPush")) {
            return aroundAction("AllPush", point);
        }
        if (action.equals("assignTo")) {
            return aroundAction("AssignTo", point);
        }
        if (action.equals("batchAssignTo")) {
            return aroundAction("BatchAssignTo", point);
        }
        if (action.equals("batchChangeBranch")) {
            return aroundAction("BatchChangeBranch", point);
        }
        if (action.equals("batchChangeModule")) {
            return aroundAction("BatchChangeModule", point);
        }
        if (action.equals("batchChangePlan")) {
            return aroundAction("BatchChangePlan", point);
        }
        if (action.equals("batchChangeStage")) {
            return aroundAction("BatchChangeStage", point);
        }
        if (action.equals("batchClose")) {
            return aroundAction("BatchClose", point);
        }
        if (action.equals("batchReview")) {
            return aroundAction("BatchReview", point);
        }
        if (action.equals("batchUnlinkStory")) {
            return aroundAction("BatchUnlinkStory", point);
        }
        if (action.equals("bugToStory")) {
            return aroundAction("BugToStory", point);
        }
        if (action.equals("buildBatchUnlinkStory")) {
            return aroundAction("buildBatchUnlinkStory", point);
        }
        if (action.equals("buildLinkStory")) {
            return aroundAction("buildLinkStory", point);
        }
        if (action.equals("buildUnlinkStory")) {
            return aroundAction("buildUnlinkStory", point);
        }
        if (action.equals("change")) {
            return aroundAction("Change", point);
        }
        if (action.equals("checkKey")) {
            return aroundAction("CheckKey", point);
        }
        if (action.equals("close")) {
            return aroundAction("Close", point);
        }
        if (action.equals("createTasks")) {
            return aroundAction("CreateTasks", point);
        }
        if (action.equals("getStorySpec")) {
            return aroundAction("GetStorySpec", point);
        }
        if (action.equals("importPlanStories")) {
            return aroundAction("importPlanStories", point);
        }
        if (action.equals("linkStory")) {
            return aroundAction("LinkStory", point);
        }
        if (action.equals("projectBatchUnlinkStory")) {
            return aroundAction("ProjectBatchUnlinkStory", point);
        }
        if (action.equals("projectLinkStory")) {
            return aroundAction("ProjectLinkStory", point);
        }
        if (action.equals("projectUnlinkStory")) {
            return aroundAction("ProjectUnlinkStory", point);
        }
        if (action.equals("push")) {
            return aroundAction("Push", point);
        }
        if (action.equals("releaseBatchUnlinkStory")) {
            return aroundAction("ReleaseBatchUnlinkStory", point);
        }
        if (action.equals("releaseLinkStory")) {
            return aroundAction("ReleaseLinkStory", point);
        }
        if (action.equals("releaseUnlinkStory")) {
            return aroundAction("ReleaseUnlinkStory", point);
        }
        if (action.equals("resetReviewedBy")) {
            return aroundAction("ResetReviewedBy", point);
        }
        if (action.equals("review")) {
            return aroundAction("Review", point);
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
        if (action.equals("syncFromIbiz")) {
            return aroundAction("SyncFromIbiz", point);
        }
        if (action.equals("unlinkStory")) {
            return aroundAction("UnlinkStory", point);
        }

    //
        if (action.equals("searchAssignedToMyStory")) {
            return aroundAction("AssignedToMyStory", point);
        }
        if (action.equals("searchAssignedToMyStoryCalendar")) {
            return aroundAction("AssignedToMyStoryCalendar", point);
        }
        if (action.equals("searchBugStory")) {
            return aroundAction("BugStory", point);
        }
        if (action.equals("searchBuildLinkCompletedStories")) {
            return aroundAction("buildLinkCompletedStories", point);
        }
        if (action.equals("searchBuildLinkableStories")) {
            return aroundAction("BuildLinkableStories", point);
        }
        if (action.equals("searchBuildStories")) {
            return aroundAction("BuildStories", point);
        }
        if (action.equals("searchByModule")) {
            return aroundAction("ByModule", point);
        }
        if (action.equals("searchCaseStory")) {
            return aroundAction("CaseStory", point);
        }
        if (action.equals("searchDefault")) {
            return aroundAction("DEFAULT", point);
        }
        if (action.equals("searchESBulk")) {
            return aroundAction("ESBulk", point);
        }
        if (action.equals("searchGetProductStories")) {
            return aroundAction("GetProductStories", point);
        }
        if (action.equals("searchMyAgentStory")) {
            return aroundAction("MyAgentStory", point);
        }
        if (action.equals("searchMyCurOpenedStory")) {
            return aroundAction("MyCurOpenedStory", point);
        }
        if (action.equals("searchMyFavorites")) {
            return aroundAction("MyFavorites", point);
        }
        if (action.equals("searchNotCurPlanLinkStory")) {
            return aroundAction("NotCurPlanLinkStory", point);
        }
        if (action.equals("searchParentDefault")) {
            return aroundAction("ParentDefault", point);
        }
        if (action.equals("searchParentDefaultQ")) {
            return aroundAction("ParentDefaultQ", point);
        }
        if (action.equals("searchProjectLinkStory")) {
            return aroundAction("projectLinkStory", point);
        }
        if (action.equals("searchProjectStories")) {
            return aroundAction("ProjectStories", point);
        }
        if (action.equals("searchReleaseLinkableStories")) {
            return aroundAction("ReleaseLinkableStories", point);
        }
        if (action.equals("searchReleaseStories")) {
            return aroundAction("ReleaseStories", point);
        }
        if (action.equals("searchReportStories")) {
            return aroundAction("ReportStories", point);
        }
        if (action.equals("searchStoryChild")) {
            return aroundAction("StoryChild", point);
        }
        if (action.equals("searchStoryRelated")) {
            return aroundAction("StoryRelated", point);
        }
        if (action.equals("searchSubStory")) {
            return aroundAction("SubStory", point);
        }
        if (action.equals("searchTaskRelatedStory")) {
            return aroundAction("TaskRelatedStory", point);
        }
        if (action.equals("searchView")) {
            return aroundAction("VIEW", point);
        }
        return point.proceed();
    }

}
