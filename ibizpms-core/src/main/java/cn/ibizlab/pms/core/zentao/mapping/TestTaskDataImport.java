package cn.ibizlab.pms.core.zentao.mapping;

import cn.ibizlab.pms.core.zentao.domain.TestTask;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface TestTaskDataImport {
    @Mappings({
        @Mapping(target = "id", source = "id"),
        @Mapping(target = "mailto", source = "mailto"),
        @Mapping(target = "owner", source = "owner"),
        @Mapping(target = "deleted", source = "deleted"),
        @Mapping(target = "begin", source = "begin"),
        @Mapping(target = "substatus", source = "substatus"),
        @Mapping(target = "name", source = "name"),
        @Mapping(target = "project", source = "project"),
        @Mapping(target = "buildname", source = "buildname"),
        @Mapping(target = "projecttname", source = "projecttname"),
        @Mapping(target = "comment", source = "comment"),
        @Mapping(target = "auto", source = "auto"),
        @Mapping(target = "status", source = "status"),
        @Mapping(target = "build", source = "build"),
        @Mapping(target = "pri", source = "pri"),
        @Mapping(target = "product", source = "product"),
        @Mapping(target = "end", source = "end"),
        @Mapping(target = "report", source = "report"),
        @Mapping(target = "productname", source = "productname"),
        @Mapping(target = "desc", source = "desc"),
    })
    @BeanMapping(ignoreByDefault = true)
    TestTask toDomain(TestTask entity);

    List<TestTask> toDomain(List<TestTask> entities);
}