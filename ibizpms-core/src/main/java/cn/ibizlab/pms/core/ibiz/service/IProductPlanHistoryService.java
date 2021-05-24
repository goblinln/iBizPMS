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

import cn.ibizlab.pms.core.ibiz.domain.ProductPlanHistory;
import cn.ibizlab.pms.core.ibiz.filter.ProductPlanHistorySearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[ProductPlanHistory] 服务对象接口
 */
public interface IProductPlanHistoryService extends IService<ProductPlanHistory> {

    boolean create(ProductPlanHistory et);
    void createBatch(List<ProductPlanHistory> list);
    boolean update(ProductPlanHistory et);
    boolean sysUpdate(ProductPlanHistory et);
    void updateBatch(List<ProductPlanHistory> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    ProductPlanHistory get(Long key);
    ProductPlanHistory sysGet(Long key);
    ProductPlanHistory getDraft(ProductPlanHistory et);
    boolean checkKey(ProductPlanHistory et);
    boolean save(ProductPlanHistory et);
    void saveBatch(List<ProductPlanHistory> list);
    List<ProductPlanHistory> select(ProductPlanHistorySearchContext context);
    List<ProductPlanHistory> selectDefault(ProductPlanHistorySearchContext context);
    List<ProductPlanHistory> selectSimple(ProductPlanHistorySearchContext context);
    List<ProductPlanHistory> selectView(ProductPlanHistorySearchContext context);

    Page<ProductPlanHistory> searchDefault(ProductPlanHistorySearchContext context);
    ProductPlanHistory dynamicCall(Long key, String action, ProductPlanHistory et);
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

    List<ProductPlanHistory> getProductplanhistoryByIds(List<Long> ids);
    List<ProductPlanHistory> getProductplanhistoryByEntities(List<ProductPlanHistory> entities);
}


