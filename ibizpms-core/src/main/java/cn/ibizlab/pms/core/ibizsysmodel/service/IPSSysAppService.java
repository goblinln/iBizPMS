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

import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSysApp;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSysAppSearchContext;


/**
 * 实体[PSSysApp] 服务对象接口
 */
public interface IPSSysAppService {

    boolean create(PSSysApp et);
    void createBatch(List<PSSysApp> list);
    boolean update(PSSysApp et);
    boolean sysUpdate(PSSysApp et);
    void updateBatch(List<PSSysApp> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    PSSysApp get(String key);
    PSSysApp sysGet(String key);
    PSSysApp getDraft(PSSysApp et);
    boolean checkKey(PSSysApp et);
    boolean save(PSSysApp et);
    void saveBatch(List<PSSysApp> list);
    
    List<PSSysApp> selectQueryByBuild(PSSysAppSearchContext context);
    List<PSSysApp> selectQueryByDefault(PSSysAppSearchContext context);
    List<PSSysApp> selectQueryByView(PSSysAppSearchContext context);

    Page<PSSysApp> searchBuild(PSSysAppSearchContext context);
    Page<PSSysApp> searchDefault(PSSysAppSearchContext context);
    List<PSSysApp> selectByPssysserviceapiid(String pssysserviceapiid);
    List<PSSysApp> selectByPssysserviceapiid(Collection<String> ids);
    void removeByPssysserviceapiid(String pssysserviceapiid);
    PSSysApp dynamicCall(String key, String action, PSSysApp et);
}



