package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Product;
import cn.ibizlab.pms.core.zentao.service.IProductService;
import cn.ibizlab.pms.core.zentao.filter.ProductSearchContext;
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
@Component("ProductRuntime")
@Slf4j
public class ProductRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IProductService productService;

    @Autowired
    DELogicExecutor logicExecutor;

    @Override
    protected IEntityBase getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntityBase) {
            return (IEntityBase) o;
        } else {
            return productService.sysGet((Long) o);
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/Product.json";
    }

    @Override
    public String getName() {
        return "ZT_PRODUCT";
    }

    @Override
    public IEntityBase createEntity() {
        return new Product();
    }

    @Override
    protected IService getService() {
        return this.productService;
    }

    @Override
    public ProductSearchContext createSearchContext() {
        return new ProductSearchContext();
    }

    @Override
    public boolean existsData(ISearchContextBase iSearchContextBase) {
        Page<Product> domains = productService.searchDefault((ProductSearchContext) iSearchContextBase);
        if (domains.getSize() == 0)
            return false;
        return true;
    }

    @Override
    public Page<Product> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        ProductSearchContext searchContext = (ProductSearchContext) iSearchContextBase;
        if (iPSDEDataSet.getName().equals("AllList"))
            return productService.searchAllList(searchContext);    
        if (iPSDEDataSet.getName().equals("AllProduct"))
            return productService.searchAllProduct(searchContext);    
        if (iPSDEDataSet.getName().equals("CheckNameOrCode"))
            return productService.searchCheckNameOrCode(searchContext);    
        if (iPSDEDataSet.getName().equals("CurDefault"))
            return productService.searchCurDefault(searchContext);    
        if (iPSDEDataSet.getName().equals("CURPROJECT"))
            return productService.searchCurProject(searchContext);    
        if (iPSDEDataSet.getName().equals("CurUer"))
            return productService.searchCurUer(searchContext);    
        if (iPSDEDataSet.getName().equals("DEFAULT"))
            return productService.searchDefault(searchContext);    
        if (iPSDEDataSet.getName().equals("DeveloperQuery"))
            return productService.searchDeveloperQuery(searchContext);    
        if (iPSDEDataSet.getName().equals("ESBulk"))
            return productService.searchESBulk(searchContext);    
        if (iPSDEDataSet.getName().equals("OpenQuery"))
            return productService.searchOpenQuery(searchContext);    
        if (iPSDEDataSet.getName().equals("POQuery"))
            return productService.searchPOQuery(searchContext);    
        if (iPSDEDataSet.getName().equals("ProductManagerQuery"))
            return productService.searchProductManagerQuery(searchContext);    
        if (iPSDEDataSet.getName().equals("ProductPM"))
            return productService.searchProductPM(searchContext);    
        if (iPSDEDataSet.getName().equals("ProductTeam"))
            return productService.searchProductTeam(searchContext);    
        if (iPSDEDataSet.getName().equals("QDQuery"))
            return productService.searchQDQuery(searchContext);    
        if (iPSDEDataSet.getName().equals("RDQuery"))
            return productService.searchRDQuery(searchContext);    
        if (iPSDEDataSet.getName().equals("StoryCURPROJECT"))
            return productService.searchStoryCurProject(searchContext);    
        return null;
    }

    @Override
    public Page<Product> searchDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        //暂未实现
        return null;
    }

    @Override
    public Product selectOne(ISearchContextBase iSearchContextBase) {
        //单条数据查询，多条数数据时 返回第一条
        ProductSearchContext searchContext = (ProductSearchContext) iSearchContextBase;
        searchContext.setSize(1);
        Page<Product> domains = productService.searchDefault(searchContext);
        if (domains.getTotalElements() == 0)
            return null;
        return domains.getContent().get(0);
    }

    @Override
    public List<Product> select(ISearchContextBase iSearchContextBase) {
        ProductSearchContext searchContext = (ProductSearchContext) iSearchContextBase;
        searchContext.setSize(Integer.MAX_VALUE);
        return productService.searchDefault(searchContext).getContent();
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return productService.create((Product) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return productService.update((Product) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return productService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                if(args[0] instanceof Product){
                    Product arg = (Product) args[0] ;
                    arg = productService.get(arg.getId()) ;
                    return arg;
                }else{
                    return productService.get((Long) args[0]);
                }
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return productService.getDraft((Product) args[0]);
            }
            else if (iPSDEAction.getName().equals("CancelProductTop")) {
                return productService.cancelProductTop((Product) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return productService.checkKey((Product) args[0]);
            }
            else if (iPSDEAction.getName().equals("Close")) {
                return productService.close((Product) args[0]);
            }
            else if (iPSDEAction.getName().equals("MobProductCounter")) {
                return productService.mobProductCounter((Product) args[0]);
            }
            else if (iPSDEAction.getName().equals("MobProductTestCounter")) {
                return productService.mobProductTestCounter((Product) args[0]);
            }
            else if (iPSDEAction.getName().equals("ProductTop")) {
                return productService.productTop((Product) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return productService.save((Product) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return productService.create((Product) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return productService.update((Product) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                if(args[0] instanceof Product){
                    Product arg = (Product) args[0] ;
                    arg = productService.get(arg.getId()) ;
                    return arg;
                }else{
                    return productService.get((Long) args[0]);
                }
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return productService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return productService.sysGet((Long) args[0]);
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
        Product entity = null;
        String action = iPSDEAction.getName();
        if (arg0 instanceof Product) {
            entity = (Product) arg0;
        } else {
            if (arg0 != null && "get".equalsIgnoreCase(action)) {
                entity = productService.sysGet((Long) arg0);
            } else if ("remove".equalsIgnoreCase(action)) {
                entity = new Product();
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

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.ProductServiceImpl.*(..))")
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
        else if (action.equals("cancelProductTop")) {
            return aroundAction("CancelProductTop", point);
        }
        else if (action.equals("checkKey")) {
            return aroundAction("CheckKey", point);
        }
        else if (action.equals("close")) {
            return aroundAction("Close", point);
        }
        else if (action.equals("mobProductCounter")) {
            return aroundAction("MobProductCounter", point);
        }
        else if (action.equals("mobProductTestCounter")) {
            return aroundAction("MobProductTestCounter", point);
        }
        else if (action.equals("productTop")) {
            return aroundAction("ProductTop", point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("searchAllList")) {
            return aroundDataSet("AllList", point);
        }
        else if (action.equals("searchAllProduct")) {
            return aroundDataSet("AllProduct", point);
        }
        else if (action.equals("searchCheckNameOrCode")) {
            return aroundDataSet("CheckNameOrCode", point);
        }
        else if (action.equals("searchCurDefault")) {
            return aroundDataSet("CurDefault", point);
        }
        else if (action.equals("searchCurProject")) {
            return aroundDataSet("CURPROJECT", point);
        }
        else if (action.equals("searchCurUer")) {
            return aroundDataSet("CurUer", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchDeveloperQuery")) {
            return aroundDataSet("DeveloperQuery", point);
        }
        else if (action.equals("searchESBulk")) {
            return aroundDataSet("ESBulk", point);
        }
        else if (action.equals("searchOpenQuery")) {
            return aroundDataSet("OpenQuery", point);
        }
        else if (action.equals("searchPOQuery")) {
            return aroundDataSet("POQuery", point);
        }
        else if (action.equals("searchProductManagerQuery")) {
            return aroundDataSet("ProductManagerQuery", point);
        }
        else if (action.equals("searchProductPM")) {
            return aroundDataSet("ProductPM", point);
        }
        else if (action.equals("searchProductTeam")) {
            return aroundDataSet("ProductTeam", point);
        }
        else if (action.equals("searchQDQuery")) {
            return aroundDataSet("QDQuery", point);
        }
        else if (action.equals("searchRDQuery")) {
            return aroundDataSet("RDQuery", point);
        }
        else if (action.equals("searchStoryCurProject")) {
            return aroundDataSet("StoryCURPROJECT", point);
        }
        return point.proceed();
    }

}
