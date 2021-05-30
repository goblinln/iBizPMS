package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.uaa.domain.SysUserRole;
import cn.ibizlab.pms.webapi.dto.SysUserRoleDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiSysUserRoleMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(SysUserRoleMappingDecorator.class)
public interface SysUserRoleMapping extends MappingBase<SysUserRoleDTO, SysUserRole> {


}

class SysUserRoleMappingDecorator implements SysUserRoleMapping {

    @Autowired
    @Qualifier("delegate")
    private SysUserRoleMapping delegate;

    @Override
    public SysUserRole toDomain(SysUserRoleDTO dto) {
        SysUserRole domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public SysUserRoleDTO toDto(SysUserRole entity) {

        SysUserRoleDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<SysUserRole> toDomain(List<SysUserRoleDTO> dtoList) {
        List<SysUserRole> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<SysUserRoleDTO> toDto(List<SysUserRole> entityList) {
        List<SysUserRoleDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
