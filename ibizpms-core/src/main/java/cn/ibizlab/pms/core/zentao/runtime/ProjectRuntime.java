package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Project;
import cn.ibizlab.pms.core.zentao.service.IProjectService;
import cn.ibizlab.pms.core.zentao.filter.ProjectSearchContext;
import  cn.ibizlab.pms.util.filter.QueryWrapperContext;
import com.baomidou.mybatisplus.extension.service.IService;
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
import org.springframework.util.ObjectUtils;
import java.io.Serializable;
import java.util.List;



@Aspect
@Order(100)
@Component("ProjectRuntime")
public class ProjectRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IProjectService projectService;

    @Override
    protected Object getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntity) {
            return o;
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
    public Object createEntity() {
        return new Project();
    }

    @Override
    protected IService getService() {
        return this.projectService;
    }

    @Override
    public ProjectSearchContext createSearchContext() {
        return new ProjectSearchContext();
    }

    @Override
    public void setSearchCondition(Object searchContext, IPSDEField iPSDEField, String strCondition, Object objValue) {
        //设置查询条件 net.ibizsys.runtime.util.Conditions
    }

    @Override
    public boolean existsData(Object searchContext) {
        //判断数据是否存在
        return false;
    }

    @Override
    public Page<?> searchDataSet(IPSDEDataSet iPSDEDataSet, Object searchContext) {
        //查询数据集合
        return null;
    }

    @Override
    public Page<?> searchDataQuery(IPSDEDataQuery iPSDataQuery, Object searchContext) {
        //暂未实现
        return null;
    }

    @Override
    public Object selectOne(Object searchContext) {
        //单条数据查询，多条数数据时 返回第一条
        return null;
    }

    @Override
    public List<?> select(Object searchContext) {
        //list
        return null;
    }

    @Override
    protected void fillEntityFullInfo(Object arg0, String strActionName, IPSDEAction iPSDEAction, IPSDER1N iPSDER1N, IPSDataEntity iPSDataEntity, ProceedingJoinPoint joinPoint) throws Throwable {
        Object objPickupValue = this.getFieldValue(arg0, iPSDER1N.getPSPickupDEField());
        if (ObjectUtils.isEmpty(objPickupValue) || NumberUtils.toLong(String.valueOf(objPickupValue), 0L) == 0L)
            return;
        super.fillEntityFullInfo(arg0, strActionName, iPSDEAction, iPSDER1N, iPSDataEntity, joinPoint);
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
                    arg = projectService.get(arg.getId()) ;
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
            else if (iPSDEAction.getName().equals("UnlinkStory")) {
                return projectService.unlinkStory((Project) args[0]);
            }
            else if (iPSDEAction.getName().equals("UpdateOrder")) {
                return projectService.updateOrder((Project) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return projectService.create((Project) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return projectService.update((Project) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                if(args[0] instanceof Project){
                    Project arg = (Project) args[0] ;
                    arg = projectService.get(arg.getId()) ;
                    return arg;
                }else{
                    return projectService.get((Long) args[0]);
                }
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return projectService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return projectService.sysGet((Long) args[0]);
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
            DELogicExecutor.getInstance().executeLogic((EntityBase) arg0, iPSDEAction, iPSDELogic, iDynaInstRuntime);
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
            DELogicExecutor.getInstance().executeAttachLogic(entity, iPSDEAction, strAttachMode, iDynaInstRuntime);
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

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.ProjectServiceImpl.*(..))")
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
        else if (action.equals("batchUnlinkStory")) {
            return aroundAction("BatchUnlinkStory", point);
        }
        else if (action.equals("cancelProjectTop")) {
            return aroundAction("CancelProjectTop", point);
        }
        else if (action.equals("checkKey")) {
            return aroundAction("CheckKey", point);
        }
        else if (action.equals("close")) {
            return aroundAction("Close", point);
        }
        else if (action.equals("linkStory")) {
            return aroundAction("LinkStory", point);
        }
        else if (action.equals("manageMembers")) {
            return aroundAction("ManageMembers", point);
        }
        else if (action.equals("mobProjectCount")) {
            return aroundAction("MobProjectCount", point);
        }
        else if (action.equals("pmsEeProjectAllTaskCount")) {
            return aroundAction("PmsEeProjectAllTaskCount", point);
        }
        else if (action.equals("pmsEeProjectTodoTaskCount")) {
            return aroundAction("PmsEeProjectTodoTaskCount", point);
        }
        else if (action.equals("projectTaskQCnt")) {
            return aroundAction("ProjectTaskQCnt", point);
        }
        else if (action.equals("projectTop")) {
            return aroundAction("projectTop", point);
        }
        else if (action.equals("putoff")) {
            return aroundAction("Putoff", point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("start")) {
            return aroundAction("Start", point);
        }
        else if (action.equals("suspend")) {
            return aroundAction("Suspend", point);
        }
        else if (action.equals("unlinkMember")) {
            return aroundAction("UnlinkMember", point);
        }
        else if (action.equals("unlinkStory")) {
            return aroundAction("UnlinkStory", point);
        }
        else if (action.equals("updateOrder")) {
            return aroundAction("UpdateOrder", point);
        }
        else if (action.equals("searchBugProject")) {
            return aroundDataSet("BugProject", point);
        }
        else if (action.equals("searchCurPlanProject")) {
            return aroundDataSet("CurPlanProject", point);
        }
        else if (action.equals("searchCurProduct")) {
            return aroundDataSet("CurProduct", point);
        }
        else if (action.equals("searchCurUser")) {
            return aroundDataSet("CurUser", point);
        }
        else if (action.equals("searchCurUserSa")) {
            return aroundDataSet("CurUserSa", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchESBulk")) {
            return aroundDataSet("ESBulk", point);
        }
        else if (action.equals("searchInvolvedProject")) {
            return aroundDataSet("InvolvedProject", point);
        }
        else if (action.equals("searchInvolvedProject_StoryTaskBug")) {
            return aroundDataSet("InvolvedProject_StoryTaskBug", point);
        }
        else if (action.equals("searchMyProject")) {
            return aroundDataSet("MyProject", point);
        }
        else if (action.equals("searchProjectTeam")) {
            return aroundDataSet("ProjectTeam", point);
        }
        else if (action.equals("searchStoryProject")) {
            return aroundDataSet("StoryProject", point);
        }
        else if (action.equals("searchUnDoneProject")) {
            return aroundDataSet("UnDoneProject", point);
        }
        return point.proceed();
    }

}
