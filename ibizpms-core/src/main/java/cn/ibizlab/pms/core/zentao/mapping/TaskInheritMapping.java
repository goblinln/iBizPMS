

package cn.ibizlab.pms.core.zentao.mapping;

import org.mapstruct.*;
import cn.ibizlab.pms.core.zentao.domain.Task;
import cn.ibizlab.pms.core.ibizpro.domain.IbizproIndex;
import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface TaskInheritMapping {

    @Mappings({
        @Mapping(source ="id",target = "indexid"),
        @Mapping(source ="name",target = "indexname"),
        @Mapping(target ="focusNull",ignore = true),
        @Mapping(source ="deleted",target = "deleted"),
        @Mapping(source ="desc",target = "indexdesc"),
        @Mapping(source ="project",target = "project"),
    })
    IbizproIndex toIbizproindex(Task minorEntity);

    @Mappings({
        @Mapping(source ="indexid" ,target = "id"),
        @Mapping(source ="indexname" ,target = "name"),
        @Mapping(target ="focusNull",ignore = true),
        @Mapping(source ="indexdesc",target = "desc"),
    })
    Task toTask(IbizproIndex majorEntity);

    List<IbizproIndex> toIbizproindex(List<Task> minorEntities);

    List<Task> toTask(List<IbizproIndex> majorEntities);

}
