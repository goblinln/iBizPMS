package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Doc;
import cn.ibizlab.pms.core.zentao.service.IDocService;
import cn.ibizlab.pms.core.zentao.filter.DocSearchContext;
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
@Component("DocRuntime")
public class DocRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IDocService docService;

    @Override
    protected Object getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntity) {
            return o;
        } else {
            return docService.sysGet(String.valueOf(o));
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/Doc.json";
    }

    @Override
    public String getName() {
        return "ZT_DOC";
    }

    @Override
    public Object createEntity() {
        return new Doc();
    }

    @Override
    protected IService getService() {
        return this.docService;
    }

    @Override
    protected QueryWrapperContext createSearchContext() {
        return new DocSearchContext();
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return docService.create((Doc) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return docService.update((Doc) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return docService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                return docService.get((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return docService.getDraft((Doc) args[0]);
            }
            else if (iPSDEAction.getName().equals("ByVersionUpdateContext")) {
                return docService.byVersionUpdateContext((Doc) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return docService.checkKey((Doc) args[0]);
            }
            else if (iPSDEAction.getName().equals("Collect")) {
                return docService.collect((Doc) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetDocStatus")) {
                return docService.getDocStatus((Doc) args[0]);
            }
            else if (iPSDEAction.getName().equals("OnlyCollectDoc")) {
                return docService.onlyCollectDoc((Doc) args[0]);
            }
            else if (iPSDEAction.getName().equals("OnlyUnCollectDoc")) {
                return docService.onlyUnCollectDoc((Doc) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return docService.save((Doc) args[0]);
            }
            else if (iPSDEAction.getName().equals("UnCollect")) {
                return docService.unCollect((Doc) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return docService.create((Doc) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return docService.update((Doc) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                return docService.get((Long) args[0]);
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return docService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return docService.sysGet((Long) args[0]);
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

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.DocServiceImpl.*(..))")
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
        else if (action.equals("byVersionUpdateContext")) {
            return aroundAction("ByVersionUpdateContext", point);
        }
        else if (action.equals("checkKey")) {
            return aroundAction("CheckKey", point);
        }
        else if (action.equals("collect")) {
            return aroundAction("Collect", point);
        }
        else if (action.equals("getDocStatus")) {
            return aroundAction("GetDocStatus", point);
        }
        else if (action.equals("onlyCollectDoc")) {
            return aroundAction("OnlyCollectDoc", point);
        }
        else if (action.equals("onlyUnCollectDoc")) {
            return aroundAction("OnlyUnCollectDoc", point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("unCollect")) {
            return aroundAction("UnCollect", point);
        }
        else if (action.equals("searchChildDocLibDoc")) {
            return aroundDataSet("ChildDocLibDoc", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchDocLibAndDoc")) {
            return aroundDataSet("DocLibAndDoc", point);
        }
        else if (action.equals("searchDocLibDoc")) {
            return aroundDataSet("DocLibDoc", point);
        }
        else if (action.equals("searchDocModuleDoc")) {
            return aroundDataSet("DocModuleDoc", point);
        }
        else if (action.equals("searchDocStatus")) {
            return aroundDataSet("DocStatus", point);
        }
        else if (action.equals("searchModuleDocChild")) {
            return aroundDataSet("ModuleDocChild", point);
        }
        else if (action.equals("searchMyFavourite")) {
            return aroundDataSet("MYFAVOURITE", point);
        }
        else if (action.equals("searchMyFavouritesOnlyDoc")) {
            return aroundDataSet("MyFavouritesOnlyDoc", point);
        }
        else if (action.equals("searchNotRootDoc")) {
            return aroundDataSet("NotRootDoc", point);
        }
        else if (action.equals("searchRootDoc")) {
            return aroundDataSet("RootDoc", point);
        }
        return point.proceed();
    }

}
