package cn.ibizlab.pms.standardapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.zentao.domain.CaseStep;
import cn.ibizlab.pms.standardapi.dto.TestCaseStepNestedDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "StandardAPITestCaseStepNestedMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(TestCaseStepNestedMappingDecorator.class)
public interface TestCaseStepNestedMapping extends MappingBase<TestCaseStepNestedDTO, CaseStep> {


}

class TestCaseStepNestedMappingDecorator implements TestCaseStepNestedMapping {

    @Autowired
    @Qualifier("delegate")
    private TestCaseStepNestedMapping delegate;

    @Override
    public CaseStep toDomain(TestCaseStepNestedDTO dto) {
        CaseStep domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public TestCaseStepNestedDTO toDto(CaseStep entity) {

        TestCaseStepNestedDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<CaseStep> toDomain(List<TestCaseStepNestedDTO> dtoList) {
        List<CaseStep> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<TestCaseStepNestedDTO> toDto(List<CaseStep> entityList) {
        List<TestCaseStepNestedDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
