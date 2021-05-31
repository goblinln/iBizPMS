package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.zentao.domain.SuiteCase;
import cn.ibizlab.pms.webapi.dto.SuiteCaseDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiSuiteCaseMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(SuiteCaseMappingDecorator.class)
public interface SuiteCaseMapping extends MappingBase<SuiteCaseDTO, SuiteCase> {


}

class SuiteCaseMappingDecorator implements SuiteCaseMapping {

    @Autowired
    @Qualifier("delegate")
    private SuiteCaseMapping delegate;

    @Override
    public SuiteCase toDomain(SuiteCaseDTO dto) {
        SuiteCase domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public SuiteCaseDTO toDto(SuiteCase entity) {

        SuiteCaseDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<SuiteCase> toDomain(List<SuiteCaseDTO> dtoList) {
        List<SuiteCase> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<SuiteCaseDTO> toDto(List<SuiteCase> entityList) {
        List<SuiteCaseDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
