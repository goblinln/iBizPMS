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

import cn.ibizlab.pms.core.ibiz.domain.DynaFilter;
import cn.ibizlab.pms.core.ibiz.filter.DynaFilterSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[DynaFilter] 服务对象接口
 */
public interface IDynaFilterService extends IService<DynaFilter> {

    boolean create(DynaFilter et);
    void createBatch(List<DynaFilter> list);
    boolean update(DynaFilter et);
    boolean sysUpdate(DynaFilter et);
    void updateBatch(List<DynaFilter> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    DynaFilter get(String key);
    DynaFilter sysGet(String key);
    DynaFilter getDraft(DynaFilter et);
    boolean checkKey(DynaFilter et);
    boolean save(DynaFilter et);
    void saveBatch(List<DynaFilter> list);
    List<DynaFilter> select(DynaFilterSearchContext context);
    List<DynaFilter> selectDefault(DynaFilterSearchContext context);
    List<DynaFilter> selectSimple(DynaFilterSearchContext context);
    List<DynaFilter> selectView(DynaFilterSearchContext context);

    Page<DynaFilter> searchDefault(DynaFilterSearchContext context);
    DynaFilter dynamicCall(String key, String action, DynaFilter et);
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

    List<DynaFilter> getDynafilterByIds(List<String> ids);
    List<DynaFilter> getDynafilterByEntities(List<DynaFilter> entities);
}


