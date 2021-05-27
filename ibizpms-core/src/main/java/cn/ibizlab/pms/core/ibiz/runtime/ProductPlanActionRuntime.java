package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.ibiz.domain.ProductPlanAction;
import cn.ibizlab.pms.core.ibiz.service.IProductPlanActionService;
import cn.ibizlab.pms.core.ibiz.filter.ProductPlanActionSearchContext;
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
import cn.ibizlab.pms.util.domain.WFTask;
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


@Aspect
@org.springframework.core.annotation.Order(100)
@Component("ProductPlanActionRuntime")
@Slf4j
public class ProductPlanActionRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IProductPlanActionService productplanactionService;

    @Autowired
    DELogicExecutor logicExecutor;

    @Override
    protected IEntityBase getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntityBase) {
            return (IEntityBase) o;
        } else {
            return productplanactionService.sysGet((Long) o);
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/ibiz/PSDATAENTITIES/ProductPlanAction.json";
    }

    @Override
    public String getName() {
        return "IBZPRO_PRODUCTPLANACTION";
    }

    @Override
    public IEntityBase createEntity() {
        return new ProductPlanAction();
    }

    @Override
    protected IService getService() {
        return this.productplanactionService;
    }

    @Override
    public ProductPlanActionSearchContext createSearchContext() {
        return new ProductPlanActionSearchContext();
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
            return MAPPER.readValue(String.valueOf(objData),ProductPlanAction.class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public IEntityBase[] deserializeEntities(Object objData) {
        try {
            return MAPPER.readValue(String.valueOf(objData),ProductPlanAction[].class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public Page<ProductPlanAction> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        ProductPlanActionSearchContext searchContext = (ProductPlanActionSearchContext) iSearchContextBase;
        if (iPSDEDataSet.getName().equals("DEFAULT"))
            return productplanactionService.searchDefault(searchContext);    
        if (iPSDEDataSet.getName().equals("Type"))
            return productplanactionService.searchType(searchContext);    
        return null;
    }

    @Override
    public List<ProductPlanAction> selectDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        ProductPlanActionSearchContext searchContext = (ProductPlanActionSearchContext) iSearchContextBase;
        if (iPSDataQuery.getName().equals("DEFAULT"))
            return productplanactionService.selectDefault(searchContext);
        if (iPSDataQuery.getName().equals("SIMPLE"))
            return productplanactionService.selectSimple(searchContext);
        if (iPSDataQuery.getName().equals("Type"))
            return productplanactionService.selectType(searchContext);
        if (iPSDataQuery.getName().equals("VIEW"))
            return productplanactionService.selectView(searchContext);
        return null;
    }

    @Override
    public List<ProductPlanAction> select(ISearchContextBase iSearchContextBase) {
        ProductPlanActionSearchContext searchContext = (ProductPlanActionSearchContext) iSearchContextBase;
        return productplanactionService.select(searchContext);
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return productplanactionService.create((ProductPlanAction) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return productplanactionService.update((ProductPlanAction) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return productplanactionService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                if(args[0] instanceof ProductPlanAction){
                    ProductPlanAction arg = (ProductPlanAction) args[0] ;
                    CachedBeanCopier.copy(productplanactionService.get(arg.getId()), arg);
                    return arg;
                }else{
                    return productplanactionService.get((Long) args[0]);
                }
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return productplanactionService.getDraft((ProductPlanAction) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return productplanactionService.checkKey((ProductPlanAction) args[0]);
            }
            else if (iPSDEAction.getName().equals("Comment")) {
                return productplanactionService.comment((ProductPlanAction) args[0]);
            }
            else if (iPSDEAction.getName().equals("CreateHis")) {
                return productplanactionService.createHis((ProductPlanAction) args[0]);
            }
            else if (iPSDEAction.getName().equals("editComment")) {
                return productplanactionService.editComment((ProductPlanAction) args[0]);
            }
            else if (iPSDEAction.getName().equals("ManagePmsEe")) {
                return productplanactionService.managePmsEe((ProductPlanAction) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return productplanactionService.save((ProductPlanAction) args[0]);
            }
            else if (iPSDEAction.getName().equals("sendMarkDone")) {
                return productplanactionService.sendMarkDone((ProductPlanAction) args[0]);
            }
            else if (iPSDEAction.getName().equals("sendTodo")) {
                return productplanactionService.sendTodo((ProductPlanAction) args[0]);
            }
            else if (iPSDEAction.getName().equals("sendToread")) {
                return productplanactionService.sendToread((ProductPlanAction) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equalsIgnoreCase("Create")) {
                return productplanactionService.create((ProductPlanAction) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Update")) {
                return productplanactionService.update((ProductPlanAction) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Remove")) {
                return productplanactionService.remove((Long) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Get")) {
                if(args[0] instanceof ProductPlanAction){
                    ProductPlanAction arg = (ProductPlanAction) args[0] ;
                    CachedBeanCopier.copy(productplanactionService.get(arg.getId()), arg);
                    return arg;
                }else{
                    return productplanactionService.get((Long) args[0]);
                }
            }
            else if (strActionName.equalsIgnoreCase("GetDraft")) {
                return productplanactionService.getDraft((ProductPlanAction) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("CheckKey")) {
                return productplanactionService.checkKey((ProductPlanAction) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Comment")) {
                return productplanactionService.comment((ProductPlanAction) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("CreateHis")) {
                return productplanactionService.createHis((ProductPlanAction) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("editComment")) {
                return productplanactionService.editComment((ProductPlanAction) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("ManagePmsEe")) {
                return productplanactionService.managePmsEe((ProductPlanAction) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Save")) {
                return productplanactionService.save((ProductPlanAction) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("sendMarkDone")) {
                return productplanactionService.sendMarkDone((ProductPlanAction) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("sendTodo")) {
                return productplanactionService.sendTodo((ProductPlanAction) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("sendToread")) {
                return productplanactionService.sendToread((ProductPlanAction) args[0]);
            }
            else  if (strActionName.equalsIgnoreCase(DEActions.SYSGET)) {
                if(args[0] instanceof ProductPlanAction){
                    ProductPlanAction arg = (ProductPlanAction) args[0] ;
                    CachedBeanCopier.copy(productplanactionService.sysGet(arg.getId()), arg);
                    return arg;
                }else{
                    return productplanactionService.sysGet((Long) args[0]);
                }
            }
            else  if (strActionName.equalsIgnoreCase(DEActions.SYSUPDATE)) {
                    return productplanactionService.sysUpdate((ProductPlanAction) args[0]);
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
        ProductPlanAction entity = null;
        String action = iPSDEAction.getName();
        if (arg0 instanceof ProductPlanAction) {
            entity = (ProductPlanAction) arg0;
        } else {
            if (arg0 != null && "get".equalsIgnoreCase(action)) {
                entity = productplanactionService.sysGet((Long) arg0);
            } else if ("remove".equalsIgnoreCase(action)) {
                entity = new ProductPlanAction();
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

    @Around("execution(* cn.ibizlab.pms.core.ibiz.service.impl.ProductPlanActionServiceImpl.*(..))")
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
        else if (action.equals("comment")) {
            return aroundAction("Comment", point);
        }
        else if (action.equals("createHis")) {
            return aroundAction("CreateHis", point);
        }
        else if (action.equals("editComment")) {
            return aroundAction("editComment", point);
        }
        else if (action.equals("managePmsEe")) {
            return aroundAction("ManagePmsEe", point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("sendMarkDone")) {
            return aroundAction("sendMarkDone", point);
        }
        else if (action.equals("sendTodo")) {
            return aroundAction("sendTodo", point);
        }
        else if (action.equals("sendToread")) {
            return aroundAction("sendToread", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchType")) {
            return aroundDataSet("Type", point);
        }
        return point.proceed();
    }

    @Override
    protected void onWFRegister(Object arg0, IPSDEAction iPSDEAction, IPSDEWF iPSDEWF, IDynaInstRuntime iDynaInstRuntime, Object actionData) throws Throwable {
    }

    @Override
    protected void onWFUnregister(Object arg0, IPSDEAction iPSDEAction, IPSDEWF iPSDEWF, IDynaInstRuntime iDynaInstRuntime, Object actionData) throws Throwable {
    }
}
