package cn.ibizlab.pms.core.ibiz.mapper;

import java.util.List;
import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.apache.ibatis.annotations.*;
import java.util.Map;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import java.util.Map;
import org.apache.ibatis.annotations.Select;
import cn.ibizlab.pms.core.ibiz.domain.ProjectTeam;
import cn.ibizlab.pms.core.ibiz.filter.ProjectTeamSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface ProjectTeamMapper extends BaseMapper<ProjectTeam> {

    Page<ProjectTeam> searchDefault(IPage page, @Param("srf") ProjectTeamSearchContext context, @Param("ew") Wrapper<ProjectTeam> wrapper);
    Page<ProjectTeam> searchProjectTeamPm(IPage page, @Param("srf") ProjectTeamSearchContext context, @Param("ew") Wrapper<ProjectTeam> wrapper);
    Page<ProjectTeam> searchRowEditDefault(IPage page, @Param("srf") ProjectTeamSearchContext context, @Param("ew") Wrapper<ProjectTeam> wrapper);
    Page<ProjectTeam> searchTaskCntEstimateConsumedLeft(IPage page, @Param("srf") ProjectTeamSearchContext context, @Param("ew") Wrapper<ProjectTeam> wrapper);
    @Override
    ProjectTeam selectById(Serializable id);
    @Override
    int insert(ProjectTeam entity);
    @Override
    int updateById(@Param(Constants.ENTITY) ProjectTeam entity);
    @Override
    int update(@Param(Constants.ENTITY) ProjectTeam entity, @Param("ew") Wrapper<ProjectTeam> updateWrapper);
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

    List<ProjectTeam> selectByRoot(@Param("id") Serializable id);

}
