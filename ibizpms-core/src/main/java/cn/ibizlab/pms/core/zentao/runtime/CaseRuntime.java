package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Case;
import cn.ibizlab.pms.core.zentao.service.ICaseService;
import cn.ibizlab.pms.core.zentao.filter.CaseSearchContext;
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
import net.ibizsys.runtime.dataentity.DataEntityRuntimeException;
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
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.alibaba.fastjson.JSONObject;


@Aspect
@Order(100)
@Component("CaseRuntime")
@Slf4j
public class CaseRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    ICaseService caseService;

    @Autowired
    DELogicExecutor logicExecutor;

    @Override
    protected IEntityBase getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntityBase) {
            return (IEntityBase) o;
        } else {
            return caseService.sysGet((Long) o);
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/Case.json";
    }

    @Override
    public String getName() {
        return "ZT_CASE";
    }

    @Override
    public IEntityBase createEntity() {
        return new Case();
    }

    @Override
    protected IService getService() {
        return this.caseService;
    }

    @Override
    public CaseSearchContext createSearchContext() {
        return new CaseSearchContext();
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
            return MAPPER.readValue(String.valueOf(objData),Case.class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public IEntityBase[] deserializeEntities(Object objData) {
        try {
            return MAPPER.readValue(String.valueOf(objData),Case[].class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public boolean existsData(ISearchContextBase iSearchContextBase) {
        Page<Case> domains = caseService.searchDefault((CaseSearchContext) iSearchContextBase);
        if (domains.getSize() == 0)
            return false;
        return true;
    }

    @Override
    public Page<Case> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        CaseSearchContext searchContext = (CaseSearchContext) iSearchContextBase;
        if (iPSDEDataSet.getName().equals("BatchNew"))
            return caseService.searchBatchNew(searchContext);    
        if (iPSDEDataSet.getName().equals("CurOpenedCase"))
            return caseService.searchCurOpenedCase(searchContext);    
        if (iPSDEDataSet.getName().equals("CurSuite"))
            return caseService.searchCurSuite(searchContext);    
        if (iPSDEDataSet.getName().equals("CurTestTask"))
            return caseService.searchCurTestTask(searchContext);    
        if (iPSDEDataSet.getName().equals("DEFAULT"))
            return caseService.searchDefault(searchContext);    
        if (iPSDEDataSet.getName().equals("ESBulk"))
            return caseService.searchESBulk(searchContext);    
        if (iPSDEDataSet.getName().equals("ModuleRePortCase"))
            return caseService.searchModuleRePortCase(searchContext);    
        if (iPSDEDataSet.getName().equals("ModuleRePortCaseEntry"))
            return caseService.searchModuleRePortCaseEntry(searchContext);    
        if (iPSDEDataSet.getName().equals("ModuleRePortCase_Project"))
            return caseService.searchModuleRePortCase_Project(searchContext);    
        if (iPSDEDataSet.getName().equals("MyFavorite"))
            return caseService.searchMyFavorites(searchContext);    
        if (iPSDEDataSet.getName().equals("NotCurTestSuite"))
            return caseService.searchNotCurTestSuite(searchContext);    
        if (iPSDEDataSet.getName().equals("NotCurTestTask"))
            return caseService.searchNotCurTestTask(searchContext);    
        if (iPSDEDataSet.getName().equals("NotCurTestTaskProject"))
            return caseService.searchNotCurTestTaskProject(searchContext);    
        if (iPSDEDataSet.getName().equals("RePortCase"))
            return caseService.searchRePortCase(searchContext);    
        if (iPSDEDataSet.getName().equals("RePortCaseEntry"))
            return caseService.searchRePortCaseEntry(searchContext);    
        if (iPSDEDataSet.getName().equals("RePortCase_Project"))
            return caseService.searchRePortCase_Project(searchContext);    
        if (iPSDEDataSet.getName().equals("RunERRePortCase"))
            return caseService.searchRunERRePortCase(searchContext);    
        if (iPSDEDataSet.getName().equals("RunERRePortCaseEntry"))
            return caseService.searchRunERRePortCaseEntry(searchContext);    
        if (iPSDEDataSet.getName().equals("RunERRePortCase_Project"))
            return caseService.searchRunERRePortCase_Project(searchContext);    
        if (iPSDEDataSet.getName().equals("RunRePortCase"))
            return caseService.searchRunRePortCase(searchContext);    
        if (iPSDEDataSet.getName().equals("RunRePortCaseEntry"))
            return caseService.searchRunRePortCaseEntry(searchContext);    
        if (iPSDEDataSet.getName().equals("RunRePortCase_Project"))
            return caseService.searchRunRePortCase_Project(searchContext);    
        return null;
    }

    @Override
    public List<Case> selectDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        CaseSearchContext searchContext = (CaseSearchContext) iSearchContextBase;
        if (iPSDataQuery.getName().equals("BatchNew"))
            return caseService.selectBatchNew(searchContext);
        if (iPSDataQuery.getName().equals("CurOpenedCase"))
            return caseService.selectCurOpenedCase(searchContext);
        if (iPSDataQuery.getName().equals("CurSuite"))
            return caseService.selectCurSuite(searchContext);
        if (iPSDataQuery.getName().equals("CurTestTask"))
            return caseService.selectCurTestTask(searchContext);
        if (iPSDataQuery.getName().equals("DEFAULT"))
            return caseService.selectDefault(searchContext);
        if (iPSDataQuery.getName().equals("ESBulk"))
            return caseService.selectESBulk(searchContext);
        if (iPSDataQuery.getName().equals("ModuleRePortCase"))
            return caseService.selectModuleRePortCase(searchContext);
        if (iPSDataQuery.getName().equals("ModuleRePortCaseEntry"))
            return caseService.selectModuleRePortCaseEntry(searchContext);
        if (iPSDataQuery.getName().equals("ModuleRePortCase_Project"))
            return caseService.selectModuleRePortCase_Project(searchContext);
        if (iPSDataQuery.getName().equals("MyFavorite"))
            return caseService.selectMyFavorite(searchContext);
        if (iPSDataQuery.getName().equals("NotCurTestSuite"))
            return caseService.selectNotCurTestSuite(searchContext);
        if (iPSDataQuery.getName().equals("NotCurTestTask"))
            return caseService.selectNotCurTestTask(searchContext);
        if (iPSDataQuery.getName().equals("NotCurTestTaskProject"))
            return caseService.selectNotCurTestTaskProject(searchContext);
        if (iPSDataQuery.getName().equals("RePortCase"))
            return caseService.selectRePortCase(searchContext);
        if (iPSDataQuery.getName().equals("RePortCaseEntry"))
            return caseService.selectRePortCaseEntry(searchContext);
        if (iPSDataQuery.getName().equals("RePortCase_Project"))
            return caseService.selectRePortCase_Project(searchContext);
        if (iPSDataQuery.getName().equals("RunERRePortCase"))
            return caseService.selectRunERRePortCase(searchContext);
        if (iPSDataQuery.getName().equals("RunERRePortCaseEntry"))
            return caseService.selectRunERRePortCaseEntry(searchContext);
        if (iPSDataQuery.getName().equals("RunERRePortCase_Project"))
            return caseService.selectRunERRePortCase_Project(searchContext);
        if (iPSDataQuery.getName().equals("RunRePortCase"))
            return caseService.selectRunRePortCase(searchContext);
        if (iPSDataQuery.getName().equals("RunRePortCaseEntry"))
            return caseService.selectRunRePortCaseEntry(searchContext);
        if (iPSDataQuery.getName().equals("RunRePortCase_Project"))
            return caseService.selectRunRePortCase_Project(searchContext);
        if (iPSDataQuery.getName().equals("VIEW"))
            return caseService.selectView(searchContext);
        return null;
    }

    @Override
    public Case selectOne(ISearchContextBase iSearchContextBase) {
        //单条数据查询，多条数数据时 返回第一条
        CaseSearchContext searchContext = (CaseSearchContext) iSearchContextBase;
        searchContext.setSize(1);
        List<Case> domains = caseService.select(searchContext);
        if (domains.size() == 0)
            return null;
        return domains.get(0);
    }

    @Override
    public List<Case> select(ISearchContextBase iSearchContextBase) {
        CaseSearchContext searchContext = (CaseSearchContext) iSearchContextBase;
        return caseService.select(searchContext);
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return caseService.create((Case) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return caseService.update((Case) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return caseService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                if(args[0] instanceof Case){
                    Case arg = (Case) args[0] ;
                    arg = caseService.get(arg.getId()) ;
                    return arg;
                }else{
                    return caseService.get((Long) args[0]);
                }
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return caseService.getDraft((Case) args[0]);
            }
            else if (iPSDEAction.getName().equals("CaseFavorite")) {
                return caseService.caseFavorite((Case) args[0]);
            }
            else if (iPSDEAction.getName().equals("CaseNFavorite")) {
                return caseService.caseNFavorite((Case) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return caseService.checkKey((Case) args[0]);
            }
            else if (iPSDEAction.getName().equals("confirmChange")) {
                return caseService.confirmChange((Case) args[0]);
            }
            else if (iPSDEAction.getName().equals("confirmstorychange")) {
                return caseService.confirmstorychange((Case) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetByTestTask")) {
                return caseService.getByTestTask((Case) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetTestTaskCntRun")) {
                return caseService.getTestTaskCntRun((Case) args[0]);
            }
            else if (iPSDEAction.getName().equals("linkCase")) {
                return caseService.linkCase((Case) args[0]);
            }
            else if (iPSDEAction.getName().equals("MobLinkCase")) {
                return caseService.mobLinkCase((Case) args[0]);
            }
            else if (iPSDEAction.getName().equals("RunCase")) {
                return caseService.runCase((Case) args[0]);
            }
            else if (iPSDEAction.getName().equals("runCases")) {
                return caseService.runCases((Case) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return caseService.save((Case) args[0]);
            }
            else if (iPSDEAction.getName().equals("TestRunCase")) {
                return caseService.testRunCase((Case) args[0]);
            }
            else if (iPSDEAction.getName().equals("testRunCases")) {
                return caseService.testRunCases((Case) args[0]);
            }
            else if (iPSDEAction.getName().equals("testsuitelinkCase")) {
                return caseService.testsuitelinkCase((Case) args[0]);
            }
            else if (iPSDEAction.getName().equals("unlinkCase")) {
                return caseService.unlinkCase((Case) args[0]);
            }
            else if (iPSDEAction.getName().equals("unlinkCases")) {
                return caseService.unlinkCases((Case) args[0]);
            }
            else if (iPSDEAction.getName().equals("unlinkSuiteCase")) {
                return caseService.unlinkSuiteCase((Case) args[0]);
            }
            else if (iPSDEAction.getName().equals("unlinkSuiteCases")) {
                return caseService.unlinkSuiteCases((Case) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return caseService.create((Case) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return caseService.update((Case) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                if(args[0] instanceof Case){
                    Case arg = (Case) args[0] ;
                    arg = caseService.get(arg.getId()) ;
                    return arg;
                }else{
                    return caseService.get((Long) args[0]);
                }
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return caseService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return caseService.sysGet((Long) args[0]);
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
        Case entity = null;
        String action = iPSDEAction.getName();
        if (arg0 instanceof Case) {
            entity = (Case) arg0;
        } else {
            if (arg0 != null && "get".equalsIgnoreCase(action)) {
                entity = caseService.sysGet((Long) arg0);
            } else if ("remove".equalsIgnoreCase(action)) {
                entity = new Case();
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

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.CaseServiceImpl.*(..))")
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
        else if (action.equals("caseFavorite")) {
            return aroundAction("CaseFavorite", point);
        }
        else if (action.equals("caseNFavorite")) {
            return aroundAction("CaseNFavorite", point);
        }
        else if (action.equals("checkKey")) {
            return aroundAction("CheckKey", point);
        }
        else if (action.equals("confirmChange")) {
            return aroundAction("confirmChange", point);
        }
        else if (action.equals("confirmstorychange")) {
            return aroundAction("confirmstorychange", point);
        }
        else if (action.equals("getByTestTask")) {
            return aroundAction("GetByTestTask", point);
        }
        else if (action.equals("getTestTaskCntRun")) {
            return aroundAction("GetTestTaskCntRun", point);
        }
        else if (action.equals("linkCase")) {
            return aroundAction("linkCase", point);
        }
        else if (action.equals("mobLinkCase")) {
            return aroundAction("MobLinkCase", point);
        }
        else if (action.equals("runCase")) {
            return aroundAction("RunCase", point);
        }
        else if (action.equals("runCases")) {
            return aroundAction("runCases", point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("testRunCase")) {
            return aroundAction("TestRunCase", point);
        }
        else if (action.equals("testRunCases")) {
            return aroundAction("testRunCases", point);
        }
        else if (action.equals("testsuitelinkCase")) {
            return aroundAction("testsuitelinkCase", point);
        }
        else if (action.equals("unlinkCase")) {
            return aroundAction("unlinkCase", point);
        }
        else if (action.equals("unlinkCases")) {
            return aroundAction("unlinkCases", point);
        }
        else if (action.equals("unlinkSuiteCase")) {
            return aroundAction("unlinkSuiteCase", point);
        }
        else if (action.equals("unlinkSuiteCases")) {
            return aroundAction("unlinkSuiteCases", point);
        }
        else if (action.equals("searchBatchNew")) {
            return aroundDataSet("BatchNew", point);
        }
        else if (action.equals("searchCurOpenedCase")) {
            return aroundDataSet("CurOpenedCase", point);
        }
        else if (action.equals("searchCurSuite")) {
            return aroundDataSet("CurSuite", point);
        }
        else if (action.equals("searchCurTestTask")) {
            return aroundDataSet("CurTestTask", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchESBulk")) {
            return aroundDataSet("ESBulk", point);
        }
        else if (action.equals("searchModuleRePortCase")) {
            return aroundDataSet("ModuleRePortCase", point);
        }
        else if (action.equals("searchModuleRePortCaseEntry")) {
            return aroundDataSet("ModuleRePortCaseEntry", point);
        }
        else if (action.equals("searchModuleRePortCase_Project")) {
            return aroundDataSet("ModuleRePortCase_Project", point);
        }
        else if (action.equals("searchMyFavorites")) {
            return aroundDataSet("MyFavorite", point);
        }
        else if (action.equals("searchNotCurTestSuite")) {
            return aroundDataSet("NotCurTestSuite", point);
        }
        else if (action.equals("searchNotCurTestTask")) {
            return aroundDataSet("NotCurTestTask", point);
        }
        else if (action.equals("searchNotCurTestTaskProject")) {
            return aroundDataSet("NotCurTestTaskProject", point);
        }
        else if (action.equals("searchRePortCase")) {
            return aroundDataSet("RePortCase", point);
        }
        else if (action.equals("searchRePortCaseEntry")) {
            return aroundDataSet("RePortCaseEntry", point);
        }
        else if (action.equals("searchRePortCase_Project")) {
            return aroundDataSet("RePortCase_Project", point);
        }
        else if (action.equals("searchRunERRePortCase")) {
            return aroundDataSet("RunERRePortCase", point);
        }
        else if (action.equals("searchRunERRePortCaseEntry")) {
            return aroundDataSet("RunERRePortCaseEntry", point);
        }
        else if (action.equals("searchRunERRePortCase_Project")) {
            return aroundDataSet("RunERRePortCase_Project", point);
        }
        else if (action.equals("searchRunRePortCase")) {
            return aroundDataSet("RunRePortCase", point);
        }
        else if (action.equals("searchRunRePortCaseEntry")) {
            return aroundDataSet("RunRePortCaseEntry", point);
        }
        else if (action.equals("searchRunRePortCase_Project")) {
            return aroundDataSet("RunRePortCase_Project", point);
        }
        return point.proceed();
    }

}
