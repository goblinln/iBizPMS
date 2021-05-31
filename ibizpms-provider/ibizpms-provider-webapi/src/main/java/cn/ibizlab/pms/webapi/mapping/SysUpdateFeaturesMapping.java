package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.SysUpdateFeatures;
import cn.ibizlab.pms.webapi.dto.SysUpdateFeaturesDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiSysUpdateFeaturesMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(SysUpdateFeaturesMappingDecorator.class)
public interface SysUpdateFeaturesMapping extends MappingBase<SysUpdateFeaturesDTO, SysUpdateFeatures> {


}

class SysUpdateFeaturesMappingDecorator implements SysUpdateFeaturesMapping {

    @Autowired
    @Qualifier("delegate")
    private SysUpdateFeaturesMapping delegate;

    @Override
    public SysUpdateFeatures toDomain(SysUpdateFeaturesDTO dto) {
        SysUpdateFeatures domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public SysUpdateFeaturesDTO toDto(SysUpdateFeatures entity) {

        SysUpdateFeaturesDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<SysUpdateFeatures> toDomain(List<SysUpdateFeaturesDTO> dtoList) {
        List<SysUpdateFeatures> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<SysUpdateFeaturesDTO> toDto(List<SysUpdateFeatures> entityList) {
        List<SysUpdateFeaturesDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
