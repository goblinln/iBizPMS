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

import cn.ibizlab.pms.core.ibiz.domain.TaskStats;
import cn.ibizlab.pms.core.ibiz.filter.TaskStatsSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[TaskStats] 服务对象接口
 */
public interface ITaskStatsService extends IService<TaskStats> {

    /**
     * 业务实体显示文本名称
     */
    final static String OBJECT_TEXT_NAME = "任务统计";

    /**
     * 业务实体资源路径名
     */
    final static String OBJECT_SOURCE_PATH = "taskstats";

    boolean create(TaskStats et);
    void createBatch(List<TaskStats> list);
    boolean update(TaskStats et);
    void updateBatch(List<TaskStats> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    TaskStats get(Long key);
    TaskStats getDraft(TaskStats et);
    boolean checkKey(TaskStats et);
    boolean save(TaskStats et);
    void saveBatch(List<TaskStats> list);
    Page<TaskStats> searchDefault(TaskStatsSearchContext context);
    Page<TaskStats> searchTaskFinishHuiZong(TaskStatsSearchContext context);
    Page<TaskStats> searchUserFinishTaskSum(TaskStatsSearchContext context);
    TaskStats dynamicCall(Long key, String action, TaskStats et);
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


