package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.File;
import cn.ibizlab.pms.core.zentao.service.IFileService;
import cn.ibizlab.pms.core.zentao.filter.FileSearchContext;
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
@Component("FileRuntime")
@Slf4j
public class FileRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IFileService fileService;

    @Autowired
    DELogicExecutor logicExecutor;

    @Override
    protected IEntityBase getSimpleEntity(Object o) {
        if (o instanceof net.ibizsys.runtime.util.IEntityBase) {
            return (IEntityBase) o;
        } else {
            return fileService.sysGet((Long) o);
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
    public IEntityBase createEntity() {
        return new File();
    }

    @Override
    protected IService getService() {
        return this.fileService;
    }

    @Override
    public FileSearchContext createSearchContext() {
        return new FileSearchContext();
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
            return MAPPER.readValue(String.valueOf(objData),File.class);
        } catch (Exception e) {
            throw new RuntimeException("反序列化错误");
        }
    }

    @Override
    public IEntityBase[] deserializeEntities(Object objData) {
        try {
            return MAPPER.readValue(String.valueOf(objData),File[].class);
        } catch (Exception e) {
            throw new RuntimeException("反序列化错误");
        }
    }

    @Override
    public boolean existsData(ISearchContextBase iSearchContextBase) {
        Page<File> domains = fileService.searchDefault((FileSearchContext) iSearchContextBase);
        if (domains.getSize() == 0)
            return false;
        return true;
    }

    @Override
    public Page<File> searchDataSet(IPSDEDataSet iPSDEDataSet, ISearchContextBase iSearchContextBase) {
        FileSearchContext searchContext = (FileSearchContext) iSearchContextBase;
        if (iPSDEDataSet.getName().equals("DEFAULT"))
            return fileService.searchDefault(searchContext);    
        if (iPSDEDataSet.getName().equals("DocLibFile"))
            return fileService.searchDocLibFile(searchContext);    
        if (iPSDEDataSet.getName().equals("ProductDocLibFile"))
            return fileService.searchProductDocLibFile(searchContext);    
        if (iPSDEDataSet.getName().equals("Type"))
            return fileService.searchType(searchContext);    
        if (iPSDEDataSet.getName().equals("TypeNotBySrfparentkey"))
            return fileService.searchTypeNotBySrfparentkey(searchContext);    
        return null;
    }

    @Override
    public List<File> selectDataQuery(IPSDEDataQuery iPSDataQuery, ISearchContextBase iSearchContextBase) {
        FileSearchContext searchContext = (FileSearchContext) iSearchContextBase;
        if (iPSDataQuery.getName().equals("DEFAULT"))
            return fileService.selectDefault(searchContext);
        if (iPSDataQuery.getName().equals("DocLibFile"))
            return fileService.selectDocLibFile(searchContext);
        if (iPSDataQuery.getName().equals("ProductDocLibFile"))
            return fileService.selectProductDocLibFile(searchContext);
        if (iPSDataQuery.getName().equals("Type"))
            return fileService.selectType(searchContext);
        if (iPSDataQuery.getName().equals("TypeNotBySrfparentkey"))
            return fileService.selectTypeNotBySrfparentkey(searchContext);
        if (iPSDataQuery.getName().equals("VIEW"))
            return fileService.selectView(searchContext);
        return null;
    }

    @Override
    public File selectOne(ISearchContextBase iSearchContextBase) {
        //单条数据查询，多条数数据时 返回第一条
        FileSearchContext searchContext = (FileSearchContext) iSearchContextBase;
        searchContext.setSize(1);
        List<File> domains = fileService.select(searchContext);
        if (domains.size() == 0)
            return null;
        return domains.get(0);
    }

    @Override
    public List<File> select(ISearchContextBase iSearchContextBase) {
        FileSearchContext searchContext = (FileSearchContext) iSearchContextBase;
        return fileService.select(searchContext);
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
                if(args[0] instanceof File){
                    File arg = (File) args[0] ;
                    arg = fileService.get(arg.getId()) ;
                    return arg;
                }else{
                    return fileService.get((Long) args[0]);
                }
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
            else if (iPSDEAction.getName().equals("updateObjectID")) {
                return fileService.updateObjectID((File) args[0]);
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
                if(args[0] instanceof File){
                    File arg = (File) args[0] ;
                    arg = fileService.get(arg.getId()) ;
                    return arg;
                }else{
                    return fileService.get((Long) args[0]);
                }
            } else if (strActionName.equals(DEActions.REMOVE)) {
                return fileService.remove((Long) args[0]);
            } else if (strActionName.equals(DEActions.SYSGET)) {
                return fileService.sysGet((Long) args[0]);
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
        File entity = null;
        String action = iPSDEAction.getName();
        if (arg0 instanceof File) {
            entity = (File) arg0;
        } else {
            if (arg0 != null && "get".equalsIgnoreCase(action)) {
                entity = fileService.sysGet((Long) arg0);
            } else if ("remove".equalsIgnoreCase(action)) {
                entity = new File();
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

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.FileServiceImpl.*(..))")
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
        else if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        else if (action.equals("updateObjectID")) {
            return aroundAction("updateObjectID", point);
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
