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

import cn.ibizlab.pms.core.ibizpro.domain.IBZProSequence;
import cn.ibizlab.pms.core.ibizpro.filter.IBZProSequenceSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IBZProSequence] 服务对象接口
 */
public interface IIBZProSequenceService extends IService<IBZProSequence> {

    boolean create(IBZProSequence et);
    void createBatch(List<IBZProSequence> list);
    boolean update(IBZProSequence et);
    boolean sysUpdate(IBZProSequence et);
    void updateBatch(List<IBZProSequence> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    IBZProSequence get(String key);
    IBZProSequence sysGet(String key);
    IBZProSequence getDraft(IBZProSequence et);
    boolean checkKey(IBZProSequence et);
    IBZProSequence init(IBZProSequence et);
    boolean initBatch(List<IBZProSequence> etList);
    boolean save(IBZProSequence et);
    void saveBatch(List<IBZProSequence> list);
    Page<IBZProSequence> searchDefault(IBZProSequenceSearchContext context);
    IBZProSequence dynamicCall(String key, String action, IBZProSequence et);
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

    List<IBZProSequence> getIbzprosequenceByIds(List<String> ids);
    List<IBZProSequence> getIbzprosequenceByEntities(List<IBZProSequence> entities);
}


