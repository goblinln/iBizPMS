package cn.ibizlab.pms.core.runtime.backendservice;

import cn.ibizlab.pms.util.security.AuthenticationUser;
import net.ibizsys.runtime.ISystemRuntime;
import net.ibizsys.runtime.security.IUserContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Order(100)
@Component("CreateProductDailyServiceServiceRuntime")
public class CreateProductDailyServiceServiceRuntime extends net.ibizsys.runtime.backend.SysDEActionBackendTaskRuntimeBase {

    @Autowired
    ISystemRuntime systemRuntime;

    @Override
    public String getId() {
        return "PSMODULES/ibizpro/PSSYSBACKSERVICES/CreateProductDailyService.json";
    }

    @Override
    public String getName() {
        return "定时生成产品日报";
    }

    @Override
    @Around("execution(* cn.ibizlab.pms.core.util.job.CreateProductDailyServiceJobHandler.execute(..))")
    public Object aroundHandler(ProceedingJoinPoint joinPoint) throws Throwable {
        return super.aroundHandler(joinPoint);
    }

    @Override
    protected Object execute(String strDynaInstId, String strParam, ProceedingJoinPoint joinPoint) throws Throwable {
        String[] arr = strDynaInstId.split("\\|");
        String strRealDynaInstId = null;
        if (arr.length == 2) {
            strRealDynaInstId = arr[1];
        }
        createUserContext(arr[0]);
        return super.execute(strRealDynaInstId, strParam, joinPoint);
    }

    @Override
    public ISystemRuntime getSystemRuntime() {
        return systemRuntime;
    }

    /**
     * 构造系统用户上下文
     *
     * @return
     */
    protected IUserContext getUserContext() {
        return AuthenticationUser.getAuthenticationUser();
    }

    /**
     * @param strDCId
     * @throws Exception
     */
    private void createUserContext(String strDCId) throws Exception {
        AuthenticationUser authuserdetail = new AuthenticationUser();
        this.getPSSysBackService().getCodeName();
        authuserdetail.setUserid("SYSTEM");
        authuserdetail.setUsername("SYSTEM");
        authuserdetail.setPersonname("SYSTEM");
        authuserdetail.setSuperuser(1);
        authuserdetail.setSrfdcid(strDCId);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(authuserdetail, null, authuserdetail.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
