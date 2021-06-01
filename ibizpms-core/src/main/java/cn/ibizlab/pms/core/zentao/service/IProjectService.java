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

import cn.ibizlab.pms.core.zentao.domain.Project;
import cn.ibizlab.pms.core.zentao.filter.ProjectSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[Project] 服务对象接口
 */
public interface IProjectService extends IService<Project> {

    boolean create(Project et);
    void createBatch(List<Project> list);
    boolean update(Project et);
    boolean sysUpdate(Project et);
    void updateBatch(List<Project> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    Project get(Long key);
    Project sysGet(Long key);
    Project getDraft(Project et);
    Project activate(Project et);
    Project batchUnlinkStory(Project et);
    Project cancelProjectTop(Project et);
    boolean checkKey(Project et);
    Project close(Project et);
    Project importPlanStories(Project et);
    Project linkProduct(Project et);
    Project linkStory(Project et);
    Project manageMembers(Project et);
    Project mobProjectCount(Project et);
    Project pmsEeProjectAllTaskCount(Project et);
    Project pmsEeProjectTodoTaskCount(Project et);
    Project projectTaskQCnt(Project et);
    Project projectTop(Project et);
    Project putoff(Project et);
    boolean save(Project et);
    void saveBatch(List<Project> list);
    Project start(Project et);
    Project suspend(Project et);
    Project unlinkMember(Project et);
    Project unlinkProduct(Project et);
    Project unlinkStory(Project et);
    Project updateOrder(Project et);
    List<Project> select(ProjectSearchContext context);
    List<Project> selectAccount(ProjectSearchContext context);
    List<Project> selectBugSelectableProjectList(ProjectSearchContext context);
    List<Project> selectCurDefaultQuery(ProjectSearchContext context);
    List<Project> selectCurDefaultQueryExp(ProjectSearchContext context);
    List<Project> selectCurPlanProject(ProjectSearchContext context);
    List<Project> selectCurProduct(ProjectSearchContext context);
    List<Project> selectCurUser(ProjectSearchContext context);
    List<Project> selectCurUserSa(ProjectSearchContext context);
    List<Project> selectDefault(ProjectSearchContext context);
    List<Project> selectDeveloperQuery(ProjectSearchContext context);
    List<Project> selectESBulk(ProjectSearchContext context);
    List<Project> selectInvolvedProject(ProjectSearchContext context);
    List<Project> selectInvolvedProjectStoryTaskBug(ProjectSearchContext context);
    List<Project> selectMy(ProjectSearchContext context);
    List<Project> selectMyProject(ProjectSearchContext context);
    List<Project> selectOpenByQuery(ProjectSearchContext context);
    List<Project> selectOpenQuery(ProjectSearchContext context);
    List<Project> selectProjectTeam(ProjectSearchContext context);
    List<Project> selectStoryProject(ProjectSearchContext context);
    List<Project> selectUnDoneProject(ProjectSearchContext context);
    List<Project> selectView(ProjectSearchContext context);

    Page<Project> searchAccount(ProjectSearchContext context);
    Page<Project> searchBugProject(ProjectSearchContext context);
    Page<Project> searchCurDefaultQuery(ProjectSearchContext context);
    Page<Project> searchCurDefaultQueryExp(ProjectSearchContext context);
    Page<Project> searchCurPlanProject(ProjectSearchContext context);
    Page<Project> searchCurProduct(ProjectSearchContext context);
    Page<Project> searchCurUser(ProjectSearchContext context);
    Page<Project> searchCurUserSa(ProjectSearchContext context);
    Page<Project> searchDefault(ProjectSearchContext context);
    Page<Project> searchDeveloperQuery(ProjectSearchContext context);
    Page<Project> searchESBulk(ProjectSearchContext context);
    Page<Project> searchInvolvedProject(ProjectSearchContext context);
    Page<Project> searchInvolvedProject_StoryTaskBug(ProjectSearchContext context);
    Page<Project> searchMy(ProjectSearchContext context);
    Page<Project> searchMyProject(ProjectSearchContext context);
    Page<Project> searchOpenByQuery(ProjectSearchContext context);
    Page<Project> searchOpenQuery(ProjectSearchContext context);
    Page<Project> searchProjectTeam(ProjectSearchContext context);
    Page<Project> searchStoryProject(ProjectSearchContext context);
    Page<Project> searchUnDoneProject(ProjectSearchContext context);
    List<Project> selectByParent(Long id);
    void removeByParent(Long id);
    Project dynamicCall(Long key, String action, Project et);
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

    List<Project> getProjectByIds(List<Long> ids);
    List<Project> getProjectByEntities(List<Project> entities);
}


