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

import cn.ibizlab.pms.core.ibiz.domain.IBZProProjectHistory;
import cn.ibizlab.pms.core.ibiz.filter.IBZProProjectHistorySearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IBZProProjectHistory] 服务对象接口
 */
public interface IIBZProProjectHistoryService extends IService<IBZProProjectHistory> {

    boolean create(IBZProProjectHistory et);
    void createBatch(List<IBZProProjectHistory> list);
    boolean update(IBZProProjectHistory et);
    boolean sysUpdate(IBZProProjectHistory et);
    void updateBatch(List<IBZProProjectHistory> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IBZProProjectHistory get(Long key);
    IBZProProjectHistory sysGet(Long key);
    IBZProProjectHistory getDraft(IBZProProjectHistory et);
    boolean checkKey(IBZProProjectHistory et);
    boolean save(IBZProProjectHistory et);
    void saveBatch(List<IBZProProjectHistory> list);
    List<IBZProProjectHistory> select(IBZProProjectHistorySearchContext context);
    List<IBZProProjectHistory> selectDefault(IBZProProjectHistorySearchContext context);
    List<IBZProProjectHistory> selectSimple(IBZProProjectHistorySearchContext context);
    List<IBZProProjectHistory> selectView(IBZProProjectHistorySearchContext context);

    Page<IBZProProjectHistory> searchDefault(IBZProProjectHistorySearchContext context);
    List<IBZProProjectHistory> selectByAction(Long id);
    void removeByAction(Long id);
    void saveByAction(Long id, List<IBZProProjectHistory> list) ;
    IBZProProjectHistory dynamicCall(Long key, String action, IBZProProjectHistory et);
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

    List<IBZProProjectHistory> getIbzproprojecthistoryByIds(List<Long> ids);
    List<IBZProProjectHistory> getIbzproprojecthistoryByEntities(List<IBZProProjectHistory> entities);
}


