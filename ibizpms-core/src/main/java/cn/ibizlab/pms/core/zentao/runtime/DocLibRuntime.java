package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.DocLib;
import cn.ibizlab.pms.core.zentao.service.IDocLibService;
import cn.ibizlab.pms.core.zentao.filter.DocLibSearchContext;
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
@Component("DocLibRuntime")
public class DocLibRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IDocLibService doclibService;

    @Override
    protected Object getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntity) {
            return o;
        } else {
            return doclibService.sysGet((Long)o);
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/DocLib.json";
    }

    @Override
    public String getName() {
        return "ZT_DOCLIB";
    }

    @Override
    public Object createEntity() {
        return new DocLib();
    }

    @Override
    protected IService getService() {
        return this.doclibService;
    }

    @Override
    protected QueryWrapperContext createSearchContext() {
        return new DocLibSearchContext();
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return doclibService.create((DocLib) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return doclibService.update((DocLib) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return doclibService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                return doclibService.get((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return doclibService.getDraft((DocLib) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return doclibService.checkKey((DocLib) args[0]);
            }
            else if (iPSDEAction.getName().equals("Collect")) {
                return doclibService.collect((DocLib) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return doclibService.save((DocLib) args[0]);
            }
            else if (iPSDEAction.getName().equals("UnCollect")) {
                return doclibService.unCollect((DocLib) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return doclibService.create((DocLib) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return doclibService.update((DocLib) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                return doclibService.get((Long) args[0]);
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return doclibService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return doclibService.sysGet((Long) args[0]);
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

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.DocLibServiceImpl.*(..))")
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
        else if (action.equals("collect")) {
            return aroundAction("Collect", point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("unCollect")) {
            return aroundAction("UnCollect", point);
        }
        else if (action.equals("searchByCustom")) {
            return aroundDataSet("ByCustom", point);
        }
        else if (action.equals("searchByProduct")) {
            return aroundDataSet("ByProduct", point);
        }
        else if (action.equals("searchByProductNotFiles")) {
            return aroundDataSet("ByProductNotFiles", point);
        }
        else if (action.equals("searchByProject")) {
            return aroundDataSet("ByProject", point);
        }
        else if (action.equals("searchByProjectNotFiles")) {
            return aroundDataSet("ByProjectNotFiles", point);
        }
        else if (action.equals("searchCurDocLib")) {
            return aroundDataSet("CurDocLib", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchMyFavourites")) {
            return aroundDataSet("MyFavourites", point);
        }
        else if (action.equals("searchRootModuleMuLu")) {
            return aroundDataSet("RootModuleMuLu", point);
        }
        return point.proceed();
    }

}
