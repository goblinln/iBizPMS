package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IBZStoryAction;
import cn.ibizlab.pms.webapi.dto.IBZStoryActionDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIBZStoryActionMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IBZStoryActionMappingDecorator.class)
public interface IBZStoryActionMapping extends MappingBase<IBZStoryActionDTO, IBZStoryAction> {


}

class IBZStoryActionMappingDecorator implements IBZStoryActionMapping {

    @Autowired
    @Qualifier("delegate")
    private IBZStoryActionMapping delegate;

    @Override
    public IBZStoryAction toDomain(IBZStoryActionDTO dto) {
        IBZStoryAction domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IBZStoryActionDTO toDto(IBZStoryAction entity) {

        IBZStoryActionDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IBZStoryAction> toDomain(List<IBZStoryActionDTO> dtoList) {
        List<IBZStoryAction> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IBZStoryActionDTO> toDto(List<IBZStoryAction> entityList) {
        List<IBZStoryActionDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
