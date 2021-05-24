package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IbzLibCaseSteps;
import cn.ibizlab.pms.webapi.dto.IbzLibCaseStepTmpDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIbzLibCaseStepTmpMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IbzLibCaseStepTmpMappingDecorator.class)
public interface IbzLibCaseStepTmpMapping extends MappingBase<IbzLibCaseStepTmpDTO, IbzLibCaseSteps> {


}

class IbzLibCaseStepTmpMappingDecorator implements IbzLibCaseStepTmpMapping {

    @Autowired
    @Qualifier("delegate")
    private IbzLibCaseStepTmpMapping delegate;

    @Override
    public IbzLibCaseSteps toDomain(IbzLibCaseStepTmpDTO dto) {
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
    public IbzLibCaseStepTmpDTO toDto(IbzLibCaseSteps entity) {

        IbzLibCaseStepTmpDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbzLibCaseSteps> toDomain(List<IbzLibCaseStepTmpDTO> dtoList) {
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
    public List<IbzLibCaseStepTmpDTO> toDto(List<IbzLibCaseSteps> entityList) {
        List<IbzLibCaseStepTmpDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
