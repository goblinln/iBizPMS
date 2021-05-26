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

import cn.ibizlab.pms.core.ibiz.domain.ProjectTeam;
import cn.ibizlab.pms.core.ibiz.filter.ProjectTeamSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[ProjectTeam] 服务对象接口
 */
public interface IProjectTeamService extends IService<ProjectTeam> {

    boolean create(ProjectTeam et);
    void createBatch(List<ProjectTeam> list);
    boolean update(ProjectTeam et);
    boolean sysUpdate(ProjectTeam et);
    void updateBatch(List<ProjectTeam> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    ProjectTeam get(Long key);
    ProjectTeam sysGet(Long key);
    ProjectTeam getDraft(ProjectTeam et);
    boolean checkKey(ProjectTeam et);
    ProjectTeam getUserRole(ProjectTeam et);
    boolean save(ProjectTeam et);
    void saveBatch(List<ProjectTeam> list);
    List<ProjectTeam> select(ProjectTeamSearchContext context);
    List<ProjectTeam> selectDefault(ProjectTeamSearchContext context);
    List<ProjectTeam> selectProjectTeamPm(ProjectTeamSearchContext context);
    List<ProjectTeam> selectRowEditDefault(ProjectTeamSearchContext context);
    List<ProjectTeam> selectTaskCntEstimateConsumedLeft(ProjectTeamSearchContext context);
    List<ProjectTeam> selectView(ProjectTeamSearchContext context);

    Page<ProjectTeam> searchDefault(ProjectTeamSearchContext context);
    Page<ProjectTeam> searchProjectTeamPm(ProjectTeamSearchContext context);
    Page<ProjectTeam> searchRowEditDefault(ProjectTeamSearchContext context);
    Page<ProjectTeam> searchTaskCntEstimateConsumedLeft(ProjectTeamSearchContext context);
    List<ProjectTeam> selectByRoot(Long id);
    void removeByRoot(Long id);
    void saveByRoot(Long id, List<ProjectTeam> list) ;
    ProjectTeam dynamicCall(Long key, String action, ProjectTeam et);
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


