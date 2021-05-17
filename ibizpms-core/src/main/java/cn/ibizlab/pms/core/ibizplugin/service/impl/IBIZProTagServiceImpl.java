package cn.ibizlab.pms.core.ibizplugin.service.impl;

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
import cn.ibizlab.pms.core.ibizplugin.domain.IBIZProTag;
import cn.ibizlab.pms.core.ibizplugin.filter.IBIZProTagSearchContext;
import cn.ibizlab.pms.core.ibizplugin.service.IIBIZProTagService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import cn.ibizlab.pms.core.ibizplugin.client.IBIZProTagFeignClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.alibaba.fastjson.JSONObject;
/**
 * 实体[标签] 服务对象接口实现
 */
@Slf4j
@Service
public class IBIZProTagServiceImpl implements IIBIZProTagService {

    @Autowired
    IBIZProTagFeignClient iBIZProTagFeignClient;


    @Override
    public boolean create(IBIZProTag et) {
        IBIZProTag rt = iBIZProTagFeignClient.create(et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void createBatch(List<IBIZProTag> list){
        iBIZProTagFeignClient.createBatch(list) ;
    }

    @Override
    public boolean update(IBIZProTag et) {
        IBIZProTag rt = iBIZProTagFeignClient.update(et.getId(),et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt, et);
        return true;

    }

    public void updateBatch(List<IBIZProTag> list){
        iBIZProTagFeignClient.updateBatch(list) ;
    }

    @Override
    public boolean sysUpdate(IBIZProTag et) {
                                IBIZProTag rt = iBIZProTagFeignClient.update(et.getId(),et);
        if(rt==null)
            return false;

        CachedBeanCopier.copy(rt, et);
        return true;
    }

    @Override
    public boolean remove(String id) {
        boolean result=iBIZProTagFeignClient.remove(id) ;
        return result;
    }

    public void removeBatch(Collection<String> idList){
        iBIZProTagFeignClient.removeBatch(idList);
    }

    @Override
    public IBIZProTag get(String id) {
		IBIZProTag et=iBIZProTagFeignClient.get(id);
        if(et==null){
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), id);
        }
        else{
        }
        return  et;
    }

    @Override
    public IBIZProTag sysGet(String id) {
         return null;
    }

    @Override
    public IBIZProTag getDraft(IBIZProTag et) {
        et=iBIZProTagFeignClient.getDraft(et);
        return et;
    }

    @Override
    public boolean checkKey(IBIZProTag et) {
        return iBIZProTagFeignClient.checkKey(et);
    }
    @Override
    @Transactional
    public boolean save(IBIZProTag et) {
        boolean result = true;
        Object rt = iBIZProTagFeignClient.saveEntity(et);
        if(rt == null)
          return false;
        try {
            if (rt instanceof Map) {
                ObjectMapper mapper = new ObjectMapper();
                rt = mapper.readValue(mapper.writeValueAsString(rt), IBIZProTag.class);
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
    public void saveBatch(List<IBIZProTag> list) {
        iBIZProTagFeignClient.saveBatch(list) ;
    }





    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IBIZProTag> searchDefault(IBIZProTagSearchContext context) {
        Page<IBIZProTag> iBIZProTags=iBIZProTagFeignClient.searchDefault(context);
        return iBIZProTags;
    }

    @Override
    @Transactional
    public IBIZProTag dynamicCall(String key, String action, IBIZProTag et) {
        return et;
    }
}



