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

    /**
     * 业务实体显示文本名称
     */
    final static String OBJECT_TEXT_NAME = "系统用户";

    /**
     * 业务实体资源路径名
     */
    final static String OBJECT_SOURCE_PATH = "sysusers";

    boolean create(SysUser et);
    void createBatch(List<SysUser> list);
    boolean update(SysUser et);
    void updateBatch(List<SysUser> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    SysUser get(String key);
    SysUser getDraft(SysUser et);
    SysUser changePwd(SysUser et);
    boolean changePwdBatch(List<SysUser> etList);
    boolean checkKey(SysUser et);
    boolean save(SysUser et);
    void saveBatch(List<SysUser> list);
    Page<SysUser> searchDefault(SysUserSearchContext context);
    SysUser dynamicCall(String key, String action, SysUser et);


}



