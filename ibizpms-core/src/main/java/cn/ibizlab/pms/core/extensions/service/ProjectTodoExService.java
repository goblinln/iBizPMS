package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibizpro.service.impl.ProjectTodoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.ibizpro.domain.ProjectTodo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[项目其他活动] 自定义服务对象
 */
@Slf4j
@Primary
@Service("ProjectTodoExService")
public class ProjectTodoExService extends ProjectTodoServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [Activate:Activate] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public ProjectTodo activate(ProjectTodo et) {
        return super.activate(et);
    }
    /**
     * [AssignTo:AssignTo] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public ProjectTodo assignTo(ProjectTodo et) {
        return super.assignTo(et);
    }
    /**
     * [Close:Close] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public ProjectTodo close(ProjectTodo et) {
        return super.close(et);
    }
    /**
     * [CreateCycle:定时创建周期] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public ProjectTodo createCycle(ProjectTodo et) {
        return super.createCycle(et);
    }
    /**
     * [Finish:Finish] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public ProjectTodo finish(ProjectTodo et) {
        return super.finish(et);
    }
    /**
     * [SendMessage:行为] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public ProjectTodo sendMessage(ProjectTodo et) {
        return super.sendMessage(et);
    }
    /**
     * [SendMsgPreProcess:发送消息前置处理] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public ProjectTodo sendMsgPreProcess(ProjectTodo et) {
        return super.sendMsgPreProcess(et);
    }
}

