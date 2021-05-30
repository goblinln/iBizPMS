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

import cn.ibizlab.pms.core.ibiz.domain.CaseStats;
import cn.ibizlab.pms.core.ibiz.filter.CaseStatsSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[CaseStats] 服务对象接口
 */
public interface ICaseStatsService extends IService<CaseStats> {

    boolean create(CaseStats et);
    void createBatch(List<CaseStats> list);
    boolean update(CaseStats et);
    boolean sysUpdate(CaseStats et);
    void updateBatch(List<CaseStats> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    CaseStats get(Long key);
    CaseStats sysGet(Long key);
    CaseStats getDraft(CaseStats et);
    boolean checkKey(CaseStats et);
    boolean save(CaseStats et);
    void saveBatch(List<CaseStats> list);
    List<CaseStats> select(CaseStatsSearchContext context);
    List<CaseStats> selectDefault(CaseStatsSearchContext context);
    List<CaseStats> selectTestCaseStats(CaseStatsSearchContext context);
    List<CaseStats> selectView(CaseStatsSearchContext context);

    Page<CaseStats> searchDefault(CaseStatsSearchContext context);
    Page<CaseStats> searchTestCaseStats(CaseStatsSearchContext context);
    List<CaseStats> selectByModule(Long id);
    void removeByModule(Long id);
    List<CaseStats> selectByProduct(Long id);
    void removeByProduct(Long id);
    CaseStats dynamicCall(Long key, String action, CaseStats et);
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

}


