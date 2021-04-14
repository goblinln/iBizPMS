package cn.ibizlab.pms.webapi.mapping;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProductMonthly;
import cn.ibizlab.pms.webapi.dto.IbizproProductMonthlyDTO;
import cn.ibizlab.pms.util.domain.MappingBase;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIbizproProductMonthlyMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface IbizproProductMonthlyMapping extends MappingBase<IbizproProductMonthlyDTO, IbizproProductMonthly> {


}
