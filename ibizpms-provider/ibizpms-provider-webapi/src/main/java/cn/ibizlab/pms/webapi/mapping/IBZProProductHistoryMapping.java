package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IBZProProductHistory;
import cn.ibizlab.pms.webapi.dto.IBZProProductHistoryDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIBZProProductHistoryMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IBZProProductHistoryMappingDecorator.class)
public interface IBZProProductHistoryMapping extends MappingBase<IBZProProductHistoryDTO, IBZProProductHistory> {


}

class IBZProProductHistoryMappingDecorator implements IBZProProductHistoryMapping {

    @Autowired
    @Qualifier("delegate")
    private IBZProProductHistoryMapping delegate;

    @Override
    public IBZProProductHistory toDomain(IBZProProductHistoryDTO dto) {
        IBZProProductHistory domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IBZProProductHistoryDTO toDto(IBZProProductHistory entity) {

        IBZProProductHistoryDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IBZProProductHistory> toDomain(List<IBZProProductHistoryDTO> dtoList) {
        List<IBZProProductHistory> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IBZProProductHistoryDTO> toDto(List<IBZProProductHistory> entityList) {
        List<IBZProProductHistoryDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
