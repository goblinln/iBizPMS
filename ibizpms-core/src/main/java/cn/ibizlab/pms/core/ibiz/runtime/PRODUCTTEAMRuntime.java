package cn.ibizlab.pms.core.ibiz.runtime;

import cn.ibizlab.pms.core.ibiz.domain.PRODUCTTEAM;
import cn.ibizlab.pms.core.ibiz.service.IPRODUCTTEAMService;
import cn.ibizlab.pms.core.ibiz.filter.PRODUCTTEAMSearchContext;
import cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("PRODUCTTEAMRuntime")
public class PRODUCTTEAMRuntime extends cn.ibizlab.pms.core.runtime.SystemDataEntityRuntime {

    @Autowired
    IPRODUCTTEAMService productteamService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new PRODUCTTEAM();
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
    public Object getFieldValue(Object o, IPSDEField ipsdeField) {
        PRODUCTTEAM domain = (PRODUCTTEAM) o;
        try {
            return domain.get(ipsdeField.getCodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public void setFieldValue(Object o, IPSDEField ipsdeField, Object o1) {
        PRODUCTTEAM domain = (PRODUCTTEAM) o;
        try {
            domain.set(ipsdeField.getCodeName(),o1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean containsFieldValue(Object o, IPSDEField ipsdeField) {
        PRODUCTTEAM domain = (PRODUCTTEAM) o;
        try {
            return domain.contains(ipsdeField.getCodeName().toLowerCase());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void resetFieldValue(Object o, IPSDEField ipsdeField) {
        PRODUCTTEAM domain = (PRODUCTTEAM) o;
        try {
            domain.reset(ipsdeField.getCodeName().toLowerCase());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object createEntity() {
        return new PRODUCTTEAM();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    //@Around("execution(* cn.ibizlab.pms.core.ibiz.service.impl.PRODUCTTEAMServiceImpl.*(..))")
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
        if (action.equals("productTeamGuoLv")) {
            return aroundAction("ProductTeamGuoLv", point);
        }
        if (action.equals("save")) {
            return aroundAction("Save", point);
        }

        if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        if (action.equals("searchProductTeamInfo")) {
            return aroundDataSet("ProductTeamInfo", point);
        }
        if (action.equals("searchProjectApp")) {
            return aroundDataSet("ProjectApp", point);
        }
        if (action.equals("searchRowEditDefaultProductTeam")) {
            return aroundDataSet("RowEditDefaultProductTeam", point);
        }
        return point.proceed();
    }

}
