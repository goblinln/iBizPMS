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
import cn.ibizlab.pms.core.zentao.domain.TaskEstimate;
import cn.ibizlab.pms.core.zentao.filter.TaskEstimateSearchContext;
import cn.ibizlab.pms.core.zentao.service.ITaskEstimateService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.zentao.mapper.TaskEstimateMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[任务预计] 服务对象接口实现
 */
@Slf4j
@Service("TaskEstimateServiceImpl")
public class TaskEstimateServiceImpl extends ServiceImpl<TaskEstimateMapper, TaskEstimate> implements ITaskEstimateService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.zentao.runtime.TaskEstimateRuntime taskestimateRuntime;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.ITaskService taskService;

    protected int batchSize = 500;

    @Override
    public List<TaskEstimate> select(TaskEstimateSearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<TaskEstimate> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(TaskEstimate et) {
        if(!taskestimateRuntime.isRtmodel()){
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
    public void createBatch(List<TaskEstimate> list) {
        if(taskestimateRuntime.isRtmodel()){
            list.forEach(item -> getProxyService().create(item));
        }else{
            list.forEach(item->fillParentData(item));
        this.saveBatch(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean update(TaskEstimate et) {
        if(!taskestimateRuntime.isRtmodel()){
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
    public void updateBatch(List<TaskEstimate> list) {
        if(taskestimateRuntime.isRtmodel()){
            list.forEach(item-> getProxyService().update(item));
        }else{
            list.forEach(item->fillParentData(item));
        updateBatchById(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean sysUpdate(TaskEstimate et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        if(!taskestimateRuntime.isRtmodel()){
        }
        boolean result = removeById(key);
        return result ;
    }

    @Override
    @Transactional
    public void removeBatch(Collection<Long> idList) {
        if(taskestimateRuntime.isRtmodel()){
            idList.forEach(id->getProxyService().remove(id));
        }else{
        removeByIds(idList);
        }
        
    }

    @Override
    @Transactional
    public TaskEstimate get(Long key) {
        TaskEstimate et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
            if(!taskestimateRuntime.isRtmodel()){
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
    public TaskEstimate sysGet(Long key) {
        TaskEstimate et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public TaskEstimate getDraft(TaskEstimate et) {
        if(!taskestimateRuntime.isRtmodel()){
            fillParentData(et);
        }
        return et;
    }

    @Override
    public boolean checkKey(TaskEstimate et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public TaskEstimate pMEvaluation(TaskEstimate et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public boolean save(TaskEstimate et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(TaskEstimate et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<TaskEstimate> list) {
        if(!taskestimateRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        List<TaskEstimate> create = new ArrayList<>();
        List<TaskEstimate> update = new ArrayList<>();
        for (TaskEstimate et : list) {
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
    public void saveBatch(List<TaskEstimate> list) {
        if(!taskestimateRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        List<TaskEstimate> create = new ArrayList<>();
        List<TaskEstimate> update = new ArrayList<>();
        for (TaskEstimate et : list) {
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
    public List<TaskEstimate> selectByTask(Long id) {
        return baseMapper.selectByTask(id);
    }
    @Override
    public void removeByTask(Long id) {
        this.remove(new QueryWrapper<TaskEstimate>().eq("task",id));
    }

    public ITaskEstimateService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
	@Override
    public void saveByTask(Long id,List<TaskEstimate> list) {
        if(list==null)
            return;
        Set<Long> delIds=new HashSet<Long>();
        List<TaskEstimate> _update=new ArrayList<TaskEstimate>();
        List<TaskEstimate> _create=new ArrayList<TaskEstimate>();
        for(TaskEstimate before:selectByTask(id)){
            delIds.add(before.getId());
        }
        for(TaskEstimate sub:list) {
            sub.setTask(id);
            if(ObjectUtils.isEmpty(sub.getId()))
                sub.setId((Long)sub.getDefaultKey(true));
            if(delIds.contains(sub.getId())) {
                delIds.remove(sub.getId());
                _update.add(sub);
            }
            else
                _create.add(sub);
        }
        if(_update.size()>0)
            getProxyService().updateBatch(_update);
        if(_create.size()>0)
            getProxyService().createBatch(_create);
        if(delIds.size()>0)
            getProxyService().removeBatch(delIds);
	}


    public List<TaskEstimate> selectActionMonth(TaskEstimateSearchContext context){
        return baseMapper.selectActionMonth(context, context.getSelectCond());
    }
    public List<TaskEstimate> selectActionYear(TaskEstimateSearchContext context){
        return baseMapper.selectActionYear(context, context.getSelectCond());
    }
    public List<TaskEstimate> selectDefault(TaskEstimateSearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<TaskEstimate> selectDefaults(TaskEstimateSearchContext context){
        return baseMapper.selectDefaults(context, context.getSelectCond());
    }
    public List<TaskEstimate> selectProjectActionMonth(TaskEstimateSearchContext context){
        return baseMapper.selectProjectActionMonth(context, context.getSelectCond());
    }
    public List<TaskEstimate> selectProjectActionYear(TaskEstimateSearchContext context){
        return baseMapper.selectProjectActionYear(context, context.getSelectCond());
    }
    public List<TaskEstimate> selectProjectTaskEstimate(TaskEstimateSearchContext context){
        return baseMapper.selectProjectTaskEstimate(context, context.getSelectCond());
    }
    public List<TaskEstimate> selectView(TaskEstimateSearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 日志月
     */
    @Override
    public Page<TaskEstimate> searchActionMonth(TaskEstimateSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<TaskEstimate> pages=baseMapper.searchActionMonth(context.getPages(),context,context.getSelectCond());
        return new PageImpl<TaskEstimate>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 日志年
     */
    @Override
    public Page<TaskEstimate> searchActionYear(TaskEstimateSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<TaskEstimate> pages=baseMapper.searchActionYear(context.getPages(),context,context.getSelectCond());
        return new PageImpl<TaskEstimate>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 DEFAULT
     */
    @Override
    public Page<TaskEstimate> searchDefault(TaskEstimateSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<TaskEstimate> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<TaskEstimate>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 DEFAULT1
     */
    @Override
    public Page<TaskEstimate> searchDefaults(TaskEstimateSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<TaskEstimate> pages=baseMapper.searchDefaults(context.getPages(),context,context.getSelectCond());
        return new PageImpl<TaskEstimate>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 日志月（项目）
     */
    @Override
    public Page<TaskEstimate> searchProjectActionMonth(TaskEstimateSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<TaskEstimate> pages=baseMapper.searchProjectActionMonth(context.getPages(),context,context.getSelectCond());
        return new PageImpl<TaskEstimate>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 日志年（项目）
     */
    @Override
    public Page<TaskEstimate> searchProjectActionYear(TaskEstimateSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<TaskEstimate> pages=baseMapper.searchProjectActionYear(context.getPages(),context,context.getSelectCond());
        return new PageImpl<TaskEstimate>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 项目日志
     */
    @Override
    public Page<TaskEstimate> searchProjectTaskEstimate(TaskEstimateSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<TaskEstimate> pages=baseMapper.searchProjectTaskEstimate(context.getPages(),context,context.getSelectCond());
        return new PageImpl<TaskEstimate>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }



    /**
     * 为当前实体填充父数据（外键值文本、外键值附加数据）
     * @param et
     */
    private void fillParentData(TaskEstimate et){
        //实体关系[DER1N_ZT_TASKESTIMATE_ZT_TASK_TASK]
        if(!ObjectUtils.isEmpty(et.getTask())){
            cn.ibizlab.pms.core.zentao.domain.Task zttask=et.getZttask();
            if(ObjectUtils.isEmpty(zttask)){
                cn.ibizlab.pms.core.zentao.domain.Task majorEntity=taskService.get(et.getTask());
                et.setZttask(majorEntity);
                zttask=majorEntity;
            }
            et.setTaskspecies(zttask.getTaskspecies());
            et.setTaskname(zttask.getName());
            et.setProjectname(zttask.getProjectname());
            et.setType(zttask.getType());
            et.setDeleted(zttask.getDeleted());
            et.setProject(zttask.getProject());
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
    public List<TaskEstimate> getTaskestimateByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<TaskEstimate> getTaskestimateByEntities(List<TaskEstimate> entities) {
        List ids =new ArrayList();
        for(TaskEstimate entity : entities){
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


    @Override
    @Transactional
    public TaskEstimate dynamicCall(Long key, String action, TaskEstimate et) {
        return et;
    }
}



