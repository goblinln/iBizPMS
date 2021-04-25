package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Action;
import cn.ibizlab.pms.core.zentao.service.IActionService;
import cn.ibizlab.pms.core.zentao.filter.ActionSearchContext;
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
@Component("ActionRuntime")
@Slf4j
public class ActionRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IActionService actionService;

    @Autowired
    DELogicExecutor logicExecutor;

    @Override
    protected IEntityBase getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntityBase) {
            return (IEntityBase) o;
        } else {
            return actionService.sysGet((Long) o);
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/Action.json";
    }

    @Override
    public String getName() {
        return "ZT_ACTION";
    }

    @Override
    public IEntityBase createEntity() {
        return new Action();
    }

    @Override
    protected IService getService() {
        return this.actionService;
    }

    @Override
    public ActionSearchContext createSearchContext() {
        return new ActionSearchContext();
    }

    @Override
    public boolean existsData(ISearchContextBase iSearchContextBase) {
        Page<Action> domains = actionService.searchDefault((ActionSearchContext) iSearchContextBase);
        if (domains.getSize() == 0)
            return false;
        return true;
    }

    @Override
    public Page<Action> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        ActionSearchContext searchContext = (ActionSearchContext) iSearchContextBase;
        if (iPSDEDataSet.getName().equals("DEFAULT"))
            return actionService.searchDefault(searchContext);    
        if (iPSDEDataSet.getName().equals("MobType"))
            return actionService.searchMobType(searchContext);    
        if (iPSDEDataSet.getName().equals("MyTrends"))
            return actionService.searchMyTrends(searchContext);    
        if (iPSDEDataSet.getName().equals("ProductTrends"))
            return actionService.searchProductTrends(searchContext);    
        if (iPSDEDataSet.getName().equals("ProjectTrends"))
            return actionService.searchProjectTrends(searchContext);    
        if (iPSDEDataSet.getName().equals("QueryUserYEAR"))
            return actionService.searchQueryUserYEAR(searchContext);    
        if (iPSDEDataSet.getName().equals("Type"))
            return actionService.searchType(searchContext);    
        return null;
    }

    @Override
    public List<Action> selectDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        ActionSearchContext searchContext = (ActionSearchContext) iSearchContextBase;
        if (iPSDataQuery.getName().equals("BianGengLineHistory"))
            return actionService.selectBianGengLineHistory(searchContext);
        if (iPSDataQuery.getName().equals("DEFAULT"))
            return actionService.selectDefault(searchContext);
        if (iPSDataQuery.getName().equals("MobType"))
            return actionService.selectMobType(searchContext);
        if (iPSDataQuery.getName().equals("MyTrends"))
            return actionService.selectMyTrends(searchContext);
        if (iPSDataQuery.getName().equals("ProductTrends"))
            return actionService.selectProductTrends(searchContext);
        if (iPSDataQuery.getName().equals("ProjectTrends"))
            return actionService.selectProjectTrends(searchContext);
        if (iPSDataQuery.getName().equals("QueryUserYEAR"))
            return actionService.selectQueryUserYEAR(searchContext);
        if (iPSDataQuery.getName().equals("Type"))
            return actionService.selectType(searchContext);
        if (iPSDataQuery.getName().equals("VIEW"))
            return actionService.selectView(searchContext);
        return null;
    }

    @Override
    public Action selectOne(ISearchContextBase iSearchContextBase) {
        //单条数据查询，多条数数据时 返回第一条
        ActionSearchContext searchContext = (ActionSearchContext) iSearchContextBase;
        searchContext.setSize(1);
        List<Action> domains = actionService.select(searchContext);
        if (domains.size() == 0)
            return null;
        return domains.get(0);
    }

    @Override
    public List<Action> select(ISearchContextBase iSearchContextBase) {
        ActionSearchContext searchContext = (ActionSearchContext) iSearchContextBase;
        return actionService.select(searchContext);
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return actionService.create((Action) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return actionService.update((Action) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return actionService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                if(args[0] instanceof Action){
                    Action arg = (Action) args[0] ;
                    arg = actionService.get(arg.getId()) ;
                    return arg;
                }else{
                    return actionService.get((Long) args[0]);
                }
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return actionService.getDraft((Action) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return actionService.checkKey((Action) args[0]);
            }
            else if (iPSDEAction.getName().equals("Comment")) {
                return actionService.comment((Action) args[0]);
            }
            else if (iPSDEAction.getName().equals("CreateHis")) {
                return actionService.createHis((Action) args[0]);
            }
            else if (iPSDEAction.getName().equals("editComment")) {
                return actionService.editComment((Action) args[0]);
            }
            else if (iPSDEAction.getName().equals("ManagePmsEe")) {
                return actionService.managePmsEe((Action) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return actionService.save((Action) args[0]);
            }
            else if (iPSDEAction.getName().equals("sendMarkDone")) {
                return actionService.sendMarkDone((Action) args[0]);
            }
            else if (iPSDEAction.getName().equals("sendTodo")) {
                return actionService.sendTodo((Action) args[0]);
            }
            else if (iPSDEAction.getName().equals("sendToread")) {
                return actionService.sendToread((Action) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return actionService.create((Action) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return actionService.update((Action) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                if(args[0] instanceof Action){
                    Action arg = (Action) args[0] ;
                    arg = actionService.get(arg.getId()) ;
                    return arg;
                }else{
                    return actionService.get((Long) args[0]);
                }
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return actionService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return actionService.sysGet((Long) args[0]);
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
        Action entity = null;
        String action = iPSDEAction.getName();
        if (arg0 instanceof Action) {
            entity = (Action) arg0;
        } else {
            if (arg0 != null && "get".equalsIgnoreCase(action)) {
                entity = actionService.sysGet((Long) arg0);
            } else if ("remove".equalsIgnoreCase(action)) {
                entity = new Action();
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

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.ActionServiceImpl.*(..))")
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
        else if (action.equals("comment")) {
            return aroundAction("Comment", point);
        }
        else if (action.equals("createHis")) {
            return aroundAction("CreateHis", point);
        }
        else if (action.equals("editComment")) {
            return aroundAction("editComment", point);
        }
        else if (action.equals("managePmsEe")) {
            return aroundAction("ManagePmsEe", point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("sendMarkDone")) {
            return aroundAction("sendMarkDone", point);
        }
        else if (action.equals("sendTodo")) {
            return aroundAction("sendTodo", point);
        }
        else if (action.equals("sendToread")) {
            return aroundAction("sendToread", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchMobType")) {
            return aroundDataSet("MobType", point);
        }
        else if (action.equals("searchMyTrends")) {
            return aroundDataSet("MyTrends", point);
        }
        else if (action.equals("searchProductTrends")) {
            return aroundDataSet("ProductTrends", point);
        }
        else if (action.equals("searchProjectTrends")) {
            return aroundDataSet("ProjectTrends", point);
        }
        else if (action.equals("searchQueryUserYEAR")) {
            return aroundDataSet("QueryUserYEAR", point);
        }
        else if (action.equals("searchType")) {
            return aroundDataSet("Type", point);
        }
        return point.proceed();
    }

}
