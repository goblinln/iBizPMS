package cn.ibizlab.pms.core.zentao.service;

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

import cn.ibizlab.pms.core.zentao.domain.Product;
import cn.ibizlab.pms.core.zentao.filter.ProductSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[Product] 服务对象接口
 */
public interface IProductService extends IService<Product> {

    boolean create(Product et);
    void createBatch(List<Product> list);
    boolean update(Product et);
    boolean sysUpdate(Product et);
    void updateBatch(List<Product> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    Product get(Long key);
    Product sysGet(Long key);
    Product getDraft(Product et);
    Product cancelProductTop(Product et);
    boolean cancelProductTopBatch(List<Product> etList);
    boolean checkKey(Product et);
    Product close(Product et);
    boolean closeBatch(List<Product> etList);
    Product mobProductCounter(Product et);
    boolean mobProductCounterBatch(List<Product> etList);
    Product mobProductTestCounter(Product et);
    boolean mobProductTestCounterBatch(List<Product> etList);
    Product productTop(Product et);
    boolean productTopBatch(List<Product> etList);
    boolean save(Product et);
    void saveBatch(List<Product> list);
    Product sysGet(Product et);
    List<Product> select(ProductSearchContext context);
    List<Product> selectAllList(ProductSearchContext context);
    List<Product> selectAllProduct(ProductSearchContext context);
    List<Product> selectCheckNameOrCode(ProductSearchContext context);
    List<Product> selectCurDefault(ProductSearchContext context);
    List<Product> selectCurProject(ProductSearchContext context);
    List<Product> selectCurUer(ProductSearchContext context);
    List<Product> selectDefault(ProductSearchContext context);
    List<Product> selectDeveloperQuery(ProductSearchContext context);
    List<Product> selectESBulk(ProductSearchContext context);
    List<Product> selectOpenQuery(ProductSearchContext context);
    List<Product> selectPOQuery(ProductSearchContext context);
    List<Product> selectProductManagerQuery(ProductSearchContext context);
    List<Product> selectProductPM(ProductSearchContext context);
    List<Product> selectProductTeam(ProductSearchContext context);
    List<Product> selectQDQuery(ProductSearchContext context);
    List<Product> selectRDQuery(ProductSearchContext context);
    List<Product> selectSimple(ProductSearchContext context);
    List<Product> selectStoryCurProject(ProductSearchContext context);
    List<Product> selectView(ProductSearchContext context);

    Page<Product> searchAllList(ProductSearchContext context);
    Page<Product> searchAllProduct(ProductSearchContext context);
    Page<Product> searchCheckNameOrCode(ProductSearchContext context);
    Page<Product> searchCurDefault(ProductSearchContext context);
    Page<Product> searchCurProject(ProductSearchContext context);
    Page<Product> searchCurUer(ProductSearchContext context);
    Page<Product> searchDefault(ProductSearchContext context);
    Page<Product> searchDeveloperQuery(ProductSearchContext context);
    Page<Product> searchESBulk(ProductSearchContext context);
    Page<Product> searchOpenQuery(ProductSearchContext context);
    Page<Product> searchPOQuery(ProductSearchContext context);
    Page<Product> searchProductManagerQuery(ProductSearchContext context);
    Page<Product> searchProductPM(ProductSearchContext context);
    Page<Product> searchProductTeam(ProductSearchContext context);
    Page<Product> searchQDQuery(ProductSearchContext context);
    Page<Product> searchRDQuery(ProductSearchContext context);
    Page<Product> searchStoryCurProject(ProductSearchContext context);
    List<Product> selectByLine(Long id);
    void removeByLine(Long id);
    Product dynamicCall(Long key, String action, Product et);
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

    List<Product> getProductByIds(List<Long> ids);
    List<Product> getProductByEntities(List<Product> entities);
}


