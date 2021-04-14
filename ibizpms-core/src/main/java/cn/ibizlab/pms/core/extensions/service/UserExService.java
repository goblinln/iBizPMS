package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.zentao.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.zentao.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[用户] 自定义服务对象
 */
@Slf4j
@Primary
@Service("UserExService")
public class UserExService extends UserServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [SyncAccount:同步账号] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public User syncAccount(User et) {
        return super.syncAccount(et);
    }
}

