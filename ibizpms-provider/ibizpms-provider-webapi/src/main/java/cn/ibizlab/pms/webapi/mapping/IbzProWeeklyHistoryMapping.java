package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IbzProWeeklyHistory;
import cn.ibizlab.pms.webapi.dto.IbzProWeeklyHistoryDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIbzProWeeklyHistoryMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IbzProWeeklyHistoryMappingDecorator.class)
public interface IbzProWeeklyHistoryMapping extends MappingBase<IbzProWeeklyHistoryDTO, IbzProWeeklyHistory> {


}

class IbzProWeeklyHistoryMappingDecorator implements IbzProWeeklyHistoryMapping {

    @Autowired
    @Qualifier("delegate")
    private IbzProWeeklyHistoryMapping delegate;

    @Override
    public IbzProWeeklyHistory toDomain(IbzProWeeklyHistoryDTO dto) {
        IbzProWeeklyHistory domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IbzProWeeklyHistoryDTO toDto(IbzProWeeklyHistory entity) {

        IbzProWeeklyHistoryDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbzProWeeklyHistory> toDomain(List<IbzProWeeklyHistoryDTO> dtoList) {
        List<IbzProWeeklyHistory> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IbzProWeeklyHistoryDTO> toDto(List<IbzProWeeklyHistory> entityList) {
        List<IbzProWeeklyHistoryDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
