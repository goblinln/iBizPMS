package cn.ibizlab.pms.standardapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.zentao.domain.TaskEstimate;
import cn.ibizlab.pms.standardapi.dto.ProjectTaskEstimateDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "StandardAPIProjectTaskEstimateMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(ProjectTaskEstimateMappingDecorator.class)
public interface ProjectTaskEstimateMapping extends MappingBase<ProjectTaskEstimateDTO, TaskEstimate> {


}

class ProjectTaskEstimateMappingDecorator implements ProjectTaskEstimateMapping {

    @Autowired
    @Qualifier("delegate")
    private ProjectTaskEstimateMapping delegate;

    @Override
    public TaskEstimate toDomain(ProjectTaskEstimateDTO dto) {
        TaskEstimate domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public ProjectTaskEstimateDTO toDto(TaskEstimate entity) {

        ProjectTaskEstimateDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<TaskEstimate> toDomain(List<ProjectTaskEstimateDTO> dtoList) {
        List<TaskEstimate> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<ProjectTaskEstimateDTO> toDto(List<TaskEstimate> entityList) {
        List<ProjectTaskEstimateDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
