package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.zentao.domain.Case;
import cn.ibizlab.pms.webapi.dto.CaseDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiCaseMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(CaseMappingDecorator.class)
public interface CaseMapping extends MappingBase<CaseDTO, Case> {


}

class CaseMappingDecorator implements CaseMapping {

    @Autowired
    @Qualifier("delegate")
    private CaseMapping delegate;

    @Override
    public Case toDomain(CaseDTO dto) {
        Case domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public CaseDTO toDto(Case entity) {

        CaseDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<Case> toDomain(List<CaseDTO> dtoList) {
        List<Case> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<CaseDTO> toDto(List<Case> entityList) {
        List<CaseDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
