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
import cn.ibizlab.pms.core.zentao.domain.Product;
import cn.ibizlab.pms.core.zentao.filter.ProductSearchContext;
import cn.ibizlab.pms.core.zentao.service.IProductService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.zentao.mapper.ProductMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[产品] 服务对象接口实现
 */
@Slf4j
@Service("ProductServiceImpl")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.zentao.runtime.ProductRuntime productRuntime;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibizpro.service.IIbizproProductDailyService ibizproproductdailyService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibizpro.service.IIbizproProductMonthlyService ibizproproductmonthlyService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibizpro.service.IIbizproProductWeeklyService ibizproproductweeklyService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.IBugStatsService bugstatsService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.ICaseStatsService casestatsService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.IProductModuleService productmoduleService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.IPRODUCTTEAMService productteamService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.ITestModuleService testmoduleService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IBranchService branchService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IBugService bugService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IBuildService buildService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.ICaseService caseService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IDocLibService doclibService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IDocService docService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProductPlanService productplanService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProjectProductService projectproductService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProjectStoryService projectstoryService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IReleaseService releaseService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IStoryService storyService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.ISuiteCaseService suitecaseService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.ITestReportService testreportService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.ITestSuiteService testsuiteService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.ITestTaskService testtaskService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IModuleService moduleService;

    protected int batchSize = 500;

    @Override
    public List<Product> select(ProductSearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Product> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(Product et) {
        if(!productRuntime.isRtmodel()){
            fillParentData(et);
        }
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        if(!productRuntime.isRtmodel()){
            productteamService.saveByRoot(et.getId(), et.getProductteam());
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<Product> list) {
        if(productRuntime.isRtmodel()){
            list.forEach(item -> getProxyService().create(item));
        }else{
            list.forEach(item->fillParentData(item));
        this.saveBatch(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean update(Product et) {
        if(!productRuntime.isRtmodel()){
            fillParentData(et);
        }
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        if(!productRuntime.isRtmodel()){
            productteamService.saveByRoot(et.getId(), et.getProductteam());
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<Product> list) {
        if(productRuntime.isRtmodel()){
            list.forEach(item-> getProxyService().update(item));
        }else{
            list.forEach(item->fillParentData(item));
        updateBatchById(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean sysUpdate(Product et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        if(!productRuntime.isRtmodel()){
            productteamService.removeByRoot(key) ;
        }
        boolean result = removeById(key);
        return result ;
    }

    @Override
    @Transactional
    public void removeBatch(Collection<Long> idList) {
        if(productRuntime.isRtmodel()){
            idList.forEach(id->getProxyService().remove(id));
        }else{
        removeByIds(idList);
        }
        
    }

    @Override
    @Transactional
    public Product get(Long key) {
        Product et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
            if(!productRuntime.isRtmodel()){
                et.setProductteam(productteamService.selectByRoot(key));
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
    public Product sysGet(Long key) {
        Product et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public Product getDraft(Product et) {
        if(!productRuntime.isRtmodel()){
            fillParentData(et);
        }
        return et;
    }

    @Override
    @Transactional
    public Product cancelProductTop(Product et) {
         return et ;
    }

    @Override
    @Transactional
    public boolean cancelProductTopBatch(List<Product> etList) {
        for(Product et : etList) {
            cancelProductTop(et);
        }
        return true;
    }

    @Override
    public boolean checkKey(Product et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public Product close(Product et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean closeBatch(List<Product> etList) {
        for(Product et : etList) {
            close(et);
        }
        return true;
    }

    @Override
    @Transactional
    public Product mobProductCounter(Product et) {
         return et ;
    }

    @Override
    @Transactional
    public boolean mobProductCounterBatch(List<Product> etList) {
        for(Product et : etList) {
            mobProductCounter(et);
        }
        return true;
    }

    @Override
    @Transactional
    public Product mobProductTestCounter(Product et) {
         return et ;
    }

    @Override
    @Transactional
    public boolean mobProductTestCounterBatch(List<Product> etList) {
        for(Product et : etList) {
            mobProductTestCounter(et);
        }
        return true;
    }

    @Override
    @Transactional
    public Product productTop(Product et) {
         return et ;
    }

    @Override
    @Transactional
    public boolean productTopBatch(List<Product> etList) {
        for(Product et : etList) {
            productTop(et);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean save(Product et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(Product et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<Product> list) {
        if(!productRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        List<Product> create = new ArrayList<>();
        List<Product> update = new ArrayList<>();
        for (Product et : list) {
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
    public void saveBatch(List<Product> list) {
        if(!productRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        List<Product> create = new ArrayList<>();
        List<Product> update = new ArrayList<>();
        for (Product et : list) {
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
    public List<Product> selectByLine(Long id) {
        return baseMapper.selectByLine(id);
    }
    @Override
    public void removeByLine(Long id) {
        this.remove(new QueryWrapper<Product>().eq("line",id));
    }


    public List<Product> selectAllList(ProductSearchContext context){
        return baseMapper.selectAllList(context, context.getSelectCond());
    }
    public List<Product> selectAllProduct(ProductSearchContext context){
        return baseMapper.selectAllProduct(context, context.getSelectCond());
    }
    public List<Product> selectCheckNameOrCode(ProductSearchContext context){
        return baseMapper.selectCheckNameOrCode(context, context.getSelectCond());
    }
    public List<Product> selectCurDefault(ProductSearchContext context){
        return baseMapper.selectCurDefault(context, context.getSelectCond());
    }
    public List<Product> selectCurProject(ProductSearchContext context){
        return baseMapper.selectCurProject(context, context.getSelectCond());
    }
    public List<Product> selectCurUer(ProductSearchContext context){
        return baseMapper.selectCurUer(context, context.getSelectCond());
    }
    public List<Product> selectDefault(ProductSearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<Product> selectDeveloperQuery(ProductSearchContext context){
        return baseMapper.selectDeveloperQuery(context, context.getSelectCond());
    }
    public List<Product> selectESBulk(ProductSearchContext context){
        return baseMapper.selectESBulk(context, context.getSelectCond());
    }
    public List<Product> selectOpenQuery(ProductSearchContext context){
        return baseMapper.selectOpenQuery(context, context.getSelectCond());
    }
    public List<Product> selectPOQuery(ProductSearchContext context){
        return baseMapper.selectPOQuery(context, context.getSelectCond());
    }
    public List<Product> selectProductManagerQuery(ProductSearchContext context){
        return baseMapper.selectProductManagerQuery(context, context.getSelectCond());
    }
    public List<Product> selectProductPM(ProductSearchContext context){
        return baseMapper.selectProductPM(context, context.getSelectCond());
    }
    public List<Product> selectProductTeam(ProductSearchContext context){
        return baseMapper.selectProductTeam(context, context.getSelectCond());
    }
    public List<Product> selectQDQuery(ProductSearchContext context){
        return baseMapper.selectQDQuery(context, context.getSelectCond());
    }
    public List<Product> selectRDQuery(ProductSearchContext context){
        return baseMapper.selectRDQuery(context, context.getSelectCond());
    }
    public List<Product> selectSimple(ProductSearchContext context){
        return baseMapper.selectSimple(context, context.getSelectCond());
    }
    public List<Product> selectStoryCurProject(ProductSearchContext context){
        return baseMapper.selectStoryCurProject(context, context.getSelectCond());
    }
    public List<Product> selectView(ProductSearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 全部产品
     */
    @Override
    public Page<Product> searchAllList(ProductSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Product> pages=baseMapper.searchAllList(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Product>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 所有产品
     */
    @Override
    public Page<Product> searchAllProduct(ProductSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Product> pages=baseMapper.searchAllProduct(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Product>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 校验产品名称或产品代号是否已经存在
     */
    @Override
    public Page<Product> searchCheckNameOrCode(ProductSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Product> pages=baseMapper.searchCheckNameOrCode(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Product>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 默认查询
     */
    @Override
    public Page<Product> searchCurDefault(ProductSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Product> pages=baseMapper.searchCurDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Product>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 当前项目
     */
    @Override
    public Page<Product> searchCurProject(ProductSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Product> pages=baseMapper.searchCurProject(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Product>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 当前用户
     */
    @Override
    public Page<Product> searchCurUer(ProductSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Product> pages=baseMapper.searchCurUer(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Product>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 DEFAULT
     */
    @Override
    public Page<Product> searchDefault(ProductSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Product> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Product>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 研发人员（启用权限）
     */
    @Override
    public Page<Product> searchDeveloperQuery(ProductSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Product> pages=baseMapper.searchDeveloperQuery(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Product>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 ES批量的导入
     */
    @Override
    public Page<Product> searchESBulk(ProductSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Product> pages=baseMapper.searchESBulk(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Product>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 公开查询
     */
    @Override
    public Page<Product> searchOpenQuery(ProductSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Product> pages=baseMapper.searchOpenQuery(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Product>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 产品负责人（启用权限）
     */
    @Override
    public Page<Product> searchPOQuery(ProductSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Product> pages=baseMapper.searchPOQuery(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Product>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 产品经理（启用权限）
     */
    @Override
    public Page<Product> searchProductManagerQuery(ProductSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Product> pages=baseMapper.searchProductManagerQuery(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Product>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 产品总览
     */
    @Override
    public Page<Product> searchProductPM(ProductSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Product> pages=baseMapper.searchProductPM(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Product>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 产品团队
     */
    @Override
    public Page<Product> searchProductTeam(ProductSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Product> pages=baseMapper.searchProductTeam(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Product>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 测试负责人（启用权限）
     */
    @Override
    public Page<Product> searchQDQuery(ProductSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Product> pages=baseMapper.searchQDQuery(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Product>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 发布负责人（启用权限）
     */
    @Override
    public Page<Product> searchRDQuery(ProductSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Product> pages=baseMapper.searchRDQuery(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Product>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 当前项目
     */
    @Override
    public Page<Product> searchStoryCurProject(ProductSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Product> pages=baseMapper.searchStoryCurProject(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Product>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }



    /**
     * 为当前实体填充父数据（外键值文本、外键值附加数据）
     * @param et
     */
    private void fillParentData(Product et){
        //实体关系[DER1N_ZT_PRODUCT_ZT_MODULE_LINE]
        if(!ObjectUtils.isEmpty(et.getLine())){
            cn.ibizlab.pms.core.zentao.domain.Module moduleline=et.getModuleline();
            if(ObjectUtils.isEmpty(moduleline)){
                cn.ibizlab.pms.core.zentao.domain.Module majorEntity=moduleService.get(et.getLine());
                et.setModuleline(majorEntity);
                moduleline=majorEntity;
            }
            et.setLinename(moduleline.getName());
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
    public List<Product> getProductByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<Product> getProductByEntities(List<Product> entities) {
        List ids =new ArrayList();
        for(Product entity : entities){
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


    public IProductService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public Product dynamicCall(Long key, String action, Product et) {
        return et;
    }
}



