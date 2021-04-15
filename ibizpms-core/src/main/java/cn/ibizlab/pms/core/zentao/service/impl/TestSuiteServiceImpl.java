package cn.ibizlab.pms.core.zentao.service.impl;

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
import cn.ibizlab.pms.core.zentao.domain.TestSuite;
import cn.ibizlab.pms.core.zentao.filter.TestSuiteSearchContext;
import cn.ibizlab.pms.core.zentao.service.ITestSuiteService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.zentao.mapper.TestSuiteMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[测试套件] 服务对象接口实现
 */
@Slf4j
@Service("TestSuiteServiceImpl")
public class TestSuiteServiceImpl extends ServiceImpl<TestSuiteMapper, TestSuite> implements ITestSuiteService {

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.ICaseService caseService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.ISuiteCaseService suitecaseService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProductService productService;

    protected int batchSize = 500;

        @Override
    @Transactional
    public boolean create(TestSuite et) {
  			return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.TestSuiteHelper.class).create(et);
    }

    @Override
    public void createBatch(List<TestSuite> list) {

    }
        @Override
    @Transactional
    public boolean update(TestSuite et) {
  			return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.TestSuiteHelper.class).edit(et);
    }

    @Override
    public void updateBatch(List<TestSuite> list) {

    }
        @Override
    @Transactional
    public boolean remove(Long key) {
  			return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.TestSuiteHelper.class).delete(key);
    }

    @Override
    public void removeBatch(Collection<Long> idList){
        if (idList != null && !idList.isEmpty()) {
            for (Long id : idList) {
                this.remove(id);
            }
        }
    }
    @Override
    @Transactional
    public TestSuite get(Long key) {
        TestSuite et = getById(key);
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
    public TestSuite sysGet(Long key) {
        TestSuite et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public TestSuite getDraft(TestSuite et) {
        return et;
    }

    @Override
    public boolean checkKey(TestSuite et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public TestSuite mobTestSuiteCount(TestSuite et) {
         return et ;
    }

    @Override
    @Transactional
    public boolean mobTestSuiteCountBatch(List<TestSuite> etList) {
        for(TestSuite et : etList) {
            mobTestSuiteCount(et);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean save(TestSuite et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(TestSuite et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<TestSuite> list) {
        List<TestSuite> create = new ArrayList<>();
        List<TestSuite> update = new ArrayList<>();
        for (TestSuite et : list) {
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
    public void saveBatch(List<TestSuite> list) {
        List<TestSuite> create = new ArrayList<>();
        List<TestSuite> update = new ArrayList<>();
        for (TestSuite et : list) {
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
    public List<TestSuite> selectByProduct(Long id) {
        return baseMapper.selectByProduct(id);
    }
    @Override
    public void removeByProduct(Long id) {
        this.remove(new QueryWrapper<TestSuite>().eq("product",id));
    }


    /**
     * 查询集合 DEFAULT
     */
    @Override
    public Page<TestSuite> searchDefault(TestSuiteSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<TestSuite> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<TestSuite>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 公开套件
     */
    @Override
    public Page<TestSuite> searchPublicTestSuite(TestSuiteSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<TestSuite> pages=baseMapper.searchPublicTestSuite(context.getPages(),context,context.getSelectCond());
        return new PageImpl<TestSuite>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    public List<TestSuite> getTestsuiteByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<TestSuite> getTestsuiteByEntities(List<TestSuite> entities) {
        List ids =new ArrayList();
        for(TestSuite entity : entities){
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


    public ITestSuiteService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public TestSuite dynamicCall(Long key, String action, TestSuite et) {
        return et;
    }
}



