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

import cn.ibizlab.pms.core.ibiz.domain.ProductModule;
import cn.ibizlab.pms.core.ibiz.filter.ProductModuleSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[ProductModule] 服务对象接口
 */
public interface IProductModuleService extends IService<ProductModule> {

    boolean create(ProductModule et);
    void createBatch(List<ProductModule> list);
    boolean update(ProductModule et);
    boolean sysUpdate(ProductModule et);
    void updateBatch(List<ProductModule> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    ProductModule get(Long key);
    ProductModule sysGet(Long key);
    ProductModule getDraft(ProductModule et);
    boolean checkKey(ProductModule et);
    ProductModule fix(ProductModule et);
    boolean fixBatch(List<ProductModule> etList);
    ProductModule removeModule(ProductModule et);
    boolean removeModuleBatch(List<ProductModule> etList);
    boolean save(ProductModule et);
    void saveBatch(List<ProductModule> list);
    ProductModule syncFromIBIZ(ProductModule et);
    boolean syncFromIBIZBatch(List<ProductModule> etList);
    List<ProductModule> select(ProductModuleSearchContext context);
    
    List<ProductModule> selectQueryByByPath(ProductModuleSearchContext context);
    List<ProductModule> selectQueryByDefault(ProductModuleSearchContext context);
    List<ProductModule> selectQueryByParentModule(ProductModuleSearchContext context);
    List<ProductModule> selectQueryByRoot(ProductModuleSearchContext context);
    List<ProductModule> selectQueryByRoot_NoBranch(ProductModuleSearchContext context);
    List<ProductModule> selectQueryByStoryModule(ProductModuleSearchContext context);
    List<ProductModule> selectQueryByView(ProductModuleSearchContext context);

    Page<ProductModule> searchByPath(ProductModuleSearchContext context);
    Page<ProductModule> searchDefault(ProductModuleSearchContext context);
    Page<ProductModule> searchParentModule(ProductModuleSearchContext context);
    Page<ProductModule> searchRoot(ProductModuleSearchContext context);
    Page<ProductModule> searchRoot_NoBranch(ProductModuleSearchContext context);
    Page<ProductModule> searchStoryModule(ProductModuleSearchContext context);
    List<ProductModule> selectByParent(Long id);
    void removeByParent(Long id);
    List<ProductModule> selectByRoot(Long id);
    void removeByRoot(Long id);
    ProductModule dynamicCall(Long key, String action, ProductModule et);
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


