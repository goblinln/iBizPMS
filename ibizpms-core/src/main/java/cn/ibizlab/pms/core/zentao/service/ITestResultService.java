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

import cn.ibizlab.pms.core.zentao.domain.TestResult;
import cn.ibizlab.pms.core.zentao.filter.TestResultSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[TestResult] 服务对象接口
 */
public interface ITestResultService extends IService<TestResult> {

    boolean create(TestResult et);
    void createBatch(List<TestResult> list);
    boolean update(TestResult et);
    boolean sysUpdate(TestResult et);
    void updateBatch(List<TestResult> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    TestResult get(Long key);
    TestResult sysGet(Long key);
    TestResult getDraft(TestResult et);
    boolean checkKey(TestResult et);
    boolean save(TestResult et);
    void saveBatch(List<TestResult> list);
    List<TestResult> select(TestResultSearchContext context);
    List<TestResult> selectCurTestRun(TestResultSearchContext context);
    List<TestResult> selectDefault(TestResultSearchContext context);
    List<TestResult> selectView(TestResultSearchContext context);

    Page<TestResult> searchCurTestRun(TestResultSearchContext context);
    Page<TestResult> searchDefault(TestResultSearchContext context);
    List<TestResult> selectByIbizcase(Long id);
    void removeByIbizcase(Long id);
    void saveByIbizcase(Long id, List<TestResult> list) ;
    List<TestResult> selectByCompile(Long id);
    void removeByCompile(Long id);
    void saveByCompile(Long id, List<TestResult> list) ;
    List<TestResult> selectByJob(Long id);
    void removeByJob(Long id);
    void saveByJob(Long id, List<TestResult> list) ;
    List<TestResult> selectByRun(Long id);
    void removeByRun(Long id);
    void saveByRun(Long id, List<TestResult> list) ;
    TestResult dynamicCall(Long key, String action, TestResult et);
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

    List<TestResult> getTestresultByIds(List<Long> ids);
    List<TestResult> getTestresultByEntities(List<TestResult> entities);
}


