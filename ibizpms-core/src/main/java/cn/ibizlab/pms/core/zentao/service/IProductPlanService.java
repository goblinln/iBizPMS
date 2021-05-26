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

import cn.ibizlab.pms.core.zentao.domain.ProductPlan;
import cn.ibizlab.pms.core.zentao.filter.ProductPlanSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[ProductPlan] 服务对象接口
 */
public interface IProductPlanService extends IService<ProductPlan> {

    boolean create(ProductPlan et);
    void createBatch(List<ProductPlan> list);
    boolean update(ProductPlan et);
    boolean sysUpdate(ProductPlan et);
    void updateBatch(List<ProductPlan> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    ProductPlan get(Long key);
    ProductPlan sysGet(Long key);
    ProductPlan getDraft(ProductPlan et);
    ProductPlan batchLinkBug(ProductPlan et);
    ProductPlan batchLinkStory(ProductPlan et);
    ProductPlan batchUnlinkBug(ProductPlan et);
    ProductPlan batchUnlinkStory(ProductPlan et);
    boolean checkKey(ProductPlan et);
    ProductPlan eeActivePlan(ProductPlan et);
    ProductPlan eeCancelPlan(ProductPlan et);
    ProductPlan eeClosePlan(ProductPlan et);
    ProductPlan eeFinishPlan(ProductPlan et);
    ProductPlan eePausePlan(ProductPlan et);
    ProductPlan eeRestartPlan(ProductPlan et);
    ProductPlan eeStartPlan(ProductPlan et);
    ProductPlan getOldPlanName(ProductPlan et);
    ProductPlan importPlanTemplet(ProductPlan et);
    ProductPlan linkBug(ProductPlan et);
    ProductPlan linkStory(ProductPlan et);
    ProductPlan linkTask(ProductPlan et);
    ProductPlan mobProductPlanCounter(ProductPlan et);
    boolean save(ProductPlan et);
    void saveBatch(List<ProductPlan> list);
    ProductPlan unlinkBug(ProductPlan et);
    ProductPlan unlinkStory(ProductPlan et);
    List<ProductPlan> select(ProductPlanSearchContext context);
    List<ProductPlan> selectChildPlan(ProductPlanSearchContext context);
    List<ProductPlan> selectCurProductPlan(ProductPlanSearchContext context);
    List<ProductPlan> selectDefault(ProductPlanSearchContext context);
    List<ProductPlan> selectDefaultParent(ProductPlanSearchContext context);
    List<ProductPlan> selectGetList(ProductPlanSearchContext context);
    List<ProductPlan> selectPlanCodeList(ProductPlanSearchContext context);
    List<ProductPlan> selectPlanTasks(ProductPlanSearchContext context);
    List<ProductPlan> selectProductQuery(ProductPlanSearchContext context);
    List<ProductPlan> selectProjectApp(ProductPlanSearchContext context);
    List<ProductPlan> selectProjectPlan(ProductPlanSearchContext context);
    List<ProductPlan> selectRootPlan(ProductPlanSearchContext context);
    List<ProductPlan> selectTaskPlan(ProductPlanSearchContext context);
    List<ProductPlan> selectView(ProductPlanSearchContext context);

    Page<ProductPlan> searchChildPlan(ProductPlanSearchContext context);
    Page<ProductPlan> searchCurProductPlan(ProductPlanSearchContext context);
    Page<ProductPlan> searchCurProductPlanStory(ProductPlanSearchContext context);
    Page<ProductPlan> searchDefault(ProductPlanSearchContext context);
    Page<ProductPlan> searchDefaultParent(ProductPlanSearchContext context);
    Page<ProductPlan> searchPlanCodeList(ProductPlanSearchContext context);
    Page<ProductPlan> searchPlanTasks(ProductPlanSearchContext context);
    Page<ProductPlan> searchProductQuery(ProductPlanSearchContext context);
    Page<ProductPlan> searchProjectApp(ProductPlanSearchContext context);
    Page<ProductPlan> searchProjectPlan(ProductPlanSearchContext context);
    Page<ProductPlan> searchRootPlan(ProductPlanSearchContext context);
    Page<ProductPlan> searchTaskPlan(ProductPlanSearchContext context);
    List<ProductPlan> selectByBranch(Long id);
    void removeByBranch(Long id);
    List<ProductPlan> selectByProduct(Long id);
    void removeByProduct(Long id);
    List<ProductPlan> selectByParent(Long id);
    List<ProductPlan> selectByParent(Collection<Long> ids);
    void removeByParent(Long id);
    ProductPlan dynamicCall(Long key, String action, ProductPlan et);
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

    List<ProductPlan> getProductplanByIds(List<Long> ids);
    List<ProductPlan> getProductplanByEntities(List<ProductPlan> entities);
}


