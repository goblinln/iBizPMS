package cn.ibizlab.pms.core.report.runtime;

import cn.ibizlab.pms.core.report.domain.IbzReport;
import cn.ibizlab.pms.core.report.service.IIbzReportService;
import cn.ibizlab.pms.core.report.filter.IbzReportSearchContext;
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
@Component("IbzReportRuntime")
public class IbzReportRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IIbzReportService ibzreportService;

    @Override
    protected Object getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntity) {
            return o;
        } else {
            return ibzreportService.sysGet((Long)o);
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/report/PSDATAENTITIES/IbzReport.json";
    }

    @Override
    public String getName() {
        return "IBZ_REPORT";
    }

    @Override
    public Object createEntity() {
        return new IbzReport();
    }

    @Override
    protected IService getService() {
        return this.ibzreportService;
    }

    @Override
    protected QueryWrapperContext createSearchContext() {
        return new IbzReportSearchContext();
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return ibzreportService.create((IbzReport) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return ibzreportService.update((IbzReport) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return ibzreportService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                return ibzreportService.get((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return ibzreportService.getDraft((IbzReport) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return ibzreportService.checkKey((IbzReport) args[0]);
            }
            else if (iPSDEAction.getName().equals("MyReportINotSubmit")) {
                return ibzreportService.myReportINotSubmit((IbzReport) args[0]);
            }
            else if (iPSDEAction.getName().equals("ReportIReceived")) {
                return ibzreportService.reportIReceived((IbzReport) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return ibzreportService.save((IbzReport) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return ibzreportService.create((IbzReport) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return ibzreportService.update((IbzReport) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                return ibzreportService.get((Long) args[0]);
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return ibzreportService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return ibzreportService.sysGet((Long) args[0]);
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

    @Around("execution(* cn.ibizlab.pms.core.report.service.impl.IbzReportServiceImpl.*(..))")
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
        else if (action.equals("myReportINotSubmit")) {
            return aroundAction("MyReportINotSubmit", point);
        }
        else if (action.equals("reportIReceived")) {
            return aroundAction("ReportIReceived", point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("searchAllReport")) {
            return aroundDataSet("AllReport", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchMyReAllReport")) {
            return aroundDataSet("MyReAllReport", point);
        }
        return point.proceed();
    }

}
