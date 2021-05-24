package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IBZTestSuitHistory;
import cn.ibizlab.pms.webapi.dto.IBZTestSuitHistoryDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIBZTestSuitHistoryMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IBZTestSuitHistoryMappingDecorator.class)
public interface IBZTestSuitHistoryMapping extends MappingBase<IBZTestSuitHistoryDTO, IBZTestSuitHistory> {


}

class IBZTestSuitHistoryMappingDecorator implements IBZTestSuitHistoryMapping {

    @Autowired
    @Qualifier("delegate")
    private IBZTestSuitHistoryMapping delegate;

    @Override
    public IBZTestSuitHistory toDomain(IBZTestSuitHistoryDTO dto) {
        IBZTestSuitHistory domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IBZTestSuitHistoryDTO toDto(IBZTestSuitHistory entity) {

        IBZTestSuitHistoryDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IBZTestSuitHistory> toDomain(List<IBZTestSuitHistoryDTO> dtoList) {
        List<IBZTestSuitHistory> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IBZTestSuitHistoryDTO> toDto(List<IBZTestSuitHistory> entityList) {
        List<IBZTestSuitHistoryDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
