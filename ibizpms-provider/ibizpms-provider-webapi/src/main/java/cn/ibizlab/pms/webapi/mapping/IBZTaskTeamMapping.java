package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.TaskTeam;
import cn.ibizlab.pms.webapi.dto.IBZTaskTeamDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIBZTaskTeamMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IBZTaskTeamMappingDecorator.class)
public interface IBZTaskTeamMapping extends MappingBase<IBZTaskTeamDTO, TaskTeam> {


}

class IBZTaskTeamMappingDecorator implements IBZTaskTeamMapping {

    @Autowired
    @Qualifier("delegate")
    private IBZTaskTeamMapping delegate;

    @Override
    public TaskTeam toDomain(IBZTaskTeamDTO dto) {
        TaskTeam domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IBZTaskTeamDTO toDto(TaskTeam entity) {

        IBZTaskTeamDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<TaskTeam> toDomain(List<IBZTaskTeamDTO> dtoList) {
        List<TaskTeam> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IBZTaskTeamDTO> toDto(List<TaskTeam> entityList) {
        List<IBZTaskTeamDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
