package cn.ibizlab.pms.standardapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.TaskTeam;
import cn.ibizlab.pms.standardapi.dto.TaskTeamNestedDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "StandardAPITaskTeamNestedMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(TaskTeamNestedMappingDecorator.class)
public interface TaskTeamNestedMapping extends MappingBase<TaskTeamNestedDTO, TaskTeam> {


}

class TaskTeamNestedMappingDecorator implements TaskTeamNestedMapping {

    @Autowired
    @Qualifier("delegate")
    private TaskTeamNestedMapping delegate;

    @Override
    public TaskTeam toDomain(TaskTeamNestedDTO dto) {
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
    public TaskTeamNestedDTO toDto(TaskTeam entity) {

        TaskTeamNestedDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<TaskTeam> toDomain(List<TaskTeamNestedDTO> dtoList) {
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
    public List<TaskTeamNestedDTO> toDto(List<TaskTeam> entityList) {
        List<TaskTeamNestedDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
