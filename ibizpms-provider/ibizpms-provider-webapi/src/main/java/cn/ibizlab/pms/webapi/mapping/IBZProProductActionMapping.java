package cn.ibizlab.pms.webapi.mapping;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IBZProProductAction;
import cn.ibizlab.pms.webapi.dto.IBZProProductActionDTO;
import cn.ibizlab.pms.util.domain.MappingBase;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIBZProProductActionMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface IBZProProductActionMapping extends MappingBase<IBZProProductActionDTO, IBZProProductAction> {


}
