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
import cn.ibizlab.pms.core.zentao.domain.TaskEstimateStats;
import cn.ibizlab.pms.core.zentao.filter.TaskEstimateStatsSearchContext;
import cn.ibizlab.pms.core.zentao.service.ITaskEstimateStatsService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.zentao.mapper.TaskEstimateStatsMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[任务工时统计] 服务对象接口实现
 */
@Slf4j
@Service("TaskEstimateStatsServiceImpl")
public class TaskEstimateStatsServiceImpl extends ServiceImpl<TaskEstimateStatsMapper, TaskEstimateStats> implements ITaskEstimateStatsService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.zentao.runtime.TaskEstimateStatsRuntime taskestimatestatsRuntime;


    protected int batchSize = 500;

    @Override
    public List<TaskEstimateStats> select(TaskEstimateStatsSearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<TaskEstimateStats> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(TaskEstimateStats et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<TaskEstimateStats> list) {
        this.saveBatch(list, batchSize);
    }

    @Override
    @Transactional
    public boolean update(TaskEstimateStats et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<TaskEstimateStats> list) {
        updateBatchById(list, batchSize);
    }

    @Override
    @Transactional
    public boolean sysUpdate(TaskEstimateStats et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        if(!taskestimatestatsRuntime.isRtmodel()){
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
    public TaskEstimateStats get(Long key) {
        TaskEstimateStats et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
        if(!taskestimatestatsRuntime.isRtmodel()){
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
    public TaskEstimateStats sysGet(Long key) {
        TaskEstimateStats et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public TaskEstimateStats getDraft(TaskEstimateStats et) {
        return et;
    }

    @Override
    public boolean checkKey(TaskEstimateStats et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public boolean save(TaskEstimateStats et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(TaskEstimateStats et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<TaskEstimateStats> list) {
        List<TaskEstimateStats> create = new ArrayList<>();
        List<TaskEstimateStats> update = new ArrayList<>();
        for (TaskEstimateStats et : list) {
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
    public void saveBatch(List<TaskEstimateStats> list) {
        List<TaskEstimateStats> create = new ArrayList<>();
        List<TaskEstimateStats> update = new ArrayList<>();
        for (TaskEstimateStats et : list) {
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



    public List<TaskEstimateStats> selectActionMonth(TaskEstimateStatsSearchContext context){
        return baseMapper.selectActionMonth(context, context.getSelectCond());
    }
    public List<TaskEstimateStats> selectActionYear(TaskEstimateStatsSearchContext context){
        return baseMapper.selectActionYear(context, context.getSelectCond());
    }
    public List<TaskEstimateStats> selectDefault(TaskEstimateStatsSearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<TaskEstimateStats> selectTaskEstimateStatsSum(TaskEstimateStatsSearchContext context){
        return baseMapper.selectTaskEstimateStatsSum(context, context.getSelectCond());
    }
    public List<TaskEstimateStats> selectView(TaskEstimateStatsSearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 日志月
     */
    @Override
    public Page<TaskEstimateStats> searchActionMonth(TaskEstimateStatsSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<TaskEstimateStats> pages=baseMapper.searchActionMonth(context.getPages(),context,context.getSelectCond());
        return new PageImpl<TaskEstimateStats>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 日志年
     */
    @Override
    public Page<TaskEstimateStats> searchActionYear(TaskEstimateStatsSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<TaskEstimateStats> pages=baseMapper.searchActionYear(context.getPages(),context,context.getSelectCond());
        return new PageImpl<TaskEstimateStats>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 数据集
     */
    @Override
    public Page<TaskEstimateStats> searchDefault(TaskEstimateStatsSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<TaskEstimateStats> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<TaskEstimateStats>(pages.getRecords(), context.getPageable(), pages.getTotal());
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



    public ITaskEstimateStatsService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public TaskEstimateStats dynamicCall(Long key, String action, TaskEstimateStats et) {
        return et;
    }
}



