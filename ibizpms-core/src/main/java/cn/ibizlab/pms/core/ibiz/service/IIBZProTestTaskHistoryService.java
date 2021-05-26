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

import cn.ibizlab.pms.core.ibiz.domain.IBZProTestTaskHistory;
import cn.ibizlab.pms.core.ibiz.filter.IBZProTestTaskHistorySearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IBZProTestTaskHistory] 服务对象接口
 */
public interface IIBZProTestTaskHistoryService extends IService<IBZProTestTaskHistory> {

    boolean create(IBZProTestTaskHistory et);
    boolean update(IBZProTestTaskHistory et);
    boolean sysUpdate(IBZProTestTaskHistory et);
    boolean remove(Long key);
    IBZProTestTaskHistory get(Long key);
    IBZProTestTaskHistory sysGet(Long key);
    IBZProTestTaskHistory getDraft(IBZProTestTaskHistory et);
    boolean checkKey(IBZProTestTaskHistory et);
    boolean save(IBZProTestTaskHistory et);
    List<IBZProTestTaskHistory> select(IBZProTestTaskHistorySearchContext context);
    List<IBZProTestTaskHistory> selectDefault(IBZProTestTaskHistorySearchContext context);
    List<IBZProTestTaskHistory> selectSimple(IBZProTestTaskHistorySearchContext context);
    List<IBZProTestTaskHistory> selectView(IBZProTestTaskHistorySearchContext context);

    Page<IBZProTestTaskHistory> searchDefault(IBZProTestTaskHistorySearchContext context);
    List<IBZProTestTaskHistory> selectByAction(Long id);
    void removeByAction(Long id);
    void saveByAction(Long id, List<IBZProTestTaskHistory> list) ;
    IBZProTestTaskHistory dynamicCall(Long key, String action, IBZProTestTaskHistory et);
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

    List<IBZProTestTaskHistory> getIbzprotesttaskhistoryByIds(List<Long> ids);
    List<IBZProTestTaskHistory> getIbzprotesttaskhistoryByEntities(List<IBZProTestTaskHistory> entities);
}


