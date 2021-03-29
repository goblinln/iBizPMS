

package cn.ibizlab.pms.core.zentao.mapping;

import org.mapstruct.*;
import cn.ibizlab.pms.core.zentao.domain.Product;
import cn.ibizlab.pms.core.ibizpro.domain.IbizproIndex;
import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface ProductInheritMapping {

    @Mappings({
        @Mapping(source ="id",target = "indexid"),
        @Mapping(source ="name",target = "indexname"),
        @Mapping(target ="focusNull",ignore = true),
        @Mapping(source ="deleted",target = "deleted"),
        @Mapping(source ="desc",target = "indexdesc"),
    })
    IbizproIndex toIbizproindex(Product minorEntity);

    @Mappings({
        @Mapping(source ="indexid" ,target = "id"),
        @Mapping(source ="indexname" ,target = "name"),
        @Mapping(target ="focusNull",ignore = true),
        @Mapping(source ="indexdesc",target = "desc"),
    })
    Product toProduct(IbizproIndex majorEntity);

    List<IbizproIndex> toIbizproindex(List<Product> minorEntities);

    List<Product> toProduct(List<IbizproIndex> majorEntities);

}
