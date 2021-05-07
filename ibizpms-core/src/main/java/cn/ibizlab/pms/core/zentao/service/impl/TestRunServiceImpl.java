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
import cn.ibizlab.pms.core.zentao.domain.TestRun;
import cn.ibizlab.pms.core.zentao.filter.TestRunSearchContext;
import cn.ibizlab.pms.core.zentao.service.ITestRunService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.zentao.mapper.TestRunMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[测试运行] 服务对象接口实现
 */
@Slf4j
@Service("TestRunServiceImpl")
public class TestRunServiceImpl extends ServiceImpl<TestRunMapper, TestRun> implements ITestRunService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.zentao.runtime.TestRunRuntime testrunRuntime;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.ITestResultService testresultService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.ICaseService caseService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.ITestTaskService testtaskService;

    protected int batchSize = 500;

    @Override
    public List<TestRun> select(TestRunSearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<TestRun> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(TestRun et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        if(!testrunRuntime.isRtmodel()){
            testresultService.saveByRun(et.getId(), et.getTestresults());
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<TestRun> list) {
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(TestRun et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        if(!testrunRuntime.isRtmodel()){
            testresultService.saveByRun(et.getId(), et.getTestresults());
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<TestRun> list) {
        updateBatchById(list, batchSize);
    }

    @Override
    @Transactional
    public boolean sysUpdate(TestRun et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        if(!testrunRuntime.isRtmodel()){
            testresultService.removeByRun(key) ;
        }
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
    public TestRun get(Long key) {
        TestRun et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
            if(!testrunRuntime.isRtmodel()){
                et.setTestresults(testresultService.selectByRun(key));
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
    public TestRun sysGet(Long key) {
        TestRun et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public TestRun getDraft(TestRun et) {
        return et;
    }

    @Override
    public boolean checkKey(TestRun et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public boolean save(TestRun et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(TestRun et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<TestRun> list) {
        List<TestRun> create = new ArrayList<>();
        List<TestRun> update = new ArrayList<>();
        for (TestRun et : list) {
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
    public void saveBatch(List<TestRun> list) {
        List<TestRun> create = new ArrayList<>();
        List<TestRun> update = new ArrayList<>();
        for (TestRun et : list) {
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
    public List<TestRun> selectByIbizcase(Long id) {
        return baseMapper.selectByIbizcase(id);
    }
    @Override
    public void removeByIbizcase(Long id) {
        this.remove(new QueryWrapper<TestRun>().eq("case",id));
    }

	@Override
    public List<TestRun> selectByTask(Long id) {
        return baseMapper.selectByTask(id);
    }
    @Override
    public void removeByTask(Long id) {
        this.remove(new QueryWrapper<TestRun>().eq("task",id));
    }


    public List<TestRun> selectDefault(TestRunSearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<TestRun> selectView(TestRunSearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 DEFAULT
     */
    @Override
    public Page<TestRun> searchDefault(TestRunSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<TestRun> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<TestRun>(pages.getRecords(), context.getPageable(), pages.getTotal());
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
    public List<TestRun> getTestrunByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<TestRun> getTestrunByEntities(List<TestRun> entities) {
        List ids =new ArrayList();
        for(TestRun entity : entities){
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


    public ITestRunService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public TestRun dynamicCall(Long key, String action, TestRun et) {
        return et;
    }
}



