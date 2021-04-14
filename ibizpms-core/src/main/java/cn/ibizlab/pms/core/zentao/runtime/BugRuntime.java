package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Bug;
import cn.ibizlab.pms.core.zentao.service.IBugService;
import cn.ibizlab.pms.core.zentao.filter.BugSearchContext;
import  cn.ibizlab.pms.util.filter.QueryWrapperContext;
import com.baomidou.mybatisplus.extension.service.IService;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import net.ibizsys.model.dataentity.der.IPSDERBase;
import net.ibizsys.model.dataentity.logic.IPSDELogic;
import net.ibizsys.model.dataentity.wf.IPSDEWF;
import net.ibizsys.runtime.IDynaInstRuntime;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.beans.factory.annotation.Autowired;
import net.ibizsys.runtime.dataentity.DEActions;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

@Aspect
@Order(100)
@Component("BugRuntime")
public class BugRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IBugService bugService;

    @Override
    protected Object getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntity) {
            return o;
        } else {
            return bugService.sysGet((Long)o);
        }
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
    public Object createEntity() {
        return new Bug();
    }

    @Override
    protected IService getService() {
        return this.bugService;
    }

    @Override
    protected QueryWrapperContext createSearchContext() {
        return new BugSearchContext();
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return bugService.create((Bug) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return bugService.update((Bug) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return bugService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                return bugService.get((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return bugService.getDraft((Bug) args[0]);
            }
            else if (iPSDEAction.getName().equals("Activate")) {
                return bugService.activate((Bug) args[0]);
            }
            else if (iPSDEAction.getName().equals("assignTo")) {
                return bugService.assignTo((Bug) args[0]);
            }
            else if (iPSDEAction.getName().equals("BatchUnlinkBug")) {
                return bugService.batchUnlinkBug((Bug) args[0]);
            }
            else if (iPSDEAction.getName().equals("BugFavorites")) {
                return bugService.bugFavorites((Bug) args[0]);
            }
            else if (iPSDEAction.getName().equals("BugNFavorites")) {
                return bugService.bugNFavorites((Bug) args[0]);
            }
            else if (iPSDEAction.getName().equals("buildBatchUnlinkBug")) {
                return bugService.buildBatchUnlinkBug((Bug) args[0]);
            }
            else if (iPSDEAction.getName().equals("buildLinkBug")) {
                return bugService.buildLinkBug((Bug) args[0]);
            }
            else if (iPSDEAction.getName().equals("buildUnlinkBug")) {
                return bugService.buildUnlinkBug((Bug) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return bugService.checkKey((Bug) args[0]);
            }
            else if (iPSDEAction.getName().equals("Close")) {
                return bugService.close((Bug) args[0]);
            }
            else if (iPSDEAction.getName().equals("Confirm")) {
                return bugService.confirm((Bug) args[0]);
            }
            else if (iPSDEAction.getName().equals("LinkBug")) {
                return bugService.linkBug((Bug) args[0]);
            }
            else if (iPSDEAction.getName().equals("ReleaaseBatchUnlinkBug")) {
                return bugService.releaaseBatchUnlinkBug((Bug) args[0]);
            }
            else if (iPSDEAction.getName().equals("ReleaseLinkBugbyBug")) {
                return bugService.releaseLinkBugbyBug((Bug) args[0]);
            }
            else if (iPSDEAction.getName().equals("ReleaseLinkBugbyLeftBug")) {
                return bugService.releaseLinkBugbyLeftBug((Bug) args[0]);
            }
            else if (iPSDEAction.getName().equals("ReleaseUnLinkBugbyLeftBug")) {
                return bugService.releaseUnLinkBugbyLeftBug((Bug) args[0]);
            }
            else if (iPSDEAction.getName().equals("ReleaseUnlinkBug")) {
                return bugService.releaseUnlinkBug((Bug) args[0]);
            }
            else if (iPSDEAction.getName().equals("Resolve")) {
                return bugService.resolve((Bug) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return bugService.save((Bug) args[0]);
            }
            else if (iPSDEAction.getName().equals("sendMessage")) {
                return bugService.sendMessage((Bug) args[0]);
            }
            else if (iPSDEAction.getName().equals("sendMsgPreProcess")) {
                return bugService.sendMsgPreProcess((Bug) args[0]);
            }
            else if (iPSDEAction.getName().equals("toStory")) {
                return bugService.toStory((Bug) args[0]);
            }
            else if (iPSDEAction.getName().equals("UnlinkBug")) {
                return bugService.unlinkBug((Bug) args[0]);
            }
            else if (iPSDEAction.getName().equals("UpdateStoryVersion")) {
                return bugService.updateStoryVersion((Bug) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return bugService.create((Bug) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return bugService.update((Bug) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                return bugService.get((Long) args[0]);
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return bugService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return bugService.sysGet((Long) args[0]);
            }  else if (strActionName.equals(DEActions.SYSUPDATE)) {
                
            }         }
        
        return null;
    }

    @Override
    protected void onExecuteDELogic(Object arg0, IPSDEAction iPSDEAction, IPSDELogic iPSDELogic, IDynaInstRuntime iDynaInstRuntime, ProceedingJoinPoint joinPoint) throws Throwable {
        //执行处理逻辑
    }

    @Override
    protected void onExecuteActionLogics(Object arg0, IPSDEAction iPSDEAction, String strAttachMode, IDynaInstRuntime iDynaInstRuntime, ProceedingJoinPoint joinPoint) throws Throwable {
        //行为附加操作
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

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.BugServiceImpl.*(..))")
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
            return aroundAction("assignTo", point);
        }
        else if (action.equals("batchUnlinkBug")) {
            return aroundAction("BatchUnlinkBug", point);
        }
        else if (action.equals("bugFavorites")) {
            return aroundAction("BugFavorites", point);
        }
        else if (action.equals("bugNFavorites")) {
            return aroundAction("BugNFavorites", point);
        }
        else if (action.equals("buildBatchUnlinkBug")) {
            return aroundAction("buildBatchUnlinkBug", point);
        }
        else if (action.equals("buildLinkBug")) {
            return aroundAction("buildLinkBug", point);
        }
        else if (action.equals("buildUnlinkBug")) {
            return aroundAction("buildUnlinkBug", point);
        }
        else if (action.equals("checkKey")) {
            return aroundAction("CheckKey", point);
        }
        else if (action.equals("close")) {
            return aroundAction("Close", point);
        }
        else if (action.equals("confirm")) {
            return aroundAction("Confirm", point);
        }
        else if (action.equals("linkBug")) {
            return aroundAction("LinkBug", point);
        }
        else if (action.equals("releaaseBatchUnlinkBug")) {
            return aroundAction("ReleaaseBatchUnlinkBug", point);
        }
        else if (action.equals("releaseLinkBugbyBug")) {
            return aroundAction("ReleaseLinkBugbyBug", point);
        }
        else if (action.equals("releaseLinkBugbyLeftBug")) {
            return aroundAction("ReleaseLinkBugbyLeftBug", point);
        }
        else if (action.equals("releaseUnLinkBugbyLeftBug")) {
            return aroundAction("ReleaseUnLinkBugbyLeftBug", point);
        }
        else if (action.equals("releaseUnlinkBug")) {
            return aroundAction("ReleaseUnlinkBug", point);
        }
        else if (action.equals("resolve")) {
            return aroundAction("Resolve", point);
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
        else if (action.equals("toStory")) {
            return aroundAction("toStory", point);
        }
        else if (action.equals("unlinkBug")) {
            return aroundAction("UnlinkBug", point);
        }
        else if (action.equals("updateStoryVersion")) {
            return aroundAction("UpdateStoryVersion", point);
        }
        else if (action.equals("searchAssignedToMyBug")) {
            return aroundDataSet("AssignedToMyBug", point);
        }
        else if (action.equals("searchAssignedToMyBugPc")) {
            return aroundDataSet("AssignedToMyBugPc", point);
        }
        else if (action.equals("searchBugsByBuild")) {
            return aroundDataSet("BugsByBuild", point);
        }
        else if (action.equals("searchBuildBugs")) {
            return aroundDataSet("BuildBugs", point);
        }
        else if (action.equals("searchBuildLinkResolvedBugs")) {
            return aroundDataSet("buildLinkResolvedBugs", point);
        }
        else if (action.equals("searchBuildOpenBugs")) {
            return aroundDataSet("BuildOpenBugs", point);
        }
        else if (action.equals("searchBuildProduceBug")) {
            return aroundDataSet("BuildProduceBug", point);
        }
        else if (action.equals("searchBuildProduceBugModule")) {
            return aroundDataSet("BuildProduceBugModule", point);
        }
        else if (action.equals("searchBuildProduceBugModule_Project")) {
            return aroundDataSet("BuildProduceBugModule_Project", point);
        }
        else if (action.equals("searchBuildProduceBugOpenedBy")) {
            return aroundDataSet("BuildProduceBugOpenedBy", point);
        }
        else if (action.equals("searchBuildProduceBugOpenedBy_Project")) {
            return aroundDataSet("BuildProduceBugOpenedBy_Project", point);
        }
        else if (action.equals("searchBuildProduceBugRES")) {
            return aroundDataSet("BuildProduceBugRES", point);
        }
        else if (action.equals("searchBuildProduceBugRESOLVEDBY")) {
            return aroundDataSet("BuildProduceBugRESOLVEDBY", point);
        }
        else if (action.equals("searchBuildProduceBugRESOLVEDBY_Project")) {
            return aroundDataSet("BuildProduceBugRESOLVEDBY_Project", point);
        }
        else if (action.equals("searchBuildProduceBugResolution_Project")) {
            return aroundDataSet("BuildProduceBugResolution_Project", point);
        }
        else if (action.equals("searchBuildProduceBugSeverity_Project")) {
            return aroundDataSet("BuildProduceBugSeverity_Project", point);
        }
        else if (action.equals("searchBuildProduceBugStatus_Project")) {
            return aroundDataSet("BuildProduceBugStatus_Project", point);
        }
        else if (action.equals("searchBuildProduceBugType_Project")) {
            return aroundDataSet("BuildProduceBugType_Project", point);
        }
        else if (action.equals("searchCurUserResolve")) {
            return aroundDataSet("CurUserResolve", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchESBulk")) {
            return aroundDataSet("ESBulk", point);
        }
        else if (action.equals("searchMyAgentBug")) {
            return aroundDataSet("MyAgentBug", point);
        }
        else if (action.equals("searchMyCurOpenedBug")) {
            return aroundDataSet("MyCurOpenedBug", point);
        }
        else if (action.equals("searchMyFavorites")) {
            return aroundDataSet("MyFavorites", point);
        }
        else if (action.equals("searchNotCurPlanLinkBug")) {
            return aroundDataSet("NotCurPlanLinkBug", point);
        }
        else if (action.equals("searchProjectBugs")) {
            return aroundDataSet("ProjectBugs", point);
        }
        else if (action.equals("searchReleaseBugs")) {
            return aroundDataSet("ReleaseBugs", point);
        }
        else if (action.equals("searchReleaseLeftBugs")) {
            return aroundDataSet("ReleaseLeftBugs", point);
        }
        else if (action.equals("searchReleaseLinkableLeftBug")) {
            return aroundDataSet("ReleaseLinkableLeftBug", point);
        }
        else if (action.equals("searchReleaseLinkableResolvedBug")) {
            return aroundDataSet("ReleaseLinkableResolvedBug", point);
        }
        else if (action.equals("searchReportBugs")) {
            return aroundDataSet("ReportBugs", point);
        }
        else if (action.equals("searchTaskRelatedBug")) {
            return aroundDataSet("TaskBug", point);
        }
        return point.proceed();
    }

}
