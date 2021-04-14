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

import cn.ibizlab.pms.core.ibiz.domain.IbzCase;
import cn.ibizlab.pms.core.ibiz.filter.IbzCaseSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IbzCase] 服务对象接口
 */
public interface IIbzCaseService extends IService<IbzCase> {

    boolean create(IbzCase et);
    void createBatch(List<IbzCase> list);
    boolean update(IbzCase et);
    boolean sysUpdate(IbzCase et);
    void updateBatch(List<IbzCase> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IbzCase get(Long key);
    IbzCase sysGet(Long key);
    IbzCase getDraft(IbzCase et);
    boolean checkKey(IbzCase et);
    boolean save(IbzCase et);
    void saveBatch(List<IbzCase> list);
    Page<IbzCase> searchDefault(IbzCaseSearchContext context);
    List<IbzCase> selectByModule(Long id);
    void removeByModule(Long id);
    List<IbzCase> selectByLib(Long id);
    void removeByLib(Long id);
    IbzCase dynamicCall(Long key, String action, IbzCase et);
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


