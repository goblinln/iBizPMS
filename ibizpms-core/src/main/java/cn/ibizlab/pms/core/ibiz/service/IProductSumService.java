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

import cn.ibizlab.pms.core.ibiz.domain.ProductSum;
import cn.ibizlab.pms.core.ibiz.filter.ProductSumSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[ProductSum] 服务对象接口
 */
public interface IProductSumService extends IService<ProductSum> {

    boolean create(ProductSum et);
    void createBatch(List<ProductSum> list);
    boolean update(ProductSum et);
    boolean sysUpdate(ProductSum et);
    void updateBatch(List<ProductSum> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    ProductSum get(Long key);
    ProductSum sysGet(Long key);
    ProductSum getDraft(ProductSum et);
    boolean checkKey(ProductSum et);
    boolean save(ProductSum et);
    void saveBatch(List<ProductSum> list);
    Page<ProductSum> searchDefault(ProductSumSearchContext context);
    Page<ProductSum> searchProductBugcnt_QA(ProductSumSearchContext context);
    Page<ProductSum> searchProductCreateStory(ProductSumSearchContext context);
    Page<ProductSum> searchProductStoryHoursSum(ProductSumSearchContext context);
    Page<ProductSum> searchProductStorySum(ProductSumSearchContext context);
    Page<ProductSum> searchProductStorycntAndPlancnt(ProductSumSearchContext context);
    Page<ProductSum> searchProductSumBugType(ProductSumSearchContext context);
    ProductSum dynamicCall(Long key, String action, ProductSum et);
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


