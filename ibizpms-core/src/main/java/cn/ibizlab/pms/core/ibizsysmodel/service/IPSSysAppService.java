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

    /**
     * 业务实体显示文本名称
     */
    final static String OBJECT_TEXT_NAME = "系统应用";

    /**
     * 业务实体资源路径名
     */
    final static String OBJECT_SOURCE_PATH = "pssysapps";

    boolean create(PSSysApp et);
    void createBatch(List<PSSysApp> list);
    boolean update(PSSysApp et);
    void updateBatch(List<PSSysApp> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    PSSysApp get(String key);
    PSSysApp getDraft(PSSysApp et);
    boolean checkKey(PSSysApp et);
    boolean save(PSSysApp et);
    void saveBatch(List<PSSysApp> list);
    Page<PSSysApp> searchBuild(PSSysAppSearchContext context);
    Page<PSSysApp> searchDefault(PSSysAppSearchContext context);
    List<PSSysApp> selectByPssysserviceapiid(String pssysserviceapiid);
    List<PSSysApp> selectByPssysserviceapiid(Collection<String> ids);
    void removeByPssysserviceapiid(String pssysserviceapiid);
    PSSysApp dynamicCall(String key, String action, PSSysApp et);

    boolean create(String devSlnSysId , PSSysApp et) ;
    void createBatch(String devSlnSysId, List<PSSysApp> list);
    boolean update(String devSlnSysId, PSSysApp et);
    void updateBatch(String devSlnSysId, List<PSSysApp> list);
    boolean remove(String devSlnSysId, String key);
    void removeBatch(String devSlnSysId , Collection<String> idList) ;
    PSSysApp get(String devSlnSysId , String key);
    String getByCodeName(String devSlnSysId , String codeName);
    PSSysApp getDraft(String devSlnSysId, PSSysApp et);
    boolean checkKey(String devSlnSysId, PSSysApp et);
    boolean save(String devSlnSysId, PSSysApp et);
    void saveBatch(String devSlnSysId, List<PSSysApp> list);
    Page<PSSysApp> searchBuild(String devSlnSysId, PSSysAppSearchContext context);
    Page<PSSysApp> searchDefault(String devSlnSysId, PSSysAppSearchContext context);
    List<PSSysApp> selectByPssysserviceapiid(String devSlnSysId, String pssysserviceapiid);
    void removeByPssysserviceapiid(String devSlnSysId, String pssysserviceapiid);

}



