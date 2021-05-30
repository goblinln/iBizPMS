package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.zentao.domain.Branch;
import cn.ibizlab.pms.webapi.dto.BranchDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiBranchMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(BranchMappingDecorator.class)
public interface BranchMapping extends MappingBase<BranchDTO, Branch> {


}

class BranchMappingDecorator implements BranchMapping {

    @Autowired
    @Qualifier("delegate")
    private BranchMapping delegate;

    @Override
    public Branch toDomain(BranchDTO dto) {
        Branch domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public BranchDTO toDto(Branch entity) {

        BranchDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<Branch> toDomain(List<BranchDTO> dtoList) {
        List<Branch> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<BranchDTO> toDto(List<Branch> entityList) {
        List<BranchDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
