package cn.ibizlab.pms.standardapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.TaskTeam;
import cn.ibizlab.pms.standardapi.dto.TaskTeamDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "StandardAPITaskTeamMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(TaskTeamMappingDecorator.class)
public interface TaskTeamMapping extends MappingBase<TaskTeamDTO, TaskTeam> {


}

class TaskTeamMappingDecorator implements TaskTeamMapping {

    @Autowired
    @Qualifier("delegate")
    private TaskTeamMapping delegate;

    @Override
    public TaskTeam toDomain(TaskTeamDTO dto) {
        TaskTeam domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public TaskTeamDTO toDto(TaskTeam entity) {

        TaskTeamDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<TaskTeam> toDomain(List<TaskTeamDTO> dtoList) {
        List<TaskTeam> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<TaskTeamDTO> toDto(List<TaskTeam> entityList) {
        List<TaskTeamDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
