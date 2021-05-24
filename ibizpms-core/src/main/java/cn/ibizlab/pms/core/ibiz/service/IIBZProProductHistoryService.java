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

import cn.ibizlab.pms.core.ibiz.domain.IBZProProductHistory;
import cn.ibizlab.pms.core.ibiz.filter.IBZProProductHistorySearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IBZProProductHistory] 服务对象接口
 */
public interface IIBZProProductHistoryService extends IService<IBZProProductHistory> {

    boolean create(IBZProProductHistory et);
    void createBatch(List<IBZProProductHistory> list);
    boolean update(IBZProProductHistory et);
    boolean sysUpdate(IBZProProductHistory et);
    void updateBatch(List<IBZProProductHistory> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IBZProProductHistory get(Long key);
    IBZProProductHistory sysGet(Long key);
    IBZProProductHistory getDraft(IBZProProductHistory et);
    boolean checkKey(IBZProProductHistory et);
    boolean save(IBZProProductHistory et);
    void saveBatch(List<IBZProProductHistory> list);
    List<IBZProProductHistory> select(IBZProProductHistorySearchContext context);
    List<IBZProProductHistory> selectDefault(IBZProProductHistorySearchContext context);
    List<IBZProProductHistory> selectSimple(IBZProProductHistorySearchContext context);
    List<IBZProProductHistory> selectView(IBZProProductHistorySearchContext context);

    Page<IBZProProductHistory> searchDefault(IBZProProductHistorySearchContext context);
    List<IBZProProductHistory> selectByAction(Long id);
    void removeByAction(Long id);
    void saveByAction(Long id, List<IBZProProductHistory> list) ;
    IBZProProductHistory dynamicCall(Long key, String action, IBZProProductHistory et);
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

    List<IBZProProductHistory> getIbzproproducthistoryByIds(List<Long> ids);
    List<IBZProProductHistory> getIbzproproducthistoryByEntities(List<IBZProProductHistory> entities);
}


