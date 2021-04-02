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
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 实体[后台服务架构] 服务对象接口实现
 */
@Slf4j
@Service
public class PSSysSFPubServiceImpl implements IPSSysSFPubService {

    @Autowired
    PSSysSFPubFeignClient pSSysSFPubFeignClient;


    @Override
    public boolean create(PSSysSFPub et) {
        PSSysSFPub rt = pSSysSFPubFeignClient.create(et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void createBatch(List<PSSysSFPub> list){
        pSSysSFPubFeignClient.createBatch(list) ;
    }

    @Override
    public boolean update(PSSysSFPub et) {
        PSSysSFPub rt = pSSysSFPubFeignClient.update(et.getPssyssfpubid(),et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt, et);
        return true;

    }

    public void updateBatch(List<PSSysSFPub> list){
        pSSysSFPubFeignClient.updateBatch(list) ;
    }

    @Override
    public boolean remove(String pssyssfpubid) {
        boolean result=pSSysSFPubFeignClient.remove(pssyssfpubid) ;
        return result;
    }

    public void removeBatch(Collection<String> idList){
        pSSysSFPubFeignClient.removeBatch(idList);
    }

    @Override
    public PSSysSFPub get(String pssyssfpubid) {
		PSSysSFPub et=pSSysSFPubFeignClient.get(pssyssfpubid);
        if(et==null){
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), pssyssfpubid);
        }
        else{
        }
        return  et;
    }

    @Override
    public PSSysSFPub getDraft(PSSysSFPub et) {
        et=pSSysSFPubFeignClient.getDraft(et);
        return et;
    }

    @Override
    public boolean checkKey(PSSysSFPub et) {
        return pSSysSFPubFeignClient.checkKey(et);
    }
    @Override
    @Transactional
    public boolean save(PSSysSFPub et) {
        boolean result = true;
        Object rt = pSSysSFPubFeignClient.saveEntity(et);
        if(rt == null)
          return false;
        try {
            if (rt instanceof Map) {
                ObjectMapper mapper = new ObjectMapper();
                rt = mapper.readValue(mapper.writeValueAsString(rt), PSSysSFPub.class);
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
    public void saveBatch(List<PSSysSFPub> list) {
        pSSysSFPubFeignClient.saveBatch(list) ;
    }



	@Override
    public List<PSSysSFPub> selectByPpssyssfpubid(String pssyssfpubid) {
        PSSysSFPubSearchContext context=new PSSysSFPubSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_ppssyssfpubid_eq(pssyssfpubid);
        return pSSysSFPubFeignClient.searchDefault(context).getContent();
    }
    @Override
    public List<PSSysSFPub> selectByPpssyssfpubid(Collection<String> ids) {
        //暂未支持
        return null;
    }


    @Override
    public void removeByPpssyssfpubid(String pssyssfpubid) {
        Set<String> delIds=new HashSet<String>();
        for(PSSysSFPub before:selectByPpssyssfpubid(pssyssfpubid)){
            delIds.add(before.getPssyssfpubid());
        }
        if(delIds.size()>0)
            this.removeBatch(delIds);
    }



    /**
     * 查询集合 版本
     */
    @Override
    public Page<PSSysSFPub> searchBuild(PSSysSFPubSearchContext context) {
        Page<PSSysSFPub> pSSysSFPubs=pSSysSFPubFeignClient.searchBuild(context);
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
    @Transactional
    public PSSysSFPub dynamicCall(String key, String action, PSSysSFPub et) {
        return et;
    }
}



