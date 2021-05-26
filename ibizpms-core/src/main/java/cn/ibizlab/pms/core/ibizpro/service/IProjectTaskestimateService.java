package cn.ibizlab.pms.core.ibizpro.service;

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

import cn.ibizlab.pms.core.ibizpro.domain.ProjectTaskestimate;
import cn.ibizlab.pms.core.ibizpro.filter.ProjectTaskestimateSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[ProjectTaskestimate] 服务对象接口
 */
public interface IProjectTaskestimateService extends IService<ProjectTaskestimate> {

    boolean create(ProjectTaskestimate et);
    boolean update(ProjectTaskestimate et);
    boolean sysUpdate(ProjectTaskestimate et);
    boolean remove(String key);
    ProjectTaskestimate get(String key);
    ProjectTaskestimate sysGet(String key);
    ProjectTaskestimate getDraft(ProjectTaskestimate et);
    boolean checkKey(ProjectTaskestimate et);
    boolean save(ProjectTaskestimate et);
    List<ProjectTaskestimate> select(ProjectTaskestimateSearchContext context);
    List<ProjectTaskestimate> selectAccountDetail(ProjectTaskestimateSearchContext context);
    List<ProjectTaskestimate> selectDefault(ProjectTaskestimateSearchContext context);
    List<ProjectTaskestimate> selectView(ProjectTaskestimateSearchContext context);

    Page<ProjectTaskestimate> searchAccountDetail(ProjectTaskestimateSearchContext context);
    Page<ProjectTaskestimate> searchDefault(ProjectTaskestimateSearchContext context);
    ProjectTaskestimate dynamicCall(String key, String action, ProjectTaskestimate et);
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


