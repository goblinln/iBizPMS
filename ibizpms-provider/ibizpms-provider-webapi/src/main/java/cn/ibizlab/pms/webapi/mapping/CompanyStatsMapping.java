package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.CompanyStats;
import cn.ibizlab.pms.webapi.dto.CompanyStatsDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiCompanyStatsMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(CompanyStatsMappingDecorator.class)
public interface CompanyStatsMapping extends MappingBase<CompanyStatsDTO, CompanyStats> {


}

class CompanyStatsMappingDecorator implements CompanyStatsMapping {

    @Autowired
    @Qualifier("delegate")
    private CompanyStatsMapping delegate;

    @Override
    public CompanyStats toDomain(CompanyStatsDTO dto) {
        CompanyStats domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public CompanyStatsDTO toDto(CompanyStats entity) {

        CompanyStatsDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<CompanyStats> toDomain(List<CompanyStatsDTO> dtoList) {
        List<CompanyStats> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<CompanyStatsDTO> toDto(List<CompanyStats> entityList) {
        List<CompanyStatsDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
