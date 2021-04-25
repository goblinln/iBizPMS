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

import cn.ibizlab.pms.core.zentao.domain.Notify;
import cn.ibizlab.pms.core.zentao.filter.NotifySearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[Notify] 服务对象接口
 */
public interface INotifyService extends IService<Notify> {

    boolean create(Notify et);
    void createBatch(List<Notify> list);
    boolean update(Notify et);
    boolean sysUpdate(Notify et);
    void updateBatch(List<Notify> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    Notify get(Long key);
    Notify sysGet(Long key);
    Notify getDraft(Notify et);
    boolean checkKey(Notify et);
    boolean save(Notify et);
    void saveBatch(List<Notify> list);
    List<Notify> select(NotifySearchContext context);
    
    List<Notify> selectQueryByDefault(NotifySearchContext context);
    List<Notify> selectQueryByView(NotifySearchContext context);

    Page<Notify> searchDefault(NotifySearchContext context);
    Notify dynamicCall(Long key, String action, Notify et);
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


