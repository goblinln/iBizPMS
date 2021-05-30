package cn.ibizlab.pms.standardapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.zentao.domain.Bug;
import cn.ibizlab.pms.standardapi.dto.ProductBugDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "StandardAPIProductBugMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(ProductBugMappingDecorator.class)
public interface ProductBugMapping extends MappingBase<ProductBugDTO, Bug> {


}

class ProductBugMappingDecorator implements ProductBugMapping {

    @Autowired
    @Qualifier("delegate")
    private ProductBugMapping delegate;

    @Override
    public Bug toDomain(ProductBugDTO dto) {
        Bug domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public ProductBugDTO toDto(Bug entity) {

        ProductBugDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<Bug> toDomain(List<ProductBugDTO> dtoList) {
        List<Bug> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<ProductBugDTO> toDto(List<Bug> entityList) {
        List<ProductBugDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
