package cn.ibizlab.pms.core.report.runtime;

import cn.ibizlab.pms.core.report.domain.IbzWeekly;
import cn.ibizlab.pms.core.report.service.IIbzWeeklyService;
import cn.ibizlab.pms.core.report.filter.IbzWeeklySearchContext;
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
@Component("IbzWeeklyRuntime")
@Slf4j
public class IbzWeeklyRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IIbzWeeklyService ibzweeklyService;

    @Autowired
    DELogicExecutor logicExecutor;

    @Override
    protected IEntityBase getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntityBase) {
            return (IEntityBase) o;
        } else {
            return ibzweeklyService.sysGet((Long) o);
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/report/PSDATAENTITIES/IbzWeekly.json";
    }

    @Override
    public String getName() {
        return "IBZ_WEEKLY";
    }

    @Override
    public IEntityBase createEntity() {
        return new IbzWeekly();
    }

    @Override
    protected IService getService() {
        return this.ibzweeklyService;
    }

    @Override
    public IbzWeeklySearchContext createSearchContext() {
        return new IbzWeeklySearchContext();
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
            return MAPPER.readValue(String.valueOf(objData),IbzWeekly.class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public IEntityBase[] deserializeEntities(Object objData) {
        try {
            return MAPPER.readValue(String.valueOf(objData),IbzWeekly[].class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public Page<IbzWeekly> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        IbzWeeklySearchContext searchContext = (IbzWeeklySearchContext) iSearchContextBase;
        if (iPSDEDataSet.getName().equals("DEFAULT"))
            return ibzweeklyService.searchDefault(searchContext);    
        if (iPSDEDataSet.getName().equals("MyNotSubmit"))
            return ibzweeklyService.searchMyNotSubmit(searchContext);    
        if (iPSDEDataSet.getName().equals("MyWeekly"))
            return ibzweeklyService.searchMyWeekly(searchContext);    
        if (iPSDEDataSet.getName().equals("ProductTeamMemberWeekly"))
            return ibzweeklyService.searchProductTeamMemberWeekly(searchContext);    
        if (iPSDEDataSet.getName().equals("ProjectWeekly"))
            return ibzweeklyService.searchProjectWeekly(searchContext);    
        return null;
    }

    @Override
    public List<IbzWeekly> selectDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        IbzWeeklySearchContext searchContext = (IbzWeeklySearchContext) iSearchContextBase;
        if (iPSDataQuery.getName().equals("DEFAULT"))
            return ibzweeklyService.selectDefault(searchContext);
        if (iPSDataQuery.getName().equals("MyNotSubmit"))
            return ibzweeklyService.selectMyNotSubmit(searchContext);
        if (iPSDataQuery.getName().equals("MyWeekly"))
            return ibzweeklyService.selectMyWeekly(searchContext);
        if (iPSDataQuery.getName().equals("ProductTeamMemberWeekly"))
            return ibzweeklyService.selectProductTeamMemberWeekly(searchContext);
        if (iPSDataQuery.getName().equals("ProjectWeekly"))
            return ibzweeklyService.selectProjectWeekly(searchContext);
        if (iPSDataQuery.getName().equals("VIEW"))
            return ibzweeklyService.selectView(searchContext);
        return null;
    }

    @Override
    public List<IbzWeekly> select(ISearchContextBase iSearchContextBase) {
        IbzWeeklySearchContext searchContext = (IbzWeeklySearchContext) iSearchContextBase;
        return ibzweeklyService.select(searchContext);
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return ibzweeklyService.create((IbzWeekly) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return ibzweeklyService.update((IbzWeekly) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return ibzweeklyService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                if(args[0] instanceof IbzWeekly){
                    IbzWeekly arg = (IbzWeekly) args[0] ;
                    CachedBeanCopier.copy(ibzweeklyService.get(arg.getIbzweeklyid()), arg);
                    return arg;
                }else{
                    return ibzweeklyService.get((Long) args[0]);
                }
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return ibzweeklyService.getDraft((IbzWeekly) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return ibzweeklyService.checkKey((IbzWeekly) args[0]);
            }
            else if (iPSDEAction.getName().equals("createEveryWeekReport")) {
                return ibzweeklyService.createEveryWeekReport((IbzWeekly) args[0]);
            }
            else if (iPSDEAction.getName().equals("CreateGetLastWeekPlanAndWork")) {
                return ibzweeklyService.createGetLastWeekPlanAndWork((IbzWeekly) args[0]);
            }
            else if (iPSDEAction.getName().equals("EditGetLastWeekTaskAndComTask")) {
                return ibzweeklyService.editGetLastWeekTaskAndComTask((IbzWeekly) args[0]);
            }
            else if (iPSDEAction.getName().equals("haveRead")) {
                return ibzweeklyService.haveRead((IbzWeekly) args[0]);
            }
            else if (iPSDEAction.getName().equals("JugThisWeekCreateWeekly")) {
                return ibzweeklyService.jugThisWeekCreateWeekly((IbzWeekly) args[0]);
            }
            else if (iPSDEAction.getName().equals("pushUserWeekly")) {
                return ibzweeklyService.pushUserWeekly((IbzWeekly) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return ibzweeklyService.save((IbzWeekly) args[0]);
            }
            else if (iPSDEAction.getName().equals("submit")) {
                return ibzweeklyService.submit((IbzWeekly) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equalsIgnoreCase("Create")) {
                return ibzweeklyService.create((IbzWeekly) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Update")) {
                return ibzweeklyService.update((IbzWeekly) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Remove")) {
                return ibzweeklyService.remove((Long) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Get")) {
                if(args[0] instanceof IbzWeekly){
                    IbzWeekly arg = (IbzWeekly) args[0] ;
                    CachedBeanCopier.copy(ibzweeklyService.get(arg.getIbzweeklyid()), arg);
                    return arg;
                }else{
                    return ibzweeklyService.get((Long) args[0]);
                }
            }
            else if (strActionName.equalsIgnoreCase("GetDraft")) {
                return ibzweeklyService.getDraft((IbzWeekly) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("CheckKey")) {
                return ibzweeklyService.checkKey((IbzWeekly) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("createEveryWeekReport")) {
                return ibzweeklyService.createEveryWeekReport((IbzWeekly) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("CreateGetLastWeekPlanAndWork")) {
                return ibzweeklyService.createGetLastWeekPlanAndWork((IbzWeekly) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("EditGetLastWeekTaskAndComTask")) {
                return ibzweeklyService.editGetLastWeekTaskAndComTask((IbzWeekly) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("haveRead")) {
                return ibzweeklyService.haveRead((IbzWeekly) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("JugThisWeekCreateWeekly")) {
                return ibzweeklyService.jugThisWeekCreateWeekly((IbzWeekly) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("pushUserWeekly")) {
                return ibzweeklyService.pushUserWeekly((IbzWeekly) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Save")) {
                return ibzweeklyService.save((IbzWeekly) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("submit")) {
                return ibzweeklyService.submit((IbzWeekly) args[0]);
            }
            else  if (strActionName.equalsIgnoreCase(DEActions.SYSGET)) {
                if(args[0] instanceof IbzWeekly){
                    IbzWeekly arg = (IbzWeekly) args[0] ;
                    CachedBeanCopier.copy(ibzweeklyService.sysGet(arg.getIbzweeklyid()), arg);
                    return arg;
                }else{
                    return ibzweeklyService.sysGet((Long) args[0]);
                }
            }
            else  if (strActionName.equalsIgnoreCase(DEActions.SYSUPDATE)) {
                    return ibzweeklyService.sysUpdate((IbzWeekly) args[0]);
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
        IbzWeekly entity = null;
        String action = iPSDEAction.getName();
        if (arg0 instanceof IbzWeekly) {
            entity = (IbzWeekly) arg0;
        } else {
            if (arg0 != null && "get".equalsIgnoreCase(action)) {
                entity = ibzweeklyService.sysGet((Long) arg0);
            } else if ("remove".equalsIgnoreCase(action)) {
                entity = new IbzWeekly();
                entity.setIbzweeklyid((Long) arg0);
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

    @Around("execution(* cn.ibizlab.pms.core.report.service.impl.IbzWeeklyServiceImpl.*(..))")
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
        else if (action.equals("createEveryWeekReport")) {
            return aroundAction("createEveryWeekReport", point);
        }
        else if (action.equals("createGetLastWeekPlanAndWork")) {
            return aroundAction("CreateGetLastWeekPlanAndWork", point);
        }
        else if (action.equals("editGetLastWeekTaskAndComTask")) {
            return aroundAction("EditGetLastWeekTaskAndComTask", point);
        }
        else if (action.equals("haveRead")) {
            return aroundAction("haveRead", point);
        }
        else if (action.equals("jugThisWeekCreateWeekly")) {
            return aroundAction("JugThisWeekCreateWeekly", point);
        }
        else if (action.equals("pushUserWeekly")) {
            return aroundAction("pushUserWeekly", point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("submit")) {
            return aroundAction("submit", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchMyNotSubmit")) {
            return aroundDataSet("MyNotSubmit", point);
        }
        else if (action.equals("searchMyWeekly")) {
            return aroundDataSet("MyWeekly", point);
        }
        else if (action.equals("searchProductTeamMemberWeekly")) {
            return aroundDataSet("ProductTeamMemberWeekly", point);
        }
        else if (action.equals("searchProjectWeekly")) {
            return aroundDataSet("ProjectWeekly", point);
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
