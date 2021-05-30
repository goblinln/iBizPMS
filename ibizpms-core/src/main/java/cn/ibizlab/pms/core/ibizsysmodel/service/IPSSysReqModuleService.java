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

import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSysReqModule;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSysReqModuleSearchContext;


/**
 * 实体[PSSysReqModule] 服务对象接口
 */
public interface IPSSysReqModuleService {

    boolean create(PSSysReqModule et);
    void createBatch(List<PSSysReqModule> list);
    boolean update(PSSysReqModule et);
    boolean sysUpdate(PSSysReqModule et);
    void updateBatch(List<PSSysReqModule> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    PSSysReqModule get(String key);
    PSSysReqModule sysGet(String key);
    PSSysReqModule getDraft(PSSysReqModule et);
    boolean checkKey(PSSysReqModule et);
    boolean save(PSSysReqModule et);
    void saveBatch(List<PSSysReqModule> list);

    Page<PSSysReqModule> searchDefault(PSSysReqModuleSearchContext context);
    List<PSSysReqModule> selectByPsmoduleid(String psmoduleid);
    List<PSSysReqModule> selectByPsmoduleid(Collection<String> ids);
    void removeByPsmoduleid(String psmoduleid);
    List<PSSysReqModule> selectByPpssysreqmoduleid(String pssysreqmoduleid);
    List<PSSysReqModule> selectByPpssysreqmoduleid(Collection<String> ids);
    void removeByPpssysreqmoduleid(String pssysreqmoduleid);
    PSSysReqModule dynamicCall(String key, String action, PSSysReqModule et);
}



