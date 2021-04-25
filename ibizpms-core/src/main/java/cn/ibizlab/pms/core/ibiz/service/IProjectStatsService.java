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

import cn.ibizlab.pms.core.ibiz.domain.ProjectStats;
import cn.ibizlab.pms.core.ibiz.filter.ProjectStatsSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[ProjectStats] 服务对象接口
 */
public interface IProjectStatsService extends IService<ProjectStats> {

    boolean create(ProjectStats et);
    void createBatch(List<ProjectStats> list);
    boolean update(ProjectStats et);
    boolean sysUpdate(ProjectStats et);
    void updateBatch(List<ProjectStats> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    ProjectStats get(Long key);
    ProjectStats sysGet(Long key);
    ProjectStats getDraft(ProjectStats et);
    boolean checkKey(ProjectStats et);
    ProjectStats projectQualitySum(ProjectStats et);
    boolean projectQualitySumBatch(List<ProjectStats> etList);
    boolean save(ProjectStats et);
    void saveBatch(List<ProjectStats> list);
    List<ProjectStats> select(ProjectStatsSearchContext context);
    Page<ProjectStats> searchDefault(ProjectStatsSearchContext context);
    Page<ProjectStats> searchNoOpenProduct(ProjectStatsSearchContext context);
    Page<ProjectStats> searchProjectBugType(ProjectStatsSearchContext context);
    Page<ProjectStats> searchProjectInputStats(ProjectStatsSearchContext context);
    Page<ProjectStats> searchProjectProgress(ProjectStatsSearchContext context);
    Page<ProjectStats> searchProjectQuality(ProjectStatsSearchContext context);
    Page<ProjectStats> searchProjectStoryStageStats(ProjectStatsSearchContext context);
    Page<ProjectStats> searchProjectStoryStatusStats(ProjectStatsSearchContext context);
    Page<ProjectStats> searchProjectTaskCountByTaskStatus(ProjectStatsSearchContext context);
    Page<ProjectStats> searchProjectTaskCountByType(ProjectStatsSearchContext context);
    Page<ProjectStats> searchTaskTime(ProjectStatsSearchContext context);
    ProjectStats dynamicCall(Long key, String action, ProjectStats et);
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


