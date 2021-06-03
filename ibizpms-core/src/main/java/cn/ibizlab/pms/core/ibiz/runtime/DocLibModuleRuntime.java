package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.ibiz.domain.DocLibModule;
import cn.ibizlab.pms.core.ibiz.service.IDocLibModuleService;
import cn.ibizlab.pms.core.ibiz.filter.DocLibModuleSearchContext;
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

@Component
@Slf4j
public class DocLibModuleRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime implements IDocLibModuleRuntime {

    @Autowired
    IDocLibModuleService doclibmoduleService;

    @Autowired
    DELogicExecutor logicExecutor;

    @Override
    protected IEntityBase getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntityBase) {
            return (IEntityBase) o;
        } else {
            return doclibmoduleService.sysGet((Long) o);
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/ibiz/PSDATAENTITIES/DocLibModule.json";
    }

    @Override
    public String getName() {
        return "IBZ_DOCLIBMODULE";
    }

    @Override
    public IEntityBase createEntity() {
        return new DocLibModule();
    }

    @Override
    public DocLibModuleSearchContext createSearchContext() {
        return new DocLibModuleSearchContext();
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
            return MAPPER.readValue(String.valueOf(objData),DocLibModule.class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public IEntityBase[] deserializeEntities(Object objData) {
        try {
            return MAPPER.readValue(String.valueOf(objData),DocLibModule[].class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public Page<DocLibModule> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        DocLibModuleSearchContext searchContext = (DocLibModuleSearchContext) iSearchContextBase;
        if (iPSDEDataSet.getName().equals("AllDocLibModule_Custom"))
            return doclibmoduleService.searchAllDocLibModule_Custom(searchContext);    
        if (iPSDEDataSet.getName().equals("AllDoclibModule"))
            return doclibmoduleService.searchAllDoclibModule(searchContext);    
        if (iPSDEDataSet.getName().equals("ChildModuleByParent"))
            return doclibmoduleService.searchChildModuleByParent(searchContext);    
        if (iPSDEDataSet.getName().equals("ChildModuleByRealParent"))
            return doclibmoduleService.searchChildModuleByRealParent(searchContext);    
        if (iPSDEDataSet.getName().equals("DEFAULT"))
            return doclibmoduleService.searchDefault(searchContext);    
        if (iPSDEDataSet.getName().equals("MyFavourites"))
            return doclibmoduleService.searchMyFavourites(searchContext);    
        if (iPSDEDataSet.getName().equals("ParentModule"))
            return doclibmoduleService.searchParentModule(searchContext);    
        if (iPSDEDataSet.getName().equals("RootModuleMuLu"))
            return doclibmoduleService.searchRootModuleMuLu(searchContext);    
        if (iPSDEDataSet.getName().equals("RootModuleMuLuByRoot"))
            return doclibmoduleService.searchRootModuleMuLuByRoot(searchContext);    
        if (iPSDEDataSet.getName().equals("RootModuleMuLuBysrfparentkey"))
            return doclibmoduleService.searchRootModuleMuLuBysrfparentkey(searchContext);    
        return null;
    }

    @Override
    public List<DocLibModule> selectDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        DocLibModuleSearchContext searchContext = (DocLibModuleSearchContext) iSearchContextBase;
        if (iPSDataQuery.getName().equals("AllDoclibModule_Custom"))
            return doclibmoduleService.selectAllDoclibModule_Custom(searchContext);
        if (iPSDataQuery.getName().equals("ChildModuleByParent"))
            return doclibmoduleService.selectChildModuleByParent(searchContext);
        if (iPSDataQuery.getName().equals("ChildModuleByRealParent"))
            return doclibmoduleService.selectChildModuleByRealParent(searchContext);
        if (iPSDataQuery.getName().equals("DEFAULT"))
            return doclibmoduleService.selectDefault(searchContext);
        if (iPSDataQuery.getName().equals("AllDoclibModule"))
            return doclibmoduleService.selectDefaultDoclib(searchContext);
        if (iPSDataQuery.getName().equals("MyFavourites"))
            return doclibmoduleService.selectMyFavourites(searchContext);
        if (iPSDataQuery.getName().equals("ParentModule"))
            return doclibmoduleService.selectParentModule(searchContext);
        if (iPSDataQuery.getName().equals("RootModuleMuLu"))
            return doclibmoduleService.selectRootModuleMuLu(searchContext);
        if (iPSDataQuery.getName().equals("RootModuleMuLuByRoot"))
            return doclibmoduleService.selectRootModuleMuLuByRoot(searchContext);
        if (iPSDataQuery.getName().equals("RootModuleMuLuBysrfparentkey"))
            return doclibmoduleService.selectRootModuleMuLuBysrfparentkey(searchContext);
        if (iPSDataQuery.getName().equals("VIEW"))
            return doclibmoduleService.selectView(searchContext);
        return null;
    }

    @Override
    public List<DocLibModule> select(ISearchContextBase iSearchContextBase) {
        DocLibModuleSearchContext searchContext = (DocLibModuleSearchContext) iSearchContextBase;
        return doclibmoduleService.select(searchContext);
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return doclibmoduleService.create((DocLibModule) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return doclibmoduleService.update((DocLibModule) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return doclibmoduleService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                if(args[0] instanceof DocLibModule){
                    DocLibModule arg = (DocLibModule) args[0] ;
                    CachedBeanCopier.copy(doclibmoduleService.get(arg.getId()), arg);
                    return arg;
                }else{
                    return doclibmoduleService.get((Long) args[0]);
                }
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return doclibmoduleService.getDraft((DocLibModule) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return doclibmoduleService.checkKey((DocLibModule) args[0]);
            }
            else if (iPSDEAction.getName().equals("Collect")) {
                return doclibmoduleService.collect((DocLibModule) args[0]);
            }
            else if (iPSDEAction.getName().equals("DocLibModuleNFavorite")) {
                return doclibmoduleService.docLibModuleNFavorite((DocLibModule) args[0]);
            }
            else if (iPSDEAction.getName().equals("DoclibModuleFavorite")) {
                return doclibmoduleService.doclibModuleFavorite((DocLibModule) args[0]);
            }
            else if (iPSDEAction.getName().equals("Fix")) {
                return doclibmoduleService.fix((DocLibModule) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return doclibmoduleService.save((DocLibModule) args[0]);
            }
            else if (iPSDEAction.getName().equals("UnCollect")) {
                return doclibmoduleService.unCollect((DocLibModule) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equalsIgnoreCase("Create")) {
                return doclibmoduleService.create((DocLibModule) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Update")) {
                return doclibmoduleService.update((DocLibModule) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Remove")) {
                return doclibmoduleService.remove((Long) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Get")) {
                if(args[0] instanceof DocLibModule){
                    DocLibModule arg = (DocLibModule) args[0] ;
                    CachedBeanCopier.copy(doclibmoduleService.get(arg.getId()), arg);
                    return arg;
                }else{
                    return doclibmoduleService.get((Long) args[0]);
                }
            }
            else if (strActionName.equalsIgnoreCase("GetDraft")) {
                return doclibmoduleService.getDraft((DocLibModule) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("CheckKey")) {
                return doclibmoduleService.checkKey((DocLibModule) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Collect")) {
                return doclibmoduleService.collect((DocLibModule) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("DocLibModuleNFavorite")) {
                return doclibmoduleService.docLibModuleNFavorite((DocLibModule) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("DoclibModuleFavorite")) {
                return doclibmoduleService.doclibModuleFavorite((DocLibModule) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Fix")) {
                return doclibmoduleService.fix((DocLibModule) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Save")) {
                return doclibmoduleService.save((DocLibModule) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("UnCollect")) {
                return doclibmoduleService.unCollect((DocLibModule) args[0]);
            }
            else  if (strActionName.equalsIgnoreCase(DEActions.SYSGET)) {
                if(args[0] instanceof DocLibModule){
                    DocLibModule arg = (DocLibModule) args[0] ;
                    CachedBeanCopier.copy(doclibmoduleService.sysGet(arg.getId()), arg);
                    return arg;
                }else{
                    return doclibmoduleService.sysGet((Long) args[0]);
                }
            }
            else  if (strActionName.equalsIgnoreCase(DEActions.SYSUPDATE)) {
                    return doclibmoduleService.sysUpdate((DocLibModule) args[0]);
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
        DocLibModule entity = null;
        String action = iPSDEAction.getName();
        if (arg0 instanceof DocLibModule) {
            entity = (DocLibModule) arg0;
        } else {
            if (arg0 != null && "get".equalsIgnoreCase(action)) {
                entity = doclibmoduleService.sysGet((Long) arg0);
            } else if ("remove".equalsIgnoreCase(action)) {
                entity = new DocLibModule();
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

    @Override
    protected void onWFRegister(Object arg0, IPSDEAction iPSDEAction, IPSDEWF iPSDEWF, IDynaInstRuntime iDynaInstRuntime, Object actionData) throws Throwable {
    }

    @Override
    protected void onWFUnregister(Object arg0, IPSDEAction iPSDEAction, IPSDEWF iPSDEWF, IDynaInstRuntime iDynaInstRuntime, Object actionData) throws Throwable {
    }
}
