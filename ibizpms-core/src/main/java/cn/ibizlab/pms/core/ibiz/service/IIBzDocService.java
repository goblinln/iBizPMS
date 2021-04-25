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

import cn.ibizlab.pms.core.ibiz.domain.IBzDoc;
import cn.ibizlab.pms.core.ibiz.filter.IBzDocSearchContext;


/**
 * 实体[IBzDoc] 服务对象接口
 */
public interface IIBzDocService {

    boolean create(IBzDoc et);
    void createBatch(List<IBzDoc> list);
    boolean update(IBzDoc et);
    boolean sysUpdate(IBzDoc et);
    void updateBatch(List<IBzDoc> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    IBzDoc get(String key);
    IBzDoc sysGet(String key);
    IBzDoc getDraft(IBzDoc et);
    boolean checkKey(IBzDoc et);
    boolean save(IBzDoc et);
    void saveBatch(List<IBzDoc> list);

    Page<IBzDoc> searchDefault(IBzDocSearchContext context);
    IBzDoc dynamicCall(String key, String action, IBzDoc et);

}



