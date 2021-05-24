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

import cn.ibizlab.pms.core.ibiz.domain.IbzProWeeklyHistory;
import cn.ibizlab.pms.core.ibiz.filter.IbzProWeeklyHistorySearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IbzProWeeklyHistory] 服务对象接口
 */
public interface IIbzProWeeklyHistoryService extends IService<IbzProWeeklyHistory> {

    boolean create(IbzProWeeklyHistory et);
    void createBatch(List<IbzProWeeklyHistory> list);
    boolean update(IbzProWeeklyHistory et);
    boolean sysUpdate(IbzProWeeklyHistory et);
    void updateBatch(List<IbzProWeeklyHistory> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IbzProWeeklyHistory get(Long key);
    IbzProWeeklyHistory sysGet(Long key);
    IbzProWeeklyHistory getDraft(IbzProWeeklyHistory et);
    boolean checkKey(IbzProWeeklyHistory et);
    boolean save(IbzProWeeklyHistory et);
    void saveBatch(List<IbzProWeeklyHistory> list);
    List<IbzProWeeklyHistory> select(IbzProWeeklyHistorySearchContext context);
    List<IbzProWeeklyHistory> selectDefault(IbzProWeeklyHistorySearchContext context);
    List<IbzProWeeklyHistory> selectSimple(IbzProWeeklyHistorySearchContext context);
    List<IbzProWeeklyHistory> selectView(IbzProWeeklyHistorySearchContext context);

    Page<IbzProWeeklyHistory> searchDefault(IbzProWeeklyHistorySearchContext context);
    List<IbzProWeeklyHistory> selectByAction(Long id);
    void removeByAction(Long id);
    void saveByAction(Long id, List<IbzProWeeklyHistory> list) ;
    IbzProWeeklyHistory dynamicCall(Long key, String action, IbzProWeeklyHistory et);
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

    List<IbzProWeeklyHistory> getIbzproweeklyhistoryByIds(List<Long> ids);
    List<IbzProWeeklyHistory> getIbzproweeklyhistoryByEntities(List<IbzProWeeklyHistory> entities);
}


