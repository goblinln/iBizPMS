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
import cn.ibizlab.pms.core.ibizplugin.domain.IBIZProKeyword;
import cn.ibizlab.pms.core.ibizplugin.filter.IBIZProKeywordSearchContext;
import cn.ibizlab.pms.core.ibizplugin.service.IIBIZProKeywordService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import cn.ibizlab.pms.core.ibizplugin.client.IBIZProKeywordFeignClient;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 实体[关键字] 服务对象接口实现
 */
@Slf4j
@Service
public class IBIZProKeywordServiceImpl implements IIBIZProKeywordService {

    @Autowired
    IBIZProKeywordFeignClient iBIZProKeywordFeignClient;


    @Override
    public boolean create(IBIZProKeyword et) {
        IBIZProKeyword rt = iBIZProKeywordFeignClient.create(et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void createBatch(List<IBIZProKeyword> list){
        iBIZProKeywordFeignClient.createBatch(list) ;
    }

    @Override
    public boolean update(IBIZProKeyword et) {
        IBIZProKeyword rt = iBIZProKeywordFeignClient.update(et.getId(),et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt, et);
        return true;

    }

    public void updateBatch(List<IBIZProKeyword> list){
        iBIZProKeywordFeignClient.updateBatch(list) ;
    }

    @Override
    public boolean sysUpdate(IBIZProKeyword et) {
                                IBIZProKeyword rt = iBIZProKeywordFeignClient.update(et.getId(),et);
        if(rt==null)
            return false;

        CachedBeanCopier.copy(rt, et);
        return true;
    }

    @Override
    public boolean remove(String id) {
        boolean result=iBIZProKeywordFeignClient.remove(id) ;
        return result;
    }

    public void removeBatch(Collection<String> idList){
        iBIZProKeywordFeignClient.removeBatch(idList);
    }

    @Override
    public IBIZProKeyword get(String id) {
		IBIZProKeyword et=iBIZProKeywordFeignClient.get(id);
        if(et==null){
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), id);
        }
        else{
        }
        return  et;
    }

    @Override
    public IBIZProKeyword sysGet(String id) {
         return null;
    }

    @Override
    public IBIZProKeyword getDraft(IBIZProKeyword et) {
        et=iBIZProKeywordFeignClient.getDraft(et);
        return et;
    }

    @Override
    public boolean checkKey(IBIZProKeyword et) {
        return iBIZProKeywordFeignClient.checkKey(et);
    }
    @Override
    @Transactional
    public boolean save(IBIZProKeyword et) {
        boolean result = true;
        Object rt = iBIZProKeywordFeignClient.saveEntity(et);
        if(rt == null)
          return false;
        try {
            if (rt instanceof Map) {
                ObjectMapper mapper = new ObjectMapper();
                rt = mapper.readValue(mapper.writeValueAsString(rt), IBIZProKeyword.class);
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
    public void saveBatch(List<IBIZProKeyword> list) {
        iBIZProKeywordFeignClient.saveBatch(list) ;
    }





    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IBIZProKeyword> searchDefault(IBIZProKeywordSearchContext context) {
        Page<IBIZProKeyword> iBIZProKeywords=iBIZProKeywordFeignClient.searchDefault(context);
        return iBIZProKeywords;
    }

    @Override
    @Transactional
    public IBIZProKeyword dynamicCall(String key, String action, IBIZProKeyword et) {
        return et;
    }
}



