package cn.ibizlab.pms.standardapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IbzLibCaseSteps;
import cn.ibizlab.pms.standardapi.dto.TestCaseLibCaseStepNestedDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "StandardAPITestCaseLibCaseStepNestedMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(TestCaseLibCaseStepNestedMappingDecorator.class)
public interface TestCaseLibCaseStepNestedMapping extends MappingBase<TestCaseLibCaseStepNestedDTO, IbzLibCaseSteps> {


}

class TestCaseLibCaseStepNestedMappingDecorator implements TestCaseLibCaseStepNestedMapping {

    @Autowired
    @Qualifier("delegate")
    private TestCaseLibCaseStepNestedMapping delegate;

    @Override
    public IbzLibCaseSteps toDomain(TestCaseLibCaseStepNestedDTO dto) {
        IbzLibCaseSteps domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public TestCaseLibCaseStepNestedDTO toDto(IbzLibCaseSteps entity) {

        TestCaseLibCaseStepNestedDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbzLibCaseSteps> toDomain(List<TestCaseLibCaseStepNestedDTO> dtoList) {
        List<IbzLibCaseSteps> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<TestCaseLibCaseStepNestedDTO> toDto(List<IbzLibCaseSteps> entityList) {
        List<TestCaseLibCaseStepNestedDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
