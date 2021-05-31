package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibizpro.domain.IBZProProductLine;
import cn.ibizlab.pms.webapi.dto.IBZProProductLineDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIBZProProductLineMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IBZProProductLineMappingDecorator.class)
public interface IBZProProductLineMapping extends MappingBase<IBZProProductLineDTO, IBZProProductLine> {


}

class IBZProProductLineMappingDecorator implements IBZProProductLineMapping {

    @Autowired
    @Qualifier("delegate")
    private IBZProProductLineMapping delegate;

    @Override
    public IBZProProductLine toDomain(IBZProProductLineDTO dto) {
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
    public IBZProProductLineDTO toDto(IBZProProductLine entity) {

        IBZProProductLineDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IBZProProductLine> toDomain(List<IBZProProductLineDTO> dtoList) {
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
    public List<IBZProProductLineDTO> toDto(List<IBZProProductLine> entityList) {
        List<IBZProProductLineDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
