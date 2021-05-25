package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibiz.service.impl.IbzProTestTaskActionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.ibiz.domain.IbzProTestTaskAction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[测试单日志] 自定义服务对象
 */
@Slf4j
@Primary
@Service("IbzProTestTaskActionExService")
public class IbzProTestTaskActionExService extends IbzProTestTaskActionServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [CreateHis:创建历史日志] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzProTestTaskAction createHis(IbzProTestTaskAction et) {
        return super.createHis(et);
    }
    /**
     * [EditComment:编辑备注信息] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzProTestTaskAction editComment(IbzProTestTaskAction et) {
        return super.editComment(et);
    }
    /**
     * [ManagePmsEe:Pms企业专用] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzProTestTaskAction managePmsEe(IbzProTestTaskAction et) {
        return super.managePmsEe(et);
    }
    /**
     * [SendMarkDone:已读] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzProTestTaskAction sendMarkDone(IbzProTestTaskAction et) {
        return super.sendMarkDone(et);
    }
    /**
     * [SendTodo:发送待办] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzProTestTaskAction sendTodo(IbzProTestTaskAction et) {
        return super.sendTodo(et);
    }
    /**
     * [SendToread:发送待阅] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzProTestTaskAction sendToread(IbzProTestTaskAction et) {
        return super.sendToread(et);
    }
}

