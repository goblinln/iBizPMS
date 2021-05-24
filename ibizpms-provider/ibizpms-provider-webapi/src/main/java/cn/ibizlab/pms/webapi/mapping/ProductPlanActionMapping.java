package cn.ibizlab.pms.webapi.mapping;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.ProductPlanAction;
import cn.ibizlab.pms.webapi.dto.ProductPlanActionDTO;
import cn.ibizlab.pms.util.domain.MappingBase;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiProductPlanActionMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProductPlanActionMapping extends MappingBase<ProductPlanActionDTO, ProductPlanAction> {


}
