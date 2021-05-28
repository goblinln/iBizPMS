package cn.ibizlab.pms.standardapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.zentao.domain.TestTask;
import cn.ibizlab.pms.standardapi.dto.ProjectTestTaskDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "StandardAPIProjectTestTaskMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(ProjectTestTaskMappingDecorator.class)
public interface ProjectTestTaskMapping extends MappingBase<ProjectTestTaskDTO, TestTask> {


}

class ProjectTestTaskMappingDecorator implements ProjectTestTaskMapping {

    @Autowired
    @Qualifier("delegate")
    private ProjectTestTaskMapping delegate;

    @Override
    public TestTask toDomain(ProjectTestTaskDTO dto) {
        TestTask domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public ProjectTestTaskDTO toDto(TestTask entity) {

        ProjectTestTaskDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<TestTask> toDomain(List<ProjectTestTaskDTO> dtoList) {
        List<TestTask> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<ProjectTestTaskDTO> toDto(List<TestTask> entityList) {
        List<ProjectTestTaskDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
