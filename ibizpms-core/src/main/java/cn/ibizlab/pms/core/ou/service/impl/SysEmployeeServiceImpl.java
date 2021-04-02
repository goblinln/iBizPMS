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
import cn.ibizlab.pms.core.ou.domain.SysEmployee;
import cn.ibizlab.pms.core.ou.filter.SysEmployeeSearchContext;
import cn.ibizlab.pms.core.ou.service.ISysEmployeeService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import cn.ibizlab.pms.core.ou.client.SysEmployeeFeignClient;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        CachedBeanCopier.copy(rt, et);
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
        CachedBeanCopier.copy(rt, et);
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
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), userid);
        }
        else{
        }
        return  et;
    }

    @Override
    public SysEmployee getDraft(SysEmployee et) {
        et=sysEmployeeFeignClient.getDraft(et);
        return et;
    }

    @Override
    public boolean checkKey(SysEmployee et) {
        return sysEmployeeFeignClient.checkKey(et);
    }
    @Override
    @Transactional
    public boolean save(SysEmployee et) {
        boolean result = true;
        Object rt = sysEmployeeFeignClient.saveEntity(et);
        if(rt == null)
          return false;
        try {
            if (rt instanceof Map) {
                ObjectMapper mapper = new ObjectMapper();
                rt = mapper.readValue(mapper.writeValueAsString(rt), SysEmployee.class);
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
    public void resetByMdeptid(String deptid) {
    }

    @Override
    public void resetByMdeptid(Collection<String> ids) {
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
    public void resetByOrgid(String orgid) {
    }

    @Override
    public void resetByOrgid(Collection<String> ids) {
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
     * 查询集合 联系人用户
     */
    @Override
    public Page<SysEmployee> searchContActList(SysEmployeeSearchContext context) {
        Page<SysEmployee> sysEmployees=sysEmployeeFeignClient.searchContActList(context);
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
    public Page<SysEmployee> searchProductTeamM(SysEmployeeSearchContext context) {
        Page<SysEmployee> sysEmployees=sysEmployeeFeignClient.searchProductTeamM(context);
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
     * 查询集合 项目团队管理
     */
    @Override
    public Page<SysEmployee> searchProjectTeamMProduct(SysEmployeeSearchContext context) {
        Page<SysEmployee> sysEmployees=sysEmployeeFeignClient.searchProjectTeamMProduct(context);
        return sysEmployees;
    }

    /**
     * 查询集合 项目团队成员(临时)
     */
    @Override
    public Page<SysEmployee> searchProjectTeamTaskUserTemp(SysEmployeeSearchContext context) {
        Page<SysEmployee> sysEmployees=sysEmployeeFeignClient.searchProjectTeamTaskUserTemp(context);
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
    public Page<SysEmployee> searchProjectTeamUserTask(SysEmployeeSearchContext context) {
        Page<SysEmployee> sysEmployees=sysEmployeeFeignClient.searchProjectTeamUserTask(context);
        return sysEmployees;
    }

    /**
     * 查询集合 项目团队成员选择
     */
    @Override
    public Page<SysEmployee> searchProjectteamPk(SysEmployeeSearchContext context) {
        Page<SysEmployee> sysEmployees=sysEmployeeFeignClient.searchProjectteamPk(context);
        return sysEmployees;
    }

    /**
     * 查询集合 产品团队成员选择
     */
    @Override
    public Page<SysEmployee> searchStoryProductTeamPK(SysEmployeeSearchContext context) {
        Page<SysEmployee> sysEmployees=sysEmployeeFeignClient.searchStoryProductTeamPK(context);
        return sysEmployees;
    }

    /**
     * 查询集合 任务多人团队
     */
    @Override
    public Page<SysEmployee> searchTaskMTeam(SysEmployeeSearchContext context) {
        Page<SysEmployee> sysEmployees=sysEmployeeFeignClient.searchTaskMTeam(context);
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

    @Override
    @Transactional
    public SysEmployee dynamicCall(String key, String action, SysEmployee et) {
        return et;
    }
}



