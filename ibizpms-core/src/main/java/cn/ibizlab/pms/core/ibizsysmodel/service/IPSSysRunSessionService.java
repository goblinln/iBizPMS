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

import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSysRunSession;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSysRunSessionSearchContext;


/**
 * 实体[PSSysRunSession] 服务对象接口
 */
public interface IPSSysRunSessionService {

    /**
     * 业务实体显示文本名称
     */
    final static String OBJECT_TEXT_NAME = "系统运行会话";

    /**
     * 业务实体资源路径名
     */
    final static String OBJECT_SOURCE_PATH = "pssysrunsessions";

    boolean create(PSSysRunSession et);
    void createBatch(List<PSSysRunSession> list);
    boolean update(PSSysRunSession et);
    void updateBatch(List<PSSysRunSession> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    PSSysRunSession get(String key);
    PSSysRunSession getDraft(PSSysRunSession et);
    boolean checkKey(PSSysRunSession et);
    boolean save(PSSysRunSession et);
    void saveBatch(List<PSSysRunSession> list);
    Page<PSSysRunSession> searchDefault(PSSysRunSessionSearchContext context);
    List<PSSysRunSession> selectByPssysappid(String pssysappid);
    void removeByPssysappid(String pssysappid);
    List<PSSysRunSession> selectByPssysappid2(String pssysappid);
    void removeByPssysappid2(String pssysappid);
    List<PSSysRunSession> selectByPssysserviceapiid(String pssysserviceapiid);
    void removeByPssysserviceapiid(String pssysserviceapiid);
    PSSysRunSession dynamicCall(String key, String action, PSSysRunSession et);

    boolean create(String devSlnSysId , PSSysRunSession et) ;
    void createBatch(String devSlnSysId, List<PSSysRunSession> list);
    boolean update(String devSlnSysId, PSSysRunSession et);
    void updateBatch(String devSlnSysId, List<PSSysRunSession> list);
    boolean remove(String devSlnSysId, String key);
    void removeBatch(String devSlnSysId , Collection<String> idList) ;
    PSSysRunSession get(String devSlnSysId , String key);
    String getByCodeName(String devSlnSysId , String codeName);
    PSSysRunSession getDraft(String devSlnSysId, PSSysRunSession et);
    boolean checkKey(String devSlnSysId, PSSysRunSession et);
    boolean save(String devSlnSysId, PSSysRunSession et);
    void saveBatch(String devSlnSysId, List<PSSysRunSession> list);
    Page<PSSysRunSession> searchDefault(String devSlnSysId, PSSysRunSessionSearchContext context);
    List<PSSysRunSession> selectByPssysappid(String devSlnSysId, String pssysappid);
    void removeByPssysappid(String devSlnSysId, String pssysappid);
    List<PSSysRunSession> selectByPssysappid2(String devSlnSysId, String pssysappid);
    void removeByPssysappid2(String devSlnSysId, String pssysappid);
    List<PSSysRunSession> selectByPssysserviceapiid(String devSlnSysId, String pssysserviceapiid);
    void removeByPssysserviceapiid(String devSlnSysId, String pssysserviceapiid);

}



