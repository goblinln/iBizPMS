package cn.ibizlab.pms.standardapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IbzLibModule;
import cn.ibizlab.pms.standardapi.dto.TestCaseLibModuleDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "StandardAPITestCaseLibModuleMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(TestCaseLibModuleMappingDecorator.class)
public interface TestCaseLibModuleMapping extends MappingBase<TestCaseLibModuleDTO, IbzLibModule> {


}

class TestCaseLibModuleMappingDecorator implements TestCaseLibModuleMapping {

    @Autowired
    @Qualifier("delegate")
    private TestCaseLibModuleMapping delegate;

    @Override
    public IbzLibModule toDomain(TestCaseLibModuleDTO dto) {
        IbzLibModule domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public TestCaseLibModuleDTO toDto(IbzLibModule entity) {

        TestCaseLibModuleDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<IbzLibModule> toDomain(List<TestCaseLibModuleDTO> dtoList) {
        List<IbzLibModule> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<TestCaseLibModuleDTO> toDto(List<IbzLibModule> entityList) {
        List<TestCaseLibModuleDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
