package cn.ibizlab.pms.util.aspect;

import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.util.annotation.DEField;
import cn.ibizlab.pms.util.enums.DupCheck;
import cn.ibizlab.pms.util.errors.BadRequestAlertException;
import cn.ibizlab.pms.util.filter.QueryFilter;
import cn.ibizlab.pms.util.filter.SearchContextBase;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.domain.Page;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import java.util.Map;

/**
 * 属性重复值检查切面
 */
@Aspect
@Component
@Slf4j
public class DupCheckAspect {

    private final ExpressionParser parser = new SpelExpressionParser();

     /**
     * 实体[Action]
     *
     * @param point
     */
    @AfterReturning(value = "(execution(* cn.ibizlab.pms.core.*.service.*Action*.create*(..))||execution(* cn.ibizlab.pms.core.*.service.*Action*.update*(..))||execution(* cn.ibizlab.pms.core.*.service.*Action*.save*(..))  ) && !execution(* cn.ibizlab.pms.core.es.service.*.create*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.update*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.save*(..)) ")
    public void checkAction(JoinPoint point) {
        check(point, "searchDefault");
    }
     /**
     * 实体[Bug]
     *
     * @param point
     */
    @AfterReturning(value = "(execution(* cn.ibizlab.pms.core.*.service.*Bug*.create*(..))||execution(* cn.ibizlab.pms.core.*.service.*Bug*.update*(..))||execution(* cn.ibizlab.pms.core.*.service.*Bug*.save*(..))  ) && !execution(* cn.ibizlab.pms.core.es.service.*.create*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.update*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.save*(..)) ")
    public void checkBug(JoinPoint point) {
        check(point, "searchDefault");
    }
     /**
     * 实体[IBZCaseAction]
     *
     * @param point
     */
    @AfterReturning(value = "(execution(* cn.ibizlab.pms.core.*.service.*IBZCaseAction*.create*(..))||execution(* cn.ibizlab.pms.core.*.service.*IBZCaseAction*.update*(..))||execution(* cn.ibizlab.pms.core.*.service.*IBZCaseAction*.save*(..))  ) && !execution(* cn.ibizlab.pms.core.es.service.*.create*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.update*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.save*(..)) ")
    public void checkIbzcaseaction(JoinPoint point) {
        check(point, "searchDefault");
    }
     /**
     * 实体[IBZDailyAction]
     *
     * @param point
     */
    @AfterReturning(value = "(execution(* cn.ibizlab.pms.core.*.service.*IBZDailyAction*.create*(..))||execution(* cn.ibizlab.pms.core.*.service.*IBZDailyAction*.update*(..))||execution(* cn.ibizlab.pms.core.*.service.*IBZDailyAction*.save*(..))  ) && !execution(* cn.ibizlab.pms.core.es.service.*.create*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.update*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.save*(..)) ")
    public void checkIbzdailyaction(JoinPoint point) {
        check(point, "searchDefault");
    }
     /**
     * 实体[IBZProProductAction]
     *
     * @param point
     */
    @AfterReturning(value = "(execution(* cn.ibizlab.pms.core.*.service.*IBZProProductAction*.create*(..))||execution(* cn.ibizlab.pms.core.*.service.*IBZProProductAction*.update*(..))||execution(* cn.ibizlab.pms.core.*.service.*IBZProProductAction*.save*(..))  ) && !execution(* cn.ibizlab.pms.core.es.service.*.create*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.update*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.save*(..)) ")
    public void checkIbzproproductaction(JoinPoint point) {
        check(point, "searchDefault");
    }
     /**
     * 实体[IBZProProjectAction]
     *
     * @param point
     */
    @AfterReturning(value = "(execution(* cn.ibizlab.pms.core.*.service.*IBZProProjectAction*.create*(..))||execution(* cn.ibizlab.pms.core.*.service.*IBZProProjectAction*.update*(..))||execution(* cn.ibizlab.pms.core.*.service.*IBZProProjectAction*.save*(..))  ) && !execution(* cn.ibizlab.pms.core.es.service.*.create*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.update*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.save*(..)) ")
    public void checkIbzproprojectaction(JoinPoint point) {
        check(point, "searchDefault");
    }
     /**
     * 实体[IBZProReleaseAction]
     *
     * @param point
     */
    @AfterReturning(value = "(execution(* cn.ibizlab.pms.core.*.service.*IBZProReleaseAction*.create*(..))||execution(* cn.ibizlab.pms.core.*.service.*IBZProReleaseAction*.update*(..))||execution(* cn.ibizlab.pms.core.*.service.*IBZProReleaseAction*.save*(..))  ) && !execution(* cn.ibizlab.pms.core.es.service.*.create*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.update*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.save*(..)) ")
    public void checkIbzproreleaseaction(JoinPoint point) {
        check(point, "searchDefault");
    }
     /**
     * 实体[IBZProWeeklyAction]
     *
     * @param point
     */
    @AfterReturning(value = "(execution(* cn.ibizlab.pms.core.*.service.*IBZProWeeklyAction*.create*(..))||execution(* cn.ibizlab.pms.core.*.service.*IBZProWeeklyAction*.update*(..))||execution(* cn.ibizlab.pms.core.*.service.*IBZProWeeklyAction*.save*(..))  ) && !execution(* cn.ibizlab.pms.core.es.service.*.create*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.update*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.save*(..)) ")
    public void checkIbzproweeklyaction(JoinPoint point) {
        check(point, "searchDefault");
    }
     /**
     * 实体[IBZStoryAction]
     *
     * @param point
     */
    @AfterReturning(value = "(execution(* cn.ibizlab.pms.core.*.service.*IBZStoryAction*.create*(..))||execution(* cn.ibizlab.pms.core.*.service.*IBZStoryAction*.update*(..))||execution(* cn.ibizlab.pms.core.*.service.*IBZStoryAction*.save*(..))  ) && !execution(* cn.ibizlab.pms.core.es.service.*.create*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.update*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.save*(..)) ")
    public void checkIbzstoryaction(JoinPoint point) {
        check(point, "searchDefault");
    }
     /**
     * 实体[IBZTaskAction]
     *
     * @param point
     */
    @AfterReturning(value = "(execution(* cn.ibizlab.pms.core.*.service.*IBZTaskAction*.create*(..))||execution(* cn.ibizlab.pms.core.*.service.*IBZTaskAction*.update*(..))||execution(* cn.ibizlab.pms.core.*.service.*IBZTaskAction*.save*(..))  ) && !execution(* cn.ibizlab.pms.core.es.service.*.create*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.update*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.save*(..)) ")
    public void checkIbztaskaction(JoinPoint point) {
        check(point, "searchDefault");
    }
     /**
     * 实体[IBZTestReportAction]
     *
     * @param point
     */
    @AfterReturning(value = "(execution(* cn.ibizlab.pms.core.*.service.*IBZTestReportAction*.create*(..))||execution(* cn.ibizlab.pms.core.*.service.*IBZTestReportAction*.update*(..))||execution(* cn.ibizlab.pms.core.*.service.*IBZTestReportAction*.save*(..))  ) && !execution(* cn.ibizlab.pms.core.es.service.*.create*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.update*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.save*(..)) ")
    public void checkIbztestreportaction(JoinPoint point) {
        check(point, "searchDefault");
    }
     /**
     * 实体[IBZTestSuiteAction]
     *
     * @param point
     */
    @AfterReturning(value = "(execution(* cn.ibizlab.pms.core.*.service.*IBZTestSuiteAction*.create*(..))||execution(* cn.ibizlab.pms.core.*.service.*IBZTestSuiteAction*.update*(..))||execution(* cn.ibizlab.pms.core.*.service.*IBZTestSuiteAction*.save*(..))  ) && !execution(* cn.ibizlab.pms.core.es.service.*.create*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.update*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.save*(..)) ")
    public void checkIbztestsuiteaction(JoinPoint point) {
        check(point, "searchDefault");
    }
     /**
     * 实体[IbzProBugAction]
     *
     * @param point
     */
    @AfterReturning(value = "(execution(* cn.ibizlab.pms.core.*.service.*IbzProBugAction*.create*(..))||execution(* cn.ibizlab.pms.core.*.service.*IbzProBugAction*.update*(..))||execution(* cn.ibizlab.pms.core.*.service.*IbzProBugAction*.save*(..))  ) && !execution(* cn.ibizlab.pms.core.es.service.*.create*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.update*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.save*(..)) ")
    public void checkIbzprobugaction(JoinPoint point) {
        check(point, "searchDefault");
    }
     /**
     * 实体[IbzProBuildAction]
     *
     * @param point
     */
    @AfterReturning(value = "(execution(* cn.ibizlab.pms.core.*.service.*IbzProBuildAction*.create*(..))||execution(* cn.ibizlab.pms.core.*.service.*IbzProBuildAction*.update*(..))||execution(* cn.ibizlab.pms.core.*.service.*IbzProBuildAction*.save*(..))  ) && !execution(* cn.ibizlab.pms.core.es.service.*.create*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.update*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.save*(..)) ")
    public void checkIbzprobuildaction(JoinPoint point) {
        check(point, "searchDefault");
    }
     /**
     * 实体[IbzProMonthlyAction]
     *
     * @param point
     */
    @AfterReturning(value = "(execution(* cn.ibizlab.pms.core.*.service.*IbzProMonthlyAction*.create*(..))||execution(* cn.ibizlab.pms.core.*.service.*IbzProMonthlyAction*.update*(..))||execution(* cn.ibizlab.pms.core.*.service.*IbzProMonthlyAction*.save*(..))  ) && !execution(* cn.ibizlab.pms.core.es.service.*.create*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.update*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.save*(..)) ")
    public void checkIbzpromonthlyaction(JoinPoint point) {
        check(point, "searchDefault");
    }
     /**
     * 实体[IbzProReportlyAction]
     *
     * @param point
     */
    @AfterReturning(value = "(execution(* cn.ibizlab.pms.core.*.service.*IbzProReportlyAction*.create*(..))||execution(* cn.ibizlab.pms.core.*.service.*IbzProReportlyAction*.update*(..))||execution(* cn.ibizlab.pms.core.*.service.*IbzProReportlyAction*.save*(..))  ) && !execution(* cn.ibizlab.pms.core.es.service.*.create*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.update*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.save*(..)) ")
    public void checkIbzproreportlyaction(JoinPoint point) {
        check(point, "searchDefault");
    }
     /**
     * 实体[IbzProTestTaskAction]
     *
     * @param point
     */
    @AfterReturning(value = "(execution(* cn.ibizlab.pms.core.*.service.*IbzProTestTaskAction*.create*(..))||execution(* cn.ibizlab.pms.core.*.service.*IbzProTestTaskAction*.update*(..))||execution(* cn.ibizlab.pms.core.*.service.*IbzProTestTaskAction*.save*(..))  ) && !execution(* cn.ibizlab.pms.core.es.service.*.create*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.update*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.save*(..)) ")
    public void checkIbzprotesttaskaction(JoinPoint point) {
        check(point, "searchDefault");
    }
     /**
     * 实体[ProductPlanAction]
     *
     * @param point
     */
    @AfterReturning(value = "(execution(* cn.ibizlab.pms.core.*.service.*ProductPlanAction*.create*(..))||execution(* cn.ibizlab.pms.core.*.service.*ProductPlanAction*.update*(..))||execution(* cn.ibizlab.pms.core.*.service.*ProductPlanAction*.save*(..))  ) && !execution(* cn.ibizlab.pms.core.es.service.*.create*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.update*(..)) && !execution(* cn.ibizlab.pms.core.es.service.*.save*(..)) ")
    public void checkProductplanaction(JoinPoint point) {
        check(point, "searchDefault");
    }

