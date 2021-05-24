package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IBZTestSuiteAction;
import cn.ibizlab.pms.webapi.dto.IBZTestSuiteActionDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIBZTestSuiteActionMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IBZTestSuiteActionMappingDecorator.class)
public interface IBZTestSuiteActionMapping extends MappingBase<IBZTestSuiteActionDTO, IBZTestSuiteAction> {


}

class IBZTestSuiteActionMappingDecorator implements IBZTestSuiteActionMapping {

    @Autowired
    @Qualifier("delegate")
    private IBZTestSuiteActionMapping delegate;

    @Override
    public IBZTestSuiteAction toDomain(IBZTestSuiteActionDTO dto) {
        IBZTestSuiteAction domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IBZTestSuiteActionDTO toDto(IBZTestSuiteAction entity) {

        IBZTestSuiteActionDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IBZTestSuiteAction> toDomain(List<IBZTestSuiteActionDTO> dtoList) {
        List<IBZTestSuiteAction> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IBZTestSuiteActionDTO> toDto(List<IBZTestSuiteAction> entityList) {
        List<IBZTestSuiteActionDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
