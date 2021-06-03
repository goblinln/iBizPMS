package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Story;
import cn.ibizlab.pms.core.zentao.service.IStoryService;
import cn.ibizlab.pms.core.zentao.filter.StorySearchContext;
import cn.ibizlab.pms.util.filter.QueryWrapperContext;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
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
import cn.ibizlab.pms.util.domain.WFInstance;
import net.ibizsys.runtime.dataentity.DataEntityRuntimeException;
import net.ibizsys.runtime.util.IEntityBase;
import net.ibizsys.runtime.util.ISearchContextBase;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import net.ibizsys.runtime.dataentity.action.DEActions;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.DigestUtils;
import java.io.Serializable;
import java.util.List;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.alibaba.fastjson.JSONObject;

@Component
@Slf4j
public class StoryRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime implements IStoryRuntime {

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
    public StorySearchContext createSearchContext() {
        return new StorySearchContext();
    }

    @Override
    public Object serializeEntity(IEntityBase iEntityBase) {
        try {
            return MAPPER.writeValueAsString(iEntityBase);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public Object serializeEntities(IEntityBase[] list) {
        try {
            return MAPPER.writeValueAsString(list);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public IEntityBase deserializeEntity(Object objData) {
        try {
            return MAPPER.readValue(String.valueOf(objData),Story.class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public IEntityBase[] deserializeEntities(Object objData) {
        try {
            return MAPPER.readValue(String.valueOf(objData),Story[].class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public Page<Story> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        StorySearchContext searchContext = (StorySearchContext) iSearchContextBase;
        if (iPSDEDataSet.getName().equals("Account"))
            return storyService.searchAccount(searchContext);    
        if (iPSDEDataSet.getName().equals("AssignedToMyStory"))
            return storyService.searchAssignedToMyStory(searchContext);    
        if (iPSDEDataSet.getName().equals("AssignedToMyStoryCalendar"))
            return storyService.searchAssignedToMyStoryCalendar(searchContext);    
        if (iPSDEDataSet.getName().equals("BugStory"))
            return storyService.searchBugStory(searchContext);    
        if (iPSDEDataSet.getName().equals("buildLinkCompletedStories"))
            return storyService.searchBuildLinkCompletedStories(searchContext);    
        if (iPSDEDataSet.getName().equals("BuildLinkableStories"))
            return storyService.searchBuildLinkableStories(searchContext);    
        if (iPSDEDataSet.getName().equals("BuildStories"))
            return storyService.searchBuildStories(searchContext);    
        if (iPSDEDataSet.getName().equals("ByModule"))
            return storyService.searchByModule(searchContext);    
        if (iPSDEDataSet.getName().equals("CaseStory"))
            return storyService.searchCaseStory(searchContext);    
        if (iPSDEDataSet.getName().equals("ChildMore"))
            return storyService.searchChildMore(searchContext);    
        if (iPSDEDataSet.getName().equals("DEFAULT"))
            return storyService.searchDefault(searchContext);    
        if (iPSDEDataSet.getName().equals("ESBulk"))
            return storyService.searchESBulk(searchContext);    
        if (iPSDEDataSet.getName().equals("GetProductStories"))
            return storyService.searchGetProductStories(searchContext);    
        if (iPSDEDataSet.getName().equals("My"))
            return storyService.searchMy(searchContext);    
        if (iPSDEDataSet.getName().equals("MyAgentStory"))
            return storyService.searchMyAgentStory(searchContext);    
        if (iPSDEDataSet.getName().equals("MyCreateOrPartake"))
            return storyService.searchMyCreateOrPartake(searchContext);    
        if (iPSDEDataSet.getName().equals("MyCurOpenedStory"))
            return storyService.searchMyCurOpenedStory(searchContext);    
        if (iPSDEDataSet.getName().equals("MyFavorites"))
            return storyService.searchMyFavorites(searchContext);    
        if (iPSDEDataSet.getName().equals("MyReProduct"))
            return storyService.searchMyReProduct(searchContext);    
        if (iPSDEDataSet.getName().equals("NotCurPlanLinkStory"))
            return storyService.searchNotCurPlanLinkStory(searchContext);    
        if (iPSDEDataSet.getName().equals("ParentDefault"))
            return storyService.searchParentDefault(searchContext);    
        if (iPSDEDataSet.getName().equals("ParentDefaultQ"))
            return storyService.searchParentDefaultQ(searchContext);    
        if (iPSDEDataSet.getName().equals("projectLinkStory"))
            return storyService.searchProjectLinkStory(searchContext);    
        if (iPSDEDataSet.getName().equals("ProjectStories"))
            return storyService.searchProjectStories(searchContext);    
        if (iPSDEDataSet.getName().equals("ReleaseLinkableStories"))
            return storyService.searchReleaseLinkableStories(searchContext);    
        if (iPSDEDataSet.getName().equals("ReleaseStories"))
            return storyService.searchReleaseStories(searchContext);    
        if (iPSDEDataSet.getName().equals("ReportStories"))
            return storyService.searchReportStories(searchContext);    
        if (iPSDEDataSet.getName().equals("StoryChild"))
            return storyService.searchStoryChild(searchContext);    
        if (iPSDEDataSet.getName().equals("StoryRelated"))
            return storyService.searchStoryRelated(searchContext);    
        if (iPSDEDataSet.getName().equals("SubStory"))
            return storyService.searchSubStory(searchContext);    
        if (iPSDEDataSet.getName().equals("TaskRelatedStory"))
            return storyService.searchTaskRelatedStory(searchContext);    
        if (iPSDEDataSet.getName().equals("VIEW"))
            return storyService.searchView(searchContext);    
        return null;
    }

    @Override
    public List<Story> selectDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        StorySearchContext searchContext = (StorySearchContext) iSearchContextBase;
        if (iPSDataQuery.getName().equals("Account"))
            return storyService.selectAccount(searchContext);
        if (iPSDataQuery.getName().equals("AssignedToMyStory"))
            return storyService.selectAssignedToMyStory(searchContext);
        if (iPSDataQuery.getName().equals("AssignedToMyStoryCalendar"))
            return storyService.selectAssignedToMyStoryCalendar(searchContext);
        if (iPSDataQuery.getName().equals("BugStory"))
            return storyService.selectBugStory(searchContext);
        if (iPSDataQuery.getName().equals("buildLinkCompletedStories"))
            return storyService.selectBuildLinkCompletedStories(searchContext);
        if (iPSDataQuery.getName().equals("BuildLinkableStories"))
            return storyService.selectBuildLinkableStories(searchContext);
        if (iPSDataQuery.getName().equals("BuildStories"))
            return storyService.selectBuildStories(searchContext);
        if (iPSDataQuery.getName().equals("ByModule"))
            return storyService.selectByModule(searchContext);
        if (iPSDataQuery.getName().equals("CaseStory"))
            return storyService.selectCaseStory(searchContext);
        if (iPSDataQuery.getName().equals("ChildMore"))
            return storyService.selectChildMore(searchContext);
        if (iPSDataQuery.getName().equals("DEFAULT"))
            return storyService.selectDefault(searchContext);
        if (iPSDataQuery.getName().equals("ESBulk"))
            return storyService.selectESBulk(searchContext);
        if (iPSDataQuery.getName().equals("GetProductStories"))
            return storyService.selectGetProductStories(searchContext);
        if (iPSDataQuery.getName().equals("My"))
            return storyService.selectMy(searchContext);
        if (iPSDataQuery.getName().equals("MyAgentStory"))
            return storyService.selectMyAgentStory(searchContext);
        if (iPSDataQuery.getName().equals("MyCreateOrPartake"))
            return storyService.selectMyCreateOrPartake(searchContext);
        if (iPSDataQuery.getName().equals("MyCurOpenedStory"))
            return storyService.selectMyCurOpenedStory(searchContext);
        if (iPSDataQuery.getName().equals("MyFavorites"))
            return storyService.selectMyFavorites(searchContext);
        if (iPSDataQuery.getName().equals("MyReProduct"))
            return storyService.selectMyReProduct(searchContext);
        if (iPSDataQuery.getName().equals("NotCurPlanLinkStory"))
            return storyService.selectNotCurPlanLinkStory(searchContext);
        if (iPSDataQuery.getName().equals("ParentDefault"))
            return storyService.selectParentDefault(searchContext);
        if (iPSDataQuery.getName().equals("ParentDefaultQ"))
            return storyService.selectParentDefaultQ(searchContext);
        if (iPSDataQuery.getName().equals("projectLinkStory"))
            return storyService.selectProjectLinkStory(searchContext);
        if (iPSDataQuery.getName().equals("ProjectStories"))
            return storyService.selectProjectStories(searchContext);
        if (iPSDataQuery.getName().equals("ReleaseLinkableStories"))
            return storyService.selectReleaseLinkableStories(searchContext);
        if (iPSDataQuery.getName().equals("ReleaseStories"))
            return storyService.selectReleaseStories(searchContext);
        if (iPSDataQuery.getName().equals("ReportStories"))
            return storyService.selectReportStories(searchContext);
        if (iPSDataQuery.getName().equals("StoryChild"))
            return storyService.selectStoryChild(searchContext);
        if (iPSDataQuery.getName().equals("StoryRelated"))
            return storyService.selectStoryRelated(searchContext);
        if (iPSDataQuery.getName().equals("SubStory"))
            return storyService.selectSubStory(searchContext);
        if (iPSDataQuery.getName().equals("TaskRelatedStory"))
            return storyService.selectTaskRelatedStory(searchContext);
        if (iPSDataQuery.getName().equals("VIEW"))
            return storyService.selectView(searchContext);
        return null;
    }

    @Override
    public List<Story> select(ISearchContextBase iSearchContextBase) {
        StorySearchContext searchContext = (StorySearchContext) iSearchContextBase;
        return storyService.select(searchContext);
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
                    CachedBeanCopier.copy(storyService.get(arg.getId()), arg);
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
            else if (iPSDEAction.getName().equals("setStage")) {
                return storyService.setStage((Story) args[0]);
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
            if (strActionName.equalsIgnoreCase("Create")) {
                return storyService.create((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Update")) {
                return storyService.update((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Remove")) {
                return storyService.remove((Long) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Get")) {
                if(args[0] instanceof Story){
                    Story arg = (Story) args[0] ;
                    CachedBeanCopier.copy(storyService.get(arg.getId()), arg);
                    return arg;
                }else{
                    return storyService.get((Long) args[0]);
                }
            }
            else if (strActionName.equalsIgnoreCase("GetDraft")) {
                return storyService.getDraft((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("activate")) {
                return storyService.activate((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("AllPush")) {
                return storyService.allPush((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("AssignTo")) {
                return storyService.assignTo((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("BatchAssignTo")) {
                return storyService.batchAssignTo((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("BatchChangeBranch")) {
                return storyService.batchChangeBranch((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("BatchChangeModule")) {
                return storyService.batchChangeModule((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("BatchChangePlan")) {
                return storyService.batchChangePlan((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("BatchChangeStage")) {
                return storyService.batchChangeStage((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("BatchClose")) {
                return storyService.batchClose((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("BatchReview")) {
                return storyService.batchReview((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("BatchUnlinkStory")) {
                return storyService.batchUnlinkStory((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("BugToStory")) {
                return storyService.bugToStory((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("buildBatchUnlinkStory")) {
                return storyService.buildBatchUnlinkStory((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("buildLinkStory")) {
                return storyService.buildLinkStory((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("buildUnlinkStory")) {
                return storyService.buildUnlinkStory((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("buildUnlinkStorys")) {
                return storyService.buildUnlinkStorys((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Change")) {
                return storyService.change((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("CheckKey")) {
                return storyService.checkKey((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Close")) {
                return storyService.close((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("CreateTasks")) {
                return storyService.createTasks((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("GetStorySpec")) {
                return storyService.getStorySpec((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("GetStorySpecs")) {
                return storyService.getStorySpecs((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("importPlanStories")) {
                return storyService.importPlanStories((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("LinkStory")) {
                return storyService.linkStory((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("ProjectBatchUnlinkStory")) {
                return storyService.projectBatchUnlinkStory((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("ProjectLinkStory")) {
                return storyService.projectLinkStory((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("ProjectUnlinkStory")) {
                return storyService.projectUnlinkStory((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("projectUnlinkStorys")) {
                return storyService.projectUnlinkStorys((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Push")) {
                return storyService.push((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("ReleaseBatchUnlinkStory")) {
                return storyService.releaseBatchUnlinkStory((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("ReleaseLinkStory")) {
                return storyService.releaseLinkStory((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("ReleaseUnlinkStory")) {
                return storyService.releaseUnlinkStory((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("ResetReviewedBy")) {
                return storyService.resetReviewedBy((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Review")) {
                return storyService.review((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Save")) {
                return storyService.save((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("sendMessage")) {
                return storyService.sendMessage((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("sendMsgPreProcess")) {
                return storyService.sendMsgPreProcess((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("setStage")) {
                return storyService.setStage((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("StoryFavorites")) {
                return storyService.storyFavorites((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("StoryNFavorites")) {
                return storyService.storyNFavorites((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("SyncFromIbiz")) {
                return storyService.syncFromIbiz((Story) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("UnlinkStory")) {
                return storyService.unlinkStory((Story) args[0]);
            }
            else  if (strActionName.equalsIgnoreCase(DEActions.SYSGET)) {
                if(args[0] instanceof Story){
                    Story arg = (Story) args[0] ;
                    CachedBeanCopier.copy(storyService.sysGet(arg.getId()), arg);
                    return arg;
                }else{
                    return storyService.sysGet((Long) args[0]);
                }
            }
            else  if (strActionName.equalsIgnoreCase(DEActions.SYSUPDATE)) {
                    return storyService.sysUpdate((Story) args[0]);
            }             
        }
        
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
    protected void onExecuteDELogic(Object arg0, IPSDEAction iPSDEAction, IPSDELogic iPSDELogic, IDynaInstRuntime iDynaInstRuntime, Object joinPoint) throws Throwable {
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
    protected void onExecuteActionLogics(Object arg0, IPSDEAction iPSDEAction, String strAttachMode, IDynaInstRuntime iDynaInstRuntime, Object joinPoint) throws Throwable {
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

    @Override
    protected void onWFRegister(Object arg0, IPSDEAction iPSDEAction, IPSDEWF iPSDEWF, IDynaInstRuntime iDynaInstRuntime, Object actionData) throws Throwable {
    }

    @Override
    protected void onWFUnregister(Object arg0, IPSDEAction iPSDEAction, IPSDEWF iPSDEWF, IDynaInstRuntime iDynaInstRuntime, Object actionData) throws Throwable {
    }
}
