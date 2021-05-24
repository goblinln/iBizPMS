package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.zentao.domain.TaskEstimate;
import cn.ibizlab.pms.webapi.dto.IBZTaskEstimateDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIBZTaskEstimateMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IBZTaskEstimateMappingDecorator.class)
public interface IBZTaskEstimateMapping extends MappingBase<IBZTaskEstimateDTO, TaskEstimate> {


}

class IBZTaskEstimateMappingDecorator implements IBZTaskEstimateMapping {

    @Autowired
    @Qualifier("delegate")
    private IBZTaskEstimateMapping delegate;

    @Override
    public TaskEstimate toDomain(IBZTaskEstimateDTO dto) {
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
    public IBZTaskEstimateDTO toDto(TaskEstimate entity) {

        IBZTaskEstimateDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<TaskEstimate> toDomain(List<IBZTaskEstimateDTO> dtoList) {
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
    public List<IBZTaskEstimateDTO> toDto(List<TaskEstimate> entityList) {
        List<IBZTaskEstimateDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
