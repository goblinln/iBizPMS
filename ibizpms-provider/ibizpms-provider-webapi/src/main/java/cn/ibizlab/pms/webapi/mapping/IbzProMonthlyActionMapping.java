package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IbzProMonthlyAction;
import cn.ibizlab.pms.webapi.dto.IbzProMonthlyActionDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIbzProMonthlyActionMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IbzProMonthlyActionMappingDecorator.class)
public interface IbzProMonthlyActionMapping extends MappingBase<IbzProMonthlyActionDTO, IbzProMonthlyAction> {


}

class IbzProMonthlyActionMappingDecorator implements IbzProMonthlyActionMapping {

    @Autowired
    @Qualifier("delegate")
    private IbzProMonthlyActionMapping delegate;

    @Override
    public IbzProMonthlyAction toDomain(IbzProMonthlyActionDTO dto) {
        IbzProMonthlyAction domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IbzProMonthlyActionDTO toDto(IbzProMonthlyAction entity) {

        IbzProMonthlyActionDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbzProMonthlyAction> toDomain(List<IbzProMonthlyActionDTO> dtoList) {
        List<IbzProMonthlyAction> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IbzProMonthlyActionDTO> toDto(List<IbzProMonthlyAction> entityList) {
        List<IbzProMonthlyActionDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
