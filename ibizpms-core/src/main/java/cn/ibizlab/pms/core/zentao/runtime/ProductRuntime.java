package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Product;
import cn.ibizlab.pms.core.zentao.service.IProductService;
import cn.ibizlab.pms.core.zentao.filter.ProductSearchContext;
import  cn.ibizlab.pms.util.filter.QueryWrapperContext;
import com.baomidou.mybatisplus.extension.service.IService;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import net.ibizsys.model.dataentity.der.IPSDERBase;
import net.ibizsys.model.dataentity.logic.IPSDELogic;
import net.ibizsys.model.dataentity.wf.IPSDEWF;
import net.ibizsys.runtime.IDynaInstRuntime;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.beans.factory.annotation.Autowired;
import net.ibizsys.runtime.dataentity.DEActions;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

@Aspect
@Order(100)
@Component("ProductRuntime")
public class ProductRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IProductService productService;

    @Override
    protected Object getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntity) {
            return o;
        } else {
            return productService.sysGet(String.valueOf(o));
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
    public Object createEntity() {
        return new Product();
    }

    @Override
    protected IService getService() {
        return this.productService;
    }

    @Override
    protected QueryWrapperContext createSearchContext() {
        return new ProductSearchContext();
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
                return productService.get((Long) args[0]);
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
                return productService.get((Long) args[0]);
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return productService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return productService.sysGet((Long) args[0]);
            }  else if (strActionName.equals(DEActions.SYSUPDATE)) {
                
            }         }
        
        return null;
    }

    @Override
    protected void onExecuteDELogic(Object arg0, IPSDEAction iPSDEAction, IPSDELogic iPSDELogic, IDynaInstRuntime iDynaInstRuntime, ProceedingJoinPoint joinPoint) throws Throwable {
        //执行处理逻辑
    }

    @Override
    protected void onExecuteActionLogics(Object arg0, IPSDEAction iPSDEAction, String strAttachMode, IDynaInstRuntime iDynaInstRuntime, ProceedingJoinPoint joinPoint) throws Throwable {
        //行为附加操作
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
        else if (action.equals("searchCurProject")) {
            return aroundDataSet("CURPROJECT", point);
        }
        else if (action.equals("searchCurUer")) {
            return aroundDataSet("CurUer", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchESBulk")) {
            return aroundDataSet("ESBulk", point);
        }
        else if (action.equals("searchProductPM")) {
            return aroundDataSet("ProductPM", point);
        }
        else if (action.equals("searchProductTeam")) {
            return aroundDataSet("ProductTeam", point);
        }
        else if (action.equals("searchStoryCurProject")) {
            return aroundDataSet("StoryCURPROJECT", point);
        }
        return point.proceed();
    }

}
