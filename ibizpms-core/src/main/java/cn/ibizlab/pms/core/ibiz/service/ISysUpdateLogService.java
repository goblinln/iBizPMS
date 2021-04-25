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

import cn.ibizlab.pms.core.ibiz.domain.SysUpdateLog;
import cn.ibizlab.pms.core.ibiz.filter.SysUpdateLogSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[SysUpdateLog] 服务对象接口
 */
public interface ISysUpdateLogService extends IService<SysUpdateLog> {

    boolean create(SysUpdateLog et);
    void createBatch(List<SysUpdateLog> list);
    boolean update(SysUpdateLog et);
    boolean sysUpdate(SysUpdateLog et);
    void updateBatch(List<SysUpdateLog> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    SysUpdateLog get(String key);
    SysUpdateLog sysGet(String key);
    SysUpdateLog getDraft(SysUpdateLog et);
    boolean checkKey(SysUpdateLog et);
    SysUpdateLog getLastUpdateInfo(SysUpdateLog et);
    boolean getLastUpdateInfoBatch(List<SysUpdateLog> etList);
    boolean save(SysUpdateLog et);
    void saveBatch(List<SysUpdateLog> list);
    List<SysUpdateLog> select(SysUpdateLogSearchContext context);
    Page<SysUpdateLog> searchDefault(SysUpdateLogSearchContext context);
    SysUpdateLog dynamicCall(String key, String action, SysUpdateLog et);
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

    List<SysUpdateLog> getSysupdatelogByIds(List<String> ids);
    List<SysUpdateLog> getSysupdatelogByEntities(List<SysUpdateLog> entities);
}


