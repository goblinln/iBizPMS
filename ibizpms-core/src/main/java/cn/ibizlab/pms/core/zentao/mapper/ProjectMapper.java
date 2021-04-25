package cn.ibizlab.pms.core.zentao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.*;
import java.util.Map;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import java.util.Map;
import org.apache.ibatis.annotations.Select;
import cn.ibizlab.pms.core.zentao.domain.Project;
import cn.ibizlab.pms.core.zentao.filter.ProjectSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface ProjectMapper extends BaseMapper<Project> {

    List<Project> selectQueryByBugSelectableProjectList(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectQueryByCurDefaultQuery(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectQueryByCurDefaultQueryExp(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectQueryByCurPlanProject(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectQueryByCurProduct(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectQueryByCurUser(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectQueryByCurUserSa(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectQueryByDefault(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectQueryByDeveloperQuery(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectQueryByESBulk(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectQueryByInvolvedProject(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectQueryByInvolvedProjectStoryTaskBug(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectQueryByMyProject(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectQueryByOpenByQuery(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectQueryByOpenQuery(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectQueryByPMQuery(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectQueryByPOQuery(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectQueryByProjectTeam(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectQueryByQDQuery(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectQueryByRDQuery(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectQueryByStoryProject(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectQueryByUnDoneProject(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectQueryByView(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);

    Page<Project> searchBugProject(IPage page, @Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    Page<Project> searchCurDefaultQuery(IPage page, @Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    Page<Project> searchCurDefaultQueryExp(IPage page, @Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    Page<Project> searchCurPlanProject(IPage page, @Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    Page<Project> searchCurProduct(IPage page, @Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    Page<Project> searchCurUser(IPage page, @Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    Page<Project> searchCurUserSa(IPage page, @Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    Page<Project> searchDefault(IPage page, @Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    Page<Project> searchDeveloperQuery(IPage page, @Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    Page<Project> searchESBulk(IPage page, @Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    Page<Project> searchInvolvedProject(IPage page, @Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    Page<Project> searchInvolvedProject_StoryTaskBug(IPage page, @Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    Page<Project> searchMyProject(IPage page, @Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    Page<Project> searchOpenByQuery(IPage page, @Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    Page<Project> searchOpenQuery(IPage page, @Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    Page<Project> searchPMQuery(IPage page, @Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    Page<Project> searchPOQuery(IPage page, @Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    Page<Project> searchProjectTeam(IPage page, @Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    Page<Project> searchQDQuery(IPage page, @Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    Page<Project> searchRDQuery(IPage page, @Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    Page<Project> searchStoryProject(IPage page, @Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    Page<Project> searchUnDoneProject(IPage page, @Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    @Override
    Project selectById(Serializable id);
    @Override
    int insert(Project entity);
    @Override
    int updateById(@Param(Constants.ENTITY) Project entity);
    @Override
    int update(@Param(Constants.ENTITY) Project entity, @Param("ew") Wrapper<Project> updateWrapper);
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

    List<Project> selectByParent(@Param("id") Serializable id);

}
