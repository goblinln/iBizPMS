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

import cn.ibizlab.pms.core.zentao.domain.Log;
import cn.ibizlab.pms.core.zentao.filter.LogSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[Log] 服务对象接口
 */
public interface ILogService extends IService<Log> {

    boolean create(Log et);
    void createBatch(List<Log> list);
    boolean update(Log et);
    boolean sysUpdate(Log et);
    void updateBatch(List<Log> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    Log get(Long key);
    Log sysGet(Long key);
    Log getDraft(Log et);
    boolean checkKey(Log et);
    boolean save(Log et);
    void saveBatch(List<Log> list);
    List<Log> select(LogSearchContext context);
    
    List<Log> selectQueryByDefault(LogSearchContext context);
    List<Log> selectQueryByView(LogSearchContext context);

    Page<Log> searchDefault(LogSearchContext context);
    Log dynamicCall(Long key, String action, Log et);
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


