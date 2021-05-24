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

import cn.ibizlab.pms.core.ibiz.domain.IBZProReleaseAction;
import cn.ibizlab.pms.core.ibiz.filter.IBZProReleaseActionSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IBZProReleaseAction] 服务对象接口
 */
public interface IIBZProReleaseActionService extends IService<IBZProReleaseAction> {

    boolean create(IBZProReleaseAction et);
    void createBatch(List<IBZProReleaseAction> list);
    boolean update(IBZProReleaseAction et);
    boolean sysUpdate(IBZProReleaseAction et);
    void updateBatch(List<IBZProReleaseAction> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IBZProReleaseAction get(Long key);
    IBZProReleaseAction sysGet(Long key);
    IBZProReleaseAction getDraft(IBZProReleaseAction et);
    boolean checkKey(IBZProReleaseAction et);
    boolean save(IBZProReleaseAction et);
    void saveBatch(List<IBZProReleaseAction> list);
    List<IBZProReleaseAction> select(IBZProReleaseActionSearchContext context);
    List<IBZProReleaseAction> selectDefault(IBZProReleaseActionSearchContext context);
    List<IBZProReleaseAction> selectSimple(IBZProReleaseActionSearchContext context);
    List<IBZProReleaseAction> selectView(IBZProReleaseActionSearchContext context);

    Page<IBZProReleaseAction> searchDefault(IBZProReleaseActionSearchContext context);
    IBZProReleaseAction dynamicCall(Long key, String action, IBZProReleaseAction et);
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

    List<IBZProReleaseAction> getIbzproreleaseactionByIds(List<Long> ids);
    List<IBZProReleaseAction> getIbzproreleaseactionByEntities(List<IBZProReleaseAction> entities);
}


