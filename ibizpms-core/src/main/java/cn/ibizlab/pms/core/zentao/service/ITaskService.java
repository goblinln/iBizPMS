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

import cn.ibizlab.pms.core.zentao.domain.Task;
import cn.ibizlab.pms.core.zentao.filter.TaskSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[Task] 服务对象接口
 */
public interface ITaskService extends IService<Task> {

    boolean create(Task et);
    void createBatch(List<Task> list);
    boolean update(Task et);
    boolean sysUpdate(Task et);
    void updateBatch(List<Task> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    Task get(Long key);
    Task sysGet(Long key);
    Task getDraft(Task et);
    Task activate(Task et);
    Task assignTo(Task et);
    Task cancel(Task et);
    boolean checkKey(Task et);
    Task close(Task et);
    Task computeBeginAndEnd(Task et);
    Task computeHours4Multiple(Task et);
    Task computeWorkingHours(Task et);
    Task confirmStoryChange(Task et);
    Task createByCycle(Task et);
    Task createCycleTasks(Task et);
    Task delete(Task et);
    Task deleteEstimate(Task et);
    Task editEstimate(Task et);
    Task finish(Task et);
    Task getNextTeamUser(Task et);
    Task getTeamUserLeftActivity(Task et);
    Task getTeamUserLeftStart(Task et);
    Task getUsernames(Task et);
    Task linkPlan(Task et);
    Task otherUpdate(Task et);
    Task pause(Task et);
    Task recordEstimate(Task et);
    Task recordTimZeroLeftAfterContinue(Task et);
    Task recordTimateZeroLeft(Task et);
    Task recordTimateZeroLeftAfterStart(Task et);
    Task restart(Task et);
    boolean save(Task et);
    void saveBatch(List<Task> list);
    Task sendMessage(Task et);
    Task sendMsgPreProcess(Task et);
    Task start(Task et);
    boolean startBatch(List<Task> etList);
    Task taskFavorites(Task et);
    Task taskForward(Task et);
    Task taskNFavorites(Task et);
    Task updateParentStatus(Task et);
    Task updateRelatedPlanStatus(Task et);
    Task updateStoryVersion(Task et);
    List<Task> select(TaskSearchContext context);
    List<Task> selectAssignedToMyTask(TaskSearchContext context);
    List<Task> selectAssignedToMyTaskPc(TaskSearchContext context);
    List<Task> selectBugTask(TaskSearchContext context);
    List<Task> selectByModule(TaskSearchContext context);
    List<Task> selectChildDefault(TaskSearchContext context);
    List<Task> selectChildDefaultMore(TaskSearchContext context);
    List<Task> selectChildTask(TaskSearchContext context);
    List<Task> selectChildTaskTree(TaskSearchContext context);
    List<Task> selectCurFinishTask(TaskSearchContext context);
    List<Task> selectCurProjectTaskQuery(TaskSearchContext context);
    List<Task> selectDefault(TaskSearchContext context);
    List<Task> selectDefaultRow(TaskSearchContext context);
    List<Task> selectESBulk(TaskSearchContext context);
    List<Task> selectMyAgentTask(TaskSearchContext context);
    List<Task> selectMyAllTask(TaskSearchContext context);
    List<Task> selectMyCompleteTask(TaskSearchContext context);
    List<Task> selectMyCompleteTaskMobDaily(TaskSearchContext context);
    List<Task> selectMyCompleteTaskMobMonthly(TaskSearchContext context);
    List<Task> selectMyCompleteTaskMonthlyZS(TaskSearchContext context);
    List<Task> selectMyCompleteTaskZS(TaskSearchContext context);
    List<Task> selectMyCreateOrPartake(TaskSearchContext context);
    List<Task> selectMyFavorites(TaskSearchContext context);
    List<Task> selectMyPlansTaskMobMonthly(TaskSearchContext context);
    List<Task> selectMyReProject(TaskSearchContext context);
    List<Task> selectMyTomorrowPlanTask(TaskSearchContext context);
    List<Task> selectMyTomorrowPlanTaskMobDaily(TaskSearchContext context);
    List<Task> selectNextWeekCompleteTaskMobZS(TaskSearchContext context);
    List<Task> selectNextWeekCompleteTaskZS(TaskSearchContext context);
    List<Task> selectNextWeekPlanCompleteTask(TaskSearchContext context);
    List<Task> selectPlanTask(TaskSearchContext context);
    List<Task> selectProjectAppTask(TaskSearchContext context);
    List<Task> selectProjectTask(TaskSearchContext context);
    List<Task> selectRootTask(TaskSearchContext context);
    List<Task> selectSimple(TaskSearchContext context);
    List<Task> selectTaskLinkPlan(TaskSearchContext context);
    List<Task> selectThisMonthCompleteTaskChoice(TaskSearchContext context);
    List<Task> selectThisWeekCompleteTask(TaskSearchContext context);
    List<Task> selectThisWeekCompleteTaskChoice(TaskSearchContext context);
    List<Task> selectThisWeekCompleteTaskMobZS(TaskSearchContext context);
    List<Task> selectThisWeekCompleteTaskZS(TaskSearchContext context);
    List<Task> selectTodoListTask(TaskSearchContext context);
    List<Task> selectTypeGroup(TaskSearchContext context);
    List<Task> selectTypeGroupPlan(TaskSearchContext context);
    List<Task> selectView(TaskSearchContext context);

    Page<Task> searchAssignedToMyTask(TaskSearchContext context);
    Page<Task> searchAssignedToMyTaskPc(TaskSearchContext context);
    Page<Task> searchBugTask(TaskSearchContext context);
    Page<Task> searchByModule(TaskSearchContext context);
    Page<Task> searchChildDefault(TaskSearchContext context);
    Page<Task> searchChildDefaultMore(TaskSearchContext context);
    Page<Task> searchChildTask(TaskSearchContext context);
    Page<Task> searchChildTaskTree(TaskSearchContext context);
    Page<Task> searchCurFinishTask(TaskSearchContext context);
    Page<Task> searchCurProjectTaskQuery(TaskSearchContext context);
    Page<Task> searchDefault(TaskSearchContext context);
    Page<Task> searchDefaultRow(TaskSearchContext context);
    Page<Task> searchESBulk(TaskSearchContext context);
    Page<Task> searchMyAgentTask(TaskSearchContext context);
    Page<Task> searchMyAllTask(TaskSearchContext context);
    Page<Task> searchMyCompleteTask(TaskSearchContext context);
    Page<Task> searchMyCompleteTaskMobDaily(TaskSearchContext context);
    Page<Task> searchMyCompleteTaskMobMonthly(TaskSearchContext context);
    Page<Task> searchMyCompleteTaskMonthlyZS(TaskSearchContext context);
    Page<Task> searchMyCompleteTaskZS(TaskSearchContext context);
    Page<Task> searchMyCreateOrPartake(TaskSearchContext context);
    Page<Task> searchMyFavorites(TaskSearchContext context);
    Page<Task> searchMyPlansTaskMobMonthly(TaskSearchContext context);
    Page<Task> searchMyReProject(TaskSearchContext context);
    Page<Task> searchMyTomorrowPlanTask(TaskSearchContext context);
    Page<Task> searchMyTomorrowPlanTaskMobDaily(TaskSearchContext context);
    Page<Task> searchNextWeekCompleteTaskMobZS(TaskSearchContext context);
    Page<Task> searchNextWeekCompleteTaskZS(TaskSearchContext context);
    Page<Task> searchNextWeekPlanCompleteTask(TaskSearchContext context);
    Page<Task> searchPlanTask(TaskSearchContext context);
    Page<Task> searchProjectAppTask(TaskSearchContext context);
    Page<Task> searchProjectTask(TaskSearchContext context);
    Page<Task> searchRootTask(TaskSearchContext context);
    Page<Task> searchTaskLinkPlan(TaskSearchContext context);
    Page<Task> searchThisMonthCompleteTaskChoice(TaskSearchContext context);
    Page<Task> searchThisWeekCompleteTask(TaskSearchContext context);
    Page<Task> searchThisWeekCompleteTaskChoice(TaskSearchContext context);
    Page<Task> searchThisWeekCompleteTaskMobZS(TaskSearchContext context);
    Page<Task> searchThisWeekCompleteTaskZS(TaskSearchContext context);
    Page<Task> searchTodoListTask(TaskSearchContext context);
    Page<Map> searchTypeGroup(TaskSearchContext context);
    Page<Map> searchTypeGroupPlan(TaskSearchContext context);
    List<Task> selectByModule(Long id);
    void removeByModule(Long id);
    List<Task> selectByFrombug(Long id);
    void removeByFrombug(Long id);
    List<Task> selectByPlan(Long id);
    void removeByPlan(Long id);
    List<Task> selectByProject(Long id);
    void removeByProject(Long id);
    List<Task> selectByStory(Long id);
    void removeByStory(Long id);
    List<Task> selectByParent(Long id);
    void removeByParent(Collection<Long> ids);
    void removeByParent(Long id);
    Task dynamicCall(Long key, String action, Task et);
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

    List<Task> getTaskByIds(List<Long> ids);
    List<Task> getTaskByEntities(List<Task> entities);
}


