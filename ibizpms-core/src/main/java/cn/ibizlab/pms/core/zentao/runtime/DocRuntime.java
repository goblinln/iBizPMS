package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Doc;
import cn.ibizlab.pms.core.zentao.service.IDocService;
import cn.ibizlab.pms.core.zentao.filter.DocSearchContext;
import  cn.ibizlab.pms.util.filter.QueryWrapperContext;
import com.baomidou.mybatisplus.extension.service.IService;
import lombok.extern.slf4j.Slf4j;
import net.ibizsys.model.dataentity.IPSDataEntity;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import net.ibizsys.model.dataentity.der.IPSDER1N;
import net.ibizsys.model.dataentity.der.IPSDERBase;
import net.ibizsys.model.dataentity.ds.IPSDEDataQuery;
import net.ibizsys.model.dataentity.ds.IPSDEDataSet;
import net.ibizsys.model.dataentity.logic.IPSDELogic;
import net.ibizsys.model.dataentity.wf.IPSDEWF;
import net.ibizsys.runtime.IDynaInstRuntime;
import cn.ibizlab.pms.util.domain.EntityBase;
import cn.ibizlab.pms.util.errors.BadRequestAlertException;
import cn.ibizlab.pms.util.helper.DELogicExecutor;
import net.ibizsys.runtime.util.IEntityBase;
import net.ibizsys.runtime.util.ISearchContextBase;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.beans.factory.annotation.Autowired;
import net.ibizsys.runtime.dataentity.action.DEActions;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import java.io.Serializable;
import java.util.List;



@Aspect
@Order(100)
@Component("DocRuntime")
@Slf4j
public class DocRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IDocService docService;

    @Autowired
    DELogicExecutor logicExecutor;

    @Override
    protected IEntityBase getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntityBase) {
            return (IEntityBase) o;
        } else {
            return docService.sysGet((Long) o);
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
    public IEntityBase createEntity() {
        return new Doc();
    }

    @Override
    protected IService getService() {
        return this.docService;
    }

    @Override
    public DocSearchContext createSearchContext() {
        return new DocSearchContext();
    }

    @Override
    public boolean existsData(ISearchContextBase iSearchContextBase) {
        Page<Doc> domains = docService.searchDefault((DocSearchContext) iSearchContextBase);
        if (domains.getSize() == 0)
            return false;
        return true;
    }

    @Override
    public Page<Doc> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        if (iPSDEDataSet.getName().equals("DEFAULT"))
            return docService.searchDefault((DocSearchContext) iSearchContextBase);
        return null;
    }

    @Override
    public Page<Doc> searchDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        //暂未实现
        return null;
    }

    @Override
    public Doc selectOne(ISearchContextBase iSearchContextBase) {
        //单条数据查询，多条数数据时 返回第一条
        Page<Doc> domains = docService.searchDefault((DocSearchContext) iSearchContextBase);
        if (domains.getTotalElements() == 0)
            return null;
        return domains.getContent().get(0);
    }

    @Override
    public List<Doc> select(ISearchContextBase iSearchContextBase) {
        return docService.searchDefault((DocSearchContext) iSearchContextBase).getContent();
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
                if(args[0] instanceof Doc){
                    Doc arg = (Doc) args[0] ;
                    arg = docService.get(arg.getId()) ;
                    return arg;
                }else{
                    return docService.get((Long) args[0]);
                }
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
                if(args[0] instanceof Doc){
                    Doc arg = (Doc) args[0] ;
                    arg = docService.get(arg.getId()) ;
                    return arg;
                }else{
                    return docService.get((Long) args[0]);
                }
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return docService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return docService.sysGet((Long) args[0]);
            }  else if (strActionName.equals(DEActions.SYSUPDATE)) {
                
            }         }
        
        return null;
    }


    /**
     * 执行实体处理逻辑
     * @param arg0 实体
     * @param iPSDEAction 行为
     * @param iPSDELogic  逻辑
     * @param iDynaInstRuntime 动态实例
     * @param joinPoint
     * @throws Throwable
     */
    @Override
    protected void onExecuteDELogic(Object arg0, IPSDEAction iPSDEAction, IPSDELogic iPSDELogic, IDynaInstRuntime iDynaInstRuntime, Object joinPoint) throws Throwable {
        if (arg0 instanceof EntityBase)
            logicExecutor.executeLogic((EntityBase) arg0, iPSDEAction, iPSDELogic, iDynaInstRuntime);
        else
            throw new BadRequestAlertException(String.format("执行实体逻辑异常，不支持参数[%s]", arg0.toString()), "", "");
    }

    /**
     * 执行实体附加逻辑
     * @param arg0 实体
     * @param iPSDEAction 行为
     * @param strAttachMode 附加模式
     * @param iDynaInstRuntime 动态实例
     * @param joinPoint
     * @throws Throwable
     */
    @Override
    protected void onExecuteActionLogics(Object arg0, IPSDEAction iPSDEAction, String strAttachMode, IDynaInstRuntime iDynaInstRuntime, Object joinPoint) throws Throwable {
        Doc entity = null;
        String action = iPSDEAction.getName();
        if (arg0 instanceof Doc) {
            entity = (Doc) arg0;
        } else {
            if (arg0 != null && "get".equalsIgnoreCase(action)) {
                entity = docService.sysGet((Long) arg0);
            } else if ("remove".equalsIgnoreCase(action)) {
                entity = new Doc();
                entity.setId((Long) arg0);
            }
        }
        if (entity != null) {
            logicExecutor.executeAttachLogic(entity, iPSDEAction, strAttachMode, iDynaInstRuntime);
        } else
            throw new BadRequestAlertException(String.format("执行实体行为附加逻辑[%s:%s]发生异常，无法获取传入参数", action, strAttachMode), "", "onExecuteActionLogics");
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
    @Transactional
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
