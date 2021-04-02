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

import cn.ibizlab.pms.core.zentao.domain.Repo;
import cn.ibizlab.pms.core.zentao.filter.RepoSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[Repo] 服务对象接口
 */
public interface IRepoService extends IService<Repo> {

    boolean create(Repo et);
    void createBatch(List<Repo> list);
    boolean update(Repo et);
    void updateBatch(List<Repo> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    Repo get(Long key);
    Repo getDraft(Repo et);
    boolean checkKey(Repo et);
    boolean save(Repo et);
    void saveBatch(List<Repo> list);
    Page<Repo> searchDefault(RepoSearchContext context);
    Repo dynamicCall(Long key, String action, Repo et);
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


