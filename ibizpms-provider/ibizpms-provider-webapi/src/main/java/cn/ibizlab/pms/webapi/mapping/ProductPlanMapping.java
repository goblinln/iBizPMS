package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.zentao.domain.ProductPlan;
import cn.ibizlab.pms.webapi.dto.ProductPlanDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiProductPlanMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(ProductPlanMappingDecorator.class)
public interface ProductPlanMapping extends MappingBase<ProductPlanDTO, ProductPlan> {


}

class ProductPlanMappingDecorator implements ProductPlanMapping {

    @Autowired
    @Qualifier("delegate")
    private ProductPlanMapping delegate;

    @Override
    public ProductPlan toDomain(ProductPlanDTO dto) {
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
    public ProductPlanDTO toDto(ProductPlan entity) {

        ProductPlanDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<ProductPlan> toDomain(List<ProductPlanDTO> dtoList) {
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
    public List<ProductPlanDTO> toDto(List<ProductPlan> entityList) {
        List<ProductPlanDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
