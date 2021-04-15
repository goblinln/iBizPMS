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

import cn.ibizlab.pms.core.ibiz.domain.IbzLibModule;
import cn.ibizlab.pms.core.ibiz.filter.IbzLibModuleSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IbzLibModule] 服务对象接口
 */
public interface IIbzLibModuleService extends IService<IbzLibModule> {

    boolean create(IbzLibModule et);
    void createBatch(List<IbzLibModule> list);
    boolean update(IbzLibModule et);
    boolean sysUpdate(IbzLibModule et);
    void updateBatch(List<IbzLibModule> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IbzLibModule get(Long key);
    IbzLibModule sysGet(Long key);
    IbzLibModule getDraft(IbzLibModule et);
    boolean checkKey(IbzLibModule et);
    boolean save(IbzLibModule et);
    void saveBatch(List<IbzLibModule> list);
    Page<IbzLibModule> searchDefault(IbzLibModuleSearchContext context);
    Page<IbzLibModule> searchRoot_NoBranch(IbzLibModuleSearchContext context);
    List<IbzLibModule> selectByParent(Long id);
    void removeByParent(Long id);
    List<IbzLibModule> selectByRoot(Long id);
    void removeByRoot(Long id);
    IbzLibModule dynamicCall(Long key, String action, IbzLibModule et);
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


