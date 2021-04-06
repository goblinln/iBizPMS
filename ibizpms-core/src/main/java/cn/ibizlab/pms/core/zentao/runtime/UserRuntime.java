package cn.ibizlab.pms.core.zentao.runtime;

import cn.ibizlab.pms.core.zentao.domain.User;
import cn.ibizlab.pms.core.zentao.service.IUserService;
import cn.ibizlab.pms.core.zentao.filter.UserSearchContext;
import net.ibizsys.model.dataentity.action.IPSDEAction;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import org.springframework.stereotype.Component;
import cn.ibizlab.pms.core.runtime.DataEntityRuntime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
@Component("UserRuntime")
public class UserRuntime extends DataEntityRuntime {

    @Autowired
    IUserService userService;

    @Override
    protected Object getSimpleEntity(Object o) {
        return new User();
    }

    @Override
    public String getId() {
        return "PSMODULES/zentao/PSDATAENTITIES/User.json";
    }

    @Override
    public String getName() {
        return "ZT_USER";
    }

    @Override
    public Object getFieldValue(Object o, IPSDEField ipsdeField) {
        User domain = (User)o;
        try {
            return domain.get(ipsdeField.getCodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }

    @Override
    public void setFieldValue(Object o, IPSDEField ipsdeField, Object o1) {
        User domain = (User)o;
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
        return new User();
    }

    @Override
    public Object executeAction(IPSDEAction ipsdeAction, Object[] objects) throws Throwable {
        return null;
    }

    @Around("execution(* cn.ibizlab.pms.core.zentao.service.impl.UserServiceImpl.*(..))")
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
        if (action.equals("getByCommiter")) {
            return aroundAction("getByCommiter", point);
        }
        if (action.equals("save")) {
            return aroundAction("Save", point);
        }
        if (action.equals("syncAccount")) {
            return aroundAction("SyncAccount", point);
        }

        if (action.equals("searchBugUser")) {
            return aroundDataSet("BugUser", point);
        }
        if (action.equals("searchDefault")) {
            return aroundDataSet("DEFAULT", point);
        }
        if (action.equals("searchGetByCommiter")) {
            return aroundDataSet("getByCommiter", point);
        }
        if (action.equals("searchProjectTeamM")) {
            return aroundDataSet("ProjectTeamM", point);
        }
        if (action.equals("searchProjectTeamUser")) {
            return aroundDataSet("ProjectTeamUser", point);
        }
        if (action.equals("searchProjectTeamUserTask")) {
            return aroundDataSet("ProjectTeamUserTask", point);
        }
        if (action.equals("searchTaskTeam")) {
            return aroundDataSet("TASKTEAM", point);
        }
        return point.proceed();
    }

}
