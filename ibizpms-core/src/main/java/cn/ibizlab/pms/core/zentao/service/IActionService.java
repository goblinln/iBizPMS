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

import cn.ibizlab.pms.core.zentao.domain.Action;
import cn.ibizlab.pms.core.zentao.filter.ActionSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[Action] 服务对象接口
 */
public interface IActionService extends IService<Action> {

    boolean create(Action et);
    void createBatch(List<Action> list);
    boolean update(Action et);
    boolean sysUpdate(Action et);
    void updateBatch(List<Action> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    Action get(Long key);
    Action sysGet(Long key);
    Action getDraft(Action et);
    boolean checkKey(Action et);
    Action comment(Action et);
    Action createHis(Action et);
    Action editComment(Action et);
    Action managePmsEe(Action et);
    boolean save(Action et);
    void saveBatch(List<Action> list);
    Action sendMarkDone(Action et);
    Action sendTodo(Action et);
    Action sendToread(Action et);
    List<Action> select(ActionSearchContext context);
    List<Action> selectBianGengLineHistory(ActionSearchContext context);
    List<Action> selectDefault(ActionSearchContext context);
    List<Action> selectMobType(ActionSearchContext context);
    List<Action> selectMyAction(ActionSearchContext context);
    List<Action> selectMyTrends(ActionSearchContext context);
    List<Action> selectProductTrends(ActionSearchContext context);
    List<Action> selectProjectTrends(ActionSearchContext context);
    List<Action> selectQueryUserYEAR(ActionSearchContext context);
    List<Action> selectType(ActionSearchContext context);
    List<Action> selectView(ActionSearchContext context);

    Page<Action> searchDefault(ActionSearchContext context);
    Page<Action> searchMobType(ActionSearchContext context);
    Page<Action> searchMyAction(ActionSearchContext context);
    Page<Action> searchMyTrends(ActionSearchContext context);
    Page<Action> searchProductTrends(ActionSearchContext context);
    Page<Action> searchProjectTrends(ActionSearchContext context);
    Page<Action> searchQueryUserYEAR(ActionSearchContext context);
    Page<Action> searchType(ActionSearchContext context);
    List<Action> selectByProject(Long id);
    void removeByProject(Long id);
    Action dynamicCall(Long key, String action, Action et);
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

    List<Action> getActionByIds(List<Long> ids);
    List<Action> getActionByEntities(List<Action> entities);
}


