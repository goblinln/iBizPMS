package cn.ibizlab.pms.core.ou.service.impl;

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
import cn.ibizlab.pms.core.ou.domain.SysTeamMember;
import cn.ibizlab.pms.core.ou.filter.SysTeamMemberSearchContext;
import cn.ibizlab.pms.core.ou.service.ISysTeamMemberService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import cn.ibizlab.pms.core.ou.client.SysTeamMemberFeignClient;
import cn.ibizlab.pms.util.security.SpringContextHolder;
import cn.ibizlab.pms.util.helper.OutsideAccessorUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 实体[组成员] 服务对象接口实现
 */
@Slf4j
@Service
public class SysTeamMemberServiceImpl implements ISysTeamMemberService {

    @Autowired
    SysTeamMemberFeignClient sysTeamMemberFeignClient;



    @Override
    public boolean create(SysTeamMember et) {
        SysTeamMember rt = sysTeamMemberFeignClient.create(et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;
    }


    public void createBatch(List<SysTeamMember> list){
        sysTeamMemberFeignClient.createBatch(list);
    }


    @Override
    public boolean update(SysTeamMember et) {
        SysTeamMember rt = sysTeamMemberFeignClient.update(et.getTeammemberid(), et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;

    }


    public void updateBatch(List<SysTeamMember> list) {
        sysTeamMemberFeignClient.updateBatch(list);
    }


    @Override
    public boolean remove(String teammemberid) {
        boolean result=sysTeamMemberFeignClient.remove(teammemberid);
        return result;
    }


    public void removeBatch(Collection<String> idList){
        sysTeamMemberFeignClient.removeBatch(idList);
    }


    @Override
    public SysTeamMember get(String teammemberid) {
        SysTeamMember et = sysTeamMemberFeignClient.get(teammemberid);
        if (et == null) {
            et = new SysTeamMember();
            et.setTeammemberid(teammemberid);
        }
        else {
        }
        return  et;
    }


    @Override
    public SysTeamMember getDraft(SysTeamMember et) {
        et=sysTeamMemberFeignClient.getDraft(et);
        return et;
    }


    @Override
    public boolean checkKey(SysTeamMember et) {
        return sysTeamMemberFeignClient.checkKey(et);
    }


    @Override
    @Transactional
    public boolean save(SysTeamMember et) {
        if (et.getTeammemberid() == null) {
            et.setTeammemberid((String)et.getDefaultKey(true));
        }
        if (!sysTeamMemberFeignClient.save(et)) {
            return false;
        }
        return true;
    }


    @Override
    public void saveBatch(List<SysTeamMember> list) {
        sysTeamMemberFeignClient.saveBatch(list);
    }




    @Override
    public List<SysTeamMember> selectByUserid(String userid) {
        SysTeamMemberSearchContext context=new SysTeamMemberSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_userid_eq(userid);
        return sysTeamMemberFeignClient.searchDefault(context).getContent();
    }



    @Override
    public void removeByUserid(String userid) {
        Set<String> delIds=new HashSet<String>();
        for (SysTeamMember before:selectByUserid(userid)) {
            delIds.add(before.getTeammemberid());
        }
        if (delIds.size() > 0) {
            this.removeBatch(delIds);
        }
    }


    @Override
    public List<SysTeamMember> selectByPostid(String postid) {
        SysTeamMemberSearchContext context=new SysTeamMemberSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_postid_eq(postid);
        return sysTeamMemberFeignClient.searchDefault(context).getContent();
    }



    @Override
    public void removeByPostid(String postid) {
        Set<String> delIds=new HashSet<String>();
        for (SysTeamMember before:selectByPostid(postid)) {
            delIds.add(before.getTeammemberid());
        }
        if (delIds.size() > 0) {
            this.removeBatch(delIds);
        }
    }


    @Override
    public List<SysTeamMember> selectByTeamid(String teamid) {
        SysTeamMemberSearchContext context=new SysTeamMemberSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_teamid_eq(teamid);
        return sysTeamMemberFeignClient.searchDefault(context).getContent();
    }



    @Override
    public void removeByTeamid(String teamid) {
        Set<String> delIds=new HashSet<String>();
        for (SysTeamMember before:selectByTeamid(teamid)) {
            delIds.add(before.getTeammemberid());
        }
        if (delIds.size() > 0) {
            this.removeBatch(delIds);
        }
    }




    /**
     * 查询集合 数据集
     */
    @Override
    public Page<SysTeamMember> searchDefault(SysTeamMemberSearchContext context) {
        Page<SysTeamMember> sysTeamMembers=sysTeamMemberFeignClient.searchDefault(context);
        return sysTeamMembers;
    }




    @Override
    @Transactional
    public SysTeamMember dynamicCall(String key, String action, SysTeamMember et) {
        return et;
    }
}


