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

    /**
     * 业务实体显示文本名称
     */
    final static String OBJECT_TEXT_NAME = "系统数据库";

    /**
     * 业务实体资源路径名
     */
    final static String OBJECT_SOURCE_PATH = "pssystemdbcfgs";

    boolean create(PSSystemDBCfg et);
    void createBatch(List<PSSystemDBCfg> list);
    boolean update(PSSystemDBCfg et);
    void updateBatch(List<PSSystemDBCfg> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    PSSystemDBCfg get(String key);
    PSSystemDBCfg getDraft(PSSystemDBCfg et);
    boolean checkKey(PSSystemDBCfg et);
    boolean save(PSSystemDBCfg et);
    void saveBatch(List<PSSystemDBCfg> list);
    Page<PSSystemDBCfg> searchBuild(PSSystemDBCfgSearchContext context);
    Page<PSSystemDBCfg> searchDefault(PSSystemDBCfgSearchContext context);
    PSSystemDBCfg dynamicCall(String key, String action, PSSystemDBCfg et);

    boolean create(String devSlnSysId , PSSystemDBCfg et) ;
    void createBatch(String devSlnSysId, List<PSSystemDBCfg> list);
    boolean update(String devSlnSysId, PSSystemDBCfg et);
    void updateBatch(String devSlnSysId, List<PSSystemDBCfg> list);
    boolean remove(String devSlnSysId, String key);
    void removeBatch(String devSlnSysId , Collection<String> idList) ;
    PSSystemDBCfg get(String devSlnSysId , String key);
    String getByCodeName(String devSlnSysId , String codeName);
    PSSystemDBCfg getDraft(String devSlnSysId, PSSystemDBCfg et);
    boolean checkKey(String devSlnSysId, PSSystemDBCfg et);
    boolean save(String devSlnSysId, PSSystemDBCfg et);
    void saveBatch(String devSlnSysId, List<PSSystemDBCfg> list);
    Page<PSSystemDBCfg> searchBuild(String devSlnSysId, PSSystemDBCfgSearchContext context);
    Page<PSSystemDBCfg> searchDefault(String devSlnSysId, PSSystemDBCfgSearchContext context);
    PSSystemDBCfg dynamicCall(String key, String action, PSSystemDBCfg et);

}



