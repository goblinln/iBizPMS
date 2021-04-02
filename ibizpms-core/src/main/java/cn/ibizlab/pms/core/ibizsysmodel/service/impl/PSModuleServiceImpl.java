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
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSModule;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSModuleSearchContext;
import cn.ibizlab.pms.core.ibizsysmodel.service.IPSModuleService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import cn.ibizlab.pms.core.ibizsysmodel.client.PSModuleFeignClient;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 实体[系统模块] 服务对象接口实现
 */
@Slf4j
@Service
public class PSModuleServiceImpl implements IPSModuleService {

    @Autowired
    PSModuleFeignClient pSModuleFeignClient;


    @Override
    public boolean create(PSModule et) {
        PSModule rt = pSModuleFeignClient.create(et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void createBatch(List<PSModule> list){
        pSModuleFeignClient.createBatch(list) ;
    }

    @Override
    public boolean update(PSModule et) {
        PSModule rt = pSModuleFeignClient.update(et.getPsmoduleid(),et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt, et);
        return true;

    }

    public void updateBatch(List<PSModule> list){
        pSModuleFeignClient.updateBatch(list) ;
    }

    @Override
    public boolean remove(String psmoduleid) {
        boolean result=pSModuleFeignClient.remove(psmoduleid) ;
        return result;
    }

    public void removeBatch(Collection<String> idList){
        pSModuleFeignClient.removeBatch(idList);
    }

    @Override
    public PSModule get(String psmoduleid) {
		PSModule et=pSModuleFeignClient.get(psmoduleid);
        if(et==null){
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), psmoduleid);
        }
        else{
        }
        return  et;
    }

    @Override
    public PSModule getDraft(PSModule et) {
        et=pSModuleFeignClient.getDraft(et);
        return et;
    }

    @Override
    public boolean checkKey(PSModule et) {
        return pSModuleFeignClient.checkKey(et);
    }
    @Override
    @Transactional
    public boolean save(PSModule et) {
        boolean result = true;
        Object rt = pSModuleFeignClient.saveEntity(et);
        if(rt == null)
          return false;
        try {
            if (rt instanceof Map) {
                ObjectMapper mapper = new ObjectMapper();
                rt = mapper.readValue(mapper.writeValueAsString(rt), PSModule.class);
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
    public void saveBatch(List<PSModule> list) {
        pSModuleFeignClient.saveBatch(list) ;
    }





    /**
     * 查询集合 数据集
     */
    @Override
    public Page<PSModule> searchDefault(PSModuleSearchContext context) {
        Page<PSModule> pSModules=pSModuleFeignClient.searchDefault(context);
        return pSModules;
    }

    @Override
    @Transactional
    public PSModule dynamicCall(String key, String action, PSModule et) {
        return et;
    }
}



