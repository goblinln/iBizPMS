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

import cn.ibizlab.pms.core.ibiz.domain.IBZProToDoHistory;
import cn.ibizlab.pms.core.ibiz.filter.IBZProToDoHistorySearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IBZProToDoHistory] 服务对象接口
 */
public interface IIBZProToDoHistoryService extends IService<IBZProToDoHistory> {

    boolean create(IBZProToDoHistory et);
    void createBatch(List<IBZProToDoHistory> list);
    boolean update(IBZProToDoHistory et);
    boolean sysUpdate(IBZProToDoHistory et);
    void updateBatch(List<IBZProToDoHistory> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IBZProToDoHistory get(Long key);
    IBZProToDoHistory sysGet(Long key);
    IBZProToDoHistory getDraft(IBZProToDoHistory et);
    boolean checkKey(IBZProToDoHistory et);
    boolean save(IBZProToDoHistory et);
    void saveBatch(List<IBZProToDoHistory> list);
    List<IBZProToDoHistory> select(IBZProToDoHistorySearchContext context);
    List<IBZProToDoHistory> selectDefault(IBZProToDoHistorySearchContext context);
    List<IBZProToDoHistory> selectSimple(IBZProToDoHistorySearchContext context);
    List<IBZProToDoHistory> selectView(IBZProToDoHistorySearchContext context);

    Page<IBZProToDoHistory> searchDefault(IBZProToDoHistorySearchContext context);
    List<IBZProToDoHistory> selectByAction(Long id);
    void removeByAction(Long id);
    void saveByAction(Long id, List<IBZProToDoHistory> list) ;
    IBZProToDoHistory dynamicCall(Long key, String action, IBZProToDoHistory et);
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

    List<IBZProToDoHistory> getIbzprotodohistoryByIds(List<Long> ids);
    List<IBZProToDoHistory> getIbzprotodohistoryByEntities(List<IBZProToDoHistory> entities);
}


