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
import cn.ibizlab.pms.core.ibiz.domain.DynaFilter;
import cn.ibizlab.pms.core.ibiz.filter.DynaFilterSearchContext;
import cn.ibizlab.pms.core.ibiz.service.IDynaFilterService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.DynaFilterMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[动态搜索栏] 服务对象接口实现
 */
@Slf4j
@Service("DynaFilterServiceImpl")
public class DynaFilterServiceImpl extends ServiceImpl<DynaFilterMapper, DynaFilter> implements IDynaFilterService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.ibiz.runtime.DynaFilterRuntime dynafilterRuntime;


    protected int batchSize = 500;

    @Override
    public List<DynaFilter> select(DynaFilterSearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<DynaFilter> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(DynaFilter et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getDynafilterid()), et);
        return true;
    }


    @Override
    @Transactional
    public boolean update(DynaFilter et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("dynafilterid", et.getDynafilterid()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getDynafilterid()), et);
        return true;
    }


    @Override
    @Transactional
    public boolean sysUpdate(DynaFilter et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("dynafilterid", et.getDynafilterid()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getDynafilterid()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(String key) {
        if(!dynafilterRuntime.isRtmodel()){
        }
        boolean result = removeById(key);
        return result ;
    }


    @Override
    @Transactional
    public DynaFilter get(String key) {
        DynaFilter et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), key);
        }
        else {
            if(!dynafilterRuntime.isRtmodel()){
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
    public DynaFilter sysGet(String key) {
        DynaFilter et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), key);
        }
        return et;
    }

    @Override
    public DynaFilter getDraft(DynaFilter et) {
        return et;
    }

    @Override
    public boolean checkKey(DynaFilter et) {
        return (!ObjectUtils.isEmpty(et.getDynafilterid())) && (!Objects.isNull(this.getById(et.getDynafilterid())));
    }
    @Override
    @Transactional
    public boolean save(DynaFilter et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(DynaFilter et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }




    public List<DynaFilter> selectDefault(DynaFilterSearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<DynaFilter> selectSimple(DynaFilterSearchContext context){
        return baseMapper.selectSimple(context, context.getSelectCond());
    }
    public List<DynaFilter> selectView(DynaFilterSearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 数据集
     */
    @Override
    public Page<DynaFilter> searchDefault(DynaFilterSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<DynaFilter> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<DynaFilter>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    public List<DynaFilter> getDynafilterByIds(List<String> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<DynaFilter> getDynafilterByEntities(List<DynaFilter> entities) {
        List ids =new ArrayList();
        for(DynaFilter entity : entities){
            Serializable id=entity.getDynafilterid();
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


    public IDynaFilterService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public DynaFilter dynamicCall(String key, String action, DynaFilter et) {
        return et;
    }
}



