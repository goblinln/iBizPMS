package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.zentao.service.impl.ProductPlanServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.zentao.domain.ProductPlan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[产品计划] 自定义服务对象
 */
@Slf4j
@Primary
@Service("ProductPlanExService")
public class ProductPlanExService extends ProductPlanServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [BatchUnlinkBug:批量解除关联Bug] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public ProductPlan batchUnlinkBug(ProductPlan et) {
        return super.batchUnlinkBug(et);
    }
    /**
     * [BatchUnlinkStory:批量解除关联需求] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public ProductPlan batchUnlinkStory(ProductPlan et) {
        return super.batchUnlinkStory(et);
    }
    /**
     * [EeActivePlan:EE激活计划] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public ProductPlan eeActivePlan(ProductPlan et) {
        return super.eeActivePlan(et);
    }
    /**
     * [EeCancelPlan:EE取消计划] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public ProductPlan eeCancelPlan(ProductPlan et) {
        return super.eeCancelPlan(et);
    }
    /**
     * [EeClosePlan:EE关闭计划] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public ProductPlan eeClosePlan(ProductPlan et) {
        return super.eeClosePlan(et);
    }
    /**
     * [EeFinishPlan:EE完成计划] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public ProductPlan eeFinishPlan(ProductPlan et) {
        return super.eeFinishPlan(et);
    }
    /**
     * [EePausePlan:EE暂停计划] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public ProductPlan eePausePlan(ProductPlan et) {
        return super.eePausePlan(et);
    }
    /**
     * [EeRestartPlan:继续计划] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public ProductPlan eeRestartPlan(ProductPlan et) {
        return super.eeRestartPlan(et);
    }
    /**
     * [EeStartPlan:EE开始计划] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public ProductPlan eeStartPlan(ProductPlan et) {
        return super.eeStartPlan(et);
    }
    /**
     * [ImportPlanTemplet:导入计划模板] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public ProductPlan importPlanTemplet(ProductPlan et) {
        return super.importPlanTemplet(et);
    }
    /**
     * [LinkBug:关联Bug] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public ProductPlan linkBug(ProductPlan et) {
        return super.linkBug(et);
    }
    /**
     * [LinkStory:关联需求] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public ProductPlan linkStory(ProductPlan et) {
        return super.linkStory(et);
    }
    /**
     * [LinkTask:关联任务] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public ProductPlan linkTask(ProductPlan et) {
        return super.linkTask(et);
    }
    /**
     * [UnlinkBug:解除关联Bug] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public ProductPlan unlinkBug(ProductPlan et) {
        return super.unlinkBug(et);
    }
    /**
     * [UnlinkStory:解除关联需求] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public ProductPlan unlinkStory(ProductPlan et) {
        return super.unlinkStory(et);
    }
}

