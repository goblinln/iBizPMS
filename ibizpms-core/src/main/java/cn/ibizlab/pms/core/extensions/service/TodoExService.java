package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.zentao.service.impl.TodoServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.zentao.domain.Todo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[待办] 自定义服务对象
 */
@Slf4j
@Primary
@Service("TodoExService")
public class TodoExService extends TodoServiceImpl {

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
    public Todo activate(Todo et) {
        return super.activate(et);
    }
    /**
     * [AssignTo:AssignTo] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Todo assignTo(Todo et) {
        return super.assignTo(et);
    }
    /**
     * [Close:Close] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Todo close(Todo et) {
        return super.close(et);
    }
    /**
     * [CreateCycle:定时创建周期] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Todo createCycle(Todo et) {
        return super.createCycle(et);
    }
    /**
     * [Finish:Finish] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Todo finish(Todo et) {
        return super.finish(et);
    }
    /**
     * [SendMessage:行为] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Todo sendMessage(Todo et) {
        return super.sendMessage(et);
    }
    /**
     * [SendMsgPreProcess:发送消息前置处理] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Todo sendMsgPreProcess(Todo et) {
        return super.sendMsgPreProcess(et);
    }
}

