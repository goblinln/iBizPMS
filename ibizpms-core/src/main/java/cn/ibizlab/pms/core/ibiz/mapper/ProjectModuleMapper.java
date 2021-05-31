package cn.ibizlab.pms.core.ibiz.mapper;

import java.util.List;
import org.apache.ibatis.annotations.*;
import java.util.Map;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import java.util.Map;
import org.apache.ibatis.annotations.Select;
import cn.ibizlab.pms.core.ibiz.domain.ProjectModule;
import cn.ibizlab.pms.core.ibiz.filter.ProjectModuleSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface ProjectModuleMapper extends BaseMapper<ProjectModule> {

    List<ProjectModule> selectByPath(@Param("srf") ProjectModuleSearchContext context, @Param("ew") Wrapper<ProjectModule> wrapper);
    List<ProjectModule> selectDefault(@Param("srf") ProjectModuleSearchContext context, @Param("ew") Wrapper<ProjectModule> wrapper);
    List<ProjectModule> selectParentModule(@Param("srf") ProjectModuleSearchContext context, @Param("ew") Wrapper<ProjectModule> wrapper);
    List<ProjectModule> selectRoot(@Param("srf") ProjectModuleSearchContext context, @Param("ew") Wrapper<ProjectModule> wrapper);
    List<ProjectModule> selectRoot_NoBranch(@Param("srf") ProjectModuleSearchContext context, @Param("ew") Wrapper<ProjectModule> wrapper);
    List<ProjectModule> selectRoot_Task(@Param("srf") ProjectModuleSearchContext context, @Param("ew") Wrapper<ProjectModule> wrapper);
    List<ProjectModule> selectTaskModules(@Param("srf") ProjectModuleSearchContext context, @Param("ew") Wrapper<ProjectModule> wrapper);
    List<ProjectModule> selectView(@Param("srf") ProjectModuleSearchContext context, @Param("ew") Wrapper<ProjectModule> wrapper);

    Page<ProjectModule> searchByPath(IPage page, @Param("srf") ProjectModuleSearchContext context, @Param("ew") Wrapper<ProjectModule> wrapper);
    Page<ProjectModule> searchDefault(IPage page, @Param("srf") ProjectModuleSearchContext context, @Param("ew") Wrapper<ProjectModule> wrapper);
    Page<ProjectModule> searchParentModule(IPage page, @Param("srf") ProjectModuleSearchContext context, @Param("ew") Wrapper<ProjectModule> wrapper);
    Page<ProjectModule> searchRoot(IPage page, @Param("srf") ProjectModuleSearchContext context, @Param("ew") Wrapper<ProjectModule> wrapper);
    Page<ProjectModule> searchRoot_NoBranch(IPage page, @Param("srf") ProjectModuleSearchContext context, @Param("ew") Wrapper<ProjectModule> wrapper);
    Page<ProjectModule> searchRoot_Task(IPage page, @Param("srf") ProjectModuleSearchContext context, @Param("ew") Wrapper<ProjectModule> wrapper);
    Page<ProjectModule> searchTaskModules(IPage page, @Param("srf") ProjectModuleSearchContext context, @Param("ew") Wrapper<ProjectModule> wrapper);
    @Override
    ProjectModule selectById(Serializable id);
    @Override
    int insert(ProjectModule entity);
    @Override
    int updateById(@Param(Constants.ENTITY) ProjectModule entity);
    @Override
    int update(@Param(Constants.ENTITY) ProjectModule entity, @Param("ew") Wrapper<ProjectModule> updateWrapper);
    @Override
    int deleteById(Serializable id);
    /**
    * 自定义查询SQL
    * @param sql
    * @return
    */
    @Select("${sql}")
    List<JSONObject> selectBySQL(@Param("sql") String sql, @Param("et")Map param);

    /**
    * 自定义更新SQL
    * @param sql
    * @return
    */
    @Update("${sql}")
    boolean updateBySQL(@Param("sql") String sql, @Param("et")Map param);

    /**
    * 自定义插入SQL
    * @param sql
    * @return
    */
    @Insert("${sql}")
    boolean insertBySQL(@Param("sql") String sql, @Param("et")Map param);

    /**
    * 自定义删除SQL
    * @param sql
    * @return
    */
    @Delete("${sql}")
    boolean deleteBySQL(@Param("sql") String sql, @Param("et")Map param);

    List<ProjectModule> selectByParent(@Param("id") Serializable id);

    List<ProjectModule> selectByRoot(@Param("id") Serializable id);

}
