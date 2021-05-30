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

import cn.ibizlab.pms.core.ibiz.domain.IbzProTestTaskAction;
import cn.ibizlab.pms.core.ibiz.filter.IbzProTestTaskActionSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IbzProTestTaskAction] 服务对象接口
 */
public interface IIbzProTestTaskActionService extends IService<IbzProTestTaskAction> {

    boolean create(IbzProTestTaskAction et);
    void createBatch(List<IbzProTestTaskAction> list);
    boolean update(IbzProTestTaskAction et);
    boolean sysUpdate(IbzProTestTaskAction et);
    void updateBatch(List<IbzProTestTaskAction> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IbzProTestTaskAction get(Long key);
    IbzProTestTaskAction sysGet(Long key);
    IbzProTestTaskAction getDraft(IbzProTestTaskAction et);
    boolean checkKey(IbzProTestTaskAction et);
    boolean save(IbzProTestTaskAction et);
    void saveBatch(List<IbzProTestTaskAction> list);
    List<IbzProTestTaskAction> select(IbzProTestTaskActionSearchContext context);
    List<IbzProTestTaskAction> selectDefault(IbzProTestTaskActionSearchContext context);
    List<IbzProTestTaskAction> selectSimple(IbzProTestTaskActionSearchContext context);
    List<IbzProTestTaskAction> selectType(IbzProTestTaskActionSearchContext context);
    List<IbzProTestTaskAction> selectView(IbzProTestTaskActionSearchContext context);

    Page<IbzProTestTaskAction> searchDefault(IbzProTestTaskActionSearchContext context);
    Page<IbzProTestTaskAction> searchType(IbzProTestTaskActionSearchContext context);
    IbzProTestTaskAction dynamicCall(Long key, String action, IbzProTestTaskAction et);
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

    List<IbzProTestTaskAction> getIbzprotesttaskactionByIds(List<Long> ids);
    List<IbzProTestTaskAction> getIbzprotesttaskactionByEntities(List<IbzProTestTaskAction> entities);
}


