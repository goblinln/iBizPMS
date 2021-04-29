package cn.ibizlab.pms.util.log;

import java.lang.annotation.*;

/**
 * ibiz日志注解
 * 在方法、类中使用该注解时，将生成方法执行日志。
 *
 * @author nancy
 * @date 2020-4-1
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface IBIZTraceLog {
}
