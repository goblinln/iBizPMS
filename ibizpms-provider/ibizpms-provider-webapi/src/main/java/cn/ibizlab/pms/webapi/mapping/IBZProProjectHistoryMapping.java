package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IBZProProjectHistory;
import cn.ibizlab.pms.webapi.dto.IBZProProjectHistoryDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIBZProProjectHistoryMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IBZProProjectHistoryMappingDecorator.class)
public interface IBZProProjectHistoryMapping extends MappingBase<IBZProProjectHistoryDTO, IBZProProjectHistory> {


}

class IBZProProjectHistoryMappingDecorator implements IBZProProjectHistoryMapping {

    @Autowired
    @Qualifier("delegate")
    private IBZProProjectHistoryMapping delegate;

    @Override
    public IBZProProjectHistory toDomain(IBZProProjectHistoryDTO dto) {
        IBZProProjectHistory domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IBZProProjectHistoryDTO toDto(IBZProProjectHistory entity) {

        IBZProProjectHistoryDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IBZProProjectHistory> toDomain(List<IBZProProjectHistoryDTO> dtoList) {
        List<IBZProProjectHistory> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IBZProProjectHistoryDTO> toDto(List<IBZProProjectHistory> entityList) {
        List<IBZProProjectHistoryDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
