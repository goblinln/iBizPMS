package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Bug;
import cn.ibizlab.pms.core.zentao.service.IBugService;
import cn.ibizlab.pms.core.zentao.filter.BugSearchContext;
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

@Component("BugRuntime")
@Slf4j
public class BugRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime implements IBugRuntime {

    @Autowired
    IBugService bugService;

    @Autowired
    DELogicExecutor logicExecutor;

    @Override
    protected IEntityBase getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntityBase) {
            return (IEntityBase) o;
        } else {
            return bugService.sysGet((Long) o);
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
    public IEntityBase createEntity() {
        return new Bug();
    }

    @Override
    public BugSearchContext createSearchContext() {
        return new BugSearchContext();
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
            return MAPPER.readValue(String.valueOf(objData),Bug.class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public IEntityBase[] deserializeEntities(Object objData) {
        try {
            return MAPPER.readValue(String.valueOf(objData),Bug[].class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public Page<Bug> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        BugSearchContext searchContext = (BugSearchContext) iSearchContextBase;
        if (iPSDEDataSet.getName().equals("Account"))
            return bugService.searchAccount(searchContext);    
        if (iPSDEDataSet.getName().equals("AssignedToMyBug"))
            return bugService.searchAssignedToMyBug(searchContext);    
        if (iPSDEDataSet.getName().equals("AssignedToMyBugPc"))
            return bugService.searchAssignedToMyBugPc(searchContext);    
        if (iPSDEDataSet.getName().equals("BugsByBuild"))
            return bugService.searchBugsByBuild(searchContext);    
        if (iPSDEDataSet.getName().equals("BuildBugs"))
            return bugService.searchBuildBugs(searchContext);    
        if (iPSDEDataSet.getName().equals("buildLinkResolvedBugs"))
            return bugService.searchBuildLinkResolvedBugs(searchContext);    
        if (iPSDEDataSet.getName().equals("BuildOpenBugs"))
            return bugService.searchBuildOpenBugs(searchContext);    
        if (iPSDEDataSet.getName().equals("BuildProduceBug"))
            return bugService.searchBuildProduceBug(searchContext);    
        if (iPSDEDataSet.getName().equals("BuildProduceBugModule"))
            return bugService.searchBuildProduceBugModule(searchContext);    
        if (iPSDEDataSet.getName().equals("BuildProduceBugModule_Project"))
            return bugService.searchBuildProduceBugModule_Project(searchContext);    
        if (iPSDEDataSet.getName().equals("BuildProduceBugOpenedBy"))
            return bugService.searchBuildProduceBugOpenedBy(searchContext);    
        if (iPSDEDataSet.getName().equals("BuildProduceBugOpenedBy_Project"))
            return bugService.searchBuildProduceBugOpenedBy_Project(searchContext);    
        if (iPSDEDataSet.getName().equals("BuildProduceBugRES"))
            return bugService.searchBuildProduceBugRES(searchContext);    
        if (iPSDEDataSet.getName().equals("BuildProduceBugRESOLVEDBY"))
            return bugService.searchBuildProduceBugRESOLVEDBY(searchContext);    
        if (iPSDEDataSet.getName().equals("BuildProduceBugRESOLVEDBY_Project"))
            return bugService.searchBuildProduceBugRESOLVEDBY_Project(searchContext);    
        if (iPSDEDataSet.getName().equals("BuildProduceBugResolution_Project"))
            return bugService.searchBuildProduceBugResolution_Project(searchContext);    
        if (iPSDEDataSet.getName().equals("BuildProduceBugSeverity_Project"))
            return bugService.searchBuildProduceBugSeverity_Project(searchContext);    
        if (iPSDEDataSet.getName().equals("BuildProduceBugStatus_Project"))
            return bugService.searchBuildProduceBugStatus_Project(searchContext);    
        if (iPSDEDataSet.getName().equals("BuildProduceBugType_Project"))
            return bugService.searchBuildProduceBugType_Project(searchContext);    
        if (iPSDEDataSet.getName().equals("CurUserResolve"))
            return bugService.searchCurUserResolve(searchContext);    
        if (iPSDEDataSet.getName().equals("DEFAULT"))
            return bugService.searchDefault(searchContext);    
        if (iPSDEDataSet.getName().equals("ESBulk"))
            return bugService.searchESBulk(searchContext);    
        if (iPSDEDataSet.getName().equals("My"))
            return bugService.searchMy(searchContext);    
        if (iPSDEDataSet.getName().equals("MyAgentBug"))
            return bugService.searchMyAgentBug(searchContext);    
        if (iPSDEDataSet.getName().equals("MyCreateOrPartake"))
            return bugService.searchMyCreateOrPartake(searchContext);    
        if (iPSDEDataSet.getName().equals("MyCurOpenedBug"))
            return bugService.searchMyCurOpenedBug(searchContext);    
        if (iPSDEDataSet.getName().equals("MyFavorites"))
            return bugService.searchMyFavorites(searchContext);    
        if (iPSDEDataSet.getName().equals("MyReProduct"))
            return bugService.searchMyReProduct(searchContext);    
        if (iPSDEDataSet.getName().equals("NotCurPlanLinkBug"))
            return bugService.searchNotCurPlanLinkBug(searchContext);    
        if (iPSDEDataSet.getName().equals("ProductBugDS"))
            return bugService.searchProductBugDS(searchContext);    
        if (iPSDEDataSet.getName().equals("ProjectBugDS"))
            return bugService.searchProjectBugDS(searchContext);    
        if (iPSDEDataSet.getName().equals("ProjectBugs"))
            return bugService.searchProjectBugs(searchContext);    
        if (iPSDEDataSet.getName().equals("ReleaseBugs"))
            return bugService.searchReleaseBugs(searchContext);    
        if (iPSDEDataSet.getName().equals("ReleaseLeftBugs"))
            return bugService.searchReleaseLeftBugs(searchContext);    
        if (iPSDEDataSet.getName().equals("ReleaseLinkableLeftBug"))
            return bugService.searchReleaseLinkableLeftBug(searchContext);    
        if (iPSDEDataSet.getName().equals("ReleaseLinkableResolvedBug"))
            return bugService.searchReleaseLinkableResolvedBug(searchContext);    
        if (iPSDEDataSet.getName().equals("ReportBugs"))
            return bugService.searchReportBugs(searchContext);    
        if (iPSDEDataSet.getName().equals("StoryFormBug"))
            return bugService.searchStoryFormBug(searchContext);    
        if (iPSDEDataSet.getName().equals("TaskBug"))
            return bugService.searchTaskRelatedBug(searchContext);    
        return null;
    }

    @Override
    public List<Bug> selectDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        BugSearchContext searchContext = (BugSearchContext) iSearchContextBase;
        if (iPSDataQuery.getName().equals("Account"))
            return bugService.selectAccount(searchContext);
        if (iPSDataQuery.getName().equals("AssignedToMyBug"))
            return bugService.selectAssignedToMyBug(searchContext);
        if (iPSDataQuery.getName().equals("AssignedToMyBugPc"))
            return bugService.selectAssignedToMyBugPc(searchContext);
        if (iPSDataQuery.getName().equals("BuildBugs"))
            return bugService.selectBuildBugs(searchContext);
        if (iPSDataQuery.getName().equals("buildLinkResolvedBugs"))
            return bugService.selectBuildLinkResolvedBugs(searchContext);
        if (iPSDataQuery.getName().equals("BuildOpenBugs"))
            return bugService.selectBuildOpenBugs(searchContext);
        if (iPSDataQuery.getName().equals("BuildProduceBug"))
            return bugService.selectBuildProduceBug(searchContext);
        if (iPSDataQuery.getName().equals("BuildProduceBugModule"))
            return bugService.selectBuildProduceBugModule(searchContext);
        if (iPSDataQuery.getName().equals("BuildProduceBugModule_Project"))
            return bugService.selectBuildProduceBugModule_Project(searchContext);
        if (iPSDataQuery.getName().equals("BuildProduceBugOpenedBy"))
            return bugService.selectBuildProduceBugOpenedBy(searchContext);
        if (iPSDataQuery.getName().equals("BuildProduceBugOpenedBy_Project"))
            return bugService.selectBuildProduceBugOpenedBy_Project(searchContext);
        if (iPSDataQuery.getName().equals("BuildProduceBugRES"))
            return bugService.selectBuildProduceBugRES(searchContext);
        if (iPSDataQuery.getName().equals("BuildProduceBugRESOLVEDBY"))
            return bugService.selectBuildProduceBugRESOLVEDBY(searchContext);
        if (iPSDataQuery.getName().equals("BuildProduceBugRESOLVEDBY_Project"))
            return bugService.selectBuildProduceBugRESOLVEDBY_Project(searchContext);
        if (iPSDataQuery.getName().equals("BuildProduceBugResolution_Project"))
            return bugService.selectBuildProduceBugResolution_Project(searchContext);
        if (iPSDataQuery.getName().equals("BuildProduceBugSeverity_Project"))
            return bugService.selectBuildProduceBugSeverity_Project(searchContext);
        if (iPSDataQuery.getName().equals("BuildProduceBugStatus_Project"))
            return bugService.selectBuildProduceBugStatus_Project(searchContext);
        if (iPSDataQuery.getName().equals("BuildProduceBugType_Project"))
            return bugService.selectBuildProduceBugType_Project(searchContext);
        if (iPSDataQuery.getName().equals("CurUserResolve"))
            return bugService.selectCurUserResolve(searchContext);
        if (iPSDataQuery.getName().equals("DEFAULT"))
            return bugService.selectDefault(searchContext);
        if (iPSDataQuery.getName().equals("ESBulk"))
            return bugService.selectESBulk(searchContext);
        if (iPSDataQuery.getName().equals("My"))
            return bugService.selectMy(searchContext);
        if (iPSDataQuery.getName().equals("MyAgentBug"))
            return bugService.selectMyAgentBug(searchContext);
        if (iPSDataQuery.getName().equals("MyCreateOrPartake"))
            return bugService.selectMyCreateOrPartake(searchContext);
        if (iPSDataQuery.getName().equals("MyCurOpenedBug"))
            return bugService.selectMyCurOpenedBug(searchContext);
        if (iPSDataQuery.getName().equals("MyFavorites"))
            return bugService.selectMyFavorites(searchContext);
        if (iPSDataQuery.getName().equals("MyReProduct"))
            return bugService.selectMyReProduct(searchContext);
        if (iPSDataQuery.getName().equals("NotCurPlanLinkBug"))
            return bugService.selectNotCurPlanLinkBug(searchContext);
        if (iPSDataQuery.getName().equals("ReleaseBugs"))
            return bugService.selectReleaseBugs(searchContext);
        if (iPSDataQuery.getName().equals("ReleaseLeftBugs"))
            return bugService.selectReleaseLeftBugs(searchContext);
        if (iPSDataQuery.getName().equals("ReleaseLinkableLeftBug"))
            return bugService.selectReleaseLinkableLeftBug(searchContext);
        if (iPSDataQuery.getName().equals("ReleaseLinkableResolvedBug"))
            return bugService.selectReleaseLinkableResolvedBug(searchContext);
        if (iPSDataQuery.getName().equals("ReportBugs"))
            return bugService.selectReportBugs(searchContext);
        if (iPSDataQuery.getName().equals("SelectBugByBuild"))
            return bugService.selectSelectBugByBuild(searchContext);
        if (iPSDataQuery.getName().equals("SelectBugsByProject"))
            return bugService.selectSelectBugsByProject(searchContext);
        if (iPSDataQuery.getName().equals("SIMPLE"))
            return bugService.selectSimple(searchContext);
        if (iPSDataQuery.getName().equals("StoryFormBug"))
            return bugService.selectStoryFormBug(searchContext);
        if (iPSDataQuery.getName().equals("TaskBug"))
            return bugService.selectTaskBug(searchContext);
        if (iPSDataQuery.getName().equals("VIEW"))
            return bugService.selectView(searchContext);
        return null;
    }

    @Override
    public List<Bug> select(ISearchContextBase iSearchContextBase) {
        BugSearchContext searchContext = (BugSearchContext) iSearchContextBase;
        return bugService.select(searchContext);
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
                if(args[0] instanceof Bug){
                    Bug arg = (Bug) args[0] ;
                    CachedBeanCopier.copy(bugService.get(arg.getId()), arg);
                    return arg;
                }else{
                    return bugService.get((Long) args[0]);
                }
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
            else if (iPSDEAction.getName().equals("TestScript")) {
                return bugService.testScript((Bug) args[0]);
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
            if (strActionName.equalsIgnoreCase("Create")) {
                return bugService.create((Bug) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Update")) {
                return bugService.update((Bug) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Remove")) {
                return bugService.remove((Long) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Get")) {
                if(args[0] instanceof Bug){
                    Bug arg = (Bug) args[0] ;
                    CachedBeanCopier.copy(bugService.get(arg.getId()), arg);
                    return arg;
                }else{
                    return bugService.get((Long) args[0]);
                }
            }
            else if (strActionName.equalsIgnoreCase("GetDraft")) {
                return bugService.getDraft((Bug) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Activate")) {
                return bugService.activate((Bug) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("assignTo")) {
                return bugService.assignTo((Bug) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("BatchUnlinkBug")) {
                return bugService.batchUnlinkBug((Bug) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("BugFavorites")) {
                return bugService.bugFavorites((Bug) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("BugNFavorites")) {
                return bugService.bugNFavorites((Bug) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("buildBatchUnlinkBug")) {
                return bugService.buildBatchUnlinkBug((Bug) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("buildLinkBug")) {
                return bugService.buildLinkBug((Bug) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("buildUnlinkBug")) {
                return bugService.buildUnlinkBug((Bug) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("CheckKey")) {
                return bugService.checkKey((Bug) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Close")) {
                return bugService.close((Bug) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Confirm")) {
                return bugService.confirm((Bug) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("LinkBug")) {
                return bugService.linkBug((Bug) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("ReleaaseBatchUnlinkBug")) {
                return bugService.releaaseBatchUnlinkBug((Bug) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("ReleaseLinkBugbyBug")) {
                return bugService.releaseLinkBugbyBug((Bug) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("ReleaseLinkBugbyLeftBug")) {
                return bugService.releaseLinkBugbyLeftBug((Bug) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("ReleaseUnLinkBugbyLeftBug")) {
                return bugService.releaseUnLinkBugbyLeftBug((Bug) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("ReleaseUnlinkBug")) {
                return bugService.releaseUnlinkBug((Bug) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Resolve")) {
                return bugService.resolve((Bug) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Save")) {
                return bugService.save((Bug) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("sendMessage")) {
                return bugService.sendMessage((Bug) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("sendMsgPreProcess")) {
                return bugService.sendMsgPreProcess((Bug) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("TestScript")) {
                return bugService.testScript((Bug) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("toStory")) {
                return bugService.toStory((Bug) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("UnlinkBug")) {
                return bugService.unlinkBug((Bug) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("UpdateStoryVersion")) {
                return bugService.updateStoryVersion((Bug) args[0]);
            }
            else  if (strActionName.equalsIgnoreCase(DEActions.SYSGET)) {
                if(args[0] instanceof Bug){
                    Bug arg = (Bug) args[0] ;
                    CachedBeanCopier.copy(bugService.sysGet(arg.getId()), arg);
                    return arg;
                }else{
                    return bugService.sysGet((Long) args[0]);
                }
            }
            else  if (strActionName.equalsIgnoreCase(DEActions.SYSUPDATE)) {
                    return bugService.sysUpdate((Bug) args[0]);
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
        Bug entity = null;
        String action = iPSDEAction.getName();
        if (arg0 instanceof Bug) {
            entity = (Bug) arg0;
        } else {
            if (arg0 != null && "get".equalsIgnoreCase(action)) {
                entity = bugService.sysGet((Long) arg0);
            } else if ("remove".equalsIgnoreCase(action)) {
                entity = new Bug();
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
