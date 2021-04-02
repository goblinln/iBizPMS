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
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 实体[系统运行会话] 服务对象接口实现
 */
@Slf4j
@Service
public class PSSysRunSessionServiceImpl implements IPSSysRunSessionService {

    @Autowired
    PSSysRunSessionFeignClient pSSysRunSessionFeignClient;


    @Override
    public boolean create(PSSysRunSession et) {
        PSSysRunSession rt = pSSysRunSessionFeignClient.create(et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void createBatch(List<PSSysRunSession> list){
        pSSysRunSessionFeignClient.createBatch(list) ;
    }

    @Override
    public boolean update(PSSysRunSession et) {
        PSSysRunSession rt = pSSysRunSessionFeignClient.update(et.getPssysrunsessionid(),et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt, et);
        return true;

    }

    public void updateBatch(List<PSSysRunSession> list){
        pSSysRunSessionFeignClient.updateBatch(list) ;
    }

    @Override
    public boolean remove(String pssysrunsessionid) {
        boolean result=pSSysRunSessionFeignClient.remove(pssysrunsessionid) ;
        return result;
    }

    public void removeBatch(Collection<String> idList){
        pSSysRunSessionFeignClient.removeBatch(idList);
    }

    @Override
    public PSSysRunSession get(String pssysrunsessionid) {
		PSSysRunSession et=pSSysRunSessionFeignClient.get(pssysrunsessionid);
        if(et==null){
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), pssysrunsessionid);
        }
        else{
        }
        return  et;
    }

    @Override
    public PSSysRunSession getDraft(PSSysRunSession et) {
        et=pSSysRunSessionFeignClient.getDraft(et);
        return et;
    }

    @Override
    public boolean checkKey(PSSysRunSession et) {
        return pSSysRunSessionFeignClient.checkKey(et);
    }
    @Override
    @Transactional
    public boolean save(PSSysRunSession et) {
        boolean result = true;
        Object rt = pSSysRunSessionFeignClient.saveEntity(et);
        if(rt == null)
          return false;
        try {
            if (rt instanceof Map) {
                ObjectMapper mapper = new ObjectMapper();
                rt = mapper.readValue(mapper.writeValueAsString(rt), PSSysRunSession.class);
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
    public void saveBatch(List<PSSysRunSession> list) {
        pSSysRunSessionFeignClient.saveBatch(list) ;
    }



	@Override
    public List<PSSysRunSession> selectByPssysappid(String pssysappid) {
        PSSysRunSessionSearchContext context=new PSSysRunSessionSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_pssysappid_eq(pssysappid);
        return pSSysRunSessionFeignClient.searchDefault(context).getContent();
    }

    @Override
    public void removeByPssysappid(String pssysappid) {
        Set<String> delIds=new HashSet<String>();
        for(PSSysRunSession before:selectByPssysappid(pssysappid)){
            delIds.add(before.getPssysrunsessionid());
        }
        if(delIds.size()>0)
            this.removeBatch(delIds);
    }

	@Override
    public List<PSSysRunSession> selectByPssysappid2(String pssysappid) {
        PSSysRunSessionSearchContext context=new PSSysRunSessionSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_pssysappid2_eq(pssysappid);
        return pSSysRunSessionFeignClient.searchDefault(context).getContent();
    }

    @Override
    public void removeByPssysappid2(String pssysappid) {
        Set<String> delIds=new HashSet<String>();
        for(PSSysRunSession before:selectByPssysappid2(pssysappid)){
            delIds.add(before.getPssysrunsessionid());
        }
        if(delIds.size()>0)
            this.removeBatch(delIds);
    }

	@Override
    public List<PSSysRunSession> selectByPssysserviceapiid(String pssysserviceapiid) {
        PSSysRunSessionSearchContext context=new PSSysRunSessionSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_pssysserviceapiid_eq(pssysserviceapiid);
        return pSSysRunSessionFeignClient.searchDefault(context).getContent();
    }

    @Override
    public void removeByPssysserviceapiid(String pssysserviceapiid) {
        Set<String> delIds=new HashSet<String>();
        for(PSSysRunSession before:selectByPssysserviceapiid(pssysserviceapiid)){
            delIds.add(before.getPssysrunsessionid());
        }
        if(delIds.size()>0)
            this.removeBatch(delIds);
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
    @Transactional
    public PSSysRunSession dynamicCall(String key, String action, PSSysRunSession et) {
        return et;
    }
}



