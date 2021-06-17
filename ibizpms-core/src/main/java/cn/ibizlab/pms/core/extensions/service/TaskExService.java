package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibiz.domain.TaskTeam;
import cn.ibizlab.pms.core.ibiz.service.ITaskTeamService;
import cn.ibizlab.pms.core.ibizplugin.service.IIBIZProMessageService;
import cn.ibizlab.pms.core.ou.client.SysEmployeeFeignClient;
import cn.ibizlab.pms.core.ou.domain.SysEmployee;
import cn.ibizlab.pms.core.ou.filter.SysEmployeeSearchContext;
import cn.ibizlab.pms.core.ou.service.ISysEmployeeService;
import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.util.ibizzentao.common.ZTDateUtil;
import cn.ibizlab.pms.core.util.ibizzentao.helper.ProductPlanHelper;
import cn.ibizlab.pms.core.util.ibizzentao.helper.ZTBaseHelper;
import cn.ibizlab.pms.core.zentao.domain.*;
import cn.ibizlab.pms.core.zentao.filter.TaskSearchContext;
import cn.ibizlab.pms.core.zentao.service.*;
import cn.ibizlab.pms.core.zentao.service.impl.TaskServiceImpl;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.errors.BadRequestAlertException;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DataObject;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;

import java.sql.Timestamp;
import java.util.*;

import static cn.ibizlab.pms.core.util.ibizzentao.helper.ZTBaseHelper.*;

/**
 * 实体[任务] 自定义服务对象
 */
