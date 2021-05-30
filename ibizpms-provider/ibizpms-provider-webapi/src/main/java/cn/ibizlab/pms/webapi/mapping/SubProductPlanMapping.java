package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.zentao.domain.ProductPlan;
import cn.ibizlab.pms.webapi.dto.SubProductPlanDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiSubProductPlanMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(SubProductPlanMappingDecorator.class)
public interface SubProductPlanMapping extends MappingBase<SubProductPlanDTO, ProductPlan> {


}

class SubProductPlanMappingDecorator implements SubProductPlanMapping {

    @Autowired
    @Qualifier("delegate")
    private SubProductPlanMapping delegate;

    @Override
    public ProductPlan toDomain(SubProductPlanDTO dto) {
        ProductPlan domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public SubProductPlanDTO toDto(ProductPlan entity) {

        SubProductPlanDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<ProductPlan> toDomain(List<SubProductPlanDTO> dtoList) {
        List<ProductPlan> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<SubProductPlanDTO> toDto(List<ProductPlan> entityList) {
        List<SubProductPlanDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
