package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IbzProReportlyAction;
import cn.ibizlab.pms.webapi.dto.IbzProReportlyActionDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIbzProReportlyActionMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IbzProReportlyActionMappingDecorator.class)
public interface IbzProReportlyActionMapping extends MappingBase<IbzProReportlyActionDTO, IbzProReportlyAction> {


}

class IbzProReportlyActionMappingDecorator implements IbzProReportlyActionMapping {

    @Autowired
    @Qualifier("delegate")
    private IbzProReportlyActionMapping delegate;

    @Override
    public IbzProReportlyAction toDomain(IbzProReportlyActionDTO dto) {
        IbzProReportlyAction domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IbzProReportlyActionDTO toDto(IbzProReportlyAction entity) {

        IbzProReportlyActionDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbzProReportlyAction> toDomain(List<IbzProReportlyActionDTO> dtoList) {
        List<IbzProReportlyAction> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IbzProReportlyActionDTO> toDto(List<IbzProReportlyAction> entityList) {
        List<IbzProReportlyActionDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
