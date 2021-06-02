package cn.ibizlab.pms.core.uaa.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.math.BigInteger;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import cn.ibizlab.pms.util.errors.BadRequestAlertException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Lazy;
import cn.ibizlab.pms.core.uaa.domain.SysUserRole;
import cn.ibizlab.pms.core.uaa.filter.SysUserRoleSearchContext;
import cn.ibizlab.pms.core.uaa.service.ISysUserRoleService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import cn.ibizlab.pms.core.uaa.client.SysUserRoleFeignClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.alibaba.fastjson.JSONObject;
/**
 * 实体[用户角色关系] 服务对象接口实现
 */
@Slf4j
@Service
public class SysUserRoleServiceImpl implements ISysUserRoleService {

    @Autowired
    SysUserRoleFeignClient sysUserRoleFeignClient;


    @Override
    public List<SysUserRole> select(SysUserRoleSearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        return searchDefault(context).getContent();
    }

    @Override
    public boolean create(SysUserRole et) {
        SysUserRole rt = sysUserRoleFeignClient.create(et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void createBatch(List<SysUserRole> list){
        sysUserRoleFeignClient.createBatch(list) ;
    }

    @Override
    public boolean update(SysUserRole et) {
        SysUserRole rt = sysUserRoleFeignClient.update(et.getUserroleid(),et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt, et);
        return true;

    }

    public void updateBatch(List<SysUserRole> list){
        sysUserRoleFeignClient.updateBatch(list) ;
    }

    @Override
    public boolean sysUpdate(SysUserRole et) {
                                SysUserRole rt = sysUserRoleFeignClient.update(et.getUserroleid(),et);
        if(rt==null)
            return false;

        CachedBeanCopier.copy(rt, et);
        return true;
    }

    @Override
    public boolean remove(String userroleid) {
        boolean result=sysUserRoleFeignClient.remove(userroleid) ;
        return result;
    }

    public void removeBatch(Collection<String> idList){
        sysUserRoleFeignClient.removeBatch(idList);
    }

    @Override
    public SysUserRole get(String userroleid) {
		SysUserRole et=sysUserRoleFeignClient.get(userroleid);
        if(et==null){
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), userroleid);
        }
        else{
        }
        return  et;
    }

    @Override
    public SysUserRole sysGet(String userroleid) {
         return null;
    }

    @Override
    public SysUserRole getDraft(SysUserRole et) {
        et=sysUserRoleFeignClient.getDraft(et);
        return et;
    }

    @Override
    public boolean checkKey(SysUserRole et) {
        return sysUserRoleFeignClient.checkKey(et);
    }
    @Override
    @Transactional
    public boolean save(SysUserRole et) {
        boolean result = true;
        Object rt = sysUserRoleFeignClient.saveEntity(et);
        if(rt == null)
          return false;
        try {
            if (rt instanceof Map) {
                ObjectMapper mapper = new ObjectMapper();
                rt = mapper.readValue(mapper.writeValueAsString(rt), SysUserRole.class);
                if (rt != null) {
                    CachedBeanCopier.copy(rt, et);
                }
            } else if (rt instanceof Boolean) {
                result = (boolean) rt;
            }
        } catch (Exception e) {
        }
            return result;
    }

    @Override
    public void saveBatch(List<SysUserRole> list) {
        sysUserRoleFeignClient.saveBatch(list) ;
    }



	@Override
    public List<SysUserRole> selectByRoleid(String roleid) {
        SysUserRoleSearchContext context=new SysUserRoleSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_sys_roleid_eq(roleid);
        return sysUserRoleFeignClient.searchDefault(context).getContent();
    }
    @Override
    public void removeByRoleid(Collection<String> ids) {
    }


    @Override
    public void removeByRoleid(String roleid) {
        Set<String> delIds=new HashSet<String>();
        for(SysUserRole before:selectByRoleid(roleid)){
            delIds.add(before.getUserroleid());
        }
        if(delIds.size()>0)
            this.removeBatch(delIds);
    }

	@Override
    public List<SysUserRole> selectByUserid(String userid) {
        SysUserRoleSearchContext context=new SysUserRoleSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_sys_userid_eq(userid);
        return sysUserRoleFeignClient.searchDefault(context).getContent();
    }

    @Override
    public void removeByUserid(String userid) {
        Set<String> delIds=new HashSet<String>();
        for(SysUserRole before:selectByUserid(userid)){
            delIds.add(before.getUserroleid());
        }
        if(delIds.size()>0)
            this.removeBatch(delIds);
    }



    /**
     * 查询集合 数据集
     */
    @Override
    public Page<SysUserRole> searchDefault(SysUserRoleSearchContext context) {
        Page<SysUserRole> sysUserRoles=sysUserRoleFeignClient.searchDefault(context);
        return sysUserRoles;
    }

    @Override
    @Transactional
    public SysUserRole dynamicCall(String key, String action, SysUserRole et) {
        return et;
    }
}



