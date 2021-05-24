package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibizplugin.domain.IBIZProPlugin;
import cn.ibizlab.pms.webapi.dto.IBIZProPluginDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIBIZProPluginMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IBIZProPluginMappingDecorator.class)
public interface IBIZProPluginMapping extends MappingBase<IBIZProPluginDTO, IBIZProPlugin> {


}

class IBIZProPluginMappingDecorator implements IBIZProPluginMapping {

    @Autowired
    @Qualifier("delegate")
    private IBIZProPluginMapping delegate;

    @Override
    public IBIZProPlugin toDomain(IBIZProPluginDTO dto) {
        IBIZProPlugin domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IBIZProPluginDTO toDto(IBIZProPlugin entity) {

        IBIZProPluginDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IBIZProPlugin> toDomain(List<IBIZProPluginDTO> dtoList) {
        List<IBIZProPlugin> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IBIZProPluginDTO> toDto(List<IBIZProPlugin> entityList) {
        List<IBIZProPluginDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
