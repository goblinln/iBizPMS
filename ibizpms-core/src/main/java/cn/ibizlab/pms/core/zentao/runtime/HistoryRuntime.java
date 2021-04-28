package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.History;
import cn.ibizlab.pms.core.zentao.service.IHistoryService;
import cn.ibizlab.pms.core.zentao.filter.HistorySearchContext;
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
import net.ibizsys.runtime.dataentity.DataEntityRuntimeException;
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
@Component("HistoryRuntime")
@Slf4j
public class HistoryRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IHistoryService historyService;

    @Autowired
    DELogicExecutor logicExecutor;

    @Override
    protected IEntityBase getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntityBase) {
            return (IEntityBase) o;
        } else {
            return historyService.sysGet((Long) o);
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/History.json";
    }

    @Override
    public String getName() {
        return "ZT_HISTORY";
    }

    @Override
    public IEntityBase createEntity() {
        return new History();
    }

    @Override
    protected IService getService() {
        return this.historyService;
    }

    @Override
    public HistorySearchContext createSearchContext() {
        return new HistorySearchContext();
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
            return MAPPER.readValue(String.valueOf(objData),History.class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public IEntityBase[] deserializeEntities(Object objData) {
        try {
            return MAPPER.readValue(String.valueOf(objData),History[].class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public boolean existsData(ISearchContextBase iSearchContextBase) {
        Page<History> domains = historyService.searchDefault((HistorySearchContext) iSearchContextBase);
        if (domains.getSize() == 0)
            return false;
        return true;
    }

    @Override
    public Page<History> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        HistorySearchContext searchContext = (HistorySearchContext) iSearchContextBase;
        if (iPSDEDataSet.getName().equals("DEFAULT"))
            return historyService.searchDefault(searchContext);    
        return null;
    }

    @Override
    public List<History> selectDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        HistorySearchContext searchContext = (HistorySearchContext) iSearchContextBase;
        if (iPSDataQuery.getName().equals("DEFAULT"))
            return historyService.selectDefault(searchContext);
        if (iPSDataQuery.getName().equals("VIEW"))
            return historyService.selectView(searchContext);
        return null;
    }

    @Override
    public History selectOne(ISearchContextBase iSearchContextBase) {
        //单条数据查询，多条数数据时 返回第一条
        HistorySearchContext searchContext = (HistorySearchContext) iSearchContextBase;
        searchContext.setSize(1);
        List<History> domains = historyService.select(searchContext);
        if (domains.size() == 0)
            return null;
        return domains.get(0);
    }

    @Override
    public List<History> select(ISearchContextBase iSearchContextBase) {
        HistorySearchContext searchContext = (HistorySearchContext) iSearchContextBase;
        return historyService.select(searchContext);
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return historyService.create((History) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return historyService.update((History) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return historyService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                if(args[0] instanceof History){
                    History arg = (History) args[0] ;
                    arg = historyService.get(arg.getId()) ;
                    return arg;
                }else{
                    return historyService.get((Long) args[0]);
                }
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return historyService.getDraft((History) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return historyService.checkKey((History) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return historyService.save((History) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return historyService.create((History) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return historyService.update((History) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                if(args[0] instanceof History){
                    History arg = (History) args[0] ;
                    arg = historyService.get(arg.getId()) ;
                    return arg;
                }else{
                    return historyService.get((Long) args[0]);
                }
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return historyService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return historyService.sysGet((Long) args[0]);
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
        History entity = null;
        String action = iPSDEAction.getName();
        if (arg0 instanceof History) {
            entity = (History) arg0;
        } else {
            if (arg0 != null && "get".equalsIgnoreCase(action)) {
                entity = historyService.sysGet((Long) arg0);
            } else if ("remove".equalsIgnoreCase(action)) {
                entity = new History();
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

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.HistoryServiceImpl.*(..))")
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
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        return point.proceed();
    }

}
