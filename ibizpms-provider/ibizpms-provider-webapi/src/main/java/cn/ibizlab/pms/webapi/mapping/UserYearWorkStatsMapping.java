package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.UserYearWorkStats;
import cn.ibizlab.pms.webapi.dto.UserYearWorkStatsDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiUserYearWorkStatsMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(UserYearWorkStatsMappingDecorator.class)
public interface UserYearWorkStatsMapping extends MappingBase<UserYearWorkStatsDTO, UserYearWorkStats> {


}

class UserYearWorkStatsMappingDecorator implements UserYearWorkStatsMapping {

    @Autowired
    @Qualifier("delegate")
    private UserYearWorkStatsMapping delegate;

    @Override
    public UserYearWorkStats toDomain(UserYearWorkStatsDTO dto) {
        UserYearWorkStats domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public UserYearWorkStatsDTO toDto(UserYearWorkStats entity) {

        UserYearWorkStatsDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<UserYearWorkStats> toDomain(List<UserYearWorkStatsDTO> dtoList) {
        List<UserYearWorkStats> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<UserYearWorkStatsDTO> toDto(List<UserYearWorkStats> entityList) {
        List<UserYearWorkStatsDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
