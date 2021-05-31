package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProductWeekly;
import cn.ibizlab.pms.webapi.dto.IbizproProductWeeklyDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIbizproProductWeeklyMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IbizproProductWeeklyMappingDecorator.class)
public interface IbizproProductWeeklyMapping extends MappingBase<IbizproProductWeeklyDTO, IbizproProductWeekly> {


}

class IbizproProductWeeklyMappingDecorator implements IbizproProductWeeklyMapping {

    @Autowired
    @Qualifier("delegate")
    private IbizproProductWeeklyMapping delegate;

    @Override
    public IbizproProductWeekly toDomain(IbizproProductWeeklyDTO dto) {
        IbizproProductWeekly domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IbizproProductWeeklyDTO toDto(IbizproProductWeekly entity) {

        IbizproProductWeeklyDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbizproProductWeekly> toDomain(List<IbizproProductWeeklyDTO> dtoList) {
        List<IbizproProductWeekly> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IbizproProductWeeklyDTO> toDto(List<IbizproProductWeekly> entityList) {
        List<IbizproProductWeeklyDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
