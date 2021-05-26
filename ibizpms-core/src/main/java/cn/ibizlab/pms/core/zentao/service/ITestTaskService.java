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

import cn.ibizlab.pms.core.zentao.domain.TestTask;
import cn.ibizlab.pms.core.zentao.filter.TestTaskSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[TestTask] 服务对象接口
 */
public interface ITestTaskService extends IService<TestTask> {

    boolean create(TestTask et);
    boolean update(TestTask et);
    boolean sysUpdate(TestTask et);
    boolean remove(Long key);
    TestTask get(Long key);
    TestTask sysGet(Long key);
    TestTask getDraft(TestTask et);
    TestTask activate(TestTask et);
    TestTask block(TestTask et);
    boolean checkKey(TestTask et);
    TestTask close(TestTask et);
    TestTask linkCase(TestTask et);
    TestTask mobTestTaskCounter(TestTask et);
    boolean save(TestTask et);
    TestTask start(TestTask et);
    TestTask unlinkCase(TestTask et);
    List<TestTask> select(TestTaskSearchContext context);
    List<TestTask> selectDefault(TestTaskSearchContext context);
    List<TestTask> selectMyTestTaskPc(TestTaskSearchContext context);
    List<TestTask> selectSimple(TestTaskSearchContext context);
    List<TestTask> selectView(TestTaskSearchContext context);

    Page<TestTask> searchDefault(TestTaskSearchContext context);
    Page<TestTask> searchMyTestTaskPc(TestTaskSearchContext context);
    List<TestTask> selectByBuild(Long id);
    void removeByBuild(Long id);
    List<TestTask> selectByProduct(Long id);
    void removeByProduct(Long id);
    List<TestTask> selectByProject(Long id);
    void removeByProject(Long id);
    JSONObject importData(List<TestTask> entities, int batchSize, boolean isIgnoreError);

    @Async("asyncExecutor")
    void asyncImportData(List<TestTask> entities, int batchSize, boolean isIgnoreError);
    TestTask dynamicCall(Long key, String action, TestTask et);
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

    List<TestTask> getTesttaskByIds(List<Long> ids);
    List<TestTask> getTesttaskByEntities(List<TestTask> entities);
}


