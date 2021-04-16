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
import cn.ibizlab.pms.core.ibiz.domain.BugStats;
import cn.ibizlab.pms.core.ibiz.filter.BugStatsSearchContext;
import cn.ibizlab.pms.core.ibiz.service.IBugStatsService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.BugStatsMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[Bug统计] 服务对象接口实现
 */
@Slf4j
@Service("BugStatsServiceImpl")
public class BugStatsServiceImpl extends ServiceImpl<BugStatsMapper, BugStats> implements IBugStatsService {

    //@Autowired
    //cn.ibizlab.pms.core.ibiz.runtime.BugStatsRuntime bugstatsRuntime;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProductService productService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProjectService projectService;

    protected int batchSize = 500;

    @Override
    @Transactional
    public boolean create(BugStats et) {
        fillParentData(et);
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<BugStats> list) {
        list.forEach(item->fillParentData(item));
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(BugStats et) {
        fillParentData(et);
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<BugStats> list) {
        list.forEach(item->fillParentData(item));
        updateBatchById(list, batchSize);
    }

    @Override
    @Transactional
    public boolean sysUpdate(BugStats et) {
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
    public BugStats get(Long key) {
        BugStats et = getById(key);
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
    public BugStats sysGet(Long key) {
        BugStats et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public BugStats getDraft(BugStats et) {
        fillParentData(et);
        return et;
    }

    @Override
    public boolean checkKey(BugStats et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public boolean save(BugStats et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(BugStats et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<BugStats> list) {
        list.forEach(item->fillParentData(item));
        List<BugStats> create = new ArrayList<>();
        List<BugStats> update = new ArrayList<>();
        for (BugStats et : list) {
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
    public void saveBatch(List<BugStats> list) {
        list.forEach(item->fillParentData(item));
        List<BugStats> create = new ArrayList<>();
        List<BugStats> update = new ArrayList<>();
        for (BugStats et : list) {
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
    public List<BugStats> selectByProduct(Long id) {
        return baseMapper.selectByProduct(id);
    }
    @Override
    public void removeByProduct(Long id) {
        this.remove(new QueryWrapper<BugStats>().eq("product",id));
    }

	@Override
    public List<BugStats> selectByProject(Long id) {
        return baseMapper.selectByProject(id);
    }
    @Override
    public void removeByProject(Long id) {
        this.remove(new QueryWrapper<BugStats>().eq("project",id));
    }


    /**
     * 查询集合 Bug在每个解决方案的Bug数
     */
    @Override
    public Page<BugStats> searchBugCountInResolution(BugStatsSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<BugStats> pages=baseMapper.searchBugCountInResolution(context.getPages(),context,context.getSelectCond());
        return new PageImpl<BugStats>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 Bug完成表
     */
    @Override
    public Page<BugStats> searchBugResolvedBy(BugStatsSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<BugStats> pages=baseMapper.searchBugResolvedBy(context.getPages(),context,context.getSelectCond());
        return new PageImpl<BugStats>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 bug解决汇总表
     */
    @Override
    public Page<BugStats> searchBugResolvedGird(BugStatsSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<BugStats> pages=baseMapper.searchBugResolvedGird(context.getPages(),context,context.getSelectCond());
        return new PageImpl<BugStats>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 Bug指派表
     */
    @Override
    public Page<BugStats> searchBugassignedTo(BugStatsSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<BugStats> pages=baseMapper.searchBugassignedTo(context.getPages(),context,context.getSelectCond());
        return new PageImpl<BugStats>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 数据集
     */
    @Override
    public Page<BugStats> searchDefault(BugStatsSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<BugStats> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<BugStats>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 产品Bug解决方案汇总
     */
    @Override
    public Page<BugStats> searchProductBugResolutionStats(BugStatsSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<BugStats> pages=baseMapper.searchProductBugResolutionStats(context.getPages(),context,context.getSelectCond());
        return new PageImpl<BugStats>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 产品Bug状态汇总
     */
    @Override
    public Page<BugStats> searchProductBugStatusSum(BugStatsSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<BugStats> pages=baseMapper.searchProductBugStatusSum(context.getPages(),context,context.getSelectCond());
        return new PageImpl<BugStats>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 产品创建bug占比
     */
    @Override
    public Page<BugStats> searchProductCreateBug(BugStatsSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<BugStats> pages=baseMapper.searchProductCreateBug(context.getPages(),context,context.getSelectCond());
        return new PageImpl<BugStats>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 项目bug状态统计
     */
    @Override
    public Page<BugStats> searchProjectBugStatusCount(BugStatsSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<BugStats> pages=baseMapper.searchProjectBugStatusCount(context.getPages(),context,context.getSelectCond());
        return new PageImpl<BugStats>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }



    /**
     * 为当前实体填充父数据（外键值文本、外键值附加数据）
     * @param et
     */
    private void fillParentData(BugStats et){
        //实体关系[DER1N_IBZ_BUGSTATS_ZT_PRODUCT_PRODUCT]
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



    public IBugStatsService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public BugStats dynamicCall(Long key, String action, BugStats et) {
        return et;
    }
}



