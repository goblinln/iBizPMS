package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IBZProBugHostory;
import cn.ibizlab.pms.webapi.dto.IBZProBugHostoryDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIBZProBugHostoryMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(IBZProBugHostoryMappingDecorator.class)
public interface IBZProBugHostoryMapping extends MappingBase<IBZProBugHostoryDTO, IBZProBugHostory> {


}

class IBZProBugHostoryMappingDecorator implements IBZProBugHostoryMapping {

    @Autowired
    @Qualifier("delegate")
    private IBZProBugHostoryMapping delegate;

    @Override
    public IBZProBugHostory toDomain(IBZProBugHostoryDTO dto) {
        IBZProBugHostory domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public IBZProBugHostoryDTO toDto(IBZProBugHostory entity) {

        IBZProBugHostoryDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IBZProBugHostory> toDomain(List<IBZProBugHostoryDTO> dtoList) {
        List<IBZProBugHostory> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<IBZProBugHostoryDTO> toDto(List<IBZProBugHostory> entityList) {
        List<IBZProBugHostoryDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
