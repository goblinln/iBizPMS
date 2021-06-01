package cn.ibizlab.pms.standardapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.DynaDashboard;
import cn.ibizlab.pms.standardapi.dto.DynaDashboardDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "StandardAPIDynaDashboardMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(DynaDashboardMappingDecorator.class)
public interface DynaDashboardMapping extends MappingBase<DynaDashboardDTO, DynaDashboard> {


}

class DynaDashboardMappingDecorator implements DynaDashboardMapping {

    @Autowired
    @Qualifier("delegate")
    private DynaDashboardMapping delegate;

    @Override
    public DynaDashboard toDomain(DynaDashboardDTO dto) {
        DynaDashboard domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public DynaDashboardDTO toDto(DynaDashboard entity) {

        DynaDashboardDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<DynaDashboard> toDomain(List<DynaDashboardDTO> dtoList) {
        List<DynaDashboard> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<DynaDashboardDTO> toDto(List<DynaDashboard> entityList) {
        List<DynaDashboardDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
