package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Project;
import cn.ibizlab.pms.core.zentao.service.IProjectService;
import cn.ibizlab.pms.core.zentao.filter.ProjectSearchContext;
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

@Component("ProjectRuntime")
@Slf4j
public class ProjectRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime implements IProjectRuntime {

    @Autowired
    IProjectService projectService;

    @Autowired
    DELogicExecutor logicExecutor;

    @Override
    protected IEntityBase getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntityBase) {
            return (IEntityBase) o;
        } else {
            return projectService.sysGet((Long) o);
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/Project.json";
    }

    @Override
    public String getName() {
        return "ZT_PROJECT";
    }

    @Override
    public IEntityBase createEntity() {
        return new Project();
    }

    @Override
    public ProjectSearchContext createSearchContext() {
        return new ProjectSearchContext();
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
            return MAPPER.readValue(String.valueOf(objData),Project.class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public IEntityBase[] deserializeEntities(Object objData) {
        try {
            return MAPPER.readValue(String.valueOf(objData),Project[].class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public Page<Project> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        ProjectSearchContext searchContext = (ProjectSearchContext) iSearchContextBase;
        if (iPSDEDataSet.getName().equals("Account"))
            return projectService.searchAccount(searchContext);    
        if (iPSDEDataSet.getName().equals("BugProject"))
            return projectService.searchBugProject(searchContext);    
        if (iPSDEDataSet.getName().equals("CurDefaultQuery"))
            return projectService.searchCurDefaultQuery(searchContext);    
        if (iPSDEDataSet.getName().equals("CurDefaultQueryExp"))
            return projectService.searchCurDefaultQueryExp(searchContext);    
        if (iPSDEDataSet.getName().equals("CurPlanProject"))
            return projectService.searchCurPlanProject(searchContext);    
        if (iPSDEDataSet.getName().equals("CurProduct"))
            return projectService.searchCurProduct(searchContext);    
        if (iPSDEDataSet.getName().equals("CurUser"))
            return projectService.searchCurUser(searchContext);    
        if (iPSDEDataSet.getName().equals("CurUserSa"))
            return projectService.searchCurUserSa(searchContext);    
        if (iPSDEDataSet.getName().equals("DEFAULT"))
            return projectService.searchDefault(searchContext);    
        if (iPSDEDataSet.getName().equals("DeveloperQuery"))
            return projectService.searchDeveloperQuery(searchContext);    
        if (iPSDEDataSet.getName().equals("ESBulk"))
            return projectService.searchESBulk(searchContext);    
        if (iPSDEDataSet.getName().equals("InvolvedProject"))
            return projectService.searchInvolvedProject(searchContext);    
        if (iPSDEDataSet.getName().equals("InvolvedProject_StoryTaskBug"))
            return projectService.searchInvolvedProject_StoryTaskBug(searchContext);    
        if (iPSDEDataSet.getName().equals("My"))
            return projectService.searchMy(searchContext);    
        if (iPSDEDataSet.getName().equals("MyProject"))
            return projectService.searchMyProject(searchContext);    
        if (iPSDEDataSet.getName().equals("OpenByQuery"))
            return projectService.searchOpenByQuery(searchContext);    
        if (iPSDEDataSet.getName().equals("OpenQuery"))
            return projectService.searchOpenQuery(searchContext);    
        if (iPSDEDataSet.getName().equals("ProjectTeam"))
            return projectService.searchProjectTeam(searchContext);    
        if (iPSDEDataSet.getName().equals("StoryProject"))
            return projectService.searchStoryProject(searchContext);    
        if (iPSDEDataSet.getName().equals("UnDoneProject"))
            return projectService.searchUnDoneProject(searchContext);    
        return null;
    }

    @Override
    public List<Project> selectDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        ProjectSearchContext searchContext = (ProjectSearchContext) iSearchContextBase;
        if (iPSDataQuery.getName().equals("Account"))
            return projectService.selectAccount(searchContext);
        if (iPSDataQuery.getName().equals("BugSelectableProjectList"))
            return projectService.selectBugSelectableProjectList(searchContext);
        if (iPSDataQuery.getName().equals("CurDefaultQuery"))
            return projectService.selectCurDefaultQuery(searchContext);
        if (iPSDataQuery.getName().equals("CurDefaultQueryExp"))
            return projectService.selectCurDefaultQueryExp(searchContext);
        if (iPSDataQuery.getName().equals("CurPlanProject"))
            return projectService.selectCurPlanProject(searchContext);
        if (iPSDataQuery.getName().equals("CurProduct"))
            return projectService.selectCurProduct(searchContext);
        if (iPSDataQuery.getName().equals("CurUser"))
            return projectService.selectCurUser(searchContext);
        if (iPSDataQuery.getName().equals("CurUserSa"))
            return projectService.selectCurUserSa(searchContext);
        if (iPSDataQuery.getName().equals("DEFAULT"))
            return projectService.selectDefault(searchContext);
        if (iPSDataQuery.getName().equals("DeveloperQuery"))
            return projectService.selectDeveloperQuery(searchContext);
        if (iPSDataQuery.getName().equals("ESBulk"))
            return projectService.selectESBulk(searchContext);
        if (iPSDataQuery.getName().equals("InvolvedProject"))
            return projectService.selectInvolvedProject(searchContext);
        if (iPSDataQuery.getName().equals("InvolvedProjectStoryTaskBug"))
            return projectService.selectInvolvedProjectStoryTaskBug(searchContext);
        if (iPSDataQuery.getName().equals("My"))
            return projectService.selectMy(searchContext);
        if (iPSDataQuery.getName().equals("MyProject"))
            return projectService.selectMyProject(searchContext);
        if (iPSDataQuery.getName().equals("OpenByQuery"))
            return projectService.selectOpenByQuery(searchContext);
        if (iPSDataQuery.getName().equals("OpenQuery"))
            return projectService.selectOpenQuery(searchContext);
        if (iPSDataQuery.getName().equals("ProjectTeam"))
            return projectService.selectProjectTeam(searchContext);
        if (iPSDataQuery.getName().equals("StoryProject"))
            return projectService.selectStoryProject(searchContext);
        if (iPSDataQuery.getName().equals("UnDoneProject"))
            return projectService.selectUnDoneProject(searchContext);
        if (iPSDataQuery.getName().equals("VIEW"))
            return projectService.selectView(searchContext);
        return null;
    }

    @Override
    public List<Project> select(ISearchContextBase iSearchContextBase) {
        ProjectSearchContext searchContext = (ProjectSearchContext) iSearchContextBase;
        return projectService.select(searchContext);
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return projectService.create((Project) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return projectService.update((Project) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return projectService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                if(args[0] instanceof Project){
                    Project arg = (Project) args[0] ;
                    CachedBeanCopier.copy(projectService.get(arg.getId()), arg);
                    return arg;
                }else{
                    return projectService.get((Long) args[0]);
                }
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return projectService.getDraft((Project) args[0]);
            }
            else if (iPSDEAction.getName().equals("Activate")) {
                return projectService.activate((Project) args[0]);
            }
            else if (iPSDEAction.getName().equals("BatchUnlinkStory")) {
                return projectService.batchUnlinkStory((Project) args[0]);
            }
            else if (iPSDEAction.getName().equals("CancelProjectTop")) {
                return projectService.cancelProjectTop((Project) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return projectService.checkKey((Project) args[0]);
            }
            else if (iPSDEAction.getName().equals("Close")) {
                return projectService.close((Project) args[0]);
            }
            else if (iPSDEAction.getName().equals("importPlanStories")) {
                return projectService.importPlanStories((Project) args[0]);
            }
            else if (iPSDEAction.getName().equals("LinkProduct")) {
                return projectService.linkProduct((Project) args[0]);
            }
            else if (iPSDEAction.getName().equals("LinkStory")) {
                return projectService.linkStory((Project) args[0]);
            }
            else if (iPSDEAction.getName().equals("ManageMembers")) {
                return projectService.manageMembers((Project) args[0]);
            }
            else if (iPSDEAction.getName().equals("MobProjectCount")) {
                return projectService.mobProjectCount((Project) args[0]);
            }
            else if (iPSDEAction.getName().equals("PmsEeProjectAllTaskCount")) {
                return projectService.pmsEeProjectAllTaskCount((Project) args[0]);
            }
            else if (iPSDEAction.getName().equals("PmsEeProjectTodoTaskCount")) {
                return projectService.pmsEeProjectTodoTaskCount((Project) args[0]);
            }
            else if (iPSDEAction.getName().equals("ProjectTaskQCnt")) {
                return projectService.projectTaskQCnt((Project) args[0]);
            }
            else if (iPSDEAction.getName().equals("projectTop")) {
                return projectService.projectTop((Project) args[0]);
            }
            else if (iPSDEAction.getName().equals("Putoff")) {
                return projectService.putoff((Project) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return projectService.save((Project) args[0]);
            }
            else if (iPSDEAction.getName().equals("Start")) {
                return projectService.start((Project) args[0]);
            }
            else if (iPSDEAction.getName().equals("Suspend")) {
                return projectService.suspend((Project) args[0]);
            }
            else if (iPSDEAction.getName().equals("UnlinkMember")) {
                return projectService.unlinkMember((Project) args[0]);
            }
            else if (iPSDEAction.getName().equals("UnlinkProduct")) {
                return projectService.unlinkProduct((Project) args[0]);
            }
            else if (iPSDEAction.getName().equals("UnlinkStory")) {
                return projectService.unlinkStory((Project) args[0]);
            }
            else if (iPSDEAction.getName().equals("UpdateOrder")) {
                return projectService.updateOrder((Project) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equalsIgnoreCase("Create")) {
                return projectService.create((Project) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Update")) {
                return projectService.update((Project) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Remove")) {
                return projectService.remove((Long) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Get")) {
                if(args[0] instanceof Project){
                    Project arg = (Project) args[0] ;
                    CachedBeanCopier.copy(projectService.get(arg.getId()), arg);
                    return arg;
                }else{
                    return projectService.get((Long) args[0]);
                }
            }
            else if (strActionName.equalsIgnoreCase("GetDraft")) {
                return projectService.getDraft((Project) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Activate")) {
                return projectService.activate((Project) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("BatchUnlinkStory")) {
                return projectService.batchUnlinkStory((Project) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("CancelProjectTop")) {
                return projectService.cancelProjectTop((Project) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("CheckKey")) {
                return projectService.checkKey((Project) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Close")) {
                return projectService.close((Project) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("importPlanStories")) {
                return projectService.importPlanStories((Project) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("LinkProduct")) {
                return projectService.linkProduct((Project) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("LinkStory")) {
                return projectService.linkStory((Project) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("ManageMembers")) {
                return projectService.manageMembers((Project) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("MobProjectCount")) {
                return projectService.mobProjectCount((Project) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("PmsEeProjectAllTaskCount")) {
                return projectService.pmsEeProjectAllTaskCount((Project) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("PmsEeProjectTodoTaskCount")) {
                return projectService.pmsEeProjectTodoTaskCount((Project) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("ProjectTaskQCnt")) {
                return projectService.projectTaskQCnt((Project) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("projectTop")) {
                return projectService.projectTop((Project) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Putoff")) {
                return projectService.putoff((Project) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Save")) {
                return projectService.save((Project) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Start")) {
                return projectService.start((Project) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Suspend")) {
                return projectService.suspend((Project) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("UnlinkMember")) {
                return projectService.unlinkMember((Project) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("UnlinkProduct")) {
                return projectService.unlinkProduct((Project) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("UnlinkStory")) {
                return projectService.unlinkStory((Project) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("UpdateOrder")) {
                return projectService.updateOrder((Project) args[0]);
            }
            else  if (strActionName.equalsIgnoreCase(DEActions.SYSGET)) {
                if(args[0] instanceof Project){
                    Project arg = (Project) args[0] ;
                    CachedBeanCopier.copy(projectService.sysGet(arg.getId()), arg);
                    return arg;
                }else{
                    return projectService.sysGet((Long) args[0]);
                }
            }
            else  if (strActionName.equalsIgnoreCase(DEActions.SYSUPDATE)) {
                    return projectService.sysUpdate((Project) args[0]);
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
        Project entity = null;
        String action = iPSDEAction.getName();
        if (arg0 instanceof Project) {
            entity = (Project) arg0;
        } else {
            if (arg0 != null && "get".equalsIgnoreCase(action)) {
                entity = projectService.sysGet((Long) arg0);
            } else if ("remove".equalsIgnoreCase(action)) {
                entity = new Project();
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
