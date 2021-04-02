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

import cn.ibizlab.pms.core.ibizsysmodel.domain.PSDataEntity;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSDataEntitySearchContext;


/**
 * 实体[PSDataEntity] 服务对象接口
 */
public interface IPSDataEntityService {

    /**
     * 业务实体显示文本名称
     */
    final static String OBJECT_TEXT_NAME = "实体";

    /**
     * 业务实体资源路径名
     */
    final static String OBJECT_SOURCE_PATH = "psdataentities";

    boolean create(PSDataEntity et);
    void createBatch(List<PSDataEntity> list);
    boolean update(PSDataEntity et);
    void updateBatch(List<PSDataEntity> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    PSDataEntity get(String key);
    PSDataEntity getDraft(PSDataEntity et);
    boolean checkKey(PSDataEntity et);
    boolean save(PSDataEntity et);
    void saveBatch(List<PSDataEntity> list);
    Page<PSDataEntity> searchDefault(PSDataEntitySearchContext context);
    List<PSDataEntity> selectByPsmoduleid(String psmoduleid);
    List<PSDataEntity> selectByPsmoduleid(Collection<String> ids);
    void removeByPsmoduleid(String psmoduleid);
    List<PSDataEntity> selectByPssubsyssadeid(String pssubsyssadeid);
    List<PSDataEntity> selectByPssubsyssadeid(Collection<String> ids);
    void removeByPssubsyssadeid(String pssubsyssadeid);
    List<PSDataEntity> selectByPssubsysserviceapiid(String pssubsysserviceapiid);
    List<PSDataEntity> selectByPssubsysserviceapiid(Collection<String> ids);
    void removeByPssubsysserviceapiid(String pssubsysserviceapiid);
    List<PSDataEntity> selectByPssysreqitemid(String pssysreqitemid);
    List<PSDataEntity> selectByPssysreqitemid(Collection<String> ids);
    void removeByPssysreqitemid(String pssysreqitemid);
    PSDataEntity dynamicCall(String key, String action, PSDataEntity et);

    boolean create(String devSlnSysId , PSDataEntity et) ;
    void createBatch(String devSlnSysId, List<PSDataEntity> list);
    boolean update(String devSlnSysId, PSDataEntity et);
    void updateBatch(String devSlnSysId, List<PSDataEntity> list);
    boolean remove(String devSlnSysId, String key);
    void removeBatch(String devSlnSysId , Collection<String> idList) ;
    PSDataEntity get(String devSlnSysId , String key);
    String getByCodeName(String devSlnSysId , String codeName);
    PSDataEntity getDraft(String devSlnSysId, PSDataEntity et);
    boolean checkKey(String devSlnSysId, PSDataEntity et);
    boolean save(String devSlnSysId, PSDataEntity et);
    void saveBatch(String devSlnSysId, List<PSDataEntity> list);
    Page<PSDataEntity> searchDefault(String devSlnSysId, PSDataEntitySearchContext context);
    List<PSDataEntity> selectByPsmoduleid(String devSlnSysId, String psmoduleid);
    void removeByPsmoduleid(String devSlnSysId, String psmoduleid);
    List<PSDataEntity> selectByPssubsyssadeid(String devSlnSysId, String pssubsyssadeid);
    void removeByPssubsyssadeid(String devSlnSysId, String pssubsyssadeid);
    List<PSDataEntity> selectByPssubsysserviceapiid(String devSlnSysId, String pssubsysserviceapiid);
    void removeByPssubsysserviceapiid(String devSlnSysId, String pssubsysserviceapiid);
    List<PSDataEntity> selectByPssysreqitemid(String devSlnSysId, String pssysreqitemid);
    void removeByPssysreqitemid(String devSlnSysId, String pssysreqitemid);

}



