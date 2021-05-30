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
import cn.ibizlab.pms.core.zentao.domain.Task;
import cn.ibizlab.pms.core.zentao.filter.TaskSearchContext;
import cn.ibizlab.pms.core.zentao.service.ITaskService;

import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DEFieldCacheMap;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ibizlab.pms.core.zentao.mapper.TaskMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

/**
 * 实体[任务] 服务对象接口实现
 */
@Slf4j
@Service("TaskServiceImpl")
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements ITaskService {

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.zentao.runtime.TaskRuntime taskRuntime;

    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.ITaskTeamService taskteamService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IBugService bugService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IBurnService burnService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.ITaskEstimateService taskestimateService;

    protected cn.ibizlab.pms.core.zentao.service.ITaskService taskService = this;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.ibiz.service.IProjectModuleService projectmoduleService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProductPlanService productplanService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IProjectService projectService;
    @Autowired
    @Lazy
    protected cn.ibizlab.pms.core.zentao.service.IStoryService storyService;

    protected int batchSize = 500;

    @Override
    public List<Task> select(TaskSearchContext context) {
        context.setSize(Integer.MAX_VALUE);
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return pages.getRecords();
    }

    @Override
    @Transactional
    public boolean create(Task et) {
        if(!taskRuntime.isRtmodel()){
            fillParentData(et);
        }
        if(!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        if(!taskRuntime.isRtmodel()){
            taskteamService.saveByRoot(et.getId(), et.getTaskteams());
        }
        if(!taskRuntime.isRtmodel()){
            taskestimateService.saveByTask(et.getId(), et.getTaskestimates());
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void createBatch(List<Task> list) {
        if(taskRuntime.isRtmodel()){
            list.forEach(item -> getProxyService().create(item));
        }else{
            list.forEach(item->fillParentData(item));
        this.saveBatch(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean update(Task et) {
        if(!taskRuntime.isRtmodel()){
            fillParentData(et);
        }
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        if(!taskRuntime.isRtmodel()){
            taskteamService.saveByRoot(et.getId(), et.getTaskteams());
        }
        if(!taskRuntime.isRtmodel()){
            taskestimateService.saveByTask(et.getId(), et.getTaskestimates());
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public void updateBatch(List<Task> list) {
        if(taskRuntime.isRtmodel()){
            list.forEach(item-> getProxyService().update(item));
        }else{
            list.forEach(item->fillParentData(item));
        updateBatchById(list, batchSize);
        }
        
    }

    @Override
    @Transactional
    public boolean sysUpdate(Task et) {
        if(!update(et, (Wrapper) et.getUpdateWrapper(true).eq("id", et.getId()))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);
        return true;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        if(!taskRuntime.isRtmodel()){
            taskteamService.removeByRoot(key) ;
            taskestimateService.removeByTask(key) ;
        taskService.removeByParent(key);
        }
        boolean result = removeById(key);
        return result ;
    }

    @Override
    @Transactional
    public void removeBatch(Collection<Long> idList) {
        if(taskRuntime.isRtmodel()){
            idList.forEach(id->getProxyService().remove(id));
        }else{
        taskService.removeByParent(idList);
        removeByIds(idList);
        }
        
    }

    @Override
    @Transactional
    public Task get(Long key) {
        Task et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
            if(!taskRuntime.isRtmodel()){
                et.setTaskteams(taskteamService.selectByRoot(key));
                et.setTaskestimates(taskestimateService.selectByTask(key));
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
    public Task sysGet(Long key) {
        Task et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        return et;
    }

    @Override
    public Task getDraft(Task et) {
        if(!taskRuntime.isRtmodel()){
            fillParentData(et);
        }
        return et;
    }

    @Override
    @Transactional
    public Task activate(Task et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Task assignTo(Task et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Task cancel(Task et) {
        //自定义代码
        return et;
    }

    @Override
    public boolean checkKey(Task et) {
        return (!ObjectUtils.isEmpty(et.getId())) && (!Objects.isNull(this.getById(et.getId())));
    }
    @Override
    @Transactional
    public Task close(Task et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Task computeBeginAndEnd(Task et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Task computeHours4Multiple(Task et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Task computeWorkingHours(Task et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Task confirmStoryChange(Task et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Task createByCycle(Task et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Task createCycleTasks(Task et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Task delete(Task et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Task deleteEstimate(Task et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Task editEstimate(Task et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Task finish(Task et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Task getNextTeamUser(Task et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Task getTeamUserLeftActivity(Task et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Task getTeamUserLeftStart(Task et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Task getUsernames(Task et) {
         return et ;
    }

    @Override
    @Transactional
    public Task linkPlan(Task et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Task otherUpdate(Task et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Task pause(Task et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Task recordEstimate(Task et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Task recordTimZeroLeftAfterContinue(Task et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Task recordTimateZeroLeft(Task et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Task recordTimateZeroLeftAfterStart(Task et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Task restart(Task et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public boolean save(Task et) {
        if(!saveOrUpdate(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(Task et) {
        if (null == et) {
            return false;
        } else {
            return checkKey(et) ? getProxyService().update(et) : getProxyService().create(et);
        }
    }

    @Override
    @Transactional
    public boolean saveBatch(Collection<Task> list) {
        if(!taskRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        List<Task> create = new ArrayList<>();
        List<Task> update = new ArrayList<>();
        for (Task et : list) {
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
    public void saveBatch(List<Task> list) {
        if(!taskRuntime.isRtmodel()){
            list.forEach(item->fillParentData(item));
        }
        List<Task> create = new ArrayList<>();
        List<Task> update = new ArrayList<>();
        for (Task et : list) {
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
    public Task sendMessage(Task et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Task sendMsgPreProcess(Task et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Task start(Task et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Task taskFavorites(Task et) {
         return et ;
    }

    @Override
    @Transactional
    public Task taskForward(Task et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Task taskNFavorites(Task et) {
         return et ;
    }

    @Override
    @Transactional
    public Task updateParentStatus(Task et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Task updateRelatedPlanStatus(Task et) {
        //自定义代码
        return et;
    }

    @Override
    @Transactional
    public Task updateStoryVersion(Task et) {
         return et ;
    }


	@Override
    public List<Task> selectByModule(Long id) {
        return baseMapper.selectByModule(id);
    }
    @Override
    public void removeByModule(Long id) {
        this.remove(new QueryWrapper<Task>().eq("module",id));
    }

	@Override
    public List<Task> selectByFrombug(Long id) {
        return baseMapper.selectByFrombug(id);
    }
    @Override
    public void removeByFrombug(Long id) {
        this.remove(new QueryWrapper<Task>().eq("frombug",id));
    }

	@Override
    public List<Task> selectByPlan(Long id) {
        return baseMapper.selectByPlan(id);
    }
    @Override
    public void removeByPlan(Long id) {
        this.remove(new QueryWrapper<Task>().eq("plan",id));
    }

	@Override
    public List<Task> selectByProject(Long id) {
        return baseMapper.selectByProject(id);
    }
    @Override
    public void removeByProject(Long id) {
        this.remove(new QueryWrapper<Task>().eq("project",id));
    }

	@Override
    public List<Task> selectByStory(Long id) {
        return baseMapper.selectByStory(id);
    }
    @Override
    public void removeByStory(Long id) {
        this.remove(new QueryWrapper<Task>().eq("story",id));
    }

	@Override
    public List<Task> selectByParent(Long id) {
        return baseMapper.selectByParent(id);
    }
    @Override
    public void removeByParent(Collection<Long> ids) {
        this.remove(new QueryWrapper<Task>().in("parent",ids));
    }

    @Override
    public void removeByParent(Long id) {
        this.remove(new QueryWrapper<Task>().eq("parent",id));
    }


    public List<Task> selectAssignedToMyTask(TaskSearchContext context){
        return baseMapper.selectAssignedToMyTask(context, context.getSelectCond());
    }
    public List<Task> selectAssignedToMyTaskPc(TaskSearchContext context){
        return baseMapper.selectAssignedToMyTaskPc(context, context.getSelectCond());
    }
    public List<Task> selectBugTask(TaskSearchContext context){
        return baseMapper.selectBugTask(context, context.getSelectCond());
    }
    public List<Task> selectByModule(TaskSearchContext context){
        return baseMapper.selectByModule(context, context.getSelectCond());
    }
    public List<Task> selectChildDefault(TaskSearchContext context){
        return baseMapper.selectChildDefault(context, context.getSelectCond());
    }
    public List<Task> selectChildDefaultMore(TaskSearchContext context){
        return baseMapper.selectChildDefaultMore(context, context.getSelectCond());
    }
    public List<Task> selectChildTask(TaskSearchContext context){
        return baseMapper.selectChildTask(context, context.getSelectCond());
    }
    public List<Task> selectChildTaskTree(TaskSearchContext context){
        return baseMapper.selectChildTaskTree(context, context.getSelectCond());
    }
    public List<Task> selectCurFinishTask(TaskSearchContext context){
        return baseMapper.selectCurFinishTask(context, context.getSelectCond());
    }
    public List<Task> selectCurProjectTaskQuery(TaskSearchContext context){
        return baseMapper.selectCurProjectTaskQuery(context, context.getSelectCond());
    }
    public List<Task> selectDefault(TaskSearchContext context){
        return baseMapper.selectDefault(context, context.getSelectCond());
    }
    public List<Task> selectDefaultRow(TaskSearchContext context){
        return baseMapper.selectDefaultRow(context, context.getSelectCond());
    }
    public List<Task> selectESBulk(TaskSearchContext context){
        return baseMapper.selectESBulk(context, context.getSelectCond());
    }
    public List<Task> selectMyAgentTask(TaskSearchContext context){
        return baseMapper.selectMyAgentTask(context, context.getSelectCond());
    }
    public List<Task> selectMyAllTask(TaskSearchContext context){
        return baseMapper.selectMyAllTask(context, context.getSelectCond());
    }
    public List<Task> selectMyCompleteTask(TaskSearchContext context){
        return baseMapper.selectMyCompleteTask(context, context.getSelectCond());
    }
    public List<Task> selectMyCompleteTaskMobDaily(TaskSearchContext context){
        return baseMapper.selectMyCompleteTaskMobDaily(context, context.getSelectCond());
    }
    public List<Task> selectMyCompleteTaskMobMonthly(TaskSearchContext context){
        return baseMapper.selectMyCompleteTaskMobMonthly(context, context.getSelectCond());
    }
    public List<Task> selectMyCompleteTaskMonthlyZS(TaskSearchContext context){
        return baseMapper.selectMyCompleteTaskMonthlyZS(context, context.getSelectCond());
    }
    public List<Task> selectMyCompleteTaskZS(TaskSearchContext context){
        return baseMapper.selectMyCompleteTaskZS(context, context.getSelectCond());
    }
    public List<Task> selectMyCreateOrPartake(TaskSearchContext context){
        return baseMapper.selectMyCreateOrPartake(context, context.getSelectCond());
    }
    public List<Task> selectMyFavorites(TaskSearchContext context){
        return baseMapper.selectMyFavorites(context, context.getSelectCond());
    }
    public List<Task> selectMyPlansTaskMobMonthly(TaskSearchContext context){
        return baseMapper.selectMyPlansTaskMobMonthly(context, context.getSelectCond());
    }
    public List<Task> selectMyReProject(TaskSearchContext context){
        return baseMapper.selectMyReProject(context, context.getSelectCond());
    }
    public List<Task> selectMyTomorrowPlanTask(TaskSearchContext context){
        return baseMapper.selectMyTomorrowPlanTask(context, context.getSelectCond());
    }
    public List<Task> selectMyTomorrowPlanTaskMobDaily(TaskSearchContext context){
        return baseMapper.selectMyTomorrowPlanTaskMobDaily(context, context.getSelectCond());
    }
    public List<Task> selectNextWeekCompleteTaskMobZS(TaskSearchContext context){
        return baseMapper.selectNextWeekCompleteTaskMobZS(context, context.getSelectCond());
    }
    public List<Task> selectNextWeekCompleteTaskZS(TaskSearchContext context){
        return baseMapper.selectNextWeekCompleteTaskZS(context, context.getSelectCond());
    }
    public List<Task> selectNextWeekPlanCompleteTask(TaskSearchContext context){
        return baseMapper.selectNextWeekPlanCompleteTask(context, context.getSelectCond());
    }
    public List<Task> selectPlanTask(TaskSearchContext context){
        return baseMapper.selectPlanTask(context, context.getSelectCond());
    }
    public List<Task> selectProjectAppTask(TaskSearchContext context){
        return baseMapper.selectProjectAppTask(context, context.getSelectCond());
    }
    public List<Task> selectProjectTask(TaskSearchContext context){
        return baseMapper.selectProjectTask(context, context.getSelectCond());
    }
    public List<Task> selectRootTask(TaskSearchContext context){
        return baseMapper.selectRootTask(context, context.getSelectCond());
    }
    public List<Task> selectSimple(TaskSearchContext context){
        return baseMapper.selectSimple(context, context.getSelectCond());
    }
    public List<Task> selectTaskLinkPlan(TaskSearchContext context){
        return baseMapper.selectTaskLinkPlan(context, context.getSelectCond());
    }
    public List<Task> selectThisMonthCompleteTaskChoice(TaskSearchContext context){
        return baseMapper.selectThisMonthCompleteTaskChoice(context, context.getSelectCond());
    }
    public List<Task> selectThisWeekCompleteTask(TaskSearchContext context){
        return baseMapper.selectThisWeekCompleteTask(context, context.getSelectCond());
    }
    public List<Task> selectThisWeekCompleteTaskChoice(TaskSearchContext context){
        return baseMapper.selectThisWeekCompleteTaskChoice(context, context.getSelectCond());
    }
    public List<Task> selectThisWeekCompleteTaskMobZS(TaskSearchContext context){
        return baseMapper.selectThisWeekCompleteTaskMobZS(context, context.getSelectCond());
    }
    public List<Task> selectThisWeekCompleteTaskZS(TaskSearchContext context){
        return baseMapper.selectThisWeekCompleteTaskZS(context, context.getSelectCond());
    }
    public List<Task> selectTodoListTask(TaskSearchContext context){
        return baseMapper.selectTodoListTask(context, context.getSelectCond());
    }
    public List<Task> selectTypeGroup(TaskSearchContext context){
        return baseMapper.selectTypeGroup(context, context.getSelectCond());
    }
    public List<Task> selectTypeGroupPlan(TaskSearchContext context){
        return baseMapper.selectTypeGroupPlan(context, context.getSelectCond());
    }
    public List<Task> selectView(TaskSearchContext context){
        return baseMapper.selectView(context, context.getSelectCond());
    }


    /**
     * 查询集合 指派给我任务
     */
    @Override
    public Page<Task> searchAssignedToMyTask(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchAssignedToMyTask(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 指派给我任务（PC）
     */
    @Override
    public Page<Task> searchAssignedToMyTaskPc(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchAssignedToMyTaskPc(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 Bug相关任务
     */
    @Override
    public Page<Task> searchBugTask(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchBugTask(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 通过模块查询
     */
    @Override
    public Page<Task> searchByModule(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchByModule(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 数据查询（子任务）
     */
    @Override
    public Page<Task> searchChildDefault(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchChildDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 子任务（更多）
     */
    @Override
    public Page<Task> searchChildDefaultMore(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchChildDefaultMore(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 子任务
     */
    @Override
    public Page<Task> searchChildTask(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchChildTask(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 子任务（树）
     */
    @Override
    public Page<Task> searchChildTaskTree(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchChildTaskTree(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 用户年度完成任务
     */
    @Override
    public Page<Task> searchCurFinishTask(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchCurFinishTask(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 当前项目任务
     */
    @Override
    public Page<Task> searchCurProjectTaskQuery(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchCurProjectTaskQuery(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 DEFAULT
     */
    @Override
    public Page<Task> searchDefault(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchDefault(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 DefaultRow
     */
    @Override
    public Page<Task> searchDefaultRow(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchDefaultRow(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 ES批量的导入
     */
    @Override
    public Page<Task> searchESBulk(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchESBulk(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我代理的任务
     */
    @Override
    public Page<Task> searchMyAgentTask(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchMyAgentTask(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我相关的任务
     */
    @Override
    public Page<Task> searchMyAllTask(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchMyAllTask(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我完成的任务（汇报）
     */
    @Override
    public Page<Task> searchMyCompleteTask(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchMyCompleteTask(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我完成的任务（移动端日报）
     */
    @Override
    public Page<Task> searchMyCompleteTaskMobDaily(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchMyCompleteTaskMobDaily(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我完成的任务（移动端月报）
     */
    @Override
    public Page<Task> searchMyCompleteTaskMobMonthly(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchMyCompleteTaskMobMonthly(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我完成的任务（月报展示）
     */
    @Override
    public Page<Task> searchMyCompleteTaskMonthlyZS(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchMyCompleteTaskMonthlyZS(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我完成的任务（汇报）
     */
    @Override
    public Page<Task> searchMyCompleteTaskZS(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchMyCompleteTaskZS(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我创建或参与（权限）
     */
    @Override
    public Page<Task> searchMyCreateOrPartake(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchMyCreateOrPartake(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我的收藏
     */
    @Override
    public Page<Task> searchMyFavorites(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchMyFavorites(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我计划参与的任务（移动端月报）
     */
    @Override
    public Page<Task> searchMyPlansTaskMobMonthly(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchMyPlansTaskMobMonthly(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我相关项目（权限）
     */
    @Override
    public Page<Task> searchMyReProject(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchMyReProject(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我计划参与的任务（汇报）
     */
    @Override
    public Page<Task> searchMyTomorrowPlanTask(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchMyTomorrowPlanTask(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我计划参与的任务（汇报）
     */
    @Override
    public Page<Task> searchMyTomorrowPlanTaskMobDaily(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchMyTomorrowPlanTaskMobDaily(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 移动端下周计划参与(汇报)
     */
    @Override
    public Page<Task> searchNextWeekCompleteTaskMobZS(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchNextWeekCompleteTaskMobZS(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 本周完成的任务(汇报)
     */
    @Override
    public Page<Task> searchNextWeekCompleteTaskZS(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchNextWeekCompleteTaskZS(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 下周计划完成任务(汇报)
     */
    @Override
    public Page<Task> searchNextWeekPlanCompleteTask(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchNextWeekPlanCompleteTask(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 相关任务（计划）
     */
    @Override
    public Page<Task> searchPlanTask(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchPlanTask(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 项目任务（项目立项）
     */
    @Override
    public Page<Task> searchProjectAppTask(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchProjectAppTask(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 项目任务
     */
    @Override
    public Page<Task> searchProjectTask(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchProjectTask(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 根任务
     */
    @Override
    public Page<Task> searchRootTask(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchRootTask(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 关联计划（当前项目未关联）
     */
    @Override
    public Page<Task> searchTaskLinkPlan(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchTaskLinkPlan(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我本月完成的任务（下拉列表框）
     */
    @Override
    public Page<Task> searchThisMonthCompleteTaskChoice(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchThisMonthCompleteTaskChoice(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 本周完成的任务(汇报)
     */
    @Override
    public Page<Task> searchThisWeekCompleteTask(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchThisWeekCompleteTask(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 本周已完成任务(下拉框选择)
     */
    @Override
    public Page<Task> searchThisWeekCompleteTaskChoice(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchThisWeekCompleteTaskChoice(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 移动端本周已完成任务(汇报)
     */
    @Override
    public Page<Task> searchThisWeekCompleteTaskMobZS(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchThisWeekCompleteTaskMobZS(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 本周完成的任务(汇报)
     */
    @Override
    public Page<Task> searchThisWeekCompleteTaskZS(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchThisWeekCompleteTaskZS(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 todo列表查询
     */
    @Override
    public Page<Task> searchTodoListTask(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchTodoListTask(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 任务类型分组
     */
    @Override
    public Page<Map> searchTypeGroup(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Map> pages=baseMapper.searchTypeGroup(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Map>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 任务类型分组（计划）
     */
    @Override
    public Page<Map> searchTypeGroupPlan(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Map> pages=baseMapper.searchTypeGroupPlan(context.getPages(),context,context.getSelectCond());
        return new PageImpl<Map>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }



    /**
     * 为当前实体填充父数据（外键值文本、外键值附加数据）
     * @param et
     */
    private void fillParentData(Task et){
        //实体关系[DER1N_ZT_TASK_IBZ_PROJECTMODULE_MODULE]
        if(!ObjectUtils.isEmpty(et.getModule())){
            cn.ibizlab.pms.core.ibiz.domain.ProjectModule projectmodule=et.getProjectmodule();
            if(ObjectUtils.isEmpty(projectmodule)){
                cn.ibizlab.pms.core.ibiz.domain.ProjectModule majorEntity=projectmoduleService.get(et.getModule());
                et.setProjectmodule(majorEntity);
                projectmodule=majorEntity;
            }
            et.setModulename(projectmodule.getName());
            et.setPath(projectmodule.getPath());
        }
        //实体关系[DER1N_ZT_TASK_ZT_PRODUCTPLAN_PLAN]
        if(!ObjectUtils.isEmpty(et.getPlan())){
            cn.ibizlab.pms.core.zentao.domain.ProductPlan productplan=et.getProductplan();
            if(ObjectUtils.isEmpty(productplan)){
                cn.ibizlab.pms.core.zentao.domain.ProductPlan majorEntity=productplanService.get(et.getPlan());
                et.setProductplan(majorEntity);
                productplan=majorEntity;
            }
            et.setPlanname(productplan.getTitle());
        }
        //实体关系[DER1N_ZT_TASK_ZT_PROJECT_PROJECT]
        if(!ObjectUtils.isEmpty(et.getProject())){
            cn.ibizlab.pms.core.zentao.domain.Project ztproject=et.getZtproject();
            if(ObjectUtils.isEmpty(ztproject)){
                cn.ibizlab.pms.core.zentao.domain.Project majorEntity=projectService.get(et.getProject());
                et.setZtproject(majorEntity);
                ztproject=majorEntity;
            }
            et.setProjectname(ztproject.getName());
        }
        //实体关系[DER1N_ZT_TASK_ZT_STORY_STORY]
        if(!ObjectUtils.isEmpty(et.getStory())){
            cn.ibizlab.pms.core.zentao.domain.Story ztstory=et.getZtstory();
            if(ObjectUtils.isEmpty(ztstory)){
                cn.ibizlab.pms.core.zentao.domain.Story majorEntity=storyService.get(et.getStory());
                et.setZtstory(majorEntity);
                ztstory=majorEntity;
            }
            et.setStoryname(ztstory.getTitle());
            et.setProduct(ztstory.getProduct());
            et.setProductname(ztstory.getProductname());
        }
        //实体关系[DER1N__ZT_TASK__ZT_TASK__PARENT]
        if(!ObjectUtils.isEmpty(et.getParent())){
            cn.ibizlab.pms.core.zentao.domain.Task ztparent=et.getZtparent();
            if(ObjectUtils.isEmpty(ztparent)){
                cn.ibizlab.pms.core.zentao.domain.Task majorEntity=taskService.get(et.getParent());
                et.setZtparent(majorEntity);
                ztparent=majorEntity;
            }
            et.setParentname(ztparent.getName());
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
    public List<Task> getTaskByIds(List<Long> ids) {
         return this.listByIds(ids);
    }

    @Override
    public List<Task> getTaskByEntities(List<Task> entities) {
        List ids =new ArrayList();
        for(Task entity : entities){
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


    public ITaskService getProxyService() {
        return cn.ibizlab.pms.util.security.SpringContextHolder.getBean(this.getClass());
    }
    @Override
    @Transactional
    public Task dynamicCall(Long key, String action, Task et) {
        return et;
    }
}



