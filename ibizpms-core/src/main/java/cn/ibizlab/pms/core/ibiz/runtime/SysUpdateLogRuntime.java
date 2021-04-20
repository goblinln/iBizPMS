package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.ibiz.domain.SysUpdateLog;
import cn.ibizlab.pms.core.ibiz.service.ISysUpdateLogService;
import cn.ibizlab.pms.core.ibiz.filter.SysUpdateLogSearchContext;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import java.io.Serializable;
import java.util.List;



@Aspect
@Order(100)
@Component("SysUpdateLogRuntime")
@Slf4j
public class SysUpdateLogRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    ISysUpdateLogService sysupdatelogService;

    @Autowired
    DELogicExecutor logicExecutor;

    @Override
    protected IEntityBase getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntityBase) {
            return (IEntityBase) o;
        } else {
            return sysupdatelogService.sysGet((String) o);
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/ibiz/PSDATAENTITIES/SysUpdateLog.json";
    }

    @Override
    public String getName() {
        return "SYS_UPDATE_LOG";
    }

    @Override
    public IEntityBase createEntity() {
        return new SysUpdateLog();
    }

    @Override
    protected IService getService() {
        return this.sysupdatelogService;
    }

    @Override
    public SysUpdateLogSearchContext createSearchContext() {
        return new SysUpdateLogSearchContext();
    }

    @Override
    public boolean existsData(ISearchContextBase iSearchContextBase) {
        Page<SysUpdateLog> domains = sysupdatelogService.searchDefault((SysUpdateLogSearchContext) iSearchContextBase);
        if (domains.getSize() == 0)
            return false;
        return true;
    }

    @Override
    public Page<SysUpdateLog> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        if (iPSDEDataSet.getName().equals("DEFAULT"))
            return sysupdatelogService.searchDefault((SysUpdateLogSearchContext) iSearchContextBase);
        return null;
    }

    @Override
    public Page<SysUpdateLog> searchDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        //暂未实现
        return null;
    }

    @Override
    public SysUpdateLog selectOne(ISearchContextBase iSearchContextBase) {
        //单条数据查询，多条数数据时 返回第一条
        Page<SysUpdateLog> domains = sysupdatelogService.searchDefault((SysUpdateLogSearchContext) iSearchContextBase);
        if (domains.getSize() == 0)
            return null;
        return domains.getContent().get(0);
    }

    @Override
    public List<SysUpdateLog> select(ISearchContextBase iSearchContextBase) {
        return sysupdatelogService.searchDefault((SysUpdateLogSearchContext) iSearchContextBase).getContent();
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return sysupdatelogService.create((SysUpdateLog) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return sysupdatelogService.update((SysUpdateLog) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return sysupdatelogService.remove((String) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                if(args[0] instanceof SysUpdateLog){
                    SysUpdateLog arg = (SysUpdateLog) args[0] ;
                    arg = sysupdatelogService.get(arg.getSysupdatelogid()) ;
                    return arg;
                }else{
                    return sysupdatelogService.get((String) args[0]);
                }
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return sysupdatelogService.getDraft((SysUpdateLog) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return sysupdatelogService.checkKey((SysUpdateLog) args[0]);
            }
            else if (iPSDEAction.getName().equals("getLastUpdateInfo")) {
                return sysupdatelogService.getLastUpdateInfo((SysUpdateLog) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return sysupdatelogService.save((SysUpdateLog) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return sysupdatelogService.create((SysUpdateLog) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return sysupdatelogService.update((SysUpdateLog) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                if(args[0] instanceof SysUpdateLog){
                    SysUpdateLog arg = (SysUpdateLog) args[0] ;
                    arg = sysupdatelogService.get(arg.getSysupdatelogid()) ;
                    return arg;
                }else{
                    return sysupdatelogService.get((String) args[0]);
                }
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return sysupdatelogService.remove((String) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return sysupdatelogService.sysGet((String) args[0]);
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
        SysUpdateLog entity = null;
        String action = iPSDEAction.getName();
        if (arg0 instanceof SysUpdateLog) {
            entity = (SysUpdateLog) arg0;
        } else {
            if (arg0 != null && "get".equalsIgnoreCase(action)) {
                entity = sysupdatelogService.sysGet((String) arg0);
            } else if ("remove".equalsIgnoreCase(action)) {
                entity = new SysUpdateLog();
                entity.setSysupdatelogid((String) arg0);
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

    @Around("execution(* cn.ibizlab.pms.core.ibiz.service.impl.SysUpdateLogServiceImpl.*(..))")
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
        else if (action.equals("getLastUpdateInfo")) {
            return aroundAction("getLastUpdateInfo", point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        return point.proceed();
    }

}
