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

import cn.ibizlab.pms.core.ibiz.domain.IBZDailyAction;
import cn.ibizlab.pms.core.ibiz.filter.IBZDailyActionSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IBZDailyAction] 服务对象接口
 */
public interface IIBZDailyActionService extends IService<IBZDailyAction> {

    boolean create(IBZDailyAction et);
    void createBatch(List<IBZDailyAction> list);
    boolean update(IBZDailyAction et);
    boolean sysUpdate(IBZDailyAction et);
    void updateBatch(List<IBZDailyAction> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IBZDailyAction get(Long key);
    IBZDailyAction sysGet(Long key);
    IBZDailyAction getDraft(IBZDailyAction et);
    boolean checkKey(IBZDailyAction et);
    IBZDailyAction comment(IBZDailyAction et);
    boolean commentBatch(List<IBZDailyAction> etList);
    IBZDailyAction createHis(IBZDailyAction et);
    boolean createHisBatch(List<IBZDailyAction> etList);
    IBZDailyAction editComment(IBZDailyAction et);
    boolean editCommentBatch(List<IBZDailyAction> etList);
    IBZDailyAction managePmsEe(IBZDailyAction et);
    boolean managePmsEeBatch(List<IBZDailyAction> etList);
    boolean save(IBZDailyAction et);
    void saveBatch(List<IBZDailyAction> list);
    IBZDailyAction sendMarkDone(IBZDailyAction et);
    boolean sendMarkDoneBatch(List<IBZDailyAction> etList);
    IBZDailyAction sendTodo(IBZDailyAction et);
    boolean sendTodoBatch(List<IBZDailyAction> etList);
    IBZDailyAction sendToread(IBZDailyAction et);
    boolean sendToreadBatch(List<IBZDailyAction> etList);
    List<IBZDailyAction> select(IBZDailyActionSearchContext context);
    List<IBZDailyAction> selectDefault(IBZDailyActionSearchContext context);
    List<IBZDailyAction> selectSimple(IBZDailyActionSearchContext context);
    List<IBZDailyAction> selectType(IBZDailyActionSearchContext context);
    List<IBZDailyAction> selectView(IBZDailyActionSearchContext context);

    Page<IBZDailyAction> searchDefault(IBZDailyActionSearchContext context);
    Page<IBZDailyAction> searchType(IBZDailyActionSearchContext context);
    List<IBZDailyAction> selectByObjectid(Long ibzdailyid);
    void removeByObjectid(Long ibzdailyid);
    IBZDailyAction dynamicCall(Long key, String action, IBZDailyAction et);
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

    List<IBZDailyAction> getIbzdailyactionByIds(List<Long> ids);
    List<IBZDailyAction> getIbzdailyactionByEntities(List<IBZDailyAction> entities);
}


