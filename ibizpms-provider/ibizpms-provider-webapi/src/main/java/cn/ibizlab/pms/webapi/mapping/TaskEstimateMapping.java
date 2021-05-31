package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.zentao.domain.TaskEstimate;
import cn.ibizlab.pms.webapi.dto.TaskEstimateDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiTaskEstimateMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(TaskEstimateMappingDecorator.class)
public interface TaskEstimateMapping extends MappingBase<TaskEstimateDTO, TaskEstimate> {


}

class TaskEstimateMappingDecorator implements TaskEstimateMapping {

    @Autowired
    @Qualifier("delegate")
    private TaskEstimateMapping delegate;

    @Override
    public TaskEstimate toDomain(TaskEstimateDTO dto) {
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
    public TaskEstimateDTO toDto(TaskEstimate entity) {

        TaskEstimateDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<TaskEstimate> toDomain(List<TaskEstimateDTO> dtoList) {
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
    public List<TaskEstimateDTO> toDto(List<TaskEstimate> entityList) {
        List<TaskEstimateDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
