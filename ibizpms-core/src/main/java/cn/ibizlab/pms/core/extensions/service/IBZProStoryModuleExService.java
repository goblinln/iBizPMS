package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibizpro.service.impl.IBZProStoryModuleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.ibizpro.domain.IBZProStoryModule;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[需求模块] 自定义服务对象
 */
@Slf4j
@Primary
@Service("IBZProStoryModuleExService")
public class IBZProStoryModuleExService extends IBZProStoryModuleServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [SyncFromIBIZ:同步Ibz平台模块] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IBZProStoryModule syncFromIBIZ(IBZProStoryModule et) {
        return super.syncFromIBIZ(et);
    }
}

