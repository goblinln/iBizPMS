package cn.ibizlab.pms.standardapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.zentao.domain.CaseStep;
import cn.ibizlab.pms.standardapi.dto.TestCaseStepDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "StandardAPITestCaseStepMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(TestCaseStepMappingDecorator.class)
public interface TestCaseStepMapping extends MappingBase<TestCaseStepDTO, CaseStep> {


}

class TestCaseStepMappingDecorator implements TestCaseStepMapping {

    @Autowired
    @Qualifier("delegate")
    private TestCaseStepMapping delegate;

    @Override
    public CaseStep toDomain(TestCaseStepDTO dto) {
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
    public TestCaseStepDTO toDto(CaseStep entity) {

        TestCaseStepDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<CaseStep> toDomain(List<TestCaseStepDTO> dtoList) {
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
    public List<TestCaseStepDTO> toDto(List<CaseStep> entityList) {
        List<TestCaseStepDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
