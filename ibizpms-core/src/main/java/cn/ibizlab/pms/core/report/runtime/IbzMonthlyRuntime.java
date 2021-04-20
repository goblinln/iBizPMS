package cn.ibizlab.pms.core.report.runtime;

import cn.ibizlab.pms.core.report.domain.IbzMonthly;
import cn.ibizlab.pms.core.report.service.IIbzMonthlyService;
import cn.ibizlab.pms.core.report.filter.IbzMonthlySearchContext;
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
    public boolean existsData(ISearchContextBase iSearchContextBase) {
        Page<IbzMonthly> domains = ibzmonthlyService.searchDefault((IbzMonthlySearchContext) iSearchContextBase);
        if (domains.getSize() == 0)
            return false;
        return true;
    }

    @Override
    public Page<IbzMonthly> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        if (iPSDEDataSet.getName().equals("DEFAULT"))
            return ibzmonthlyService.searchDefault((IbzMonthlySearchContext) iSearchContextBase);
        return null;
    }

    @Override
    public Page<IbzMonthly> searchDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        //暂未实现
        return null;
    }

    @Override
    public IbzMonthly selectOne(ISearchContextBase iSearchContextBase) {
        //单条数据查询，多条数数据时 返回第一条
        Page<IbzMonthly> domains = ibzmonthlyService.searchDefault((IbzMonthlySearchContext) iSearchContextBase);
        if (domains.getTotalElements() == 0)
            return null;
        return domains.getContent().get(0);
    }

    @Override
    public List<IbzMonthly> select(ISearchContextBase iSearchContextBase) {
        return ibzmonthlyService.searchDefault((IbzMonthlySearchContext) iSearchContextBase).getContent();
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
                    arg = ibzmonthlyService.get(arg.getIbzmonthlyid()) ;
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
            if (strActionName.equals(DEActions.CREATE)) {
                return ibzmonthlyService.create((IbzMonthly) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return ibzmonthlyService.update((IbzMonthly) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                if(args[0] instanceof IbzMonthly){
                    IbzMonthly arg = (IbzMonthly) args[0] ;
                    arg = ibzmonthlyService.get(arg.getIbzmonthlyid()) ;
                    return arg;
                }else{
                    return ibzmonthlyService.get((Long) args[0]);
                }
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return ibzmonthlyService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return ibzmonthlyService.sysGet((Long) args[0]);
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

}
