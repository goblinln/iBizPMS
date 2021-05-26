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

import cn.ibizlab.pms.core.ibiz.domain.IBZTestReportHistory;
import cn.ibizlab.pms.core.ibiz.filter.IBZTestReportHistorySearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IBZTestReportHistory] 服务对象接口
 */
public interface IIBZTestReportHistoryService extends IService<IBZTestReportHistory> {

    boolean create(IBZTestReportHistory et);
    boolean update(IBZTestReportHistory et);
    boolean sysUpdate(IBZTestReportHistory et);
    boolean remove(Long key);
    IBZTestReportHistory get(Long key);
    IBZTestReportHistory sysGet(Long key);
    IBZTestReportHistory getDraft(IBZTestReportHistory et);
    boolean checkKey(IBZTestReportHistory et);
    boolean save(IBZTestReportHistory et);
    List<IBZTestReportHistory> select(IBZTestReportHistorySearchContext context);
    List<IBZTestReportHistory> selectDefault(IBZTestReportHistorySearchContext context);
    List<IBZTestReportHistory> selectSimple(IBZTestReportHistorySearchContext context);
    List<IBZTestReportHistory> selectView(IBZTestReportHistorySearchContext context);

    Page<IBZTestReportHistory> searchDefault(IBZTestReportHistorySearchContext context);
    List<IBZTestReportHistory> selectByAction(Long id);
    void removeByAction(Long id);
    void saveByAction(Long id, List<IBZTestReportHistory> list) ;
    IBZTestReportHistory dynamicCall(Long key, String action, IBZTestReportHistory et);
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

    List<IBZTestReportHistory> getIbztestreporthistoryByIds(List<Long> ids);
    List<IBZTestReportHistory> getIbztestreporthistoryByEntities(List<IBZTestReportHistory> entities);
}


