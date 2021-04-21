package cn.ibizlab.pms.core.ibizpro.runtime;

import cn.ibizlab.pms.core.ibizpro.domain.IbzPlanTemplet;
import cn.ibizlab.pms.core.ibizpro.service.IIbzPlanTempletService;
import cn.ibizlab.pms.core.ibizpro.filter.IbzPlanTempletSearchContext;
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
@Component("IbzPlanTempletRuntime")
@Slf4j
public class IbzPlanTempletRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IIbzPlanTempletService ibzplantempletService;

    @Autowired
    DELogicExecutor logicExecutor;

    @Override
    protected IEntityBase getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntityBase) {
            return (IEntityBase) o;
        } else {
            return ibzplantempletService.sysGet((String) o);
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/ibizpro/PSDATAENTITIES/IbzPlanTemplet.json";
    }

    @Override
    public String getName() {
        return "IBZ_PLANTEMPLET";
    }

    @Override
    public IEntityBase createEntity() {
        return new IbzPlanTemplet();
    }

    @Override
    protected IService getService() {
        return this.ibzplantempletService;
    }

    @Override
    public IbzPlanTempletSearchContext createSearchContext() {
        return new IbzPlanTempletSearchContext();
    }

    @Override
    public boolean existsData(ISearchContextBase iSearchContextBase) {
        Page<IbzPlanTemplet> domains = ibzplantempletService.searchDefault((IbzPlanTempletSearchContext) iSearchContextBase);
        if (domains.getSize() == 0)
            return false;
        return true;
    }

    @Override
    public Page<IbzPlanTemplet> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        IbzPlanTempletSearchContext searchContext = (IbzPlanTempletSearchContext) iSearchContextBase;
        if (iPSDEDataSet.getName().equals("CurUserTemplet"))
            return ibzplantempletService.searchCurUserTemplet(searchContext);    
        if (iPSDEDataSet.getName().equals("DEFAULT"))
            return ibzplantempletService.searchDefault(searchContext);    
        return null;
    }

    @Override
    public Page<IbzPlanTemplet> searchDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        //暂未实现
        return null;
    }

    @Override
    public IbzPlanTemplet selectOne(ISearchContextBase iSearchContextBase) {
        //单条数据查询，多条数数据时 返回第一条
        IbzPlanTempletSearchContext searchContext = (IbzPlanTempletSearchContext) iSearchContextBase;
        searchContext.setSize(1);
        Page<IbzPlanTemplet> domains = ibzplantempletService.searchDefault(searchContext);
        if (domains.getTotalElements() == 0)
            return null;
        return domains.getContent().get(0);
    }

    @Override
    public List<IbzPlanTemplet> select(ISearchContextBase iSearchContextBase) {
        IbzPlanTempletSearchContext searchContext = (IbzPlanTempletSearchContext) iSearchContextBase;
        searchContext.setSize(Integer.MAX_VALUE);
        return ibzplantempletService.searchDefault(searchContext).getContent();
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return ibzplantempletService.create((IbzPlanTemplet) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return ibzplantempletService.update((IbzPlanTemplet) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return ibzplantempletService.remove((String) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                if(args[0] instanceof IbzPlanTemplet){
                    IbzPlanTemplet arg = (IbzPlanTemplet) args[0] ;
                    arg = ibzplantempletService.get(arg.getIbzplantempletid()) ;
                    return arg;
                }else{
                    return ibzplantempletService.get((String) args[0]);
                }
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return ibzplantempletService.getDraft((IbzPlanTemplet) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return ibzplantempletService.checkKey((IbzPlanTemplet) args[0]);
            }
            else if (iPSDEAction.getName().equals("getPlan")) {
                return ibzplantempletService.getPlan((IbzPlanTemplet) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return ibzplantempletService.save((IbzPlanTemplet) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return ibzplantempletService.create((IbzPlanTemplet) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return ibzplantempletService.update((IbzPlanTemplet) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                if(args[0] instanceof IbzPlanTemplet){
                    IbzPlanTemplet arg = (IbzPlanTemplet) args[0] ;
                    arg = ibzplantempletService.get(arg.getIbzplantempletid()) ;
                    return arg;
                }else{
                    return ibzplantempletService.get((String) args[0]);
                }
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return ibzplantempletService.remove((String) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return ibzplantempletService.sysGet((String) args[0]);
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
        IbzPlanTemplet entity = null;
        String action = iPSDEAction.getName();
        if (arg0 instanceof IbzPlanTemplet) {
            entity = (IbzPlanTemplet) arg0;
        } else {
            if (arg0 != null && "get".equalsIgnoreCase(action)) {
                entity = ibzplantempletService.sysGet((String) arg0);
            } else if ("remove".equalsIgnoreCase(action)) {
                entity = new IbzPlanTemplet();
                entity.setIbzplantempletid((String) arg0);
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

    @Around("execution(* cn.ibizlab.pms.core.ibizpro.service.impl.IbzPlanTempletServiceImpl.*(..))")
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
        else if (action.equals("getPlan")) {
            return aroundAction("getPlan", point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("searchCurUserTemplet")) {
            return aroundDataSet("CurUserTemplet", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        return point.proceed();
    }

}
