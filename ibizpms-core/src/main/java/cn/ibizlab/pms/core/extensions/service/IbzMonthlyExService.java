package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.report.service.impl.IbzMonthlyServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.report.domain.IbzMonthly;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[月报] 自定义服务对象
 */
@Slf4j
@Primary
@Service("IbzMonthlyExService")
public class IbzMonthlyExService extends IbzMonthlyServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [CreateGetInfo:新建时获取信息] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzMonthly createGetInfo(IbzMonthly et) {
        return super.createGetInfo(et);
    }
    /**
     * [CreateUserMonthly:定时生成用户月报] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzMonthly createUserMonthly(IbzMonthly et) {
        return super.createUserMonthly(et);
    }
    /**
     * [EditGetCompleteTask:编辑时获取完成任务] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzMonthly editGetCompleteTask(IbzMonthly et) {
        return super.editGetCompleteTask(et);
    }
    /**
     * [HaveRead:已读] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzMonthly haveRead(IbzMonthly et) {
        return super.haveRead(et);
    }
    /**
     * [PushUserMonthly:定时推送待阅提醒用户月报] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzMonthly pushUserMonthly(IbzMonthly et) {
        return super.pushUserMonthly(et);
    }
    /**
     * [Submit:提交] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzMonthly submit(IbzMonthly et) {
        return super.submit(et);
    }
}

