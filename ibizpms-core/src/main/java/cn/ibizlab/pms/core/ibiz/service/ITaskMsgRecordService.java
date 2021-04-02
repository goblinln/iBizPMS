package cn.ibizlab.pms.core.ibiz.service;

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

import cn.ibizlab.pms.core.ibiz.domain.TaskMsgRecord;
import cn.ibizlab.pms.core.ibiz.filter.TaskMsgRecordSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[TaskMsgRecord] 服务对象接口
 */
public interface ITaskMsgRecordService extends IService<TaskMsgRecord> {

    /**
     * 业务实体显示文本名称
     */
    final static String OBJECT_TEXT_NAME = "待办消息记录";

    /**
     * 业务实体资源路径名
     */
    final static String OBJECT_SOURCE_PATH = "taskmsgrecords";

    boolean create(TaskMsgRecord et);
    void createBatch(List<TaskMsgRecord> list);
    boolean update(TaskMsgRecord et);
    void updateBatch(List<TaskMsgRecord> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    TaskMsgRecord get(String key);
    TaskMsgRecord getDraft(TaskMsgRecord et);
    boolean checkKey(TaskMsgRecord et);
    boolean save(TaskMsgRecord et);
    void saveBatch(List<TaskMsgRecord> list);
    Page<TaskMsgRecord> searchDefault(TaskMsgRecordSearchContext context);
    TaskMsgRecord dynamicCall(String key, String action, TaskMsgRecord et);
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

    List<TaskMsgRecord> getTaskmsgrecordByIds(List<String> ids);
    List<TaskMsgRecord> getTaskmsgrecordByEntities(List<TaskMsgRecord> entities);
}


