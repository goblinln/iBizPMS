package cn.ibizlab.pms.util.log;

import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

import static cn.ibizlab.pms.util.log.LogMessage.*;

/**
 * 系统全局日志切面类
 * 对标记为@IBIZTraceLog的类、方法生成系统日志。
 * ps：不支持类内部互相调用场景。
 *
 * @author nancy
 * @date 2020-3-20
 */
@Aspect
@Component
@ConditionalOnExpression("${pms.trace:false}")
public class LogAspect {

    //切入标记为@IBIZLog的类及非私有方法，适用于类、方法。
    @Around("@annotation(cn.ibizlab.pms.util.log.IBIZTraceLog) || @within(cn.ibizlab.pms.util.log.IBIZTraceLog)")
    public Object aroundLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //设置日志位置，aop类-->切入点位置
//        String className = joinPoint.getSignature().getDeclaringTypeName();
        Class<?> aopClass = joinPoint.getSignature().getDeclaringType();
        Logger log = LoggerFactory.getLogger(aopClass);

        //设置日志中执行的方法签名：类名.方法名（参数类型1，参数类型2...)
        String methodSignature = getMethodSignature(joinPoint);
        String methodName = getMethod(joinPoint);

        //监控返回值、参数、执行时间
        Object[] params = joinPoint.getArgs();
        Object returnedValue = null;
        long start = System.currentTimeMillis();
        MDC.put("method",methodName);
        MDC.put("loginname", AuthenticationUser.getAuthenticationUser()==null?"---": AuthenticationUser.getAuthenticationUser().getUsername());

        log.info(FUNCTION_START.getMsg(),methodSignature);
        log.debug(CURRENTTIMEMILLIS.getMsg(),System.currentTimeMillis());
        log.debug(PARAMS.getMsg(), getStr(params));

        //0代表正常结束，1抛出了异常，2准正常结束
        int status = 0;
        String errormsg = null;
        try {
            returnedValue = joinPoint.proceed();
            return returnedValue;
        } catch (Throwable throwable) {
            errormsg = throwable.getMessage();
            if (throwable instanceof RuntimeException) {
                status = 1;
                throwable.printStackTrace();
            } else {
                status = 2;
            }
            throw throwable;

        } finally {
            //形成本地日志信息
            String returnedStr = null;
            try {
                returnedStr = JSON.toJSONString(returnedValue);
            } catch (Exception e) {
                returnedStr = returnedValue + "";
            }



            long timeCost = System.currentTimeMillis() - start;
            switch (status) {
                case 0:
                    log.info(FUNCTION_END.getMsg(),methodSignature);
                    log.debug(RETURNED_VALUE.getMsg()+","+TIME_COST.getMsg(),returnedStr, timeCost);
                    break;
                case 1:
                    log.error(FUCNTION_ERROR_END.getMsg(), methodSignature,errormsg);
                    break;
                case 2:
                    log.info(FUNCTION_EXCEPTION_END.getMsg(),methodSignature);
                    log.debug(EXCEPTION_MSG.getMsg()+","+TIME_COST.getMsg(), errormsg, timeCost);
                    break;
                default:

            }
            MDC.clear();
        }
    }
    private String getMethod(ProceedingJoinPoint joinPoint) {
        Signature s = joinPoint.getSignature();
        joinPoint.getSignature().getDeclaringTypeName();
        MethodSignature ms = (MethodSignature) s;
        Method m = ms.getMethod();
        return m.getName();
    }
    private String getMethodSignature(ProceedingJoinPoint joinPoint){
//        String methodSignature = joinPoint.getSignature().toShortString();
//        Signature s = joinPoint.getSignature();
//        MethodSignature ms = (MethodSignature)s;
//        Method m = ms.getMethod();
//        Class[] types = m.getParameterTypes();
//        String[] paramtypes = new String[types.length];
//        for(int i=0;i<types.length;i++){
//            paramtypes[i] = types[i].getSimpleName();
//        }
//        return methodSignature.replace("..", String.join(",", paramtypes));
        String methodSignature = joinPoint.getSignature().toLongString();
        return methodSignature;
    }


}
