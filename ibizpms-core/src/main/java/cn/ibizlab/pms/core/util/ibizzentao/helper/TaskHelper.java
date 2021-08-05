package cn.ibizlab.pms.core.util.ibizzentao.helper;

import cn.ibizlab.pms.core.ibiz.domain.TaskTeam;
import cn.ibizlab.pms.core.ibizplugin.service.IIBIZProMessageService;
import cn.ibizlab.pms.core.ou.service.ISysEmployeeService;
import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.util.ibizzentao.common.ZTDateUtil;
import cn.ibizlab.pms.core.zentao.domain.*;
import cn.ibizlab.pms.core.zentao.filter.TaskSearchContext;
import cn.ibizlab.pms.core.zentao.mapper.TaskMapper;
import cn.ibizlab.pms.core.zentao.service.*;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.errors.BadRequestAlertException;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.helper.DataObject;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author huwei
 */
@Component
@Slf4j
public class TaskHelper extends ZTBaseHelper<TaskMapper, Task> {

    @Autowired
    ITaskEstimateService taskEstimateService;
    @Autowired
    ITaskService taskService;
    @Autowired
    IStoryService storyService;
    @Autowired
    IProjectService projectService;
    @Autowired
    ActionHelper actionHelper;

    @Autowired
    FileHelper fileHelper;

    @Autowired
    UserHelper userHelper;

    @Autowired
    TeamHelper teamHelper;

    @Autowired
    TaskEstimateHelper taskEstimateHelper;

    @Autowired
    BugHelper bugHelper;

    @Autowired
    ITeamService teamService;

    @Autowired
    StoryHelper storyHelper;

    @Autowired
    TaskTeamHelper taskTeamHelper;

    @Autowired
    IIBIZProMessageService iibizProMessageService;

    @Autowired
    ISysEmployeeService sysEmployeeService;

    @Autowired
    IProductPlanService productPlanService;
    @Autowired
    ProductPlanHelper productPlanHelper;
    @Autowired
            IActionService iActionService;
    @Autowired
            IHistoryService iHistoryService;

    String[] diffAttrs = {"desc"};
    List<String> ignore = Arrays.asList("totalwh", "totalleft", "totalconsumed", "totalestimate");

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
    @Transactional(rollbackFor = Exception.class)
    public boolean create(Task et) {
        boolean bOk = false;
        jugEststartedAndDeadline(et);
        String multiple = et.getMultiple();//多人任务
        List<TaskTeam> taskTeams = et.getTaskteam();//任务团队成员
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
            // et.setParent(-1L);
            et.setEststarted(et.getConfigbegin());
            et.setDeadline(et.getConfigend());
            et.setAssignedto(et.getAssignedtopk());
        }

