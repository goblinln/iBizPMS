package cn.ibizlab.pms.webapi.mapping;

import org.mapstruct.*;
import cn.ibizlab.pms.core.ibiz.domain.IbzLibCaseSteps;
import cn.ibizlab.pms.webapi.dto.IbzLibCaseStepTmpDTO;
import cn.ibizlab.pms.util.domain.MappingBase;

@Mapper(componentModel = "spring", uses = {}, implementationName = "WebApiIbzLibCaseStepTmpMapping",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface IbzLibCaseStepTmpMapping extends MappingBase<IbzLibCaseStepTmpDTO, IbzLibCaseSteps> {


}
