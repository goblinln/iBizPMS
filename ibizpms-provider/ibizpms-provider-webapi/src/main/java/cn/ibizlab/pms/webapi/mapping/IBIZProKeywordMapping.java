package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibizplugin.domain.IBIZProKeyword;
import cn.ibizlab.pms.webapi.dto.IBIZProKeywordDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIBIZProKeywordMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IBIZProKeywordMappingDecorator.class)
public interface IBIZProKeywordMapping extends MappingBase<IBIZProKeywordDTO, IBIZProKeyword> {


}

class IBIZProKeywordMappingDecorator implements IBIZProKeywordMapping {

    @Autowired
    @Qualifier("delegate")
    private IBIZProKeywordMapping delegate;

    @Override
    public IBIZProKeyword toDomain(IBIZProKeywordDTO dto) {
        IBIZProKeyword domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IBIZProKeywordDTO toDto(IBIZProKeyword entity) {

        IBIZProKeywordDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IBIZProKeyword> toDomain(List<IBIZProKeywordDTO> dtoList) {
        List<IBIZProKeyword> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IBIZProKeywordDTO> toDto(List<IBIZProKeyword> entityList) {
        List<IBIZProKeywordDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
