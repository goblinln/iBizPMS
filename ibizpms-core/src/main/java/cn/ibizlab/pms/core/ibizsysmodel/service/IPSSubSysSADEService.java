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

import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSubSysSADE;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSubSysSADESearchContext;


/**
 * 实体[PSSubSysSADE] 服务对象接口
 */
public interface IPSSubSysSADEService {

    boolean create(PSSubSysSADE et);
    void createBatch(List<PSSubSysSADE> list);
    boolean update(PSSubSysSADE et);
    boolean sysUpdate(PSSubSysSADE et);
    void updateBatch(List<PSSubSysSADE> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    PSSubSysSADE get(String key);
    PSSubSysSADE sysGet(String key);
    PSSubSysSADE getDraft(PSSubSysSADE et);
    boolean checkKey(PSSubSysSADE et);
    boolean save(PSSubSysSADE et);
    void saveBatch(List<PSSubSysSADE> list);

    Page<PSSubSysSADE> searchDefault(PSSubSysSADESearchContext context);
    List<PSSubSysSADE> selectByPssubsysserviceapiid(String pssubsysserviceapiid);
    List<PSSubSysSADE> selectByPssubsysserviceapiid(Collection<String> ids);
    void removeByPssubsysserviceapiid(String pssubsysserviceapiid);
    PSSubSysSADE dynamicCall(String key, String action, PSSubSysSADE et);
}



