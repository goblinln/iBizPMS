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

import cn.ibizlab.pms.core.ibiz.domain.IBZTestSuitHistory;
import cn.ibizlab.pms.core.ibiz.filter.IBZTestSuitHistorySearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IBZTestSuitHistory] 服务对象接口
 */
public interface IIBZTestSuitHistoryService extends IService<IBZTestSuitHistory> {

    boolean create(IBZTestSuitHistory et);
    void createBatch(List<IBZTestSuitHistory> list);
    boolean update(IBZTestSuitHistory et);
    boolean sysUpdate(IBZTestSuitHistory et);
    void updateBatch(List<IBZTestSuitHistory> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IBZTestSuitHistory get(Long key);
    IBZTestSuitHistory sysGet(Long key);
    IBZTestSuitHistory getDraft(IBZTestSuitHistory et);
    boolean checkKey(IBZTestSuitHistory et);
    boolean save(IBZTestSuitHistory et);
    void saveBatch(List<IBZTestSuitHistory> list);
    List<IBZTestSuitHistory> select(IBZTestSuitHistorySearchContext context);
    List<IBZTestSuitHistory> selectDefault(IBZTestSuitHistorySearchContext context);
    List<IBZTestSuitHistory> selectSimple(IBZTestSuitHistorySearchContext context);
    List<IBZTestSuitHistory> selectView(IBZTestSuitHistorySearchContext context);

    Page<IBZTestSuitHistory> searchDefault(IBZTestSuitHistorySearchContext context);
    List<IBZTestSuitHistory> selectByAction(Long id);
    void removeByAction(Long id);
    void saveByAction(Long id, List<IBZTestSuitHistory> list) ;
    IBZTestSuitHistory dynamicCall(Long key, String action, IBZTestSuitHistory et);
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

    List<IBZTestSuitHistory> getIbztestsuithistoryByIds(List<Long> ids);
    List<IBZTestSuitHistory> getIbztestsuithistoryByEntities(List<IBZTestSuitHistory> entities);
}


