package cn.ibizlab.pms.core.zentao.service;

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

import cn.ibizlab.pms.core.zentao.domain.TaskEstimateStats;
import cn.ibizlab.pms.core.zentao.filter.TaskEstimateStatsSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[TaskEstimateStats] 服务对象接口
 */
public interface ITaskEstimateStatsService extends IService<TaskEstimateStats> {

    boolean create(TaskEstimateStats et);
    void createBatch(List<TaskEstimateStats> list);
    boolean update(TaskEstimateStats et);
    boolean sysUpdate(TaskEstimateStats et);
    void updateBatch(List<TaskEstimateStats> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    TaskEstimateStats get(Long key);
    TaskEstimateStats sysGet(Long key);
    TaskEstimateStats getDraft(TaskEstimateStats et);
    boolean checkKey(TaskEstimateStats et);
    boolean save(TaskEstimateStats et);
    void saveBatch(List<TaskEstimateStats> list);
    List<TaskEstimateStats> select(TaskEstimateStatsSearchContext context);
    
    List<TaskEstimateStats> selectQueryByActionMonth(TaskEstimateStatsSearchContext context);
    List<TaskEstimateStats> selectQueryByActionYear(TaskEstimateStatsSearchContext context);
    List<TaskEstimateStats> selectQueryByDefault(TaskEstimateStatsSearchContext context);
    List<TaskEstimateStats> selectQueryByTaskEstimateStatsSum(TaskEstimateStatsSearchContext context);
    List<TaskEstimateStats> selectQueryByView(TaskEstimateStatsSearchContext context);

    Page<TaskEstimateStats> searchActionMonth(TaskEstimateStatsSearchContext context);
    Page<TaskEstimateStats> searchActionYear(TaskEstimateStatsSearchContext context);
    Page<TaskEstimateStats> searchDefault(TaskEstimateStatsSearchContext context);
    TaskEstimateStats dynamicCall(Long key, String action, TaskEstimateStats et);
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


