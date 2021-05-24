package cn.ibizlab.pms.webapi.mapping;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IBZProProductHistory;
import cn.ibizlab.pms.webapi.dto.IBZProProductHistoryDTO;
import cn.ibizlab.pms.util.domain.MappingBase;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIBZProProductHistoryMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface IBZProProductHistoryMapping extends MappingBase<IBZProProductHistoryDTO, IBZProProductHistory> {


}
