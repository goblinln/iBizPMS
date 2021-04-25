package cn.ibizlab.pms.core.util.ibizzentao.helper;

import cn.ibizlab.pms.core.ibiz.domain.ProjectTeam;
import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.util.ibizzentao.common.ZTDateUtil;
import cn.ibizlab.pms.core.zentao.domain.*;
import cn.ibizlab.pms.core.zentao.mapper.ProjectMapper;
import cn.ibizlab.pms.core.zentao.service.IProjectService;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenxiang
 */
@Component
@Slf4j
public class ProjectHelper extends ZTBaseHelper<ProjectMapper, Project> {

    @Autowired
    ActionHelper actionHelper;

    @Autowired
    FileHelper fileHelper;

    @Autowired
    DocLibHelper docLibHelper;

    @Autowired
    ProjectProductHelper projectProductHelper;

    @Autowired
    ProjectStoryHelper projectStoryHelper;

    @Autowired
    StoryHelper storyHelper;

    @Autowired
    TaskHelper taskHelper;

    @Autowired
    TeamHelper teamHelper;

    @Autowired
    IProjectService projectService;

    @Autowired
    private UserHelper userHelper;

    String[] diffAttrs = {"desc"};

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean create(Project et) {
        String sql = "select * from zt_project where  deleted = '0' and  (`name` = #{et.name} or `code` = #{et.code})";
        Map<String,Object> param = new HashMap<>();
        param.put(FIELD_NAME, et.getName());
        param.put(FIELD_CODE, et.getCode());
        List<JSONObject> nameList = projectService.select(sql,param);
        JSONObject jsonObject = userHelper.getSettings();
        String[] srfmstatus = jsonObject.getString("srfmstatus").split("_");
        if(!nameList.isEmpty() && nameList.size() > 0) {
            if("project".equals(srfmstatus[1])) {
                throw new RuntimeException(String.format("[项目名称：%1$s]或[项目代号：%2$s]已经存在。如果您确定该记录已删除，请联系管理员恢复。", et.getName(), et.getCode()));
            }else if("iteration".equals(srfmstatus[1])) {
                throw new RuntimeException(String.format("[迭代名称：%1$s]或[迭代代号：%2$s]已经存在。如果您确定该记录已删除，请联系管理员恢复。", et.getName(), et.getCode()));
            }else {
                throw new RuntimeException(String.format("[冲刺名称：%1$s]或[冲刺代号：%2$s]已经存在。如果您确定该记录已删除，请联系管理员恢复。", et.getName(), et.getCode()));
            }

        }
        JSONArray projectproducts = JSONArray.parseArray(et.getSrfarray());
        fileHelper.processImgURL(et, null, null);
        et.setCloseddate(new Timestamp(-28800000L));
        et.setCanceleddate(new Timestamp(-28800000L));
        et.setOpeneddate(ZTDateUtil.now());
        if (!this.retBool(this.baseMapper.insert(et))) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);

        //更新order
        et.setOrder(et.getId().intValue() * 5);
        internalUpdate(et);

        //DocLib
        DocLib docLib = new DocLib();
        docLib.setType(StaticDict.Action__object_type.PROJECT.getValue());
        docLib.setProject(et.getId());
        if("project".equals(srfmstatus[1])) {
            docLib.setName("项目主库");
        }else if("iteration".equals(srfmstatus[1])) {
            docLib.setName("迭代主库");
        }else {
            docLib.setName("冲刺主库");
        }

        docLib.setOrgid(AuthenticationUser.getAuthenticationUser().getOrgid());
        docLib.setMdeptid(AuthenticationUser.getAuthenticationUser().getMdeptid());
        docLib.setMain(StaticDict.YesNo.ITEM_1.getValue());
        docLib.setAcl(StaticDict.Doclib__acl.DEFAULT.getValue());
        docLibHelper.create(docLib);

        //Team
        Team team = new Team();
        team.setType(StaticDict.Action__object_type.PROJECT.getValue());
        team.setRoot(et.getId());
        team.setAccount(AuthenticationUser.getAuthenticationUser().getUsername());
        team.setJoin(ZTDateUtil.now());
        team.setRole("");
        team.setDays(et.getDays());
        team.setHours(7.0);
        teamHelper.create(team);

