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
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSModule;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSModuleSearchContext;
import cn.ibizlab.pms.core.ibizsysmodel.service.IPSModuleService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import cn.ibizlab.pms.core.ibizsysmodel.client.PSModuleFeignClient;
import cn.ibizlab.pms.util.security.SpringContextHolder;
import cn.ibizlab.pms.util.helper.OutsideAccessorUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 实体[系统模块] 服务对象接口实现
 */
@Slf4j
@Service
public class PSModuleServiceImpl implements IPSModuleService {

//    @Autowired
    PSModuleFeignClient pSModuleFeignClient;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.serviceid:}")
    private String serviceName;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.serviceurl:}")
    private String serviceUrl;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.loginname:loginname}")
    private String loginname;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.password:password}")
    private String password;

    public PSModuleFeignClient getPSModuleFeignClient(String devSlnSysId) {
        if (StringUtils.isNotBlank(serviceName)) {
            return OutsideAccessorUtils.buildAccessor(SpringContextHolder.getApplicationContext(), PSModuleFeignClient.class, serviceName, false, serviceName, false, loginname, password, devSlnSysId);
        } else if (StringUtils.isNotBlank(serviceUrl)) {
            return OutsideAccessorUtils.buildAccessorByUrl(SpringContextHolder.getApplicationContext(), PSModuleFeignClient.class, serviceUrl, false, serviceUrl, false, loginname, password, devSlnSysId);
        } else {
            throw new RuntimeException("缺少平台服务配置信息。");
        }
    }


    @Override
    public boolean create(PSModule et) {
        PSModule rt = pSModuleFeignClient.create(et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    @Override
    public boolean create(String devSlnSysId, PSModule et) {
        PSModule rt = getPSModuleFeignClient(devSlnSysId).create(et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void createBatch(List<PSModule> list){
        pSModuleFeignClient.createBatch(list);
    }

    public void createBatch(String devSlnSysId, List<PSModule> list){
        getPSModuleFeignClient(devSlnSysId).createBatch(list);
    }

    @Override
    public boolean update(PSModule et) {
        PSModule rt = pSModuleFeignClient.update(et.getPsmoduleid(), et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;

    }

    @Override
    public boolean update(String devSlnSysId, PSModule et) {
        PSModule rt = getPSModuleFeignClient(devSlnSysId).update(et.getPsmoduleid(), et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void updateBatch(List<PSModule> list) {
        pSModuleFeignClient.updateBatch(list);
    }

    public void updateBatch(String devSlnSysId, List<PSModule> list){
        getPSModuleFeignClient(devSlnSysId).updateBatch(list);
    }

    @Override
    public boolean remove(String psmoduleid) {
        boolean result=pSModuleFeignClient.remove(psmoduleid);
        return result;
    }

    @Override
    public boolean remove(String devSlnSysId, String psmoduleid) {
        boolean result = getPSModuleFeignClient(devSlnSysId).remove(psmoduleid);
        return result;
    }

    public void removeBatch(Collection<String> idList){
        pSModuleFeignClient.removeBatch(idList);
    }

    public void removeBatch(String devSlnSysId, Collection<String> idList) {
        getPSModuleFeignClient(devSlnSysId).removeBatch(idList);
    }

    @Override
    public PSModule get(String psmoduleid) {
        PSModule et = pSModuleFeignClient.get(psmoduleid);
        if (et == null) {
            et = new PSModule();
            et.setPsmoduleid(psmoduleid);
        }
        else {
        }
        return  et;
    }

    @Override
    public PSModule get(String devSlnSysId, String psmoduleid) {
        PSModule et = getPSModuleFeignClient(devSlnSysId).get(psmoduleid);
        if (et == null) {
            et = new PSModule();
            et.setPsmoduleid(psmoduleid);
        }
        else {
        }
        return et;
    }

    @Override
    public String getByCodeName(String devSlnSysId, String codeName) {
        return getPSModuleFeignClient(devSlnSysId).getByCodeName(codeName);
    }

    @Override
    public PSModule getDraft(PSModule et) {
        et=pSModuleFeignClient.getDraft(et);
        return et;
    }

    @Override
    public PSModule getDraft(String devSlnSysId, PSModule et) {
        et = getPSModuleFeignClient(devSlnSysId).getDraft(et);
        return et;
    }

    @Override
    public boolean checkKey(PSModule et) {
        return pSModuleFeignClient.checkKey(et);
    }

    @Override
    public boolean checkKey(String devSlnSysId, PSModule et) {
        return getPSModuleFeignClient(devSlnSysId).checkKey(et);
    }

    @Override
    @Transactional
    public boolean save(PSModule et) {
        if (et.getPsmoduleid() == null) {
            et.setPsmoduleid((String)et.getDefaultKey(true));
        }
        if (!pSModuleFeignClient.save(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean save(String devSlnSysId, PSModule et) {
        if (et.getPsmoduleid() == null) {
            et.setPsmoduleid((String)et.getDefaultKey(true));
        }
        if (!getPSModuleFeignClient(devSlnSysId).save(et)) {
            return false;
        }
        return true;
    }

    @Override
    public void saveBatch(List<PSModule> list) {
        pSModuleFeignClient.saveBatch(list);
    }

    @Override
    public void saveBatch(String devSlnSysId, List<PSModule> list) {
        getPSModuleFeignClient(devSlnSysId).saveBatch(list);
    }





    /**
     * 查询集合 数据集
     */
    @Override
    public Page<PSModule> searchDefault(PSModuleSearchContext context) {
        Page<PSModule> pSModules=pSModuleFeignClient.searchDefault(context);
        return pSModules;
    }

    @Override
    public Page<PSModule> searchDefault(String devSlnSysId, PSModuleSearchContext context) {
        Page<PSModule> pSModules=getPSModuleFeignClient(devSlnSysId).searchDefault(context);
        return pSModules;
    }



    @Override
    @Transactional
    public PSModule dynamicCall(String key, String action, PSModule et) {
        return et;
    }
}


