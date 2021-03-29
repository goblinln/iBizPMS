package cn.ibizlab.pms.core.ibizsysmodel.service.impl;

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
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSDataEntity;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSDataEntitySearchContext;
import cn.ibizlab.pms.core.ibizsysmodel.service.IPSDataEntityService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import cn.ibizlab.pms.core.ibizsysmodel.client.PSDataEntityFeignClient;
import cn.ibizlab.pms.util.security.SpringContextHolder;
import cn.ibizlab.pms.util.helper.OutsideAccessorUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 实体[实体] 服务对象接口实现
 */
@Slf4j
@Service
public class PSDataEntityServiceImpl implements IPSDataEntityService {

//    @Autowired
    PSDataEntityFeignClient pSDataEntityFeignClient;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.serviceid:}")
    private String serviceName;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.serviceurl:}")
    private String serviceUrl;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.loginname:loginname}")
    private String loginname;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.password:password}")
    private String password;

    public PSDataEntityFeignClient getPSDataEntityFeignClient(String devSlnSysId) {
        if (StringUtils.isNotBlank(serviceName)) {
            return OutsideAccessorUtils.buildAccessor(SpringContextHolder.getApplicationContext(), PSDataEntityFeignClient.class, serviceName, false, serviceName, false, loginname, password, devSlnSysId);
        } else if (StringUtils.isNotBlank(serviceUrl)) {
            return OutsideAccessorUtils.buildAccessorByUrl(SpringContextHolder.getApplicationContext(), PSDataEntityFeignClient.class, serviceUrl, false, serviceUrl, false, loginname, password, devSlnSysId);
        } else {
            throw new RuntimeException("缺少平台服务配置信息。");
        }
    }


    @Override
    public boolean create(PSDataEntity et) {
        PSDataEntity rt = pSDataEntityFeignClient.create(et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    @Override
    public boolean create(String devSlnSysId, PSDataEntity et) {
        PSDataEntity rt = getPSDataEntityFeignClient(devSlnSysId).create(et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void createBatch(List<PSDataEntity> list){
        pSDataEntityFeignClient.createBatch(list);
    }

    public void createBatch(String devSlnSysId, List<PSDataEntity> list){
        getPSDataEntityFeignClient(devSlnSysId).createBatch(list);
    }

    @Override
    public boolean update(PSDataEntity et) {
        PSDataEntity rt = pSDataEntityFeignClient.update(et.getPsdataentityid(), et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;

    }

    @Override
    public boolean update(String devSlnSysId, PSDataEntity et) {
        PSDataEntity rt = getPSDataEntityFeignClient(devSlnSysId).update(et.getPsdataentityid(), et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void updateBatch(List<PSDataEntity> list) {
        pSDataEntityFeignClient.updateBatch(list);
    }

    public void updateBatch(String devSlnSysId, List<PSDataEntity> list){
        getPSDataEntityFeignClient(devSlnSysId).updateBatch(list);
    }

    @Override
    public boolean remove(String psdataentityid) {
        boolean result=pSDataEntityFeignClient.remove(psdataentityid);
        return result;
    }

    @Override
    public boolean remove(String devSlnSysId, String psdataentityid) {
        boolean result = getPSDataEntityFeignClient(devSlnSysId).remove(psdataentityid);
        return result;
    }

    public void removeBatch(Collection<String> idList){
        pSDataEntityFeignClient.removeBatch(idList);
    }

    public void removeBatch(String devSlnSysId, Collection<String> idList) {
        getPSDataEntityFeignClient(devSlnSysId).removeBatch(idList);
    }

    @Override
    public PSDataEntity get(String psdataentityid) {
        PSDataEntity et = pSDataEntityFeignClient.get(psdataentityid);
        if (et == null) {
            et = new PSDataEntity();
            et.setPsdataentityid(psdataentityid);
        }
        else {
        }
        return  et;
    }

    @Override
    public PSDataEntity get(String devSlnSysId, String psdataentityid) {
        PSDataEntity et = getPSDataEntityFeignClient(devSlnSysId).get(psdataentityid);
        if (et == null) {
            et = new PSDataEntity();
            et.setPsdataentityid(psdataentityid);
        }
        else {
        }
        return et;
    }

    @Override
    public String getByCodeName(String devSlnSysId, String codeName) {
        return getPSDataEntityFeignClient(devSlnSysId).getByCodeName(codeName);
    }

    @Override
    public PSDataEntity getDraft(PSDataEntity et) {
        et=pSDataEntityFeignClient.getDraft(et);
        return et;
    }

    @Override
    public PSDataEntity getDraft(String devSlnSysId, PSDataEntity et) {
        et = getPSDataEntityFeignClient(devSlnSysId).getDraft(et);
        return et;
    }

    @Override
    public boolean checkKey(PSDataEntity et) {
        return pSDataEntityFeignClient.checkKey(et);
    }

    @Override
    public boolean checkKey(String devSlnSysId, PSDataEntity et) {
        return getPSDataEntityFeignClient(devSlnSysId).checkKey(et);
    }

    @Override
    @Transactional
    public boolean save(PSDataEntity et) {
        if (et.getPsdataentityid() == null) {
            et.setPsdataentityid((String)et.getDefaultKey(true));
        }
        if (!pSDataEntityFeignClient.save(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean save(String devSlnSysId, PSDataEntity et) {
        if (et.getPsdataentityid() == null) {
            et.setPsdataentityid((String)et.getDefaultKey(true));
        }
        if (!getPSDataEntityFeignClient(devSlnSysId).save(et)) {
            return false;
        }
        return true;
    }

    @Override
    public void saveBatch(List<PSDataEntity> list) {
        pSDataEntityFeignClient.saveBatch(list);
    }

    @Override
    public void saveBatch(String devSlnSysId, List<PSDataEntity> list) {
        getPSDataEntityFeignClient(devSlnSysId).saveBatch(list);
    }



    @Override
    public List<PSDataEntity> selectByPsmoduleid(String psmoduleid) {
        PSDataEntitySearchContext context=new PSDataEntitySearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_psmoduleid_eq(psmoduleid);
        return pSDataEntityFeignClient.searchDefault(context).getContent();
    }

    @Override
    public List<PSDataEntity> selectByPsmoduleid(String devSlnSysId, String psmoduleid) {
        PSDataEntitySearchContext context = new PSDataEntitySearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_psmoduleid_eq(psmoduleid);
        return getPSDataEntityFeignClient(devSlnSysId).searchDefault(context).getContent();
    }

    @Override
    public List<PSDataEntity> selectByPsmoduleid(Collection<String> ids) {
        //暂未支持
        return null;
    }


    @Override
    public void removeByPsmoduleid(String psmoduleid) {
        Set<String> delIds=new HashSet<String>();
        for (PSDataEntity before:selectByPsmoduleid(psmoduleid)) {
            delIds.add(before.getPsdataentityid());
        }
        if (delIds.size() > 0) {
            this.removeBatch(delIds);
        }
    }

    @Override
    public void removeByPsmoduleid(String devSlnSysId, String psmoduleid) {
        Set<String> delIds = new HashSet<String>();
        for(PSDataEntity before:selectByPsmoduleid(devSlnSysId, psmoduleid)){
            delIds.add(before.getPsdataentityid());
        }
        if (delIds.size() > 0) {
            this.removeBatch(delIds);
        }
    }

    @Override
    public List<PSDataEntity> selectByPssubsyssadeid(String pssubsyssadeid) {
        PSDataEntitySearchContext context=new PSDataEntitySearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_pssubsyssadeid_eq(pssubsyssadeid);
        return pSDataEntityFeignClient.searchDefault(context).getContent();
    }

    @Override
    public List<PSDataEntity> selectByPssubsyssadeid(String devSlnSysId, String pssubsyssadeid) {
        PSDataEntitySearchContext context = new PSDataEntitySearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_pssubsyssadeid_eq(pssubsyssadeid);
        return getPSDataEntityFeignClient(devSlnSysId).searchDefault(context).getContent();
    }

    @Override
    public List<PSDataEntity> selectByPssubsyssadeid(Collection<String> ids) {
        //暂未支持
        return null;
    }


    @Override
    public void removeByPssubsyssadeid(String pssubsyssadeid) {
        Set<String> delIds=new HashSet<String>();
        for (PSDataEntity before:selectByPssubsyssadeid(pssubsyssadeid)) {
            delIds.add(before.getPsdataentityid());
        }
        if (delIds.size() > 0) {
            this.removeBatch(delIds);
        }
    }

    @Override
    public void removeByPssubsyssadeid(String devSlnSysId, String pssubsyssadeid) {
        Set<String> delIds = new HashSet<String>();
        for(PSDataEntity before:selectByPssubsyssadeid(devSlnSysId, pssubsyssadeid)){
            delIds.add(before.getPsdataentityid());
        }
        if (delIds.size() > 0) {
            this.removeBatch(delIds);
        }
    }

    @Override
    public List<PSDataEntity> selectByPssubsysserviceapiid(String pssubsysserviceapiid) {
        PSDataEntitySearchContext context=new PSDataEntitySearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_pssubsysserviceapiid_eq(pssubsysserviceapiid);
        return pSDataEntityFeignClient.searchDefault(context).getContent();
    }

    @Override
    public List<PSDataEntity> selectByPssubsysserviceapiid(String devSlnSysId, String pssubsysserviceapiid) {
        PSDataEntitySearchContext context = new PSDataEntitySearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_pssubsysserviceapiid_eq(pssubsysserviceapiid);
        return getPSDataEntityFeignClient(devSlnSysId).searchDefault(context).getContent();
    }

    @Override
    public List<PSDataEntity> selectByPssubsysserviceapiid(Collection<String> ids) {
        //暂未支持
        return null;
    }


    @Override
    public void removeByPssubsysserviceapiid(String pssubsysserviceapiid) {
        Set<String> delIds=new HashSet<String>();
        for (PSDataEntity before:selectByPssubsysserviceapiid(pssubsysserviceapiid)) {
            delIds.add(before.getPsdataentityid());
        }
        if (delIds.size() > 0) {
            this.removeBatch(delIds);
        }
    }

    @Override
    public void removeByPssubsysserviceapiid(String devSlnSysId, String pssubsysserviceapiid) {
        Set<String> delIds = new HashSet<String>();
        for(PSDataEntity before:selectByPssubsysserviceapiid(devSlnSysId, pssubsysserviceapiid)){
            delIds.add(before.getPsdataentityid());
        }
        if (delIds.size() > 0) {
            this.removeBatch(delIds);
        }
    }

    @Override
    public List<PSDataEntity> selectByPssysreqitemid(String pssysreqitemid) {
        PSDataEntitySearchContext context=new PSDataEntitySearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_pssysreqitemid_eq(pssysreqitemid);
        return pSDataEntityFeignClient.searchDefault(context).getContent();
    }

    @Override
    public List<PSDataEntity> selectByPssysreqitemid(String devSlnSysId, String pssysreqitemid) {
        PSDataEntitySearchContext context = new PSDataEntitySearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_pssysreqitemid_eq(pssysreqitemid);
        return getPSDataEntityFeignClient(devSlnSysId).searchDefault(context).getContent();
    }

    @Override
    public List<PSDataEntity> selectByPssysreqitemid(Collection<String> ids) {
        //暂未支持
        return null;
    }


    @Override
    public void removeByPssysreqitemid(String pssysreqitemid) {
        Set<String> delIds=new HashSet<String>();
        for (PSDataEntity before:selectByPssysreqitemid(pssysreqitemid)) {
            delIds.add(before.getPsdataentityid());
        }
        if (delIds.size() > 0) {
            this.removeBatch(delIds);
        }
    }

    @Override
    public void removeByPssysreqitemid(String devSlnSysId, String pssysreqitemid) {
        Set<String> delIds = new HashSet<String>();
        for(PSDataEntity before:selectByPssysreqitemid(devSlnSysId, pssysreqitemid)){
            delIds.add(before.getPsdataentityid());
        }
        if (delIds.size() > 0) {
            this.removeBatch(delIds);
        }
    }



    /**
     * 查询集合 数据集
     */
    @Override
    public Page<PSDataEntity> searchDefault(PSDataEntitySearchContext context) {
        Page<PSDataEntity> pSDataEntitys=pSDataEntityFeignClient.searchDefault(context);
        return pSDataEntitys;
    }

    @Override
    public Page<PSDataEntity> searchDefault(String devSlnSysId, PSDataEntitySearchContext context) {
        Page<PSDataEntity> pSDataEntitys=getPSDataEntityFeignClient(devSlnSysId).searchDefault(context);
        return pSDataEntitys;
    }



    @Override
    @Transactional
    public PSDataEntity dynamicCall(String key, String action, PSDataEntity et) {
        return et;
    }
}


