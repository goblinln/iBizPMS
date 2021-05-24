package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibizpro.domain.IbzPlanTemplet;
import cn.ibizlab.pms.webapi.dto.IbzPlanTempletDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIbzPlanTempletMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IbzPlanTempletMappingDecorator.class)
public interface IbzPlanTempletMapping extends MappingBase<IbzPlanTempletDTO, IbzPlanTemplet> {


}

class IbzPlanTempletMappingDecorator implements IbzPlanTempletMapping {

    @Autowired
    @Qualifier("delegate")
    private IbzPlanTempletMapping delegate;

    @Override
    public IbzPlanTemplet toDomain(IbzPlanTempletDTO dto) {
        IbzPlanTemplet domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IbzPlanTempletDTO toDto(IbzPlanTemplet entity) {

        IbzPlanTempletDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbzPlanTemplet> toDomain(List<IbzPlanTempletDTO> dtoList) {
        List<IbzPlanTemplet> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IbzPlanTempletDTO> toDto(List<IbzPlanTemplet> entityList) {
        List<IbzPlanTempletDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
