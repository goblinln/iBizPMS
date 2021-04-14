package cn.ibizlab.pms.util.aspect;

import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.util.domain.EntityBase;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import java.util.List;

/**
 * es同步数据切面
 */
@Aspect
@Component
@Slf4j
public class ESAspect
{

    private final ExpressionParser parser = new SpelExpressionParser();

    /**
     * 实体[IbizproIndex]es切面
     * @param point
     */
    @AfterReturning(value = "(execution(* cn.ibizlab.pms.core.*.service.*IbizproIndex*.create*(..))||execution(* cn.ibizlab.pms.core.*.service.*IbizproIndex*.update*(..))||execution(* cn.ibizlab.pms.core.*.service.*IbizproIndex*.save*(..)) ||execution(* cn.ibizlab.pms.core.*.service.*IbizproIndex*.remove*(..))) && !execution(* cn.ibizlab.pms.core.es.service.*.create*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.update*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.save*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.remove*(..))")
    @Async
    public void SyncIbizproindex(JoinPoint point) {
        syncSaveESData(point,"IbizproIndex");
    }

    /**
     * 异步往es中保存数据
     * @param point
     */
    public void syncSaveESData(JoinPoint point, String deName) {
        try {
            Object service=point.getTarget();
            String action=point.getSignature().getName();
            Object [] args = point.getArgs();
            if(ObjectUtils.isEmpty(args) || args.length==0 || StringUtils.isEmpty(action)) {
                return;
            }

            EvaluationContext exServiceCtx = new StandardEvaluationContext();
            exServiceCtx.setVariable("service", service);
            Expression esServiceExp = parser.parseExpression("#service.getESService()");
            Object exService=esServiceExp.getValue(exServiceCtx);
            if(ObjectUtils.isEmpty(exService)) {
                log.error("获取[{}]实体全文检索服务对象失败",deName);
                return;
            }
            Object arg=args[0];
            if ("remove".equals(action) || "removeBatch".equals(action)) {
                executeESMethod(exService, action, arg);
            }
            else if(arg instanceof EntityBase  || arg instanceof List) {
                EvaluationContext exMappingCtx = new StandardEvaluationContext();
                exMappingCtx.setVariable("service", service);
                Expression esMappingExp = parser.parseExpression("#service.getESMapping()");
                Object exMapping=esMappingExp.getValue(exMappingCtx);
                if(ObjectUtils.isEmpty(exMapping)) {
                    log.error("获取[{}]实体全文检索映射对象失败", deName);
                    return;
                }
                EvaluationContext exDomainCtx = new StandardEvaluationContext();
                exDomainCtx.setVariable("mapping", exMapping);
                exDomainCtx.setVariable("arg", arg);
                Expression esDomainExp = parser.parseExpression("#mapping.toESDomain(#arg)");
                arg=esDomainExp.getValue(exDomainCtx);
                executeESMethod(exService, action, arg);
            }
        } catch (Exception e) {
            log.error("同步[{}]实体全文检索数据失败，{}", deName, e);
        }
    }

    /**
     * 执行es方法
     * @param exService
     * @param action
     * @param arg
     */
    private void executeESMethod(Object exService, Object action, Object arg) {
        EvaluationContext esContext = new StandardEvaluationContext();
        esContext.setVariable("exService", exService);
        esContext.setVariable("arg", arg);
        Expression exExp = parser.parseExpression(String.format("#exService.%s(#arg)", action));
        exExp.getValue(esContext);
    }
}
