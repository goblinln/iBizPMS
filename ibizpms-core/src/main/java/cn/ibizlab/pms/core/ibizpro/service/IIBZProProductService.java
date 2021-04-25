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

import cn.ibizlab.pms.core.ibizpro.domain.IBZProProduct;
import cn.ibizlab.pms.core.ibizpro.filter.IBZProProductSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[IBZProProduct] 服务对象接口
 */
public interface IIBZProProductService extends IService<IBZProProduct> {

    boolean create(IBZProProduct et);
    void createBatch(List<IBZProProduct> list);
    boolean update(IBZProProduct et);
    boolean sysUpdate(IBZProProduct et);
    void updateBatch(List<IBZProProduct> list);
    boolean remove(Long key);
    void removeBatch(Collection<Long> idList);
    IBZProProduct get(Long key);
    IBZProProduct sysGet(Long key);
    IBZProProduct getDraft(IBZProProduct et);
    boolean checkKey(IBZProProduct et);
    boolean save(IBZProProduct et);
    void saveBatch(List<IBZProProduct> list);
    List<IBZProProduct> select(IBZProProductSearchContext context);
    Page<IBZProProduct> searchDefault(IBZProProductSearchContext context);
    IBZProProduct dynamicCall(Long key, String action, IBZProProduct et);
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


