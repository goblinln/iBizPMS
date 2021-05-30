package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.report.domain.IbzReport;
import cn.ibizlab.pms.webapi.dto.IbzReportDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIbzReportMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IbzReportMappingDecorator.class)
public interface IbzReportMapping extends MappingBase<IbzReportDTO, IbzReport> {


}

class IbzReportMappingDecorator implements IbzReportMapping {

    @Autowired
    @Qualifier("delegate")
    private IbzReportMapping delegate;

    @Override
    public IbzReport toDomain(IbzReportDTO dto) {
        IbzReport domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IbzReportDTO toDto(IbzReport entity) {

        IbzReportDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbzReport> toDomain(List<IbzReportDTO> dtoList) {
        List<IbzReport> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IbzReportDTO> toDto(List<IbzReport> entityList) {
        List<IbzReportDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
