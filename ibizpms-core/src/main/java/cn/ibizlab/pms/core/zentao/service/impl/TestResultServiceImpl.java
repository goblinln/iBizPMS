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
        fillParentData(et);
        if(!this.retBool(this.baseMapper.insert(et)))
            return false;
        CachedBeanCopier.copy(get(et.getId()),et);
        return true;
    }

    @Override
    public void createBatch(List<TestResult> list) {
        list.forEach(item->fillParentData(item));
        this.saveBatch(list,batchSize);
    }

    @Override
    @Transactional
    public boolean update(TestResult et) {
        fillParentData(et);
        if(!update(et,(Wrapper) et.getUpdateWrapper(true).eq("id",et.getId())))
            return false;
        CachedBeanCopier.copy(get(et.getId()),et);
        return true;
    }

    @Override
    public void updateBatch(List<TestResult> list) {
        list.forEach(item->fillParentData(item));
        updateBatchById(list,batchSize);
    }

    @Override
    @Transactional
    public boolean remove(BigInteger key) {
        boolean result=removeById(key);
        return result ;
    }

    @Override
    public void removeBatch(Collection<BigInteger> idList) {
        removeByIds(idList);
    }

    @Override
    @Transactional
    public TestResult get(BigInteger key) {
        TestResult et = getById(key);
        if(et==null){
            et=new TestResult();
            et.setId(key);
        }
        else{
        }
        return et;
    }

    @Override
    public TestResult getDraft(TestResult et) {
        fillParentData(et);
        return et;
    }

    @Override
    public boolean checkKey(TestResult et) {
        return (!ObjectUtils.isEmpty(et.getId()))&&(!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public boolean save(TestResult et) {
        if(!saveOrUpdate(et))
            return false;
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(TestResult et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? this.update(et) : this.create(et);
        }
    }

    @Override
    public boolean saveBatch(Collection<TestResult> list) {
        list.forEach(item->fillParentData(item));
        saveOrUpdateBatch(list,batchSize);
        return true;
    }

    @Override
    public void saveBatch(List<TestResult> list) {
        list.forEach(item->fillParentData(item));
        saveOrUpdateBatch(list,batchSize);
    }


	@Override
    public List<TestResult> selectByIbizcase(BigInteger id) {
        return baseMapper.selectByIbizcase(id);
    }

    @Override
    public void removeByIbizcase(BigInteger id) {
        this.remove(new QueryWrapper<TestResult>().eq("case",id));
    }

	@Override
    public List<TestResult> selectByCompile(BigInteger id) {
        return baseMapper.selectByCompile(id);
    }

    @Override
    public void removeByCompile(BigInteger id) {
        this.remove(new QueryWrapper<TestResult>().eq("compile",id));
    }

	@Override
    public List<TestResult> selectByJob(BigInteger id) {
        return baseMapper.selectByJob(id);
    }

    @Override
    public void removeByJob(BigInteger id) {
        this.remove(new QueryWrapper<TestResult>().eq("job",id));
    }

	@Override
    public List<TestResult> selectByRun(BigInteger id) {
        return baseMapper.selectByRun(id);
    }

    @Override
    public void removeByRun(BigInteger id) {
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
            et.setModule(ztcase.getModule());
            et.setProduct(ztcase.getProduct());
            et.setPrecondition(ztcase.getPrecondition());
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


}



