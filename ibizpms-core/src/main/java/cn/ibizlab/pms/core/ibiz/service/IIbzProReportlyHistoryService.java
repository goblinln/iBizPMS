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

import cn.ibizlab.pms.core.ibiz.domain.IbzProReportlyHistory;
import cn.ibizlab.pms.core.ibiz.filter.IbzProReportlyHistorySearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IbzProReportlyHistory] 服务对象接口
 */
public interface IIbzProReportlyHistoryService extends IService<IbzProReportlyHistory> {

    boolean create(IbzProReportlyHistory et);
    boolean update(IbzProReportlyHistory et);
    boolean sysUpdate(IbzProReportlyHistory et);
    boolean remove(Long key);
    IbzProReportlyHistory get(Long key);
    IbzProReportlyHistory sysGet(Long key);
    IbzProReportlyHistory getDraft(IbzProReportlyHistory et);
    boolean checkKey(IbzProReportlyHistory et);
    boolean save(IbzProReportlyHistory et);
    List<IbzProReportlyHistory> select(IbzProReportlyHistorySearchContext context);
    List<IbzProReportlyHistory> selectDefault(IbzProReportlyHistorySearchContext context);
    List<IbzProReportlyHistory> selectSimple(IbzProReportlyHistorySearchContext context);
    List<IbzProReportlyHistory> selectView(IbzProReportlyHistorySearchContext context);

    Page<IbzProReportlyHistory> searchDefault(IbzProReportlyHistorySearchContext context);
    List<IbzProReportlyHistory> selectByAction(Long id);
    void removeByAction(Long id);
    void saveByAction(Long id, List<IbzProReportlyHistory> list) ;
    IbzProReportlyHistory dynamicCall(Long key, String action, IbzProReportlyHistory et);
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

    List<IbzProReportlyHistory> getIbzproreportlyhistoryByIds(List<Long> ids);
    List<IbzProReportlyHistory> getIbzproreportlyhistoryByEntities(List<IbzProReportlyHistory> entities);
}


