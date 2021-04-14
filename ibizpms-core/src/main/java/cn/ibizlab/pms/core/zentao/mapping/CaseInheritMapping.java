

package cn.ibizlab.pms.core.zentao.mapping;

import org.mapstruct.*;
import cn.ibizlab.pms.core.zentao.domain.Case;
import cn.ibizlab.pms.core.ibizpro.domain.IbizproIndex;
import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface CaseInheritMapping {

    @Mappings({
        @Mapping(source ="id",target = "indexid"),
        @Mapping(source ="title",target = "indexname"),
        @Mapping(target ="focusNull",ignore = true),
        @Mapping(source ="deleted",target = "deleted"),
        @Mapping(source ="precondition",target = "indexdesc"),
        @Mapping(source ="product",target = "product"),
    })
    IbizproIndex toIbizproindex(Case minorEntity);

    @Mappings({
        @Mapping(source ="indexid" ,target = "id"),
        @Mapping(source ="indexname" ,target = "title"),
        @Mapping(target ="focusNull",ignore = true),
        @Mapping(source ="indexdesc",target = "precondition"),
    })
    Case toCase(IbizproIndex majorEntity);

    List<IbizproIndex> toIbizproindex(List<Case> minorEntities);

    List<Case> toCase(List<IbizproIndex> majorEntities);

}
