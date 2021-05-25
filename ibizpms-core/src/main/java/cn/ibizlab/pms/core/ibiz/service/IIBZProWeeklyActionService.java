package cn.ibizlab.pms.core.ibiz.service;

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

import cn.ibizlab.pms.core.ibiz.domain.IBZProWeeklyAction;
import cn.ibizlab.pms.core.ibiz.filter.IBZProWeeklyActionSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IBZProWeeklyAction] 服务对象接口
 */
public interface IIBZProWeeklyActionService extends IService<IBZProWeeklyAction> {

    boolean create(IBZProWeeklyAction et);
    void createBatch(List<IBZProWeeklyAction> list);
    boolean update(IBZProWeeklyAction et);
    boolean sysUpdate(IBZProWeeklyAction et);
    void updateBatch(List<IBZProWeeklyAction> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IBZProWeeklyAction get(Long key);
    IBZProWeeklyAction sysGet(Long key);
    IBZProWeeklyAction getDraft(IBZProWeeklyAction et);
    boolean checkKey(IBZProWeeklyAction et);
    boolean save(IBZProWeeklyAction et);
    void saveBatch(List<IBZProWeeklyAction> list);
    List<IBZProWeeklyAction> select(IBZProWeeklyActionSearchContext context);
    List<IBZProWeeklyAction> selectDefault(IBZProWeeklyActionSearchContext context);
    List<IBZProWeeklyAction> selectSimple(IBZProWeeklyActionSearchContext context);
    List<IBZProWeeklyAction> selectType(IBZProWeeklyActionSearchContext context);
    List<IBZProWeeklyAction> selectView(IBZProWeeklyActionSearchContext context);

    Page<IBZProWeeklyAction> searchDefault(IBZProWeeklyActionSearchContext context);
    Page<IBZProWeeklyAction> searchType(IBZProWeeklyActionSearchContext context);
    List<IBZProWeeklyAction> selectByObjectid(Long ibzweeklyid);
    void removeByObjectid(Long ibzweeklyid);
    IBZProWeeklyAction dynamicCall(Long key, String action, IBZProWeeklyAction et);
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

    List<IBZProWeeklyAction> getIbzproweeklyactionByIds(List<Long> ids);
    List<IBZProWeeklyAction> getIbzproweeklyactionByEntities(List<IBZProWeeklyAction> entities);
}


