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
import cn.ibizlab.pms.core.ibiz.domain.ProductLine;
import cn.ibizlab.pms.core.ibiz.filter.ProductLineSearchContext;
import cn.ibizlab.pms.core.ibiz.service.IProductLineService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.ProductLineMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[产品线（废弃）] 服务对象接口实现
 */
@Slf4j
@Service("ProductLineServiceImpl")
public class ProductLineServiceImpl extends ServiceImpl<ProductLineMapper, ProductLine> implements IProductLineService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.ibiz.runtime.ProductLineRuntime productlineRuntime;


    protected int batchSize = 500;

    @Override
    public List<ProductLine> select(ProductLineSearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        return searchDefault(context).getContent();
    }

    @Override
    @Transactional
    public boolean create(ProductLine et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getProductlineid()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<ProductLine> list) {
        if(productlineRuntime.isRtmodel()){
            list.forEach(item -> getProxyService().create(item));
        }else{
        this.saveBatch(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean update(ProductLine et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("ibz_productlineid", et.getProductlineid()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getProductlineid()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<ProductLine> list) {
        if(productlineRuntime.isRtmodel()){
            list.forEach(item-> getProxyService().update(item));
        }else{
        updateBatchById(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean sysUpdate(ProductLine et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("ibz_productlineid", et.getProductlineid()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getProductlineid()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(String key) {
        if(!productlineRuntime.isRtmodel()){
        }
        boolean result = removeById(key);
        return result ;
    }

    @Override
    @Transactional
    public void removeBatch(Collection<String> idList) {
        if(productlineRuntime.isRtmodel()){
            idList.forEach(id->getProxyService().remove(id));
        }else{
        removeByIds(idList);
        }
        
    }

    @Override
    @Transactional
    public ProductLine get(String key) {
        ProductLine et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), key);
        }
        else {
            if(!productlineRuntime.isRtmodel()){
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
    public ProductLine sysGet(String key) {
        ProductLine et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), key);
        }
        return et;
    }

    @Override
    public ProductLine getDraft(ProductLine et) {
        return et;
    }

    @Override
    public boolean checkKey(ProductLine et) {
        return (!ObjectUtils.isEmpty(et.getProductlineid())) && (!Objects.isNull(this.getById(et.getProductlineid())));
    }
    @Override
    @Transactional
    public boolean save(ProductLine et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(ProductLine et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<ProductLine> list) {
        List<ProductLine> create = new ArrayList<>();
        List<ProductLine> update = new ArrayList<>();
        for (ProductLine et : list) {
            if (ObjectUtils.isEmpty(et.getProductlineid()) || ObjectUtils.isEmpty(getById(et.getProductlineid()))) {
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
    public void saveBatch(List<ProductLine> list) {
        List<ProductLine> create = new ArrayList<>();
        List<ProductLine> update = new ArrayList<>();
        for (ProductLine et : list) {
            if (ObjectUtils.isEmpty(et.getProductlineid()) || ObjectUtils.isEmpty(getById(et.getProductlineid()))) {
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



    public List<ProductLine> selectDefault(ProductLineSearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<ProductLine> selectView(ProductLineSearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 DEFAULT
     */
    @Override
    public Page<ProductLine> searchDefault(ProductLineSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProductLine> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProductLine>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    public List<ProductLine> getProductlineByIds(List<String> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<ProductLine> getProductlineByEntities(List<ProductLine> entities) {
        List ids =new ArrayList();
        for(ProductLine entity : entities){
            Serializable id=entity.getProductlineid();
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


    public IProductLineService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public ProductLine dynamicCall(String key, String action, ProductLine et) {
        return et;
    }
}



