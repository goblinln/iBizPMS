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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Lazy;
import cn.ibizlab.pms.core.ou.domain.SysEmployee;
import cn.ibizlab.pms.core.ou.filter.SysEmployeeSearchContext;
import cn.ibizlab.pms.core.ou.service.ISysEmployeeService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import cn.ibizlab.pms.core.ou.client.SysEmployeeFeignClient;
import cn.ibizlab.pms.util.security.SpringContextHolder;
import cn.ibizlab.pms.util.helper.OutsideAccessorUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 实体[人员] 服务对象接口实现
 */
@Slf4j
@Service
public class SysEmployeeServiceImpl implements ISysEmployeeService {

    @Autowired
    SysEmployeeFeignClient sysEmployeeFeignClient;
    


    @Override
    public boolean create(SysEmployee et) {
        SysEmployee rt = sysEmployeeFeignClient.create(et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt,et);
        return true;
    }


    public void createBatch(List<SysEmployee> list){
        sysEmployeeFeignClient.createBatch(list) ;
    }


    @Override
    public boolean update(SysEmployee et) {
        SysEmployee rt = sysEmployeeFeignClient.update(et.getUserid(),et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt,et);
        return true;

    }


    public void updateBatch(List<SysEmployee> list){
        sysEmployeeFeignClient.updateBatch(list) ;
    }


    @Override
    public boolean remove(String userid) {
        boolean result=sysEmployeeFeignClient.remove(userid) ;
        return result;
    }


    public void removeBatch(Collection<String> idList){
        sysEmployeeFeignClient.removeBatch(idList);
    }


    @Override
    public SysEmployee get(String userid) {
		SysEmployee et=sysEmployeeFeignClient.get(userid);
        if(et==null){
            et=new SysEmployee();
            et.setUserid(userid);
        }
        else{
        }
        return  et;
    }


    @Override
    public SysEmployee getDraft(SysEmployee et) {
        et=sysEmployeeFeignClient.getDraft();
        return et;
    }


    @Override
    public boolean checkKey(SysEmployee et) {
        return sysEmployeeFeignClient.checkKey(et);
    }


    @Override
    @Transactional
    public boolean save(SysEmployee et) {
        if(et.getUserid()==null) et.setUserid((String)et.getDefaultKey(true));
        if(!sysEmployeeFeignClient.save(et))
            return false;
        return true;
    }


    @Override
    public void saveBatch(List<SysEmployee> list) {
        sysEmployeeFeignClient.saveBatch(list) ;
    }




	@Override
    public List<SysEmployee> selectByMdeptid(String deptid) {
        SysEmployeeSearchContext context=new SysEmployeeSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_mdeptid_eq(deptid);
        return sysEmployeeFeignClient.searchDefault(context).getContent();
    }


    @Override
    public void removeByMdeptid(String deptid) {
        Set<String> delIds=new HashSet<String>();
        for(SysEmployee before:selectByMdeptid(deptid)){
            delIds.add(before.getUserid());
        }
        if(delIds.size()>0)
            this.removeBatch(delIds);
    }


	@Override
    public List<SysEmployee> selectByOrgid(String orgid) {
        SysEmployeeSearchContext context=new SysEmployeeSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_orgid_eq(orgid);
        return sysEmployeeFeignClient.searchDefault(context).getContent();
    }


    @Override
    public void removeByOrgid(String orgid) {
        Set<String> delIds=new HashSet<String>();
        for(SysEmployee before:selectByOrgid(orgid)){
            delIds.add(before.getUserid());
        }
        if(delIds.size()>0)
            this.removeBatch(delIds);
    }




    /**
     * 查询集合 Bug用户
     */
    @Override
    public Page<SysEmployee> searchBugUser(SysEmployeeSearchContext context) {
        Page<SysEmployee> sysEmployees=sysEmployeeFeignClient.searchBugUser(context);
        return sysEmployees;
    }


    /**
     * 查询集合 数据集
     */
    @Override
    public Page<SysEmployee> searchDefault(SysEmployeeSearchContext context) {
        Page<SysEmployee> sysEmployees=sysEmployeeFeignClient.searchDefault(context);
        return sysEmployees;
    }


    /**
     * 查询集合 项目团队管理
     */
    @Override
    public Page<SysEmployee> searchProjectTeamM(SysEmployeeSearchContext context) {
        Page<SysEmployee> sysEmployees=sysEmployeeFeignClient.searchProjectTeamM(context);
        return sysEmployees;
    }


    /**
     * 查询集合 项目团队成员
     */
    @Override
    public Page<SysEmployee> searchProjectTeamUser(SysEmployeeSearchContext context) {
        Page<SysEmployee> sysEmployees=sysEmployeeFeignClient.searchProjectTeamUser(context);
        return sysEmployees;
    }


    /**
     * 查询集合 项目团队成员
     */
    @Override
    public Page<SysEmployee> searchProjectTeamUser_Task(SysEmployeeSearchContext context) {
        Page<SysEmployee> sysEmployees=sysEmployeeFeignClient.searchProjectTeamUser_Task(context);
        return sysEmployees;
    }


    /**
     * 查询集合 数据查询2
     */
    @Override
    public Page<SysEmployee> searchTaskTeam(SysEmployeeSearchContext context) {
        Page<SysEmployee> sysEmployees=sysEmployeeFeignClient.searchTaskTeam(context);
        return sysEmployees;
    }




}



