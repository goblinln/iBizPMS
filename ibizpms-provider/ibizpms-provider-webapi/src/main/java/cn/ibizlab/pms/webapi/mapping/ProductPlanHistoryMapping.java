package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.ProductPlanHistory;
import cn.ibizlab.pms.webapi.dto.ProductPlanHistoryDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiProductPlanHistoryMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(ProductPlanHistoryMappingDecorator.class)
public interface ProductPlanHistoryMapping extends MappingBase<ProductPlanHistoryDTO, ProductPlanHistory> {


}

class ProductPlanHistoryMappingDecorator implements ProductPlanHistoryMapping {

    @Autowired
    @Qualifier("delegate")
    private ProductPlanHistoryMapping delegate;

    @Override
    public ProductPlanHistory toDomain(ProductPlanHistoryDTO dto) {
        ProductPlanHistory domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public ProductPlanHistoryDTO toDto(ProductPlanHistory entity) {

        ProductPlanHistoryDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<ProductPlanHistory> toDomain(List<ProductPlanHistoryDTO> dtoList) {
        List<ProductPlanHistory> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<ProductPlanHistoryDTO> toDto(List<ProductPlanHistory> entityList) {
        List<ProductPlanHistoryDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
