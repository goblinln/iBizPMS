package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IBZProWeeklyAction;
import cn.ibizlab.pms.webapi.dto.IBZProWeeklyActionDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIBZProWeeklyActionMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IBZProWeeklyActionMappingDecorator.class)
public interface IBZProWeeklyActionMapping extends MappingBase<IBZProWeeklyActionDTO, IBZProWeeklyAction> {


}

class IBZProWeeklyActionMappingDecorator implements IBZProWeeklyActionMapping {

    @Autowired
    @Qualifier("delegate")
    private IBZProWeeklyActionMapping delegate;

    @Override
    public IBZProWeeklyAction toDomain(IBZProWeeklyActionDTO dto) {
        IBZProWeeklyAction domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IBZProWeeklyActionDTO toDto(IBZProWeeklyAction entity) {

        IBZProWeeklyActionDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IBZProWeeklyAction> toDomain(List<IBZProWeeklyActionDTO> dtoList) {
        List<IBZProWeeklyAction> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IBZProWeeklyActionDTO> toDto(List<IBZProWeeklyAction> entityList) {
        List<IBZProWeeklyActionDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
