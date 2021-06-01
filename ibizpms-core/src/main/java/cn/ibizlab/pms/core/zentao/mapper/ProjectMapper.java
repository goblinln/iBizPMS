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

    List<Project> selectAccount(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectBugSelectableProjectList(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectCurDefaultQuery(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectCurDefaultQueryExp(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectCurPlanProject(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectCurProduct(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectCurUser(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectCurUserSa(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectDefault(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectDeveloperQuery(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectESBulk(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectInvolvedProject(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectInvolvedProjectStoryTaskBug(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectMy(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectMyProject(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectOpenByQuery(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectOpenQuery(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectProjectTeam(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectStoryProject(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectUnDoneProject(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    List<Project> selectView(@Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);

    Page<Project> searchAccount(IPage page, @Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
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
    Page<Project> searchMy(IPage page, @Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    Page<Project> searchMyProject(IPage page, @Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    Page<Project> searchOpenByQuery(IPage page, @Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    Page<Project> searchOpenQuery(IPage page, @Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
    Page<Project> searchProjectTeam(IPage page, @Param("srf") ProjectSearchContext context, @Param("ew") Wrapper<Project> wrapper);
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
