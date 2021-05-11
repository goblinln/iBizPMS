package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.ibiz.domain.ProjectTeam;
import cn.ibizlab.pms.core.ibiz.service.IProjectTeamService;
import cn.ibizlab.pms.core.ibiz.filter.ProjectTeamSearchContext;
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
import java.io.Serializable;
import java.util.List;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.alibaba.fastjson.JSONObject;


@Aspect
@org.springframework.core.annotation.Order(100)
@Component("ProjectTeamRuntime")
@Slf4j
public class ProjectTeamRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IProjectTeamService projectteamService;

    @Autowired
    DELogicExecutor logicExecutor;

    @Override
    protected IEntityBase getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntityBase) {
            return (IEntityBase) o;
        } else {
            return projectteamService.sysGet((Long) o);
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/ibiz/PSDATAENTITIES/ProjectTeam.json";
    }

    @Override
    public String getName() {
        return "IBZ_PROJECTTEAM";
    }

    @Override
    public IEntityBase createEntity() {
        return new ProjectTeam();
    }

    @Override
    protected IService getService() {
        return this.projectteamService;
    }

    @Override
    public ProjectTeamSearchContext createSearchContext() {
        return new ProjectTeamSearchContext();
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
            return MAPPER.readValue(String.valueOf(objData),ProjectTeam.class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public IEntityBase[] deserializeEntities(Object objData) {
        try {
            return MAPPER.readValue(String.valueOf(objData),ProjectTeam[].class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public Page<ProjectTeam> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        ProjectTeamSearchContext searchContext = (ProjectTeamSearchContext) iSearchContextBase;
        if (iPSDEDataSet.getName().equals("DEFAULT"))
            return projectteamService.searchDefault(searchContext);    
        if (iPSDEDataSet.getName().equals("ProjectTeamPm"))
            return projectteamService.searchProjectTeamPm(searchContext);    
        if (iPSDEDataSet.getName().equals("RowEditDefault"))
            return projectteamService.searchRowEditDefault(searchContext);    
        if (iPSDEDataSet.getName().equals("TaskCntEstimateConsumedLeft"))
            return projectteamService.searchTaskCntEstimateConsumedLeft(searchContext);    
        return null;
    }

    @Override
    public List<ProjectTeam> selectDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        ProjectTeamSearchContext searchContext = (ProjectTeamSearchContext) iSearchContextBase;
        if (iPSDataQuery.getName().equals("DEFAULT"))
            return projectteamService.selectDefault(searchContext);
        if (iPSDataQuery.getName().equals("ProjectTeamPm"))
            return projectteamService.selectProjectTeamPm(searchContext);
        if (iPSDataQuery.getName().equals("RowEditDefault"))
            return projectteamService.selectRowEditDefault(searchContext);
        if (iPSDataQuery.getName().equals("TaskCntEstimateConsumedLeft"))
            return projectteamService.selectTaskCntEstimateConsumedLeft(searchContext);
        if (iPSDataQuery.getName().equals("VIEW"))
            return projectteamService.selectView(searchContext);
        return null;
    }

    @Override
    public List<ProjectTeam> select(ISearchContextBase iSearchContextBase) {
        ProjectTeamSearchContext searchContext = (ProjectTeamSearchContext) iSearchContextBase;
        return projectteamService.select(searchContext);
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return projectteamService.create((ProjectTeam) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return projectteamService.update((ProjectTeam) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return projectteamService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                if(args[0] instanceof ProjectTeam){
                    ProjectTeam arg = (ProjectTeam) args[0] ;
                    CachedBeanCopier.copy(projectteamService.get(arg.getId()), arg);
                    return arg;
                }else{
                    return projectteamService.get((Long) args[0]);
                }
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return projectteamService.getDraft((ProjectTeam) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return projectteamService.checkKey((ProjectTeam) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetUserRole")) {
                return projectteamService.getUserRole((ProjectTeam) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return projectteamService.save((ProjectTeam) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return projectteamService.create((ProjectTeam) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return projectteamService.update((ProjectTeam) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                if(args[0] instanceof ProjectTeam){
                    ProjectTeam arg = (ProjectTeam) args[0] ;
                    CachedBeanCopier.copy(projectteamService.get(arg.getId()), arg);
                    return arg;
                }else{
                    return projectteamService.get((Long) args[0]);
                }
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return projectteamService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return projectteamService.sysGet((Long) args[0]);
            }  else if (strActionName.equals(DEActions.SYSUPDATE)) {
                
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
        ProjectTeam entity = null;
        String action = iPSDEAction.getName();
        if (arg0 instanceof ProjectTeam) {
            entity = (ProjectTeam) arg0;
        } else {
            if (arg0 != null && "get".equalsIgnoreCase(action)) {
                entity = projectteamService.sysGet((Long) arg0);
            } else if ("remove".equalsIgnoreCase(action)) {
                entity = new ProjectTeam();
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

    @Around("execution(* cn.ibizlab.pms.core.ibiz.service.impl.ProjectTeamServiceImpl.*(..))")
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
        else if (action.equals("checkKey")) {
            return aroundAction("CheckKey", point);
        }
        else if (action.equals("getUserRole")) {
            return aroundAction("GetUserRole", point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchProjectTeamPm")) {
            return aroundDataSet("ProjectTeamPm", point);
        }
        else if (action.equals("searchRowEditDefault")) {
            return aroundDataSet("RowEditDefault", point);
        }
        else if (action.equals("searchTaskCntEstimateConsumedLeft")) {
            return aroundDataSet("TaskCntEstimateConsumedLeft", point);
        }
        return point.proceed();
    }

}
