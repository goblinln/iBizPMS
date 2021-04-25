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

import cn.ibizlab.pms.core.ibiz.domain.IbzAgent;
import cn.ibizlab.pms.core.ibiz.filter.IbzAgentSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IbzAgent] 服务对象接口
 */
public interface IIbzAgentService extends IService<IbzAgent> {

    boolean create(IbzAgent et);
    void createBatch(List<IbzAgent> list);
    boolean update(IbzAgent et);
    boolean sysUpdate(IbzAgent et);
    void updateBatch(List<IbzAgent> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IbzAgent get(Long key);
    IbzAgent sysGet(Long key);
    IbzAgent getDraft(IbzAgent et);
    boolean checkKey(IbzAgent et);
    boolean save(IbzAgent et);
    void saveBatch(List<IbzAgent> list);
    List<IbzAgent> select(IbzAgentSearchContext context);
    
    List<IbzAgent> selectQueryByDefault(IbzAgentSearchContext context);
    List<IbzAgent> selectQueryByView(IbzAgentSearchContext context);

    Page<IbzAgent> searchDefault(IbzAgentSearchContext context);
    IbzAgent dynamicCall(Long key, String action, IbzAgent et);
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

    List<IbzAgent> getIbzagentByIds(List<Long> ids);
    List<IbzAgent> getIbzagentByEntities(List<IbzAgent> entities);
}


