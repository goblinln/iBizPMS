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
    ProductPlan batchUnlinkBug(ProductPlan et);
    boolean batchUnlinkBugBatch(List<ProductPlan> etList);
    ProductPlan batchUnlinkStory(ProductPlan et);
    boolean batchUnlinkStoryBatch(List<ProductPlan> etList);
    boolean checkKey(ProductPlan et);
    ProductPlan eeActivePlan(ProductPlan et);
    boolean eeActivePlanBatch(List<ProductPlan> etList);
    ProductPlan eeCancelPlan(ProductPlan et);
    boolean eeCancelPlanBatch(List<ProductPlan> etList);
    ProductPlan eeClosePlan(ProductPlan et);
    boolean eeClosePlanBatch(List<ProductPlan> etList);
    ProductPlan eeFinishPlan(ProductPlan et);
    boolean eeFinishPlanBatch(List<ProductPlan> etList);
    ProductPlan eePausePlan(ProductPlan et);
    boolean eePausePlanBatch(List<ProductPlan> etList);
    ProductPlan eeRestartPlan(ProductPlan et);
    boolean eeRestartPlanBatch(List<ProductPlan> etList);
    ProductPlan eeStartPlan(ProductPlan et);
    boolean eeStartPlanBatch(List<ProductPlan> etList);
    ProductPlan getOldPlanName(ProductPlan et);
    boolean getOldPlanNameBatch(List<ProductPlan> etList);
    ProductPlan importPlanTemplet(ProductPlan et);
    boolean importPlanTempletBatch(List<ProductPlan> etList);
    ProductPlan linkBug(ProductPlan et);
    boolean linkBugBatch(List<ProductPlan> etList);
    ProductPlan linkStory(ProductPlan et);
    boolean linkStoryBatch(List<ProductPlan> etList);
    ProductPlan linkTask(ProductPlan et);
    boolean linkTaskBatch(List<ProductPlan> etList);
    ProductPlan mobProductPlanCounter(ProductPlan et);
    boolean mobProductPlanCounterBatch(List<ProductPlan> etList);
    boolean save(ProductPlan et);
    void saveBatch(List<ProductPlan> list);
    ProductPlan unlinkBug(ProductPlan et);
    boolean unlinkBugBatch(List<ProductPlan> etList);
    ProductPlan unlinkStory(ProductPlan et);
    boolean unlinkStoryBatch(List<ProductPlan> etList);
    List<ProductPlan> select(ProductPlanSearchContext context);
    List<ProductPlan> selectQueryByChildPlan(ProductPlanSearchContext context);
    List<ProductPlan> selectQueryByDefault(ProductPlanSearchContext context);
    List<ProductPlan> selectQueryByDefaultParent(ProductPlanSearchContext context);
    List<ProductPlan> selectQueryByGetList(ProductPlanSearchContext context);
    List<ProductPlan> selectQueryByPlanCodeList(ProductPlanSearchContext context);
    List<ProductPlan> selectQueryByPlanTasks(ProductPlanSearchContext context);
    List<ProductPlan> selectQueryByProductQuery(ProductPlanSearchContext context);
    List<ProductPlan> selectQueryByProjectApp(ProductPlanSearchContext context);
    List<ProductPlan> selectQueryByProjectPlan(ProductPlanSearchContext context);
    List<ProductPlan> selectQueryByRootPlan(ProductPlanSearchContext context);
    List<ProductPlan> selectQueryByTaskPlan(ProductPlanSearchContext context);
    List<ProductPlan> selectQueryByView(ProductPlanSearchContext context);

    Page<ProductPlan> searchChildPlan(ProductPlanSearchContext context);
    Page<ProductPlan> searchCurProductPlan(ProductPlanSearchContext context);
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


