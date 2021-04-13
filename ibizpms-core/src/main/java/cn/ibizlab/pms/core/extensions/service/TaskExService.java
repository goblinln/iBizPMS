package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibiz.domain.TaskTeam;
import cn.ibizlab.pms.core.ibiz.filter.IbzFavoritesSearchContext;
import cn.ibizlab.pms.core.ibiz.service.IIbzFavoritesService;
import cn.ibizlab.pms.core.ibiz.service.ITaskTeamService;
import cn.ibizlab.pms.core.ibiz.service.impl.IbzFavoritesServiceImpl;
import cn.ibizlab.pms.core.ou.client.SysEmployeeFeignClient;
import cn.ibizlab.pms.core.ou.domain.SysEmployee;
import cn.ibizlab.pms.core.ou.filter.SysEmployeeSearchContext;
import cn.ibizlab.pms.core.util.ibizzentao.helper.TaskHelper;
import cn.ibizlab.pms.core.util.ibizzentao.helper.ZTBaseHelper;
import cn.ibizlab.pms.core.util.message.SendMessage;
import cn.ibizlab.pms.core.util.zentao.service.IIBZZTFileService;
import cn.ibizlab.pms.core.zentao.domain.File;
import cn.ibizlab.pms.core.zentao.domain.TaskEstimate;
import cn.ibizlab.pms.core.zentao.filter.TaskSearchContext;
import cn.ibizlab.pms.core.zentao.service.IFileService;
import cn.ibizlab.pms.core.zentao.service.impl.TaskServiceImpl;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.zentao.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 实体[任务] 自定义服务对象
 */
@Slf4j
@Primary
@Service("TaskExService")
public class TaskExService extends TaskServiceImpl {

    @Autowired
    IFileService iFileService;

    @Autowired
    ITaskTeamService iTaskTeamService;


    @Autowired
    IIbzFavoritesService iIbzFavoritesService;

    @Autowired
    SysEmployeeFeignClient sysEmployeeFeignClient;

    @Autowired
    TaskHelper taskHelper;

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    @Override
    @Transactional
    public Task taskForward(Task et) {
        Object actioninfo = et.get("actioninfo");
        et = this.get(et.getId());
        if(StaticDict.YesNo.ITEM_1.getValue().equals(et.getMultiple())) {
            if (!AuthenticationUser.getAuthenticationUser().getUsername().equals(et.getAssignedto())) {
                SysEmployeeSearchContext context = new SysEmployeeSearchContext();
                context.setN_username_in(et.getAssignedto());
                Page<SysEmployee> page = sysEmployeeFeignClient.searchDefault(context);
                List<SysEmployee> list = page.getContent();
                if (list.size() > 0) {
                    throw new RuntimeException(String.format(actioninfo.toString(), list.get(0).getPersonname()));
                }
                throw new RuntimeException(String.format(actioninfo.toString(), et.getAssignedto()));
            }
        }
        //自定义代码
        return et;
    }

