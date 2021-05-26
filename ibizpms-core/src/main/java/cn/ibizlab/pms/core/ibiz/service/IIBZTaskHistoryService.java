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

import cn.ibizlab.pms.core.ibiz.domain.IBZTaskHistory;
import cn.ibizlab.pms.core.ibiz.filter.IBZTaskHistorySearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IBZTaskHistory] 服务对象接口
 */
public interface IIBZTaskHistoryService extends IService<IBZTaskHistory> {

    boolean create(IBZTaskHistory et);
    void createBatch(List<IBZTaskHistory> list);
    boolean update(IBZTaskHistory et);
    boolean sysUpdate(IBZTaskHistory et);
    void updateBatch(List<IBZTaskHistory> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IBZTaskHistory get(Long key);
    IBZTaskHistory sysGet(Long key);
    IBZTaskHistory getDraft(IBZTaskHistory et);
    boolean checkKey(IBZTaskHistory et);
    boolean save(IBZTaskHistory et);
    void saveBatch(List<IBZTaskHistory> list);
    List<IBZTaskHistory> select(IBZTaskHistorySearchContext context);
    List<IBZTaskHistory> selectDefault(IBZTaskHistorySearchContext context);
    List<IBZTaskHistory> selectSimple(IBZTaskHistorySearchContext context);
    List<IBZTaskHistory> selectView(IBZTaskHistorySearchContext context);

    Page<IBZTaskHistory> searchDefault(IBZTaskHistorySearchContext context);
    List<IBZTaskHistory> selectByAction(Long id);
    void removeByAction(Long id);
    void saveByAction(Long id, List<IBZTaskHistory> list) ;
    IBZTaskHistory dynamicCall(Long key, String action, IBZTaskHistory et);
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

    List<IBZTaskHistory> getIbztaskhistoryByIds(List<Long> ids);
    List<IBZTaskHistory> getIbztaskhistoryByEntities(List<IBZTaskHistory> entities);
}


