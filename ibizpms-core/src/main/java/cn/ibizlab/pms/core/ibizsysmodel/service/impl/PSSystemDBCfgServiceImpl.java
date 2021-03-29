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
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSystemDBCfg;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSystemDBCfgSearchContext;
import cn.ibizlab.pms.core.ibizsysmodel.service.IPSSystemDBCfgService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import cn.ibizlab.pms.core.ibizsysmodel.client.PSSystemDBCfgFeignClient;
import cn.ibizlab.pms.util.security.SpringContextHolder;
import cn.ibizlab.pms.util.helper.OutsideAccessorUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 实体[系统数据库] 服务对象接口实现
 */
@Slf4j
@Service
public class PSSystemDBCfgServiceImpl implements IPSSystemDBCfgService {

//    @Autowired
    PSSystemDBCfgFeignClient pSSystemDBCfgFeignClient;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.serviceid:}")
    private String serviceName;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.serviceurl:}")
    private String serviceUrl;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.loginname:loginname}")
    private String loginname;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.password:password}")
    private String password;

    public PSSystemDBCfgFeignClient getPSSystemDBCfgFeignClient(String devSlnSysId) {
        if (StringUtils.isNotBlank(serviceName)) {
            return OutsideAccessorUtils.buildAccessor(SpringContextHolder.getApplicationContext(), PSSystemDBCfgFeignClient.class, serviceName, false, serviceName, false, loginname, password, devSlnSysId);
        } else if (StringUtils.isNotBlank(serviceUrl)) {
            return OutsideAccessorUtils.buildAccessorByUrl(SpringContextHolder.getApplicationContext(), PSSystemDBCfgFeignClient.class, serviceUrl, false, serviceUrl, false, loginname, password, devSlnSysId);
        } else {
            throw new RuntimeException("缺少平台服务配置信息。");
        }
    }


    @Override
    public boolean create(PSSystemDBCfg et) {
        PSSystemDBCfg rt = pSSystemDBCfgFeignClient.create(et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    @Override
    public boolean create(String devSlnSysId, PSSystemDBCfg et) {
        PSSystemDBCfg rt = getPSSystemDBCfgFeignClient(devSlnSysId).create(et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void createBatch(List<PSSystemDBCfg> list){
        pSSystemDBCfgFeignClient.createBatch(list);
    }

    public void createBatch(String devSlnSysId, List<PSSystemDBCfg> list){
        getPSSystemDBCfgFeignClient(devSlnSysId).createBatch(list);
    }

    @Override
    public boolean update(PSSystemDBCfg et) {
        PSSystemDBCfg rt = pSSystemDBCfgFeignClient.update(et.getPssystemdbcfgid(), et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;

    }

    @Override
    public boolean update(String devSlnSysId, PSSystemDBCfg et) {
        PSSystemDBCfg rt = getPSSystemDBCfgFeignClient(devSlnSysId).update(et.getPssystemdbcfgid(), et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void updateBatch(List<PSSystemDBCfg> list) {
        pSSystemDBCfgFeignClient.updateBatch(list);
    }

    public void updateBatch(String devSlnSysId, List<PSSystemDBCfg> list){
        getPSSystemDBCfgFeignClient(devSlnSysId).updateBatch(list);
    }

    @Override
    public boolean remove(String pssystemdbcfgid) {
        boolean result=pSSystemDBCfgFeignClient.remove(pssystemdbcfgid);
        return result;
    }

    @Override
    public boolean remove(String devSlnSysId, String pssystemdbcfgid) {
        boolean result = getPSSystemDBCfgFeignClient(devSlnSysId).remove(pssystemdbcfgid);
        return result;
    }

    public void removeBatch(Collection<String> idList){
        pSSystemDBCfgFeignClient.removeBatch(idList);
    }

    public void removeBatch(String devSlnSysId, Collection<String> idList) {
        getPSSystemDBCfgFeignClient(devSlnSysId).removeBatch(idList);
    }

    @Override
    public PSSystemDBCfg get(String pssystemdbcfgid) {
        PSSystemDBCfg et = pSSystemDBCfgFeignClient.get(pssystemdbcfgid);
        if (et == null) {
            et = new PSSystemDBCfg();
            et.setPssystemdbcfgid(pssystemdbcfgid);
        }
        else {
        }
        return  et;
    }

    @Override
    public PSSystemDBCfg get(String devSlnSysId, String pssystemdbcfgid) {
        PSSystemDBCfg et = getPSSystemDBCfgFeignClient(devSlnSysId).get(pssystemdbcfgid);
        if (et == null) {
            et = new PSSystemDBCfg();
            et.setPssystemdbcfgid(pssystemdbcfgid);
        }
        else {
        }
        return et;
    }

    @Override
    public String getByCodeName(String devSlnSysId, String codeName) {
        return getPSSystemDBCfgFeignClient(devSlnSysId).getByCodeName(codeName);
    }

    @Override
    public PSSystemDBCfg getDraft(PSSystemDBCfg et) {
        et=pSSystemDBCfgFeignClient.getDraft(et);
        return et;
    }

    @Override
    public PSSystemDBCfg getDraft(String devSlnSysId, PSSystemDBCfg et) {
        et = getPSSystemDBCfgFeignClient(devSlnSysId).getDraft(et);
        return et;
    }

    @Override
    public boolean checkKey(PSSystemDBCfg et) {
        return pSSystemDBCfgFeignClient.checkKey(et);
    }

    @Override
    public boolean checkKey(String devSlnSysId, PSSystemDBCfg et) {
        return getPSSystemDBCfgFeignClient(devSlnSysId).checkKey(et);
    }

    @Override
    @Transactional
    public boolean save(PSSystemDBCfg et) {
        if (et.getPssystemdbcfgid() == null) {
            et.setPssystemdbcfgid((String)et.getDefaultKey(true));
        }
        if (!pSSystemDBCfgFeignClient.save(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean save(String devSlnSysId, PSSystemDBCfg et) {
        if (et.getPssystemdbcfgid() == null) {
            et.setPssystemdbcfgid((String)et.getDefaultKey(true));
        }
        if (!getPSSystemDBCfgFeignClient(devSlnSysId).save(et)) {
            return false;
        }
        return true;
    }

    @Override
    public void saveBatch(List<PSSystemDBCfg> list) {
        pSSystemDBCfgFeignClient.saveBatch(list);
    }

    @Override
    public void saveBatch(String devSlnSysId, List<PSSystemDBCfg> list) {
        getPSSystemDBCfgFeignClient(devSlnSysId).saveBatch(list);
    }





    /**
     * 查询集合 版本
     */
    @Override
    public Page<PSSystemDBCfg> searchBuild(PSSystemDBCfgSearchContext context) {
        Page<PSSystemDBCfg> pSSystemDBCfgs=pSSystemDBCfgFeignClient.searchBuild(context);
        return pSSystemDBCfgs;
    }

    @Override
    public Page<PSSystemDBCfg> searchBuild(String devSlnSysId, PSSystemDBCfgSearchContext context) {
        Page<PSSystemDBCfg> pSSystemDBCfgs=getPSSystemDBCfgFeignClient(devSlnSysId).searchBuild(context);
        return pSSystemDBCfgs;
    }

    /**
     * 查询集合 数据集
     */
    @Override
    public Page<PSSystemDBCfg> searchDefault(PSSystemDBCfgSearchContext context) {
        Page<PSSystemDBCfg> pSSystemDBCfgs=pSSystemDBCfgFeignClient.searchDefault(context);
        return pSSystemDBCfgs;
    }

    @Override
    public Page<PSSystemDBCfg> searchDefault(String devSlnSysId, PSSystemDBCfgSearchContext context) {
        Page<PSSystemDBCfg> pSSystemDBCfgs=getPSSystemDBCfgFeignClient(devSlnSysId).searchDefault(context);
        return pSSystemDBCfgs;
    }



    @Override
    @Transactional
    public PSSystemDBCfg dynamicCall(String key, String action, PSSystemDBCfg et) {
        return et;
    }
}


