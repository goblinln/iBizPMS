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
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 实体[外部服务接口] 服务对象接口实现
 */
@Slf4j
@Service
public class PSSubSysServiceAPIServiceImpl implements IPSSubSysServiceAPIService {

    @Autowired
    PSSubSysServiceAPIFeignClient pSSubSysServiceAPIFeignClient;


    @Override
    public boolean create(PSSubSysServiceAPI et) {
        PSSubSysServiceAPI rt = pSSubSysServiceAPIFeignClient.create(et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void createBatch(List<PSSubSysServiceAPI> list){
        pSSubSysServiceAPIFeignClient.createBatch(list) ;
    }

    @Override
    public boolean update(PSSubSysServiceAPI et) {
        PSSubSysServiceAPI rt = pSSubSysServiceAPIFeignClient.update(et.getPssubsysserviceapiid(),et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt, et);
        return true;

    }

    public void updateBatch(List<PSSubSysServiceAPI> list){
        pSSubSysServiceAPIFeignClient.updateBatch(list) ;
    }

    @Override
    public boolean remove(String pssubsysserviceapiid) {
        boolean result=pSSubSysServiceAPIFeignClient.remove(pssubsysserviceapiid) ;
        return result;
    }

    public void removeBatch(Collection<String> idList){
        pSSubSysServiceAPIFeignClient.removeBatch(idList);
    }

    @Override
    public PSSubSysServiceAPI get(String pssubsysserviceapiid) {
		PSSubSysServiceAPI et=pSSubSysServiceAPIFeignClient.get(pssubsysserviceapiid);
        if(et==null){
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), pssubsysserviceapiid);
        }
        else{
        }
        return  et;
    }

    @Override
    public PSSubSysServiceAPI getDraft(PSSubSysServiceAPI et) {
        et=pSSubSysServiceAPIFeignClient.getDraft(et);
        return et;
    }

    @Override
    public boolean checkKey(PSSubSysServiceAPI et) {
        return pSSubSysServiceAPIFeignClient.checkKey(et);
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
    public void saveBatch(List<PSSubSysServiceAPI> list) {
        pSSubSysServiceAPIFeignClient.saveBatch(list) ;
    }



	@Override
    public List<PSSubSysServiceAPI> selectByPsmoduleid(String psmoduleid) {
        PSSubSysServiceAPISearchContext context=new PSSubSysServiceAPISearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_psmoduleid_eq(psmoduleid);
        return pSSubSysServiceAPIFeignClient.searchDefault(context).getContent();
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
    public List<PSSubSysServiceAPI> selectByPssysserviceapiid(String pssysserviceapiid) {
        PSSubSysServiceAPISearchContext context=new PSSubSysServiceAPISearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_pssysserviceapiid_eq(pssysserviceapiid);
        return pSSubSysServiceAPIFeignClient.searchDefault(context).getContent();
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



    /**
     * 查询集合 数据集
     */
    @Override
    public Page<PSSubSysServiceAPI> searchDefault(PSSubSysServiceAPISearchContext context) {
        Page<PSSubSysServiceAPI> pSSubSysServiceAPIs=pSSubSysServiceAPIFeignClient.searchDefault(context);
        return pSSubSysServiceAPIs;
    }

    @Override
    @Transactional
    public PSSubSysServiceAPI dynamicCall(String key, String action, PSSubSysServiceAPI et) {
        return et;
    }
}



