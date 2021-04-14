package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.ibiz.domain.IbzMyTerritory;
import cn.ibizlab.pms.core.ibiz.service.IIbzMyTerritoryService;
import cn.ibizlab.pms.core.ibiz.filter.IbzMyTerritorySearchContext;
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
@Component("IbzMyTerritoryRuntime")
public class IbzMyTerritoryRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IIbzMyTerritoryService ibzmyterritoryService;

    @Override
    protected Object getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntity) {
            return o;
        } else {
            return ibzmyterritoryService.sysGet(String.valueOf(o));
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/ibiz/PSDATAENTITIES/IbzMyTerritory.json";
    }

    @Override
    public String getName() {
        return "IBZ_MYTERRITORY";
    }

    @Override
    public Object createEntity() {
        return new IbzMyTerritory();
    }

    @Override
    protected IService getService() {
        return this.ibzmyterritoryService;
    }

    @Override
    protected QueryWrapperContext createSearchContext() {
        return new IbzMyTerritorySearchContext();
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return ibzmyterritoryService.create((IbzMyTerritory) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return ibzmyterritoryService.update((IbzMyTerritory) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return ibzmyterritoryService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                return ibzmyterritoryService.get((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return ibzmyterritoryService.getDraft((IbzMyTerritory) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return ibzmyterritoryService.checkKey((IbzMyTerritory) args[0]);
            }
            else if (iPSDEAction.getName().equals("MobMenuCount")) {
                return ibzmyterritoryService.mobMenuCount((IbzMyTerritory) args[0]);
            }
            else if (iPSDEAction.getName().equals("MyFavoriteCount")) {
                return ibzmyterritoryService.myFavoriteCount((IbzMyTerritory) args[0]);
            }
            else if (iPSDEAction.getName().equals("MyTerritoryCount")) {
                return ibzmyterritoryService.myTerritoryCount((IbzMyTerritory) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return ibzmyterritoryService.save((IbzMyTerritory) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return ibzmyterritoryService.create((IbzMyTerritory) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return ibzmyterritoryService.update((IbzMyTerritory) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                return ibzmyterritoryService.get((Long) args[0]);
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return ibzmyterritoryService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return ibzmyterritoryService.sysGet((Long) args[0]);
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

    @Around("execution(* cn.ibizlab.pms.core.ibiz.service.impl.IbzMyTerritoryServiceImpl.*(..))")
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
        else if (action.equals("mobMenuCount")) {
            return aroundAction("MobMenuCount", point);
        }
        else if (action.equals("myFavoriteCount")) {
            return aroundAction("MyFavoriteCount", point);
        }
        else if (action.equals("myTerritoryCount")) {
            return aroundAction("MyTerritoryCount", point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchMyWork")) {
            return aroundDataSet("MyWork", point);
        }
        else if (action.equals("searchMyWorkMob")) {
            return aroundDataSet("MyWorkMob", point);
        }
        else if (action.equals("searchMyWorkPm")) {
            return aroundDataSet("MyWorkPm", point);
        }
        else if (action.equals("searchPersonInfo")) {
            return aroundDataSet("PersonInfo", point);
        }
        else if (action.equals("searchWelcome")) {
            return aroundDataSet("welcome", point);
        }
        return point.proceed();
    }

}
