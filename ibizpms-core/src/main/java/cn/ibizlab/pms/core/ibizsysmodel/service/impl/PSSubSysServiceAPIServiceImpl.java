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
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSubSysServiceAPI;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSubSysServiceAPISearchContext;
import cn.ibizlab.pms.core.ibizsysmodel.service.IPSSubSysServiceAPIService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import cn.ibizlab.pms.core.ibizsysmodel.client.PSSubSysServiceAPIFeignClient;
import cn.ibizlab.pms.util.security.SpringContextHolder;
import cn.ibizlab.pms.util.helper.OutsideAccessorUtils;
import org.apache.commons.lang3.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 实体[外部服务接口] 服务对象接口实现
 */
@Slf4j
@Service
public class PSSubSysServiceAPIServiceImpl implements IPSSubSysServiceAPIService {

//    @Autowired
    PSSubSysServiceAPIFeignClient pSSubSysServiceAPIFeignClient;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.serviceid:}")
    private String serviceName;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.serviceurl:}")
    private String serviceUrl;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.loginname:loginname}")
    private String loginname;

    @Value("${ibiz.ref.service.ibizpssysmodelapi-sysmodelapi.password:password}")
    private String password;

    public PSSubSysServiceAPIFeignClient getPSSubSysServiceAPIFeignClient(String devSlnSysId) {
        if (StringUtils.isNotBlank(serviceName)) {
            return OutsideAccessorUtils.buildAccessor(SpringContextHolder.getApplicationContext(), PSSubSysServiceAPIFeignClient.class, serviceName, false, serviceName, false, loginname, password, devSlnSysId);
        } else if (StringUtils.isNotBlank(serviceUrl)) {
            return OutsideAccessorUtils.buildAccessorByUrl(SpringContextHolder.getApplicationContext(), PSSubSysServiceAPIFeignClient.class, serviceUrl, false, serviceUrl, false, loginname, password, devSlnSysId);
        } else {
            throw new RuntimeException("缺少平台服务配置信息。");
        }
    }


    @Override
    public boolean create(PSSubSysServiceAPI et) {
        PSSubSysServiceAPI rt = pSSubSysServiceAPIFeignClient.create(et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    @Override
    public boolean create(String devSlnSysId, PSSubSysServiceAPI et) {
        PSSubSysServiceAPI rt = getPSSubSysServiceAPIFeignClient(devSlnSysId).create(et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void createBatch(List<PSSubSysServiceAPI> list){
        pSSubSysServiceAPIFeignClient.createBatch(list) ;
    }

    public void createBatch(String devSlnSysId, List<PSSubSysServiceAPI> list){
        getPSSubSysServiceAPIFeignClient(devSlnSysId).createBatch(list);
    }

    @Override
    public boolean update(PSSubSysServiceAPI et) {
        PSSubSysServiceAPI rt = pSSubSysServiceAPIFeignClient.update(et.getPssubsysserviceapiid(),et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt, et);
        return true;

    }

    @Override
    public boolean update(String devSlnSysId, PSSubSysServiceAPI et) {
        PSSubSysServiceAPI rt = getPSSubSysServiceAPIFeignClient(devSlnSysId).update(et.getPssubsysserviceapiid(), et);
        if (rt == null) {
            return false;
        }
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void updateBatch(List<PSSubSysServiceAPI> list){
        pSSubSysServiceAPIFeignClient.updateBatch(list) ;
    }

    public void updateBatch(String devSlnSysId, List<PSSubSysServiceAPI> list){
        getPSSubSysServiceAPIFeignClient(devSlnSysId).updateBatch(list);
    }

    @Override
    public boolean remove(String pssubsysserviceapiid) {
        boolean result=pSSubSysServiceAPIFeignClient.remove(pssubsysserviceapiid) ;
        return result;
    }

    @Override
    public boolean remove(String devSlnSysId, String pssubsysserviceapiid) {
        boolean result = getPSSubSysServiceAPIFeignClient(devSlnSysId).remove(pssubsysserviceapiid);
        return result;
    }

    public void removeBatch(Collection<String> idList){
        pSSubSysServiceAPIFeignClient.removeBatch(idList);
    }

    public void removeBatch(String devSlnSysId, Collection<String> idList) {
        getPSSubSysServiceAPIFeignClient(devSlnSysId).removeBatch(idList);
    }

    @Override
    public PSSubSysServiceAPI get(String pssubsysserviceapiid) {
		PSSubSysServiceAPI et=pSSubSysServiceAPIFeignClient.get(pssubsysserviceapiid);
        if(et==null){
            et=new PSSubSysServiceAPI();
            et.setPssubsysserviceapiid(pssubsysserviceapiid);
        }
        else{
        }
        return  et;
    }

    @Override
    public PSSubSysServiceAPI get(String devSlnSysId, String pssubsysserviceapiid) {
        PSSubSysServiceAPI et = getPSSubSysServiceAPIFeignClient(devSlnSysId).get(pssubsysserviceapiid);
        if (et == null) {
            et = new PSSubSysServiceAPI();
            et.setPssubsysserviceapiid(pssubsysserviceapiid);
        }
        else {
        }
        return et;
    }

    @Override
    public String getByCodeName(String devSlnSysId, String codeName) {
        return getPSSubSysServiceAPIFeignClient(devSlnSysId).getByCodeName(codeName);
    }

    @Override
    public PSSubSysServiceAPI getDraft(PSSubSysServiceAPI et) {
        et=pSSubSysServiceAPIFeignClient.getDraft(et);
        return et;
    }

    @Override
    public PSSubSysServiceAPI getDraft(String devSlnSysId, PSSubSysServiceAPI et) {
        et = getPSSubSysServiceAPIFeignClient(devSlnSysId).getDraft(et);
        return et;
    }

    @Override
    public boolean checkKey(PSSubSysServiceAPI et) {
        return pSSubSysServiceAPIFeignClient.checkKey(et);
    }

    @Override
    public boolean checkKey(String devSlnSysId, PSSubSysServiceAPI et) {
        return getPSSubSysServiceAPIFeignClient(devSlnSysId).checkKey(et);
    }

    @Override
    @Transactional
    public boolean save(PSSubSysServiceAPI et) {
        boolean result = true;
        Object rt = pSSubSysServiceAPIFeignClient.saveEntity(et);
        if(rt == null)
          return false;
        try {
            if (rt instanceof Map) {
                ObjectMapper mapper = new ObjectMapper();
                rt = mapper.readValue(mapper.writeValueAsString(rt), PSSubSysServiceAPI.class);
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
    public boolean save(String devSlnSysId, PSSubSysServiceAPI et) {
        if (et.getPssubsysserviceapiid() == null) {
            et.setPssubsysserviceapiid((String)et.getDefaultKey(true));
        }
        if (!getPSSubSysServiceAPIFeignClient(devSlnSysId).save(et)) {
            return false;
        }
        return true;
    }

    @Override
    public void saveBatch(List<PSSubSysServiceAPI> list) {
        pSSubSysServiceAPIFeignClient.saveBatch(list) ;
    }

    @Override
    public void saveBatch(String devSlnSysId, List<PSSubSysServiceAPI> list) {
        getPSSubSysServiceAPIFeignClient(devSlnSysId).saveBatch(list);
    }



	@Override
    public List<PSSubSysServiceAPI> selectByPsmoduleid(String psmoduleid) {
        PSSubSysServiceAPISearchContext context=new PSSubSysServiceAPISearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_psmoduleid_eq(psmoduleid);
        return pSSubSysServiceAPIFeignClient.searchDefault(context).getContent();
    }

    @Override
    public List<PSSubSysServiceAPI> selectByPsmoduleid(String devSlnSysId, String psmoduleid) {
        PSSubSysServiceAPISearchContext context = new PSSubSysServiceAPISearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_psmoduleid_eq(psmoduleid);
        return getPSSubSysServiceAPIFeignClient(devSlnSysId).searchDefault(context).getContent();
    }

    @Override
    public List<PSSubSysServiceAPI> selectByPsmoduleid(Collection<String> ids) {
        //暂未支持
        return null;
    }


    @Override
    public void removeByPsmoduleid(String psmoduleid) {
        Set<String> delIds=new HashSet<String>();
        for(PSSubSysServiceAPI before:selectByPsmoduleid(psmoduleid)){
            delIds.add(before.getPssubsysserviceapiid());
        }
        if(delIds.size()>0)
            this.removeBatch(delIds);
    }

    @Override
    public void removeByPsmoduleid(String devSlnSysId, String psmoduleid) {
        Set<String> delIds = new HashSet<String>();
        for(PSSubSysServiceAPI before:selectByPsmoduleid(devSlnSysId, psmoduleid)){
            delIds.add(before.getPssubsysserviceapiid());
        }
        if (delIds.size() > 0) {
            this.removeBatch(delIds);
        }
    }

	@Override
    public List<PSSubSysServiceAPI> selectByPssysserviceapiid(String pssysserviceapiid) {
        PSSubSysServiceAPISearchContext context=new PSSubSysServiceAPISearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_pssysserviceapiid_eq(pssysserviceapiid);
        return pSSubSysServiceAPIFeignClient.searchDefault(context).getContent();
    }

    @Override
    public List<PSSubSysServiceAPI> selectByPssysserviceapiid(String devSlnSysId, String pssysserviceapiid) {
        PSSubSysServiceAPISearchContext context = new PSSubSysServiceAPISearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_pssysserviceapiid_eq(pssysserviceapiid);
        return getPSSubSysServiceAPIFeignClient(devSlnSysId).searchDefault(context).getContent();
    }

    @Override
    public List<PSSubSysServiceAPI> selectByPssysserviceapiid(Collection<String> ids) {
        //暂未支持
        return null;
    }


    @Override
    public void removeByPssysserviceapiid(String pssysserviceapiid) {
        Set<String> delIds=new HashSet<String>();
        for(PSSubSysServiceAPI before:selectByPssysserviceapiid(pssysserviceapiid)){
            delIds.add(before.getPssubsysserviceapiid());
        }
        if(delIds.size()>0)
            this.removeBatch(delIds);
    }

    @Override
    public void removeByPssysserviceapiid(String devSlnSysId, String pssysserviceapiid) {
        Set<String> delIds = new HashSet<String>();
        for(PSSubSysServiceAPI before:selectByPssysserviceapiid(devSlnSysId, pssysserviceapiid)){
            delIds.add(before.getPssubsysserviceapiid());
        }
        if (delIds.size() > 0) {
            this.removeBatch(delIds);
        }
    }



    /**
     * 查询集合 数据集
     */
    @Override
    public Page<PSSubSysServiceAPI> searchDefault(PSSubSysServiceAPISearchContext context) {
        Page<PSSubSysServiceAPI> pSSubSysServiceAPIs=pSSubSysServiceAPIFeignClient.searchDefault(context);
        return pSSubSysServiceAPIs;
    }

    @Override
    public Page<PSSubSysServiceAPI> searchDefault(String devSlnSysId, PSSubSysServiceAPISearchContext context) {
        Page<PSSubSysServiceAPI> pSSubSysServiceAPIs=getPSSubSysServiceAPIFeignClient(devSlnSysId).searchDefault(context);
        return pSSubSysServiceAPIs;
    }

    @Override
    @Transactional
    public PSSubSysServiceAPI dynamicCall(String key, String action, PSSubSysServiceAPI et) {
        return et;
    }
}


