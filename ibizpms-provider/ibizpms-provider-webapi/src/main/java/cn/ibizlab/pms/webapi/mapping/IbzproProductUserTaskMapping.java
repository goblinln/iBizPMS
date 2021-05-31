package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibizpro.domain.IbzproProductUserTask;
import cn.ibizlab.pms.webapi.dto.IbzproProductUserTaskDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIbzproProductUserTaskMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IbzproProductUserTaskMappingDecorator.class)
public interface IbzproProductUserTaskMapping extends MappingBase<IbzproProductUserTaskDTO, IbzproProductUserTask> {


}

class IbzproProductUserTaskMappingDecorator implements IbzproProductUserTaskMapping {

    @Autowired
    @Qualifier("delegate")
    private IbzproProductUserTaskMapping delegate;

    @Override
    public IbzproProductUserTask toDomain(IbzproProductUserTaskDTO dto) {
        IbzproProductUserTask domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IbzproProductUserTaskDTO toDto(IbzproProductUserTask entity) {

        IbzproProductUserTaskDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbzproProductUserTask> toDomain(List<IbzproProductUserTaskDTO> dtoList) {
        List<IbzproProductUserTask> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IbzproProductUserTaskDTO> toDto(List<IbzproProductUserTask> entityList) {
        List<IbzproProductUserTaskDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
