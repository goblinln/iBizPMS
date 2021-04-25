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
    boolean activateBatch(List<Project> etList);
    Project batchUnlinkStory(Project et);
    boolean batchUnlinkStoryBatch(List<Project> etList);
    Project cancelProjectTop(Project et);
    boolean cancelProjectTopBatch(List<Project> etList);
    boolean checkKey(Project et);
    Project close(Project et);
    boolean closeBatch(List<Project> etList);
    Project linkStory(Project et);
    boolean linkStoryBatch(List<Project> etList);
    Project manageMembers(Project et);
    boolean manageMembersBatch(List<Project> etList);
    Project mobProjectCount(Project et);
    boolean mobProjectCountBatch(List<Project> etList);
    Project pmsEeProjectAllTaskCount(Project et);
    boolean pmsEeProjectAllTaskCountBatch(List<Project> etList);
    Project pmsEeProjectTodoTaskCount(Project et);
    boolean pmsEeProjectTodoTaskCountBatch(List<Project> etList);
    Project projectTaskQCnt(Project et);
    boolean projectTaskQCntBatch(List<Project> etList);
    Project projectTop(Project et);
    boolean projectTopBatch(List<Project> etList);
    Project putoff(Project et);
    boolean putoffBatch(List<Project> etList);
    boolean save(Project et);
    void saveBatch(List<Project> list);
    Project start(Project et);
    boolean startBatch(List<Project> etList);
    Project suspend(Project et);
    boolean suspendBatch(List<Project> etList);
    Project unlinkMember(Project et);
    boolean unlinkMemberBatch(List<Project> etList);
    Project unlinkStory(Project et);
    boolean unlinkStoryBatch(List<Project> etList);
    Project updateOrder(Project et);
    boolean updateOrderBatch(List<Project> etList);
    List<Project> select(ProjectSearchContext context);
    List<Project> selectQueryByBugSelectableProjectList(ProjectSearchContext context);
    List<Project> selectQueryByCurDefaultQuery(ProjectSearchContext context);
    List<Project> selectQueryByCurDefaultQueryExp(ProjectSearchContext context);
    List<Project> selectQueryByCurPlanProject(ProjectSearchContext context);
    List<Project> selectQueryByCurProduct(ProjectSearchContext context);
    List<Project> selectQueryByCurUser(ProjectSearchContext context);
    List<Project> selectQueryByCurUserSa(ProjectSearchContext context);
    List<Project> selectQueryByDefault(ProjectSearchContext context);
    List<Project> selectQueryByDeveloperQuery(ProjectSearchContext context);
    List<Project> selectQueryByESBulk(ProjectSearchContext context);
    List<Project> selectQueryByInvolvedProject(ProjectSearchContext context);
    List<Project> selectQueryByInvolvedProjectStoryTaskBug(ProjectSearchContext context);
    List<Project> selectQueryByMyProject(ProjectSearchContext context);
    List<Project> selectQueryByOpenByQuery(ProjectSearchContext context);
    List<Project> selectQueryByOpenQuery(ProjectSearchContext context);
    List<Project> selectQueryByPMQuery(ProjectSearchContext context);
    List<Project> selectQueryByPOQuery(ProjectSearchContext context);
    List<Project> selectQueryByProjectTeam(ProjectSearchContext context);
    List<Project> selectQueryByQDQuery(ProjectSearchContext context);
    List<Project> selectQueryByRDQuery(ProjectSearchContext context);
    List<Project> selectQueryByStoryProject(ProjectSearchContext context);
    List<Project> selectQueryByUnDoneProject(ProjectSearchContext context);
    List<Project> selectQueryByView(ProjectSearchContext context);

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
    Page<Project> searchMyProject(ProjectSearchContext context);
    Page<Project> searchOpenByQuery(ProjectSearchContext context);
    Page<Project> searchOpenQuery(ProjectSearchContext context);
    Page<Project> searchPMQuery(ProjectSearchContext context);
    Page<Project> searchPOQuery(ProjectSearchContext context);
    Page<Project> searchProjectTeam(ProjectSearchContext context);
    Page<Project> searchQDQuery(ProjectSearchContext context);
    Page<Project> searchRDQuery(ProjectSearchContext context);
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


