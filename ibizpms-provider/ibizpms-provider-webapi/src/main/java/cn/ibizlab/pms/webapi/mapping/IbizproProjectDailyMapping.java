package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProjectDaily;
import cn.ibizlab.pms.webapi.dto.IbizproProjectDailyDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIbizproProjectDailyMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IbizproProjectDailyMappingDecorator.class)
public interface IbizproProjectDailyMapping extends MappingBase<IbizproProjectDailyDTO, IbizproProjectDaily> {


}

class IbizproProjectDailyMappingDecorator implements IbizproProjectDailyMapping {

    @Autowired
    @Qualifier("delegate")
    private IbizproProjectDailyMapping delegate;

    @Override
    public IbizproProjectDaily toDomain(IbizproProjectDailyDTO dto) {
        IbizproProjectDaily domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IbizproProjectDailyDTO toDto(IbizproProjectDaily entity) {

        IbizproProjectDailyDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbizproProjectDaily> toDomain(List<IbizproProjectDailyDTO> dtoList) {
        List<IbizproProjectDaily> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IbizproProjectDailyDTO> toDto(List<IbizproProjectDaily> entityList) {
        List<IbizproProjectDailyDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
