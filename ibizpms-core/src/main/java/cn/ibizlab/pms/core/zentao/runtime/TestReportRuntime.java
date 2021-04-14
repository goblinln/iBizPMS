package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.TestReport;
import cn.ibizlab.pms.core.zentao.service.ITestReportService;
import cn.ibizlab.pms.core.zentao.filter.TestReportSearchContext;
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
@Component("TestReportRuntime")
public class TestReportRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    ITestReportService testreportService;

    @Override
    protected Object getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntity) {
            return o;
        } else {
            return testreportService.sysGet(String.valueOf(o));
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/TestReport.json";
    }

    @Override
    public String getName() {
        return "ZT_TESTREPORT";
    }

    @Override
    public Object createEntity() {
        return new TestReport();
    }

    @Override
    protected IService getService() {
        return this.testreportService;
    }

    @Override
    protected QueryWrapperContext createSearchContext() {
        return new TestReportSearchContext();
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return testreportService.create((TestReport) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return testreportService.update((TestReport) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return testreportService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                return testreportService.get((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return testreportService.getDraft((TestReport) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return testreportService.checkKey((TestReport) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetInfoTaskOvByTime")) {
                return testreportService.getInfoTaskOvByTime((TestReport) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetInfoTestTask")) {
                return testreportService.getInfoTestTask((TestReport) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetInfoTestTaskOvProject")) {
                return testreportService.getInfoTestTaskOvProject((TestReport) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetInfoTestTaskProject")) {
                return testreportService.getInfoTestTaskProject((TestReport) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetInfoTestTaskR")) {
                return testreportService.getInfoTestTaskR((TestReport) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetInfoTestTaskS")) {
                return testreportService.getInfoTestTaskS((TestReport) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetTestReportBasicInfo")) {
                return testreportService.getTestReportBasicInfo((TestReport) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetTestReportProject")) {
                return testreportService.getTestReportProject((TestReport) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return testreportService.save((TestReport) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return testreportService.create((TestReport) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return testreportService.update((TestReport) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                return testreportService.get((Long) args[0]);
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return testreportService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return testreportService.sysGet((Long) args[0]);
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

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.TestReportServiceImpl.*(..))")
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
        else if (action.equals("getInfoTaskOvByTime")) {
            return aroundAction("GetInfoTaskOvByTime", point);
        }
        else if (action.equals("getInfoTestTask")) {
            return aroundAction("GetInfoTestTask", point);
        }
        else if (action.equals("getInfoTestTaskOvProject")) {
            return aroundAction("GetInfoTestTaskOvProject", point);
        }
        else if (action.equals("getInfoTestTaskProject")) {
            return aroundAction("GetInfoTestTaskProject", point);
        }
        else if (action.equals("getInfoTestTaskR")) {
            return aroundAction("GetInfoTestTaskR", point);
        }
        else if (action.equals("getInfoTestTaskS")) {
            return aroundAction("GetInfoTestTaskS", point);
        }
        else if (action.equals("getTestReportBasicInfo")) {
            return aroundAction("GetTestReportBasicInfo", point);
        }
        else if (action.equals("getTestReportProject")) {
            return aroundAction("GetTestReportProject", point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        return point.proceed();
    }

}
