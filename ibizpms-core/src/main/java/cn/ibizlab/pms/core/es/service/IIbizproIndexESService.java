package cn.ibizlab.pms.core.es.service;

import cn.ibizlab.pms.core.es.domain.IbizproIndex;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproIndexSearchContext;
import org.springframework.data.domain.Page;
import java.util.Collection;
import java.util.List;

/**
 * 实体[IbizproIndex] 服务对象接口
 */
public interface IIbizproIndexESService{
    boolean create(IbizproIndex et) ;
    void createBatch(List<IbizproIndex> list) ;
    boolean update(IbizproIndex et) ;
    void updateBatch(List<IbizproIndex> list) ;
    boolean remove(Long key) ;
    void removeBatch(Collection<Long> idList) ;
    IbizproIndex get(Long key) ;
    boolean save(IbizproIndex et) ;
    void saveBatch(List<IbizproIndex> list) ;
}


