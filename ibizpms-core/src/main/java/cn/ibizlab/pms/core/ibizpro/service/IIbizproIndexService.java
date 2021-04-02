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

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IbizproIndex] 服务对象接口
 */
public interface IIbizproIndexService extends IService<IbizproIndex> {

    boolean create(IbizproIndex et);
    void createBatch(List<IbizproIndex> list);
    boolean update(IbizproIndex et);
    void updateBatch(List<IbizproIndex> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IbizproIndex get(Long key);
    IbizproIndex getDraft(IbizproIndex et);
    boolean checkKey(IbizproIndex et);
    boolean save(IbizproIndex et);
    void saveBatch(List<IbizproIndex> list);
    Page<IbizproIndex> searchDefault(IbizproIndexSearchContext context);
    Page<IbizproIndex> searchESquery(IbizproIndexSearchContext context);
    Page<IbizproIndex> searchIndexDER(IbizproIndexSearchContext context);
    IbizproIndex dynamicCall(Long key, String action, IbizproIndex et);
    /**
     *自定义查询SQL
     * @param sql  select * from table where id =#{et.param}
     * @param param 参数列表  param.put("param","1");
     * @return select * from table where id = '1'
     */
    List<JSONObject> select(String sql, Map param);
    /**
     *自定义SQL
     * @param sql  update table  set name ='test' where id =#{et.param}
     * @param param 参数列表  param.put("param","1");
     * @return     update table  set name ='test' where id = '1'
     */
    boolean execute(String sql, Map param);

    List<IbizproIndex> getIbizproindexByIds(List<Long> ids);
    List<IbizproIndex> getIbizproindexByEntities(List<IbizproIndex> entities);
}


