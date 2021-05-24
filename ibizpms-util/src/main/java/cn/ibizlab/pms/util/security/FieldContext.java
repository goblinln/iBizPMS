package cn.ibizlab.pms.util.security;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

public class FieldContext {

    public static final ThreadLocal<List<String>> fields = new ThreadLocal<List<String>>() {
        @Override
        protected List<String> initialValue() {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                String fields = request.getHeader("srffields");
                if (org.apache.commons.lang3.StringUtils.isNotBlank(fields)) {
                    return Arrays.asList(fields.split(","));
                }
                return null;
            }
            return null;
        }
    };

    public static List<String> get() {
        return fields.get();
    }

}