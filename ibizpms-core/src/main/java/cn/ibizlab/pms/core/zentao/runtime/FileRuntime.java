package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.File;
import cn.ibizlab.pms.core.zentao.service.IFileService;
import cn.ibizlab.pms.core.zentao.filter.FileSearchContext;
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
@Component("FileRuntime")
public class FileRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IFileService fileService;

    @Override
    protected Object getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntity) {
            return o;
        } else {
            return fileService.sysGet(String.valueOf(o));
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/File.json";
    }

    @Override
    public String getName() {
        return "ZT_FILE";
    }

    @Override
    public Object createEntity() {
        return new File();
    }

    @Override
    protected IService getService() {
        return this.fileService;
    }

    @Override
    protected QueryWrapperContext createSearchContext() {
        return new FileSearchContext();
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return fileService.create((File) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return fileService.update((File) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return fileService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                return fileService.get((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return fileService.getDraft((File) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return fileService.checkKey((File) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return fileService.save((File) args[0]);
            }
            else if (iPSDEAction.getName().equals("updateObjectIDForPmsEe")) {
                return fileService.updateObjectIDForPmsEe((File) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return fileService.create((File) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return fileService.update((File) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                return fileService.get((Long) args[0]);
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return fileService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return fileService.sysGet((Long) args[0]);
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

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.FileServiceImpl.*(..))")
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
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("updateObjectIDForPmsEe")) {
            return aroundAction("updateObjectIDForPmsEe", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchDocLibFile")) {
            return aroundDataSet("DocLibFile", point);
        }
        else if (action.equals("searchProductDocLibFile")) {
            return aroundDataSet("ProductDocLibFile", point);
        }
        else if (action.equals("searchType")) {
            return aroundDataSet("Type", point);
        }
        else if (action.equals("searchTypeNotBySrfparentkey")) {
            return aroundDataSet("TypeNotBySrfparentkey", point);
        }
        return point.proceed();
    }

}
