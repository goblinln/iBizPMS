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
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSysSFPub;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSysSFPubSearchContext;
import cn.ibizlab.pms.core.ibizsysmodel.service.IPSSysSFPubService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import cn.ibizlab.pms.core.ibizsysmodel.client.PSSysSFPubFeignClient;
import cn.ibizlab.pms.util.security.SpringContextHolder;
import cn.ibizlab.pms.util.helper.OutsideAccessorUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 实体[后台服务架构] 服务对象接口实现
 */
@Slf4j
@Service
public class PSSysSFPubServiceImpl implements IPSSysSFPubService {

//    @Autowired
    PSSysSFPubFeignClient pSSysSFPubFeignClient;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.serviceid:}")
    private String serviceName;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.serviceurl:}")
    private String serviceUrl;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.loginname:loginname}")
    private String loginname;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.password:password}")
    private String password;

    public PSSysSFPubFeignClient getPSSysSFPubFeignClient(String devSlnSysId) {
        if (StringUtils.isNotBlank(serviceName)) {
            return OutsideAccessorUtils.buildAccessor(SpringContextHolder.getApplicationContext(), PSSysSFPubFeignClient.class, serviceName, false, serviceName, false, loginname, password, devSlnSysId);
        } else if (StringUtils.isNotBlank(serviceUrl)) {
            return OutsideAccessorUtils.buildAccessorByUrl(SpringContextHolder.getApplicationContext(), PSSysSFPubFeignClient.class, serviceUrl, false, serviceUrl, false, loginname, password, devSlnSysId);
        } else {
            throw new RuntimeException("缺少平台服务配置信息。");
        }
    }


    @Override
    public boolean create(PSSysSFPub et) {
        PSSysSFPub rt = pSSysSFPubFeignClient.create(et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    @Override
    public boolean create(String devSlnSysId, PSSysSFPub et) {
        PSSysSFPub rt = getPSSysSFPubFeignClient(devSlnSysId).create(et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void createBatch(List<PSSysSFPub> list){
        pSSysSFPubFeignClient.createBatch(list);
    }

    public void createBatch(String devSlnSysId, List<PSSysSFPub> list){
        getPSSysSFPubFeignClient(devSlnSysId).createBatch(list);
    }

    @Override
    public boolean update(PSSysSFPub et) {
        PSSysSFPub rt = pSSysSFPubFeignClient.update(et.getPssyssfpubid(), et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;

    }

    @Override
    public boolean update(String devSlnSysId, PSSysSFPub et) {
        PSSysSFPub rt = getPSSysSFPubFeignClient(devSlnSysId).update(et.getPssyssfpubid(), et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void updateBatch(List<PSSysSFPub> list) {
        pSSysSFPubFeignClient.updateBatch(list);
    }

    public void updateBatch(String devSlnSysId, List<PSSysSFPub> list){
        getPSSysSFPubFeignClient(devSlnSysId).updateBatch(list);
    }

    @Override
    public boolean remove(String pssyssfpubid) {
        boolean result=pSSysSFPubFeignClient.remove(pssyssfpubid);
        return result;
    }

    @Override
    public boolean remove(String devSlnSysId, String pssyssfpubid) {
        boolean result = getPSSysSFPubFeignClient(devSlnSysId).remove(pssyssfpubid);
        return result;
    }

    public void removeBatch(Collection<String> idList){
        pSSysSFPubFeignClient.removeBatch(idList);
    }

    public void removeBatch(String devSlnSysId, Collection<String> idList) {
        getPSSysSFPubFeignClient(devSlnSysId).removeBatch(idList);
    }

    @Override
    public PSSysSFPub get(String pssyssfpubid) {
        PSSysSFPub et = pSSysSFPubFeignClient.get(pssyssfpubid);
        if (et == null) {
            et = new PSSysSFPub();
            et.setPssyssfpubid(pssyssfpubid);
        }
        else {
        }
        return  et;
    }

    @Override
    public PSSysSFPub get(String devSlnSysId, String pssyssfpubid) {
        PSSysSFPub et = getPSSysSFPubFeignClient(devSlnSysId).get(pssyssfpubid);
        if (et == null) {
            et = new PSSysSFPub();
            et.setPssyssfpubid(pssyssfpubid);
        }
        else {
        }
        return et;
    }

    @Override
    public String getByCodeName(String devSlnSysId, String codeName) {
        return getPSSysSFPubFeignClient(devSlnSysId).getByCodeName(codeName);
    }

    @Override
    public PSSysSFPub getDraft(PSSysSFPub et) {
        et=pSSysSFPubFeignClient.getDraft(et);
        return et;
    }

    @Override
    public PSSysSFPub getDraft(String devSlnSysId, PSSysSFPub et) {
        et = getPSSysSFPubFeignClient(devSlnSysId).getDraft(et);
        return et;
    }

    @Override
    public boolean checkKey(PSSysSFPub et) {
        return pSSysSFPubFeignClient.checkKey(et);
    }

    @Override
    public boolean checkKey(String devSlnSysId, PSSysSFPub et) {
        return getPSSysSFPubFeignClient(devSlnSysId).checkKey(et);
    }

    @Override
    @Transactional
    public boolean save(PSSysSFPub et) {
        if (et.getPssyssfpubid() == null) {
            et.setPssyssfpubid((String)et.getDefaultKey(true));
        }
        if (!pSSysSFPubFeignClient.save(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean save(String devSlnSysId, PSSysSFPub et) {
        if (et.getPssyssfpubid() == null) {
            et.setPssyssfpubid((String)et.getDefaultKey(true));
        }
        if (!getPSSysSFPubFeignClient(devSlnSysId).save(et)) {
            return false;
        }
        return true;
    }

    @Override
    public void saveBatch(List<PSSysSFPub> list) {
        pSSysSFPubFeignClient.saveBatch(list);
    }

    @Override
    public void saveBatch(String devSlnSysId, List<PSSysSFPub> list) {
        getPSSysSFPubFeignClient(devSlnSysId).saveBatch(list);
    }



    @Override
    public List<PSSysSFPub> selectByPpssyssfpubid(String pssyssfpubid) {
        PSSysSFPubSearchContext context=new PSSysSFPubSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_ppssyssfpubid_eq(pssyssfpubid);
        return pSSysSFPubFeignClient.searchDefault(context).getContent();
    }

    @Override
    public List<PSSysSFPub> selectByPpssyssfpubid(String devSlnSysId, String pssyssfpubid) {
        PSSysSFPubSearchContext context = new PSSysSFPubSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_ppssyssfpubid_eq(pssyssfpubid);
        return getPSSysSFPubFeignClient(devSlnSysId).searchDefault(context).getContent();
    }

    @Override
    public List<PSSysSFPub> selectByPpssyssfpubid(Collection<String> ids) {
        //暂未支持
        return null;
    }


    @Override
    public void removeByPpssyssfpubid(String pssyssfpubid) {
        Set<String> delIds=new HashSet<String>();
        for (PSSysSFPub before:selectByPpssyssfpubid(pssyssfpubid)) {
            delIds.add(before.getPssyssfpubid());
        }
        if (delIds.size() > 0) {
            this.removeBatch(delIds);
        }
    }

    @Override
    public void removeByPpssyssfpubid(String devSlnSysId, String pssyssfpubid) {
        Set<String> delIds = new HashSet<String>();
        for(PSSysSFPub before:selectByPpssyssfpubid(devSlnSysId, pssyssfpubid)){
            delIds.add(before.getPssyssfpubid());
        }
        if (delIds.size() > 0) {
            this.removeBatch(delIds);
        }
    }



    /**
     * 查询集合 版本
     */
    @Override
    public Page<PSSysSFPub> searchBuild(PSSysSFPubSearchContext context) {
        Page<PSSysSFPub> pSSysSFPubs=pSSysSFPubFeignClient.searchBuild(context);
        return pSSysSFPubs;
    }

    @Override
    public Page<PSSysSFPub> searchBuild(String devSlnSysId, PSSysSFPubSearchContext context) {
        Page<PSSysSFPub> pSSysSFPubs=getPSSysSFPubFeignClient(devSlnSysId).searchBuild(context);
        return pSSysSFPubs;
    }

    /**
     * 查询集合 数据集
     */
    @Override
    public Page<PSSysSFPub> searchDefault(PSSysSFPubSearchContext context) {
        Page<PSSysSFPub> pSSysSFPubs=pSSysSFPubFeignClient.searchDefault(context);
        return pSSysSFPubs;
    }

    @Override
    public Page<PSSysSFPub> searchDefault(String devSlnSysId, PSSysSFPubSearchContext context) {
        Page<PSSysSFPub> pSSysSFPubs=getPSSysSFPubFeignClient(devSlnSysId).searchDefault(context);
        return pSSysSFPubs;
    }



    @Override
    @Transactional
    public PSSysSFPub dynamicCall(String key, String action, PSSysSFPub et) {
        return et;
    }
}


