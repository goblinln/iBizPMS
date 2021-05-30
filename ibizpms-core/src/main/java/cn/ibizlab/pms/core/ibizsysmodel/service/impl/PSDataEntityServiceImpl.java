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
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSDataEntity;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSDataEntitySearchContext;
import cn.ibizlab.pms.core.ibizsysmodel.service.IPSDataEntityService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import cn.ibizlab.pms.core.ibizsysmodel.client.PSDataEntityFeignClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.alibaba.fastjson.JSONObject;
/**
 * 实体[实体] 服务对象接口实现
 */
@Slf4j
@Service
public class PSDataEntityServiceImpl implements IPSDataEntityService {

    @Autowired
    PSDataEntityFeignClient pSDataEntityFeignClient;


    @Override
    public boolean create(PSDataEntity et) {
        PSDataEntity rt = pSDataEntityFeignClient.create(et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void createBatch(List<PSDataEntity> list){
        pSDataEntityFeignClient.createBatch(list) ;
    }

    @Override
    public boolean update(PSDataEntity et) {
        PSDataEntity rt = pSDataEntityFeignClient.update(et.getPsdataentityid(),et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt, et);
        return true;

    }

    public void updateBatch(List<PSDataEntity> list){
        pSDataEntityFeignClient.updateBatch(list) ;
    }

    @Override
    public boolean sysUpdate(PSDataEntity et) {
                                PSDataEntity rt = pSDataEntityFeignClient.update(et.getPsdataentityid(),et);
        if(rt==null)
            return false;

        CachedBeanCopier.copy(rt, et);
        return true;
    }

    @Override
    public boolean remove(String psdataentityid) {
        boolean result=pSDataEntityFeignClient.remove(psdataentityid) ;
        return result;
    }

    public void removeBatch(Collection<String> idList){
        pSDataEntityFeignClient.removeBatch(idList);
    }

    @Override
    public PSDataEntity get(String psdataentityid) {
		PSDataEntity et=pSDataEntityFeignClient.get(psdataentityid);
        if(et==null){
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), psdataentityid);
        }
        else{
        }
        return  et;
    }

    @Override
    public PSDataEntity sysGet(String psdataentityid) {
         return null;
    }

    @Override
    public PSDataEntity getDraft(PSDataEntity et) {
        et=pSDataEntityFeignClient.getDraft(et);
        return et;
    }

    @Override
    public boolean checkKey(PSDataEntity et) {
        return pSDataEntityFeignClient.checkKey(et);
    }
    @Override
    @Transactional
    public boolean save(PSDataEntity et) {
        boolean result = true;
        Object rt = pSDataEntityFeignClient.saveEntity(et);
        if(rt == null)
          return false;
        try {
            if (rt instanceof Map) {
                ObjectMapper mapper = new ObjectMapper();
                rt = mapper.readValue(mapper.writeValueAsString(rt), PSDataEntity.class);
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
    public void saveBatch(List<PSDataEntity> list) {
        pSDataEntityFeignClient.saveBatch(list) ;
    }



	@Override
    public List<PSDataEntity> selectByPsmoduleid(String psmoduleid) {
        PSDataEntitySearchContext context=new PSDataEntitySearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_psmoduleid_eq(psmoduleid);
        return pSDataEntityFeignClient.searchDefault(context).getContent();
    }
    @Override
    public List<PSDataEntity> selectByPsmoduleid(Collection<String> ids) {
        //暂未支持
        return null;
    }


    @Override
    public void removeByPsmoduleid(String psmoduleid) {
        Set<String> delIds=new HashSet<String>();
        for(PSDataEntity before:selectByPsmoduleid(psmoduleid)){
            delIds.add(before.getPsdataentityid());
        }
        if(delIds.size()>0)
            this.removeBatch(delIds);
    }

	@Override
    public List<PSDataEntity> selectByPssubsyssadeid(String pssubsyssadeid) {
        PSDataEntitySearchContext context=new PSDataEntitySearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_pssubsyssadeid_eq(pssubsyssadeid);
        return pSDataEntityFeignClient.searchDefault(context).getContent();
    }
    @Override
    public List<PSDataEntity> selectByPssubsyssadeid(Collection<String> ids) {
        //暂未支持
        return null;
    }


    @Override
    public void removeByPssubsyssadeid(String pssubsyssadeid) {
        Set<String> delIds=new HashSet<String>();
        for(PSDataEntity before:selectByPssubsyssadeid(pssubsyssadeid)){
            delIds.add(before.getPsdataentityid());
        }
        if(delIds.size()>0)
            this.removeBatch(delIds);
    }

	@Override
    public List<PSDataEntity> selectByPssubsysserviceapiid(String pssubsysserviceapiid) {
        PSDataEntitySearchContext context=new PSDataEntitySearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_pssubsysserviceapiid_eq(pssubsysserviceapiid);
        return pSDataEntityFeignClient.searchDefault(context).getContent();
    }
    @Override
    public List<PSDataEntity> selectByPssubsysserviceapiid(Collection<String> ids) {
        //暂未支持
        return null;
    }


    @Override
    public void removeByPssubsysserviceapiid(String pssubsysserviceapiid) {
        Set<String> delIds=new HashSet<String>();
        for(PSDataEntity before:selectByPssubsysserviceapiid(pssubsysserviceapiid)){
            delIds.add(before.getPsdataentityid());
        }
        if(delIds.size()>0)
            this.removeBatch(delIds);
    }

	@Override
    public List<PSDataEntity> selectByPssysreqitemid(String pssysreqitemid) {
        PSDataEntitySearchContext context=new PSDataEntitySearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_pssysreqitemid_eq(pssysreqitemid);
        return pSDataEntityFeignClient.searchDefault(context).getContent();
    }
    @Override
    public List<PSDataEntity> selectByPssysreqitemid(Collection<String> ids) {
        //暂未支持
        return null;
    }


    @Override
    public void removeByPssysreqitemid(String pssysreqitemid) {
        Set<String> delIds=new HashSet<String>();
        for(PSDataEntity before:selectByPssysreqitemid(pssysreqitemid)){
            delIds.add(before.getPsdataentityid());
        }
        if(delIds.size()>0)
            this.removeBatch(delIds);
    }



    /**
     * 查询集合 数据集
     */
    @Override
    public Page<PSDataEntity> searchDefault(PSDataEntitySearchContext context) {
        Page<PSDataEntity> pSDataEntitys=pSDataEntityFeignClient.searchDefault(context);
        return pSDataEntitys;
    }

    @Override
    @Transactional
    public PSDataEntity dynamicCall(String key, String action, PSDataEntity et) {
        return et;
    }
}



