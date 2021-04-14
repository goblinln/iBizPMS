package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Burn;
import cn.ibizlab.pms.core.zentao.service.IBurnService;
import cn.ibizlab.pms.core.zentao.filter.BurnSearchContext;
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
@Component("BurnRuntime")
public class BurnRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IBurnService burnService;

    @Override
    protected Object getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntity) {
            return o;
        } else {
            return burnService.sysGet(String.valueOf(o));
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/Burn.json";
    }

    @Override
    public String getName() {
        return "ZT_BURN";
    }

    @Override
    public Object createEntity() {
        return new Burn();
    }

    @Override
    protected IService getService() {
        return this.burnService;
    }

    @Override
    protected QueryWrapperContext createSearchContext() {
        return new BurnSearchContext();
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return burnService.create((Burn) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return burnService.update((Burn) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return burnService.remove((String) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                return burnService.get((String) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return burnService.getDraft((Burn) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return burnService.checkKey((Burn) args[0]);
            }
            else if (iPSDEAction.getName().equals("ComputeBurn")) {
                return burnService.computeBurn((Burn) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return burnService.save((Burn) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return burnService.create((Burn) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return burnService.update((Burn) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                return burnService.get((String) args[0]);
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return burnService.remove((String) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return burnService.sysGet((String) args[0]);
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

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.BurnServiceImpl.*(..))")
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
        else if (action.equals("computeBurn")) {
            return aroundAction("ComputeBurn", point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchESTIMATEANDLEFT")) {
            return aroundDataSet("ESTIMATEANDLEFT", point);
        }
        return point.proceed();
    }

}
