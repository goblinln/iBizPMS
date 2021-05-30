package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProductMonthly;
import cn.ibizlab.pms.webapi.dto.IbizproProductMonthlyDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIbizproProductMonthlyMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IbizproProductMonthlyMappingDecorator.class)
public interface IbizproProductMonthlyMapping extends MappingBase<IbizproProductMonthlyDTO, IbizproProductMonthly> {


}

class IbizproProductMonthlyMappingDecorator implements IbizproProductMonthlyMapping {

    @Autowired
    @Qualifier("delegate")
    private IbizproProductMonthlyMapping delegate;

    @Override
    public IbizproProductMonthly toDomain(IbizproProductMonthlyDTO dto) {
        IbizproProductMonthly domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IbizproProductMonthlyDTO toDto(IbizproProductMonthly entity) {

        IbizproProductMonthlyDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbizproProductMonthly> toDomain(List<IbizproProductMonthlyDTO> dtoList) {
        List<IbizproProductMonthly> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IbizproProductMonthlyDTO> toDto(List<IbizproProductMonthly> entityList) {
        List<IbizproProductMonthlyDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
