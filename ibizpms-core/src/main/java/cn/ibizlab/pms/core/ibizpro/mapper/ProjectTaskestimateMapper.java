package cn.ibizlab.pms.core.ibizpro.mapper;

import java.util.List;
import org.apache.ibatis.annotations.*;
import java.util.Map;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import java.util.Map;
import org.apache.ibatis.annotations.Select;
import cn.ibizlab.pms.core.ibizpro.domain.ProjectTaskestimate;
import cn.ibizlab.pms.core.ibizpro.filter.ProjectTaskestimateSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface ProjectTaskestimateMapper extends BaseMapper<ProjectTaskestimate> {

    List<ProjectTaskestimate> selectQueryByAccountDetail(@Param("srf") ProjectTaskestimateSearchContext context, @Param("ew") Wrapper<ProjectTaskestimate> wrapper);
    List<ProjectTaskestimate> selectQueryByDefault(@Param("srf") ProjectTaskestimateSearchContext context, @Param("ew") Wrapper<ProjectTaskestimate> wrapper);
    List<ProjectTaskestimate> selectQueryByView(@Param("srf") ProjectTaskestimateSearchContext context, @Param("ew") Wrapper<ProjectTaskestimate> wrapper);

    Page<ProjectTaskestimate> searchAccountDetail(IPage page, @Param("srf") ProjectTaskestimateSearchContext context, @Param("ew") Wrapper<ProjectTaskestimate> wrapper);
    Page<ProjectTaskestimate> searchDefault(IPage page, @Param("srf") ProjectTaskestimateSearchContext context, @Param("ew") Wrapper<ProjectTaskestimate> wrapper);
    @Override
    ProjectTaskestimate selectById(Serializable id);
    @Override
    int insert(ProjectTaskestimate entity);
    @Override
    int updateById(@Param(Constants.ENTITY) ProjectTaskestimate entity);
    @Override
    int update(@Param(Constants.ENTITY) ProjectTaskestimate entity, @Param("ew") Wrapper<ProjectTaskestimate> updateWrapper);
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

}
