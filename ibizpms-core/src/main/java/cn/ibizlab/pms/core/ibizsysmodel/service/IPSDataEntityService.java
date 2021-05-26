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

    boolean create(PSDataEntity et);
    boolean update(PSDataEntity et);
    boolean sysUpdate(PSDataEntity et);
    boolean remove(String key);
    PSDataEntity get(String key);
    PSDataEntity sysGet(String key);
    PSDataEntity getDraft(PSDataEntity et);
    boolean checkKey(PSDataEntity et);
    boolean save(PSDataEntity et);

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
}



