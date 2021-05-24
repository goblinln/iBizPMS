package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IbzProBuildHistory;
import cn.ibizlab.pms.webapi.dto.IbzProBuildHistoryDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIbzProBuildHistoryMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IbzProBuildHistoryMappingDecorator.class)
public interface IbzProBuildHistoryMapping extends MappingBase<IbzProBuildHistoryDTO, IbzProBuildHistory> {


}

class IbzProBuildHistoryMappingDecorator implements IbzProBuildHistoryMapping {

    @Autowired
    @Qualifier("delegate")
    private IbzProBuildHistoryMapping delegate;

    @Override
    public IbzProBuildHistory toDomain(IbzProBuildHistoryDTO dto) {
        IbzProBuildHistory domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IbzProBuildHistoryDTO toDto(IbzProBuildHistory entity) {

        IbzProBuildHistoryDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbzProBuildHistory> toDomain(List<IbzProBuildHistoryDTO> dtoList) {
        List<IbzProBuildHistory> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IbzProBuildHistoryDTO> toDto(List<IbzProBuildHistory> entityList) {
        List<IbzProBuildHistoryDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
