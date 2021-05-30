package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IbzLibModule;
import cn.ibizlab.pms.webapi.dto.IbzLibModuleDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIbzLibModuleMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IbzLibModuleMappingDecorator.class)
public interface IbzLibModuleMapping extends MappingBase<IbzLibModuleDTO, IbzLibModule> {


}

class IbzLibModuleMappingDecorator implements IbzLibModuleMapping {

    @Autowired
    @Qualifier("delegate")
    private IbzLibModuleMapping delegate;

    @Override
    public IbzLibModule toDomain(IbzLibModuleDTO dto) {
        IbzLibModule domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IbzLibModuleDTO toDto(IbzLibModule entity) {

        IbzLibModuleDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbzLibModule> toDomain(List<IbzLibModuleDTO> dtoList) {
        List<IbzLibModule> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IbzLibModuleDTO> toDto(List<IbzLibModule> entityList) {
        List<IbzLibModuleDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
