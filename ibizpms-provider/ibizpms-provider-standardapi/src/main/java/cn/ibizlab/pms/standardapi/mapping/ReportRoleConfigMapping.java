package cn.ibizlab.pms.standardapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.report.domain.IbzReportRoleConfig;
import cn.ibizlab.pms.standardapi.dto.ReportRoleConfigDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "StandardAPIReportRoleConfigMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(ReportRoleConfigMappingDecorator.class)
public interface ReportRoleConfigMapping extends MappingBase<ReportRoleConfigDTO, IbzReportRoleConfig> {


}

class ReportRoleConfigMappingDecorator implements ReportRoleConfigMapping {

    @Autowired
    @Qualifier("delegate")
    private ReportRoleConfigMapping delegate;

    @Override
    public IbzReportRoleConfig toDomain(ReportRoleConfigDTO dto) {
        IbzReportRoleConfig domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public ReportRoleConfigDTO toDto(IbzReportRoleConfig entity) {

        ReportRoleConfigDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbzReportRoleConfig> toDomain(List<ReportRoleConfigDTO> dtoList) {
        List<IbzReportRoleConfig> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<ReportRoleConfigDTO> toDto(List<IbzReportRoleConfig> entityList) {
        List<ReportRoleConfigDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
