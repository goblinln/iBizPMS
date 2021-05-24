package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IBZProToDoHistory;
import cn.ibizlab.pms.webapi.dto.IBZProToDoHistoryDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIBZProToDoHistoryMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IBZProToDoHistoryMappingDecorator.class)
public interface IBZProToDoHistoryMapping extends MappingBase<IBZProToDoHistoryDTO, IBZProToDoHistory> {


}

class IBZProToDoHistoryMappingDecorator implements IBZProToDoHistoryMapping {

    @Autowired
    @Qualifier("delegate")
    private IBZProToDoHistoryMapping delegate;

    @Override
    public IBZProToDoHistory toDomain(IBZProToDoHistoryDTO dto) {
        IBZProToDoHistory domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IBZProToDoHistoryDTO toDto(IBZProToDoHistory entity) {

        IBZProToDoHistoryDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IBZProToDoHistory> toDomain(List<IBZProToDoHistoryDTO> dtoList) {
        List<IBZProToDoHistory> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IBZProToDoHistoryDTO> toDto(List<IBZProToDoHistory> entityList) {
        List<IBZProToDoHistoryDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
