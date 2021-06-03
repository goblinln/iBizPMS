package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Product;
import cn.ibizlab.pms.core.zentao.service.IProductService;
import cn.ibizlab.pms.core.zentao.filter.ProductSearchContext;
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
import cn.ibizlab.pms.util.domain.WFInstance;
import net.ibizsys.runtime.dataentity.DataEntityRuntimeException;
import net.ibizsys.runtime.util.IEntityBase;
import net.ibizsys.runtime.util.ISearchContextBase;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import net.ibizsys.runtime.dataentity.action.DEActions;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.DigestUtils;
import java.io.Serializable;
import java.util.List;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.alibaba.fastjson.JSONObject;

@Component("ProductRuntime")
@Slf4j
public class ProductRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime implements IProductRuntime {

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
    public ProductSearchContext createSearchContext() {
        return new ProductSearchContext();
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
            return MAPPER.readValue(String.valueOf(objData),Product.class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public IEntityBase[] deserializeEntities(Object objData) {
        try {
            return MAPPER.readValue(String.valueOf(objData),Product[].class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public Page<Product> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        ProductSearchContext searchContext = (ProductSearchContext) iSearchContextBase;
        if (iPSDEDataSet.getName().equals("Account"))
            return productService.searchAccount(searchContext);    
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
        if (iPSDEDataSet.getName().equals("My"))
            return productService.searchMy(searchContext);    
        if (iPSDEDataSet.getName().equals("OpenQuery"))
            return productService.searchOpenQuery(searchContext);    
        if (iPSDEDataSet.getName().equals("ProductManagerQuery"))
            return productService.searchProductManagerQuery(searchContext);    
        if (iPSDEDataSet.getName().equals("ProductPM"))
            return productService.searchProductPM(searchContext);    
        if (iPSDEDataSet.getName().equals("ProductTeam"))
            return productService.searchProductTeam(searchContext);    
        if (iPSDEDataSet.getName().equals("StoryCURPROJECT"))
            return productService.searchStoryCurProject(searchContext);    
        return null;
    }

    @Override
    public List<Product> selectDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        ProductSearchContext searchContext = (ProductSearchContext) iSearchContextBase;
        if (iPSDataQuery.getName().equals("Account"))
            return productService.selectAccount(searchContext);
        if (iPSDataQuery.getName().equals("AllList"))
            return productService.selectAllList(searchContext);
        if (iPSDataQuery.getName().equals("AllProduct"))
            return productService.selectAllProduct(searchContext);
        if (iPSDataQuery.getName().equals("CheckNameOrCode"))
            return productService.selectCheckNameOrCode(searchContext);
        if (iPSDataQuery.getName().equals("CurDefault"))
            return productService.selectCurDefault(searchContext);
        if (iPSDataQuery.getName().equals("CURPROJECT"))
            return productService.selectCurProject(searchContext);
        if (iPSDataQuery.getName().equals("CurUer"))
            return productService.selectCurUer(searchContext);
        if (iPSDataQuery.getName().equals("DEFAULT"))
            return productService.selectDefault(searchContext);
        if (iPSDataQuery.getName().equals("DeveloperQuery"))
            return productService.selectDeveloperQuery(searchContext);
        if (iPSDataQuery.getName().equals("ESBulk"))
            return productService.selectESBulk(searchContext);
        if (iPSDataQuery.getName().equals("My"))
            return productService.selectMy(searchContext);
        if (iPSDataQuery.getName().equals("OpenQuery"))
            return productService.selectOpenQuery(searchContext);
        if (iPSDataQuery.getName().equals("ProductManagerQuery"))
            return productService.selectProductManagerQuery(searchContext);
        if (iPSDataQuery.getName().equals("ProductPM"))
            return productService.selectProductPM(searchContext);
        if (iPSDataQuery.getName().equals("ProductTeam"))
            return productService.selectProductTeam(searchContext);
        if (iPSDataQuery.getName().equals("SIMPLE"))
            return productService.selectSimple(searchContext);
        if (iPSDataQuery.getName().equals("StoryCURPROJECT"))
            return productService.selectStoryCurProject(searchContext);
        if (iPSDataQuery.getName().equals("VIEW"))
            return productService.selectView(searchContext);
        return null;
    }

    @Override
    public List<Product> select(ISearchContextBase iSearchContextBase) {
        ProductSearchContext searchContext = (ProductSearchContext) iSearchContextBase;
        return productService.select(searchContext);
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
                    CachedBeanCopier.copy(productService.get(arg.getId()), arg);
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
            if (strActionName.equalsIgnoreCase("Create")) {
                return productService.create((Product) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Update")) {
                return productService.update((Product) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Remove")) {
                return productService.remove((Long) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Get")) {
                if(args[0] instanceof Product){
                    Product arg = (Product) args[0] ;
                    CachedBeanCopier.copy(productService.get(arg.getId()), arg);
                    return arg;
                }else{
                    return productService.get((Long) args[0]);
                }
            }
            else if (strActionName.equalsIgnoreCase("GetDraft")) {
                return productService.getDraft((Product) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("CancelProductTop")) {
                return productService.cancelProductTop((Product) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("CheckKey")) {
                return productService.checkKey((Product) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Close")) {
                return productService.close((Product) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("MobProductCounter")) {
                return productService.mobProductCounter((Product) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("MobProductTestCounter")) {
                return productService.mobProductTestCounter((Product) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("ProductTop")) {
                return productService.productTop((Product) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Save")) {
                return productService.save((Product) args[0]);
            }
            else  if (strActionName.equalsIgnoreCase(DEActions.SYSGET)) {
                if(args[0] instanceof Product){
                    Product arg = (Product) args[0] ;
                    CachedBeanCopier.copy(productService.sysGet(arg.getId()), arg);
                    return arg;
                }else{
                    return productService.sysGet((Long) args[0]);
                }
            }
            else  if (strActionName.equalsIgnoreCase(DEActions.SYSUPDATE)) {
                    return productService.sysUpdate((Product) args[0]);
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
        if (objKey != null && "DER1N_ZT_PRODUCT_IBZPRO_PRODUCTLINE_LINE".equals(iPSDERBase.getName()) && !ObjectUtils.isEmpty(productService.selectByLine((Long)objKey)))
            return true;
        return false;
    }

    @Override
    public void resetByForeignKey(IPSDEField iPSDEField, Object objKey, IPSDERBase iPSDERBase) {
    }

    @Override
    public void removeByForeignKey(IPSDEField iPSDEField, Object objKey, IPSDERBase iPSDERBase) {
    }

    @Override
    protected void onWFRegister(Object arg0, IPSDEAction iPSDEAction, IPSDEWF iPSDEWF, IDynaInstRuntime iDynaInstRuntime, Object actionData) throws Throwable {
    }

    @Override
    protected void onWFUnregister(Object arg0, IPSDEAction iPSDEAction, IPSDEWF iPSDEWF, IDynaInstRuntime iDynaInstRuntime, Object actionData) throws Throwable {
    }
}
