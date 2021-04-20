package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Story;
import cn.ibizlab.pms.core.zentao.service.IStoryService;
import cn.ibizlab.pms.core.zentao.filter.StorySearchContext;
import  cn.ibizlab.pms.util.filter.QueryWrapperContext;
import com.baomidou.mybatisplus.extension.service.IService;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import java.io.Serializable;
import java.util.List;



@Aspect
@Order(100)
@Component("StoryRuntime")
@Slf4j
public class StoryRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IStoryService storyService;

    @Autowired
    DELogicExecutor logicExecutor;

    @Override
    protected IEntityBase getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntityBase) {
            return (IEntityBase) o;
        } else {
            return storyService.sysGet((Long) o);
        }
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
    public IEntityBase createEntity() {
        return new Story();
    }

    @Override
    protected IService getService() {
        return this.storyService;
    }

    @Override
    public StorySearchContext createSearchContext() {
        return new StorySearchContext();
    }

    @Override
    public boolean existsData(ISearchContextBase iSearchContextBase) {
        Page<Story> domains = storyService.searchDefault((StorySearchContext) iSearchContextBase);
        if (domains.getSize() == 0)
            return false;
        return true;
    }

    @Override
    public Page<Story> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        if (iPSDEDataSet.getName().equals("DEFAULT"))
            return storyService.searchDefault((StorySearchContext) iSearchContextBase);
        return null;
    }

    @Override
    public Page<Story> searchDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        //暂未实现
        return null;
    }

    @Override
    public Story selectOne(ISearchContextBase iSearchContextBase) {
        //单条数据查询，多条数数据时 返回第一条
        Page<Story> domains = storyService.searchDefault((StorySearchContext) iSearchContextBase);
        if (domains.getSize() == 0)
            return null;
        return domains.getContent().get(0);
    }

    @Override
    public List<Story> select(ISearchContextBase iSearchContextBase) {
        return storyService.searchDefault((StorySearchContext) iSearchContextBase).getContent();
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return storyService.create((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return storyService.update((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return storyService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                if(args[0] instanceof Story){
                    Story arg = (Story) args[0] ;
                    arg = storyService.get(arg.getId()) ;
                    return arg;
                }else{
                    return storyService.get((Long) args[0]);
                }
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return storyService.getDraft((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("activate")) {
                return storyService.activate((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("AllPush")) {
                return storyService.allPush((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("AssignTo")) {
                return storyService.assignTo((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("BatchAssignTo")) {
                return storyService.batchAssignTo((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("BatchChangeBranch")) {
                return storyService.batchChangeBranch((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("BatchChangeModule")) {
                return storyService.batchChangeModule((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("BatchChangePlan")) {
                return storyService.batchChangePlan((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("BatchChangeStage")) {
                return storyService.batchChangeStage((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("BatchClose")) {
                return storyService.batchClose((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("BatchReview")) {
                return storyService.batchReview((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("BatchUnlinkStory")) {
                return storyService.batchUnlinkStory((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("BugToStory")) {
                return storyService.bugToStory((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("buildBatchUnlinkStory")) {
                return storyService.buildBatchUnlinkStory((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("buildLinkStory")) {
                return storyService.buildLinkStory((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("buildUnlinkStory")) {
                return storyService.buildUnlinkStory((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("buildUnlinkStorys")) {
                return storyService.buildUnlinkStorys((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("Change")) {
                return storyService.change((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return storyService.checkKey((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("Close")) {
                return storyService.close((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("CreateTasks")) {
                return storyService.createTasks((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetStorySpec")) {
                return storyService.getStorySpec((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetStorySpecs")) {
                return storyService.getStorySpecs((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("importPlanStories")) {
                return storyService.importPlanStories((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("LinkStory")) {
                return storyService.linkStory((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("ProjectBatchUnlinkStory")) {
                return storyService.projectBatchUnlinkStory((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("ProjectLinkStory")) {
                return storyService.projectLinkStory((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("ProjectUnlinkStory")) {
                return storyService.projectUnlinkStory((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("projectUnlinkStorys")) {
                return storyService.projectUnlinkStorys((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("Push")) {
                return storyService.push((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("ReleaseBatchUnlinkStory")) {
                return storyService.releaseBatchUnlinkStory((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("ReleaseLinkStory")) {
                return storyService.releaseLinkStory((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("ReleaseUnlinkStory")) {
                return storyService.releaseUnlinkStory((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("ResetReviewedBy")) {
                return storyService.resetReviewedBy((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("Review")) {
                return storyService.review((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return storyService.save((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("sendMessage")) {
                return storyService.sendMessage((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("sendMsgPreProcess")) {
                return storyService.sendMsgPreProcess((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("StoryFavorites")) {
                return storyService.storyFavorites((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("StoryNFavorites")) {
                return storyService.storyNFavorites((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("SyncFromIbiz")) {
                return storyService.syncFromIbiz((Story) args[0]);
            }
            else if (iPSDEAction.getName().equals("UnlinkStory")) {
                return storyService.unlinkStory((Story) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return storyService.create((Story) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return storyService.update((Story) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                if(args[0] instanceof Story){
                    Story arg = (Story) args[0] ;
                    arg = storyService.get(arg.getId()) ;
                    return arg;
                }else{
                    return storyService.get((Long) args[0]);
                }
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return storyService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return storyService.sysGet((Long) args[0]);
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
            logicExecutor.executeLogic((EntityBase) arg0, iPSDEAction, iPSDELogic, iDynaInstRuntime);
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
        Story entity = null;
        String action = iPSDEAction.getName();
        if (arg0 instanceof Story) {
            entity = (Story) arg0;
        } else {
            if (arg0 != null && "get".equalsIgnoreCase(action)) {
                entity = storyService.sysGet((Long) arg0);
            } else if ("remove".equalsIgnoreCase(action)) {
                entity = new Story();
                entity.setId((Long) arg0);
            }
        }
        if (entity != null) {
            logicExecutor.executeAttachLogic(entity, iPSDEAction, strAttachMode, iDynaInstRuntime);
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

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.StoryServiceImpl.*(..))")
    @Transactional
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
            return aroundAction("activate", point);
        }
        else if (action.equals("allPush")) {
            return aroundAction("AllPush", point);
        }
        else if (action.equals("assignTo")) {
            return aroundAction("AssignTo", point);
        }
        else if (action.equals("batchAssignTo")) {
            return aroundAction("BatchAssignTo", point);
        }
        else if (action.equals("batchChangeBranch")) {
            return aroundAction("BatchChangeBranch", point);
        }
        else if (action.equals("batchChangeModule")) {
            return aroundAction("BatchChangeModule", point);
        }
        else if (action.equals("batchChangePlan")) {
            return aroundAction("BatchChangePlan", point);
        }
        else if (action.equals("batchChangeStage")) {
            return aroundAction("BatchChangeStage", point);
        }
        else if (action.equals("batchClose")) {
            return aroundAction("BatchClose", point);
        }
        else if (action.equals("batchReview")) {
            return aroundAction("BatchReview", point);
        }
        else if (action.equals("batchUnlinkStory")) {
            return aroundAction("BatchUnlinkStory", point);
        }
        else if (action.equals("bugToStory")) {
            return aroundAction("BugToStory", point);
        }
        else if (action.equals("buildBatchUnlinkStory")) {
            return aroundAction("buildBatchUnlinkStory", point);
        }
        else if (action.equals("buildLinkStory")) {
            return aroundAction("buildLinkStory", point);
        }
        else if (action.equals("buildUnlinkStory")) {
            return aroundAction("buildUnlinkStory", point);
        }
        else if (action.equals("buildUnlinkStorys")) {
            return aroundAction("buildUnlinkStorys", point);
        }
        else if (action.equals("change")) {
            return aroundAction("Change", point);
        }
        else if (action.equals("checkKey")) {
            return aroundAction("CheckKey", point);
        }
        else if (action.equals("close")) {
            return aroundAction("Close", point);
        }
        else if (action.equals("createTasks")) {
            return aroundAction("CreateTasks", point);
        }
        else if (action.equals("getStorySpec")) {
            return aroundAction("GetStorySpec", point);
        }
        else if (action.equals("getStorySpecs")) {
            return aroundAction("GetStorySpecs", point);
        }
        else if (action.equals("importPlanStories")) {
            return aroundAction("importPlanStories", point);
        }
        else if (action.equals("linkStory")) {
            return aroundAction("LinkStory", point);
        }
        else if (action.equals("projectBatchUnlinkStory")) {
            return aroundAction("ProjectBatchUnlinkStory", point);
        }
        else if (action.equals("projectLinkStory")) {
            return aroundAction("ProjectLinkStory", point);
        }
        else if (action.equals("projectUnlinkStory")) {
            return aroundAction("ProjectUnlinkStory", point);
        }
        else if (action.equals("projectUnlinkStorys")) {
            return aroundAction("projectUnlinkStorys", point);
        }
        else if (action.equals("push")) {
            return aroundAction("Push", point);
        }
        else if (action.equals("releaseBatchUnlinkStory")) {
            return aroundAction("ReleaseBatchUnlinkStory", point);
        }
        else if (action.equals("releaseLinkStory")) {
            return aroundAction("ReleaseLinkStory", point);
        }
        else if (action.equals("releaseUnlinkStory")) {
            return aroundAction("ReleaseUnlinkStory", point);
        }
        else if (action.equals("resetReviewedBy")) {
            return aroundAction("ResetReviewedBy", point);
        }
        else if (action.equals("review")) {
            return aroundAction("Review", point);
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
        else if (action.equals("storyFavorites")) {
            return aroundAction("StoryFavorites", point);
        }
        else if (action.equals("storyNFavorites")) {
            return aroundAction("StoryNFavorites", point);
        }
        else if (action.equals("syncFromIbiz")) {
            return aroundAction("SyncFromIbiz", point);
        }
        else if (action.equals("unlinkStory")) {
            return aroundAction("UnlinkStory", point);
        }
        else if (action.equals("searchAssignedToMyStory")) {
            return aroundDataSet("AssignedToMyStory", point);
        }
        else if (action.equals("searchAssignedToMyStoryCalendar")) {
            return aroundDataSet("AssignedToMyStoryCalendar", point);
        }
        else if (action.equals("searchBugStory")) {
            return aroundDataSet("BugStory", point);
        }
        else if (action.equals("searchBuildLinkCompletedStories")) {
            return aroundDataSet("buildLinkCompletedStories", point);
        }
        else if (action.equals("searchBuildLinkableStories")) {
            return aroundDataSet("BuildLinkableStories", point);
        }
        else if (action.equals("searchBuildStories")) {
            return aroundDataSet("BuildStories", point);
        }
        else if (action.equals("searchByModule")) {
            return aroundDataSet("ByModule", point);
        }
        else if (action.equals("searchCaseStory")) {
            return aroundDataSet("CaseStory", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchESBulk")) {
            return aroundDataSet("ESBulk", point);
        }
        else if (action.equals("searchGetProductStories")) {
            return aroundDataSet("GetProductStories", point);
        }
        else if (action.equals("searchMyAgentStory")) {
            return aroundDataSet("MyAgentStory", point);
        }
        else if (action.equals("searchMyCurOpenedStory")) {
            return aroundDataSet("MyCurOpenedStory", point);
        }
        else if (action.equals("searchMyFavorites")) {
            return aroundDataSet("MyFavorites", point);
        }
        else if (action.equals("searchNotCurPlanLinkStory")) {
            return aroundDataSet("NotCurPlanLinkStory", point);
        }
        else if (action.equals("searchParentDefault")) {
            return aroundDataSet("ParentDefault", point);
        }
        else if (action.equals("searchParentDefaultQ")) {
            return aroundDataSet("ParentDefaultQ", point);
        }
        else if (action.equals("searchProjectLinkStory")) {
            return aroundDataSet("projectLinkStory", point);
        }
        else if (action.equals("searchProjectStories")) {
            return aroundDataSet("ProjectStories", point);
        }
        else if (action.equals("searchReleaseLinkableStories")) {
            return aroundDataSet("ReleaseLinkableStories", point);
        }
        else if (action.equals("searchReleaseStories")) {
            return aroundDataSet("ReleaseStories", point);
        }
        else if (action.equals("searchReportStories")) {
            return aroundDataSet("ReportStories", point);
        }
        else if (action.equals("searchStoryChild")) {
            return aroundDataSet("StoryChild", point);
        }
        else if (action.equals("searchStoryRelated")) {
            return aroundDataSet("StoryRelated", point);
        }
        else if (action.equals("searchSubStory")) {
            return aroundDataSet("SubStory", point);
        }
        else if (action.equals("searchTaskRelatedStory")) {
            return aroundDataSet("TaskRelatedStory", point);
        }
        else if (action.equals("searchView")) {
            return aroundDataSet("VIEW", point);
        }
        return point.proceed();
    }

}
