package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibizpro.domain.AccountTaskestimate;
import cn.ibizlab.pms.webapi.dto.AccountTaskestimateDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiAccountTaskestimateMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(AccountTaskestimateMappingDecorator.class)
public interface AccountTaskestimateMapping extends MappingBase<AccountTaskestimateDTO, AccountTaskestimate> {


}

class AccountTaskestimateMappingDecorator implements AccountTaskestimateMapping {

    @Autowired
    @Qualifier("delegate")
    private AccountTaskestimateMapping delegate;

    @Override
    public AccountTaskestimate toDomain(AccountTaskestimateDTO dto) {
        AccountTaskestimate domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public AccountTaskestimateDTO toDto(AccountTaskestimate entity) {

        AccountTaskestimateDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<AccountTaskestimate> toDomain(List<AccountTaskestimateDTO> dtoList) {
        List<AccountTaskestimate> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<AccountTaskestimateDTO> toDto(List<AccountTaskestimate> entityList) {
        List<AccountTaskestimateDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
