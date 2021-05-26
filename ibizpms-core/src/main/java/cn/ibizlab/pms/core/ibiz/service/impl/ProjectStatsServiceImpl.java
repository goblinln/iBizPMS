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
import cn.ibizlab.pms.core.ibiz.domain.ProjectStats;
import cn.ibizlab.pms.core.ibiz.filter.ProjectStatsSearchContext;
import cn.ibizlab.pms.core.ibiz.service.IProjectStatsService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.ibiz.mapper.ProjectStatsMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[项目统计] 服务对象接口实现
 */
@Slf4j
@Service("ProjectStatsServiceImpl")
public class ProjectStatsServiceImpl extends ServiceImpl<ProjectStatsMapper, ProjectStats> implements IProjectStatsService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.ibiz.runtime.ProjectStatsRuntime projectstatsRuntime;


    protected int batchSize = 500;

    @Override
    public List<ProjectStats> select(ProjectStatsSearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProjectStats> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(ProjectStats et) {
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<ProjectStats> list) {
        if(projectstatsRuntime.isRtmodel()){
            list.forEach(item -> getProxyService().create(item));
        }else{
        this.saveBatch(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean update(ProjectStats et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<ProjectStats> list) {
        if(projectstatsRuntime.isRtmodel()){
            list.forEach(item-> getProxyService().update(item));
        }else{
        updateBatchById(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean sysUpdate(ProjectStats et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        if(!projectstatsRuntime.isRtmodel()){
        }
        boolean result = removeById(key);
        return result ;
    }

    @Override
    @Transactional
    public void removeBatch(Collection<Long> idList) {
        if(projectstatsRuntime.isRtmodel()){
            idList.forEach(id->getProxyService().remove(id));
        }else{
        removeByIds(idList);
        }
        
    }

    @Override
    @Transactional
    public ProjectStats get(Long key) {
        ProjectStats et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
            if(!projectstatsRuntime.isRtmodel()){
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
    public ProjectStats sysGet(Long key) {
        ProjectStats et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public ProjectStats getDraft(ProjectStats et) {
        return et;
    }

    @Override
    public boolean checkKey(ProjectStats et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public ProjectStats projectQualitySum(ProjectStats et) {
         return et ;
    }

    @Override
    @Transactional
    public boolean save(ProjectStats et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(ProjectStats et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<ProjectStats> list) {
        List<ProjectStats> create = new ArrayList<>();
        List<ProjectStats> update = new ArrayList<>();
        for (ProjectStats et : list) {
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
    public void saveBatch(List<ProjectStats> list) {
        List<ProjectStats> create = new ArrayList<>();
        List<ProjectStats> update = new ArrayList<>();
        for (ProjectStats et : list) {
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



    public List<ProjectStats> selectDefault(ProjectStatsSearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<ProjectStats> selectNoOpenProduct(ProjectStatsSearchContext context){
        return baseMapper.selectNoOpenProduct(context, context.getSelectCond());
    }
    public List<ProjectStats> selectProjectBugType(ProjectStatsSearchContext context){
        return baseMapper.selectProjectBugType(context, context.getSelectCond());
    }
    public List<ProjectStats> selectProjectInputStats(ProjectStatsSearchContext context){
        return baseMapper.selectProjectInputStats(context, context.getSelectCond());
    }
    public List<ProjectStats> selectProjectProgress(ProjectStatsSearchContext context){
        return baseMapper.selectProjectProgress(context, context.getSelectCond());
    }
    public List<ProjectStats> selectProjectQuality(ProjectStatsSearchContext context){
        return baseMapper.selectProjectQuality(context, context.getSelectCond());
    }
    public List<ProjectStats> selectProjectStoryStageStats(ProjectStatsSearchContext context){
        return baseMapper.selectProjectStoryStageStats(context, context.getSelectCond());
    }
    public List<ProjectStats> selectProjectStoryStatusStats(ProjectStatsSearchContext context){
        return baseMapper.selectProjectStoryStatusStats(context, context.getSelectCond());
    }
    public List<ProjectStats> selectProjectTaskCountByTaskStatus(ProjectStatsSearchContext context){
        return baseMapper.selectProjectTaskCountByTaskStatus(context, context.getSelectCond());
    }
    public List<ProjectStats> selectProjectTaskCountByType(ProjectStatsSearchContext context){
        return baseMapper.selectProjectTaskCountByType(context, context.getSelectCond());
    }
    public List<ProjectStats> selectTaskTime(ProjectStatsSearchContext context){
        return baseMapper.selectTaskTime(context, context.getSelectCond());
    }
    public List<ProjectStats> selectView(ProjectStatsSearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 DEFAULT
     */
    @Override
    public Page<ProjectStats> searchDefault(ProjectStatsSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProjectStats> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProjectStats>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 未关闭产品
     */
    @Override
    public Page<ProjectStats> searchNoOpenProduct(ProjectStatsSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProjectStats> pages=baseMapper.searchNoOpenProduct(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProjectStats>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 项目bug类型统计
     */
    @Override
    public Page<ProjectStats> searchProjectBugType(ProjectStatsSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProjectStats> pages=baseMapper.searchProjectBugType(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProjectStats>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 项目投入统计
     */
    @Override
    public Page<ProjectStats> searchProjectInputStats(ProjectStatsSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProjectStats> pages=baseMapper.searchProjectInputStats(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProjectStats>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 项目进度
     */
    @Override
    public Page<ProjectStats> searchProjectProgress(ProjectStatsSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProjectStats> pages=baseMapper.searchProjectProgress(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProjectStats>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 项目质量
     */
    @Override
    public Page<ProjectStats> searchProjectQuality(ProjectStatsSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProjectStats> pages=baseMapper.searchProjectQuality(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProjectStats>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 项目需求阶段统计
     */
    @Override
    public Page<ProjectStats> searchProjectStoryStageStats(ProjectStatsSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProjectStats> pages=baseMapper.searchProjectStoryStageStats(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProjectStats>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 项目需求状态统计
     */
    @Override
    public Page<ProjectStats> searchProjectStoryStatusStats(ProjectStatsSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProjectStats> pages=baseMapper.searchProjectStoryStatusStats(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProjectStats>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 项目任务统计(任务状态)
     */
    @Override
    public Page<ProjectStats> searchProjectTaskCountByTaskStatus(ProjectStatsSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProjectStats> pages=baseMapper.searchProjectTaskCountByTaskStatus(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProjectStats>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 项目任务类型统计
     */
    @Override
    public Page<ProjectStats> searchProjectTaskCountByType(ProjectStatsSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProjectStats> pages=baseMapper.searchProjectTaskCountByType(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProjectStats>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 任务工时消耗剩余查询
     */
    @Override
    public Page<ProjectStats> searchTaskTime(ProjectStatsSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ProjectStats> pages=baseMapper.searchTaskTime(context.getPages(),context,context.getSelectCond());
        return new PageImpl<ProjectStats>(pages.getRecords(), context.getPageable(), pages.getTotal());
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



    public IProjectStatsService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public ProjectStats dynamicCall(Long key, String action, ProjectStats et) {
        return et;
    }
}



