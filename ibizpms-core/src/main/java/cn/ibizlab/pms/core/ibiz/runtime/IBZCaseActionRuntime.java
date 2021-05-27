package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.ibiz.domain.IBZCaseAction;
import cn.ibizlab.pms.core.ibiz.service.IIBZCaseActionService;
import cn.ibizlab.pms.core.ibiz.filter.IBZCaseActionSearchContext;
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
import cn.ibizlab.pms.util.domain.WFTask;
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
@Component("IBZCaseActionRuntime")
@Slf4j
public class IBZCaseActionRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IIBZCaseActionService ibzcaseactionService;

    @Autowired
    DELogicExecutor logicExecutor;

    @Override
    protected IEntityBase getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntityBase) {
            return (IEntityBase) o;
        } else {
            return ibzcaseactionService.sysGet((Long) o);
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/ibiz/PSDATAENTITIES/IBZCaseAction.json";
    }

    @Override
    public String getName() {
        return "IBZPRO_CASEACTION";
    }

    @Override
    public IEntityBase createEntity() {
        return new IBZCaseAction();
    }

    @Override
    protected IService getService() {
        return this.ibzcaseactionService;
    }

    @Override
    public IBZCaseActionSearchContext createSearchContext() {
        return new IBZCaseActionSearchContext();
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
            return MAPPER.readValue(String.valueOf(objData),IBZCaseAction.class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public IEntityBase[] deserializeEntities(Object objData) {
        try {
            return MAPPER.readValue(String.valueOf(objData),IBZCaseAction[].class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public Page<IBZCaseAction> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        IBZCaseActionSearchContext searchContext = (IBZCaseActionSearchContext) iSearchContextBase;
        if (iPSDEDataSet.getName().equals("DEFAULT"))
            return ibzcaseactionService.searchDefault(searchContext);    
        if (iPSDEDataSet.getName().equals("Type"))
            return ibzcaseactionService.searchType(searchContext);    
        return null;
    }

    @Override
    public List<IBZCaseAction> selectDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        IBZCaseActionSearchContext searchContext = (IBZCaseActionSearchContext) iSearchContextBase;
        if (iPSDataQuery.getName().equals("DEFAULT"))
            return ibzcaseactionService.selectDefault(searchContext);
        if (iPSDataQuery.getName().equals("SIMPLE"))
            return ibzcaseactionService.selectSimple(searchContext);
        if (iPSDataQuery.getName().equals("Type"))
            return ibzcaseactionService.selectType(searchContext);
        if (iPSDataQuery.getName().equals("VIEW"))
            return ibzcaseactionService.selectView(searchContext);
        return null;
    }

    @Override
    public List<IBZCaseAction> select(ISearchContextBase iSearchContextBase) {
        IBZCaseActionSearchContext searchContext = (IBZCaseActionSearchContext) iSearchContextBase;
        return ibzcaseactionService.select(searchContext);
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return ibzcaseactionService.create((IBZCaseAction) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return ibzcaseactionService.update((IBZCaseAction) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return ibzcaseactionService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                if(args[0] instanceof IBZCaseAction){
                    IBZCaseAction arg = (IBZCaseAction) args[0] ;
                    CachedBeanCopier.copy(ibzcaseactionService.get(arg.getId()), arg);
                    return arg;
                }else{
                    return ibzcaseactionService.get((Long) args[0]);
                }
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return ibzcaseactionService.getDraft((IBZCaseAction) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return ibzcaseactionService.checkKey((IBZCaseAction) args[0]);
            }
            else if (iPSDEAction.getName().equals("Comment")) {
                return ibzcaseactionService.comment((IBZCaseAction) args[0]);
            }
            else if (iPSDEAction.getName().equals("CreateHis")) {
                return ibzcaseactionService.createHis((IBZCaseAction) args[0]);
            }
            else if (iPSDEAction.getName().equals("editComment")) {
                return ibzcaseactionService.editComment((IBZCaseAction) args[0]);
            }
            else if (iPSDEAction.getName().equals("ManagePmsEe")) {
                return ibzcaseactionService.managePmsEe((IBZCaseAction) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return ibzcaseactionService.save((IBZCaseAction) args[0]);
            }
            else if (iPSDEAction.getName().equals("sendMarkDone")) {
                return ibzcaseactionService.sendMarkDone((IBZCaseAction) args[0]);
            }
            else if (iPSDEAction.getName().equals("sendTodo")) {
                return ibzcaseactionService.sendTodo((IBZCaseAction) args[0]);
            }
            else if (iPSDEAction.getName().equals("sendToread")) {
                return ibzcaseactionService.sendToread((IBZCaseAction) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equalsIgnoreCase("Create")) {
                return ibzcaseactionService.create((IBZCaseAction) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Update")) {
                return ibzcaseactionService.update((IBZCaseAction) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Remove")) {
                return ibzcaseactionService.remove((Long) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Get")) {
                if(args[0] instanceof IBZCaseAction){
                    IBZCaseAction arg = (IBZCaseAction) args[0] ;
                    CachedBeanCopier.copy(ibzcaseactionService.get(arg.getId()), arg);
                    return arg;
                }else{
                    return ibzcaseactionService.get((Long) args[0]);
                }
            }
            else if (strActionName.equalsIgnoreCase("GetDraft")) {
                return ibzcaseactionService.getDraft((IBZCaseAction) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("CheckKey")) {
                return ibzcaseactionService.checkKey((IBZCaseAction) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Comment")) {
                return ibzcaseactionService.comment((IBZCaseAction) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("CreateHis")) {
                return ibzcaseactionService.createHis((IBZCaseAction) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("editComment")) {
                return ibzcaseactionService.editComment((IBZCaseAction) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("ManagePmsEe")) {
                return ibzcaseactionService.managePmsEe((IBZCaseAction) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Save")) {
                return ibzcaseactionService.save((IBZCaseAction) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("sendMarkDone")) {
                return ibzcaseactionService.sendMarkDone((IBZCaseAction) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("sendTodo")) {
                return ibzcaseactionService.sendTodo((IBZCaseAction) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("sendToread")) {
                return ibzcaseactionService.sendToread((IBZCaseAction) args[0]);
            }
            else  if (strActionName.equalsIgnoreCase(DEActions.SYSGET)) {
                if(args[0] instanceof IBZCaseAction){
                    IBZCaseAction arg = (IBZCaseAction) args[0] ;
                    CachedBeanCopier.copy(ibzcaseactionService.sysGet(arg.getId()), arg);
                    return arg;
                }else{
                    return ibzcaseactionService.sysGet((Long) args[0]);
                }
            }
            else  if (strActionName.equalsIgnoreCase(DEActions.SYSUPDATE)) {
                    return ibzcaseactionService.sysUpdate((IBZCaseAction) args[0]);
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
        IBZCaseAction entity = null;
        String action = iPSDEAction.getName();
        if (arg0 instanceof IBZCaseAction) {
            entity = (IBZCaseAction) arg0;
        } else {
            if (arg0 != null && "get".equalsIgnoreCase(action)) {
                entity = ibzcaseactionService.sysGet((Long) arg0);
            } else if ("remove".equalsIgnoreCase(action)) {
                entity = new IBZCaseAction();
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

    @Around("execution(* cn.ibizlab.pms.core.ibiz.service.impl.IBZCaseActionServiceImpl.*(..))")
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
        else if (action.equals("searchType")) {
            return aroundDataSet("Type", point);
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
