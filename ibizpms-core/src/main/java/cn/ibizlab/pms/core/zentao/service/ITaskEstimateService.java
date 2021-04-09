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

import cn.ibizlab.pms.core.zentao.domain.TaskEstimate;
import cn.ibizlab.pms.core.zentao.filter.TaskEstimateSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[TaskEstimate] 服务对象接口
 */
public interface ITaskEstimateService extends IService<TaskEstimate> {

    /**
     * 业务实体显示文本名称
     */
    final static String OBJECT_TEXT_NAME = "任务预计";

    /**
     * 业务实体资源路径名
     */
    final static String OBJECT_SOURCE_PATH = "taskestimates";

    boolean create(TaskEstimate et);
    void createBatch(List<TaskEstimate> list);
    boolean update(TaskEstimate et);
    void updateBatch(List<TaskEstimate> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    TaskEstimate get(Long key);
    TaskEstimate getDraft(TaskEstimate et);
    boolean checkKey(TaskEstimate et);
    TaskEstimate pMEvaluation(TaskEstimate et);
    boolean pMEvaluationBatch(List<TaskEstimate> etList);
    boolean save(TaskEstimate et);
    void saveBatch(List<TaskEstimate> list);
    Page<TaskEstimate> searchActionMonth(TaskEstimateSearchContext context);
    Page<TaskEstimate> searchActionYear(TaskEstimateSearchContext context);
    Page<TaskEstimate> searchDefault(TaskEstimateSearchContext context);
    Page<TaskEstimate> searchDefaults(TaskEstimateSearchContext context);
    Page<TaskEstimate> searchProjectActionMonth(TaskEstimateSearchContext context);
    Page<TaskEstimate> searchProjectActionYear(TaskEstimateSearchContext context);
    Page<TaskEstimate> searchProjectTaskEstimate(TaskEstimateSearchContext context);
    List<TaskEstimate> selectByTask(Long id);
    void removeByTask(Long id);
    void saveByTask(Long id, List<TaskEstimate> list) ;
    TaskEstimate dynamicCall(Long key, String action, TaskEstimate et);
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


