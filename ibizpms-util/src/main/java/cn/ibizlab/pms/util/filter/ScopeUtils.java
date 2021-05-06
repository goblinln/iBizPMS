package cn.ibizlab.pms.util.filter;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import net.ibizsys.model.dataentity.defield.IPSDEField;
import net.ibizsys.runtime.dataentity.DataEntityRuntimeException;
import net.ibizsys.runtime.dataentity.IDataEntityRuntime;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScopeUtils {

    @JsonIgnore
    @JSONField(serialize = false)
    private static
    final ExpressionParser parser = new SpelExpressionParser();

    public static String OPERATION_AND = "'&'";
    public static String OPERATION_OR = "'|'";

    public static String TERM_OPERATOR_EQ = "'='";
    public static String TERM_OPERATOR_NE = "'!='";
    public static String TERM_OPERATOR_GT = "'>'";
    public static String TERM_OPERATOR_GE = "'>='";
    public static String TERM_OPERATOR_LT = "'<'";
    public static String TERM_OPERATOR_LE = "'<='";
    public static String TERM_OPERATOR_IN = "'in'";
    public static String TERM_OPERATOR_NOTIN = "'not in'";
    public static String TERM_OPERATOR_LIKE = "'like'";
    public static String TERM_OPERATOR_NOTLIKE = "'not like'";

    public static Consumer<QueryWrapper> parse(IDataEntityRuntime dataEntityRuntime, String scope) {
        Consumer<QueryWrapper> wrapper = null;
        Stack<String> conditions = new Stack<>();
        Stack<Object> conditions_in = new Stack<>();
        Pattern bracketPattern = Pattern.compile("\\[(.*?)]");
        Matcher matcher = bracketPattern.matcher(scope);
        if (matcher.find()) {
            scope = matcher.group().substring(1, matcher.group().length() - 1);
        } else {
            throw new RuntimeException(String.format("scope解析错误:%s", scope));
        }

        //提取
        Pattern conditionPattern = Pattern.compile("\\'\\|\\'|\\'\\&\\'|\\([^\\)]+\\)");
        matcher = conditionPattern.matcher(scope);
        while (matcher.find()) {
            conditions.push(matcher.group());
        }

        if (conditions.size() >= 3) {
            wrapper = convert(dataEntityRuntime, conditions, conditions_in);
        }

        if (conditions.size() == 1) {
            wrapper = dataCondition -> {
                parseTerm(dataEntityRuntime, dataCondition, conditions.pop().toString());
            };
        }
        return wrapper;
    }

    /**
     * @param operations
     * @param conditions
     * @return
     */
    private static Consumer<QueryWrapper> convert(IDataEntityRuntime dataEntityRuntime, Stack<String> operations, Stack<Object> conditions) {
        Consumer<QueryWrapper> dataConditions = null;
        dataConditions = dataCondition -> {
            while (operations.size() > 0) {
                String operation = operations.pop();
                if (operation.equals(OPERATION_AND) || operation.equals(OPERATION_OR)) {
                    Consumer<QueryWrapper> terms = parseTerms(dataEntityRuntime, operation, conditions.pop(), conditions.pop());
                    conditions.add(0, terms);
                    if (operations.size() == 0)
                        dataCondition.and(terms);
                } else {
                    conditions.push(operation);
                }
            }
        };
        return dataConditions;
    }

    private static Consumer<QueryWrapper> parseTerms(IDataEntityRuntime dataEntityRuntime, String operation, Object condition, Object condition2) {
        Consumer<QueryWrapper> child = dataCondition -> {
            if (condition instanceof String && condition2 instanceof String) {
                parseTerm(dataEntityRuntime, dataCondition, condition.toString());
                if (operation.equals(OPERATION_OR))
                    dataCondition.or();
                parseTerm(dataEntityRuntime, dataCondition, condition2.toString());
            } else if (condition instanceof String && condition2 instanceof Consumer) {
                parseTerm(dataEntityRuntime, dataCondition, condition.toString());
                if (operation.equals(OPERATION_OR))
                    dataCondition.or();
                dataCondition.nested((Consumer<QueryWrapper>) condition2);
            } else if (condition instanceof Consumer && condition2 instanceof String) {
                dataCondition.nested((Consumer<QueryWrapper>) condition);
                if (operation.equals(OPERATION_OR))
                    dataCondition.or();
                parseTerm(dataEntityRuntime, dataCondition, condition2.toString());
            } else if (condition instanceof Consumer && condition2 instanceof Consumer) {
                dataCondition.nested((Consumer<QueryWrapper>) condition);
                if (operation.equals(OPERATION_OR))
                    dataCondition.or();
                dataCondition.nested((Consumer<QueryWrapper>) condition2);
            }
        };
        return child;
    }


    /**
     * 条件转换
     *
     * @param wrapper
     * @param condition
     */
    private static void parseTerm(IDataEntityRuntime dataEntityRuntime, QueryWrapper wrapper, String condition) {
        Pattern p1 = Pattern.compile("\'(.*?)\'");
        Matcher m = p1.matcher(condition);
        List<String> argList = new ArrayList<>();
        while (m.find()) {
            argList.add(m.group());
        }
        if(argList.size() == 1){
            argList.add(0,condition.split(",")[0].replace("(",""));
        }
        if(argList.size() == 2){
            argList.add(condition.split(",")[2].replace(")","")) ;
        }
        String[] args = argList.toArray(new String[argList.size()]);
        if (args.length != 3)
            throw new RuntimeException(String.format("字段条件解析错误:%s", args.toString()));
        String strFieldQueryExp = getFieldQueryExp(dataEntityRuntime, args[0].replace("'", "").trim());
        Object fieldValue = getFieldValue(dataEntityRuntime, args[0].replace("'", "").trim(), args[2]);
        if (args[1].trim().equals(TERM_OPERATOR_EQ)) {
            if ("false".equalsIgnoreCase(args[2].trim().replace("'", ""))) {
                wrapper.apply(String.format("%s IS NULL", strFieldQueryExp));
            } else if ("true".equalsIgnoreCase(args[2].trim().replace("'", ""))) {
                wrapper.apply(String.format("%s IS NOT NULL", strFieldQueryExp));
            } else {
                wrapper.apply(String.format("%s=%s", strFieldQueryExp, fieldValue));
            }
        } else if (args[1].trim().equals(TERM_OPERATOR_NE)) {
            if ("false".equalsIgnoreCase(args[2].trim().replace("'", ""))) {
                wrapper.apply(String.format("%s IS NOT NULL", strFieldQueryExp));
            }
            if ("true".equalsIgnoreCase(args[2].trim().replace("'", ""))) {
                wrapper.apply(String.format("%s IS NULL", strFieldQueryExp));
            } else {
                wrapper.apply(String.format("%s!=%s", strFieldQueryExp, fieldValue));
            }
        } else if (args[1].trim().equalsIgnoreCase(TERM_OPERATOR_GT)) {
            wrapper.apply(String.format("%s>%s", strFieldQueryExp, fieldValue));
        } else if (args[1].trim().equalsIgnoreCase(TERM_OPERATOR_GE)) {
            wrapper.apply(String.format("%s>=%s", strFieldQueryExp, fieldValue));
        } else if (args[1].trim().equalsIgnoreCase(TERM_OPERATOR_LT)) {
            wrapper.apply(String.format("%s<%s", strFieldQueryExp, fieldValue));
        } else if (args[1].trim().equalsIgnoreCase(TERM_OPERATOR_LE)) {
            wrapper.apply(String.format("%s<=%s", strFieldQueryExp, fieldValue));
        } else if (args[1].trim().equalsIgnoreCase(TERM_OPERATOR_IN)) {
            wrapper.apply(String.format("%s IN (%s)", strFieldQueryExp, fieldValue));
        } else if (args[1].trim().equalsIgnoreCase(TERM_OPERATOR_NOTIN)) {
            wrapper.apply(String.format("%s NOT IN (%s)", strFieldQueryExp, fieldValue));
        } else if (args[1].trim().equalsIgnoreCase(TERM_OPERATOR_LIKE)) {
            wrapper.apply(String.format("%s LIKE %s", strFieldQueryExp, fieldValue));
        } else if (args[1].trim().equalsIgnoreCase(TERM_OPERATOR_NOTLIKE)) {
            wrapper.apply(String.format("%s NOT LIKE %s", strFieldQueryExp, fieldValue));
        }
    }

    /**
     * @param strField
     * @return
     */
    public static String getFieldQueryExp(IDataEntityRuntime dataEntityRuntime, String strField) {
        if (dataEntityRuntime == null)
            return strField;
        try {
            EvaluationContext elContext = new StandardEvaluationContext();
            elContext.setVariable("runtime", dataEntityRuntime);
            elContext.setVariable("field", strField);
            Expression expression = parser.parseExpression("#runtime.getFieldQueryExp(#field)");
            String strFieldQueryExp = expression.getValue(elContext, String.class);
            if (StringUtils.isEmpty(strFieldQueryExp))
                return strField;
            return strFieldQueryExp;
        } catch (Exception e) {
        }
        return strField;
    }

    /**
     * @param strField
     * @return
     */
    public static Object getFieldValue(IDataEntityRuntime dataEntityRuntime, String strField, String strFieldValue) {
        if (dataEntityRuntime == null)
            throw new DataEntityRuntimeException(String.format("无法获取实体属性[%s]", strField), dataEntityRuntime);
        if(strFieldValue.indexOf("'")==-1)
            return strFieldValue;
        try {
            EvaluationContext elContext = new StandardEvaluationContext();
            elContext.setVariable("runtime", dataEntityRuntime);
            elContext.setVariable("field", strField);
            Expression expression = parser.parseExpression("#runtime.getPSDEField(#field.toLowerCase())");
            IPSDEField field = expression.getValue(elContext, IPSDEField.class);
            if (field == null)
                throw new DataEntityRuntimeException(String.format("无法获取实体属性[%s]", strField), dataEntityRuntime);
            return String.format("%s", strFieldValue);
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("无法获取实体属性[%s]值[%s]发生错误：%s", strField, strFieldValue, e.getMessage()), dataEntityRuntime);
        }
    }


    public static void main(String[] args) {
        QueryWrapper query = new QueryWrapper();
        query.like("orgchid", "HHH");
        Stack<String> operations = new Stack<>();
        Stack<Object> conditions = new Stack<>();
        String scope = "['|', '|'," +
                "('public', '=', 'public')," +
                "'&', ('public', '=', 'private'), ('channel_partner_ids', 'in', '1')," +
                "'&', ('public', 'like', 'groups'), ('group_public_id', 'in', a)]";
//        String scope = "['|', ('type', '!=', 'private'), ('type', '=', False)]";

        query.and(parse(null, scope));
        System.out.println(query.getSqlSegment());
    }

}
