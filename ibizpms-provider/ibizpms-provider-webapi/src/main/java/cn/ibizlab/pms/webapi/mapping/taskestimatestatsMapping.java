package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.zentao.domain.TaskEstimateStats;
import cn.ibizlab.pms.webapi.dto.taskestimatestatsDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApitaskestimatestatsMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(taskestimatestatsMappingDecorator.class)
public interface taskestimatestatsMapping extends MappingBase<taskestimatestatsDTO, TaskEstimateStats> {


}

class taskestimatestatsMappingDecorator implements taskestimatestatsMapping {

    @Autowired
    @Qualifier("delegate")
    private taskestimatestatsMapping delegate;

    @Override
    public TaskEstimateStats toDomain(taskestimatestatsDTO dto) {
        TaskEstimateStats domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public taskestimatestatsDTO toDto(TaskEstimateStats entity) {

        taskestimatestatsDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<TaskEstimateStats> toDomain(List<taskestimatestatsDTO> dtoList) {
        List<TaskEstimateStats> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<taskestimatestatsDTO> toDto(List<TaskEstimateStats> entityList) {
        List<taskestimatestatsDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
