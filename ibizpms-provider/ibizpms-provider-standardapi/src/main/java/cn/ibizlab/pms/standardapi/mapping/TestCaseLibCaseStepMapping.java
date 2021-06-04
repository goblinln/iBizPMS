package cn.ibizlab.pms.standardapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IbzLibCaseStep;
import cn.ibizlab.pms.standardapi.dto.TestCaseLibCaseStepDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "StandardAPITestCaseLibCaseStepMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(TestCaseLibCaseStepMappingDecorator.class)
public interface TestCaseLibCaseStepMapping extends MappingBase<TestCaseLibCaseStepDTO, IbzLibCaseStep> {


}

class TestCaseLibCaseStepMappingDecorator implements TestCaseLibCaseStepMapping {

    @Autowired
    @Qualifier("delegate")
    private TestCaseLibCaseStepMapping delegate;

    @Override
    public IbzLibCaseStep toDomain(TestCaseLibCaseStepDTO dto) {
        IbzLibCaseStep domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public TestCaseLibCaseStepDTO toDto(IbzLibCaseStep entity) {

        TestCaseLibCaseStepDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbzLibCaseStep> toDomain(List<TestCaseLibCaseStepDTO> dtoList) {
        List<IbzLibCaseStep> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<TestCaseLibCaseStepDTO> toDto(List<IbzLibCaseStep> entityList) {
        List<TestCaseLibCaseStepDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
