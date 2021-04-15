package cn.ibizlab.pms.core.ibizpro.service;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.math.BigInteger;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cache.annotation.CacheEvict;

import cn.ibizlab.pms.core.ibizpro.domain.IbizproIndex;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproIndexSearchContext;

/**
 * 实体[IbizproIndex] 服务对象接口
 */
public interface IIbizproIndexService {

    boolean create(IbizproIndex et);
    void createBatch(List<IbizproIndex> list);
    boolean update(IbizproIndex et);
    boolean sysUpdate(IbizproIndex et);
    void updateBatch(List<IbizproIndex> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IbizproIndex get(Long key);
    IbizproIndex sysGet(Long key);
    IbizproIndex getDraft(IbizproIndex et);
    boolean checkKey(IbizproIndex et);
    boolean save(IbizproIndex et);
    void saveBatch(List<IbizproIndex> list);
    Page<IbizproIndex> searchDefault(IbizproIndexSearchContext context);
    Page<IbizproIndex> searchESquery(IbizproIndexSearchContext context);
    Page<IbizproIndex> searchIndexDER(IbizproIndexSearchContext context);
    IbizproIndex dynamicCall(Long key, String action, IbizproIndex et);
    List<IbizproIndex> getIbizproindexByIds(List<Long> ids);
    List<IbizproIndex> getIbizproindexByEntities(List<IbizproIndex> entities);
}



