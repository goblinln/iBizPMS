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
import cn.ibizlab.pms.core.ibiz.domain.TestModule;
import cn.ibizlab.pms.core.ibiz.filter.TestModuleSearchContext;
import cn.ibizlab.pms.core.ibiz.service.ITestModuleService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.TestModuleMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[测试模块] 服务对象接口实现
 */
@Slf4j
@Service("TestModuleServiceImpl")
public class TestModuleServiceImpl extends ServiceImpl<TestModuleMapper, TestModule> implements ITestModuleService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.ibiz.runtime.TestModuleRuntime testmoduleRuntime;


    protected cn.ibizlab.pms.core.ibiz.service.ITestModuleService testmoduleService = this;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IBugService bugService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.ICaseService caseService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProductService productService;

    protected int batchSize = 500;

    @Override
    public List<TestModule> select(TestModuleSearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        return searchDefault(context).getContent();
    }

    @Override
    @Transactional
    public boolean create(TestModule et) {
        if(!testmoduleRuntime.isRtmodel()){
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
    public void createBatch(List<TestModule> list) {
        if(testmoduleRuntime.isRtmodel()){
            list.forEach(item -> getProxyService().create(item));
        }else{
            list.forEach(item->fillParentData(item));
        for (TestModule et : list) {
            getProxyService().save(et);
        }
        }
        
    }

    @Override
    @Transactional
    public boolean update(TestModule et) {
        if(!testmoduleRuntime.isRtmodel()){
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
    public void updateBatch(List<TestModule> list) {
        if(testmoduleRuntime.isRtmodel()){
            list.forEach(item-> getProxyService().update(item));
        }else{
            list.forEach(item->fillParentData(item));
        for (TestModule et : list) {
            getProxyService().update(et);
        }
        }
        
    }

    @Override
    @Transactional
    public boolean sysUpdate(TestModule et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        if(!testmoduleRuntime.isRtmodel()){
        }
        boolean result = removeById(key);
        return result ;
    }

    @Override
    @Transactional
    public void removeBatch(Collection<Long> idList) {
        if(testmoduleRuntime.isRtmodel()){
            idList.forEach(id->getProxyService().remove(id));
        }else{
        removeByIds(idList);
        }
        
    }

    @Override
    @Transactional
    public TestModule get(Long key) {
        TestModule et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
            if(!testmoduleRuntime.isRtmodel()){
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
    public TestModule sysGet(Long key) {
        TestModule et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public TestModule getDraft(TestModule et) {
        if(!testmoduleRuntime.isRtmodel()){
            fillParentData(et);
        }
        return et;
    }

    @Override
    public boolean checkKey(TestModule et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public TestModule fix(TestModule et) {
         return et ;
    }

    @Override
    @Transactional
    public TestModule removeModule(TestModule et) {
         return et ;
    }
    @Override
    @Transactional
    public boolean removeModuleBatch(List<TestModule> etList) {
        for(TestModule et : etList) {
            removeModule(et);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean save(TestModule et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(TestModule et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<TestModule> list) {
        if(!testmoduleRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        List<TestModule> create = new ArrayList<>();
        List<TestModule> update = new ArrayList<>();
        for (TestModule et : list) {
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
    public void saveBatch(List<TestModule> list) {
        if(!testmoduleRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        List<TestModule> create = new ArrayList<>();
        List<TestModule> update = new ArrayList<>();
        for (TestModule et : list) {
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
    public List<TestModule> selectByParent(Long id) {
        return baseMapper.selectByParent(id);
    }
    @Override
    public void removeByParent(Long id) {
        this.remove(new QueryWrapper<TestModule>().eq("parent",id));
    }

	@Override
    public List<TestModule> selectByRoot(Long id) {
        return baseMapper.selectByRoot(id);
    }
    @Override
    public void removeByRoot(Long id) {
        this.remove(new QueryWrapper<TestModule>().eq("root",id));
    }


    public List<TestModule> selectByPath(TestModuleSearchContext context){
        return baseMapper.selectByPath(context, context.getSelectCond());
    }
    public List<TestModule> selectDefault(TestModuleSearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<TestModule> selectParentModule(TestModuleSearchContext context){
        return baseMapper.selectParentModule(context, context.getSelectCond());
    }
    public List<TestModule> selectRoot(TestModuleSearchContext context){
        return baseMapper.selectRoot(context, context.getSelectCond());
    }
    public List<TestModule> selectRoot_NoBranch(TestModuleSearchContext context){
        return baseMapper.selectRoot_NoBranch(context, context.getSelectCond());
    }
    public List<TestModule> selectTestModule(TestModuleSearchContext context){
        return baseMapper.selectTestModule(context, context.getSelectCond());
    }
    public List<TestModule> selectView(TestModuleSearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 BYPATH
     */
    @Override
    public Page<TestModule> searchByPath(TestModuleSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<TestModule> pages=baseMapper.searchByPath(context.getPages(),context,context.getSelectCond());
        return new PageImpl<TestModule>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 DEFAULT
     */
    @Override
    public Page<TestModule> searchDefault(TestModuleSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<TestModule> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<TestModule>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 父模块
     */
    @Override
    public Page<TestModule> searchParentModule(TestModuleSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<TestModule> pages=baseMapper.searchParentModule(context.getPages(),context,context.getSelectCond());
        return new PageImpl<TestModule>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 根模块
     */
    @Override
    public Page<TestModule> searchRoot(TestModuleSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<TestModule> pages=baseMapper.searchRoot(context.getPages(),context,context.getSelectCond());
        return new PageImpl<TestModule>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 根模块_无分支
     */
    @Override
    public Page<TestModule> searchRoot_NoBranch(TestModuleSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<TestModule> pages=baseMapper.searchRoot_NoBranch(context.getPages(),context,context.getSelectCond());
        return new PageImpl<TestModule>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 TestModule
     */
    @Override
    public Page<TestModule> searchTestModule(TestModuleSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<TestModule> pages=baseMapper.searchTestModule(context.getPages(),context,context.getSelectCond());
        return new PageImpl<TestModule>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }



    /**
     * 为当前实体填充父数据（外键值文本、外键值附加数据）
     * @param et
     */
    private void fillParentData(TestModule et){
        //实体关系[DER1N_IBZ_TESTMODULE_IBZ_TESTMODULE_PARENT]
        if(!ObjectUtils.isEmpty(et.getParent())){
            cn.ibizlab.pms.core.ibiz.domain.TestModule parentmodule=et.getParentmodule();
            if(ObjectUtils.isEmpty(parentmodule)){
                cn.ibizlab.pms.core.ibiz.domain.TestModule majorEntity=testmoduleService.get(et.getParent());
                et.setParentmodule(majorEntity);
                parentmodule=majorEntity;
            }
            et.setParentname(parentmodule.getName());
        }
        //实体关系[DER1N_IBZ_TESTMODULE_ZT_PRODUCT_ROOT]
        if(!ObjectUtils.isEmpty(et.getRoot())){
            cn.ibizlab.pms.core.zentao.domain.Product roottest=et.getRoottest();
            if(ObjectUtils.isEmpty(roottest)){
                cn.ibizlab.pms.core.zentao.domain.Product majorEntity=productService.get(et.getRoot());
                et.setRoottest(majorEntity);
                roottest=majorEntity;
            }
            et.setRootname(roottest.getName());
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

    @Override
    public List<TestModule> getTestmoduleByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<TestModule> getTestmoduleByEntities(List<TestModule> entities) {
        List ids =new ArrayList();
        for(TestModule entity : entities){
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


    public ITestModuleService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public TestModule dynamicCall(Long key, String action, TestModule et) {
        return et;
    }
}



