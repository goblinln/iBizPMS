package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IBZTestReportHistory;
import cn.ibizlab.pms.webapi.dto.IBZTestReportHistoryDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIBZTestReportHistoryMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IBZTestReportHistoryMappingDecorator.class)
public interface IBZTestReportHistoryMapping extends MappingBase<IBZTestReportHistoryDTO, IBZTestReportHistory> {


}

class IBZTestReportHistoryMappingDecorator implements IBZTestReportHistoryMapping {

    @Autowired
    @Qualifier("delegate")
    private IBZTestReportHistoryMapping delegate;

    @Override
    public IBZTestReportHistory toDomain(IBZTestReportHistoryDTO dto) {
        IBZTestReportHistory domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IBZTestReportHistoryDTO toDto(IBZTestReportHistory entity) {

        IBZTestReportHistoryDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IBZTestReportHistory> toDomain(List<IBZTestReportHistoryDTO> dtoList) {
        List<IBZTestReportHistory> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IBZTestReportHistoryDTO> toDto(List<IBZTestReportHistory> entityList) {
        List<IBZTestReportHistoryDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
