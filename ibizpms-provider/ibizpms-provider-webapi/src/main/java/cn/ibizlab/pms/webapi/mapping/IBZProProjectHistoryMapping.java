package cn.ibizlab.pms.webapi.mapping;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IBZProProjectHistory;
import cn.ibizlab.pms.webapi.dto.IBZProProjectHistoryDTO;
import cn.ibizlab.pms.util.domain.MappingBase;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIBZProProjectHistoryMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface IBZProProjectHistoryMapping extends MappingBase<IBZProProjectHistoryDTO, IBZProProjectHistory> {


}
