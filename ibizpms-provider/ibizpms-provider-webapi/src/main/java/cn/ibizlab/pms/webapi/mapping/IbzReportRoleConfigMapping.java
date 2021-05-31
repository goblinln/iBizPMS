package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.report.domain.IbzReportRoleConfig;
import cn.ibizlab.pms.webapi.dto.IbzReportRoleConfigDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIbzReportRoleConfigMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IbzReportRoleConfigMappingDecorator.class)
public interface IbzReportRoleConfigMapping extends MappingBase<IbzReportRoleConfigDTO, IbzReportRoleConfig> {


}

class IbzReportRoleConfigMappingDecorator implements IbzReportRoleConfigMapping {

    @Autowired
    @Qualifier("delegate")
    private IbzReportRoleConfigMapping delegate;

    @Override
    public IbzReportRoleConfig toDomain(IbzReportRoleConfigDTO dto) {
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
    public IbzReportRoleConfigDTO toDto(IbzReportRoleConfig entity) {

        IbzReportRoleConfigDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbzReportRoleConfig> toDomain(List<IbzReportRoleConfigDTO> dtoList) {
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
    public List<IbzReportRoleConfigDTO> toDto(List<IbzReportRoleConfig> entityList) {
        List<IbzReportRoleConfigDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
