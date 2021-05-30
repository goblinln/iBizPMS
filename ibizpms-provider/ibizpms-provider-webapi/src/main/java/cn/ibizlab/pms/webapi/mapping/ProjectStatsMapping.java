package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.ProjectStats;
import cn.ibizlab.pms.webapi.dto.ProjectStatsDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiProjectStatsMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(ProjectStatsMappingDecorator.class)
public interface ProjectStatsMapping extends MappingBase<ProjectStatsDTO, ProjectStats> {


}

class ProjectStatsMappingDecorator implements ProjectStatsMapping {

    @Autowired
    @Qualifier("delegate")
    private ProjectStatsMapping delegate;

    @Override
    public ProjectStats toDomain(ProjectStatsDTO dto) {
        ProjectStats domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public ProjectStatsDTO toDto(ProjectStats entity) {

        ProjectStatsDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<ProjectStats> toDomain(List<ProjectStatsDTO> dtoList) {
        List<ProjectStats> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<ProjectStatsDTO> toDto(List<ProjectStats> entityList) {
        List<ProjectStatsDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
