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

import cn.ibizlab.pms.core.ibiz.domain.IbzProMonthlyHistory;
import cn.ibizlab.pms.core.ibiz.filter.IbzProMonthlyHistorySearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IbzProMonthlyHistory] 服务对象接口
 */
public interface IIbzProMonthlyHistoryService extends IService<IbzProMonthlyHistory> {

    boolean create(IbzProMonthlyHistory et);
    boolean update(IbzProMonthlyHistory et);
    boolean sysUpdate(IbzProMonthlyHistory et);
    boolean remove(Long key);
    IbzProMonthlyHistory get(Long key);
    IbzProMonthlyHistory sysGet(Long key);
    IbzProMonthlyHistory getDraft(IbzProMonthlyHistory et);
    boolean checkKey(IbzProMonthlyHistory et);
    boolean save(IbzProMonthlyHistory et);
    List<IbzProMonthlyHistory> select(IbzProMonthlyHistorySearchContext context);
    List<IbzProMonthlyHistory> selectDefault(IbzProMonthlyHistorySearchContext context);
    List<IbzProMonthlyHistory> selectSimple(IbzProMonthlyHistorySearchContext context);
    List<IbzProMonthlyHistory> selectView(IbzProMonthlyHistorySearchContext context);

    Page<IbzProMonthlyHistory> searchDefault(IbzProMonthlyHistorySearchContext context);
    List<IbzProMonthlyHistory> selectByAction(Long id);
    void removeByAction(Long id);
    void saveByAction(Long id, List<IbzProMonthlyHistory> list) ;
    IbzProMonthlyHistory dynamicCall(Long key, String action, IbzProMonthlyHistory et);
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

    List<IbzProMonthlyHistory> getIbzpromonthlyhistoryByIds(List<Long> ids);
    List<IbzProMonthlyHistory> getIbzpromonthlyhistoryByEntities(List<IbzProMonthlyHistory> entities);
}


