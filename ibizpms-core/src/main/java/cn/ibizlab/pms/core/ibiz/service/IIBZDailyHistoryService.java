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

import cn.ibizlab.pms.core.ibiz.domain.IBZDailyHistory;
import cn.ibizlab.pms.core.ibiz.filter.IBZDailyHistorySearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IBZDailyHistory] 服务对象接口
 */
public interface IIBZDailyHistoryService extends IService<IBZDailyHistory> {

    boolean create(IBZDailyHistory et);
    boolean update(IBZDailyHistory et);
    boolean sysUpdate(IBZDailyHistory et);
    boolean remove(Long key);
    IBZDailyHistory get(Long key);
    IBZDailyHistory sysGet(Long key);
    IBZDailyHistory getDraft(IBZDailyHistory et);
    boolean checkKey(IBZDailyHistory et);
    boolean save(IBZDailyHistory et);
    List<IBZDailyHistory> select(IBZDailyHistorySearchContext context);
    List<IBZDailyHistory> selectDefault(IBZDailyHistorySearchContext context);
    List<IBZDailyHistory> selectSimple(IBZDailyHistorySearchContext context);
    List<IBZDailyHistory> selectView(IBZDailyHistorySearchContext context);

    Page<IBZDailyHistory> searchDefault(IBZDailyHistorySearchContext context);
    List<IBZDailyHistory> selectByAction(Long id);
    void removeByAction(Long id);
    void saveByAction(Long id, List<IBZDailyHistory> list) ;
    IBZDailyHistory dynamicCall(Long key, String action, IBZDailyHistory et);
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

    List<IBZDailyHistory> getIbzdailyhistoryByIds(List<Long> ids);
    List<IBZDailyHistory> getIbzdailyhistoryByEntities(List<IBZDailyHistory> entities);
}


