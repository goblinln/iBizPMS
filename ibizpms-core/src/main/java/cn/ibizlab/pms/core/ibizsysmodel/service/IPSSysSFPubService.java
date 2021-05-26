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

    boolean create(PSSysSFPub et);
    boolean update(PSSysSFPub et);
    boolean sysUpdate(PSSysSFPub et);
    boolean remove(String key);
    PSSysSFPub get(String key);
    PSSysSFPub sysGet(String key);
    PSSysSFPub getDraft(PSSysSFPub et);
    boolean checkKey(PSSysSFPub et);
    boolean save(PSSysSFPub et);

    Page<PSSysSFPub> searchBuild(PSSysSFPubSearchContext context);
    Page<PSSysSFPub> searchDefault(PSSysSFPubSearchContext context);
    List<PSSysSFPub> selectByPpssyssfpubid(String pssyssfpubid);
    List<PSSysSFPub> selectByPpssyssfpubid(Collection<String> ids);
    void removeByPpssyssfpubid(String pssyssfpubid);
    PSSysSFPub dynamicCall(String key, String action, PSSysSFPub et);
}



