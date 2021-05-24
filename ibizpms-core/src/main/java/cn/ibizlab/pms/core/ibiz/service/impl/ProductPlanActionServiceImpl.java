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
import cn.ibizlab.pms.core.ibiz.domain.ProductPlanAction;
import cn.ibizlab.pms.core.ibiz.filter.ProductPlanActionSearchContext;
import cn.ibizlab.pms.core.ibiz.service.IProductPlanActionService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.ProductPlanActionMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[产品计划日志] 服务对象接口实现
 */
@Slf4j
@Service("ProductPlanActionServiceImpl")
public class ProductPlanActionServiceImpl extends ServiceImpl<ProductPlanActionMapper, ProductPlanAction> implements IProductPlanActionService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.ibiz.runtime.ProductPlanActionRuntime productplanactionRuntime;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.IProductPlanHistoryService productplanhistoryService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProductPlanService productplanService;

    protected int batchSize = 500;

    @Override
    public List<ProductPlanAction> select(ProductPlanActionSearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProductPlanAction> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(ProductPlanAction et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        if(!productplanactionRuntime.isRtmodel()){
            productplanhistoryService.saveByAction(et.getId(), et.getProductplanhistory());
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<ProductPlanAction> list) {
        if(productplanactionRuntime.isRtmodel()){
            list.forEach(item -> getProxyService().create(item));
        }else{
        this.saveBatch(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean update(ProductPlanAction et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        if(!productplanactionRuntime.isRtmodel()){
            productplanhistoryService.saveByAction(et.getId(), et.getProductplanhistory());
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<ProductPlanAction> list) {
        if(productplanactionRuntime.isRtmodel()){
            list.forEach(item-> getProxyService().update(item));
        }else{
        updateBatchById(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean sysUpdate(ProductPlanAction et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        if(!productplanactionRuntime.isRtmodel()){
            productplanhistoryService.removeByAction(key) ;
        }
        boolean result = removeById(key);
        return result ;
    }

    @Override
    @Transactional
    public void removeBatch(Collection<Long> idList) {
        if(productplanactionRuntime.isRtmodel()){
            idList.forEach(id->getProxyService().remove(id));
        }else{
        removeByIds(idList);
        }
        
    }

    @Override
    @Transactional
    public ProductPlanAction get(Long key) {
        ProductPlanAction et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
            if(!productplanactionRuntime.isRtmodel()){
                et.setProductplanhistory(productplanhistoryService.selectByAction(key));
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
    public ProductPlanAction sysGet(Long key) {
        ProductPlanAction et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public ProductPlanAction getDraft(ProductPlanAction et) {
        return et;
    }

    @Override
    public boolean checkKey(ProductPlanAction et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public boolean save(ProductPlanAction et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(ProductPlanAction et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<ProductPlanAction> list) {
        List<ProductPlanAction> create = new ArrayList<>();
        List<ProductPlanAction> update = new ArrayList<>();
        for (ProductPlanAction et : list) {
            if (ObjectUtils.isEmpty(et.getId()) || ObjectUtils.isEmpty(getById(et.getId()))) {
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
    public void saveBatch(List<ProductPlanAction> list) {
        List<ProductPlanAction> create = new ArrayList<>();
        List<ProductPlanAction> update = new ArrayList<>();
        for (ProductPlanAction et : list) {
            if (ObjectUtils.isEmpty(et.getId()) || ObjectUtils.isEmpty(getById(et.getId()))) {
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


	@Override
    public List<ProductPlanAction> selectByObjectid(Long id) {
        return baseMapper.selectByObjectid(id);
    }
    @Override
    public void removeByObjectid(Long id) {
        this.remove(new QueryWrapper<ProductPlanAction>().eq("objectid",id));
    }


    public List<ProductPlanAction> selectDefault(ProductPlanActionSearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<ProductPlanAction> selectSimple(ProductPlanActionSearchContext context){
        return baseMapper.selectSimple(context, context.getSelectCond());
    }
    public List<ProductPlanAction> selectView(ProductPlanActionSearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 数据集
     */
    @Override
    public Page<ProductPlanAction> searchDefault(ProductPlanActionSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProductPlanAction> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProductPlanAction>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    public List<ProductPlanAction> getProductplanactionByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<ProductPlanAction> getProductplanactionByEntities(List<ProductPlanAction> entities) {
        List ids =new ArrayList();
        for(ProductPlanAction entity : entities){
            Serializable id=entity.getId();
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


    /**
     * 获取searchContext
     * @return
     */
    public ProductPlanActionSearchContext getSearchContext(){
        return new ProductPlanActionSearchContext();
    }
    public IProductPlanActionService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public ProductPlanAction dynamicCall(Long key, String action, ProductPlanAction et) {
        return et;
    }
}



