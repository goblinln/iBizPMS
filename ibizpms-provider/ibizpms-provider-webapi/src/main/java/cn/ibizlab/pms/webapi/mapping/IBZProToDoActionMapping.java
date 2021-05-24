package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IBZProToDoAction;
import cn.ibizlab.pms.webapi.dto.IBZProToDoActionDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIBZProToDoActionMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IBZProToDoActionMappingDecorator.class)
public interface IBZProToDoActionMapping extends MappingBase<IBZProToDoActionDTO, IBZProToDoAction> {


}

class IBZProToDoActionMappingDecorator implements IBZProToDoActionMapping {

    @Autowired
    @Qualifier("delegate")
    private IBZProToDoActionMapping delegate;

    @Override
    public IBZProToDoAction toDomain(IBZProToDoActionDTO dto) {
        IBZProToDoAction domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IBZProToDoActionDTO toDto(IBZProToDoAction entity) {

        IBZProToDoActionDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IBZProToDoAction> toDomain(List<IBZProToDoActionDTO> dtoList) {
        List<IBZProToDoAction> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IBZProToDoActionDTO> toDto(List<IBZProToDoAction> entityList) {
        List<IBZProToDoActionDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
