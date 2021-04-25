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

import cn.ibizlab.pms.core.ibizpro.domain.IbizproProjectDaily;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproProjectDailySearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IbizproProjectDaily] 服务对象接口
 */
public interface IIbizproProjectDailyService extends IService<IbizproProjectDaily> {

    boolean create(IbizproProjectDaily et);
    void createBatch(List<IbizproProjectDaily> list);
    boolean update(IbizproProjectDaily et);
    boolean sysUpdate(IbizproProjectDaily et);
    void updateBatch(List<IbizproProjectDaily> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    IbizproProjectDaily get(String key);
    IbizproProjectDaily sysGet(String key);
    IbizproProjectDaily getDraft(IbizproProjectDaily et);
    boolean checkKey(IbizproProjectDaily et);
    boolean save(IbizproProjectDaily et);
    void saveBatch(List<IbizproProjectDaily> list);
    IbizproProjectDaily sumProjectDaily(IbizproProjectDaily et);
    boolean sumProjectDailyBatch(List<IbizproProjectDaily> etList);
    List<IbizproProjectDaily> select(IbizproProjectDailySearchContext context);
    
    List<IbizproProjectDaily> selectQueryByDefault(IbizproProjectDailySearchContext context);
    List<IbizproProjectDaily> selectQueryByView(IbizproProjectDailySearchContext context);

    Page<IbizproProjectDaily> searchDefault(IbizproProjectDailySearchContext context);
    List<IbizproProjectDaily> selectByProject(Long id);
    void removeByProject(Long id);
    IbizproProjectDaily dynamicCall(String key, String action, IbizproProjectDaily et);
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

    List<IbizproProjectDaily> getIbizproprojectdailyByIds(List<String> ids);
    List<IbizproProjectDaily> getIbizproprojectdailyByEntities(List<IbizproProjectDaily> entities);
}


