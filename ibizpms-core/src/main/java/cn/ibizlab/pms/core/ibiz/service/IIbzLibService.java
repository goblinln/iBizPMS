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

import cn.ibizlab.pms.core.ibiz.domain.IbzLib;
import cn.ibizlab.pms.core.ibiz.filter.IbzLibSearchContext;


import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IbzLib] 服务对象接口
 */
public interface IIbzLibService extends IService<IbzLib>{

    boolean create(IbzLib et) ;
    void createBatch(List<IbzLib> list) ;
    boolean update(IbzLib et) ;
    void updateBatch(List<IbzLib> list) ;
    boolean remove(BigInteger key) ;
    void removeBatch(Collection<BigInteger> idList) ;
    IbzLib get(BigInteger key) ;
    IbzLib getDraft(IbzLib et) ;
    boolean checkKey(IbzLib et) ;
    boolean save(IbzLib et) ;
    void saveBatch(List<IbzLib> list) ;
    Page<IbzLib> searchDefault(IbzLibSearchContext context) ;
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


