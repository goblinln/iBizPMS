package cn.ibizlab.pms.core.ibizpro.runtime;

import cn.ibizlab.pms.core.ibizpro.domain.IbzproProductUserTask;
import cn.ibizlab.pms.core.ibizpro.service.IIbzproProductUserTaskService;
import cn.ibizlab.pms.core.ibizpro.filter.IbzproProductUserTaskSearchContext;
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

@Component("IbzproProductUserTaskRuntime")
@Slf4j
public class IbzproProductUserTaskRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime implements IIbzproProductUserTaskRuntime {

    @Autowired
    IIbzproProductUserTaskService ibzproproductusertaskService;

    @Autowired
    DELogicExecutor logicExecutor;

    @Override
    protected IEntityBase getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntityBase) {
            return (IEntityBase) o;
        } else {
            return ibzproproductusertaskService.sysGet((Long) o);
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/ibizpro/PSDATAENTITIES/IbzproProductUserTask.json";
    }

    @Override
    public String getName() {
        return "IBIZPRO_PRODUCTUSERTASK";
    }

    @Override
    public IEntityBase createEntity() {
        return new IbzproProductUserTask();
    }

    @Override
    public IbzproProductUserTaskSearchContext createSearchContext() {
        return new IbzproProductUserTaskSearchContext();
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
            return MAPPER.readValue(String.valueOf(objData),IbzproProductUserTask.class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public IEntityBase[] deserializeEntities(Object objData) {
        try {
            return MAPPER.readValue(String.valueOf(objData),IbzproProductUserTask[].class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public Page<IbzproProductUserTask> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        IbzproProductUserTaskSearchContext searchContext = (IbzproProductUserTaskSearchContext) iSearchContextBase;
        if (iPSDEDataSet.getName().equals("DEFAULT"))
            return ibzproproductusertaskService.searchDefault(searchContext);    
        if (iPSDEDataSet.getName().equals("ProductDailyUserTaskStats"))
            return ibzproproductusertaskService.searchProductDailyUserTaskStats(searchContext);    
        if (iPSDEDataSet.getName().equals("ProductMonthlyUserTaskStats"))
            return ibzproproductusertaskService.searchProductMonthlyUserTaskStats(searchContext);    
        if (iPSDEDataSet.getName().equals("ProductWeeklyUserTaskStats"))
            return ibzproproductusertaskService.searchProductWeeklyUserTaskStats(searchContext);    
        return null;
    }

    @Override
    public List<IbzproProductUserTask> selectDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        IbzproProductUserTaskSearchContext searchContext = (IbzproProductUserTaskSearchContext) iSearchContextBase;
        if (iPSDataQuery.getName().equals("DEFAULT"))
            return ibzproproductusertaskService.selectDefault(searchContext);
        if (iPSDataQuery.getName().equals("ProductDailyUserTaskStats"))
            return ibzproproductusertaskService.selectProductDailyUserTaskStats(searchContext);
        if (iPSDataQuery.getName().equals("ProductMonthlyUserTaskStats"))
            return ibzproproductusertaskService.selectProductMonthlyUserTaskStats(searchContext);
        if (iPSDataQuery.getName().equals("ProductWeeklyUserTaskStats"))
            return ibzproproductusertaskService.selectProductWeeklyUserTaskStats(searchContext);
        if (iPSDataQuery.getName().equals("VIEW"))
            return ibzproproductusertaskService.selectView(searchContext);
        return null;
    }

    @Override
    public List<IbzproProductUserTask> select(ISearchContextBase iSearchContextBase) {
        IbzproProductUserTaskSearchContext searchContext = (IbzproProductUserTaskSearchContext) iSearchContextBase;
        return ibzproproductusertaskService.select(searchContext);
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return ibzproproductusertaskService.create((IbzproProductUserTask) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return ibzproproductusertaskService.update((IbzproProductUserTask) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return ibzproproductusertaskService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                if(args[0] instanceof IbzproProductUserTask){
                    IbzproProductUserTask arg = (IbzproProductUserTask) args[0] ;
                    CachedBeanCopier.copy(ibzproproductusertaskService.get(arg.getId()), arg);
                    return arg;
                }else{
                    return ibzproproductusertaskService.get((Long) args[0]);
                }
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return ibzproproductusertaskService.getDraft((IbzproProductUserTask) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return ibzproproductusertaskService.checkKey((IbzproProductUserTask) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return ibzproproductusertaskService.save((IbzproProductUserTask) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equalsIgnoreCase("Create")) {
                return ibzproproductusertaskService.create((IbzproProductUserTask) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Update")) {
                return ibzproproductusertaskService.update((IbzproProductUserTask) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Remove")) {
                return ibzproproductusertaskService.remove((Long) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Get")) {
                if(args[0] instanceof IbzproProductUserTask){
                    IbzproProductUserTask arg = (IbzproProductUserTask) args[0] ;
                    CachedBeanCopier.copy(ibzproproductusertaskService.get(arg.getId()), arg);
                    return arg;
                }else{
                    return ibzproproductusertaskService.get((Long) args[0]);
                }
            }
            else if (strActionName.equalsIgnoreCase("GetDraft")) {
                return ibzproproductusertaskService.getDraft((IbzproProductUserTask) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("CheckKey")) {
                return ibzproproductusertaskService.checkKey((IbzproProductUserTask) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Save")) {
                return ibzproproductusertaskService.save((IbzproProductUserTask) args[0]);
            }
            else  if (strActionName.equalsIgnoreCase(DEActions.SYSGET)) {
                if(args[0] instanceof IbzproProductUserTask){
                    IbzproProductUserTask arg = (IbzproProductUserTask) args[0] ;
                    CachedBeanCopier.copy(ibzproproductusertaskService.sysGet(arg.getId()), arg);
                    return arg;
                }else{
                    return ibzproproductusertaskService.sysGet((Long) args[0]);
                }
            }
            else  if (strActionName.equalsIgnoreCase(DEActions.SYSUPDATE)) {
                    return ibzproproductusertaskService.sysUpdate((IbzproProductUserTask) args[0]);
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
        IbzproProductUserTask entity = null;
        String action = iPSDEAction.getName();
        if (arg0 instanceof IbzproProductUserTask) {
            entity = (IbzproProductUserTask) arg0;
        } else {
            if (arg0 != null && "get".equalsIgnoreCase(action)) {
                entity = ibzproproductusertaskService.sysGet((Long) arg0);
            } else if ("remove".equalsIgnoreCase(action)) {
                entity = new IbzproProductUserTask();
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
