package cn.ibizlab.pms.standardapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.zentao.domain.Task;
import cn.ibizlab.pms.standardapi.dto.ProjectTaskReportDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "StandardAPIProjectTaskReportMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(ProjectTaskReportMappingDecorator.class)
public interface ProjectTaskReportMapping extends MappingBase<ProjectTaskReportDTO, Task> {


}

class ProjectTaskReportMappingDecorator implements ProjectTaskReportMapping {

    @Autowired
    @Qualifier("delegate")
    private ProjectTaskReportMapping delegate;

    @Override
    public Task toDomain(ProjectTaskReportDTO dto) {
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
    public ProjectTaskReportDTO toDto(Task entity) {

        ProjectTaskReportDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<Task> toDomain(List<ProjectTaskReportDTO> dtoList) {
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
    public List<ProjectTaskReportDTO> toDto(List<Task> entityList) {
        List<ProjectTaskReportDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
