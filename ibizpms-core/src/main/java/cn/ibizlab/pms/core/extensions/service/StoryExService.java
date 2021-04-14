package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.zentao.service.impl.StoryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.zentao.domain.Story;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
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

    /**
     * [Activate:激活] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story activate(Story et) {
        return super.activate(et);
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
        return super.assignTo(et);
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
        return super.buildLinkStory(et);
    }
    /**
     * [BuildUnlinkStory:版本解除关联需求] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story buildUnlinkStory(Story et) {
        return super.buildUnlinkStory(et);
    }
    /**
     * [Change:变更] 行为扩展：需求变更
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story change(Story et) {
        return super.change(et);
    }
    /**
     * [Close:关闭] 行为扩展：需求关闭
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story close(Story et) {
        return super.close(et);
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
    /**
     * [GetStorySpec:获取需求描述] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story getStorySpec(Story et) {
        return super.getStorySpec(et);
    }
    /**
     * [ImportPlanStories:项目关联需求-按计划关联] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story importPlanStories(Story et) {
        return super.importPlanStories(et);
    }
    /**
     * [LinkStory:计划关联需求] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story linkStory(Story et) {
        return super.linkStory(et);
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
        return super.projectLinkStory(et);
    }
    /**
     * [ProjectUnlinkStory:项目解除关联需求] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story projectUnlinkStory(Story et) {
        return super.projectUnlinkStory(et);
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
        return super.releaseLinkStory(et);
    }
    /**
     * [ReleaseUnlinkStory:发布解除关联需求] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Story releaseUnlinkStory(Story et) {
        return super.releaseUnlinkStory(et);
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
        return super.review(et);
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
        return super.unlinkStory(et);
    }
}

