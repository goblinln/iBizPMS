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
import cn.ibizlab.pms.core.zentao.domain.TestReport;
import cn.ibizlab.pms.core.zentao.filter.TestReportSearchContext;
import cn.ibizlab.pms.core.zentao.service.ITestReportService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.zentao.mapper.TestReportMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
    cn.ibizlab.pms.core.zentao.runtime.TestReportRuntime testreportRuntime;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProductService productService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProjectService projectService;

    protected int batchSize = 500;

    @Override
    public List<TestReport> select(TestReportSearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<TestReport> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(TestReport et) {
        if(!testreportRuntime.isRtmodel()){
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
    public void createBatch(List<TestReport> list) {
        if(!testreportRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(TestReport et) {
        if(!testreportRuntime.isRtmodel()){
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
    public void updateBatch(List<TestReport> list) {
        if(!testreportRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        updateBatchById(list, batchSize);
    }

    @Override
    @Transactional
    public boolean sysUpdate(TestReport et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        if(!testreportRuntime.isRtmodel()){
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
    public TestReport get(Long key) {
        TestReport et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
        if(!testreportRuntime.isRtmodel()){
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
    public TestReport sysGet(Long key) {
        TestReport et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public TestReport getDraft(TestReport et) {
        if(!testreportRuntime.isRtmodel()){
            fillParentData(et);
        }
        return et;
    }

    @Override
    public boolean checkKey(TestReport et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public TestReport getInfoTaskOvByTime(TestReport et) {
         return et ;
    }

    @Override
    @Transactional
    public boolean getInfoTaskOvByTimeBatch(List<TestReport> etList) {
        for(TestReport et : etList) {
            getInfoTaskOvByTime(et);
        }
        return true;
    }

    @Override
    @Transactional
    public TestReport getInfoTestTask(TestReport et) {
         return et ;
    }

    @Override
    @Transactional
    public boolean getInfoTestTaskBatch(List<TestReport> etList) {
        for(TestReport et : etList) {
            getInfoTestTask(et);
        }
        return true;
    }

    @Override
    @Transactional
    public TestReport getInfoTestTaskOvProject(TestReport et) {
         return et ;
    }

    @Override
    @Transactional
    public boolean getInfoTestTaskOvProjectBatch(List<TestReport> etList) {
        for(TestReport et : etList) {
            getInfoTestTaskOvProject(et);
        }
        return true;
    }

    @Override
    @Transactional
    public TestReport getInfoTestTaskProject(TestReport et) {
         return et ;
    }

    @Override
    @Transactional
    public boolean getInfoTestTaskProjectBatch(List<TestReport> etList) {
        for(TestReport et : etList) {
            getInfoTestTaskProject(et);
        }
        return true;
    }

    @Override
    @Transactional
    public TestReport getInfoTestTaskR(TestReport et) {
         return et ;
    }

    @Override
    @Transactional
    public boolean getInfoTestTaskRBatch(List<TestReport> etList) {
        for(TestReport et : etList) {
            getInfoTestTaskR(et);
        }
        return true;
    }

    @Override
    @Transactional
    public TestReport getInfoTestTaskS(TestReport et) {
         return et ;
    }

    @Override
    @Transactional
    public boolean getInfoTestTaskSBatch(List<TestReport> etList) {
        for(TestReport et : etList) {
            getInfoTestTaskS(et);
        }
        return true;
    }

    @Override
    @Transactional
    public TestReport getTestReportBasicInfo(TestReport et) {
         return et ;
    }

    @Override
    @Transactional
    public boolean getTestReportBasicInfoBatch(List<TestReport> etList) {
        for(TestReport et : etList) {
            getTestReportBasicInfo(et);
        }
        return true;
    }

    @Override
    @Transactional
    public TestReport getTestReportProject(TestReport et) {
         return et ;
    }

    @Override
    @Transactional
    public boolean getTestReportProjectBatch(List<TestReport> etList) {
        for(TestReport et : etList) {
            getTestReportProject(et);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean save(TestReport et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(TestReport et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<TestReport> list) {
        if(!testreportRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        List<TestReport> create = new ArrayList<>();
        List<TestReport> update = new ArrayList<>();
        for (TestReport et : list) {
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
    public void saveBatch(List<TestReport> list) {
        if(!testreportRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        List<TestReport> create = new ArrayList<>();
        List<TestReport> update = new ArrayList<>();
        for (TestReport et : list) {
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


    public List<TestReport> selectDefault(TestReportSearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<TestReport> selectView(TestReportSearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
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

    @Override
    public List<TestReport> getTestreportByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<TestReport> getTestreportByEntities(List<TestReport> entities) {
        List ids =new ArrayList();
        for(TestReport entity : entities){
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


    public ITestReportService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public TestReport dynamicCall(Long key, String action, TestReport et) {
        return et;
    }
}



