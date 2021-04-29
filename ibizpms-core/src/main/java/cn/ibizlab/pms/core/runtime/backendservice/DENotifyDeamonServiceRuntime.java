package cn.ibizlab.pms.core.runtime.backendservice;

import cn.ibizlab.pms.util.security.AuthenticationUser;
import net.ibizsys.runtime.ISystemRuntime;
import net.ibizsys.runtime.security.IUserContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Order(100)
@Component("DENotifyDeamonServiceRuntime")
public class DENotifyDeamonServiceRuntime extends net.ibizsys.runtime.backend.SysDENotifyBackendTaskRuntimeBase {

    @Autowired
    ISystemRuntime systemRuntime;

    @Override
    public String getId() {
        return "PSSYSBACKSERVICES/DENotifyDeamon.json";
    }

    @Override
    public String getName() {
        return "实体通知定时调度";
    }

    @Override
    @Around("execution(* cn.ibizlab.pms.core.util.job.DENotifyDeamonJobHandler.execute(..))")
    public Object aroundHandler(ProceedingJoinPoint joinPoint) throws Throwable {
        return super.aroundHandler(joinPoint);
    }

    @Override
    public ISystemRuntime getSystemRuntime() {
        return systemRuntime;
    }

    /**
     * 构造系统用户上下文
     * @return
     */
    protected IUserContext getUserContext() {
        try {
            if (SecurityContextHolder.getContext() == null || SecurityContextHolder.getContext().getAuthentication() == null || SecurityContextHolder.getContext().getAuthentication().getPrincipal() == null) {
                AuthenticationUser authuserdetail = new AuthenticationUser();
                this.getPSSysBackService().getCodeName();
                String strDeploySystemId = this.getSystemRuntime().getDeploySystemId();
                authuserdetail.setUserid("SYSTEM");
                authuserdetail.setUsername("SYSTEM");
                authuserdetail.setPersonname("SYSTEM");
                authuserdetail.setSuperuser(1);
                authuserdetail.setSrfdcid("aibizhi");
                return authuserdetail;
            }
            Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            AuthenticationUser authuserdetail;
            if (userDetails instanceof AuthenticationUser) {
                authuserdetail = (AuthenticationUser) userDetails;
            } else {
                authuserdetail = new AuthenticationUser();
            }
            return authuserdetail;
        } catch (Exception e) {
            return null;
        }
    }

}
