package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.CaseStats;
import cn.ibizlab.pms.webapi.dto.CaseStatsDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiCaseStatsMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(CaseStatsMappingDecorator.class)
public interface CaseStatsMapping extends MappingBase<CaseStatsDTO, CaseStats> {


}

class CaseStatsMappingDecorator implements CaseStatsMapping {

    @Autowired
    @Qualifier("delegate")
    private CaseStatsMapping delegate;

    @Override
    public CaseStats toDomain(CaseStatsDTO dto) {
        CaseStats domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public CaseStatsDTO toDto(CaseStats entity) {

        CaseStatsDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<CaseStats> toDomain(List<CaseStatsDTO> dtoList) {
        List<CaseStats> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<CaseStatsDTO> toDto(List<CaseStats> entityList) {
        List<CaseStatsDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
