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
import cn.ibizlab.pms.util.domain.EntityBase;
import cn.ibizlab.pms.util.errors.BadRequestAlertException;
import cn.ibizlab.pms.util.helper.DELogicExecutor;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.beans.factory.annotation.Autowired;
import net.ibizsys.runtime.dataentity.DEActions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;
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
            return ibzmyterritoryService.sysGet((Long)o);
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
    protected void onExecuteDELogic(Object arg0, IPSDEAction iPSDEAction, IPSDELogic iPSDELogic, IDynaInstRuntime iDynaInstRuntime, ProceedingJoinPoint joinPoint) throws Throwable {
        if (arg0 instanceof EntityBase)
            DELogicExecutor.getInstance().executeLogic((EntityBase) arg0, iPSDEAction, iPSDELogic, iDynaInstRuntime);
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
    protected void onExecuteActionLogics(Object arg0, IPSDEAction iPSDEAction, String strAttachMode, IDynaInstRuntime iDynaInstRuntime, ProceedingJoinPoint joinPoint) throws Throwable {
        IbzMyTerritory entity = null;
        String action = iPSDEAction.getName();
        if (arg0 instanceof IbzMyTerritory) {
            entity = (IbzMyTerritory) arg0;
        } else {
            if (arg0 != null && "get".equalsIgnoreCase(action)) {
                entity = ibzmyterritoryService.sysGet((Long) arg0);
            } else if ("remove".equalsIgnoreCase(action)) {
                entity = new IbzMyTerritory();
                entity.setId((Long) arg0);
            }
        }
        if (entity != null) {
            DELogicExecutor.getInstance().executeAttachLogic(entity, iPSDEAction, strAttachMode, iDynaInstRuntime);
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
