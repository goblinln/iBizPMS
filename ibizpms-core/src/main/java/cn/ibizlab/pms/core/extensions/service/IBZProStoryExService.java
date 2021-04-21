package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibizpro.service.impl.IBZProStoryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.ibizpro.domain.IBZProStory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[需求] 自定义服务对象
 */
@Slf4j
@Primary
@Service("IBZProStoryExService")
public class IBZProStoryExService extends IBZProStoryServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [SyncFromIBIZ:同步Ibz平台需求] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IBZProStory syncFromIBIZ(IBZProStory et) {
        return super.syncFromIBIZ(et);
    }
}

