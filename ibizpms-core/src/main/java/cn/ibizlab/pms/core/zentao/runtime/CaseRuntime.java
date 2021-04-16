package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Case;
import cn.ibizlab.pms.core.zentao.service.ICaseService;
import cn.ibizlab.pms.core.zentao.filter.CaseSearchContext;
import  cn.ibizlab.pms.util.filter.QueryWrapperContext;
import com.baomidou.mybatisplus.extension.service.IService;
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
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.beans.factory.annotation.Autowired;
import net.ibizsys.runtime.dataentity.DEActions;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.ObjectUtils;
import java.io.Serializable;
import java.util.List;



@Aspect
@Order(100)
@Component("CaseRuntime")
public class CaseRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    ICaseService caseService;

    @Override
    protected Object getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntity) {
            return o;
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
    public Object createEntity() {
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
    protected void fillEntityFullInfo(Object arg0, String strActionName, IPSDEAction iPSDEAction, IPSDER1N iPSDER1N, IPSDataEntity iPSDataEntity, ProceedingJoinPoint joinPoint) throws Throwable {
        Object objPickupValue = this.getFieldValue(arg0, iPSDER1N.getPSPickupDEField());
        if (ObjectUtils.isEmpty(objPickupValue) || NumberUtils.toLong(String.valueOf(objPickupValue), 0L) == 0L)
            return;
        super.fillEntityFullInfo(arg0, strActionName, iPSDEAction, iPSDER1N, iPSDataEntity, joinPoint);
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

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.CaseServiceImpl.*(..))")
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
