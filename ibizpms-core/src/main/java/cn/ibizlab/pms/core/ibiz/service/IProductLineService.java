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

import cn.ibizlab.pms.core.ibiz.domain.ProductLine;
import cn.ibizlab.pms.core.ibiz.filter.ProductLineSearchContext;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 实体[ProductLine] 服务对象接口
 */
public interface IProductLineService extends IService<ProductLine> {

    boolean create(ProductLine et);
    boolean update(ProductLine et);
    boolean sysUpdate(ProductLine et);
    boolean remove(String key);
    ProductLine get(String key);
    ProductLine sysGet(String key);
    ProductLine getDraft(ProductLine et);
    boolean checkKey(ProductLine et);
    boolean save(ProductLine et);
    List<ProductLine> select(ProductLineSearchContext context);
    List<ProductLine> selectDefault(ProductLineSearchContext context);
    List<ProductLine> selectView(ProductLineSearchContext context);

    Page<ProductLine> searchDefault(ProductLineSearchContext context);
    ProductLine dynamicCall(String key, String action, ProductLine et);
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

    List<ProductLine> getProductlineByIds(List<String> ids);
    List<ProductLine> getProductlineByEntities(List<ProductLine> entities);
}


