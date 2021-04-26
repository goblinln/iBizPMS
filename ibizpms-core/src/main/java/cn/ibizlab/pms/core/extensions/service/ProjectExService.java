package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.zentao.service.impl.ProjectServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.zentao.domain.Project;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
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

    /**
     * [Activate:激活] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Project activate(Project et) {
        return super.activate(et);
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
        return super.close(et);
    }
    /**
     * [ImportPlanStories:项目关联需求-按计划关联] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Project importPlanStories(Project et) {
        return super.importPlanStories(et);
    }
    /**
     * [LinkStory:关联需求] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Project linkStory(Project et) {
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
        return super.manageMembers(et);
    }
    /**
     * [PmsEeProjectAllTaskCount:项目立项任务快速分组计数器] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Project pmsEeProjectAllTaskCount(Project et) {
        return super.pmsEeProjectAllTaskCount(et);
    }
    /**
     * [PmsEeProjectTodoTaskCount:项目立项待办任务快速分组计数器] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Project pmsEeProjectTodoTaskCount(Project et) {
        return super.pmsEeProjectTodoTaskCount(et);
    }
    /**
     * [Putoff:延期] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Project putoff(Project et) {
        return super.putoff(et);
    }
    /**
     * [Start:开始] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Project start(Project et) {
        return super.start(et);
    }
    /**
     * [Suspend:挂起] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Project suspend(Project et) {
        return super.suspend(et);
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
        return super.unlinkStory(et);
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
}

