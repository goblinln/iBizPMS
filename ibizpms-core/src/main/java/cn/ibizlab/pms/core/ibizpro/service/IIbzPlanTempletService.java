package cn.ibizlab.pms.core.ibizpro.service;

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

import cn.ibizlab.pms.core.ibizpro.domain.IbzPlanTemplet;
import cn.ibizlab.pms.core.ibizpro.filter.IbzPlanTempletSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IbzPlanTemplet] 服务对象接口
 */
public interface IIbzPlanTempletService extends IService<IbzPlanTemplet> {

    boolean create(IbzPlanTemplet et);
    void createBatch(List<IbzPlanTemplet> list);
    boolean update(IbzPlanTemplet et);
    boolean sysUpdate(IbzPlanTemplet et);
    void updateBatch(List<IbzPlanTemplet> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    IbzPlanTemplet get(String key);
    IbzPlanTemplet sysGet(String key);
    IbzPlanTemplet getDraft(IbzPlanTemplet et);
    boolean checkKey(IbzPlanTemplet et);
    IbzPlanTemplet getPlan(IbzPlanTemplet et);
    boolean getPlanBatch(List<IbzPlanTemplet> etList);
    boolean save(IbzPlanTemplet et);
    void saveBatch(List<IbzPlanTemplet> list);
    List<IbzPlanTemplet> select(IbzPlanTempletSearchContext context);
    List<IbzPlanTemplet> selectQueryByCurUserTemplet(IbzPlanTempletSearchContext context);
    List<IbzPlanTemplet> selectQueryByDefault(IbzPlanTempletSearchContext context);
    List<IbzPlanTemplet> selectQueryByView(IbzPlanTempletSearchContext context);

    Page<IbzPlanTemplet> searchCurUserTemplet(IbzPlanTempletSearchContext context);
    Page<IbzPlanTemplet> searchDefault(IbzPlanTempletSearchContext context);
    IbzPlanTemplet dynamicCall(String key, String action, IbzPlanTemplet et);
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

    List<IbzPlanTemplet> getIbzplantempletByIds(List<String> ids);
    List<IbzPlanTemplet> getIbzplantempletByEntities(List<IbzPlanTemplet> entities);
}


