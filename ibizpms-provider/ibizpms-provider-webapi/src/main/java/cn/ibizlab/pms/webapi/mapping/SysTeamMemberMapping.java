package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ou.domain.SysTeamMember;
import cn.ibizlab.pms.webapi.dto.SysTeamMemberDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiSysTeamMemberMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(SysTeamMemberMappingDecorator.class)
public interface SysTeamMemberMapping extends MappingBase<SysTeamMemberDTO, SysTeamMember> {


}

class SysTeamMemberMappingDecorator implements SysTeamMemberMapping {

    @Autowired
    @Qualifier("delegate")
    private SysTeamMemberMapping delegate;

    @Override
    public SysTeamMember toDomain(SysTeamMemberDTO dto) {
        SysTeamMember domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public SysTeamMemberDTO toDto(SysTeamMember entity) {

        SysTeamMemberDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<SysTeamMember> toDomain(List<SysTeamMemberDTO> dtoList) {
        List<SysTeamMember> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<SysTeamMemberDTO> toDto(List<SysTeamMember> entityList) {
        List<SysTeamMemberDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
