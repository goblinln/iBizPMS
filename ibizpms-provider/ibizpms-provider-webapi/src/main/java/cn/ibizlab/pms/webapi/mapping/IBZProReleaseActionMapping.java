package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IBZProReleaseAction;
import cn.ibizlab.pms.webapi.dto.IBZProReleaseActionDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIBZProReleaseActionMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IBZProReleaseActionMappingDecorator.class)
public interface IBZProReleaseActionMapping extends MappingBase<IBZProReleaseActionDTO, IBZProReleaseAction> {


}

class IBZProReleaseActionMappingDecorator implements IBZProReleaseActionMapping {

    @Autowired
    @Qualifier("delegate")
    private IBZProReleaseActionMapping delegate;

    @Override
    public IBZProReleaseAction toDomain(IBZProReleaseActionDTO dto) {
        IBZProReleaseAction domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IBZProReleaseActionDTO toDto(IBZProReleaseAction entity) {

        IBZProReleaseActionDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IBZProReleaseAction> toDomain(List<IBZProReleaseActionDTO> dtoList) {
        List<IBZProReleaseAction> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IBZProReleaseActionDTO> toDto(List<IBZProReleaseAction> entityList) {
        List<IBZProReleaseActionDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
