package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.zentao.service.impl.TaskServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.zentao.domain.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[任务] 自定义服务对象
 */
@Slf4j
@Primary
@Service("TaskExService")
public class TaskExService extends TaskServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [Activate:激活] 行为扩展：激活完成、取消、关闭的任务
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task activate(Task et) {
        return super.activate(et);
    }
    /**
     * [AssignTo:指派/转交] 行为扩展：单人任务指派任务 & 多人任务时转交任务
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task assignTo(Task et) {
        return super.assignTo(et);
    }
    /**
     * [Cancel:取消] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task cancel(Task et) {
        return super.cancel(et);
    }
    /**
     * [Close:关闭] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task close(Task et) {
        return super.close(et);
    }
    /**
     * [ConfirmStoryChange:需求变更确认] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task confirmStoryChange(Task et) {
        return super.confirmStoryChange(et);
    }
    /**
     * [CreateByCycle:创建周期任务] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task createByCycle(Task et) {
        return super.createByCycle(et);
    }
    /**
     * [CreateCycleTasks:创建周期任务] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task createCycleTasks(Task et) {
        return super.createCycleTasks(et);
    }
    /**
     * [Delete:删除任务] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task delete(Task et) {
        return super.delete(et);
    }
    /**
     * [DeleteEstimate:删除工时] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task deleteEstimate(Task et) {
        return super.deleteEstimate(et);
    }
    /**
     * [EditEstimate:编辑工时] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task editEstimate(Task et) {
        return super.editEstimate(et);
    }
    /**
     * [Finish:完成] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task finish(Task et) {
        return super.finish(et);
    }
    /**
     * [GetNextTeamUser:获取下一个团队成员(完成)] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task getNextTeamUser(Task et) {
        return super.getNextTeamUser(et);
    }
    /**
     * [GetTeamUserLeftActivity:获取团队成员剩余工时（激活）] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task getTeamUserLeftActivity(Task et) {
        return super.getTeamUserLeftActivity(et);
    }
    /**
     * [GetTeamUserLeftStart:获取团队成员剩余工时（开始或继续）] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task getTeamUserLeftStart(Task et) {
        return super.getTeamUserLeftStart(et);
    }
    /**
     * [LinkPlan:关联计划] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task linkPlan(Task et) {
        return super.linkPlan(et);
    }
    /**
     * [OtherUpdate:其他更新] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task otherUpdate(Task et) {
        return super.otherUpdate(et);
    }
    /**
     * [Pause:暂停] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task pause(Task et) {
        return super.pause(et);
    }
    /**
     * [RecordEstimate:工时录入] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task recordEstimate(Task et) {
        return super.recordEstimate(et);
    }
    /**
     * [RecordTimZeroLeftAfterContinue:继续任务时填入预计剩余为0设置为进行中] 行为扩展：继续任务
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task recordTimZeroLeftAfterContinue(Task et) {
        return super.recordTimZeroLeftAfterContinue(et);
    }
    /**
     * [RecordTimateZeroLeft:预计剩余为0进行中] 行为扩展：激活和填入工时
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task recordTimateZeroLeft(Task et) {
        return super.recordTimateZeroLeft(et);
    }
    /**
     * [RecordTimateZeroLeftAfterStart:开始任务时填入预计剩余为0设为进行中] 行为扩展：开始任务
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task recordTimateZeroLeftAfterStart(Task et) {
        return super.recordTimateZeroLeftAfterStart(et);
    }
    /**
     * [Restart:继续] 行为扩展：重启挂起的任务
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task restart(Task et) {
        return super.restart(et);
    }
    /**
     * [SendMessage:行为] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task sendMessage(Task et) {
        return super.sendMessage(et);
    }
    /**
     * [SendMsgPreProcess:发送消息前置处理] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task sendMsgPreProcess(Task et) {
        return super.sendMsgPreProcess(et);
    }
    /**
     * [Start:开始] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task start(Task et) {
        return super.start(et);
    }
    /**
     * [TaskForward:检查多人任务操作权限] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Task taskForward(Task et) {
        return super.taskForward(et);
    }
}

