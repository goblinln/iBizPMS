package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IbzMyTerritory;
import cn.ibizlab.pms.webapi.dto.IbzMyTerritoryDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIbzMyTerritoryMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IbzMyTerritoryMappingDecorator.class)
public interface IbzMyTerritoryMapping extends MappingBase<IbzMyTerritoryDTO, IbzMyTerritory> {


}

class IbzMyTerritoryMappingDecorator implements IbzMyTerritoryMapping {

    @Autowired
    @Qualifier("delegate")
    private IbzMyTerritoryMapping delegate;

    @Override
    public IbzMyTerritory toDomain(IbzMyTerritoryDTO dto) {
        IbzMyTerritory domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IbzMyTerritoryDTO toDto(IbzMyTerritory entity) {

        IbzMyTerritoryDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbzMyTerritory> toDomain(List<IbzMyTerritoryDTO> dtoList) {
        List<IbzMyTerritory> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IbzMyTerritoryDTO> toDto(List<IbzMyTerritory> entityList) {
        List<IbzMyTerritoryDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
