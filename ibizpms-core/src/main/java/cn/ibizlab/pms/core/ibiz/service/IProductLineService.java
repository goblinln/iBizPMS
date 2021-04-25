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


/**
 * 实体[ProductLine] 服务对象接口
 */
public interface IProductLineService {

    boolean create(ProductLine et);
    void createBatch(List<ProductLine> list);
    boolean update(ProductLine et);
    boolean sysUpdate(ProductLine et);
    void updateBatch(List<ProductLine> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    ProductLine get(String key);
    ProductLine sysGet(String key);
    ProductLine getDraft(ProductLine et);
    boolean checkKey(ProductLine et);
    boolean save(ProductLine et);
    void saveBatch(List<ProductLine> list);
    
    List<ProductLine> selectQueryByDefault(ProductLineSearchContext context);
    List<ProductLine> selectQueryByView(ProductLineSearchContext context);

    Page<ProductLine> searchDefault(ProductLineSearchContext context);
    ProductLine dynamicCall(String key, String action, ProductLine et);

}



