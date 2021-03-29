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
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSysReqModule;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSysReqModuleSearchContext;
import cn.ibizlab.pms.core.ibizsysmodel.service.IPSSysReqModuleService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import cn.ibizlab.pms.core.ibizsysmodel.client.PSSysReqModuleFeignClient;
import cn.ibizlab.pms.util.security.SpringContextHolder;
import cn.ibizlab.pms.util.helper.OutsideAccessorUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 实体[系统需求模块] 服务对象接口实现
 */
@Slf4j
@Service
public class PSSysReqModuleServiceImpl implements IPSSysReqModuleService {

//    @Autowired
    PSSysReqModuleFeignClient pSSysReqModuleFeignClient;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.serviceid:}")
    private String serviceName;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.serviceurl:}")
    private String serviceUrl;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.loginname:loginname}")
    private String loginname;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.password:password}")
    private String password;

    public PSSysReqModuleFeignClient getPSSysReqModuleFeignClient(String devSlnSysId) {
        if (StringUtils.isNotBlank(serviceName)) {
            return OutsideAccessorUtils.buildAccessor(SpringContextHolder.getApplicationContext(), PSSysReqModuleFeignClient.class, serviceName, false, serviceName, false, loginname, password, devSlnSysId);
        } else if (StringUtils.isNotBlank(serviceUrl)) {
            return OutsideAccessorUtils.buildAccessorByUrl(SpringContextHolder.getApplicationContext(), PSSysReqModuleFeignClient.class, serviceUrl, false, serviceUrl, false, loginname, password, devSlnSysId);
        } else {
            throw new RuntimeException("缺少平台服务配置信息。");
        }
    }


    @Override
    public boolean create(PSSysReqModule et) {
        PSSysReqModule rt = pSSysReqModuleFeignClient.create(et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    @Override
    public boolean create(String devSlnSysId, PSSysReqModule et) {
        PSSysReqModule rt = getPSSysReqModuleFeignClient(devSlnSysId).create(et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void createBatch(List<PSSysReqModule> list){
        pSSysReqModuleFeignClient.createBatch(list);
    }

    public void createBatch(String devSlnSysId, List<PSSysReqModule> list){
        getPSSysReqModuleFeignClient(devSlnSysId).createBatch(list);
    }

    @Override
    public boolean update(PSSysReqModule et) {
        PSSysReqModule rt = pSSysReqModuleFeignClient.update(et.getPssysreqmoduleid(), et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;

    }

    @Override
    public boolean update(String devSlnSysId, PSSysReqModule et) {
        PSSysReqModule rt = getPSSysReqModuleFeignClient(devSlnSysId).update(et.getPssysreqmoduleid(), et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void updateBatch(List<PSSysReqModule> list) {
        pSSysReqModuleFeignClient.updateBatch(list);
    }

    public void updateBatch(String devSlnSysId, List<PSSysReqModule> list){
        getPSSysReqModuleFeignClient(devSlnSysId).updateBatch(list);
    }

    @Override
    public boolean remove(String pssysreqmoduleid) {
        boolean result=pSSysReqModuleFeignClient.remove(pssysreqmoduleid);
        return result;
    }

    @Override
    public boolean remove(String devSlnSysId, String pssysreqmoduleid) {
        boolean result = getPSSysReqModuleFeignClient(devSlnSysId).remove(pssysreqmoduleid);
        return result;
    }

    public void removeBatch(Collection<String> idList){
        pSSysReqModuleFeignClient.removeBatch(idList);
    }

    public void removeBatch(String devSlnSysId, Collection<String> idList) {
        getPSSysReqModuleFeignClient(devSlnSysId).removeBatch(idList);
    }

    @Override
    public PSSysReqModule get(String pssysreqmoduleid) {
        PSSysReqModule et = pSSysReqModuleFeignClient.get(pssysreqmoduleid);
        if (et == null) {
            et = new PSSysReqModule();
            et.setPssysreqmoduleid(pssysreqmoduleid);
        }
        else {
        }
        return  et;
    }

    @Override
    public PSSysReqModule get(String devSlnSysId, String pssysreqmoduleid) {
        PSSysReqModule et = getPSSysReqModuleFeignClient(devSlnSysId).get(pssysreqmoduleid);
        if (et == null) {
            et = new PSSysReqModule();
            et.setPssysreqmoduleid(pssysreqmoduleid);
        }
        else {
        }
        return et;
    }

    @Override
    public String getByCodeName(String devSlnSysId, String codeName) {
        return getPSSysReqModuleFeignClient(devSlnSysId).getByCodeName(codeName);
    }

    @Override
    public PSSysReqModule getDraft(PSSysReqModule et) {
        et=pSSysReqModuleFeignClient.getDraft(et);
        return et;
    }

    @Override
    public PSSysReqModule getDraft(String devSlnSysId, PSSysReqModule et) {
        et = getPSSysReqModuleFeignClient(devSlnSysId).getDraft(et);
        return et;
    }

    @Override
    public boolean checkKey(PSSysReqModule et) {
        return pSSysReqModuleFeignClient.checkKey(et);
    }

    @Override
    public boolean checkKey(String devSlnSysId, PSSysReqModule et) {
        return getPSSysReqModuleFeignClient(devSlnSysId).checkKey(et);
    }

    @Override
    @Transactional
    public boolean save(PSSysReqModule et) {
        if (et.getPssysreqmoduleid() == null) {
            et.setPssysreqmoduleid((String)et.getDefaultKey(true));
        }
        if (!pSSysReqModuleFeignClient.save(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean save(String devSlnSysId, PSSysReqModule et) {
        if (et.getPssysreqmoduleid() == null) {
            et.setPssysreqmoduleid((String)et.getDefaultKey(true));
        }
        if (!getPSSysReqModuleFeignClient(devSlnSysId).save(et)) {
            return false;
        }
        return true;
    }

    @Override
    public void saveBatch(List<PSSysReqModule> list) {
        pSSysReqModuleFeignClient.saveBatch(list);
    }

    @Override
    public void saveBatch(String devSlnSysId, List<PSSysReqModule> list) {
        getPSSysReqModuleFeignClient(devSlnSysId).saveBatch(list);
    }



    @Override
    public List<PSSysReqModule> selectByPsmoduleid(String psmoduleid) {
        PSSysReqModuleSearchContext context=new PSSysReqModuleSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_psmoduleid_eq(psmoduleid);
        return pSSysReqModuleFeignClient.searchDefault(context).getContent();
    }

    @Override
    public List<PSSysReqModule> selectByPsmoduleid(String devSlnSysId, String psmoduleid) {
        PSSysReqModuleSearchContext context = new PSSysReqModuleSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_psmoduleid_eq(psmoduleid);
        return getPSSysReqModuleFeignClient(devSlnSysId).searchDefault(context).getContent();
    }

    @Override
    public List<PSSysReqModule> selectByPsmoduleid(Collection<String> ids) {
        //暂未支持
        return null;
    }


    @Override
    public void removeByPsmoduleid(String psmoduleid) {
        Set<String> delIds=new HashSet<String>();
        for (PSSysReqModule before:selectByPsmoduleid(psmoduleid)) {
            delIds.add(before.getPssysreqmoduleid());
        }
        if (delIds.size() > 0) {
            this.removeBatch(delIds);
        }
    }

    @Override
    public void removeByPsmoduleid(String devSlnSysId, String psmoduleid) {
        Set<String> delIds = new HashSet<String>();
        for(PSSysReqModule before:selectByPsmoduleid(devSlnSysId, psmoduleid)){
            delIds.add(before.getPssysreqmoduleid());
        }
        if (delIds.size() > 0) {
            this.removeBatch(delIds);
        }
    }

    @Override
    public List<PSSysReqModule> selectByPpssysreqmoduleid(String pssysreqmoduleid) {
        PSSysReqModuleSearchContext context=new PSSysReqModuleSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_ppssysreqmoduleid_eq(pssysreqmoduleid);
        return pSSysReqModuleFeignClient.searchDefault(context).getContent();
    }

    @Override
    public List<PSSysReqModule> selectByPpssysreqmoduleid(String devSlnSysId, String pssysreqmoduleid) {
        PSSysReqModuleSearchContext context = new PSSysReqModuleSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_ppssysreqmoduleid_eq(pssysreqmoduleid);
        return getPSSysReqModuleFeignClient(devSlnSysId).searchDefault(context).getContent();
    }

    @Override
    public List<PSSysReqModule> selectByPpssysreqmoduleid(Collection<String> ids) {
        //暂未支持
        return null;
    }


    @Override
    public void removeByPpssysreqmoduleid(String pssysreqmoduleid) {
        Set<String> delIds=new HashSet<String>();
        for (PSSysReqModule before:selectByPpssysreqmoduleid(pssysreqmoduleid)) {
            delIds.add(before.getPssysreqmoduleid());
        }
        if (delIds.size() > 0) {
            this.removeBatch(delIds);
        }
    }

    @Override
    public void removeByPpssysreqmoduleid(String devSlnSysId, String pssysreqmoduleid) {
        Set<String> delIds = new HashSet<String>();
        for(PSSysReqModule before:selectByPpssysreqmoduleid(devSlnSysId, pssysreqmoduleid)){
            delIds.add(before.getPssysreqmoduleid());
        }
        if (delIds.size() > 0) {
            this.removeBatch(delIds);
        }
    }



    /**
     * 查询集合 数据集
     */
    @Override
    public Page<PSSysReqModule> searchDefault(PSSysReqModuleSearchContext context) {
        Page<PSSysReqModule> pSSysReqModules=pSSysReqModuleFeignClient.searchDefault(context);
        return pSSysReqModules;
    }

    @Override
    public Page<PSSysReqModule> searchDefault(String devSlnSysId, PSSysReqModuleSearchContext context) {
        Page<PSSysReqModule> pSSysReqModules=getPSSysReqModuleFeignClient(devSlnSysId).searchDefault(context);
        return pSSysReqModules;
    }



    @Override
    @Transactional
    public PSSysReqModule dynamicCall(String key, String action, PSSysReqModule et) {
        return et;
    }
}


