package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.EmpLoyeeload;
import cn.ibizlab.pms.webapi.dto.EmpLoyeeloadDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiEmpLoyeeloadMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(EmpLoyeeloadMappingDecorator.class)
public interface EmpLoyeeloadMapping extends MappingBase<EmpLoyeeloadDTO, EmpLoyeeload> {


}

class EmpLoyeeloadMappingDecorator implements EmpLoyeeloadMapping {

    @Autowired
    @Qualifier("delegate")
    private EmpLoyeeloadMapping delegate;

    @Override
    public EmpLoyeeload toDomain(EmpLoyeeloadDTO dto) {
        EmpLoyeeload domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public EmpLoyeeloadDTO toDto(EmpLoyeeload entity) {

        EmpLoyeeloadDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<EmpLoyeeload> toDomain(List<EmpLoyeeloadDTO> dtoList) {
        List<EmpLoyeeload> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<EmpLoyeeloadDTO> toDto(List<EmpLoyeeload> entityList) {
        List<EmpLoyeeloadDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
