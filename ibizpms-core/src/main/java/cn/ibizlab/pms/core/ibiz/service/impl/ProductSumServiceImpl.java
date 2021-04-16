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
import cn.ibizlab.pms.core.ibiz.domain.ProductSum;
import cn.ibizlab.pms.core.ibiz.filter.ProductSumSearchContext;
import cn.ibizlab.pms.core.ibiz.service.IProductSumService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.ProductSumMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[产品汇总表] 服务对象接口实现
 */
@Slf4j
@Service("ProductSumServiceImpl")
public class ProductSumServiceImpl extends ServiceImpl<ProductSumMapper, ProductSum> implements IProductSumService {

    @Autowired
    cn.ibizlab.pms.core.ibiz.runtime ProductSumRuntime;


    protected int batchSize = 500;

    @Override
    @Transactional
    public boolean create(ProductSum et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<ProductSum> list) {
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(ProductSum et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<ProductSum> list) {
        updateBatchById(list, batchSize);
    }

    @Override
    @Transactional
    public boolean sysUpdate(ProductSum et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        boolean result = removeById(key);
        return result ;
    }

    @Override
    @Transactional
    public void removeBatch(Collection<Long> idList) {
        removeByIds(idList);
    }

    @Override
    @Transactional
    public ProductSum get(Long key) {
        ProductSum et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
        }
        return et;
    }

     /**
     *  系统获取
     *  @return
     */
    @Override
    @Transactional
    public ProductSum sysGet(Long key) {
        ProductSum et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public ProductSum getDraft(ProductSum et) {
        return et;
    }

    @Override
    public boolean checkKey(ProductSum et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public boolean save(ProductSum et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(ProductSum et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<ProductSum> list) {
        List<ProductSum> create = new ArrayList<>();
        List<ProductSum> update = new ArrayList<>();
        for (ProductSum et : list) {
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
    public void saveBatch(List<ProductSum> list) {
        List<ProductSum> create = new ArrayList<>();
        List<ProductSum> update = new ArrayList<>();
        for (ProductSum et : list) {
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



    /**
     * 查询集合 数据集
     */
    @Override
    public Page<ProductSum> searchDefault(ProductSumSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProductSum> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProductSum>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 产品创建bug数及占比
     */
    @Override
    public Page<ProductSum> searchProductBugcnt_QA(ProductSumSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProductSum> pages=baseMapper.searchProductBugcnt_QA(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProductSum>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 产品创建需求占比
     */
    @Override
    public Page<ProductSum> searchProductCreateStory(ProductSumSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProductSum> pages=baseMapper.searchProductCreateStory(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProductSum>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 产品需求工时汇总
     */
    @Override
    public Page<ProductSum> searchProductStoryHoursSum(ProductSumSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProductSum> pages=baseMapper.searchProductStoryHoursSum(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProductSum>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 产品需求汇总查询
     */
    @Override
    public Page<ProductSum> searchProductStorySum(ProductSumSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProductSum> pages=baseMapper.searchProductStorySum(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProductSum>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 产品计划数和需求数
     */
    @Override
    public Page<ProductSum> searchProductStorycntAndPlancnt(ProductSumSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProductSum> pages=baseMapper.searchProductStorycntAndPlancnt(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProductSum>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 产品Bug类型统计
     */
    @Override
    public Page<ProductSum> searchProductSumBugType(ProductSumSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProductSum> pages=baseMapper.searchProductSumBugType(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProductSum>(pages.getRecords(), context.getPageable(), pages.getTotal());
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



    public IProductSumService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public ProductSum dynamicCall(Long key, String action, ProductSum et) {
        return et;
    }
}



