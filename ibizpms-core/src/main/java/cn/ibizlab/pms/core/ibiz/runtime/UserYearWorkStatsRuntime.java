package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.ibiz.domain.UserYearWorkStats;
import cn.ibizlab.pms.core.ibiz.service.IUserYearWorkStatsService;
import cn.ibizlab.pms.core.ibiz.filter.UserYearWorkStatsSearchContext;
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
@Component("UserYearWorkStatsRuntime")
public class UserYearWorkStatsRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IUserYearWorkStatsService useryearworkstatsService;

    @Override
    protected Object getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntity) {
            return o;
        } else {
            return useryearworkstatsService.sysGet(String.valueOf(o));
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/ibiz/PSDATAENTITIES/UserYearWorkStats.json";
    }

    @Override
    public String getName() {
        return "IBZ_USERYEARWORKSTATS";
    }

    @Override
    public Object createEntity() {
        return new UserYearWorkStats();
    }

    @Override
    protected IService getService() {
        return this.useryearworkstatsService;
    }

    @Override
    protected QueryWrapperContext createSearchContext() {
        return new UserYearWorkStatsSearchContext();
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return useryearworkstatsService.create((UserYearWorkStats) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return useryearworkstatsService.update((UserYearWorkStats) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return useryearworkstatsService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                return useryearworkstatsService.get((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return useryearworkstatsService.getDraft((UserYearWorkStats) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return useryearworkstatsService.checkKey((UserYearWorkStats) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetDevInfomation")) {
                return useryearworkstatsService.getDevInfomation((UserYearWorkStats) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetPoInfomation")) {
                return useryearworkstatsService.getPoInfomation((UserYearWorkStats) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetQaInfomation")) {
                return useryearworkstatsService.getQaInfomation((UserYearWorkStats) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetUserYearAction")) {
                return useryearworkstatsService.getUserYearAction((UserYearWorkStats) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return useryearworkstatsService.save((UserYearWorkStats) args[0]);
            }
            else if (iPSDEAction.getName().equals("UpdateTitleByYear")) {
                return useryearworkstatsService.updateTitleByYear((UserYearWorkStats) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return useryearworkstatsService.create((UserYearWorkStats) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return useryearworkstatsService.update((UserYearWorkStats) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                return useryearworkstatsService.get((Long) args[0]);
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return useryearworkstatsService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return useryearworkstatsService.sysGet((Long) args[0]);
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

    @Around("execution(* cn.ibizlab.pms.core.ibiz.service.impl.UserYearWorkStatsServiceImpl.*(..))")
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
        else if (action.equals("getDevInfomation")) {
            return aroundAction("GetDevInfomation", point);
        }
        else if (action.equals("getPoInfomation")) {
            return aroundAction("GetPoInfomation", point);
        }
        else if (action.equals("getQaInfomation")) {
            return aroundAction("GetQaInfomation", point);
        }
        else if (action.equals("getUserYearAction")) {
            return aroundAction("GetUserYearAction", point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("updateTitleByYear")) {
            return aroundAction("UpdateTitleByYear", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchMonthFinishTaskAndBug")) {
            return aroundDataSet("MonthFinishTaskAndBug", point);
        }
        else if (action.equals("searchMonthOpenedBugAndCase")) {
            return aroundDataSet("MonthOpenedBugAndCase", point);
        }
        else if (action.equals("searchMonthOpenedStory")) {
            return aroundDataSet("MonthOpenedStory", point);
        }
        return point.proceed();
    }

}
