package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.ibiz.domain.IbzMyTerritory;
import cn.ibizlab.pms.core.ibiz.service.IIbzMyTerritoryService;
import cn.ibizlab.pms.core.ibiz.filter.IbzMyTerritorySearchContext;
import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("IbzMyTerritoryRuntime")
public class IbzMyTerritoryRuntime extends SystemDataEntityRuntime {

    @Autowired
    IIbzMyTerritoryService ibzmyterritoryService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new IbzMyTerritory();
    }

    @Override
    public String getId() {
        return "PSMODULES/ibiz/PSDATAENTITIES/IbzMyTerritory.json";
    }

    @Override
    public String getName() {
        return "IBZ_MYTERRITORY";
    }

    @Override
    public Object getFieldValue(Object o, IPSDEField ipsdeField) {
        IbzMyTerritory domain = (IbzMyTerritory)o;
        try {
            return domain.get(ipsdeField.getCodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public void setFieldValue(Object o, IPSDEField ipsdeField, Object o1) {
        IbzMyTerritory domain = (IbzMyTerritory)o;
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
        return new IbzMyTerritory();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    @Around("execution(* cn.ibizlab.pms.core.ibiz.service.impl.IbzMyTerritoryServiceImpl.*(..))")
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
        if (action.equals("searchMyWork")) {
            return aroundDataSet("MyWork", point);
        }
        if (action.equals("searchMyWorkMob")) {
            return aroundDataSet("MyWorkMob", point);
        }
        if (action.equals("searchMyWorkPm")) {
            return aroundDataSet("MyWorkPm", point);
        }
        if (action.equals("searchPersonInfo")) {
            return aroundDataSet("PersonInfo", point);
        }
        if (action.equals("searchWelcome")) {
            return aroundDataSet("welcome", point);
        }
        return point.proceed();
    }

}
