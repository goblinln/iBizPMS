package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Case;
import cn.ibizlab.pms.core.zentao.service.ICaseService;
import cn.ibizlab.pms.core.zentao.filter.CaseSearchContext;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import cn.ibizlab.pms.core.runtime.DataEntityRuntime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("CaseRuntime")
public class CaseRuntime extends DataEntityRuntime {

    @Autowired
    ICaseService caseService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new Case();
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
    public Object getFieldValue(Object o, IPSDEField ipsdeField) {
        Case domain = (Case)o;
        try {
            return domain.get(ipsdeField.getCodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public void setFieldValue(Object o, IPSDEField ipsdeField, Object o1) {
        Case domain = (Case)o;
        try {
            domain.set(ipsdeField.getCodeName(),o1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean containsFieldValue(Object o, IPSDEField ipsdeField) {
        return false;
    }

    @Override
    public Object createEntity() {
        return new Case();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.CaseServiceImpl.*(..))")
    public Object aroundMethod(ProceedingJoinPoint point) throws Throwable {
        String action = point.getSignature().getName();
        if (action.equals("create")) {
            return aroundAction("Create", point);
        }
        if (action.equals("update")) {
            return aroundAction("Update", point);
        }
        if (action.equals("remove")) {
            return aroundAction("Remove", point);
        }
        if (action.equals("get")) {
            return aroundAction("Get", point);
        }
        if (action.equals("getDraft")) {
            return aroundAction("GetDraft", point);
        }
        if (action.equals("checkKey")) {
            return aroundAction("CheckKey", point);
        }
        if (action.equals("confirmChange")) {
            return aroundAction("confirmChange", point);
        }
        if (action.equals("confirmstorychange")) {
            return aroundAction("confirmstorychange", point);
        }
        if (action.equals("getByTestTask")) {
            return aroundAction("GetByTestTask", point);
        }
        if (action.equals("getTestTaskCntRun")) {
            return aroundAction("GetTestTaskCntRun", point);
        }
        if (action.equals("linkCase")) {
            return aroundAction("linkCase", point);
        }
        if (action.equals("mobLinkCase")) {
            return aroundAction("MobLinkCase", point);
        }
        if (action.equals("runCase")) {
            return aroundAction("RunCase", point);
        }
        if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        if (action.equals("testRunCase")) {
            return aroundAction("TestRunCase", point);
        }
        if (action.equals("testsuitelinkCase")) {
            return aroundAction("testsuitelinkCase", point);
        }
        if (action.equals("unlinkCase")) {
            return aroundAction("unlinkCase", point);
        }
        if (action.equals("unlinkSuiteCase")) {
            return aroundAction("unlinkSuiteCase", point);
        }

    //
        if (action.equals("searchBatchNew")) {
            return aroundAction("BatchNew", point);
        }
        if (action.equals("searchCurOpenedCase")) {
            return aroundAction("CurOpenedCase", point);
        }
        if (action.equals("searchCurSuite")) {
            return aroundAction("CurSuite", point);
        }
        if (action.equals("searchCurTestTask")) {
            return aroundAction("CurTestTask", point);
        }
        if (action.equals("searchDefault")) {
            return aroundAction("DEFAULT", point);
        }
        if (action.equals("searchESBulk")) {
            return aroundAction("ESBulk", point);
        }
        if (action.equals("searchModuleRePortCase")) {
            return aroundAction("ModuleRePortCase", point);
        }
        if (action.equals("searchModuleRePortCaseEntry")) {
            return aroundAction("ModuleRePortCaseEntry", point);
        }
        if (action.equals("searchModuleRePortCase_Project")) {
            return aroundAction("ModuleRePortCase_Project", point);
        }
        if (action.equals("searchMyFavorites")) {
            return aroundAction("MyFavorite", point);
        }
        if (action.equals("searchNotCurTestSuite")) {
            return aroundAction("NotCurTestSuite", point);
        }
        if (action.equals("searchNotCurTestTask")) {
            return aroundAction("NotCurTestTask", point);
        }
        if (action.equals("searchNotCurTestTaskProject")) {
            return aroundAction("NotCurTestTaskProject", point);
        }
        if (action.equals("searchRePortCase")) {
            return aroundAction("RePortCase", point);
        }
        if (action.equals("searchRePortCaseEntry")) {
            return aroundAction("RePortCaseEntry", point);
        }
        if (action.equals("searchRePortCase_Project")) {
            return aroundAction("RePortCase_Project", point);
        }
        if (action.equals("searchRunERRePortCase")) {
            return aroundAction("RunERRePortCase", point);
        }
        if (action.equals("searchRunERRePortCaseEntry")) {
            return aroundAction("RunERRePortCaseEntry", point);
        }
        if (action.equals("searchRunERRePortCase_Project")) {
            return aroundAction("RunERRePortCase_Project", point);
        }
        if (action.equals("searchRunRePortCase")) {
            return aroundAction("RunRePortCase", point);
        }
        if (action.equals("searchRunRePortCaseEntry")) {
            return aroundAction("RunRePortCaseEntry", point);
        }
        if (action.equals("searchRunRePortCase_Project")) {
            return aroundAction("RunRePortCase_Project", point);
        }
        return point.proceed();
    }

}
