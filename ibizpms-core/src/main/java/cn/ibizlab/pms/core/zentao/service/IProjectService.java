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

    /**
     * 业务实体显示文本名称
     */
    final static String OBJECT_TEXT_NAME = "项目";

    /**
     * 业务实体资源路径名
     */
    final static String OBJECT_SOURCE_PATH = "projects";

    boolean create(Project et);
    void createBatch(List<Project> list);
    boolean update(Project et);
    void updateBatch(List<Project> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    Project get(Long key);
    Project getDraft(Project et);
    Project activate(Project et);
    Project batchUnlinkStory(Project et);
    Project cancelProjectTop(Project et);
    boolean checkKey(Project et);
    Project close(Project et);
    Project linkStory(Project et);
    Project manageMembers(Project et);
    Project mobProjectCount(Project et);
    Project projectTaskQCnt(Project et);
    Project projectTop(Project et);
    Project putoff(Project et);
    boolean save(Project et);
    void saveBatch(List<Project> list);
    Project start(Project et);
    Project suspend(Project et);
    Project unlinkMember(Project et);
    Project unlinkStory(Project et);
    Project updateOrder(Project et);
    Page<Project> searchBugProject(ProjectSearchContext context);
    Page<Project> searchCurProduct(ProjectSearchContext context);
    Page<Project> searchCurUser(ProjectSearchContext context);
    Page<Project> searchDefault(ProjectSearchContext context);
    Page<Project> searchInvolvedProject(ProjectSearchContext context);
    Page<Project> searchInvolvedProject_StoryTaskBug(ProjectSearchContext context);
    Page<Project> searchMyProject(ProjectSearchContext context);
    Page<Project> searchProjectTeam(ProjectSearchContext context);
    Page<Project> searchStoryProject(ProjectSearchContext context);
    List<Project> selectByParent(Long id);
    void removeByParent(Long id);
    /**
     * 自定义查询SQL
     * @param sql  select * from table where id =#{et.param}
     * @param param 参数列表  param.put("param", "1");
     * @return select * from table where id = '1'
     */
    List<JSONObject> select(String sql, Map param);
    /**
     * 自定义SQL
     * @param sql  update table  set name ='test' where id =#{et.param}
     * @param param 参数列表  param.put("param", "1");
     * @return     update table  set name ='test' where id = '1'
     */
    boolean execute(String sql, Map param);

    List<Project> getProjectByIds(List<Long> ids);
    List<Project> getProjectByEntities(List<Project> entities);
}


