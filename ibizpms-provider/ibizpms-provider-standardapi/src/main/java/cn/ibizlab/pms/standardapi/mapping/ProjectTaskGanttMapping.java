package cn.ibizlab.pms.standardapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.zentao.domain.Task;
import cn.ibizlab.pms.standardapi.dto.ProjectTaskGanttDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "StandardAPIProjectTaskGanttMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(ProjectTaskGanttMappingDecorator.class)
public interface ProjectTaskGanttMapping extends MappingBase<ProjectTaskGanttDTO, Task> {


}

class ProjectTaskGanttMappingDecorator implements ProjectTaskGanttMapping {

    @Autowired
    @Qualifier("delegate")
    private ProjectTaskGanttMapping delegate;

    @Override
    public Task toDomain(ProjectTaskGanttDTO dto) {
        Task domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public ProjectTaskGanttDTO toDto(Task entity) {

        ProjectTaskGanttDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<Task> toDomain(List<ProjectTaskGanttDTO> dtoList) {
        List<Task> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<ProjectTaskGanttDTO> toDto(List<Task> entityList) {
        List<ProjectTaskGanttDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
