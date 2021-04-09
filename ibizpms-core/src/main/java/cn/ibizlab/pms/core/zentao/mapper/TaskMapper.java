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
import cn.ibizlab.pms.core.zentao.domain.Task;
import cn.ibizlab.pms.core.zentao.filter.TaskSearchContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.io.Serializable;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.alibaba.fastjson.JSONObject;

public interface TaskMapper extends BaseMapper<Task> {

    Page<Task> searchAssignedToMyTask(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Task> searchAssignedToMyTaskPc(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Task> searchBugTask(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Task> searchByModule(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Task> searchChildDefault(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Task> searchChildDefaultMore(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Task> searchChildTask(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Task> searchChildTaskTree(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Task> searchCurFinishTask(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Task> searchDefault(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Task> searchDefaultRow(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Task> searchESBulk(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Task> searchMyAgentTask(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Task> searchMyAllTask(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Task> searchMyCompleteTask(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Task> searchMyCompleteTaskMobDaily(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Task> searchMyCompleteTaskMobMonthly(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Task> searchMyCompleteTaskMonthlyZS(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Task> searchMyCompleteTaskZS(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Task> searchMyFavorites(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Task> searchMyPlansTaskMobMonthly(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Task> searchMyTomorrowPlanTask(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Task> searchMyTomorrowPlanTaskMobDaily(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Task> searchNextWeekCompleteTaskMobZS(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Task> searchNextWeekCompleteTaskZS(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Task> searchNextWeekPlanCompleteTask(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Task> searchPlanTask(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Task> searchProjectAppTask(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Task> searchProjectTask(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Task> searchRootTask(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Task> searchTaskLinkPlan(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Task> searchThisMonthCompleteTaskChoice(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Task> searchThisWeekCompleteTask(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Task> searchThisWeekCompleteTaskChoice(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Task> searchThisWeekCompleteTaskMobZS(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Task> searchThisWeekCompleteTaskZS(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Task> searchTodoListTask(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Map> searchTypeGroup(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    Page<Map> searchTypeGroupPlan(IPage page, @Param("srf") TaskSearchContext context, @Param("ew") Wrapper<Task> wrapper);
    @Override
    Task selectById(Serializable id);
    @Override
    int insert(Task entity);
    @Override
    int updateById(@Param(Constants.ENTITY) Task entity);
    @Override
    int update(@Param(Constants.ENTITY) Task entity, @Param("ew") Wrapper<Task> updateWrapper);
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

    List<Task> selectByModule(@Param("id") Serializable id);

    List<Task> selectByFrombug(@Param("id") Serializable id);

    List<Task> selectByPlan(@Param("id") Serializable id);

    List<Task> selectByProject(@Param("id") Serializable id);

    List<Task> selectByStory(@Param("id") Serializable id);

    List<Task> selectByParent(@Param("id") Serializable id);

}
