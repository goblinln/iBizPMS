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

import cn.ibizlab.pms.core.zentao.domain.Job;
import cn.ibizlab.pms.core.zentao.filter.JobSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[Job] 服务对象接口
 */
public interface IJobService extends IService<Job> {

    boolean create(Job et);
    void createBatch(List<Job> list);
    boolean update(Job et);
    boolean sysUpdate(Job et);
    void updateBatch(List<Job> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    Job get(Long key);
    Job sysGet(Long key);
    Job getDraft(Job et);
    boolean checkKey(Job et);
    boolean save(Job et);
    void saveBatch(List<Job> list);
    List<Job> select(JobSearchContext context);
    List<Job> selectQueryByDefault(JobSearchContext context);
    List<Job> selectQueryByView(JobSearchContext context);

    Page<Job> searchDefault(JobSearchContext context);
    Job dynamicCall(Long key, String action, Job et);
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


