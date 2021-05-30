package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProjectMonthly;
import cn.ibizlab.pms.webapi.dto.IbizproProjectMonthlyDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIbizproProjectMonthlyMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IbizproProjectMonthlyMappingDecorator.class)
public interface IbizproProjectMonthlyMapping extends MappingBase<IbizproProjectMonthlyDTO, IbizproProjectMonthly> {


}

class IbizproProjectMonthlyMappingDecorator implements IbizproProjectMonthlyMapping {

    @Autowired
    @Qualifier("delegate")
    private IbizproProjectMonthlyMapping delegate;

    @Override
    public IbizproProjectMonthly toDomain(IbizproProjectMonthlyDTO dto) {
        IbizproProjectMonthly domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IbizproProjectMonthlyDTO toDto(IbizproProjectMonthly entity) {

        IbizproProjectMonthlyDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbizproProjectMonthly> toDomain(List<IbizproProjectMonthlyDTO> dtoList) {
        List<IbizproProjectMonthly> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IbizproProjectMonthlyDTO> toDto(List<IbizproProjectMonthly> entityList) {
        List<IbizproProjectMonthlyDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