    /**
     * 实体属性重复值检查
     * @param point 切点
     * @param defaultDS 实体默认数据集名称
     */
    private void check(JoinPoint point, String defaultDS) {
        Object[] args = point.getArgs();
        if (args.length > 0) {
            Object entity = args[0];
            Object service = point.getTarget();
            Map<String, DEField> deFields = DEFieldCacheMap.getDEFields(entity.getClass());
            for (Map.Entry<String, DEField> deField : deFields.entrySet()) {
                String fieldName = deField.getKey();
                DEField fieldAnnotation = deField.getValue();
                DupCheck dupCheck = fieldAnnotation.dupCheck();
                String dupCheckField=fieldAnnotation.dupCheckField();
                if (dupCheck == DupCheck.ALL) {
                    Object newValue =getDEFieldValue(entity,fieldName);
                    //获取searchContext
                    EvaluationContext searchContextCtx = new StandardEvaluationContext();
                    searchContextCtx.setVariable("service", service);
                    Expression searchContextExp = parser.parseExpression("#service.getSearchContext()");
                    SearchContextBase searchContext = searchContextExp.getValue(searchContextCtx, SearchContextBase.class);

                    //设置检查属性值
                    QueryFilter filter = new QueryFilter();
                    setValue(entity, filter, fieldName, newValue);

                    //设定重复值检查范围
                    if(!StringUtils.isEmpty(dupCheckField)) {
                        Object dupFieldValue=getDEFieldValue(entity,dupCheckField);
                        setValue(entity, filter, dupCheckField, dupFieldValue);
                    }
                    searchContext.setFilter(filter);
                    //使用当前值到数据库中进行查询，判断是否重复
                    EvaluationContext oldValueMappingCtx = new StandardEvaluationContext();
                    oldValueMappingCtx.setVariable("service", service);
                    oldValueMappingCtx.setVariable("searchContext", searchContext);
                    Expression oldValueMappingExp = parser.parseExpression(String.format("#service.%s(#searchContext)", defaultDS));
                    Page oldData = oldValueMappingExp.getValue(oldValueMappingCtx, Page.class);
                    if (!ObjectUtils.isEmpty(oldData) && !ObjectUtils.isEmpty(oldData.getContent()) && oldData.getContent().size() > 1) {
                        throw new BadRequestAlertException(String.format("数据保存失败，属性[%s]:值[%s]已存在!", fieldName, newValue), "DupCheckAspect", "DupCheck");
                    }
                }
            }
        }
    }

    /**
     * 获取实体属性值
     * @param entity
     * @param fieldName
     * @return
     */
    private Object getDEFieldValue(Object entity, String fieldName) {
        EvaluationContext exMappingCtx = new StandardEvaluationContext();
        exMappingCtx.setVariable("entity", entity);
        Expression esMappingExp = parser.parseExpression(String.format("#entity.get(\"%s\")", fieldName));
        return esMappingExp.getValue(exMappingCtx);
    }

    /**
     * 设置filter
     * @param entity
     * @param filter
     * @param value
     */
    private void setValue(Object entity , QueryFilter filter, String fieldName, Object value){
        if(ObjectUtils.isEmpty(value)) {
            filter.isnull(DEFieldCacheMap.getFieldColumnName(entity.getClass(), fieldName));
        }
        else {
            filter.eq(DEFieldCacheMap.getFieldColumnName(entity.getClass(), fieldName), value);
        }
    }
}

