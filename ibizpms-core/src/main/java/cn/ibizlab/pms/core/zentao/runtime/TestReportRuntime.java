package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.TestReport;
import cn.ibizlab.pms.core.zentao.service.ITestReportService;
import cn.ibizlab.pms.core.zentao.filter.TestReportSearchContext;
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
@Component("TestReportRuntime")
@Slf4j
public class TestReportRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    ITestReportService testreportService;

    @Autowired
    DELogicExecutor logicExecutor;

    @Override
    protected IEntityBase getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntityBase) {
            return (IEntityBase) o;
        } else {
            return testreportService.sysGet((Long) o);
        }
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/TestReport.json";
    }

    @Override
    public String getName() {
        return "ZT_TESTREPORT";
    }

    @Override
    public IEntityBase createEntity() {
        return new TestReport();
    }

    @Override
    protected IService getService() {
        return this.testreportService;
    }

    @Override
    public TestReportSearchContext createSearchContext() {
        return new TestReportSearchContext();
    }

    @Override
    public Object serializeEntity(IEntityBase iEntityBase) {
        try {
            return MAPPER.writeValueAsString(iEntityBase);
        } catch (Exception e) {
            throw new RuntimeException("序列化错误");
        }
    }

    @Override
    public Object serializeEntities(IEntityBase[] list) {
        try {
            return MAPPER.writeValueAsString(list);
        } catch (Exception e) {
            throw new RuntimeException("序列化错误");
        }
    }

    @Override
    public IEntityBase deserializeEntity(Object objData) {
        try {
            return MAPPER.readValue(String.valueOf(objData),TestReport.class);
        } catch (Exception e) {
            throw new RuntimeException("反序列化错误");
        }
    }

    @Override
    public IEntityBase[] deserializeEntities(Object objData) {
        try {
            return MAPPER.readValue(String.valueOf(objData),TestReport[].class);
        } catch (Exception e) {
            throw new RuntimeException("反序列化错误");
        }
    }

    @Override
    public boolean existsData(ISearchContextBase iSearchContextBase) {
        Page<TestReport> domains = testreportService.searchDefault((TestReportSearchContext) iSearchContextBase);
        if (domains.getSize() == 0)
            return false;
        return true;
    }

    @Override
    public Page<TestReport> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        TestReportSearchContext searchContext = (TestReportSearchContext) iSearchContextBase;
        if (iPSDEDataSet.getName().equals("DEFAULT"))
            return testreportService.searchDefault(searchContext);    
        return null;
    }

    @Override
    public List<TestReport> selectDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        TestReportSearchContext searchContext = (TestReportSearchContext) iSearchContextBase;
        if (iPSDataQuery.getName().equals("DEFAULT"))
            return testreportService.selectDefault(searchContext);
        if (iPSDataQuery.getName().equals("VIEW"))
            return testreportService.selectView(searchContext);
        return null;
    }

    @Override
    public TestReport selectOne(ISearchContextBase iSearchContextBase) {
        //单条数据查询，多条数数据时 返回第一条
        TestReportSearchContext searchContext = (TestReportSearchContext) iSearchContextBase;
        searchContext.setSize(1);
        List<TestReport> domains = testreportService.select(searchContext);
        if (domains.size() == 0)
            return null;
        return domains.get(0);
    }

    @Override
    public List<TestReport> select(ISearchContextBase iSearchContextBase) {
        TestReportSearchContext searchContext = (TestReportSearchContext) iSearchContextBase;
        return testreportService.select(searchContext);
    }

    @Override
    public Object executeAction(String strActionName, IPSDEAction iPSDEAction, Object[] args) throws Throwable {
        if (iPSDEAction != null) {
            if (iPSDEAction.getName().equals("Create")) {
                return testreportService.create((TestReport) args[0]);
            }
            else if (iPSDEAction.getName().equals("Update")) {
                return testreportService.update((TestReport) args[0]);
            }
            else if (iPSDEAction.getName().equals("Remove")) {
                return testreportService.remove((Long) args[0]);
            }
            else if (iPSDEAction.getName().equals("Get")) {
                if(args[0] instanceof TestReport){
                    TestReport arg = (TestReport) args[0] ;
                    arg = testreportService.get(arg.getId()) ;
                    return arg;
                }else{
                    return testreportService.get((Long) args[0]);
                }
            }
            else if (iPSDEAction.getName().equals("GetDraft")) {
                return testreportService.getDraft((TestReport) args[0]);
            }
            else if (iPSDEAction.getName().equals("CheckKey")) {
                return testreportService.checkKey((TestReport) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetInfoTaskOvByTime")) {
                return testreportService.getInfoTaskOvByTime((TestReport) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetInfoTestTask")) {
                return testreportService.getInfoTestTask((TestReport) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetInfoTestTaskOvProject")) {
                return testreportService.getInfoTestTaskOvProject((TestReport) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetInfoTestTaskProject")) {
                return testreportService.getInfoTestTaskProject((TestReport) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetInfoTestTaskR")) {
                return testreportService.getInfoTestTaskR((TestReport) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetInfoTestTaskS")) {
                return testreportService.getInfoTestTaskS((TestReport) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetTestReportBasicInfo")) {
                return testreportService.getTestReportBasicInfo((TestReport) args[0]);
            }
            else if (iPSDEAction.getName().equals("GetTestReportProject")) {
                return testreportService.getTestReportProject((TestReport) args[0]);
            }
            else if (iPSDEAction.getName().equals("Save")) {
                return testreportService.save((TestReport) args[0]);
            }
        }else if (StringUtils.isNotBlank(strActionName)) {
            if (strActionName.equals(DEActions.CREATE)) {
                return testreportService.create((TestReport) args[0]);
            } else if (strActionName.equals(DEActions.UPDATE)) {
                return testreportService.update((TestReport) args[0]);
            } else if (strActionName.equals(DEActions.GET)) {
                if(args[0] instanceof TestReport){
                    TestReport arg = (TestReport) args[0] ;
                    arg = testreportService.get(arg.getId()) ;
                    return arg;
                }else{
                    return testreportService.get((Long) args[0]);
                }
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return testreportService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return testreportService.sysGet((Long) args[0]);
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
        TestReport entity = null;
        String action = iPSDEAction.getName();
        if (arg0 instanceof TestReport) {
            entity = (TestReport) arg0;
        } else {
            if (arg0 != null && "get".equalsIgnoreCase(action)) {
                entity = testreportService.sysGet((Long) arg0);
            } else if ("remove".equalsIgnoreCase(action)) {
                entity = new TestReport();
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

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.TestReportServiceImpl.*(..))")
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
        else if (action.equals("getInfoTaskOvByTime")) {
            return aroundAction("GetInfoTaskOvByTime", point);
        }
        else if (action.equals("getInfoTestTask")) {
            return aroundAction("GetInfoTestTask", point);
        }
        else if (action.equals("getInfoTestTaskOvProject")) {
            return aroundAction("GetInfoTestTaskOvProject", point);
        }
        else if (action.equals("getInfoTestTaskProject")) {
            return aroundAction("GetInfoTestTaskProject", point);
        }
        else if (action.equals("getInfoTestTaskR")) {
            return aroundAction("GetInfoTestTaskR", point);
        }
        else if (action.equals("getInfoTestTaskS")) {
            return aroundAction("GetInfoTestTaskS", point);
        }
        else if (action.equals("getTestReportBasicInfo")) {
            return aroundAction("GetTestReportBasicInfo", point);
        }
        else if (action.equals("getTestReportProject")) {
            return aroundAction("GetTestReportProject", point);
        }
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        return point.proceed();
    }

}
