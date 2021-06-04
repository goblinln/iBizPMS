package cn.ibizlab.pms.standardapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProjectMonthly;
import cn.ibizlab.pms.standardapi.dto.ProjectMonthlyDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "StandardAPIProjectMonthlyMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(ProjectMonthlyMappingDecorator.class)
public interface ProjectMonthlyMapping extends MappingBase<ProjectMonthlyDTO, IbizproProjectMonthly> {


}

class ProjectMonthlyMappingDecorator implements ProjectMonthlyMapping {

    @Autowired
    @Qualifier("delegate")
    private ProjectMonthlyMapping delegate;

    @Override
    public IbizproProjectMonthly toDomain(ProjectMonthlyDTO dto) {
        IbizproProjectMonthly domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public ProjectMonthlyDTO toDto(IbizproProjectMonthly entity) {

        ProjectMonthlyDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbizproProjectMonthly> toDomain(List<ProjectMonthlyDTO> dtoList) {
        List<IbizproProjectMonthly> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<ProjectMonthlyDTO> toDto(List<IbizproProjectMonthly> entityList) {
        List<ProjectMonthlyDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
