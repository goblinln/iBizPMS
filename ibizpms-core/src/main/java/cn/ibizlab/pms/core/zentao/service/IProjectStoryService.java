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

import cn.ibizlab.pms.core.zentao.domain.ProjectStory;
import cn.ibizlab.pms.core.zentao.filter.ProjectStorySearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[ProjectStory] 服务对象接口
 */
public interface IProjectStoryService extends IService<ProjectStory> {

    boolean create(ProjectStory et);
    void createBatch(List<ProjectStory> list);
    boolean update(ProjectStory et);
    boolean sysUpdate(ProjectStory et);
    void updateBatch(List<ProjectStory> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    ProjectStory get(String key);
    ProjectStory sysGet(String key);
    ProjectStory getDraft(ProjectStory et);
    boolean checkKey(ProjectStory et);
    boolean save(ProjectStory et);
    void saveBatch(List<ProjectStory> list);
    Page<ProjectStory> searchDefault(ProjectStorySearchContext context);
    List<ProjectStory> selectByProduct(Long id);
    void removeByProduct(Long id);
    List<ProjectStory> selectByProject(Long id);
    void removeByProject(Long id);
    List<ProjectStory> selectByStory(Long id);
    void removeByStory(Long id);
    ProjectStory dynamicCall(String key, String action, ProjectStory et);
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

    List<ProjectStory> getProjectstoryByIds(List<String> ids);
    List<ProjectStory> getProjectstoryByEntities(List<ProjectStory> entities);
}


