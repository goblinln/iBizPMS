package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IbzProMonthlyHistory;
import cn.ibizlab.pms.webapi.dto.IbzProMonthlyHistoryDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIbzProMonthlyHistoryMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IbzProMonthlyHistoryMappingDecorator.class)
public interface IbzProMonthlyHistoryMapping extends MappingBase<IbzProMonthlyHistoryDTO, IbzProMonthlyHistory> {


}

class IbzProMonthlyHistoryMappingDecorator implements IbzProMonthlyHistoryMapping {

    @Autowired
    @Qualifier("delegate")
    private IbzProMonthlyHistoryMapping delegate;

    @Override
    public IbzProMonthlyHistory toDomain(IbzProMonthlyHistoryDTO dto) {
        IbzProMonthlyHistory domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IbzProMonthlyHistoryDTO toDto(IbzProMonthlyHistory entity) {

        IbzProMonthlyHistoryDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbzProMonthlyHistory> toDomain(List<IbzProMonthlyHistoryDTO> dtoList) {
        List<IbzProMonthlyHistory> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IbzProMonthlyHistoryDTO> toDto(List<IbzProMonthlyHistory> entityList) {
        List<IbzProMonthlyHistoryDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
