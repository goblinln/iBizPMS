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

import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSysReqItem;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSysReqItemSearchContext;


/**
 * 实体[PSSysReqItem] 服务对象接口
 */
public interface IPSSysReqItemService {

    boolean create(PSSysReqItem et);
    void createBatch(List<PSSysReqItem> list);
    boolean update(PSSysReqItem et);
    void updateBatch(List<PSSysReqItem> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    PSSysReqItem get(String key);
    PSSysReqItem getDraft(PSSysReqItem et);
    boolean checkKey(PSSysReqItem et);
    boolean save(PSSysReqItem et);
    void saveBatch(List<PSSysReqItem> list);
    Page<PSSysReqItem> searchDefault(PSSysReqItemSearchContext context);
    List<PSSysReqItem> selectByPpssysreqitemid(String pssysreqitemid);
    void removeByPpssysreqitemid(Collection<String> ids);
    void removeByPpssysreqitemid(String pssysreqitemid);
    List<PSSysReqItem> selectByPssysreqmoduleid(String pssysreqmoduleid);
    List<PSSysReqItem> selectByPssysreqmoduleid(Collection<String> ids);
    void removeByPssysreqmoduleid(String pssysreqmoduleid);
    PSSysReqItem dynamicCall(String key, String action, PSSysReqItem et);

}



