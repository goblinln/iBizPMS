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

import cn.ibizlab.pms.core.zentao.domain.Todo;
import cn.ibizlab.pms.core.zentao.filter.TodoSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[Todo] 服务对象接口
 */
public interface ITodoService extends IService<Todo>{

    boolean create(Todo et) ;
    void createBatch(List<Todo> list) ;
    boolean update(Todo et) ;
    void updateBatch(List<Todo> list) ;
    boolean remove(Long key) ;
    void removeBatch(Collection<Long> idList) ;
    Todo get(Long key) ;
    Todo getDraft(Todo et) ;
    Todo activate(Todo et) ;
    Todo assignTo(Todo et) ;
    boolean checkKey(Todo et) ;
    Todo close(Todo et) ;
    Todo createCycle(Todo et) ;
    Todo finish(Todo et) ;
    boolean save(Todo et) ;
    void saveBatch(List<Todo> list) ;
    Todo sendMessage(Todo et) ;
    Todo sendMsgPreProcess(Todo et) ;
    Page<Todo> searchDefault(TodoSearchContext context) ;
    Page<Todo> searchMyTodo(TodoSearchContext context) ;
    Page<Todo> searchMyTodoPc(TodoSearchContext context) ;
    Page<Todo> searchMyUpcoming(TodoSearchContext context) ;
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


