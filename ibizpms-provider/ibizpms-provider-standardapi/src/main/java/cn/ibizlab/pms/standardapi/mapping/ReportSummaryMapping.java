package cn.ibizlab.pms.standardapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.report.domain.IbzReport;
import cn.ibizlab.pms.standardapi.dto.ReportSummaryDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "StandardAPIReportSummaryMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(ReportSummaryMappingDecorator.class)
public interface ReportSummaryMapping extends MappingBase<ReportSummaryDTO, IbzReport> {


}

class ReportSummaryMappingDecorator implements ReportSummaryMapping {

    @Autowired
    @Qualifier("delegate")
    private ReportSummaryMapping delegate;

    @Override
    public IbzReport toDomain(ReportSummaryDTO dto) {
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
    public ReportSummaryDTO toDto(IbzReport entity) {

        ReportSummaryDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbzReport> toDomain(List<ReportSummaryDTO> dtoList) {
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
    public List<ReportSummaryDTO> toDto(List<IbzReport> entityList) {
        List<ReportSummaryDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
