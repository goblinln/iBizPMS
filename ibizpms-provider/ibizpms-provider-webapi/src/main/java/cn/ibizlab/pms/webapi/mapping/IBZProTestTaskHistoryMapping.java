package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IBZProTestTaskHistory;
import cn.ibizlab.pms.webapi.dto.IBZProTestTaskHistoryDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIBZProTestTaskHistoryMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IBZProTestTaskHistoryMappingDecorator.class)
public interface IBZProTestTaskHistoryMapping extends MappingBase<IBZProTestTaskHistoryDTO, IBZProTestTaskHistory> {


}

class IBZProTestTaskHistoryMappingDecorator implements IBZProTestTaskHistoryMapping {

    @Autowired
    @Qualifier("delegate")
    private IBZProTestTaskHistoryMapping delegate;

    @Override
    public IBZProTestTaskHistory toDomain(IBZProTestTaskHistoryDTO dto) {
        IBZProTestTaskHistory domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IBZProTestTaskHistoryDTO toDto(IBZProTestTaskHistory entity) {

        IBZProTestTaskHistoryDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IBZProTestTaskHistory> toDomain(List<IBZProTestTaskHistoryDTO> dtoList) {
        List<IBZProTestTaskHistory> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IBZProTestTaskHistoryDTO> toDto(List<IBZProTestTaskHistory> entityList) {
        List<IBZProTestTaskHistoryDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
