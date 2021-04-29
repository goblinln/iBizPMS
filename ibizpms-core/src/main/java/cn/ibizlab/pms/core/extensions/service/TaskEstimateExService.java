package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibiz.domain.TaskTeam;
import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.zentao.domain.*;
import cn.ibizlab.pms.core.zentao.service.*;
import cn.ibizlab.pms.core.zentao.service.impl.TaskEstimateServiceImpl;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

import static cn.ibizlab.pms.core.util.ibizzentao.helper.ZTBaseHelper.*;

/**
 * 实体[任务预计] 自定义服务对象
 */
@Slf4j
@Primary
@Service("TaskEstimateExService")
public class TaskEstimateExService extends TaskEstimateServiceImpl {

    @Autowired
    ITaskEstimateService taskEstimateService;

    @Autowired
    ITeamService teamService;

    @Autowired
    ITaskService taskService;

    @Autowired
    ICaseService caseService;
    @Autowired
    IStoryService storyService;
    @Autowired
    ITeamService iTeamService;
    @Autowired
    ITaskService iTaskService;
    @Autowired
    IActionService iActionService;

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [PMEvaluation:项目经理评估] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public TaskEstimate pMEvaluation(TaskEstimate et) {
        return super.pMEvaluation(et);
    }

    @Override
    public boolean create(TaskEstimate et) {
        if (!super.create(et)) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(TaskEstimate et) {
        TaskEstimate oldEstimate = taskEstimateService.getById(et.getId());
        Task task = taskService.getById(oldEstimate.getTask());

        et.setAccount(AuthenticationUser.getAuthenticationUser().getUsername());
        super.update(et);
        double consumed = task.getConsumed()+et.getConsumed() - oldEstimate.getConsumed();
        List<JSONObject> lastEstimate = taskEstimateService.select(String.format("select * from zt_TaskEstimate where task = %1$s ORDER BY id desc",task.getId()),null);
        double left = (lastEstimate.size() != 0 && et.getId() == lastEstimate.get(0).getLongValue(FIELD_ID) ) ? et.getLeft() : task.getLeft();
        LocalDateTime now = LocalDateTime.now();
        Task data = new Task();
        data.setConsumed(consumed);
        data.setLeft(left);
        data.setStatus((left == 0) ? StaticDict.Task__status.DONE.getValue() : task.getStatus());
        data.setLasteditedby(AuthenticationUser.getAuthenticationUser().getUsername());
        data.setLastediteddate(Timestamp.valueOf(now));
        data.setId(task.getId());
        if (left == 0){
            data.setFinishedby(AuthenticationUser.getAuthenticationUser().getUsername());
            data.setFinisheddate(Timestamp.valueOf(now));
            data.setAssignedto(task.getOpenedby());
        }

        List<Team> teamLists = teamService.list(new QueryWrapper<Team>().eq(FIELD_ROOT,oldEstimate.getTask()).eq(FIELD_TYPE, StaticDict.Team__type.TASK.getValue()).orderByDesc("`order`"));
        if (teamLists.size() != 0){
            double oldConsumed = 0;
            for (Team team : teamLists) {
                if (team.getAccount().equals(oldEstimate.getAccount())) {
                    oldConsumed = team.getConsumed();
                }
            }
            Team newTeamInfo = new Team();
            newTeamInfo.setConsumed(oldConsumed+et.getConsumed() - oldEstimate.getConsumed());
            newTeamInfo.setLeft(left);
            Map<String,Object> param = new HashMap<>();
            param.put(FIELD_ROOT,oldEstimate.getTask());
            param.put(FIELD_TYPE, StaticDict.Team__type.TASK.getValue());
            param.put("account",oldEstimate.getAccount());
            iTeamService.update(newTeamInfo,(Wrapper<Team>) newTeamInfo.getUpdateWrapper(true).allEq(param));
            List<TaskTeam> teams = task.getTaskteams();
            // TODO 后续补充
            // iTaskService.computeHours4Multiple(task,data,teams,false);
        }
        iTaskService.sysUpdate(data);


        if (task.getParent() > 0) {  //编辑的任务是子任务
            // TODO 后续补充
            // iTaskService.updateParentStatus(task, task.getParent(),false);
        }

        //     * Set stage of a story.
        if (task.getStory() != 0){
            long storyID = task.getStory();
            Story story = storyService.getById(storyID);
            storyService.setStage(story);
        }

        Task oldTask = new Task();
        oldTask.setConsumed(task.getConsumed());
        oldTask.setLeft(task.getLeft());
        oldTask.setStatus(task.getStatus());

        Task newTask = new Task();
        newTask.setConsumed(data.getConsumed());
        newTask.setLeft(data.getLeft());
        newTask.setStatus(data.getStatus());
        List<History> changes = ChangeUtil.diff(oldTask,newTask);


        ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.TASK.getValue(),changes,StaticDict.Action__type.EDITESTIMATE.getValue(),
                et.getWork(),"", null,iActionService);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean remove(Long key) {
        TaskEstimate taskEstimate = this.get(key);
        Task task = iTaskService.get(taskEstimate.getTask());
        this.remove(new QueryWrapper<TaskEstimate>().eq(FIELD_ID,key));
        List<JSONObject> estimateLists = taskEstimateService.select(String.format("select * from zt_TASKESTIMATE where task = %1$s order by date desc,id desc limit 0,1",taskEstimate.getTask()),null);
        TaskEstimate lastEstimate = null;
        if (estimateLists.size() != 0){
            lastEstimate = JSONObject.toJavaObject(estimateLists.get(0), TaskEstimate.class);
        }
        double consumed = task.getConsumed() - taskEstimate.getConsumed();
        double left = lastEstimate != null && lastEstimate.getLeft() != 0 ? lastEstimate.getLeft() : taskEstimate.getLeft();
        Task data = new Task();
        data.setConsumed(consumed);
        data.setLeft(left);
        data.setStatus((left == 0 && consumed != 0) ? StaticDict.Task__status.DONE.getValue() : task.getStatus());

        List<Team> teamLists = teamService.list(new QueryWrapper<Team>().eq(FIELD_ROOT,task.getId()).eq(FIELD_TYPE, StaticDict.Team__type.TASK.getValue()));
        if (teamLists.size() != 0){
            double oldConsumed = 0;
            for (Team team : teamLists) {
                if (team.getAccount().equals(taskEstimate.getAccount())){
                    oldConsumed = team.getConsumed();
                }
            }
            Team newTeamInfo = new Team();
            newTeamInfo.setConsumed(oldConsumed - taskEstimate.getConsumed());
            newTeamInfo.setLeft(left);
            Map<String,Object> param = new HashMap<>();
            param.put(FIELD_ROOT,taskEstimate.getTask());
            param.put(FIELD_TYPE, StaticDict.Team__type.TASK.getValue());
            param.put(FIELD_ACCOUNT,taskEstimate.getAccount());

            iTeamService.update(newTeamInfo,(Wrapper<Team>) newTeamInfo.getUpdateWrapper(true).allEq(param));
            List<TaskTeam> teams = task.getTaskteams();
            // TODO 后续补充
            //iTaskService.computeHours4Multiple(task,data,teams,false);
        }
        data.setId(taskEstimate.getTask());
        iTaskService.update(data);

        if (task.getParent() > 0) {
            // TODO 后续补充
            //iTaskService.updateParentStatus(task, task.getParent(), false);
        }

        if (task.getStory() != 0) {
            Story et = storyService.getById(task.getStory());
            storyService.setStage(et);
        }
        Task oldTask = new Task();
        oldTask.setConsumed(task.getConsumed());
        oldTask.setLeft(left);
        oldTask.setStatus(task.getStatus());

        Task newTask = new Task();
        newTask.setConsumed(data.getConsumed());
        newTask.setLeft(data.getLeft());
        newTask.setStatus(data.getStatus());

        List<History> changes = ChangeUtil.diff(oldTask,newTask);
        if (changes.size() > 0) {
            ActionHelper.createHis(task.getId(),StaticDict.Action__object_type.TASK.getValue(),changes,StaticDict.Action__type.EDITESTIMATE.getValue(),
                    "","", null,iActionService);
        }
        return true;
    }
}

