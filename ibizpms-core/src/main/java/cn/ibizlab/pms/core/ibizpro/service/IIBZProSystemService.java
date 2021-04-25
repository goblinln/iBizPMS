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

import cn.ibizlab.pms.core.ibizpro.domain.IBZProSystem;
import cn.ibizlab.pms.core.ibizpro.filter.IBZProSystemSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IBZProSystem] 服务对象接口
 */
public interface IIBZProSystemService extends IService<IBZProSystem> {

    boolean create(IBZProSystem et);
    void createBatch(List<IBZProSystem> list);
    boolean update(IBZProSystem et);
    boolean sysUpdate(IBZProSystem et);
    void updateBatch(List<IBZProSystem> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    IBZProSystem get(String key);
    IBZProSystem sysGet(String key);
    IBZProSystem getDraft(IBZProSystem et);
    boolean checkKey(IBZProSystem et);
    boolean save(IBZProSystem et);
    void saveBatch(List<IBZProSystem> list);
    List<IBZProSystem> select(IBZProSystemSearchContext context);
    Page<IBZProSystem> searchDefault(IBZProSystemSearchContext context);
    IBZProSystem dynamicCall(String key, String action, IBZProSystem et);
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

    List<IBZProSystem> getIbzprosystemByIds(List<String> ids);
    List<IBZProSystem> getIbzprosystemByEntities(List<IBZProSystem> entities);
}


