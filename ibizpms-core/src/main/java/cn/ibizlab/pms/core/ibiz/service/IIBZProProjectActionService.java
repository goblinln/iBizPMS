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

import cn.ibizlab.pms.core.ibiz.domain.IBZProProjectAction;
import cn.ibizlab.pms.core.ibiz.filter.IBZProProjectActionSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IBZProProjectAction] 服务对象接口
 */
public interface IIBZProProjectActionService extends IService<IBZProProjectAction> {

    boolean create(IBZProProjectAction et);
    void createBatch(List<IBZProProjectAction> list);
    boolean update(IBZProProjectAction et);
    boolean sysUpdate(IBZProProjectAction et);
    void updateBatch(List<IBZProProjectAction> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IBZProProjectAction get(Long key);
    IBZProProjectAction sysGet(Long key);
    IBZProProjectAction getDraft(IBZProProjectAction et);
    boolean checkKey(IBZProProjectAction et);
    boolean save(IBZProProjectAction et);
    void saveBatch(List<IBZProProjectAction> list);
    List<IBZProProjectAction> select(IBZProProjectActionSearchContext context);
    List<IBZProProjectAction> selectDefault(IBZProProjectActionSearchContext context);
    List<IBZProProjectAction> selectSimple(IBZProProjectActionSearchContext context);
    List<IBZProProjectAction> selectType(IBZProProjectActionSearchContext context);
    List<IBZProProjectAction> selectView(IBZProProjectActionSearchContext context);

    Page<IBZProProjectAction> searchDefault(IBZProProjectActionSearchContext context);
    Page<IBZProProjectAction> searchType(IBZProProjectActionSearchContext context);
    List<IBZProProjectAction> selectByObjectid(Long id);
    void removeByObjectid(Long id);
    IBZProProjectAction dynamicCall(Long key, String action, IBZProProjectAction et);
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

    List<IBZProProjectAction> getIbzproprojectactionByIds(List<Long> ids);
    List<IBZProProjectAction> getIbzproprojectactionByEntities(List<IBZProProjectAction> entities);
}


