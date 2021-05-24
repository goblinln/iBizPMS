package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSystemDBCfg;
import cn.ibizlab.pms.webapi.dto.PSSystemDBCfgDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiPSSystemDBCfgMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(PSSystemDBCfgMappingDecorator.class)
public interface PSSystemDBCfgMapping extends MappingBase<PSSystemDBCfgDTO, PSSystemDBCfg> {


}

class PSSystemDBCfgMappingDecorator implements PSSystemDBCfgMapping {

    @Autowired
    @Qualifier("delegate")
    private PSSystemDBCfgMapping delegate;

    @Override
    public PSSystemDBCfg toDomain(PSSystemDBCfgDTO dto) {
        PSSystemDBCfg domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public PSSystemDBCfgDTO toDto(PSSystemDBCfg entity) {

        PSSystemDBCfgDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<PSSystemDBCfg> toDomain(List<PSSystemDBCfgDTO> dtoList) {
        List<PSSystemDBCfg> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<PSSystemDBCfgDTO> toDto(List<PSSystemDBCfg> entityList) {
        List<PSSystemDBCfgDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
