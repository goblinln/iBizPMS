package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.SysUpdateLog;
import cn.ibizlab.pms.webapi.dto.SysUpdateLogDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiSysUpdateLogMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(SysUpdateLogMappingDecorator.class)
public interface SysUpdateLogMapping extends MappingBase<SysUpdateLogDTO, SysUpdateLog> {


}

class SysUpdateLogMappingDecorator implements SysUpdateLogMapping {

    @Autowired
    @Qualifier("delegate")
    private SysUpdateLogMapping delegate;

    @Override
    public SysUpdateLog toDomain(SysUpdateLogDTO dto) {
        SysUpdateLog domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public SysUpdateLogDTO toDto(SysUpdateLog entity) {

        SysUpdateLogDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<SysUpdateLog> toDomain(List<SysUpdateLogDTO> dtoList) {
        List<SysUpdateLog> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<SysUpdateLogDTO> toDto(List<SysUpdateLog> entityList) {
        List<SysUpdateLogDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
