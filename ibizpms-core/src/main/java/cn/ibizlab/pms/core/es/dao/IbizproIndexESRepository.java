package cn.ibizlab.pms.core.es.dao;

import cn.ibizlab.pms.core.es.domain.IbizproIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

 /**
 * 实体[索引检索]
 */
@Repository
public interface IbizproIndexESRepository extends ElasticsearchRepository<IbizproIndex, String>{

}






