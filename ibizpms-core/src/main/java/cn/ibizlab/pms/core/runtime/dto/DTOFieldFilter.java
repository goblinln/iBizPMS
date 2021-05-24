package cn.ibizlab.pms.core.runtime.dto;

import cn.ibizlab.pms.util.security.FieldContext;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import org.apache.commons.collections4.ListUtils;

import java.util.ArrayList;
import java.util.List;

public class DTOFieldFilter extends SimpleBeanPropertyFilter implements java.io.Serializable {

    @Override
    protected boolean include(BeanPropertyWriter writer) {
        if ("srfopprivs".equalsIgnoreCase(writer.getName()))
            return true;
        List<String> outFields = getOutFields();
        return (outFields != null && outFields.size() > 0) ? outFields.contains(writer.getName()) : true;
    }


    @Override
    protected boolean include(PropertyWriter writer) {
        if ("srfopprivs".equalsIgnoreCase(writer.getName()))
            return true;
        List<String> outFields = getOutFields();
        return (outFields != null && outFields.size() > 0) ? outFields.contains(writer.getName()) : true;
    }


    private List<String> getOutFields() {
        //输出字段
        List<String> outFields;
        //可访问字段 权限
        List<String> perFields = new ArrayList<>();
        try {
//            ProductRuntime productRuntime = SpringContextHolder.getBean(ProductRuntime.class);
//            List<IPSDEField> deFields = productRuntime.getPSDataEntity().getAllPSDEFields();
//            if (deFields != null) {
//                //受控字段
//                for (IPSDEField deField : deFields) {
//                    perFields.add(deField.getCodeName().toLowerCase());
//                }
//            }
        } catch (Exception e) {

        }
        //请求 字段
        List<String> reqFields = FieldContext.get();
        if (reqFields != null && reqFields.size() > 0) {
            outFields = ListUtils.intersection(perFields, reqFields);
        } else {
            outFields = perFields;
        }
        return reqFields;
    }

}
