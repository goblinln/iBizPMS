package cn.ibizlab.pms.webapi.mapping;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibizpro.domain.IBZProProductLine;
import cn.ibizlab.pms.webapi.dto.IBZProProductLineDTO;
import cn.ibizlab.pms.util.domain.MappingBase;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIBZProProductLineMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface IBZProProductLineMapping extends MappingBase<IBZProProductLineDTO, IBZProProductLine> {


}
