package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.zentao.service.impl.UserTplServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.zentao.domain.UserTpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[用户模板] 自定义服务对象
 */
@Slf4j
@Primary
@Service("UserTplExService")
public class UserTplExService extends UserTplServiceImpl {

    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [HasDeleted:删除] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public UserTpl hasDeleted(UserTpl et) {
        return super.hasDeleted(et);
    }
}

