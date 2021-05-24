package cn.ibizlab.pms.core.runtime.dto;

import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DTOFieldFilter extends SimpleBeanPropertyFilter implements java.io.Serializable {

    @Override
    protected boolean include(BeanPropertyWriter writer) {
        if(true)
            return true;
        if("srfopprivs".equalsIgnoreCase(writer.getName()))
            return true ;
        return getOutFields().contains(writer.getName());

    }


    @Override
    protected boolean include(PropertyWriter writer) {
        if(true)
            return true;
        if("srfopprivs".equalsIgnoreCase(writer.getName()))
            return true ;
        return getOutFields().contains(writer.getName());
    }


    private List<String> getOutFields() {
        //输出字段
        List<String> outFields;
        //可访问字段 权限
        List<String> perFields = new ArrayList<>();
        perFields.add("amount");
        perFields.add("createman");
        //请求 字段
        List<String> reqFields = new ArrayList<>();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            String strReqFields = request.getHeader("srffields");
            if (StringUtils.isNotBlank(strReqFields)) {
                reqFields.addAll(Arrays.asList(strReqFields.toLowerCase().split(",")));
            }
        }
        if (reqFields.size() > 0) {
            outFields = ListUtils.intersection(perFields, reqFields);
        } else {
            outFields = perFields;
        }
        return outFields;
    }

}
