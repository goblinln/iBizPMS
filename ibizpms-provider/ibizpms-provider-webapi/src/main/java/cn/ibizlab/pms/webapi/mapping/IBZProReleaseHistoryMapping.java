package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IBZProReleaseHistory;
import cn.ibizlab.pms.webapi.dto.IBZProReleaseHistoryDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIBZProReleaseHistoryMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IBZProReleaseHistoryMappingDecorator.class)
public interface IBZProReleaseHistoryMapping extends MappingBase<IBZProReleaseHistoryDTO, IBZProReleaseHistory> {


}

class IBZProReleaseHistoryMappingDecorator implements IBZProReleaseHistoryMapping {

    @Autowired
    @Qualifier("delegate")
    private IBZProReleaseHistoryMapping delegate;

    @Override
    public IBZProReleaseHistory toDomain(IBZProReleaseHistoryDTO dto) {
        IBZProReleaseHistory domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IBZProReleaseHistoryDTO toDto(IBZProReleaseHistory entity) {

        IBZProReleaseHistoryDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IBZProReleaseHistory> toDomain(List<IBZProReleaseHistoryDTO> dtoList) {
        List<IBZProReleaseHistory> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IBZProReleaseHistoryDTO> toDto(List<IBZProReleaseHistory> entityList) {
        List<IBZProReleaseHistoryDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