    /**
     * 自定义行为[Activate]用户扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    @SendMessage
    public Task activate(Task et) {
        return super.activate(et);
    }
    /**
     * 自定义行为[AssignTo]用户扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    @SendMessage
    public Task assignTo(Task et) {
        return super.assignTo(et);
    }
    /**
     * 自定义行为[Cancel]用户扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task cancel(Task et) {
        return super.cancel(et);
    }
    /**
     * 自定义行为[Close]用户扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    @SendMessage
    public Task close(Task et) {
        return super.close(et);
    }
    /**
     * 自定义行为[DeleteEstimate]用户扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task deleteEstimate(Task et) {
        return super.deleteEstimate(et);
    }
    /**
     * 自定义行为[EditEstimate]用户扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task editEstimate(Task et) {
        return super.editEstimate(et);
    }
    /**
     * 自定义行为[Finish]用户扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    @SendMessage
    public Task finish(Task et) {
        return super.finish(et);
    }
    /**
     * 自定义行为[Pause]用户扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task pause(Task et) {
        return super.pause(et);
    }

    @Override
    public void saveBatch(List<Task> list) {
        if (list.isEmpty() || list.size() == 0) {
            return;
        }
        boolean flag = cn.ibizlab.pms.util.security.SpringContextHolder.getBean(cn.ibizlab.pms.core.util.ibizzentao.helper.TaskHelper.class).batchCreate(list);

        if (flag) {
            log.error("子任务批量添加成功");
        } else {
            log.error("子任务批量添加失败");
        }
    }

    /**
     * 自定义行为[Restart]用户扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task restart(Task et) {
        return super.restart(et);
    }
    /**
     * 自定义行为[Start]用户扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    @SendMessage
    public Task start(Task et) {
        return super.start(et);
    }

    /**
     * 查询集合 通过模块查询
     */
    @Override
    public Page<Task> searchByModule(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchByModule(context.getPages(),context,context.getSelectCond());
        for(Task task : pages.getRecords()) {
            if(task.getParent() == 0) {
                continue;
            }
            TaskSearchContext context1 = new TaskSearchContext();
            context1.setSelectCond(context.getSelectCond().clone());
            context1.setN_parent_eq(task.getId());
            context1.set("parent", task.getId());
            List<Task> taskList = this.searchChildDefault(context1).getContent();
            task.set("items", taskList);
            pages.setPages(pages.getTotal() + taskList.size());
        }
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 项目任务
     */
    @Override
    public Page<Task> searchProjectTask(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchProjectTask(context.getPages(),context,context.getSelectCond());
        for(Task task : pages.getRecords()) {
            if(task.getParent() == 0) {
                continue;
            }
            TaskSearchContext context1 = new TaskSearchContext();
            context1.setSelectCond(context.getSelectCond().clone());
            context1.setN_parent_eq(task.getId());
            context1.set("parent", task.getId());
            List<Task> taskList = this.searchChildDefault(context1).getContent();
            task.set("items", taskList);
            pages.setPages(pages.getTotal() + taskList.size());

        }
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 项目任务
     */
    @Override
    public Page<Task> searchPlanTask(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchPlanTask(context.getPages(),context,context.getSelectCond());
        List<Task> records = pages.getRecords();

       //  this.setChildTaskToParent(records,context,pages);
        pages.setRecords(records);
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }


    public void setChildTaskToParent(List<Task> records,TaskSearchContext context,com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages){
        List<Long> allTaskId = new ArrayList<>();
        for (Task task : records) {
            if (task.getParent() < 0){//父任务
                allTaskId.add(task.getId());
            }
        }

        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getParent() > 0 && allTaskId.contains(records.get(i).getParent())){
                //说明这个子任务的父任务也在这个查询中
                records.remove(i);
                i--;
                continue;
            }
            if(records.get(i).getParent() == 0) {
                continue;
            }
            TaskSearchContext context1 = new TaskSearchContext();
            context1.setSelectCond(context.getSelectCond().clone());
            context1.setN_parent_eq(records.get(i).getId());
            List<Task> taskList = this.searchDefault(context1).getContent();
            records.get(i).set("items", taskList);
            pages.setPages(pages.getTotal() + taskList.size());
        }
    }
    /**
     * 查询集合 项目任务
     */
    @Override
    public Page<Task> searchProjectAppTask(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchProjectAppTask(context.getPages(),context,context.getSelectCond());
        for(Task task : pages.getRecords()) {
            if(task.getParent() == 0) {
                continue;
            }
            TaskSearchContext context1 = new TaskSearchContext();
            context1.setSelectCond(context.getSelectCond().clone());
            context1.setN_parent_eq(task.getId());
            context1.set("parent", task.getId());
            List<Task> taskList = this.searchChildDefault(context1).getContent();
            task.set("items", taskList);
            pages.setPages(pages.getTotal() + taskList.size());

        }
        return new PageImpl<Task>(pages.getRecords(), context.getPageable(), pages.getTotal());
    }

