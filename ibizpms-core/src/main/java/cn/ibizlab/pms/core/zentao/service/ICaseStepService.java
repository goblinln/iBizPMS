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

import cn.ibizlab.pms.core.zentao.domain.CaseStep;
import cn.ibizlab.pms.core.zentao.filter.CaseStepSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[CaseStep] 服务对象接口
 */
public interface ICaseStepService extends IService<CaseStep> {

    boolean create(CaseStep et);
    void createBatch(List<CaseStep> list);
    boolean update(CaseStep et);
    boolean sysUpdate(CaseStep et);
    void updateBatch(List<CaseStep> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    CaseStep get(Long key);
    CaseStep sysGet(Long key);
    CaseStep getDraft(CaseStep et);
    boolean checkKey(CaseStep et);
    boolean save(CaseStep et);
    void saveBatch(List<CaseStep> list);
    List<CaseStep> select(CaseStepSearchContext context);
    Page<CaseStep> searchCurTest(CaseStepSearchContext context);
    Page<CaseStep> searchDefault(CaseStepSearchContext context);
    Page<CaseStep> searchDefault1(CaseStepSearchContext context);
    Page<CaseStep> searchMob(CaseStepSearchContext context);
    Page<CaseStep> searchVersion(CaseStepSearchContext context);
    Page<CaseStep> searchVersions(CaseStepSearchContext context);
    List<CaseStep> selectByIbizcase(Long id);
    void removeByIbizcase(Long id);
    void saveByIbizcase(Long id, List<CaseStep> list) ;
    List<CaseStep> selectByParent(Long id);
    void removeByParent(Long id);
    void saveByParent(Long id, List<CaseStep> list) ;
    CaseStep dynamicCall(Long key, String action, CaseStep et);
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

    List<CaseStep> getCasestepByIds(List<Long> ids);
    List<CaseStep> getCasestepByEntities(List<CaseStep> entities);
}


