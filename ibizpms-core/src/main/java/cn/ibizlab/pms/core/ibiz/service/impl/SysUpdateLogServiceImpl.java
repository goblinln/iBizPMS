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
import cn.ibizlab.pms.util.errors.BadRequestAlertException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Lazy;
import cn.ibizlab.pms.core.ibiz.domain.SysUpdateLog;
import cn.ibizlab.pms.core.ibiz.filter.SysUpdateLogSearchContext;
import cn.ibizlab.pms.core.ibiz.service.ISysUpdateLogService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.SysUpdateLogMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[更新日志] 服务对象接口实现
 */
@Slf4j
@Service("SysUpdateLogServiceImpl")
public class SysUpdateLogServiceImpl extends ServiceImpl<SysUpdateLogMapper, SysUpdateLog> implements ISysUpdateLogService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.ibiz.runtime.SysUpdateLogRuntime sysupdatelogRuntime;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.ISysUpdateFeaturesService sysupdatefeaturesService;

    protected int batchSize = 500;

    @Override
    public List<SysUpdateLog> select(SysUpdateLogSearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<SysUpdateLog> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(SysUpdateLog et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getSysupdatelogid()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<SysUpdateLog> list) {
        if(sysupdatelogRuntime.isRtmodel()){
            list.forEach(item -> getProxyService().create(item));
        }else{
        for (SysUpdateLog et : list) {
            getProxyService().save(et);
        }
        }
        
    }

    @Override
    @Transactional
    public boolean update(SysUpdateLog et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("sys_update_logid", et.getSysupdatelogid()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getSysupdatelogid()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<SysUpdateLog> list) {
        if(sysupdatelogRuntime.isRtmodel()){
            list.forEach(item-> getProxyService().update(item));
        }else{
        updateBatchById(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean sysUpdate(SysUpdateLog et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("sys_update_logid", et.getSysupdatelogid()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getSysupdatelogid()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(String key) {
        if(!sysupdatelogRuntime.isRtmodel()){
        sysupdatefeaturesService.removeBySysupdatelogid(key);
        }
        boolean result = removeById(key);
        return result ;
    }

    @Override
    @Transactional
    public void removeBatch(Collection<String> idList) {
        if(sysupdatelogRuntime.isRtmodel()){
            idList.forEach(id->getProxyService().remove(id));
        }else{
        sysupdatefeaturesService.removeBySysupdatelogid(idList);
        removeByIds(idList);
        }
        
    }

    @Override
    @Transactional
    public SysUpdateLog get(String key) {
        SysUpdateLog et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), key);
        }
        else {
            if(!sysupdatelogRuntime.isRtmodel()){
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
    public SysUpdateLog sysGet(String key) {
        SysUpdateLog et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), key);
        }
        return et;
    }

    @Override
    public SysUpdateLog getDraft(SysUpdateLog et) {
        return et;
    }

    @Override
    public boolean checkKey(SysUpdateLog et) {
        return (!ObjectUtils.isEmpty(et.getSysupdatelogid())) && (!Objects.isNull(this.getById(et.getSysupdatelogid())));
    }
    @Override
    @Transactional
    public SysUpdateLog getLastUpdateInfo(SysUpdateLog et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public boolean save(SysUpdateLog et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(SysUpdateLog et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<SysUpdateLog> list) {
        List<SysUpdateLog> create = new ArrayList<>();
        List<SysUpdateLog> update = new ArrayList<>();
        for (SysUpdateLog et : list) {
            if (ObjectUtils.isEmpty(et.getSysupdatelogid()) || ObjectUtils.isEmpty(getById(et.getSysupdatelogid()))) {
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
    public void saveBatch(List<SysUpdateLog> list) {
        List<SysUpdateLog> create = new ArrayList<>();
        List<SysUpdateLog> update = new ArrayList<>();
        for (SysUpdateLog et : list) {
            if (ObjectUtils.isEmpty(et.getSysupdatelogid()) || ObjectUtils.isEmpty(getById(et.getSysupdatelogid()))) {
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



    public List<SysUpdateLog> selectDefault(SysUpdateLogSearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<SysUpdateLog> selectView(SysUpdateLogSearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 数据集
     */
    @Override
    public Page<SysUpdateLog> searchDefault(SysUpdateLogSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<SysUpdateLog> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<SysUpdateLog>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    public List<SysUpdateLog> getSysupdatelogByIds(List<String> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<SysUpdateLog> getSysupdatelogByEntities(List<SysUpdateLog> entities) {
        List ids =new ArrayList();
        for(SysUpdateLog entity : entities){
            Serializable id=entity.getSysupdatelogid();
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


    public ISysUpdateLogService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public SysUpdateLog dynamicCall(String key, String action, SysUpdateLog et) {
        return et;
    }
}



