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

import cn.ibizlab.pms.core.ibiz.domain.ProjectModule;
import cn.ibizlab.pms.core.ibiz.filter.ProjectModuleSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[ProjectModule] 服务对象接口
 */
public interface IProjectModuleService extends IService<ProjectModule> {

    boolean create(ProjectModule et);
    boolean update(ProjectModule et);
    boolean sysUpdate(ProjectModule et);
    boolean remove(Long key);
    ProjectModule get(Long key);
    ProjectModule sysGet(Long key);
    ProjectModule getDraft(ProjectModule et);
    boolean checkKey(ProjectModule et);
    ProjectModule fix(ProjectModule et);
    ProjectModule removeModule(ProjectModule et);
    boolean save(ProjectModule et);
    List<ProjectModule> select(ProjectModuleSearchContext context);
    List<ProjectModule> selectByPath(ProjectModuleSearchContext context);
    List<ProjectModule> selectDefault(ProjectModuleSearchContext context);
    List<ProjectModule> selectParentModule(ProjectModuleSearchContext context);
    List<ProjectModule> selectRoot(ProjectModuleSearchContext context);
    List<ProjectModule> selectRoot_NoBranch(ProjectModuleSearchContext context);
    List<ProjectModule> selectRoot_Task(ProjectModuleSearchContext context);
    List<ProjectModule> selectTaskModules(ProjectModuleSearchContext context);
    List<ProjectModule> selectView(ProjectModuleSearchContext context);

    Page<ProjectModule> searchByPath(ProjectModuleSearchContext context);
    Page<ProjectModule> searchDefault(ProjectModuleSearchContext context);
    Page<ProjectModule> searchParentModule(ProjectModuleSearchContext context);
    Page<ProjectModule> searchRoot(ProjectModuleSearchContext context);
    Page<ProjectModule> searchRoot_NoBranch(ProjectModuleSearchContext context);
    Page<ProjectModule> searchRoot_Task(ProjectModuleSearchContext context);
    Page<ProjectModule> searchTaskModules(ProjectModuleSearchContext context);
    List<ProjectModule> selectByParent(Long id);
    void removeByParent(Long id);
    List<ProjectModule> selectByRoot(Long id);
    void removeByRoot(Long id);
    ProjectModule dynamicCall(Long key, String action, ProjectModule et);
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

    List<ProjectModule> getProjectmoduleByIds(List<Long> ids);
    List<ProjectModule> getProjectmoduleByEntities(List<ProjectModule> entities);
}


