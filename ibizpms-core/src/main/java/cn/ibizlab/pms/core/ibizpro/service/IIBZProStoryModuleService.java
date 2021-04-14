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

import cn.ibizlab.pms.core.ibizpro.domain.IBZProStoryModule;
import cn.ibizlab.pms.core.ibizpro.filter.IBZProStoryModuleSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IBZProStoryModule] 服务对象接口
 */
public interface IIBZProStoryModuleService extends IService<IBZProStoryModule> {

    boolean create(IBZProStoryModule et);
    void createBatch(List<IBZProStoryModule> list);
    boolean update(IBZProStoryModule et);
    boolean sysUpdate(IBZProStoryModule et);
    void updateBatch(List<IBZProStoryModule> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IBZProStoryModule get(Long key);
    IBZProStoryModule sysGet(Long key);
    IBZProStoryModule getDraft(IBZProStoryModule et);
    boolean checkKey(IBZProStoryModule et);
    boolean save(IBZProStoryModule et);
    void saveBatch(List<IBZProStoryModule> list);
    IBZProStoryModule syncFromIBIZ(IBZProStoryModule et);
    boolean syncFromIBIZBatch(List<IBZProStoryModule> etList);
    Page<IBZProStoryModule> searchDefault(IBZProStoryModuleSearchContext context);
    List<IBZProStoryModule> selectByRoot(Long id);
    void removeByRoot(Long id);
    List<IBZProStoryModule> selectByParent(Long id);
    void removeByParent(Long id);
    IBZProStoryModule dynamicCall(Long key, String action, IBZProStoryModule et);
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


