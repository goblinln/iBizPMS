package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.util.ibizzentao.common.ChangeUtil;
import cn.ibizlab.pms.core.zentao.domain.Action;
import cn.ibizlab.pms.core.zentao.domain.File;
import cn.ibizlab.pms.core.zentao.domain.History;
import cn.ibizlab.pms.core.zentao.service.*;
import cn.ibizlab.pms.core.zentao.service.impl.BugServiceImpl;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.helper.CachedBeanCopier;
import cn.ibizlab.pms.util.security.AuthenticationUser;
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
        updateObjectID(et, files, "");
        // 创建日志
        createHis(et, null,  StaticDict.Action__type.OPENED.getValue(), "","");
        // 发送待办消息
        sendTodoOrToread(et, noticeusers, StaticDict.Action__type.OPENED.getText(), true);

        return true;
    }

    /**
     * 更新附件
     *
     * @param et
     * @param files
     * @param extra
     */
    private void updateObjectID(Bug et, String files, String extra){
        File file = new File();
        file.set("files",files);
        file.setObjectid(et.getId());
        file.setObjecttype(StaticDict.File__object_type.BUG.getValue());
        file.setExtra(extra);
        iFileService.updateObjectID(file);
    }

    /**
     * 创建历史记录
     *
     * @param et
     * @param histories
     * @param actions
     * @param comment
     * @param extra
     */
    private void createHis(Bug et, List<History> histories, String actions, String comment, String extra) {
        Action action = new Action();
        action.setObjecttype(StaticDict.Action__object_type.BUG.getValue());
        action.setObjectid(et.getId());
        action.setAction(actions);
        action.setComment(comment);
        action.setExtra(extra);
        action.setHistorys(histories);
        iActionService.createHis(action);
    }

    /**
     * 发送待办或者待阅消息
     *
     * @param et
     * @param noticeusers
     * @param actiontextname
     */
    private void sendTodoOrToread(Bug et, String noticeusers, String actiontextname, boolean todo) {
        Action actionTodoOrToread = new Action();
        actionTodoOrToread.setObjectid(et.getId());
        actionTodoOrToread.set("name", et.getTitle());
        actionTodoOrToread.set("noticeusers", noticeusers);
        actionTodoOrToread.set("ccuser", et.getMailto());
        actionTodoOrToread.set("touser", et.getAssignedto());
        actionTodoOrToread.set("logicname", "Bug");
        actionTodoOrToread.setObjecttype(StaticDict.Action__object_type.BUG.getValue());
        actionTodoOrToread.set("path", "bugs");
        actionTodoOrToread.set("actiontextname", actiontextname);
        if(todo) {
            iActionService.sendTodo(actionTodoOrToread);
        }else {
            iActionService.sendToread(actionTodoOrToread);
        }
    }

    private void sendMarkDone(Bug et, String touser, String actiontextname) {
        Action actionMarkDone = new Action();
        actionMarkDone.setObjectid(et.getId());
        actionMarkDone.set("name", et.getTitle());
        actionMarkDone.set("touser", touser);
        actionMarkDone.set("logicname", "Bug");
        actionMarkDone.setObjecttype(StaticDict.Action__object_type.BUG.getValue());
        actionMarkDone.set("path", "bugs");
        actionMarkDone.set("actiontextname", actiontextname);
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
        updateObjectID(et, files, "");

        List<History> changes = ChangeUtil.diff(old, et,null,null,diffAttrs);
        if (changes.size() > 0 || StringUtils.isNotBlank(comment)) {

            String strActionText = StaticDict.Action__type.EDITED.getText();
            String strAction = StaticDict.Action__type.EDITED.getValue();
            if (changes.size() == 0) {
                strAction = StaticDict.Action__type.COMMENTED.getValue();
                strActionText = StaticDict.Action__type.COMMENTED.getText();
            }
            // 发送待阅
            sendTodoOrToread(et, noticeusers, strActionText, false);
            // 创建日志
            createHis(et, changes,  strAction, comment,"");
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
        updateObjectID(et, files, "");
        List<History> changes = ChangeUtil.diff(old, et);
        if (!et.getAssignedto().equals(old.getAssignedto())){
            // 已读
            sendMarkDone(et, old.getAssignedto(), StaticDict.Action__type.ASSIGNED.getText());
            // 发送待办消息
            sendTodoOrToread(et, noticeusers, StaticDict.Action__type.ASSIGNED.getText(), true);
        }
        // 创建日志
        createHis(et, changes,  StaticDict.Action__type.ASSIGNED.getValue(), comment,et.getAssignedto());
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

