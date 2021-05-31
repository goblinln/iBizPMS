package cn.ibizlab.pms.webapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.zentao.domain.TestSuite;
import cn.ibizlab.pms.webapi.dto.TestSuiteDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiTestSuiteMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(TestSuiteMappingDecorator.class)
public interface TestSuiteMapping extends MappingBase<TestSuiteDTO, TestSuite> {


}

class TestSuiteMappingDecorator implements TestSuiteMapping {

    @Autowired
    @Qualifier("delegate")
    private TestSuiteMapping delegate;

    @Override
    public TestSuite toDomain(TestSuiteDTO dto) {
        TestSuite domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public TestSuiteDTO toDto(TestSuite entity) {

        TestSuiteDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<TestSuite> toDomain(List<TestSuiteDTO> dtoList) {
        List<TestSuite> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<TestSuiteDTO> toDto(List<TestSuite> entityList) {
        List<TestSuiteDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
