package cn.ibizlab.pms.standardapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProductMonthly;
import cn.ibizlab.pms.standardapi.dto.ProductMonthlyDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "StandardAPIProductMonthlyMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(ProductMonthlyMappingDecorator.class)
public interface ProductMonthlyMapping extends MappingBase<ProductMonthlyDTO, IbizproProductMonthly> {


}

class ProductMonthlyMappingDecorator implements ProductMonthlyMapping {

    @Autowired
    @Qualifier("delegate")
    private ProductMonthlyMapping delegate;

    @Override
    public IbizproProductMonthly toDomain(ProductMonthlyDTO dto) {
        IbizproProductMonthly domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public ProductMonthlyDTO toDto(IbizproProductMonthly entity) {

        ProductMonthlyDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbizproProductMonthly> toDomain(List<ProductMonthlyDTO> dtoList) {
        List<IbizproProductMonthly> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<ProductMonthlyDTO> toDto(List<IbizproProductMonthly> entityList) {
        List<ProductMonthlyDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
