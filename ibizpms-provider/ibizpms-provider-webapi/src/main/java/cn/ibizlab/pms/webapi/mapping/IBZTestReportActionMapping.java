package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IBZTestReportAction;
import cn.ibizlab.pms.webapi.dto.IBZTestReportActionDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIBZTestReportActionMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IBZTestReportActionMappingDecorator.class)
public interface IBZTestReportActionMapping extends MappingBase<IBZTestReportActionDTO, IBZTestReportAction> {


}

class IBZTestReportActionMappingDecorator implements IBZTestReportActionMapping {

    @Autowired
    @Qualifier("delegate")
    private IBZTestReportActionMapping delegate;

    @Override
    public IBZTestReportAction toDomain(IBZTestReportActionDTO dto) {
        IBZTestReportAction domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IBZTestReportActionDTO toDto(IBZTestReportAction entity) {

        IBZTestReportActionDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IBZTestReportAction> toDomain(List<IBZTestReportActionDTO> dtoList) {
        List<IBZTestReportAction> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IBZTestReportActionDTO> toDto(List<IBZTestReportAction> entityList) {
        List<IBZTestReportActionDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