        fileHelper.processImgURL(et, null, null);
        if (et.getStory() != null && et.getStory() != 0L) {
            et.setStoryversion(storyHelper.get(et.getStory()).getVersion());
        }
        if (et.getAssignedto() != null && !"".equals(et.getAssignedto())) {
            et.setAssigneddate(ZTDateUtil.now());
        } else {
            et.set("Assigneddate", DEFAULT_TIME);
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

        fileHelper.updateObjectID(et.getId(), StaticDict.File__object_type.TASK.getValue(), files, "");


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
                teamHelper.create(team);
                i++;
            }
            this.internalUpdate(et);

        }
        //PmsEe操作任务，需判断状态，计算关联的计划的状态
        if (et.getPlan() != null && et.getPlan() > 0){
            //该任务有计划，根据任务状态更新计划状态
            this.updateRelatedPlanStatus(et,et.getPlan());
        }

        actionHelper.sendTodo(et.getId(), et.getName(), noticeusers, et.getAssignedto(), et.getMailto(), ITaskService.OBJECT_TEXT_NAME, StaticDict.Action__object_type.TASK.getValue(), ITaskService.OBJECT_SOURCE_PATH, StaticDict.Action__type.OPENED.getText());
        actionHelper.create(StaticDict.Action__object_type.TASK.getValue(), et.getId(), StaticDict.Action__type.OPENED.getValue(), "", "", null, true);
        if (et.getStory() != null && et.getStory() != 0L) {
            storyHelper.setStage(et.getZtstory());
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public Task otherUpdate(Task et) {

        Task newTask = new Task();
        newTask.setId(et.getId());

        if (et.getName() != null) {
            newTask.setName(et.getName());
        } else if (DataObject.getBooleanValue(et.get(FIELD_NAMEDIRTYFLAG), false)) {
            throw new RuntimeException("任务名称不能为空！");
        }
        if (et.getType() != null) {
            newTask.setType(et.getType());
        } else if (DataObject.getBooleanValue(et.get(FIELD_TYPEDIRTYFLAG), false)) {
            newTask.setType("");
        }
        if (et.getDeadline() != null) {
            newTask.setDeadline(et.getDeadline());
        } else if (DataObject.getBooleanValue(et.get(FIELD_DEADLINEDIRTYFLAG), false)) {
            newTask.setDeadline(null);
        }
        if (et.getPri() != null) {
            newTask.setPri(et.getPri());
        } else if (DataObject.getBooleanValue(et.get(FIELD_PRIDIRTYFLAG), false)) {
            newTask.setPri(0);
        }
        if (et.getDesc() != null) { //任务描述
            newTask.setDesc(et.getDesc());
        } else if (DataObject.getBooleanValue(et.get(FIELD_DESCDIRTYFLAG), false)) {
            newTask.setDesc("");
        }
        if (et.getMailto() != null) { //抄送给
            newTask.setMailto(et.getMailto());
        } else if (DataObject.getBooleanValue(et.get(FIELD_MAILTODIRTYFLAG), false)) {
            newTask.setMailto("");
        }
        Timestamp now = ZTDateUtil.now();
        newTask.setLastediteddate(now);
        newTask.setLasteditedby(AuthenticationUser.getAuthenticationUser().getUsername());
        String noticeusers = et.getNoticeusers();
        this.internalUpdate(newTask);

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

    public void judChanges(List<History> changes, Task et, String noticeusers, String comment) {
        String strAction = StaticDict.Action__type.EDITED.getValue();
        String strActionText = StaticDict.Action__type.EDITED.getText();
        if (changes.size() == 0) {
            strAction = StaticDict.Action__type.COMMENTED.getValue();
            strActionText = StaticDict.Action__type.COMMENTED.getText();
        }
        actionHelper.sendToread(et.getId(), et.getName(), noticeusers, et.getAssignedto(),
                et.getMailto(), ITaskService.OBJECT_TEXT_NAME, StaticDict.Action__object_type.TASK.getValue(),
                ITaskService.OBJECT_SOURCE_PATH, strActionText);
        Action action = actionHelper.create(StaticDict.Action__object_type.TASK.getValue(), et.getId(), strAction,
                comment, "", null, true);
        if (changes.size() > 0) {
            actionHelper.logHistory(action.getId(), changes);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean edit(Task et) {
        jugEststartedAndDeadline(et);
        if (null != et.getConfigbegin()){
            et.setEststarted(et.getConfigbegin());
        }
        if (null != et.getConfigend()){
            et.setDeadline(et.getConfigend());
        }
        if (StaticDict.TaskSpecies.CYCLE.getValue().equals(et.getTaskspecies())){
            createByCycle(et);
        }
        String multiple = et.getMultiple();
        List<TaskTeam> teams = et.getTaskteam();
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
            Story story = storyHelper.getById(et.getStory());
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
                    et.setCanceledby(get(et.getCanceledby(), AuthenticationUser.getAuthenticationUser().getUsername()));
                    et.setCanceleddate(get(et.getCanceleddate(), ZTDateUtil.now()));
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
            this.update(data, (Wrapper<Task>) data.getUpdateWrapper(true).eq(FIELD_PARENT, et.getId()));
        }
        fileHelper.processImgURL(et, null, null);

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


        teamHelper.remove(new QueryWrapper<Team>().eq(FIELD_ROOT, et.getId()).eq(FIELD_TYPE, StaticDict.Team__type.TASK.getValue()));
        if (StringUtils.compare(multiple, StaticDict.YesNo.ITEM_1.getValue()) == 0 && teams != null && !teams.isEmpty()) {
            String assignedto = "";
            for (TaskTeam team : teams) {
                if (team.getAccount() == null || "".equals(team.getAccount())) {
                    continue;
                }
                if ("".equals(assignedto)) {
                    assignedto = team.getAccount();
                }
                taskTeamHelper.create(team);
            }
            this.computeHours4Multiple(old, et, null, false);
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
        this.internalUpdate(et);
        fileHelper.updateObjectID(et.getId(), StaticDict.File__object_type.TASK.getValue(), files, "");
        boolean changeParent = false;
        if (!et.getParent().equals(old.getParent())) {
            changeParent = true;
        }

        if (old.getParent() > 0) {
            Task oldParent = this.get(old.getParent());
            updateParentStatus(et, oldParent.getId(), !changeParent);
            computeBeginAndEnd(oldParent);
            if (changeParent) {
                int oldChildCount = this.count(new QueryWrapper<Task>().eq(FIELD_PARENT, old.getParent()));
                if (oldChildCount == 0) {
                    Task task1 = new Task();
                    task1.setId(old.getParent());
                    task1.setParent(0L);
                    this.internalUpdate(task1);
                }
                Task task2 = new Task();
                task2.setId(old.getParent());
                task2.setLasteditedby(AuthenticationUser.getAuthenticationUser().getUsername());
                task2.setLastediteddate(ZTDateUtil.now());
                this.internalUpdate(task2);
                actionHelper.create(StaticDict.Action__object_type.TASK.getValue(), et.getId(), StaticDict.Action__type.UNLINKPARENTTASK.getValue(), "", String.valueOf(old.getParent()), null, false);

                Action action = actionHelper.create(StaticDict.Action__object_type.TASK.getValue(), old.getParent(), StaticDict.Action__type.UNLINKCHILDRENTASK.getValue(), "", String.valueOf(et.getId()), null, false);
                Task parent = this.get(et.getParent());
                List<History> changes = ChangeUtil.diff(oldParent, parent);
                if (changes.size() > 0) {
                    actionHelper.logHistory(action.getId(), changes);
                }

            }
        }

        if (et.getParent() > 0) {
            Task parentTask = this.getById(et.getParent());
//            Task task2 = new Task();
//            task2.setId(et.getParent());
//            // task2.setParent(-1L);
//            this.internalUpdate(task2);
            updateParentStatus(et, et.getParent(), !changeParent);
            computeBeginAndEnd(this.get(et.getParent()));
            if (changeParent) {
                Task task1 = new Task();
                task1.setLasteditedby(AuthenticationUser.getAuthenticationUser().getUsername());
                task1.setLastediteddate(ZTDateUtil.now());
                task1.setId(et.getParent());
                actionHelper.create(StaticDict.Action__object_type.TASK.getValue(), et.getId(), StaticDict.Action__type.LINKPARENTTASK.getValue(), "", String.valueOf(et.getParent()), null, false);
                Action action1 = actionHelper.create(StaticDict.Action__object_type.TASK.getValue(), et.getParent(), StaticDict.Action__type.LINKCHILDTASK.getValue(), "", String.valueOf(et.getId()), null, false);
                Task newParentTask = this.getById(et.getParent());
                List<History> changes = ChangeUtil.diff(parentTask, newParentTask);
                if (changes.size() > 0) {
                    actionHelper.logHistory(action1.getId(), changes);
                }
            }
        }
        //PmsEe操作任务，需判断状态，计算关联的计划的状态
        if (et.getPlan() != null && et.getPlan() > 0){
            //该任务有计划，根据任务状态更新计划状态
            this.updateRelatedPlanStatus(et,et.getPlan());
        }

        et.setStatus1(old.getStatus1());
        List<History> changes = ChangeUtil.diff(old, et, null, null, diffAttrs);
        this.removeIgonreChanges(changes);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {
            judChanges(changes, et, noticeusers, comment);
        }
        if (et.getStory() != null && et.getStory() != 0L) {
            storyHelper.setStage(et.getZtstory());
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long key) {
        boolean bOk = false;
        Task old = this.get(key);
        if (old.getParent() < 0) {
            throw new RuntimeException("不能删除父任务");
        }
        bOk = super.delete(key);
        if (old.getParent() > 0) {
            updateParentStatus(old, old.getParent(), false);
            actionHelper.create(StaticDict.Action__object_type.TASK.getValue(), old.getParent(), StaticDict.Action__type.DELETECHILDRENTASK.getValue(), "", "", null, true);
        }
        if (old.getFrombug() != 0) {
            Bug bug = new Bug();
            bug.setId(old.getFrombug());
            bug.setTotask(0L);
            bugHelper.internalUpdate(bug);
        }
        if (old.getStory() != null && old.getStory() != 0L) {
            storyHelper.setStage(old.getZtstory());
        }

        //PmsEe操作任务，需判断状态，计算关联的计划的状态
        if (old.getPlan() != null && old.getPlan() > 0){
            //该任务有计划，根据任务状态更新计划状态
            this.updateRelatedPlanStatus(old,old.getPlan());
        }
        return bOk;
    }

    public void computeHours4Multiple(Task old, Task task, List<TaskTeam> teams, boolean auto) {
        if (old == null) {
            return;
        }
        if (teams == null) {
            teams = taskTeamHelper.list(new QueryWrapper<TaskTeam>().eq(FIELD_ROOT, old.getId()).eq(FIELD_TYPE, StaticDict.Team__type.TASK.getValue()).orderByAsc("`order`"));
        }
        if (teams != null && teams.size() != 0) {
            Timestamp now = ZTDateUtil.now();
            Task currentTask = task != null ? task : new Task();
            if (currentTask.getStatus() == null) {
                currentTask.setStatus(old.getStatus());
            }
            if (task.getAssignedto() != null) {
                currentTask.setAssignedto(task.getAssignedto());
            } else {
                if (old.getAssignedto() == null) {
                    TaskTeam firstMember = teams.get(0);
                    currentTask.setAssignedto(firstMember.getAccount());
                    currentTask.setAssigneddate(now);
                } else {
                    for (TaskTeam team : teams) {
                        if (team.getAccount() != null && team.getAccount().equals(old.getAssignedto()) && team.getLeft() == 0 && team.getConsumed() != 0) {
                            if (!old.getAssignedto().equals(teams.get(teams.size() - 1).getAccount())) {
                                currentTask.setAssignedto(this.getNextUser(teams, old));
                            } else {
                                currentTask.setAssignedto(old.getOpenedby());
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
                        if (team.getAccount().equals(old.getAssignedto())) {
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
                if (!old.getAssignedto().equals(currentTask.getAssignedto()) || currentTask.getStatus().equals(StaticDict.Task__status.DONE.getValue())) {
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
                    if (flag && left == 0 && !old.getFinishedlist().contains(login)) { //完成者列表
                        String finsihList = old.getFinishedlist() + "," + login;
                        if (finsihList.indexOf(",") == 0) {
                            finsihList.substring(1);
                        } else if (finsihList.indexOf(",") == finsihList.length() - 1) {
                            finsihList.substring(0, finsihList.length() - 1);
                        }
                        currentTask.setFinishedlist(finsihList);

                    }
                    if ((StaticDict.Task__status.DONE.getValue().equals(old.getStatus()) || StaticDict.Task__status.CLOSED.getValue().equals(old.getStatus())) && StaticDict.Task__status.DOING.getValue().equals(currentTask.getStatus()) && old.getStatus() != null) {
                        if (old.getFinishedlist().contains(old.getAssignedto()) && old.getFinishedlist().length() >= old.getFinishedlist().indexOf(old.getAssignedto())) {
                            currentTask.setFinishedlist(old.getFinishedlist().substring(0, old.getFinishedlist().indexOf(old.getAssignedto()) - 1));
                        }
                    }
                }
            }
            currentTask.setId(old.getId());
            this.internalUpdate(currentTask);
        }
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

    public Task getNextTeamUser(Task et) {
        et = this.getById(et.getId());
        List<Team> teamList = teamHelper.list(new QueryWrapper<Team>().eq(FIELD_ROOT, et.getId()).eq(FIELD_TYPE, StaticDict.Team__type.TASK.getValue()).orderByAsc(" `order` "));
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

    @Transactional(rollbackFor = Exception.class)
    public void computeBeginAndEnd(Task et) {
        if (et.getId() == null) {
            return;
        }
        et = this.get(et.getId());
        String sql = String.format("select estStarted, realStarted, deadline from zt_task where parent = %1$s and `status` <> 'cancel'  and deleted = '0'", et.getId());
        List<JSONObject> list = taskService.select(sql, null);
        if (list.size() == 0) {
            return;
        }
        Timestamp earliestEstStarted = et.getEststarted();
        Timestamp earliestRealStarted = et.getRealstarted();
        Timestamp latestDeadline = et.getDeadline();
        for (JSONObject jsonObject : list) {
            if (jsonObject == null) {
                continue;
            }
            Timestamp estStarted = jsonObject.getTimestamp(FIELD_ESTSTARTED);
            Timestamp realStarted = jsonObject.getTimestamp(FIELD_REALSTARTED);
            Timestamp deadline = jsonObject.getTimestamp(FIELD_DEADLINE);
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
        this.internalUpdate(task);
    }

    @Transactional(rollbackFor = Exception.class)
    public void computeWorkingHours(Task et) {
        if (et.getId() == null) {
            return;
        }

//        String sql = String.format("select * from zt_task where parent = %1$s and status <> 'cancel' and deleted = '0'", et.getId());
        List<Task> list = this.list(new QueryWrapper<Task>().eq("parent", et.getId()).ne("status", "cancel").eq("deleted", "0").isNotNull("Consumed"));
//        List<JSONObject> list = taskService.select(sql, null);
        if (list.size() == 0) {
            return;
        }
        double estimate = 0;
        double consumed = 0;
        double left = 0;
        for (Task task1 : list) {
            estimate += task1.getEstimate() != null ? task1.getEstimate() : 0.0;
            consumed += task1.getConsumed() != null ? task1.getConsumed()  : 0.0;
            if (!StaticDict.Task__status.CLOSED.getValue().equals(task1.getStatus())) {
                left += task1.getLeft() != null ? task1.getLeft() : 0.0;
            }
        }
        Task task = new Task();
        task.setId(et.getId());
        task.setEstimate(estimate);
        task.setConsumed(consumed);
        task.setLeft(left);
        this.internalUpdate(task);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateParentStatus(Task childTask, Long parentId, boolean changed) {
        log.info("updateParentStatus:未实现");
        if (parentId == null) {
            parentId = childTask.getParent();
        }
        if (parentId <= 0) {
            return;
        }
        Task oldParentTask = this.get(parentId);

//        if (oldParentTask.getParent() != -1) {
//            Task pTask = new Task();
//            pTask.setId(parentId);
//            // pTask.setParent(-1L);
//            this.internalUpdate(pTask);
//        }
        computeWorkingHours(oldParentTask);

        String sql = String.format("select * from zt_task where parent = %1$s and deleted = '0'", parentId);
        List<JSONObject> childTasks = taskService.select(sql, null);
        if (childTasks.size() == 0) {
            Task pTask = new Task();
            pTask.setId(parentId);
            pTask.setParent(0L);
            this.internalUpdate(pTask);
            return;
        }

        //根据子任务状态计算父任务状态
        String status = this.getParentOrRelatedPlanStatusByChildTasks(childTasks);

        Task parentTask = this.getOne(new QueryWrapper<Task>().eq(FIELD_ID, parentId).eq(FIELD_DELETED, '0'));
        if (parentTask == null) {
            Task task1 = new Task();
            task1.setId(childTask.getId());
            task1.setParent(0L);
            this.internalUpdate(task1);
            return;
        }
        //
        if (!"".equals(status) && !status.equals(oldParentTask.getStatus())) {
            Task task = new Task();
            Timestamp nowDate = ZTDateUtil.now();
            task.setStatus(status);
            task.setId(parentId);
            task.setLastediteddate(nowDate);
            // task.setParent(-1L);
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
                    task.setAssignedto(childTask.getAssignedto());
                    task.setCanceleddate(nowDate);
                }
                task.setFinishedby("");
                task.setFinisheddate(null);
                task.setClosedby("");
                task.setCloseddate(null);
                task.setClosedreason("");
            }
            this.internalUpdate(task);
            //PmsEe操作任务，需判断状态，计算关联的计划的状态
            if (task.getPlan() != null && task.getPlan() > 0){
                //该任务有计划，根据任务状态更新计划状态
                this.updateRelatedPlanStatus(task,task.getPlan());
            }
            if (!changed) {
                return;
            }

            List<History> changes = ChangeUtil.diff(oldParentTask, this.get(parentId));
            this.removeIgonreChanges(changes);


            //根据原本父任务状态，和stauts计算行为类型actiontype
            String action = this.getActionTypeByStatus(oldParentTask.getStatus(),status);
            if (!"".equals(action)) {
                Action action1 = actionHelper.create(StaticDict.Action__object_type.TASK.getValue(), parentId, action,
                        "", "", null, false);
                if (changes.size() > 0) {
                    actionHelper.logHistory(action1.getId(), changes);
                }
            }
        } else {
            List<History> changes = ChangeUtil.diff(oldParentTask, this.get(parentId));
            this.removeIgonreChanges(changes);
            if (changes.size() > 0) {
                Action action1 = actionHelper.create(StaticDict.Action__object_type.TASK.getValue(), parentId, StaticDict.Action__type.EDITED.getValue(),
                        "", "", null, false);
                actionHelper.logHistory(action1.getId(), changes);
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Task start(Task et) {
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        Task old = new Task();
        CachedBeanCopier.copy(this.get(et.getId()), old);

        Task newTask = new Task();
        newTask.setId(et.getId());
        String noticeusers = et.getNoticeusers();
        starts(et, old, newTask);

        //PmsEe操作任务，需判断状态，计算关联的计划的状态
        if (newTask.getPlan() != null && newTask.getPlan() > 0){
            //该任务有计划，根据任务状态更新计划状态
            this.updateRelatedPlanStatus(newTask,newTask.getPlan());
        }
        List<History> changes = ChangeUtil.diff(old, newTask);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {
            String strAction = StaticDict.Action__type.STARTED.getValue();
            String strActionText = StaticDict.Action__type.STARTED.getText();
            if (et.getLeft() == 0) {
                strAction = StaticDict.Action__type.FINISHED.getValue();
                strActionText = StaticDict.Action__type.FINISHED.getText();
            }
            actionHelper.sendToread(newTask.getId(), newTask.getName(), noticeusers, newTask.getAssignedto(),
                    newTask.getMailto(), ITaskService.OBJECT_TEXT_NAME, StaticDict.Action__object_type.TASK.getValue(),
                    ITaskService.OBJECT_SOURCE_PATH, strActionText);
            Action action = actionHelper.create(StaticDict.Action__object_type.TASK.getValue(), newTask.getId(), strAction,
                    comment, "", null, true);
            if (changes.size() > 0) {
                actionHelper.logHistory(action.getId(), changes);
            }
        }
        if (newTask.getStory() != null && newTask.getStory() != 0L) {
            storyHelper.setStage(newTask.getZtstory());
        }
        return et;
    }

    @Transactional(rollbackFor = Exception.class)
    public void starts(Task et, Task old, Task newTask) {
        String files = et.getFiles();
        newTask.setStatus(StaticDict.Task__status.DOING.getValue());
        newTask.setLeft(et.getLeft() != null ? et.getLeft() : 0d);
        newTask.setConsumed(et.getConsumed() != null ? et.getConsumed() : 0d);
        newTask.setRealstarted(get(et.getRealstarted(), ZTDateUtil.now()));
        if (StringUtils.compare(old.getAssignedto(), AuthenticationUser.getAuthenticationUser().getUsername()) != 0) {
            newTask.setAssigneddate(ZTDateUtil.now());
        }
        if (newTask.getLeft() == 0) {
            newTask.setStatus(StaticDict.Task__status.DONE.getValue());
            newTask.setFinisheddate(ZTDateUtil.now());
            newTask.setFinishedby(AuthenticationUser.getAuthenticationUser().getUsername());
            newTask.setAssignedto(old.getOpenedby());
        }

        if(get(newTask.getConsumed(), 0.0) > 0) {
            //taskEstimate
            TaskEstimate taskEstimate = new TaskEstimate();
            taskEstimate.setTask(newTask.getId());
            taskEstimate.setAccount(AuthenticationUser.getAuthenticationUser().getUsername());
            taskEstimate.setDate(newTask.getRealstarted());
            taskEstimate.setLeft(get(newTask.getLeft(), 0.0));
            taskEstimate.setConsumed(get(newTask.getConsumed(), 0.0));
            taskEstimateHelper.create(taskEstimate);
        }

        //teams
        List<Team> teams = teamHelper.list(new QueryWrapper<Team>().eq(FIELD_ROOT, newTask.getId()).eq(FIELD_TYPE, StaticDict.Team__type.TASK.getValue()));
        if (teams.size() > 0) {
            String oldAssignTo = StringUtils.isNotBlank(old.getAssignedto()) ? old.getAssignedto() : teams.get(0).getAccount();
            Team team = new Team();
            team.setLeft(et.getLeft());
            team.setConsumed(et.getConsumed());
            teamHelper.update(team, new QueryWrapper<Team>().eq(FIELD_ROOT, newTask.getId()).eq(FIELD_TYPE, StaticDict.Team__type.TASK.getValue()).eq(FIELD_ACCOUNT, oldAssignTo));
            computeHours4Multiple(old, newTask, null, false);
        }

        this.internalUpdate(newTask);
        //保存开始任务时上传的附件
        fileHelper.updateObjectID(newTask.getId(),StaticDict.File__object_type.TASK.getValue(),files,"");
        if (old.getParent() > 0) {
            updateParentStatus(old, old.getParent(), true);
            computeBeginAndEnd(this.get(old.getParent()));
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Task restart(Task et) {
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        Task old = new Task();
        CachedBeanCopier.copy(this.get(et.getId()), old);
        Task newTask = new Task();
        newTask.setId(et.getId());
        newTask.setConsumed(et.getConsumed());
        newTask.setRealstarted(get(et.getRealstarted(), ZTDateUtil.now()));
        if (newTask.getConsumed() < old.getConsumed()) {
            throw new RuntimeException("总计消耗必须大于原先消耗");
        }
        String noticeusers = et.getNoticeusers();
        starts(et, old, newTask);

        //PmsEe操作任务，需判断状态，计算关联的计划的状态
        if (newTask.getPlan() != null && newTask.getPlan() > 0){
            //该任务有计划，根据任务状态更新计划状态
            this.updateRelatedPlanStatus(newTask,newTask.getPlan());
        }
        List<History> changes = ChangeUtil.diff(old, newTask);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {
            String strAction = StaticDict.Action__type.RESTARTED.getValue();
            String strActionText = StaticDict.Action__type.RESTARTED.getText();
            if (et.getLeft() == 0) {
                strAction = StaticDict.Action__type.FINISHED.getValue();
                strActionText = StaticDict.Action__type.FINISHED.getText();
            }
            actionHelper.sendToread(newTask.getId(), newTask.getName(), noticeusers, newTask.getAssignedto(), newTask.getMailto(),
                    ITaskService.OBJECT_TEXT_NAME, StaticDict.Action__object_type.TASK.getValue(), ITaskService.OBJECT_SOURCE_PATH, strActionText);
            Action action = actionHelper.create(StaticDict.Action__object_type.TASK.getValue(), newTask.getId(), strAction,
                    comment, "", null, true);
            if (changes.size() > 0) {
                actionHelper.logHistory(action.getId(), changes);
            }
        }
        if (newTask.getStory() != null && newTask.getStory() != 0L) {
            storyHelper.setStage(newTask.getZtstory());
        }
        return et;
    }

    @Transactional(rollbackFor = Exception.class)
    public Task pause(Task et) {
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        Task old = new Task();
        CachedBeanCopier.copy(this.get(et.getId()), old);
        Task newTask = new Task();
        newTask.setId(et.getId());
        newTask.setStatus(StaticDict.Task__status.PAUSE.getValue());
        newTask.setLastediteddate(ZTDateUtil.now());
        newTask.setLasteditedby(AuthenticationUser.getAuthenticationUser().getUsername());
        String noticeusers = et.getNoticeusers();
        this.internalUpdate(newTask);

        if (old.getParent() > 0) {
            this.updateParentStatus(newTask, newTask.getParent(), true);
        }
        //PmsEe操作任务，需判断状态，计算关联的计划的状态
        if (newTask.getPlan() != null && newTask.getPlan() > 0){
            //该任务有计划，根据任务状态更新计划状态
            this.updateRelatedPlanStatus(newTask,newTask.getPlan());
        }
        List<History> changes = ChangeUtil.diff(old, newTask);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {
            actionHelper.sendToread(newTask.getId(), newTask.getName(), noticeusers, newTask.getAssignedto(), newTask.getMailto(),
                    ITaskService.OBJECT_TEXT_NAME, StaticDict.Action__object_type.TASK.getValue(), ITaskService.OBJECT_SOURCE_PATH, StaticDict.Action__type.PAUSED.getText());
            Action action = actionHelper.create(StaticDict.Action__object_type.TASK.getValue(), newTask.getId(), StaticDict.Action__type.PAUSED.getValue(),
                    comment, "", null, true);
            if (changes.size() > 0) {
                actionHelper.logHistory(action.getId(), changes);
            }
        }
        if (newTask.getStory() != null && newTask.getStory() != 0L) {
            storyHelper.setStage(newTask.getZtstory());
        }
        return newTask;
    }

    @Transactional(rollbackFor = Exception.class)
    public Task assignTo(Task et) {
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
        List<Team> teams = teamHelper.list(new QueryWrapper<Team>().eq(FIELD_ROOT, et.getId()).eq(FIELD_TYPE, StaticDict.Team__type.TASK.getValue()));
        if (teams.size() > 0) {

            Team team = new Team();
            team.setLeft(0d);
            teamHelper.update(team, new QueryWrapper<Team>().eq(FIELD_ROOT, old.getId()).eq(FIELD_TYPE, StaticDict.Team__type.TASK.getValue()).eq(FIELD_ACCOUNT, old.getAssignedto()));
            Team team1 = new Team();
            team1.setLeft(newTask.getLeft());
            teamHelper.update(team1, new QueryWrapper<Team>().eq(FIELD_ROOT, newTask.getId()).eq(FIELD_TYPE, StaticDict.Team__type.TASK.getValue()).eq(FIELD_ACCOUNT, newTask.getAssignedto()));

            computeHours4Multiple(old, newTask, null, false);
        }


        if (old.getParent() > 0) {
            updateParentStatus(newTask, old.getParent(), true);
        }
        String noticeusers = et.getNoticeusers();
        this.internalUpdate(newTask);

        //PmsEe操作任务，需判断状态，计算关联的计划的状态
        if (newTask.getPlan() != null && newTask.getPlan() > 0){
            //该任务有计划，根据任务状态更新计划状态
            this.updateRelatedPlanStatus(newTask,newTask.getPlan());
        }
        List<History> changes = ChangeUtil.diff(old, newTask);
        this.removeIgonreChanges(changes);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {
            if (jugAssignToIsChanged(old, et)) {
                actionHelper.sendMarkDone(newTask.getId(), newTask.getName(), old.getAssignedto(), ITaskService.OBJECT_TEXT_NAME, StaticDict.Action__object_type.TASK.getValue(), ITaskService.OBJECT_SOURCE_PATH, StaticDict.Action__type.ASSIGNED.getText());
                actionHelper.sendTodo(newTask.getId(), newTask.getName(), noticeusers, newTask.getAssignedto(), newTask.getMailto(),
                        ITaskService.OBJECT_TEXT_NAME, StaticDict.Action__object_type.TASK.getValue(), ITaskService.OBJECT_SOURCE_PATH, StaticDict.Action__type.ASSIGNED.getText());
            }
            Action action = actionHelper.create(StaticDict.Action__object_type.TASK.getValue(), newTask.getId(), StaticDict.Action__type.ASSIGNED.getValue(),
                    comment, newTask.getAssignedto(), null, true);
            if (changes.size() > 0) {
                actionHelper.logHistory(action.getId(), changes);
            }
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

    @Transactional(rollbackFor = Exception.class)
    public Task activate(Task et) {
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        Task old = new Task();
        CachedBeanCopier.copy(this.get(et.getId()), old);

        Task newTask = new Task();
        this.getActivateUpdateTask(et, newTask);
        newTask.setId(et.getId());
        newTask.setLeft(et.getLeft() != null ? et.getLeft() : 0.0d);

        //team
        List<Team> teams = teamHelper.list(new QueryWrapper<Team>().eq(FIELD_ROOT, newTask.getId()).eq(FIELD_TYPE, StaticDict.Team__type.TASK.getValue()));
        if (teams.size() > 0) {
            for (Team team : teams) {
                if (StringUtils.compare(team.getAccount(), newTask.getAssignedto()) == 0) {
                    team.setLeft(newTask.getLeft());
                    teamHelper.internalUpdate(team);
                    break;
                }
            }
            computeHours4Multiple(old, newTask, null, false);
        }

        String noticeusers = et.getNoticeusers();
        this.internalUpdate(newTask);

        if (old.getParent() > 0) {
            updateParentStatus(newTask, old.getParent(), true);
        }

        List<Task> childTask = this.list(new QueryWrapper<Task>().eq("parent", et.getId()));
        if (childTask.size() > 0) {
            Task task1 = new Task();
            this.getActivateUpdateTask(et, task1);
            this.update(task1, (Wrapper<Task>) task1.getUpdateWrapper(true).eq(FIELD_PARENT, old.getId()));
            this.computeWorkingHours(newTask);
        }
        //PmsEe操作任务，需判断状态，计算关联的计划的状态
        if (newTask.getPlan() != null && newTask.getPlan() > 0){
            //该任务有计划，根据任务状态更新计划状态
            this.updateRelatedPlanStatus(newTask,newTask.getPlan());
        }

        newTask.setStatus1(old.getStatus1());
        List<History> changes = ChangeUtil.diff(old, newTask);
        this.removeIgonreChanges(changes);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {
            actionHelper.sendTodo(newTask.getId(), newTask.getName(), noticeusers, newTask.getAssignedto(), newTask.getMailto(),
                    ITaskService.OBJECT_TEXT_NAME, StaticDict.Action__object_type.TASK.getValue(), ITaskService.OBJECT_SOURCE_PATH, StaticDict.Action__type.ACTIVATED.getText());
            Action action = actionHelper.create(StaticDict.Action__object_type.TASK.getValue(), newTask.getId(), StaticDict.Action__type.ACTIVATED.getValue(),
                    comment, newTask.getAssignedto(), null, true);
            if (changes.size() > 0) {
                actionHelper.logHistory(action.getId(), changes);
            }
        }
        return newTask;
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

    public void removeIgonreChanges(List<History> changes) {
        for (int i = 0; i < changes.size(); i++) {
            if (ignore.contains(changes.get(i).getField())) {
                changes.remove(i);
                i--;
            }
        }
    }


    @Transactional(rollbackFor = Exception.class)
    public Task cancel(Task et) {
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        Task old = new Task();
        CachedBeanCopier.copy(this.get(et.getId()), old);

        Task newTask = new Task();
        this.setCancelNewTask(old, newTask);
        newTask.setId(et.getId());
        String noticeusers = et.getNoticeusers();
        this.internalUpdate(newTask);

        if (old.getParent() > 0) {
            updateParentStatus(newTask, old.getParent(), true);
        }
        List<Task> childTask = this.list(new QueryWrapper<Task>().eq("parent", et.getId()));
        if (childTask.size() > 0) {
            Task childNewTask = new Task();
            this.setCancelNewTask(old, childNewTask);
            this.update(childNewTask, (Wrapper<Task>) childNewTask.getUpdateWrapper(true).eq(FIELD_PARENT, old.getId()));
        }
        //PmsEe操作任务，需判断状态，计算关联的计划的状态
        if (newTask.getPlan() != null && newTask.getPlan() > 0){
            //该任务有计划，根据任务状态更新计划状态
            this.updateRelatedPlanStatus(newTask,newTask.getPlan());
        }

        List<History> changes = ChangeUtil.diff(old, newTask);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {
            actionHelper.sendToread(newTask.getId(), newTask.getName(), noticeusers, newTask.getAssignedto(), newTask.getMailto(),
                    ITaskService.OBJECT_TEXT_NAME, StaticDict.Action__object_type.TASK.getValue(), ITaskService.OBJECT_SOURCE_PATH, StaticDict.Action__type.CANCELED.getText());
            Action action = actionHelper.create(StaticDict.Action__object_type.TASK.getValue(), newTask.getId(), StaticDict.Action__type.CANCELED.getValue(),
                    comment, newTask.getAssignedto(), null, true);
            if (changes.size() > 0) {
                actionHelper.logHistory(action.getId(), changes);
            }
        }
        return newTask;
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

    @Transactional(rollbackFor = Exception.class)
    public Task finish(Task et) {
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        Task old = new Task();
        CachedBeanCopier.copy(this.get(et.getId()), old);
        List<Team> teams = teamHelper.list(new QueryWrapper<Team>().eq(FIELD_ROOT, et.getId()).eq(FIELD_TYPE, StaticDict.Team__type.TASK.getValue()));
        Task newTask = new Task();
        newTask.setId(et.getId());
        newTask.setAssigneddate(ZTDateUtil.now());
        newTask.setLastediteddate(ZTDateUtil.now());
        newTask.setLasteditedby(AuthenticationUser.getAuthenticationUser().getUsername());
        newTask.setConsumed(et.getTotaltime() != null ? et.getTotaltime() : (et.getConsumed() + (et.getCurrentconsumed()== null ?0:et.getCurrentconsumed()) ));
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
        taskEstimate.setConsumed(consumed == 0 ? (et.getCurrentconsumed()==null?0:et.getCurrentconsumed()) : consumed);

        if (teams.size() > 0) {
            for (Team team : teams) {
                if (StringUtils.compare(team.getAccount(), taskEstimate.getAccount()) != 0) {
                    taskEstimate.setLeft(taskEstimate.getLeft() + team.getLeft());
                }
            }
        }
        if (null != et.getCurrentconsumed()){
            taskEstimateHelper.create(taskEstimate);
        }

        if (teams.size() > 0) {
            for (Team team : teams) {
                if (StringUtils.compare(team.getAccount(), old.getAssignedto()) == 0) {
                    team.setLeft(0.0);
                    team.setConsumed(newTask.getCurrentconsumed() + team.getConsumed());
                    teamHelper.internalUpdate(team);
                }
            }
            newTask.setFinisheddate(et.getFinisheddate());
            computeHours4Multiple(old, newTask, null, false);
        }

        String files = newTask.getFiles();

        String noticeusers = et.getNoticeusers();
        this.internalUpdate(newTask);
        fileHelper.updateObjectID(newTask.getId(), StaticDict.File__object_type.TASK.getValue(), files, "");

        if (old.getParent() > 0) {
            updateParentStatus(newTask, old.getParent(), true);
        }

        //PmsEe操作任务，需判断状态，计算关联的计划的状态
        if (newTask.getPlan() != null && newTask.getPlan() > 0){
            //该任务有计划，根据任务状态更新计划状态
            this.updateRelatedPlanStatus(newTask,newTask.getPlan());
        }
        List<History> changes = ChangeUtil.diff(old, newTask);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {
            actionHelper.sendMarkDone(newTask.getId(), newTask.getName(), old.getAssignedto(), ITaskService.OBJECT_TEXT_NAME, StaticDict.Action__object_type.TASK.getValue(), ITaskService.OBJECT_SOURCE_PATH, StaticDict.Action__type.FINISHED.getText());
            actionHelper.sendTodo(newTask.getId(), newTask.getName(), noticeusers, newTask.getAssignedto(), newTask.getMailto(),
                    ITaskService.OBJECT_TEXT_NAME, StaticDict.Action__object_type.TASK.getValue(), ITaskService.OBJECT_SOURCE_PATH, StaticDict.Action__type.FINISHED.getText());
            Action action = actionHelper.create(StaticDict.Action__object_type.TASK.getValue(), newTask.getId(), StaticDict.Action__type.FINISHED.getValue(),
                    comment, newTask.getAssignedto(), null, true);
            if (changes.size() > 0) {
                actionHelper.logHistory(action.getId(), changes);
            }
        }
        if (newTask.getStory() != null && newTask.getStory() != 0L) {
            storyHelper.setStage(newTask.getZtstory());
        }

        return newTask;
    }


    @Transactional(rollbackFor = Exception.class)
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
        this.internalUpdate(newTask);
        if (old.getParent() > 0) {
            updateParentStatus(newTask, old.getParent(), true);
        }
        //PmsEe操作任务，需判断状态，计算关联的计划的状态
        if (newTask.getPlan() != null && newTask.getPlan() > 0){
            //该任务有计划，根据任务状态更新计划状态
            this.updateRelatedPlanStatus(newTask,newTask.getPlan());
        }

        String[] ignores = {FIELD_ASSIGNED_DATE, FIELD_CLOSED_DATE};
        List<History> changes = ChangeUtil.diff(old, newTask, ignores, null, null);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {
            actionHelper.sendToread(newTask.getId(), newTask.getName(), noticeusers, newTask.getAssignedto(), newTask.getMailto(),
                    ITaskService.OBJECT_TEXT_NAME, StaticDict.Action__object_type.TASK.getValue(), ITaskService.OBJECT_SOURCE_PATH, StaticDict.Action__type.CLOSED.getText());
            Action action = actionHelper.create(StaticDict.Action__object_type.TASK.getValue(), newTask.getId(), StaticDict.Action__type.CLOSED.getValue(),
                    comment, newTask.getAssignedto(), null, true);
            if (changes.size() > 0) {
                actionHelper.logHistory(action.getId(), changes);
            }
        }
        if (newTask.getStory() != null && newTask.getStory() != 0L) {
            storyHelper.setStage(newTask.getZtstory());
        }
        return newTask;
    }


    @Transactional(rollbackFor = Exception.class)
    public Task recordEstimate(Task et) {
        String files = et.getFiles();
        Task old = new Task();
        CachedBeanCopier.copy(this.get(et.getId()), old);
        List<TaskEstimate> taskEstimates = new ArrayList<>();
        Timestamp earliestTime = null;
        if (et.getTaskestimate() != null && et.getTaskestimate().size() > 0) {
            for (TaskEstimate taskEstimate : et.getTaskestimate()) {
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
        List<TaskEstimate> list = taskEstimateHelper.list(new QueryWrapper<TaskEstimate>().eq("task", old.getId()).orderByDesc(FIELD_DATE, FIELD_ID));
        Timestamp lastDate = null;
        if (list.size() > 0) {
            lastDate = list.get(0).getDate();
        }
        Long actionid = 0L;
        boolean isNew = false;
        for (TaskEstimate taskEstimate : taskEstimates) {
            taskEstimateHelper.create(taskEstimate);
            consumed += taskEstimate.getConsumed();

            Action action = actionHelper.create(StaticDict.Action__object_type.TASK.getValue(), old.getId(), StaticDict.Action__type.RECORDESTIMATE.getValue(),
                    taskEstimate.getWork(), String.valueOf(taskEstimate.getConsumed()), null, true);
            actionid = action.getId();
            if (lastDate == null || lastDate.getTime() <= taskEstimate.getDate().getTime()) {
                isNew = true;
                teamLeft = taskEstimate.getLeft();
                lastDate = taskEstimate.getDate();
            }

        }

        // 团队任务
        List<Team> teams = teamHelper.list(new QueryWrapper<Team>().eq(FIELD_ROOT, old.getId()).eq(FIELD_TYPE, StaticDict.Team__type.TASK.getValue()));
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
            teamHelper.update(team, new QueryWrapper<Team>().eq(FIELD_ROOT, old.getId()).eq(FIELD_TYPE, StaticDict.Team__type.TASK.getValue()).eq(FIELD_ACCOUNT, AuthenticationUser.getAuthenticationUser().getUsername()));
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
            this.computeHours4Multiple(old, task, null, false);
        }
        this.internalUpdate(task);

        fileHelper.updateObjectID(task.getId(),StaticDict.File__object_type.TASK.getValue(),files,"");

        List<History> changes = ChangeUtil.diff(old, task);
        if (actionid != null && changes.size() > 0) {
            actionHelper.logHistory(actionid, changes);
        }
        if (old.getParent() > 0) {
            updateParentStatus(task, task.getParent(), true);
        }
        if (old.getStory() != null && old.getStory() != 0L) {
            storyHelper.setStage(old.getZtstory());
        }
        //PmsEe操作任务，需判断状态，计算关联的计划的状态
        if (task.getPlan() != null && task.getPlan() > 0){
            //该任务有计划，根据任务状态更新计划状态
            this.updateRelatedPlanStatus(task,task.getPlan());
        }
        return task;
    }


    @Transactional(rollbackFor = Exception.class)
    public Task deleteEstimate(Task et) {
        throw new RuntimeException("未实现");
    }

    @Transactional(rollbackFor = Exception.class)
    public Task editEstimate(Task et) {
        throw new RuntimeException("未实现");
    }


    @Transactional(rollbackFor = Exception.class)
    public Task confirmStoryChange(Task et) {
        et = this.get(et.getId());
        Task task = new Task();
        task.setId(et.getId());
        task.setStoryversion(storyService.get(et.getStory()).getVersion());
        this.internalUpdate(task);
        actionHelper.create(StaticDict.Action__object_type.TASK.getValue(), et.getId(), StaticDict.Action__type.CONFIRMED.getValue(), "", "", null, true);
        return et;
    }


    @Transactional(rollbackFor = Exception.class)
    public boolean batchCreate(List<Task> list) {
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
            if(isOps) {
                task.setStory(0L);
            }else if (task.getStory() != null && task.getStory() != 0L) {
                task.setStoryversion(storyHelper.get(task.getStory()).getVersion());
            }
            if (assignedTo != "" && assignedTo != null) {
                task.setAssigneddate(ZTDateUtil.now());
            }
            task.setTaskspecies(StaticDict.TaskSpecies.TEMP.getValue());//创建子任务时子任务的状态为临时任务
            super.create(task);

            childTasks += task.getId() + ",";
            actionHelper.create(StaticDict.Action__object_type.TASK.getValue(), task.getId(), StaticDict.Action__type.OPENED.getValue(), "", "", null, true);
            if (task.getStory() != null && task.getStory() != 0L) {
                storyHelper.setStage(task.getZtstory());
            }
        }

        long parent = list.get(0).getParent();
        Task old = new Task();
        CachedBeanCopier.copy(this.get(parent), old);

        if (parent > 0) {
            Task lastInsertTask = list.get(list.size() - 1);
            updateParentStatus(lastInsertTask, parent, true);
            computeBeginAndEnd(this.get(old.getId()));
//            if (old.getParent() != -1L) {
//                Task update = new Task();
//                // update.setParent(-1L);
//                update.setId(parent);
//                this.internalUpdate(update);
//            }
            Task newT = this.getById(parent);
            String regex = "^,*|,*$";
            childTasks = childTasks.replaceAll(regex, "");
            CachedBeanCopier.copy(this.get(old.getId()), newT);
            Action action = actionHelper.create(StaticDict.Action__object_type.TASK.getValue(), parent, StaticDict.Action__type.CREATECHILDREN.getValue(), "", childTasks, null, true);
            List<History> changes = ChangeUtil.diff(old, newT);
            if (changes.size() >= 0) {
                actionHelper.logHistory(action.getId(), changes);
            }
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public Task createByCycle(Task et) {
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date today = null;
        String curToday = sdf.format(now);
        try {
            today = sdf.parse(curToday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //开始时间
        Date begin = et.getConfigbegin();
        //结束时间
        Date end = et.getConfigend();
        //提前提醒天数
        Integer beforeDays = et.getConfigbeforedays();
        //设置提醒时间
        Calendar calendar = Calendar.getInstance();
        if (beforeDays != null && beforeDays >= 0) {
            if (begin != null) {
                calendar.setTime(begin);
            }
            calendar.add(Calendar.DATE, -beforeDays);
            begin = calendar.getTime();
        }
        //如果还未开始或者已经结束
        if (today.before(begin) || today.after(end)) {
            return et;
        }

        Task newTask = new Task();
        CachedBeanCopier.copy(et, newTask);

        //要更改的属性：parent idvalue cycle name
        newTask.setParent(et.getId());
        newTask.setIdvalue(et.getId());
        newTask.setCycle(0);
        newTask.setConsumed(0.0D);
        newTask.setTaskspecies(StaticDict.TaskSpecies.TEMP.getValue());//子任务设为临时任务

        newTask.setStatus(StaticDict.Task__status.WAIT.getValue());
        if (newTask.getAssignedto() != null) {
            newTask.setAssigneddate(new Timestamp(now.getTime()));
        }
        String assignedtopks = (newTask.getAssignedtopk() == null) ? newTask.getAssignedto() : newTask.getAssignedtopk();
        String[] accounts = assignedtopks.split(",");
        newTask.setAssignedto(accounts[0]);
        calendar.setTime(today);
        if (beforeDays != null) {
            calendar.add(Calendar.DATE, beforeDays);
        }
        Date finish = calendar.getTime();
        int count = 1;
        for (long time = begin.getTime(); time <= finish.getTime() ; time += 86400000) {
            Date today1 = new Date(time);
            List<Task> lastCycleList = this.list(new QueryWrapper<Task>().eq("idvalue", et.getId()).last(" order by eststarted "));
            Task lastCycleJson = null;
            if (lastCycleList.size() > 0) {
                lastCycleJson = lastCycleList.get(0);
            }

            Date date = null;

            if (StaticDict.CycleType.DAY.getValue().equals(et.getConfigtype())) {
                Integer day = et.getConfigday();
                if (day <= 0) {
                    continue;
                }
                if (lastCycleJson == null) {
                    calendar.setTime(today1);
                    calendar.add(Calendar.DATE, day - 1);
                    date = calendar.getTime();
                } else if (lastCycleJson.getConfigbegin() != null) {
                    calendar.setTime(lastCycleJson.getConfigbegin());
                    calendar.add(Calendar.DATE, day*count);
                    date = calendar.getTime();
                    count = count +1;
                }
            } else if (StaticDict.CycleType.WEEK.getValue().equals(et.getConfigtype())) {
                calendar.setTime(today1);
                int week = calendar.get(Calendar.DAY_OF_WEEK);
                String[] weeks = et.getConfigweek().split(",");
                for (String someWeek : weeks) {
                    if (someWeek.equals(String.valueOf(week))) {
                        if (lastCycleJson == null) {
                            date = today1;
                        } else if (lastCycleJson.getConfigbegin() != null && lastCycleJson.getConfigbegin().before(today1)) {
                            date = today1;
                        }
                    }
                }

            } else if (StaticDict.CycleType.MONTH.getValue().equals(et.getConfigtype())) {
                calendar.setTime(today1);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                String[] months = et.getConfigmonth().split(",");
                for (String someMonth: months) {
                    if (someMonth.equals(String.valueOf(day))) {
                        if (lastCycleJson == null) {
                            date = today1;
                        } else if (lastCycleJson.getConfigbegin() != null && lastCycleJson.getConfigbegin().before(today1)) {
                            date = today1;
                        }
                    }
                }

            }

            if (date == null) {
                continue;
            }
            Date configBegin = et.getConfigbegin();


            if (date.before(configBegin)) {
                continue;
            }

            if (date.before(today)) {
                continue;
            }
            if (date.after(finish)) {
                continue;
            }
            if (end != null && date.after(end)) {
                continue;
            }
            calendar.setTimeInMillis(date.getTime());
            String name = sdf.format(calendar.getTime());
            String title = et.getName() + "-" + name + "-" + accounts[0];
            newTask.setName(title);
            List<Task> taskList = this.list(new QueryWrapper<Task>().eq("parent", et.getId()).eq("CONFIG_BEGIN",new Timestamp(date.getTime())));
            if (taskList.size() > 0){
                continue;
            }
            newTask.setEststarted(new Timestamp(date.getTime()));
            newTask.setDeadline(new Timestamp(date.getTime()));
            newTask.setRealstarted(new Timestamp(date.getTime()));
            newTask.setConfigbegin(new Timestamp(date.getTime()));
            this.baseMapper.insert(newTask);
            actionHelper.create(StaticDict.Action__object_type.TASK.getValue(), newTask.getId(), StaticDict.Action__type.OPENED.getValue(),
                    "", "", newTask.getOpenedby(), true);
           if (accounts.length >1){
               for (int i = 1; i <= accounts.length - 1; i++) {
                   newTask.setId(null);
                   Task otherTask = new Task();
                   CachedBeanCopier.copy(newTask,otherTask);
                   otherTask.setAssignedto(accounts[i]);
                   otherTask.setName(et.getName() + "-" + name + "-" + accounts[i]);
                   this.create(otherTask);
                   actionHelper.create(StaticDict.Action__object_type.TASK.getValue(), otherTask.getId(), StaticDict.Action__type.OPENED.getValue(),
                           "", "", otherTask.getOpenedby(), true);
               }
           }
        }

        return et;
    }

    @Transactional
    public Task linkPlan(Task et) {
        //先找到这个任务对应的productplan
        List<ProductPlan> productPlanList = productPlanService.list(new QueryWrapper<ProductPlan>().eq("id", et.getPlan()));
        ProductPlan oldProductPlan = productPlanList.get(0);
        ProductPlan productPlan = new ProductPlan();
        productPlan.setProduct(oldProductPlan.getProduct());
        productPlan.setId(oldProductPlan.getId());
        String tasksId = getTasks(et);
        if ("[]".equals(tasksId)){
            productPlan.set("tasks",null);
        }else {
            productPlan.set("tasks",tasksId);

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


    //根据子任务状态计算父任务(或者关联的计划)状态
    public String getParentOrRelatedPlanStatusByChildTasks(List<JSONObject> childTasks){
        String status = "";
        if (childTasks.size() == 1) {
            status = childTasks.get(0).getString(FIELD_STATUS);
        } else {

            String strStatus = "";
            String strClose = "";
            for (JSONObject task : childTasks) {
                strStatus += task.getString(FIELD_STATUS) + MULTIPLE_CHOICE;
                strClose += task.getString(FIELD_CLOSED_REASON) + MULTIPLE_CHOICE;
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

    public String getActionTypeByStatus(String oldStatus,String status){
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

    //pmsee中，任务可以管理计划，操作任务时，需要更改任务关联的计划的状态
    public void updateRelatedPlanStatus(Task relatedTask,Long planId){
        if (planId == null){
            planId = relatedTask.getPlan();
        }
        if (planId == null || planId <= 0){
            return;
        }
        ProductPlan oldRelatePlan = productPlanService.getById(planId);//任务关联的计划
        if (oldRelatePlan == null){
            relatedTask.setPlan(0L);
            this.internalUpdate(relatedTask);
            return;
        }
        List<JSONObject> relatedTasks = taskService.select(String.format("select * from zt_task t1 where t1.deleted = '0' and t1.plan = %1$s",oldRelatePlan.getId()),null);
        //根据关联的任务的状态，计算计划的状态
        String status = this.getParentOrRelatedPlanStatusByChildTasks(relatedTasks);
        if (StringUtils.compare(status,oldRelatePlan.getStatus()) != 0){
            ProductPlan newProductPlan = new ProductPlan();
            CachedBeanCopier.copy(oldRelatePlan,newProductPlan);
            newProductPlan.setStatus(status);
            productPlanHelper.internalUpdate(newProductPlan);
            String actionType = this.getActionTypeByStatus(oldRelatePlan.getStatus() == null ? StaticDict.Task__status.WAIT.getValue() : oldRelatePlan.getStatus(),status);
            Action action = actionHelper.create(StaticDict.Action__object_type.PRODUCTPLAN.getValue(),oldRelatePlan.getId(),actionType,"","","",false);
            List<History> changes = ChangeUtil.diff(oldRelatePlan,newProductPlan);
            if (changes.size() > 0){
                actionHelper.logHistory(action.getId(),changes);
            }
        }



    }

    /**
     * 激活时或者填入工时的时候 如果预计剩余为0 任务状态换成进行中
     * @param et
     * @return
     */
    @Transactional
    public Task recordTimateZeroLeft(Task et) {
        et = this.get(et.getId());
        Task newTask = new Task();
        newTask.setId(et.getId());
        if ( et.getLeft() == 0){
            newTask.setStatus(StaticDict.Task__status.DOING.getValue());
            newTask.setFinishedby("");
            newTask.setFinisheddate(null);
            List<Action> actions = iActionService.list(new QueryWrapper<Action>().eq("objectType", StaticDict.Action__object_type.TASK.getValue()).eq("action", StaticDict.Action__type.RECORDESTIMATE.getValue()).eq("objectId", et.getId()).last(" order by id desc"));
            if (actions.size()>0){
                Action action = actions.get(0);
                List<History> historyList = iHistoryService.list(new QueryWrapper<History>().eq("action", action.getId()).last(" and field in ('assignedTo' ,'assignedDate')"));
                for (History history : historyList) {
                    if ("assignedTo".equals(history.getField())){
                        newTask.setAssignedto(history.getOld());
                    }
                    if ("assignedDate".equals(history.getField())){
                        newTask.setAssigneddate(Timestamp.valueOf(history.getOld()));
                    }
                }
            }
            super.internalUpdate(newTask);
        }
        if (et.getParent()>0){
            updateParentStatus(et,et.getParent(),true);
        }
        return et;
    }

    /**
     * 继续任务时如果预计剩余为0 操作记录里要将完成换成继续
     * @param et
     * @return
     */
    @Transactional
    public Task recordTimZeroLeftAfterContinue(Task et) {
        et = this.get(et.getId());
        Task newTask = new Task();
        newTask.setId(et.getId());
        if ( et.getLeft() == 0){
            newTask.setStatus(StaticDict.Task__status.DOING.getValue());
            newTask.setFinishedby("");
            newTask.setFinisheddate(null);
            List<Action> actions = iActionService.list(new QueryWrapper<Action>().eq("objectType", StaticDict.Action__object_type.TASK.getValue()).eq("action", StaticDict.Action__type.RECORDESTIMATE.getValue()).eq("objectId", et.getId()).last(" order by id desc"));
            if (actions.size()>0){
                Action action = actions.get(0);
                List<History> historyList = iHistoryService.list(new QueryWrapper<History>().eq("action", action.getId()).last(" and field in ('assignedTo' ,'assignedDate')"));
                for (History history : historyList) {
                    if ("assignedTo".equalsIgnoreCase(history.getField())){
                        newTask.setAssignedto(history.getOld());
                    }
                    if ("assignedDate".equalsIgnoreCase(history.getField())){
                        newTask.setAssigneddate(Timestamp.valueOf(history.getOld()));
                    }
                }
            }
            super.internalUpdate(newTask);
            List<Action> actionList = actionHelper.list(new QueryWrapper<Action>().eq("objectID", et.getId()).eq("actor", AuthenticationUser.getAuthenticationUser().getUsername()).eq("action",StaticDict.Action__type.FINISHED.getValue()).orderByDesc("date"));
            if (actionList.size()>0){
                Action action = actionList.get(0);
                Action old = new Action();
                old.setId(action.getId());
                old.setAction(StaticDict.Action__type.RESTARTED.getValue());
                actionHelper.updateById(old);
            }
        }
        if (et.getParent()>0){
            updateParentStatus(et,et.getParent(),true);
        }
        return et;
    }

    /**
     * 开始任务时如果预计剩余为0时将操作记录里完成换成开始
     * @param et
     * @return
     */
    @Transactional
    public Task recordTimateZeroLeftAfterStart(Task et) {
        et = this.get(et.getId());
        Task newTask = new Task();
        newTask.setId(et.getId());
        if ( et.getLeft() == 0){
            newTask.setStatus(StaticDict.Task__status.DOING.getValue());
            newTask.setFinishedby("");
            newTask.setFinisheddate(null);
            List<Action> actions = iActionService.list(new QueryWrapper<Action>().eq("objectType", StaticDict.Action__object_type.TASK.getValue()).eq("action", StaticDict.Action__type.RECORDESTIMATE.getValue()).eq("objectId", et.getId()).last(" order by id desc"));
            if (actions.size()>0){
                Action action = actions.get(0);
                List<History> historyList = iHistoryService.list(new QueryWrapper<History>().eq("action", action.getId()).last(" and field in ('assignedTo' ,'assignedDate')"));
                for (History history : historyList) {
                    if ("assignedTo".equals(history.getField())){
                        newTask.setAssignedto(history.getOld());
                    }
                    if ("assignedDate".equals(history.getField())){
                        newTask.setAssigneddate(Timestamp.valueOf(history.getOld()));
                    }
                }
            }
            super.internalUpdate(newTask);
            List<Action> actionList = actionHelper.list(new QueryWrapper<Action>().eq("objectID", et.getId()).eq("actor", AuthenticationUser.getAuthenticationUser().getUsername()).eq("action",StaticDict.Action__type.FINISHED.getValue()).orderByDesc("date"));
            if (actionList.size()>0){
                Action action = actionList.get(0);
                Action old = new Action();
                old.setId(action.getId());
                old.setAction(StaticDict.Action__type.STARTED.getValue());
                actionHelper.updateById(old);
            }
        }
        if (et.getParent()>0){
            updateParentStatus(et,et.getParent(),true);
        }
        return et;
    }

}
