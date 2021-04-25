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
import cn.ibizlab.pms.core.ibiz.domain.ProductModule;
import cn.ibizlab.pms.core.ibiz.filter.ProductModuleSearchContext;
import cn.ibizlab.pms.core.ibiz.service.IProductModuleService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.ProductModuleMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[需求模块] 服务对象接口实现
 */
@Slf4j
@Service("ProductModuleServiceImpl")
public class ProductModuleServiceImpl extends ServiceImpl<ProductModuleMapper, ProductModule> implements IProductModuleService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.ibiz.runtime.ProductModuleRuntime productmoduleRuntime;


    protected cn.ibizlab.pms.core.ibiz.service.IProductModuleService productmoduleService = this;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IStoryService storyService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProductService productService;

    protected int batchSize = 500;

    @Override
    public List<ProductModule> select(ProductModuleSearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProductModule> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(ProductModule et) {
        if(!productmoduleRuntime.isRtmodel()){
            fillParentData(et);
        }
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<ProductModule> list) {
        if(!productmoduleRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        for (ProductModule et : list) {
            getProxyService().save(et);
        }
    }

    @Override
    @Transactional
    public boolean update(ProductModule et) {
        if(!productmoduleRuntime.isRtmodel()){
            fillParentData(et);
        }
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<ProductModule> list) {
        if(!productmoduleRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        for (ProductModule et : list) {
            getProxyService().update(et);
        }
    }

    @Override
    @Transactional
    public boolean sysUpdate(ProductModule et) {
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
    public ProductModule get(Long key) {
        ProductModule et = getById(key);
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
    public ProductModule sysGet(Long key) {
        ProductModule et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public ProductModule getDraft(ProductModule et) {
        if(!productmoduleRuntime.isRtmodel()){
            fillParentData(et);
        }
        return et;
    }

    @Override
    public boolean checkKey(ProductModule et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public ProductModule fix(ProductModule et) {
         return et ;
    }

    @Override
    @Transactional
    public boolean fixBatch(List<ProductModule> etList) {
        for(ProductModule et : etList) {
            fix(et);
        }
        return true;
    }

    @Override
    @Transactional
    public ProductModule removeModule(ProductModule et) {
         return et ;
    }

    @Override
    @Transactional
    public boolean removeModuleBatch(List<ProductModule> etList) {
        for(ProductModule et : etList) {
            removeModule(et);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean save(ProductModule et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(ProductModule et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<ProductModule> list) {
        if(!productmoduleRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        List<ProductModule> create = new ArrayList<>();
        List<ProductModule> update = new ArrayList<>();
        for (ProductModule et : list) {
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
    public void saveBatch(List<ProductModule> list) {
        if(!productmoduleRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        List<ProductModule> create = new ArrayList<>();
        List<ProductModule> update = new ArrayList<>();
        for (ProductModule et : list) {
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
    @Transactional
    public ProductModule syncFromIBIZ(ProductModule et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean syncFromIBIZBatch(List<ProductModule> etList) {
        for(ProductModule et : etList) {
            syncFromIBIZ(et);
        }
        return true;
    }


	@Override
    public List<ProductModule> selectByParent(Long id) {
        return baseMapper.selectByParent(id);
    }
    @Override
    public void removeByParent(Long id) {
        this.remove(new QueryWrapper<ProductModule>().eq("parent",id));
    }

	@Override
    public List<ProductModule> selectByRoot(Long id) {
        return baseMapper.selectByRoot(id);
    }
    @Override
    public void removeByRoot(Long id) {
        this.remove(new QueryWrapper<ProductModule>().eq("root",id));
    }


    /**
     * 查询集合 BYPATH
     */
    @Override
    public Page<ProductModule> searchByPath(ProductModuleSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProductModule> pages=baseMapper.searchByPath(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProductModule>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 DEFAULT
     */
    @Override
    public Page<ProductModule> searchDefault(ProductModuleSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProductModule> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProductModule>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 父模块
     */
    @Override
    public Page<ProductModule> searchParentModule(ProductModuleSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProductModule> pages=baseMapper.searchParentModule(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProductModule>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 根模块
     */
    @Override
    public Page<ProductModule> searchRoot(ProductModuleSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProductModule> pages=baseMapper.searchRoot(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProductModule>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 根模块_无分支
     */
    @Override
    public Page<ProductModule> searchRoot_NoBranch(ProductModuleSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProductModule> pages=baseMapper.searchRoot_NoBranch(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProductModule>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 StoryModule
     */
    @Override
    public Page<ProductModule> searchStoryModule(ProductModuleSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProductModule> pages=baseMapper.searchStoryModule(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProductModule>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }



    /**
     * 为当前实体填充父数据（外键值文本、外键值附加数据）
     * @param et
     */
    private void fillParentData(ProductModule et){
        //实体关系[DER1N_IBZ_PRODUCTMODULE_IBZ_PRODUCTMODULE_PARENT]
        if(!ObjectUtils.isEmpty(et.getParent())){
            cn.ibizlab.pms.core.ibiz.domain.ProductModule parentmodule=et.getParentmodule();
            if(ObjectUtils.isEmpty(parentmodule)){
                cn.ibizlab.pms.core.ibiz.domain.ProductModule majorEntity=productmoduleService.get(et.getParent());
                et.setParentmodule(majorEntity);
                parentmodule=majorEntity;
            }
            et.setParentname(parentmodule.getName());
        }
        //实体关系[DER1N_IBZ_PRODUCTMODULE_ZT_PRODUCT_ROOT]
        if(!ObjectUtils.isEmpty(et.getRoot())){
            cn.ibizlab.pms.core.zentao.domain.Product ztproduct=et.getZtproduct();
            if(ObjectUtils.isEmpty(ztproduct)){
                cn.ibizlab.pms.core.zentao.domain.Product majorEntity=productService.get(et.getRoot());
                et.setZtproduct(majorEntity);
                ztproduct=majorEntity;
            }
            et.setRootname(ztproduct.getName());
        }
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



    public IProductModuleService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public ProductModule dynamicCall(Long key, String action, ProductModule et) {
        return et;
    }
}



