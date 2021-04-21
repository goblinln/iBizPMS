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
import cn.ibizlab.pms.core.ibizplugin.domain.IBIZProPlugin;
import cn.ibizlab.pms.core.ibizplugin.filter.IBIZProPluginSearchContext;
import cn.ibizlab.pms.core.ibizplugin.service.IIBIZProPluginService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import cn.ibizlab.pms.core.ibizplugin.client.IBIZProPluginFeignClient;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 实体[系统插件] 服务对象接口实现
 */
@Slf4j
@Service
public class IBIZProPluginServiceImpl implements IIBIZProPluginService {

    @Autowired
    IBIZProPluginFeignClient iBIZProPluginFeignClient;


    @Override
    public boolean create(IBIZProPlugin et) {
        IBIZProPlugin rt = iBIZProPluginFeignClient.create(et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt, et);
        return true;
    }

    public void createBatch(List<IBIZProPlugin> list){
        iBIZProPluginFeignClient.createBatch(list) ;
    }

    @Override
    public boolean update(IBIZProPlugin et) {
        IBIZProPlugin rt = iBIZProPluginFeignClient.update(et.getIbizpropluginid(),et);
        if(rt==null)
            return false;
        CachedBeanCopier.copy(rt, et);
        return true;

    }

    public void updateBatch(List<IBIZProPlugin> list){
        iBIZProPluginFeignClient.updateBatch(list) ;
    }

    @Override
    public boolean sysUpdate(IBIZProPlugin et) {
                                IBIZProPlugin rt = iBIZProPluginFeignClient.update(et.getIbizpropluginid(),et);
        if(rt==null)
            return false;

        CachedBeanCopier.copy(rt, et);
        return true;
    }

    @Override
    public boolean remove(String ibizpropluginid) {
        boolean result=iBIZProPluginFeignClient.remove(ibizpropluginid) ;
        return result;
    }

    public void removeBatch(Collection<String> idList){
        iBIZProPluginFeignClient.removeBatch(idList);
    }

    @Override
    public IBIZProPlugin get(String ibizpropluginid) {
		IBIZProPlugin et=iBIZProPluginFeignClient.get(ibizpropluginid);
        if(et==null){
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), ibizpropluginid);
        }
        else{
        }
        return  et;
    }

    @Override
    public IBIZProPlugin sysGet(String ibizpropluginid) {
         return null;
    }

    @Override
    public IBIZProPlugin getDraft(IBIZProPlugin et) {
        et=iBIZProPluginFeignClient.getDraft(et);
        return et;
    }

    @Override
    public boolean checkKey(IBIZProPlugin et) {
        return iBIZProPluginFeignClient.checkKey(et);
    }
    @Override
    @Transactional
    public boolean save(IBIZProPlugin et) {
        boolean result = true;
        Object rt = iBIZProPluginFeignClient.saveEntity(et);
        if(rt == null)
          return false;
        try {
            if (rt instanceof Map) {
                ObjectMapper mapper = new ObjectMapper();
                rt = mapper.readValue(mapper.writeValueAsString(rt), IBIZProPlugin.class);
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
    public void saveBatch(List<IBIZProPlugin> list) {
        iBIZProPluginFeignClient.saveBatch(list) ;
    }





    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IBIZProPlugin> searchDefault(IBIZProPluginSearchContext context) {
        Page<IBIZProPlugin> iBIZProPlugins=iBIZProPluginFeignClient.searchDefault(context);
        return iBIZProPlugins;
    }

    @Override
    @Transactional
    public IBIZProPlugin dynamicCall(String key, String action, IBIZProPlugin et) {
        return et;
    }
}



