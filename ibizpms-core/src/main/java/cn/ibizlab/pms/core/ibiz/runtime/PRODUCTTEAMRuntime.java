package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.ibiz.domain.PRODUCTTEAM;
import cn.ibizlab.pms.core.ibiz.service.IPRODUCTTEAMService;
import cn.ibizlab.pms.core.ibiz.filter.PRODUCTTEAMSearchContext;
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
@Component("PRODUCTTEAMRuntime")
@Slf4j
public class PRODUCTTEAMRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IPRODUCTTEAMService productteamService;

    @Autowired
    DELogicExecutor logicExecutor;

    @Override
    protected IEntityBase getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntityBase) {
            return (IEntityBase) o;
        } else {
            return productteamService.sysGet((Long) o);
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/ibiz/PSDATAENTITIES/PRODUCTTEAM.json";
    }

    @Override
    public String getName() {
        return "IBZ_PRODUCTTEAM";
    }

    @Override
    public IEntityBase createEntity() {
        return new PRODUCTTEAM();
    }

    @Override
    protected IService getService() {
        return this.productteamService;
    }

    @Override
    public PRODUCTTEAMSearchContext createSearchContext() {
        return new PRODUCTTEAMSearchContext();
    }

    @Override
    public boolean existsData(ISearchContextBase iSearchContextBase) {
        Page<PRODUCTTEAM> domains = productteamService.searchDefault((PRODUCTTEAMSearchContext) iSearchContextBase);
        if (domains.getSize() == 0)
            return false;
        return true;
    }

    @Override
    public Page<PRODUCTTEAM> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        PRODUCTTEAMSearchContext searchContext = (PRODUCTTEAMSearchContext) iSearchContextBase;
        if (iPSDEDataSet.getName().equals("DEFAULT"))
            return productteamService.searchDefault(searchContext);    
        if (iPSDEDataSet.getName().equals("ProductTeamInfo"))
            return productteamService.searchProductTeamInfo(searchContext);    
        if (iPSDEDataSet.getName().equals("ProjectApp"))
            return productteamService.searchProjectApp(searchContext);    
        if (iPSDEDataSet.getName().equals("RowEditDefaultProductTeam"))
            return productteamService.searchRowEditDefaultProductTeam(searchContext);    
        return null;
    }

    @Override
    public List<PRODUCTTEAM> selectDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        PRODUCTTEAMSearchContext searchContext = (PRODUCTTEAMSearchContext) iSearchContextBase;
        if (iPSDataQuery.getName().equals("DEFAULT"))
            return productteamService.selectDefault(searchContext);
        if (iPSDataQuery.getName().equals("ProductTeamInfo"))
            return productteamService.selectProductTeamInfo(searchContext);
        if (iPSDataQuery.getName().equals("ProjectApp"))
            return productteamService.selectProjectApp(searchContext);
        if (iPSDataQuery.getName().equals("RowEditDefaultProductTeam"))
            return productteamService.selectRowEditDefaultProductTeam(searchContext);
        if (iPSDataQuery.getName().equals("VIEW"))
            return productteamService.selectView(searchContext);
        return null;
    }

    @Override
    public PRODUCTTEAM selectOne(ISearchContextBase iSearchContextBase) {
        //单条数据查询，多条数数据时 返回第一条
        PRODUCTTEAMSearchContext searchContext = (PRODUCTTEAMSearchContext) iSearchContextBase;
        searchContext.setSize(1);
        List<PRODUCTTEAM> domains = productteamService.select(searchContext);
        if (domains.size() == 0)
            return null;
        return domains.get(0);
    }

    @Override
    public List<PRODUCTTEAM> select(ISearchContextBase iSearchContextBase) {
        PRODUCTTEAMSearchContext searchContext = (PRODUCTTEAMSearchContext) iSearchContextBase;
        return productteamService.select(searchContext);
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return productteamService.create((PRODUCTTEAM) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return productteamService.update((PRODUCTTEAM) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return productteamService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                if(args[0] instanceof PRODUCTTEAM){
                    PRODUCTTEAM arg = (PRODUCTTEAM) args[0] ;
                    arg = productteamService.get(arg.getId()) ;
                    return arg;
                }else{
                    return productteamService.get((Long) args[0]);
                }
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return productteamService.getDraft((PRODUCTTEAM) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return productteamService.checkKey((PRODUCTTEAM) args[0]);
            }
            else if (iPSDEAction.getName().equals("ProductTeamGuoLv")) {
                return productteamService.productTeamGuoLv((PRODUCTTEAM) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return productteamService.save((PRODUCTTEAM) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return productteamService.create((PRODUCTTEAM) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return productteamService.update((PRODUCTTEAM) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                if(args[0] instanceof PRODUCTTEAM){
                    PRODUCTTEAM arg = (PRODUCTTEAM) args[0] ;
                    arg = productteamService.get(arg.getId()) ;
                    return arg;
                }else{
                    return productteamService.get((Long) args[0]);
                }
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return productteamService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return productteamService.sysGet((Long) args[0]);
            }  else if (strActionName.equals(DEActions.SYSUPDATE)) {
                
            }             
        }
        
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
        PRODUCTTEAM entity = null;
        String action = iPSDEAction.getName();
        if (arg0 instanceof PRODUCTTEAM) {
            entity = (PRODUCTTEAM) arg0;
        } else {
            if (arg0 != null && "get".equalsIgnoreCase(action)) {
                entity = productteamService.sysGet((Long) arg0);
            } else if ("remove".equalsIgnoreCase(action)) {
                entity = new PRODUCTTEAM();
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

    @Around("execution(* cn.ibizlab.pms.core.ibiz.service.impl.PRODUCTTEAMServiceImpl.*(..))")
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
        else if (action.equals("checkKey")) {
            return aroundAction("CheckKey", point);
        }
        else if (action.equals("productTeamGuoLv")) {
            return aroundAction("ProductTeamGuoLv", point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchProductTeamInfo")) {
            return aroundDataSet("ProductTeamInfo", point);
        }
        else if (action.equals("searchProjectApp")) {
            return aroundDataSet("ProjectApp", point);
        }
        else if (action.equals("searchRowEditDefaultProductTeam")) {
            return aroundDataSet("RowEditDefaultProductTeam", point);
        }
        return point.proceed();
    }

}
