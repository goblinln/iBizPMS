package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.ibiz.service.impl.SysUpdateLogServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.ibiz.domain.SysUpdateLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[更新日志] 自定义服务对象
 */
@Slf4j
@Primary
@Service("SysUpdateLogExService")
public class SysUpdateLogExService extends SysUpdateLogServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [GetLastUpdateInfo:获取最新更新信息] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public SysUpdateLog getLastUpdateInfo(SysUpdateLog et) {
        return super.getLastUpdateInfo(et);
    }
}

