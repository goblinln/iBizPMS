package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ou.domain.SysOrganization;
import cn.ibizlab.pms.webapi.dto.SysOrganizationDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiSysOrganizationMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(SysOrganizationMappingDecorator.class)
public interface SysOrganizationMapping extends MappingBase<SysOrganizationDTO, SysOrganization> {


}

class SysOrganizationMappingDecorator implements SysOrganizationMapping {

    @Autowired
    @Qualifier("delegate")
    private SysOrganizationMapping delegate;

    @Override
    public SysOrganization toDomain(SysOrganizationDTO dto) {
        SysOrganization domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public SysOrganizationDTO toDto(SysOrganization entity) {

        SysOrganizationDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<SysOrganization> toDomain(List<SysOrganizationDTO> dtoList) {
        List<SysOrganization> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<SysOrganizationDTO> toDto(List<SysOrganization> entityList) {
        List<SysOrganizationDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
