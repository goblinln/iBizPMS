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

import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSysServiceAPI;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSysServiceAPISearchContext;


/**
 * 实体[PSSysServiceAPI] 服务对象接口
 */
public interface IPSSysServiceAPIService {

    boolean create(PSSysServiceAPI et);
    void createBatch(List<PSSysServiceAPI> list);
    boolean update(PSSysServiceAPI et);
    boolean sysUpdate(PSSysServiceAPI et);
    void updateBatch(List<PSSysServiceAPI> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    PSSysServiceAPI get(String key);
    PSSysServiceAPI sysGet(String key);
    PSSysServiceAPI getDraft(PSSysServiceAPI et);
    boolean checkKey(PSSysServiceAPI et);
    boolean save(PSSysServiceAPI et);
    void saveBatch(List<PSSysServiceAPI> list);
    Page<PSSysServiceAPI> searchDefault(PSSysServiceAPISearchContext context);
    List<PSSysServiceAPI> selectByPsmoduleid(String psmoduleid);
    List<PSSysServiceAPI> selectByPsmoduleid(Collection<String> ids);
    void removeByPsmoduleid(String psmoduleid);
    PSSysServiceAPI dynamicCall(String key, String action, PSSysServiceAPI et);
}



