package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibizpro.domain.IbzPlanTempletDetail;
import cn.ibizlab.pms.webapi.dto.PlanTempletDetailDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiPlanTempletDetailMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(PlanTempletDetailMappingDecorator.class)
public interface PlanTempletDetailMapping extends MappingBase<PlanTempletDetailDTO, IbzPlanTempletDetail> {


}

class PlanTempletDetailMappingDecorator implements PlanTempletDetailMapping {

    @Autowired
    @Qualifier("delegate")
    private PlanTempletDetailMapping delegate;

    @Override
    public IbzPlanTempletDetail toDomain(PlanTempletDetailDTO dto) {
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
    public PlanTempletDetailDTO toDto(IbzPlanTempletDetail entity) {

        PlanTempletDetailDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbzPlanTempletDetail> toDomain(List<PlanTempletDetailDTO> dtoList) {
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
    public List<PlanTempletDetailDTO> toDto(List<IbzPlanTempletDetail> entityList) {
        List<PlanTempletDetailDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
