package cn.ibizlab.pms.core.util.config;

import feign.QueryMapEncoder;
import feign.codec.EncodeException;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 用于将searchContext中的param参数拼接在地址栏中
 */
public class FeignEncoder implements QueryMapEncoder {

    private final Map<Class<?>, ObjectParamMetadata> classToMetadata = new HashMap<>();

    @Override
    public Map<String, Object> encode(Object object) throws EncodeException {
        try {
            ObjectParamMetadata metadata = getMetadata(object.getClass());
            Map<String, Object> fieldNameToValue = new HashMap<String, Object>();
            for (Field field : metadata.objectFields) {
                Object value = field.get(object);
                if (value != null && value != object) {
                    if(value instanceof Map){
                        fieldNameToValue.putAll((Map<String,Object>)value);
                        continue;
                    }
                    fieldNameToValue.put(field.getName(), value);
                }
            }
            return fieldNameToValue;
        } catch (IllegalAccessException e) {
            throw new EncodeException("Failure encoding object into query map", e);
        }
    }

    private ObjectParamMetadata getMetadata(Class<?> objectType) {
        ObjectParamMetadata metadata = classToMetadata.get(objectType);
        if (metadata == null) {
            metadata = ObjectParamMetadata.parseObjectType(objectType);
            classToMetadata.put(objectType, metadata);
        }
        return metadata;
    }

    private static class ObjectParamMetadata {

        private final List<Field> objectFields;

        private ObjectParamMetadata(List<Field> objectFields) {
            this.objectFields = Collections.unmodifiableList(objectFields);
        }

        private static ObjectParamMetadata parseObjectType(Class<?> type) {
            List<Field> allFields = new ArrayList<>();

            for (Class<?> currentClass = type; currentClass != null; currentClass =
                    currentClass.getSuperclass()) {
                Collections.addAll(allFields, currentClass.getDeclaredFields());
            }

            return new ObjectParamMetadata(allFields.stream()
                    .filter(field -> !field.isSynthetic())
                    .peek(field -> field.setAccessible(true))
                    .collect(Collectors.toList()));
        }
    }

}
