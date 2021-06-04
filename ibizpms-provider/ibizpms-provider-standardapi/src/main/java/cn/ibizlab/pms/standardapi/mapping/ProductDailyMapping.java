package cn.ibizlab.pms.standardapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProductDaily;
import cn.ibizlab.pms.standardapi.dto.ProductDailyDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "StandardAPIProductDailyMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(ProductDailyMappingDecorator.class)
public interface ProductDailyMapping extends MappingBase<ProductDailyDTO, IbizproProductDaily> {


}

class ProductDailyMappingDecorator implements ProductDailyMapping {

    @Autowired
    @Qualifier("delegate")
    private ProductDailyMapping delegate;

    @Override
    public IbizproProductDaily toDomain(ProductDailyDTO dto) {
        IbizproProductDaily domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public ProductDailyDTO toDto(IbizproProductDaily entity) {

        ProductDailyDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbizproProductDaily> toDomain(List<ProductDailyDTO> dtoList) {
        List<IbizproProductDaily> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<ProductDailyDTO> toDto(List<IbizproProductDaily> entityList) {
        List<ProductDailyDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
