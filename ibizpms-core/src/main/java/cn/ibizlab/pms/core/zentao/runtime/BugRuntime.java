package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Bug;
import cn.ibizlab.pms.core.zentao.service.IBugService;
import cn.ibizlab.pms.core.zentao.filter.BugSearchContext;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import cn.ibizlab.pms.core.runtime.DataEntityRuntime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("BugRuntime")
public class BugRuntime extends DataEntityRuntime {

    @Autowired
    IBugService bugService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new Bug();
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/Bug.json";
    }

    @Override
    public String getName() {
        return ZT_BUG;
    }

    @Override
    public Object getFieldValue(Object o, IPSDEField ipsdeField) {
        Bug domain = (Bug)o;
        try {
            return domain.get(ipsdeField.getCodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public void setFieldValue(Object o, IPSDEField ipsdeField, Object o1) {
        Bug domain = (Bug)o;
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
        return new Bug();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.BugServiceImpl.*(..))")
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
            return aroundAction("assignTo", point);
        }
        if (action.equals("batchUnlinkBug")) {
            return aroundAction("BatchUnlinkBug", point);
        }
        if (action.equals("buildBatchUnlinkBug")) {
            return aroundAction("buildBatchUnlinkBug", point);
        }
        if (action.equals("buildLinkBug")) {
            return aroundAction("buildLinkBug", point);
        }
        if (action.equals("buildUnlinkBug")) {
            return aroundAction("buildUnlinkBug", point);
        }
        if (action.equals("checkKey")) {
            return aroundAction("CheckKey", point);
        }
        if (action.equals("close")) {
            return aroundAction("Close", point);
        }
        if (action.equals("confirm")) {
            return aroundAction("Confirm", point);
        }
        if (action.equals("linkBug")) {
            return aroundAction("LinkBug", point);
        }
        if (action.equals("releaaseBatchUnlinkBug")) {
            return aroundAction("ReleaaseBatchUnlinkBug", point);
        }
        if (action.equals("releaseLinkBugbyBug")) {
            return aroundAction("ReleaseLinkBugbyBug", point);
        }
        if (action.equals("releaseLinkBugbyLeftBug")) {
            return aroundAction("ReleaseLinkBugbyLeftBug", point);
        }
        if (action.equals("releaseUnLinkBugbyLeftBug")) {
            return aroundAction("ReleaseUnLinkBugbyLeftBug", point);
        }
        if (action.equals("releaseUnlinkBug")) {
            return aroundAction("ReleaseUnlinkBug", point);
        }
        if (action.equals("resolve")) {
            return aroundAction("Resolve", point);
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
        if (action.equals("toStory")) {
            return aroundAction("toStory", point);
        }
        if (action.equals("unlinkBug")) {
            return aroundAction("UnlinkBug", point);
        }

    //
        if (action.equals("searchAssignedToMyBug")) {
            return aroundAction("AssignedToMyBug", point);
        }
        if (action.equals("searchAssignedToMyBugPc")) {
            return aroundAction("AssignedToMyBugPc", point);
        }
        if (action.equals("searchBugsByBuild")) {
            return aroundAction("BugsByBuild", point);
        }
        if (action.equals("searchBuildBugs")) {
            return aroundAction("BuildBugs", point);
        }
        if (action.equals("searchBuildLinkResolvedBugs")) {
            return aroundAction("buildLinkResolvedBugs", point);
        }
        if (action.equals("searchBuildOpenBugs")) {
            return aroundAction("BuildOpenBugs", point);
        }
        if (action.equals("searchBuildProduceBug")) {
            return aroundAction("BuildProduceBug", point);
        }
        if (action.equals("searchBuildProduceBugModule")) {
            return aroundAction("BuildProduceBugModule", point);
        }
        if (action.equals("searchBuildProduceBugModule_Project")) {
            return aroundAction("BuildProduceBugModule_Project", point);
        }
        if (action.equals("searchBuildProduceBugOpenedBy")) {
            return aroundAction("BuildProduceBugOpenedBy", point);
        }
        if (action.equals("searchBuildProduceBugOpenedBy_Project")) {
            return aroundAction("BuildProduceBugOpenedBy_Project", point);
        }
        if (action.equals("searchBuildProduceBugRES")) {
            return aroundAction("BuildProduceBugRES", point);
        }
        if (action.equals("searchBuildProduceBugRESOLVEDBY")) {
            return aroundAction("BuildProduceBugRESOLVEDBY", point);
        }
        if (action.equals("searchBuildProduceBugRESOLVEDBY_Project")) {
            return aroundAction("BuildProduceBugRESOLVEDBY_Project", point);
        }
        if (action.equals("searchBuildProduceBugResolution_Project")) {
            return aroundAction("BuildProduceBugResolution_Project", point);
        }
        if (action.equals("searchBuildProduceBugSeverity_Project")) {
            return aroundAction("BuildProduceBugSeverity_Project", point);
        }
        if (action.equals("searchBuildProduceBugStatus_Project")) {
            return aroundAction("BuildProduceBugStatus_Project", point);
        }
        if (action.equals("searchBuildProduceBugType_Project")) {
            return aroundAction("BuildProduceBugType_Project", point);
        }
        if (action.equals("searchCurUserResolve")) {
            return aroundAction("CurUserResolve", point);
        }
        if (action.equals("searchDefault")) {
            return aroundAction("DEFAULT", point);
        }
        if (action.equals("searchESBulk")) {
            return aroundAction("ESBulk", point);
        }
        if (action.equals("searchMyAgentBug")) {
            return aroundAction("MyAgentBug", point);
        }
        if (action.equals("searchMyCurOpenedBug")) {
            return aroundAction("MyCurOpenedBug", point);
        }
        if (action.equals("searchMyFavorites")) {
            return aroundAction("MyFavorites", point);
        }
        if (action.equals("searchNotCurPlanLinkBug")) {
            return aroundAction("NotCurPlanLinkBug", point);
        }
        if (action.equals("searchProjectBugs")) {
            return aroundAction("ProjectBugs", point);
        }
        if (action.equals("searchReleaseBugs")) {
            return aroundAction("ReleaseBugs", point);
        }
        if (action.equals("searchReleaseLeftBugs")) {
            return aroundAction("ReleaseLeftBugs", point);
        }
        if (action.equals("searchReleaseLinkableLeftBug")) {
            return aroundAction("ReleaseLinkableLeftBug", point);
        }
        if (action.equals("searchReleaseLinkableResolvedBug")) {
            return aroundAction("ReleaseLinkableResolvedBug", point);
        }
        if (action.equals("searchReportBugs")) {
            return aroundAction("ReportBugs", point);
        }
        if (action.equals("searchTaskRelatedBug")) {
            return aroundAction("TaskBug", point);
        }
        return point.proceed();
    }

}
