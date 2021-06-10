package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.util.ibizzentao.common.ZTDateUtil;
import cn.ibizlab.pms.core.zentao.domain.*;
import cn.ibizlab.pms.core.zentao.filter.BugSearchContext;
import cn.ibizlab.pms.core.zentao.service.*;
import cn.ibizlab.pms.core.zentao.service.impl.BugServiceImpl;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[Bug] 自定义服务对象
 */
@Slf4j
@Primary
@Service("BugExService")
public class BugExService extends BugServiceImpl {

    @Autowired
    IStoryService iStoryService;

    @Autowired
    ICaseService iCaseService;

    @Autowired
    IActionService iActionService;

    @Autowired
    IFileService iFileService;

    @Autowired
    IBuildService iBuildService;

    @Autowired
    ITestTaskService iTestTaskService;

    @Autowired
    IHistoryService iHistoryService;

    @Autowired
    IReleaseService iReleaseService;

    @Autowired
    IProductPlanService iProductPlanService;

    String[] diffAttrs = {"steps"};

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    @Override
    public Page<Bug> searchDefault(BugSearchContext context) {
        Map<String,Object> params = context.getParams();
        if(params.get("type") != null) {
            if("ReleaseLinkableResolvedBug".equals(params.get("type"))) {
                return super.searchReleaseLinkableResolvedBug(context);
            }else if("ReleaseBugs".equals(params.get("type"))){
                return super.searchReleaseBugs(context);
            }else if("ReleaseLeftBugs".equals(params.get("type"))){
                return super.searchReleaseLeftBugs(context);
            }else if("BuildBugs".equals(params.get("type"))){
                return super.searchBuildBugs(context);
            }else if("BuildOpenBugs".equals(params.get("type"))){
                return super.searchBuildOpenBugs(context);
            }else if("BuildOpenBugs".equals(params.get("type"))){
                return super.searchBuildOpenBugs(context);
            }
        }
        return super.searchDefault(context);
    }

    @Override
    public Bug sysGet(Long key) {
        if (key == 0){
            return null;
        }
        Bug bug = super.sysGet(key);
        String sql = "SELECT COUNT(1) as ISFAVOURITES from t_ibz_favorites t where t.OBJECTID = #{et.id} and t.TYPE = 'bug' and t.ACCOUNT = #{et.account}";
        Map<String,Object> param = new HashMap<>();
        param.put("id",bug.getId());
        param.put("account",AuthenticationUser.getAuthenticationUser().getLoginname());
        List<JSONObject> list = this.select(sql, param);
        if (list.size() > 0){
            bug.setIsfavorites(list.get(0).getString("ISFAVOURITES"));
        }
        return bug;

    }

    @Override
    public boolean create(Bug et) {
        et.setStoryversion(et.getStory() != null && et.getStory() != 0 ? iStoryService.get(et.getStory()).getVersion() : 1);
        et.setCaseversion(et.getIbizcase() != null && et.getIbizcase() != 0 ? iCaseService.get(et.getIbizcase()).getVersion() : 1);
        String files = et.getFiles();
        String noticeusers = et.getNoticeusers();
        if (!super.create(et)) {
            return false;
        }
        // 更新file
        FileHelper.updateObjectID(et.getId(),StaticDict.File__object_type.BUG.getValue(), files, "", iFileService);
        // 创建日志
        ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.BUG.getValue(), null,  StaticDict.Action__type.OPENED.getValue(), "","", null, iActionService);
        // 发送待办消息
        ActionHelper.sendTodoOrToread(et.getId(), et.getTitle(), noticeusers, et.getAssignedto(), et.getMailto(), "Bug", StaticDict.Action__object_type.BUG.getValue(), "bugs", StaticDict.Action__type.OPENED.getText(), true, iActionService);

