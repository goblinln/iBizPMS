package cn.ibizlab.pms.webapi.mapping;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibizplugin.domain.IBIZProPlugin;
import cn.ibizlab.pms.webapi.dto.IBIZProPluginDTO;
import cn.ibizlab.pms.util.domain.MappingBase;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIBIZProPluginMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface IBIZProPluginMapping extends MappingBase<IBIZProPluginDTO, IBIZProPlugin> {


}
