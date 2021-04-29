package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibiz.domain.ProjectTeam;
import cn.ibizlab.pms.core.ibizpro.domain.IbzproConfig;
import cn.ibizlab.pms.core.ibizpro.service.IIbzproConfigService;
import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.util.ibizzentao.common.ZTDateUtil;
import cn.ibizlab.pms.core.zentao.domain.*;
import cn.ibizlab.pms.core.zentao.service.*;
import cn.ibizlab.pms.core.zentao.service.impl.ProjectServiceImpl;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;

import java.sql.Timestamp;
import java.util.*;

/**
 * 实体[项目] 自定义服务对象
 */
@Slf4j
@Primary
@Service("ProjectExService")
public class ProjectExService extends ProjectServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    @Autowired
    IActionService iActionService;

    @Autowired
    IFileService iFileService;

    @Autowired
    IDocLibService iDocLibService;

    @Autowired
    IIbzproConfigService iIbzproConfigService;

    @Autowired
    IProjectProductService iProjectProductService;

    @Autowired
    IProjectStoryService iProjectStoryService;

    @Autowired
    ITaskService iTaskService;

    @Autowired
    ITeamService iTeamService;

    @Autowired
    IStoryService iStoryService;

    String[] diffAttrs = {"desc"};

    @Override
    public boolean create(Project et) {
        String sql = "select * from zt_project where  deleted = '0' and  (`name` = #{et.name} or `code` = #{et.code})";
        Map<String,Object> param = new HashMap<>();
        param.put("name", et.getName());
        param.put("code", et.getCode());
        List<JSONObject> nameList = projectService.select(sql,param);
        JSONObject jsonObject = getSettings();
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
        et.setCloseddate(new Timestamp(-28800000L));
        et.setCanceleddate(new Timestamp(-28800000L));
        et.setOpeneddate(ZTDateUtil.now());

        if(!super.create(et)) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);

        //更新order
        et.setOrder(et.getId().intValue() * 5);
        super.update(et);

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
        iDocLibService.create(docLib);
        //Team
        Team team = new Team();
        team.setType(StaticDict.Action__object_type.PROJECT.getValue());
        team.setRoot(et.getId());
        team.setAccount(AuthenticationUser.getAuthenticationUser().getUsername());
        team.setJoin(ZTDateUtil.now());
        team.setRole("");
        team.setDays(et.getDays());
        team.setHours(7.0);
        iTeamService.create(team);
        //关联产品
        iProjectProductService.remove(new QueryWrapper<ProjectProduct>().eq(StaticDict.Action__object_type.PROJECT.getValue(), et.getId()));
        if(projectproducts != null) {
            for (int i = 0; i < projectproducts.size(); i++) {
                JSONObject json = projectproducts.getJSONObject(i);
                ProjectProduct projectProduct = new ProjectProduct();
                projectProduct.setProject(et.getId());
                if (json.containsKey("products")) {
                    projectProduct.setProduct(json.getLongValue("products"));
                } else {
                    continue;
                }
                projectProduct.setPlan(json.getLongValue("plans"));
                projectProduct.setBranch(json.getLongValue("branchs"));
                iProjectProductService.create(projectProduct);
            }
        }
        ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.PROJECT.getValue(), null,  StaticDict.Action__type.OPENED.getValue(), StringUtils.isNotBlank(et.getProducts()) ? et.getProducts() : "","", null, iActionService);

        return true;
    }

    public JSONObject getSettings() {
        JSONObject  jsonObject = new JSONObject();
        List<IbzproConfig> list = iIbzproConfigService.list(new QueryWrapper<IbzproConfig>().eq("createman", AuthenticationUser.getAuthenticationUser().getUserid()).eq("Scope", StaticDict.ConfigScope.USER.getValue()).eq("vaild", StaticDict.YesNo.ITEM_1.getValue()).orderByDesc("updatedate"));
        if(list.size() > 0) {
            jsonObject.put("srfmstatus", list.get(0).getManagementstatus());
        }else {
            jsonObject.put("srfmstatus", StaticDict.ConfigManagementstatus.PRODUCT_PROJECT.getValue());
        }
        return jsonObject;
    }

    @Override
    public boolean update(Project et) {
        String sql = "select * from zt_project where (`name` = #{et.name} or `code` = #{et.code}) and `id` <> #{et.id}";
        Map<String,Object> param = new HashMap<>();
        param.put("name", et.getName());
        param.put("code", et.getCode());
        param.put("id", et.getId());
        List<JSONObject> nameList = projectService.select(sql,param);
        if(!nameList.isEmpty() && nameList.size() > 0) {
            throw new RuntimeException(String.format("[项目名称：%1$s]或[项目代号：%2$s]已经存在。如果您确定该记录已删除，请联系管理员恢复。", et.getName(), et.getCode()));
        }
        String comment = et.getComment() == null ? "" : et.getComment();
        JSONArray projectproducts = JSONArray.parseArray(et.getSrfarray());
        Project old = new Project();
        CachedBeanCopier.copy(get(et.getId()), old);
        if(!super.update(et)) {
            return false;
        }

        //关联产品
        iProjectProductService.remove(new QueryWrapper<ProjectProduct>().eq(StaticDict.Action__object_type.PROJECT.getValue(), et.getId()));
        if(projectproducts != null) {
            for (int i = 0; i < projectproducts.size(); i++) {
                JSONObject json = projectproducts.getJSONObject(i);
                ProjectProduct projectProduct = new ProjectProduct();
                projectProduct.setProject(et.getId());
                if (json.containsKey("products") && json.get("products") != null) {
                    projectProduct.setProduct(json.getLongValue("products"));
                } else {
                    continue;
                }
                projectProduct.setPlan(json.getLongValue("plans"));
                projectProduct.setBranch(json.getLongValue("branchs"));
                iProjectProductService.create(projectProduct);
            }
        }

        //Team 处理

        List<History> changes = ChangeUtil.diff(old, et,null,null,diffAttrs);
        ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.PROJECT.getValue(), changes,  StaticDict.Action__type.EDITED.getValue(), comment,"", null, iActionService);

        return true;
    }

    @Override
    public boolean remove(Long key) {
        if(!super.remove(key)) {
            return false;
        }
        iDocLibService.remove(new QueryWrapper<DocLib>().eq(StaticDict.Action__object_type.PROJECT.getValue(), key));
        return true;
    }

    /**
     * [Activate:激活] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Project activate(Project et) {
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        Project old = new Project();
        CachedBeanCopier.copy(get(et.getId()), old);
        et.setStatus(StaticDict.Project__status.DOING.getValue());
        super.update(et);
        /* Readjust task. */

        List<History> changes = ChangeUtil.diff(old, et);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {
            ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.PROJECT.getValue(), changes,  StaticDict.Action__type.ACTIVATED.getValue(), comment,"", null, iActionService);

        }
        return et;
    }
    /**
     * [BatchUnlinkStory:批量解除关联需求] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Project batchUnlinkStory(Project et) {
        return super.batchUnlinkStory(et);
    }
    /**
     * [Close:关闭] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Project close(Project et) {
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        Project old = new Project();
        CachedBeanCopier.copy(get(et.getId()), old);
        et.setStatus(StaticDict.Project__status.CLOSED.getValue());
        et.setClosedby(AuthenticationUser.getAuthenticationUser().getUsername());
        et.setCloseddate(ZTDateUtil.now());
        super.update(et);
        List<History> changes = ChangeUtil.diff(old, et);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {
            ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.PROJECT.getValue(), changes,  StaticDict.Action__type.CLOSED.getValue(), comment,"", null, iActionService);

        }
        return et;
    }
    /**
     * [ImportPlanStories:项目关联需求-按计划关联] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Project importPlanStories(Project et) {
        List<Story> planStories = iStoryService.list(new QueryWrapper<Story>().eq("plan", et.getPlans()));
        String stories = "" ;
        for (Story story : planStories) {
            if(stories.length()>0) {
                stories += ",";
            }
            stories += story.getId() ;
        }
        if (StringUtils.isNotBlank(stories)) {
            et.set("stories", stories);
            linkStory(et);
        }
        return et;
    }
    /**
     * [LinkStory:关联需求] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Project linkStory(Project et) {
        if (et.getId() == null || et.get("stories") == null) {
            return et;
        }

        int order = -1;
        ProjectStory maxProjectStory = iProjectStoryService.getOne(new QueryWrapper<ProjectStory>().eq(StaticDict.Action__object_type.PROJECT.getValue(), et.getId()).orderByDesc("`order`").last("limit 0,1"));
        if (maxProjectStory != null) {
            order = maxProjectStory.getOrder();
        }

        for (String storyId :  et.get("stories").toString().split(",")) {
            Story story = iStoryService.get(Long.parseLong(storyId));
            ProjectStory exists = iProjectStoryService.getOne(new QueryWrapper<ProjectStory>().eq(StaticDict.Action__object_type.PROJECT.getValue(), et.getId()).eq(StaticDict.Action__object_type.STORY.getValue(), story.getId()));
            if (exists != null) {
                continue;
            }
            ProjectStory projectStory = new ProjectStory();
            projectStory.setProject(et.getId());
            projectStory.setStory(story.getId());
            projectStory.setProduct(story.getProduct());
            projectStory.setVersion(story.getVersion());
            projectStory.setOrder(++order);
            iProjectStoryService.create(projectStory);
            iStoryService.setStage(story);
            ActionHelper.createHis(story.getId(), StaticDict.Action__object_type.PROJECT.getValue(), null,  StaticDict.Action__type.LINKED2PROJECT.getValue(), "",String.valueOf(et.getId()), null, iActionService);

        }

        return super.linkStory(et);
    }
    /**
     * [ManageMembers:团队管理] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Project manageMembers(Project et) {
        List<ProjectTeam> list = et.getProjectteam();
        iTeamService.remove(new QueryWrapper<Team>().eq("type", StaticDict.Action__object_type.PROJECT.getValue()).eq("root", et.getId()));
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
            iTeamService.create(team);
            i ++;
        }
        return et;
    }
    /**
     * [PmsEeProjectAllTaskCount:项目立项任务快速分组计数器] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Project pmsEeProjectAllTaskCount(Project et) {
        Map<String,Object> map = et.getExtensionparams();
        String srfCurProject = map.get("n_project_in").toString().replaceAll(";",",");
        String srfloginname = AuthenticationUser.getAuthenticationUser().getUsername();
        List<Task> tempTasks = iTaskService.list(new QueryWrapper<Task>().last(String.format("and project in (%1$s) and taskspecies = 'temp' and (assignedTo = '%2$s' or openedBy = '%3$s'  or FIND_IN_SET('%4$s', finishedList) or closedBy = '%5$s' or finishedBy = '%6$s' or canceledBy = '%7$s' )",srfCurProject,srfloginname,srfloginname,srfloginname,srfloginname,srfloginname,srfloginname)));
        et.setTemptaskcnt(tempTasks.size());
        List<Task> cycleTasks = iTaskService.list(new QueryWrapper<Task>().last(String.format("and project in (%1$s) and taskspecies = 'cycle' and (assignedTo = '%2$s' or openedBy = '%3$s'  or FIND_IN_SET('%4$s', finishedList) or closedBy = '%5$s' or finishedBy = '%6$s' or canceledBy = '%7$s'  )",srfCurProject,srfloginname,srfloginname,srfloginname,srfloginname,srfloginname,srfloginname)));
        et.setCycletaskcnt(cycleTasks.size());
        List<Task> planTasks = iTaskService.list(new QueryWrapper<Task>().last(String.format("and project in (%1$s) and taskspecies = 'plan' and (assignedTo = '%2$s' or openedBy = '%3$s'  or FIND_IN_SET('%4$s', finishedList) or closedBy = '%5$s' or finishedBy = '%6$s' or canceledBy = '%7$s'  )",srfCurProject,srfloginname,srfloginname,srfloginname,srfloginname,srfloginname,srfloginname)));
        et.setPlantaskcnt(planTasks.size());
        List<Task> allTasks = iTaskService.list(new QueryWrapper<Task>().last(String.format("and project in (%1$s)  and (assignedTo = '%2$s' or openedBy = '%3$s'  or FIND_IN_SET('%4$s', finishedList) or closedBy = '%5$s' or finishedBy = '%6$s' or canceledBy = '%7$s'  )",srfCurProject,srfloginname,srfloginname,srfloginname,srfloginname,srfloginname,srfloginname)));
        et.setAlltaskcnt(allTasks.size());
        return et;
    }
    /**
     * [PmsEeProjectTodoTaskCount:项目立项待办任务快速分组计数器] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Project pmsEeProjectTodoTaskCount(Project et) {
        Map<String,Object> map = et.getExtensionparams();
        String srfCurProject = map.get("n_project_in").toString().replaceAll(";",",");

        String srfloginname = AuthenticationUser.getAuthenticationUser().getUsername();
        List<Task> tempTasks = iTaskService.list(new QueryWrapper<Task>().last(String.format("and project in (%1$s) and taskspecies = 'temp' and (assignedTo = '%2$s' )",srfCurProject,srfloginname)));
        et.setTemptaskcnt(tempTasks.size());
        List<Task> cycleTasks = iTaskService.list(new QueryWrapper<Task>().last(String.format("and project in (%1$s) and taskspecies = 'cycle' and (assignedTo = '%2$s' )",srfCurProject,srfloginname)));
        et.setCycletaskcnt(cycleTasks.size());
        List<Task> planTasks = iTaskService.list(new QueryWrapper<Task>().last(String.format("and project in (%1$s) and taskspecies = 'plan' and (assignedTo = '%2$s' )",srfCurProject,srfloginname)));
        et.setPlantaskcnt(planTasks.size());
        List<Task> allTasks = iTaskService.list(new QueryWrapper<Task>().last(String.format("and project in (%1$s)  and (assignedTo = '%2$s' )",srfCurProject,srfloginname)));
        et.setAlltaskcnt(allTasks.size());
        return et;
    }
    /**
     * [Putoff:延期] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Project putoff(Project et) {
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        Project old = new Project();
        CachedBeanCopier.copy(et.getId(), et);
        super.update(et);
        List<History> changes = ChangeUtil.diff(old, et, null, new String[]{"end", "days"}, null);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {

            ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.PROJECT.getValue(), changes,  StaticDict.Action__type.DELAYED.getValue(), comment,"", null, iActionService);

        }
        return et;
    }
    /**
     * [Start:开始] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Project start(Project et) {
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        Project old = new Project();
        CachedBeanCopier.copy(get(et.getId()), old);
        et.setStatus(StaticDict.Project__status.DOING.getValue());
        super.update(et);
        List<History> changes = ChangeUtil.diff(old, et);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {
            ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.PROJECT.getValue(), changes,  StaticDict.Action__type.STARTED.getValue(), comment,"", null, iActionService);

        }
        return et;
    }
    /**
     * [Suspend:挂起] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Project suspend(Project et) {
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        Project old = new Project();
        CachedBeanCopier.copy(get(et.getId()), old);
        et.setStatus(StaticDict.Project__status.SUSPENDED.getValue());
        super.update(et);
        List<History> changes = ChangeUtil.diff(old, et);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {
            ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.PROJECT.getValue(), changes,  StaticDict.Action__type.SUSPENDED.getValue(), comment,"", null, iActionService);

        }
        return et;
    }
    /**
     * [UnlinkMember:移除成员] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Project unlinkMember(Project et) {
        return super.unlinkMember(et);
    }
    /**
     * [UnlinkStory:解除关联需求] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Project unlinkStory(Project et) {
        if (et.getId() == null || et.get(StaticDict.Action__object_type.STORY.getValue()) == null) {
            throw new RuntimeException("解除需求错误");
        }

        iProjectStoryService.remove(new QueryWrapper<ProjectStory>().eq(StaticDict.Action__object_type.PROJECT.getValue(), et.getId()).eq(StaticDict.Action__object_type.STORY.getValue(), et.get(StaticDict.Action__object_type.STORY.getValue())));

        //order 处理
        ActionHelper.createHis(Long.parseLong(et.get(StaticDict.Action__object_type.STORY.getValue()).toString()), StaticDict.Action__object_type.STORY.getValue(), null,  StaticDict.Action__type.UNLINKEDFROMPROJECT.getValue(), "",String.valueOf(et.getId()), null, iActionService);

        //需求的task处理
        List<Task> tasks = iTaskService.list(new QueryWrapper<Task>().eq(StaticDict.Action__object_type.STORY.getValue(), et.get(StaticDict.Action__object_type.STORY.getValue())).eq(StaticDict.Action__object_type.PROJECT.getValue(), et.getId()).in("status", StaticDict.Task__status.WAIT.getValue(), StaticDict.Task__status.DOING.getValue()));
        for (Task task : tasks) {
            iTaskService.cancel(task);
        }
        return et;
    }
    /**
     * [UpdateOrder:排序] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Project updateOrder(Project et) {
        return super.updateOrder(et);
    }

    @Override
    public Project sysGet(Long key) {
        Project project = super.sysGet(key);
        String sql = "SELECT COUNT(1) AS ISTOP FROM `t_ibz_top` t WHERE t.OBJECTID = #{et.id} AND t.TYPE = 'project' AND t.ACCOUNT = #{et.account}";
        HashMap<String, Object> param = new HashMap<>();
        param.put("id",project.getId());
        param.put("account",AuthenticationUser.getAuthenticationUser().getLoginname());
        List<JSONObject> result = this.select(sql, param);
        if (result.size()>0){
            project.setIstop(result.get(0).getInteger("ISTOP"));
        }

        return project;
    }

    @Override
    public Project get(Long key) {
        Project project = super.get(key);
        List<ProjectProduct> list = iProjectProductService.selectByProject(key);
        if(list.size() > 0) {
            JSONArray jsonArray = new JSONArray();
            for(ProjectProduct projectProduct : list) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("products", projectProduct.getProduct());
                jsonObject.put("plans", projectProduct.getPlan());
                jsonObject.put("branchs", projectProduct.getBranch());
                jsonArray.add(jsonObject);
            }
            project.setSrfarray(jsonArray.toString());
        }

        return project;
    }
}

