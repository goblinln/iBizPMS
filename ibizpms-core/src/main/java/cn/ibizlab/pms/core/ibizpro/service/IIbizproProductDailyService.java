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

import cn.ibizlab.pms.core.ibizpro.domain.IbizproProductDaily;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproProductDailySearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IbizproProductDaily] 服务对象接口
 */
public interface IIbizproProductDailyService extends IService<IbizproProductDaily> {

    boolean create(IbizproProductDaily et);
    void createBatch(List<IbizproProductDaily> list);
    boolean update(IbizproProductDaily et);
    boolean sysUpdate(IbizproProductDaily et);
    void updateBatch(List<IbizproProductDaily> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IbizproProductDaily get(Long key);
    IbizproProductDaily sysGet(Long key);
    IbizproProductDaily getDraft(IbizproProductDaily et);
    boolean checkKey(IbizproProductDaily et);
    IbizproProductDaily manualCreateDaily(IbizproProductDaily et);
    boolean manualCreateDailyBatch(List<IbizproProductDaily> etList);
    boolean save(IbizproProductDaily et);
    void saveBatch(List<IbizproProductDaily> list);
    IbizproProductDaily statsProductDaily(IbizproProductDaily et);
    boolean statsProductDailyBatch(List<IbizproProductDaily> etList);
    List<IbizproProductDaily> select(IbizproProductDailySearchContext context);
    Page<IbizproProductDaily> searchDefault(IbizproProductDailySearchContext context);
    Page<IbizproProductDaily> searchProductDaily(IbizproProductDailySearchContext context);
    List<IbizproProductDaily> selectByProduct(Long id);
    void removeByProduct(Long id);
    IbizproProductDaily dynamicCall(Long key, String action, IbizproProductDaily et);
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

    List<IbizproProductDaily> getIbizproproductdailyByIds(List<Long> ids);
    List<IbizproProductDaily> getIbizproproductdailyByEntities(List<IbizproProductDaily> entities);
}


