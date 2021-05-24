package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IBZTaskHistory;
import cn.ibizlab.pms.webapi.dto.IBZTaskHistoryDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIBZTaskHistoryMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IBZTaskHistoryMappingDecorator.class)
public interface IBZTaskHistoryMapping extends MappingBase<IBZTaskHistoryDTO, IBZTaskHistory> {


}

class IBZTaskHistoryMappingDecorator implements IBZTaskHistoryMapping {

    @Autowired
    @Qualifier("delegate")
    private IBZTaskHistoryMapping delegate;

    @Override
    public IBZTaskHistory toDomain(IBZTaskHistoryDTO dto) {
        IBZTaskHistory domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IBZTaskHistoryDTO toDto(IBZTaskHistory entity) {

        IBZTaskHistoryDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IBZTaskHistory> toDomain(List<IBZTaskHistoryDTO> dtoList) {
        List<IBZTaskHistory> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IBZTaskHistoryDTO> toDto(List<IBZTaskHistory> entityList) {
        List<IBZTaskHistoryDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
