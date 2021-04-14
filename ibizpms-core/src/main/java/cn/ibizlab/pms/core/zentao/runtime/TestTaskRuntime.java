package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.TestTask;
import cn.ibizlab.pms.core.zentao.service.ITestTaskService;
import cn.ibizlab.pms.core.zentao.filter.TestTaskSearchContext;
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
@Component("TestTaskRuntime")
public class TestTaskRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    ITestTaskService testtaskService;

    @Override
    protected Object getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntity) {
            return o;
        } else {
            return testtaskService.sysGet(String.valueOf(o));
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/TestTask.json";
    }

    @Override
    public String getName() {
        return "ZT_TESTTASK";
    }

    @Override
    public Object createEntity() {
        return new TestTask();
    }

    @Override
    protected IService getService() {
        return this.testtaskService;
    }

    @Override
    protected QueryWrapperContext createSearchContext() {
        return new TestTaskSearchContext();
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return testtaskService.create((TestTask) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return testtaskService.update((TestTask) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return testtaskService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                return testtaskService.get((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return testtaskService.getDraft((TestTask) args[0]);
            }
            else if (iPSDEAction.getName().equals("Activate")) {
                return testtaskService.activate((TestTask) args[0]);
            }
            else if (iPSDEAction.getName().equals("Block")) {
                return testtaskService.block((TestTask) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return testtaskService.checkKey((TestTask) args[0]);
            }
            else if (iPSDEAction.getName().equals("Close")) {
                return testtaskService.close((TestTask) args[0]);
            }
            else if (iPSDEAction.getName().equals("linkCase")) {
                return testtaskService.linkCase((TestTask) args[0]);
            }
            else if (iPSDEAction.getName().equals("MobTestTaskCounter")) {
                return testtaskService.mobTestTaskCounter((TestTask) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return testtaskService.save((TestTask) args[0]);
            }
            else if (iPSDEAction.getName().equals("Start")) {
                return testtaskService.start((TestTask) args[0]);
            }
            else if (iPSDEAction.getName().equals("unlinkCase")) {
                return testtaskService.unlinkCase((TestTask) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return testtaskService.create((TestTask) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return testtaskService.update((TestTask) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                return testtaskService.get((Long) args[0]);
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return testtaskService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return testtaskService.sysGet((Long) args[0]);
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

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.TestTaskServiceImpl.*(..))")
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
        else if (action.equals("activate")) {
            return aroundAction("Activate", point);
        }
        else if (action.equals("block")) {
            return aroundAction("Block", point);
        }
        else if (action.equals("checkKey")) {
            return aroundAction("CheckKey", point);
        }
        else if (action.equals("close")) {
            return aroundAction("Close", point);
        }
        else if (action.equals("linkCase")) {
            return aroundAction("linkCase", point);
        }
        else if (action.equals("mobTestTaskCounter")) {
            return aroundAction("MobTestTaskCounter", point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("start")) {
            return aroundAction("Start", point);
        }
        else if (action.equals("unlinkCase")) {
            return aroundAction("unlinkCase", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchMyTestTaskPc")) {
            return aroundDataSet("MyTestTaskPc", point);
        }
        return point.proceed();
    }

}
