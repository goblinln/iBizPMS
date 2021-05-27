package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.CaseStep;
import cn.ibizlab.pms.core.zentao.service.ICaseStepService;
import cn.ibizlab.pms.core.zentao.filter.CaseStepSearchContext;
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
@Component("CaseStepRuntime")
@Slf4j
public class CaseStepRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    ICaseStepService casestepService;

    @Autowired
    DELogicExecutor logicExecutor;

    @Override
    protected IEntityBase getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntityBase) {
            return (IEntityBase) o;
        } else {
            return casestepService.sysGet((Long) o);
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/CaseStep.json";
    }

    @Override
    public String getName() {
        return "ZT_CASESTEP";
    }

    @Override
    public IEntityBase createEntity() {
        return new CaseStep();
    }

    @Override
    protected IService getService() {
        return this.casestepService;
    }

    @Override
    public CaseStepSearchContext createSearchContext() {
        return new CaseStepSearchContext();
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
            return MAPPER.readValue(String.valueOf(objData),CaseStep.class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public IEntityBase[] deserializeEntities(Object objData) {
        try {
            return MAPPER.readValue(String.valueOf(objData),CaseStep[].class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public Page<CaseStep> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        CaseStepSearchContext searchContext = (CaseStepSearchContext) iSearchContextBase;
        if (iPSDEDataSet.getName().equals("CurTest"))
            return casestepService.searchCurTest(searchContext);    
        if (iPSDEDataSet.getName().equals("DEFAULT"))
            return casestepService.searchDefault(searchContext);    
        if (iPSDEDataSet.getName().equals("DEFAULT1"))
            return casestepService.searchDefault1(searchContext);    
        if (iPSDEDataSet.getName().equals("Mob"))
            return casestepService.searchMob(searchContext);    
        if (iPSDEDataSet.getName().equals("Version"))
            return casestepService.searchVersion(searchContext);    
        if (iPSDEDataSet.getName().equals("Versions"))
            return casestepService.searchVersions(searchContext);    
        return null;
    }

    @Override
    public List<CaseStep> selectDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        CaseStepSearchContext searchContext = (CaseStepSearchContext) iSearchContextBase;
        if (iPSDataQuery.getName().equals("CurTest"))
            return casestepService.selectCurTest(searchContext);
        if (iPSDataQuery.getName().equals("DEFAULT"))
            return casestepService.selectDefault(searchContext);
        if (iPSDataQuery.getName().equals("DEFAULT1"))
            return casestepService.selectDefault1(searchContext);
        if (iPSDataQuery.getName().equals("Mob"))
            return casestepService.selectMob(searchContext);
        if (iPSDataQuery.getName().equals("Version"))
            return casestepService.selectVersion(searchContext);
        if (iPSDataQuery.getName().equals("Versions"))
            return casestepService.selectVersions(searchContext);
        if (iPSDataQuery.getName().equals("VIEW"))
            return casestepService.selectView(searchContext);
        return null;
    }

    @Override
    public List<CaseStep> select(ISearchContextBase iSearchContextBase) {
        CaseStepSearchContext searchContext = (CaseStepSearchContext) iSearchContextBase;
        return casestepService.select(searchContext);
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return casestepService.create((CaseStep) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return casestepService.update((CaseStep) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return casestepService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                if(args[0] instanceof CaseStep){
                    CaseStep arg = (CaseStep) args[0] ;
                    CachedBeanCopier.copy(casestepService.get(arg.getId()), arg);
                    return arg;
                }else{
                    return casestepService.get((Long) args[0]);
                }
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return casestepService.getDraft((CaseStep) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return casestepService.checkKey((CaseStep) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return casestepService.save((CaseStep) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equalsIgnoreCase("Create")) {
                return casestepService.create((CaseStep) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Update")) {
                return casestepService.update((CaseStep) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Remove")) {
                return casestepService.remove((Long) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Get")) {
                if(args[0] instanceof CaseStep){
                    CaseStep arg = (CaseStep) args[0] ;
                    CachedBeanCopier.copy(casestepService.get(arg.getId()), arg);
                    return arg;
                }else{
                    return casestepService.get((Long) args[0]);
                }
            }
            else if (strActionName.equalsIgnoreCase("GetDraft")) {
                return casestepService.getDraft((CaseStep) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("CheckKey")) {
                return casestepService.checkKey((CaseStep) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Save")) {
                return casestepService.save((CaseStep) args[0]);
            }
            else  if (strActionName.equalsIgnoreCase(DEActions.SYSGET)) {
                if(args[0] instanceof CaseStep){
                    CaseStep arg = (CaseStep) args[0] ;
                    CachedBeanCopier.copy(casestepService.sysGet(arg.getId()), arg);
                    return arg;
                }else{
                    return casestepService.sysGet((Long) args[0]);
                }
            }
            else  if (strActionName.equalsIgnoreCase(DEActions.SYSUPDATE)) {
                    return casestepService.sysUpdate((CaseStep) args[0]);
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
        CaseStep entity = null;
        String action = iPSDEAction.getName();
        if (arg0 instanceof CaseStep) {
            entity = (CaseStep) arg0;
        } else {
            if (arg0 != null && "get".equalsIgnoreCase(action)) {
                entity = casestepService.sysGet((Long) arg0);
            } else if ("remove".equalsIgnoreCase(action)) {
                entity = new CaseStep();
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

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.CaseStepServiceImpl.*(..))")
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
        else if (action.equals("searchCurTest")) {
            return aroundDataSet("CurTest", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchDefault1")) {
            return aroundDataSet("DEFAULT1", point);
        }
        else if (action.equals("searchMob")) {
            return aroundDataSet("Mob", point);
        }
        else if (action.equals("searchVersion")) {
            return aroundDataSet("Version", point);
        }
        else if (action.equals("searchVersions")) {
            return aroundDataSet("Versions", point);
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
