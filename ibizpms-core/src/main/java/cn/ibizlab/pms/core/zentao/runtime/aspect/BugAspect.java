package cn.ibizlab.pms.core.zentao.runtime.aspect;

import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import cn.ibizlab.pms.core.zentao.runtime.IBugRuntime;
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
public class BugAspect {

     @Autowired
    IBugRuntime bugRuntime;

    private SystemDataEntityRuntime getDataEntityRuntime() {
        return (SystemDataEntityRuntime) bugRuntime;
    }

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.BugServiceImpl.*(..))")
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
            return getDataEntityRuntime().aroundAction("assignTo", point);
        }
        else if (action.equals("batchUnlinkBug")) {
            return getDataEntityRuntime().aroundAction("BatchUnlinkBug", point);
        }
        else if (action.equals("bugFavorites")) {
            return getDataEntityRuntime().aroundAction("BugFavorites", point);
        }
        else if (action.equals("bugNFavorites")) {
            return getDataEntityRuntime().aroundAction("BugNFavorites", point);
        }
        else if (action.equals("buildBatchUnlinkBug")) {
            return getDataEntityRuntime().aroundAction("buildBatchUnlinkBug", point);
        }
        else if (action.equals("buildLinkBug")) {
            return getDataEntityRuntime().aroundAction("buildLinkBug", point);
        }
        else if (action.equals("buildUnlinkBug")) {
            return getDataEntityRuntime().aroundAction("buildUnlinkBug", point);
        }
        else if (action.equals("checkKey")) {
            return getDataEntityRuntime().aroundAction("CheckKey", point);
        }
        else if (action.equals("close")) {
            return getDataEntityRuntime().aroundAction("Close", point);
        }
        else if (action.equals("confirm")) {
            return getDataEntityRuntime().aroundAction("Confirm", point);
        }
        else if (action.equals("linkBug")) {
            return getDataEntityRuntime().aroundAction("LinkBug", point);
        }
        else if (action.equals("releaaseBatchUnlinkBug")) {
            return getDataEntityRuntime().aroundAction("ReleaaseBatchUnlinkBug", point);
        }
        else if (action.equals("releaseLinkBugbyBug")) {
            return getDataEntityRuntime().aroundAction("ReleaseLinkBugbyBug", point);
        }
        else if (action.equals("releaseLinkBugbyLeftBug")) {
            return getDataEntityRuntime().aroundAction("ReleaseLinkBugbyLeftBug", point);
        }
        else if (action.equals("releaseUnLinkBugbyLeftBug")) {
            return getDataEntityRuntime().aroundAction("ReleaseUnLinkBugbyLeftBug", point);
        }
        else if (action.equals("releaseUnlinkBug")) {
            return getDataEntityRuntime().aroundAction("ReleaseUnlinkBug", point);
        }
        else if (action.equals("resolve")) {
            return getDataEntityRuntime().aroundAction("Resolve", point);
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
        else if (action.equals("testScript")) {
            return getDataEntityRuntime().aroundAction(action, point);
        }
        else if (action.equals("toStory")) {
            return getDataEntityRuntime().aroundAction("toStory", point);
        }
        else if (action.equals("unlinkBug")) {
            return getDataEntityRuntime().aroundAction("UnlinkBug", point);
        }
        else if (action.equals("updateStoryVersion")) {
            return getDataEntityRuntime().aroundAction("UpdateStoryVersion", point);
        }
        else if (action.equals("searchAccount")) {
            return getDataEntityRuntime().aroundDataSet("Account", point);
        }
        else if (action.equals("searchAssignedToMyBug")) {
            return getDataEntityRuntime().aroundDataSet("AssignedToMyBug", point);
        }
        else if (action.equals("searchAssignedToMyBugPc")) {
            return getDataEntityRuntime().aroundDataSet("AssignedToMyBugPc", point);
        }
        else if (action.equals("searchBugsByBuild")) {
            return getDataEntityRuntime().aroundDataSet("BugsByBuild", point);
        }
        else if (action.equals("searchBuildBugs")) {
            return getDataEntityRuntime().aroundDataSet("BuildBugs", point);
        }
        else if (action.equals("searchBuildLinkResolvedBugs")) {
            return getDataEntityRuntime().aroundDataSet("buildLinkResolvedBugs", point);
        }
        else if (action.equals("searchBuildOpenBugs")) {
            return getDataEntityRuntime().aroundDataSet("BuildOpenBugs", point);
        }
        else if (action.equals("searchBuildProduceBug")) {
            return getDataEntityRuntime().aroundDataSet("BuildProduceBug", point);
        }
        else if (action.equals("searchBuildProduceBugModule")) {
            return getDataEntityRuntime().aroundDataSet("BuildProduceBugModule", point);
        }
        else if (action.equals("searchBuildProduceBugModule_Project")) {
            return getDataEntityRuntime().aroundDataSet("BuildProduceBugModule_Project", point);
        }
        else if (action.equals("searchBuildProduceBugOpenedBy")) {
            return getDataEntityRuntime().aroundDataSet("BuildProduceBugOpenedBy", point);
        }
        else if (action.equals("searchBuildProduceBugOpenedBy_Project")) {
            return getDataEntityRuntime().aroundDataSet("BuildProduceBugOpenedBy_Project", point);
        }
        else if (action.equals("searchBuildProduceBugRES")) {
            return getDataEntityRuntime().aroundDataSet("BuildProduceBugRES", point);
        }
        else if (action.equals("searchBuildProduceBugRESOLVEDBY")) {
            return getDataEntityRuntime().aroundDataSet("BuildProduceBugRESOLVEDBY", point);
        }
        else if (action.equals("searchBuildProduceBugRESOLVEDBY_Project")) {
            return getDataEntityRuntime().aroundDataSet("BuildProduceBugRESOLVEDBY_Project", point);
        }
        else if (action.equals("searchBuildProduceBugResolution_Project")) {
            return getDataEntityRuntime().aroundDataSet("BuildProduceBugResolution_Project", point);
        }
        else if (action.equals("searchBuildProduceBugSeverity_Project")) {
            return getDataEntityRuntime().aroundDataSet("BuildProduceBugSeverity_Project", point);
        }
        else if (action.equals("searchBuildProduceBugStatus_Project")) {
            return getDataEntityRuntime().aroundDataSet("BuildProduceBugStatus_Project", point);
        }
        else if (action.equals("searchBuildProduceBugType_Project")) {
            return getDataEntityRuntime().aroundDataSet("BuildProduceBugType_Project", point);
        }
        else if (action.equals("searchCurUserResolve")) {
            return getDataEntityRuntime().aroundDataSet("CurUserResolve", point);
        }
        else if (action.equals("searchDefault")) {
            return getDataEntityRuntime().aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchESBulk")) {
            return getDataEntityRuntime().aroundDataSet("ESBulk", point);
        }
        else if (action.equals("searchMy")) {
            return getDataEntityRuntime().aroundDataSet("My", point);
        }
        else if (action.equals("searchMyAgentBug")) {
            return getDataEntityRuntime().aroundDataSet("MyAgentBug", point);
        }
        else if (action.equals("searchMyCreate")) {
            return getDataEntityRuntime().aroundDataSet("MyCreate", point);
        }
        else if (action.equals("searchMyCreateOrPartake")) {
            return getDataEntityRuntime().aroundDataSet("MyCreateOrPartake", point);
        }
        else if (action.equals("searchMyCurOpenedBug")) {
            return getDataEntityRuntime().aroundDataSet("MyCurOpenedBug", point);
        }
        else if (action.equals("searchMyFavorites")) {
            return getDataEntityRuntime().aroundDataSet("MyFavorites", point);
        }
        else if (action.equals("searchMyReProduct")) {
            return getDataEntityRuntime().aroundDataSet("MyReProduct", point);
        }
        else if (action.equals("searchNotCurPlanLinkBug")) {
            return getDataEntityRuntime().aroundDataSet("NotCurPlanLinkBug", point);
        }
        else if (action.equals("searchProductBugDS")) {
            return getDataEntityRuntime().aroundDataSet("ProductBugDS", point);
        }
        else if (action.equals("searchProjectBugDS")) {
            return getDataEntityRuntime().aroundDataSet("ProjectBugDS", point);
        }
        else if (action.equals("searchProjectBugs")) {
            return getDataEntityRuntime().aroundDataSet("ProjectBugs", point);
        }
        else if (action.equals("searchReleaseBugs")) {
            return getDataEntityRuntime().aroundDataSet("ReleaseBugs", point);
        }
        else if (action.equals("searchReleaseLeftBugs")) {
            return getDataEntityRuntime().aroundDataSet("ReleaseLeftBugs", point);
        }
        else if (action.equals("searchReleaseLinkableLeftBug")) {
            return getDataEntityRuntime().aroundDataSet("ReleaseLinkableLeftBug", point);
        }
        else if (action.equals("searchReleaseLinkableResolvedBug")) {
            return getDataEntityRuntime().aroundDataSet("ReleaseLinkableResolvedBug", point);
        }
        else if (action.equals("searchReportBugs")) {
            return getDataEntityRuntime().aroundDataSet("ReportBugs", point);
        }
        else if (action.equals("searchStoryFormBug")) {
            return getDataEntityRuntime().aroundDataSet("StoryFormBug", point);
        }
        else if (action.equals("searchTaskRelatedBug")) {
            return getDataEntityRuntime().aroundDataSet("TaskBug", point);
        }
        return point.proceed();
    }
}
