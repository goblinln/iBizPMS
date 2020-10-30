package cn.ibizlab.pms.core.ibiz.service.impl;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Lazy;
import cn.ibizlab.pms.core.ibiz.domain.SysUpdateFeatures;
import cn.ibizlab.pms.core.ibiz.filter.SysUpdateFeaturesSearchContext;
import cn.ibizlab.pms.core.ibiz.service.ISysUpdateFeaturesService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.SysUpdateFeaturesMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[系统更新功能] 服务对象接口实现
 */
@Slf4j
@Service("SysUpdateFeaturesServiceImpl")
public class SysUpdateFeaturesServiceImpl extends ServiceImpl<SysUpdateFeaturesMapper, SysUpdateFeatures> implements ISysUpdateFeaturesService {

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.ISysUpdateLogService sysupdatelogService;

    protected int batchSize = 500;

    @Override
    @Transactional
    public boolean create(SysUpdateFeatures et) {
        if(!this.retBool(this.baseMapper.insert(et)))
            return false;
        CachedBeanCopier.copy(get(et.getSysupdatefeaturesid()),et);
        return true;
    }

    @Override
    public void createBatch(List<SysUpdateFeatures> list) {
        this.saveBatch(list,batchSize);
    }

    @Override
    @Transactional
    public boolean update(SysUpdateFeatures et) {
        if(!update(et,(Wrapper) et.getUpdateWrapper(true).eq("sys_update_featuresid",et.getSysupdatefeaturesid())))
            return false;
        CachedBeanCopier.copy(get(et.getSysupdatefeaturesid()),et);
        return true;
    }

    @Override
    public void updateBatch(List<SysUpdateFeatures> list) {
        updateBatchById(list,batchSize);
    }

    @Override
    @Transactional
    public boolean remove(String key) {
        boolean result=removeById(key);
        return result ;
    }

    @Override
    public void removeBatch(Collection<String> idList) {
        removeByIds(idList);
    }

    @Override
    @Transactional
    public SysUpdateFeatures get(String key) {
        SysUpdateFeatures et = getById(key);
        if(et==null){
            et=new SysUpdateFeatures();
            et.setSysupdatefeaturesid(key);
        }
        else{
        }
        return et;
    }

    @Override
    public SysUpdateFeatures getDraft(SysUpdateFeatures et) {
        return et;
    }

    @Override
    public boolean checkKey(SysUpdateFeatures et) {
        return (!ObjectUtils.isEmpty(et.getSysupdatefeaturesid()))&&(!Objects.isNull(this.getById(et.getSysupdatefeaturesid())));
    }
    @Override
    @Transactional
    public boolean save(SysUpdateFeatures et) {
        if(!saveOrUpdate(et))
            return false;
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(SysUpdateFeatures et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? this.update(et) : this.create(et);
        }
    }

    @Override
    public boolean saveBatch(Collection<SysUpdateFeatures> list) {
        saveOrUpdateBatch(list,batchSize);
        return true;
    }

    @Override
    public void saveBatch(List<SysUpdateFeatures> list) {
        saveOrUpdateBatch(list,batchSize);
    }


	@Override
    public List<SysUpdateFeatures> selectBySysUpdateLog(String sysupdatelogid) {
        return baseMapper.selectBySysUpdateLog(sysupdatelogid);
    }

    @Override
    public void removeBySysUpdateLog(String sysupdatelogid) {
        this.remove(new QueryWrapper<SysUpdateFeatures>().eq("sys_update_log",sysupdatelogid));
    }


    /**
     * 查询集合 数据集
     */
    @Override
    public Page<SysUpdateFeatures> searchDefault(SysUpdateFeaturesSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<SysUpdateFeatures> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<SysUpdateFeatures>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    public List<SysUpdateFeatures> getSysupdatefeaturesByIds(List<String> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<SysUpdateFeatures> getSysupdatefeaturesByEntities(List<SysUpdateFeatures> entities) {
        List ids =new ArrayList();
        for(SysUpdateFeatures entity : entities){
            Serializable id=entity.getSysupdatefeaturesid();
            if(!ObjectUtils.isEmpty(id)){
                ids.add(id);
            }
        }
        if(ids.size()>0)
           return this.listByIds(ids);
        else
           return entities;
    }

}



