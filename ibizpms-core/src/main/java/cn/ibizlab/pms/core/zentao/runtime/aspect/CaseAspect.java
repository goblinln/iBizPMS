package cn.ibizlab.pms.core.zentao.runtime.aspect;

import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import cn.ibizlab.pms.core.zentao.runtime.ICaseRuntime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Aspect
@org.springframework.core.annotation.Order(100)
@Slf4j
@Component
public class CaseAspect {

     @Autowired
    ICaseRuntime caseRuntime;

    private SystemDataEntityRuntime getDataEntityRuntime() {
        return (SystemDataEntityRuntime) caseRuntime;
    }

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.CaseServiceImpl.*(..))")
    @Transactional
    public Object aroundMethod(ProceedingJoinPoint point) throws Throwable {
        if (!getDataEntityRuntime().isRtmodel()) {
            return point.proceed();
        }
        String action = point.getSignature().getName();
        if (action.equals("create")) {
            return getDataEntityRuntime().aroundAction("Create", point);
        }
        else if (action.equals("update")) {
            return getDataEntityRuntime().aroundAction("Update", point);
        }
        else if (action.equals("remove")) {
            return getDataEntityRuntime().aroundAction("Remove", point);
        }
        else if (action.equals("get")) {
            return getDataEntityRuntime().aroundAction("Get", point);
        }
        else if (action.equals("getDraft")) {
            return getDataEntityRuntime().aroundAction("GetDraft", point);
        }
        else if (action.equals("caseFavorite")) {
            return getDataEntityRuntime().aroundAction("CaseFavorite", point);
        }
        else if (action.equals("caseNFavorite")) {
            return getDataEntityRuntime().aroundAction("CaseNFavorite", point);
        }
        else if (action.equals("checkKey")) {
            return getDataEntityRuntime().aroundAction("CheckKey", point);
        }
        else if (action.equals("confirmChange")) {
            return getDataEntityRuntime().aroundAction("confirmChange", point);
        }
        else if (action.equals("confirmstorychange")) {
            return getDataEntityRuntime().aroundAction("confirmstorychange", point);
        }
        else if (action.equals("getByTestTask")) {
            return getDataEntityRuntime().aroundAction("GetByTestTask", point);
        }
        else if (action.equals("getTestTaskCntRun")) {
            return getDataEntityRuntime().aroundAction("GetTestTaskCntRun", point);
        }
        else if (action.equals("linkCase")) {
            return getDataEntityRuntime().aroundAction("linkCase", point);
        }
        else if (action.equals("mobLinkCase")) {
            return getDataEntityRuntime().aroundAction("MobLinkCase", point);
        }
        else if (action.equals("runCase")) {
            return getDataEntityRuntime().aroundAction("RunCase", point);
        }
        else if (action.equals("runCases")) {
            return getDataEntityRuntime().aroundAction("runCases", point);
        }
        else if (action.equals("save")) {
            return getDataEntityRuntime().aroundAction("Save", point);
        }
        else if (action.equals("testRunCase")) {
            return getDataEntityRuntime().aroundAction("TestRunCase", point);
        }
        else if (action.equals("testRunCases")) {
            return getDataEntityRuntime().aroundAction("testRunCases", point);
        }
        else if (action.equals("testsuitelinkCase")) {
            return getDataEntityRuntime().aroundAction("testsuitelinkCase", point);
        }
        else if (action.equals("unlinkCase")) {
            return getDataEntityRuntime().aroundAction("unlinkCase", point);
        }
        else if (action.equals("unlinkCases")) {
            return getDataEntityRuntime().aroundAction("unlinkCases", point);
        }
        else if (action.equals("unlinkSuiteCase")) {
            return getDataEntityRuntime().aroundAction("unlinkSuiteCase", point);
        }
        else if (action.equals("unlinkSuiteCases")) {
            return getDataEntityRuntime().aroundAction("unlinkSuiteCases", point);
        }
        else if (action.equals("searchAccount")) {
            return getDataEntityRuntime().aroundDataSet("Account", point);
        }
        else if (action.equals("searchBatchNew")) {
            return getDataEntityRuntime().aroundDataSet("BatchNew", point);
        }
        else if (action.equals("searchCurOpenedCase")) {
            return getDataEntityRuntime().aroundDataSet("CurOpenedCase", point);
        }
        else if (action.equals("searchCurSuite")) {
            return getDataEntityRuntime().aroundDataSet("CurSuite", point);
        }
        else if (action.equals("searchCurTestTask")) {
            return getDataEntityRuntime().aroundDataSet("CurTestTask", point);
        }
        else if (action.equals("searchDefault")) {
            return getDataEntityRuntime().aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchESBulk")) {
            return getDataEntityRuntime().aroundDataSet("ESBulk", point);
        }
        else if (action.equals("searchModuleRePortCase")) {
            return getDataEntityRuntime().aroundDataSet("ModuleRePortCase", point);
        }
        else if (action.equals("searchModuleRePortCaseEntry")) {
            return getDataEntityRuntime().aroundDataSet("ModuleRePortCaseEntry", point);
        }
        else if (action.equals("searchModuleRePortCase_Project")) {
            return getDataEntityRuntime().aroundDataSet("ModuleRePortCase_Project", point);
        }
        else if (action.equals("searchMy")) {
            return getDataEntityRuntime().aroundDataSet("My", point);
        }
        else if (action.equals("searchMyCreateOrUpdate")) {
            return getDataEntityRuntime().aroundDataSet("MyCreateOrUpdate", point);
        }
        else if (action.equals("searchMyFavorites")) {
            return getDataEntityRuntime().aroundDataSet("MyFavorite", point);
        }
        else if (action.equals("searchMyReProduct")) {
            return getDataEntityRuntime().aroundDataSet("MyReProduct", point);
        }
        else if (action.equals("searchNotCurTestSuite")) {
            return getDataEntityRuntime().aroundDataSet("NotCurTestSuite", point);
        }
        else if (action.equals("searchNotCurTestTask")) {
            return getDataEntityRuntime().aroundDataSet("NotCurTestTask", point);
        }
        else if (action.equals("searchNotCurTestTaskProject")) {
            return getDataEntityRuntime().aroundDataSet("NotCurTestTaskProject", point);
        }
        else if (action.equals("searchRePortCase")) {
            return getDataEntityRuntime().aroundDataSet("RePortCase", point);
        }
        else if (action.equals("searchRePortCaseEntry")) {
            return getDataEntityRuntime().aroundDataSet("RePortCaseEntry", point);
        }
        else if (action.equals("searchRePortCase_Project")) {
            return getDataEntityRuntime().aroundDataSet("RePortCase_Project", point);
        }
        else if (action.equals("searchRunERRePortCase")) {
            return getDataEntityRuntime().aroundDataSet("RunERRePortCase", point);
        }
        else if (action.equals("searchRunERRePortCaseEntry")) {
            return getDataEntityRuntime().aroundDataSet("RunERRePortCaseEntry", point);
        }
        else if (action.equals("searchRunERRePortCase_Project")) {
            return getDataEntityRuntime().aroundDataSet("RunERRePortCase_Project", point);
        }
        else if (action.equals("searchRunRePortCase")) {
            return getDataEntityRuntime().aroundDataSet("RunRePortCase", point);
        }
        else if (action.equals("searchRunRePortCaseEntry")) {
            return getDataEntityRuntime().aroundDataSet("RunRePortCaseEntry", point);
        }
        else if (action.equals("searchRunRePortCase_Project")) {
            return getDataEntityRuntime().aroundDataSet("RunRePortCase_Project", point);
        }
        return point.proceed();
    }
}
