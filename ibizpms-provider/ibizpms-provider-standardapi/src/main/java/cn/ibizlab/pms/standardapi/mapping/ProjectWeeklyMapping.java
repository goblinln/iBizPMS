package cn.ibizlab.pms.standardapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProjectWeekly;
import cn.ibizlab.pms.standardapi.dto.ProjectWeeklyDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "StandardAPIProjectWeeklyMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(ProjectWeeklyMappingDecorator.class)
public interface ProjectWeeklyMapping extends MappingBase<ProjectWeeklyDTO, IbizproProjectWeekly> {


}

class ProjectWeeklyMappingDecorator implements ProjectWeeklyMapping {

    @Autowired
    @Qualifier("delegate")
    private ProjectWeeklyMapping delegate;

    @Override
    public IbizproProjectWeekly toDomain(ProjectWeeklyDTO dto) {
        IbizproProjectWeekly domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public ProjectWeeklyDTO toDto(IbizproProjectWeekly entity) {

        ProjectWeeklyDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbizproProjectWeekly> toDomain(List<ProjectWeeklyDTO> dtoList) {
        List<IbizproProjectWeekly> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<ProjectWeeklyDTO> toDto(List<IbizproProjectWeekly> entityList) {
        List<ProjectWeeklyDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
