package cn.ibizlab.pms.core.ibizpro.runtime;

import cn.ibizlab.pms.core.ibizpro.domain.IbzproConfig;
import cn.ibizlab.pms.core.ibizpro.service.IIbzproConfigService;
import cn.ibizlab.pms.core.ibizpro.filter.IbzproConfigSearchContext;
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
@Component("IbzproConfigRuntime")
@Slf4j
public class IbzproConfigRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IIbzproConfigService ibzproconfigService;

    @Autowired
    DELogicExecutor logicExecutor;

    @Override
    protected IEntityBase getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntityBase) {
            return (IEntityBase) o;
        } else {
            return ibzproconfigService.sysGet((String) o);
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/ibizpro/PSDATAENTITIES/IbzproConfig.json";
    }

    @Override
    public String getName() {
        return "IBZPRO_CONFIG";
    }

    @Override
    public IEntityBase createEntity() {
        return new IbzproConfig();
    }

    @Override
    protected IService getService() {
        return this.ibzproconfigService;
    }

    @Override
    public IbzproConfigSearchContext createSearchContext() {
        return new IbzproConfigSearchContext();
    }

    @Override
    public boolean existsData(ISearchContextBase iSearchContextBase) {
        Page<IbzproConfig> domains = ibzproconfigService.searchDefault((IbzproConfigSearchContext) iSearchContextBase);
        if (domains.getSize() == 0)
            return false;
        return true;
    }

    @Override
    public Page<IbzproConfig> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        IbzproConfigSearchContext searchContext = (IbzproConfigSearchContext) iSearchContextBase;
        if (iPSDEDataSet.getName().equals("DEFAULT"))
            return ibzproconfigService.searchDefault(searchContext);    
        return null;
    }

    @Override
    public List<IbzproConfig> selectDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        IbzproConfigSearchContext searchContext = (IbzproConfigSearchContext) iSearchContextBase;
        if (iPSDataQuery.getName().equals("DEFAULT"))
            return ibzproconfigService.selectQueryByDefault(searchContext);
        if (iPSDataQuery.getName().equals("VIEW"))
            return ibzproconfigService.selectQueryByView(searchContext);
        return null;
    }

    @Override
    public IbzproConfig selectOne(ISearchContextBase iSearchContextBase) {
        //单条数据查询，多条数数据时 返回第一条
        IbzproConfigSearchContext searchContext = (IbzproConfigSearchContext) iSearchContextBase;
        searchContext.setSize(1);
        List<IbzproConfig> domains = ibzproconfigService.select(searchContext);
        if (domains.size() == 0)
            return null;
        return domains.get(0);
    }

    @Override
    public List<IbzproConfig> select(ISearchContextBase iSearchContextBase) {
        IbzproConfigSearchContext searchContext = (IbzproConfigSearchContext) iSearchContextBase;
        return ibzproconfigService.select(searchContext);
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return ibzproconfigService.create((IbzproConfig) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return ibzproconfigService.update((IbzproConfig) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return ibzproconfigService.remove((String) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                if(args[0] instanceof IbzproConfig){
                    IbzproConfig arg = (IbzproConfig) args[0] ;
                    arg = ibzproconfigService.get(arg.getIbzproconfigid()) ;
                    return arg;
                }else{
                    return ibzproconfigService.get((String) args[0]);
                }
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return ibzproconfigService.getDraft((IbzproConfig) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return ibzproconfigService.checkKey((IbzproConfig) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetSystemConfig")) {
                return ibzproconfigService.getSystemConfig((IbzproConfig) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return ibzproconfigService.save((IbzproConfig) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return ibzproconfigService.create((IbzproConfig) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return ibzproconfigService.update((IbzproConfig) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                if(args[0] instanceof IbzproConfig){
                    IbzproConfig arg = (IbzproConfig) args[0] ;
                    arg = ibzproconfigService.get(arg.getIbzproconfigid()) ;
                    return arg;
                }else{
                    return ibzproconfigService.get((String) args[0]);
                }
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return ibzproconfigService.remove((String) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return ibzproconfigService.sysGet((String) args[0]);
            }  else if (strActionName.equals(DEActions.SYSUPDATE)) {
                
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
        IbzproConfig entity = null;
        String action = iPSDEAction.getName();
        if (arg0 instanceof IbzproConfig) {
            entity = (IbzproConfig) arg0;
        } else {
            if (arg0 != null && "get".equalsIgnoreCase(action)) {
                entity = ibzproconfigService.sysGet((String) arg0);
            } else if ("remove".equalsIgnoreCase(action)) {
                entity = new IbzproConfig();
                entity.setIbzproconfigid((String) arg0);
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

    @Around("execution(* cn.ibizlab.pms.core.ibizpro.service.impl.IbzproConfigServiceImpl.*(..))")
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
        else if (action.equals("getSystemConfig")) {
            return aroundAction("GetSystemConfig", point);
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
