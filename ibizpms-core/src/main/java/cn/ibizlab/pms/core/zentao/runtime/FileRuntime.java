package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.File;
import cn.ibizlab.pms.core.zentao.service.IFileService;
import cn.ibizlab.pms.core.zentao.filter.FileSearchContext;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import cn.ibizlab.pms.core.runtime.DataEntityRuntime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("FileRuntime")
public class FileRuntime extends DataEntityRuntime {

    @Autowired
    IFileService fileService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new File();
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
    public Object getFieldValue(Object o, IPSDEField ipsdeField) {
        File domain = (File)o;
        try {
            return domain.get(ipsdeField.getCodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public void setFieldValue(Object o, IPSDEField ipsdeField, Object o1) {
        File domain = (File)o;
        try {
            domain.set(ipsdeField.getCodeName(),o1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean containsFieldValue(Object o, IPSDEField ipsdeField) {
        return false;
    }

    @Override
    public Object createEntity() {
        return new File();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.FileServiceImpl.*(..))")
    public Object aroundMethod(ProceedingJoinPoint point) throws Throwable {
        String action = point.getSignature().getName();
        if (action.equals("create")) {
            return aroundAction("Create", point);
        }
        if (action.equals("update")) {
            return aroundAction("Update", point);
        }
        if (action.equals("remove")) {
            return aroundAction("Remove", point);
        }
        if (action.equals("get")) {
            return aroundAction("Get", point);
        }
        if (action.equals("getDraft")) {
            return aroundAction("GetDraft", point);
        }
        if (action.equals("checkKey")) {
            return aroundAction("CheckKey", point);
        }
        if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        if (action.equals("updateObjectIDForPmsEe")) {
            return aroundAction("updateObjectIDForPmsEe", point);
        }

        if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        if (action.equals("searchDocLibFile")) {
            return aroundDataSet("DocLibFile", point);
        }
        if (action.equals("searchProductDocLibFile")) {
            return aroundDataSet("ProductDocLibFile", point);
        }
        if (action.equals("searchType")) {
            return aroundDataSet("Type", point);
        }
        if (action.equals("searchTypeNotBySrfparentkey")) {
            return aroundDataSet("TypeNotBySrfparentkey", point);
        }
        return point.proceed();
    }

}
