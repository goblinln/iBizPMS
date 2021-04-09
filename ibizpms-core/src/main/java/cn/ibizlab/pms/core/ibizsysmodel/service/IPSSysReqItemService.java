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

    /**
     * 业务实体显示文本名称
     */
    final static String OBJECT_TEXT_NAME = "系统需求项";

    /**
     * 业务实体资源路径名
     */
    final static String OBJECT_SOURCE_PATH = "pssysreqitems";

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

    boolean create(String devSlnSysId , PSSysReqItem et) ;
    void createBatch(String devSlnSysId, List<PSSysReqItem> list);
    boolean update(String devSlnSysId, PSSysReqItem et);
    void updateBatch(String devSlnSysId, List<PSSysReqItem> list);
    boolean remove(String devSlnSysId, String key);
    void removeBatch(String devSlnSysId , Collection<String> idList) ;
    PSSysReqItem get(String devSlnSysId , String key);
    String getByCodeName(String devSlnSysId , String codeName);
    PSSysReqItem getDraft(String devSlnSysId, PSSysReqItem et);
    boolean checkKey(String devSlnSysId, PSSysReqItem et);
    boolean save(String devSlnSysId, PSSysReqItem et);
    void saveBatch(String devSlnSysId, List<PSSysReqItem> list);
    Page<PSSysReqItem> searchDefault(String devSlnSysId, PSSysReqItemSearchContext context);
    List<PSSysReqItem> selectByPpssysreqitemid(String devSlnSysId, String pssysreqitemid);
    void removeByPpssysreqitemid(String devSlnSysId, String pssysreqitemid);
    List<PSSysReqItem> selectByPssysreqmoduleid(String devSlnSysId, String pssysreqmoduleid);
    void removeByPssysreqmoduleid(String devSlnSysId, String pssysreqmoduleid);

}



