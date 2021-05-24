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

import cn.ibizlab.pms.core.ibiz.domain.IBZProProductAction;
import cn.ibizlab.pms.core.ibiz.filter.IBZProProductActionSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IBZProProductAction] 服务对象接口
 */
public interface IIBZProProductActionService extends IService<IBZProProductAction> {

    boolean create(IBZProProductAction et);
    void createBatch(List<IBZProProductAction> list);
    boolean update(IBZProProductAction et);
    boolean sysUpdate(IBZProProductAction et);
    void updateBatch(List<IBZProProductAction> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IBZProProductAction get(Long key);
    IBZProProductAction sysGet(Long key);
    IBZProProductAction getDraft(IBZProProductAction et);
    boolean checkKey(IBZProProductAction et);
    IBZProProductAction comment(IBZProProductAction et);
    boolean commentBatch(List<IBZProProductAction> etList);
    IBZProProductAction createHis(IBZProProductAction et);
    boolean createHisBatch(List<IBZProProductAction> etList);
    IBZProProductAction editComment(IBZProProductAction et);
    boolean editCommentBatch(List<IBZProProductAction> etList);
    IBZProProductAction managePmsEe(IBZProProductAction et);
    boolean managePmsEeBatch(List<IBZProProductAction> etList);
    boolean save(IBZProProductAction et);
    void saveBatch(List<IBZProProductAction> list);
    IBZProProductAction sendMarkDone(IBZProProductAction et);
    boolean sendMarkDoneBatch(List<IBZProProductAction> etList);
    IBZProProductAction sendTodo(IBZProProductAction et);
    boolean sendTodoBatch(List<IBZProProductAction> etList);
    IBZProProductAction sendToread(IBZProProductAction et);
    boolean sendToreadBatch(List<IBZProProductAction> etList);
    List<IBZProProductAction> select(IBZProProductActionSearchContext context);
    List<IBZProProductAction> selectDefault(IBZProProductActionSearchContext context);
    List<IBZProProductAction> selectSimple(IBZProProductActionSearchContext context);
    List<IBZProProductAction> selectView(IBZProProductActionSearchContext context);

    Page<IBZProProductAction> searchDefault(IBZProProductActionSearchContext context);
    List<IBZProProductAction> selectByObjectid(Long id);
    void removeByObjectid(Long id);
    IBZProProductAction dynamicCall(Long key, String action, IBZProProductAction et);
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

    List<IBZProProductAction> getIbzproproductactionByIds(List<Long> ids);
    List<IBZProProductAction> getIbzproproductactionByEntities(List<IBZProProductAction> entities);
}


