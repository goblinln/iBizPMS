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

import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSubSysServiceAPI;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSubSysServiceAPISearchContext;


/**
 * 实体[PSSubSysServiceAPI] 服务对象接口
 */
public interface IPSSubSysServiceAPIService {

    /**
     * 业务实体显示文本名称
     */
    final static String OBJECT_TEXT_NAME = "外部服务接口";

    /**
     * 业务实体资源路径名
     */
    final static String OBJECT_SOURCE_PATH = "pssubsysserviceapis";

    boolean create(PSSubSysServiceAPI et);
    void createBatch(List<PSSubSysServiceAPI> list);
    boolean update(PSSubSysServiceAPI et);
    void updateBatch(List<PSSubSysServiceAPI> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    PSSubSysServiceAPI get(String key);
    PSSubSysServiceAPI getDraft(PSSubSysServiceAPI et);
    boolean checkKey(PSSubSysServiceAPI et);
    boolean save(PSSubSysServiceAPI et);
    void saveBatch(List<PSSubSysServiceAPI> list);
    Page<PSSubSysServiceAPI> searchDefault(PSSubSysServiceAPISearchContext context);
    List<PSSubSysServiceAPI> selectByPsmoduleid(String psmoduleid);
    List<PSSubSysServiceAPI> selectByPsmoduleid(Collection<String> ids);
    void removeByPsmoduleid(String psmoduleid);
    List<PSSubSysServiceAPI> selectByPssysserviceapiid(String pssysserviceapiid);
    List<PSSubSysServiceAPI> selectByPssysserviceapiid(Collection<String> ids);
    void removeByPssysserviceapiid(String pssysserviceapiid);
    PSSubSysServiceAPI dynamicCall(String key, String action, PSSubSysServiceAPI et);

    boolean create(String devSlnSysId , PSSubSysServiceAPI et) ;
    void createBatch(String devSlnSysId, List<PSSubSysServiceAPI> list);
    boolean update(String devSlnSysId, PSSubSysServiceAPI et);
    void updateBatch(String devSlnSysId, List<PSSubSysServiceAPI> list);
    boolean remove(String devSlnSysId, String key);
    void removeBatch(String devSlnSysId , Collection<String> idList) ;
    PSSubSysServiceAPI get(String devSlnSysId , String key);
    String getByCodeName(String devSlnSysId , String codeName);
    PSSubSysServiceAPI getDraft(String devSlnSysId, PSSubSysServiceAPI et);
    boolean checkKey(String devSlnSysId, PSSubSysServiceAPI et);
    boolean save(String devSlnSysId, PSSubSysServiceAPI et);
    void saveBatch(String devSlnSysId, List<PSSubSysServiceAPI> list);
    Page<PSSubSysServiceAPI> searchDefault(String devSlnSysId, PSSubSysServiceAPISearchContext context);
    List<PSSubSysServiceAPI> selectByPsmoduleid(String devSlnSysId, String psmoduleid);
    void removeByPsmoduleid(String devSlnSysId, String psmoduleid);
    List<PSSubSysServiceAPI> selectByPssysserviceapiid(String devSlnSysId, String pssysserviceapiid);
    void removeByPssysserviceapiid(String devSlnSysId, String pssysserviceapiid);

}



