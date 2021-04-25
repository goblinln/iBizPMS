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

import cn.ibizlab.pms.core.ibizpro.domain.IbzproConfig;
import cn.ibizlab.pms.core.ibizpro.filter.IbzproConfigSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IbzproConfig] 服务对象接口
 */
public interface IIbzproConfigService extends IService<IbzproConfig> {

    boolean create(IbzproConfig et);
    void createBatch(List<IbzproConfig> list);
    boolean update(IbzproConfig et);
    boolean sysUpdate(IbzproConfig et);
    void updateBatch(List<IbzproConfig> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    IbzproConfig get(String key);
    IbzproConfig sysGet(String key);
    IbzproConfig getDraft(IbzproConfig et);
    boolean checkKey(IbzproConfig et);
    IbzproConfig getSystemConfig(IbzproConfig et);
    boolean getSystemConfigBatch(List<IbzproConfig> etList);
    boolean save(IbzproConfig et);
    void saveBatch(List<IbzproConfig> list);
    List<IbzproConfig> select(IbzproConfigSearchContext context);
    Page<IbzproConfig> searchDefault(IbzproConfigSearchContext context);
    IbzproConfig dynamicCall(String key, String action, IbzproConfig et);
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

    List<IbzproConfig> getIbzproconfigByIds(List<String> ids);
    List<IbzproConfig> getIbzproconfigByEntities(List<IbzproConfig> entities);
}


