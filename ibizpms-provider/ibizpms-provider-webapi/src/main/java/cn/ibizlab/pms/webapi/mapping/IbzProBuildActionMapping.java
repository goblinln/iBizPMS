package cn.ibizlab.pms.webapi.mapping;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IbzProBuildAction;
import cn.ibizlab.pms.webapi.dto.IbzProBuildActionDTO;
import cn.ibizlab.pms.util.domain.MappingBase;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIbzProBuildActionMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface IbzProBuildActionMapping extends MappingBase<IbzProBuildActionDTO, IbzProBuildAction> {


}
