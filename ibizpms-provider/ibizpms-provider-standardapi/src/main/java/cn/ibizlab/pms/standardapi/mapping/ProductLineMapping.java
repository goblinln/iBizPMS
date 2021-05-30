package cn.ibizlab.pms.standardapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibizpro.domain.IBZProProductLine;
import cn.ibizlab.pms.standardapi.dto.ProductLineDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "StandardAPIProductLineMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(ProductLineMappingDecorator.class)
public interface ProductLineMapping extends MappingBase<ProductLineDTO, IBZProProductLine> {


}

class ProductLineMappingDecorator implements ProductLineMapping {

    @Autowired
    @Qualifier("delegate")
    private ProductLineMapping delegate;

    @Override
    public IBZProProductLine toDomain(ProductLineDTO dto) {
        IBZProProductLine domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public ProductLineDTO toDto(IBZProProductLine entity) {

        ProductLineDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IBZProProductLine> toDomain(List<ProductLineDTO> dtoList) {
        List<IBZProProductLine> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<ProductLineDTO> toDto(List<IBZProProductLine> entityList) {
        List<ProductLineDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
