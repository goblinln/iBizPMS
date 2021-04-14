package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.TestSuite;
import cn.ibizlab.pms.core.zentao.service.ITestSuiteService;
import cn.ibizlab.pms.core.zentao.filter.TestSuiteSearchContext;
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
@Component("TestSuiteRuntime")
public class TestSuiteRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    ITestSuiteService testsuiteService;

    @Override
    protected Object getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntity) {
            return o;
        } else {
            return testsuiteService.sysGet(String.valueOf(o));
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/TestSuite.json";
    }

    @Override
    public String getName() {
        return "ZT_TESTSUITE";
    }

    @Override
    public Object createEntity() {
        return new TestSuite();
    }

    @Override
    protected IService getService() {
        return this.testsuiteService;
    }

    @Override
    protected QueryWrapperContext createSearchContext() {
        return new TestSuiteSearchContext();
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return testsuiteService.create((TestSuite) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return testsuiteService.update((TestSuite) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return testsuiteService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                return testsuiteService.get((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return testsuiteService.getDraft((TestSuite) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return testsuiteService.checkKey((TestSuite) args[0]);
            }
            else if (iPSDEAction.getName().equals("MobTestSuiteCount")) {
                return testsuiteService.mobTestSuiteCount((TestSuite) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return testsuiteService.save((TestSuite) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return testsuiteService.create((TestSuite) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return testsuiteService.update((TestSuite) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                return testsuiteService.get((Long) args[0]);
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return testsuiteService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return testsuiteService.sysGet((Long) args[0]);
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

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.TestSuiteServiceImpl.*(..))")
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
        else if (action.equals("mobTestSuiteCount")) {
            return aroundAction("MobTestSuiteCount", point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchPublicTestSuite")) {
            return aroundDataSet("PublicTestSuite", point);
        }
        return point.proceed();
    }

}
