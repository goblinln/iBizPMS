package cn.ibizlab.pms.core.util.ibizzentao.helper;

import cn.ibizlab.pms.core.ibiz.domain.TaskTeam;
import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.zentao.domain.*;
import cn.ibizlab.pms.core.zentao.mapper.TaskEstimateMapper;
import cn.ibizlab.pms.core.zentao.service.*;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author chenxiang
 */
@Component
@Slf4j
public class TaskEstimateHelper extends ZTBaseHelper<TaskEstimateMapper, TaskEstimate> {

    @Autowired
    ActionHelper actionHelper;

    @Autowired
    TaskHelper taskHelper;
    @Autowired
    TeamHelper teamHelper;

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
    StoryHelper storyHelper;

    @Override
    public boolean hasDeleted() {
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean create(TaskEstimate et) {
        if (!super.create(et)) {
            return false;
        }
        return true;
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean edit(TaskEstimate et) {
        TaskEstimate oldEstimate = taskEstimateService.getById(et.getId());
        Task task = taskService.getById(oldEstimate.getTask());

        et.setAccount(AuthenticationUser.getAuthenticationUser().getUsername());
        this.internalUpdate(et);
        double consumed = (task.getConsumed() != null ? task.getConsumed() : 0.0) + et.getConsumed() - oldEstimate.getConsumed();
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

        List<Team> teamLists = teamService.list(new QueryWrapper<Team>().eq(FIELD_ROOT,oldEstimate.getTask()).eq(FIELD_TYPE,StaticDict.Team__type.TASK.getValue()).orderByDesc("`order`"));
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
            teamHelper.update(newTeamInfo,(Wrapper<Team>) newTeamInfo.getUpdateWrapper(true).allEq(param));
            List<TaskTeam> teams = task.getTaskteam();
            taskHelper.computeHours4Multiple(task,data,teams,false);
        }
        taskHelper.internalUpdate(data);


        if (task.getParent() > 0) {  //编辑的任务是子任务
            taskHelper.updateParentStatus(task, task.getParent(),false);
        }

        //     * Set stage of a story.
        if (task.getStory() != 0){
            long storyID = task.getStory();
            Story story = storyService.getById(storyID);
            storyHelper.setStage(story);
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
        Action action1 = actionHelper.create(StaticDict.Team__type.TASK.getValue(),et.getTask(),StaticDict.Action__type.EDITESTIMATE.getValue(),et.getWork(),"",null,true);
        actionHelper.logHistory(action1.getId(),changes);

        return true;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long key) {
        TaskEstimate taskEstimate = this.get(key);
        Task task = taskHelper.get(taskEstimate.getTask());
        this.remove(new QueryWrapper<TaskEstimate>().eq(FIELD_ID,key));
        List<JSONObject> estimateLists = taskEstimateService.select(String.format("select * from zt_TASKESTIMATE where task = %1$s order by date desc,id desc limit 0,1",taskEstimate.getTask()),null);
        TaskEstimate lastEstimate = null;
        if (estimateLists.size() != 0){
            lastEstimate = JSONObject.toJavaObject(estimateLists.get(0),TaskEstimate.class);
        }
        double consumed = (task.getConsumed() != null ? task.getConsumed() : 0.0) - taskEstimate.getConsumed();
        double left = lastEstimate != null && lastEstimate.getLeft() != 0 ? lastEstimate.getLeft() : taskEstimate.getLeft();
        Task data = new Task();
        data.setConsumed(consumed);
        data.setLeft(left);
        data.setStatus((left == 0 && consumed != 0) ? StaticDict.Task__status.DONE.getValue() : task.getStatus());

        List<Team> teamLists = teamService.list(new QueryWrapper<Team>().eq(FIELD_ROOT,task.getId()).eq(FIELD_TYPE,StaticDict.Team__type.TASK.getValue()));
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

            teamHelper.update(newTeamInfo,(Wrapper<Team>) newTeamInfo.getUpdateWrapper(true).allEq(param));
            List<TaskTeam> teams = task.getTaskteam();

            taskHelper.computeHours4Multiple(task,data,teams,false);
        }
        data.setId(taskEstimate.getTask());
        taskHelper.internalUpdate(data);

        if (task.getParent() > 0) {
            taskHelper.updateParentStatus(task, task.getParent(), false);
        }

        if (task.getStory() != 0) {
            Story et = storyHelper.getById(task.getStory());
            storyHelper.setStage(et);
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
            Action action = actionHelper.create(StaticDict.Action__object_type.TASK.getValue(), task.getId(), StaticDict.Action__type.DELETEESTIMATE.getValue(), "", "", null, true);
            if (changes.size() > 0){
                actionHelper.logHistory(action.getId(),changes);
            }
        }
        return true;
    }
}
