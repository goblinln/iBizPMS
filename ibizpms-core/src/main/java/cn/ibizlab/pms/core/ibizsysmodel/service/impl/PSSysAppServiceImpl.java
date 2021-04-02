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
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSysApp;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSysAppSearchContext;
import cn.ibizlab.pms.core.ibizsysmodel.service.IPSSysAppService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import cn.ibizlab.pms.core.ibizsysmodel.client.PSSysAppFeignClient;
import cn.ibizlab.pms.util.security.SpringContextHolder;
import cn.ibizlab.pms.util.helper.OutsideAccessorUtils;
import org.apache.commons.lang3.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 实体[系统应用] 服务对象接口实现
 */
@Slf4j
@Service
public class PSSysAppServiceImpl implements IPSSysAppService {

//    @Autowired
    PSSysAppFeignClient pSSysAppFeignClient;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.serviceid:}")
    private String serviceName;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.serviceurl:}")
    private String serviceUrl;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.loginname:loginname}")
    private String loginname;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.password:password}")
    private String password;

    public PSSysAppFeignClient getPSSysAppFeignClient(String devSlnSysId) {
        if (StringUtils.isNotBlank(serviceName)) {
            return OutsideAccessorUtils.buildAccessor(SpringContextHolder.getApplicationContext(), PSSysAppFeignClient.class, serviceName, false, serviceName, false, loginname, password, devSlnSysId);
        } else if (StringUtils.isNotBlank(serviceUrl)) {
            return OutsideAccessorUtils.buildAccessorByUrl(SpringContextHolder.getApplicationContext(), PSSysAppFeignClient.class, serviceUrl, false, serviceUrl, false, loginname, password, devSlnSysId);
        } else {
            throw new RuntimeException("缺少平台服务配置信息。");
        }
    }


    @Override
    public boolean create(PSSysApp et) {
        PSSysApp rt = pSSysAppFeignClient.create(et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    @Override
    public boolean create(String devSlnSysId, PSSysApp et) {
        PSSysApp rt = getPSSysAppFeignClient(devSlnSysId).create(et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void createBatch(List<PSSysApp> list){
        pSSysAppFeignClient.createBatch(list) ;
    }

    public void createBatch(String devSlnSysId, List<PSSysApp> list){
        getPSSysAppFeignClient(devSlnSysId).createBatch(list);
    }

    @Override
    public boolean update(PSSysApp et) {
        PSSysApp rt = pSSysAppFeignClient.update(et.getPssysappid(),et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt, et);
        return true;

    }

    @Override
    public boolean update(String devSlnSysId, PSSysApp et) {
        PSSysApp rt = getPSSysAppFeignClient(devSlnSysId).update(et.getPssysappid(), et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void updateBatch(List<PSSysApp> list){
        pSSysAppFeignClient.updateBatch(list) ;
    }

    public void updateBatch(String devSlnSysId, List<PSSysApp> list){
        getPSSysAppFeignClient(devSlnSysId).updateBatch(list);
    }

    @Override
    public boolean remove(String pssysappid) {
        boolean result=pSSysAppFeignClient.remove(pssysappid) ;
        return result;
    }

    @Override
    public boolean remove(String devSlnSysId, String pssysappid) {
        boolean result = getPSSysAppFeignClient(devSlnSysId).remove(pssysappid);
        return result;
    }

    public void removeBatch(Collection<String> idList){
        pSSysAppFeignClient.removeBatch(idList);
    }

    public void removeBatch(String devSlnSysId, Collection<String> idList) {
        getPSSysAppFeignClient(devSlnSysId).removeBatch(idList);
    }

    @Override
    public PSSysApp get(String pssysappid) {
		PSSysApp et=pSSysAppFeignClient.get(pssysappid);
        if(et==null){
            et=new PSSysApp();
            et.setPssysappid(pssysappid);
        }
        else{
        }
        return  et;
    }

    @Override
    public PSSysApp get(String devSlnSysId, String pssysappid) {
        PSSysApp et = getPSSysAppFeignClient(devSlnSysId).get(pssysappid);
        if (et == null) {
            et = new PSSysApp();
            et.setPssysappid(pssysappid);
        }
        else {
        }
        return et;
    }

    @Override
    public String getByCodeName(String devSlnSysId, String codeName) {
        return getPSSysAppFeignClient(devSlnSysId).getByCodeName(codeName);
    }

    @Override
    public PSSysApp getDraft(PSSysApp et) {
        et=pSSysAppFeignClient.getDraft(et);
        return et;
    }

    @Override
    public PSSysApp getDraft(String devSlnSysId, PSSysApp et) {
        et = getPSSysAppFeignClient(devSlnSysId).getDraft(et);
        return et;
    }

    @Override
    public boolean checkKey(PSSysApp et) {
        return pSSysAppFeignClient.checkKey(et);
    }

    @Override
    public boolean checkKey(String devSlnSysId, PSSysApp et) {
        return getPSSysAppFeignClient(devSlnSysId).checkKey(et);
    }

    @Override
    @Transactional
    public boolean save(PSSysApp et) {
        boolean result = true;
        Object rt = pSSysAppFeignClient.saveEntity(et);
        if(rt == null)
          return false;
        try {
            if (rt instanceof Map) {
                ObjectMapper mapper = new ObjectMapper();
                rt = mapper.readValue(mapper.writeValueAsString(rt), PSSysApp.class);
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
    @Transactional
    public boolean save(String devSlnSysId, PSSysApp et) {
        if (et.getPssysappid() == null) {
            et.setPssysappid((String)et.getDefaultKey(true));
        }
        if (!getPSSysAppFeignClient(devSlnSysId).save(et)) {
            return false;
        }
        return true;
    }

    @Override
    public void saveBatch(List<PSSysApp> list) {
        pSSysAppFeignClient.saveBatch(list) ;
    }

    @Override
    public void saveBatch(String devSlnSysId, List<PSSysApp> list) {
        getPSSysAppFeignClient(devSlnSysId).saveBatch(list);
    }



	@Override
    public List<PSSysApp> selectByPssysserviceapiid(String pssysserviceapiid) {
        PSSysAppSearchContext context=new PSSysAppSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_pssysserviceapiid_eq(pssysserviceapiid);
        return pSSysAppFeignClient.searchDefault(context).getContent();
    }

    @Override
    public List<PSSysApp> selectByPssysserviceapiid(String devSlnSysId, String pssysserviceapiid) {
        PSSysAppSearchContext context = new PSSysAppSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_pssysserviceapiid_eq(pssysserviceapiid);
        return getPSSysAppFeignClient(devSlnSysId).searchDefault(context).getContent();
    }

    @Override
    public List<PSSysApp> selectByPssysserviceapiid(Collection<String> ids) {
        //暂未支持
        return null;
    }


    @Override
    public void removeByPssysserviceapiid(String pssysserviceapiid) {
        Set<String> delIds=new HashSet<String>();
        for(PSSysApp before:selectByPssysserviceapiid(pssysserviceapiid)){
            delIds.add(before.getPssysappid());
        }
        if(delIds.size()>0)
            this.removeBatch(delIds);
    }

    @Override
    public void removeByPssysserviceapiid(String devSlnSysId, String pssysserviceapiid) {
        Set<String> delIds = new HashSet<String>();
        for(PSSysApp before:selectByPssysserviceapiid(devSlnSysId, pssysserviceapiid)){
            delIds.add(before.getPssysappid());
        }
        if (delIds.size() > 0) {
            this.removeBatch(delIds);
        }
    }



    /**
     * 查询集合 版本
     */
    @Override
    public Page<PSSysApp> searchBuild(PSSysAppSearchContext context) {
        Page<PSSysApp> pSSysApps=pSSysAppFeignClient.searchBuild(context);
        return pSSysApps;
    }

    @Override
    public Page<PSSysApp> searchBuild(String devSlnSysId, PSSysAppSearchContext context) {
        Page<PSSysApp> pSSysApps=getPSSysAppFeignClient(devSlnSysId).searchBuild(context);
        return pSSysApps;
    }

    /**
     * 查询集合 数据集
     */
    @Override
    public Page<PSSysApp> searchDefault(PSSysAppSearchContext context) {
        Page<PSSysApp> pSSysApps=pSSysAppFeignClient.searchDefault(context);
        return pSSysApps;
    }

    @Override
    public Page<PSSysApp> searchDefault(String devSlnSysId, PSSysAppSearchContext context) {
        Page<PSSysApp> pSSysApps=getPSSysAppFeignClient(devSlnSysId).searchDefault(context);
        return pSSysApps;
    }

    @Override
    @Transactional
    public PSSysApp dynamicCall(String key, String action, PSSysApp et) {
        return et;
    }
}


