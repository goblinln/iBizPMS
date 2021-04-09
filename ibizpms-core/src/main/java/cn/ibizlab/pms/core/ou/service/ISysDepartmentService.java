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

    /**
     * 业务实体显示文本名称
     */
    final static String OBJECT_TEXT_NAME = "部门";

    /**
     * 业务实体资源路径名
     */
    final static String OBJECT_SOURCE_PATH = "sysdepartments";

    boolean create(SysDepartment et);
    void createBatch(List<SysDepartment> list);
    boolean update(SysDepartment et);
    void updateBatch(List<SysDepartment> list);
    boolean remove(String key);
    void removeBatch(Collection<String> idList);
    SysDepartment get(String key);
    SysDepartment getDraft(SysDepartment et);
    boolean checkKey(SysDepartment et);
    boolean save(SysDepartment et);
    void saveBatch(List<SysDepartment> list);
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



