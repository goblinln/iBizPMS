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
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSysRunSession;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSysRunSessionSearchContext;
import cn.ibizlab.pms.core.ibizsysmodel.service.IPSSysRunSessionService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import cn.ibizlab.pms.core.ibizsysmodel.client.PSSysRunSessionFeignClient;
import cn.ibizlab.pms.util.security.SpringContextHolder;
import cn.ibizlab.pms.util.helper.OutsideAccessorUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 实体[系统运行会话] 服务对象接口实现
 */
@Slf4j
@Service
public class PSSysRunSessionServiceImpl implements IPSSysRunSessionService {

//    @Autowired
    PSSysRunSessionFeignClient pSSysRunSessionFeignClient;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.serviceid:}")
    private String serviceName;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.serviceurl:}")
    private String serviceUrl;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.loginname:loginname}")
    private String loginname;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.password:password}")
    private String password;

    public PSSysRunSessionFeignClient getPSSysRunSessionFeignClient(String devSlnSysId) {
        if (StringUtils.isNotBlank(serviceName)) {
            return OutsideAccessorUtils.buildAccessor(SpringContextHolder.getApplicationContext(), PSSysRunSessionFeignClient.class, serviceName, false, serviceName, false, loginname, password, devSlnSysId);
        } else if (StringUtils.isNotBlank(serviceUrl)) {
            return OutsideAccessorUtils.buildAccessorByUrl(SpringContextHolder.getApplicationContext(), PSSysRunSessionFeignClient.class, serviceUrl, false, serviceUrl, false, loginname, password, devSlnSysId);
        } else {
            throw new RuntimeException("缺少平台服务配置信息。");
        }
    }


    @Override
    public boolean create(PSSysRunSession et) {
        PSSysRunSession rt = pSSysRunSessionFeignClient.create(et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    @Override
    public boolean create(String devSlnSysId, PSSysRunSession et) {
        PSSysRunSession rt = getPSSysRunSessionFeignClient(devSlnSysId).create(et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void createBatch(List<PSSysRunSession> list){
        pSSysRunSessionFeignClient.createBatch(list);
    }

    public void createBatch(String devSlnSysId, List<PSSysRunSession> list){
        getPSSysRunSessionFeignClient(devSlnSysId).createBatch(list);
    }

    @Override
    public boolean update(PSSysRunSession et) {
        PSSysRunSession rt = pSSysRunSessionFeignClient.update(et.getPssysrunsessionid(), et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;

    }

    @Override
    public boolean update(String devSlnSysId, PSSysRunSession et) {
        PSSysRunSession rt = getPSSysRunSessionFeignClient(devSlnSysId).update(et.getPssysrunsessionid(), et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void updateBatch(List<PSSysRunSession> list) {
        pSSysRunSessionFeignClient.updateBatch(list);
    }

    public void updateBatch(String devSlnSysId, List<PSSysRunSession> list){
        getPSSysRunSessionFeignClient(devSlnSysId).updateBatch(list);
    }

    @Override
    public boolean remove(String pssysrunsessionid) {
        boolean result=pSSysRunSessionFeignClient.remove(pssysrunsessionid);
        return result;
    }

    @Override
    public boolean remove(String devSlnSysId, String pssysrunsessionid) {
        boolean result = getPSSysRunSessionFeignClient(devSlnSysId).remove(pssysrunsessionid);
        return result;
    }

    public void removeBatch(Collection<String> idList){
        pSSysRunSessionFeignClient.removeBatch(idList);
    }

    public void removeBatch(String devSlnSysId, Collection<String> idList) {
        getPSSysRunSessionFeignClient(devSlnSysId).removeBatch(idList);
    }

    @Override
    public PSSysRunSession get(String pssysrunsessionid) {
        PSSysRunSession et = pSSysRunSessionFeignClient.get(pssysrunsessionid);
        if (et == null) {
            et = new PSSysRunSession();
            et.setPssysrunsessionid(pssysrunsessionid);
        }
        else {
        }
        return  et;
    }

    @Override
    public PSSysRunSession get(String devSlnSysId, String pssysrunsessionid) {
        PSSysRunSession et = getPSSysRunSessionFeignClient(devSlnSysId).get(pssysrunsessionid);
        if (et == null) {
            et = new PSSysRunSession();
            et.setPssysrunsessionid(pssysrunsessionid);
        }
        else {
        }
        return et;
    }

    @Override
    public String getByCodeName(String devSlnSysId, String codeName) {
        return getPSSysRunSessionFeignClient(devSlnSysId).getByCodeName(codeName);
    }

    @Override
    public PSSysRunSession getDraft(PSSysRunSession et) {
        et=pSSysRunSessionFeignClient.getDraft(et);
        return et;
    }

    @Override
    public PSSysRunSession getDraft(String devSlnSysId, PSSysRunSession et) {
        et = getPSSysRunSessionFeignClient(devSlnSysId).getDraft(et);
        return et;
    }

    @Override
    public boolean checkKey(PSSysRunSession et) {
        return pSSysRunSessionFeignClient.checkKey(et);
    }

    @Override
    public boolean checkKey(String devSlnSysId, PSSysRunSession et) {
        return getPSSysRunSessionFeignClient(devSlnSysId).checkKey(et);
    }

    @Override
    @Transactional
    public boolean save(PSSysRunSession et) {
        if (et.getPssysrunsessionid() == null) {
            et.setPssysrunsessionid((String)et.getDefaultKey(true));
        }
        if (!pSSysRunSessionFeignClient.save(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean save(String devSlnSysId, PSSysRunSession et) {
        if (et.getPssysrunsessionid() == null) {
            et.setPssysrunsessionid((String)et.getDefaultKey(true));
        }
        if (!getPSSysRunSessionFeignClient(devSlnSysId).save(et)) {
            return false;
        }
        return true;
    }

    @Override
    public void saveBatch(List<PSSysRunSession> list) {
        pSSysRunSessionFeignClient.saveBatch(list);
    }

    @Override
    public void saveBatch(String devSlnSysId, List<PSSysRunSession> list) {
        getPSSysRunSessionFeignClient(devSlnSysId).saveBatch(list);
    }



    @Override
    public List<PSSysRunSession> selectByPssysappid(String pssysappid) {
        PSSysRunSessionSearchContext context=new PSSysRunSessionSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_pssysappid_eq(pssysappid);
        return pSSysRunSessionFeignClient.searchDefault(context).getContent();
    }

    @Override
    public List<PSSysRunSession> selectByPssysappid(String devSlnSysId, String pssysappid) {
        PSSysRunSessionSearchContext context = new PSSysRunSessionSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_pssysappid_eq(pssysappid);
        return getPSSysRunSessionFeignClient(devSlnSysId).searchDefault(context).getContent();
    }


    @Override
    public void removeByPssysappid(String pssysappid) {
        Set<String> delIds=new HashSet<String>();
        for (PSSysRunSession before:selectByPssysappid(pssysappid)) {
            delIds.add(before.getPssysrunsessionid());
        }
        if (delIds.size() > 0) {
            this.removeBatch(delIds);
        }
    }

    @Override
    public void removeByPssysappid(String devSlnSysId, String pssysappid) {
        Set<String> delIds = new HashSet<String>();
        for(PSSysRunSession before:selectByPssysappid(devSlnSysId, pssysappid)){
            delIds.add(before.getPssysrunsessionid());
        }
        if (delIds.size() > 0) {
            this.removeBatch(delIds);
        }
    }

    @Override
    public List<PSSysRunSession> selectByPssysappid2(String pssysappid) {
        PSSysRunSessionSearchContext context=new PSSysRunSessionSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_pssysappid2_eq(pssysappid);
        return pSSysRunSessionFeignClient.searchDefault(context).getContent();
    }

    @Override
    public List<PSSysRunSession> selectByPssysappid2(String devSlnSysId, String pssysappid) {
        PSSysRunSessionSearchContext context = new PSSysRunSessionSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_pssysappid2_eq(pssysappid);
        return getPSSysRunSessionFeignClient(devSlnSysId).searchDefault(context).getContent();
    }


    @Override
    public void removeByPssysappid2(String pssysappid) {
        Set<String> delIds=new HashSet<String>();
        for (PSSysRunSession before:selectByPssysappid2(pssysappid)) {
            delIds.add(before.getPssysrunsessionid());
        }
        if (delIds.size() > 0) {
            this.removeBatch(delIds);
        }
    }

    @Override
    public void removeByPssysappid2(String devSlnSysId, String pssysappid) {
        Set<String> delIds = new HashSet<String>();
        for(PSSysRunSession before:selectByPssysappid2(devSlnSysId, pssysappid)){
            delIds.add(before.getPssysrunsessionid());
        }
        if (delIds.size() > 0) {
            this.removeBatch(delIds);
        }
    }

    @Override
    public List<PSSysRunSession> selectByPssysserviceapiid(String pssysserviceapiid) {
        PSSysRunSessionSearchContext context=new PSSysRunSessionSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_pssysserviceapiid_eq(pssysserviceapiid);
        return pSSysRunSessionFeignClient.searchDefault(context).getContent();
    }

    @Override
    public List<PSSysRunSession> selectByPssysserviceapiid(String devSlnSysId, String pssysserviceapiid) {
        PSSysRunSessionSearchContext context = new PSSysRunSessionSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_pssysserviceapiid_eq(pssysserviceapiid);
        return getPSSysRunSessionFeignClient(devSlnSysId).searchDefault(context).getContent();
    }


    @Override
    public void removeByPssysserviceapiid(String pssysserviceapiid) {
        Set<String> delIds=new HashSet<String>();
        for (PSSysRunSession before:selectByPssysserviceapiid(pssysserviceapiid)) {
            delIds.add(before.getPssysrunsessionid());
        }
        if (delIds.size() > 0) {
            this.removeBatch(delIds);
        }
    }

    @Override
    public void removeByPssysserviceapiid(String devSlnSysId, String pssysserviceapiid) {
        Set<String> delIds = new HashSet<String>();
        for(PSSysRunSession before:selectByPssysserviceapiid(devSlnSysId, pssysserviceapiid)){
            delIds.add(before.getPssysrunsessionid());
        }
        if (delIds.size() > 0) {
            this.removeBatch(delIds);
        }
    }



    /**
     * 查询集合 数据集
     */
    @Override
    public Page<PSSysRunSession> searchDefault(PSSysRunSessionSearchContext context) {
        Page<PSSysRunSession> pSSysRunSessions=pSSysRunSessionFeignClient.searchDefault(context);
        return pSSysRunSessions;
    }

    @Override
    public Page<PSSysRunSession> searchDefault(String devSlnSysId, PSSysRunSessionSearchContext context) {
        Page<PSSysRunSession> pSSysRunSessions=getPSSysRunSessionFeignClient(devSlnSysId).searchDefault(context);
        return pSSysRunSessions;
    }



    @Override
    @Transactional
    public PSSysRunSession dynamicCall(String key, String action, PSSysRunSession et) {
        return et;
    }
}


