package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.User;
import cn.ibizlab.pms.core.zentao.service.IUserService;
import cn.ibizlab.pms.core.zentao.filter.UserSearchContext;
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
import net.ibizsys.runtime.dataentity.action.DEActions;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import java.io.Serializable;
import java.util.List;



@Aspect
@Order(100)
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
    protected IService getService() {
        return this.userService;
    }

    @Override
    public UserSearchContext createSearchContext() {
        return new UserSearchContext();
    }

    @Override
    public boolean existsData(ISearchContextBase iSearchContextBase) {
        Page<User> domains = userService.searchDefault((UserSearchContext) iSearchContextBase);
        if (domains.getSize() == 0)
            return false;
        return true;
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
            return userService.selectQueryByBugUser(searchContext);
        if (iPSDataQuery.getName().equals("DEFAULT"))
            return userService.selectQueryByDefault(searchContext);
        if (iPSDataQuery.getName().equals("getByLogin"))
            return userService.selectQueryByGetByLogin(searchContext);
        if (iPSDataQuery.getName().equals("ProjectTeamM"))
            return userService.selectQueryByProjectTeamM(searchContext);
        if (iPSDataQuery.getName().equals("ProjectTeamUser"))
            return userService.selectQueryByProjectTeamUser(searchContext);
        if (iPSDataQuery.getName().equals("ProjectTeamUserTask"))
            return userService.selectQueryByProjectTeamUserTask(searchContext);
        if (iPSDataQuery.getName().equals("TASKTEAM"))
            return userService.selectQueryByTaskTeam(searchContext);
        if (iPSDataQuery.getName().equals("VIEW"))
            return userService.selectQueryByView(searchContext);
        return null;
    }

    @Override
    public User selectOne(ISearchContextBase iSearchContextBase) {
        //单条数据查询，多条数数据时 返回第一条
        UserSearchContext searchContext = (UserSearchContext) iSearchContextBase;
        searchContext.setSize(1);
        List<User> domains = userService.select(searchContext);
        if (domains.size() == 0)
            return null;
        return domains.get(0);
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
                    arg = userService.get(arg.getId()) ;
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
            if (strActionName.equals(DEActions.CREATE)) {
                return userService.create((User) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return userService.update((User) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                if(args[0] instanceof User){
                    User arg = (User) args[0] ;
                    arg = userService.get(arg.getId()) ;
                    return arg;
                }else{
                    return userService.get((Long) args[0]);
                }
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return userService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return userService.sysGet((Long) args[0]);
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

}
