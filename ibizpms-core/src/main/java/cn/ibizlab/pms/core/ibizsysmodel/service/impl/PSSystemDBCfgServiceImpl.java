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
import cn.ibizlab.pms.core.ibizsysmodel.domain.PSSystemDBCfg;
import cn.ibizlab.pms.core.ibizsysmodel.filter.PSSystemDBCfgSearchContext;
import cn.ibizlab.pms.core.ibizsysmodel.service.IPSSystemDBCfgService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import cn.ibizlab.pms.core.ibizsysmodel.client.PSSystemDBCfgFeignClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.alibaba.fastjson.JSONObject;
/**
 * 实体[系统数据库] 服务对象接口实现
 */
@Slf4j
@Service
public class PSSystemDBCfgServiceImpl implements IPSSystemDBCfgService {

    @Autowired
    PSSystemDBCfgFeignClient pSSystemDBCfgFeignClient;


    @Override
    public boolean create(PSSystemDBCfg et) {
        PSSystemDBCfg rt = pSSystemDBCfgFeignClient.create(et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void createBatch(List<PSSystemDBCfg> list){
        pSSystemDBCfgFeignClient.createBatch(list) ;
    }

    @Override
    public boolean update(PSSystemDBCfg et) {
        PSSystemDBCfg rt = pSSystemDBCfgFeignClient.update(et.getPssystemdbcfgid(),et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt, et);
        return true;

    }

    public void updateBatch(List<PSSystemDBCfg> list){
        pSSystemDBCfgFeignClient.updateBatch(list) ;
    }

    @Override
    public boolean sysUpdate(PSSystemDBCfg et) {
                                PSSystemDBCfg rt = pSSystemDBCfgFeignClient.update(et.getPssystemdbcfgid(),et);
        if(rt==null)
            return false;

        CachedBeanCopier.copy(rt, et);
        return true;
    }

    @Override
    public boolean remove(String pssystemdbcfgid) {
        boolean result=pSSystemDBCfgFeignClient.remove(pssystemdbcfgid) ;
        return result;
    }

    public void removeBatch(Collection<String> idList){
        pSSystemDBCfgFeignClient.removeBatch(idList);
    }

    @Override
    public PSSystemDBCfg get(String pssystemdbcfgid) {
		PSSystemDBCfg et=pSSystemDBCfgFeignClient.get(pssystemdbcfgid);
        if(et==null){
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), pssystemdbcfgid);
        }
        else{
        }
        return  et;
    }

    @Override
    public PSSystemDBCfg sysGet(String pssystemdbcfgid) {
         return null;
    }

    @Override
    public PSSystemDBCfg getDraft(PSSystemDBCfg et) {
        et=pSSystemDBCfgFeignClient.getDraft(et);
        return et;
    }

    @Override
    public boolean checkKey(PSSystemDBCfg et) {
        return pSSystemDBCfgFeignClient.checkKey(et);
    }
    @Override
    @Transactional
    public boolean save(PSSystemDBCfg et) {
        boolean result = true;
        Object rt = pSSystemDBCfgFeignClient.saveEntity(et);
        if(rt == null)
          return false;
        try {
            if (rt instanceof Map) {
                ObjectMapper mapper = new ObjectMapper();
                rt = mapper.readValue(mapper.writeValueAsString(rt), PSSystemDBCfg.class);
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
    public void saveBatch(List<PSSystemDBCfg> list) {
        pSSystemDBCfgFeignClient.saveBatch(list) ;
    }





    /**
     * 查询集合 版本
     */
    @Override
    public Page<PSSystemDBCfg> searchBuild(PSSystemDBCfgSearchContext context) {
        Page<PSSystemDBCfg> pSSystemDBCfgs=pSSystemDBCfgFeignClient.searchBuild(context);
        return pSSystemDBCfgs;
    }

    /**
     * 查询集合 数据集
     */
    @Override
    public Page<PSSystemDBCfg> searchDefault(PSSystemDBCfgSearchContext context) {
        Page<PSSystemDBCfg> pSSystemDBCfgs=pSSystemDBCfgFeignClient.searchDefault(context);
        return pSSystemDBCfgs;
    }

    @Override
    @Transactional
    public PSSystemDBCfg dynamicCall(String key, String action, PSSystemDBCfg et) {
        return et;
    }
}



