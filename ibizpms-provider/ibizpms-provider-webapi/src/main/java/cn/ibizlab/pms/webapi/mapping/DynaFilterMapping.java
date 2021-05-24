package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.DynaFilter;
import cn.ibizlab.pms.webapi.dto.DynaFilterDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiDynaFilterMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(DynaFilterMappingDecorator.class)
public interface DynaFilterMapping extends MappingBase<DynaFilterDTO, DynaFilter> {


}

class DynaFilterMappingDecorator implements DynaFilterMapping {

    @Autowired
    @Qualifier("delegate")
    private DynaFilterMapping delegate;

    @Override
    public DynaFilter toDomain(DynaFilterDTO dto) {
        DynaFilter domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public DynaFilterDTO toDto(DynaFilter entity) {

        DynaFilterDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<DynaFilter> toDomain(List<DynaFilterDTO> dtoList) {
        List<DynaFilter> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<DynaFilterDTO> toDto(List<DynaFilter> entityList) {
        List<DynaFilterDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
