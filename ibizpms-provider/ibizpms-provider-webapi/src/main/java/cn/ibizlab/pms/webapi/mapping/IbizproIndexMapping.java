package cn.ibizlab.pms.webapi.mapping;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibizpro.domain.IbizproIndex;
import cn.ibizlab.pms.webapi.dto.IbizproIndexDTO;
import cn.ibizlab.pms.util.domain.MappingBase;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIbizproIndexMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface IbizproIndexMapping extends MappingBase<IbizproIndexDTO, IbizproIndex> {


}
