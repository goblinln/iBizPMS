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

import cn.ibizlab.pms.core.ibizsysmodel.domain.PSDEField;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSDEFieldSearchContext;


/**
 * 实体[PSDEField] 服务对象接口
 */
public interface IPSDEFieldService {

    /**
     * 业务实体显示文本名称
     */
    final static String OBJECT_TEXT_NAME = "实体属性";

    /**
     * 业务实体资源路径名
     */
    final static String OBJECT_SOURCE_PATH = "psdefields";

    boolean create(PSDEField et);
    void createBatch(List<PSDEField> list);
    boolean update(PSDEField et);
    void updateBatch(List<PSDEField> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    PSDEField get(String key);
    PSDEField getDraft(PSDEField et);
    boolean checkKey(PSDEField et);
    boolean save(PSDEField et);
    void saveBatch(List<PSDEField> list);
    Page<PSDEField> searchDefault(PSDEFieldSearchContext context);
    List<PSDEField> selectByPsdeid(String psdataentityid);
    void removeByPsdeid(Collection<String> ids);
    void removeByPsdeid(String psdataentityid);
    List<PSDEField> selectByDerpsdefid(String psdefieldid);
    List<PSDEField> selectByDerpsdefid(Collection<String> ids);
    void removeByDerpsdefid(String psdefieldid);
    List<PSDEField> selectByDupcheckpsdefid(String psdefieldid);
    List<PSDEField> selectByDupcheckpsdefid(Collection<String> ids);
    void removeByDupcheckpsdefid(String psdefieldid);
    List<PSDEField> selectByNo2dupchkpsdefid(String psdefieldid);
    List<PSDEField> selectByNo2dupchkpsdefid(Collection<String> ids);
    void removeByNo2dupchkpsdefid(String psdefieldid);
    List<PSDEField> selectByNo3dupchkpsdefid(String psdefieldid);
    List<PSDEField> selectByNo3dupchkpsdefid(Collection<String> ids);
    void removeByNo3dupchkpsdefid(String psdefieldid);
    List<PSDEField> selectByValuepsdefid(String psdefieldid);
    List<PSDEField> selectByValuepsdefid(Collection<String> ids);
    void removeByValuepsdefid(String psdefieldid);
    PSDEField dynamicCall(String key, String action, PSDEField et);

    boolean create(String devSlnSysId , PSDEField et) ;
    void createBatch(String devSlnSysId, List<PSDEField> list);
    boolean update(String devSlnSysId, PSDEField et);
    void updateBatch(String devSlnSysId, List<PSDEField> list);
    boolean remove(String devSlnSysId, String key);
    void removeBatch(String devSlnSysId , Collection<String> idList) ;
    PSDEField get(String devSlnSysId , String key);
    String getByCodeName(String devSlnSysId , String codeName);
    PSDEField getDraft(String devSlnSysId, PSDEField et);
    boolean checkKey(String devSlnSysId, PSDEField et);
    boolean save(String devSlnSysId, PSDEField et);
    void saveBatch(String devSlnSysId, List<PSDEField> list);
    Page<PSDEField> searchDefault(String devSlnSysId, PSDEFieldSearchContext context);
    List<PSDEField> selectByPsdeid(String devSlnSysId, String psdataentityid);
    void removeByPsdeid(String devSlnSysId, String psdataentityid);
    List<PSDEField> selectByDerpsdefid(String devSlnSysId, String psdefieldid);
    void removeByDerpsdefid(String devSlnSysId, String psdefieldid);
    List<PSDEField> selectByDupcheckpsdefid(String devSlnSysId, String psdefieldid);
    void removeByDupcheckpsdefid(String devSlnSysId, String psdefieldid);
    List<PSDEField> selectByNo2dupchkpsdefid(String devSlnSysId, String psdefieldid);
    void removeByNo2dupchkpsdefid(String devSlnSysId, String psdefieldid);
    List<PSDEField> selectByNo3dupchkpsdefid(String devSlnSysId, String psdefieldid);
    void removeByNo3dupchkpsdefid(String devSlnSysId, String psdefieldid);
    List<PSDEField> selectByValuepsdefid(String devSlnSysId, String psdefieldid);
    void removeByValuepsdefid(String devSlnSysId, String psdefieldid);

}



