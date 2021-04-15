package cn.ibizlab.pms.core.ibizpro.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import cn.ibizlab.pms.core.ibizpro.domain.IbizproIndex;
import org.springframework.stereotype.Repository;

@Repository
public interface IbizproIndexRepository extends MongoRepository<IbizproIndex, String>{

}

