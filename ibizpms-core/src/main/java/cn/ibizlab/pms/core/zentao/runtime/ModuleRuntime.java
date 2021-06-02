package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Module;
import cn.ibizlab.pms.core.zentao.service.IModuleService;
import cn.ibizlab.pms.core.zentao.filter.ModuleSearchContext;
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


@Aspect
@org.springframework.core.annotation.Order(100)
@Component("ModuleRuntime")
@Slf4j
public class ModuleRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IModuleService moduleService;

    @Autowired
    DELogicExecutor logicExecutor;

    @Override
    protected IEntityBase getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntityBase) {
            return (IEntityBase) o;
        } else {
            return moduleService.sysGet((Long) o);
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/Module.json";
    }

    @Override
    public String getName() {
        return "ZT_MODULE";
    }

    @Override
    public IEntityBase createEntity() {
        return new Module();
    }

    @Override
    public ModuleSearchContext createSearchContext() {
        return new ModuleSearchContext();
    }

    @Override
    public Object serializeEntity(IEntityBase iEntityBase) {
        try {
            return MAPPER.writeValueAsString(iEntityBase);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]序列化数据异常。%2$s", this.getName(), e.getMessage()), this, e);
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
            return MAPPER.readValue(String.valueOf(objData),Module.class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public IEntityBase[] deserializeEntities(Object objData) {
        try {
            return MAPPER.readValue(String.valueOf(objData),Module[].class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public Page<Module> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        ModuleSearchContext searchContext = (ModuleSearchContext) iSearchContextBase;
        if (iPSDEDataSet.getName().equals("BugModule"))
            return moduleService.searchBugModule(searchContext);    
        if (iPSDEDataSet.getName().equals("BugModuleCodeList"))
            return moduleService.searchBugModuleCodeList(searchContext);    
        if (iPSDEDataSet.getName().equals("DEFAULT"))
            return moduleService.searchDefault(searchContext);    
        if (iPSDEDataSet.getName().equals("DocModule"))
            return moduleService.searchDocModule(searchContext);    
        if (iPSDEDataSet.getName().equals("Line"))
            return moduleService.searchLine(searchContext);    
        if (iPSDEDataSet.getName().equals("StoryModule"))
            return moduleService.searchStoryModule(searchContext);    
        if (iPSDEDataSet.getName().equals("TaskModule"))
            return moduleService.searchTaskModule(searchContext);    
        return null;
    }

    @Override
    public List<Module> selectDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        ModuleSearchContext searchContext = (ModuleSearchContext) iSearchContextBase;
        if (iPSDataQuery.getName().equals("BugModule"))
            return moduleService.selectBugModule(searchContext);
        if (iPSDataQuery.getName().equals("BugModuleCodeList"))
            return moduleService.selectBugModuleCodeList(searchContext);
        if (iPSDataQuery.getName().equals("DEFAULT"))
            return moduleService.selectDefault(searchContext);
        if (iPSDataQuery.getName().equals("DocModule"))
            return moduleService.selectDocModule(searchContext);
        if (iPSDataQuery.getName().equals("Line"))
            return moduleService.selectLine(searchContext);
        if (iPSDataQuery.getName().equals("StoryModule"))
            return moduleService.selectStoryModule(searchContext);
        if (iPSDataQuery.getName().equals("TaskModule"))
            return moduleService.selectTaskModule(searchContext);
        if (iPSDataQuery.getName().equals("VIEW"))
            return moduleService.selectView(searchContext);
        return null;
    }

    @Override
    public List<Module> select(ISearchContextBase iSearchContextBase) {
        ModuleSearchContext searchContext = (ModuleSearchContext) iSearchContextBase;
        return moduleService.select(searchContext);
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return moduleService.create((Module) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return moduleService.update((Module) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return moduleService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                if(args[0] instanceof Module){
                    Module arg = (Module) args[0] ;
                    CachedBeanCopier.copy(moduleService.get(arg.getId()), arg);
                    return arg;
                }else{
                    return moduleService.get((Long) args[0]);
                }
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return moduleService.getDraft((Module) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return moduleService.checkKey((Module) args[0]);
            }
            else if (iPSDEAction.getName().equals("Fix")) {
                return moduleService.fix((Module) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return moduleService.save((Module) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equalsIgnoreCase("Create")) {
                return moduleService.create((Module) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Update")) {
                return moduleService.update((Module) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Remove")) {
                return moduleService.remove((Long) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Get")) {
                if(args[0] instanceof Module){
                    Module arg = (Module) args[0] ;
                    CachedBeanCopier.copy(moduleService.get(arg.getId()), arg);
                    return arg;
                }else{
                    return moduleService.get((Long) args[0]);
                }
            }
            else if (strActionName.equalsIgnoreCase("GetDraft")) {
                return moduleService.getDraft((Module) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("CheckKey")) {
                return moduleService.checkKey((Module) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Fix")) {
                return moduleService.fix((Module) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Save")) {
                return moduleService.save((Module) args[0]);
            }
            else  if (strActionName.equalsIgnoreCase(DEActions.SYSGET)) {
                if(args[0] instanceof Module){
                    Module arg = (Module) args[0] ;
                    CachedBeanCopier.copy(moduleService.sysGet(arg.getId()), arg);
                    return arg;
                }else{
                    return moduleService.sysGet((Long) args[0]);
                }
            }
            else  if (strActionName.equalsIgnoreCase(DEActions.SYSUPDATE)) {
                    return moduleService.sysUpdate((Module) args[0]);
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
        Module entity = null;
        String action = iPSDEAction.getName();
        if (arg0 instanceof Module) {
            entity = (Module) arg0;
        } else {
            if (arg0 != null && "get".equalsIgnoreCase(action)) {
                entity = moduleService.sysGet((Long) arg0);
            } else if ("remove".equalsIgnoreCase(action)) {
                entity = new Module();
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

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.ModuleServiceImpl.*(..))")
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
        else if (action.equals("fix")) {
            return aroundAction("Fix", point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("searchBugModule")) {
            return aroundDataSet("BugModule", point);
        }
        else if (action.equals("searchBugModuleCodeList")) {
            return aroundDataSet("BugModuleCodeList", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchDocModule")) {
            return aroundDataSet("DocModule", point);
        }
        else if (action.equals("searchLine")) {
            return aroundDataSet("Line", point);
        }
        else if (action.equals("searchStoryModule")) {
            return aroundDataSet("StoryModule", point);
        }
        else if (action.equals("searchTaskModule")) {
            return aroundDataSet("TaskModule", point);
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
