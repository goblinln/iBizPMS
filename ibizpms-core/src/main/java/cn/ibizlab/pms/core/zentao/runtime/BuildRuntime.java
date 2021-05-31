package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.Build;
import cn.ibizlab.pms.core.zentao.service.IBuildService;
import cn.ibizlab.pms.core.zentao.filter.BuildSearchContext;
import cn.ibizlab.pms.util.filter.QueryWrapperContext;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
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
import cn.ibizlab.pms.util.domain.WFInstance;
import net.ibizsys.runtime.dataentity.DataEntityRuntimeException;
import net.ibizsys.runtime.util.IEntityBase;
import net.ibizsys.runtime.util.ISearchContextBase;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import net.ibizsys.runtime.dataentity.action.DEActions;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.DigestUtils;
import java.io.Serializable;
import java.util.List;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.alibaba.fastjson.JSONObject;


@Aspect
@org.springframework.core.annotation.Order(100)
@Component("BuildRuntime")
@Slf4j
public class BuildRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IBuildService buildService;

    @Autowired
    DELogicExecutor logicExecutor;

    @Override
    protected IEntityBase getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntityBase) {
            return (IEntityBase) o;
        } else {
            return buildService.sysGet((Long) o);
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/Build.json";
    }

    @Override
    public String getName() {
        return "ZT_BUILD";
    }

    @Override
    public IEntityBase createEntity() {
        return new Build();
    }

    @Override
    protected IService getService() {
        return this.buildService;
    }

    @Override
    public BuildSearchContext createSearchContext() {
        return new BuildSearchContext();
    }

    @Override
    public Object serializeEntity(IEntityBase iEntityBase) {
        try {
            return MAPPER.writeValueAsString(iEntityBase);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public Object serializeEntities(IEntityBase[] list) {
        try {
            return MAPPER.writeValueAsString(list);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public IEntityBase deserializeEntity(Object objData) {
        try {
            return MAPPER.readValue(String.valueOf(objData),Build.class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public IEntityBase[] deserializeEntities(Object objData) {
        try {
            return MAPPER.readValue(String.valueOf(objData),Build[].class);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("[%1$s]反序列化数据异常，%2$s", this.getName(), e.getMessage()), this, e);
        }
    }

    @Override
    public Page<Build> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        BuildSearchContext searchContext = (BuildSearchContext) iSearchContextBase;
        if (iPSDEDataSet.getName().equals("BugProductBuild"))
            return buildService.searchBugProductBuild(searchContext);    
        if (iPSDEDataSet.getName().equals("BugProductOrProjectBuild"))
            return buildService.searchBugProductOrProjectBuild(searchContext);    
        if (iPSDEDataSet.getName().equals("CurProduct"))
            return buildService.searchCurProduct(searchContext);    
        if (iPSDEDataSet.getName().equals("DEFAULT"))
            return buildService.searchDefault(searchContext);    
        if (iPSDEDataSet.getName().equals("ProductBuildDS"))
            return buildService.searchProductBuildDS(searchContext);    
        if (iPSDEDataSet.getName().equals("TestBuild"))
            return buildService.searchTestBuild(searchContext);    
        if (iPSDEDataSet.getName().equals("TestRounds"))
            return buildService.searchTestRounds(searchContext);    
        if (iPSDEDataSet.getName().equals("UpdateLog"))
            return buildService.searchUpdateLog(searchContext);    
        return null;
    }

    @Override
    public List<Build> selectDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        BuildSearchContext searchContext = (BuildSearchContext) iSearchContextBase;
        if (iPSDataQuery.getName().equals("BugProductBuild"))
            return buildService.selectBugProductBuild(searchContext);
        if (iPSDataQuery.getName().equals("BugProductOrProjectBuild"))
            return buildService.selectBugProductOrProjectBuild(searchContext);
        if (iPSDataQuery.getName().equals("CurProduct"))
            return buildService.selectCurProduct(searchContext);
        if (iPSDataQuery.getName().equals("DEFAULT"))
            return buildService.selectDefault(searchContext);
        if (iPSDataQuery.getName().equals("SIMPLE"))
            return buildService.selectSimple(searchContext);
        if (iPSDataQuery.getName().equals("TestBuild"))
            return buildService.selectTestBuild(searchContext);
        if (iPSDataQuery.getName().equals("TestRounds"))
            return buildService.selectTestRounds(searchContext);
        if (iPSDataQuery.getName().equals("UpdateLog"))
            return buildService.selectUpdateLog(searchContext);
        if (iPSDataQuery.getName().equals("VIEW"))
            return buildService.selectView(searchContext);
        return null;
    }

    @Override
    public List<Build> select(ISearchContextBase iSearchContextBase) {
        BuildSearchContext searchContext = (BuildSearchContext) iSearchContextBase;
        return buildService.select(searchContext);
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return buildService.create((Build) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return buildService.update((Build) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return buildService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                if(args[0] instanceof Build){
                    Build arg = (Build) args[0] ;
                    CachedBeanCopier.copy(buildService.get(arg.getId()), arg);
                    return arg;
                }else{
                    return buildService.get((Long) args[0]);
                }
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return buildService.getDraft((Build) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return buildService.checkKey((Build) args[0]);
            }
            else if (iPSDEAction.getName().equals("linkBug")) {
                return buildService.linkBug((Build) args[0]);
            }
            else if (iPSDEAction.getName().equals("LinkStory")) {
                return buildService.linkStory((Build) args[0]);
            }
            else if (iPSDEAction.getName().equals("MobProjectBuildCounter")) {
                return buildService.mobProjectBuildCounter((Build) args[0]);
            }
            else if (iPSDEAction.getName().equals("OneClickRelease")) {
                return buildService.oneClickRelease((Build) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return buildService.save((Build) args[0]);
            }
            else if (iPSDEAction.getName().equals("unlinkBug")) {
                return buildService.unlinkBug((Build) args[0]);
            }
            else if (iPSDEAction.getName().equals("unlinkStory")) {
                return buildService.unlinkStory((Build) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equalsIgnoreCase("Create")) {
                return buildService.create((Build) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Update")) {
                return buildService.update((Build) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Remove")) {
                return buildService.remove((Long) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Get")) {
                if(args[0] instanceof Build){
                    Build arg = (Build) args[0] ;
                    CachedBeanCopier.copy(buildService.get(arg.getId()), arg);
                    return arg;
                }else{
                    return buildService.get((Long) args[0]);
                }
            }
            else if (strActionName.equalsIgnoreCase("GetDraft")) {
                return buildService.getDraft((Build) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("CheckKey")) {
                return buildService.checkKey((Build) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("linkBug")) {
                return buildService.linkBug((Build) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("LinkStory")) {
                return buildService.linkStory((Build) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("MobProjectBuildCounter")) {
                return buildService.mobProjectBuildCounter((Build) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("OneClickRelease")) {
                return buildService.oneClickRelease((Build) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("Save")) {
                return buildService.save((Build) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("unlinkBug")) {
                return buildService.unlinkBug((Build) args[0]);
            }
            else if (strActionName.equalsIgnoreCase("unlinkStory")) {
                return buildService.unlinkStory((Build) args[0]);
            }
            else  if (strActionName.equalsIgnoreCase(DEActions.SYSGET)) {
                if(args[0] instanceof Build){
                    Build arg = (Build) args[0] ;
                    CachedBeanCopier.copy(buildService.sysGet(arg.getId()), arg);
                    return arg;
                }else{
                    return buildService.sysGet((Long) args[0]);
                }
            }
            else  if (strActionName.equalsIgnoreCase(DEActions.SYSUPDATE)) {
                    return buildService.sysUpdate((Build) args[0]);
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
        Build entity = null;
        String action = iPSDEAction.getName();
        if (arg0 instanceof Build) {
            entity = (Build) arg0;
        } else {
            if (arg0 != null && "get".equalsIgnoreCase(action)) {
                entity = buildService.sysGet((Long) arg0);
            } else if ("remove".equalsIgnoreCase(action)) {
                entity = new Build();
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

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.BuildServiceImpl.*(..))")
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
        else if (action.equals("linkBug")) {
            return aroundAction("linkBug", point);
        }
        else if (action.equals("linkStory")) {
            return aroundAction("LinkStory", point);
        }
        else if (action.equals("mobProjectBuildCounter")) {
            return aroundAction("MobProjectBuildCounter", point);
        }
        else if (action.equals("oneClickRelease")) {
            return aroundAction("OneClickRelease", point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("unlinkBug")) {
            return aroundAction("unlinkBug", point);
        }
        else if (action.equals("unlinkStory")) {
            return aroundAction("unlinkStory", point);
        }
        else if (action.equals("searchBugProductBuild")) {
            return aroundDataSet("BugProductBuild", point);
        }
        else if (action.equals("searchBugProductOrProjectBuild")) {
            return aroundDataSet("BugProductOrProjectBuild", point);
        }
        else if (action.equals("searchCurProduct")) {
            return aroundDataSet("CurProduct", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        else if (action.equals("searchProductBuildDS")) {
            return aroundDataSet("ProductBuildDS", point);
        }
        else if (action.equals("searchTestBuild")) {
            return aroundDataSet("TestBuild", point);
        }
        else if (action.equals("searchTestRounds")) {
            return aroundDataSet("TestRounds", point);
        }
        else if (action.equals("searchUpdateLog")) {
            return aroundDataSet("UpdateLog", point);
        }
        return point.proceed();
    }

    @Override
    protected void onWFRegister(Object arg0, IPSDEAction iPSDEAction, IPSDEWF iPSDEWF, IDynaInstRuntime iDynaInstRuntime, Object actionData) throws Throwable {
    }

    @Override
    protected void onWFUnregister(Object arg0, IPSDEAction iPSDEAction, IPSDEWF iPSDEWF, IDynaInstRuntime iDynaInstRuntime, Object actionData) throws Throwable {
    }
}
