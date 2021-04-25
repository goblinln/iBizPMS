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
    boolean activateBatch(List<Task> etList);
    Task assignTo(Task et);
    boolean assignToBatch(List<Task> etList);
    Task cancel(Task et);
    boolean cancelBatch(List<Task> etList);
    boolean checkKey(Task et);
    Task close(Task et);
    boolean closeBatch(List<Task> etList);
    Task confirmStoryChange(Task et);
    boolean confirmStoryChangeBatch(List<Task> etList);
    Task createCycleTasks(Task et);
    boolean createCycleTasksBatch(List<Task> etList);
    Task deleteEstimate(Task et);
    boolean deleteEstimateBatch(List<Task> etList);
    Task editEstimate(Task et);
    boolean editEstimateBatch(List<Task> etList);
    Task finish(Task et);
    boolean finishBatch(List<Task> etList);
    Task getNextTeamUser(Task et);
    boolean getNextTeamUserBatch(List<Task> etList);
    Task getTeamUserLeftActivity(Task et);
    boolean getTeamUserLeftActivityBatch(List<Task> etList);
    Task getTeamUserLeftStart(Task et);
    boolean getTeamUserLeftStartBatch(List<Task> etList);
    Task getUsernames(Task et);
    boolean getUsernamesBatch(List<Task> etList);
    Task linkPlan(Task et);
    boolean linkPlanBatch(List<Task> etList);
    Task otherUpdate(Task et);
    boolean otherUpdateBatch(List<Task> etList);
    Task pause(Task et);
    boolean pauseBatch(List<Task> etList);
    Task recordEstimate(Task et);
    boolean recordEstimateBatch(List<Task> etList);
    Task restart(Task et);
    boolean restartBatch(List<Task> etList);
    boolean save(Task et);
    void saveBatch(List<Task> list);
    Task sendMessage(Task et);
    boolean sendMessageBatch(List<Task> etList);
    Task sendMsgPreProcess(Task et);
    boolean sendMsgPreProcessBatch(List<Task> etList);
    Task start(Task et);
    boolean startBatch(List<Task> etList);
    Task taskFavorites(Task et);
    boolean taskFavoritesBatch(List<Task> etList);
    Task taskForward(Task et);
    boolean taskForwardBatch(List<Task> etList);
    Task taskNFavorites(Task et);
    boolean taskNFavoritesBatch(List<Task> etList);
    Task updateStoryVersion(Task et);
    boolean updateStoryVersionBatch(List<Task> etList);
    List<Task> select(TaskSearchContext context);
    Page<Task> searchAssignedToMyTask(TaskSearchContext context);
    Page<Task> searchAssignedToMyTaskPc(TaskSearchContext context);
    Page<Task> searchBugTask(TaskSearchContext context);
    Page<Task> searchByModule(TaskSearchContext context);
    Page<Task> searchChildDefault(TaskSearchContext context);
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
    Page<Task> searchMyFavorites(TaskSearchContext context);
    Page<Task> searchMyPlansTaskMobMonthly(TaskSearchContext context);
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


