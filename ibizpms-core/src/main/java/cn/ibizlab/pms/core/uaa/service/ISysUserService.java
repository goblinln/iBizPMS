package cn.ibizlab.pms.core.uaa.service;

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

import cn.ibizlab.pms.core.uaa.domain.SysUser;
import cn.ibizlab.pms.core.uaa.filter.SysUserSearchContext;


/**
 * 实体[SysUser] 服务对象接口
 */
public interface ISysUserService {

    boolean create(SysUser et);
    void createBatch(List<SysUser> list);
    boolean update(SysUser et);
    boolean sysUpdate(SysUser et);
    void updateBatch(List<SysUser> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    SysUser get(String key);
    SysUser sysGet(String key);
    SysUser getDraft(SysUser et);
    SysUser changePwd(SysUser et);
    boolean checkKey(SysUser et);
    boolean save(SysUser et);
    void saveBatch(List<SysUser> list);

    Page<SysUser> searchDefault(SysUserSearchContext context);
    SysUser dynamicCall(String key, String action, SysUser et);
}



