package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibizpro.domain.IbzproProjectUserTask;
import cn.ibizlab.pms.webapi.dto.IbzproProjectUserTaskDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIbzproProjectUserTaskMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IbzproProjectUserTaskMappingDecorator.class)
public interface IbzproProjectUserTaskMapping extends MappingBase<IbzproProjectUserTaskDTO, IbzproProjectUserTask> {


}

class IbzproProjectUserTaskMappingDecorator implements IbzproProjectUserTaskMapping {

    @Autowired
    @Qualifier("delegate")
    private IbzproProjectUserTaskMapping delegate;

    @Override
    public IbzproProjectUserTask toDomain(IbzproProjectUserTaskDTO dto) {
        IbzproProjectUserTask domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IbzproProjectUserTaskDTO toDto(IbzproProjectUserTask entity) {

        IbzproProjectUserTaskDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbzproProjectUserTask> toDomain(List<IbzproProjectUserTaskDTO> dtoList) {
        List<IbzproProjectUserTask> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IbzproProjectUserTaskDTO> toDto(List<IbzproProjectUserTask> entityList) {
        List<IbzproProjectUserTaskDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
