package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.zentao.domain.Action;
import cn.ibizlab.pms.core.zentao.domain.File;
import cn.ibizlab.pms.core.zentao.domain.History;
import cn.ibizlab.pms.core.zentao.service.*;
import cn.ibizlab.pms.core.zentao.service.impl.BugServiceImpl;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.zentao.domain.Bug;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    IHistoryService iHistoryService;

    String[] diffAttrs = {"steps"};

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
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
        File file = new File();
        file.set("files",files);
        file.setObjectid(et.getId());
        file.setObjecttype(StaticDict.File__object_type.BUG.getValue());
        file.setExtra("");
        iFileService.updateObjectID(file);

        // 创建日志
        Action action = new Action();
        action.setObjecttype(StaticDict.Action__object_type.BUG.getValue());
        action.setObjectid(et.getId());
        action.setAction(StaticDict.Action__type.OPENED.getValue());
        action.setComment("");
        action.setExtra("");
        iActionService.createHis(action);

        // 发送消息
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
        File file = new File();
        file.set("files",files);
        file.setObjectid(et.getId());
        file.setObjecttype(StaticDict.File__object_type.BUG.getValue());
        file.setExtra("");
        iFileService.updateObjectID(file);

        List<History> changes = ChangeUtil.diff(old, et,null,null,diffAttrs);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {

            String strActionText = StaticDict.Action__type.EDITED.getText();
            String strAction = StaticDict.Action__type.EDITED.getValue();
            if (changes.size() == 0) {
                strAction = StaticDict.Action__type.COMMENTED.getValue();
                strActionText = StaticDict.Action__type.COMMENTED.getText();
            }
            // actionHelper.sendToread(et.getId(), et.getTitle(), noticeusers, et.getAssignedto(), et.getMailto(), "Bug", StaticDict.Action__object_type.BUG.getValue(), "bugs", strActionText);
            // 创建日志
            Action action = new Action();
            action.setObjecttype(StaticDict.Action__object_type.BUG.getValue());
            action.setObjectid(et.getId());
            action.setAction(strAction);
            action.setComment(comment);
            action.setExtra("");
            action.setHistorys(changes);
            iActionService.createHis(action);

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
        return super.activate(et);
    }
    /**
     * [AssignTo:指派] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Bug assignTo(Bug et) {
        return super.assignTo(et);
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
        return super.buildLinkBug(et);
    }
    /**
     * [BuildUnlinkBug:版本解除关联Bug] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Bug buildUnlinkBug(Bug et) {
        return super.buildUnlinkBug(et);
    }
    /**
     * [Close:关闭] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Bug close(Bug et) {
        return super.close(et);
    }
    /**
     * [Confirm:确认] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Bug confirm(Bug et) {
        return super.confirm(et);
    }
    /**
     * [LinkBug:关联Bug] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Bug linkBug(Bug et) {
        return super.linkBug(et);
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
        return super.releaseLinkBugbyBug(et);
    }
    /**
     * [ReleaseLinkBugbyLeftBug:关联Bug（遗留Bug）] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Bug releaseLinkBugbyLeftBug(Bug et) {
        return super.releaseLinkBugbyLeftBug(et);
    }
    /**
     * [ReleaseUnLinkBugbyLeftBug:移除关联Bug（遗留Bug）] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Bug releaseUnLinkBugbyLeftBug(Bug et) {
        return super.releaseUnLinkBugbyLeftBug(et);
    }
    /**
     * [ReleaseUnlinkBug:解除关联Bug] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Bug releaseUnlinkBug(Bug et) {
        return super.releaseUnlinkBug(et);
    }
    /**
     * [Resolve:解决] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Bug resolve(Bug et) {
        return super.resolve(et);
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
        return super.unlinkBug(et);
    }
}

