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
import cn.ibizlab.pms.core.uaa.domain.SysRole;
import cn.ibizlab.pms.core.uaa.filter.SysRoleSearchContext;
import cn.ibizlab.pms.core.uaa.service.ISysRoleService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import cn.ibizlab.pms.core.uaa.client.SysRoleFeignClient;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 实体[系统角色] 服务对象接口实现
 */
@Slf4j
@Service
public class SysRoleServiceImpl implements ISysRoleService {

    @Autowired
    SysRoleFeignClient sysRoleFeignClient;


    @Override
    public boolean create(SysRole et) {
        SysRole rt = sysRoleFeignClient.create(et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void createBatch(List<SysRole> list){
        sysRoleFeignClient.createBatch(list) ;
    }

    @Override
    public boolean update(SysRole et) {
        SysRole rt = sysRoleFeignClient.update(et.getRoleid(),et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt, et);
        return true;

    }

    public void updateBatch(List<SysRole> list){
        sysRoleFeignClient.updateBatch(list) ;
    }

    @Override
    public boolean sysUpdate(SysRole et) {
                                SysRole rt = sysRoleFeignClient.update(et.getRoleid(),et);
        if(rt==null)
            return false;

        CachedBeanCopier.copy(rt, et);
        return true;
    }

    @Override
    public boolean remove(String roleid) {
        boolean result=sysRoleFeignClient.remove(roleid) ;
        return result;
    }

    public void removeBatch(Collection<String> idList){
        sysRoleFeignClient.removeBatch(idList);
    }

    @Override
    public SysRole get(String roleid) {
		SysRole et=sysRoleFeignClient.get(roleid);
        if(et==null){
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), roleid);
        }
        else{
        }
        return  et;
    }

    @Override
    public SysRole sysGet(String roleid) {
         return null;
    }

    @Override
    public SysRole getDraft(SysRole et) {
        et=sysRoleFeignClient.getDraft(et);
        return et;
    }

    @Override
    public boolean checkKey(SysRole et) {
        return sysRoleFeignClient.checkKey(et);
    }
    @Override
    @Transactional
    public boolean save(SysRole et) {
        boolean result = true;
        Object rt = sysRoleFeignClient.saveEntity(et);
        if(rt == null)
          return false;
        try {
            if (rt instanceof Map) {
                ObjectMapper mapper = new ObjectMapper();
                rt = mapper.readValue(mapper.writeValueAsString(rt), SysRole.class);
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
    public void saveBatch(List<SysRole> list) {
        sysRoleFeignClient.saveBatch(list) ;
    }



	@Override
    public List<SysRole> selectByProleid(String roleid) {
        SysRoleSearchContext context=new SysRoleSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_proleid_eq(roleid);
        return sysRoleFeignClient.searchDefault(context).getContent();
    }

    @Override
    public void removeByProleid(String roleid) {
        Set<String> delIds=new HashSet<String>();
        for(SysRole before:selectByProleid(roleid)){
            delIds.add(before.getRoleid());
        }
        if(delIds.size()>0)
            this.removeBatch(delIds);
    }



    /**
     * 查询集合 数据集
     */
    @Override
    public Page<SysRole> searchDefault(SysRoleSearchContext context) {
        Page<SysRole> sysRoles=sysRoleFeignClient.searchDefault(context);
        return sysRoles;
    }

    @Override
    @Transactional
    public SysRole dynamicCall(String key, String action, SysRole et) {
        return et;
    }
}



