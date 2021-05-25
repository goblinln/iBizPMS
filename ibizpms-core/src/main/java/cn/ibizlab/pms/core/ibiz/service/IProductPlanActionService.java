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

import cn.ibizlab.pms.core.ibiz.domain.ProductPlanAction;
import cn.ibizlab.pms.core.ibiz.filter.ProductPlanActionSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[ProductPlanAction] 服务对象接口
 */
public interface IProductPlanActionService extends IService<ProductPlanAction> {

    boolean create(ProductPlanAction et);
    void createBatch(List<ProductPlanAction> list);
    boolean update(ProductPlanAction et);
    boolean sysUpdate(ProductPlanAction et);
    void updateBatch(List<ProductPlanAction> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    ProductPlanAction get(Long key);
    ProductPlanAction sysGet(Long key);
    ProductPlanAction getDraft(ProductPlanAction et);
    boolean checkKey(ProductPlanAction et);
    ProductPlanAction comment(ProductPlanAction et);
    boolean commentBatch(List<ProductPlanAction> etList);
    ProductPlanAction createHis(ProductPlanAction et);
    boolean createHisBatch(List<ProductPlanAction> etList);
    ProductPlanAction editComment(ProductPlanAction et);
    boolean editCommentBatch(List<ProductPlanAction> etList);
    ProductPlanAction managePmsEe(ProductPlanAction et);
    boolean managePmsEeBatch(List<ProductPlanAction> etList);
    boolean save(ProductPlanAction et);
    void saveBatch(List<ProductPlanAction> list);
    ProductPlanAction sendMarkDone(ProductPlanAction et);
    boolean sendMarkDoneBatch(List<ProductPlanAction> etList);
    ProductPlanAction sendTodo(ProductPlanAction et);
    boolean sendTodoBatch(List<ProductPlanAction> etList);
    ProductPlanAction sendToread(ProductPlanAction et);
    boolean sendToreadBatch(List<ProductPlanAction> etList);
    List<ProductPlanAction> select(ProductPlanActionSearchContext context);
    List<ProductPlanAction> selectDefault(ProductPlanActionSearchContext context);
    List<ProductPlanAction> selectSimple(ProductPlanActionSearchContext context);
    List<ProductPlanAction> selectType(ProductPlanActionSearchContext context);
    List<ProductPlanAction> selectView(ProductPlanActionSearchContext context);

    Page<ProductPlanAction> searchDefault(ProductPlanActionSearchContext context);
    Page<ProductPlanAction> searchType(ProductPlanActionSearchContext context);
    List<ProductPlanAction> selectByObjectid(Long id);
    void removeByObjectid(Long id);
    ProductPlanAction dynamicCall(Long key, String action, ProductPlanAction et);
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

    List<ProductPlanAction> getProductplanactionByIds(List<Long> ids);
    List<ProductPlanAction> getProductplanactionByEntities(List<ProductPlanAction> entities);
}


