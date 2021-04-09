package cn.ibizlab.pms.core.ibizpro.mapping;

import cn.ibizlab.pms.core.ibizpro.domain.IbizproIndex;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.util.List;

@Mapper(componentModel = "spring", uses = {},
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface IbizproIndexESMapping{

    IbizproIndex toDomain(cn.ibizlab.pms.core.es.domain.IbizproIndex esEntity);

    cn.ibizlab.pms.core.es.domain.IbizproIndex toESDomain(IbizproIndex entity);

    List<IbizproIndex> toDomain(List<cn.ibizlab.pms.core.es.domain.IbizproIndex> esEntityList);

    List<cn.ibizlab.pms.core.es.domain.IbizproIndex> toESDomain(List<IbizproIndex> entityList);
}
