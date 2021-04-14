package cn.ibizlab.pms.webapi.mapping;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProductDaily;
import cn.ibizlab.pms.webapi.dto.IbizproProductDailyDTO;
import cn.ibizlab.pms.util.domain.MappingBase;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIbizproProductDailyMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface IbizproProductDailyMapping extends MappingBase<IbizproProductDailyDTO, IbizproProductDaily> {


}
