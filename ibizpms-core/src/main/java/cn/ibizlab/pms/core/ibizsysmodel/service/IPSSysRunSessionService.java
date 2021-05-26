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

    boolean create(PSSysRunSession et);
    boolean update(PSSysRunSession et);
    boolean sysUpdate(PSSysRunSession et);
    boolean remove(String key);
    PSSysRunSession get(String key);
    PSSysRunSession sysGet(String key);
    PSSysRunSession getDraft(PSSysRunSession et);
    boolean checkKey(PSSysRunSession et);
    boolean save(PSSysRunSession et);

    Page<PSSysRunSession> searchDefault(PSSysRunSessionSearchContext context);
    List<PSSysRunSession> selectByPssysappid(String pssysappid);
    void removeByPssysappid(String pssysappid);
    List<PSSysRunSession> selectByPssysappid2(String pssysappid);
    void removeByPssysappid2(String pssysappid);
    List<PSSysRunSession> selectByPssysserviceapiid(String pssysserviceapiid);
    void removeByPssysserviceapiid(String pssysserviceapiid);
    PSSysRunSession dynamicCall(String key, String action, PSSysRunSession et);
}



