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

import cn.ibizlab.pms.core.uaa.domain.SysRole;
import cn.ibizlab.pms.core.uaa.filter.SysRoleSearchContext;


/**
 * 实体[SysRole] 服务对象接口
 */
public interface ISysRoleService {

    boolean create(SysRole et);
    boolean update(SysRole et);
    boolean sysUpdate(SysRole et);
    boolean remove(String key);
    SysRole get(String key);
    SysRole sysGet(String key);
    SysRole getDraft(SysRole et);
    boolean checkKey(SysRole et);
    boolean save(SysRole et);

    Page<SysRole> searchDefault(SysRoleSearchContext context);
    List<SysRole> selectByProleid(String roleid);
    void removeByProleid(String roleid);
    SysRole dynamicCall(String key, String action, SysRole et);
}



