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

import cn.ibizlab.pms.core.zentao.domain.SuiteCase;
import cn.ibizlab.pms.core.zentao.filter.SuiteCaseSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[SuiteCase] 服务对象接口
 */
public interface ISuiteCaseService extends IService<SuiteCase> {

    boolean create(SuiteCase et);
    void createBatch(List<SuiteCase> list);
    boolean update(SuiteCase et);
    boolean sysUpdate(SuiteCase et);
    void updateBatch(List<SuiteCase> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    SuiteCase get(String key);
    SuiteCase sysGet(String key);
    SuiteCase getDraft(SuiteCase et);
    boolean checkKey(SuiteCase et);
    boolean save(SuiteCase et);
    void saveBatch(List<SuiteCase> list);
    List<SuiteCase> select(SuiteCaseSearchContext context);
    List<SuiteCase> selectQueryByDefault(SuiteCaseSearchContext context);
    List<SuiteCase> selectQueryByView(SuiteCaseSearchContext context);

    Page<SuiteCase> searchDefault(SuiteCaseSearchContext context);
    List<SuiteCase> selectByIbizcase(Long id);
    void removeByIbizcase(Long id);
    List<SuiteCase> selectByProduct(Long id);
    void removeByProduct(Long id);
    List<SuiteCase> selectBySuite(Long id);
    void removeBySuite(Long id);
    SuiteCase dynamicCall(String key, String action, SuiteCase et);
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

    List<SuiteCase> getSuitecaseByIds(List<String> ids);
    List<SuiteCase> getSuitecaseByEntities(List<SuiteCase> entities);
}


