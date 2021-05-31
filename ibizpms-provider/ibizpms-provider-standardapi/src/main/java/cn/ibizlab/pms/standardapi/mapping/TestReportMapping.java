package cn.ibizlab.pms.standardapi.mapping;

import cn.ibizlab.pms.util.security.FieldContext;

import org.mapstruct.*;
import cn.ibizlab.pms.core.zentao.domain.TestReport;
import cn.ibizlab.pms.standardapi.dto.TestReportDTO;
import cn.ibizlab.pms.util.domain.MappingBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", uses = {}, implementationName = "StandardAPITestReportMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(TestReportMappingDecorator.class)
public interface TestReportMapping extends MappingBase<TestReportDTO, TestReport> {


}

class TestReportMappingDecorator implements TestReportMapping {

    @Autowired
    @Qualifier("delegate")
    private TestReportMapping delegate;

    @Override
    public TestReport toDomain(TestReportDTO dto) {
        TestReport domain = delegate.toDomain(dto);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domain.reset(field.toLowerCase());
            }
        }
        return domain;
    }

    @Override
    public TestReportDTO toDto(TestReport entity) {

        TestReportDTO dto = delegate.toDto(entity);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dto.reset(field.toLowerCase());
            }
        }

        return dto;
    }

    @Override
    public List<TestReport> toDomain(List<TestReportDTO> dtoList) {
        List<TestReport> domains = delegate.toDomain(dtoList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                domains.forEach(domain -> domain.reset(field));
            }
        }
        return domains;
    }

    @Override
    public List<TestReportDTO> toDto(List<TestReport> entityList) {
        List<TestReportDTO> dtos = delegate.toDto(entityList);
        List<String> fields = FieldContext.get();
        if (fields != null) {
            for (String field : fields) {
                dtos.forEach(dto -> dto.reset(field));
            }
        }
        return dtos;
    }

}
