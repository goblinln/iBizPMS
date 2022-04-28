package cn.ibizlab.pms.core.zentao.mapping;

import cn.ibizlab.pms.core.zentao.domain.Task;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface TaskDataImport {
    @Mappings({
        @Mapping(target = "id", source = "id"),

    })
    @BeanMapping(ignoreByDefault = true)
    Task toDomain(Task entity);

    List<Task> toDomain(List<Task> entities);
}