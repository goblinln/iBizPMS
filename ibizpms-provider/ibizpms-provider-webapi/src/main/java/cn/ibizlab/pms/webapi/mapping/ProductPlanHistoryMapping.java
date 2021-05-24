package cn.ibizlab.pms.webapi.mapping;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.ProductPlanHistory;
import cn.ibizlab.pms.webapi.dto.ProductPlanHistoryDTO;
import cn.ibizlab.pms.util.domain.MappingBase;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiProductPlanHistoryMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProductPlanHistoryMapping extends MappingBase<ProductPlanHistoryDTO, ProductPlanHistory> {


}
