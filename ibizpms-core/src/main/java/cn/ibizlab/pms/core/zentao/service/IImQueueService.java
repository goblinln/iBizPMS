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

import cn.ibizlab.pms.core.zentao.domain.ImQueue;
import cn.ibizlab.pms.core.zentao.filter.ImQueueSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[ImQueue] 服务对象接口
 */
public interface IImQueueService extends IService<ImQueue> {

    boolean create(ImQueue et);
    boolean update(ImQueue et);
    boolean sysUpdate(ImQueue et);
    boolean remove(Long key);
    ImQueue get(Long key);
    ImQueue sysGet(Long key);
    ImQueue getDraft(ImQueue et);
    boolean checkKey(ImQueue et);
    boolean save(ImQueue et);
    List<ImQueue> select(ImQueueSearchContext context);
    List<ImQueue> selectDefault(ImQueueSearchContext context);
    List<ImQueue> selectView(ImQueueSearchContext context);

    Page<ImQueue> searchDefault(ImQueueSearchContext context);
    ImQueue dynamicCall(Long key, String action, ImQueue et);
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


