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
import com.alibaba.fastjson.JSONObject;
import org.springframework.cache.annotation.CacheEvict;

import cn.ibizlab.pms.core.ibiz.domain.IBZ_PROJECTTEAM;
import cn.ibizlab.pms.core.ibiz.filter.IBZ_PROJECTTEAMSearchContext;


import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IBZ_PROJECTTEAM] 服务对象接口
 */
public interface IIBZ_PROJECTTEAMService extends IService<IBZ_PROJECTTEAM>{

    boolean create(IBZ_PROJECTTEAM et) ;
    void createBatch(List<IBZ_PROJECTTEAM> list) ;
    boolean update(IBZ_PROJECTTEAM et) ;
    void updateBatch(List<IBZ_PROJECTTEAM> list) ;
    boolean remove(BigInteger key) ;
    void removeBatch(Collection<BigInteger> idList) ;
    IBZ_PROJECTTEAM get(BigInteger key) ;
    IBZ_PROJECTTEAM getDraft(IBZ_PROJECTTEAM et) ;
    boolean checkKey(IBZ_PROJECTTEAM et) ;
    boolean save(IBZ_PROJECTTEAM et) ;
    void saveBatch(List<IBZ_PROJECTTEAM> list) ;
    Page<IBZ_PROJECTTEAM> searchDefault(IBZ_PROJECTTEAMSearchContext context) ;
    List<IBZ_PROJECTTEAM> selectByRoot(BigInteger id) ;
    void removeByRoot(BigInteger id) ;
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


