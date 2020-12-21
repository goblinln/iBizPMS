package cn.ibizlab.pms.core.zentao.mapping;

import cn.ibizlab.pms.core.zentao.domain.TestTask;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface TestTaskDataImport {
    @Mappings({
        @Mapping(target = "id", source = "id"),
        @Mapping(target = "desc", source = "desc"),
        @Mapping(target = "name", source = "name"),
        @Mapping(target = "mailto", source = "mailto"),
        @Mapping(target = "productname", source = "productname"),
        @Mapping(target = "report", source = "report"),
        @Mapping(target = "auto", source = "auto"),
        @Mapping(target = "end", source = "end"),
        @Mapping(target = "projecttname", source = "projecttname"),
        @Mapping(target = "deleted", source = "deleted"),
        @Mapping(target = "owner", source = "owner"),
        @Mapping(target = "comment", source = "comment"),
        @Mapping(target = "product", source = "product"),
        @Mapping(target = "buildname", source = "buildname"),
        @Mapping(target = "pri", source = "pri"),
        @Mapping(target = "project", source = "project"),
        @Mapping(target = "status", source = "status"),
        @Mapping(target = "substatus", source = "substatus"),
        @Mapping(target = "begin", source = "begin"),
        @Mapping(target = "build", source = "build"),
    })
    @BeanMapping(ignoreByDefault = true)
    TestTask toDomain(TestTask entity);

    List<TestTask> toDomain(List<TestTask> entities);
}




