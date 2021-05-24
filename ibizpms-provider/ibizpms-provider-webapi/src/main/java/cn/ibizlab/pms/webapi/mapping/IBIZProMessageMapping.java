package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibizplugin.domain.IBIZProMessage;
import cn.ibizlab.pms.webapi.dto.IBIZProMessageDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIBIZProMessageMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IBIZProMessageMappingDecorator.class)
public interface IBIZProMessageMapping extends MappingBase<IBIZProMessageDTO, IBIZProMessage> {


}

class IBIZProMessageMappingDecorator implements IBIZProMessageMapping {

    @Autowired
    @Qualifier("delegate")
    private IBIZProMessageMapping delegate;

    @Override
    public IBIZProMessage toDomain(IBIZProMessageDTO dto) {
        IBIZProMessage domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IBIZProMessageDTO toDto(IBIZProMessage entity) {

        IBIZProMessageDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IBIZProMessage> toDomain(List<IBIZProMessageDTO> dtoList) {
        List<IBIZProMessage> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IBIZProMessageDTO> toDto(List<IBIZProMessage> entityList) {
        List<IBIZProMessageDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
