package cn.ibizlab.pms.core.ou.service;

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

import cn.ibizlab.pms.core.ou.domain.SysPost;
import cn.ibizlab.pms.core.ou.filter.SysPostSearchContext;


/**
 * 实体[SysPost] 服务对象接口
 */
public interface ISysPostService {

    boolean create(SysPost et);
    boolean update(SysPost et);
    boolean sysUpdate(SysPost et);
    boolean remove(String key);
    SysPost get(String key);
    SysPost sysGet(String key);
    SysPost getDraft(SysPost et);
    boolean checkKey(SysPost et);
    boolean save(SysPost et);

    Page<SysPost> searchDefault(SysPostSearchContext context);
    SysPost dynamicCall(String key, String action, SysPost et);
}



