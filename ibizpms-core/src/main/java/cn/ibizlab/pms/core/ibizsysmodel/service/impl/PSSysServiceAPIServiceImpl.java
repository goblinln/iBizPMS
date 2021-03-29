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
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSysServiceAPI;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSysServiceAPISearchContext;
import cn.ibizlab.pms.core.ibizsysmodel.service.IPSSysServiceAPIService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import cn.ibizlab.pms.core.ibizsysmodel.client.PSSysServiceAPIFeignClient;
import cn.ibizlab.pms.util.security.SpringContextHolder;
import cn.ibizlab.pms.util.helper.OutsideAccessorUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 实体[系统服务接口] 服务对象接口实现
 */
@Slf4j
@Service
public class PSSysServiceAPIServiceImpl implements IPSSysServiceAPIService {

//    @Autowired
    PSSysServiceAPIFeignClient pSSysServiceAPIFeignClient;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.serviceid:}")
    private String serviceName;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.serviceurl:}")
    private String serviceUrl;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.loginname:loginname}")
    private String loginname;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.password:password}")
    private String password;

    public PSSysServiceAPIFeignClient getPSSysServiceAPIFeignClient(String devSlnSysId) {
        if (StringUtils.isNotBlank(serviceName)) {
            return OutsideAccessorUtils.buildAccessor(SpringContextHolder.getApplicationContext(), PSSysServiceAPIFeignClient.class, serviceName, false, serviceName, false, loginname, password, devSlnSysId);
        } else if (StringUtils.isNotBlank(serviceUrl)) {
            return OutsideAccessorUtils.buildAccessorByUrl(SpringContextHolder.getApplicationContext(), PSSysServiceAPIFeignClient.class, serviceUrl, false, serviceUrl, false, loginname, password, devSlnSysId);
        } else {
            throw new RuntimeException("缺少平台服务配置信息。");
        }
    }


    @Override
    public boolean create(PSSysServiceAPI et) {
        PSSysServiceAPI rt = pSSysServiceAPIFeignClient.create(et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    @Override
    public boolean create(String devSlnSysId, PSSysServiceAPI et) {
        PSSysServiceAPI rt = getPSSysServiceAPIFeignClient(devSlnSysId).create(et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void createBatch(List<PSSysServiceAPI> list){
        pSSysServiceAPIFeignClient.createBatch(list);
    }

    public void createBatch(String devSlnSysId, List<PSSysServiceAPI> list){
        getPSSysServiceAPIFeignClient(devSlnSysId).createBatch(list);
    }

    @Override
    public boolean update(PSSysServiceAPI et) {
        PSSysServiceAPI rt = pSSysServiceAPIFeignClient.update(et.getPssysserviceapiid(), et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;

    }

    @Override
    public boolean update(String devSlnSysId, PSSysServiceAPI et) {
        PSSysServiceAPI rt = getPSSysServiceAPIFeignClient(devSlnSysId).update(et.getPssysserviceapiid(), et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void updateBatch(List<PSSysServiceAPI> list) {
        pSSysServiceAPIFeignClient.updateBatch(list);
    }

    public void updateBatch(String devSlnSysId, List<PSSysServiceAPI> list){
        getPSSysServiceAPIFeignClient(devSlnSysId).updateBatch(list);
    }

    @Override
    public boolean remove(String pssysserviceapiid) {
        boolean result=pSSysServiceAPIFeignClient.remove(pssysserviceapiid);
        return result;
    }

    @Override
    public boolean remove(String devSlnSysId, String pssysserviceapiid) {
        boolean result = getPSSysServiceAPIFeignClient(devSlnSysId).remove(pssysserviceapiid);
        return result;
    }

    public void removeBatch(Collection<String> idList){
        pSSysServiceAPIFeignClient.removeBatch(idList);
    }

    public void removeBatch(String devSlnSysId, Collection<String> idList) {
        getPSSysServiceAPIFeignClient(devSlnSysId).removeBatch(idList);
    }

    @Override
    public PSSysServiceAPI get(String pssysserviceapiid) {
        PSSysServiceAPI et = pSSysServiceAPIFeignClient.get(pssysserviceapiid);
        if (et == null) {
            et = new PSSysServiceAPI();
            et.setPssysserviceapiid(pssysserviceapiid);
        }
        else {
        }
        return  et;
    }

    @Override
    public PSSysServiceAPI get(String devSlnSysId, String pssysserviceapiid) {
        PSSysServiceAPI et = getPSSysServiceAPIFeignClient(devSlnSysId).get(pssysserviceapiid);
        if (et == null) {
            et = new PSSysServiceAPI();
            et.setPssysserviceapiid(pssysserviceapiid);
        }
        else {
        }
        return et;
    }

    @Override
    public String getByCodeName(String devSlnSysId, String codeName) {
        return getPSSysServiceAPIFeignClient(devSlnSysId).getByCodeName(codeName);
    }

    @Override
    public PSSysServiceAPI getDraft(PSSysServiceAPI et) {
        et=pSSysServiceAPIFeignClient.getDraft(et);
        return et;
    }

    @Override
    public PSSysServiceAPI getDraft(String devSlnSysId, PSSysServiceAPI et) {
        et = getPSSysServiceAPIFeignClient(devSlnSysId).getDraft(et);
        return et;
    }

    @Override
    public boolean checkKey(PSSysServiceAPI et) {
        return pSSysServiceAPIFeignClient.checkKey(et);
    }

    @Override
    public boolean checkKey(String devSlnSysId, PSSysServiceAPI et) {
        return getPSSysServiceAPIFeignClient(devSlnSysId).checkKey(et);
    }

    @Override
    @Transactional
    public boolean save(PSSysServiceAPI et) {
        if (et.getPssysserviceapiid() == null) {
            et.setPssysserviceapiid((String)et.getDefaultKey(true));
        }
        if (!pSSysServiceAPIFeignClient.save(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean save(String devSlnSysId, PSSysServiceAPI et) {
        if (et.getPssysserviceapiid() == null) {
            et.setPssysserviceapiid((String)et.getDefaultKey(true));
        }
        if (!getPSSysServiceAPIFeignClient(devSlnSysId).save(et)) {
            return false;
        }
        return true;
    }

    @Override
    public void saveBatch(List<PSSysServiceAPI> list) {
        pSSysServiceAPIFeignClient.saveBatch(list);
    }

    @Override
    public void saveBatch(String devSlnSysId, List<PSSysServiceAPI> list) {
        getPSSysServiceAPIFeignClient(devSlnSysId).saveBatch(list);
    }



    @Override
    public List<PSSysServiceAPI> selectByPsmoduleid(String psmoduleid) {
        PSSysServiceAPISearchContext context=new PSSysServiceAPISearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_psmoduleid_eq(psmoduleid);
        return pSSysServiceAPIFeignClient.searchDefault(context).getContent();
    }

    @Override
    public List<PSSysServiceAPI> selectByPsmoduleid(String devSlnSysId, String psmoduleid) {
        PSSysServiceAPISearchContext context = new PSSysServiceAPISearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_psmoduleid_eq(psmoduleid);
        return getPSSysServiceAPIFeignClient(devSlnSysId).searchDefault(context).getContent();
    }

    @Override
    public List<PSSysServiceAPI> selectByPsmoduleid(Collection<String> ids) {
        //暂未支持
        return null;
    }


    @Override
    public void removeByPsmoduleid(String psmoduleid) {
        Set<String> delIds=new HashSet<String>();
        for (PSSysServiceAPI before:selectByPsmoduleid(psmoduleid)) {
            delIds.add(before.getPssysserviceapiid());
        }
        if (delIds.size() > 0) {
            this.removeBatch(delIds);
        }
    }

    @Override
    public void removeByPsmoduleid(String devSlnSysId, String psmoduleid) {
        Set<String> delIds = new HashSet<String>();
        for(PSSysServiceAPI before:selectByPsmoduleid(devSlnSysId, psmoduleid)){
            delIds.add(before.getPssysserviceapiid());
        }
        if (delIds.size() > 0) {
            this.removeBatch(delIds);
        }
    }



    /**
     * 查询集合 数据集
     */
    @Override
    public Page<PSSysServiceAPI> searchDefault(PSSysServiceAPISearchContext context) {
        Page<PSSysServiceAPI> pSSysServiceAPIs=pSSysServiceAPIFeignClient.searchDefault(context);
        return pSSysServiceAPIs;
    }

    @Override
    public Page<PSSysServiceAPI> searchDefault(String devSlnSysId, PSSysServiceAPISearchContext context) {
        Page<PSSysServiceAPI> pSSysServiceAPIs=getPSSysServiceAPIFeignClient(devSlnSysId).searchDefault(context);
        return pSSysServiceAPIs;
    }



    @Override
    @Transactional
    public PSSysServiceAPI dynamicCall(String key, String action, PSSysServiceAPI et) {
        return et;
    }
}


