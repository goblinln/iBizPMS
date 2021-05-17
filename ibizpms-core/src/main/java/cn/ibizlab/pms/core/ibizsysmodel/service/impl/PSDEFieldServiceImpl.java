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
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSDEField;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSDEFieldSearchContext;
import cn.ibizlab.pms.core.ibizsysmodel.service.IPSDEFieldService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import cn.ibizlab.pms.core.ibizsysmodel.client.PSDEFieldFeignClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.alibaba.fastjson.JSONObject;
/**
 * 实体[实体属性] 服务对象接口实现
 */
@Slf4j
@Service
public class PSDEFieldServiceImpl implements IPSDEFieldService {

    @Autowired
    PSDEFieldFeignClient pSDEFieldFeignClient;


    @Override
    public boolean create(PSDEField et) {
        PSDEField rt = pSDEFieldFeignClient.create(et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void createBatch(List<PSDEField> list){
        pSDEFieldFeignClient.createBatch(list) ;
    }

    @Override
    public boolean update(PSDEField et) {
        PSDEField rt = pSDEFieldFeignClient.update(et.getPsdefieldid(),et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt, et);
        return true;

    }

    public void updateBatch(List<PSDEField> list){
        pSDEFieldFeignClient.updateBatch(list) ;
    }

    @Override
    public boolean sysUpdate(PSDEField et) {
                                PSDEField rt = pSDEFieldFeignClient.update(et.getPsdefieldid(),et);
        if(rt==null)
            return false;

        CachedBeanCopier.copy(rt, et);
        return true;
    }

    @Override
    public boolean remove(String psdefieldid) {
        boolean result=pSDEFieldFeignClient.remove(psdefieldid) ;
        return result;
    }

    public void removeBatch(Collection<String> idList){
        pSDEFieldFeignClient.removeBatch(idList);
    }

    @Override
    public PSDEField get(String psdefieldid) {
		PSDEField et=pSDEFieldFeignClient.get(psdefieldid);
        if(et==null){
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), psdefieldid);
        }
        else{
        }
        return  et;
    }

    @Override
    public PSDEField sysGet(String psdefieldid) {
         return null;
    }

    @Override
    public PSDEField getDraft(PSDEField et) {
        et=pSDEFieldFeignClient.getDraft(et);
        return et;
    }

