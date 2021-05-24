package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IBZCaseHistory;
import cn.ibizlab.pms.webapi.dto.IBZCaseHistoryDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIBZCaseHistoryMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IBZCaseHistoryMappingDecorator.class)
public interface IBZCaseHistoryMapping extends MappingBase<IBZCaseHistoryDTO, IBZCaseHistory> {


}

class IBZCaseHistoryMappingDecorator implements IBZCaseHistoryMapping {

    @Autowired
    @Qualifier("delegate")
    private IBZCaseHistoryMapping delegate;

    @Override
    public IBZCaseHistory toDomain(IBZCaseHistoryDTO dto) {
        IBZCaseHistory domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IBZCaseHistoryDTO toDto(IBZCaseHistory entity) {

        IBZCaseHistoryDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IBZCaseHistory> toDomain(List<IBZCaseHistoryDTO> dtoList) {
        List<IBZCaseHistory> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IBZCaseHistoryDTO> toDto(List<IBZCaseHistory> entityList) {
        List<IBZCaseHistoryDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
