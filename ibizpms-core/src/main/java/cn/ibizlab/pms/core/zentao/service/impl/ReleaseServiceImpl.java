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
import cn.ibizlab.pms.core.zentao.domain.Release;
import cn.ibizlab.pms.core.zentao.filter.ReleaseSearchContext;
import cn.ibizlab.pms.core.zentao.service.IReleaseService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.zentao.mapper.ReleaseMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[发布] 服务对象接口实现
 */
@Slf4j
@Service("ReleaseServiceImpl")
public class ReleaseServiceImpl extends ServiceImpl<ReleaseMapper, Release> implements IReleaseService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.zentao.runtime.ReleaseRuntime releaseRuntime;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IBranchService branchService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IBuildService buildService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProductService productService;

    protected int batchSize = 500;

    @Override
    public List<Release> select(ReleaseSearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Release> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(Release et) {
        if(!releaseRuntime.isRtmodel()){
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
    public void createBatch(List<Release> list) {
        if(!releaseRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(Release et) {
        if(!releaseRuntime.isRtmodel()){
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
    public void updateBatch(List<Release> list) {
        if(!releaseRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        updateBatchById(list, batchSize);
    }

    @Override
    @Transactional
    public boolean sysUpdate(Release et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        if(!releaseRuntime.isRtmodel()){
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
    public Release get(Long key) {
        Release et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
        if(!releaseRuntime.isRtmodel()){
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
    public Release sysGet(Long key) {
        Release et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public Release getDraft(Release et) {
        if(!releaseRuntime.isRtmodel()){
            fillParentData(et);
        }
        return et;
    }

    @Override
    @Transactional
    public Release activate(Release et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean activateBatch(List<Release> etList) {
        for(Release et : etList) {
            activate(et);
        }
        return true;
    }

    @Override
    @Transactional
    public Release batchUnlinkBug(Release et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean batchUnlinkBugBatch(List<Release> etList) {
        for(Release et : etList) {
            batchUnlinkBug(et);
        }
        return true;
    }

    @Override
    @Transactional
    public Release changeStatus(Release et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean changeStatusBatch(List<Release> etList) {
        for(Release et : etList) {
            changeStatus(et);
        }
        return true;
    }

    @Override
    public boolean checkKey(Release et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public Release linkBug(Release et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean linkBugBatch(List<Release> etList) {
        for(Release et : etList) {
            linkBug(et);
        }
        return true;
    }

    @Override
    @Transactional
    public Release linkBugbyBug(Release et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean linkBugbyBugBatch(List<Release> etList) {
        for(Release et : etList) {
            linkBugbyBug(et);
        }
        return true;
    }

    @Override
    @Transactional
    public Release linkBugbyLeftBug(Release et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean linkBugbyLeftBugBatch(List<Release> etList) {
        for(Release et : etList) {
            linkBugbyLeftBug(et);
        }
        return true;
    }

    @Override
    @Transactional
    public Release linkStory(Release et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean linkStoryBatch(List<Release> etList) {
        for(Release et : etList) {
            linkStory(et);
        }
        return true;
    }

    @Override
    @Transactional
    public Release mobReleaseCounter(Release et) {
         return et ;
    }

    @Override
    @Transactional
    public boolean mobReleaseCounterBatch(List<Release> etList) {
        for(Release et : etList) {
            mobReleaseCounter(et);
        }
        return true;
    }

    @Override
    @Transactional
    public Release oneClickRelease(Release et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean oneClickReleaseBatch(List<Release> etList) {
        for(Release et : etList) {
            oneClickRelease(et);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean save(Release et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(Release et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<Release> list) {
        if(!releaseRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        List<Release> create = new ArrayList<>();
        List<Release> update = new ArrayList<>();
        for (Release et : list) {
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
    public void saveBatch(List<Release> list) {
        if(!releaseRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        List<Release> create = new ArrayList<>();
        List<Release> update = new ArrayList<>();
        for (Release et : list) {
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
    public Release terminate(Release et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean terminateBatch(List<Release> etList) {
        for(Release et : etList) {
            terminate(et);
        }
        return true;
    }

    @Override
    @Transactional
    public Release unlinkBug(Release et) {
        //自定义代码
        return et;
    }
    @Override
    @Transactional
    public boolean unlinkBugBatch(List<Release> etList) {
        for(Release et : etList) {
            unlinkBug(et);
        }
        return true;
    }


	@Override
    public List<Release> selectByBranch(Long id) {
        return baseMapper.selectByBranch(id);
    }
    @Override
    public void removeByBranch(Long id) {
        this.remove(new QueryWrapper<Release>().eq("branch",id));
    }

	@Override
    public List<Release> selectByBuild(Long id) {
        return baseMapper.selectByBuild(id);
    }
    @Override
    public void removeByBuild(Long id) {
        this.remove(new QueryWrapper<Release>().eq("build",id));
    }

	@Override
    public List<Release> selectByProduct(Long id) {
        return baseMapper.selectByProduct(id);
    }
    @Override
    public void removeByProduct(Long id) {
        this.remove(new QueryWrapper<Release>().eq("product",id));
    }


    public List<Release> selectDefault(ReleaseSearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<Release> selectGetList(ReleaseSearchContext context){
        return baseMapper.selectGetList(context, context.getSelectCond());
    }
    public List<Release> selectReportRelease(ReleaseSearchContext context){
        return baseMapper.selectReportRelease(context, context.getSelectCond());
    }
    public List<Release> selectView(ReleaseSearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 DEFAULT
     */
    @Override
    public Page<Release> searchDefault(ReleaseSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Release> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Release>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 测试报告关联发布
     */
    @Override
    public Page<Release> searchReportRelease(ReleaseSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Release> pages=baseMapper.searchReportRelease(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Release>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }



    /**
     * 为当前实体填充父数据（外键值文本、外键值附加数据）
     * @param et
     */
    private void fillParentData(Release et){
        //实体关系[DER1N_ZT_RELEASE_ZT_BUILD_BUILD]
        if(!ObjectUtils.isEmpty(et.getBuild())){
            cn.ibizlab.pms.core.zentao.domain.Build ztbuild=et.getZtbuild();
            if(ObjectUtils.isEmpty(ztbuild)){
                cn.ibizlab.pms.core.zentao.domain.Build majorEntity=buildService.get(et.getBuild());
                et.setZtbuild(majorEntity);
                ztbuild=majorEntity;
            }
            et.setBuilder(ztbuild.getBuilder());
            et.setBuildname(ztbuild.getName());
            et.setBuilddate(ztbuild.getDate());
        }
        //实体关系[DER1N_ZT_RELEASE_ZT_PRODUCT_PRODUCT]
        if(!ObjectUtils.isEmpty(et.getProduct())){
            cn.ibizlab.pms.core.zentao.domain.Product ztproduct=et.getZtproduct();
            if(ObjectUtils.isEmpty(ztproduct)){
                cn.ibizlab.pms.core.zentao.domain.Product majorEntity=productService.get(et.getProduct());
                et.setZtproduct(majorEntity);
                ztproduct=majorEntity;
            }
            et.setProductname(ztproduct.getName());
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
    public List<Release> getReleaseByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<Release> getReleaseByEntities(List<Release> entities) {
        List ids =new ArrayList();
        for(Release entity : entities){
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


    public IReleaseService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public Release dynamicCall(Long key, String action, Release et) {
        return et;
    }
}



