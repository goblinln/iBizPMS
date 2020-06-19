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

import cn.ibizlab.pms.core.zentao.domain.Release;
import cn.ibizlab.pms.core.zentao.filter.ReleaseSearchContext;


import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[Release] 服务对象接口
 */
public interface IReleaseService extends IService<Release>{

    boolean checkKey(Release et) ;
    Release terminate(Release et) ;
    Release activate(Release et) ;
    Release get(BigInteger key) ;
    boolean save(Release et) ;
    void saveBatch(List<Release> list) ;
    Release changeStatus(Release et) ;
    boolean create(Release et) ;
    void createBatch(List<Release> list) ;
    Release getDraft(Release et) ;
    boolean update(Release et) ;
    void updateBatch(List<Release> list) ;
    boolean remove(BigInteger key) ;
    void removeBatch(Collection<BigInteger> idList) ;
    Page<Release> searchDefault(ReleaseSearchContext context) ;
    List<Release> selectByBranch(BigInteger id) ;
    void removeByBranch(BigInteger id) ;
    List<Release> selectByBuild(BigInteger id) ;
    void removeByBuild(BigInteger id) ;
    List<Release> selectByProduct(BigInteger id) ;
    void removeByProduct(BigInteger id) ;
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


