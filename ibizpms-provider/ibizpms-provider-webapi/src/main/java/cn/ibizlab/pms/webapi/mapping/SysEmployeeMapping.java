package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ou.domain.SysEmployee;
import cn.ibizlab.pms.webapi.dto.SysEmployeeDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiSysEmployeeMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(SysEmployeeMappingDecorator.class)
public interface SysEmployeeMapping extends MappingBase<SysEmployeeDTO, SysEmployee> {


}

class SysEmployeeMappingDecorator implements SysEmployeeMapping {

    @Autowired
    @Qualifier("delegate")
    private SysEmployeeMapping delegate;

    @Override
    public SysEmployee toDomain(SysEmployeeDTO dto) {
        SysEmployee domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public SysEmployeeDTO toDto(SysEmployee entity) {

        SysEmployeeDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<SysEmployee> toDomain(List<SysEmployeeDTO> dtoList) {
        List<SysEmployee> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<SysEmployeeDTO> toDto(List<SysEmployee> entityList) {
        List<SysEmployeeDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
