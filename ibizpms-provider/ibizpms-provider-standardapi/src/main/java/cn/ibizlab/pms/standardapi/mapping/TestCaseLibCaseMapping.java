package cn.ibizlab.pms.standardapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IbzCase;
import cn.ibizlab.pms.standardapi.dto.TestCaseLibCaseDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "StandardAPITestCaseLibCaseMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(TestCaseLibCaseMappingDecorator.class)
public interface TestCaseLibCaseMapping extends MappingBase<TestCaseLibCaseDTO, IbzCase> {


}

class TestCaseLibCaseMappingDecorator implements TestCaseLibCaseMapping {

    @Autowired
    @Qualifier("delegate")
    private TestCaseLibCaseMapping delegate;

    @Override
    public IbzCase toDomain(TestCaseLibCaseDTO dto) {
        IbzCase domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public TestCaseLibCaseDTO toDto(IbzCase entity) {

        TestCaseLibCaseDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbzCase> toDomain(List<TestCaseLibCaseDTO> dtoList) {
        List<IbzCase> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<TestCaseLibCaseDTO> toDto(List<IbzCase> entityList) {
        List<TestCaseLibCaseDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