        return true;
    }

    @Override
    public boolean update(Bug et) {
        Bug old = new Bug();
        CachedBeanCopier.copy(this.get(et.getId()), old);
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        String files = et.getFiles();
        String noticeusers = et.getNoticeusers();
        if(!super.update(et)) {
            return false;
        }
        // 更新file
        FileHelper.updateObjectID(et.getId(),StaticDict.File__object_type.BUG.getValue(), files, "", iFileService);

        List<History> changes = ChangeUtil.diff(old, et,null,null,diffAttrs);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {

            String strActionText = StaticDict.Action__type.EDITED.getText();
            String strAction = StaticDict.Action__type.EDITED.getValue();
            if (changes.size() == 0) {
                strAction = StaticDict.Action__type.COMMENTED.getValue();
                strActionText = StaticDict.Action__type.COMMENTED.getText();
            }
            // 发送待阅
            ActionHelper.sendTodoOrToread(et.getId(), et.getTitle(), noticeusers, et.getAssignedto(), et.getMailto(), "Bug", StaticDict.Action__object_type.BUG.getValue(), "bugs", strActionText, false, iActionService);
            // 创建日志
            ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.BUG.getValue(), changes,  strAction, comment,"", null, iActionService);
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
    public Bug activate(Bug et) {
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        Bug old = new Bug();
        CachedBeanCopier.copy(get(et.getId()), old);

        if(StringUtils.isBlank(et.getAssignedto()) || StringUtils.compare(et.getAssignedto(), StaticDict.Bug__status.CLOSED.getValue())==0) {
            et.setAssignedto(AuthenticationUser.getAuthenticationUser().getUsername());
        }
        et.setActivatedcount(old.getActivatedcount() + 1);
        et.setAssigneddate(ZTDateUtil.now());
        et.setActivateddate(ZTDateUtil.now());
        et.setClosedby("");
        et.setCloseddate(null);
        et.setStatus(StaticDict.Bug__status.ACTIVE.getValue());
        et.setResolution("");
        et.setResolvedby("");
        et.setResolveddate(null);
        et.setResolvedbuild("0");
        et.setDuplicatebug(0L);
        et.setTotask(0L);
        et.setTostory(0L);

        String files = et.getFiles();
        String noticeusers = et.getNoticeusers();

        super.update(et);
        // 更新file
        FileHelper.updateObjectID(et.getId(),StaticDict.File__object_type.BUG.getValue(), files, "", iFileService);

        // 发送待办消息
        ActionHelper.sendTodoOrToread(et.getId(), et.getTitle(), noticeusers, et.getAssignedto(), et.getMailto(), "Bug", StaticDict.Action__object_type.BUG.getValue(), "bugs", StaticDict.Action__type.ACTIVATED.getText(), true, iActionService);

        List<History> changes = ChangeUtil.diff(old, et,null,null,diffAttrs);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {
            // 创建日志
            ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.BUG.getValue(), changes,   StaticDict.Action__type.ACTIVATED.getValue(), comment,"", null, iActionService);
        }
        return et;
    }
    /**
     * [AssignTo:指派] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Bug assignTo(Bug et) {
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        Bug old = new Bug();
        CachedBeanCopier.copy(get(et.getId()), old);
        if(StringUtils.isBlank(et.getAssignedto())) {
            et.setAssignedto(AuthenticationUser.getAuthenticationUser().getUsername());
        }
        String files = et.getFiles();
        String noticeusers = et.getNoticeusers();
        super.update(et);

        // 更新file
        FileHelper.updateObjectID(et.getId(),StaticDict.File__object_type.BUG.getValue(), files, "", iFileService);
        List<History> changes = ChangeUtil.diff(old, et);
        if (!et.getAssignedto().equals(old.getAssignedto())){
            // 已读
            ActionHelper.sendMarkDone(et.getId(),et.getTitle(),old.getAssignedto(), "Bug", StaticDict.Action__object_type.BUG.getValue(), "bugs", StaticDict.Action__type.ASSIGNED.getText(), iActionService);
            // 发送待办消息
            ActionHelper.sendTodoOrToread(et.getId(), et.getTitle(), noticeusers, et.getAssignedto(), et.getMailto(), "Bug", StaticDict.Action__object_type.BUG.getValue(), "bugs", StaticDict.Action__type.ASSIGNED.getText(), true, iActionService);

        }
        // 创建日志
        ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.BUG.getValue(), changes,  StaticDict.Action__type.ASSIGNED.getValue(), comment,et.getAssignedto(), null, iActionService);
        return et;
    }
    /**
     * [BatchUnlinkBug:批量解除关联Bug] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Bug batchUnlinkBug(Bug et) {
        return super.batchUnlinkBug(et);
    }
    /**
     * [BuildBatchUnlinkBug:版本批量解除关联Bug] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Bug buildBatchUnlinkBug(Bug et) {
        return super.buildBatchUnlinkBug(et);
    }
    /**
     * [BuildLinkBug:版本关联Bug] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Bug buildLinkBug(Bug et) {
        //build
        if (et.get("builds") == null) {
            return et;
        }
        if (et.get("ids") == null) {
            return et;
        }
        if("trunk".equals(et.get("builds").toString().split(",")[0])) {
            return et;
        }

        Build build = new Build();
        build.setId(Long.parseLong(et.get("builds").toString().split(",")[0]));
        build.set("bugs", et.get("ids"));
        build.set("resolvedby", et.getResolvedby());
        iBuildService.linkBug(build);
        return et;
    }
    /**
     * [BuildUnlinkBug:版本解除关联Bug] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Bug buildUnlinkBug(Bug et) {
        //build
        if (et.get(StaticDict.Action__object_type.BUILD.getValue()) == null) {
            return et;
        }
        Build build = new Build();
        build.setId(Long.parseLong(et.get(StaticDict.Action__object_type.BUILD.getValue()).toString()));
        build.set("bugs", et.getId());
        iBuildService.unlinkBug(build);
        return et;
    }
    /**
     * [Close:关闭] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Bug close(Bug et) {
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        Bug old = new Bug();
        CachedBeanCopier.copy(get(et.getId()), old);

        et.setAssignedto(StaticDict.Bug__status.CLOSED.getValue());
        et.setAssigneddate(ZTDateUtil.now());
        et.setClosedby(AuthenticationUser.getAuthenticationUser().getUsername());
        et.setCloseddate(ZTDateUtil.now());
        et.setStatus(StaticDict.Bug__status.CLOSED.getValue());
        et.setConfirmed(1);
        String noticeusers = et.getNoticeusers();
        super.update(et);
        // 发送待办消息
        ActionHelper.sendTodoOrToread(et.getId(), et.getTitle(), noticeusers, et.getAssignedto(), et.getMailto(), "Bug", StaticDict.Action__object_type.BUG.getValue(), "bugs", StaticDict.Action__type.CLOSED.getText(), false, iActionService);

        List<History> changes = ChangeUtil.diff(old, et);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {
            // 创建日志
            ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.BUG.getValue(), changes,  StaticDict.Action__type.CLOSED.getText(), comment,"", null, iActionService);
        }
        return et;
    }
    /**
     * [Confirm:确认] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Bug confirm(Bug et) {
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        Bug old = new Bug();
        CachedBeanCopier.copy(get(et.getId()), old);

        et.setConfirmed(1);
        if (StringUtils.isBlank(et.getAssignedto())) {
            et.setAssignedto(AuthenticationUser.getAuthenticationUser().getUsername());
        }
        et.setAssigneddate(ZTDateUtil.now());
        String noticeusers = et.getNoticeusers();
        super.update(et);
        ActionHelper.sendTodoOrToread(et.getId(), et.getTitle(), noticeusers, et.getAssignedto(), et.getMailto(), "Bug", StaticDict.Action__object_type.BUG.getValue(), "bugs", StaticDict.Action__type.BUGCONFIRMED.getText(), false, iActionService);

        List<History> changes = ChangeUtil.diff(old, et, null, new String[]{"confirmed", "assignedto"}, null);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {
            if (!et.getAssignedto().equals(old.getAssignedto())){
                // 已读
                ActionHelper.sendMarkDone(et.getId(),et.getTitle(),old.getAssignedto(), "Bug", StaticDict.Action__object_type.BUG.getValue(), "bugs", StaticDict.Action__type.BUGCONFIRMED.getText(), iActionService);

                // 发送待办消息
                ActionHelper.sendTodoOrToread(et.getId(), et.getTitle(), noticeusers, et.getAssignedto(), et.getMailto(), "Bug", StaticDict.Action__object_type.BUG.getValue(), "bugs", StaticDict.Action__type.BUGCONFIRMED.getText(), true, iActionService);

            }
            // 创建日志
            ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.BUG.getValue(), changes,   StaticDict.Action__type.BUGCONFIRMED.getValue(), comment,"", null, iActionService);
        }
        return et;
    }
    /**
     * [LinkBug:关联Bug] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Bug linkBug(Bug et) {
        //release
        if (et.get(StaticDict.Action__object_type.RELEASE.getValue()) != null) {

            Release release = iReleaseService.get(Long.parseLong(et.get(StaticDict.Action__object_type.RELEASE.getValue()).toString()));

            if (release != null) {
                if (StringUtils.isBlank(release.getBugs())) {
                    release.setBugs(String.valueOf(et.getId()));
                    iReleaseService.update(release);
                } else {
                    if (!("," + release.getBugs()).contains("," + et.getId())) {
                        release.setBugs(release.getBugs() + "," + et.getId());
                        iReleaseService.update(release);
                        ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.BUG.getValue(), null,   StaticDict.Action__type.LINKED2RELEASE.getValue(), "",String.valueOf(release.getId()), null, iActionService);
                    }
                }
            }
        }

        if (et.get(StaticDict.Action__object_type.PRODUCTPLAN.getValue()) != null) {
            ProductPlan productPlan = new ProductPlan();
            productPlan.setId(Long.parseLong(et.get(StaticDict.Action__object_type.PRODUCTPLAN.getValue()).toString()));
            String bugs = "";
            ArrayList<Map> list = (ArrayList) et.get("srfactionparam");
            for (Map data : list) {
                if (bugs.length() > 0) {
                    bugs += ",";
                }
                bugs += data.get("id");
            }
            productPlan.set("bugs",bugs);
            productPlan.set("srfactionparam",et.get("srfactionparam"));
            iProductPlanService.linkBug(productPlan);
        }
        return et;
    }
    /**
     * [ReleaaseBatchUnlinkBug:批量解除关联Bug] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Bug releaaseBatchUnlinkBug(Bug et) {
        return super.releaaseBatchUnlinkBug(et);
    }
    /**
     * [ReleaseLinkBugbyBug:关联Bug（解决Bug）] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Bug releaseLinkBugbyBug(Bug et) {
        if(et.get(StaticDict.Action__object_type.RELEASE.getValue()) == null) {
            return et;
        }
        Release release = new Release();
        release.setId(Long.parseLong(et.get(StaticDict.Action__object_type.RELEASE.getValue()).toString()));
        release.set("srfactionparam",et.get("srfactionparam"));
        iReleaseService.linkBug(release);
        return et;
    }
    /**
     * [ReleaseLinkBugbyLeftBug:关联Bug（遗留Bug）] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Bug releaseLinkBugbyLeftBug(Bug et) {
        if(et.get(StaticDict.Action__object_type.RELEASE.getValue()) == null) {
            return et;
        }
        Release release = new Release();
        release.setId(Long.parseLong(et.get(StaticDict.Action__object_type.RELEASE.getValue()).toString()));
        release.set("srfactionparam",et.get("srfactionparam"));
        iReleaseService.linkBugbyLeftBug(release);
        return et;
    }
    /**
     * [ReleaseUnLinkBugbyLeftBug:移除关联Bug（遗留Bug）] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Bug releaseUnLinkBugbyLeftBug(Bug et) {
        if(et.getId() == null &&  et.get(StaticDict.Action__object_type.RELEASE.getValue()) == null) {
            return et;
        }
        Release release = iReleaseService.get(Long.parseLong(et.get(StaticDict.Action__object_type.RELEASE.getValue()).toString()));
        Release releaseUpdate = new Release();
        releaseUpdate.setId(release.getId());
        String leftbugs = release.getLeftbugs();
        // String bugs = release.getBugs();
        leftbugs = ("," + leftbugs + ",").replace("," + String.valueOf(et.getId()) + ",", ",");
        String regex = "^,*|,*$";
        leftbugs = leftbugs.replaceAll(regex, "");
        releaseUpdate.setLeftbugs(leftbugs);
        iReleaseService.update(releaseUpdate);
        ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.BUG.getValue(), null,   StaticDict.Action__type.UNLINKEDFROMRELEASE.getValue(), "",String.valueOf(release.getId()), AuthenticationUser.getAuthenticationUser().getUsername(), iActionService);
        return et;
    }
    /**
     * [ReleaseUnlinkBug:解除关联Bug] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Bug releaseUnlinkBug(Bug et) {
        if(et.getId() == null &&  et.get(StaticDict.Action__object_type.RELEASE.getValue()) == null) {
            return et;
        }
        Release release = iReleaseService.get(Long.parseLong(et.get(StaticDict.Action__object_type.RELEASE.getValue()).toString()));
        Release releaseUpdate = new Release();
        releaseUpdate.setId(release.getId());
        // String leftbugs = release.getLeftbugs();
        String bugs = release.getBugs();
        bugs = ("," + bugs + ",").replace("," + String.valueOf(et.getId()) + ",", ",");
        String regex = "^,*|,*$";
        bugs = bugs.replaceAll(regex, "");
        releaseUpdate.setBugs(bugs);
        iReleaseService.update(releaseUpdate);
        ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.BUG.getValue(), null,   StaticDict.Action__type.UNLINKEDFROMRELEASE.getValue(), "",String.valueOf(release.getId()), AuthenticationUser.getAuthenticationUser().getUsername(), iActionService);
        return et;
    }
    /**
     * [Resolve:解决] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Bug resolve(Bug et) {
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        Bug old = new Bug();
        CachedBeanCopier.copy(get(et.getId()), old);

        et.setConfirmed(1);
        et.setStatus(StaticDict.Bug__status.RESOLVED.getValue());
        et.setResolvedby(AuthenticationUser.getAuthenticationUser().getUsername());
        et.setResolveddate(ZTDateUtil.now());

        //创建版本
        if (et.getCreatebuild() != null && et.getCreatebuild() == 1) {
            Build build = new Build();
            build.setProject(Long.parseLong(et.getBuildproject()));
            build.setName(et.getBuildname());
            build.setDate(ZTDateUtil.now());
            build.setProduct(et.getProduct());
            build.setBuilder(AuthenticationUser.getAuthenticationUser().getUsername());
            iBuildService.create(build);
            et.setResolvedbuild(String.valueOf(build.getId()));
        }

        if (StringUtils.isNotBlank(et.getResolvedbuild()) && StringUtils.compare(et.getResolvedbuild(), "trunk") != 0) {
            TestTask testTask = iTestTaskService.getOne(new QueryWrapper<TestTask>().eq(StaticDict.Action__object_type.BUILD.getValue(), et.getResolvedbuild()).orderByDesc("`id`").last("limit 0,1"));
            if (testTask != null) {
                et.setTesttask(testTask.getId());
            }
        }

        String files = et.getFiles();
        String noticeusers = et.getNoticeusers();
        super.update(et);
        ActionHelper.sendTodoOrToread(et.getId(), et.getTitle(), noticeusers, et.getAssignedto(), et.getMailto(), "Bug", StaticDict.Action__object_type.BUG.getValue(), "bugs", StaticDict.Action__type.RESOLVED.getText(), true, iActionService);
        FileHelper.updateObjectID(et.getId(),StaticDict.File__object_type.BUG.getValue(), files, "", iFileService);
        //关联
        et.set("builds", et.getResolvedbuild());
        et.set("ids", et.getId());
        buildLinkBug(et);
        //release关联
        Release release = iReleaseService.getOne(new QueryWrapper<Release>().eq(StaticDict.Action__object_type.BUILD.getValue(), et.getResolvedbuild()).eq(StaticDict.Action__object_type.PRODUCT.getValue(), et.getProduct()).ne(StaticDict.Action__object_type.BUILD.getValue(), 0).last(" LIMIT 0,1 "));
        if (release != null) {
            et.set(StaticDict.Action__object_type.RELEASE.getValue(), release.getId());
            linkBug(et);
        }
        List<History> changes = ChangeUtil.diff(old, et);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {

            if (!et.getAssignedto().equals(old.getAssignedto())){
                // 已读
                ActionHelper.sendMarkDone(et.getId(),et.getTitle(),old.getAssignedto(), "Bug", StaticDict.Action__object_type.BUG.getValue(), "bugs", StaticDict.Action__type.RESOLVED.getText(), iActionService);
                // 发送待办消息
                ActionHelper.sendTodoOrToread(et.getId(), et.getTitle(), noticeusers, et.getAssignedto(), et.getMailto(), "Bug", StaticDict.Action__object_type.BUG.getValue(), "bugs", StaticDict.Action__type.RESOLVED.getText(), true, iActionService);
            }
            // 创建日志
            ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.BUG.getValue(), changes,   StaticDict.Action__type.RESOLVED.getValue(), comment,et.getResolution(), null, iActionService);
        }
        return et;
    }
    /**
     * [SendMessage:行为] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Bug sendMessage(Bug et) {
        return super.sendMessage(et);
    }
    /**
     * [SendMsgPreProcess:发送消息前置处理] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Bug sendMsgPreProcess(Bug et) {
        return super.sendMsgPreProcess(et);
    }
    /**
     * [ToStory:转需求] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Bug toStory(Bug et) {
        return super.toStory(et);
    }
    /**
     * [UnlinkBug:解除关联Bug] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Bug unlinkBug(Bug et) {
        //release
        if (et.get(StaticDict.Action__object_type.RELEASE.getValue()) != null) {
            Release release = iReleaseService.get(Long.parseLong(et.get(StaticDict.Action__object_type.RELEASE.getValue()).toString()));

            if (("," + release.getBugs()).contains("," + et.getId())) {
                release.setBugs((";" + release.getBugs()).replace("," + et.getId(), ""));
                iReleaseService.update(release);
                ActionHelper.createHis(et.getId(), StaticDict.Action__object_type.BUG.getValue(), null,   StaticDict.Action__type.RESOLVED.getValue(), "",String.valueOf(release.getId()), null, iActionService);
            }
        }

        if (et.get(StaticDict.Action__object_type.PRODUCTPLAN.getValue()) != null) {
            ProductPlan productPlan = new ProductPlan();
            productPlan.setId(Long.parseLong(et.get(StaticDict.Action__object_type.PRODUCTPLAN.getValue()).toString()));
            productPlan.set("bugs",et.getId());
            iProductPlanService.unlinkBug(productPlan);
        }
        return et;
    }
}

