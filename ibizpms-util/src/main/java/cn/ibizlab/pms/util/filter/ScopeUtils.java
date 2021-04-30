package cn.ibizlab.pms.util.filter;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScopeUtils {
    public static String OPERATION_AND = "'&'";
    public static String OPERATION_OR = "'|'";

    public static String COLUMN_EQ = "'='";
    public static String COLUMN_NE = "'!='";
    public static String COLUMN_GT = "'>'";
    public static String COLUMN_GE = "'>='";
    public static String COLUMN_LT = "'<'";
    public static String COLUMN_LE = "'<='";
    public static String COLUMN_IN = "'in'";
    public static String COLUMN_NOTIN = "'not in'";
    public static String COLUMN_LIKE = "'like'";

    public static Consumer<QueryWrapper> parse(String scope) {
        Consumer<QueryWrapper> wrapper = null;
        Stack<String> conditions = new Stack<>();
        Stack<Object> conditions_in = new Stack<>();

        Pattern bracketPattern = Pattern.compile("\\[(.*?)]");
        Matcher matcher = bracketPattern.matcher(scope);
        if (matcher.find()) {
            scope = matcher.group().substring(1, matcher.group().length() - 1);
        } else {
            throw new RuntimeException(String.format("scope解析错误:%s",scope));
        }

        //提取
        Pattern conditionPattern = Pattern.compile("(?<=\\()(.+?)(?=\\))");
        matcher = conditionPattern.matcher(scope);
        while (matcher.find()) {
            String m = matcher.group();
            String r = m.replace(",", ";");
            scope = scope.replace(m, r).replace("(", "").replace(")", "");
        }

        //提取操作
        for (String arg : scope.split(",")) {
            arg = arg.trim();
            conditions.push(arg);
        }
        if (conditions.size() >= 3) {
            wrapper = convert(conditions, conditions_in);
        }

        if (conditions.size() == 1) {
            wrapper = dataCondition -> {
                fillColumn(dataCondition, conditions.pop().toString());
            };
        }
        return wrapper;
    }

    private static Consumer<QueryWrapper> convert(Stack<String> operations, Stack<Object> conditions) {
        Consumer<QueryWrapper> dataConditions = null;

        dataConditions = dataCondition -> {
            while (operations.size() > 0) {
                String operation = operations.pop();
                if (operation.equals(OPERATION_AND) || operation.equals(OPERATION_OR)) {
                    Consumer<QueryWrapper> child = genChildWrapper(operation, conditions.pop(), conditions.pop());
                    conditions.add(0, child);
                    if (operations.size() == 0)
                        dataCondition.and(child);
                } else {
                    conditions.push(operation);
                }
            }
        };


        return dataConditions;
    }

    private static Consumer<QueryWrapper> genChildWrapper(String operation, Object condition, Object condition2) {
        Consumer<QueryWrapper> child = dataCondition -> {
            if (condition instanceof String && condition2 instanceof String) {
                fillColumn(dataCondition, condition.toString());
                if (operation.equals(OPERATION_OR))
                    dataCondition.or();
                fillColumn(dataCondition, condition2.toString());
            } else if (condition instanceof String && condition2 instanceof QueryWrapper) {
                fillColumn(dataCondition, condition.toString());
                if (operation.equals(OPERATION_OR))
                    dataCondition.or();
                dataCondition.nested((Consumer<QueryWrapper>) condition2);
            } else if (condition instanceof QueryWrapper && condition2 instanceof String) {
                dataCondition.nested((Consumer<QueryWrapper>) condition);
                if (operation.equals(OPERATION_OR))
                    dataCondition.or();
                fillColumn(dataCondition, condition2.toString());
            } else if (condition instanceof QueryWrapper && condition2 instanceof QueryWrapper) {
                dataCondition.nested((Consumer<QueryWrapper>) condition);
                if (operation.equals(OPERATION_OR))
                    dataCondition.or();
                dataCondition.nested((Consumer<QueryWrapper>) condition2);
            }
        };
        return child;
    }

    private static void fillColumn(QueryWrapper wrapper, String condition) {
        String[] args = condition.split(";");
        if (args.length != 3)
            throw new RuntimeException(String.format("字段条件解析错误:%s", args.toString()));
        if (args[1].trim().equals(COLUMN_EQ)) {
            if ("false".equalsIgnoreCase(args[2].trim().replace("'", "")))
                wrapper.isNull(args[0].replace("'", ""));
            else if ("true".equalsIgnoreCase(args[2].trim().replace("'", "")))
                wrapper.isNotNull(args[0].replace("'", ""));
            else {
                wrapper.eq(args[0].replace("'", ""), args[2].trim().replace("'", ""));
            }
        } else if (args[1].trim().equals(COLUMN_NE)) {
            if ("false".equalsIgnoreCase(args[2].trim().replace("'", "")))
                wrapper.isNotNull(args[0].replace("'", ""));
            if ("true".equalsIgnoreCase(args[2].trim().replace("'", "")))
                wrapper.isNull(args[0].replace("'", ""));
            else {
                wrapper.ne(args[0].replace("'", ""), args[2].trim().replace("'", ""));
            }
        } else if (args[1].trim().equalsIgnoreCase(COLUMN_GT)) {
            wrapper.gt(args[0].replace("'", ""), args[2].trim().replace("'", ""));
        } else if (args[1].trim().equalsIgnoreCase(COLUMN_GE)) {
            wrapper.ge(args[0].replace("'", ""), args[2].trim().replace("'", ""));
        } else if (args[1].trim().equalsIgnoreCase(COLUMN_LT)) {
            wrapper.lt(args[0].replace("'", ""), args[2].trim().replace("'", ""));
        } else if (args[1].trim().equalsIgnoreCase(COLUMN_LE)) {
            wrapper.le(args[0].replace("'", ""), args[2].trim().replace("'", ""));
        } else if (args[1].trim().equalsIgnoreCase(COLUMN_IN)) {
            wrapper.in(args[0].replace("'", ""), args[2].trim().replace("'", ""));
        } else if (args[1].trim().equalsIgnoreCase(COLUMN_NOTIN)) {
            wrapper.notIn(args[0].replace("'", ""), args[2].trim().replace("'", ""));
        } else if (args[1].trim().equalsIgnoreCase(COLUMN_LIKE)) {
            wrapper.like(args[0].replace("'", ""), args[2].trim().replace("'", ""));
        }
    }


    public static void main(String[] args) {
        QueryWrapper query = new QueryWrapper();
        query.like("orgchid", "HHH");
        Stack<String> operations = new Stack<>();
        Stack<Object> conditions = new Stack<>();
//        String scope = "['|', '|'," +
//                "('public', '=', 'public')," +
//                "'&', ('public', '=', 'private'), ('channel_partner_ids', 'in', 1)," +
//                "'&', ('public', '=', 'groups'), ('group_public_id', 'in', a)]";
        String scope = "['|', ('type', '!=', 'private'), ('type', '=', False)]";
        parse(scope);

        System.out.println(query.getSqlSegment());
    }
}
