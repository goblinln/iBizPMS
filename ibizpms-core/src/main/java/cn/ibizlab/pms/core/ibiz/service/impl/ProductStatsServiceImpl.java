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
import cn.ibizlab.pms.core.ibiz.domain.ProductStats;
import cn.ibizlab.pms.core.ibiz.filter.ProductStatsSearchContext;
import cn.ibizlab.pms.core.ibiz.service.IProductStatsService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.ProductStatsMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[产品统计] 服务对象接口实现
 */
@Slf4j
@Service("ProductStatsServiceImpl")
public class ProductStatsServiceImpl extends ServiceImpl<ProductStatsMapper, ProductStats> implements IProductStatsService {


    protected int batchSize = 500;

    @Override
    @Transactional
    public boolean create(ProductStats et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<ProductStats> list) {
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(ProductStats et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<ProductStats> list) {
        updateBatchById(list, batchSize);
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
    public ProductStats get(Long key) {
        ProductStats et = getById(key);
        if(et == null){
            et = new ProductStats();
            et.setId(key);
        }
        else {
        }
        return et;
    }

    @Override
    public ProductStats getDraft(ProductStats et) {
        return et;
    }

    @Override
    public boolean checkKey(ProductStats et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public ProductStats getTestStats(ProductStats et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public boolean save(ProductStats et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(ProductStats et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<ProductStats> list) {
        List<ProductStats> create = new ArrayList<>();
        List<ProductStats> update = new ArrayList<>();
        for (ProductStats et : list) {
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
    public void saveBatch(List<ProductStats> list) {
        List<ProductStats> create = new ArrayList<>();
        List<ProductStats> update = new ArrayList<>();
        for (ProductStats et : list) {
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
     * 查询集合 DEFAULT
     */
    @Override
    public Page<ProductStats> searchDefault(ProductStatsSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProductStats> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProductStats>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 未关闭产品
     */
    @Override
    public Page<ProductStats> searchNoOpenProduct(ProductStatsSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProductStats> pages=baseMapper.searchNoOpenProduct(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProductStats>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 产品质量表
     */
    @Override
    public Page<ProductStats> searchProdctQuantiGird(ProductStatsSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProductStats> pages=baseMapper.searchProdctQuantiGird(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProductStats>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 产品投入表
     */
    @Override
    public Page<ProductStats> searchProductInputTable(ProductStatsSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProductStats> pages=baseMapper.searchProductInputTable(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProductStats>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 产品完成统计表
     */
    @Override
    public Page<ProductStats> searchProductcompletionstatistics(ProductStatsSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProductStats> pages=baseMapper.searchProductcompletionstatistics(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProductStats>(pages.getRecords(), context.getPageable(), pages.getTotal());
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



    public IProductStatsService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public ProductStats dynamicCall(Long key, String action, ProductStats et) {
        return et;
    }
}


