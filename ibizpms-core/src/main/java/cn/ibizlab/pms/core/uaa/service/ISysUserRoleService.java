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

import cn.ibizlab.pms.core.uaa.domain.SysUserRole;
import cn.ibizlab.pms.core.uaa.filter.SysUserRoleSearchContext;


/**
 * 实体[SysUserRole] 服务对象接口
 */
public interface ISysUserRoleService {

    boolean create(SysUserRole et);
    boolean update(SysUserRole et);
    boolean sysUpdate(SysUserRole et);
    boolean remove(String key);
    SysUserRole get(String key);
    SysUserRole sysGet(String key);
    SysUserRole getDraft(SysUserRole et);
    boolean checkKey(SysUserRole et);
    boolean save(SysUserRole et);

    Page<SysUserRole> searchDefault(SysUserRoleSearchContext context);
    List<SysUserRole> selectByRoleid(String roleid);
    void removeByRoleid(Collection<String> ids);
    void removeByRoleid(String roleid);
    List<SysUserRole> selectByUserid(String userid);
    void removeByUserid(String userid);
    SysUserRole dynamicCall(String key, String action, SysUserRole et);
}



