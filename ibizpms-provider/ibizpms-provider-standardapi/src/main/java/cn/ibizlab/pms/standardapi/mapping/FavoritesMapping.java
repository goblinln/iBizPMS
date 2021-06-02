package cn.ibizlab.pms.standardapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IbzFavorites;
import cn.ibizlab.pms.standardapi.dto.FavoritesDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "StandardAPIFavoritesMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(FavoritesMappingDecorator.class)
public interface FavoritesMapping extends MappingBase<FavoritesDTO, IbzFavorites> {


}

class FavoritesMappingDecorator implements FavoritesMapping {

    @Autowired
    @Qualifier("delegate")
    private FavoritesMapping delegate;

    @Override
    public IbzFavorites toDomain(FavoritesDTO dto) {
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
    public FavoritesDTO toDto(IbzFavorites entity) {

        FavoritesDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbzFavorites> toDomain(List<FavoritesDTO> dtoList) {
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
    public List<FavoritesDTO> toDto(List<IbzFavorites> entityList) {
        List<FavoritesDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
