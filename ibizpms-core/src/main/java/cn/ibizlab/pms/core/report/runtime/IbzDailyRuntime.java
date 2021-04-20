package cn.ibizlab.pms.core.report.runtime;

import cn.ibizlab.pms.core.report.domain.IbzDaily;
import cn.ibizlab.pms.core.report.service.IIbzDailyService;
import cn.ibizlab.pms.core.report.filter.IbzDailySearchContext;
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
@Component("IbzDailyRuntime")
@Slf4j
public class IbzDailyRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IIbzDailyService ibzdailyService;

    @Autowired
    DELogicExecutor logicExecutor;

    @Override
    protected IEntityBase getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntityBase) {
            return (IEntityBase) o;
        } else {
            return ibzdailyService.sysGet((Long) o);
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/report/PSDATAENTITIES/IbzDaily.json";
    }

    @Override
    public String getName() {
        return "IBZ_DAILY";
    }

    @Override
    public IEntityBase createEntity() {
        return new IbzDaily();
    }

    @Override
    protected IService getService() {
        return this.ibzdailyService;
    }

    @Override
    public IbzDailySearchContext createSearchContext() {
        return new IbzDailySearchContext();
    }

    @Override
    public boolean existsData(ISearchContextBase iSearchContextBase) {
        Page<IbzDaily> domains = ibzdailyService.searchDefault((IbzDailySearchContext) iSearchContextBase);
        if (domains.getSize() == 0)
            return false;
        return true;
    }

    @Override
    public Page<IbzDaily> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        if (iPSDEDataSet.getName().equals("DEFAULT"))
            return ibzdailyService.searchDefault((IbzDailySearchContext) iSearchContextBase);
        return null;
    }

    @Override
    public Page<IbzDaily> searchDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        //暂未实现
        return null;
    }

    @Override
    public IbzDaily selectOne(ISearchContextBase iSearchContextBase) {
        //单条数据查询，多条数数据时 返回第一条
        Page<IbzDaily> domains = ibzdailyService.searchDefault((IbzDailySearchContext) iSearchContextBase);
        if (domains.getSize() == 0)
            return null;
        return domains.getContent().get(0);
    }

    @Override
    public List<IbzDaily> select(ISearchContextBase iSearchContextBase) {
        return ibzdailyService.searchDefault((IbzDailySearchContext) iSearchContextBase).getContent();
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return ibzdailyService.create((IbzDaily) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return ibzdailyService.update((IbzDaily) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return ibzdailyService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                if(args[0] instanceof IbzDaily){
                    IbzDaily arg = (IbzDaily) args[0] ;
                    arg = ibzdailyService.get(arg.getIbzdailyid()) ;
                    return arg;
                }else{
                    return ibzdailyService.get((Long) args[0]);
                }
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return ibzdailyService.getDraft((IbzDaily) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return ibzdailyService.checkKey((IbzDaily) args[0]);
            }
            else if (iPSDEAction.getName().equals("CreateUserDaily")) {
                return ibzdailyService.createUserDaily((IbzDaily) args[0]);
            }
            else if (iPSDEAction.getName().equals("getYeaterdayDailyPlansTaskEdit")) {
                return ibzdailyService.getYeaterdayDailyPlansTaskEdit((IbzDaily) args[0]);
            }
            else if (iPSDEAction.getName().equals("getYesterdayDailyPlansTask")) {
                return ibzdailyService.getYesterdayDailyPlansTask((IbzDaily) args[0]);
            }
            else if (iPSDEAction.getName().equals("HaveRead")) {
                return ibzdailyService.haveRead((IbzDaily) args[0]);
            }
            else if (iPSDEAction.getName().equals("LinkCompleteTask")) {
                return ibzdailyService.linkCompleteTask((IbzDaily) args[0]);
            }
            else if (iPSDEAction.getName().equals("PushUserDaily")) {
                return ibzdailyService.pushUserDaily((IbzDaily) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return ibzdailyService.save((IbzDaily) args[0]);
            }
            else if (iPSDEAction.getName().equals("submit")) {
                return ibzdailyService.submit((IbzDaily) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return ibzdailyService.create((IbzDaily) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return ibzdailyService.update((IbzDaily) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                if(args[0] instanceof IbzDaily){
                    IbzDaily arg = (IbzDaily) args[0] ;
                    arg = ibzdailyService.get(arg.getIbzdailyid()) ;
                    return arg;
                }else{
                    return ibzdailyService.get((Long) args[0]);
                }
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return ibzdailyService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return ibzdailyService.sysGet((Long) args[0]);
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
    protected void onExecuteActionLogics(Object arg0, IPSDEAction iPSDEAction, String strAttachMode, IDynaInstRuntime iDynaInstRuntime, ProceedingJoinPoint joinPoint) throws Throwable {
        IbzDaily entity = null;
        String action = iPSDEAction.getName();
        if (arg0 instanceof IbzDaily) {
            entity = (IbzDaily) arg0;
        } else {
            if (arg0 != null && "get".equalsIgnoreCase(action)) {
                entity = ibzdailyService.sysGet((Long) arg0);
            } else if ("remove".equalsIgnoreCase(action)) {
                entity = new IbzDaily();
                entity.setIbzdailyid((Long) arg0);
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

    @Around("execution(* cn.ibizlab.pms.core.report.service.impl.IbzDailyServiceImpl.*(..))")
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
        else if (action.equals("createUserDaily")) {
            return aroundAction("CreateUserDaily", point);
        }
        else if (action.equals("getYeaterdayDailyPlansTaskEdit")) {
            return aroundAction("getYeaterdayDailyPlansTaskEdit", point);
        }
        else if (action.equals("getYesterdayDailyPlansTask")) {
            return aroundAction("getYesterdayDailyPlansTask", point);
        }
        else if (action.equals("haveRead")) {
            return aroundAction("HaveRead", point);
        }
        else if (action.equals("linkCompleteTask")) {
            return aroundAction("LinkCompleteTask", point);
        }
        else if (action.equals("pushUserDaily")) {
            return aroundAction("PushUserDaily", point);
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
        else if (action.equals("searchMyAllDaily")) {
            return aroundDataSet("MyAllDaily", point);
        }
        else if (action.equals("searchMyDaily")) {
            return aroundDataSet("MyDaily", point);
        }
        else if (action.equals("searchMyNotSubmit")) {
            return aroundDataSet("MyNotSubmit", point);
        }
        else if (action.equals("searchMySubmitDaily")) {
            return aroundDataSet("MySubmitDaily", point);
        }
        else if (action.equals("searchProductDaily")) {
            return aroundDataSet("ProductDaily", point);
        }
        else if (action.equals("searchProjectDaily")) {
            return aroundDataSet("ProjectDaily", point);
        }
        return point.proceed();
    }

}
