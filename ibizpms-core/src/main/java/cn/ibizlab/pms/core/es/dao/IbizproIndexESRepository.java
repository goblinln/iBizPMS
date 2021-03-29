package cn.ibizlab.pms.core.es.dao;

import cn.ibizlab.pms.core.es.domain.IbizproIndex;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

 /**
 * 实体[索引检索]
 */
@Repository
@ConditionalOnProperty( name = "ibiz.es.enabled", havingValue = "true")
public interface IbizproIndexESRepository extends ElasticsearchRepository<IbizproIndex, String>{

}






