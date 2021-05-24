package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibizpro.domain.IbzPlanTempletDetail;
import cn.ibizlab.pms.webapi.dto.IbzPlanTempletDetailDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIbzPlanTempletDetailMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IbzPlanTempletDetailMappingDecorator.class)
public interface IbzPlanTempletDetailMapping extends MappingBase<IbzPlanTempletDetailDTO, IbzPlanTempletDetail> {


}

class IbzPlanTempletDetailMappingDecorator implements IbzPlanTempletDetailMapping {

    @Autowired
    @Qualifier("delegate")
    private IbzPlanTempletDetailMapping delegate;

    @Override
    public IbzPlanTempletDetail toDomain(IbzPlanTempletDetailDTO dto) {
        IbzPlanTempletDetail domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IbzPlanTempletDetailDTO toDto(IbzPlanTempletDetail entity) {

        IbzPlanTempletDetailDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbzPlanTempletDetail> toDomain(List<IbzPlanTempletDetailDTO> dtoList) {
        List<IbzPlanTempletDetail> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IbzPlanTempletDetailDTO> toDto(List<IbzPlanTempletDetail> entityList) {
        List<IbzPlanTempletDetailDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
