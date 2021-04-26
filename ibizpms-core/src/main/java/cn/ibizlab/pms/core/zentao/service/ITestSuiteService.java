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

import cn.ibizlab.pms.core.zentao.domain.TestSuite;
import cn.ibizlab.pms.core.zentao.filter.TestSuiteSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[TestSuite] 服务对象接口
 */
public interface ITestSuiteService extends IService<TestSuite> {

    boolean create(TestSuite et);
    void createBatch(List<TestSuite> list);
    boolean update(TestSuite et);
    boolean sysUpdate(TestSuite et);
    void updateBatch(List<TestSuite> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    TestSuite get(Long key);
    TestSuite sysGet(Long key);
    TestSuite getDraft(TestSuite et);
    boolean checkKey(TestSuite et);
    TestSuite linkCase(TestSuite et);
    boolean linkCaseBatch(List<TestSuite> etList);
    TestSuite mobTestSuiteCount(TestSuite et);
    boolean mobTestSuiteCountBatch(List<TestSuite> etList);
    boolean save(TestSuite et);
    void saveBatch(List<TestSuite> list);
    TestSuite unlinkCase(TestSuite et);
    boolean unlinkCaseBatch(List<TestSuite> etList);
    List<TestSuite> select(TestSuiteSearchContext context);
    List<TestSuite> selectDefault(TestSuiteSearchContext context);
    List<TestSuite> selectPublicTestSuite(TestSuiteSearchContext context);
    List<TestSuite> selectView(TestSuiteSearchContext context);

    Page<TestSuite> searchDefault(TestSuiteSearchContext context);
    Page<TestSuite> searchPublicTestSuite(TestSuiteSearchContext context);
    List<TestSuite> selectByProduct(Long id);
    void removeByProduct(Long id);
    TestSuite dynamicCall(Long key, String action, TestSuite et);
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

    List<TestSuite> getTestsuiteByIds(List<Long> ids);
    List<TestSuite> getTestsuiteByEntities(List<TestSuite> entities);
}


