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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.alibaba.fastjson.JSONObject;
/**
 * 实体[外部接口实体] 服务对象接口实现
 */
@Slf4j
@Service
public class PSSubSysSADEServiceImpl implements IPSSubSysSADEService {

    @Autowired
    PSSubSysSADEFeignClient pSSubSysSADEFeignClient;


    @Override
    public List<PSSubSysSADE> select(PSSubSysSADESearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        return searchDefault(context).getContent();
    }

    @Override
    public boolean create(PSSubSysSADE et) {
        PSSubSysSADE rt = pSSubSysSADEFeignClient.create(et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void createBatch(List<PSSubSysSADE> list){
        pSSubSysSADEFeignClient.createBatch(list) ;
    }

    @Override
    public boolean update(PSSubSysSADE et) {
        PSSubSysSADE rt = pSSubSysSADEFeignClient.update(et.getPssubsyssadeid(),et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt, et);
        return true;

    }

    public void updateBatch(List<PSSubSysSADE> list){
        pSSubSysSADEFeignClient.updateBatch(list) ;
    }

    @Override
    public boolean sysUpdate(PSSubSysSADE et) {
                                PSSubSysSADE rt = pSSubSysSADEFeignClient.update(et.getPssubsyssadeid(),et);
        if(rt==null)
            return false;

        CachedBeanCopier.copy(rt, et);
        return true;
    }

    @Override
    public boolean remove(String pssubsyssadeid) {
        boolean result=pSSubSysSADEFeignClient.remove(pssubsyssadeid) ;
        return result;
    }

    public void removeBatch(Collection<String> idList){
        pSSubSysSADEFeignClient.removeBatch(idList);
    }

    @Override
    public PSSubSysSADE get(String pssubsyssadeid) {
		PSSubSysSADE et=pSSubSysSADEFeignClient.get(pssubsyssadeid);
        if(et==null){
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), pssubsyssadeid);
        }
        else{
        }
        return  et;
    }

    @Override
    public PSSubSysSADE sysGet(String pssubsyssadeid) {
         return null;
    }

    @Override
    public PSSubSysSADE getDraft(PSSubSysSADE et) {
        et=pSSubSysSADEFeignClient.getDraft(et);
        return et;
    }

    @Override
    public boolean checkKey(PSSubSysSADE et) {
        return pSSubSysSADEFeignClient.checkKey(et);
    }
    @Override
    @Transactional
    public boolean save(PSSubSysSADE et) {
        boolean result = true;
        Object rt = pSSubSysSADEFeignClient.saveEntity(et);
        if(rt == null)
          return false;
        try {
            if (rt instanceof Map) {
                ObjectMapper mapper = new ObjectMapper();
                rt = mapper.readValue(mapper.writeValueAsString(rt), PSSubSysSADE.class);
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
    public void saveBatch(List<PSSubSysSADE> list) {
        pSSubSysSADEFeignClient.saveBatch(list) ;
    }



	@Override
    public List<PSSubSysSADE> selectByPssubsysserviceapiid(String pssubsysserviceapiid) {
        PSSubSysSADESearchContext context=new PSSubSysSADESearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_pssubsysserviceapiid_eq(pssubsysserviceapiid);
        return pSSubSysSADEFeignClient.searchDefault(context).getContent();
    }
    @Override
    public List<PSSubSysSADE> selectByPssubsysserviceapiid(Collection<String> ids) {
        //暂未支持
        return null;
    }


    @Override
    public void removeByPssubsysserviceapiid(String pssubsysserviceapiid) {
        Set<String> delIds=new HashSet<String>();
        for(PSSubSysSADE before:selectByPssubsysserviceapiid(pssubsysserviceapiid)){
            delIds.add(before.getPssubsyssadeid());
        }
        if(delIds.size()>0)
            this.removeBatch(delIds);
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
    @Transactional
    public PSSubSysSADE dynamicCall(String key, String action, PSSubSysSADE et) {
        return et;
    }
}



