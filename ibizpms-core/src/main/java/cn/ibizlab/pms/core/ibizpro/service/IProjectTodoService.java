package cn.ibizlab.pms.core.ibizpro.service;

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

import cn.ibizlab.pms.core.ibizpro.domain.ProjectTodo;
import cn.ibizlab.pms.core.ibizpro.filter.ProjectTodoSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[ProjectTodo] 服务对象接口
 */
public interface IProjectTodoService extends IService<ProjectTodo> {

    /**
     * 业务实体显示文本名称
     */
    final static String OBJECT_TEXT_NAME = "项目其他活动";

    /**
     * 业务实体资源路径名
     */
    final static String OBJECT_SOURCE_PATH = "projecttodos";

    boolean create(ProjectTodo et);
    void createBatch(List<ProjectTodo> list);
    boolean update(ProjectTodo et);
    void updateBatch(List<ProjectTodo> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    ProjectTodo get(Long key);
    ProjectTodo getDraft(ProjectTodo et);
    ProjectTodo activate(ProjectTodo et);
    boolean activateBatch(List<ProjectTodo> etList);
    ProjectTodo assignTo(ProjectTodo et);
    boolean assignToBatch(List<ProjectTodo> etList);
    boolean checkKey(ProjectTodo et);
    ProjectTodo close(ProjectTodo et);
    boolean closeBatch(List<ProjectTodo> etList);
    ProjectTodo createCycle(ProjectTodo et);
    boolean createCycleBatch(List<ProjectTodo> etList);
    ProjectTodo finish(ProjectTodo et);
    boolean finishBatch(List<ProjectTodo> etList);
    boolean save(ProjectTodo et);
    void saveBatch(List<ProjectTodo> list);
    ProjectTodo sendMessage(ProjectTodo et);
    boolean sendMessageBatch(List<ProjectTodo> etList);
    ProjectTodo sendMsgPreProcess(ProjectTodo et);
    boolean sendMsgPreProcessBatch(List<ProjectTodo> etList);
    Page<ProjectTodo> searchDefault(ProjectTodoSearchContext context);
    List<ProjectTodo> selectByIdvalue(Long id);
    void removeByIdvalue(Long id);
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

}


