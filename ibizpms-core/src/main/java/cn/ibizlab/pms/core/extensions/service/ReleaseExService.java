package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.zentao.service.impl.ReleaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.zentao.domain.Release;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[发布] 自定义服务对象
 */
@Slf4j
@Primary
@Service("ReleaseExService")
public class ReleaseExService extends ReleaseServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [Activate:状态变更（激活）] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Release activate(Release et) {
        return super.activate(et);
    }
    /**
     * [BatchUnlinkBug:批量解除关联Bug] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Release batchUnlinkBug(Release et) {
        return super.batchUnlinkBug(et);
    }
    /**
     * [ChangeStatus:状态变更] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Release changeStatus(Release et) {
        return super.changeStatus(et);
    }
    /**
     * [LinkBug:关联Bug] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Release linkBug(Release et) {
        return super.linkBug(et);
    }
    /**
     * [LinkBugbyBug:关联Bug（解决Bug）] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Release linkBugbyBug(Release et) {
        return super.linkBugbyBug(et);
    }
    /**
     * [LinkBugbyLeftBug:关联Bug（遗留Bug）] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Release linkBugbyLeftBug(Release et) {
        return super.linkBugbyLeftBug(et);
    }
    /**
     * [LinkStory:关联需求] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Release linkStory(Release et) {
        return super.linkStory(et);
    }
    /**
     * [OneClickRelease:一键发布] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Release oneClickRelease(Release et) {
        return super.oneClickRelease(et);
    }
    /**
     * [Terminate:状态变更（停止维护）] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Release terminate(Release et) {
        return super.terminate(et);
    }
    /**
     * [UnlinkBug:解除关联Bug] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Release unlinkBug(Release et) {
        return super.unlinkBug(et);
    }
}

