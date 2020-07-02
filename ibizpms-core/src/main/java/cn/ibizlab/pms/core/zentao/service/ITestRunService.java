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
import com.alibaba.fastjson.JSONObject;
import org.springframework.cache.annotation.CacheEvict;

import cn.ibizlab.pms.core.zentao.domain.TestRun;
import cn.ibizlab.pms.core.zentao.filter.TestRunSearchContext;


import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[TestRun] 服务对象接口
 */
public interface ITestRunService extends IService<TestRun>{

    boolean create(TestRun et) ;
    void createBatch(List<TestRun> list) ;
    boolean update(TestRun et) ;
    void updateBatch(List<TestRun> list) ;
    boolean remove(BigInteger key) ;
    void removeBatch(Collection<BigInteger> idList) ;
    TestRun get(BigInteger key) ;
    TestRun getDraft(TestRun et) ;
    boolean checkKey(TestRun et) ;
    boolean save(TestRun et) ;
    void saveBatch(List<TestRun> list) ;
    Page<TestRun> searchDefault(TestRunSearchContext context) ;
    List<TestRun> selectByIbizcase(BigInteger id) ;
    void removeByIbizcase(BigInteger id) ;
    List<TestRun> selectByTask(BigInteger id) ;
    void removeByTask(BigInteger id) ;
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


