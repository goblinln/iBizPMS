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

import cn.ibizlab.pms.core.ou.domain.SysDepartment;
import cn.ibizlab.pms.core.ou.filter.SysDepartmentSearchContext;


/**
 * 实体[SysDepartment] 服务对象接口
 */
public interface ISysDepartmentService {

    boolean create(SysDepartment et);
    boolean update(SysDepartment et);
    boolean sysUpdate(SysDepartment et);
    boolean remove(String key);
    SysDepartment get(String key);
    SysDepartment sysGet(String key);
    SysDepartment getDraft(SysDepartment et);
    boolean checkKey(SysDepartment et);
    boolean save(SysDepartment et);

    Page<SysDepartment> searchDefault(SysDepartmentSearchContext context);
    List<SysDepartment> selectByParentdeptid(String deptid);
    List<SysDepartment> selectByParentdeptid(Collection<String> ids);
    void removeByParentdeptid(String deptid);
    List<SysDepartment> selectByOrgid(String orgid);
    List<SysDepartment> selectByOrgid(Collection<String> ids);
    void removeByOrgid(String orgid);
    void saveByOrgid(String orgid, List<SysDepartment> list) ;
    SysDepartment dynamicCall(String key, String action, SysDepartment et);
}



