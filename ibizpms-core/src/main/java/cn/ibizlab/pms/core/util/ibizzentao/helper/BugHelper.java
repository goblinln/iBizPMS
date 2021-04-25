package cn.ibizlab.pms.core.util.ibizzentao.helper;

import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.util.ibizzentao.common.ZTDateUtil;
import cn.ibizlab.pms.core.zentao.domain.*;
import cn.ibizlab.pms.core.zentao.mapper.BugMapper;
import cn.ibizlab.pms.core.zentao.service.IBugService;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import cn.ibizlab.pms.util.security.SpringContextHolder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author chenxiang
 */
@Component
@Slf4j
public class BugHelper extends ZTBaseHelper<BugMapper, Bug> {

    @Autowired
    BuildHelper buildHelper;

    @Autowired
    ReleaseHelper releaseHelper;

    @Autowired
    TestTaskHelper testTaskHelper;

    @Autowired
    ActionHelper actionHelper;

    @Autowired
    FileHelper fileHelper;

    @Autowired
    StoryHelper storyHelper;

    @Autowired
    CaseHelper caseHelper;

    String[] diffAttrs = {"steps"};

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean create(Bug et) {
        et.setStoryversion(et.getStory() != null && et.getStory() != 0 ? storyHelper.get(et.getStory()).getVersion() : 1);
        et.setCaseversion(et.getIbizcase() != null && et.getIbizcase() != 0 ? caseHelper.get(et.getIbizcase()).getVersion() : 1);
        String files = et.getFiles();
        String noticeusers = et.getNoticeusers();
        if (!super.create(et)) {
            return false;
        }
        fileHelper.updateObjectID(et.getId(), StaticDict.File__object_type.BUG.getValue(), files, "");
        actionHelper.create(StaticDict.Action__object_type.BUG.getValue(), et.getId(), StaticDict.Action__type.OPENED.getValue(), "", "", null, true);
        actionHelper.sendTodo(et.getId(), et.getTitle(), noticeusers, et.getAssignedto(), et.getMailto(), "Bug", StaticDict.Action__object_type.BUG.getValue(), "bugs", StaticDict.Action__type.OPENED.getText());
        return true;
    }
    /**
     * edit 编辑
     *
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean edit(Bug et) {
        Bug old = new Bug();
        CachedBeanCopier.copy(this.get(et.getId()), old);

        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";

        fileHelper.processImgURL(et, null, null);
        String files = et.getFiles();
        String noticeusers = et.getNoticeusers();
        this.internalUpdate(et);
        fileHelper.updateObjectID(et.getId(), StaticDict.File__object_type.BUG.getValue(), files, "");
        List<History> changes = ChangeUtil.diff(old, et,null,null,diffAttrs);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {

            String strActionText = StaticDict.Action__type.EDITED.getText();
            String strAction = StaticDict.Action__type.EDITED.getValue();
            if (changes.size() == 0) {
                strAction = StaticDict.Action__type.COMMENTED.getValue();
                strActionText = StaticDict.Action__type.COMMENTED.getText();
            }
            actionHelper.sendToread(et.getId(), et.getTitle(), noticeusers, et.getAssignedto(), et.getMailto(), "Bug", StaticDict.Action__object_type.BUG.getValue(), "bugs", strActionText);
            Action action = actionHelper.create(StaticDict.Action__object_type.BUG.getValue(), et.getId(), strAction,
                    comment, "", null, true);
            if (changes.size() > 0) {
                actionHelper.logHistory(action.getId(), changes);
            }
        }
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public Bug assignTo(Bug et) {
        String comment = StringUtils.isNotBlank(et.getComment()) ? et.getComment() : "";
        Bug old = new Bug();
        CachedBeanCopier.copy(get(et.getId()), old);
        if(StringUtils.isBlank(et.getAssignedto())) {
            et.setAssignedto(AuthenticationUser.getAuthenticationUser().getUsername());
        }
        String files = et.getFiles();
        String noticeusers = et.getNoticeusers();
        this.internalUpdate(et);
        fileHelper.updateObjectID(et.getId(), StaticDict.File__object_type.BUG.getValue(), files, "");
        List<History> changes = ChangeUtil.diff(old, et);
        if (!et.getAssignedto().equals(old.getAssignedto())){
            actionHelper.sendMarkDone(et.getId(),et.getTitle(),old.getAssignedto(), "Bug", StaticDict.Action__object_type.BUG.getValue(), "bugs", StaticDict.Action__type.ASSIGNED.getText());
            actionHelper.sendTodo(et.getId(), et.getTitle(), noticeusers,et.getAssignedto(), et.getMailto(), "Bug", StaticDict.Action__object_type.BUG.getValue(), "bugs", StaticDict.Action__type.ASSIGNED.getText());
        }
        Action action = actionHelper.create(StaticDict.Action__object_type.BUG.getValue(), et.getId(), StaticDict.Action__type.ASSIGNED.getValue(),
                comment, et.getAssignedto(), null, true);
        if (changes.size() > 0) {
            actionHelper.logHistory(action.getId(), changes);
        }
        return et;
    }

    @Transactional(rollbackFor = Exception.class)
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
        internalUpdate(et);
        fileHelper.updateObjectID(et.getId(), StaticDict.File__object_type.BUG.getValue(), files, "");
        actionHelper.sendTodo(et.getId(), et.getTitle(), noticeusers,et.getAssignedto(), et.getMailto(), "Bug", StaticDict.Action__object_type.BUG.getValue(), "bugs", StaticDict.Action__type.ACTIVATED.getText());
        List<History> changes = ChangeUtil.diff(old, et);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {
            Action action = actionHelper.create(StaticDict.Action__object_type.BUG.getValue(), et.getId(), StaticDict.Action__type.ACTIVATED.getValue(),
                    comment, "", null, true);
            if (changes.size() > 0) {
                actionHelper.logHistory(action.getId(), changes);
            }
        }
        return et;
    }

    @Transactional(rollbackFor = Exception.class)
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
        this.internalUpdate(et);
        actionHelper.sendToread(et.getId(), et.getTitle(), noticeusers,et.getAssignedto(), et.getMailto(), "Bug", StaticDict.Action__object_type.BUG.getValue(), "bugs", StaticDict.Action__type.BUGCONFIRMED.getText());
        List<History> changes = ChangeUtil.diff(old, et, null, new String[]{"confirmed", "assignedto"}, null);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {
            if (!et.getAssignedto().equals(old.getAssignedto())){
                actionHelper.sendMarkDone(et.getId(),et.getTitle(),old.getAssignedto(), "Bug", StaticDict.Action__object_type.BUG.getValue(), "bugs", StaticDict.Action__type.BUGCONFIRMED.getText());
                actionHelper.sendTodo(et.getId(), et.getTitle(), noticeusers,et.getAssignedto(), et.getMailto(), "Bug", StaticDict.Action__object_type.BUG.getValue(), "bugs", StaticDict.Action__type.BUGCONFIRMED.getText());
            }
            Action action = actionHelper.create(StaticDict.Action__object_type.BUG.getValue(), et.getId(), StaticDict.Action__type.BUGCONFIRMED.getValue(),
                    comment, "", null, true);
            if (changes.size() > 0) {
                actionHelper.logHistory(action.getId(), changes);
            }
        }

        return et;
    }

    @Transactional(rollbackFor = Exception.class)
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
            buildHelper.create(build);
            et.setResolvedbuild(String.valueOf(build.getId()));
        }

        if (StringUtils.isNotBlank(et.getResolvedbuild()) && StringUtils.compare(et.getResolvedbuild(), FIELD_TRUNK) != 0) {
            TestTask testTask = testTaskHelper.getOne(new QueryWrapper<TestTask>().eq(StaticDict.Action__object_type.BUILD.getValue(), et.getResolvedbuild()).orderByDesc("`id`").last("limit 0,1"));
            if (testTask != null) {
                et.setTesttask(testTask.getId());
            }
        }

        String files = et.getFiles();
        String noticeusers = et.getNoticeusers();
        internalUpdate(et);
        fileHelper.updateObjectID(et.getId(), StaticDict.File__object_type.BUG.getValue(), files, "");
        actionHelper.sendTodo(et.getId(), et.getTitle(), noticeusers,et.getAssignedto(), et.getMailto(), "Bug", StaticDict.Action__object_type.BUG.getValue(), "bugs", StaticDict.Action__type.RESOLVED.getText());
        //关联
        et.set(FIELD_BUILDS, et.getResolvedbuild());
        et.set(FIELD_IDS, et.getId());
        buildLinkBug(et);

        //release关联
        Release release = releaseHelper.getOne(new QueryWrapper<Release>().eq(StaticDict.Action__object_type.BUILD.getValue(), et.getResolvedbuild()).eq(StaticDict.Action__object_type.PRODUCT.getValue(), et.getProduct()).ne(StaticDict.Action__object_type.BUILD.getValue(), 0).last(" LIMIT 0,1 "));
        if (release != null) {
            et.set(StaticDict.Action__object_type.RELEASE.getValue(), release.getId());
            linkBug(et);
        }

        List<History> changes = ChangeUtil.diff(old, et);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {
            if (!et.getAssignedto().equals(old.getAssignedto())){
                actionHelper.sendMarkDone(et.getId(),et.getTitle(),old.getAssignedto(), "Bug", StaticDict.Action__object_type.BUG.getValue(), "bugs", StaticDict.Action__type.RESOLVED.getText());
                actionHelper.sendTodo(et.getId(), et.getTitle(), noticeusers,et.getAssignedto(), et.getMailto(), "Bug", StaticDict.Action__object_type.BUG.getValue(), "bugs", StaticDict.Action__type.RESOLVED.getText());
            }
            Action action = actionHelper.create(StaticDict.Action__object_type.BUG.getValue(), et.getId(), StaticDict.Action__type.RESOLVED.getValue(),
                    comment, et.getResolution(), null, true);
            if (changes.size() > 0) {
                actionHelper.logHistory(action.getId(), changes);
            }
        }

        return et;
    }

    @Transactional(rollbackFor = Exception.class)
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
        internalUpdate(et);
        actionHelper.sendToread(et.getId(), et.getTitle(), noticeusers, et.getAssignedto(), et.getMailto(), "Bug", StaticDict.Action__object_type.BUG.getValue(), "bugs", StaticDict.Action__type.CLOSED.getText());
        List<History> changes = ChangeUtil.diff(old, et);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {
            Action action = actionHelper.create(StaticDict.Action__object_type.BUG.getValue(), et.getId(), StaticDict.Action__type.CLOSED.getValue(),
                    comment, "", null, true);
            if (changes.size() > 0) {
                actionHelper.logHistory(action.getId(), changes);
            }
        }
        return et;
    }

    @Transactional(rollbackFor = Exception.class)
    public Bug linkBug(Bug et) {
        //release
        if (et.get(StaticDict.Action__object_type.RELEASE.getValue()) != null) {

            Release release = releaseHelper.get(Long.parseLong(et.get(StaticDict.Action__object_type.RELEASE.getValue()).toString()));

            if (release != null) {
                if (StringUtils.isBlank(release.getBugs())) {
                    release.setBugs(String.valueOf(et.getId()));
                    releaseHelper.internalUpdate(release);
                } else {
                    if (!(MULTIPLE_CHOICE + release.getBugs()).contains(MULTIPLE_CHOICE + et.getId())) {
                        release.setBugs(release.getBugs() + MULTIPLE_CHOICE + et.getId());
                        releaseHelper.internalUpdate(release);
                        actionHelper.create(StaticDict.Action__object_type.BUG.getValue(), et.getId(), StaticDict.Action__type.LINKED2RELEASE.getValue(),
                                "", String.valueOf(release.getId()), null, true);
                    }
                }
            }
        }

        if (et.get(StaticDict.Action__object_type.PRODUCTPLAN.getValue()) != null) {
            ProductPlan productPlan = new ProductPlan();
            productPlan.setId(Long.parseLong(et.get(StaticDict.Action__object_type.PRODUCTPLAN.getValue()).toString()));
            String bugs = "";
            ArrayList<Map> list = (ArrayList) et.get(FIELD_SRFACTIONPARAM);
            for (Map data : list) {
                if (bugs.length() > 0) {
                    bugs += MULTIPLE_CHOICE;
                }
                bugs += data.get(FIELD_ID);
            }
            productPlan.set(FIELD_BUGS,bugs);
            productPlan.set(FIELD_SRFACTIONPARAM,et.get(FIELD_SRFACTIONPARAM));
            SpringContextHolder.getBean(ProductPlanHelper.class).linkBug(productPlan);
        }
        return et;
    }

    @Transactional(rollbackFor = Exception.class)
    public Bug unlinkBug(Bug et) {
        //release
        if (et.get(StaticDict.Action__object_type.RELEASE.getValue()) != null) {
            Release release = releaseHelper.get(Long.parseLong(et.get(StaticDict.Action__object_type.RELEASE.getValue()).toString()));

            if ((MULTIPLE_CHOICE + release.getBugs()).contains(MULTIPLE_CHOICE + et.getId())) {
                release.setBugs((IN_CHOICE + release.getBugs()).replace(MULTIPLE_CHOICE + et.getId(), ""));
                releaseHelper.internalUpdate(release);
                actionHelper.create(StaticDict.Action__object_type.BUG.getValue(), et.getId(), StaticDict.Action__type.UNLINKEDFROMRELEASE.getValue(),
                        "", String.valueOf(release.getId()), null, true);
            }
        }

        if (et.get(StaticDict.Action__object_type.PRODUCTPLAN.getValue()) != null) {
            ProductPlan productPlan = new ProductPlan();
            productPlan.setId(Long.parseLong(et.get(StaticDict.Action__object_type.PRODUCTPLAN.getValue()).toString()));
            productPlan.set(FIELD_BUGS,et.getId());
            SpringContextHolder.getBean(ProductPlanHelper.class).unlinkBug(productPlan);


        }
        return et;
    }

    @Transactional(rollbackFor = Exception.class)
    public Bug batchUnlinkBug(Bug et) {
        throw new RuntimeException("未实现");
    }

    @Transactional(rollbackFor = Exception.class)
    public Bug buildLinkBug(Bug et) {
        //build
        if (et.get(FIELD_BUILDS) == null) {
            return et;
        }
        if (et.get(FIELD_IDS) == null) {
            return et;
        }
        if(FIELD_TRUNK.equals(et.get(FIELD_BUILDS).toString().split(MULTIPLE_CHOICE)[0])) {
            return et;
        }

        Build build = new Build();
        build.setId(Long.parseLong(et.get(FIELD_BUILDS).toString().split(MULTIPLE_CHOICE)[0]));
        build.set(FIELD_BUGS, et.get(FIELD_IDS));
        build.set(FIELD_RESOLVEDBY, et.getResolvedby());
        buildHelper.linkBug(build);

        return et;
    }

    @Transactional(rollbackFor = Exception.class)
    public Bug buildUnlinkBug(Bug et) {
        //build
        if (et.get(StaticDict.Action__object_type.BUILD.getValue()) == null) {
            return et;
        }
        Build build = new Build();
        build.setId(Long.parseLong(et.get(StaticDict.Action__object_type.BUILD.getValue()).toString()));
        build.set(FIELD_BUGS, et.getId());
        buildHelper.unlinkBug(build);
        return et;
    }

    @Transactional(rollbackFor = Exception.class)
    public Bug buildBatchUnlinkBug(Bug et) {
        throw new RuntimeException("未实现");
    }

    @Transactional(rollbackFor = Exception.class)
    public Bug releaseUnlinkBug(Bug et) {
        if(et.getId() == null &&  et.get(StaticDict.Action__object_type.RELEASE.getValue()) == null) {
            return et;
        }
        Release release = releaseHelper.get(Long.parseLong(et.get(StaticDict.Action__object_type.RELEASE.getValue()).toString()));
        Release releaseUpdate = new Release();
        releaseUpdate.setId(release.getId());
        String bugs = release.getBugs();
        bugs = (MULTIPLE_CHOICE + bugs + MULTIPLE_CHOICE).replace(MULTIPLE_CHOICE + String.valueOf(et.getId()) +MULTIPLE_CHOICE, MULTIPLE_CHOICE);
        String regex = "^,*|,*$";
        bugs = bugs.replaceAll(regex, "");
        releaseUpdate.setBugs(bugs);
        releaseHelper.internalUpdate(releaseUpdate);
        actionHelper.create(StaticDict.Action__object_type.BUG.getValue(), et.getId(), StaticDict.Action__type.UNLINKEDFROMRELEASE.getValue(),
                "",
                et.get(StaticDict.Action__object_type.RELEASE.getValue()).toString(),
                AuthenticationUser.getAuthenticationUser().getUsername(),
                false);

        return et;
    }

    @Transactional(rollbackFor = Exception.class)
    public Bug releaseUnLinkBugbyLeftBug(Bug et) {
        if(et.getId() == null &&  et.get(StaticDict.Action__object_type.RELEASE.getValue()) == null) {
            return et;
        }
        Release release = releaseHelper.get(Long.parseLong(et.get(StaticDict.Action__object_type.RELEASE.getValue()).toString()));
        Release releaseUpdate = new Release();
        releaseUpdate.setId(release.getId());
        String leftbugs = release.getLeftbugs();
        leftbugs = (MULTIPLE_CHOICE + leftbugs + MULTIPLE_CHOICE).replace(MULTIPLE_CHOICE + String.valueOf(et.getId()) + MULTIPLE_CHOICE, MULTIPLE_CHOICE);
        String regex = "^,*|,*$";
        leftbugs = leftbugs.replaceAll(regex, "");
        releaseUpdate.setLeftbugs(leftbugs);
        releaseHelper.internalUpdate(releaseUpdate);

        actionHelper.create(StaticDict.Action__object_type.BUG.getValue(), et.getId(), StaticDict.Action__type.UNLINKEDFROMRELEASE.getValue(),
                "",
                et.get(StaticDict.Action__object_type.RELEASE.getValue()).toString(),
                AuthenticationUser.getAuthenticationUser().getUsername(),
                false);

        return et;
    }

    @Transactional(rollbackFor = Exception.class)
    public Bug releaseLinkBugbyBug(Bug et) {
        if(et.get(StaticDict.Action__object_type.RELEASE.getValue()) == null) {
            return et;
        }
        Release release = new Release();
        release.setId(Long.parseLong(et.get(StaticDict.Action__object_type.RELEASE.getValue()).toString()));
        release.set(FIELD_SRFACTIONPARAM,et.get(FIELD_SRFACTIONPARAM));
        SpringContextHolder.getBean(ReleaseHelper.class).linkBug(release);
        return  et;
    }

    @Transactional(rollbackFor = Exception.class)
    public Bug releaseLinkBugbyLeftBug(Bug et) {
        if(et.get(StaticDict.Action__object_type.RELEASE.getValue()) == null) {
            return et;
        }
        Release release = new Release();
        release.setId(Long.parseLong(et.get(StaticDict.Action__object_type.RELEASE.getValue()).toString()));
        release.set(FIELD_SRFACTIONPARAM,et.get(FIELD_SRFACTIONPARAM));
        SpringContextHolder.getBean(ReleaseHelper.class).linkBugbyLeftBug(release);
        return  et;
    }

    @Transactional(rollbackFor = Exception.class)
    public Bug releaaseBatchUnlinkBug(Bug et) {
        throw new RuntimeException("未实现");
    }

    @Transactional(rollbackFor = Exception.class)
    public Bug toStory(Bug et) {
        throw new RuntimeException("未实现");
    }
}
