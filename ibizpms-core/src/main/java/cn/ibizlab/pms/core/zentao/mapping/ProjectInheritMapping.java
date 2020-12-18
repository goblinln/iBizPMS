

package cn.ibizlab.pms.core.zentao.mapping;

import org.mapstruct.*;
import cn.ibizlab.pms.core.zentao.domain.Project;
import cn.ibizlab.pms.core.ibizpro.domain.IbizproIndex;
import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface ProjectInheritMapping {

    @Mappings({
        @Mapping(source ="id",target = "indexid"),
        @Mapping(source ="name",target = "indexname"),
        @Mapping(target ="focusNull",ignore = true),
    })
    IbizproIndex toIbizproindex(Project minorEntity);

    @Mappings({
        @Mapping(source ="indexid" ,target = "id"),
        @Mapping(source ="indexname" ,target = "name"),
        @Mapping(target ="focusNull",ignore = true),
    })
    Project toProject(IbizproIndex majorEntity);

    List<IbizproIndex> toIbizproindex(List<Project> minorEntities);

    List<Project> toProject(List<IbizproIndex> majorEntities);

}