        //关联产品
        projectProductHelper.remove(new QueryWrapper<ProjectProduct>().eq(StaticDict.Action__object_type.PROJECT.getValue(), et.getId()));
        if(projectproducts != null) {
            for (int i = 0; i < projectproducts.size(); i++) {
                JSONObject json = projectproducts.getJSONObject(i);
                ProjectProduct projectProduct = new ProjectProduct();
                projectProduct.setProject(et.getId());
                if (json.containsKey(FIELD_PRODUCTS)) {
                    projectProduct.setProduct(json.getLongValue(FIELD_PRODUCTS));
                } else {
                    continue;
                }
                projectProduct.setPlan(json.getLongValue(FIELD_PLANS));
                projectProduct.setBranch(json.getLongValue(FIELD_BRANCHS));
                projectProductHelper.create(projectProduct);
            }
        }

        actionHelper.create(StaticDict.Action__object_type.PROJECT.getValue(), et.getId(), StaticDict.Action__type.OPENED.getValue(), "",
                StringUtils.isNotBlank(et.getProducts()) ? et.getProducts() : "", null, true);


        return true;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean edit(Project et) {
        String sql = "select * from zt_project where (`name` = #{et.name} or `code` = #{et.code}) and `id` <> #{et.id}";
        Map<String,Object> param = new HashMap<>();
        param.put(FIELD_NAME, et.getName());
        param.put(FIELD_CODE, et.getCode());
        param.put(FIELD_ID, et.getId());
        List<JSONObject> nameList = projectService.select(sql,param);
        if(!nameList.isEmpty() && nameList.size() > 0) {
            throw new RuntimeException(String.format("[项目名称：%1$s]或[项目代号：%2$s]已经存在。如果您确定该记录已删除，请联系管理员恢复。", et.getName(), et.getCode()));
        }
        String comment = et.getComment() == null ? "" : et.getComment();
        JSONArray projectproducts = JSONArray.parseArray(et.getSrfarray());
        Project old = new Project();
        CachedBeanCopier.copy(get(et.getId()), old);
        fileHelper.processImgURL(et, null, null);

        fileHelper.processImgURL(et, null, null);
        if (!this.internalUpdate(et)) {
            return false;
        }

        //关联产品
        projectProductHelper.remove(new QueryWrapper<ProjectProduct>().eq(StaticDict.Action__object_type.PROJECT.getValue(), et.getId()));
        if(projectproducts != null) {
            for (int i = 0; i < projectproducts.size(); i++) {
                JSONObject json = projectproducts.getJSONObject(i);
                ProjectProduct projectProduct = new ProjectProduct();
                projectProduct.setProject(et.getId());
                if (json.containsKey(FIELD_PRODUCTS) && json.get(FIELD_PRODUCTS) != null) {
                    projectProduct.setProduct(json.getLongValue(FIELD_PRODUCTS));
                } else {
                    continue;
                }
                projectProduct.setPlan(json.getLongValue(FIELD_PLANS));
                projectProduct.setBranch(json.getLongValue(FIELD_BRANCHS));
                projectProductHelper.create(projectProduct);
            }
        }

        //Team 处理

        List<History> changes = ChangeUtil.diff(old, et,null,null,diffAttrs);
        Action action = actionHelper.create(StaticDict.Action__object_type.PROJECT.getValue(), et.getId(), StaticDict.Action__type.EDITED.getValue(), comment, "", null, true);
        if (changes.size() > 0) {
            actionHelper.logHistory(action.getId(), changes);
        }

        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long key) {

        boolean result = removeById(key);

        //删除doclib
        docLibHelper.remove(new QueryWrapper<DocLib>().eq(StaticDict.Action__object_type.PROJECT.getValue(), key));

        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public Project start(Project et) {
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        Project old = new Project();
        CachedBeanCopier.copy(get(et.getId()), old);
        et.setStatus(StaticDict.Project__status.DOING.getValue());
        this.internalUpdate(et);
        List<History> changes = ChangeUtil.diff(old, et);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {
            Action action = actionHelper.create(StaticDict.Action__object_type.PROJECT.getValue(), et.getId(), StaticDict.Action__type.STARTED.getValue(),
                    comment, "", null, true);
            if (changes.size() > 0) {
                actionHelper.logHistory(action.getId(), changes);
            }
        }
        return et;
    }

    @Transactional(rollbackFor = Exception.class)
    public Project activate(Project et) {
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        Project old = new Project();
        CachedBeanCopier.copy(get(et.getId()), old);
        et.setStatus(StaticDict.Project__status.DOING.getValue());
        this.internalUpdate(et);
        /* Readjust task. */

        List<History> changes = ChangeUtil.diff(old, et);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {
            Action action = actionHelper.create(StaticDict.Action__object_type.PROJECT.getValue(), et.getId(), StaticDict.Action__type.ACTIVATED.getValue(),
                    comment, "", null, true);
            if (changes.size() > 0) {
                actionHelper.logHistory(action.getId(), changes);
            }
        }
        return et;
    }

    @Transactional(rollbackFor = Exception.class)
    public Project close(Project et) {
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        Project old = new Project();
        CachedBeanCopier.copy(get(et.getId()), old);
        et.setStatus(StaticDict.Project__status.CLOSED.getValue());
        et.setClosedby(AuthenticationUser.getAuthenticationUser().getUsername());
        et.setCloseddate(ZTDateUtil.now());
        this.internalUpdate(et);
        List<History> changes = ChangeUtil.diff(old, et);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {
            Action action = actionHelper.create(StaticDict.Action__object_type.PROJECT.getValue(), et.getId(), StaticDict.Action__type.CLOSED.getValue(),
                    comment, "", null, true);
            if (changes.size() > 0) {
                actionHelper.logHistory(action.getId(), changes);
            }
        }
        return et;
    }

    @Transactional(rollbackFor = Exception.class)
    public Project putoff(Project et) {
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        Project old = new Project();
        CachedBeanCopier.copy(et.getId(), et);
        this.internalUpdate(et);
        List<History> changes = ChangeUtil.diff(old, et, null, new String[]{"end", "days"}, null);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {
            Action action = actionHelper.create(StaticDict.Action__object_type.PROJECT.getValue(), et.getId(), StaticDict.Action__type.DELAYED.getValue(),
                    comment, "", null, true);
            if (changes.size() > 0) {
                actionHelper.logHistory(action.getId(), changes);
            }
        }
        return et;
    }

    @Transactional(rollbackFor = Exception.class)
    public Project suspend(Project et) {
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        Project old = new Project();
        CachedBeanCopier.copy(get(et.getId()), old);
        et.setStatus(StaticDict.Project__status.SUSPENDED.getValue());
        this.internalUpdate(et);
        List<History> changes = ChangeUtil.diff(old, et);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {
            Action action = actionHelper.create(StaticDict.Action__object_type.PROJECT.getValue(), et.getId(), StaticDict.Action__type.SUSPENDED.getValue(),
                    comment, "", null, true);
            if (changes.size() > 0) {
                actionHelper.logHistory(action.getId(), changes);
            }
        }
        return et;
    }

    @Transactional(rollbackFor = Exception.class)
    public Project linkStory(Project et) {
        if (et.getId() == null || et.get(FIELD_STORIES) == null) {
            return et;
        }

        int order = -1;
        ProjectStory maxProjectStory = projectStoryHelper.getOne(new QueryWrapper<ProjectStory>().eq(StaticDict.Action__object_type.PROJECT.getValue(), et.getId()).orderByDesc("`order`").last("limit 0,1"));
        if (maxProjectStory != null) {
            order = maxProjectStory.getOrder();
        }

        for (String storyId :  et.get(FIELD_STORIES).toString().split(MULTIPLE_CHOICE)) {
            Story story = storyHelper.get(Long.parseLong(storyId));
            ProjectStory exists = projectStoryHelper.getOne(new QueryWrapper<ProjectStory>().eq(StaticDict.Action__object_type.PROJECT.getValue(), et.getId()).eq(StaticDict.Action__object_type.STORY.getValue(), story.getId()));
            if (exists != null) {
                continue;
            }
            ProjectStory projectStory = new ProjectStory();
            projectStory.setProject(et.getId());
            projectStory.setStory(story.getId());
            projectStory.setProduct(story.getProduct());
            projectStory.setVersion(story.getVersion());
            projectStory.setOrder(++order);
            projectStoryHelper.create(projectStory);
            storyHelper.setStage(story);
            actionHelper.create(StaticDict.Action__object_type.STORY.getValue(), story.getId(), StaticDict.Action__type.LINKED2PROJECT.getValue(),
                    "", String.valueOf(et.getId()), null, true);
        }


        return et;
    }

    @Transactional(rollbackFor = Exception.class)
    public Project unlinkStory(Project et) {
        if (et.getId() == null || et.get(StaticDict.Action__object_type.STORY.getValue()) == null) {
            throw new RuntimeException("解除需求错误");
        }

        projectStoryHelper.remove(new QueryWrapper<ProjectStory>().eq(StaticDict.Action__object_type.PROJECT.getValue(), et.getId()).eq(StaticDict.Action__object_type.STORY.getValue(), et.get(StaticDict.Action__object_type.STORY.getValue())));

        //order 处理

        actionHelper.create(StaticDict.Action__object_type.STORY.getValue(), Long.parseLong(et.get(StaticDict.Action__object_type.STORY.getValue()).toString()), StaticDict.Action__type.UNLINKEDFROMPROJECT.getValue(),
                "", String.valueOf(et.getId()), null, true);

        //需求的task处理
        List<Task> tasks = taskHelper.list(new QueryWrapper<Task>().eq(StaticDict.Action__object_type.STORY.getValue(), et.get(StaticDict.Action__object_type.STORY.getValue())).eq(StaticDict.Action__object_type.PROJECT.getValue(), et.getId()).in(FIELD_STATUS, StaticDict.Task__status.WAIT.getValue(), StaticDict.Task__status.DOING.getValue()));
        for (Task task : tasks) {
            taskHelper.cancel(task);
        }


        return et;
    }

    @Transactional(rollbackFor = Exception.class)
    public Project batchUnlinkStory(Project et) {
        throw new RuntimeException("未实现");
    }

    @Transactional(rollbackFor = Exception.class)
    public Project manageMembers(Project et) {
        List<ProjectTeam> list = et.getProjectteam();
        teamHelper.remove(new QueryWrapper<Team>().eq(FIELD_TYPE, StaticDict.Action__object_type.PROJECT.getValue()).eq(FIELD_ROOT, et.getId()));
        int i = 1;
        List<String> accounts = new ArrayList<>();
        for(ProjectTeam projectTeam : list) {
            if(accounts.size() > 0 && accounts.contains(projectTeam.getAccount())) {
                continue;
            }
            projectTeam.setType(StaticDict.Action__object_type.PROJECT.getValue());
            Team team = new Team();
            CachedBeanCopier.copy(projectTeam, team);
            team.setId(null);
            team.setOrder(i);
            team.setJoin(team.getJoin() != null ? team.getJoin() : ZTDateUtil.now());
            accounts.add(team.getAccount());
            teamHelper.create(team);
            i ++;
        }
        return et;
    }

    @Transactional(rollbackFor = Exception.class)
    public Project unlinkMember(Project et) {
        throw new RuntimeException("未实现");
    }

    @Transactional(rollbackFor = Exception.class)
    public Project updateOrder(Project et) {
        throw new RuntimeException("未实现");
    }

    @Transactional(rollbackFor = Exception.class)
    public Project importPlanStories(Project et) {
        List<Story> planStories = storyHelper.list(new QueryWrapper<Story>().eq(FIELD_PLAN, et.getPlans()));
        String stories = "" ;
        for (Story story : planStories) {
            if(stories.length()>0) {
                stories += MULTIPLE_CHOICE;
            }
            stories += story.getId() ;
        }
        if (StringUtils.isNotBlank(stories)) {
            et.set(FIELD_STORIES, stories);
            linkStory(et);
        }
        return et;
    }


    @Transactional(rollbackFor = Exception.class)
    public Project pmsEeProjectAllTaskCount(Project et){
        Map<String,Object> map = et.getExtensionparams();
        String srfCurProject = map.get("n_project_in").toString().replaceAll(";",",");
        String srfloginname = AuthenticationUser.getAuthenticationUser().getUsername();
        List<Task> tempTasks = taskHelper.list(new QueryWrapper<Task>().last(String.format("and project in (%1$s) and taskspecies = 'temp' and (assignedTo = '%2$s' or openedBy = '%3$s'  or FIND_IN_SET('%4$s', finishedList) or closedBy = '%5$s' or finishedBy = '%6$s' or canceledBy = '%7$s' )",srfCurProject,srfloginname,srfloginname,srfloginname,srfloginname,srfloginname,srfloginname)));
        et.setTemptaskcnt(tempTasks.size());
        List<Task> cycleTasks = taskHelper.list(new QueryWrapper<Task>().last(String.format("and project in (%1$s) and taskspecies = 'cycle' and (assignedTo = '%2$s' or openedBy = '%3$s'  or FIND_IN_SET('%4$s', finishedList) or closedBy = '%5$s' or finishedBy = '%6$s' or canceledBy = '%7$s'  )",srfCurProject,srfloginname,srfloginname,srfloginname,srfloginname,srfloginname,srfloginname)));
        et.setCycletaskcnt(cycleTasks.size());
        List<Task> planTasks = taskHelper.list(new QueryWrapper<Task>().last(String.format("and project in (%1$s) and taskspecies = 'plan' and (assignedTo = '%2$s' or openedBy = '%3$s'  or FIND_IN_SET('%4$s', finishedList) or closedBy = '%5$s' or finishedBy = '%6$s' or canceledBy = '%7$s'  )",srfCurProject,srfloginname,srfloginname,srfloginname,srfloginname,srfloginname,srfloginname)));
        et.setPlantaskcnt(planTasks.size());
        List<Task> allTasks = taskHelper.list(new QueryWrapper<Task>().last(String.format("and project in (%1$s)  and (assignedTo = '%2$s' or openedBy = '%3$s'  or FIND_IN_SET('%4$s', finishedList) or closedBy = '%5$s' or finishedBy = '%6$s' or canceledBy = '%7$s'  )",srfCurProject,srfloginname,srfloginname,srfloginname,srfloginname,srfloginname,srfloginname)));
        et.setAlltaskcnt(allTasks.size());
        return et;
    }


    @Transactional(rollbackFor = Exception.class)
    public Project pmsEeProjectTodoTaskCount(Project et){
        Map<String,Object> map = et.getExtensionparams();
        String srfCurProject = map.get("n_project_in").toString().replaceAll(";",",");

        String srfloginname = AuthenticationUser.getAuthenticationUser().getUsername();
        List<Task> tempTasks = taskHelper.list(new QueryWrapper<Task>().last(String.format("and project in (%1$s) and taskspecies = 'temp' and (assignedTo = '%2$s' )",srfCurProject,srfloginname)));
        et.setTemptaskcnt(tempTasks.size());
        List<Task> cycleTasks = taskHelper.list(new QueryWrapper<Task>().last(String.format("and project in (%1$s) and taskspecies = 'cycle' and (assignedTo = '%2$s' )",srfCurProject,srfloginname)));
        et.setCycletaskcnt(cycleTasks.size());
        List<Task> planTasks = taskHelper.list(new QueryWrapper<Task>().last(String.format("and project in (%1$s) and taskspecies = 'plan' and (assignedTo = '%2$s' )",srfCurProject,srfloginname)));
        et.setPlantaskcnt(planTasks.size());
        List<Task> allTasks = taskHelper.list(new QueryWrapper<Task>().last(String.format("and project in (%1$s)  and (assignedTo = '%2$s' )",srfCurProject,srfloginname)));
        et.setAlltaskcnt(allTasks.size());
        return et;
    }

}
