package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.ibiz.domain.ProductStats;
import cn.ibizlab.pms.core.ibiz.service.IProductStatsService;
import cn.ibizlab.pms.core.ibiz.filter.ProductStatsSearchContext;
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
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.alibaba.fastjson.JSONObject;


@Aspect
@Order(100)
@Component("ProductStatsRuntime")
@Slf4j
public class ProductStatsRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IProductStatsService productstatsService;

    @Autowired
    DELogicExecutor logicExecutor;

    @Override
    protected IEntityBase getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntityBase) {
            return (IEntityBase) o;
        } else {
            return productstatsService.sysGet((Long) o);
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/ibiz/PSDATAENTITIES/ProductStats.json";
    }

    @Override
    public String getName() {
        return "IBZ_PRODUCTSTATS";
    }

    @Override
    public IEntityBase createEntity() {
        return new ProductStats();
    }

    @Override
    protected IService getService() {
        return this.productstatsService;
    }

    @Override
    public ProductStatsSearchContext createSearchContext() {
        return new ProductStatsSearchContext();
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
            return MAPPER.readValue(String.valueOf(objData),ProductStats.class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public IEntityBase[] deserializeEntities(Object objData) {
        try {
            return MAPPER.readValue(String.valueOf(objData),ProductStats[].class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public Page<ProductStats> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        ProductStatsSearchContext searchContext = (ProductStatsSearchContext) iSearchContextBase;
        if (iPSDEDataSet.getName().equals("DEFAULT"))
            return productstatsService.searchDefault(searchContext);    
        if (iPSDEDataSet.getName().equals("NoOpenProduct"))
            return productstatsService.searchNoOpenProduct(searchContext);    
        if (iPSDEDataSet.getName().equals("ProdctQuantiGird"))
            return productstatsService.searchProdctQuantiGird(searchContext);    
        if (iPSDEDataSet.getName().equals("ProductInputTable"))
            return productstatsService.searchProductInputTable(searchContext);    
        if (iPSDEDataSet.getName().equals("Productcompletionstatistics"))
            return productstatsService.searchProductcompletionstatistics(searchContext);    
        return null;
    }

    @Override
    public List<ProductStats> selectDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        ProductStatsSearchContext searchContext = (ProductStatsSearchContext) iSearchContextBase;
        if (iPSDataQuery.getName().equals("DEFAULT"))
            return productstatsService.selectDefault(searchContext);
        if (iPSDataQuery.getName().equals("NoOpenProduct"))
            return productstatsService.selectNoOpenProduct(searchContext);
        if (iPSDataQuery.getName().equals("ProdctQuantiGird"))
            return productstatsService.selectProdctQuantiGird(searchContext);
        if (iPSDataQuery.getName().equals("ProductInputTable"))
            return productstatsService.selectProductInputTable(searchContext);
        if (iPSDataQuery.getName().equals("Productcompletionstatistics"))
            return productstatsService.selectProductcompletionstatistics(searchContext);
        if (iPSDataQuery.getName().equals("VIEW"))
            return productstatsService.selectView(searchContext);
        return null;
    }

    @Override
    public List<ProductStats> select(ISearchContextBase iSearchContextBase) {
        ProductStatsSearchContext searchContext = (ProductStatsSearchContext) iSearchContextBase;
        return productstatsService.select(searchContext);
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return productstatsService.create((ProductStats) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return productstatsService.update((ProductStats) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return productstatsService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                if(args[0] instanceof ProductStats){
                    ProductStats arg = (ProductStats) args[0] ;
                    CachedBeanCopier.copy(productstatsService.get(arg.getId()), arg);
                    return arg;
                }else{
                    return productstatsService.get((Long) args[0]);
                }
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return productstatsService.getDraft((ProductStats) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return productstatsService.checkKey((ProductStats) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetTestStats")) {
                return productstatsService.getTestStats((ProductStats) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return productstatsService.save((ProductStats) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return productstatsService.create((ProductStats) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return productstatsService.update((ProductStats) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                if(args[0] instanceof ProductStats){
                    ProductStats arg = (ProductStats) args[0] ;
                    CachedBeanCopier.copy(productstatsService.get(arg.getId()), arg);
                    return arg;
                }else{
                    return productstatsService.get((Long) args[0]);
                }
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return productstatsService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return productstatsService.sysGet((Long) args[0]);
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
        ProductStats entity = null;
        String action = iPSDEAction.getName();
        if (arg0 instanceof ProductStats) {
            entity = (ProductStats) arg0;
        } else {
            if (arg0 != null && "get".equalsIgnoreCase(action)) {
                entity = productstatsService.sysGet((Long) arg0);
            } else if ("remove".equalsIgnoreCase(action)) {
                entity = new ProductStats();
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

    @Around("execution(* cn.ibizlab.pms.core.ibiz.service.impl.ProductStatsServiceImpl.*(..))")
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
        else if (action.equals("getTestStats")) {
            return aroundAction(action, point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchNoOpenProduct")) {
            return aroundDataSet("NoOpenProduct", point);
        }
        else if (action.equals("searchProdctQuantiGird")) {
            return aroundDataSet("ProdctQuantiGird", point);
        }
        else if (action.equals("searchProductInputTable")) {
            return aroundDataSet("ProductInputTable", point);
        }
        else if (action.equals("searchProductcompletionstatistics")) {
            return aroundDataSet("Productcompletionstatistics", point);
        }
        return point.proceed();
    }

}
