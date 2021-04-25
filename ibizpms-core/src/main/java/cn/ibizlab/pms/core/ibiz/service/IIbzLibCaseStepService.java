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

import cn.ibizlab.pms.core.ibiz.domain.IbzLibCaseStep;
import cn.ibizlab.pms.core.ibiz.filter.IbzLibCaseStepSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IbzLibCaseStep] 服务对象接口
 */
public interface IIbzLibCaseStepService extends IService<IbzLibCaseStep> {

    boolean create(IbzLibCaseStep et);
    void createBatch(List<IbzLibCaseStep> list);
    boolean update(IbzLibCaseStep et);
    boolean sysUpdate(IbzLibCaseStep et);
    void updateBatch(List<IbzLibCaseStep> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IbzLibCaseStep get(Long key);
    IbzLibCaseStep sysGet(Long key);
    IbzLibCaseStep getDraft(IbzLibCaseStep et);
    boolean checkKey(IbzLibCaseStep et);
    boolean save(IbzLibCaseStep et);
    void saveBatch(List<IbzLibCaseStep> list);
    List<IbzLibCaseStep> select(IbzLibCaseStepSearchContext context);
    Page<IbzLibCaseStep> searchDefault(IbzLibCaseStepSearchContext context);
    IbzLibCaseStep dynamicCall(Long key, String action, IbzLibCaseStep et);
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


