package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.ibiz.domain.EmpLoyeeload;
import cn.ibizlab.pms.core.ibiz.service.IEmpLoyeeloadService;
import cn.ibizlab.pms.core.ibiz.filter.EmpLoyeeloadSearchContext;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import cn.ibizlab.pms.core.runtime.DataEntityRuntime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("EmpLoyeeloadRuntime")
public class EmpLoyeeloadRuntime extends DataEntityRuntime {

    @Autowired
    IEmpLoyeeloadService employeeloadService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new EmpLoyeeload();
    }

    @Override
    public String getId() {
        return "PSMODULES/ibiz/PSDATAENTITIES/EmpLoyeeload.json";
    }

    @Override
    public String getName() {
        return "IBZ_EMPLOYEELOAD";
    }

    @Override
    public Object getFieldValue(Object o, IPSDEField ipsdeField) {
        EmpLoyeeload domain = (EmpLoyeeload)o;
        try {
            return domain.get(ipsdeField.getCodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public void setFieldValue(Object o, IPSDEField ipsdeField, Object o1) {
        EmpLoyeeload domain = (EmpLoyeeload)o;
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
        return new EmpLoyeeload();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    @Around("execution(* cn.ibizlab.pms.core.ibiz.service.impl.EmpLoyeeloadServiceImpl.*(..))")
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

        if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        if (action.equals("searchGETWOERKLOAD")) {
            return aroundDataSet("GETWOERKLOAD", point);
        }
        return point.proceed();
    }

}
