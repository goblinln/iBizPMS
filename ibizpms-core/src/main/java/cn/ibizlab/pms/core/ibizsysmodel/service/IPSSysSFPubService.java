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

import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSysSFPub;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSysSFPubSearchContext;


/**
 * 实体[PSSysSFPub] 服务对象接口
 */
public interface IPSSysSFPubService {

    /**
     * 业务实体显示文本名称
     */
    final static String OBJECT_TEXT_NAME = "后台服务架构";

    /**
     * 业务实体资源路径名
     */
    final static String OBJECT_SOURCE_PATH = "pssyssfpubs";

    boolean create(PSSysSFPub et);
    void createBatch(List<PSSysSFPub> list);
    boolean update(PSSysSFPub et);
    void updateBatch(List<PSSysSFPub> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    PSSysSFPub get(String key);
    PSSysSFPub getDraft(PSSysSFPub et);
    boolean checkKey(PSSysSFPub et);
    boolean save(PSSysSFPub et);
    void saveBatch(List<PSSysSFPub> list);
    Page<PSSysSFPub> searchBuild(PSSysSFPubSearchContext context);
    Page<PSSysSFPub> searchDefault(PSSysSFPubSearchContext context);
    List<PSSysSFPub> selectByPpssyssfpubid(String pssyssfpubid);
    List<PSSysSFPub> selectByPpssyssfpubid(Collection<String> ids);
    void removeByPpssyssfpubid(String pssyssfpubid);
    PSSysSFPub dynamicCall(String key, String action, PSSysSFPub et);

    boolean create(String devSlnSysId , PSSysSFPub et) ;
    void createBatch(String devSlnSysId, List<PSSysSFPub> list);
    boolean update(String devSlnSysId, PSSysSFPub et);
    void updateBatch(String devSlnSysId, List<PSSysSFPub> list);
    boolean remove(String devSlnSysId, String key);
    void removeBatch(String devSlnSysId , Collection<String> idList) ;
    PSSysSFPub get(String devSlnSysId , String key);
    String getByCodeName(String devSlnSysId , String codeName);
    PSSysSFPub getDraft(String devSlnSysId, PSSysSFPub et);
    boolean checkKey(String devSlnSysId, PSSysSFPub et);
    boolean save(String devSlnSysId, PSSysSFPub et);
    void saveBatch(String devSlnSysId, List<PSSysSFPub> list);
    Page<PSSysSFPub> searchBuild(String devSlnSysId, PSSysSFPubSearchContext context);
    Page<PSSysSFPub> searchDefault(String devSlnSysId, PSSysSFPubSearchContext context);
    List<PSSysSFPub> selectByPpssyssfpubid(String devSlnSysId, String pssyssfpubid);
    void removeByPpssyssfpubid(String devSlnSysId, String pssyssfpubid);
    PSSysSFPub dynamicCall(String key, String action, PSSysSFPub et);

}



