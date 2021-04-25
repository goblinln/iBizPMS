package cn.ibizlab.pms.core.ibizpro.service;

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

import cn.ibizlab.pms.core.ibizpro.domain.AccountTaskestimate;
import cn.ibizlab.pms.core.ibizpro.filter.AccountTaskestimateSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[AccountTaskestimate] 服务对象接口
 */
public interface IAccountTaskestimateService extends IService<AccountTaskestimate> {

    boolean create(AccountTaskestimate et);
    void createBatch(List<AccountTaskestimate> list);
    boolean update(AccountTaskestimate et);
    boolean sysUpdate(AccountTaskestimate et);
    void updateBatch(List<AccountTaskestimate> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    AccountTaskestimate get(String key);
    AccountTaskestimate sysGet(String key);
    AccountTaskestimate getDraft(AccountTaskestimate et);
    boolean checkKey(AccountTaskestimate et);
    boolean save(AccountTaskestimate et);
    void saveBatch(List<AccountTaskestimate> list);
    List<AccountTaskestimate> select(AccountTaskestimateSearchContext context);
    List<AccountTaskestimate> selectAllAccountEstimate(AccountTaskestimateSearchContext context);
    List<AccountTaskestimate> selectDefault(AccountTaskestimateSearchContext context);
    List<AccountTaskestimate> selectView(AccountTaskestimateSearchContext context);

    Page<AccountTaskestimate> searchAllAccountEstimate(AccountTaskestimateSearchContext context);
    Page<AccountTaskestimate> searchDefault(AccountTaskestimateSearchContext context);
    AccountTaskestimate dynamicCall(String key, String action, AccountTaskestimate et);
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


