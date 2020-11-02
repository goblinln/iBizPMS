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

import cn.ibizlab.pms.core.ou.domain.SysOrganization;
import cn.ibizlab.pms.core.ou.filter.SysOrganizationSearchContext;


/**
 * 实体[SysOrganization] 服务对象接口
 */
public interface ISysOrganizationService{

    boolean create(SysOrganization et) ;
    void createBatch(List<SysOrganization> list) ;
    boolean update(SysOrganization et) ;
    void updateBatch(List<SysOrganization> list) ;
    boolean remove(String key) ;
    void removeBatch(Collection<String> idList) ;
    SysOrganization get(String key) ;
    SysOrganization getDraft(SysOrganization et) ;
    boolean checkKey(SysOrganization et) ;
    boolean save(SysOrganization et) ;
    void saveBatch(List<SysOrganization> list) ;
    Page<SysOrganization> searchDefault(SysOrganizationSearchContext context) ;


}



