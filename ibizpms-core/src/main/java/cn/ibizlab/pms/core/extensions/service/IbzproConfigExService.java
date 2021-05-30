package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibizpro.service.impl.IbzproConfigServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.ibizpro.domain.IbzproConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[系统配置表] 自定义服务对象
 */
@Slf4j
@Primary
@Service("IbzproConfigExService")
public class IbzproConfigExService extends IbzproConfigServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [GetSystemConfig:获取系统配置] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public IbzproConfig getSystemConfig(IbzproConfig et) {
        return super.getSystemConfig(et);
    }
}

