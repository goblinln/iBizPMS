package cn.ibizlab.pms.core.ibizsysmodel.service;

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

import cn.ibizlab.pms.core.ibizsysmodel.domain.PSModule;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSModuleSearchContext;


/**
 * 实体[PSModule] 服务对象接口
 */
public interface IPSModuleService {

    boolean create(PSModule et);
    void createBatch(List<PSModule> list);
    boolean update(PSModule et);
    boolean sysUpdate(PSModule et);
    void updateBatch(List<PSModule> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    PSModule get(String key);
    PSModule sysGet(String key);
    PSModule getDraft(PSModule et);
    boolean checkKey(PSModule et);
    boolean save(PSModule et);
    void saveBatch(List<PSModule> list);
    Page<PSModule> searchDefault(PSModuleSearchContext context);
    PSModule dynamicCall(String key, String action, PSModule et);
}



