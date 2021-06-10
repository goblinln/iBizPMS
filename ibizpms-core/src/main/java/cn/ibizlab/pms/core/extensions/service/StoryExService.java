package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.util.ibizzentao.common.ZTDateUtil;
import cn.ibizlab.pms.core.zentao.domain.*;
import cn.ibizlab.pms.core.zentao.filter.StorySearchContext;
import cn.ibizlab.pms.core.zentao.filter.StorySpecSearchContext;
import cn.ibizlab.pms.core.zentao.service.*;
import cn.ibizlab.pms.core.zentao.service.impl.StoryServiceImpl;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.errors.BadRequestAlertException;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import springfox.documentation.spring.web.json.Json;

import java.sql.Timestamp;
import java.util.*;

/**
 * 实体[需求] 自定义服务对象
 */
@Slf4j
@Primary
@Service("StoryExService")
public class StoryExService extends StoryServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    @Autowired
    IActionService iActionService;

    @Autowired
    IFileService iFileService;

    @Autowired
    IProductService iProductService;

    @Autowired
    IStorySpecService iStorySpecService;

    @Autowired
    IProjectStoryService iProjectStoryService;

    @Autowired
    IBugService iBugService;

    @Autowired
    IStoryStageService iStoryStageService;

    @Autowired
    IProjectProductService iProjectProductService;

    @Autowired
    IProductPlanService iProductPlanService;

    @Autowired
    IProjectService iProjectService;

    @Autowired
    IReleaseService iReleaseService;

    @Autowired
    IBuildService iBuildService;

    @Autowired
    ITaskService iTaskService;

    String[] ignores = {"lasteditedby", "lastediteddate", "versionc", "assignedtopk", "mailtopk"};

    @Override
    public Story sysGet(Long key) {
        if(key == null || key == 0L) {
            Story story = new Story();
            story.setId(key);
            story.setOrgid(AuthenticationUser.getAuthenticationUser().getOrgid());
            return story;
        }else {
            Story story = super.sysGet(key);
            String sql = "SELECT COUNT(1) as ISFAVOURITES from t_ibz_favorites t where t.OBJECTID = #{et.id} and t.TYPE = 'story' and t.ACCOUNT = #{et.account}";
            Map<String, Object> param = new HashMap<>();
            param.put("id", story.getId());
            param.put("account", AuthenticationUser.getAuthenticationUser().getLoginname());
            List<JSONObject> list = this.select(sql, param);
            if (list.size() > 0) {
                story.setIsfavorites(list.get(0).getString("ISFAVOURITES"));
            }
            story.setStorystages(null);
            story.setStoryspecs(null);
            return story;
        }

    }

    @Override
    public boolean create(Story et) {
        et.setVersion(1);//版本号 默认1
        et.setReviewedby("");//由谁评审
        et.setAssigneddate(et.getAssignedto() != null ? ZTDateUtil.now() : ZTDateUtil.nul());//指派日期  需求指派的人不为null 则设置指派日期为系统当前时间 需求指派的人为null则日期设置null
        //设置当前状态   需求的“不需要评审”字段为null 或者 为空 或者字段值为1（表示不需要评审） 则设置当前状态为草稿  否则设置当前状态为激活
        et.setStatus((et.getNeednotreview() == null || "".equals(et.getNeednotreview()) || !StaticDict.NeedNotReviewNew.ITEM_1.getValue().equals(et.getNeednotreview())) ? StaticDict.Story__status.DRAFT.getValue() : StaticDict.Story__status.ACTIVE.getValue());
        //如果当前状态是草稿
        if (StaticDict.Story__status.DRAFT.getValue().equals(et.getStatus())) {
            //如果指派的人为null或为空
            if (et.getAssignedto() == null || "".equals(et.getAssignedto())) {
                //那么“由谁评审”字段设置为  当前需求所属产品的产品负责人
                et.setReviewedby(iProductService.get(et.getProduct()).getPo());
            } else {
                //如果指派的人不为空 “由谁评审”字段则设置为 指派的人
                et.setReviewedby(et.getAssignedto());
            }
        }
        //设置“所处阶段” 根据“项目”字段 如果不为null并且大于0 那么“所处阶段”设置为已立项；
        //如果“项目”字段 为null或者不大于0 则判断“所属计划”字段
        //如果“所属计划”字段不为null并且不为空并且不等于0，则设置“所处阶段”为已计划；
        //否则设置“所处计划”为未开始
        et.setStage(et.getProject() != null && et.getProject() > 0 ? StaticDict.Story__stage.PROJECTED.getValue() : et.getPlan() != null && !"".equals(et.getPlan()) && !"0".equals(et.getPlan()) ? StaticDict.Story__stage.PLANNED.getValue() : StaticDict.Story__stage.WAIT.getValue());
        String files = et.getFiles();//获取需求附件
        String noticeusers = et.getNoticeusers();//获取需求的消息通知用户
        StorySpec storySpec = new StorySpec();
        storySpec.setTitle(et.getTitle());//将需求的需求名称给到需求描述对象的“需求名称”字段
        storySpec.setSpec(et.getSpec());//将需求的需求描述给到需求描述对象的“需求描述”字段    可同上合并
        storySpec.setVerify(et.getVerify());//将需求的验收标准给到需求描述对象的“验收标准”字段  可同上合并
        long projectId = 0L;//create之前拿出project属性，否则创建之后，project属性会置空
        boolean flag = (et.getProject() != null && et.getProject() != 0L);
        if (flag){
            projectId = et.getProject();
            et.setStatus(StaticDict.Story__status.ACTIVE.getValue());
        }
        //调用父类的create（）
        if(!super.create(et)) {
            return false;
        }
        // 更新file
        FileHelper.updateObjectID(et.getId(),StaticDict.File__object_type.STORY.getValue(), files,  String.valueOf(et.getVersion()), iFileService);
        FileHelper.saveUpload(StaticDict.Action__object_type.STORY.getValue(), et.getId(), "", "", "");
        storySpec.setStory(et.getId());//将需求的编号值给到需求描述对象的“需求”字段
        storySpec.setVersion(1);//需求描述对象的版本号设置为1
        iStorySpecService.create(storySpec);
        et.setSpec(storySpec.getSpec());
        //如果需求的项目不为null 并且不为0 并且 所处阶段不为草稿状态
        if (flag && StringUtils.compare(et.getStage(), StaticDict.Story__status.DRAFT.getValue()) != 0) {
            //projectstroy 项目中需要做的需求
            ProjectStory projectStory = new ProjectStory();
            projectStory.setProject(projectId);//将需求的项目内容给到projectstroy的“项目”字段
            projectStory.setProduct(et.getProduct());//将需求的所属产品给到projectstroy的“所属产品”字段
            projectStory.setStory(et.getId());//将需求的编号值给到projectstroy的“需求”字段
            projectStory.setOrder(1);//设置排序
            projectStory.setVersion(1);//设置版本
            iProjectStoryService.create(projectStory);
        }

        String action = StaticDict.Action__type.OPENED.getValue();//opened
        String actionText = StaticDict.Action__type.OPENED.getText();//创建
        String extra = "";//备注
        //如果需求的来源bug不为null 并且不为0
        if (et.getFrombug() != null && et.getFrombug() != 0) {
            //action赋值为转需求
            action = StaticDict.Action__type.FROMBUG.getValue();
            extra = String.valueOf(et.getFrombug());
            Bug bug = new Bug();
            bug.setId(et.getFrombug());//设置bug编号
            bug.setTostory(et.getId());//设置bug的“转需求”字段
            bug.setStatus(StaticDict.Bug__status.CLOSED.getValue());//设置bug状态
            bug.setResolution(StaticDict.Bug__resolution.TOSTORY.getEmptyText());//设置解决方案
            bug.setResolvedby(AuthenticationUser.getAuthenticationUser().getUsername());//设置由谁解决
            bug.setResolveddate(ZTDateUtil.now());//设置解决日期 当前时间
            bug.setClosedby(AuthenticationUser.getAuthenticationUser().getUsername());//设置由谁关闭
            bug.setCloseddate(ZTDateUtil.now());//设置关闭日期
            bug.setAssignedto(StaticDict.Assignedto_closed.CLOSED.getValue());//设置指派给
            bug.setAssigneddate(ZTDateUtil.now());//设置指派日期
            iBugService.sysUpdate(bug);

            /* add files to story from bug. */

            ActionHelper.createHis(et.getFrombug(), StaticDict.Action__object_type.BUG.getValue(), null,  StaticDict.Action__type.TOSTORY.getValue(), "",String.valueOf(et.getId()), AuthenticationUser.getAuthenticationUser().getUsername(), iActionService);
            ActionHelper.createHis(et.getFrombug(), StaticDict.Action__object_type.BUG.getValue(), null,  StaticDict.Action__type.CLOSED.getValue(), "","", AuthenticationUser.getAuthenticationUser().getUsername(), iActionService);

        }

        setStage(et);
        ActionHelper.sendTodoOrToread(et.getId(), et.getTitle(), noticeusers, et.getAssignedto(), et.getMailto(), "需求", StaticDict.Action__object_type.STORY.getValue(), "stories", actionText, true, iActionService);

        ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.STORY.getValue(), null,  action, "",extra, AuthenticationUser.getAuthenticationUser().getUsername(), iActionService);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Story setStage(Story et) {
        iStoryStageService.removeByStory(et.getId());

        et = this.get(et.getId());
        if (et.getStagedby() != null && !"".equals(et.getStagedby())) {
            return et;
        }

        Product product = et.getZtproduct();
        boolean hasBranch = (!StaticDict.Product__status.NORMAL.getValue().equals(product.getType()) && (et.getBranch() == null || et.getBranch()==0));

        String releaseSql = String.format("select DISTINCT branch,'released' as stage from zt_release where deleted = '0' and CONCAT(',', stories, ',') like %1$s ", "CONCAT('%,'," + et.getId() + ",',%')");
        List<JSONObject> releaseList = iStoryStageService.select(releaseSql, null);
        if (releaseList.size() > 0) {
            if (hasBranch) {
                for (JSONObject jsonObject : releaseList) {
                    StoryStage storyStage = new StoryStage();
                    storyStage.setStory(et.getId());
                    storyStage.setBranch(jsonObject.getLongValue(StaticDict.Action__object_type.BRANCH.getValue()));
                    storyStage.setStage(StaticDict.Story__stage.RELEASED.getValue());
                    iStoryStageService.create(storyStage);
                }
            } else {
                Story story = new Story();
                story.setStage(StaticDict.Story__stage.RELEASED.getValue());
                story.setId(et.getId());
                this.sysUpdate(story);
            }
            return et;
        }

        String projectSql = String.format("select DISTINCT t1.project,t3.branch from zt_projectstory t1 left join zt_project t2 on t1.project = t2.id left join zt_projectproduct t3 on t1.project = t3.project where t1.story = %1$s and t2.deleted = '0'", et.getId());
        List<JSONObject> objectList = this.select(projectSql, null);
        String projectids = "0";
        for (JSONObject jsonObject : objectList) {
            if (!"".equals(projectids)) {
                projectids += ",";
            }
            projectids += jsonObject.get(StaticDict.Action__object_type.PROJECT.getValue());
        }

        String taskSql = String.format("select DISTINCT t1.project,t3.branch,case when t1.develdone = t1.devel and t1.devel > 0 and t1.testdone = t1.test and t1.test > 0 then 'tested' when (t1.develwait > 0 or t1.develdoing > 0) and t1.testdone = t1.test and t1.test > 0 then 'testing' when t1.testdoing > 0 then 'testing' when t1.develdone = t1.devel and t1.devel > 0 and t1.testwait > 0 and t1.testdone > 0 then 'testing' when t1.develdone = t1.devel and t1.devel > 0 and t1.testwait = t1.test then 'developed' when t1.develwait > 0 and t1.develdone > 0 and t1.testwait = t1.test then 'developing' when t1.develdoing > 0 and t1.testwait = t1.test then 'developing' else 'projected' end as stage from (SELECT t.project, sum( IF ( t.type = 'devel' AND t.`status` = 'wait', t.ss, 0 ) ) AS develwait, sum( IF ( t.type = 'devel' AND t.`status` = 'done', t.ss, 0 ) ) AS develdone,sum( IF ( t.type = 'devel' AND t.`status` = 'doing', t.ss, 0 ) ) AS develdoing, sum( IF ( t.type = 'devel' AND t.`status` = 'pause', t.ss, 0 ) ) AS develpause, sum( IF ( t.type = 'test' AND t.`status` = 'wait', t.ss, 0 ) ) AS testwait, sum( IF ( t.type = 'test' AND t.`status` = 'done', t.ss, 0 ) ) AS testdone, sum( IF ( t.type = 'test' AND t.`status` = 'doing', t.ss, 0 ) ) AS testdoing, sum( IF ( t.type = 'test' AND t.`status` = 'pause', t.ss, 0 ) ) AS testpause, sum( IF ( t.type = 'test', t.ss, 0 ) ) AS test, sum( IF ( t.type = 'devel', t.ss, 0 ) ) AS devel FROM ( SELECT type,project,CASE WHEN `status` IS NULL OR `status` = '' THEN 'wait'  WHEN `status` = 'closed' THEN 'done' ELSE `status`  END `status`, 1 ss  FROM zt_task  WHERE story = %1$s  AND type IN ( 'devel', 'test' )  AND `status` <> 'cancel'  AND closedReason <> 'cancel'  AND deleted = '0'  AND FIND_IN_SET(project,'%2$s')  ) t  GROUP BY t.project) t1 left JOIN zt_projectproduct t3 on t3.project=t1.project", et.getId(), projectids);
        List<JSONObject> taskList = this.select(taskSql, null);

        if ((taskList.isEmpty() || taskList.size() == 0) && objectList.size() > 0) {
            if (hasBranch) {
                for (JSONObject jsonObject : objectList) {
                    // 插入需求阶段
                    StoryStage storyStage = new StoryStage();
                    storyStage.setStory(et.getId());
                    storyStage.setBranch(jsonObject.getLongValue(StaticDict.Action__object_type.BRANCH.getValue()));
                    storyStage.setStage(StaticDict.Story__stage.PROJECTED.getValue());
                    iStoryStageService.create(storyStage);
                }
            }

            Story story = new Story();
            story.setStage(StaticDict.Story__stage.PROJECTED.getValue());
            story.setId(et.getId());
            this.sysUpdate(story);
            return et;
        } else if (taskList.size() > 0) {
            if (hasBranch) {
                for (JSONObject jsonObject : taskList) {
                    // 插入需求阶段
                    StoryStage storyStage = new StoryStage();
                    storyStage.setStory(et.getId());
                    storyStage.setBranch(jsonObject.getLongValue(StaticDict.Action__object_type.BRANCH.getValue()));
                    storyStage.setStage(jsonObject.getString("stage"));
                    iStoryStageService.create(storyStage);
                }

            }
            Story story = new Story();
            story.setStage(taskList.get(0).getString("stage"));
            story.setId(et.getId());
            this.sysUpdate(story);
            return et;
        }

        Map<Long, String> stages = new HashMap<>();

        if (hasBranch && (et.getPlan() != null && !"".equals(et.getPlan()))) {
            String planSql = String.format("select DISTINCT branch from zt_productplan where FIND_IN_SET(id, %1$s) ", et.getPlan());
            List<JSONObject> planList = this.select(planSql, null);
            for (JSONObject jsonObject : planList) {
                stages.put(jsonObject.getLongValue(StaticDict.Action__object_type.BRANCH.getValue()), StaticDict.Story__stage.PLANNED.getValue());
            }
        }
        if (objectList.size() > 0) {
            if (et.getPlan() == null || "".equals(et.getPlan())) {
                Story story = new Story();
                story.setStage(StaticDict.Story__stage.WAIT.getValue());
                story.setId(et.getId());
                this.sysUpdate(story);
            } else if (!"0".equals(et.getPlan())) {
                Story story = new Story();
                story.setStage(StaticDict.Story__stage.PLANNED.getValue());
                story.setId(et.getId());
                this.sysUpdate(story);
            }
            if (hasBranch) {
                iStoryStageService.removeByStory(et.getId());
                for (Long branch : stages.keySet()) {
                    String stage = stages.get(branch);

                    StoryStage storyStage = new StoryStage();
                    storyStage.setStory(et.getId());
                    storyStage.setBranch(branch);
                    storyStage.setStage(stage);
                    iStoryStageService.create(storyStage);
                }
            }
        } else {
            if (et.getPlan() != null && !"".equals(et.getPlan()) && !"0".equals(et.getPlan())) {
                Story story = new Story();
                story.setStage(StaticDict.Story__stage.PLANNED.getValue());
                story.setId(et.getId());
                this.sysUpdate(story);
            }
        }
        return et;
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateParentStatus(Story story, Story parentStory, boolean changed) {
        log.info("updateParentStatus：未实现");

        if (parentStory == null) {
            parentStory = this.get(story.getParent());
        }

        if (parentStory == null) {
            Story story1 = new Story();
            story1.setParent(0L);
            story1.setId(story.getId());
            this.sysUpdate(story1);
            return;
        }
        Story old = new Story();
        CachedBeanCopier.copy(parentStory, old);
        if (parentStory.getParent() != -1) {
            Story parentStory1 = new Story();
            parentStory1.setId(parentStory.getId());
            parentStory1.setParent(-1L);
            this.sysUpdate(parentStory1);
            parentStory.setParent(-1L);
        }
        computeEstimate(parentStory.getId());
        String sql = String.format("select DISTINCT `status` from zt_story where parent = %1$s and deleted = '0'", parentStory.getId());
        List<JSONObject> childrenStatus = this.select(sql, null);
        if (childrenStatus.size() == 0) {
            Story parentStory1 = new Story();
            parentStory1.setId(parentStory.getId());
            parentStory1.setParent(-1L);
            this.sysUpdate(parentStory1);

            return;
        }
        String parentStatus = parentStory.getStatus();
        if (childrenStatus.size() == 1 && !StaticDict.Story__status.CHANGED.getValue().equals(parentStatus)) {
            parentStatus = childrenStatus.get(0).getString("status");
            if (StaticDict.Story__status.DRAFT.getValue().equals(parentStatus) || StaticDict.Story__status.CHANGED.getValue().equals(parentStatus)) {
                parentStatus = StaticDict.Story__status.ACTIVE.getValue();
            }
        } else if (childrenStatus.size() > 1 && StaticDict.Story__status.CLOSED.getValue().equals(parentStatus)) {
            parentStatus = StaticDict.Story__status.ACTIVE.getValue();
        }

        if (parentStatus != null && !parentStatus.equals(parentStory.getStatus())) {
            Story story1 = new Story();
            Timestamp timestamp = ZTDateUtil.now();
            story1.setStatus(parentStatus);
            story1.setStage(StaticDict.Story__stage.WAIT.getValue());
            if (StaticDict.Story__status.ACTIVE.getValue().equals(parentStatus)) {
                story1.setAssignedto(parentStory.getOpenedby());
                story1.setAssigneddate(timestamp);
                story1.setClosedby("");
                story1.setClosedreason("");
                story1.set("Closeddate", "0000-00-00");
                story1.setReviewedby("");
                story1.set("reviewedDate", "0000-00-00");
            } else if (StaticDict.Story__status.CLOSED.getValue().equals(parentStatus)) {
                story1.setAssignedto(StaticDict.Assignedto_closed.CLOSED.getValue());
                story1.setAssigneddate(timestamp);
                story1.setClosedby(AuthenticationUser.getAuthenticationUser().getLoginname());
                story1.setClosedreason(StaticDict.Story__closed_reason.DONE.getValue());
                story1.setCloseddate(timestamp);
            }
            story1.setLasteditedby(AuthenticationUser.getAuthenticationUser().getLoginname());
            story1.setLastediteddate(timestamp);
            story1.setParent(-1L);
            story1.setId(parentStory.getId());
            if (this.sysUpdate(story1)) {
                if (changed) {
                    return;
                }
                Story newParentStory = new Story();
                CachedBeanCopier.copy(this.get(parentStory.getId()), newParentStory);
                List<History> changes = ChangeUtil.diff(old, newParentStory);
                if (changes.size() > 0) {
                    String actionType = StaticDict.Story__status.ACTIVE.getValue().equals(parentStatus) ? StaticDict.Action__type.ACTIVATED.getValue() : StaticDict.Story__status.CLOSED.getValue().equals(parentStatus) ? StaticDict.Action__type.CLOSED.getValue() : "";
                    ActionHelper.createHis(parentStory.getId(), StaticDict.Action__object_type.STORY.getValue(), changes,  actionType, "","", null, iActionService);
                }
            }
        } else {
            if (changed) {
                return;
            }
            Story newParentStory = this.get(parentStory.getId());
            List<History> changes = ChangeUtil.diff(parentStory, newParentStory);
            if (changes.size() > 0) {
                ActionHelper.createHis(parentStory.getId(), StaticDict.Action__object_type.STORY.getValue(), changes,  StaticDict.Action__type.EDITED.getValue(), "","", null, iActionService);
            }
        }
    }

    /**
     * @param parentStoryId
     */
    @Transactional(rollbackFor = Exception.class)
    public void computeEstimate(Long parentStoryId) {
        String sql = String.format("select `id`,`estimate`,`status` from zt_story where deleted = '0' and parent = %1$s ", parentStoryId);

        List<JSONObject> list = this.select(sql, null);
        if (list.size() == 0) {
            return;
        }

        double estimate = 0d;
        for (JSONObject jsonObject : list) {
            estimate += jsonObject.getDoubleValue("estimate");
        }
        Story parentStory = new Story();
        parentStory.setId(parentStoryId);
        parentStory.setEstimate(estimate);
        this.sysUpdate(parentStory);
    }

    @Override
    public void saveBatch(List<Story> stories) {
        Long branch = 0L;
        Long product = stories.get(0).getProduct() == 0 ? this.get(stories.get(0).getParent()).getProduct() : stories.get(0).getProduct();
        Timestamp nowDate = ZTDateUtil.now();
        Long module = 0L;
        String plan = "0";
        int pri = 0;
        String source = "";
        Long storyId = stories.get(0).getParent();
        List<Story> storyList = new ArrayList<>();
        for (Story story : stories) {
            if (story.getTitle() == null || "".equals(story.getTitle())) {
                continue;
            }
            story.setModule(story.getModule() != null ? story.getModule() : module);//所属模块
            story.setPlan(story.getPlan() != null ? story.getPlan() : plan);//所属计划
            story.setType(StaticDict.Story__type.STORY.getValue());//需求类型
            story.setProduct(product);
            story.setSource(story.getSource() != null ? story.getSource() : source);//需求来源
            story.setPri(story.getPri() != null ? story.getPri() : pri);//优先级
            story.setNeednotreview(story.getNeednotreview() != null && StaticDict.YesNo.ITEM_0.getValue().equals(story.getNeednotreview()) ? StaticDict.NeedNotReviewNew.ITEM_1.getValue() : null);//是否需要评审
            story.setOpenedby(AuthenticationUser.getAuthenticationUser().getLoginname());//由谁创建
            story.setAssignedto("");
            story.setReviewedby("");//由谁评审
            story.setOpeneddate(nowDate);//创建日期
            story.setBranch(story.getBranch() != null ? story.getBranch() : branch);
            story.setVersion(1);
            storyList.add(story);
        }
        String storyIds = "";

        for (Story story : storyList) {
            if (!getProxyService().create(story)) {
                continue;
            }
            if (!"".equals(storyIds)) {
                storyIds += ",";
            }

            storyIds += story.getId();
        }
        if (!"".equals(storyIds) && storyId != null) {
            subdivide(storyId, storyIds);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void subdivide(Long storyId, String stories) {
        Story story = this.get(storyId);
        computeEstimate(storyId);
        //Story newStory = new Story();
        Story newStory = createNewStory(storyId);
        newStory.setParent(-1L);
        newStory.setPlan("0");
        // newStory.setLastediteddate(ZTDateUtil.now());//最后修改日期
        // newStory.setLasteditedby(AuthenticationUser.getAuthenticationUser().getLoginname());//最后由谁修改
        // newStory.setId(storyId);
        newStory.setChildstories(story.getChildstories() != null && !"".equals(story.getChildstories()) ? story.getChildstories() + "," + stories : stories);
        this.sysUpdate(newStory);
        List<History> changes = ChangeUtil.diff(story, newStory);
        if (changes.size() > 0) {
            ActionHelper.createHis(story.getId(), StaticDict.Action__object_type.STORY.getValue(), changes,  StaticDict.Action__type.CREATECHILDRENSTORY.getValue(), "",stories, null, iActionService);
        }

    }

    //创建新的需求对象
    public Story createNewStory(Long id){
        Story newStory = new Story();
        newStory.setLastediteddate(ZTDateUtil.now());//最后修改日期
        newStory.setLasteditedby(AuthenticationUser.getAuthenticationUser().getLoginname());//最后由谁修改
        newStory.setId(id);
        return newStory;
    }

    @Override
    public boolean update(Story et) {
        Story old = new Story();
        CachedBeanCopier.copy(this.get(et.getId()), old);
        //stages 处理

        if (!super.update(et)) {
            return false;
        }
        CachedBeanCopier.copy(get(et.getId()), et);

        if (old.getProduct().equals(et.getProduct())) {
            UpdateWrapper<ProjectStory> updateWrapper = new UpdateWrapper<ProjectStory>();
            updateWrapper.set(StaticDict.Action__object_type.PRODUCT.getValue(), et.getProduct());
            updateWrapper.eq(StaticDict.Action__object_type.STORY.getValue(), et.getId());
            iProjectStoryService.update(updateWrapper);

            //projectProduct 处理
            //需求相关项目  挪至  新的product

            List<ProjectStory> projectStories = iProjectStoryService.list(new QueryWrapper<ProjectStory>().select("distinct project").eq(StaticDict.Action__object_type.STORY.getValue(), et.getId()));
            Long[] projects = new Long[projectStories.size()];
            int i = 0;
            for (ProjectStory projectStory : projectStories) {
                projects[i] = projectStory.getProject();
            }
            if (projects.length > 0) {
                List<ProjectProduct> projectProducts = iProjectProductService.list(new QueryWrapper<ProjectProduct>().eq(StaticDict.Action__object_type.PRODUCT.getValue(), old.getProduct()).in(StaticDict.Action__object_type.PROJECT.getValue(), projects).ne(StaticDict.Action__object_type.PRODUCT.getValue(), et.getProduct()));
                for (ProjectProduct projectProduct : projectProducts) {
                    projectProduct.setProduct(et.getProduct());
                    iProjectProductService.create(projectProduct);
                }
            }
        }

        //
        boolean changed = old.getParent().equals(et.getParent());
        if (old.getParent() > 0) {

            updateParentStatus(et, this.get(old.getParent()), changed);

            if (changed) {
                Story oldParentStory = this.get(old.getParent());
                List<Story> oldChildren = this.list(new QueryWrapper<Story>().eq("parent", old.getParent()));
                if (oldChildren.size() == 0) {
                    Story update = new Story();
                    update.setId(old.getParent());
                    update.setParent(0L);
                    this.sysUpdate(update);
                }
                StringBuilder strChildStories = new StringBuilder();
                for (Story child : oldChildren) {
                    strChildStories.append(child.getId()).append(",");
                }
                Story newParentStory = new Story();
                newParentStory.setId(old.getParent());
                newParentStory.setLasteditedby(AuthenticationUser.getAuthenticationUser().getUsername());
                newParentStory.setLastediteddate(ZTDateUtil.now());
                newParentStory.setChildstories(strChildStories.substring(0, strChildStories.length() - 1).toString());
                this.sysUpdate(newParentStory);

                ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.STORY.getValue(), null,  StaticDict.Action__type.UNLINKPARENTSTORY.getValue(), "",String.valueOf(old.getParent()), null, iActionService);

                List<History> changes = ChangeUtil.diff(oldParentStory, newParentStory, new String[]{"lasteditedby", "lastediteddate", "spec", "verify"});
                ActionHelper.createHis(old.getParent(), StaticDict.Action__object_type.STORY.getValue(), changes,  StaticDict.Action__type.UNLINKCHILDSTORY.getValue(), "", String.valueOf(et.getId()), null, iActionService);
            }
        }
        if (et.getParent() > 0) {
            updateParentStatus(et, null, changed);
            if (changed) {
                Story oldParentStory = this.get(et.getParent());
                List<Story> children = this.list(new QueryWrapper<Story>().eq("parent", et.getParent()));
                StringBuilder strChildStories = new StringBuilder();
                for (Story child : children) {
                    strChildStories.append(child.getId()).append(",");
                }
                Story newParentStory = createNewStory(et.getParent());
//                Story newParentStory = new Story();
//                newParentStory.setId(et.getParent());
//                newParentStory.setLasteditedby(AuthenticationUser.getAuthenticationUser().getUsername());
//                newParentStory.setLastediteddate(ZTDateUtil.now());
                newParentStory.setChildstories(strChildStories.substring(0, strChildStories.length() - 1).toString());
                this.sysUpdate(newParentStory);
                ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.STORY.getValue(), null,  StaticDict.Action__type.LINKPARENTSTORY.getValue(), "",String.valueOf(old.getParent()), null, iActionService);
                List<History> changes = ChangeUtil.diff(oldParentStory, newParentStory, new String[]{"lasteditedby", "lastediteddate", "spec", "verify"});
                ActionHelper.createHis(old.getParent(), StaticDict.Action__object_type.STORY.getValue(), changes,  StaticDict.Action__type.LINKCHILDSTORY.getValue(), "", String.valueOf(et.getId()), null, iActionService);
            }
        }

        List<History> changes = ChangeUtil.diff(old, et, new String[]{"lasteditedby", "lastediteddate", "spec", "verify"});
        if (StringUtils.isNotBlank(et.getComment()) || changes.size() > 0) {
            if (jugAssignToIsChanged(old,et)){
                ActionHelper.sendMarkDone(et.getId(),et.getTitle(),old.getAssignedto(), "需求", StaticDict.Action__object_type.STORY.getValue(), "stories", StaticDict.Action__type.EDITED.getText(), iActionService);
                // 发送待办消息
                ActionHelper.sendTodoOrToread(et.getId(), et.getTitle(), null, et.getAssignedto(), et.getMailto(), "需求", StaticDict.Action__object_type.STORY.getValue(), "stories", StaticDict.Action__type.EDITED.getText(), true, iActionService);
            }
            String strAction = changes.size() > 0 ? StaticDict.Action__type.EDITED.getValue() : StaticDict.Action__type.COMMENTED.getValue();
            String strActionText = changes.size() > 0 ? StaticDict.Action__type.EDITED.getText() : StaticDict.Action__type.COMMENTED.getText();
            ActionHelper.sendTodoOrToread(et.getId(), et.getTitle(), null, et.getAssignedto(), et.getMailto(), "需求", StaticDict.Action__object_type.STORY.getValue(), "stories", strActionText, false, iActionService);
            ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.STORY.getValue(), changes,  strAction, "", "", null, iActionService);

        }
        return true;
    }

    public boolean jugAssignToIsChanged(Story old, Story et){
        boolean flag = false;
        if ((et.getStatus().equals(StaticDict.Story__status.DRAFT.getValue()) || et.getStatus().equals(StaticDict.Story__status.ACTIVE.getValue()) || et.getStatus().equals(StaticDict.Story__status.CHANGED.getValue()))
                && !old.getAssignedto().equals(et.getAssignedto())){
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean remove(Long key) {
        Story old = this.get(key);
        if(!super.remove(key)) {
            return false;
        }
        if (old.getParent() > 0) {
            updateParentStatus(old, null, true);
            ActionHelper.createHis(key, StaticDict.Action__object_type.STORY.getValue(), null,  StaticDict.Action__type.DELETECHILDRENSTORY.getValue(), "", "", null, iActionService);
        }

        String sql = "SELECT * FROM `zt_task` where story = #{et.id}";
        Map<String, Object> param = new HashMap<>();
        param.put("id", key);
        List<JSONObject> task = this.select(sql, param);
        if (task.size() > 0) {
            for (JSONObject jsonTask : task) {
                Task et = JSON.toJavaObject(jsonTask, Task.class);
                et.setStory(0L);
                iTaskService.update(et);
                ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.TASK.getValue(), null,  StaticDict.Action__type.DELETECHILDRENTASK.getValue(), "", "", null, iActionService);
            }
        }

        return true;
    }

    /**
     * [Activate:激活] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story activate(Story et) {
        Story old = new Story();
        CachedBeanCopier.copy(this.get(et.getId()), old);

        et.setStatus(StaticDict.Story__status.ACTIVE.getValue());
        et.setClosedreason("");
        et.setCloseddate(ZTDateUtil.nul());
        et.setClosedby("");
        et.setRevieweddate(ZTDateUtil.nul());
        et.setReviewedby("");
        et.setAssigneddate(ZTDateUtil.now());
        if (StringUtils.isBlank(et.getAssignedto())) {
            et.setAssignedto(AuthenticationUser.getAuthenticationUser().getUsername());
        }
        String noticeusers = et.getNoticeusers();
        this.sysUpdate(et);
        setStage(et);
        if (et.getParent() > 0) {
            updateParentStatus(et, this.get(et.getParent()), false);
        }

        List<History> changes = ChangeUtil.diff(old, et, null, new String[]{"status", "stage", "assignedto", "closedby", "closedreason", "closeddate"}, null);
        if (changes.size() > 0) {
            ActionHelper.sendTodoOrToread(et.getId(), et.getTitle(), noticeusers, et.getAssignedto(), et.getMailto(), "需求", StaticDict.Action__object_type.STORY.getValue(), "stories",  StaticDict.Action__type.ACTIVATED.getText(), true, iActionService);
            ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.STORY.getValue(), changes,  StaticDict.Action__type.ACTIVATED.getValue(), "", String.valueOf(et.getParent()), null, iActionService);
        }
        return et;
    }
    /**
     * [AllPush:全部推送] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story allPush(Story et) {
        return super.allPush(et);
    }
    /**
     * [AssignTo:指派] 行为扩展：需求指派
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story assignTo(Story et) {
        String comment = et.getComment() == null ? "" : et.getComment();
        Story old = new Story();
        CachedBeanCopier.copy(this.get(et.getId()), old);

        if (StringUtils.compare(et.getAssignedto(), old.getAssignedto()) == 0) {
            return et;
        }

        et.setAssigneddate(ZTDateUtil.now());
        String noticeusers = et.getNoticeusers();
        this.sysUpdate(et);
        List<History> changes = ChangeUtil.diff(old, et, new String[]{"lasteditedby", "assigneddate", "lastediteddate","assignedtopk","mailtopk", "spec", "verify"});
        if (changes.size() > 0) {
            if (jugAssignToIsChanged(old,et)){
                ActionHelper.sendMarkDone(et.getId(),et.getTitle(),old.getAssignedto(), "需求", StaticDict.Action__object_type.STORY.getValue(), "stories", StaticDict.Action__type.CHANGED.getText(), iActionService);
                // 发送待办消息
                ActionHelper.sendTodoOrToread(et.getId(), et.getTitle(), noticeusers, et.getAssignedto(), et.getMailto(), "需求", StaticDict.Action__object_type.STORY.getValue(), "stories", StaticDict.Action__type.CHANGED.getText(), true, iActionService);
            }
            ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.STORY.getValue(), changes,  StaticDict.Action__type.ASSIGNED.getValue(), comment, et.getAssignedto(), null, iActionService);
        }
        return et;
    }
    /**
     * [BatchAssignTo:批量指派] 行为扩展：×，模板暂不支持批处理（参数）...
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story batchAssignTo(Story et) {
        return super.batchAssignTo(et);
    }
    /**
     * [BatchChangeBranch:批量变更平台/分支] 行为扩展：×，模板暂不支持批处理（参数）...
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story batchChangeBranch(Story et) {
        return super.batchChangeBranch(et);
    }
    /**
     * [BatchChangeModule:批量变更模块] 行为扩展：×，模板暂不支持批处理（参数）...
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story batchChangeModule(Story et) {
        return super.batchChangeModule(et);
    }
    /**
     * [BatchChangePlan:批量关联计划] 行为扩展：×，模板暂不支持批处理（参数）...
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story batchChangePlan(Story et) {
        return super.batchChangePlan(et);
    }
    /**
     * [BatchChangeStage:批量变更阶段] 行为扩展：×，模板暂不支持批处理（参数）...
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story batchChangeStage(Story et) {
        return super.batchChangeStage(et);
    }
    /**
     * [BatchClose:批量关闭] 行为扩展：×，模板暂不支持批处理（参数）...
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story batchClose(Story et) {
        return super.batchClose(et);
    }
    /**
     * [BatchReview:批量评审] 行为扩展：×，模板暂不支持批处理（参数）...
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story batchReview(Story et) {
        return super.batchReview(et);
    }
    /**
     * [BatchUnlinkStory:计划批量解除关联需求] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story batchUnlinkStory(Story et) {
        return super.batchUnlinkStory(et);
    }
    /**
     * [BugToStory:bug转需求] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story bugToStory(Story et) {
        return super.bugToStory(et);
    }
    /**
     * [BuildBatchUnlinkStory:版本批量解除关联需求] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story buildBatchUnlinkStory(Story et) {
        return super.buildBatchUnlinkStory(et);
    }
    /**
     * [BuildLinkStory:项目关联需求] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story buildLinkStory(Story et) {
        if (et.get(StaticDict.Action__object_type.BUILD.getValue()) == null || et.get("srfactionparam") == null) {
            return et;
        }
        Build build = new Build();
        build.setId(Long.parseLong(et.get(StaticDict.Action__object_type.BUILD.getValue()).toString()));
        build.set("stories", getStories(et));
        iBuildService.linkStory(build);
        return et;
    }
    /**
     * [BuildUnlinkStory:版本解除关联需求] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story buildUnlinkStory(Story et) {
        if (et.get(StaticDict.Action__object_type.BUILD.getValue()) == null) {
            return et;
        }
        Build build = new Build();
        build.setId(Long.parseLong(et.get(StaticDict.Action__object_type.BUILD.getValue()).toString()));
        build.set("stories", et.getId());
        iBuildService.unlinkStory(build);
        return et;
    }
    /**
     * [Change:变更] 行为扩展：需求变更
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story change(Story et) {
        String comment = et.getComment() == null ? "" : et.getComment();
        Story old = new Story();
        CachedBeanCopier.copy(this.get(et.getId()), old);
        StorySpec oldStorySpec = iStorySpecService.getOne(new QueryWrapper<StorySpec>().eq(StaticDict.Action__object_type.STORY.getValue(), old.getId()).eq("version", old.getVersion()));
        if (oldStorySpec != null) {
            old.setSpec(oldStorySpec.getSpec());
            old.setVerify(oldStorySpec.getVerify());
        }


        if (!old.getTitle().equals(et.getTitle()) || ((StringUtils.isBlank(et.getSpec()) && !StringUtils.isBlank(old.getSpec())) || (!StringUtils.isBlank(et.getSpec()) && !et.getSpec().equals(old.getSpec()))) || ((StringUtils.isBlank(et.getVerify()) && !StringUtils.isBlank(old.getVerify())) || (!StringUtils.isBlank(et.getVerify()) && !et.getVerify().equals(old.getVerify())))) {
            et.setVersion(oldStorySpec != null ? oldStorySpec.getVersion() + 1 : et.getVersion() + 1);

            //相关 spec 处理
            oldStorySpec.setStory(et.getId());
            oldStorySpec.setTitle(et.getTitle());
            oldStorySpec.setVerify(et.getVerify());
            oldStorySpec.setSpec(et.getSpec());
            oldStorySpec.setVersion(et.getVersion());
            oldStorySpec.setId(null);
            iStorySpecService.create(oldStorySpec);
            if ((et.getNeednotreview() == null || "".equals(et.getNeednotreview())) && StaticDict.Story__status.ACTIVE.getValue().equals(et.getStatus())) {
                et.setStatus(StaticDict.Story__status.CHANGED.getValue());
            }
        }

        //
        et.setAssignedto((et.getAssignedto() == null || "".equals(et.getAssignedto())) ? old.getAssignedto() : et.getAssignedto());

        String files = et.getFiles();
        String noticeusers = et.getNoticeusers();
        this.sysUpdate(et);
        FileHelper.updateObjectID(et.getId(), StaticDict.File__object_type.STORY.getValue(), files, String.valueOf(et.getVersion()), iFileService);
        et.setTitle(oldStorySpec.getTitle());
        et.setSpec(oldStorySpec.getSpec());
        et.setVerify(oldStorySpec.getVerify());
        List<History> changes = ChangeUtil.diff(old, et, ignores, null, new String[]{"title", "spec", "verify"});

        if (StringUtils.isNotBlank(comment) || changes.size() > 0) {
            String strAction = changes.size() > 0 ? StaticDict.Action__type.CHANGED.getValue() : StaticDict.Action__type.COMMENTED.getValue();
            String strActionText = changes.size() > 0 ? StaticDict.Action__type.CHANGED.getText() : StaticDict.Action__type.COMMENTED.getText();

            ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.STORY.getValue(), changes,  strAction, comment, String.valueOf(et.getParent()), null, iActionService);

            if (!et.getStatus().equals(StaticDict.Story__status.CLOSED.getValue()) && et.getReviewedby().equals(old.getReviewedby())){
                ActionHelper.sendMarkDone(et.getId(),et.getTitle(),old.getAssignedto(), "需求", StaticDict.Action__object_type.STORY.getValue(), "stories", StaticDict.Action__type.CHANGED.getText(), iActionService);
                // 发送待办消息
                ActionHelper.sendTodoOrToread(et.getId(), et.getTitle(), noticeusers, et.getAssignedto(), et.getMailto(), "需求", StaticDict.Action__object_type.STORY.getValue(), "stories", strActionText, true, iActionService);
            }
        }
        return et;
    }
    /**
     * [Close:关闭] 行为扩展：需求关闭
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story close(Story et) {
        String comment = et.getComment() == null ? "" : et.getComment();
        Story old = new Story();
        CachedBeanCopier.copy(this.get(et.getId()), old);

        et.setCloseddate(ZTDateUtil.nul());
        et.setClosedby(AuthenticationUser.getAuthenticationUser().getUsername());
        et.setStatus(StaticDict.Story__status.CLOSED.getValue());
        et.setStage(StaticDict.Story__stage.CLOSED.getValue());
        et.setAssigneddate(ZTDateUtil.now());
        et.setAssignedto(StaticDict.Assignedto_closed.CLOSED.getValue());
        String noticeusers = et.getNoticeusers();
        this.sysUpdate(et);
        if (et.getParent() > 0) {
            updateParentStatus(et, this.get(et.getParent()), false);
        }
        List<History> changes = ChangeUtil.diff(old, et, null, new String[]{"status", "stage", "assignedto", "closedby", "closedreason", "closeddate"}, null);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {
            ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.STORY.getValue(), changes,  StaticDict.Action__type.CLOSED.getValue(), comment, et.getClosedreason(), null, iActionService);
            ActionHelper.sendTodoOrToread(et.getId(), et.getTitle(), noticeusers, et.getAssignedto(), et.getMailto(), "需求", StaticDict.Action__object_type.STORY.getValue(), "stories", StaticDict.Action__type.CLOSED.getText(), true, iActionService);

        }
        return et;
    }
    /**
     * [CreateTasks:生成任务] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story createTasks(Story et) {
        return super.createTasks(et);
    }

    @Override
    public Story get(Long key) {
        if(key == null || key == 0L) {
            Story story = new Story();
            story.setId(key);
            story.setOrgid(AuthenticationUser.getAuthenticationUser().getOrgid());
            return story;
        }
        Story et = getById(key);
        if (et == null) {
            throw new BadRequestAlertException("数据不存在", this.getClass().getSimpleName(), String.valueOf(key));
        }
        else {
            et.setStoryspecs(null);
            et.setStorystages(null);
            this.getStorySpec(et);
        }
        return et;
    }

    /**
     * [GetStorySpec:获取需求描述] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story getStorySpec(Story et) {
        StorySpecSearchContext context = new StorySpecSearchContext();
        context.setN_story_eq(et.getId());
        context.setN_version_eq(et.getVersion());
        context.setSort("version,desc");
        List<StorySpec> list = storyspecService.searchDefault(context).getContent();
        if (!list.isEmpty() && list.size() > 0) {
            StorySpec storySpec = list.get(0);
            et.setSpec(storySpec.getSpec());
            et.setVerify(storySpec.getVerify());
            et.setTitle(storySpec.getTitle());
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
    public Story importPlanStories(Story et) {
        Project project = new Project();
        project.setId(et.getProject());
        project.setPlans(et.getPlan());
        iProjectService.importPlanStories(project);
        return et;
    }
    /**
     * [LinkStory:计划关联需求] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story linkStory(Story et) {
        ProductPlan productPlan = new ProductPlan();
        productPlan.setProduct(et.getProduct());
        productPlan.setId(Long.parseLong(et.get(StaticDict.Action__object_type.PRODUCTPLAN.getValue()).toString()));
        productPlan.set("stories", getStories(et));

        iProductPlanService.linkStory(productPlan);
        return et;
    }

    public String getStories(Story et){
        String stories = "";
        ArrayList<Map> list = (ArrayList) et.get("srfactionparam");
        for (Map data : list) {
            if (stories.length() > 0) {
                stories += ",";
            }
            stories += data.get("id");
        }
        return stories;
    }
    /**
     * [ProjectBatchUnlinkStory:项目批量解除关联需求] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story projectBatchUnlinkStory(Story et) {
        return super.projectBatchUnlinkStory(et);
    }
    /**
     * [ProjectLinkStory:项目关联需求] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story projectLinkStory(Story et) {
        Project project = new Project();
        project.setId(et.getProject());
        project.set("stories", getStories(et));
        iProjectService.linkStory(project);
        return et;
    }
    /**
     * [ProjectUnlinkStory:项目解除关联需求] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story projectUnlinkStory(Story et) {
        Project project = new Project();
        project.setId(et.getProject());
        project.set(StaticDict.Action__object_type.STORY.getValue(), et.getId());
        iProjectService.unlinkStory(project);
        return et;
    }
    /**
     * [Push:推送] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story push(Story et) {
        return super.push(et);
    }
    /**
     * [ReleaseBatchUnlinkStory:发布批量解除关联需求] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story releaseBatchUnlinkStory(Story et) {
        return super.releaseBatchUnlinkStory(et);
    }
    /**
     * [ReleaseLinkStory:发布关联需求] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story releaseLinkStory(Story et) {
        if (et.get(StaticDict.Action__object_type.RELEASE.getValue()) == null) {
            return et;
        }
        Release release = new Release();
        release.setId(Long.parseLong(et.get(StaticDict.Action__object_type.RELEASE.getValue()).toString()));
        release.set("srfactionparam", et.get("srfactionparam"));
        iReleaseService.linkStory(release);
        return et;
    }
    /**
     * [ReleaseUnlinkStory:发布解除关联需求] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story releaseUnlinkStory(Story et) {
        if (et.getId() == null && et.get(StaticDict.Action__object_type.RELEASE.getValue()) == null) {
            return et;
        }
        Release release = iReleaseService.get(Long.parseLong(et.get(StaticDict.Action__object_type.RELEASE.getValue()).toString()));
        Release releaseUpdate = new Release();
        releaseUpdate.setId(release.getId());
        String stories = release.getStories();
        stories = ("," + stories + ",").replace("," + String.valueOf(et.getId()) + ",", ",");
        String regex = "^,*|,*$";
        stories = stories.replaceAll(regex, "");
        releaseUpdate.setStories(stories);
        iReleaseService.sysUpdate(releaseUpdate);
        ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.STORY.getValue(), null,  StaticDict.Action__type.UNLINKEDFROMRELEASE.getValue(), "", et.get(StaticDict.Action__object_type.RELEASE.getValue()).toString(), null, iActionService);

        this.setStage(et);
        return et;
    }
    /**
     * [ResetReviewedBy:重置由谁评审] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story resetReviewedBy(Story et) {
        return super.resetReviewedBy(et);
    }
    /**
     * [Review:评审] 行为扩展：需求评审
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story review(Story et) {
        String comment = et.getComment() == null ? "" : et.getComment();
        String result = et.getResult() == null ? et.getAssessresult() : et.getResult();
        Story old = new Story();
        CachedBeanCopier.copy(this.get(et.getId()), old);

        if (et.getRevieweddate() == null) {
            et.setRevieweddate(ZTDateUtil.now());
        }
        if (StringUtils.compare(result, StaticDict.Story__review_result.PASS.getValue()) == 0) {
            if (StringUtils.compare(old.getStatus(), StaticDict.Story__status.DRAFT.getValue()) == 0 || StringUtils.compare(old.getStatus(), StaticDict.Story__status.CHANGED.getValue()) == 0) {
                et.setStatus(StaticDict.Story__status.ACTIVE.getValue());
            }
//        } else if (StringUtils.compare(result, StaticDict.Story__review_result.REJECT.getValue()) == 0) {
//            et.setCloseddate(ZTDateUtil.nul());
//            et.setClosedby(AuthenticationUser.getAuthenticationUser().getUsername());
//            et.setStatus(StaticDict.Story__status.CLOSED.getValue());
//            et.setStage(StaticDict.Story__stage.CLOSED.getValue());
//            et.setAssignedto(StaticDict.Assignedto_closed.CLOSED.getValue());
//        }
        }else if (StringUtils.compare(result, StaticDict.Story__review_result.REVERT.getValue()) == 0) {
            et.setStatus(StaticDict.Story__status.ACTIVE.getValue());
            StorySpec oldStorySpec = iStorySpecService.getOne(new QueryWrapper<StorySpec>().eq(StaticDict.Action__object_type.STORY.getValue(), old.getId()).eq("version", et.getPreversion()));
            et.setTitle(oldStorySpec.getTitle());
            et.setVersion(oldStorySpec.getVersion());
            // List<StorySpec> list = iStorySpecService.list(new QueryWrapper<StorySpec>().eq(StaticDict.Action__object_type.STORY.getValue(), old.getId()).gt("version", et.getPreversion()));
            iStorySpecService.remove(new QueryWrapper<StorySpec>().eq(StaticDict.Action__object_type.STORY.getValue(), old.getId()).gt("version", et.getPreversion()));
        }
        String noticeusers = et.getNoticeusers();
        this.sysUpdate(et);

        List<History> changes = ChangeUtil.diff(old, et, new String[]{"lasteditedby", "lastediteddate"});

        ActionHelper.sendMarkDone(et.getId(),et.getTitle(),old.getAssignedto(), "需求", StaticDict.Action__object_type.STORY.getValue(), "stories", StaticDict.Action__type.REVIEWED.getText(), iActionService);
        // 发送待办消息
        ActionHelper.sendTodoOrToread(et.getId(), et.getTitle(), noticeusers, et.getAssignedto(), et.getMailto(), "需求", StaticDict.Action__object_type.STORY.getValue(), "stories", StaticDict.Action__type.REVIEWED.getText(), true, iActionService);
        if (changes.size() > 0) {
            ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.STORY.getValue(), changes,  StaticDict.Action__type.REVIEWED.getValue(), comment, result, null, iActionService);
//            if (StringUtils.compare(result, StaticDict.Story__review_result.REJECT.getValue()) == 0) {
//                ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.STORY.getValue(), null,  StaticDict.Action__type.CLOSED.getValue(), comment, et.getClosedreason(), null, iActionService);
//            }

        }
//        if (StaticDict.Story__review_result.REJECT.getValue().equals(result)) {
//            this.setStage(et);
//        }
        return et;
    }
    /**
     * [SendMessage:行为] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story sendMessage(Story et) {
        return super.sendMessage(et);
    }
    /**
     * [SendMsgPreProcess:发送消息前置处理] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story sendMsgPreProcess(Story et) {
        return super.sendMsgPreProcess(et);
    }
    /**
     * [SyncFromIbiz:同步Ibz平台实体] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story syncFromIbiz(Story et) {
        return super.syncFromIbiz(et);
    }
    /**
     * [UnlinkStory:计划解除关联需求] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story unlinkStory(Story et) {
        if (et.getPlan() == null && et.get(StaticDict.Action__object_type.PRODUCTPLAN.getValue()) == null) {
            return et;
        }

        ProductPlan productPlan = new ProductPlan();

        productPlan.setId(Long.parseLong(et.getPlan() == null ? et.get(StaticDict.Action__object_type.PRODUCTPLAN.getValue()).toString() : et.getPlan()));
        productPlan = iProductPlanService.get(productPlan.getId());
        productPlan.setOrder(productPlan.getOrder().replace(et.getId() + ",", ""));
        iProductPlanService.sysUpdate(productPlan);

        et.setPlan("");
        this.sysUpdate(et);

        ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.STORY.getValue(), null,  StaticDict.Action__type.UNLINKEDFROMPLAN.getValue(), "", et.getPlan(), null, iActionService);

        return et;
    }

    /**
     * 查询集合 数据查询
     */
    @Override
    public Page<Story> searchParentDefault(StorySearchContext context) {
        Page<Story> page = super.searchParentDefault(context);
        for (Story story : page.getContent()) {
            if (story.getParent() < 0) {
                StorySearchContext context1 = new StorySearchContext();
                context1.setSelectCond(context.getSelectCond().clone());
                context1.set("parent",story.getId());
                List<Story> storyList = this.searchChildMore(context1).getContent();
                story.set("items",storyList);
            }
        }
        return page;
    }

    @Override
    public Page<Story> searchProjectStories(StorySearchContext context) {
        Map<String, Object> params = context.getParams();
        if(params.get("srfparentkey") == null && params.get("project") != null) {
            context.set("srfparentkey", params.get("project"));
        }
        if(params.get("module") != null && !"0".equals(params.get("module"))) {
            context.setN_module_eq(Long.parseLong(params.get("module").toString()));
        }
        return super.searchProjectStories(context);
    }
}

