package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibizpro.domain.IBZProStoryModule;
import cn.ibizlab.pms.webapi.dto.IBZProStoryModuleDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIBZProStoryModuleMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IBZProStoryModuleMappingDecorator.class)
public interface IBZProStoryModuleMapping extends MappingBase<IBZProStoryModuleDTO, IBZProStoryModule> {


}

class IBZProStoryModuleMappingDecorator implements IBZProStoryModuleMapping {

    @Autowired
    @Qualifier("delegate")
    private IBZProStoryModuleMapping delegate;

    @Override
    public IBZProStoryModule toDomain(IBZProStoryModuleDTO dto) {
        IBZProStoryModule domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IBZProStoryModuleDTO toDto(IBZProStoryModule entity) {

        IBZProStoryModuleDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IBZProStoryModule> toDomain(List<IBZProStoryModuleDTO> dtoList) {
        List<IBZProStoryModule> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IBZProStoryModuleDTO> toDto(List<IBZProStoryModule> entityList) {
        List<IBZProStoryModuleDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
