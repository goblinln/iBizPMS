package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.ProjectTeam;
import cn.ibizlab.pms.webapi.dto.IBZPROJECTTEAMDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIBZPROJECTTEAMMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IBZPROJECTTEAMMappingDecorator.class)
public interface IBZPROJECTTEAMMapping extends MappingBase<IBZPROJECTTEAMDTO, ProjectTeam> {


}

class IBZPROJECTTEAMMappingDecorator implements IBZPROJECTTEAMMapping {

    @Autowired
    @Qualifier("delegate")
    private IBZPROJECTTEAMMapping delegate;

    @Override
    public ProjectTeam toDomain(IBZPROJECTTEAMDTO dto) {
        ProjectTeam domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IBZPROJECTTEAMDTO toDto(ProjectTeam entity) {

        IBZPROJECTTEAMDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<ProjectTeam> toDomain(List<IBZPROJECTTEAMDTO> dtoList) {
        List<ProjectTeam> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IBZPROJECTTEAMDTO> toDto(List<ProjectTeam> entityList) {
        List<IBZPROJECTTEAMDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