    @Override
    public boolean checkKey(PSDEField et) {
        return pSDEFieldFeignClient.checkKey(et);
    }
    @Override
    @Transactional
    public boolean save(PSDEField et) {
        boolean result = true;
        Object rt = pSDEFieldFeignClient.saveEntity(et);
        if(rt == null)
          return false;
        try {
            if (rt instanceof Map) {
                ObjectMapper mapper = new ObjectMapper();
                rt = mapper.readValue(mapper.writeValueAsString(rt), PSDEField.class);
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
    public void saveBatch(List<PSDEField> list) {
        pSDEFieldFeignClient.saveBatch(list) ;
    }



	@Override
    public List<PSDEField> selectByPsdeid(String psdataentityid) {
        PSDEFieldSearchContext context=new PSDEFieldSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_psdeid_eq(psdataentityid);
        return pSDEFieldFeignClient.searchDefault(context).getContent();
    }
    @Override
    public void removeByPsdeid(Collection<String> ids) {
    }


    @Override
    public void removeByPsdeid(String psdataentityid) {
        Set<String> delIds=new HashSet<String>();
        for(PSDEField before:selectByPsdeid(psdataentityid)){
            delIds.add(before.getPsdefieldid());
        }
        if(delIds.size()>0)
            this.removeBatch(delIds);
    }

	@Override
    public List<PSDEField> selectByDerpsdefid(String psdefieldid) {
        PSDEFieldSearchContext context=new PSDEFieldSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_derpsdefid_eq(psdefieldid);
        return pSDEFieldFeignClient.searchDefault(context).getContent();
    }
    @Override
    public List<PSDEField> selectByDerpsdefid(Collection<String> ids) {
        //暂未支持
        return null;
    }


    @Override
    public void removeByDerpsdefid(String psdefieldid) {
        Set<String> delIds=new HashSet<String>();
        for(PSDEField before:selectByDerpsdefid(psdefieldid)){
            delIds.add(before.getPsdefieldid());
        }
        if(delIds.size()>0)
            this.removeBatch(delIds);
    }

	@Override
    public List<PSDEField> selectByDupcheckpsdefid(String psdefieldid) {
        PSDEFieldSearchContext context=new PSDEFieldSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_dupchkpsdefid_eq(psdefieldid);
        return pSDEFieldFeignClient.searchDefault(context).getContent();
    }
    @Override
    public List<PSDEField> selectByDupcheckpsdefid(Collection<String> ids) {
        //暂未支持
        return null;
    }


    @Override
    public void removeByDupcheckpsdefid(String psdefieldid) {
        Set<String> delIds=new HashSet<String>();
        for(PSDEField before:selectByDupcheckpsdefid(psdefieldid)){
            delIds.add(before.getPsdefieldid());
        }
        if(delIds.size()>0)
            this.removeBatch(delIds);
    }

	@Override
    public List<PSDEField> selectByNo2dupchkpsdefid(String psdefieldid) {
        PSDEFieldSearchContext context=new PSDEFieldSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_no2dupchkpsdefid_eq(psdefieldid);
        return pSDEFieldFeignClient.searchDefault(context).getContent();
    }
    @Override
    public List<PSDEField> selectByNo2dupchkpsdefid(Collection<String> ids) {
        //暂未支持
        return null;
    }


    @Override
    public void removeByNo2dupchkpsdefid(String psdefieldid) {
        Set<String> delIds=new HashSet<String>();
        for(PSDEField before:selectByNo2dupchkpsdefid(psdefieldid)){
            delIds.add(before.getPsdefieldid());
        }
        if(delIds.size()>0)
            this.removeBatch(delIds);
    }

	@Override
    public List<PSDEField> selectByNo3dupchkpsdefid(String psdefieldid) {
        PSDEFieldSearchContext context=new PSDEFieldSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_no3dupchkpsdefid_eq(psdefieldid);
        return pSDEFieldFeignClient.searchDefault(context).getContent();
    }
    @Override
    public List<PSDEField> selectByNo3dupchkpsdefid(Collection<String> ids) {
        //暂未支持
        return null;
    }


    @Override
    public void removeByNo3dupchkpsdefid(String psdefieldid) {
        Set<String> delIds=new HashSet<String>();
        for(PSDEField before:selectByNo3dupchkpsdefid(psdefieldid)){
            delIds.add(before.getPsdefieldid());
        }
        if(delIds.size()>0)
            this.removeBatch(delIds);
    }

	@Override
    public List<PSDEField> selectByValuepsdefid(String psdefieldid) {
        PSDEFieldSearchContext context=new PSDEFieldSearchContext();
        context.setSize(Integer.MAX_VALUE);
        context.setN_valuepsdefid_eq(psdefieldid);
        return pSDEFieldFeignClient.searchDefault(context).getContent();
    }
    @Override
    public List<PSDEField> selectByValuepsdefid(Collection<String> ids) {
        //暂未支持
        return null;
    }


    @Override
    public void removeByValuepsdefid(String psdefieldid) {
        Set<String> delIds=new HashSet<String>();
        for(PSDEField before:selectByValuepsdefid(psdefieldid)){
            delIds.add(before.getPsdefieldid());
        }
        if(delIds.size()>0)
            this.removeBatch(delIds);
    }



    /**
     * 查询集合 数据集
     */
    @Override
    public Page<PSDEField> searchDefault(PSDEFieldSearchContext context) {
        Page<PSDEField> pSDEFields=pSDEFieldFeignClient.searchDefault(context);
        return pSDEFields;
    }

    @Override
    @Transactional
    public PSDEField dynamicCall(String key, String action, PSDEField et) {
        return et;
    }
}



