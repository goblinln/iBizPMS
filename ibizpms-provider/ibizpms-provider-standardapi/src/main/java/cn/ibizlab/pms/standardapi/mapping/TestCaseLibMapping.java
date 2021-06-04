package cn.ibizlab.pms.standardapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IbzLib;
import cn.ibizlab.pms.standardapi.dto.TestCaseLibDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "StandardAPITestCaseLibMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(TestCaseLibMappingDecorator.class)
public interface TestCaseLibMapping extends MappingBase<TestCaseLibDTO, IbzLib> {


}

class TestCaseLibMappingDecorator implements TestCaseLibMapping {

    @Autowired
    @Qualifier("delegate")
    private TestCaseLibMapping delegate;

    @Override
    public IbzLib toDomain(TestCaseLibDTO dto) {
        IbzLib domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public TestCaseLibDTO toDto(IbzLib entity) {

        TestCaseLibDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbzLib> toDomain(List<TestCaseLibDTO> dtoList) {
        List<IbzLib> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<TestCaseLibDTO> toDto(List<IbzLib> entityList) {
        List<TestCaseLibDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
