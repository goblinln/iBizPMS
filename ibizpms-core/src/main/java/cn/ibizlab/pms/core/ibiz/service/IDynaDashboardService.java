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

import cn.ibizlab.pms.core.ibiz.domain.DynaDashboard;
import cn.ibizlab.pms.core.ibiz.filter.DynaDashboardSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[DynaDashboard] 服务对象接口
 */
public interface IDynaDashboardService extends IService<DynaDashboard> {

    boolean create(DynaDashboard et);
    boolean update(DynaDashboard et);
    boolean sysUpdate(DynaDashboard et);
    boolean remove(String key);
    DynaDashboard get(String key);
    DynaDashboard sysGet(String key);
    DynaDashboard getDraft(DynaDashboard et);
    boolean checkKey(DynaDashboard et);
    boolean save(DynaDashboard et);
    List<DynaDashboard> select(DynaDashboardSearchContext context);
    List<DynaDashboard> selectDefault(DynaDashboardSearchContext context);
    List<DynaDashboard> selectView(DynaDashboardSearchContext context);

    Page<DynaDashboard> searchDefault(DynaDashboardSearchContext context);
    DynaDashboard dynamicCall(String key, String action, DynaDashboard et);
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

    List<DynaDashboard> getDynadashboardByIds(List<String> ids);
    List<DynaDashboard> getDynadashboardByEntities(List<DynaDashboard> entities);
}


