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

import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSystemDBCfg;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSystemDBCfgSearchContext;


/**
 * 实体[PSSystemDBCfg] 服务对象接口
 */
public interface IPSSystemDBCfgService {

    boolean create(PSSystemDBCfg et);
    void createBatch(List<PSSystemDBCfg> list);
    boolean update(PSSystemDBCfg et);
    boolean sysUpdate(PSSystemDBCfg et);
    void updateBatch(List<PSSystemDBCfg> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    PSSystemDBCfg get(String key);
    PSSystemDBCfg sysGet(String key);
    PSSystemDBCfg getDraft(PSSystemDBCfg et);
    boolean checkKey(PSSystemDBCfg et);
    boolean save(PSSystemDBCfg et);
    void saveBatch(List<PSSystemDBCfg> list);
    List<PSSystemDBCfg> select(PSSystemDBCfgSearchContext context);

    Page<PSSystemDBCfg> searchBuild(PSSystemDBCfgSearchContext context);
    Page<PSSystemDBCfg> searchDefault(PSSystemDBCfgSearchContext context);
    PSSystemDBCfg dynamicCall(String key, String action, PSSystemDBCfg et);
}



