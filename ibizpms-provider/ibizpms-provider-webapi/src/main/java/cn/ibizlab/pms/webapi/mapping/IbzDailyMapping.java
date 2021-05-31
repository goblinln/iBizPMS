package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.report.domain.IbzDaily;
import cn.ibizlab.pms.webapi.dto.IbzDailyDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIbzDailyMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IbzDailyMappingDecorator.class)
public interface IbzDailyMapping extends MappingBase<IbzDailyDTO, IbzDaily> {


}

class IbzDailyMappingDecorator implements IbzDailyMapping {

    @Autowired
    @Qualifier("delegate")
    private IbzDailyMapping delegate;

    @Override
    public IbzDaily toDomain(IbzDailyDTO dto) {
        IbzDaily domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IbzDailyDTO toDto(IbzDaily entity) {

        IbzDailyDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbzDaily> toDomain(List<IbzDailyDTO> dtoList) {
        List<IbzDaily> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IbzDailyDTO> toDto(List<IbzDaily> entityList) {
        List<IbzDailyDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
