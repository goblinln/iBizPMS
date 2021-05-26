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

import cn.ibizlab.pms.core.ibiz.domain.TestModule;
import cn.ibizlab.pms.core.ibiz.filter.TestModuleSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[TestModule] 服务对象接口
 */
public interface ITestModuleService extends IService<TestModule> {

    boolean create(TestModule et);
    boolean update(TestModule et);
    boolean sysUpdate(TestModule et);
    boolean remove(Long key);
    TestModule get(Long key);
    TestModule sysGet(Long key);
    TestModule getDraft(TestModule et);
    boolean checkKey(TestModule et);
    TestModule fix(TestModule et);
    TestModule removeModule(TestModule et);
    boolean save(TestModule et);
    List<TestModule> select(TestModuleSearchContext context);
    List<TestModule> selectByPath(TestModuleSearchContext context);
    List<TestModule> selectDefault(TestModuleSearchContext context);
    List<TestModule> selectParentModule(TestModuleSearchContext context);
    List<TestModule> selectRoot(TestModuleSearchContext context);
    List<TestModule> selectRoot_NoBranch(TestModuleSearchContext context);
    List<TestModule> selectTestModule(TestModuleSearchContext context);
    List<TestModule> selectView(TestModuleSearchContext context);

    Page<TestModule> searchByPath(TestModuleSearchContext context);
    Page<TestModule> searchDefault(TestModuleSearchContext context);
    Page<TestModule> searchParentModule(TestModuleSearchContext context);
    Page<TestModule> searchRoot(TestModuleSearchContext context);
    Page<TestModule> searchRoot_NoBranch(TestModuleSearchContext context);
    Page<TestModule> searchTestModule(TestModuleSearchContext context);
    List<TestModule> selectByParent(Long id);
    void removeByParent(Long id);
    List<TestModule> selectByRoot(Long id);
    void removeByRoot(Long id);
    TestModule dynamicCall(Long key, String action, TestModule et);
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

    List<TestModule> getTestmoduleByIds(List<Long> ids);
    List<TestModule> getTestmoduleByEntities(List<TestModule> entities);
}


