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

import cn.ibizlab.pms.core.ibiz.domain.ProductLife;
import cn.ibizlab.pms.core.ibiz.filter.ProductLifeSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[ProductLife] 服务对象接口
 */
public interface IProductLifeService extends IService<ProductLife> {

    boolean create(ProductLife et);
    void createBatch(List<ProductLife> list);
    boolean update(ProductLife et);
    void updateBatch(List<ProductLife> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    ProductLife get(String key);
    ProductLife getDraft(ProductLife et);
    boolean checkKey(ProductLife et);
    boolean save(ProductLife et);
    void saveBatch(List<ProductLife> list);
    Page<ProductLife> searchDefault(ProductLifeSearchContext context);
    Page<ProductLife> searchGetRoadmap(ProductLifeSearchContext context);
    Page<ProductLife> searchGetRoadmapS(ProductLifeSearchContext context);
    Page<ProductLife> searchRoadMapYear(ProductLifeSearchContext context);
    ProductLife dynamicCall(String key, String action, ProductLife et);
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

    List<ProductLife> getProductlifeByIds(List<String> ids);
    List<ProductLife> getProductlifeByEntities(List<ProductLife> entities);
}


