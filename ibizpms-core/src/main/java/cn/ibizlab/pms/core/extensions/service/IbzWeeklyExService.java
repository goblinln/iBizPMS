package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.report.service.impl.IbzWeeklyServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.report.domain.IbzWeekly;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[周报] 自定义服务对象
 */
@Slf4j
@Primary
@Service("IbzWeeklyExService")
public class IbzWeeklyExService extends IbzWeeklyServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [CreateEveryWeekReport:定时生成每周周报] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzWeekly createEveryWeekReport(IbzWeekly et) {
        return super.createEveryWeekReport(et);
    }
    /**
     * [CreateGetLastWeekPlanAndWork:获取上周周报的下周计划] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzWeekly createGetLastWeekPlanAndWork(IbzWeekly et) {
        return super.createGetLastWeekPlanAndWork(et);
    }
    /**
     * [EditGetLastWeekTaskAndComTask:编辑获取上周计划完成任务和本周已完成任务] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzWeekly editGetLastWeekTaskAndComTask(IbzWeekly et) {
        return super.editGetLastWeekTaskAndComTask(et);
    }
    /**
     * [HaveRead:已读] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzWeekly haveRead(IbzWeekly et) {
        return super.haveRead(et);
    }
    /**
     * [JugThisWeekCreateWeekly:判断本周是否创建过周报] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzWeekly jugThisWeekCreateWeekly(IbzWeekly et) {
        return super.jugThisWeekCreateWeekly(et);
    }
    /**
     * [PushUserWeekly:定时推送待阅提醒用户周报提交] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzWeekly pushUserWeekly(IbzWeekly et) {
        return super.pushUserWeekly(et);
    }
    /**
     * [Submit:提交] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzWeekly submit(IbzWeekly et) {
        return super.submit(et);
    }
}

