package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.DocLibModule;
import cn.ibizlab.pms.webapi.dto.DocLibModuleDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiDocLibModuleMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(DocLibModuleMappingDecorator.class)
public interface DocLibModuleMapping extends MappingBase<DocLibModuleDTO, DocLibModule> {


}

class DocLibModuleMappingDecorator implements DocLibModuleMapping {

    @Autowired
    @Qualifier("delegate")
    private DocLibModuleMapping delegate;

    @Override
    public DocLibModule toDomain(DocLibModuleDTO dto) {
        DocLibModule domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public DocLibModuleDTO toDto(DocLibModule entity) {

        DocLibModuleDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<DocLibModule> toDomain(List<DocLibModuleDTO> dtoList) {
        List<DocLibModule> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<DocLibModuleDTO> toDto(List<DocLibModule> entityList) {
        List<DocLibModuleDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
