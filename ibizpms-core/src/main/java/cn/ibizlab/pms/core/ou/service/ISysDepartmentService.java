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
import com.alibaba.fastjson.JSONObject;
import org.springframework.cache.annotation.CacheEvict;

import cn.ibizlab.pms.core.ou.domain.SysDepartment;
import cn.ibizlab.pms.core.ou.filter.SysDepartmentSearchContext;


/**
 * 实体[SysDepartment] 服务对象接口
 */
public interface ISysDepartmentService{

    boolean create(SysDepartment et) ;
    void createBatch(List<SysDepartment> list) ;
    boolean update(SysDepartment et) ;
    void updateBatch(List<SysDepartment> list) ;
    boolean remove(String key) ;
    void removeBatch(Collection<String> idList) ;
    SysDepartment get(String key) ;
    SysDepartment getDraft(SysDepartment et) ;
    boolean checkKey(SysDepartment et) ;
    boolean save(SysDepartment et) ;
    void saveBatch(List<SysDepartment> list) ;
    Page<SysDepartment> searchDefault(SysDepartmentSearchContext context) ;


}



