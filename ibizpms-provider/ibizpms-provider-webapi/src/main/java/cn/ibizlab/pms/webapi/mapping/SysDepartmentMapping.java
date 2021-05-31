package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ou.domain.SysDepartment;
import cn.ibizlab.pms.webapi.dto.SysDepartmentDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiSysDepartmentMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(SysDepartmentMappingDecorator.class)
public interface SysDepartmentMapping extends MappingBase<SysDepartmentDTO, SysDepartment> {


}

class SysDepartmentMappingDecorator implements SysDepartmentMapping {

    @Autowired
    @Qualifier("delegate")
    private SysDepartmentMapping delegate;

    @Override
    public SysDepartment toDomain(SysDepartmentDTO dto) {
        SysDepartment domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public SysDepartmentDTO toDto(SysDepartment entity) {

        SysDepartmentDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<SysDepartment> toDomain(List<SysDepartmentDTO> dtoList) {
        List<SysDepartment> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<SysDepartmentDTO> toDto(List<SysDepartment> entityList) {
        List<SysDepartmentDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
