package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Case;
import cn.ibizlab.pms.core.zentao.service.ICaseService;
import cn.ibizlab.pms.core.zentao.filter.CaseSearchContext;
import  cn.ibizlab.pms.util.filter.QueryWrapperContext;
import com.baomidou.mybatisplus.extension.service.IService;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import net.ibizsys.model.dataentity.der.IPSDERBase;
import net.ibizsys.model.dataentity.logic.IPSDELogic;
import net.ibizsys.model.dataentity.wf.IPSDEWF;
import net.ibizsys.runtime.IDynaInstRuntime;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.beans.factory.annotation.Autowired;
import net.ibizsys.runtime.dataentity.DEActions;
import org.apache.commons.lang3.StringUtils;

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
            return caseService.sysGet(String.valueOf(o));
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
    protected QueryWrapperContext createSearchContext() {
        return new CaseSearchContext();
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
                return caseService.get((Long) args[0]);
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
                return caseService.get((Long) args[0]);
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return caseService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return caseService.sysGet((Long) args[0]);
            }  else if (strActionName.equals(DEActions.SYSUPDATE)) {
                
            }         }
        
        return null;
    }

    @Override
    protected void onExecuteDELogic(Object arg0, IPSDEAction iPSDEAction, IPSDELogic iPSDELogic, IDynaInstRuntime iDynaInstRuntime, ProceedingJoinPoint joinPoint) throws Throwable {
        //执行处理逻辑
    }

    @Override
    protected void onExecuteActionLogics(Object arg0, IPSDEAction iPSDEAction, String strAttachMode, IDynaInstRuntime iDynaInstRuntime, ProceedingJoinPoint joinPoint) throws Throwable {
        //行为附加操作
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
