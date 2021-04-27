package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.ibiz.domain.DocLibModule;
import cn.ibizlab.pms.core.ibiz.service.IDocLibModuleService;
import cn.ibizlab.pms.core.ibiz.filter.DocLibModuleSearchContext;
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
@Component("DocLibModuleRuntime")
@Slf4j
public class DocLibModuleRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

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
    protected IService getService() {
        return this.doclibmoduleService;
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
            throw new RuntimeException("序列化错误");
        }
    }

    @Override
    public Object serializeEntities(IEntityBase[] list) {
        try {
            return MAPPER.writeValueAsString(list);
        } catch (Exception e) {
            throw new RuntimeException("序列化错误");
        }
    }

    @Override
    public IEntityBase deserializeEntity(Object objData) {
        try {
            return MAPPER.readValue(String.valueOf(objData),DocLibModule.class);
        } catch (Exception e) {
            throw new RuntimeException("反序列化错误");
        }
    }

    @Override
    public IEntityBase[] deserializeEntities(Object objData) {
        try {
            return MAPPER.readValue(String.valueOf(objData),DocLibModule[].class);
        } catch (Exception e) {
            throw new RuntimeException("反序列化错误");
        }
    }

    @Override
    public boolean existsData(ISearchContextBase iSearchContextBase) {
        Page<DocLibModule> domains = doclibmoduleService.searchDefault((DocLibModuleSearchContext) iSearchContextBase);
        if (domains.getSize() == 0)
            return false;
        return true;
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
    public DocLibModule selectOne(ISearchContextBase iSearchContextBase) {
        //单条数据查询，多条数数据时 返回第一条
        DocLibModuleSearchContext searchContext = (DocLibModuleSearchContext) iSearchContextBase;
        searchContext.setSize(1);
        List<DocLibModule> domains = doclibmoduleService.select(searchContext);
        if (domains.size() == 0)
            return null;
        return domains.get(0);
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
                    arg = doclibmoduleService.get(arg.getId()) ;
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
            if (strActionName.equals(DEActions.CREATE)) {
                return doclibmoduleService.create((DocLibModule) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return doclibmoduleService.update((DocLibModule) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                if(args[0] instanceof DocLibModule){
                    DocLibModule arg = (DocLibModule) args[0] ;
                    arg = doclibmoduleService.get(arg.getId()) ;
                    return arg;
                }else{
                    return doclibmoduleService.get((Long) args[0]);
                }
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return doclibmoduleService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return doclibmoduleService.sysGet((Long) args[0]);
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

    @Around("execution(* cn.ibizlab.pms.core.ibiz.service.impl.DocLibModuleServiceImpl.*(..))")
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
        else if (action.equals("collect")) {
            return aroundAction("Collect", point);
        }
        else if (action.equals("docLibModuleNFavorite")) {
            return aroundAction("DocLibModuleNFavorite", point);
        }
        else if (action.equals("doclibModuleFavorite")) {
            return aroundAction("DoclibModuleFavorite", point);
        }
        else if (action.equals("fix")) {
            return aroundAction("Fix", point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("unCollect")) {
            return aroundAction("UnCollect", point);
        }
        else if (action.equals("searchAllDocLibModule_Custom")) {
            return aroundDataSet("AllDocLibModule_Custom", point);
        }
        else if (action.equals("searchAllDoclibModule")) {
            return aroundDataSet("AllDoclibModule", point);
        }
        else if (action.equals("searchChildModuleByParent")) {
            return aroundDataSet("ChildModuleByParent", point);
        }
        else if (action.equals("searchChildModuleByRealParent")) {
            return aroundDataSet("ChildModuleByRealParent", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchMyFavourites")) {
            return aroundDataSet("MyFavourites", point);
        }
        else if (action.equals("searchParentModule")) {
            return aroundDataSet("ParentModule", point);
        }
        else if (action.equals("searchRootModuleMuLu")) {
            return aroundDataSet("RootModuleMuLu", point);
        }
        else if (action.equals("searchRootModuleMuLuByRoot")) {
            return aroundDataSet("RootModuleMuLuByRoot", point);
        }
        else if (action.equals("searchRootModuleMuLuBysrfparentkey")) {
            return aroundDataSet("RootModuleMuLuBysrfparentkey", point);
        }
        return point.proceed();
    }

}