@Slf4j
@Primary
@Service("TaskExService")
public class TaskExService extends TaskServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    @Autowired
    IActionService iActionService;

    @Autowired
    IFileService iFileService;

    @Autowired
    IIBIZProMessageService iibizProMessageService;

    @Autowired
    ISysEmployeeService sysEmployeeService;

    @Autowired
    IProductPlanService productPlanService;

    @Autowired
    ITeamService teamService;

    @Autowired
    IBugService iBugService;

    @Autowired
    IStoryService iStoryService;

    @Autowired
    ITaskEstimateService iTaskEstimateService;

    @Autowired
    ITaskTeamService iTaskTeamService;

    @Autowired
    ITeamService iTeamService;

    @Autowired
    IHistoryService iHistoryService;

    @Autowired
    SysEmployeeFeignClient sysEmployeeFeignClient;

    @Autowired
    @Lazy
    cn.ibizlab.pms.core.zentao.runtime.TaskRuntime taskRuntime;

    String[] diffAttrs = {"desc"};
    List<String> ignore = Arrays.asList("totalwh", "totalleft", "totalconsumed", "totalestimate");

    @Override
    public Page<Task> searchDefault(TaskSearchContext context) {
        Map<String,Object> params = context.getParams();
        if(StringUtils.isBlank(context.getN_id_isnull())) {
            context.setN_id_isnull(null);
        }
        if(params.get("type") != null) {
            if ("BugTask".equals(params.get("type"))) {
                return this.searchBugTask(context);
            }
        }
        return super.searchDefault(context);
    }

    @Override
    public Page<Task> searchBugTask(TaskSearchContext context) {
        List<Task> list = new ArrayList<>();
        Task task = new Task();
        task.setId(0L);
        task.setName("/");
        list.add(task);
        if(context.getN_project_eq() != null && context.getN_project_eq()==0) {
            return new PageImpl<Task>(list, context.getPageable(), list.size());
        }else {
            list.addAll(super.searchBugTask(context).getContent());
        }
        return new PageImpl<Task>(list, context.getPageable(), list.size());
    }

    @Override
    @Transactional
    public Task get(Long key) {
        Task et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        } else {
            if (!taskRuntime.isRtmodel()) {
                et.setTaskteams(taskteamService.selectByRoot(key));
            }
            et.setTaskestimates(null);
        }
        return et;
    }

    @Override
    public Task sysGet(Long key) {
        Task task = super.sysGet(key);
        String sql = "SELECT COUNT(1) as ISFAVOURITES from t_ibz_favorites t where t.OBJECTID = #{et.id} and t.TYPE = 'task' and t.ACCOUNT = #{et.account}";
        Map<String, Object> param = new HashMap<>();
        param.put("id", task.getId());
        param.put("account", AuthenticationUser.getAuthenticationUser().getLoginname());
        List<JSONObject> list = this.select(sql, param);
        if (list.size() > 0) {
            task.setIsfavorites(list.get(0).getString("ISFAVOURITES"));
        }
        task.setTaskestimates(null);
        task.setTaskteams(null);
        return task;
    }

    @Override
    @Transactional
    public boolean create(Task et) {
        jugEststartedAndDeadline(et);
        String multiple = et.getMultiple();//多人任务
        List<TaskTeam> taskTeams = et.getTaskteams();//任务团队成员
        String assignedto = et.getAssignedto();//任务指派给
        //如果是多人任务且任务团队不为空
        if (StringUtils.compare(multiple, StaticDict.YesNo.ITEM_1.getValue()) == 0 && taskTeams != null && !taskTeams.isEmpty()) {
            et.setAssignedto(null);
            double left = 0d;
            for (TaskTeam taskTeam : taskTeams) {
                if (taskTeam.getAccount() == null || "".equals(taskTeam.getAccount())) { //成员（类似姓名）
                    continue;
                }
                if (et.getAssignedto() == null && taskTeam.getAccount() != null && !"".equals(taskTeam.getAccount())) {
                    et.setAssignedto(taskTeam.getAccount());
                }
                if (taskTeam.getEstimate() != null) { //最初预计（工时？单位小时 double类型）
                    left += taskTeam.getEstimate();
                }

            }
            et.setLeft(left); //预计剩余
            et.setEstimate(left);//最初预计
        }
        if (et.getAssignedto() == null) {
            et.setAssignedto(assignedto);
        }
        //如果是周期任务  设置周期为1 parent为-1 （父任务）
        if (et.getTaskspecies() != null && et.getTaskspecies().equals(StaticDict.TaskSpecies.CYCLE.getValue())) {
            et.setCycle(1);
            et.setParent(-1L);
            et.setEststarted(et.getConfigbegin());
            et.setDeadline(et.getConfigend());
        }

        if (et.getStory() != null && et.getStory() != 0L) {
            et.setStoryversion(iStoryService.get(et.getStory()).getVersion());
        }
        if (et.getAssignedto() != null && !"".equals(et.getAssignedto())) {
            et.setAssigneddate(ZTDateUtil.now());
        } else {
            et.set("Assigneddate", "0000-00-00 00:00:00");
        }
        String files = et.getFiles();
        String noticeusers = et.getNoticeusers();

        if (!super.create(et)) {
            return false;
        }
        //周期循环处理
        if (et.getCycle() != null && et.getCycle() == 1) {
            et = createByCycle(et);
        }

        FileHelper.updateObjectID(et.getId(), StaticDict.File__object_type.TASK.getValue(), files, "", iFileService);


        if (StringUtils.compare(multiple, StaticDict.YesNo.ITEM_1.getValue()) == 0 && taskTeams != null && !taskTeams.isEmpty()) {
            int i = 0;
            for (TaskTeam taskTeam : taskTeams) {
                if (taskTeam.getAccount() == null || "".equals(taskTeam.getAccount())) {
                    continue;
                }
                Team team = new Team();
                team.setType(StaticDict.Team__type.TASK.getValue());
                team.setRoot(et.getId());
                team.setAccount(taskTeam.getAccount());
                team.setJoin(ZTDateUtil.now());
                team.setRole(AuthenticationUser.getAuthenticationUser().getUsername());
                team.setEstimate(taskTeam.getEstimate());
                team.setLeft(taskTeam.getEstimate());
                team.setDays(0);
                team.setHours(0.0);
                team.setOrder(i);
                iTeamService.create(team);
                i++;
            }
            super.update(et);

        }
        //PmsEe操作任务，需判断状态，计算关联的计划的状态
        if (et.getPlan() != null && et.getPlan() > 0) {
            //该任务有计划，根据任务状态更新计划状态
            this.updateRelatedPlanStatus(et);
        }
        ActionHelper.sendTodoOrToread(et.getId(), et.getName(), noticeusers, et.getAssignedto(), et.getMailto(), "任务", StaticDict.Action__object_type.STORY.getValue(), "tasks", StaticDict.Action__type.OPENED.getText(), true, iActionService);
        ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.TASK.getValue(), null, StaticDict.Action__type.OPENED.getValue(), "", "", null, iActionService);

        if (et.getStory() != null && et.getStory() != 0L) {
            iStoryService.setStage(et.getZtstory());
        }
        return true;
    }

    @Transactional
    @Override
    public Task updateRelatedPlanStatus(Task relatedTask) {
        Long planId = relatedTask.getPlan();

        if (planId == null || planId <= 0) {
            return relatedTask;
        }
        ProductPlan oldRelatePlan = productPlanService.getById(planId);//任务关联的计划
        if (oldRelatePlan == null) {
            relatedTask.setPlan(0L);
            super.update(relatedTask);
            return relatedTask;
        }
        List<JSONObject> relatedTasks = taskService.select(String.format("select * from zt_task t1 where t1.deleted = '0' and t1.plan = %1$s", oldRelatePlan.getId()), null);
        //根据关联的任务的状态，计算计划的状态
        String status = this.getParentOrRelatedPlanStatusByChildTasks(relatedTasks);
        if (StringUtils.compare(status, oldRelatePlan.getStatus()) != 0) {
            ProductPlan newProductPlan = new ProductPlan();
            CachedBeanCopier.copy(oldRelatePlan, newProductPlan);
            newProductPlan.setStatus(status);
            productPlanService.sysUpdate(newProductPlan);
            String actionType = this.getActionTypeByStatus(oldRelatePlan.getStatus() == null ? StaticDict.Task__status.WAIT.getValue() : oldRelatePlan.getStatus(), status);
            List<History> changes = ChangeUtil.diff(oldRelatePlan, newProductPlan);
            ActionHelper.createHis(oldRelatePlan.getId(), StaticDict.Action__object_type.PRODUCTPLAN.getValue(), changes, actionType, "", "", null, iActionService);

        }
        return relatedTask;
    }

    public String getActionTypeByStatus(String oldStatus, String status) {
        String action = "";
        if (StaticDict.Task__status.DOING.getValue().equals(status) && !StaticDict.Task__status.WAIT.getValue().equals(oldStatus) && !StaticDict.Task__status.PAUSE.getValue().equals(oldStatus)) {
            action = StaticDict.Action__type.ACTIVATED.getValue();
        } else if (StaticDict.Task__status.DOING.getValue().equals(status) && StaticDict.Task__status.PAUSE.getValue().equals(oldStatus)) {
            action = StaticDict.Action__type.RESTARTED.getValue();
        } else if (StaticDict.Task__status.DOING.getValue().equals(status) && StaticDict.Task__status.WAIT.getValue().equals(oldStatus)) {
            action = StaticDict.Action__type.STARTED.getValue();
        } else if (StaticDict.Task__status.CANCEL.getValue().equals(status) && !StaticDict.Task__status.CANCEL.getValue().equals(oldStatus)) {
            action = StaticDict.Action__type.CANCELED.getValue();
        } else if (StaticDict.Task__status.PAUSE.getValue().equals(status) && !StaticDict.Task__status.PAUSE.getValue().equals(oldStatus)) {
            action = StaticDict.Action__type.PAUSED.getValue();
        } else if (StaticDict.Task__status.CLOSED.getValue().equals(status) && !StaticDict.Task__status.CLOSED.getValue().equals(oldStatus)) {
            action = StaticDict.Action__type.CLOSED.getValue();
        } else if (StaticDict.Task__status.DONE.getValue().equals(status) && !StaticDict.Task__status.DONE.getValue().equals(oldStatus)) {
            action = StaticDict.Action__type.FINISHED.getValue();
        }
        return action;
    }

    //根据子任务状态计算父任务(或者关联的计划)状态
    public String getParentOrRelatedPlanStatusByChildTasks(List<JSONObject> childTasks) {
        String status = "";
        if (childTasks.size() == 1) {
            status = childTasks.get(0).getString("status");
        } else {

            String strStatus = "";
            String strClose = "";
            for (JSONObject task : childTasks) {
                strStatus += task.getString("status") + ",";
                strClose += task.getString("closedreason") + ",";
            }

            if (strStatus.contains(StaticDict.Task__status.DOING.getValue()) || strStatus.contains(StaticDict.Task__status.PAUSE.getValue())) {
                status = StaticDict.Task__status.DOING.getValue();
            } else if ((strStatus.contains(StaticDict.Task__status.DONE.getValue()) || strClose.contains(StaticDict.Task__closed_reason.DONE.getValue())) && strStatus.contains(StaticDict.Task__status.WAIT.getValue())) {
                status = StaticDict.Task__status.DOING.getValue();
            } else if (strStatus.contains(StaticDict.Task__status.WAIT.getValue())) {
                status = StaticDict.Task__status.WAIT.getValue();
            } else if (strStatus.contains(StaticDict.Task__status.DONE.getValue())) {
                status = StaticDict.Task__status.DONE.getValue();
            } else if (strStatus.contains(StaticDict.Task__status.CLOSED.getValue())) {
                status = StaticDict.Task__status.CLOSED.getValue();
            } else if (strStatus.contains(StaticDict.Task__status.CANCEL.getValue())) {
                status = StaticDict.Task__status.CANCEL.getValue();
            }
        }
        return status;
    }

    /**
     * 判断起始时间和截至时间
     *
     * @param et Task对象
     */
    public void jugEststartedAndDeadline(Task et) {
        if (et.getDeadline() != null) {
            if (et.getEststarted() == null) {//预计开始时间
                throw new RuntimeException("请输入实际开始时间!");
            }
            if (et.getDeadline().getTime() < et.getEststarted().getTime()) {
                throw new RuntimeException("截至日期必须大于实际开始时间!");
            }
        }
        if (et.getEststarted() != null) {
            if (et.getDeadline() == null) {
                throw new RuntimeException("请输入截至时间!");
            }
            if (et.getDeadline().getTime() < et.getEststarted().getTime()) {
                throw new RuntimeException("截至日期必须大于实际开始时间!");
            }
        }
    }

    @Override
    public Task createByCycle(Task et) {
        return super.createByCycle(et);
    }

    @Override
    @Transactional
    public boolean update(Task et) {
        jugEststartedAndDeadline(et);
        if (null != et.getConfigbegin()) {
            et.setEststarted(et.getConfigbegin());
        }
        if (null != et.getConfigend()) {
            et.setDeadline(et.getConfigend());
        }
        if (StaticDict.TaskSpecies.CYCLE.getValue().equals(et.getTaskspecies())) {
            createByCycle(et);
        }
        String multiple = et.getMultiple();
        List<TaskTeam> teams = et.getTaskteams();
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        Task old = new Task();
        CachedBeanCopier.copy(this.get(et.getId()), old);

        if (StaticDict.Task__status.DONE.getValue().equals(et.getStatus()) && (et.getConsumed() == null)) {
            throw new RuntimeException("任务完成状态消耗不能为空");
        }
        if (!StaticDict.Task__status.CANCEL.getValue().equals(et.getStatus()) && (et.getName() == null || et.getType() == null)) {
            throw new RuntimeException("非取消状态，任务名和类型不能为空！");
        }
        if ((StaticDict.Task__status.DOING.getValue().equals(et.getStatus()) || StaticDict.Task__status.PAUSE.getValue().equals(et.getStatus())) && et.getLeft() == 0 && StringUtils.compare(et.getMultiple(), StaticDict.YesNo.ITEM_0.getValue()) == 0) {
            throw new RuntimeException("预计剩余不能为0");
        }


        if (et.getStory() != 0 && !et.getStory().equals(old.getStory())) {
            Story story = iStoryService.getById(et.getStory());
            et.setStoryversion(story.getVersion());
        }

        if (StringUtils.compare(et.getStatus(), StaticDict.Task__status.DONE.getValue()) == 0) {
            et.setLeft(0.0);
            if (StringUtils.isBlank(et.getFinishedby())) {
                et.setFinishedby(AuthenticationUser.getAuthenticationUser().getUsername());
            }
            if (et.getFinisheddate() == null) {
                et.setFinisheddate(ZTDateUtil.now());
            }
        } else if (StringUtils.compare(et.getStatus(), StaticDict.Task__status.CANCEL.getValue()) == 0) {
            if (StringUtils.isBlank(et.getClosedby())) {
                et.setCanceledby(AuthenticationUser.getAuthenticationUser().getUsername());
            }
            if (et.getCanceleddate() == null) {
                et.setCanceleddate(ZTDateUtil.now());
            }
        } else if (StringUtils.compare(et.getStatus(), StaticDict.Task__status.CLOSED.getValue()) == 0) {
            if (et.getClosedreason() == null || "".equals(et.getClosedreason())) {
                throw new RuntimeException("关闭原因不能为空");
            }
            if (StringUtils.compare(et.getClosedreason(), StaticDict.Task__closed_reason.CANCEL.getValue()) == 0) {
                if (!StringUtils.isEmpty(et.getFinishedby()) || et.getFinisheddate() != null) {
                    throw new RuntimeException("由谁完成和完成时间必须为空!");
                } else {
                    et.setCanceledby(et.getCanceledby() != null ? et.getCanceledby() : AuthenticationUser.getAuthenticationUser().getUsername());
                    et.setCanceleddate(et.getCanceleddate() != null ? et.getCanceleddate() : ZTDateUtil.now());
                }
            }

        }


        if (StringUtils.compare(et.getAssignedto(), old.getAssignedto()) != 0) {
            et.setAssigneddate(ZTDateUtil.now());
        }

        if (et.getConsumed() > 0 && et.getLeft() > 0 && StringUtils.compare(et.getStatus(), StaticDict.Task__status.WAIT.getValue()) == 0 && StringUtils.compare(multiple, StaticDict.YesNo.ITEM_1.getValue()) != 0) {
            et.setStatus(StaticDict.Task__status.DOING.getValue());
        }

        if (StringUtils.compare(et.getStatus(), StaticDict.Task__status.WAIT.getValue()) == 0 && old.getLeft().equals(et.getLeft()) && et.getConsumed() == 0 && et.getEstimate() != 0) {
            et.setLeft(et.getEstimate());
        }


        if (et.getConsumed() < old.getConsumed()) {
            throw new RuntimeException("总计消耗必须大于之前消耗");
        }

        if (old.getProject().equals(et.getProject())) {
            Task data = new Task();
            data.setProject(et.getProject());
            data.setModule(et.getModule());
            this.update(data, (Wrapper<Task>) data.getUpdateWrapper(true).eq("parent", et.getId()));
        }

        if (StringUtils.compare(multiple, StaticDict.YesNo.ITEM_1.getValue()) == 0 && teams != null && !teams.isEmpty()) {
            //String statusStr = "done,closed,cancel";
            String statusStr = StaticDict.Task__status.DONE.getValue() + "," + StaticDict.Task__status.CLOSED.getValue() + "," + StaticDict.Task__status.CANCEL.getValue();
            List<String> accounts = new ArrayList<>();
            for (TaskTeam team : teams) {
                if (team.getAccount() == null || "".equals(team.getAccount())) {
                    continue;
                }
                accounts.add(team.getAccount());
            }
            if (!statusStr.contains(et.getStatus()) && !accounts.contains(et.getAssignedto()) && !"".equals(et.getAssignedto())) {
                throw new RuntimeException("当前状态的多人任务不能指派给任务团队以外的成员。");
            }
            int i = 0;
            for (TaskTeam taskTeam : teams) {
                if (taskTeam.getAccount() == null || "".equals(taskTeam.getAccount())) {
                    continue;
                }
                taskTeam.setType(StaticDict.Team__type.TASK.getValue());
                taskTeam.setRoot(et.getId());
                taskTeam.setAccount(taskTeam.getAccount());
                taskTeam.setJoin(ZTDateUtil.now());
                taskTeam.setRole(et.getAssignedto());
                taskTeam.setEstimate(taskTeam.getEstimate());
                taskTeam.setConsumed(taskTeam.getConsumed());
                taskTeam.setLeft(taskTeam.getLeft() != null ? taskTeam.getLeft() : taskTeam.getConsumed());
                taskTeam.setOrder(i);
                if (StringUtils.compare(et.getStatus(), StaticDict.Task__status.DONE.getValue()) == 0) {
                    taskTeam.setLeft(0.0);
                }
                if (et.getAssignedto() == null || "".equals(et.getAssignedto())) {
                    et.setAssignedto(taskTeam.getAccount());
                    et.setAssigneddate(ZTDateUtil.now());
                }
                i++;
            }
        }


        iTeamService.remove(new QueryWrapper<Team>().eq("root", et.getId()).eq("type", StaticDict.Team__type.TASK.getValue()));
        if (StringUtils.compare(multiple, StaticDict.YesNo.ITEM_1.getValue()) == 0 && teams != null && !teams.isEmpty()) {
            String assignedto = "";
            for (TaskTeam team : teams) {
                if (team.getAccount() == null || "".equals(team.getAccount())) {
                    continue;
                }
                if ("".equals(assignedto)) {
                    assignedto = team.getAccount();
                }
                iTaskTeamService.create(team);
            }
            old.set("task", et);
            old.setTaskteams(null);
            old.set("auto", false);
            this.computeHours4Multiple(old);
            if (et.getStatus().equals(StaticDict.Task__status.WAIT.getValue())) {
                et.setAssignedto(assignedto);
            }
        }

        if (et.getStatus().equals(StaticDict.Task__status.WAIT.getValue()) || et.getStatus().equals(StaticDict.Task__status.DOING.getValue())) {
            et.setFinishedby("");
            et.setFinisheddate(null);
            et.setCanceledby("");
            et.setCanceleddate(null);
            et.setClosedby("");
            et.setCloseddate(null);
            et.setClosedreason("");
        }
        if (et.getStatus().equals(StaticDict.Task__status.DONE.getValue())) {
            et.setEstimate(0d);
            et.setCanceledby("");
            et.setCanceleddate(null);
        }
        if (et.getStatus().equals(StaticDict.Task__status.CANCEL.getValue())) {
            et.setFinisheddate(null);
            et.setFinishedby("");
        }

        String files = et.getFiles();
        String noticeusers = et.getNoticeusers();
        super.update(et);
        FileHelper.updateObjectID(et.getId(), StaticDict.File__object_type.TASK.getValue(), files, "", iFileService);
        boolean changeParent = false;
        if (et.getParent()  != null && !et.getParent().equals(old.getParent())) {
            changeParent = true;
        }

        if (old.getParent() != null && old.getParent() > 0) {
            Task oldParent = this.get(old.getParent());
            et.set("parentId", et.getParent());
            et.set("changed", !changeParent);
            updateParentStatus(et);
            computeBeginAndEnd(oldParent);
            if (changeParent) {
                int oldChildCount = this.count(new QueryWrapper<Task>().eq("parent", old.getParent()));
                if (oldChildCount == 0) {
                    Task task1 = new Task();
                    task1.setId(old.getParent());
                    task1.setParent(0L);
                    super.update(task1);
                }
                Task task2 = new Task();
                task2.setId(old.getParent());
                task2.setLasteditedby(AuthenticationUser.getAuthenticationUser().getUsername());
                task2.setLastediteddate(ZTDateUtil.now());
                super.update(task2);
                ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.TASK.getValue(), null, StaticDict.Action__type.UNLINKPARENTTASK.getValue(), "", String.valueOf(old.getParent()), null, iActionService);

                Task parent = this.get(et.getParent());
                List<History> changes = ChangeUtil.diff(oldParent, parent);
                ActionHelper.createHis(old.getParent(), StaticDict.Action__object_type.TASK.getValue(), changes, StaticDict.Action__type.UNLINKCHILDRENTASK.getValue(), "", String.valueOf(et.getId()), null, iActionService);
            }
        }

        if (et.getParent()  != null && et.getParent() > 0) {
            Task parentTask = this.getById(et.getParent());
            Task task2 = new Task();
            task2.setId(et.getParent());
            task2.setParent(-1L);
            super.update(task2);
            et.set("parentId", et.getParent());
            et.set("changed", !changeParent);
            updateParentStatus(et);
            computeBeginAndEnd(this.get(et.getParent()));
            if (changeParent) {
                Task task1 = new Task();
                task1.setLasteditedby(AuthenticationUser.getAuthenticationUser().getUsername());
                task1.setLastediteddate(ZTDateUtil.now());
                task1.setId(et.getParent());
                ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.TASK.getValue(), null, StaticDict.Action__type.LINKPARENTTASK.getValue(), "", String.valueOf(old.getParent()), null, iActionService);

                Task newParentTask = this.getById(et.getParent());
                List<History> changes = ChangeUtil.diff(parentTask, newParentTask);
                ActionHelper.createHis(et.getParent(), StaticDict.Action__object_type.TASK.getValue(), changes, StaticDict.Action__type.LINKCHILDTASK.getValue(), "", String.valueOf(et.getId()), null, iActionService);

            }
        }
        //PmsEe操作任务，需判断状态，计算关联的计划的状态
        if (et.getPlan() != null && et.getPlan() > 0) {
            //该任务有计划，根据任务状态更新计划状态
            this.updateRelatedPlanStatus(et);
        }

        et.setStatus1(old.getStatus1());
        List<History> changes = ChangeUtil.diff(old, et, null, null, diffAttrs);
        this.removeIgonreChanges(changes);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {
            judChanges(changes, et, noticeusers, comment);
        }
        if (et.getStory() != null && et.getStory() != 0L) {
            iStoryService.setStage(et.getZtstory());
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Task computeBeginAndEnd(Task et) {
        if (et.getId() == null) {
            return et;
        }
        et = this.get(et.getId());
        String sql = String.format("select estStarted, realStarted, deadline from zt_task where parent = %1$s and `status` <> 'cancel'  and deleted = '0'", et.getId());
        List<JSONObject> list = taskService.select(sql, null);
        if (list.size() == 0) {
            return et;
        }
        Timestamp earliestEstStarted = et.getEststarted();
        Timestamp earliestRealStarted = et.getRealstarted();
        Timestamp latestDeadline = et.getDeadline();
        for (JSONObject jsonObject : list) {
            if (jsonObject == null) {
                continue;
            }
            Timestamp estStarted = jsonObject.getTimestamp("estStarted");
            Timestamp realStarted = jsonObject.getTimestamp("realStarted");
            Timestamp deadline = jsonObject.getTimestamp("deadline");
            if (earliestEstStarted == null || (estStarted != null && earliestEstStarted.getTime() > estStarted.getTime())) {
                earliestEstStarted = estStarted;
            }
            if (earliestRealStarted == null || (realStarted != null && earliestRealStarted.getTime() > realStarted.getTime())) {
                earliestRealStarted = estStarted;
            }
            if (latestDeadline == null || (deadline != null && latestDeadline.getTime() < deadline.getTime())) {
                latestDeadline = deadline;
            }
        }
        Task task = new Task();
        task.setEststarted(earliestEstStarted);
        task.setId(et.getId());
        task.setRealstarted(earliestRealStarted);
        task.setDeadline(latestDeadline);
        super.update(task);
        return et;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Task computeWorkingHours(Task et) {
        if (et.getId() == null) {
            return et;
        }

//        String sql = String.format("select * from zt_task where parent = %1$s and status <> 'cancel' and deleted = '0'", et.getId());
        List<Task> list = this.list(new QueryWrapper<Task>().eq("parent", et.getId()).ne("status", "cancel").eq("deleted", "0"));
//        List<JSONObject> list = taskService.select(sql, null);
        if (list.size() == 0) {
            return et;
        }
        double estimate = 0;
        double consumed = 0;
        double left = 0;
        for (Task task1 : list) {
            estimate += task1.getEstimate();
            consumed += task1.getConsumed();
            if (!StaticDict.Task__status.CLOSED.getValue().equals(task1.getStatus())) {
                left += task1.getLeft();
            }
        }
        Task task = new Task();
        task.setId(et.getId());
        task.setEstimate(estimate);
        task.setConsumed(consumed);
        task.setLeft(left);
        super.update(task);

        return et;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Task updateParentStatus(Task et) {
        log.info("updateParentStatus:未实现");
        Long parentId = (Long) et.get("parentId");
        Boolean changed = (Boolean) et.get("changed");

        if (parentId == null) {
            parentId = et.getParent();
        }
        if (parentId <= 0) {
            return null;
        }
        Task oldParentTask = this.get(parentId);

        if (oldParentTask.getParent() != null && oldParentTask.getParent() != -1) {
            Task pTask = new Task();
            pTask.setId(parentId);
            pTask.setParent(-1L);
            super.update(pTask);
        }
        computeWorkingHours(oldParentTask);

        String sql = String.format("select * from zt_task where parent = %1$s and deleted = '0'", parentId);
        List<JSONObject> childTasks = taskService.select(sql, null);
        if (childTasks.size() == 0) {
            Task pTask = new Task();
            pTask.setId(parentId);
            pTask.setParent(0L);
            super.update(pTask);
            return et;
        }

        //根据子任务状态计算父任务状态
        String status = this.getParentOrRelatedPlanStatusByChildTasks(childTasks);

        Task parentTask = this.getOne(new QueryWrapper<Task>().eq("id", parentId).eq("deleted", '0'));
        if (parentTask == null) {
            Task task1 = new Task();
            task1.setId(et.getId());
            task1.setParent(0L);
            super.update(task1);
            return et;
        }
        //
        if (!"".equals(status) && !status.equals(oldParentTask.getStatus())) {
            Task task = new Task();
            Timestamp nowDate = ZTDateUtil.now();
            task.setStatus(status);
            task.setId(parentId);
            task.setLastediteddate(nowDate);
            task.setParent(-1L);
            task.setLasteditedby(AuthenticationUser.getAuthenticationUser().getLoginname());
            if (StaticDict.Task__status.DONE.getValue().equals(status)) {
                task.setAssigneddate(nowDate);
                task.setAssignedto(oldParentTask.getOpenedby());
                task.setFinishedby(AuthenticationUser.getAuthenticationUser().getLoginname());
                task.setFinisheddate(nowDate);
            } else if (StaticDict.Task__status.CANCEL.getValue().equals(status)) {
                task.setAssigneddate(nowDate);
                task.setAssignedto(oldParentTask.getOpenedby());
                task.setFinishedby(AuthenticationUser.getAuthenticationUser().getLoginname());
                task.setFinisheddate(nowDate);
                task.setCanceledby(AuthenticationUser.getAuthenticationUser().getLoginname());
                task.setCanceleddate(nowDate);
            } else if (StaticDict.Task__status.CLOSED.getValue().equals(status)) {
                task.setAssigneddate(nowDate);
                task.setAssignedto(StaticDict.Task__status.CLOSED.getValue());
                task.setClosedby(AuthenticationUser.getAuthenticationUser().getLoginname());
                task.setCloseddate(nowDate);
                task.setClosedreason(StaticDict.Task__status.DONE.getValue());
            } else if (StaticDict.Task__status.DOING.getValue().equals(status) || StaticDict.Task__status.WAIT.getValue().equals(status)) {
                if (StaticDict.Task__status.CLOSED.getValue().equals(oldParentTask.getAssignedto())) {
                    task.setAssignedto(et.getAssignedto());
                    task.setCanceleddate(nowDate);
                }
                task.setFinishedby("");
                task.setFinisheddate(null);
                task.setClosedby("");
                task.setCloseddate(null);
                task.setClosedreason("");
            }
            super.update(task);
            //PmsEe操作任务，需判断状态，计算关联的计划的状态
            if (task.getPlan() != null && task.getPlan() > 0) {
                //该任务有计划，根据任务状态更新计划状态
                this.updateRelatedPlanStatus(task);
            }
            if (!changed) {
                return et;
            }

            List<History> changes = ChangeUtil.diff(oldParentTask, this.get(parentId));
            this.removeIgonreChanges(changes);


            //根据原本父任务状态，和stauts计算行为类型actiontype
            String action = this.getActionTypeByStatus(oldParentTask.getStatus(), status);
            if (!"".equals(action)) {
                ActionHelper.createHis(parentId, StaticDict.Action__object_type.TASK.getValue(), changes, action, "", "", null, iActionService);
            }
        } else {
            List<History> changes = ChangeUtil.diff(oldParentTask, this.get(parentId));
            this.removeIgonreChanges(changes);
            if (changes.size() > 0) {

                ActionHelper.createHis(parentId, StaticDict.Action__object_type.TASK.getValue(), changes, StaticDict.Action__type.EDITED.getValue(), "", "", null, iActionService);

            }
        }
        return et;
    }

    @Override
    @Transactional
    public Task computeHours4Multiple(Task et) {
        if (et == null) {
            return et;
        }
        Task task = (Task) et.get("task");
        List<TaskTeam> teams = et.getTaskteams();
        //List<TaskTeam> teams = (List<TaskTeam>)et.get("teams");
        Boolean auto = (Boolean) et.get("auto");

        if (teams == null) {
            teams = iTaskTeamService.list(new QueryWrapper<TaskTeam>().eq("root", et.getId()).eq("type", StaticDict.Team__type.TASK.getValue()).orderByAsc("`order`"));
        }
        if (teams != null && teams.size() != 0) {
            Timestamp now = ZTDateUtil.now();
            Task currentTask = task != null ? task : new Task();
            if (currentTask.getStatus() == null) {
                currentTask.setStatus(et.getStatus());
            }
            if (task.getAssignedto() != null) {
                currentTask.setAssignedto(task.getAssignedto());
            } else {
                if (et.getAssignedto() == null) {
                    TaskTeam firstMember = teams.get(0);
                    currentTask.setAssignedto(firstMember.getAccount());
                    currentTask.setAssigneddate(now);
                } else {
                    for (TaskTeam team : teams) {
                        if (team.getAccount() != null && team.getAccount().equals(et.getAssignedto()) && team.getLeft() == 0 && team.getConsumed() != 0) {
                            if (!et.getAssignedto().equals(teams.get(teams.size() - 1).getAccount())) {
                                currentTask.setAssignedto(this.getNextUser(teams, et));
                            } else {
                                currentTask.setAssignedto(et.getOpenedby());
                            }
                            break;
                        }
                    }
                }
            }

            currentTask.setEstimate(0d);
            currentTask.setConsumed(0d);
            currentTask.setLeft(0d);
            for (TaskTeam member : teams) {
                currentTask.setEstimate(currentTask.getEstimate() + (member.getEstimate() == null ? 0 : member.getEstimate()));
                currentTask.setConsumed(currentTask.getConsumed() + (member.getConsumed() == null ? 0 : member.getConsumed()));
                currentTask.setLeft(currentTask.getLeft() + (member.getLeft() == null ? 0 : member.getLeft()));
            }
            if (task != null) {
                if (currentTask.getConsumed() == 0) {
                    if (task.getStatus() == null) {
                        currentTask.setStatus(StaticDict.Task__status.WAIT.getValue());
                    }
                    currentTask.setFinishedby("");
                    currentTask.setFinisheddate(null);
                }
                if (currentTask.getConsumed() > 0 && currentTask.getLeft() > 0 && StringUtils.compare(currentTask.getMultiple(), StaticDict.YesNo.ITEM_1.getValue()) != 0) {
                    currentTask.setStatus(StaticDict.Task__status.DOING.getValue());
                    currentTask.setFinishedby("");
                    currentTask.setFinisheddate(null);
                }
                if (currentTask.getConsumed() > 0 && currentTask.getLeft() == 0 && StringUtils.compare(currentTask.getMultiple(), StaticDict.YesNo.ITEM_1.getValue()) != 0) {
                    boolean flag = false;
                    for (TaskTeam team : teams) {
                        if (team.getAccount().equals(currentTask.getAssignedto())) {
                            flag = true;
                            break;
                        }
                    }
                    boolean flag1 = true;
                    for (TaskTeam team : teams) {
                        if (team.getAccount().equals(et.getAssignedto())) {
                            flag1 = false;
                            break;
                        }
                    }

                    if (flag && flag1) {
                        currentTask.setStatus(StaticDict.Task__status.DOING.getValue());
                        currentTask.setFinishedby("");
                        currentTask.setFinisheddate(null);
                    } else if (!flag1) {
                        currentTask.setStatus(StaticDict.Task__status.DONE.getValue());
                        currentTask.setFinishedby(AuthenticationUser.getAuthenticationUser().getUsername());
                        currentTask.setFinisheddate(currentTask.getFinisheddate() == null ? ZTDateUtil.now() : currentTask.getFinisheddate());
                    }
                }
                if (!et.getAssignedto().equals(currentTask.getAssignedto()) || currentTask.getStatus().equals(StaticDict.Task__status.DONE.getValue())) {
                    String login = AuthenticationUser.getAuthenticationUser().getUsername();
                    boolean flag = false;
                    double left = 0;
                    for (TaskTeam team : teams) {
                        if (team.getAccount().equals(login)) {
                            flag = true;
                            left = team.getLeft();
                            break;
                        }
                    }
                    if (flag && left == 0 && !et.getFinishedlist().contains(login)) { //完成者列表
                        String finsihList = et.getFinishedlist() + "," + login;
                        if (finsihList.indexOf(",") == 0) {
                            finsihList.substring(1);
                        } else if (finsihList.indexOf(",") == finsihList.length() - 1) {
                            finsihList.substring(0, finsihList.length() - 1);
                        }
                        currentTask.setFinishedlist(finsihList);

                    }
                    if ((StaticDict.Task__status.DONE.getValue().equals(et.getStatus()) || StaticDict.Task__status.CLOSED.getValue().equals(et.getStatus())) && StaticDict.Task__status.DOING.getValue().equals(currentTask.getStatus()) && et.getStatus() != null) {
                        if (et.getFinishedlist().contains(et.getAssignedto()) && et.getFinishedlist().length() >= et.getFinishedlist().indexOf(et.getAssignedto())) {
                            currentTask.setFinishedlist(et.getFinishedlist().substring(0, et.getFinishedlist().indexOf(et.getAssignedto()) - 1));
                        }
                    }
                }
            }
            currentTask.setId(et.getId());
            super.update(currentTask);
        }
        return et;
    }

    public String getNextUser(List<TaskTeam> teams, Task old) {
        List<String> accounts = new ArrayList<>();
        for (TaskTeam team : teams) {
            accounts.add(team.getAccount());
        }
        int index = -1;
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getAccount().equals(old.getAssignedto())) {
                index = i;
                break;
            }
        }
        if (old == null || !accounts.contains(old.getAssignedto()) || index == teams.size() - 1) {
            return teams.get(0).getAccount();
        }
        String next = "";
        for (int i = 0; i < teams.size(); i++) {
            if (teams.get(i).getAccount().equals(old.getAssignedto()) && i != teams.size() - 1) {
                next = teams.get(i + 1).getAccount();
                break;
            }
        }
        return next;
    }

    @Override
    @Transactional
    public Task getNextTeamUser(Task et) {
        et = this.getById(et.getId());
        List<Team> teamList = iTeamService.list(new QueryWrapper<Team>().eq("root", et.getId()).eq("type", StaticDict.Team__type.TASK.getValue()).orderByAsc(" `order` "));
        if (teamList.size() == 0) {
            et.setAssignedtozj(et.getOpenedby());
            et.setAssignedto(et.getOpenedby());
        } else {
            double myconsumed = 0d;
            int index = -1;
            for (int i = 0; i < teamList.size(); i++) {
                if (teamList.get(i).getAccount().equals(et.getAssignedto())) {
                    index = i;
                    myconsumed = teamList.get(i).getConsumed();
                    break;
                }
            }
            et.setMyconsumed(myconsumed);
            et.setMytotaltime(myconsumed);
            if (index == teamList.size() - 1) {
                et.setAssignedtozj(null);
                et.setAssignedto(null);
            } else {
                String next = "";
                for (int i = 0; i < teamList.size(); i++) {
                    if (teamList.get(i).getAccount().equals(et.getAssignedto()) && i != teamList.size() - 1) {
                        next = teamList.get(i + 1).getAccount();
                        break;
                    }
                }
                et.setAssignedtozj(next);
                et.setAssignedto(next);
            }
        }
        return et;
    }

    @Override
    @Transactional
    public boolean remove(Long key) {
        Task old = this.get(key);
        if (old.getParent() != null && old.getParent() < 0) {
            throw new RuntimeException("不能删除父任务");
        }
        if (!super.remove(key)) {
            return false;
        }
        if (old.getParent() != null && old.getParent() > 0) {
            old.set("parentId", old.getParent());
            old.set("changed", false);
            updateParentStatus(old);
            ActionHelper.createHis(old.getParent(), StaticDict.Action__object_type.TASK.getValue(), null, StaticDict.Action__type.DELETECHILDRENTASK.getValue(), "", "", null, iActionService);
        }
        if (old.getFrombug() != 0) {
            Bug bug = new Bug();
            bug.setId(old.getFrombug());
            bug.setTotask(0L);
            iBugService.sysUpdate(bug);
        }
        if (old.getStory() != null && old.getStory() != 0L) {
            iStoryService.setStage(old.getZtstory());
        }

        //PmsEe操作任务，需判断状态，计算关联的计划的状态
        if (old.getPlan() != null && old.getPlan() > 0) {
            //该任务有计划，根据任务状态更新计划状态
            this.updateRelatedPlanStatus(old);
        }
        return true;
    }

    /**
     * [Activate:激活] 行为扩展：激活完成、取消、关闭的任务
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task activate(Task et) {
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        Task old = new Task();
        CachedBeanCopier.copy(this.get(et.getId()), old);

        Task newTask = new Task();
        this.getActivateUpdateTask(et, newTask);
        newTask.setId(et.getId());
        newTask.setLeft(et.getLeft() != null ? et.getLeft() : 0.0d);

        //team
        List<Team> teams = iTeamService.list(new QueryWrapper<Team>().eq("root", newTask.getId()).eq("type", StaticDict.Team__type.TASK.getValue()));
        if (teams.size() > 0) {
            for (Team team : teams) {
                if (StringUtils.compare(team.getAccount(), newTask.getAssignedto()) == 0) {
                    team.setLeft(newTask.getLeft());
                    iTeamService.sysUpdate(team);
                    break;
                }
            }
            old.set("task", newTask);
            old.setTaskteams(null);
            old.set("auto", false);
            computeHours4Multiple(old);
        }

        String noticeusers = et.getNoticeusers();
        super.update(newTask);

        if (old.getParent() != null && old.getParent() > 0) {
            newTask.set("parentId", old.getParent());
            newTask.set("changed", true);
            updateParentStatus(newTask);
        }

        if (old.getParent() != null && old.getParent() == -1L) {
            Task task1 = new Task();
            getActivateUpdateTask(et, task1);
            this.update(task1, (Wrapper<Task>) task1.getUpdateWrapper(true).eq("parent", old.getId()));
            this.computeWorkingHours(newTask);
        }
        //PmsEe操作任务，需判断状态，计算关联的计划的状态
        if (newTask.getPlan() != null && newTask.getPlan() > 0) {
            //该任务有计划，根据任务状态更新计划状态
            this.updateRelatedPlanStatus(newTask);
        }

        newTask.setStatus1(old.getStatus1());
        List<History> changes = ChangeUtil.diff(old, newTask);
        this.removeIgonreChanges(changes);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {
            ActionHelper.sendTodoOrToread(newTask.getId(), newTask.getName(), noticeusers, newTask.getAssignedto(), newTask.getMailto(), "任务", StaticDict.Action__object_type.TASK.getValue(), "tasks", StaticDict.Action__type.ACTIVATED.getText(), false, iActionService);
            ActionHelper.createHis(newTask.getId(), StaticDict.Action__object_type.TASK.getValue(), changes, StaticDict.Action__type.ACTIVATED.getValue(), comment, newTask.getAssignedto(), null, iActionService);
        }
        return et;
    }

    public void getActivateUpdateTask(Task et, Task newTask) {
        newTask.setAssignedto(et.getAssignedto() == null ? "" : et.getAssignedto());
        newTask.setAssigneddate(ZTDateUtil.now());
        newTask.setStatus(StaticDict.Task__status.DOING.getValue());
        newTask.setFinishedby("");
        newTask.setFinisheddate(null);
        newTask.setCanceledby("");
        newTask.setCanceleddate(null);
        newTask.setClosedby("");
        newTask.setCloseddate(null);
        newTask.setClosedreason("");
        newTask.setLasteditedby(AuthenticationUser.getAuthenticationUser().getUsername());
        newTask.setLastediteddate(ZTDateUtil.now());
    }

    /**
     * [AssignTo:指派/转交] 行为扩展：单人任务指派任务 & 多人任务时转交任务
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task assignTo(Task et) {
        et.set("actioninfo", "当前任务只有%1$s才可以转交。");
        this.taskForward(et);
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        Task old = new Task();
        CachedBeanCopier.copy(this.get(et.getId()), old);
        Task newTask = new Task();
        newTask.setId(et.getId());
        newTask.setLeft(et.getLeft() != null ? et.getLeft() : 0.0d);
        newTask.setAssignedto(et.getAssignedto());
        newTask.setAssigneddate(ZTDateUtil.now());
        newTask.setLasteditedby(AuthenticationUser.getAuthenticationUser().getUsername());
        newTask.setLastediteddate(ZTDateUtil.now());
        if (!StaticDict.Task__status.DONE.getValue().equals(old.getStatus()) && !StaticDict.Task__status.CLOSED.getValue().equals(old.getStatus()) && (et.getLeft() == null || et.getLeft() == 0)) {
            throw new RuntimeException("[预计剩余]不能为空！");
        }
//        TaskEstimate taskEstimate = new TaskEstimate();
//        taskEstimate.setTask(newTask.getId());
//        taskEstimate.setAccount(AuthenticationUser.getAuthenticationUser().getUsername());
//        taskEstimate.setDate(ZTDateUtil.now());
//        taskEstimate.setLeft(newTask.getLeft());
//        taskEstimate.setConsumed(0d);
//        taskEstimateHelper.create(taskEstimate);
        //teams
        List<Team> teams = iTeamService.list(new QueryWrapper<Team>().eq("root", et.getId()).eq("type", StaticDict.Team__type.TASK.getValue()));
        if (teams.size() > 0) {

            Team team = new Team();
            team.setLeft(0d);
            iTeamService.update(team, new QueryWrapper<Team>().eq("root", old.getId()).eq("type", StaticDict.Team__type.TASK.getValue()).eq("account", old.getAssignedto()));
            Team team1 = new Team();
            team1.setLeft(newTask.getLeft());
            iTeamService.update(team1, new QueryWrapper<Team>().eq("root", newTask.getId()).eq("type", StaticDict.Team__type.TASK.getValue()).eq("account", newTask.getAssignedto()));
            old.set("task", newTask);
            old.setTaskteams(null);
            old.set("auto", false);
            computeHours4Multiple(old);
        }


        if (old.getParent() != null && old.getParent() > 0) {
            newTask.set("parentId", old.getParent());
            newTask.set("changed", true);
            updateParentStatus(newTask);
        }
        String noticeusers = et.getNoticeusers();
        super.update(newTask);

        //PmsEe操作任务，需判断状态，计算关联的计划的状态
        if (newTask.getPlan() != null && newTask.getPlan() > 0) {
            //该任务有计划，根据任务状态更新计划状态
            this.updateRelatedPlanStatus(newTask);
        }
        List<History> changes = ChangeUtil.diff(old, newTask);
        this.removeIgonreChanges(changes);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {
            if (jugAssignToIsChanged(old, et)) {
                ActionHelper.sendMarkDone(newTask.getId(), newTask.getName(), old.getAssignedto(), "任務", StaticDict.Action__object_type.TASK.getValue(), "tasks", StaticDict.Action__type.ASSIGNED.getText(), iActionService);
                // 发送待办消息
                ActionHelper.sendTodoOrToread(newTask.getId(), newTask.getName(), noticeusers, newTask.getAssignedto(), newTask.getMailto(), "任务", StaticDict.Action__object_type.TASK.getValue(), "tasks", StaticDict.Action__type.ASSIGNED.getText(), true, iActionService);

            }
            ActionHelper.createHis(newTask.getId(), StaticDict.Action__object_type.TASK.getValue(), changes, StaticDict.Action__type.ASSIGNED.getValue(), comment, newTask.getAssignedto(), null, iActionService);

        }
        return et;
    }

    public boolean jugAssignToIsChanged(Task old, Task et) {
        boolean flag = false;
        if ((StaticDict.Task__status.WAIT.getValue().equals(et.getStatus()) || StaticDict.Task__status.DOING.getValue().equals(et.getStatus())) && ((old.getAssignedto() == null) || !old.getAssignedto().equals(et.getAssignedto()))) {
            flag = true;
        }
        return flag;
    }

    /**
     * [Cancel:取消] 行为扩展
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task cancel(Task et) {
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        Task old = new Task();
        CachedBeanCopier.copy(this.get(et.getId()), old);

        Task newTask = new Task();
        setCancelNewTask(old, newTask);
        newTask.setId(et.getId());
        String noticeusers = et.getNoticeusers();
        super.update(newTask);

        if (old.getParent()  != null && old.getParent() > 0) {
            newTask.set("parentId", old.getParent());
            newTask.set("changed", true);
            updateParentStatus(newTask);
        }

        if (old.getParent()  != null && old.getParent() == -1L) {
            Task childNewTask = new Task();
            setCancelNewTask(old, childNewTask);
            this.update(childNewTask, (Wrapper<Task>) childNewTask.getUpdateWrapper(true).eq("parent", old.getId()));
        }
        //PmsEe操作任务，需判断状态，计算关联的计划的状态
        if (newTask.getPlan() != null && newTask.getPlan() > 0) {
            //该任务有计划，根据任务状态更新计划状态
            this.updateRelatedPlanStatus(newTask);
        }

        List<History> changes = ChangeUtil.diff(old, newTask);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {
            ActionHelper.sendTodoOrToread(newTask.getId(), newTask.getName(), noticeusers, newTask.getAssignedto(), newTask.getMailto(), "任务", StaticDict.Action__object_type.TASK.getValue(), "tasks", StaticDict.Action__type.CANCELED.getText(), false, iActionService);
            ActionHelper.createHis(newTask.getId(), StaticDict.Action__object_type.TASK.getValue(), changes, StaticDict.Action__type.CANCELED.getValue(), comment, newTask.getAssignedto(), null, iActionService);

        }
        return et;
    }

    @Transactional(rollbackFor = Exception.class)
    public void setCancelNewTask(Task old, Task newTask) {
        newTask.setLastediteddate(ZTDateUtil.now());
        newTask.setLasteditedby(AuthenticationUser.getAuthenticationUser().getUsername());
        newTask.setStatus(StaticDict.Task__status.CANCEL.getValue());
        newTask.setAssignedto(old.getOpenedby());
        newTask.setAssigneddate(ZTDateUtil.now());
        newTask.setFinishedby("");
        newTask.setFinisheddate(null);
        newTask.setCanceledby(AuthenticationUser.getAuthenticationUser().getUsername());
        newTask.setCanceleddate(ZTDateUtil.now());
    }

    /**
     * [Close:关闭] 行为扩展
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task close(Task et) {
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        Task old = new Task();
        CachedBeanCopier.copy(this.get(et.getId()), old);

        Task newTask = new Task();
        newTask.setId(et.getId());
        newTask.setLastediteddate(ZTDateUtil.now());
        newTask.setLasteditedby(AuthenticationUser.getAuthenticationUser().getUsername());
        newTask.setStatus(StaticDict.Task__status.CLOSED.getValue());
        newTask.setAssignedto(StaticDict.Assignedto_closed.CLOSED.getValue());
        newTask.setAssigneddate(ZTDateUtil.now());

        newTask.setClosedby(AuthenticationUser.getAuthenticationUser().getUsername());
        newTask.setCloseddate(ZTDateUtil.now());
        if (StringUtils.compare(old.getStatus(), StaticDict.Task__status.DONE.getValue()) == 0) {
            newTask.setClosedreason(StaticDict.Task__closed_reason.DONE.getValue());
        } else if (StringUtils.compare(old.getStatus(), StaticDict.Task__status.CANCEL.getValue()) == 0) {
            newTask.setClosedreason(StaticDict.Task__closed_reason.CANCEL.getValue());
        }

        String noticeusers = et.getNoticeusers();
        super.update(newTask);
        if (old.getParent()  != null && old.getParent() > 0) {
            newTask.set("parentId", old.getParent());
            newTask.set("changed", true);
            updateParentStatus(newTask);
        }
        //PmsEe操作任务，需判断状态，计算关联的计划的状态
        if (newTask.getPlan() != null && newTask.getPlan() > 0) {
            //该任务有计划，根据任务状态更新计划状态
            this.updateRelatedPlanStatus(newTask);
        }

        String[] ignores = {"assigneddate", "closeddate"};
        List<History> changes = ChangeUtil.diff(old, newTask, ignores, null, null);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {
            ActionHelper.sendTodoOrToread(newTask.getId(), newTask.getName(), noticeusers, newTask.getAssignedto(), newTask.getMailto(), "任务", StaticDict.Action__object_type.TASK.getValue(), "tasks", StaticDict.Action__type.CLOSED.getText(), false, iActionService);
            ActionHelper.createHis(newTask.getId(), StaticDict.Action__object_type.TASK.getValue(), changes, StaticDict.Action__type.CLOSED.getValue(), comment, newTask.getAssignedto(), null, iActionService);

        }
        if (newTask.getStory() != null && newTask.getStory() != 0L) {
            iStoryService.setStage(newTask.getZtstory());
        }
        return et;
    }

    /**
     * [ConfirmStoryChange:需求变更确认] 行为扩展
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task confirmStoryChange(Task et) {
        et = this.get(et.getId());
        Task task = new Task();
        task.setId(et.getId());
        task.setStoryversion(storyService.get(et.getStory()).getVersion());
        super.update(task);
        ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.TASK.getValue(), null, StaticDict.Action__type.CONFIRMED.getValue(), "", "", null, iActionService);

        return et;
    }

    @Override
    @Transactional
    public void saveBatch(List<Task> list) {
        List<Long> storyIds = new ArrayList<>();
        List<String> taskNames = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            long storyId = list.get(i).getStory();
            boolean inNames = taskNames.contains(list.get(i).getName());
            if (!inNames || (inNames && !storyIds.contains(storyId))) {
                storyIds.add(storyId);
                taskNames.add(list.get(i).getName());
            } else {
                throw new RuntimeException("任务名不能相同!");
            }
        }
        Project project = projectService.get(list.get(0).getProject());
        boolean isOps = StaticDict.Project__type.OPS.getValue().equalsIgnoreCase(project.getType());
        String childTasks = "";
        for (Task task : list) {
            long story = task.getStory() == null ? 0 : task.getStory();
            long module = task.getModule() == null ? 0 : task.getModule();
            String type = task.getType() == null ? "" : task.getType();
            String assignedTo = task.getAssignedto() == null ? "" : task.getAssignedto();
            task.setStory(story);
            task.setModule(module);
            task.setType(type);
            task.setAssignedto(assignedTo);
//            task.set(FIELD_ESTSTARTED, task.getEststarted() == null ? DEFAULT_TIME : task.getEststarted());
////            task.set(FIELD_DEADLINE, task.getDeadline() == null ? DEFAULT_TIME : task.getDeadline());
            task.setStatus(StaticDict.Task__status.WAIT.getValue());
//            task.setLeft(task.getEstimate());
            task.setEstimate(task.getLeft());
            if (isOps) {
                task.setStory(0L);
            } else if (task.getStory() != null && task.getStory() != 0L) {
                task.setStoryversion(iStoryService.get(task.getStory()).getVersion());
            }
            if (assignedTo != "" && assignedTo != null) {
                task.setAssigneddate(ZTDateUtil.now());
            }
            task.setTaskspecies(StaticDict.TaskSpecies.TEMP.getValue());//创建子任务时子任务的状态为临时任务
            getProxyService().create(task);

            childTasks += task.getId() + ",";
            ActionHelper.createHis(task.getId(), StaticDict.Action__object_type.TASK.getValue(), null, StaticDict.Action__type.OPENED.getValue(), "", "", null, iActionService);
            if (task.getStory() != null && task.getStory() != 0L) {
                iStoryService.setStage(task.getZtstory());
            }
        }

        long parent = list.get(0).getParent();
        Task old = new Task();
        CachedBeanCopier.copy(this.get(parent), old);

        if (parent > 0) {
            Task lastInsertTask = list.get(list.size() - 1);
            lastInsertTask.set("parentId", parent);
            lastInsertTask.set("changed", true);
            updateParentStatus(lastInsertTask);
            computeBeginAndEnd(this.get(old.getId()));
            if (old.getParent()  != null && old.getParent() != -1L) {
                Task update = new Task();
                update.setParent(-1L);
                update.setId(parent);
                super.update(update);
            }
            Task newT = this.getById(parent);
            String regex = "^,*|,*$";
            childTasks = childTasks.replaceAll(regex, "");
            CachedBeanCopier.copy(this.get(old.getId()), newT);
            List<History> changes = ChangeUtil.diff(old, newT);
            ActionHelper.createHis(parent, StaticDict.Action__object_type.TASK.getValue(), changes, StaticDict.Action__type.CREATECHILDREN.getValue(), "", childTasks, null, iActionService);

        }
    }

    /**
     * [CreateCycleTasks:创建周期任务] 行为扩展
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task createCycleTasks(Task et) {
        List<Task> taskList = this.list(new QueryWrapper<Task>().eq("cycle", 1));
        for (Task task : taskList) {
            createByCycle(task);
        }
        return super.createCycleTasks(et);
    }

    /**
     * [Delete:删除任务] 行为扩展
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task delete(Task et) {
        boolean bOk = false;
        Task old = this.get(et.getId());
        if (old.getParent() != null && old.getParent() < 0) {
            throw new RuntimeException("不能删除父任务");
        }
        bOk = delete(et.getId());
        if (old.getParent()  != null && old.getParent() > 0) {
            old.set("changed", false);
            updateParentStatus(old);
            ActionHelper.createHis(old.getId(), StaticDict.Action__object_type.TASK.getValue(), null, StaticDict.Action__type.DELETECHILDRENTASK.getValue(), et.getComment(), "", "", iActionService);
        }
        if (old.getFrombug() != 0) {
            Bug bug = new Bug();
            bug.setId(old.getFrombug());
            bug.setTotask(0L);
            iBugService.sysUpdate(bug);
        }
        if (old.getStory() != null && old.getStory() != 0L) {
            iStoryService.setStage(old.getZtstory());
        }

        //PmsEe操作任务，需判断状态，计算关联的计划的状态
        if (old.getPlan() != null && old.getPlan() > 0) {
            //该任务有计划，根据任务状态更新计划状态
            this.updateRelatedPlanStatus(old);
        }
        et.set("bOk", bOk);
        return et;
    }

    public boolean hasId() {
        return true;
    }

    public boolean hasDeleted() {
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long key) {
        if (hasId()) {
            if (hasDeleted()) {
                UpdateWrapper deleteWrapper = new UpdateWrapper();
                deleteWrapper.set(FIELD_DELETED, StaticDict.YesNo.ITEM_1.getValue());
                deleteWrapper.eq(FIELD_ID, key);
                return this.update(deleteWrapper);
            } else {
                return removeById(key);
            }
        }
        return true;
    }

    /**
     * [DeleteEstimate:删除工时] 行为扩展
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task deleteEstimate(Task et) {
        return super.deleteEstimate(et);
    }

    /**
     * [EditEstimate:编辑工时] 行为扩展
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task editEstimate(Task et) {
        return super.editEstimate(et);
    }

    /**
     * [Finish:完成] 行为扩展
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task finish(Task et) {
        et.set("actioninfo", "当前任务只有%1$s才可以完成。");
        this.taskForward(et);
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        Task old = new Task();
        CachedBeanCopier.copy(this.get(et.getId()), old);
        List<Team> teams = iTeamService.list(new QueryWrapper<Team>().eq("root", et.getId()).eq("type", StaticDict.Team__type.TASK.getValue()));
        Task newTask = new Task();
        newTask.setId(et.getId());
        newTask.setAssigneddate(ZTDateUtil.now());
        newTask.setLastediteddate(ZTDateUtil.now());
        newTask.setLasteditedby(AuthenticationUser.getAuthenticationUser().getUsername());
        newTask.setConsumed(et.getTotaltime() != null ? et.getTotaltime() : (et.getConsumed() + (et.getCurrentconsumed() == null ? 0 : et.getCurrentconsumed())));
        newTask.setCurrentconsumed(et.getCurrentconsumed());
        newTask.setFiles(et.getFiles());

        double consumed = 0;
        if (teams.size() == 0) {
            newTask.setLeft(0d);
            newTask.setStatus(StaticDict.Task__status.DONE.getValue());
            newTask.setFinisheddate(et.getFinisheddate() == null ? ZTDateUtil.now() : et.getFinisheddate());
            newTask.setFinishedby(AuthenticationUser.getAuthenticationUser().getUsername());
            consumed = et.getConsumed() - old.getConsumed();
            if (consumed < 0) {
                throw new RuntimeException("总计消耗必须大于原消耗");
            }
            if (et.getAssignedto() == null || "".equals(et.getAssignedto())) {
                newTask.setAssignedto(old.getOpenedby());
            } else {
                newTask.setAssignedto(et.getAssignedto());
            }

        } else {
            for (Team team : teams) {
                if (team.getAccount().equals(AuthenticationUser.getAuthenticationUser().getUsername())) {
                    consumed = et.getMyconsumed() - team.getConsumed();
                    if (consumed < 0) {
                        throw new RuntimeException("总计消耗必须大于原消耗");
                    }
                    break;
                }
            }
            if (!(et.getAssignedtozj() == null || "".equals(et.getAssignedtozj()))) {
                newTask.setAssignedto(et.getAssignedtozj());
            }
        }


        //Task
        TaskEstimate taskEstimate = new TaskEstimate();
        taskEstimate.setTask(newTask.getId());
        taskEstimate.setAccount(AuthenticationUser.getAuthenticationUser().getUsername());
        taskEstimate.setDate(et.getFinisheddate() == null ? ZTDateUtil.now() : et.getFinisheddate());
        taskEstimate.setLeft(0.0);
        taskEstimate.setConsumed(consumed == 0 ? (et.getCurrentconsumed() == null ? 0 : et.getCurrentconsumed()) : consumed);

        if (teams.size() > 0) {
            for (Team team : teams) {
                if (StringUtils.compare(team.getAccount(), taskEstimate.getAccount()) != 0) {
                    taskEstimate.setLeft(taskEstimate.getLeft() + team.getLeft());
                }
            }
        }
        if (null != et.getCurrentconsumed()) {
            iTaskEstimateService.create(taskEstimate);
        }

        if (teams.size() > 0) {
            for (Team team : teams) {
                if (StringUtils.compare(team.getAccount(), old.getAssignedto()) == 0) {
                    team.setLeft(0.0);
                    team.setConsumed(newTask.getCurrentconsumed() + team.getConsumed());
                    iTeamService.sysUpdate(team);
                }
            }
            newTask.setFinisheddate(et.getFinisheddate());
            old.set("task", newTask);
            old.setTaskteams(null);
            old.set("auto", false);
            computeHours4Multiple(old);
        }

        String files = newTask.getFiles();

        String noticeusers = et.getNoticeusers();
        super.update(newTask);
        FileHelper.updateObjectID(newTask.getId(), StaticDict.File__object_type.TASK.getValue(), files, "", iFileService);

        if (old.getParent() != null && old.getParent() > 0) {
            newTask.set("parentId", old.getParent());
            newTask.set("changed", true);
            updateParentStatus(newTask);
        }

        //PmsEe操作任务，需判断状态，计算关联的计划的状态
        if (newTask.getPlan() != null && newTask.getPlan() > 0) {
            //该任务有计划，根据任务状态更新计划状态
            this.updateRelatedPlanStatus(newTask);
        }
        List<History> changes = ChangeUtil.diff(old, newTask);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {

            ActionHelper.sendMarkDone(newTask.getId(), newTask.getName(), old.getAssignedto(), "任務", StaticDict.Action__object_type.TASK.getValue(), "tasks", StaticDict.Action__type.FINISHED.getText(), iActionService);
            // 发送待办消息
            ActionHelper.sendTodoOrToread(newTask.getId(), newTask.getName(), noticeusers, newTask.getAssignedto(), newTask.getMailto(), "任务", StaticDict.Action__object_type.TASK.getValue(), "tasks", StaticDict.Action__type.FINISHED.getText(), true, iActionService);
            ActionHelper.createHis(newTask.getId(), StaticDict.Action__object_type.TASK.getValue(), changes, StaticDict.Action__type.FINISHED.getValue(), comment, newTask.getAssignedto(), null, iActionService);

        }
        if (newTask.getStory() != null && newTask.getStory() != 0L) {
            iStoryService.setStage(newTask.getZtstory());
        }
        return et;
    }

    /**
     * [GetTeamUserLeftActivity:获取团队成员剩余工时（激活）] 行为扩展
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task getTeamUserLeftActivity(Task et) {
        //自定义代码
        et = this.get(et.getId());
        //获取团队成员的剩余工时
        List<TaskTeam> taskTeams = iTaskTeamService.list(new QueryWrapper<TaskTeam>().eq(ZTBaseHelper.FIELD_TYPE, StaticDict.Action__object_type.TASK.getValue()).eq(ZTBaseHelper.FIELD_ROOT, et.getId()).eq(ZTBaseHelper.FIELD_ACCOUNT, AuthenticationUser.getAuthenticationUser().getUsername()));
        if (taskTeams.size() > 0) {
            et.setLeft(taskTeams.get(0).getLeft());
        }
        return et;
    }

    /**
     * [GetTeamUserLeftStart:获取团队成员剩余工时（开始或继续）] 行为扩展
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task getTeamUserLeftStart(Task et) {
        //自定义代码
        et = this.get(et.getId());
        // 获取团队成员的消耗和剩余工时
        List<TaskTeam> taskTeams = iTaskTeamService.list(new QueryWrapper<TaskTeam>().eq(ZTBaseHelper.FIELD_TYPE, StaticDict.Action__object_type.TASK.getValue()).eq(ZTBaseHelper.FIELD_ROOT, et.getId()).eq(ZTBaseHelper.FIELD_ACCOUNT, AuthenticationUser.getAuthenticationUser().getUsername()));
        if (taskTeams.size() > 0) {
            et.setLeft(taskTeams.get(0).getLeft());
            et.setConsumed(taskTeams.get(0).getConsumed());
        }
        return et;
    }

    /**
     * [LinkPlan:关联计划] 行为扩展
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task linkPlan(Task et) {
        //先找到这个任务对应的productplan
        List<ProductPlan> productPlanList = productPlanService.list(new QueryWrapper<ProductPlan>().eq("id", et.getPlan()));
        ProductPlan oldProductPlan = productPlanList.get(0);
        ProductPlan productPlan = new ProductPlan();
        productPlan.setProduct(oldProductPlan.getProduct());
        productPlan.setId(oldProductPlan.getId());
        String tasksId = getTasks(et);
        if ("[]".equals(tasksId)) {
            productPlan.set("tasks", null);
        } else {
            productPlan.set("tasks", tasksId);

        }
        cn.ibizlab.pms.util.security.SpringContextHolder.getBean(ProductPlanHelper.class).linkTask(productPlan);
        return et;
    }

    public String getTasks(Task et) {
        String tasks = "";
        ArrayList<Map> list = (ArrayList) et.get(FIELD_SRFACTIONPARAM);
        for (Map data : list) {
            if (tasks.length() > 0) {
                tasks += MULTIPLE_CHOICE;
            }
            tasks += data.get(FIELD_ID);
        }
        return tasks;
    }

    /**
     * [OtherUpdate:其他更新] 行为扩展
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task otherUpdate(Task et) {
        Task newTask = new Task();
        newTask.setId(et.getId());

        if (et.getName() != null) {
            newTask.setName(et.getName());
        } else if (DataObject.getBooleanValue(et.get("namedirtyflag"), false)) {
            throw new RuntimeException("任务名称不能为空！");
        }
        if (et.getType() != null) {
            newTask.setType(et.getType());
        } else if (DataObject.getBooleanValue(et.get("typedirtyflag"), false)) {
            newTask.setType("");
        }
        if (et.getDeadline() != null) {
            newTask.setDeadline(et.getDeadline());
        } else if (DataObject.getBooleanValue(et.get("deadlinedirtyflag"), false)) {
            newTask.setDeadline(null);
        }
        if (et.getPri() != null) {
            newTask.setPri(et.getPri());
        } else if (DataObject.getBooleanValue(et.get("pridirtyflag"), false)) {
            newTask.setPri(0);
        }
        if (et.getDesc() != null) { //任务描述
            newTask.setDesc(et.getDesc());
        } else if (DataObject.getBooleanValue(et.get("descdirtyflag"), false)) {
            newTask.setDesc("");
        }
        if (et.getMailto() != null) { //抄送给
            newTask.setMailto(et.getMailto());
        } else if (DataObject.getBooleanValue(et.get("mailtodirtyflag"), false)) {
            newTask.setMailto("");
        }
        Timestamp now = ZTDateUtil.now();
        newTask.setLastediteddate(now);
        newTask.setLasteditedby(AuthenticationUser.getAuthenticationUser().getUsername());
        String noticeusers = et.getNoticeusers();
        super.update(newTask);

        Task old = new Task();
        CachedBeanCopier.copy(this.get(et.getId()), old);
        old.setLastediteddate(now);
        old.setLasteditedby(AuthenticationUser.getAuthenticationUser().getUsername());

        List<History> changes = ChangeUtil.diff(old, newTask, null, null, diffAttrs);
        this.removeIgonreChanges(changes);
        if (changes.size() > 0) {
            judChanges(changes, newTask, noticeusers, "");
        }
        return et;
    }

    public void removeIgonreChanges(List<History> changes) {
        for (int i = 0; i < changes.size(); i++) {
            if (ignore.contains(changes.get(i).getField())) {
                changes.remove(i);
                i--;
            }
        }
    }

    public void judChanges(List<History> changes, Task et, String noticeusers, String comment) {
        String strAction = StaticDict.Action__type.EDITED.getValue();
        String strActionText = StaticDict.Action__type.EDITED.getText();
        if (changes.size() == 0) {
            strAction = StaticDict.Action__type.COMMENTED.getValue();
            strActionText = StaticDict.Action__type.COMMENTED.getText();
        }

        ActionHelper.sendTodoOrToread(et.getId(), et.getName(), noticeusers, et.getAssignedto(), et.getMailto(), "任务", StaticDict.Action__object_type.STORY.getValue(), "tasks", strActionText, false, iActionService);
        ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.TASK.getValue(), changes, strAction, comment, "", null, iActionService);


    }

    /**
     * [Pause:暂停] 行为扩展
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task pause(Task et) {
        et.set("actioninfo", "当前任务只有%1$s才可以暂停任务。");
        this.taskForward(et);
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        Task old = new Task();
        CachedBeanCopier.copy(this.get(et.getId()), old);
        Task newTask = new Task();
        newTask.setId(et.getId());
        newTask.setStatus(StaticDict.Task__status.PAUSE.getValue());
        newTask.setLastediteddate(ZTDateUtil.now());
        newTask.setLasteditedby(AuthenticationUser.getAuthenticationUser().getUsername());
        String noticeusers = et.getNoticeusers();
        super.update(newTask);

        if (old.getParent()  != null && old.getParent() > 0) {
            newTask.set("parentId", newTask.getParent());
            newTask.set("changed", true);
            updateParentStatus(newTask);
        }
        //PmsEe操作任务，需判断状态，计算关联的计划的状态
        if (newTask.getPlan() != null && newTask.getPlan() > 0) {
            //该任务有计划，根据任务状态更新计划状态
            this.updateRelatedPlanStatus(newTask);
        }
        List<History> changes = ChangeUtil.diff(old, newTask);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {

            ActionHelper.sendTodoOrToread(newTask.getId(), newTask.getName(), noticeusers, newTask.getAssignedto(), newTask.getMailto(), "任务", StaticDict.Action__object_type.STORY.getValue(), "tasks", StaticDict.Action__type.PAUSED.getText(), false, iActionService);
            ActionHelper.createHis(newTask.getId(), StaticDict.Action__object_type.TASK.getValue(), changes, StaticDict.Action__type.PAUSED.getValue(), comment, "", null, iActionService);

        }
        if (newTask.getStory() != null && newTask.getStory() != 0L) {
            iStoryService.setStage(newTask.getZtstory());
        }
        return et;
    }

    /**
     * [RecordEstimate:工时录入] 行为扩展
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task recordEstimate(Task et) {
        et.set("actioninfo", "当前任务只有%1$s才可以记录工时。");
        this.taskForward(et);
        String files = et.getFiles();
        Task old = new Task();
        CachedBeanCopier.copy(this.get(et.getId()), old);
        List<TaskEstimate> taskEstimates = new ArrayList<>();
        Timestamp earliestTime = null;
        if (et.getTaskestimates() != null && et.getTaskestimates().size() > 0) {
            for (TaskEstimate taskEstimate : et.getTaskestimates()) {
                taskEstimate.setTask(et.getId());
                taskEstimate.setDate(ZTDateUtil.now());
                if (taskEstimate.getDates() != null) {
                    taskEstimate.setDate(taskEstimate.getDates());
                    if (taskEstimate.getLeft() == null || taskEstimate.getConsumed() == null) {
                        throw new RuntimeException("当日期不为空时，消耗和剩余不能为空！");
                    }
                }
                if (earliestTime == null) {
                    earliestTime = taskEstimate.getDate();
                }
                taskEstimate.setAccount(AuthenticationUser.getAuthenticationUser().getLoginname());
                if (taskEstimate.getConsumed() == null) {
                    continue;
                }
                if (taskEstimate.getLeft() == null) {
                    continue;
                }
                taskEstimates.add(taskEstimate);
            }
        }
        if (taskEstimates.size() == 0) {
            return et;
        }
        double consumed = 0d;
        double teamLeft = 0d;
        double taskLeft = 0d;
        Timestamp nowDate = ZTDateUtil.now();
        List<TaskEstimate> list = iTaskEstimateService.list(new QueryWrapper<TaskEstimate>().eq("task", old.getId()).orderByDesc("date", "id"));
        Timestamp lastDate = null;
        if (list.size() > 0) {
            lastDate = list.get(0).getDate();
        }
        Long actionid = 0L;
        boolean isNew = false;
        for (TaskEstimate taskEstimate : taskEstimates) {
            iTaskEstimateService.create(taskEstimate);
            consumed += taskEstimate.getConsumed();

            Action action1 = ActionHelper.createHis(old.getId(), StaticDict.Action__object_type.TASK.getValue(), null, StaticDict.Action__type.RECORDESTIMATE.getValue(), taskEstimate.getWork(), String.valueOf(taskEstimate.getConsumed()), null, iActionService);
            actionid = action1.getId();
            if (lastDate == null || lastDate.getTime() <= taskEstimate.getDate().getTime()) {
                isNew = true;
                teamLeft = taskEstimate.getLeft();
                lastDate = taskEstimate.getDate();
            }

        }

        // 团队任务
        List<Team> teams = iTeamService.list(new QueryWrapper<Team>().eq("root", old.getId()).eq("type", StaticDict.Team__type.TASK.getValue()));
        if (teams.size() > 0) {
            double myconsumed = 0d;
            for (Team team : teams) {
                if (AuthenticationUser.getAuthenticationUser().getUsername().equals(team.getAccount()) && team.getConsumed() != null) {
                    myconsumed = team.getConsumed();
                } else {
                    taskLeft += team.getLeft();
                }
                if (!isNew) {
                    teamLeft += team.getLeft();
                }
            }
            Team team = new Team();
            team.setConsumed(myconsumed + consumed);
            team.setLeft(teamLeft);
            iTeamService.update(team, new QueryWrapper<Team>().eq("root", old.getId()).eq("type", StaticDict.Team__type.TASK.getValue()).eq("account", AuthenticationUser.getAuthenticationUser().getUsername()));
        } else {
            if (!isNew) {
                teamLeft = list.get(0).getLeft();
            }

        }

        Task task = new Task();
        taskLeft += teamLeft;
        task.setLeft(taskLeft);
        task.setConsumed(old.getConsumed() + consumed);
        task.setLasteditedby(AuthenticationUser.getAuthenticationUser().getLoginname());
        task.setLastediteddate(nowDate);
        task.setId(old.getId());
        task.setParent(old.getParent());
        if (taskLeft == 0) {
            task.setStatus(StaticDict.Task__status.DONE.getValue());
            task.setAssignedto(old.getOpenedby());
            task.setAssigneddate(nowDate);
            task.setFinisheddate(nowDate);
            task.setFinishedby(AuthenticationUser.getAuthenticationUser().getLoginname());
        } else if (StaticDict.Task__status.WAIT.getValue().equals(old.getStatus())) {
            task.setStatus(StaticDict.Task__status.DOING.getValue());
            task.setAssignedto(AuthenticationUser.getAuthenticationUser().getLoginname());
            task.setAssigneddate(nowDate);
            task.setRealstarted(nowDate);
        } else if (StaticDict.Task__status.PAUSE.getValue().equals(old.getStatus())) {
            task.setStatus(StaticDict.Task__status.DOING.getValue());
            task.setAssignedto(AuthenticationUser.getAuthenticationUser().getLoginname());
            task.setAssigneddate(nowDate);
        }
        if (teams.size() > 0) {
            old.set("task", task);
            old.setTaskteams(null);
            old.set("auto", false);
            computeHours4Multiple(old);

        }
        super.update(task);

        FileHelper.updateObjectID(task.getId(), StaticDict.File__object_type.TASK.getValue(), files, "", iFileService);

        List<History> changes = ChangeUtil.diff(old, task);
        if (actionid != null && changes.size() > 0) {
            ActionHelper.logHistory(actionid, changes, iHistoryService);
        }
        if (old.getParent()  != null && old.getParent() > 0) {
            task.set("parentId", task.getParent());
            task.set("changed", true);
            updateParentStatus(task);
        }
        if (old.getStory() != null && old.getStory() != 0L) {
            iStoryService.setStage(old.getZtstory());
        }
        //PmsEe操作任务，需判断状态，计算关联的计划的状态
        if (task.getPlan() != null && task.getPlan() > 0) {
            //该任务有计划，根据任务状态更新计划状态
            this.updateRelatedPlanStatus(task);
        }
        return et;
    }

    /**
     * [RecordTimZeroLeftAfterContinue:继续任务时填入预计剩余为0设置为进行中] 行为扩展：继续任务
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task recordTimZeroLeftAfterContinue(Task et) {
        et = this.get(et.getId());
        Task newTask = new Task();
        newTask.setId(et.getId());
        if (et.getLeft() == 0) {
            newTask.setStatus(StaticDict.Task__status.DOING.getValue());
            newTask.setFinishedby("");
            newTask.setFinisheddate(null);
            super.sysUpdate(newTask);
            List<Action> actionList = iActionService.list(new QueryWrapper<Action>().eq("objectID", et.getId()).eq("actor", AuthenticationUser.getAuthenticationUser().getUsername()).eq("action", StaticDict.Action__type.FINISHED.getValue()).orderByDesc("date"));
            if (actionList.size() > 0) {
                Action action = actionList.get(0);
                Action old = new Action();
                old.setId(action.getId());
                old.setAction(StaticDict.Action__type.RESTARTED.getValue());
                iActionService.updateById(old);
            }
        }
        if (et.getParent()  != null && et.getParent() > 0) {
            et.set("changed", true);
            updateParentStatus(et);
        }
        return et;
    }

    /**
     * [RecordTimateZeroLeft:预计剩余为0进行中] 行为扩展：激活和填入工时
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task recordTimateZeroLeft(Task et) {
        et = this.get(et.getId());
        Task newTask = new Task();
        newTask.setId(et.getId());
        if (et.getLeft() == 0) {
            newTask.setStatus(StaticDict.Task__status.DOING.getValue());
            newTask.setFinishedby("");
            newTask.setFinisheddate(null);
            super.sysUpdate(newTask);
        }
        if (et.getParent()  != null && et.getParent() > 0) {
            et.set("changed", true);
            updateParentStatus(et);
        }
        return et;
    }

    /**
     * [RecordTimateZeroLeftAfterStart:开始任务时填入预计剩余为0设为进行中] 行为扩展：开始任务
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task recordTimateZeroLeftAfterStart(Task et) {
        et = this.get(et.getId());
        Task newTask = new Task();
        newTask.setId(et.getId());
        if (et.getLeft() == 0) {
            newTask.setStatus(StaticDict.Task__status.DOING.getValue());
            newTask.setFinishedby("");
            newTask.setFinisheddate(null);
            super.sysUpdate(newTask);
            List<Action> actionList = iActionService.list(new QueryWrapper<Action>().eq("objectID", et.getId()).eq("actor", AuthenticationUser.getAuthenticationUser().getUsername()).eq("action", StaticDict.Action__type.FINISHED.getValue()).orderByDesc("date"));
            if (actionList.size() > 0) {
                Action action = actionList.get(0);
                Action old = new Action();
                old.setId(action.getId());
                old.setAction(StaticDict.Action__type.STARTED.getValue());
                iActionService.updateById(old);
            }
        }
        if (et.getParent()  != null && et.getParent() > 0) {
            et.set("changed", true);
            updateParentStatus(et);
        }
        return et;
    }

    /**
     * [Restart:继续] 行为扩展：重启挂起的任务
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task restart(Task et) {
        et.set("actioninfo", "当前任务只有%1$s才可以继续。");
        this.taskForward(et);
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        Task old = new Task();
        CachedBeanCopier.copy(this.get(et.getId()), old);
        Task newTask = new Task();
        newTask.setId(et.getId());
        newTask.setConsumed(et.getConsumed());
        newTask.setRealstarted(et.getRealstarted() != null ? et.getRealstarted() : ZTDateUtil.now());
        if (newTask.getConsumed() < old.getConsumed()) {
            throw new RuntimeException("总计消耗必须大于原先消耗");
        }
        String noticeusers = et.getNoticeusers();
        starts(et, old, newTask);

        //PmsEe操作任务，需判断状态，计算关联的计划的状态
        if (newTask.getPlan() != null && newTask.getPlan() > 0) {
            //该任务有计划，根据任务状态更新计划状态
            this.updateRelatedPlanStatus(newTask);
        }
        List<History> changes = ChangeUtil.diff(old, newTask);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {
            String strAction = StaticDict.Action__type.RESTARTED.getValue();
            String strActionText = StaticDict.Action__type.RESTARTED.getText();
            if (et.getLeft() == 0) {
                strAction = StaticDict.Action__type.FINISHED.getValue();
                strActionText = StaticDict.Action__type.FINISHED.getText();
            }

            ActionHelper.sendTodoOrToread(newTask.getId(), newTask.getName(), noticeusers, newTask.getAssignedto(), newTask.getMailto(), "任务", StaticDict.Action__object_type.STORY.getValue(), "tasks", strActionText, false, iActionService);
            ActionHelper.createHis(newTask.getId(), StaticDict.Action__object_type.TASK.getValue(), changes, strAction, comment, "", null, iActionService);

        }
        if (newTask.getStory() != null && newTask.getStory() != 0L) {
            iStoryService.setStage(newTask.getZtstory());
        }
        return et;
    }

    /**
     * [SendMessage:行为] 行为扩展
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task sendMessage(Task et) {
        return super.sendMessage(et);
    }

    /**
     * [SendMsgPreProcess:发送消息前置处理] 行为扩展
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task sendMsgPreProcess(Task et) {
        return super.sendMsgPreProcess(et);
    }

    /**
     * [Start:开始] 行为扩展
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task start(Task et) {
        et.set("actioninfo", "当前任务只有%1$s才可以开始。");
        this.taskForward(et);
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        Task old = new Task();
        CachedBeanCopier.copy(this.get(et.getId()), old);

        Task newTask = new Task();
        newTask.setId(et.getId());
        String noticeusers = et.getNoticeusers();
        starts(et, old, newTask);

        //PmsEe操作任务，需判断状态，计算关联的计划的状态
        if (newTask.getPlan() != null && newTask.getPlan() > 0) {
            //该任务有计划，根据任务状态更新计划状态
            this.updateRelatedPlanStatus(newTask);
        }
        List<History> changes = ChangeUtil.diff(old, newTask);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {
            String strAction = StaticDict.Action__type.STARTED.getValue();
            String strActionText = StaticDict.Action__type.STARTED.getText();
            if (et.getLeft() == 0) {
                strAction = StaticDict.Action__type.FINISHED.getValue();
                strActionText = StaticDict.Action__type.FINISHED.getText();
            }

            ActionHelper.sendTodoOrToread(newTask.getId(), newTask.getName(), noticeusers, newTask.getAssignedto(), newTask.getMailto(), "任务", StaticDict.Action__object_type.STORY.getValue(), "tasks", strActionText, false, iActionService);
            ActionHelper.createHis(newTask.getId(), StaticDict.Action__object_type.TASK.getValue(), changes, strAction, comment, "", null, iActionService);

        }
        if (newTask.getStory() != null && newTask.getStory() != 0L) {
            iStoryService.setStage(newTask.getZtstory());
        }
        return et;
    }

    @Transactional(rollbackFor = Exception.class)
    public void starts(Task et, Task old, Task newTask) {
        String files = et.getFiles();
        newTask.setStatus(StaticDict.Task__status.DOING.getValue());
        newTask.setLeft(et.getLeft() != null ? et.getLeft() : 0d);
        newTask.setConsumed(et.getConsumed() != null ? et.getConsumed() : 0d);
        newTask.setRealstarted(et.getRealstarted() != null ? et.getRealstarted() : ZTDateUtil.now());
        if (StringUtils.compare(old.getAssignedto(), AuthenticationUser.getAuthenticationUser().getUsername()) != 0) {
            newTask.setAssigneddate(ZTDateUtil.now());
        }
        if (newTask.getLeft() == 0) {
            newTask.setStatus(StaticDict.Task__status.DONE.getValue());
            newTask.setFinisheddate(ZTDateUtil.now());
            newTask.setFinishedby(AuthenticationUser.getAuthenticationUser().getUsername());
            newTask.setAssignedto(old.getOpenedby());
        }

        if (newTask.getConsumed() != null && newTask.getConsumed() > 0) {
            //taskEstimate
            TaskEstimate taskEstimate = new TaskEstimate();
            taskEstimate.setTask(newTask.getId());
            taskEstimate.setAccount(AuthenticationUser.getAuthenticationUser().getUsername());
            taskEstimate.setDate(newTask.getRealstarted());
            taskEstimate.setLeft(newTask.getLeft() != null ? newTask.getLeft() : 0.0);
            taskEstimate.setConsumed(newTask.getConsumed() != null ? newTask.getConsumed() : 0.0);
            iTaskEstimateService.create(taskEstimate);
        }

        //teams
        List<Team> teams = iTeamService.list(new QueryWrapper<Team>().eq("root", newTask.getId()).eq("type", StaticDict.Team__type.TASK.getValue()));
        if (teams.size() > 0) {
            String oldAssignTo = StringUtils.isNotBlank(old.getAssignedto()) ? old.getAssignedto() : teams.get(0).getAccount();
            Team team = new Team();
            team.setLeft(et.getLeft());
            team.setConsumed(et.getConsumed());
            iTeamService.update(team, new QueryWrapper<Team>().eq("root", newTask.getId()).eq("type", StaticDict.Team__type.TASK.getValue()).eq("account", oldAssignTo));
            old.set("task", newTask);
            old.setTaskteams(null);
            old.set("auto", false);
            computeHours4Multiple(old);
        }

        super.update(newTask);
        //保存开始任务时上传的附件
        FileHelper.updateObjectID(newTask.getId(), StaticDict.File__object_type.TASK.getValue(), files, "", iFileService);
        if (old.getParent() != null && old.getParent() > 0) {
            old.set("parentId", old.getParent());
            old.set("changed", true);
            updateParentStatus(old);
            computeBeginAndEnd(this.get(old.getParent()));
        }
    }

    /**
     * [TaskForward:检查多人任务操作权限] 行为扩展
     *
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task taskForward(Task et) {
        Object actioninfo = et.get("actioninfo");
        Task task = this.get(et.getId());
        if (StaticDict.YesNo.ITEM_1.getValue().equals(task.getMultiple())) {
            if (!AuthenticationUser.getAuthenticationUser().getUsername().equals(task.getAssignedto())) {
                SysEmployeeSearchContext context = new SysEmployeeSearchContext();
                context.setN_username_in(task.getAssignedto());
                Page<SysEmployee> page = sysEmployeeFeignClient.searchDefault(context);
                List<SysEmployee> list = page.getContent();
                if (list.size() > 0) {
                    throw new RuntimeException(String.format(actioninfo.toString(), list.get(0).getPersonname()));
                }
                throw new RuntimeException(String.format(actioninfo.toString(), task.getAssignedto()));
            }
        }
        //自定义代码
        return et;
    }

    /**
     * 查询集合 当前项目任务
     */
    @Override
    public Page<Task> searchCurProjectTaskQuery(TaskSearchContext context) {
        context.setN_parent_ltandeq(0L);
        Page<Task> page = super.searchCurProjectTaskQuery(context);
        /*for (Task task : page.getContent()) {
            // 子任务
            if (task.getParent() != null && task.getParent() < 0) {
                TaskSearchContext context1 = new TaskSearchContext();
                context1.setSelectCond(context.getSelectCond().clone());
                context1.setN_parent_eq(task.getId());
                context1.setN_parent_ltandeq(null);

//                List<Task> taskList = this.searchChildDefault(context1).getContent();
//                task.set("items", taskList);
            }
        }*/
        return page;
    }

}

