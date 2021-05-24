package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IbzLibCaseSteps;
import cn.ibizlab.pms.webapi.dto.IbzLibCaseStepsDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIbzLibCaseStepsMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IbzLibCaseStepsMappingDecorator.class)
public interface IbzLibCaseStepsMapping extends MappingBase<IbzLibCaseStepsDTO, IbzLibCaseSteps> {


}

class IbzLibCaseStepsMappingDecorator implements IbzLibCaseStepsMapping {

    @Autowired
    @Qualifier("delegate")
    private IbzLibCaseStepsMapping delegate;

    @Override
    public IbzLibCaseSteps toDomain(IbzLibCaseStepsDTO dto) {
        IbzLibCaseSteps domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IbzLibCaseStepsDTO toDto(IbzLibCaseSteps entity) {

        IbzLibCaseStepsDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbzLibCaseSteps> toDomain(List<IbzLibCaseStepsDTO> dtoList) {
        List<IbzLibCaseSteps> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IbzLibCaseStepsDTO> toDto(List<IbzLibCaseSteps> entityList) {
        List<IbzLibCaseStepsDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
