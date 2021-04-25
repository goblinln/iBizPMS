package cn.ibizlab.pms.core.zentao.service;

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

import cn.ibizlab.pms.core.zentao.domain.Config;
import cn.ibizlab.pms.core.zentao.filter.ConfigSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[Config] 服务对象接口
 */
public interface IConfigService extends IService<Config> {

    boolean create(Config et);
    void createBatch(List<Config> list);
    boolean update(Config et);
    boolean sysUpdate(Config et);
    void updateBatch(List<Config> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    Config get(Long key);
    Config sysGet(Long key);
    Config getDraft(Config et);
    boolean checkKey(Config et);
    boolean save(Config et);
    void saveBatch(List<Config> list);
    List<Config> select(ConfigSearchContext context);
    Page<Config> searchDefault(ConfigSearchContext context);
    Config dynamicCall(Long key, String action, Config et);
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

}


