package cn.ibizlab.pms.core.ibizpro.runtime;

import cn.ibizlab.pms.core.ibizpro.domain.IbizproProjectMonthly;
import cn.ibizlab.pms.core.ibizpro.service.IIbizproProjectMonthlyService;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproProjectMonthlySearchContext;
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
import net.ibizsys.runtime.dataentity.DEActions;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.ObjectUtils;
import java.io.Serializable;
import java.util.List;



@Aspect
@Order(100)
@Component("IbizproProjectMonthlyRuntime")
@Slf4j
public class IbizproProjectMonthlyRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IIbizproProjectMonthlyService ibizproprojectmonthlyService;

    @Override
    protected IEntityBase getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntityBase) {
            return (IEntityBase) o;
        } else {
            return ibizproprojectmonthlyService.sysGet((String) o);
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/ibizpro/PSDATAENTITIES/IbizproProjectMonthly.json";
    }

    @Override
    public String getName() {
        return "IBIZPRO_PROJECTMONTHLY";
    }

    @Override
    public IEntityBase createEntity() {
        return new IbizproProjectMonthly();
    }

    @Override
    protected IService getService() {
        return this.ibizproprojectmonthlyService;
    }

    @Override
    public IbizproProjectMonthlySearchContext createSearchContext() {
        return new IbizproProjectMonthlySearchContext();
    }

    @Override
    public boolean existsData(ISearchContextBase iSearchContextBase) {
        Page<IbizproProjectMonthly> domains = ibizproprojectmonthlyService.searchDefault((IbizproProjectMonthlySearchContext) iSearchContextBase);
        if (domains.getSize() == 0)
            return false;
        return true;
    }

    @Override
    public Page<IbizproProjectMonthly> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        if (iPSDEDataSet.getName().equals("DEFAULT"))
            return ibizproprojectmonthlyService.searchDefault((IbizproProjectMonthlySearchContext) iSearchContextBase);
        return null;
    }

    @Override
    public Page<IbizproProjectMonthly> searchDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        //暂未实现
        return null;
    }

    @Override
    public IbizproProjectMonthly selectOne(ISearchContextBase iSearchContextBase) {
        //单条数据查询，多条数数据时 返回第一条
        Page<IbizproProjectMonthly> domains = ibizproprojectmonthlyService.searchDefault((IbizproProjectMonthlySearchContext) iSearchContextBase);
        if (domains.getSize() == 0)
            return null;
        return domains.getContent().get(0);
    }

    @Override
    public List<IbizproProjectMonthly> select(ISearchContextBase iSearchContextBase) {
        return ibizproprojectmonthlyService.searchDefault((IbizproProjectMonthlySearchContext) iSearchContextBase).getContent();
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return ibizproprojectmonthlyService.create((IbizproProjectMonthly) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return ibizproprojectmonthlyService.update((IbizproProjectMonthly) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return ibizproprojectmonthlyService.remove((String) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                if(args[0] instanceof IbizproProjectMonthly){
                    IbizproProjectMonthly arg = (IbizproProjectMonthly) args[0] ;
                    arg = ibizproprojectmonthlyService.get(arg.getIbizproprojectmonthlyid()) ;
                    return arg;
                }else{
                    return ibizproprojectmonthlyService.get((String) args[0]);
                }
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return ibizproprojectmonthlyService.getDraft((IbizproProjectMonthly) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return ibizproprojectmonthlyService.checkKey((IbizproProjectMonthly) args[0]);
            }
            else if (iPSDEAction.getName().equals("ManualCreateMonthly")) {
                return ibizproprojectmonthlyService.manualCreateMonthly((IbizproProjectMonthly) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return ibizproprojectmonthlyService.save((IbizproProjectMonthly) args[0]);
            }
            else if (iPSDEAction.getName().equals("SumProjectMonthly")) {
                return ibizproprojectmonthlyService.sumProjectMonthly((IbizproProjectMonthly) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return ibizproprojectmonthlyService.create((IbizproProjectMonthly) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return ibizproprojectmonthlyService.update((IbizproProjectMonthly) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                if(args[0] instanceof IbizproProjectMonthly){
                    IbizproProjectMonthly arg = (IbizproProjectMonthly) args[0] ;
                    arg = ibizproprojectmonthlyService.get(arg.getIbizproprojectmonthlyid()) ;
                    return arg;
                }else{
                    return ibizproprojectmonthlyService.get((String) args[0]);
                }
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return ibizproprojectmonthlyService.remove((String) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return ibizproprojectmonthlyService.sysGet((String) args[0]);
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
        IbizproProjectMonthly entity = null;
        String action = iPSDEAction.getName();
        if (arg0 instanceof IbizproProjectMonthly) {
            entity = (IbizproProjectMonthly) arg0;
        } else {
            if (arg0 != null && "get".equalsIgnoreCase(action)) {
                entity = ibizproprojectmonthlyService.sysGet((String) arg0);
            } else if ("remove".equalsIgnoreCase(action)) {
                entity = new IbizproProjectMonthly();
                entity.setIbizproprojectmonthlyid((String) arg0);
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

    @Around("execution(* cn.ibizlab.pms.core.ibizpro.service.impl.IbizproProjectMonthlyServiceImpl.*(..))")
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
        else if (action.equals("manualCreateMonthly")) {
            return aroundAction("ManualCreateMonthly", point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("sumProjectMonthly")) {
            return aroundAction("SumProjectMonthly", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        return point.proceed();
    }

}
