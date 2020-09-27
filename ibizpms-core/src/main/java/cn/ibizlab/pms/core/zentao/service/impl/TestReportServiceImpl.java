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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Lazy;
import cn.ibizlab.pms.core.zentao.domain.TestReport;
import cn.ibizlab.pms.core.zentao.filter.TestReportSearchContext;
import cn.ibizlab.pms.core.zentao.service.ITestReportService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.zentao.mapper.TestReportMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[测试报告] 服务对象接口实现
 */
@Slf4j
@Service("TestReportServiceImpl")
public class TestReportServiceImpl extends ServiceImpl<TestReportMapper, TestReport> implements ITestReportService {

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProductService productService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProjectService projectService;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.logic.ITestReportGetInfoTestTaskLogic getinfotesttaskLogic;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.logic.ITestReportGetInfoTestTaskOvProjectLogic getinfotesttaskovprojectLogic;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.logic.ITestReportGetInfoTestTaskProjectLogic getinfotesttaskprojectLogic;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.logic.ITestReportGetTestReportBasicInfoLogic gettestreportbasicinfoLogic;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.logic.ITestReportGetTestReportProjectLogic gettestreportprojectLogic;

    protected int batchSize = 500;

    @Override
    @Transactional
    public boolean create(TestReport et) {
        fillParentData(et);
        if(!this.retBool(this.baseMapper.insert(et)))
            return false;
        CachedBeanCopier.copy(get(et.getId()),et);
        return true;
    }

    @Override
    public void createBatch(List<TestReport> list) {
        list.forEach(item->fillParentData(item));
        this.saveBatch(list,batchSize);
    }

    @Override
    @Transactional
    public boolean update(TestReport et) {
        fillParentData(et);
        if(!update(et,(Wrapper) et.getUpdateWrapper(true).eq("id",et.getId())))
            return false;
        CachedBeanCopier.copy(get(et.getId()),et);
        return true;
    }

    @Override
    public void updateBatch(List<TestReport> list) {
        list.forEach(item->fillParentData(item));
        updateBatchById(list,batchSize);
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        boolean result=removeById(key);
        return result ;
    }

    @Override
    public void removeBatch(Collection<Long> idList) {
        removeByIds(idList);
    }

    @Override
    @Transactional
    public TestReport get(Long key) {
        TestReport et = getById(key);
        if(et==null){
            et=new TestReport();
            et.setId(key);
        }
        else{
        }
        return et;
    }

    @Override
    public TestReport getDraft(TestReport et) {
        fillParentData(et);
        return et;
    }

    @Override
    public boolean checkKey(TestReport et) {
        return (!ObjectUtils.isEmpty(et.getId()))&&(!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public TestReport getInfoTestTask(TestReport et) {
        getinfotesttaskLogic.execute(et);
         return et ;
    }

    @Override
    @Transactional
    public TestReport getInfoTestTaskOvProject(TestReport et) {
        getinfotesttaskovprojectLogic.execute(et);
         return et ;
    }

    @Override
    @Transactional
    public TestReport getInfoTestTaskProject(TestReport et) {
        getinfotesttaskprojectLogic.execute(et);
         return et ;
    }

    @Override
    @Transactional
    public TestReport getTestReportBasicInfo(TestReport et) {
        gettestreportbasicinfoLogic.execute(et);
         return et ;
    }

    @Override
    @Transactional
    public TestReport getTestReportProject(TestReport et) {
        gettestreportprojectLogic.execute(et);
         return et ;
    }

    @Override
    @Transactional
    public boolean save(TestReport et) {
        if(!saveOrUpdate(et))
            return false;
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(TestReport et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? this.update(et) : this.create(et);
        }
    }

    @Override
    public boolean saveBatch(Collection<TestReport> list) {
        list.forEach(item->fillParentData(item));
        saveOrUpdateBatch(list,batchSize);
        return true;
    }

    @Override
    public void saveBatch(List<TestReport> list) {
        list.forEach(item->fillParentData(item));
        saveOrUpdateBatch(list,batchSize);
    }


	@Override
    public List<TestReport> selectByProduct(Long id) {
        return baseMapper.selectByProduct(id);
    }

    @Override
    public void removeByProduct(Long id) {
        this.remove(new QueryWrapper<TestReport>().eq("product",id));
    }

	@Override
    public List<TestReport> selectByProject(Long id) {
        return baseMapper.selectByProject(id);
    }

    @Override
    public void removeByProject(Long id) {
        this.remove(new QueryWrapper<TestReport>().eq("project",id));
    }


    /**
     * 查询集合 DEFAULT
     */
    @Override
    public Page<TestReport> searchDefault(TestReportSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<TestReport> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<TestReport>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }



    /**
     * 为当前实体填充父数据（外键值文本、外键值附加数据）
     * @param et
     */
    private void fillParentData(TestReport et){
        //实体关系[DER1N_ZT_TESTREPORT_ZT_PRODUCT_PRODUCT]
        if(!ObjectUtils.isEmpty(et.getProduct())){
            cn.ibizlab.pms.core.zentao.domain.Product ztproduct=et.getZtproduct();
            if(ObjectUtils.isEmpty(ztproduct)){
                cn.ibizlab.pms.core.zentao.domain.Product majorEntity=productService.get(et.getProduct());
                et.setZtproduct(majorEntity);
                ztproduct=majorEntity;
            }
            et.setProductname(ztproduct.getName());
        }
        //实体关系[DER1N_ZT_TESTREPORT_ZT_PROJECT_PROJECT]
        if(!ObjectUtils.isEmpty(et.getProject())){
            cn.ibizlab.pms.core.zentao.domain.Project ztproject=et.getZtproject();
            if(ObjectUtils.isEmpty(ztproject)){
                cn.ibizlab.pms.core.zentao.domain.Project majorEntity=projectService.get(et.getProject());
                et.setZtproject(majorEntity);
                ztproject=majorEntity;
            }
            et.setProjectname(ztproject.getName());
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



