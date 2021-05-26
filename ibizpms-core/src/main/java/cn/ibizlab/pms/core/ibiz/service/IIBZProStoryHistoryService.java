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

import cn.ibizlab.pms.core.ibiz.domain.IBZProStoryHistory;
import cn.ibizlab.pms.core.ibiz.filter.IBZProStoryHistorySearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IBZProStoryHistory] 服务对象接口
 */
public interface IIBZProStoryHistoryService extends IService<IBZProStoryHistory> {

    boolean create(IBZProStoryHistory et);
    void createBatch(List<IBZProStoryHistory> list);
    boolean update(IBZProStoryHistory et);
    boolean sysUpdate(IBZProStoryHistory et);
    void updateBatch(List<IBZProStoryHistory> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IBZProStoryHistory get(Long key);
    IBZProStoryHistory sysGet(Long key);
    IBZProStoryHistory getDraft(IBZProStoryHistory et);
    boolean checkKey(IBZProStoryHistory et);
    boolean save(IBZProStoryHistory et);
    void saveBatch(List<IBZProStoryHistory> list);
    List<IBZProStoryHistory> select(IBZProStoryHistorySearchContext context);
    List<IBZProStoryHistory> selectDefault(IBZProStoryHistorySearchContext context);
    List<IBZProStoryHistory> selectSimple(IBZProStoryHistorySearchContext context);
    List<IBZProStoryHistory> selectView(IBZProStoryHistorySearchContext context);

    Page<IBZProStoryHistory> searchDefault(IBZProStoryHistorySearchContext context);
    List<IBZProStoryHistory> selectByAction(Long id);
    void removeByAction(Long id);
    IBZProStoryHistory dynamicCall(Long key, String action, IBZProStoryHistory et);
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

    List<IBZProStoryHistory> getIbzprostoryhistoryByIds(List<Long> ids);
    List<IBZProStoryHistory> getIbzprostoryhistoryByEntities(List<IBZProStoryHistory> entities);
}


