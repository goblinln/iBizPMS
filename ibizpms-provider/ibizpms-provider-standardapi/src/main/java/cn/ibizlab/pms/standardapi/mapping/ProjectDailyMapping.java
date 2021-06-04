package cn.ibizlab.pms.standardapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProjectDaily;
import cn.ibizlab.pms.standardapi.dto.ProjectDailyDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "StandardAPIProjectDailyMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(ProjectDailyMappingDecorator.class)
public interface ProjectDailyMapping extends MappingBase<ProjectDailyDTO, IbizproProjectDaily> {


}

class ProjectDailyMappingDecorator implements ProjectDailyMapping {

    @Autowired
    @Qualifier("delegate")
    private ProjectDailyMapping delegate;

    @Override
    public IbizproProjectDaily toDomain(ProjectDailyDTO dto) {
        IbizproProjectDaily domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public ProjectDailyDTO toDto(IbizproProjectDaily entity) {

        ProjectDailyDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbizproProjectDaily> toDomain(List<ProjectDailyDTO> dtoList) {
        List<IbizproProjectDaily> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<ProjectDailyDTO> toDto(List<IbizproProjectDaily> entityList) {
        List<ProjectDailyDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
