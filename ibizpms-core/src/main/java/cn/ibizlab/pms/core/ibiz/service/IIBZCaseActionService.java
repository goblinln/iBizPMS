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

import cn.ibizlab.pms.core.ibiz.domain.IBZCaseAction;
import cn.ibizlab.pms.core.ibiz.filter.IBZCaseActionSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IBZCaseAction] 服务对象接口
 */
public interface IIBZCaseActionService extends IService<IBZCaseAction> {

    boolean create(IBZCaseAction et);
    void createBatch(List<IBZCaseAction> list);
    boolean update(IBZCaseAction et);
    boolean sysUpdate(IBZCaseAction et);
    void updateBatch(List<IBZCaseAction> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IBZCaseAction get(Long key);
    IBZCaseAction sysGet(Long key);
    IBZCaseAction getDraft(IBZCaseAction et);
    boolean checkKey(IBZCaseAction et);
    boolean save(IBZCaseAction et);
    void saveBatch(List<IBZCaseAction> list);
    List<IBZCaseAction> select(IBZCaseActionSearchContext context);
    List<IBZCaseAction> selectDefault(IBZCaseActionSearchContext context);
    List<IBZCaseAction> selectSimple(IBZCaseActionSearchContext context);
    List<IBZCaseAction> selectType(IBZCaseActionSearchContext context);
    List<IBZCaseAction> selectView(IBZCaseActionSearchContext context);

    Page<IBZCaseAction> searchDefault(IBZCaseActionSearchContext context);
    Page<IBZCaseAction> searchType(IBZCaseActionSearchContext context);
    List<IBZCaseAction> selectByObjectid(Long id);
    void removeByObjectid(Long id);
    IBZCaseAction dynamicCall(Long key, String action, IBZCaseAction et);
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

    List<IBZCaseAction> getIbzcaseactionByIds(List<Long> ids);
    List<IBZCaseAction> getIbzcaseactionByEntities(List<IBZCaseAction> entities);
}


