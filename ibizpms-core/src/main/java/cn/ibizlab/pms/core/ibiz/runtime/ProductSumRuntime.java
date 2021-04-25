package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.ibiz.domain.ProductSum;
import cn.ibizlab.pms.core.ibiz.service.IProductSumService;
import cn.ibizlab.pms.core.ibiz.filter.ProductSumSearchContext;
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
@Component("ProductSumRuntime")
@Slf4j
public class ProductSumRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IProductSumService productsumService;

    @Autowired
    DELogicExecutor logicExecutor;

    @Override
    protected IEntityBase getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntityBase) {
            return (IEntityBase) o;
        } else {
            return productsumService.sysGet((Long) o);
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/ibiz/PSDATAENTITIES/ProductSum.json";
    }

    @Override
    public String getName() {
        return "IBZ_PRODUCTSUM";
    }

    @Override
    public IEntityBase createEntity() {
        return new ProductSum();
    }

    @Override
    protected IService getService() {
        return this.productsumService;
    }

    @Override
    public ProductSumSearchContext createSearchContext() {
        return new ProductSumSearchContext();
    }

    @Override
    public boolean existsData(ISearchContextBase iSearchContextBase) {
        Page<ProductSum> domains = productsumService.searchDefault((ProductSumSearchContext) iSearchContextBase);
        if (domains.getSize() == 0)
            return false;
        return true;
    }

    @Override
    public Page<ProductSum> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        ProductSumSearchContext searchContext = (ProductSumSearchContext) iSearchContextBase;
        if (iPSDEDataSet.getName().equals("DEFAULT"))
            return productsumService.searchDefault(searchContext);    
        if (iPSDEDataSet.getName().equals("ProductBugcnt_QA"))
            return productsumService.searchProductBugcnt_QA(searchContext);    
        if (iPSDEDataSet.getName().equals("ProductCreateStory"))
            return productsumService.searchProductCreateStory(searchContext);    
        if (iPSDEDataSet.getName().equals("ProductStoryHoursSum"))
            return productsumService.searchProductStoryHoursSum(searchContext);    
        if (iPSDEDataSet.getName().equals("ProductStorySum"))
            return productsumService.searchProductStorySum(searchContext);    
        if (iPSDEDataSet.getName().equals("ProductStorycntAndPlancnt"))
            return productsumService.searchProductStorycntAndPlancnt(searchContext);    
        if (iPSDEDataSet.getName().equals("ProductSumBugType"))
            return productsumService.searchProductSumBugType(searchContext);    
        return null;
    }

    @Override
    public List<ProductSum> selectDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        //暂未实现
        return null;
    }

    @Override
    public ProductSum selectOne(ISearchContextBase iSearchContextBase) {
        //单条数据查询，多条数数据时 返回第一条
        ProductSumSearchContext searchContext = (ProductSumSearchContext) iSearchContextBase;
        searchContext.setSize(1);
        List<ProductSum> domains = productsumService.select(searchContext);
        if (domains.size() == 0)
            return null;
        return domains.get(0);
    }

    @Override
    public List<ProductSum> select(ISearchContextBase iSearchContextBase) {
        ProductSumSearchContext searchContext = (ProductSumSearchContext) iSearchContextBase;
        return productsumService.select(searchContext);
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return productsumService.create((ProductSum) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return productsumService.update((ProductSum) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return productsumService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                if(args[0] instanceof ProductSum){
                    ProductSum arg = (ProductSum) args[0] ;
                    arg = productsumService.get(arg.getId()) ;
                    return arg;
                }else{
                    return productsumService.get((Long) args[0]);
                }
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return productsumService.getDraft((ProductSum) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return productsumService.checkKey((ProductSum) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return productsumService.save((ProductSum) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return productsumService.create((ProductSum) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return productsumService.update((ProductSum) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                if(args[0] instanceof ProductSum){
                    ProductSum arg = (ProductSum) args[0] ;
                    arg = productsumService.get(arg.getId()) ;
                    return arg;
                }else{
                    return productsumService.get((Long) args[0]);
                }
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return productsumService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return productsumService.sysGet((Long) args[0]);
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
        ProductSum entity = null;
        String action = iPSDEAction.getName();
        if (arg0 instanceof ProductSum) {
            entity = (ProductSum) arg0;
        } else {
            if (arg0 != null && "get".equalsIgnoreCase(action)) {
                entity = productsumService.sysGet((Long) arg0);
            } else if ("remove".equalsIgnoreCase(action)) {
                entity = new ProductSum();
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

    @Around("execution(* cn.ibizlab.pms.core.ibiz.service.impl.ProductSumServiceImpl.*(..))")
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
        else if (action.equals("searchProductBugcnt_QA")) {
            return aroundDataSet("ProductBugcnt_QA", point);
        }
        else if (action.equals("searchProductCreateStory")) {
            return aroundDataSet("ProductCreateStory", point);
        }
        else if (action.equals("searchProductStoryHoursSum")) {
            return aroundDataSet("ProductStoryHoursSum", point);
        }
        else if (action.equals("searchProductStorySum")) {
            return aroundDataSet("ProductStorySum", point);
        }
        else if (action.equals("searchProductStorycntAndPlancnt")) {
            return aroundDataSet("ProductStorycntAndPlancnt", point);
        }
        else if (action.equals("searchProductSumBugType")) {
            return aroundDataSet("ProductSumBugType", point);
        }
        return point.proceed();
    }

}
