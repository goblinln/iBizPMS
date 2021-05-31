package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.zentao.domain.CaseStep;
import cn.ibizlab.pms.webapi.dto.IBZCaseStepDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIBZCaseStepMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IBZCaseStepMappingDecorator.class)
public interface IBZCaseStepMapping extends MappingBase<IBZCaseStepDTO, CaseStep> {


}

class IBZCaseStepMappingDecorator implements IBZCaseStepMapping {

    @Autowired
    @Qualifier("delegate")
    private IBZCaseStepMapping delegate;

    @Override
    public CaseStep toDomain(IBZCaseStepDTO dto) {
        CaseStep domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IBZCaseStepDTO toDto(CaseStep entity) {

        IBZCaseStepDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<CaseStep> toDomain(List<IBZCaseStepDTO> dtoList) {
        List<CaseStep> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IBZCaseStepDTO> toDto(List<CaseStep> entityList) {
        List<IBZCaseStepDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
