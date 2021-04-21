package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.uaa.service.impl.SysUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.uaa.domain.SysUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

/**
 * 实体[系统用户] 自定义服务对象
 */
@Slf4j
@Primary
@Service("SysUserExService")
public class SysUserExService extends SysUserServiceImpl {


    /**
     * [ChangePwd:修改密码] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public SysUser changePwd(SysUser et) {
        return super.changePwd(et);
    }
}

