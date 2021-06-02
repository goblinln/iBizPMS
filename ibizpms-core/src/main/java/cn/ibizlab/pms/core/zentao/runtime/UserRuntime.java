package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.User;
import cn.ibizlab.pms.core.zentao.service.IUserService;
import cn.ibizlab.pms.core.zentao.filter.UserSearchContext;
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


@Aspect
@org.springframework.core.annotation.Order(100)
@Component("UserRuntime")
@Slf4j
public class UserRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IUserService userService;

    @Autowired
    DELogicExecutor logicExecutor;

    @Override
    protected IEntityBase getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntityBase) {
            return (IEntityBase) o;
        } else {
            return userService.sysGet((Long) o);
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/User.json";
    }

    @Override
    public String getName() {
        return "ZT_USER";
    }

    @Override
    public IEntityBase createEntity() {
        return new User();
    }

    @Override
    public UserSearchContext createSearchContext() {
        return new UserSearchContext();
    }

    @Override
    public Object serializeEntity(IEntityBase iEntityBase) {
        try {
            return MAPPER.writeValueAsString(iEntityBase);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]序列化数据异常。%2$s", this.getName(), e.getMessage()), this, e);
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
            return MAPPER.readValue(String.valueOf(objData),User.class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public IEntityBase[] deserializeEntities(Object objData) {
        try {
            return MAPPER.readValue(String.valueOf(objData),User[].class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public Page<User> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        UserSearchContext searchContext = (UserSearchContext) iSearchContextBase;
        if (iPSDEDataSet.getName().equals("BugUser"))
            return userService.searchBugUser(searchContext);    
        if (iPSDEDataSet.getName().equals("DEFAULT"))
            return userService.searchDefault(searchContext);    
        if (iPSDEDataSet.getName().equals("getByCommiter"))
            return userService.searchGetByCommiter(searchContext);    
        if (iPSDEDataSet.getName().equals("ProjectTeamM"))
            return userService.searchProjectTeamM(searchContext);    
        if (iPSDEDataSet.getName().equals("ProjectTeamUser"))
            return userService.searchProjectTeamUser(searchContext);    
        if (iPSDEDataSet.getName().equals("ProjectTeamUserTask"))
            return userService.searchProjectTeamUserTask(searchContext);    
        if (iPSDEDataSet.getName().equals("TASKTEAM"))
            return userService.searchTaskTeam(searchContext);    
        return null;
    }

    @Override
    public List<User> selectDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        UserSearchContext searchContext = (UserSearchContext) iSearchContextBase;
        if (iPSDataQuery.getName().equals("BugUser"))
            return userService.selectBugUser(searchContext);
        if (iPSDataQuery.getName().equals("DEFAULT"))
            return userService.selectDefault(searchContext);
        if (iPSDataQuery.getName().equals("getByLogin"))
            return userService.selectGetByLogin(searchContext);
        if (iPSDataQuery.getName().equals("ProjectTeamM"))
            return userService.selectProjectTeamM(searchContext);
        if (iPSDataQuery.getName().equals("ProjectTeamUser"))
            return userService.selectProjectTeamUser(searchContext);
        if (iPSDataQuery.getName().equals("ProjectTeamUserTask"))
            return userService.selectProjectTeamUserTask(searchContext);
        if (iPSDataQuery.getName().equals("TASKTEAM"))
            return userService.selectTaskTeam(searchContext);
        if (iPSDataQuery.getName().equals("VIEW"))
            return userService.selectView(searchContext);
        return null;
    }

    @Override
    public List<User> select(ISearchContextBase iSearchContextBase) {
        UserSearchContext searchContext = (UserSearchContext) iSearchContextBase;
        return userService.select(searchContext);
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return userService.create((User) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return userService.update((User) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return userService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                if(args[0] instanceof User){
                    User arg = (User) args[0] ;
                    CachedBeanCopier.copy(userService.get(arg.getId()), arg);
                    return arg;
                }else{
                    return userService.get((Long) args[0]);
                }
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return userService.getDraft((User) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return userService.checkKey((User) args[0]);
            }
            else if (iPSDEAction.getName().equals("getByCommiter")) {
                return userService.getByCommiter((User) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return userService.save((User) args[0]);
            }
            else if (iPSDEAction.getName().equals("SyncAccount")) {
                return userService.syncAccount((User) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equalsIgnoreCase("Create")) {
                return userService.create((User) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Update")) {
                return userService.update((User) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Remove")) {
                return userService.remove((Long) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Get")) {
                if(args[0] instanceof User){
                    User arg = (User) args[0] ;
                    CachedBeanCopier.copy(userService.get(arg.getId()), arg);
                    return arg;
                }else{
                    return userService.get((Long) args[0]);
                }
            }
            else if (strActionName.equalsIgnoreCase("GetDraft")) {
                return userService.getDraft((User) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("CheckKey")) {
                return userService.checkKey((User) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("getByCommiter")) {
                return userService.getByCommiter((User) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Save")) {
                return userService.save((User) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("SyncAccount")) {
                return userService.syncAccount((User) args[0]);
            }
            else  if (strActionName.equalsIgnoreCase(DEActions.SYSGET)) {
                if(args[0] instanceof User){
                    User arg = (User) args[0] ;
                    CachedBeanCopier.copy(userService.sysGet(arg.getId()), arg);
                    return arg;
                }else{
                    return userService.sysGet((Long) args[0]);
                }
            }
            else  if (strActionName.equalsIgnoreCase(DEActions.SYSUPDATE)) {
                    return userService.sysUpdate((User) args[0]);
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
        User entity = null;
        String action = iPSDEAction.getName();
        if (arg0 instanceof User) {
            entity = (User) arg0;
        } else {
            if (arg0 != null && "get".equalsIgnoreCase(action)) {
                entity = userService.sysGet((Long) arg0);
            } else if ("remove".equalsIgnoreCase(action)) {
                entity = new User();
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

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.UserServiceImpl.*(..))")
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
        else if (action.equals("getByCommiter")) {
            return aroundAction(action, point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("syncAccount")) {
            return aroundAction("SyncAccount", point);
        }
        else if (action.equals("searchBugUser")) {
            return aroundDataSet("BugUser", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchGetByCommiter")) {
            return aroundDataSet("getByCommiter", point);
        }
        else if (action.equals("searchProjectTeamM")) {
            return aroundDataSet("ProjectTeamM", point);
        }
        else if (action.equals("searchProjectTeamUser")) {
            return aroundDataSet("ProjectTeamUser", point);
        }
        else if (action.equals("searchProjectTeamUserTask")) {
            return aroundDataSet("ProjectTeamUserTask", point);
        }
        else if (action.equals("searchTaskTeam")) {
            return aroundDataSet("TASKTEAM", point);
        }
        return point.proceed();
    }

    @Override
    protected void onWFRegister(Object arg0, IPSDEAction iPSDEAction, IPSDEWF iPSDEWF, IDynaInstRuntime iDynaInstRuntime, Object actionData) throws Throwable {
    }

    @Override
    protected void onWFUnregister(Object arg0, IPSDEAction iPSDEAction, IPSDEWF iPSDEWF, IDynaInstRuntime iDynaInstRuntime, Object actionData) throws Throwable {
    }
}
