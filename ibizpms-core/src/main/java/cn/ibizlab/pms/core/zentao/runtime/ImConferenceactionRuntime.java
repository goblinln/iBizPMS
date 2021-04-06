package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.ImConferenceaction;
import cn.ibizlab.pms.core.zentao.service.IImConferenceactionService;
import cn.ibizlab.pms.core.zentao.filter.ImConferenceactionSearchContext;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import cn.ibizlab.pms.core.runtime.DataEntityRuntime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("ImConferenceactionRuntime")
public class ImConferenceactionRuntime extends DataEntityRuntime {

    @Autowired
    IImConferenceactionService imconferenceactionService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new ImConferenceaction();
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/ImConferenceaction.json";
    }

    @Override
    public String getName() {
        return "ZT_IM_CONFERENCEACTION";
    }

    @Override
    public Object getFieldValue(Object o, IPSDEField ipsdeField) {
        ImConferenceaction domain = (ImConferenceaction)o;
        try {
            return domain.get(ipsdeField.getCodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public void setFieldValue(Object o, IPSDEField ipsdeField, Object o1) {
        ImConferenceaction domain = (ImConferenceaction)o;
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
        return new ImConferenceaction();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.ImConferenceactionServiceImpl.*(..))")
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

    //
        if (action.equals("searchDefault")) {
            return aroundAction("DEFAULT", point);
        }
        return point.proceed();
    }

}
