package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Release;
import cn.ibizlab.pms.core.zentao.service.IReleaseService;
import cn.ibizlab.pms.core.zentao.filter.ReleaseSearchContext;
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
@Component("ReleaseRuntime")
@Slf4j
public class ReleaseRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IReleaseService releaseService;

    @Autowired
    DELogicExecutor logicExecutor;

    @Override
    protected IEntityBase getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntityBase) {
            return (IEntityBase) o;
        } else {
            return releaseService.sysGet((Long) o);
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
    public IEntityBase createEntity() {
        return new Release();
    }

    @Override
    protected IService getService() {
        return this.releaseService;
    }

    @Override
    public ReleaseSearchContext createSearchContext() {
        return new ReleaseSearchContext();
    }

    @Override
    public boolean existsData(ISearchContextBase iSearchContextBase) {
        Page<Release> domains = releaseService.searchDefault((ReleaseSearchContext) iSearchContextBase);
        if (domains.getSize() == 0)
            return false;
        return true;
    }

    @Override
    public Page<Release> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        ReleaseSearchContext searchContext = (ReleaseSearchContext) iSearchContextBase;
        if (iPSDEDataSet.getName().equals("DEFAULT"))
            return releaseService.searchDefault(searchContext);    
        if (iPSDEDataSet.getName().equals("ReportRelease"))
            return releaseService.searchReportRelease(searchContext);    
        return null;
    }

    @Override
    public Page<Release> searchDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        //暂未实现
        return null;
    }

    @Override
    public Release selectOne(ISearchContextBase iSearchContextBase) {
        //单条数据查询，多条数数据时 返回第一条
        ReleaseSearchContext searchContext = (ReleaseSearchContext) iSearchContextBase;
        searchContext.setSize(1);
        Page<Release> domains = releaseService.searchDefault(searchContext);
        if (domains.getTotalElements() == 0)
            return null;
        return domains.getContent().get(0);
    }

    @Override
    public List<Release> select(ISearchContextBase iSearchContextBase) {
        ReleaseSearchContext searchContext = (ReleaseSearchContext) iSearchContextBase;
        searchContext.setSize(Integer.MAX_VALUE);
        return releaseService.searchDefault(searchContext).getContent();
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
                if(args[0] instanceof Release){
                    Release arg = (Release) args[0] ;
                    arg = releaseService.get(arg.getId()) ;
                    return arg;
                }else{
                    return releaseService.get((Long) args[0]);
                }
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
                if(args[0] instanceof Release){
                    Release arg = (Release) args[0] ;
                    arg = releaseService.get(arg.getId()) ;
                    return arg;
                }else{
                    return releaseService.get((Long) args[0]);
                }
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return releaseService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return releaseService.sysGet((Long) args[0]);
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

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.ReleaseServiceImpl.*(..))")
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
