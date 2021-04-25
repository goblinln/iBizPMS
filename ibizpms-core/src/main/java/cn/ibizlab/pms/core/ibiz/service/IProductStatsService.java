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

import cn.ibizlab.pms.core.ibiz.domain.ProductStats;
import cn.ibizlab.pms.core.ibiz.filter.ProductStatsSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[ProductStats] 服务对象接口
 */
public interface IProductStatsService extends IService<ProductStats> {

    boolean create(ProductStats et);
    void createBatch(List<ProductStats> list);
    boolean update(ProductStats et);
    boolean sysUpdate(ProductStats et);
    void updateBatch(List<ProductStats> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    ProductStats get(Long key);
    ProductStats sysGet(Long key);
    ProductStats getDraft(ProductStats et);
    boolean checkKey(ProductStats et);
    ProductStats getTestStats(ProductStats et);
    boolean save(ProductStats et);
    void saveBatch(List<ProductStats> list);
    List<ProductStats> select(ProductStatsSearchContext context);
    List<ProductStats> selectQueryByDefault(ProductStatsSearchContext context);
    List<ProductStats> selectQueryByNoOpenProduct(ProductStatsSearchContext context);
    List<ProductStats> selectQueryByProdctQuantiGird(ProductStatsSearchContext context);
    List<ProductStats> selectQueryByProductInputTable(ProductStatsSearchContext context);
    List<ProductStats> selectQueryByProductcompletionstatistics(ProductStatsSearchContext context);
    List<ProductStats> selectQueryByView(ProductStatsSearchContext context);

    Page<ProductStats> searchDefault(ProductStatsSearchContext context);
    Page<ProductStats> searchNoOpenProduct(ProductStatsSearchContext context);
    Page<ProductStats> searchProdctQuantiGird(ProductStatsSearchContext context);
    Page<ProductStats> searchProductInputTable(ProductStatsSearchContext context);
    Page<ProductStats> searchProductcompletionstatistics(ProductStatsSearchContext context);
    ProductStats dynamicCall(Long key, String action, ProductStats et);
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


