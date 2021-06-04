package cn.ibizlab.pms.standardapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProductWeekly;
import cn.ibizlab.pms.standardapi.dto.ProductWeeklyDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "StandardAPIProductWeeklyMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(ProductWeeklyMappingDecorator.class)
public interface ProductWeeklyMapping extends MappingBase<ProductWeeklyDTO, IbizproProductWeekly> {


}

class ProductWeeklyMappingDecorator implements ProductWeeklyMapping {

    @Autowired
    @Qualifier("delegate")
    private ProductWeeklyMapping delegate;

    @Override
    public IbizproProductWeekly toDomain(ProductWeeklyDTO dto) {
        IbizproProductWeekly domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public ProductWeeklyDTO toDto(IbizproProductWeekly entity) {

        ProductWeeklyDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbizproProductWeekly> toDomain(List<ProductWeeklyDTO> dtoList) {
        List<IbizproProductWeekly> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<ProductWeeklyDTO> toDto(List<IbizproProductWeekly> entityList) {
        List<ProductWeeklyDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
