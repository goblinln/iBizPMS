package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Todo;
import cn.ibizlab.pms.core.zentao.service.ITodoService;
import cn.ibizlab.pms.core.zentao.filter.TodoSearchContext;
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
@Component("TodoRuntime")
public class TodoRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    ITodoService todoService;

    @Override
    protected Object getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntity) {
            return o;
        } else {
            return todoService.sysGet(String.valueOf(o));
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/Todo.json";
    }

    @Override
    public String getName() {
        return "ZT_TODO";
    }

    @Override
    public Object createEntity() {
        return new Todo();
    }

    @Override
    protected IService getService() {
        return this.todoService;
    }

    @Override
    protected QueryWrapperContext createSearchContext() {
        return new TodoSearchContext();
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return todoService.create((Todo) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return todoService.update((Todo) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return todoService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                return todoService.get((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return todoService.getDraft((Todo) args[0]);
            }
            else if (iPSDEAction.getName().equals("Activate")) {
                return todoService.activate((Todo) args[0]);
            }
            else if (iPSDEAction.getName().equals("AssignTo")) {
                return todoService.assignTo((Todo) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return todoService.checkKey((Todo) args[0]);
            }
            else if (iPSDEAction.getName().equals("Close")) {
                return todoService.close((Todo) args[0]);
            }
            else if (iPSDEAction.getName().equals("createCycle")) {
                return todoService.createCycle((Todo) args[0]);
            }
            else if (iPSDEAction.getName().equals("Finish")) {
                return todoService.finish((Todo) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return todoService.save((Todo) args[0]);
            }
            else if (iPSDEAction.getName().equals("sendMessage")) {
                return todoService.sendMessage((Todo) args[0]);
            }
            else if (iPSDEAction.getName().equals("sendMsgPreProcess")) {
                return todoService.sendMsgPreProcess((Todo) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return todoService.create((Todo) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return todoService.update((Todo) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                return todoService.get((Long) args[0]);
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return todoService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return todoService.sysGet((Long) args[0]);
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

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.TodoServiceImpl.*(..))")
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
        else if (action.equals("assignTo")) {
            return aroundAction("AssignTo", point);
        }
        else if (action.equals("checkKey")) {
            return aroundAction("CheckKey", point);
        }
        else if (action.equals("close")) {
            return aroundAction("Close", point);
        }
        else if (action.equals("createCycle")) {
            return aroundAction("createCycle", point);
        }
        else if (action.equals("finish")) {
            return aroundAction("Finish", point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("sendMessage")) {
            return aroundAction("sendMessage", point);
        }
        else if (action.equals("sendMsgPreProcess")) {
            return aroundAction("sendMsgPreProcess", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchMyTodo")) {
            return aroundDataSet("MyTodo", point);
        }
        else if (action.equals("searchMyTodoPc")) {
            return aroundDataSet("MyTodoPc", point);
        }
        else if (action.equals("searchMyUpcoming")) {
            return aroundDataSet("MyUpcoming", point);
        }
        return point.proceed();
    }

}
