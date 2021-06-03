package cn.ibizlab.pms.core.report.runtime;

import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import cn.ibizlab.pms.core.sample.runtime.IIbzWeeklyRuntime;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Aspect
@org.springframework.core.annotation.Order(100)
@Component("IbzWeeklyRuntime")
@Slf4j
public class IbzWeeklyAspect extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

     @Autowired
    IIbzWeeklyRuntime ibzWeeklyRuntime;

    private SystemDataEntityRuntime getDataEntityRuntime() {
        return (SystemDataEntityRuntime) ibzWeeklyRuntime;
    }

    @Around("execution(* cn.ibizlab.pms.core.report.service.impl.IbzWeeklyServiceImpl.*(..))")
    @Transactional
    public Object aroundMethod(ProceedingJoinPoint point) throws Throwable {
        if (!this.isRtmodel()) {
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
        else if (action.equals("checkKey")) {
            return getDataEntityRuntime().aroundAction("CheckKey", point);
        }
        else if (action.equals("createEveryWeekReport")) {
            return getDataEntityRuntime().aroundAction("createEveryWeekReport", point);
        }
        else if (action.equals("createGetLastWeekPlanAndWork")) {
            return getDataEntityRuntime().aroundAction("CreateGetLastWeekPlanAndWork", point);
        }
        else if (action.equals("editGetLastWeekTaskAndComTask")) {
            return getDataEntityRuntime().aroundAction("EditGetLastWeekTaskAndComTask", point);
        }
        else if (action.equals("haveRead")) {
            return getDataEntityRuntime().aroundAction("haveRead", point);
        }
        else if (action.equals("jugThisWeekCreateWeekly")) {
            return getDataEntityRuntime().aroundAction("JugThisWeekCreateWeekly", point);
        }
        else if (action.equals("pushUserWeekly")) {
            return getDataEntityRuntime().aroundAction("pushUserWeekly", point);
        }
        else if (action.equals("save")) {
            return getDataEntityRuntime().aroundAction("Save", point);
        }
        else if (action.equals("submit")) {
            return getDataEntityRuntime().aroundAction("submit", point);
        }
        else if (action.equals("searchDefault")) {
            return getDataEntityRuntime().aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchMyNotSubmit")) {
            return getDataEntityRuntime().aroundDataSet("MyNotSubmit", point);
        }
        else if (action.equals("searchMyWeekly")) {
            return getDataEntityRuntime().aroundDataSet("MyWeekly", point);
        }
        else if (action.equals("searchProductTeamMemberWeekly")) {
            return getDataEntityRuntime().aroundDataSet("ProductTeamMemberWeekly", point);
        }
        else if (action.equals("searchProjectWeekly")) {
            return getDataEntityRuntime().aroundDataSet("ProjectWeekly", point);
        }
        return point.proceed();
    }
}
