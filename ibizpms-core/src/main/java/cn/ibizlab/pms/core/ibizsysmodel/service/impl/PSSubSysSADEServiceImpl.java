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
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSubSysSADE;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSubSysSADESearchContext;
import cn.ibizlab.pms.core.ibizsysmodel.service.IPSSubSysSADEService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import cn.ibizlab.pms.core.ibizsysmodel.client.PSSubSysSADEFeignClient;
import cn.ibizlab.pms.util.security.SpringContextHolder;
import cn.ibizlab.pms.util.helper.OutsideAccessorUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 实体[外部接口实体] 服务对象接口实现
 */
@Slf4j
@Service
public class PSSubSysSADEServiceImpl implements IPSSubSysSADEService {

//    @Autowired
    PSSubSysSADEFeignClient pSSubSysSADEFeignClient;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.serviceid:}")
    private String serviceName;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.serviceurl:}")
    private String serviceUrl;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.loginname:loginname}")
    private String loginname;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.password:password}")
    private String password;

    public PSSubSysSADEFeignClient getPSSubSysSADEFeignClient(String devSlnSysId) {
        if (StringUtils.isNotBlank(serviceName)) {
            return OutsideAccessorUtils.buildAccessor(SpringContextHolder.getApplicationContext(), PSSubSysSADEFeignClient.class, serviceName, false, serviceName, false, loginname, password, devSlnSysId);
        } else if (StringUtils.isNotBlank(serviceUrl)) {
            return OutsideAccessorUtils.buildAccessorByUrl(SpringContextHolder.getApplicationContext(), PSSubSysSADEFeignClient.class, serviceUrl, false, serviceUrl, false, loginname, password, devSlnSysId);
        } else {
            throw new RuntimeException("缺少平台服务配置信息。");
        }
    }


    @Override
    public boolean create(PSSubSysSADE et) {
        PSSubSysSADE rt = pSSubSysSADEFeignClient.create(et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    @Override
    public boolean create(String devSlnSysId, PSSubSysSADE et) {
        PSSubSysSADE rt = getPSSubSysSADEFeignClient(devSlnSysId).create(et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void createBatch(List<PSSubSysSADE> list){
        pSSubSysSADEFeignClient.createBatch(list);
    }

    public void createBatch(String devSlnSysId, List<PSSubSysSADE> list){
        getPSSubSysSADEFeignClient(devSlnSysId).createBatch(list);
    }

    @Override
    public boolean update(PSSubSysSADE et) {
        PSSubSysSADE rt = pSSubSysSADEFeignClient.update(et.getPssubsyssadeid(), et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;

    }

    @Override
    public boolean update(String devSlnSysId, PSSubSysSADE et) {
        PSSubSysSADE rt = getPSSubSysSADEFeignClient(devSlnSysId).update(et.getPssubsyssadeid(), et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void updateBatch(List<PSSubSysSADE> list) {
        pSSubSysSADEFeignClient.updateBatch(list);
    }

    public void updateBatch(String devSlnSysId, List<PSSubSysSADE> list){
        getPSSubSysSADEFeignClient(devSlnSysId).updateBatch(list);
    }

    @Override
    public boolean remove(String pssubsyssadeid) {
        boolean result=pSSubSysSADEFeignClient.remove(pssubsyssadeid);
        return result;
    }

    @Override
    public boolean remove(String devSlnSysId, String pssubsyssadeid) {
        boolean result = getPSSubSysSADEFeignClient(devSlnSysId).remove(pssubsyssadeid);
        return result;
    }

    public void removeBatch(Collection<String> idList){
        pSSubSysSADEFeignClient.removeBatch(idList);
    }

    public void removeBatch(String devSlnSysId, Collection<String> idList) {
        getPSSubSysSADEFeignClient(devSlnSysId).removeBatch(idList);
    }

    @Override
    public PSSubSysSADE get(String pssubsyssadeid) {
        PSSubSysSADE et = pSSubSysSADEFeignClient.get(pssubsyssadeid);
        if (et == null) {
            et = new PSSubSysSADE();
            et.setPssubsyssadeid(pssubsyssadeid);
        }
        else {
        }
        return  et;
    }

    @Override
    public PSSubSysSADE get(String devSlnSysId, String pssubsyssadeid) {
        PSSubSysSADE et = getPSSubSysSADEFeignClient(devSlnSysId).get(pssubsyssadeid);
        if (et == null) {
            et = new PSSubSysSADE();
            et.setPssubsyssadeid(pssubsyssadeid);
        }
        else {
        }
        return et;
    }

    @Override
    public String getByCodeName(String devSlnSysId, String codeName) {
        return getPSSubSysSADEFeignClient(devSlnSysId).getByCodeName(codeName);
    }

    @Override
    public PSSubSysSADE getDraft(PSSubSysSADE et) {
        et=pSSubSysSADEFeignClient.getDraft(et);
        return et;
    }

    @Override
    public PSSubSysSADE getDraft(String devSlnSysId, PSSubSysSADE et) {
        et = getPSSubSysSADEFeignClient(devSlnSysId).getDraft(et);
        return et;
    }

    @Override
    public boolean checkKey(PSSubSysSADE et) {
        return pSSubSysSADEFeignClient.checkKey(et);
    }

    @Override
    public boolean checkKey(String devSlnSysId, PSSubSysSADE et) {
        return getPSSubSysSADEFeignClient(devSlnSysId).checkKey(et);
    }

    @Override
    @Transactional
    public boolean save(PSSubSysSADE et) {
        if (et.getPssubsyssadeid() == null) {
            et.setPssubsyssadeid((String)et.getDefaultKey(true));
        }
        if (!pSSubSysSADEFeignClient.save(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean save(String devSlnSysId, PSSubSysSADE et) {
        if (et.getPssubsyssadeid() == null) {
            et.setPssubsyssadeid((String)et.getDefaultKey(true));
        }
        if (!getPSSubSysSADEFeignClient(devSlnSysId).save(et)) {
            return false;
        }
        return true;
    }

    @Override
    public void saveBatch(List<PSSubSysSADE> list) {
        pSSubSysSADEFeignClient.saveBatch(list);
    }

    @Override
    public void saveBatch(String devSlnSysId, List<PSSubSysSADE> list) {
        getPSSubSysSADEFeignClient(devSlnSysId).saveBatch(list);
    }



    @Override
    public List<PSSubSysSADE> selectByPssubsysserviceapiid(String pssubsysserviceapiid) {
        PSSubSysSADESearchContext context=new PSSubSysSADESearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_pssubsysserviceapiid_eq(pssubsysserviceapiid);
        return pSSubSysSADEFeignClient.searchDefault(context).getContent();
    }

    @Override
    public List<PSSubSysSADE> selectByPssubsysserviceapiid(String devSlnSysId, String pssubsysserviceapiid) {
        PSSubSysSADESearchContext context = new PSSubSysSADESearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_pssubsysserviceapiid_eq(pssubsysserviceapiid);
        return getPSSubSysSADEFeignClient(devSlnSysId).searchDefault(context).getContent();
    }

    @Override
    public List<PSSubSysSADE> selectByPssubsysserviceapiid(Collection<String> ids) {
        //暂未支持
        return null;
    }


    @Override
    public void removeByPssubsysserviceapiid(String pssubsysserviceapiid) {
        Set<String> delIds=new HashSet<String>();
        for (PSSubSysSADE before:selectByPssubsysserviceapiid(pssubsysserviceapiid)) {
            delIds.add(before.getPssubsyssadeid());
        }
        if (delIds.size() > 0) {
            this.removeBatch(delIds);
        }
    }

    @Override
    public void removeByPssubsysserviceapiid(String devSlnSysId, String pssubsysserviceapiid) {
        Set<String> delIds = new HashSet<String>();
        for(PSSubSysSADE before:selectByPssubsysserviceapiid(devSlnSysId, pssubsysserviceapiid)){
            delIds.add(before.getPssubsyssadeid());
        }
        if (delIds.size() > 0) {
            this.removeBatch(delIds);
        }
    }



    /**
     * 查询集合 数据集
     */
    @Override
    public Page<PSSubSysSADE> searchDefault(PSSubSysSADESearchContext context) {
        Page<PSSubSysSADE> pSSubSysSADEs=pSSubSysSADEFeignClient.searchDefault(context);
        return pSSubSysSADEs;
    }

    @Override
    public Page<PSSubSysSADE> searchDefault(String devSlnSysId, PSSubSysSADESearchContext context) {
        Page<PSSubSysSADE> pSSubSysSADEs=getPSSubSysSADEFeignClient(devSlnSysId).searchDefault(context);
        return pSSubSysSADEs;
    }



    @Override
    @Transactional
    public PSSubSysSADE dynamicCall(String key, String action, PSSubSysSADE et) {
        return et;
    }
}


