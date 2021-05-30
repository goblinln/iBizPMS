package cn.ibizlab.pms.util.security;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.util.ObjectUtils;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

public class FieldContext {

    public static final ThreadLocal<List<String>> fields = new ThreadLocal<List<String>>() {
        @Override
        protected List<String> initialValue() {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                List<String> fieldList = new ArrayList<>();
                HttpServletRequest request = attributes.getRequest();
                String fields = request.getHeader("srffields");
                Object fields2 = request.getAttribute("srffields");
                if (org.apache.commons.lang3.StringUtils.isNotBlank(fields)) {
                    fieldList.addAll(Arrays.asList(fields.split(",")));
                }
                if (!ObjectUtils.isEmpty(fields2)) {
                    fieldList.addAll(Arrays.asList(fields2.toString().split(",")));
                }
                return fieldList;
            }
            return null;
        }
    };

    public static List<String> get() {
        return fields.get();
    }

}