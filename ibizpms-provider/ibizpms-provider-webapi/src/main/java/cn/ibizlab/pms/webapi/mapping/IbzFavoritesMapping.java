package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IbzFavorites;
import cn.ibizlab.pms.webapi.dto.IbzFavoritesDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIbzFavoritesMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IbzFavoritesMappingDecorator.class)
public interface IbzFavoritesMapping extends MappingBase<IbzFavoritesDTO, IbzFavorites> {


}

class IbzFavoritesMappingDecorator implements IbzFavoritesMapping {

    @Autowired
    @Qualifier("delegate")
    private IbzFavoritesMapping delegate;

    @Override
    public IbzFavorites toDomain(IbzFavoritesDTO dto) {
        IbzFavorites domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IbzFavoritesDTO toDto(IbzFavorites entity) {

        IbzFavoritesDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbzFavorites> toDomain(List<IbzFavoritesDTO> dtoList) {
        List<IbzFavorites> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IbzFavoritesDTO> toDto(List<IbzFavorites> entityList) {
        List<IbzFavoritesDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
