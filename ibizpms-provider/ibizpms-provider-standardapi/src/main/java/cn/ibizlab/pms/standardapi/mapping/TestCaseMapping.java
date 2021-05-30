package cn.ibizlab.pms.standardapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.zentao.domain.Case;
import cn.ibizlab.pms.standardapi.dto.TestCaseDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "StandardAPITestCaseMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(TestCaseMappingDecorator.class)
public interface TestCaseMapping extends MappingBase<TestCaseDTO, Case> {


}

class TestCaseMappingDecorator implements TestCaseMapping {

    @Autowired
    @Qualifier("delegate")
    private TestCaseMapping delegate;

    @Override
    public Case toDomain(TestCaseDTO dto) {
        Case domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public TestCaseDTO toDto(Case entity) {

        TestCaseDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<Case> toDomain(List<TestCaseDTO> dtoList) {
        List<Case> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<TestCaseDTO> toDto(List<Case> entityList) {
        List<TestCaseDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
