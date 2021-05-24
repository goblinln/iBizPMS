package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IbzProjectMember;
import cn.ibizlab.pms.webapi.dto.IbzProjectMemberDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIbzProjectMemberMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IbzProjectMemberMappingDecorator.class)
public interface IbzProjectMemberMapping extends MappingBase<IbzProjectMemberDTO, IbzProjectMember> {


}

class IbzProjectMemberMappingDecorator implements IbzProjectMemberMapping {

    @Autowired
    @Qualifier("delegate")
    private IbzProjectMemberMapping delegate;

    @Override
    public IbzProjectMember toDomain(IbzProjectMemberDTO dto) {
        IbzProjectMember domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IbzProjectMemberDTO toDto(IbzProjectMember entity) {

        IbzProjectMemberDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbzProjectMember> toDomain(List<IbzProjectMemberDTO> dtoList) {
        List<IbzProjectMember> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IbzProjectMemberDTO> toDto(List<IbzProjectMember> entityList) {
        List<IbzProjectMemberDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
