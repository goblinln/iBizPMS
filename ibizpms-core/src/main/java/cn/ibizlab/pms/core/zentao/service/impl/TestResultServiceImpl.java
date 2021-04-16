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
import cn.ibizlab.pms.core.zentao.domain.TestResult;
import cn.ibizlab.pms.core.zentao.filter.TestResultSearchContext;
import cn.ibizlab.pms.core.zentao.service.ITestResultService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.zentao.mapper.TestResultMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[测试结果] 服务对象接口实现
 */
@Slf4j
@Service("TestResultServiceImpl")
public class TestResultServiceImpl extends ServiceImpl<TestResultMapper, TestResult> implements ITestResultService {

    @Autowired
    cn.ibizlab.pms.core.zentao.runtime.TestResultRuntime testresultRuntime;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.ICaseService caseService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.ICompileService compileService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IJobService jobService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.ITestRunService testrunService;

    protected int batchSize = 500;

    @Override
    @Transactional
    public boolean create(TestResult et) {
        if(!testresultRuntime.isRtmodel()){
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
    public void createBatch(List<TestResult> list) {
        if(!testresultRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(TestResult et) {
        if(!testresultRuntime.isRtmodel()){
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
    public void updateBatch(List<TestResult> list) {
        if(!testresultRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        updateBatchById(list, batchSize);
    }

    @Override
    @Transactional
    public boolean sysUpdate(TestResult et) {
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
    public TestResult get(Long key) {
        TestResult et = getById(key);
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
    public TestResult sysGet(Long key) {
        TestResult et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public TestResult getDraft(TestResult et) {
        if(!testresultRuntime.isRtmodel()){
            fillParentData(et);
        }
        return et;
    }

    @Override
    public boolean checkKey(TestResult et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public boolean save(TestResult et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(TestResult et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<TestResult> list) {
        if(!testresultRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        List<TestResult> create = new ArrayList<>();
        List<TestResult> update = new ArrayList<>();
        for (TestResult et : list) {
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
    public void saveBatch(List<TestResult> list) {
        if(!testresultRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        List<TestResult> create = new ArrayList<>();
        List<TestResult> update = new ArrayList<>();
        for (TestResult et : list) {
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
    public List<TestResult> selectByIbizcase(Long id) {
        return baseMapper.selectByIbizcase(id);
    }
    @Override
    public void removeByIbizcase(Long id) {
        this.remove(new QueryWrapper<TestResult>().eq("case",id));
    }

	@Override
    public List<TestResult> selectByCompile(Long id) {
        return baseMapper.selectByCompile(id);
    }
    @Override
    public void removeByCompile(Long id) {
        this.remove(new QueryWrapper<TestResult>().eq("compile",id));
    }

	@Override
    public List<TestResult> selectByJob(Long id) {
        return baseMapper.selectByJob(id);
    }
    @Override
    public void removeByJob(Long id) {
        this.remove(new QueryWrapper<TestResult>().eq("job",id));
    }

	@Override
    public List<TestResult> selectByRun(Long id) {
        return baseMapper.selectByRun(id);
    }
    @Override
    public void removeByRun(Long id) {
        this.remove(new QueryWrapper<TestResult>().eq("run",id));
    }


    /**
     * 查询集合 CurTestRun
     */
    @Override
    public Page<TestResult> searchCurTestRun(TestResultSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<TestResult> pages=baseMapper.searchCurTestRun(context.getPages(),context,context.getSelectCond());
        return new PageImpl<TestResult>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 DEFAULT
     */
    @Override
    public Page<TestResult> searchDefault(TestResultSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<TestResult> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<TestResult>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }



    /**
     * 为当前实体填充父数据（外键值文本、外键值附加数据）
     * @param et
     */
    private void fillParentData(TestResult et){
        //实体关系[DER1N_ZT_TESTRESULT_ZT_CASE_CASE]
        if(!ObjectUtils.isEmpty(et.getIbizcase())){
            cn.ibizlab.pms.core.zentao.domain.Case ztcase=et.getZtcase();
            if(ObjectUtils.isEmpty(ztcase)){
                cn.ibizlab.pms.core.zentao.domain.Case majorEntity=caseService.get(et.getIbizcase());
                et.setZtcase(majorEntity);
                ztcase=majorEntity;
            }
            et.setVersion(ztcase.getVersion());
            et.setStory(ztcase.getStory());
            et.setTitle(ztcase.getTitle());
            et.setModulename(ztcase.getModulename());
            et.setModule(ztcase.getModule());
            et.setPrecondition(ztcase.getPrecondition());
            et.setProduct(ztcase.getProduct());
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
    public List<TestResult> getTestresultByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<TestResult> getTestresultByEntities(List<TestResult> entities) {
        List ids =new ArrayList();
        for(TestResult entity : entities){
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


    public ITestResultService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public TestResult dynamicCall(Long key, String action, TestResult et) {
        return et;
    }
}



