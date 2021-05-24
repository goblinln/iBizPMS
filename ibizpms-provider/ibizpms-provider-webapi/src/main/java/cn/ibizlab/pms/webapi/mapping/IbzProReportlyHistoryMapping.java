package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IbzProReportlyHistory;
import cn.ibizlab.pms.webapi.dto.IbzProReportlyHistoryDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIbzProReportlyHistoryMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IbzProReportlyHistoryMappingDecorator.class)
public interface IbzProReportlyHistoryMapping extends MappingBase<IbzProReportlyHistoryDTO, IbzProReportlyHistory> {


}

class IbzProReportlyHistoryMappingDecorator implements IbzProReportlyHistoryMapping {

    @Autowired
    @Qualifier("delegate")
    private IbzProReportlyHistoryMapping delegate;

    @Override
    public IbzProReportlyHistory toDomain(IbzProReportlyHistoryDTO dto) {
        IbzProReportlyHistory domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IbzProReportlyHistoryDTO toDto(IbzProReportlyHistory entity) {

        IbzProReportlyHistoryDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbzProReportlyHistory> toDomain(List<IbzProReportlyHistoryDTO> dtoList) {
        List<IbzProReportlyHistory> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IbzProReportlyHistoryDTO> toDto(List<IbzProReportlyHistory> entityList) {
        List<IbzProReportlyHistoryDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
