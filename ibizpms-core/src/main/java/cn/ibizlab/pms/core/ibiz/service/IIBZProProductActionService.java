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
    IBZProProductAction createHis(IBZProProductAction et);
    IBZProProductAction editComment(IBZProProductAction et);
    IBZProProductAction managePmsEe(IBZProProductAction et);
    boolean save(IBZProProductAction et);
    void saveBatch(List<IBZProProductAction> list);
    IBZProProductAction sendMarkDone(IBZProProductAction et);
    IBZProProductAction sendTodo(IBZProProductAction et);
    IBZProProductAction sendToread(IBZProProductAction et);
    List<IBZProProductAction> select(IBZProProductActionSearchContext context);
    List<IBZProProductAction> selectDefault(IBZProProductActionSearchContext context);
    List<IBZProProductAction> selectMobType(IBZProProductActionSearchContext context);
    List<IBZProProductAction> selectProductTrends(IBZProProductActionSearchContext context);
    List<IBZProProductAction> selectSimple(IBZProProductActionSearchContext context);
    List<IBZProProductAction> selectType(IBZProProductActionSearchContext context);
    List<IBZProProductAction> selectView(IBZProProductActionSearchContext context);

    Page<IBZProProductAction> searchDefault(IBZProProductActionSearchContext context);
    Page<IBZProProductAction> searchMobType(IBZProProductActionSearchContext context);
    Page<IBZProProductAction> searchProductTrends(IBZProProductActionSearchContext context);
    Page<IBZProProductAction> searchType(IBZProProductActionSearchContext context);
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


