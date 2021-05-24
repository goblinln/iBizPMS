package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.ProductPlanAction;
import cn.ibizlab.pms.webapi.dto.ProductPlanActionDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiProductPlanActionMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(ProductPlanActionMappingDecorator.class)
public interface ProductPlanActionMapping extends MappingBase<ProductPlanActionDTO, ProductPlanAction> {


}

class ProductPlanActionMappingDecorator implements ProductPlanActionMapping {

    @Autowired
    @Qualifier("delegate")
    private ProductPlanActionMapping delegate;

    @Override
    public ProductPlanAction toDomain(ProductPlanActionDTO dto) {
        ProductPlanAction domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public ProductPlanActionDTO toDto(ProductPlanAction entity) {

        ProductPlanActionDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<ProductPlanAction> toDomain(List<ProductPlanActionDTO> dtoList) {
        List<ProductPlanAction> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<ProductPlanActionDTO> toDto(List<ProductPlanAction> entityList) {
        List<ProductPlanActionDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
