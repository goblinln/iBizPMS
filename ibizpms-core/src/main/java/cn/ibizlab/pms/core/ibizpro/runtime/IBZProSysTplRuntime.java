package cn.ibizlab.pms.core.ibizpro.runtime;

import cn.ibizlab.pms.core.ibizpro.domain.IBZProSysTpl;
import cn.ibizlab.pms.core.ibizpro.service.IIBZProSysTplService;
import cn.ibizlab.pms.core.ibizpro.filter.IBZProSysTplSearchContext;
import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("IBZProSysTplRuntime")
public class IBZProSysTplRuntime extends SystemDataEntityRuntime {

    @Autowired
    IIBZProSysTplService ibzprosystplService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new IBZProSysTpl();
    }

    @Override
    public String getId() {
        return "PSMODULES/ibizpro/PSDATAENTITIES/IBZProSysTpl.json";
    }

    @Override
    public String getName() {
        return "IBZPRO_SYSTPL";
    }

    @Override
    public Object getFieldValue(Object o, IPSDEField ipsdeField) {
        IBZProSysTpl domain = (IBZProSysTpl)o;
        try {
            return domain.get(ipsdeField.getCodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public void setFieldValue(Object o, IPSDEField ipsdeField, Object o1) {
        IBZProSysTpl domain = (IBZProSysTpl)o;
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
    public void resetFieldValue(Object o, IPSDEField ipsdeField) {
        
    }

    @Override
    public Object createEntity() {
        return new IBZProSysTpl();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    @Around("execution(* cn.ibizlab.pms.core.ibizpro.service.impl.IBZProSysTplServiceImpl.*(..))")
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
        return point.proceed();
    }

}
