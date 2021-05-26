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

import cn.ibizlab.pms.core.ibiz.domain.IbzTop;
import cn.ibizlab.pms.core.ibiz.filter.IbzTopSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IbzTop] 服务对象接口
 */
public interface IIbzTopService extends IService<IbzTop> {

    boolean create(IbzTop et);
    boolean update(IbzTop et);
    boolean sysUpdate(IbzTop et);
    boolean remove(String key);
    IbzTop get(String key);
    IbzTop sysGet(String key);
    IbzTop getDraft(IbzTop et);
    boolean checkKey(IbzTop et);
    boolean save(IbzTop et);
    List<IbzTop> select(IbzTopSearchContext context);
    List<IbzTop> selectDefault(IbzTopSearchContext context);
    List<IbzTop> selectView(IbzTopSearchContext context);

    Page<IbzTop> searchDefault(IbzTopSearchContext context);
    IbzTop dynamicCall(String key, String action, IbzTop et);
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

    List<IbzTop> getIbztopByIds(List<String> ids);
    List<IbzTop> getIbztopByEntities(List<IbzTop> entities);
}


