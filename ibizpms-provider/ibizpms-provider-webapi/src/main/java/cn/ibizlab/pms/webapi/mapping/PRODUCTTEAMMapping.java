package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.PRODUCTTEAM;
import cn.ibizlab.pms.webapi.dto.PRODUCTTEAMDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiPRODUCTTEAMMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(PRODUCTTEAMMappingDecorator.class)
public interface PRODUCTTEAMMapping extends MappingBase<PRODUCTTEAMDTO, PRODUCTTEAM> {


}

class PRODUCTTEAMMappingDecorator implements PRODUCTTEAMMapping {

    @Autowired
    @Qualifier("delegate")
    private PRODUCTTEAMMapping delegate;

    @Override
    public PRODUCTTEAM toDomain(PRODUCTTEAMDTO dto) {
        PRODUCTTEAM domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public PRODUCTTEAMDTO toDto(PRODUCTTEAM entity) {

        PRODUCTTEAMDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<PRODUCTTEAM> toDomain(List<PRODUCTTEAMDTO> dtoList) {
        List<PRODUCTTEAM> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<PRODUCTTEAMDTO> toDto(List<PRODUCTTEAM> entityList) {
        List<PRODUCTTEAMDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
