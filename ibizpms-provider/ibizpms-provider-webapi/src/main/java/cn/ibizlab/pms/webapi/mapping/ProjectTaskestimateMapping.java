package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibizpro.domain.ProjectTaskestimate;
import cn.ibizlab.pms.webapi.dto.ProjectTaskestimateDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiProjectTaskestimateMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(ProjectTaskestimateMappingDecorator.class)
public interface ProjectTaskestimateMapping extends MappingBase<ProjectTaskestimateDTO, ProjectTaskestimate> {


}

class ProjectTaskestimateMappingDecorator implements ProjectTaskestimateMapping {

    @Autowired
    @Qualifier("delegate")
    private ProjectTaskestimateMapping delegate;

    @Override
    public ProjectTaskestimate toDomain(ProjectTaskestimateDTO dto) {
        ProjectTaskestimate domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public ProjectTaskestimateDTO toDto(ProjectTaskestimate entity) {

        ProjectTaskestimateDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<ProjectTaskestimate> toDomain(List<ProjectTaskestimateDTO> dtoList) {
        List<ProjectTaskestimate> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<ProjectTaskestimateDTO> toDto(List<ProjectTaskestimate> entityList) {
        List<ProjectTaskestimateDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
