package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSysSFPub;
import cn.ibizlab.pms.webapi.dto.PSSysSFPubDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiPSSysSFPubMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(PSSysSFPubMappingDecorator.class)
public interface PSSysSFPubMapping extends MappingBase<PSSysSFPubDTO, PSSysSFPub> {


}

class PSSysSFPubMappingDecorator implements PSSysSFPubMapping {

    @Autowired
    @Qualifier("delegate")
    private PSSysSFPubMapping delegate;

    @Override
    public PSSysSFPub toDomain(PSSysSFPubDTO dto) {
        PSSysSFPub domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public PSSysSFPubDTO toDto(PSSysSFPub entity) {

        PSSysSFPubDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<PSSysSFPub> toDomain(List<PSSysSFPubDTO> dtoList) {
        List<PSSysSFPub> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<PSSysSFPubDTO> toDto(List<PSSysSFPub> entityList) {
        List<PSSysSFPubDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
