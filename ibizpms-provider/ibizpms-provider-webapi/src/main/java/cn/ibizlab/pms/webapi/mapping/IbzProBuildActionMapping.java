package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IbzProBuildAction;
import cn.ibizlab.pms.webapi.dto.IbzProBuildActionDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIbzProBuildActionMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IbzProBuildActionMappingDecorator.class)
public interface IbzProBuildActionMapping extends MappingBase<IbzProBuildActionDTO, IbzProBuildAction> {


}

class IbzProBuildActionMappingDecorator implements IbzProBuildActionMapping {

    @Autowired
    @Qualifier("delegate")
    private IbzProBuildActionMapping delegate;

    @Override
    public IbzProBuildAction toDomain(IbzProBuildActionDTO dto) {
        IbzProBuildAction domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IbzProBuildActionDTO toDto(IbzProBuildAction entity) {

        IbzProBuildActionDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbzProBuildAction> toDomain(List<IbzProBuildActionDTO> dtoList) {
        List<IbzProBuildAction> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IbzProBuildActionDTO> toDto(List<IbzProBuildAction> entityList) {
        List<IbzProBuildActionDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
