package cn.ibizlab.pms.webapi.mapping;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IBZProProjectAction;
import cn.ibizlab.pms.webapi.dto.IBZProProjectActionDTO;
import cn.ibizlab.pms.util.domain.MappingBase;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIBZProProjectActionMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface IBZProProjectActionMapping extends MappingBase<IBZProProjectActionDTO, IBZProProjectAction> {


}
