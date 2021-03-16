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

import cn.ibizlab.pms.core.ibizsysmodel.domain.PSModule;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSModuleSearchContext;


/**
 * 实体[PSModule] 服务对象接口
 */
public interface IPSModuleService {

    /**
     * 业务实体显示文本名称
     */
    final static String OBJECT_TEXT_NAME = "系统模块";

    /**
     * 业务实体资源路径名
     */
    final static String OBJECT_SOURCE_PATH = "psmodules";

    boolean create(PSModule et);
    void createBatch(List<PSModule> list);
    boolean update(PSModule et);
    void updateBatch(List<PSModule> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    PSModule get(String key);
    PSModule getDraft(PSModule et);
    boolean checkKey(PSModule et);
    boolean save(PSModule et);
    void saveBatch(List<PSModule> list);
    Page<PSModule> searchDefault(PSModuleSearchContext context);
    PSModule dynamicCall(String key, String action, PSModule et);

    boolean create(String devSlnSysId , PSModule et) ;
    void createBatch(String devSlnSysId, List<PSModule> list);
    boolean update(String devSlnSysId, PSModule et);
    void updateBatch(String devSlnSysId, List<PSModule> list);
    boolean remove(String devSlnSysId, String key);
    void removeBatch(String devSlnSysId , Collection<String> idList) ;
    PSModule get(String devSlnSysId , String key);
    String getByCodeName(String devSlnSysId , String codeName);
    PSModule getDraft(String devSlnSysId, PSModule et);
    boolean checkKey(String devSlnSysId, PSModule et);
    boolean save(String devSlnSysId, PSModule et);
    void saveBatch(String devSlnSysId, List<PSModule> list);
    Page<PSModule> searchDefault(String devSlnSysId, PSModuleSearchContext context);
    PSModule dynamicCall(String key, String action, PSModule et);

}



