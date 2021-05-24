package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IBZDailyHistory;
import cn.ibizlab.pms.webapi.dto.IBZDailyHistoryDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIBZDailyHistoryMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IBZDailyHistoryMappingDecorator.class)
public interface IBZDailyHistoryMapping extends MappingBase<IBZDailyHistoryDTO, IBZDailyHistory> {


}

class IBZDailyHistoryMappingDecorator implements IBZDailyHistoryMapping {

    @Autowired
    @Qualifier("delegate")
    private IBZDailyHistoryMapping delegate;

    @Override
    public IBZDailyHistory toDomain(IBZDailyHistoryDTO dto) {
        IBZDailyHistory domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IBZDailyHistoryDTO toDto(IBZDailyHistory entity) {

        IBZDailyHistoryDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IBZDailyHistory> toDomain(List<IBZDailyHistoryDTO> dtoList) {
        List<IBZDailyHistory> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IBZDailyHistoryDTO> toDto(List<IBZDailyHistory> entityList) {
        List<IBZDailyHistoryDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
