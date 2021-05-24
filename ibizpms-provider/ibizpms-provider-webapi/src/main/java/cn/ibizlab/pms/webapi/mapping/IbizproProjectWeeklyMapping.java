package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProjectWeekly;
import cn.ibizlab.pms.webapi.dto.IbizproProjectWeeklyDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIbizproProjectWeeklyMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IbizproProjectWeeklyMappingDecorator.class)
public interface IbizproProjectWeeklyMapping extends MappingBase<IbizproProjectWeeklyDTO, IbizproProjectWeekly> {


}

class IbizproProjectWeeklyMappingDecorator implements IbizproProjectWeeklyMapping {

    @Autowired
    @Qualifier("delegate")
    private IbizproProjectWeeklyMapping delegate;

    @Override
    public IbizproProjectWeekly toDomain(IbizproProjectWeeklyDTO dto) {
        IbizproProjectWeekly domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IbizproProjectWeeklyDTO toDto(IbizproProjectWeekly entity) {

        IbizproProjectWeeklyDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbizproProjectWeekly> toDomain(List<IbizproProjectWeeklyDTO> dtoList) {
        List<IbizproProjectWeekly> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IbizproProjectWeeklyDTO> toDto(List<IbizproProjectWeekly> entityList) {
        List<IbizproProjectWeeklyDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
