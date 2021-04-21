package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.zentao.service.impl.BugServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.zentao.domain.Bug;
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

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
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

