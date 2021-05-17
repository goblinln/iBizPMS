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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.alibaba.fastjson.JSONObject;
/**
 * 实体[系统服务接口] 服务对象接口实现
 */
@Slf4j
@Service
public class PSSysServiceAPIServiceImpl implements IPSSysServiceAPIService {

    @Autowired
    PSSysServiceAPIFeignClient pSSysServiceAPIFeignClient;


    @Override
    public boolean create(PSSysServiceAPI et) {
        PSSysServiceAPI rt = pSSysServiceAPIFeignClient.create(et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void createBatch(List<PSSysServiceAPI> list){
        pSSysServiceAPIFeignClient.createBatch(list) ;
    }

    @Override
    public boolean update(PSSysServiceAPI et) {
        PSSysServiceAPI rt = pSSysServiceAPIFeignClient.update(et.getPssysserviceapiid(),et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt, et);
        return true;

    }

    public void updateBatch(List<PSSysServiceAPI> list){
        pSSysServiceAPIFeignClient.updateBatch(list) ;
    }

    @Override
    public boolean sysUpdate(PSSysServiceAPI et) {
                                PSSysServiceAPI rt = pSSysServiceAPIFeignClient.update(et.getPssysserviceapiid(),et);
        if(rt==null)
            return false;

        CachedBeanCopier.copy(rt, et);
        return true;
    }

    @Override
    public boolean remove(String pssysserviceapiid) {
        boolean result=pSSysServiceAPIFeignClient.remove(pssysserviceapiid) ;
        return result;
    }

    public void removeBatch(Collection<String> idList){
        pSSysServiceAPIFeignClient.removeBatch(idList);
    }

    @Override
    public PSSysServiceAPI get(String pssysserviceapiid) {
		PSSysServiceAPI et=pSSysServiceAPIFeignClient.get(pssysserviceapiid);
        if(et==null){
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), pssysserviceapiid);
        }
        else{
        }
        return  et;
    }

    @Override
    public PSSysServiceAPI sysGet(String pssysserviceapiid) {
         return null;
    }

    @Override
    public PSSysServiceAPI getDraft(PSSysServiceAPI et) {
        et=pSSysServiceAPIFeignClient.getDraft(et);
        return et;
    }

    @Override
    public boolean checkKey(PSSysServiceAPI et) {
        return pSSysServiceAPIFeignClient.checkKey(et);
    }
    @Override
    @Transactional
    public boolean save(PSSysServiceAPI et) {
        boolean result = true;
        Object rt = pSSysServiceAPIFeignClient.saveEntity(et);
        if(rt == null)
          return false;
        try {
            if (rt instanceof Map) {
                ObjectMapper mapper = new ObjectMapper();
                rt = mapper.readValue(mapper.writeValueAsString(rt), PSSysServiceAPI.class);
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
    public void saveBatch(List<PSSysServiceAPI> list) {
        pSSysServiceAPIFeignClient.saveBatch(list) ;
    }



	@Override
    public List<PSSysServiceAPI> selectByPsmoduleid(String psmoduleid) {
        PSSysServiceAPISearchContext context=new PSSysServiceAPISearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_psmoduleid_eq(psmoduleid);
        return pSSysServiceAPIFeignClient.searchDefault(context).getContent();
    }
    @Override
    public List<PSSysServiceAPI> selectByPsmoduleid(Collection<String> ids) {
        //暂未支持
        return null;
    }


    @Override
    public void removeByPsmoduleid(String psmoduleid) {
        Set<String> delIds=new HashSet<String>();
        for(PSSysServiceAPI before:selectByPsmoduleid(psmoduleid)){
            delIds.add(before.getPssysserviceapiid());
        }
        if(delIds.size()>0)
            this.removeBatch(delIds);
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
    @Transactional
    public PSSysServiceAPI dynamicCall(String key, String action, PSSysServiceAPI et) {
        return et;
    }
}