    /**
     * 查询集合 我相关的任务
     */
    @Override
    public Page<Task> searchMyAllTask(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchMyAllTask(context.getPages(),context,context.getSelectCond());
        for(Task task : pages.getRecords()) {
            if(task.getParent() == 0) {
                continue;
            }
            TaskSearchContext context1 = new TaskSearchContext();
            context1.setSelectCond(context.getSelectCond().clone());
            context1.setN_parent_eq(task.getId());
            context1.set("parent", task.getId());
            List<Task> taskList = this.searchChildDefault(context1).getContent();
            task.set("items", taskList);
            pages.setPages(pages.getTotal() + taskList.size());

        }
        return new PageImpl<Task>(pages.getRecords(),context.getPageable(),pages.getTotal());
    }
    /**
     * 查询集合 我相关的任务
     */
    @Override
    public Page<Task> searchAssignedToMyTask(TaskSearchContext context) {
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<Task> pages=baseMapper.searchAssignedToMyTask(context.getPages(),context,context.getSelectCond());
        for(Task task : pages.getRecords()) {
            if(task.getParent() == 0) {
                continue;
            }
            TaskSearchContext context1 = new TaskSearchContext();
            context1.setSelectCond(context.getSelectCond().clone());
            context1.setN_parent_eq(task.getId());
            context1.set("parent", task.getId());
            List<Task> taskList = this.searchChildDefault(context1).getContent();
            task.set("items", taskList);
            pages.setPages(pages.getTotal() + taskList.size());

        }
        return new PageImpl<Task>(pages.getRecords(),context.getPageable(),pages.getTotal());
    }


        @Override
    @Transactional
    public Task get(Long key) {
        Task et = getById(key);
        if(et==null){
            et=new Task();
            et.setId(key);
        }
        else{
            et.setTaskteam(taskteamService.selectByRoot(key));
            // et.setTaskestimate(taskestimateService.selectByTask(key));
        }
        IbzFavoritesSearchContext ibzFavoritesSearchContext = new IbzFavoritesSearchContext();
        ibzFavoritesSearchContext.setN_type_eq(StaticDict.Action__object_type.TASK.getValue());
        ibzFavoritesSearchContext.setN_objectid_eq(key);
        ibzFavoritesSearchContext.setN_account_eq(AuthenticationUser.getAuthenticationUser().getLoginname());
        if(iIbzFavoritesService.searchDefault(ibzFavoritesSearchContext).getContent().size() > 0) {
            et.setIsfavorites(StaticDict.YesNo.ITEM_1.getValue());
        }
        return et;
    }

    @Override
    @Transactional
    public Task getTeamUserLeftActivity(Task et) {
        //自定义代码
        et=this.get(et.getId());
        //获取团队成员的剩余工时
        List<TaskTeam> taskTeams = iTaskTeamService.list(new QueryWrapper<TaskTeam>().eq(ZTBaseHelper.FIELD_TYPE, StaticDict.Action__object_type.TASK.getValue()).eq(ZTBaseHelper.FIELD_ROOT, et.getId()).eq(ZTBaseHelper.FIELD_ACCOUNT, AuthenticationUser.getAuthenticationUser().getUsername()));
        if (taskTeams.size()>0){
            et.setLeft(taskTeams.get(0).getLeft());
        }
        return et;
    }

    @Override
    @Transactional
    public Task getTeamUserLeftStart(Task et) {
        //自定义代码
        et = this.get(et.getId());
        // 获取团队成员的消耗和剩余工时
        List<TaskTeam> taskTeams = iTaskTeamService.list(new QueryWrapper<TaskTeam>().eq(ZTBaseHelper.FIELD_TYPE, StaticDict.Action__object_type.TASK.getValue()).eq(ZTBaseHelper.FIELD_ROOT, et.getId()).eq(ZTBaseHelper.FIELD_ACCOUNT, AuthenticationUser.getAuthenticationUser().getUsername()));
        if(taskTeams.size() > 0) {
            et.setLeft(taskTeams.get(0).getLeft());
            et.setConsumed(taskTeams.get(0).getConsumed());
        }
        return et;
    }

    @Override
    @Transactional
    public Task createCycleTasks(Task et) {
        List<Task> taskList = this.list(new QueryWrapper<Task>().eq("cycle", 1));
        for (Task task : taskList) {
            taskHelper.createByCycle(task);
        }
        //自定义代码
        return  super.createCycleTasks(et);
    }
}

