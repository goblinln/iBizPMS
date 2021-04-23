package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.ImConferenceaction;
import cn.ibizlab.pms.core.zentao.service.IImConferenceactionService;
import cn.ibizlab.pms.core.zentao.filter.ImConferenceactionSearchContext;
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
@Component("ImConferenceactionRuntime")
@Slf4j
public class ImConferenceactionRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IImConferenceactionService imconferenceactionService;

    @Autowired
    DELogicExecutor logicExecutor;

    @Override
    protected IEntityBase getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntityBase) {
            return (IEntityBase) o;
        } else {
            return imconferenceactionService.sysGet((Long) o);
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/ImConferenceaction.json";
    }

    @Override
    public String getName() {
        return "ZT_IM_CONFERENCEACTION";
    }

    @Override
    public IEntityBase createEntity() {
        return new ImConferenceaction();
    }

    @Override
    protected IService getService() {
        return this.imconferenceactionService;
    }

    @Override
    public ImConferenceactionSearchContext createSearchContext() {
        return new ImConferenceactionSearchContext();
    }

    @Override
    public boolean existsData(ISearchContextBase iSearchContextBase) {
        Page<ImConferenceaction> domains = imconferenceactionService.searchDefault((ImConferenceactionSearchContext) iSearchContextBase);
        if (domains.getSize() == 0)
            return false;
        return true;
    }

    @Override
    public Page<ImConferenceaction> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        ImConferenceactionSearchContext searchContext = (ImConferenceactionSearchContext) iSearchContextBase;
        if (iPSDEDataSet.getName().equals("DEFAULT"))
            return imconferenceactionService.searchDefault(searchContext);    
        return null;
    }

    @Override
    public Page<ImConferenceaction> searchDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        //暂未实现
        return null;
    }

    @Override
    public ImConferenceaction selectOne(ISearchContextBase iSearchContextBase) {
        //单条数据查询，多条数数据时 返回第一条
        ImConferenceactionSearchContext searchContext = (ImConferenceactionSearchContext) iSearchContextBase;
        searchContext.setSize(1);
        Page<ImConferenceaction> domains = imconferenceactionService.searchDefault(searchContext);
        if (domains.getTotalElements() == 0)
            return null;
        return domains.getContent().get(0);
    }

    @Override
    public List<ImConferenceaction> select(ISearchContextBase iSearchContextBase) {
        ImConferenceactionSearchContext searchContext = (ImConferenceactionSearchContext) iSearchContextBase;
        searchContext.setSize(Integer.MAX_VALUE);
        return imconferenceactionService.searchDefault(searchContext).getContent();
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return imconferenceactionService.create((ImConferenceaction) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return imconferenceactionService.update((ImConferenceaction) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return imconferenceactionService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                if(args[0] instanceof ImConferenceaction){
                    ImConferenceaction arg = (ImConferenceaction) args[0] ;
                    arg = imconferenceactionService.get(arg.getId()) ;
                    return arg;
                }else{
                    return imconferenceactionService.get((Long) args[0]);
                }
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return imconferenceactionService.getDraft((ImConferenceaction) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return imconferenceactionService.checkKey((ImConferenceaction) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return imconferenceactionService.save((ImConferenceaction) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return imconferenceactionService.create((ImConferenceaction) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return imconferenceactionService.update((ImConferenceaction) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                if(args[0] instanceof ImConferenceaction){
                    ImConferenceaction arg = (ImConferenceaction) args[0] ;
                    arg = imconferenceactionService.get(arg.getId()) ;
                    return arg;
                }else{
                    return imconferenceactionService.get((Long) args[0]);
                }
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return imconferenceactionService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return imconferenceactionService.sysGet((Long) args[0]);
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
        ImConferenceaction entity = null;
        String action = iPSDEAction.getName();
        if (arg0 instanceof ImConferenceaction) {
            entity = (ImConferenceaction) arg0;
        } else {
            if (arg0 != null && "get".equalsIgnoreCase(action)) {
                entity = imconferenceactionService.sysGet((Long) arg0);
            } else if ("remove".equalsIgnoreCase(action)) {
                entity = new ImConferenceaction();
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

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.ImConferenceactionServiceImpl.*(..))")
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
        return point.proceed();
    }

}
