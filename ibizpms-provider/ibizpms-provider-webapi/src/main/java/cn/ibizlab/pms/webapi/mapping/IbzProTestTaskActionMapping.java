package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IbzProTestTaskAction;
import cn.ibizlab.pms.webapi.dto.IbzProTestTaskActionDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIbzProTestTaskActionMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IbzProTestTaskActionMappingDecorator.class)
public interface IbzProTestTaskActionMapping extends MappingBase<IbzProTestTaskActionDTO, IbzProTestTaskAction> {


}

class IbzProTestTaskActionMappingDecorator implements IbzProTestTaskActionMapping {

    @Autowired
    @Qualifier("delegate")
    private IbzProTestTaskActionMapping delegate;

    @Override
    public IbzProTestTaskAction toDomain(IbzProTestTaskActionDTO dto) {
        IbzProTestTaskAction domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IbzProTestTaskActionDTO toDto(IbzProTestTaskAction entity) {

        IbzProTestTaskActionDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbzProTestTaskAction> toDomain(List<IbzProTestTaskActionDTO> dtoList) {
        List<IbzProTestTaskAction> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IbzProTestTaskActionDTO> toDto(List<IbzProTestTaskAction> entityList) {
        List<IbzProTestTaskActionDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
