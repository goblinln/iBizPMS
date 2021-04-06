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
        return "ZT_BUG";
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

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.BugServiceImpl.*(..))")
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

        if (action.equals("searchAssignedToMyBug")) {
            return aroundDataSet("AssignedToMyBug", point);
        }
        if (action.equals("searchAssignedToMyBugPc")) {
            return aroundDataSet("AssignedToMyBugPc", point);
        }
        if (action.equals("searchBugsByBuild")) {
            return aroundDataSet("BugsByBuild", point);
        }
        if (action.equals("searchBuildBugs")) {
            return aroundDataSet("BuildBugs", point);
        }
        if (action.equals("searchBuildLinkResolvedBugs")) {
            return aroundDataSet("buildLinkResolvedBugs", point);
        }
        if (action.equals("searchBuildOpenBugs")) {
            return aroundDataSet("BuildOpenBugs", point);
        }
        if (action.equals("searchBuildProduceBug")) {
            return aroundDataSet("BuildProduceBug", point);
        }
        if (action.equals("searchBuildProduceBugModule")) {
            return aroundDataSet("BuildProduceBugModule", point);
        }
        if (action.equals("searchBuildProduceBugModule_Project")) {
            return aroundDataSet("BuildProduceBugModule_Project", point);
        }
        if (action.equals("searchBuildProduceBugOpenedBy")) {
            return aroundDataSet("BuildProduceBugOpenedBy", point);
        }
        if (action.equals("searchBuildProduceBugOpenedBy_Project")) {
            return aroundDataSet("BuildProduceBugOpenedBy_Project", point);
        }
        if (action.equals("searchBuildProduceBugRES")) {
            return aroundDataSet("BuildProduceBugRES", point);
        }
        if (action.equals("searchBuildProduceBugRESOLVEDBY")) {
            return aroundDataSet("BuildProduceBugRESOLVEDBY", point);
        }
        if (action.equals("searchBuildProduceBugRESOLVEDBY_Project")) {
            return aroundDataSet("BuildProduceBugRESOLVEDBY_Project", point);
        }
        if (action.equals("searchBuildProduceBugResolution_Project")) {
            return aroundDataSet("BuildProduceBugResolution_Project", point);
        }
        if (action.equals("searchBuildProduceBugSeverity_Project")) {
            return aroundDataSet("BuildProduceBugSeverity_Project", point);
        }
        if (action.equals("searchBuildProduceBugStatus_Project")) {
            return aroundDataSet("BuildProduceBugStatus_Project", point);
        }
        if (action.equals("searchBuildProduceBugType_Project")) {
            return aroundDataSet("BuildProduceBugType_Project", point);
        }
        if (action.equals("searchCurUserResolve")) {
            return aroundDataSet("CurUserResolve", point);
        }
        if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        if (action.equals("searchESBulk")) {
            return aroundDataSet("ESBulk", point);
        }
        if (action.equals("searchMyAgentBug")) {
            return aroundDataSet("MyAgentBug", point);
        }
        if (action.equals("searchMyCurOpenedBug")) {
            return aroundDataSet("MyCurOpenedBug", point);
        }
        if (action.equals("searchMyFavorites")) {
            return aroundDataSet("MyFavorites", point);
        }
        if (action.equals("searchNotCurPlanLinkBug")) {
            return aroundDataSet("NotCurPlanLinkBug", point);
        }
        if (action.equals("searchProjectBugs")) {
            return aroundDataSet("ProjectBugs", point);
        }
        if (action.equals("searchReleaseBugs")) {
            return aroundDataSet("ReleaseBugs", point);
        }
        if (action.equals("searchReleaseLeftBugs")) {
            return aroundDataSet("ReleaseLeftBugs", point);
        }
        if (action.equals("searchReleaseLinkableLeftBug")) {
            return aroundDataSet("ReleaseLinkableLeftBug", point);
        }
        if (action.equals("searchReleaseLinkableResolvedBug")) {
            return aroundDataSet("ReleaseLinkableResolvedBug", point);
        }
        if (action.equals("searchReportBugs")) {
            return aroundDataSet("ReportBugs", point);
        }
        if (action.equals("searchTaskRelatedBug")) {
            return aroundDataSet("TaskBug", point);
        }
        return point.proceed();
    }

}
