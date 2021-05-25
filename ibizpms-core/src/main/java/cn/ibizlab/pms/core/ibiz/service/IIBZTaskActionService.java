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

import cn.ibizlab.pms.core.ibiz.domain.IBZTaskAction;
import cn.ibizlab.pms.core.ibiz.filter.IBZTaskActionSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IBZTaskAction] 服务对象接口
 */
public interface IIBZTaskActionService extends IService<IBZTaskAction> {

    boolean create(IBZTaskAction et);
    void createBatch(List<IBZTaskAction> list);
    boolean update(IBZTaskAction et);
    boolean sysUpdate(IBZTaskAction et);
    void updateBatch(List<IBZTaskAction> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IBZTaskAction get(Long key);
    IBZTaskAction sysGet(Long key);
    IBZTaskAction getDraft(IBZTaskAction et);
    boolean checkKey(IBZTaskAction et);
    IBZTaskAction comment(IBZTaskAction et);
    boolean commentBatch(List<IBZTaskAction> etList);
    IBZTaskAction createHis(IBZTaskAction et);
    boolean createHisBatch(List<IBZTaskAction> etList);
    IBZTaskAction editComment(IBZTaskAction et);
    boolean editCommentBatch(List<IBZTaskAction> etList);
    IBZTaskAction managePmsEe(IBZTaskAction et);
    boolean managePmsEeBatch(List<IBZTaskAction> etList);
    boolean save(IBZTaskAction et);
    void saveBatch(List<IBZTaskAction> list);
    IBZTaskAction sendMarkDone(IBZTaskAction et);
    boolean sendMarkDoneBatch(List<IBZTaskAction> etList);
    IBZTaskAction sendTodo(IBZTaskAction et);
    boolean sendTodoBatch(List<IBZTaskAction> etList);
    IBZTaskAction sendToread(IBZTaskAction et);
    boolean sendToreadBatch(List<IBZTaskAction> etList);
    List<IBZTaskAction> select(IBZTaskActionSearchContext context);
    List<IBZTaskAction> selectDefault(IBZTaskActionSearchContext context);
    List<IBZTaskAction> selectSimple(IBZTaskActionSearchContext context);
    List<IBZTaskAction> selectType(IBZTaskActionSearchContext context);
    List<IBZTaskAction> selectView(IBZTaskActionSearchContext context);

    Page<IBZTaskAction> searchDefault(IBZTaskActionSearchContext context);
    Page<IBZTaskAction> searchType(IBZTaskActionSearchContext context);
    List<IBZTaskAction> selectByObjectid(Long id);
    void removeByObjectid(Long id);
    IBZTaskAction dynamicCall(Long key, String action, IBZTaskAction et);
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

    List<IBZTaskAction> getIbztaskactionByIds(List<Long> ids);
    List<IBZTaskAction> getIbztaskactionByEntities(List<IBZTaskAction> entities);
}


