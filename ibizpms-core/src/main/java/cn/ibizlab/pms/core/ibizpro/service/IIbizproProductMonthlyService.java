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

import cn.ibizlab.pms.core.ibizpro.domain.IbizproProductMonthly;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproProductMonthlySearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IbizproProductMonthly] 服务对象接口
 */
public interface IIbizproProductMonthlyService extends IService<IbizproProductMonthly> {

    boolean create(IbizproProductMonthly et);
    void createBatch(List<IbizproProductMonthly> list);
    boolean update(IbizproProductMonthly et);
    boolean sysUpdate(IbizproProductMonthly et);
    void updateBatch(List<IbizproProductMonthly> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IbizproProductMonthly get(Long key);
    IbizproProductMonthly sysGet(Long key);
    IbizproProductMonthly getDraft(IbizproProductMonthly et);
    boolean checkKey(IbizproProductMonthly et);
    IbizproProductMonthly manualCreateMonthly(IbizproProductMonthly et);
    boolean save(IbizproProductMonthly et);
    void saveBatch(List<IbizproProductMonthly> list);
    IbizproProductMonthly statsProductMonthly(IbizproProductMonthly et);
    List<IbizproProductMonthly> select(IbizproProductMonthlySearchContext context);
    List<IbizproProductMonthly> selectDefault(IbizproProductMonthlySearchContext context);
    List<IbizproProductMonthly> selectView(IbizproProductMonthlySearchContext context);

    Page<IbizproProductMonthly> searchDefault(IbizproProductMonthlySearchContext context);
    List<IbizproProductMonthly> selectByProduct(Long id);
    void removeByProduct(Long id);
    IbizproProductMonthly dynamicCall(Long key, String action, IbizproProductMonthly et);
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

    List<IbizproProductMonthly> getIbizproproductmonthlyByIds(List<Long> ids);
    List<IbizproProductMonthly> getIbizproproductmonthlyByEntities(List<IbizproProductMonthly> entities);
}


