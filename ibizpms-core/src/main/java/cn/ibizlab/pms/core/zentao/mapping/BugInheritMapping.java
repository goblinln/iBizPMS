

package cn.ibizlab.pms.core.zentao.mapping;

import org.mapstruct.*;
import cn.ibizlab.pms.core.zentao.domain.Bug;
import cn.ibizlab.pms.core.ibizpro.domain.IbizproIndex;
import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface BugInheritMapping {

    @Mappings({
        @Mapping(source ="id",target = "indexid"),
        @Mapping(source ="title",target = "indexname"),
        @Mapping(target ="focusNull",ignore = true),
        @Mapping(source ="deleted",target = "deleted"),
        @Mapping(source ="product",target = "product"),
        @Mapping(source ="steps",target = "indexdesc"),
        @Mapping(source ="project",target = "project"),
    })
    IbizproIndex toIbizproindex(Bug minorEntity);

    @Mappings({
        @Mapping(source ="indexid" ,target = "id"),
        @Mapping(source ="indexname" ,target = "title"),
        @Mapping(target ="focusNull",ignore = true),
        @Mapping(source ="indexdesc",target = "steps"),
    })
    Bug toBug(IbizproIndex majorEntity);

    List<IbizproIndex> toIbizproindex(List<Bug> minorEntities);

    List<Bug> toBug(List<IbizproIndex> majorEntities);

}
