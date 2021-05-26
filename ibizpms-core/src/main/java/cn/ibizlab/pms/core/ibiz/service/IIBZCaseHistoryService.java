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

import cn.ibizlab.pms.core.ibiz.domain.IBZCaseHistory;
import cn.ibizlab.pms.core.ibiz.filter.IBZCaseHistorySearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IBZCaseHistory] 服务对象接口
 */
public interface IIBZCaseHistoryService extends IService<IBZCaseHistory> {

    boolean create(IBZCaseHistory et);
    void createBatch(List<IBZCaseHistory> list);
    boolean update(IBZCaseHistory et);
    boolean sysUpdate(IBZCaseHistory et);
    void updateBatch(List<IBZCaseHistory> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IBZCaseHistory get(Long key);
    IBZCaseHistory sysGet(Long key);
    IBZCaseHistory getDraft(IBZCaseHistory et);
    boolean checkKey(IBZCaseHistory et);
    boolean save(IBZCaseHistory et);
    void saveBatch(List<IBZCaseHistory> list);
    List<IBZCaseHistory> select(IBZCaseHistorySearchContext context);
    List<IBZCaseHistory> selectDefault(IBZCaseHistorySearchContext context);
    List<IBZCaseHistory> selectSimple(IBZCaseHistorySearchContext context);
    List<IBZCaseHistory> selectView(IBZCaseHistorySearchContext context);

    Page<IBZCaseHistory> searchDefault(IBZCaseHistorySearchContext context);
    List<IBZCaseHistory> selectByAction(Long id);
    void removeByAction(Long id);
    void saveByAction(Long id, List<IBZCaseHistory> list) ;
    IBZCaseHistory dynamicCall(Long key, String action, IBZCaseHistory et);
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

    List<IBZCaseHistory> getIbzcasehistoryByIds(List<Long> ids);
    List<IBZCaseHistory> getIbzcasehistoryByEntities(List<IBZCaseHistory> entities);
}


