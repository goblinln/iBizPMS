package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IBZCaseAction;
import cn.ibizlab.pms.webapi.dto.IBZCaseActionDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIBZCaseActionMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IBZCaseActionMappingDecorator.class)
public interface IBZCaseActionMapping extends MappingBase<IBZCaseActionDTO, IBZCaseAction> {


}

class IBZCaseActionMappingDecorator implements IBZCaseActionMapping {

    @Autowired
    @Qualifier("delegate")
    private IBZCaseActionMapping delegate;

    @Override
    public IBZCaseAction toDomain(IBZCaseActionDTO dto) {
        IBZCaseAction domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IBZCaseActionDTO toDto(IBZCaseAction entity) {

        IBZCaseActionDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IBZCaseAction> toDomain(List<IBZCaseActionDTO> dtoList) {
        List<IBZCaseAction> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IBZCaseActionDTO> toDto(List<IBZCaseAction> entityList) {
        List<IBZCaseActionDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
