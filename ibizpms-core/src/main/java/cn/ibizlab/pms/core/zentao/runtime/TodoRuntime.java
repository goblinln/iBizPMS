package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Todo;
import cn.ibizlab.pms.core.zentao.service.ITodoService;
import cn.ibizlab.pms.core.zentao.filter.TodoSearchContext;
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
@Component("TodoRuntime")
@Slf4j
public class TodoRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    ITodoService todoService;

    @Autowired
    DELogicExecutor logicExecutor;

    @Override
    protected IEntityBase getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntityBase) {
            return (IEntityBase) o;
        } else {
            return todoService.sysGet((Long) o);
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/Todo.json";
    }

    @Override
    public String getName() {
        return "ZT_TODO";
    }

    @Override
    public IEntityBase createEntity() {
        return new Todo();
    }

    @Override
    protected IService getService() {
        return this.todoService;
    }

    @Override
    public TodoSearchContext createSearchContext() {
        return new TodoSearchContext();
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
            return MAPPER.readValue(String.valueOf(objData),Todo.class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public IEntityBase[] deserializeEntities(Object objData) {
        try {
            return MAPPER.readValue(String.valueOf(objData),Todo[].class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public Page<Todo> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        TodoSearchContext searchContext = (TodoSearchContext) iSearchContextBase;
        if (iPSDEDataSet.getName().equals("Account"))
            return todoService.searchAccount(searchContext);    
        if (iPSDEDataSet.getName().equals("DEFAULT"))
            return todoService.searchDefault(searchContext);    
        if (iPSDEDataSet.getName().equals("My"))
            return todoService.searchMy(searchContext);    
        if (iPSDEDataSet.getName().equals("MyCreateTodo"))
            return todoService.searchMyCreateTodo(searchContext);    
        if (iPSDEDataSet.getName().equals("MyTodo"))
            return todoService.searchMyTodo(searchContext);    
        if (iPSDEDataSet.getName().equals("MyTodoPc"))
            return todoService.searchMyTodoPc(searchContext);    
        if (iPSDEDataSet.getName().equals("MyUpcoming"))
            return todoService.searchMyUpcoming(searchContext);    
        return null;
    }

    @Override
    public List<Todo> selectDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        TodoSearchContext searchContext = (TodoSearchContext) iSearchContextBase;
        if (iPSDataQuery.getName().equals("Account"))
            return todoService.selectAccount(searchContext);
        if (iPSDataQuery.getName().equals("DEFAULT"))
            return todoService.selectDefault(searchContext);
        if (iPSDataQuery.getName().equals("My"))
            return todoService.selectMy(searchContext);
        if (iPSDataQuery.getName().equals("MyCreateTodo"))
            return todoService.selectMyCreateTodo(searchContext);
        if (iPSDataQuery.getName().equals("MyTodo"))
            return todoService.selectMyTodo(searchContext);
        if (iPSDataQuery.getName().equals("MyTodoPc"))
            return todoService.selectMyTodoPc(searchContext);
        if (iPSDataQuery.getName().equals("MyUpcoming"))
            return todoService.selectMyUpcoming(searchContext);
        if (iPSDataQuery.getName().equals("VIEW"))
            return todoService.selectView(searchContext);
        return null;
    }

    @Override
    public List<Todo> select(ISearchContextBase iSearchContextBase) {
        TodoSearchContext searchContext = (TodoSearchContext) iSearchContextBase;
        return todoService.select(searchContext);
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return todoService.create((Todo) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return todoService.update((Todo) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return todoService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                if(args[0] instanceof Todo){
                    Todo arg = (Todo) args[0] ;
                    CachedBeanCopier.copy(todoService.get(arg.getId()), arg);
                    return arg;
                }else{
                    return todoService.get((Long) args[0]);
                }
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return todoService.getDraft((Todo) args[0]);
            }
            else if (iPSDEAction.getName().equals("Activate")) {
                return todoService.activate((Todo) args[0]);
            }
            else if (iPSDEAction.getName().equals("AssignTo")) {
                return todoService.assignTo((Todo) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return todoService.checkKey((Todo) args[0]);
            }
            else if (iPSDEAction.getName().equals("Close")) {
                return todoService.close((Todo) args[0]);
            }
            else if (iPSDEAction.getName().equals("createCycle")) {
                return todoService.createCycle((Todo) args[0]);
            }
            else if (iPSDEAction.getName().equals("Finish")) {
                return todoService.finish((Todo) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return todoService.save((Todo) args[0]);
            }
            else if (iPSDEAction.getName().equals("sendMessage")) {
                return todoService.sendMessage((Todo) args[0]);
            }
            else if (iPSDEAction.getName().equals("sendMsgPreProcess")) {
                return todoService.sendMsgPreProcess((Todo) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equalsIgnoreCase("Create")) {
                return todoService.create((Todo) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Update")) {
                return todoService.update((Todo) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Remove")) {
                return todoService.remove((Long) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Get")) {
                if(args[0] instanceof Todo){
                    Todo arg = (Todo) args[0] ;
                    CachedBeanCopier.copy(todoService.get(arg.getId()), arg);
                    return arg;
                }else{
                    return todoService.get((Long) args[0]);
                }
            }
            else if (strActionName.equalsIgnoreCase("GetDraft")) {
                return todoService.getDraft((Todo) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Activate")) {
                return todoService.activate((Todo) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("AssignTo")) {
                return todoService.assignTo((Todo) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("CheckKey")) {
                return todoService.checkKey((Todo) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Close")) {
                return todoService.close((Todo) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("createCycle")) {
                return todoService.createCycle((Todo) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Finish")) {
                return todoService.finish((Todo) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Save")) {
                return todoService.save((Todo) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("sendMessage")) {
                return todoService.sendMessage((Todo) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("sendMsgPreProcess")) {
                return todoService.sendMsgPreProcess((Todo) args[0]);
            }
            else  if (strActionName.equalsIgnoreCase(DEActions.SYSGET)) {
                if(args[0] instanceof Todo){
                    Todo arg = (Todo) args[0] ;
                    CachedBeanCopier.copy(todoService.sysGet(arg.getId()), arg);
                    return arg;
                }else{
                    return todoService.sysGet((Long) args[0]);
                }
            }
            else  if (strActionName.equalsIgnoreCase(DEActions.SYSUPDATE)) {
                    return todoService.sysUpdate((Todo) args[0]);
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
        Todo entity = null;
        String action = iPSDEAction.getName();
        if (arg0 instanceof Todo) {
            entity = (Todo) arg0;
        } else {
            if (arg0 != null && "get".equalsIgnoreCase(action)) {
                entity = todoService.sysGet((Long) arg0);
            } else if ("remove".equalsIgnoreCase(action)) {
                entity = new Todo();
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

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.TodoServiceImpl.*(..))")
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
            return aroundAction("Activate", point);
        }
        else if (action.equals("assignTo")) {
            return aroundAction("AssignTo", point);
        }
        else if (action.equals("checkKey")) {
            return aroundAction("CheckKey", point);
        }
        else if (action.equals("close")) {
            return aroundAction("Close", point);
        }
        else if (action.equals("createCycle")) {
            return aroundAction("createCycle", point);
        }
        else if (action.equals("finish")) {
            return aroundAction("Finish", point);
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
        else if (action.equals("searchAccount")) {
            return aroundDataSet("Account", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchMy")) {
            return aroundDataSet("My", point);
        }
        else if (action.equals("searchMyCreateTodo")) {
            return aroundDataSet("MyCreateTodo", point);
        }
        else if (action.equals("searchMyTodo")) {
            return aroundDataSet("MyTodo", point);
        }
        else if (action.equals("searchMyTodoPc")) {
            return aroundDataSet("MyTodoPc", point);
        }
        else if (action.equals("searchMyUpcoming")) {
            return aroundDataSet("MyUpcoming", point);
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
