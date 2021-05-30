package cn.ibizlab.pms.core.report.runtime;

import cn.ibizlab.pms.core.report.domain.IbzMonthly;
import cn.ibizlab.pms.core.report.service.IIbzMonthlyService;
import cn.ibizlab.pms.core.report.filter.IbzMonthlySearchContext;
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
@Component("IbzMonthlyRuntime")
@Slf4j
public class IbzMonthlyRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IIbzMonthlyService ibzmonthlyService;

    @Autowired
    DELogicExecutor logicExecutor;

    @Override
    protected IEntityBase getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntityBase) {
            return (IEntityBase) o;
        } else {
            return ibzmonthlyService.sysGet((Long) o);
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/report/PSDATAENTITIES/IbzMonthly.json";
    }

    @Override
    public String getName() {
        return "IBZ_MONTHLY";
    }

    @Override
    public IEntityBase createEntity() {
        return new IbzMonthly();
    }

    @Override
    protected IService getService() {
        return this.ibzmonthlyService;
    }

    @Override
    public IbzMonthlySearchContext createSearchContext() {
        return new IbzMonthlySearchContext();
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
            return MAPPER.readValue(String.valueOf(objData),IbzMonthly.class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public IEntityBase[] deserializeEntities(Object objData) {
        try {
            return MAPPER.readValue(String.valueOf(objData),IbzMonthly[].class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public Page<IbzMonthly> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        IbzMonthlySearchContext searchContext = (IbzMonthlySearchContext) iSearchContextBase;
        if (iPSDEDataSet.getName().equals("DEFAULT"))
            return ibzmonthlyService.searchDefault(searchContext);    
        if (iPSDEDataSet.getName().equals("MyMonthly"))
            return ibzmonthlyService.searchMyMonthly(searchContext);    
        if (iPSDEDataSet.getName().equals("MyMonthlyMob"))
            return ibzmonthlyService.searchMyMonthlyMob(searchContext);    
        if (iPSDEDataSet.getName().equals("MyReceivedMonthly"))
            return ibzmonthlyService.searchMyReceivedMonthly(searchContext);    
        if (iPSDEDataSet.getName().equals("MySubmitMonthly"))
            return ibzmonthlyService.searchMySubmitMonthly(searchContext);    
        if (iPSDEDataSet.getName().equals("ProductMonthly"))
            return ibzmonthlyService.searchProductMonthly(searchContext);    
        if (iPSDEDataSet.getName().equals("ProjectMonthly"))
            return ibzmonthlyService.searchProjectMonthly(searchContext);    
        return null;
    }

    @Override
    public List<IbzMonthly> selectDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        IbzMonthlySearchContext searchContext = (IbzMonthlySearchContext) iSearchContextBase;
        if (iPSDataQuery.getName().equals("DEFAULT"))
            return ibzmonthlyService.selectDefault(searchContext);
        if (iPSDataQuery.getName().equals("MyMonthly"))
            return ibzmonthlyService.selectMyMonthly(searchContext);
        if (iPSDataQuery.getName().equals("MyMonthlyMob"))
            return ibzmonthlyService.selectMyMonthlyMob(searchContext);
        if (iPSDataQuery.getName().equals("MyReceivedMonthly"))
            return ibzmonthlyService.selectMyReceivedMonthly(searchContext);
        if (iPSDataQuery.getName().equals("MySubmitMonthly"))
            return ibzmonthlyService.selectMySubmitMonthly(searchContext);
        if (iPSDataQuery.getName().equals("ProductMonthly"))
            return ibzmonthlyService.selectProductMonthly(searchContext);
        if (iPSDataQuery.getName().equals("ProjectMonthly"))
            return ibzmonthlyService.selectProjectMonthly(searchContext);
        if (iPSDataQuery.getName().equals("VIEW"))
            return ibzmonthlyService.selectView(searchContext);
        return null;
    }

    @Override
    public List<IbzMonthly> select(ISearchContextBase iSearchContextBase) {
        IbzMonthlySearchContext searchContext = (IbzMonthlySearchContext) iSearchContextBase;
        return ibzmonthlyService.select(searchContext);
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return ibzmonthlyService.create((IbzMonthly) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return ibzmonthlyService.update((IbzMonthly) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return ibzmonthlyService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                if(args[0] instanceof IbzMonthly){
                    IbzMonthly arg = (IbzMonthly) args[0] ;
                    CachedBeanCopier.copy(ibzmonthlyService.get(arg.getIbzmonthlyid()), arg);
                    return arg;
                }else{
                    return ibzmonthlyService.get((Long) args[0]);
                }
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return ibzmonthlyService.getDraft((IbzMonthly) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return ibzmonthlyService.checkKey((IbzMonthly) args[0]);
            }
            else if (iPSDEAction.getName().equals("CreateGetInfo")) {
                return ibzmonthlyService.createGetInfo((IbzMonthly) args[0]);
            }
            else if (iPSDEAction.getName().equals("CreateUserMonthly")) {
                return ibzmonthlyService.createUserMonthly((IbzMonthly) args[0]);
            }
            else if (iPSDEAction.getName().equals("EditGetCompleteTask")) {
                return ibzmonthlyService.editGetCompleteTask((IbzMonthly) args[0]);
            }
            else if (iPSDEAction.getName().equals("HaveRead")) {
                return ibzmonthlyService.haveRead((IbzMonthly) args[0]);
            }
            else if (iPSDEAction.getName().equals("PushUserMonthly")) {
                return ibzmonthlyService.pushUserMonthly((IbzMonthly) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return ibzmonthlyService.save((IbzMonthly) args[0]);
            }
            else if (iPSDEAction.getName().equals("Submit")) {
                return ibzmonthlyService.submit((IbzMonthly) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equalsIgnoreCase("Create")) {
                return ibzmonthlyService.create((IbzMonthly) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Update")) {
                return ibzmonthlyService.update((IbzMonthly) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Remove")) {
                return ibzmonthlyService.remove((Long) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Get")) {
                if(args[0] instanceof IbzMonthly){
                    IbzMonthly arg = (IbzMonthly) args[0] ;
                    CachedBeanCopier.copy(ibzmonthlyService.get(arg.getIbzmonthlyid()), arg);
                    return arg;
                }else{
                    return ibzmonthlyService.get((Long) args[0]);
                }
            }
            else if (strActionName.equalsIgnoreCase("GetDraft")) {
                return ibzmonthlyService.getDraft((IbzMonthly) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("CheckKey")) {
                return ibzmonthlyService.checkKey((IbzMonthly) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("CreateGetInfo")) {
                return ibzmonthlyService.createGetInfo((IbzMonthly) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("CreateUserMonthly")) {
                return ibzmonthlyService.createUserMonthly((IbzMonthly) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("EditGetCompleteTask")) {
                return ibzmonthlyService.editGetCompleteTask((IbzMonthly) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("HaveRead")) {
                return ibzmonthlyService.haveRead((IbzMonthly) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("PushUserMonthly")) {
                return ibzmonthlyService.pushUserMonthly((IbzMonthly) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Save")) {
                return ibzmonthlyService.save((IbzMonthly) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Submit")) {
                return ibzmonthlyService.submit((IbzMonthly) args[0]);
            }
            else  if (strActionName.equalsIgnoreCase(DEActions.SYSGET)) {
                if(args[0] instanceof IbzMonthly){
                    IbzMonthly arg = (IbzMonthly) args[0] ;
                    CachedBeanCopier.copy(ibzmonthlyService.sysGet(arg.getIbzmonthlyid()), arg);
                    return arg;
                }else{
                    return ibzmonthlyService.sysGet((Long) args[0]);
                }
            }
            else  if (strActionName.equalsIgnoreCase(DEActions.SYSUPDATE)) {
                    return ibzmonthlyService.sysUpdate((IbzMonthly) args[0]);
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
        IbzMonthly entity = null;
        String action = iPSDEAction.getName();
        if (arg0 instanceof IbzMonthly) {
            entity = (IbzMonthly) arg0;
        } else {
            if (arg0 != null && "get".equalsIgnoreCase(action)) {
                entity = ibzmonthlyService.sysGet((Long) arg0);
            } else if ("remove".equalsIgnoreCase(action)) {
                entity = new IbzMonthly();
                entity.setIbzmonthlyid((Long) arg0);
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

    @Around("execution(* cn.ibizlab.pms.core.report.service.impl.IbzMonthlyServiceImpl.*(..))")
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
        else if (action.equals("createGetInfo")) {
            return aroundAction("CreateGetInfo", point);
        }
        else if (action.equals("createUserMonthly")) {
            return aroundAction("CreateUserMonthly", point);
        }
        else if (action.equals("editGetCompleteTask")) {
            return aroundAction("EditGetCompleteTask", point);
        }
        else if (action.equals("haveRead")) {
            return aroundAction("HaveRead", point);
        }
        else if (action.equals("pushUserMonthly")) {
            return aroundAction("PushUserMonthly", point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("submit")) {
            return aroundAction("Submit", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchMyMonthly")) {
            return aroundDataSet("MyMonthly", point);
        }
        else if (action.equals("searchMyMonthlyMob")) {
            return aroundDataSet("MyMonthlyMob", point);
        }
        else if (action.equals("searchMyReceivedMonthly")) {
            return aroundDataSet("MyReceivedMonthly", point);
        }
        else if (action.equals("searchMySubmitMonthly")) {
            return aroundDataSet("MySubmitMonthly", point);
        }
        else if (action.equals("searchProductMonthly")) {
            return aroundDataSet("ProductMonthly", point);
        }
        else if (action.equals("searchProjectMonthly")) {
            return aroundDataSet("ProjectMonthly", point);
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
