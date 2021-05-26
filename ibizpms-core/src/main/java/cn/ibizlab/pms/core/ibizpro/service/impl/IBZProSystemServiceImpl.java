package cn.ibizlab.pms.core.ibizpro.service.impl;

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
import cn.ibizlab.pms.core.ibizpro.domain.IBZProSystem;
import cn.ibizlab.pms.core.ibizpro.filter.IBZProSystemSearchContext;
import cn.ibizlab.pms.core.ibizpro.service.IIBZProSystemService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibizpro.mapper.IBZProSystemMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[运行生产系统] 服务对象接口实现
 */
@Slf4j
@Service("IBZProSystemServiceImpl")
public class IBZProSystemServiceImpl extends ServiceImpl<IBZProSystemMapper, IBZProSystem> implements IIBZProSystemService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.ibizpro.runtime.IBZProSystemRuntime ibzprosystemRuntime;


    protected int batchSize = 500;

    @Override
    public List<IBZProSystem> select(IBZProSystemSearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IBZProSystem> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(IBZProSystem et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbzprosystemid()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<IBZProSystem> list) {
        if(ibzprosystemRuntime.isRtmodel()){
            list.forEach(item -> getProxyService().create(item));
        }else{
        this.saveBatch(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean update(IBZProSystem et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("ibzpro_systemid", et.getIbzprosystemid()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbzprosystemid()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<IBZProSystem> list) {
        if(ibzprosystemRuntime.isRtmodel()){
            list.forEach(item-> getProxyService().update(item));
        }else{
        updateBatchById(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean sysUpdate(IBZProSystem et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("ibzpro_systemid", et.getIbzprosystemid()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getIbzprosystemid()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(String key) {
        if(!ibzprosystemRuntime.isRtmodel()){
        }
        boolean result = removeById(key);
        return result ;
    }

    @Override
    @Transactional
    public void removeBatch(Collection<String> idList) {
        if(ibzprosystemRuntime.isRtmodel()){
            idList.forEach(id->getProxyService().remove(id));
        }else{
        removeByIds(idList);
        }
        
    }

    @Override
    @Transactional
    public IBZProSystem get(String key) {
        IBZProSystem et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), key);
        }
        else {
            if(!ibzprosystemRuntime.isRtmodel()){
            }
        }
        return et;
    }

     /**
     *  系统获取
     *  @return
     */
    @Override
    @Transactional
    public IBZProSystem sysGet(String key) {
        IBZProSystem et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), key);
        }
        return et;
    }

    @Override
    public IBZProSystem getDraft(IBZProSystem et) {
        return et;
    }

    @Override
    public boolean checkKey(IBZProSystem et) {
        return (!ObjectUtils.isEmpty(et.getIbzprosystemid())) && (!Objects.isNull(this.getById(et.getIbzprosystemid())));
    }
    @Override
    @Transactional
    public boolean save(IBZProSystem et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(IBZProSystem et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<IBZProSystem> list) {
        List<IBZProSystem> create = new ArrayList<>();
        List<IBZProSystem> update = new ArrayList<>();
        for (IBZProSystem et : list) {
            if (ObjectUtils.isEmpty(et.getIbzprosystemid()) || ObjectUtils.isEmpty(getById(et.getIbzprosystemid()))) {
                create.add(et);
            } else {
                update.add(et);
            }
        }
        if (create.size() > 0) {
            getProxyService().createBatch(create);
        }
        if (update.size() > 0) {
            getProxyService().updateBatch(update);
        }
        return true;
    }

    @Override
    @Transactional
    public void saveBatch(List<IBZProSystem> list) {
        List<IBZProSystem> create = new ArrayList<>();
        List<IBZProSystem> update = new ArrayList<>();
        for (IBZProSystem et : list) {
            if (ObjectUtils.isEmpty(et.getIbzprosystemid()) || ObjectUtils.isEmpty(getById(et.getIbzprosystemid()))) {
                create.add(et);
            } else {
                update.add(et);
            }
        }
        if (create.size() > 0) {
            getProxyService().createBatch(create);
        }
        if (update.size() > 0) {
            getProxyService().updateBatch(update);
        }
    }



    public List<IBZProSystem> selectDefault(IBZProSystemSearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<IBZProSystem> selectView(IBZProSystemSearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 数据集
     */
    @Override
    public Page<IBZProSystem> searchDefault(IBZProSystemSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<IBZProSystem> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<IBZProSystem>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }







    @Override
    public List<JSONObject> select(String sql, Map param){
        return this.baseMapper.selectBySQL(sql,param);
    }

    @Override
    @Transactional
    public boolean execute(String sql , Map param){
        if (sql == null || sql.isEmpty()) {
            return false;
        }
        if (sql.toLowerCase().trim().startsWith("insert")) {
            return this.baseMapper.insertBySQL(sql,param);
        }
        if (sql.toLowerCase().trim().startsWith("update")) {
            return this.baseMapper.updateBySQL(sql,param);
        }
        if (sql.toLowerCase().trim().startsWith("delete")) {
            return this.baseMapper.deleteBySQL(sql,param);
        }
        log.warn("暂未支持的SQL语法");
        return true;
    }

    @Override
    public List<IBZProSystem> getIbzprosystemByIds(List<String> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<IBZProSystem> getIbzprosystemByEntities(List<IBZProSystem> entities) {
        List ids =new ArrayList();
        for(IBZProSystem entity : entities){
            Serializable id=entity.getIbzprosystemid();
            if(!ObjectUtils.isEmpty(id)){
                ids.add(id);
            }
        }
        if(ids.size()>0) {
            return this.listByIds(ids);
        }
        else {
            return entities;
        }
    }


    public IIBZProSystemService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public IBZProSystem dynamicCall(String key, String action, IBZProSystem et) {
        return et;
    }
}



