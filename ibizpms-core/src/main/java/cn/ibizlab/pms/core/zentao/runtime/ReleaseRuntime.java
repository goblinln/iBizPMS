package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Release;
import cn.ibizlab.pms.core.zentao.service.IReleaseService;
import cn.ibizlab.pms.core.zentao.filter.ReleaseSearchContext;
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
@Component("ReleaseRuntime")
public class ReleaseRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IReleaseService releaseService;

    @Override
    protected Object getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntity) {
            return o;
        } else {
            return releaseService.sysGet((Long)o);
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/Release.json";
    }

    @Override
    public String getName() {
        return "ZT_RELEASE";
    }

    @Override
    public Object createEntity() {
        return new Release();
    }

    @Override
    protected IService getService() {
        return this.releaseService;
    }

    @Override
    public ReleaseSearchContext() createSearchContext() {
        return new ReleaseSearchContext();
    }

    @Override
    public void setSearchCondition(Object searchContext, IPSDEField iPSDEField, String strCondition, Object objValue) {
        //设置查询条件 net.ibizsys.runtime.util.Conditions
    }

    @Override
    public boolean existsData(Object searchContext) {
        //判断数据是否存在
        return false;
    }

    @Override
    public Page<?> searchDataSet(IPSDEDataSet iPSDEDataSet, Object searchContext) {
        //查询数据集合
        return null;
    }

    @Override
    public Page<?> searchDataQuery(IPSDEDataQuery iPSDataQuery, Object searchContext) {
        //暂未实现
        return null;
    }

    @Override
    public Object selectOne(Object searchContext) {
        //单条数据查询，多条数数据时 返回第一条
        return null;
    }

    @Override
    public List<?> select(Object searchContext) {
        //list
        return null;
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return releaseService.create((Release) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return releaseService.update((Release) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return releaseService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                return releaseService.get((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return releaseService.getDraft((Release) args[0]);
            }
            else if (iPSDEAction.getName().equals("Activate")) {
                return releaseService.activate((Release) args[0]);
            }
            else if (iPSDEAction.getName().equals("BatchUnlinkBug")) {
                return releaseService.batchUnlinkBug((Release) args[0]);
            }
            else if (iPSDEAction.getName().equals("ChangeStatus")) {
                return releaseService.changeStatus((Release) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return releaseService.checkKey((Release) args[0]);
            }
            else if (iPSDEAction.getName().equals("linkBug")) {
                return releaseService.linkBug((Release) args[0]);
            }
            else if (iPSDEAction.getName().equals("LinkBugbyBug")) {
                return releaseService.linkBugbyBug((Release) args[0]);
            }
            else if (iPSDEAction.getName().equals("LinkBugbyLeftBug")) {
                return releaseService.linkBugbyLeftBug((Release) args[0]);
            }
            else if (iPSDEAction.getName().equals("linkStory")) {
                return releaseService.linkStory((Release) args[0]);
            }
            else if (iPSDEAction.getName().equals("MobReleaseCounter")) {
                return releaseService.mobReleaseCounter((Release) args[0]);
            }
            else if (iPSDEAction.getName().equals("OneClickRelease")) {
                return releaseService.oneClickRelease((Release) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return releaseService.save((Release) args[0]);
            }
            else if (iPSDEAction.getName().equals("Terminate")) {
                return releaseService.terminate((Release) args[0]);
            }
            else if (iPSDEAction.getName().equals("UnlinkBug")) {
                return releaseService.unlinkBug((Release) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return releaseService.create((Release) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return releaseService.update((Release) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                return releaseService.get((Long) args[0]);
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return releaseService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return releaseService.sysGet((Long) args[0]);
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
        Release entity = null;
        String action = iPSDEAction.getName();
        if (arg0 instanceof Release) {
            entity = (Release) arg0;
        } else {
            if (arg0 != null && "get".equalsIgnoreCase(action)) {
                entity = releaseService.sysGet((Long) arg0);
            } else if ("remove".equalsIgnoreCase(action)) {
                entity = new Release();
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

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.ReleaseServiceImpl.*(..))")
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
        else if (action.equals("activate")) {
            return aroundAction("Activate", point);
        }
        else if (action.equals("batchUnlinkBug")) {
            return aroundAction("BatchUnlinkBug", point);
        }
        else if (action.equals("changeStatus")) {
            return aroundAction("ChangeStatus", point);
        }
        else if (action.equals("checkKey")) {
            return aroundAction("CheckKey", point);
        }
        else if (action.equals("linkBug")) {
            return aroundAction("linkBug", point);
        }
        else if (action.equals("linkBugbyBug")) {
            return aroundAction("LinkBugbyBug", point);
        }
        else if (action.equals("linkBugbyLeftBug")) {
            return aroundAction("LinkBugbyLeftBug", point);
        }
        else if (action.equals("linkStory")) {
            return aroundAction("linkStory", point);
        }
        else if (action.equals("mobReleaseCounter")) {
            return aroundAction("MobReleaseCounter", point);
        }
        else if (action.equals("oneClickRelease")) {
            return aroundAction("OneClickRelease", point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("terminate")) {
            return aroundAction("Terminate", point);
        }
        else if (action.equals("unlinkBug")) {
            return aroundAction("UnlinkBug", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchReportRelease")) {
            return aroundDataSet("ReportRelease", point);
        }
        return point.proceed();
    }

}
